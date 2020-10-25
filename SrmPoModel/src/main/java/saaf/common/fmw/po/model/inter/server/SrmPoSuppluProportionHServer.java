package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SaafInstitutionEntity_HI_RO;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoSupplyProportionHEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoSupplyProportionLEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoSupplyProportionHEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoSupplyProportionH;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供货比例头表
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-20     hgq             modify
 * ==============================================================================
 */
@Component("srmPoSuppluProportionHServer")
public class SrmPoSuppluProportionHServer implements ISrmPoSupplyProportionH {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoSuppluProportionHServer.class);

    @Autowired
    private BaseViewObject<SaafInstitutionEntity_HI_RO> Institution_HI_RO;


    @Autowired
    private BaseViewObject<SrmPoSupplyProportionHEntity_HI_RO> supplyProportionHDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPoSupplyProportionHEntity_HI> srmPoSupplyProportionHDAO_HI;

    @Autowired
    private ViewObject<SrmPoSupplyProportionLEntity_HI> srmPoSupplyProportionLDAO_HI;
    /**
     * Description：查询供货比例
     * @param parameters 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Override
    public Pagination<SrmPoSupplyProportionHEntity_HI_RO> findSupplyProportion(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer queryString = new StringBuffer(SrmPoSupplyProportionHEntity_HI_RO.QUERY_SUPPLY_PROPORTION_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(parameters, "sph.SUPPLY_TYPE", "supplyType", queryString, map, "=");
        // 物料类型SMALL_CATEGORY_CODE
        SaafToolUtils.parperParam(parameters, "sph.SMALL_CATEGORY_CODE", "categoryCode", queryString, map, "=");
        SaafToolUtils.parperParam(parameters, "sph.SMALL_CATEGORY_CODE", "smallCategoryCode", queryString, map, "=");
        // 比例分类
        // ITEM_CODE
        SaafToolUtils.parperParam(parameters, "sph.ITEM_CODE", "itemCode", queryString, map, "=");
        // 物料名称SMALL_CATEGORY_NAME
        // 分厂INST_ID
        SaafToolUtils.parperParam(parameters, "sph.INST_ID", "instId", queryString, map, "=");
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" ORDER BY sph.SUPPLY_ID DESC ");
        Pagination<SrmPoSupplyProportionHEntity_HI_RO> h2Entity_HI_ROs = supplyProportionHDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
        return h2Entity_HI_ROs;
    }
    /**
     * Description：保存供货列表
     * @param params 参数
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * supplyId  表ID，主键，供其他表做外键  NUMBER  Y
     * supplyType  供货比例类型  VARCHAR2  Y
     * smallCategoryCode  物料类别  VARCHAR2  N
     * smallCategoryName  物料类别名称  VARCHAR2  N
     * itemId    NUMBER  N
     * itemCode  物料编码  VARCHAR2  N
     * itemDescription  物料编码名称  VARCHAR2  N
     * instId  分厂ID  NUMBER  N
     * startDateActive  起始日期  DATE  Y
     * endDateActive  终止日期  DATE  N
     * description  说明、备注  VARCHAR2  N
     * status  状态  VARCHAR2  Y
     * supplyId  表ID，主键，供其他表做外键  NUMBER  Y
     * supplyType  供货比例类型  VARCHAR2  Y
     * smallCategoryCode  物料类别  VARCHAR2  N
     * smallCategoryName  物料类别名称  VARCHAR2  N
     * itemId    NUMBER  N
     * itemCode  物料编码  VARCHAR2  N
     * itemDescription  物料编码名称  VARCHAR2  N
     * instId  分厂ID  NUMBER  N
     * startDateActive  起始日期  DATE  Y
     * endDateActive  终止日期  DATE  N
     * description  说明、备注  VARCHAR2  N
     * status  状态  VARCHAR2  Y
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    public JSONObject saveSupplyProportionH(JSONObject params) throws Exception {
        SrmPoSupplyProportionHEntity_HI poSupplyProportionHEntity_HI = null;
        JSONObject obj = params.getJSONObject("data");
        Integer varUserId = params.getInteger("varUserId");
        if(null == obj.getString("supplyType") || "".equals(obj.getString("supplyType"))){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"供货比例分类必填",0,null);
        }
        //如果id为空则新增 否则为修改
        if (null == obj.get("supplyId")) {
            poSupplyProportionHEntity_HI = new SrmPoSupplyProportionHEntity_HI();
            poSupplyProportionHEntity_HI.setSupplyType(obj.getString("supplyType"));
            poSupplyProportionHEntity_HI.setSmallCategoryCode(obj.getString("smallCategoryCode"));
            poSupplyProportionHEntity_HI.setSmallCategoryName(obj.getString("smallCategoryName"));
            poSupplyProportionHEntity_HI.setItemDescription(obj.getString("itemDescription"));
            poSupplyProportionHEntity_HI.setInstId(obj.getInteger("instId"));
            poSupplyProportionHEntity_HI.setItemId(obj.getInteger("itemId"));
            poSupplyProportionHEntity_HI.setItemCode(obj.getString("itemCode"));
            poSupplyProportionHEntity_HI.setStartDateActive(obj.getDate("startDateActive"));
            if (null != obj.get("endDateActive") && !"".equals(obj.getString("endDateActive").trim())) {
                Date endDateActive = SToolUtils.string2DateTime(obj.getString("endDateActive"), "yyyy-MM-dd");
                poSupplyProportionHEntity_HI.setEndDateActive(endDateActive);
            } else {
                poSupplyProportionHEntity_HI.setEndDateActive(null);
            }
            poSupplyProportionHEntity_HI.setStatus(obj.getString("status"));
            poSupplyProportionHEntity_HI.setLastUpdateDate(new Date());
            poSupplyProportionHEntity_HI.setCreationDate(new Date());
            poSupplyProportionHEntity_HI.setOperatorUserId(varUserId);
            poSupplyProportionHEntity_HI.setLastUpdatedBy(-1);
            poSupplyProportionHEntity_HI.setCreatedBy(varUserId);
            //新增
            LOGGER.info("add----->>>" + poSupplyProportionHEntity_HI);
            LOGGER.info("obj----->>>" + obj);
            srmPoSupplyProportionHDAO_HI.save(poSupplyProportionHEntity_HI);
        } else {
            poSupplyProportionHEntity_HI = srmPoSupplyProportionHDAO_HI.findByProperty("supplyId", obj.get("supplyId")).get(0);
            poSupplyProportionHEntity_HI.setSupplyType(obj.getString("supplyType"));
            poSupplyProportionHEntity_HI.setSmallCategoryName(obj.getString("smallCategoryName"));
            poSupplyProportionHEntity_HI.setInstId(obj.getInteger("instId"));
            poSupplyProportionHEntity_HI.setItemCode(obj.getString("itemCode"));
            if (null != obj.get("startDateActive") && !"".equals(obj.getString("startDateActive").trim())) {
                Date startDateActive = SToolUtils.string2DateTime(obj.getString("startDateActive"), "yyyy-MM-dd");
                poSupplyProportionHEntity_HI.setStartDateActive(startDateActive);
            }
            poSupplyProportionHEntity_HI.setEndDateActive(obj.getDate("endDateActive"));
            poSupplyProportionHEntity_HI.setOperatorUserId(varUserId);
            poSupplyProportionHEntity_HI.setStatus(obj.getString("status"));
            poSupplyProportionHEntity_HI.setLastUpdateDate(new Date());
            poSupplyProportionHEntity_HI.setLastUpdatedBy(varUserId);
            LOGGER.info("update----->>>" + poSupplyProportionHEntity_HI);
            srmPoSupplyProportionHDAO_HI.update(poSupplyProportionHEntity_HI);
        }
        return SToolUtils.convertResultJSONObj("S", "保存供货比例成功", 1, poSupplyProportionHEntity_HI);
    }
    /**
     * Description：导出供货比例
     * @param params 参数
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Override
    public List<SrmPoSupplyProportionHEntity_HI_RO> findSupplyExport(JSONObject params) throws Exception {
        StringBuffer queryString = new StringBuffer(SrmPoSupplyProportionHEntity_HI_RO.QUERY_SUPPLY_PRINT_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        LOGGER.info("============" + JSONObject.toJSONString(params));
        SaafToolUtils.parperParam(params, "h.SUPPLY_TYPE", "supplyType", queryString, map, "=");
        // 物料类型SMALL_CATEGORY_CODE
        SaafToolUtils.parperParam(params, "h.SMALL_CATEGORY_CODE", "categoryCode", queryString, map, "=");
        // 比例分类
        // ITEM_CODE
        SaafToolUtils.parperParam(params, "h.ITEM_CODE", "itemCode", queryString, map, "=");
        // 物料名称SMALL_CATEGORY_NAME
        // 分厂INST_ID
        SaafToolUtils.parperParam(params, "h.INST_ID", "instId", queryString, map, "=");
        queryString.append(" ORDER BY h.SUPPLY_ID DESC");
        return supplyProportionHDAO_HI_RO.findList(queryString, map);
    }
    /**
     * Description：批量导入供货比例
     * @param params 参数
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Override
    public JSONObject saveList(JSONObject params) throws Exception {
        LOGGER.info("params:-{}", JSON.toJSONString(params));
        JSONArray jsonArray = params.getJSONArray("data");
        if(null == jsonArray || jsonArray.isEmpty()){
            SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        JSONArray error = cehckArray(jsonArray);
        Integer userId = params.getInteger("varUserId");
        if (null != error && error.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存失败", error.size(), error);
        }
        List<SrmPoSupplyProportionHEntity_HI> supplyHList = new ArrayList<SrmPoSupplyProportionHEntity_HI>();
        SrmPoSupplyProportionHEntity_HI supplyHEntity = null;
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            supplyHEntity = new SrmPoSupplyProportionHEntity_HI();
            supplyHEntity.setSupplyType(getSypplyTypeCode(obj.getString("supplyType")));
            supplyHEntity.setItemId(contaisItemCode(obj.getString("itemCode")));
            supplyHEntity.setItemCode(obj.getString("itemCode"));
            supplyHEntity.setItemDescription(obj.getString("itemName"));
            supplyHEntity.setInstId(getInstId(obj.getString("instName")));
            supplyHEntity.setSmallCategoryCode(obj.getString("categoryCode"));
            supplyHEntity.setSmallCategoryName(obj.getString("categoryName"));
            supplyHEntity.setStartDateActive(obj.getDate("startDateActive"));
            supplyHEntity.setEndDateActive(obj.getDate("endDateActive"));
            supplyHEntity.setSupplierId(contaisSupplier(obj.getString("supplierNumber")));
            supplyHEntity.setSupplierName(obj.getString("supplierName"));
            supplyHEntity.setSupplierNumber(obj.getString("supplierNumber"));
            supplyHEntity.setProportion(obj.getBigDecimal("proportion"));
            supplyHEntity.setStatus("Y");
            supplyHEntity.setCreatedBy(userId);
            supplyHEntity.setCreationDate(new Date());
            supplyHEntity.setLastUpdateDate(new Date());
            supplyHEntity.setLastUpdatedBy(userId);
            supplyHEntity.setOperatorUserId(userId);
            supplyHList.add(supplyHEntity);
        }
        //去重复
        List<SrmPoSupplyProportionHEntity_HI> supplyHList2 = new ArrayList<SrmPoSupplyProportionHEntity_HI>();
        for (SrmPoSupplyProportionHEntity_HI supplyHEntity2 : supplyHList) {
            if (!supplyHList2.contains(supplyHEntity2)) {
                supplyHList2.add(supplyHEntity2);
            }
        }
        Calendar cal = Calendar.getInstance();
        int count = 0;
        for (SrmPoSupplyProportionHEntity_HI supplyHEntity3 : supplyHList2) {
            //分类
            if ("CLASSIFY".equals(supplyHEntity3.getSupplyType())) {
                List<SrmPoSupplyProportionHEntity_HI> supplyEntity = srmPoSupplyProportionHDAO_HI.findByProperty("smallCategoryCode", supplyHEntity3.getSmallCategoryCode());
                for (SrmPoSupplyProportionHEntity_HI resultEntity : supplyEntity) {
                    if (resultEntity.getEndDateActive() == null && "Y".equals(resultEntity.getStatus())) {
                        cal.setTime(supplyHEntity3.getStartDateActive());
                        cal.add(Calendar.DATE, -1);
                        resultEntity.setEndDateActive(cal.getTime());
                        resultEntity.setLastUpdateDate(new Date());
                        resultEntity.setLastUpdatedBy(userId);
                        //失效时间改成昨天
                        resultEntity.setOperatorUserId(userId);
                        srmPoSupplyProportionHDAO_HI.update(resultEntity);
                        break;
                    }
                }
            } else if ("SUPPLY".equals(supplyHEntity3.getSupplyType()) && "Y".equals(supplyHEntity3.getStatus())) {
                //物料
                List<SrmPoSupplyProportionHEntity_HI> supplyEntity = srmPoSupplyProportionHDAO_HI.findByProperty("itemCode", supplyHEntity3.getItemCode());
                for (SrmPoSupplyProportionHEntity_HI resultSupply : supplyEntity) {
                    resultSupply.setOperatorUserId(userId);
                    if (resultSupply.getInstId() == null && resultSupply.getEndDateActive() == null && "Y".equals(resultSupply.getStatus())) {
                        cal.setTime(supplyHEntity3.getStartDateActive());
                        cal.add(Calendar.DATE, -1);
                        resultSupply.setEndDateActive(cal.getTime());
                        resultSupply.setLastUpdateDate(new Date());
                        resultSupply.setLastUpdatedBy(userId);
                        //失效时间改成昨天
                        srmPoSupplyProportionHDAO_HI.update(resultSupply);
                        break;
                        //当分厂id为null的时候 用==
                    } else if (resultSupply.getInstId() != null) {
                        if (resultSupply.getInstId().equals(supplyHEntity3.getInstId()) && resultSupply.getEndDateActive() == null && "Y".equals(resultSupply.getStatus())) {
                            cal.setTime(supplyHEntity3.getStartDateActive());
                            cal.add(Calendar.DATE, -1);
                            resultSupply.setEndDateActive(cal.getTime());
                            resultSupply.setLastUpdateDate(new Date());
                            resultSupply.setLastUpdatedBy(userId);
                            //失效时间改成昨天
                            srmPoSupplyProportionHDAO_HI.update(resultSupply);
                            break;
                        }
                    }
                }
            }
            //先加头
            srmPoSupplyProportionHDAO_HI.save(supplyHEntity3);
            //再加行
            SrmPoSupplyProportionLEntity_HI supplyLentity = null;
            for (SrmPoSupplyProportionHEntity_HI supplyHEntity4 : supplyHList) {
                //1==3
                if (supplyHEntity3.equals(supplyHEntity4)) {
                    supplyLentity = new SrmPoSupplyProportionLEntity_HI();
                    supplyLentity.setSupplyId(supplyHEntity3.getSupplyId());
                    supplyLentity.setSupplierId(supplyHEntity4.getSupplierId());
                    supplyLentity.setSupplierNmuber(supplyHEntity4.getSupplierNumber());
                    supplyLentity.setSupplierName(supplyHEntity4.getSupplierName());
                    supplyLentity.setProportion(supplyHEntity4.getProportion());
                    supplyLentity.setEnableFlag("Y");
                    supplyLentity.setCreationDate(new Date());
                    supplyLentity.setCreatedBy(userId);
                    supplyLentity.setLastUpdatedBy(userId);
                    supplyLentity.setLastUpdateDate(new Date());
                    supplyLentity.setOperatorUserId(userId);
                    srmPoSupplyProportionLDAO_HI.save(supplyLentity);
                }
            }
        }
        JSONObject resultObj = new JSONObject();
        resultObj.put("msg", "导入成功行数为:" + supplyHList2.size() + "行!");
        resultObj.put("status", "S");
        return resultObj;
    }

    /**
     * Description：导入数据校验
     * @param jsonArray
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private JSONArray cehckArray(JSONArray jsonArray) {
        if (null == jsonArray || jsonArray.isEmpty()){
            return null;
        }
        JSONArray error = new JSONArray();
        JSONObject e = null;
        //只验证编码
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String sypplyType = getSypplyTypeCode(obj.getString("supplyType"));//分类 物料
            Integer instId = getInstId(obj.getString("instName"));
            if (null == sypplyType) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "比例分类不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            } else if ("CLASSIFY".equals(sypplyType)) {//分类
                String categoryCode = obj.getString("categoryCode");
                if (contaisSmallCateGoryCode(categoryCode) <= 0) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "物料类别不存在");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
                BigDecimal sumProportion = new BigDecimal(0);
                for (int k = 0, l = jsonArray.size(); k < l; k++) {
                    JSONObject obj2 = jsonArray.getJSONObject(k);
                    if (categoryCode.equals(obj2.getString("categoryCode"))) {
                        try {
                            sumProportion = sumProportion.add(obj2.getBigDecimal("proportion"));
                        } catch (Exception e1) {
                            e = new JSONObject();
                            e.put("ERR_MESSAGE", "比例错误");
                            e.put("ROW_NUM", k + 1);
                            error.add(e);
                            break;
                        }
                    }
                }
                if ((sumProportion.compareTo(new BigDecimal(100)) != 0)) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "物料或类别比例汇总不为100%");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
                List<SrmPoSupplyProportionHEntity_HI> supplyEntity = srmPoSupplyProportionHDAO_HI.findByProperty("smallCategoryCode", categoryCode);

                for (SrmPoSupplyProportionHEntity_HI resultEntity : supplyEntity) {
                    if (resultEntity.getEndDateActive() != null && "Y".equals(resultEntity.getStatus())) {
                        if (obj.getDate("startDateActive").compareTo(resultEntity.getStartDateActive()) <= 0 || obj.getDate("startDateActive").compareTo(resultEntity.getEndDateActive()) <= 0) {
                            e = new JSONObject();
                            e.put("ERR_MESSAGE", "新供货比例的有效期比旧记录早");
                            e.put("ROW_NUM", i + 1);
                            error.add(e);
                            break;
                        }
                    }
                }
            } else if ("SUPPLY".equals(sypplyType)) {//物料
                String itemCode = obj.getString("itemCode");
                List<SrmPoSupplyProportionHEntity_HI> supplyEntity = srmPoSupplyProportionHDAO_HI.findByProperty("itemCode", itemCode);
                if (contaisItemCode(itemCode) == null) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "物料不存在");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                } else if (obj.getString("instName") != null &&
                        !"".equals(obj.getString("instName")) && null == instId) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "分厂不存在");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                } else if (instId != null) {
                    if (!isValidProportion(obj.getString("proportion"))) {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "比例输入错误");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    } else if ((obj.getInteger("proportion") < 100 || obj.getInteger("proportion") > 100)) {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "物料-分厂比例不为100%");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                    for (SrmPoSupplyProportionHEntity_HI resultSupply : supplyEntity) {
                        if (instId != null && resultSupply.getInstId() != null && resultSupply.getEndDateActive() != null) {
                            if (instId.equals(resultSupply.getInstId()) && "Y".equals(resultSupply.getStatus())) {
                                if (obj.getDate("startDateActive").compareTo(resultSupply.getStartDateActive()) <= 0 || obj.getDate("startDateActive").compareTo(resultSupply.getEndDateActive()) <= 0) {
                                    e = new JSONObject();
                                    e.put("ERR_MESSAGE", "新供货比例的有效期比旧记录早");
                                    e.put("ROW_NUM", i + 1);
                                    error.add(e);
                                    break;
                                }
                            }
                        }

                    }
                } else if (instId == null) {
                    BigDecimal sumProportion = new BigDecimal(0);
                    for (int k = 0, l = jsonArray.size(); k < l; k++) {
                        JSONObject obj2 = jsonArray.getJSONObject(k);
                        if (itemCode.equals(obj2.getString("itemCode"))) {
                            try {
                                sumProportion = sumProportion.add(obj2.getBigDecimal("proportion"));
                            } catch (Exception e1) {
                                e = new JSONObject();
                                e.put("ERR_MESSAGE", "比例错误");
                                e.put("ROW_NUM", k + 1);
                                error.add(e);
                                break;
                            }
                        }
                    }
                    if ((sumProportion.compareTo(new BigDecimal(100)) != 0)) {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "物料或类别比例汇总不为100%");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                    for (SrmPoSupplyProportionHEntity_HI resultSupply : supplyEntity) {
                        if (instId == null && resultSupply.getInstId() == null && "Y".equals(resultSupply.getStatus())) {
                            if (resultSupply.getEndDateActive() != null) {
                                if (obj.getDate("startDateActive").compareTo(resultSupply.getStartDateActive()) <= 0 || obj.getDate("startDateActive").compareTo(resultSupply.getEndDateActive()) <= 0) {
                                    e = new JSONObject();
                                    e.put("ERR_MESSAGE", "新供货比例的有效期比旧记录早");
                                    e.put("ROW_NUM", i + 1);
                                    error.add(e);
                                    break;
                                }
                            } else {
                                if (obj.getDate("startDateActive").compareTo(resultSupply.getStartDateActive()) <= 0) {
                                    e = new JSONObject();
                                    e.put("ERR_MESSAGE", "新供货比例的有效期比旧记录早");
                                    e.put("ROW_NUM", i + 1);
                                    error.add(e);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            Integer supplierId = contaisSupplier(obj.getString("supplierNumber"));
            if (supplierId == null || supplierId == -1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "供应商不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
            } else if (null == obj.get("startDateActive") || "".equals(obj.getString("startDateActive").trim()) || !isValidDate(obj.getString("startDateActive"))) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "日期从数据不一致或为空");
                e.put("ROW_NUM", i + 1);
                error.add(e);
            } else if (null != obj.get("endDateActive") && !"".equals(obj.getString("endDateActive").trim()) && !isValidDate(obj.getString("endDateActive"))) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "日期至错误");
                e.put("ROW_NUM", i + 1);
                error.add(e);
            }
            int count1 = 0,
                    count2 = 0,
                    count3 = 0,
                    count4 = 0,
                    count5 = 0,
                    count6 = 0;

            for (int k = 0, l = jsonArray.size(); k < j; k++) {
                JSONObject obj2 = jsonArray.getJSONObject(k);
                if (obj.getString("itemCode") != null && !"".equals(obj.getString("itemCode").trim())
                        && obj2.getString("itemCode") != null && !"".equals(obj2.getString("itemCode").trim())
                        && obj.getString("instName") == null && obj2.getString("instName") == null) {
                    if (obj.getString("itemCode").equals(obj2.getString("itemCode")) && obj.getString("supplierNumber").equals(obj2.getString("supplierNumber"))) {
                        count1++;
                    }
                } else if (obj.getString("itemCode") != null && !"".equals(obj.getString("itemCode").trim())
                        && obj2.getString("itemCode") != null && !"".equals(obj2.getString("itemCode").trim())
                        && obj.getString("instName") != null && obj2.getString("instName") != null) {
                    if (obj.getString("itemCode").equals(obj2.getString("itemCode")) && obj.getString("instName").equals(obj2.getString("instName")) && obj.getString("supplierNumber").equals(obj2.getString("supplierNumber"))) {
                        count2++;
                    }
                } else if (obj.getString("categoryCode") != null && !"".equals(obj.getString("categoryCode").trim()) && obj2.getString("categoryCode") != null && !"".equals(obj2.getString("categoryCode").trim())) {
                    if (obj.getString("categoryCode").equals(obj2.getString("categoryCode")) && obj.getString("supplierNumber").equals(obj2.getString("supplierNumber"))) {
                        count3++;
                    }
                }
                //判断时间是否重复
                if (obj.get("itemCode") != null && !"".equals(obj.getString("itemCode").trim())
                        && obj2.get("itemCode") != null && !"".equals(obj2.getString("itemCode").trim())
                        && obj.getString("instName") == null && obj2.getString("instName") == null) {
                    if (obj.getString("itemCode").equals(obj2.getString("itemCode")) && obj.getDate("startDateActive").compareTo(obj2.getDate("startDateActive")) != 0) {

                        count4++;
                    }
                } else if (obj.get("itemCode") != null && !"".equals(obj.getString("itemCode").trim()) &&
                        obj2.get("itemCode") != null && !"".equals(obj2.getString("itemCode").trim())
                        && obj.getString("instName") != null && obj2.getString("instName") != null) {
                    if (obj.getString("itemCode").equals(obj2.getString("itemCode")) && obj.getDate("startDateActive").compareTo(obj2.getDate("startDateActive")) != 0
                            && obj.getString("instName").equals(obj2.getString("instName"))) {
                        count5++;
                    }
                } else if (obj.getString("categoryCode") != null && !"".equals(obj.getString("categoryCode").trim()) && obj2.getString("categoryCode") != null && !"".equals(obj2.getString("categoryCode").trim())) {
                    if (obj.getString("categoryCode").equals(obj2.getString("categoryCode")) && obj.getDate("startDateActive").compareTo(obj2.getDate("startDateActive")) != 0) {
                        count6++;
                    }
                }
            }
            if (count1 > 1 || count2 > 1 || count3 > 1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "供应商编码重复");
                e.put("ROW_NUM", i + 1);
                error.add(e);
            }
            if (count4 > 0 || count5 > 0 || count6 > 0) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "日期从数据不一致或为空");
                e.put("ROW_NUM", i + 1);
                error.add(e);
            }
        }
        return error;
    }
    /**
     * Description：校验比例
     * @param s
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private boolean isValidProportion(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Description：校验时间格式
     * @param s
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private boolean isValidDate(String s) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.parse(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Description：查询分类
     * @param meaning
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private String getSypplyTypeCode(String meaning) {
        if (null == meaning || "".equals(meaning.trim())) return null;
        StringBuffer sqlBuff = new StringBuffer(SrmPoSupplyProportionHEntity_HI_RO.QUERY_LOOKUP_BY_TYPE);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("meaning", meaning.trim());
        try {
            return supplyProportionHDAO_HI_RO.findList(sqlBuff, map).get(0).getLookupCode();
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * Description：查询分类 如果存在大于0 否则返回 0 其他返回-1
     * @param code
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private int contaisSmallCateGoryCode(String code) {
        if (null == code || "".equals(code.trim())) return -1;
        StringBuffer sqlBuff = new StringBuffer(SrmPoSupplyProportionHEntity_HI_RO.QUERY_CATEGORIES_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("smallCateGoryCode", code.trim());
        try {
            return supplyProportionHDAO_HI_RO.findList(sqlBuff, map).get(0).getCount();
        } catch (Exception e) {
            return -1;
        }
    }
    /**
     * Description：根据物料code查询 如果存在大于0 否则返回 0 其他返回-1
     * @param code
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private Integer contaisItemCode(String code) {
        if (null == code || "".equals(code.trim())) return -1;
        StringBuffer sqlBuff = new StringBuffer(SrmPoSupplyProportionHEntity_HI_RO.QUERY_ITEM_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemCode", code.trim());
        try {
            return supplyProportionHDAO_HI_RO.findList(sqlBuff, map).get(0).getItemId();
        } catch (Exception e) {
            return -1;
        }
    }
    /**
     * Description：查询供应商是否存在
     * @param supplierNumber
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private Integer contaisSupplier(String supplierNumber) {
        if (null == supplierNumber || "".equals(supplierNumber.trim())) return -1;
        StringBuffer sqlBuff = new StringBuffer(SrmPoSupplyProportionHEntity_HI_RO.QUERY_SUPPLIER_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("supplierNumber", supplierNumber.trim());
        try {
            SrmPoSupplyProportionHEntity_HI_RO entity = supplyProportionHDAO_HI_RO.findList(sqlBuff, map).get(0);
            return entity.getSupplierId();
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return -1;
        }
    }

    /**
     * Description：获取组织ID
     * @param instName 组织名称
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private Integer getInstId(String instName) {
        if (null == instName || "".equals(instName.trim())) return null;
        SaafInstitutionEntity_HI_RO instEntity = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("instName", instName.trim());
            instEntity = Institution_HI_RO.findList(SaafInstitutionEntity_HI_RO.QUERY_INST_ID_BY_INST_CODE, map).get(0);
            return instEntity.getInstId();
        } catch (Exception e) {
            return null;
        }
    }
}
