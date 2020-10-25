package saaf.common.fmw.tarkjob;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.inter.*;
import saaf.common.fmw.base.utils.ESBClientUtils;
import saaf.common.fmw.base.utils.annotation.Log;
import saaf.common.fmw.base.utils.enums.ESBParams;
import saaf.common.fmw.services.CommonAbstractServices;

import java.util.Calendar;
import java.util.Date;

/**
 * @author: ZHJ
 * @date:Created in 11:45 2019/11/8
 * @modify By:
 * @description : 基础数据的定时任务
 */
@Component("srmBaseRequestJobs")
public class SrmBaseRequestJobs extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseRequestJobs.class);
    private static final String SUCCESS = "success";
    private ESBClientUtils esbClientUtils = new ESBClientUtils();
    @Autowired
    private ISrmBaseBanks iSrmBaseBanks;
    @Autowired
    private ISrmBaseBranches iSrmBaseBranches;
    @Autowired
    private ISaafLookup iSaafLookup;
    @Autowired
    private ISrmBaseItem iSrmBaseItem;
    @Autowired
    private ISaafEmployees iSaafEmployees;
    @Autowired
    private ISrmBaseExchangeRate iSrmBaseExchangeRate;
    @Autowired
    private IESBClient iesbClient;
    public SrmBaseRequestJobs(){
        super();
    }

    /**
     * Description：银行获取定时任务（每天凌晨4点50分钟运行一次）
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-11-08     zhj             创建
     * ==============================================================================
     */
    //@Scheduled(cron = "0 50 4 * * ?")
    //@Scheduled(cron = "0 */1 * * * ?")
    /*@Log(title = "银行接口定时任务,凌晨4点50分",businessType = "银行接口，每天定时从EBS系统获取数据")
    public JSONObject updateSrmBaseBankListJob()throws Exception{
        Date lastUpdateDateF = DateUtil.addDay(new Date(),-2);
        //Date lastUpdateDateF = new Date(946656000);
        JSONObject baseQueryParams = new JSONObject();
        baseQueryParams.put("lastUpdateDateF", DateUtil.date2Str(lastUpdateDateF,"yyyy-MM-dd HH:mm:ss"));
        JSONArray businessData = new JSONArray();
        JSONObject json = new JSONObject();
        json.put("LAST_UPDATE_DATE_F", DateUtil.date2Str(lastUpdateDateF,"yyyy-MM-dd HH:mm:ss"));
        json.put("LAST_UPDATE_DATE_T", DateUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss"));
        businessData.add(json);
        LOGGER.info("----------------------银行定时任务开始，批量获取数据----------------------");
        JSONObject result = esbClientUtils.doPost(ESBParams.SystemName.PCB_SRM.getValue(),
                ESBParams.SystemName.PCB_APS.getValue(),
                ESBParams.SystemName.EBS.getValue(),
                ESBParams.ServiceName.callSrmProcedure.getValue(),
                ESBParams.BusinessServiceName.REST_SRM_PULLBANKLIST.getValue(),
                SUCCESS_STATUS,baseQueryParams,businessData);
        if(null == result.getString(STATUS) || "".equals(result.getString(STATUS)) || !SUCCESS_STATUS.equals(result.getString(STATUS))){
            LOGGER.error("银行接口请求失败，错误：{}",result.getString(MSG));
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "银行接口请求失败，错误："+result.getString(MSG), 0, result);
        }
        JSONObject data = result.getJSONObject(DATA);
        if(null == data){
            LOGGER.info("银行定时接口，暂无数据");
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "暂无数据", 0, result);
        }
        JSONObject obj = data.getJSONObject("obj");
        if(null == obj){
            LOGGER.info("银行定时接口，暂无数据");
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "暂无数据", 0, result);
        }
        JSONObject returnSystemParam = obj.getJSONObject(ESBParams.StatusCode.SYSTEM_PARAMS.getValue());
        if(null != returnSystemParam && null != returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()) && ESBParams.StatusCode.ESB_CODE_OK.getValue().equals(returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()))){
            JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
            if(null == businessDataValue || businessDataValue.isEmpty()){
                LOGGER.info("银行接口定时获取数据成功，获取数据：0");
                return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "银行接口没有可更新的数据，更新数据：0", 0, result);
            }
            //更新银行信息
            Integer count = iSrmBaseBanks.updateSrmBaseBankListJob(businessDataValue);
            LOGGER.info("银行接口定时获取数据成功，获取数据："+businessDataValue.size());
            LOGGER.info("银行接口定时更新成功，更新数据："+count);
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "银行接口定时更新成功，更新数据："+count, count, result);
        }else{
            LOGGER.error("银行调用接口异常");
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "银行调用接口异常", 0, result);
        }
    }*/

    /**
     * Description：银行分行获取定时任务（每天凌晨1点30分钟运行一次）
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-11-11     zhj             创建
     * ==============================================================================
     */
    //@Scheduled(cron = "0 0 5 * * ?")
    //@Scheduled(cron = "0 */1 * * * ?")
    /*@Log(title = "银行分行接口定时任务,凌晨1点30分",businessType = "银行分行接口，每天定时从EBS系统获取数据")
    public JSONObject updateSrmBaseBrancheListJob()throws Exception{
        Date lastUpdateDateF = DateUtil.addDay(new Date(),-2);
        //Date lastUpdateDateF = new Date(946656000);
        JSONObject baseQueryParams = new JSONObject();
        baseQueryParams.put("lastUpdateDateF", DateUtil.date2Str(lastUpdateDateF,"yyyy-MM-dd HH:mm:ss"));
        JSONArray businessData = new JSONArray();
        JSONObject json = new JSONObject();
        json.put("LAST_UPDATE_DATE_F", DateUtil.date2Str(lastUpdateDateF,"yyyy-MM-dd HH:mm:ss"));
        json.put("LAST_UPDATE_DATE_T", DateUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss"));
        businessData.add(json);
        LOGGER.info("----------------------银行分行接口定时任务开始，批量获取数据----------------------");
        JSONObject result = esbClientUtils.doPost(ESBParams.SystemName.PCB_SRM.getValue(),
                ESBParams.SystemName.PCB_APS.getValue(),
                ESBParams.SystemName.EBS.getValue(),
                ESBParams.ServiceName.callSrmProcedure.getValue(),
                ESBParams.BusinessServiceName.REST_SRM_PULLBKBBRHLIST.getValue(),
                SUCCESS_STATUS,baseQueryParams,businessData);
        if(null == result.getString(STATUS) || "".equals(result.getString(STATUS)) || !SUCCESS_STATUS.equals(result.getString(STATUS))){
            LOGGER.error("银行分行接口请求失败，错误：{}",result.getString(MSG));
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "银行分行接口请求失败，错误："+result.getString(MSG), 0, result);
        }
        JSONObject data = result.getJSONObject(DATA);
        if(null == data){
            LOGGER.info("银行分行定时接口，暂无数据");
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "暂无数据", 0, result);
        }
        JSONObject obj = data.getJSONObject("obj");
        if(null == obj){
            LOGGER.info("银行分行定时接口，暂无数据");
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "暂无数据", 0, result);
        }
        JSONObject returnSystemParam = obj.getJSONObject(ESBParams.StatusCode.SYSTEM_PARAMS.getValue());
        if(null != returnSystemParam && null != returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()) && ESBParams.StatusCode.ESB_CODE_OK.getValue().equals(returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()))){
            JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
            if(null == businessDataValue || businessDataValue.isEmpty()){
                LOGGER.info("银行分行接口定时获取数据成功，获取数据：0");
                return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "银行分行接口没有可更新的数据，更新数据：0", 0, result);
            }
            //更新分行信息
            Integer count = iSrmBaseBranches.updateSrmBaseBrancheListJob(businessDataValue);
            LOGGER.info("银行分行接口定时获取数据成功，获取数据："+businessDataValue.size());
            LOGGER.info("银行分行接口定时更新成功，更新数据："+count);
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "银行分行接口定时更新成功，更新数据："+count, count, result);
        }else{
            LOGGER.error("银行分行调用接口异常");
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "银行分行调用接口异常", 0, result);
        }
    }*/

    /**
     * Description：付款条件接口定时任务List——（每隔6个小时运行一次）
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-12-20     zhj             创建
     * ==============================================================================
     */
    //@Scheduled(cron = "0 0 0/6 * * ?")
    //@Scheduled(cron = "0 */1 * * * ?")
    /*@Log(title = "付款条件接口定时任务,每隔6个小时",businessType = "付款条件接口，每天定时从EBS系统获取数据")
    public JSONObject updateSrmPonPaymentTermsListJob() throws Exception{
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        //过去8小时前的时间
        cal.add(Calendar.HOUR, -8);
        Date lastUpdateDateF = cal.getTime();
        //Date lastUpdateDateF = new Date(946656000);
        JSONObject baseQueryParams = new JSONObject();
        baseQueryParams.put("lastUpdateDateF",DateUtil.date2Str(lastUpdateDateF,"yyyy-MM-dd HH:mm:ss"));
        JSONArray businessData = new JSONArray();
        JSONObject json = new JSONObject();
        json.put("LAST_UPDATE_DATE_F",DateUtil.date2Str(lastUpdateDateF,"yyyy-MM-dd HH:mm:ss"));
        json.put("LAST_UPDATE_DATE_T", DateUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss"));
        businessData.add(json);
        LOGGER.info("----------------------付款条件接口定时任务开始，批量获取数据----------------------");
        JSONObject result = esbClientUtils.doPost(ESBParams.SystemName.PCB_SRM.getValue(),
                ESBParams.SystemName.PCB_SRM.getValue(),
                ESBParams.SystemName.EBS.getValue(),
                ESBParams.ServiceName.callSrmProcedure.getValue(),
                ESBParams.BusinessServiceName.REST_SRM_PULLPAYTERMLIST.getValue(),
                SUCCESS_STATUS,baseQueryParams,businessData);
        if(null == result.getString(STATUS) || "".equals(result.getString(STATUS)) || !SUCCESS_STATUS.equals(result.getString(STATUS))){
            LOGGER.error("付款条件接口请求失败，错误：{}",result.getString(MSG));
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "付款条件接口请求失败，错误："+result.getString(MSG), 0, result);
        }
        JSONObject data = result.getJSONObject(DATA);
        if(null == data){
            LOGGER.info("付款条件定时接口，暂无数据");
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "暂无数据", 0, result);
        }
        JSONObject obj = data.getJSONObject("obj");
        if(null == obj){
            LOGGER.info("付款条件定时接口，暂无数据");
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "暂无数据", 0, result);
        }
        JSONObject returnSystemParam = obj.getJSONObject(ESBParams.StatusCode.SYSTEM_PARAMS.getValue());
        if(null != returnSystemParam && null != returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()) && ESBParams.StatusCode.ESB_CODE_OK.getValue().equals(returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()))){
            JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
            if(null == businessDataValue || businessDataValue.isEmpty()){
                LOGGER.info("付款条件接口定时获取数据成功，获取数据：0");
                return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "付款条件接口没有可更新的数据，更新数据：0", 0, result);
            }
            //更新付款条件
            Integer count = iesbClient.updateSrmPonPaymentTermsListJob(businessDataValue);
            LOGGER.info("付款条件接口定时获取数据成功，获取数据："+businessDataValue.size());
            LOGGER.info("付款条件接口定时更新成功，更新数据："+count);
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "付款条件接口定时更新成功，更新数据："+count, count, result);
        }else{
            LOGGER.error("付款条件调用接口异常");
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "付款条件调用接口异常", 0, result);
        }
    }*/

/*    @Scheduled(cron = "0 50 4 * * ?")
    @Log(title = "银行接口定时任务,凌晨4点50分",businessType = "银行接口，每天定时从EBS系统获取数据")
    public JSONObject updateJob()throws Exception{
        Date lastUpdateDateF = DateUtil.addDay(new Date(),-2);
        JSONObject baseQueryParams = new JSONObject();
        baseQueryParams.put("lastUpdateDateF", DateUtil.date2Str(lastUpdateDateF,"yyyy-MM-dd HH:mm:ss"));
        JSONArray businessData = new JSONArray();
        JSONObject json = new JSONObject();
        json.put("LAST_UPDATE_DATE_F", DateUtil.date2Str(lastUpdateDateF,"yyyy-MM-dd HH:mm:ss"));
        json.put("LAST_UPDATE_DATE_T", DateUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss"));
        businessData.add(json);
        LOGGER.info("----------------------银行定时任务开始，批量获取数据----------------------");
        LOGGER.info(json.toString());
        LOGGER.info("----------------------银行定时任务结束----------------------");
        return json;
    }*/

}
