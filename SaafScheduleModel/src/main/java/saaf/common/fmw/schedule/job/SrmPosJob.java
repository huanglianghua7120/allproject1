package saaf.common.fmw.schedule.job;

import com.alibaba.fastjson.JSONObject;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saaf.common.fmw.pos.model.inter.ISrmPosJob;
import saaf.common.fmw.schedule.utils.SaafToolUtils;
import saaf.common.fmw.schedule.utils.ScheduleUtil;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商调度配置
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-07-08     谢晓霞             创建
 * ==============================================================================
 */
public class SrmPosJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosJob.class);

    /**
     * 根据资质有效时间自动创建预警
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-08      谢晓霞            创建
     * ==============================================================================
     */
    public void saveCreateWarning(JobExecutionContext parameters){
        try {
            LOGGER.info("---------根据资质有效时间自动创建预警---------");
            //==================== 获取时间参数 ====================
            ISrmPosJob srmPosJobServer = (ISrmPosJob) SaafToolUtils.context.getBean("srmPosJobServer");
            JSONObject paramsObject = new JSONObject();
            //查询成功
            JSONObject result = srmPosJobServer.saveCreateWarning();

            LOGGER.info(result.toJSONString());
            ScheduleUtil.logInfo(parameters, "返回信息:" + result.toJSONString());
        }catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
        }
    }


    /**
     * 保存ekpStatus
     *
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-05-24     谢晓霞             创建
     * =======================================================================
     */
    public void saveEkpStatus(JobExecutionContext parameters){
        try {
            LOGGER.info("---------供应商引入EKP审批结果---------");
            //==================== 获取时间参数 ====================
            ISrmPosJob srmPoJobServer = (ISrmPosJob) SaafToolUtils.context.getBean("srmPosJobServer");
            JSONObject paramsObject = new JSONObject();
            //查询成功
            JSONObject result = srmPoJobServer.saveEkpStatus();

            LOGGER.info(result.toJSONString());
            ScheduleUtil.logInfo(parameters, "返回信息:" + result.toJSONString());
        }catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
        }
    }


}
