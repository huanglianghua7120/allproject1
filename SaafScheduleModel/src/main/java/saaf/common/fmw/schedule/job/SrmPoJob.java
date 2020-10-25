package saaf.common.fmw.schedule.job;

import com.alibaba.fastjson.JSONObject;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saaf.common.fmw.po.model.inter.ISrmPoJob;
import saaf.common.fmw.schedule.utils.SaafToolUtils;
import saaf.common.fmw.schedule.utils.ScheduleUtil;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：PO调度配置
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-07-08     谢晓霞             创建
 * ==============================================================================
 */
public class SrmPoJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoJob.class);

    /**
     * Description：获取EBS技改编号
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    public void savePoTechnicalNum(JobExecutionContext parameters){
        try {
            LOGGER.info("---------获取EBS技改编号---------");
            //==================== 获取时间参数 ====================
            ISrmPoJob srmPoJobServer = (ISrmPoJob) SaafToolUtils.context.getBean("srmPoJobServer");
            JSONObject paramsObject = new JSONObject();
            //查询成功
            JSONObject result = srmPoJobServer.savePoTechnicalNum();

            LOGGER.info(result.toJSONString());
            ScheduleUtil.logInfo(parameters, "返回信息:" + result.toJSONString());
        }catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
        }
    }


    /**
     * Description：获取EBS子项目编号
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    public void savePoSubprojectNum(JobExecutionContext parameters){
        try {
            LOGGER.info("---------获取EBS子项目编号---------");
            //==================== 获取时间参数 ====================
            ISrmPoJob srmPoJobServer = (ISrmPoJob) SaafToolUtils.context.getBean("srmPoJobServer");
            JSONObject paramsObject = new JSONObject();
            //查询成功
            JSONObject result = srmPoJobServer.savePoSubprojectNum();

            LOGGER.info(result.toJSONString());
            ScheduleUtil.logInfo(parameters, "返回信息:" + result.toJSONString());
        }catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
        }
    }

}
