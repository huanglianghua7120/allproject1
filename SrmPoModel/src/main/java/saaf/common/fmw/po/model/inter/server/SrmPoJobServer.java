package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.utils.ESBClientUtils;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.base.utils.enums.ESBParams;
import saaf.common.fmw.po.model.entities.SrmPoSubprojectNumEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoTechnicalNumEntity_HI;
import saaf.common.fmw.po.model.inter.ISrmPoJob;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionHeadersEntity_HI_RO;

import java.text.SimpleDateFormat;
import java.util.*;

import static saaf.common.fmw.services.CommonAbstractServices.*;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：srmPoJobServer.java
 * Description：PO调度配置
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       2020-07-09           谢晓霞             创建
 * ===========================================================================
 */
@Component("srmPoJobServer")
public class SrmPoJobServer implements ISrmPoJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoJobServer.class);
    @Autowired
    private ViewObject<SrmPoTechnicalNumEntity_HI> srmPoTechnicalNumDAO_HI;

    @Autowired
    private ViewObject<SrmPoSubprojectNumEntity_HI> srmPoSubprojectNumDAO_HI;

    private ESBClientUtils esbClientUtils = new ESBClientUtils();


    public SrmPoJobServer() {
        super();
    }
    /**
     * Description：获取EBS技改编号
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject savePoTechnicalNum() throws Exception {
        Date lastUpdateDateF = DateUtil.addDay(new Date(),-3);
        JSONObject baseQueryParams = new JSONObject();
        baseQueryParams.put("lastUpdateDateF", DateUtil.date2Str(lastUpdateDateF,"yyyy-MM-dd HH:mm:ss"));
        JSONArray businessData = new JSONArray();
        JSONObject json = new JSONObject();
        json.put("LAST_UPDATE_DATE_F", DateUtil.date2Str(lastUpdateDateF,"yyyy-MM-dd HH:mm:ss"));
        json.put("LAST_UPDATE_DATE_T", DateUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss"));
        //获取技改编号
        json.put("DATA_SOURCE","JG");
        businessData.add(json);
        JSONObject poTechnicalNumResult = esbClientUtils.doPost(ESBParams.SystemName.PCB_SRM.getValue(),
                ESBParams.SystemName.PCB_SRM.getValue(),
                ESBParams.SystemName.EBS.getValue(),
                ESBParams.ServiceName.callSrmProcedure.getValue(),
                ESBParams.BusinessServiceName.REST_SRM_PULLJGLIST.getValue(),
                SUCCESS_STATUS,baseQueryParams,businessData);
        if(null == poTechnicalNumResult.getString(STATUS) || "".equals(poTechnicalNumResult.getString(STATUS)) || !SUCCESS_STATUS.equals(poTechnicalNumResult.getString(STATUS))){
            LOGGER.error("获取技改编号请求失败，错误：{}",poTechnicalNumResult.getString(MSG));
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "获取技改编号请求失败，错误："+poTechnicalNumResult.getString(MSG), 0, poTechnicalNumResult);
        }
        JSONObject data = poTechnicalNumResult.getJSONObject(DATA);
        if(null == data){
            LOGGER.info("获取技改编号定时接口，暂无数据");
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "暂无数据", 0, poTechnicalNumResult);
        }
        JSONObject obj = data.getJSONObject("obj");
        if(null == obj){
            LOGGER.info("获取技改编号定时接口，暂无数据");
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "暂无数据", 0, poTechnicalNumResult);
        }
        JSONObject returnSystemParam = obj.getJSONObject(ESBParams.StatusCode.SYSTEM_PARAMS.getValue());
        if(null != returnSystemParam && null != returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()) && ESBParams.StatusCode.ESB_CODE_OK.getValue().equals(returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()))){
            JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
            if(null == businessDataValue || businessDataValue.isEmpty()){
                LOGGER.info("获取技改编号接口定时获取数据成功，获取数据：0");
                return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "获取技改编号接口没有可更新的数据，更新数据：0", 0, poTechnicalNumResult);
            }
            //更新技改编号表数据
            Integer count = updateSrmPoTechnicalNum(businessDataValue);
            LOGGER.info("技改编号接口获取数据成功，获取数据："+businessDataValue.size());
            LOGGER.info("技改编号更新成功，更新数据："+count);
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "技改编号更新成功，更新数据："+count, count, poTechnicalNumResult);
        }else{
            JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
            if(null != businessDataValue && !businessDataValue.isEmpty()){
                String processMsg=businessDataValue.getJSONObject(0).getString("PROCESS_MSG");
                LOGGER.info("技改编号接口获取数据："+processMsg);
                return com.yhg.base.utils.SToolUtils.convertResultJSONObj(ERROR_STATUS, "技改编号接口获取数据，错误："+processMsg, 0, poTechnicalNumResult);
            }else{
                LOGGER.error("技改编号调用接口异常");
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "技改编号调用接口异常", 0, poTechnicalNumResult);
            }
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
    public JSONObject savePoSubprojectNum() throws Exception {
        Date lastUpdateDateF = DateUtil.addDay(new Date(),-3);
        JSONObject baseQueryParams = new JSONObject();
        baseQueryParams.put("lastUpdateDateF", DateUtil.date2Str(lastUpdateDateF,"yyyy-MM-dd HH:mm:ss"));
        JSONArray businessData = new JSONArray();
        JSONObject json = new JSONObject();
        json.put("LAST_UPDATE_DATE_F", DateUtil.date2Str(lastUpdateDateF,"yyyy-MM-dd HH:mm:ss"));
        json.put("LAST_UPDATE_DATE_T", DateUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss"));
        //获取技改编号
        json.put("DATA_SOURCE","ZXM");
        businessData.add(json);
        JSONObject result = esbClientUtils.doPost(ESBParams.SystemName.PCB_SRM.getValue(),
                ESBParams.SystemName.PCB_SRM.getValue(),
                ESBParams.SystemName.EBS.getValue(),
                ESBParams.ServiceName.callSrmProcedure.getValue(),
                ESBParams.BusinessServiceName.REST_SRM_PULLJGLIST.getValue(),
                SUCCESS_STATUS,baseQueryParams,businessData);
        if(null == result.getString(STATUS) || "".equals(result.getString(STATUS)) || !SUCCESS_STATUS.equals(result.getString(STATUS))){
            LOGGER.error("获取子项目编号请求失败，错误：{}",result.getString(MSG));
            return com.yhg.base.utils.SToolUtils.convertResultJSONObj(ERROR_STATUS, "获取子项目编号请求失败，错误："+result.getString(MSG), 0, result);
        }
        JSONObject data = result.getJSONObject(DATA);
        if(null == data){
            LOGGER.info("获取子项目编号定时接口，暂无数据");
            return com.yhg.base.utils.SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "暂无数据", 0, result);
        }
        JSONObject obj = data.getJSONObject("obj");
        if(null == obj){
            LOGGER.info("获取子项目编号定时接口，暂无数据");
            return com.yhg.base.utils.SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "暂无数据", 0, result);
        }
        JSONObject returnSystemParam = obj.getJSONObject(ESBParams.StatusCode.SYSTEM_PARAMS.getValue());
        if(null != returnSystemParam && null != returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()) && ESBParams.StatusCode.ESB_CODE_OK.getValue().equals(returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()))){
            JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
            if(null == businessDataValue || businessDataValue.isEmpty()){
                LOGGER.info("获取子项目编号接口定时获取数据成功，获取数据：0");
                return com.yhg.base.utils.SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "获取子项目编号接口没有可更新的数据，更新数据：0", 0, result);
            }
            //更新技改编号表数据
            Integer count = updateSrmPoSubprojectNum(businessDataValue);
            LOGGER.info("子项目编号接口获取数据成功，获取数据："+businessDataValue.size());
            LOGGER.info("子项目编号更新成功，更新数据："+count);
            return com.yhg.base.utils.SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "子项目编号更新成功，更新数据："+count, count, result);
        }else{
            JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
            if(null != businessDataValue && !businessDataValue.isEmpty()){
                String processMsg=businessDataValue.getJSONObject(0).getString("PROCESS_MSG");
                LOGGER.info("子项目编号接口获取数据："+processMsg);
                return com.yhg.base.utils.SToolUtils.convertResultJSONObj(ERROR_STATUS, "子项目编号接口获取数据，错误："+processMsg, 0, result);
            }else{
                LOGGER.error("子项目编号调用接口异常");
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "子项目编号调用接口异常", 0, result);
            }

        }
    }




    /**
     * Description：获取EBS技改编号-保存
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Integer updateSrmPoTechnicalNum(JSONArray businessData) throws Exception{
        Integer count = 0;
        if(null != businessData && businessData.size()>0){
            for(int i=0;i<businessData.size();i++) {
                JSONObject obj = businessData.getJSONObject(i);
                try {
                    String technicalCode = obj.getString("JGBH");
                    String technicalName = obj.getString("JG_DESC");
                    String enable = obj.getString("JG_ENABLE_FLAG");
                    Date effectiveDate = null;
                    Date expirationDate = null;
                    Date creationDate = null;
                    Date lastUpdateDate = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (!ObjectUtils.isEmpty(obj.getString("JG_START_DATE"))) {
                        effectiveDate = sdf.parse(obj.getString("JG_START_DATE"));
                    }
                    if (!ObjectUtils.isEmpty(obj.getString("JG_END_DATE"))) {
                        expirationDate = sdf.parse(obj.getString("JG_END_DATE"));
                    }
                    if (!ObjectUtils.isEmpty(obj.getString("JG_CREATION_DATE"))) {
                        creationDate = sdf.parse(obj.getString("JG_CREATION_DATE"));
                    }
                    if (!ObjectUtils.isEmpty(obj.getString("JG_LAST_UPDATE_DATE"))) {
                        lastUpdateDate = sdf.parse(obj.getString("JG_LAST_UPDATE_DATE"));
                    }

                    String sourceId=obj.getString("JG_ID");
                    List<SrmPoTechnicalNumEntity_HI> poTechnicalNumList = srmPoTechnicalNumDAO_HI.findByProperty("sourceId",sourceId);
                    SrmPoTechnicalNumEntity_HI poTechnicalNum = new SrmPoTechnicalNumEntity_HI();
                    if (!ObjectUtils.isEmpty(poTechnicalNumList)) {
                        poTechnicalNum = poTechnicalNumList.get(0);
                    }
                    poTechnicalNum.setTechnicalCode(technicalCode);
                    poTechnicalNum.setTechnicalName(technicalName);
                    poTechnicalNum.setSourceId(sourceId);
                    poTechnicalNum.setEnable(enable);
                    poTechnicalNum.setEffectiveDate(effectiveDate);
                    poTechnicalNum.setExpirationDate(expirationDate);
                    poTechnicalNum.setCreationDate(creationDate);
                    poTechnicalNum.setLastUpdateDate(lastUpdateDate);
                    poTechnicalNum.setCreatedBy(-1);
                    poTechnicalNum.setLastUpdatedBy(-1);
                    poTechnicalNum.setOperatorUserId(-1);
                    count++;
                    srmPoTechnicalNumDAO_HI.saveOrUpdate(poTechnicalNum);
                    srmPoTechnicalNumDAO_HI.fluch();
                }catch (Exception e){
                    LOGGER.error("保存技改编号参数失败！参数：{}" , obj.toJSONString() + "，异常：{}" , e.getMessage());
                    throw new UtilsException("保存技改编号参数失败！参数："+obj.toJSONString() + "，异常："+ e.getMessage());
                }
            }
        }
        return count;
    }

    /**
     * Description：获取EBS子项目编号-保存
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Integer updateSrmPoSubprojectNum(JSONArray businessData) throws Exception{
        Integer count = 0;
        if(null != businessData && businessData.size()>0){
            for(int i=0;i<businessData.size();i++) {
                JSONObject obj = businessData.getJSONObject(i);
                try {
                    String  subprojectCode = obj.getString("ZXM_NUM");
                    String  subprojectName = obj.getString("ZXM_DESC");
                    String jgId=obj.getString("JG_ID");
                    String enable = obj.getString("ZXM_ENABLE_FLAG");
                    Integer technicalId=null;
                    Date effectiveDate = null;
                    Date expirationDate = null;
                    Date creationDate = null;
                    Date lastUpdateDate = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (!ObjectUtils.isEmpty(obj.getString("ZXM_START_DATE"))) {
                        effectiveDate = sdf.parse(obj.getString("ZXM_START_DATE"));
                    }
                    if (!ObjectUtils.isEmpty(obj.getString("ZXM_END_DATE"))) {
                        expirationDate = sdf.parse(obj.getString("ZXM_END_DATE"));
                    }
                    if (!ObjectUtils.isEmpty(obj.getString("ZXM_CREATION_DATE"))) {
                        creationDate = sdf.parse(obj.getString("ZXM_CREATION_DATE"));
                    }
                    if (!ObjectUtils.isEmpty(obj.getString("ZXM_LAST_UPDATE_DATE"))) {
                        lastUpdateDate = sdf.parse(obj.getString("ZXM_LAST_UPDATE_DATE"));
                    }
                    String sourceId=obj.getString("ZXM_ID");
                    List<SrmPoSubprojectNumEntity_HI> poSubprojectNumList = srmPoSubprojectNumDAO_HI.findByProperty("sourceId", sourceId);
                    SrmPoSubprojectNumEntity_HI poSubprojectNum = new SrmPoSubprojectNumEntity_HI();
                    if (!ObjectUtils.isEmpty(poSubprojectNumList)) {
                        poSubprojectNum = poSubprojectNumList.get(0);
                    } else {
                        List<SrmPoTechnicalNumEntity_HI> poTechnicalNumList = srmPoTechnicalNumDAO_HI.findByProperty("sourceId",jgId);
                        if(ObjectUtils.isEmpty(poTechnicalNumList)){
                            throw new UtilsException("系统中未存在子项目编号"+subprojectCode+" "+ subprojectName +"所对应的技改编号");
                        }else{
                            technicalId=poTechnicalNumList.get(0).getTechnicalId();
                        }
                    }
                    poSubprojectNum.setSubprojectCode(subprojectCode);
                    poSubprojectNum.setSubprojectName(subprojectName);
                    poSubprojectNum.setTechnicalId(technicalId);
                    poSubprojectNum.setSourceId(sourceId);
                    poSubprojectNum.setEnable(enable);
                    poSubprojectNum.setEffectiveDate(effectiveDate);
                    poSubprojectNum.setExpirationDate(expirationDate);
                    poSubprojectNum.setCreationDate(creationDate);
                    poSubprojectNum.setLastUpdateDate(lastUpdateDate);
                    poSubprojectNum.setCreatedBy(-1);
                    poSubprojectNum.setLastUpdatedBy(-1);
                    poSubprojectNum.setOperatorUserId(-1);
                    count++;
                    srmPoSubprojectNumDAO_HI.saveOrUpdate(poSubprojectNum);
                    srmPoSubprojectNumDAO_HI.fluch();
                }catch (Exception e){
                    LOGGER.error("保存子项目编号参数失败！参数：{}" , obj.toJSONString() + "，异常：{}" , e.getMessage());
                    throw new UtilsException("保存子项目编号参数失败！参数："+obj.toJSONString() + "，异常："+ e.getMessage());
                }
            }
        }
        return count;
    }
}
