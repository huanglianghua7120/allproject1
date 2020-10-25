package saaf.common.fmw.schedule.utils;


import java.util.HashMap;
import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.base.sie.common.utils.RestfulClientUtils;
import com.yhg.redis.framework.WSSecurityPolicy;


@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class RunStatefulJob implements InterruptableJob  {
    private static final Logger logger = LoggerFactory.getLogger(RunStatefulJob.class);

    public RunStatefulJob() {
        super();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            // 0：忽略， -1：一直尝试，直到成功 ， 9：尝试9次
            int failAttemptFrequency =
                Integer.parseInt("" + getArguments(context, "failAttemptFrequency") != null && getArguments(context, "failAttemptFrequency").toString().length() > 0 ?
                                 getArguments(context, "failAttemptFrequency").toString() : "0");
            // -1：不作限制， 5：超时时间5秒
            int timeout =
                Integer.parseInt("" + getArguments(context, "timeout") != null && getArguments(context, "timeout").toString().length() > 0 ? getArguments(context, "timeout").toString() :
                                 "-1");
            if (failAttemptFrequency < 0) {
                //如果失败尝试为 一直尝试，直到成功  则默认最多尝试 99999 次
                failAttemptFrequency = 99999;
            }
            if (timeout <= 0) {
                //如果没有设置超时参数， 则强制默认 超时时间为 60 秒
                timeout = 60 * 1000;
            } else {
                timeout = timeout * 1000;
            }
            String jobType = "" + getArguments(context, "jobType");
            if (jobType.equalsIgnoreCase("webservice")) {
                run_webservice(context, timeout, failAttemptFrequency);
            } else if (jobType.equalsIgnoreCase("java")) {
                //TODO
            } else if (jobType.equalsIgnoreCase("package")) {
                //TODO
            } else {
                logger.error("******** 调度执行，webservice 任务执行失败" + "未知任务类型（jobType）=" + jobType + "，jobName=" + context.getJobDetail().getKey().getName());
            }
        } catch (Exception e) {
            logger.error("******** 调度执行，webservice 任务执行失败" + "，jobName=" + context.getJobDetail().getKey().getName() + ", exception=" + getDetailErrorMessage(e));
            throw new JobExecutionException(e);
        }
    }

    private void run_webservice(JobExecutionContext context, int socketTimeout, int retryCount) throws Exception {
        String executableName = ("" + getArguments(context, "executableName")).trim();
        String method = "" + getArguments(context, "method");
        logger.info("******** 调度执行，webservice 任务，executableName=" + executableName + ", method=" + method);
        //url
        Map<String, String> atUrlParamsMap = new HashMap<String, String>();
        //header
        Map<String, String> atHeaderParamsMap = new HashMap<String, String>();
        //body
        Map<String, Object> atBodyParamsMap = new HashMap<String, Object>();
        //获取运行参数
        for (Object item : context.getJobDetail().getJobDataMap().keySet().toArray()) {
            String paramRegion = "" + ((HashMap<String, Object>)context.getJobDetail().getJobDataMap().get(item)).keySet().toArray()[0];
            String defaultValue = "" + getArguments(context, "" + item);
            String paramName = "" + item;
            if (paramRegion.equalsIgnoreCase("HEAD")) {
                atHeaderParamsMap.put(paramName, defaultValue);
            } else if (paramRegion.equalsIgnoreCase("URL")) {
                atUrlParamsMap.put(paramName, defaultValue);
            } else if (paramRegion.equalsIgnoreCase("BODY")) {
                JSONObject paramObj = JSONObject.parseObject(defaultValue);
                for (Object key : paramObj.keySet().toArray()) {
                    atBodyParamsMap.put(key.toString(), paramObj.get(key.toString()).toString());
                }
            }
        }
        if (method != null && method.equalsIgnoreCase("get")) {
            doWebserviceGet(executableName, atUrlParamsMap, atHeaderParamsMap, socketTimeout, retryCount);
        } else if (method != null && method.equalsIgnoreCase("post")) {
            doWebservicePost(executableName, atUrlParamsMap, atHeaderParamsMap, atBodyParamsMap, socketTimeout, retryCount);
        } else {
            logger.info("******** 调度执行，未知的 webservice method，" + " method=" + method);
            throw new Exception("******** 调度执行，未知的 webservice method，" + " method=" + method);
        }
    }

    private void doWebserviceGet(String url, Map<String, String> atUrlParamsMap, Map<String, String> atHeaderParamsMap, int socketTimeout, int retryCount) throws Exception {
        logger.info("******** 调度执行，<doWebserviceGet> 方法，参数：<url>=" + url + ", <atUrlParamsMap>=" + JSONObject.toJSONString(atUrlParamsMap) + ", <atHeaderParamsMap>=" +
                    JSONObject.toJSONString(atHeaderParamsMap) + ", <socketTimeout>=" + socketTimeout + ", <retryCount>=" + retryCount);
        do {
            WSSecurityPolicy wsSecurityPolicy = (WSSecurityPolicy)SaafToolUtils.context.getBean("wsSecurityPolicy");
            String tokenValue = wsSecurityPolicy.getTokenByKey("schedule_Token");
            if (null == tokenValue || tokenValue.equals("")) {
                tokenValue = wsSecurityPolicy.generateStaticToken("schedule_Token", 0);
            }
            atHeaderParamsMap.put("TokenCode", tokenValue);
            atHeaderParamsMap.put("TokenKey", "schedule_Token");
            String result = RestfulClientUtils.restfulGet(url, atHeaderParamsMap, socketTimeout);
            logger.info("******** 调度执行，<doWebserviceGet> 方法，结果：<result>=" + result);
            //尝试解析 执行返回的结果，并尝试判断 其‘status’是否为‘S’（即 执行成功）
            try {
                JSONObject resultJsonObj = JSONObject.parseObject(result);
                if (resultJsonObj.get("status").toString().equalsIgnoreCase("S")) {
                    return;
                } else {
                    if (retryCount == 0) {
                        throw new JobExecutionException();
                    }
                }
            } catch (JobExecutionException e) {
                throw e;
            } catch (Exception ignore) {
                //被默认成功
                return;
            }
        } while (retryCount-- > 0);
    }

    private void doWebservicePost(String url, Map<String, String> atUrlParamsMap, Map<String, String> atHeaderParamsMap, Map<String, Object> atBodyParamsMap, int socketTimeout,
                                  int retryCount) throws Exception {
        logger.info("******** 调度执行，<doWebservicePost> 方法，参数：<url>=" + url + ", <atUrlParamsMap>=" + JSONObject.toJSONString(atUrlParamsMap) + ", <atHeaderParamsMap>=" +
                    JSONObject.toJSONString(atHeaderParamsMap) + ", <atBodyParamsMap>=" + JSONObject.toJSONString(atBodyParamsMap) + ", <socketTimeout>=" + socketTimeout +
                    ", <retryCount>=" + retryCount);
        do {
            WSSecurityPolicy wsSecurityPolicy = (WSSecurityPolicy)SaafToolUtils.context.getBean("wsSecurityPolicy");
            String tokenValue = wsSecurityPolicy.getTokenByKey("schedule_Token");
            if (null == tokenValue || tokenValue.equals("")) {
                tokenValue = wsSecurityPolicy.generateStaticToken("schedule_Token", 0);
            }
            atHeaderParamsMap.put("TokenCode", tokenValue);
            atHeaderParamsMap.put("TokenKey", "schedule_Token");
            String result = RestfulClientUtils.restfulPostFormParam(url, atBodyParamsMap, atHeaderParamsMap, socketTimeout);
            logger.info("******** 调度执行，<doWebservicePost> 方法，结果：<result>=" + result);
            //尝试解析 执行返回的结果，并尝试判断 其‘status’是否为‘S’（即 执行成功）
            try {
                JSONObject resultJsonObj = JSONObject.parseObject(result);
                if (resultJsonObj.get("status").toString().equalsIgnoreCase("S")) {
                    return;
                } else {
                    if (retryCount == 0) {
                        throw new JobExecutionException();
                    }
                } 
            } catch (Exception ignore) {

                logger.error("******** 调度执行异常，<doWebservicePost> 方法",ignore);
                //被默认成功
                return;
            }
        } while (retryCount-- > 0);
    }

    private Object getArguments(JobExecutionContext context, String argName) throws Exception {
        Object value = null;
        try {
            value = ((HashMap<String, Object>)context.getJobDetail().getJobDataMap().get(argName)).values().toArray()[0];
        } catch (Exception e) {
            logger.error("******** 调度执行，获取参数失败：指定名称 <" + argName + "> 的参数不存在");
            throw e;
        }
        return value;
    }

    private String getDetailErrorMessage(Exception e) {
        StringBuilder detailMessageOfError = new StringBuilder();
        if (e != null && e.getStackTrace().length > 0) {
            detailMessageOfError = new StringBuilder().append("\r\n").append("Error: ").append(e.getMessage() == null ? "" : e.getMessage()).append("\r\n");
            for (StackTraceElement item : e.getStackTrace()) {
                detailMessageOfError.append("\t").append(item.toString()).append("\r\n");
            }
            if (e.getCause() != null) {
                for (StackTraceElement item : e.getCause().getStackTrace()) {
                    detailMessageOfError.append("Cause By: \r\n").append(item.toString()).append("\r\n");
                }
            }
        }
        return detailMessageOfError.toString();
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        System.out.println("------------Job-interrupted（!）----------------");
    }


}
