package saaf.common.fmw.schedule.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.base.utils.ESBClientUtils;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.base.utils.enums.ESBParams;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonJob;
import saaf.common.fmw.schedule.utils.SaafToolUtils;
import saaf.common.fmw.schedule.utils.ScheduleUtil;

import java.util.List;

import static saaf.common.fmw.services.CommonAbstractServices.SUCCESS_STATUS;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：采购寻源调度配置
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-07-08     谢晓霞             创建
 * ==============================================================================
 */
public class SrmPonJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonJob.class);

    /**
     * 寻源自动截标定时任务
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-08      谢晓霞            创建
     * ==============================================================================
     */
    public void updatePonAuctionHeaderJob(JobExecutionContext parameters){
        try {
            LOGGER.info("---------寻源自动截标定时任务---------");
            //==================== 获取时间参数 ====================
            ISrmPonJob srmPonJobServer = (ISrmPonJob) SaafToolUtils.context.getBean("srmPonJobServer");
            JSONObject paramsObject = new JSONObject();
            //查询成功
            JSONObject result = srmPonJobServer.updatePonAuctionHeaderJob();
            LOGGER.info(result.toJSONString());
            ScheduleUtil.logInfo(parameters, "返回信息:" + result.toJSONString());
        }catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
        }
    }

    /**
     * 招标询价EKP审批状态获取定时任务
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
            LOGGER.info("---------招标询价EKP审批状态获取定时任务---------");
            //==================== 获取时间参数 ====================
            ISrmPonJob srmPonJobServer = (ISrmPonJob) SaafToolUtils.context.getBean("srmPonJobServer");
            JSONObject paramsObject = new JSONObject();
            //查询成功
            JSONObject result = srmPonJobServer.saveEkpStatus();
            LOGGER.info(result.toJSONString());
            ScheduleUtil.logInfo(parameters, "返回信息:" + result.toJSONString());
        }catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
        }
    }

}
