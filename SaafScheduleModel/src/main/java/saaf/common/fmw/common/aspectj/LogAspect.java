package saaf.common.fmw.common.aspectj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.base.model.entities.SaafBaseOperlogEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafBaseOperlog;
import saaf.common.fmw.base.utils.annotation.Log;

import java.lang.reflect.Method;
import java.util.Arrays;

import static saaf.common.fmw.services.CommonAbstractServices.MSG;
import static saaf.common.fmw.services.CommonAbstractServices.STATUS;
import static saaf.common.fmw.services.CommonAbstractServices.SUCCESS_STATUS;

/**
 * @Description: (操作日志记录处理)
 * @Auther: LiPengFei
 * @Date: 2019/10/28 10:38
 */
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Autowired
    private ISaafBaseOperlog iSaafBaseOperlog;
    /**
     * 功能描述: 拦截异常操作
     *
     * @param: [joinPoint, e]
     * @return: void
     * @auther: LiPengFei
     * @date: 2019/10/28 10:41
     */
    public Object doAroundMethodLog(ProceedingJoinPoint jp) {
        return handleLog(jp);
    }
    protected JSONObject handleLog(final ProceedingJoinPoint joinPoint) {
        try {
            // 获得注解
            Log  controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null)
            {
                return null;
            }
            SaafBaseOperlogEntity_HI operLog = new SaafBaseOperlogEntity_HI();
            //调用状态 SUCCESS 成功  FAIL 失败
            operLog.setStatus("SUCCESS");
//            operLog.setOperIp(ServletUtils.getIp());
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
//            operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
            // 处理设置注解上的参数
            getControllerMethodDescription(controllerLog, operLog);
            //是否保存请求参数 默认为true
            if(controllerLog.isSaveRequestData()){
                operLog.setOperParam(Arrays.toString(joinPoint.getArgs()));
            }
            //返回的数据
            String params = JSON.toJSONString(joinPoint.proceed());
            JSONObject paramJSON = JSONObject.parseObject(params);
            if (null == paramJSON || null == paramJSON.getString(STATUS) || "".equals(paramJSON.getString(STATUS)) || !SUCCESS_STATUS.equals(paramJSON.getString(STATUS))) {
                operLog.setStatus("FAIL");
                if(null == paramJSON || null == paramJSON.getString(MSG)){
                    operLog.setErrorMsg("返回值为空");
                }else{
                    operLog.setErrorMsg(paramJSON.getString(MSG));
                }
            }
            operLog.setOperRespParam(params);
            operLog.setOperatorUserId(-1);
            iSaafBaseOperlog.saveOperLog(operLog);
            return paramJSON;
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @return 方法描述
     * @throws Exception
     */
    public void getControllerMethodDescription(Log log, SaafBaseOperlogEntity_HI operLog) throws Exception
    {
        // 设置action动作
        operLog.setBusinessType(log.businessType());
        // 设置标题
        operLog.setTitle(log.title());
        // 设置操作人类别
        operLog.setOperatorType(log.operatorType().name());
    }
    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(ProceedingJoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

}
