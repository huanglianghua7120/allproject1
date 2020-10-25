package saaf.common.fmw.schedule.job;

import com.alibaba.fastjson.JSONObject;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saaf.common.fmw.base.model.inter.ISrmBaseJob;
import saaf.common.fmw.pon.model.inter.ISrmPonJob;
import saaf.common.fmw.schedule.utils.SaafToolUtils;
import saaf.common.fmw.schedule.utils.ScheduleUtil;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：基础模块调度配置
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-07-09     谢晓霞             创建
 * ==============================================================================
 */
public class SrmBaseJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseJob.class);

    /**
     * 银行接口定时任务
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-09      谢晓霞            创建
     * ==============================================================================
     */
    public void updateSrmBaseBankListJob(JobExecutionContext parameters){
        try {
            LOGGER.info("---------银行接口定时任务---------");
            //==================== 获取时间参数 ====================
            ISrmBaseJob srmBaseJobServer = (ISrmBaseJob) SaafToolUtils.context.getBean("srmBaseJobServer");
            JSONObject paramsObject = new JSONObject();
            //查询成功
            JSONObject result = srmBaseJobServer.updateSrmBaseBankListJob();
            LOGGER.info(result.toJSONString());

            ScheduleUtil.logInfo(parameters, "返回信息:" + result.toJSONString());
        }catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
        }
    }



    /**
     * 银行分行获取定时任务
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-09      谢晓霞            创建
     * ==============================================================================
     */
    public void updateSrmBaseBrancheListJob(JobExecutionContext parameters){
        try {
            LOGGER.info("---------银行分行获取定时任务---------");
            //==================== 获取时间参数 ====================
            ISrmBaseJob srmBaseJobServer = (ISrmBaseJob) SaafToolUtils.context.getBean("srmBaseJobServer");
            JSONObject paramsObject = new JSONObject();
            //查询成功
            JSONObject result = srmBaseJobServer.updateSrmBaseBrancheListJob();
            LOGGER.info(result.toJSONString());
            ScheduleUtil.logInfo(parameters, "返回信息:" + result.toJSONString());
        }catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
        }
    }

    /**
     * Description：付款条件接口定时任务,每天定时从EBS系统获取数据
     * @return Integer
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-07-09           谢晓霞             创建
     * =======================================================================
     */
    public void updateSrmPonPaymentTermsListJob(JobExecutionContext parameters){
        try {
            LOGGER.info("---------付款条件获取定时任务---------");
            //==================== 获取时间参数 ====================
            ISrmBaseJob srmBaseJobServer = (ISrmBaseJob) SaafToolUtils.context.getBean("srmBaseJobServer");
            JSONObject paramsObject = new JSONObject();
            //查询成功
            JSONObject result = srmBaseJobServer.updateSrmPonPaymentTermsListJob();
            LOGGER.info(result.toJSONString());
            ScheduleUtil.logInfo(parameters, "返回信息:" + result.toJSONString());
        }catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
        }
    }

}
