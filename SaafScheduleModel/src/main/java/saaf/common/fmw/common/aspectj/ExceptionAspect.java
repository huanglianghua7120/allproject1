package saaf.common.fmw.common.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: (用一句话描述类的作用)
 * @Auther: LiPengFei
 * @Date: 2019/11/7 10:52
 */
public class ExceptionAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 在方法调用之前，打印入参
     */
    public void before(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder params = new StringBuilder();
        for (Object arg : args) {
            params.append(arg).append(" ");
        }
        logger.info(className + "的" + methodName + "入参为：" + params.toString());
    }

    /**
     * 过程中监测，catch到异常之后返回包装后的错误信息，并打印日志
     */
    public Object catchException(ProceedingJoinPoint joinPoint) {
        Object object=null;
        try {
            object = joinPoint.proceed();
            return object;
        } catch (Throwable e) {
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            logger.error("在" + className + "的" + methodName + "中，发生了异常：" + e);
            return object;
        }
    }

    /**
     * 返回之后，打印出参
     */
    public void afterReturin(JoinPoint joinPoint, Object returnVal) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(className + "的" + methodName + "结果为：" + returnVal);
    }
}
