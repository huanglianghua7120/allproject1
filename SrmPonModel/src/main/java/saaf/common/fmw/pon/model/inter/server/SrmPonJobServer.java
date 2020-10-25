package saaf.common.fmw.pon.model.inter.server;

import com.alibaba.fastjson.JSON;
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
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.utils.ESBClientUtils;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.base.utils.enums.ESBParams;
import saaf.common.fmw.pon.model.entities.*;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.*;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;
import static saaf.common.fmw.services.CommonAbstractServices.SUCCESS_STATUS;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：srmPonJobServer.java
 * Description：寻源调度配置
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           	   Updated By     Description
 * -------    ---------------    -----------    ---------------
 * V1.0       2020-07-09           谢晓霞             创建
 * ===========================================================================
 */
@Component("srmPonJobServer")
public class SrmPonJobServer implements ISrmPonJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonJobServer.class);
    @Autowired
    private ViewObject<SrmPonAuctionHeadersEntity_HI> srmPonAuctionHeadersDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonAuctionHeadersEntity_HI_RO> srmPonAuctionHeadersDAO_HI_RO;

    @Autowired
    private ISrmPonAuctionHeaders iSrmPonAuctionHeaders;//供应商报价头表

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值

    public SrmPonJobServer() {
        super();
    }

    /**
     * Description：寻源自动截标定时任务
     *
     * @return Integer
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-07-09           谢晓霞             创建
     * =======================================================================
     */
    @Override
    public JSONObject updatePonAuctionHeaderJob() {
        Integer count = 0;
        String queryString = "from SrmPonAuctionHeadersEntity_HI h where h.auctionStatus = 'PUBLISHED' and h.bidEndDate <= :endDate ";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("endDate", new Date());
        List<SrmPonAuctionHeadersEntity_HI> lists = srmPonAuctionHeadersDAO_HI.findList(queryString.toString(), map);
        if (null == lists || lists.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无寻源单据需截标", 0, null);
        }

        for (SrmPonAuctionHeadersEntity_HI list : lists) {
            try {
                list.setAuctionStatus("CLOSED");
                list.setCloseBiddingDate(new Date());
                list.setOperatorUserId(1);
                srmPonAuctionHeadersDAO_HI.saveOrUpdate(list);
                count++;
            } catch (Exception e) {
                LOGGER.error("未知错误:{}", e);
                continue;
            }
        }
        return SToolUtils.convertResultJSONObj("S", "寻源单据截标成功", 0, null);
    }

    /**
     * Description：招标询价EKP审批状态获取定时任务
     *
     * @return
     * @throws Exception
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09      谢晓霞             创建
     * ==============================================================================
     */
    //@Transactional(rollbackFor = { Exception.class })
    @Override
    public JSONObject saveEkpStatus() throws Exception {
        try {
            StringBuffer sql = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_AUCTIONINFO_FROMEKP);
            sql.append(" and Spah.Attribute1 is not null");
            List<SrmPonAuctionHeadersEntity_HI_RO> list = srmPonAuctionHeadersDAO_HI_RO.findList(sql.toString());
            if (null != list && list.size() > 0) {
                ESBClientUtils esb = new ESBClientUtils();
                JSONObject baseQueryParams = new JSONObject();
                JSONArray businessData = new JSONArray();
                for (SrmPonAuctionHeadersEntity_HI_RO ro : list) {
                    try{
                        JSONArray jsonArray = findEkpId(ro.getEkpNumber());
                        if (!ObjectUtils.isEmpty(jsonArray)) {
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            //查询节点快码
                            if ("ENGINEERING".equals(ro.getItemType())) {
                                //查询业务实体区域
                                StringBuffer instRegionString = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_INST_REGION);
                                instRegionString.append(" and  siv.Inst_Id=" + ro.getOrgId());
                                List<SrmPonAuctionHeadersEntity_HI_RO> instRegionList = srmPonAuctionHeadersDAO_HI_RO.findList(instRegionString.toString());
                                if (null == instRegionList || instRegionList.isEmpty()) {
                                    throw new RuntimeException("无此业务实体");
                                }
                                String instRegion = instRegionList.get(0).getInstRegion();

                                Map<String, Object> mapV = new HashMap<>();
                                mapV.put("tag", "20");

                                if ("SHENZHEN".equals(instRegion)) { //深圳
                                    mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE");
                                } else if ("NANTONG".equals(instRegion)) { //南通
                                    mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE_NT");
                                } else if ("WUXI".equals(instRegion)) {//无锡
                                    mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE_WX");
                                }
                                List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                                String node = "";
                                if (null != lookupValueList && lookupValueList.size() > 0) {
                                    node = lookupValueList.get(0).getLookupCode();
                                } else {
                                    throw new UtilsException("请在快码中配置招标询价EKP流程接出节点！");
                                }
                                if (!ObjectUtils.isEmpty(jsonObject.getString("fdFactNodeId"))&& node.equals(jsonObject.getString("fdFactNodeId"))) {
                                    JSONObject params = new JSONObject();
                                    params.put("auctionHeaderId", ro.getAuctionHeaderId());
                                    params.put("varUserId", -999);
                                    params.put("action", "APPROVED");
                                    iSrmPonAuctionHeaders.updatePonAuctionHeadersApprove(params);
                                }else{
                                    JSONObject businessJson = new JSONObject();
                                    businessJson.put("fdId", ro.getAttribute1());
                                    businessData.add(businessJson);
                                }
                            }else if ("INFORMATION_TECHNOLOGY".equals(ro.getItemType())) {
                                Map<String, Object> mapV = new HashMap<>();
                                mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE_IT");
                                mapV.put("tag", "20");
                                List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                                if (null == lookupValueList || lookupValueList.size() <= 0) {
                                    throw new UtilsException("请在快码中配置IT招标询价EKP流程接出节点！");
                                }
                                List<String> lookCodeList = lookupValueList.stream().map(SaafLookupValuesEntity_HI::getLookupCode).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList()).stream().filter(String -> String != null).collect(Collectors.toList());
                                if (!ObjectUtils.isEmpty(jsonObject.getString("fdFactNodeId")) && lookCodeList.contains(jsonObject.getString("fdFactNodeId"))) {
                                    JSONObject params = new JSONObject();
                                    params.put("auctionHeaderId", ro.getAuctionHeaderId());
                                    params.put("varUserId", -999);
                                    params.put("action", "APPROVED");
                                    iSrmPonAuctionHeaders.updatePonAuctionHeadersApprove(params);
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
                   /* JSONObject businessJson = new JSONObject();
                    businessJson.put("fdId", ro.getAttribute1());
                    businessData.add(businessJson);*/
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
                                    sql.append(" and Spah.attribute1 =" +row.getString("fdId"));
                                    List<SrmPonAuctionHeadersEntity_HI> rowList = srmPonAuctionHeadersDAO_HI.findList(sql.toString());
                                    //List<SrmPonAuctionHeadersEntity_HI> rowList = srmPonAuctionHeadersDAO_HI.findByProperty("attribute1", row.getString("fdId"));
                                    if (null != rowList && rowList.size() > 0) {
                                        for (int n = 0; n < rowList.size(); n++) {
                                            SrmPonAuctionHeadersEntity_HI qualInfo = rowList.get(n);
                                            if (!"20".equals(row.getString("docStatus"))) {
                                                JSONObject params = new JSONObject();
                                                params.put("auctionHeaderId", qualInfo.getAuctionHeaderId());
                                                params.put("varUserId", -999);
                                                if ("30".equals(row.getString("docStatus"))) {
                                                    params.put("action", "APPROVED");
                                                    iSrmPonAuctionHeaders.updatePonAuctionHeadersApprove(params);
                                                } else if ("11".equals(row.getString("docStatus"))) {
                                                    params.put("action", "REJECT");
                                                    iSrmPonAuctionHeaders.updatePonAuctionHeadersApprove(params);
                                                }
                                                /*if ("S".equals(responseJson.getString("status"))) {
                                                    qualInfo.setOperatorUserId(-999);
                                                    srmPonAuctionHeadersDAO_HI.saveEntity(qualInfo);
                                                }*/
                                            }
                                        }
                                    }
                                }
                            } else {
                                throw new UtilsException("查询EKP审批结果返回结果rows不能为空, 返回结果: " + result);
                            }
                        } else {
                            throw new UtilsException("查询EKP审批结果返回结果obj为空, 返回结果: " + result);
                        }
                    } else {
                        throw new UtilsException("查询EKP审批结果,查询SRM调用EKP接口srm工具类返回结果data为空, 返回结果: " + result);
                    }
                } else {
                    throw new UtilsException("查询EKP审批结果查询EKP接口返回结果不为S, 返回结果: " + result);
                }
            }
            return SToolUtils.convertResultJSONObj("S", "获取招标询价结果成功", 0, null);
        }catch (Exception e){
            //手动回滚事务
            // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
