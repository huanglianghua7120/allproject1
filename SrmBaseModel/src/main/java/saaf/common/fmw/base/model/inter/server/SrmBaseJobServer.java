package saaf.common.fmw.base.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import saaf.common.fmw.base.model.inter.IESBClient;
import saaf.common.fmw.base.model.inter.ISrmBaseBanks;
import saaf.common.fmw.base.model.inter.ISrmBaseBranches;
import saaf.common.fmw.base.model.inter.ISrmBaseJob;
import saaf.common.fmw.base.utils.ESBClientUtils;
import saaf.common.fmw.base.utils.enums.ESBParams;

import java.util.Calendar;
import java.util.Date;

import static saaf.common.fmw.services.CommonAbstractServices.*;

@Component("srmBaseJobServer")
public class SrmBaseJobServer implements ISrmBaseJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseJobServer.class);
    private ESBClientUtils esbClientUtils = new ESBClientUtils();
    @Autowired
    private ISrmBaseBanks iSrmBaseBanks;

    @Autowired
    private ISrmBaseBranches iSrmBaseBranches;

    @Autowired
    private IESBClient iesbClient;
    public SrmBaseJobServer() {
        super();
    }

    /**
     * Description：银行接口定时任务,每天定时从EBS系统获取数据
     * @return Integer
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-07-09           谢晓霞             创建
     * =======================================================================
     */

    //@Transactional(rollbackFor = { Exception.class })
    @Override
    public JSONObject updateSrmBaseBankListJob() throws Exception{
        try {
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
                JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
                if(null != businessDataValue && !businessDataValue.isEmpty()){
                    String processMsg=businessDataValue.getJSONObject(0).getString("PROCESS_MSG");
                    LOGGER.info("银行接口获取数据："+processMsg);
                    return com.yhg.base.utils.SToolUtils.convertResultJSONObj(ERROR_STATUS, "银行接口获取数据，错误："+processMsg, 0, result);
                }else{
                    LOGGER.error("银行调用接口异常");
                    return SToolUtils.convertResultJSONObj(ERROR_STATUS, "银行调用接口异常", 0, result);
                }
            }
        }catch (Exception e){
            //手动回滚事务
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOGGER.error("未知错误:{}", e);
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"操作失败"+e.getMessage(),0,null);
        }
    }

    /**
     * Description：银行分行接口定时任务,每天定时从EBS系统获取数据
     * @return Integer
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-07-09           谢晓霞             创建
     * =======================================================================
     */
    //@Transactional(rollbackFor = { Exception.class })
    @Override
    public JSONObject updateSrmBaseBrancheListJob() throws Exception{
        try {
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
                JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
                if(null != businessDataValue && !businessDataValue.isEmpty()){
                    String processMsg=businessDataValue.getJSONObject(0).getString("PROCESS_MSG");
                    LOGGER.info("银行分行接口获取数据："+processMsg);
                    return com.yhg.base.utils.SToolUtils.convertResultJSONObj(ERROR_STATUS, "银行分行接口获取数据，错误："+processMsg, 0, result);
                }else{
                    LOGGER.error("银行分行调用接口异常");
                    return SToolUtils.convertResultJSONObj(ERROR_STATUS, "银行分行调用接口异常", 0, result);
                }

            }
        }catch (Exception e){
            //手动回滚事务
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOGGER.error("未知错误:{}", e);
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"操作失败"+e.getMessage(),0,null);
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
   // @Transactional(rollbackFor = { Exception.class })
    @Override
    public JSONObject updateSrmPonPaymentTermsListJob() throws Exception{
        try {
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
                JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
                if(null != businessDataValue && !businessDataValue.isEmpty()){
                    String processMsg=businessDataValue.getJSONObject(0).getString("PROCESS_MSG");
                    LOGGER.info("付款条件接口获取数据："+processMsg);
                    return com.yhg.base.utils.SToolUtils.convertResultJSONObj(ERROR_STATUS, "付款条件接口获取数据，错误："+processMsg, 0, result);
                }else{
                    LOGGER.error("付款条件调用接口异常");
                    return SToolUtils.convertResultJSONObj(ERROR_STATUS, "付款条件调用接口异常", 0, result);
                }


            }
        }catch (Exception e){
            //手动回滚事务
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOGGER.error("未知错误:{}", e);
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"操作失败"+e.getMessage(),0,null);
        }
    }
}
