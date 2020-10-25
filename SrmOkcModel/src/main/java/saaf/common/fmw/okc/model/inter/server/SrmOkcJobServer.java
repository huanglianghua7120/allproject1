package saaf.common.fmw.okc.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.utils.ESBClientUtils;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.base.utils.enums.ESBParams;
import saaf.common.fmw.okc.model.entities.SrmOkcContractsEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractsEntity_HI_RO;
import saaf.common.fmw.okc.model.inter.ISrmOkcJob;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;
import static saaf.common.fmw.services.CommonAbstractServices.SUCCESS_STATUS;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：srmOkcJobServer.java
 * Description：合同调度配置
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           	   Updated By     Description
 * -------    ---------------    -----------    ---------------
 * V1.0       2020-07-09           谢晓霞             创建
 * ===========================================================================
 */
@Component("srmOkcJobServer")
public class SrmOkcJobServer implements ISrmOkcJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmOkcJobServer.class);
    @Autowired
    private BaseViewObject<SrmOkcContractsEntity_HI_RO> srmOkcContractsDAO_HI_RO;


    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值


    @Autowired
    private ViewObject<SrmOkcContractsEntity_HI> srmOkcContractsDAO_HI;

    public SrmOkcJobServer() {
        super();
    }
    /**
     * Description：合同EKP审批状态获取定时任务
     *
     * @return
     * @throws Exception
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09      谢晓霞             创建
     * ==============================================================================
     */
   @Override
    public JSONObject saveEkpStatus() throws Exception {
        try {
            StringBuffer sql = new StringBuffer(SrmOkcContractsEntity_HI_RO.QUERY_EKP_NUMBER);
            sql.append(" and soc.Attribute1 is not null");
            List<SrmOkcContractsEntity_HI_RO> list = srmOkcContractsDAO_HI_RO.findList(sql.toString());
            if (null != list && list.size() > 0) {
                ESBClientUtils esb = new ESBClientUtils();
                JSONObject baseQueryParams = new JSONObject();
                JSONArray businessData = new JSONArray();
                for (SrmOkcContractsEntity_HI_RO ro : list) {
                    try{
                        JSONArray jsonArray = findEkpId(ro.getEkpNumber());
                        if (!ObjectUtils.isEmpty(jsonArray)) {
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            //查询节点快码
                            if ("W1".equals(ro.getContractType())) {
                                //查询业务实体区域
                                StringBuffer instRegionString = new StringBuffer(SrmOkcContractsEntity_HI_RO.QUERY_INST_REGION);
                                instRegionString.append(" and  siv.Inst_Id=" + ro.getPartyAId());
                                List<SrmOkcContractsEntity_HI_RO> instRegionList = srmOkcContractsDAO_HI_RO.findList(instRegionString.toString());
                                if (null == instRegionList || instRegionList.isEmpty()) {
                                    throw new RuntimeException("无此甲方组织");
                                }
                                String instRegion = instRegionList.get(0).getInstRegion();

                                Map<String, Object> mapV = new HashMap<>();
                                mapV.put("tag", "20");

                                if ("SHENZHEN".equals(instRegion)) { //深圳
                                    mapV.put("lookupType", "SZ_CONTRACT_EKP_NODE");
                                } /*else if ("NANTONG".equals(instRegion)) { //南通
                                mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE_NT");
                            } else if ("WUXI".equals(instRegion)) {//无锡
                                mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE_WX");
                            }*/
                                List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                                String node = "";
                                if (null != lookupValueList && lookupValueList.size() > 0) {
                                    node = lookupValueList.get(0).getLookupCode();
                                } else {
                                    throw new UtilsException("请在快码中配置合同EKP流程接出节点！");
                                }
                                if (!ObjectUtils.isEmpty(jsonObject.getString("fdFactNodeId"))&& node.equals(jsonObject.getString("fdFactNodeId"))) {
                                    SrmOkcContractsEntity_HI srmOkcContractsEntity_HI = srmOkcContractsDAO_HI.getById(ro.getContractId());
                                    srmOkcContractsEntity_HI.setContractApprovalStatus("4");
                                    srmOkcContractsEntity_HI.setOperatorUserId(-999);
                                    srmOkcContractsDAO_HI.update(srmOkcContractsEntity_HI);
                                }else{
                                    JSONObject businessJson = new JSONObject();
                                    businessJson.put("fdId", ro.getAttribute1());
                                    businessData.add(businessJson);
                                }
                            }
                        }
                    }catch (Exception e){
                        LOGGER.info("接口返回结果异常："+e.getMessage());
                        continue;
                    }
                }

                JSONObject result = esb.doPost(ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.SRM_SERVER.getValue(),
                        ESBParams.SystemName.EKP.getValue(), ESBParams.ServiceName.EKP_REVIEW_MAIN.getValue(),
                        null, "S", baseQueryParams, businessData);
                LOGGER.info("----------------------EKP查询审批返回结果: " + result);
                if (SUCCESS_STATUS.equalsIgnoreCase(result.getString("status"))) {
                    if (null != result.getJSONObject("data")) {
                        JSONObject data = result.getJSONObject("data");
                        if (null != data.getJSONObject("obj")) {
                            JSONObject obj = data.getJSONObject("obj");
                            if (null != obj.getJSONArray("rows") && obj.getJSONArray("rows").size() > 0) {
                                JSONArray rows = obj.getJSONArray("rows");
                                LOGGER.info("----------------------查询EKP审批结果,返回结果: " + rows);
                                for (int i = 0; i < rows.size(); i++) {
                                    JSONObject row = rows.getJSONObject(i);
                                    sql.append(" and soc.attribute1 ='" +row.getString("fdId")+"'");
                                    List<SrmOkcContractsEntity_HI_RO> rowList = srmOkcContractsDAO_HI_RO.findList(sql.toString());
                                    //List<SrmOkcContractsEntity_HI> rowList = srmOkcContractsDAO_HI.findByProperty("attribute1", row.getString("fdId"));
                                    if (null != rowList && rowList.size() > 0) {
                                        for (int n = 0; n < rowList.size(); n++) {
                                            SrmOkcContractsEntity_HI_RO qualInfo = rowList.get(n);
                                            if (!"20".equals(row.getString("docStatus"))) {
                                                if ("30".equals(row.getString("docStatus"))) {
                                                    SrmOkcContractsEntity_HI srmOkcContractsEntity_HI = srmOkcContractsDAO_HI.getById(qualInfo.getContractId());
                                                    srmOkcContractsEntity_HI.setContractApprovalStatus("4");
                                                    srmOkcContractsEntity_HI.setOperatorUserId(-999);
                                                    srmOkcContractsDAO_HI.update(srmOkcContractsEntity_HI);
                                                    srmOkcContractsDAO_HI.fluch();
                                                } else if ("11".equals(row.getString("docStatus"))) {
                                                    SrmOkcContractsEntity_HI srmOkcContractsEntity_HI = srmOkcContractsDAO_HI.getById(qualInfo.getContractId());
                                                    srmOkcContractsEntity_HI.setContractApprovalStatus("5");
                                                    srmOkcContractsEntity_HI.setContractStatus("1");
                                                    srmOkcContractsEntity_HI.setOperatorUserId(-999);
                                                    srmOkcContractsDAO_HI.update(srmOkcContractsEntity_HI);
                                                    srmOkcContractsDAO_HI.fluch();
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                throw new UtilsException("查询EKP审批结果返回结果rows不能为空, 返回结果: " + obj);
                            }
                        } else {
                            throw new UtilsException("查询EKP审批结果返回结果obj为空, 返回结果: " + data);
                        }
                    } else {
                        throw new UtilsException("查询EKP审批结果,查询SRM调用EKP接口srm工具类返回结果data为空, 返回结果: " + result);
                    }
                } else {
                    throw new UtilsException("查询EKP审批结果查询EKP接口返回结果不为S, 返回结果: " + result);
                }
            }
            return SToolUtils.convertResultJSONObj("S", "获取合同审批结果成功", 0, null);
        }catch (Exception e){
            LOGGER.error("未知错误:{}", e);
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"操作失败"+e.getMessage(),0,null);
        }
    }

    /**
     * Description：通过EKP编号查询流程节点
     *
     * @return JSONArray
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-05-29         xiexiaoxia      创建
     * =======================================================================
     */
    private JSONArray findEkpId(String ekpNumber) throws Exception {
        JSONArray rows = null;
        String isSuccess = null;
        ESBClientUtils esb = new ESBClientUtils();
        JSONObject baseQueryParams = new JSONObject();
        JSONArray businessData = new JSONArray();
        JSONObject businessJson = new JSONObject();
        businessJson.put("fdNumber", ekpNumber);
        businessData.add(businessJson);
        JSONObject result = esb.doPost(ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.SRM_SERVER.getValue(),
                ESBParams.SystemName.EKP.getValue(), ESBParams.ServiceName.EKP_REVIEW_NODE.getValue(),
                null, "S", baseQueryParams, businessData);
        LOGGER.info("----------------------EKP查询返回结果: " + result);
        if (SUCCESS_STATUS.equalsIgnoreCase(result.getString("status"))) {
            if (null != result.getJSONObject("data")) {
                JSONObject data = result.getJSONObject("data");
                if (null != data.getJSONObject("obj")) {
                    JSONObject obj = data.getJSONObject("obj");
                    if (null != obj.getJSONArray("rows") && obj.getJSONArray("rows").size() > 0) {
                        rows = obj.getJSONArray("rows");
                        LOGGER.info("----------------------查询EKP流程ID,返回结果: " + rows);
                        return rows;
                    } else {
                        throw new UtilsException("查询EKP流程节点返回结果rows不能为空, 返回结果: " + result);
                    }
                } else {
                    throw new UtilsException("查询EKP流程节点返回结果obj为空, 返回结果: " + result);
                }
            } else {
                throw new UtilsException("查询EKP流程节点,查询SRM调用EKP接口srm工具类返回结果data为空, 返回结果: " + result);
            }
        } else {
            throw new UtilsException("查询EKP流程节点查询EKP接口返回结果不为S, 返回结果: " + result);
        }
    }


}
