package saaf.common.fmw.schedule.job;

import com.alibaba.fastjson.JSONObject;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saaf.common.fmw.pon.model.inter.ISrmPonJob;
import saaf.common.fmw.schedule.utils.SaafToolUtils;
import saaf.common.fmw.schedule.utils.ScheduleUtil;
import saaf.common.fmw.okc.model.inter.ISrmOkcJob;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：合同调度配置
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-07-08     谢晓霞             创建
 * ==============================================================================
 */
public class SrmOkcJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmOkcJob.class);

    /**
     * 合同EKP审批状态获取定时任务
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-08      谢晓霞            创建
     * ==============================================================================
     */
    public void saveEkpStatus(JobExecutionContext parameters){
        try {
            LOGGER.info("---------合同EKP审批状态获取定时任务---------");
            //==================== 获取时间参数 ====================
            ISrmOkcJob srmOkcJobServer = (ISrmOkcJob) SaafToolUtils.context.getBean("srmOkcJobServer");
            JSONObject paramsObject = new JSONObject();
            //查询成功
            JSONObject result = srmOkcJobServer.saveEkpStatus();
            LOGGER.info(result.toJSONString());
            ScheduleUtil.logInfo(parameters, "返回信息:" + result.toJSONString());
        }catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
        }
    }

}
