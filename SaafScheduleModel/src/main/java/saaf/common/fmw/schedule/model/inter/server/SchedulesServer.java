package saaf.common.fmw.schedule.model.inter.server;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import saaf.common.fmw.schedule.model.entities.SaafJobsEntity_HI;
import saaf.common.fmw.schedule.model.entities.SaafSchedulesEntity_HI;
import saaf.common.fmw.schedule.model.entities.readonly.SaafSchedulesEntity_HI_RO;
import saaf.common.fmw.schedule.model.inter.ISchedules;
import saaf.common.fmw.schedule.utils.MyLogUtils;
import saaf.common.fmw.schedule.utils.Run;
import saaf.common.fmw.schedule.utils.SaafToolUtils;
import saaf.common.fmw.utils.SrmUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;


@Component("schedulesServer")
public class SchedulesServer implements ISchedules, ApplicationContextAware {

    private final Logger log = LoggerFactory.getLogger(SchedulesServer.class);

    private ViewObject saafJobsDAOHI;
    private ViewObject saafSchedulesDAOHI;
    private BaseViewObject saafSchedulesDAOHIRO;

    private Scheduler stdScheduler;

    private ApplicationContext context;

    public SchedulesServer() {
        super();
    }

    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }

    @Resource(name = "saafJobsDAO_HI")
    public void setSaafJobsDAOHI(ViewObject saafJobsDAOHI) {
        this.saafJobsDAOHI = saafJobsDAOHI;
    }

    @Resource(name = "saafSchedulesDAO_HI")
    public void setSaafSchedulesDAOHI(ViewObject saafSchedulesDAOHI) {
        this.saafSchedulesDAOHI = saafSchedulesDAOHI;
    }

    @Resource(name = "saafSchedulesDAO_HI_RO")
    public void setSaafSchedulesDAOHIRO(BaseViewObject saafSchedulesDAOHIRO) {
        this.saafSchedulesDAOHIRO = saafSchedulesDAOHIRO;
    }

    /**
     * 查询请求列表
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public String findRequests(JSONObject parameters, Integer pageIndex, Integer pageRows) {

        //当删除了job时， 则使用该 Job 提交的请求 界面将无法查询并显示，具体可查看sql语句：SaafSchedulesEntity_HI_RO.QUERY_SQL
        log.info("查询请求,参数:parameters==>" + parameters + ", pageIndex==>" + pageIndex + ",pageRows==>" + pageRows);
        BaseViewObject vo = this.saafSchedulesDAOHIRO;
        String sql = SaafSchedulesEntity_HI_RO.QUERY_SQL;
        StringBuffer where = new StringBuffer();
        Map<String, Object> map = new HashMap<String, Object>();
        if ((null != parameters.get("jobName")) && !"".equals(parameters.get("jobName").toString())) {
            where.append(" and sj.JOB_NAME like :varJobName");
            map.put("varJobName", "%" + parameters.get("jobName").toString() + "%");
        }
        if ((null != parameters.get("phaseCode")) && !"".equals(parameters.get("phaseCode").toString())) {
            where.append(" and PHASE_CODE = :varPhaseCode");
            map.put("varPhaseCode", parameters.get("phaseCode").toString());
        }
        if ((null != parameters.get("statusCode")) && !"".equals(parameters.get("statusCode").toString())) {
            where.append(" and STATUS_CODE = :varStatusCode");
            map.put("varStatusCode", parameters.get("statusCode").toString());
        }
        String scheduleType = parameters.getString("scheduleType");
        if (null != scheduleType && !"".equals(scheduleType)) {
            where.append(" and ss.SCHEDULE_TYPE  = :scheduleType");
            map.put("scheduleType", scheduleType);
        }
        if (!"Y".equals(parameters.getString("varIsAdmin"))&&(null != parameters.get("varUserId") && !"".equals(parameters.get("varUserId").toString()))) {
            where.append(" and su.user_id = :varUserId");
            map.put("varUserId", Integer.parseInt(parameters.get("varUserId").toString()));
        }
//        if ((null != parameters.get("varUserName") && !"".equals(parameters.get("varUserName").toString()))) {
//            where.append(" and su.user_name like :varUserName");
//            map.put("varUserName", "%" + parameters.get("varUserName").toString() + "%");
//        }
        if (null != parameters.getInteger("scheduleId") && !"".equals(parameters.getInteger("scheduleId"))) {
            where.append(" and SCHEDULE_ID = :varScheduleId");
            map.put("varScheduleId", Integer.parseInt(parameters.get("scheduleId").toString()));
        }
        //
        if ((null != parameters.get("actualStartDate")) && !"".equals(parameters.get("actualStartDate").toString())) {
            try {
                map.put("actualStartDate", SToolUtils.string2DateTime(parameters.get("actualStartDate").toString(), "yyyy-MM-dd HH:mm:ss")); // new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parameters.get("varActualStartDate").toString()));
                where.append(" and ACTUAL_START_DATE >= :actualStartDate");
            } catch (ParseException e) {
                try {
                    map.put("actualStartDate", SToolUtils.string2DateTime(parameters.get("actualStartDate").toString(), "yyyy-MM-dd")); //new SimpleDateFormat("yyyy-MM-dd").parse(parameters.get("varActualStartDate").toString()));
                    where.append(" and ACTUAL_START_DATE >= :actualStartDate");
                } catch (ParseException ignore) {
                    log.error("未知错误:{}", e);
                }
            }
        }
        if ((null != parameters.get("actualCompletionDate")) && !"".equals(parameters.get("actualCompletionDate").toString())) {
            try {
                map.put("actualCompletionDate", SToolUtils.string2DateTime(parameters.get("actualCompletionDate").toString(), "yyyy-MM-dd HH:mm:ss")); //new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parameters.get("varActualCompletionDate").toString()));
                where.append(" and ACTUAL_COMPLETION_DATE <= :actualCompletionDate");
            } catch (ParseException e) {
                try {
                    map.put("actualCompletionDate", SToolUtils.string2DateTime(parameters.get("actualCompletionDate").toString(), "yyyy-MM-dd")); //new SimpleDateFormat("yyyy-MM-dd").parse(parameters.get("varActualCompletionDate").toString()));
                    where.append(" and ACTUAL_COMPLETION_DATE <= :actualCompletionDate");
                } catch (ParseException ignore) {
                    log.error("未知错误:{}", e);
                }
            }
        }

        if ((null != parameters.get("system")) && !"".equals(parameters.get("system").toString())) {
            where.append(" and sj.SYSTEM = :varSystem");
            map.put("varSystem", parameters.get("system").toString());
        }
        if ((null != parameters.get("module")) && !"".equals(parameters.get("module").toString())) {
            where.append(" and sj.MODULE = :varModule");
            map.put("varModule", parameters.get("module").toString());
        }
        //绩效方案Id
        if ((null != parameters.getInteger("schemeId")) && !"".equals(parameters.getInteger("schemeId"))) {
            where.append(" and (ps.scheme_id = :schemeId or ps1.scheme_id = :schemeId or ps2.scheme_id = :schemeId)");
            map.put("schemeId", parameters.get("schemeId").toString());
        }
        //
        where.append("  ORDER BY ss.creation_date desc , ss.SCHEDULE_ID desc  ");
        Pagination<SaafSchedulesEntity_HI_RO> rowSet;
        if (map.size() > 0) {
            rowSet = vo.findPagination(sql + where.toString(), map, pageIndex, pageRows);
        } else {
            rowSet = vo.findPagination(sql + where.toString(), pageIndex, pageRows);
        }
        //
        return JSON.toJSONString(rowSet);
    }

    /**
     *即：提交请求，分为 1、提交新请求，2、提交 ‘已执行完毕’ 或 ‘已卸载’（即：取消） 了的请求。
     * @param parameters
     * @return
     */
    public String saveRequest(JSONObject parameters) {

        //测试打印参数
        //        System.out.println("---------------------提交请求----参数-----" + parameters.toString() + "----------");

        com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();

        //
        stdScheduler = (Scheduler)context.getBean("scheduler");
        if (stdScheduler == null) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "提交失败！调度执行环境未初始化", 0, null);
            return jsonResult.toString();
        }

        //检查 必填项
        if (parameters.get("jobId") == null || parameters.get("jobId").toString().length() == 0) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "提交失败！未指定jobId", 0, null);
            return jsonResult.toString();
        }
        if (parameters.get("scheduleType") == null || parameters.get("scheduleType").toString().length() == 0) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "提交失败！未指定scheduleType", 0, null);
            return jsonResult.toString();
        }
        //

        int jobId = Integer.parseInt(parameters.get("jobId").toString());
        String scheduleType = parameters.get("scheduleType").toString();
        String argumentsText = parameters.get("argumentsText").toString();
        //‘0’表示 不尝试
        int failAttemptFrequency =
            Integer.parseInt(parameters.get("failAttemptFrequency") != null && parameters.get("failAttemptFrequency").toString().length() > 0 ? parameters.get("failAttemptFrequency").toString() :
                             "0");
        //‘-1’表示忽略超时时间，即等待请求执行返回
        int timeout = Integer.parseInt(parameters.get("timeout") != null && parameters.get("timeout").toString().length() > 0 ? parameters.get("timeout").toString() : "-1");

        //isRedo ，true：提交 已执行完毕 或 已卸载 的请求 ， false：提交新请求
        boolean isRedo = false; //默认为 false：提交新请求
        String redo_quartzJobName = "";
        if (parameters.get("isRedo") == null || parameters.get("isRedo").toString().length() == 0) {
            //默认为 false：提交新请求
        } else {
            isRedo = parameters.get("isRedo").toString().equalsIgnoreCase("YES");
            if (isRedo) {
                //true：提交 已执行完毕 或 已卸载 的请求 ， false：提交新请求
                if (parameters.get("scheduleId") == null || parameters.get("scheduleId").toString().length() == 0) {
                    jsonResult = SaafToolUtils.convertResultJSONObj("E", "提交失败！提交 已执行完毕 或 已卸载 的请求时，未指定scheduleId", 0, null);
                    return jsonResult.toString();
                }
            }
        }

        //
        String cornexpress = parameters.get("cornexpress") == null ? "" : parameters.get("cornexpress").toString();
        if (scheduleType.equalsIgnoreCase("plan") && cornexpress.length() == 0) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "提交失败！scheduleType为‘plan’时，cornexpress为必填", 0, "");
            return jsonResult.toString();
        } else if (scheduleType.equalsIgnoreCase("plan")) {
            //控制每次执行间隔时间 >= 30秒
            String corn_secondStr = cornexpress.substring(0, cornexpress.indexOf(" "));
            try {
                Integer.parseInt(corn_secondStr);
            } catch (NumberFormatException e) {
                try {
                    if (corn_secondStr.length() > 3 && corn_secondStr.indexOf("/") != -1) {
                        int interval = Integer.parseInt(corn_secondStr.substring(corn_secondStr.indexOf('/') + 1));
                        if (interval < 30) {
                            jsonResult = SaafToolUtils.convertResultJSONObj("E", "提交失败！执行间隔 必须 >= 30秒，请检查 corn表达式", 0, "");
                            return jsonResult.toString();
                        }
                    }

                    if (corn_secondStr.length() > 3 && corn_secondStr.indexOf(",") != -1) {
                        int start1 = Integer.parseInt(corn_secondStr.substring(0, corn_secondStr.indexOf(',')));
                        int start2 = Integer.parseInt(corn_secondStr.substring(corn_secondStr.indexOf(',') + 1));
                        if (!(start2 == 31 && start1 == 1)) {
                            jsonResult = SaafToolUtils.convertResultJSONObj("E", "提交失败！执行间隔 必须 >= 30秒，请检查 corn表达式", 0, "");
                            return jsonResult.toString();
                        }
                    }
                } catch (NumberFormatException e1) {
                    jsonResult = SaafToolUtils.convertResultJSONObj("E", "提交失败！执行间隔 必须 >= 30秒，请检查 corn表达式", 0, "");
                    return jsonResult.toString();
                }
            }
        }

        int userId = Integer.parseInt(parameters.get("varUserId").toString());
        //提交新请求时，记录Quartz自动生成的jobName
        String quartzJobName = null;
        Timestamp scheduleExpectStartDate = null;
        Timestamp scheduleExpectEndDate = null;
        try {
            if (!scheduleType.equalsIgnoreCase("Immediate")) {
                if (parameters.get("scheduleExpectStartDate") == null || parameters.get("scheduleExpectStartDate").toString().length() == 0) {
                    jsonResult = SaafToolUtils.convertResultJSONObj("E", "提交失败！必须指定请求开始时间", 0, "");
                    return jsonResult.toString();
                } else {
                    try {
                        scheduleExpectStartDate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parameters.get("scheduleExpectStartDate").toString()).getTime());
                    } catch (ParseException e) {
                        try {
                            scheduleExpectStartDate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(parameters.get("scheduleExpectStartDate").toString()).getTime());
                        } catch (ParseException e2) {
                            throw new ParseException("--", 0);
                        }
                    }
                }
            }

            if (parameters.get("scheduleExpectEndDate") == null || parameters.get("scheduleExpectEndDate").toString().length() == 0) {
                //ignore
            } else {
                try {
                    scheduleExpectEndDate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parameters.get("scheduleExpectEndDate").toString()).getTime());
                } catch (ParseException e) {
                    try {
                        scheduleExpectEndDate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(parameters.get("scheduleExpectEndDate").toString()).getTime());
                    } catch (ParseException e2) {
                        throw new ParseException("--", 0);
                    }
                }
            }
        } catch (ParseException e) {
            log.error("未知错误:{}", e);
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "提交失败！请求开始 或 结束时间 格式错误"+parameters, 0, "");
            return jsonResult.toString();
        }

        if (scheduleExpectEndDate != null && scheduleExpectStartDate != null) {
            if (scheduleExpectStartDate.getTime() >= scheduleExpectEndDate.getTime()) {
                jsonResult = SaafToolUtils.convertResultJSONObj("E", "提交失败！开始时间 必须小于 结束时间", 0, "");
                return jsonResult.toString();
            }
        }

        //参数
        HashMap<String, Object> args = new HashMap<String, Object>();
        JobDetail jobDetail = null;
        Trigger trigger = null;

        //获取Job信息
        //        ViewObject pvo = (ViewObject)SaafToolUtils.context.getBean("saafJobsDAO_HI");
        ViewObject pvo = this.saafJobsDAOHI;
        SaafJobsEntity_HI prow = (SaafJobsEntity_HI)pvo.getById(jobId);

        if (prow == null) {
            return SaafToolUtils.convertResultJSONObj("E", "提交失败！指定id的job不存在：" + jobId, 0, "").toString();
        }

        String executableName = prow.getExecutableName();
        String jobType = prow.getJobType();
        String method = prow.getMethod();

        //保存必要的参数 start
        HashMap<String, String> jobTypeMap = new HashMap<String, String>();
        jobTypeMap.put("Private", jobType);
        args.put("jobType", jobTypeMap);

        HashMap<String, String> executableNameMap = new HashMap<String, String>();
        executableNameMap.put("Private", executableName);
        args.put("executableName", executableNameMap);

        HashMap<String, String> methodMap = new HashMap<String, String>();
        methodMap.put("Private", method);
        args.put("method", methodMap);

        HashMap<String, String> failAttemptFrequencyMap = new HashMap<String, String>();
        failAttemptFrequencyMap.put("Private", "" + failAttemptFrequency);
        args.put("failAttemptFrequency", failAttemptFrequencyMap);

        HashMap<String, String> timeoutMap = new HashMap<String, String>();
        timeoutMap.put("Private", "" + timeout);
        args.put("timeout", timeoutMap);
        //保存必要的参数 end

        //处理：请求运行时参数 start
        String paramsJsonArray = parameters.getJSONArray("param").toString();
        com.alibaba.fastjson.JSONArray jsonArray = com.alibaba.fastjson.JSON.parseArray(paramsJsonArray);
        //
        for (int i = 0; i < jsonArray.size(); i++) {

            HashMap<String, String> argsValue = new HashMap<String, String>();
            com.alibaba.fastjson.JSONObject jsonItem = com.alibaba.fastjson.JSON.parseObject(jsonArray.get(i).toString());
            if (jobType.equalsIgnoreCase("webservice")) {
                //<参数名称，<参数位置（url、body、head），值（默认值）>>， 忽略参数类型， 所有的参数值都是 使用String 处理
                if (jsonItem.getString("defaultValue") == null) {
                    argsValue.put(jsonItem.getString("paramRegion").toString(), "");
                } else {
                    argsValue.put(jsonItem.getString("paramRegion").toString(), jsonItem.getString("defaultValue"));
                }
                //
                args.put(jsonItem.getString("paramName"), argsValue);
            } else if (jobType.equalsIgnoreCase("package")) {
                //<参数序号，<“参数类型（String、Number、Date） 参数位置（out、in）”，值（默认值）>> ，其中 参数类型 与 参数位置 使用一个空格拼接！
                if (jsonItem.getString("defaultValue") == null) {
                    argsValue.put(jsonItem.getString("paramType").toString() + " " + jsonItem.getString("paramRegion").toString(), "");
                } else {
                    argsValue.put(jsonItem.getString("paramType").toString() + " " + jsonItem.getString("paramRegion").toString(), jsonItem.getString("defaultValue"));
                }
                //
                args.put(jsonItem.getString("paramName"), argsValue);
            } else if (jobType.equalsIgnoreCase("java")) {
                //<参数序号，<参数类型（String、Number、Date），值（默认值）>>
                if (jsonItem.getString("defaultValue") == null) {
                    argsValue.put(jsonItem.getString("paramType").toString(), "");
                } else {
                    argsValue.put(jsonItem.getString("paramType").toString(), jsonItem.getString("defaultValue"));
                }
                //
                args.put(jsonItem.getString("paramName"), argsValue);
            }
        }

        //added by sie 20180323
        args.put("jobId",prow.getJobId());
        args.put("jobName",prow.getJobName());
        args.put("varUserId",parameters.get("varUserId"));
        if(!SrmUtils.isNvl(argumentsText))
        {//added by sie 20180323 将参数加入jobDetail 便于job程序获取
            Map  map = JSON.parseObject(argumentsText);
            for (Object obj: map.keySet()) {
                args.put(obj.toString(),map.get(obj));
            }
        }


        //处理 JobDetail start
        HashMap<String, Object> tempArgs = null;
        //
        ViewObject svo = this.saafSchedulesDAOHI;
        SaafSchedulesEntity_HI srow = null;
        SaafSchedulesEntity_HI row =null;

        if (isRedo) { //提交 ‘已执行完毕’ 或 ‘已卸载’ 的请求时，可复用上次提交的参数

            srow = (SaafSchedulesEntity_HI)svo.getById(Integer.parseInt(parameters.get("scheduleId").toString()));
            if (srow == null) {
                return SaafToolUtils.convertResultJSONObj("E", "提交失败！参数复用失败，请尝试提交新请求 或 重新赋值参数。指定id的记录不存在：" + parameters.get("scheduleId").toString(), 0, null).toString();
            } else {
                redo_quartzJobName = srow.getQuartzJobName();
            }

            if (argumentsText.length() > 0 && jsonArray.size() == 0) { //表示复用上次参数
                try {
                    Blob ssblob = srow.getArgumentObj();
                    if (ssblob != null) {
                        byte[] ssbytes = ssblob.getBytes(1, (int)ssblob.length());
                        ObjectInputStream objectOutputStream = new ObjectInputStream(new ByteArrayInputStream(ssbytes));
                        tempArgs = (HashMap<String, Object>)objectOutputStream.readObject();
                    } else {
                        tempArgs = new HashMap<String, Object>();
                    }
                } catch (SQLException e) {
                    log.error("未知错误:{}", e);
                    return SaafToolUtils.convertResultJSONObj("E", "提交失败！" + e, 0, null).toString();
                } catch (IOException e) {
                    log.error("未知错误:{}", e);
                    return SaafToolUtils.convertResultJSONObj("E", "提交失败！" + e, 0, null).toString();
                } catch (ClassNotFoundException e) {
                    log.error("未知错误:{}", e);
                    return SaafToolUtils.convertResultJSONObj("E", "提交失败！" + e, 0, null).toString();
                }
                //
                tempArgs.put("jobType", jobTypeMap);
                tempArgs.put("executableName", executableNameMap);
                tempArgs.put("method", methodMap);
                tempArgs.put("failAttemptFrequency", failAttemptFrequencyMap);
                tempArgs.put("timeout", timeoutMap);

                //added by sie 20180323
                tempArgs.put("jobId",prow.getJobId());
                tempArgs.put("jobName",prow.getJobName());
                tempArgs.put("varUserId",parameters.get("varUserId"));
                if(!SrmUtils.isNvl(argumentsText))
                {//added by sie 20180323 将参数加入jobDetail 便于job程序获取
                    Map  map = JSON.parseObject(argumentsText);
                    for (Object obj: map.keySet()) {
                        tempArgs.put(obj.toString(),map.get(obj));
                    }
                }

                //
                jobDetail = JobBuilder.newJob(Run.class).requestRecovery(true).storeDurably(false).withIdentity(redo_quartzJobName).usingJobData(new JobDataMap(tempArgs)).build();
                quartzJobName = redo_quartzJobName;
            } else {
                jobDetail = JobBuilder.newJob(Run.class).requestRecovery(true).storeDurably(false).withIdentity(redo_quartzJobName).usingJobData(new JobDataMap(args)).build();
                quartzJobName = redo_quartzJobName;
            }
        } else {
            jobDetail = JobBuilder.newJob(Run.class).requestRecovery(true).storeDurably(false).usingJobData(new JobDataMap(args)).build();
            quartzJobName = jobDetail.getKey().getName();
        }
        
      /*  withMisfireHandlingInstructionDoNothing
        ——不触发立即执行
        ——等待下次Cron触发频率到达时刻开始按照Cron频率依次执行

        withMisfireHandlingInstructionIgnoreMisfires
        ——以错过的第一个频率时间立刻开始执行
        ——重做错过的所有频率周期后
        ——当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行

        withMisfireHandlingInstructionFireAndProceed
        ——以当前时间为触发频率立刻触发一次执行
        ——然后按照Cron频率依次执行*/
        //处理 JobDetail end
        //处理：请求运行时参数 end

        if (scheduleType.equalsIgnoreCase("Immediate")) {
            trigger = TriggerBuilder.newTrigger().startNow().withIdentity(quartzJobName).build();
        } else if (scheduleType.equalsIgnoreCase("once") && scheduleExpectStartDate != null) {
            trigger = TriggerBuilder.newTrigger().startAt(scheduleExpectStartDate).withIdentity(quartzJobName).build();
        } else if (scheduleType.equalsIgnoreCase("plan") && scheduleExpectStartDate != null) {
            if (scheduleExpectEndDate != null) {
                trigger =
                        TriggerBuilder.newTrigger().startAt(scheduleExpectStartDate).endAt(scheduleExpectEndDate).withSchedule(CronScheduleBuilder.cronSchedule(cornexpress).withMisfireHandlingInstructionIgnoreMisfires()).withIdentity(quartzJobName).build();
            } else {
                trigger =
                        TriggerBuilder.newTrigger().startAt(scheduleExpectStartDate).withSchedule(CronScheduleBuilder.cronSchedule(cornexpress)).withIdentity(quartzJobName).build();
            }
        } else {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "提交失败！请检查scheduleType 以及 scheduleExpectStartDate 是否正确", 0, null);
            log.error("----------------------create trigger error--------------------------");
            return jsonResult.toString();
        }
        //
        if (isRedo) {
            //复用前面的 srow ， svo
            srow.setActualCompletionDate(null);
            srow.setScheduleExpectStartDate(scheduleExpectStartDate);
            srow.setScheduleExpectEndDate(scheduleExpectEndDate);
            srow.setCornexpress(cornexpress);
            srow.setScheduleType(scheduleType);
            srow.setArgumentsText(argumentsText);
            srow.setFailAttemptFrequency(failAttemptFrequency);
            srow.setTimeout(timeout);
            //处理 argument_obj（blob） 字段
            byte[] argsBytes = objectConvertTobytes(tempArgs == null ? args : tempArgs);
            srow.setArgumentObj(Hibernate.createBlob(argsBytes));

            srow.setLastUpdateDate(new Timestamp(new Date().getTime()));
            srow.setLastUpdatedBy(userId);
            srow.setOperatorUserId(userId);
            //
            svo.saveOrUpdate(srow);
        } else {
            //提交新请求
             row = new SaafSchedulesEntity_HI();
            //设置
            row.setPhaseCode("JOBSCHEDULED_PHASECODE"); //初始 阶段 为‘已部署’
            row.setStatusCode("OK_STATUSCODE"); //初始 状态 为‘已部署’
            row.setLogFileName(quartzJobName + ".log");
            row.setScheduleExpectStartDate(scheduleExpectStartDate);
            row.setScheduleExpectEndDate(scheduleExpectEndDate);
            row.setJobId(jobId);
            row.setQuartzJobName(quartzJobName);
            row.setCornexpress(cornexpress);
            row.setPriority(5);
            row.setScheduleType(scheduleType);
            row.setWasExecutedTotalCount(0);
            row.setWasExecutedFailCount(0);
            row.setWasExecutedSuccessCount(0);
            row.setArgumentsText(argumentsText);
            row.setFailAttemptFrequency(failAttemptFrequency);
            row.setTimeout(timeout);

            row.setCreatedBy(userId);
            row.setCreationDate(new Timestamp(new Date().getTime()));
            row.setLastUpdateDate(new Timestamp(new Date().getTime()));
            row.setLastUpdateLogin(userId);
            row.setLastUpdatedBy(userId);
            row.setOperatorUserId(userId);
            //处理 argument_obj（blob） 字段
            byte[] argsBytes = objectConvertTobytes(args);
            row.setArgumentObj(Hibernate.createBlob(argsBytes));
            //
            svo.save(row);
        }
        //
        try {
            stdScheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e1) {
            //
            e1.printStackTrace();
            if (e1 instanceof org.quartz.ObjectAlreadyExistsException) {
                return SaafToolUtils.convertResultJSONObj("E", "该请求正在执行中，请稍后重试！！", 0, null).toString();
            }
            return SaafToolUtils.convertResultJSONObj("E", "提交失败失败！部署到Scheduler失败" + e1, 0, null).toString();
        }

        jsonResult = SaafToolUtils.convertResultJSONObj("S", "提交成功！", 1, (isRedo?srow:row));
        return jsonResult.toString();
    }

    public String pauseRequest(JSONObject parameters) {

        com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();

        //
        stdScheduler = (Scheduler)context.getBean("scheduler");
        if (stdScheduler == null) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "操作失败！调度执行环境未初始化", 0, null);
            return jsonResult.toString();
        }

        if (parameters.get("scheduleId") == null || parameters.get("scheduleId").toString().length() == 0) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "操作失败！未指定请求Id（scheduleId）", 0, null);
            return jsonResult.toString();
        }

        String quartzJobName = null;
        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafSchedulesDAO_HI");
        ViewObject vo = this.saafSchedulesDAOHI;
        SaafSchedulesEntity_HI row = (SaafSchedulesEntity_HI)vo.getById(Integer.parseInt(parameters.get("scheduleId").toString()));
        if (row != null) {
            quartzJobName = row.getQuartzJobName();
        }
        try {
            if (quartzJobName != null && quartzJobName.length() > 0) {
                stdScheduler.pauseJob(new JobKey(quartzJobName));
                jsonResult = SaafToolUtils.convertResultJSONObj("S", "请求暂停成功!", 1, null);
            } else {
                jsonResult = SaafToolUtils.convertResultJSONObj("E", "请求暂停失败！指定请求不存在 requestId = " + parameters.get("scheduleId").toString(), 0, null);
            }
        } catch (SchedulerException e) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "请求暂停失败！" + e, 0, null);
            log.error("未知错误:{}", e);
        }
        return jsonResult.toString();
    }

    /**
     * 启动处于 暂停状态 的请求
     * @param parameters
     * @return
     */
    public String resumeRequest(JSONObject parameters) {

        com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();

        //
        stdScheduler = (Scheduler)context.getBean("scheduler");
        if (stdScheduler == null) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "操作失败！调度执行环境未初始化", 0, null);
            return jsonResult.toString();
        }

        if (parameters.get("scheduleId") == null || parameters.get("scheduleId").toString().length() == 0) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "操作失败！未指定请求Id（scheduleId）", 0, null);
            return jsonResult.toString();
        }

        String quartzJobName = null;
        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafSchedulesDAO_HI");
        ViewObject vo = this.saafSchedulesDAOHI;
        SaafSchedulesEntity_HI row = (SaafSchedulesEntity_HI)vo.getById(Integer.parseInt(parameters.get("scheduleId").toString()));
        if (row != null) {
            quartzJobName = row.getQuartzJobName();
        }
        try {
            if (quartzJobName != null && quartzJobName.length() > 0) {
                stdScheduler.resumeJob(new JobKey(quartzJobName));
                jsonResult = SaafToolUtils.convertResultJSONObj("S", "请求启动（resume）成功!", 1, null);
            } else {
                jsonResult = SaafToolUtils.convertResultJSONObj("E", "请求启动（resume）失败！指定请求不存在 requestId = " + parameters.get("scheduleId").toString(), 0, null);
            }
        } catch (SchedulerException e) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "请求启动（resume）失败！" + e, 0, null);
            log.error("未知错误:{}", e);
        }
        return jsonResult.toString();
    }

    public String cancelRequest(JSONObject parameters) {

        com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();

        //
        stdScheduler = (Scheduler)context.getBean("scheduler");
        if (stdScheduler == null) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "操作失败！调度执行环境未初始化", 0, null);
            return jsonResult.toString();
        }

        if (parameters.get("scheduleId") == null || parameters.get("scheduleId").toString().length() == 0) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "操作失败！未指定请求Id（scheduleId）", 0, null);
            return jsonResult.toString();
        }

        String quartzJobName = null;

        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafSchedulesDAO_HI");
        ViewObject vo = this.saafSchedulesDAOHI;
        SaafSchedulesEntity_HI row = (SaafSchedulesEntity_HI)vo.getById(Integer.parseInt(parameters.get("scheduleId").toString()));
        if (row != null) {
            quartzJobName = row.getQuartzJobName();
        }
        try {
            if (quartzJobName != null && quartzJobName.length() > 0) {
                stdScheduler.unscheduleJob(new TriggerKey(quartzJobName));
                jsonResult = SaafToolUtils.convertResultJSONObj("S", "请求取消成功", 1, null);
            } else {
                jsonResult = SaafToolUtils.convertResultJSONObj("E", "请求取消失败！指定请求不存在 requestId = " + parameters.get("scheduleId").toString(), 0, null);
            }
        } catch (SchedulerException e) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "请求取消失败！" + e, 0, null);
            log.error("未知错误:{}", e);
        }
        return jsonResult.toString();
    }

    public String launchRequest(JSONObject parameters) {

        com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();

        //
        stdScheduler = (Scheduler)context.getBean("scheduler");
        if (stdScheduler == null) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "操作失败！调度执行环境未初始化", 0, null);
            return jsonResult.toString();
        }

        if (parameters.get("scheduleId") == null || parameters.get("scheduleId").toString().length() == 0) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "操作失败！未指定请求Id（scheduleId）", 0, null);
            return jsonResult.toString();
        }

        String quartzJobName = null;
        String phaseCode = null;
        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafSchedulesDAO_HI");
        ViewObject vo = this.saafSchedulesDAOHI;
        SaafSchedulesEntity_HI row = (SaafSchedulesEntity_HI)vo.getById(Integer.parseInt(parameters.get("scheduleId").toString()));
        if (row != null) {
            quartzJobName = row.getQuartzJobName();
            phaseCode = row.getPhaseCode();
        }
        try {
            if (quartzJobName != null && quartzJobName.length() > 0 && phaseCode.contains("jobPaused_PhaseCode")) {
                stdScheduler.resumeJob(new JobKey(quartzJobName));
                jsonResult = SaafToolUtils.convertResultJSONObj("S", "请求启动成功", 1, null);
            } else {
                jsonResult = SaafToolUtils.convertResultJSONObj("E", "请求取消失败！指定请求不存在 或 请求已启动 requestId = " + parameters.get("scheduleId").toString(), 0, null);
            }
        } catch (SchedulerException e) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "请求启动失败，只允许启动处于暂停状态的请求" + e, 0, null);
            log.error("未知错误:{}", e);
        }
        return jsonResult.toString();
    }

    public String getRequestLog(JSONObject parameters) {

        com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();

        if (parameters.get("scheduleId") == null || parameters.get("scheduleId").toString().length() == 0) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "查询失败！未指定请求Id（scheduleId）", 0, null);
            return jsonResult.toString();
        }

        String quartzJobName = null;

        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafSchedulesDAO_HI");
        ViewObject vo = this.saafSchedulesDAOHI;
        SaafSchedulesEntity_HI row = (SaafSchedulesEntity_HI)vo.getById(Integer.parseInt(parameters.get("scheduleId").toString()));
        if (row != null) {
            quartzJobName = row.getQuartzJobName();
        } else {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "查询日志失败!指定请求记录不存在", 0, null);
            return jsonResult.toString();
        }
        jsonResult = SaafToolUtils.convertResultJSONObj("S", "查询日志成功!", 1, MyLogUtils.getLogContents(quartzJobName));
        return jsonResult.toString();
    }

    /**
     *删除请求，仅仅修改标识 表示该记录已删除， 实际仍然存在
     * @param parameters
     * @return
     */
    public String deleteSchedule(JSONObject parameters) {

        com.alibaba.fastjson.JSONObject jsonResult = new com.alibaba.fastjson.JSONObject();

        //
        stdScheduler = (Scheduler)context.getBean("scheduler");
        if (stdScheduler == null) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "操作失败！调度执行环境未初始化", 0, null);
            return jsonResult.toString();
        }

        if (parameters.get("scheduleId") == null || parameters.get("scheduleId").toString().length() == 0) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "删除失败！未指定请求Id（scheduleId）", 0, null);
            return jsonResult.toString();
        }

        //仅仅允许删除 ‘已执行完毕’ 或 ‘已卸载’（即：取消） 了的请求记录
        if (!isAllowDeleteSchedule(parameters)) {
            jsonResult = SaafToolUtils.convertResultJSONObj("E", "删除失败！请求 正在执行中 或 不存在", 0, "");
            return jsonResult.toString();
        }
        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafSchedulesDAO_HI");
        ViewObject vo = this.saafSchedulesDAOHI;
        SaafSchedulesEntity_HI row = (SaafSchedulesEntity_HI)vo.getById(Integer.parseInt(parameters.get("scheduleId").toString()));
        if (row != null) {
            row.setIsDeleted("Y");
            vo.saveOrUpdate(row);
        }
        jsonResult = SaafToolUtils.convertResultJSONObj("S", "删除成功!", 1, "");
        return jsonResult.toString();
    }

    private boolean isAllowDeleteSchedule(JSONObject parameters) {

        List<SaafJobsEntity_HI> list = null;
        String where = " where 1=1 ";
        Map<String, Object> map = new HashMap<String, Object>();
        //        ViewObject vo = (ViewObject)SaafToolUtils.context.getBean("saafSchedulesDAO_HI");
        ViewObject vo = this.saafSchedulesDAOHI;

        where = where + " and scheduleId = :varScheduleId ";
        map.put("varScheduleId", Integer.parseInt(parameters.get("scheduleId").toString().trim()));
        //仅仅允许删除 ‘已执行完毕’ 或 ‘已卸载’（即：取消） 了的请求记录
        where = where + " and phaseCode in ('jobUnscheduled_PhaseCode','jobWasExecuted_PhaseCode') ";
        list = vo.findList("from " + vo.getEntityClass().getSimpleName() + where, map);
        if (list.size() > 0) {
            return true;
        }
        return false;

    }

    /**
     *序列化操作
     * @param obj
     * @return
     */
    private static byte[] objectConvertTobytes(Object obj) {

        byte[] dByte = null;

        if (obj != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(obj);
                dByte = byteArrayOutputStream.toByteArray();

            } catch (IOException e) {

            }
        }

        return dByte;
    }

	public static void main(String[] args) {

        ISchedules messageServerInfoServer = (ISchedules) SaafToolUtils.context.getBean("schedulesServer");
		JSONObject paramJSON = new JSONObject();
		//{"actualCompletionDate":"2017-09-29 16:16:43","actualStartDate":"2017-09-27 16:31:54","argumentsText":"{\"byProvinceName\":\"广东\"}","cornexpress":"0/5 * * * * ? ","createdBy":-9999,"failAttemptFrequency":0,"jobId":37,"jobName":"WeatherWebService","logFileName":"6da64b5bd2ee-f6c51518-07d8-4f18-ba86-12f0d57dca67.log","module":"ORDER","moduleMeaning":"订单模块","nextFireTime":"2017-09-29 16:16:40","phaseCode":"JOBUNSCHEDULED_PHASECODE","phaseCodeMeaning":"已卸载","previousFireTime":"2017-09-29 16:16:35","priority":5,"quartzJobName":"6da64b5bd2ee-f6c51518-07d8-4f18-ba86-12f0d57dca67","scheduleExpectStartDate":"2017-09-27 16:31:44","scheduleId":36268,"scheduleType":"PLAN","scheduleTypeMeaning":"计划","statusCode":"OK_STATUSCODE","statusCodeMeaning":"正常","system":"SAAF_SYSTEM","systemMeaning":"Saaf平台","timeout":10,"wasExecutedFailCount":15955,"wasExecutedSuccessCount":4026,"wasExecutedTotalCount":19981,"$$hashKey":"object:287","isRedo":"YES","param":[{"defaultValue":"广东","isEnabled":"true","isRequired":"","jobId":37,"paramId":78,"paramName":"byProvinceName","paramRegion":"URL","paramSeqNum":1506498161,"paramType":"STRING","paramTypeMeaning":"字符类型"}]}

        //paramJSON = JSONObject.parseObject("{\"actualCompletionDate\":\"2018-03-23 16:16:39\",\"actualStartDate\":\"2018-03-23 16:16:39\",\"argumentsText\":\"{\\\"itemCode\\\":\\\"-1\\\",\\\"StartDate\\\":\\\"\\\",\\\"EndDate\\\":\\\"\\\",\\\"pageNum\\\":1,\\\"Type\\\":\\\"1\\\"}\",\"cornexpress\":\"\",\"createdBy\":1,\"failAttemptFrequency\":0,\"jobId\":36,\"jobName\":\"物料接口\",\"logFileName\":\"6da64b5bd2ee-93cf9e98-1ed6-4b4c-8d9d-7bff3572432a.log\",\"module\":\"SCHEDULED\",\"moduleMeaning\":\"调度模块\",\"phaseCode\":\"JOBWASEXECUTED_PHASECODE\",\"phaseCodeMeaning\":\"运行完毕\",\"previousFireTime\":\"2018-03-23 16:16:39\",\"priority\":5,\"quartzJobName\":\"6da64b5bd2ee-93cf9e98-1ed6-4b4c-8d9d-7bff3572432a\",\"scheduleId\":36328,\"scheduleType\":\"IMMEDIATE\",\"scheduleTypeMeaning\":\"立即\",\"statusCode\":\"OK_STATUSCODE\",\"statusCodeMeaning\":\"正常\",\"system\":\"SAAF_SYSTEM\",\"systemMeaning\":\"Saaf平台\",\"timeout\":-1,\"userName\":\"sysadmin\",\"wasExecutedFailCount\":0,\"wasExecutedSuccessCount\":1,\"wasExecutedTotalCount\":1,\"param\":[],\"isRedo\":\"YES\"}");
        String resultStr = messageServerInfoServer.saveRequest(paramJSON);

		System.out.println(resultStr);

		// JSONArray array= paramJSON.getJSONArray("detail");
		// System.out.println(JSONObject.parseObject(JSON.toJSONString(array.get(0))));
		// logger.info(resultStr);

		// JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1,
		// "save success");

	}


}
