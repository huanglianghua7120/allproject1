package saaf.common.fmw.base.utils.annotation;

/**
 * @Description: (自定义操作日志记录注解)
 * @Auther: LiPengFei
 * @Date: 2019/10/28 10:29
 */


import saaf.common.fmw.base.utils.enums.OperatorType;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
         *
         */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{
    /** 模块 */
    String title() default "";

    String businessType() default "";

    /** 操作人类别 */
    OperatorType operatorType() default OperatorType.MANAGE;

    /** 是否保存请求的参数 */
    boolean isSaveRequestData() default true;
}
