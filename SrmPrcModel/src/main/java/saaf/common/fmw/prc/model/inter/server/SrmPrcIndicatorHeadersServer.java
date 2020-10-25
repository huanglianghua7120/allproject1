package saaf.common.fmw.prc.model.inter.server;

import java.rmi.ServerException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoApprovedListEntity_HI;
import saaf.common.fmw.prc.model.entities.*;
import saaf.common.fmw.prc.model.entities.readonly.SrmPrcIndicatorHeadersEntity_HI_RO;
import saaf.common.fmw.prc.model.inter.IPrcIndicatorHeaders;
import saaf.common.fmw.utils.SrmUtils;

@Component("srmPrcIndicatorHeadersServer")
public class SrmPrcIndicatorHeadersServer implements IPrcIndicatorHeaders {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPrcIndicatorHeadersServer.class);

    @Autowired
    private BaseViewObject<SrmPrcIndicatorHeadersEntity_HI_RO> srmPrcIndicatorHeadersDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPrcIndicatorHeadersEntity_HI> srmPrcIndicatorHeadersDAO_HI;

    @Autowired
    private ViewObject<SrmPrcMappingHeadersEntity_HI> srmPrcMappingHeadersDAO_HI;

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ViewObject<SrmPrcMappingItemsEntity_HI> srmPrcMappingItemsDAO_HI;

    @Autowired
    private ViewObject<SrmPrcIndicatorSuppliersEntity_HI> srmPrcIndicatorSuppliersDAO_HI;

    @Autowired
    private ViewObject<SrmPoApprovedListEntity_HI> srmPoApprovedListDAO_HI;

    @Autowired
    private ViewObject<SrmPrcMappingSuppliersEntity_HI> srmPrcMappingSuppliersDAO_HI;


    /**
     * 查询材质指标列表
     */
    @Override
    public Pagination<SrmPrcIndicatorHeadersEntity_HI_RO> findIndicatorHeadersList(
            JSONObject parameters, Integer pageIndex, Integer pageRows)
            throws Exception {
        StringBuffer queryString = new StringBuffer(SrmPrcIndicatorHeadersEntity_HI_RO.QUERY_INDICATOR_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        //查询过滤条件
        SaafToolUtils.parperParam(parameters, "ih.indicator_header_id", "indicatorHeaderId", queryString, map, "=");
        SaafToolUtils.parperParam(parameters, "ih.disp_indicator_number", "dispIndicatorNumber", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "ih.indicator_name", "indicatorName", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "su.user_full_name", "userFullName", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "u.user_full_name", "lastUpdatedName", queryString, map, "like");
        SaafToolUtils.parperParam(parameters, "DATE_FORMAT(ih.creation_date,'%Y-%m-%d') ", "creationDateTo", queryString, map, ">=");
        SaafToolUtils.parperParam(parameters, "DATE_FORMAT(ih.creation_date,'%Y-%m-%d') ", "creationDateFrom", queryString, map, "<=");
        SaafToolUtils.parperParam(parameters, "DATE_FORMAT(ih.end_date_active,'%Y-%m-%d') ", "endDateTo", queryString, map, ">=");
        SaafToolUtils.parperParam(parameters, "DATE_FORMAT(ih.end_date_active,'%Y-%m-%d') ", "endDateFrom", queryString, map, "<=");
        SaafToolUtils.parperParam(parameters, "ih.indicator_status", "indicatorStatus", queryString, map, "=");
        //排序
        queryString.append(" ORDER BY ih.creation_date DESC");
        Pagination<SrmPrcIndicatorHeadersEntity_HI_RO> indicatorList = srmPrcIndicatorHeadersDAO_HI_RO.findPagination(queryString, map, pageIndex, pageRows);
        return indicatorList;
    }

    /**
     * 删除材质指标
     */
    @Override
    public JSONObject deleteIndicatorHeaders(JSONObject params) throws Exception {
        SrmPrcIndicatorHeadersEntity_HI indicatorRow = null;
        indicatorRow = srmPrcIndicatorHeadersDAO_HI.getById(params.getInteger("indicatorHeaderId"));
        if (null != indicatorRow) {
            if ("DRAFT".equals(indicatorRow.getIndicatorStatus())) {
                srmPrcIndicatorHeadersDAO_HI.delete(indicatorRow);
            } else {
                throw new ServerException("非拟定状态的单据不能删除！");
            }
        } else {
            throw new ServerException("删除失败，" + params.getString("indicatorHeaderId") + "不存在");
        }
        return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
    }

    /**
     * 保存／确认材质指标
     */
    @Override
    public JSONObject saveIndicatorHeaders(JSONObject params) throws Exception {
        SrmPrcIndicatorHeadersEntity_HI indicatorRow = null;
        Integer operatorUserId = params.getInteger("varUserId");
        if ("ACT".equals(params.getString("action"))) {  //确认生效，检验存不存在跟该指标名称相同且是生效状态的记录
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("indicatorName", params.getString("indicatorName").trim());
            map.put("indicatorStatus", "ACT");
            List indicatorList = srmPrcIndicatorHeadersDAO_HI.findByProperty(map);
            if (indicatorList.size() > 0) {
                throw new ServerException("该指标名称已存在生效的记录，如果确定要生效该记录，请先失效之前的记录！");
            }
        }
        // 保存材质指标判断
        if (null == params.getInteger("indicatorHeaderId")) {
            indicatorRow = new SrmPrcIndicatorHeadersEntity_HI();
            String dispIndicatorNumber = saafSequencesUtil.getDocSequences("SRM_PRC_INDICATOR_HEADERS", "HOMA-", 5);
            indicatorRow.setDispIndicatorNumber(dispIndicatorNumber);

            //根据指标名称查询系统是否已存在该指标名称，如果存在，获取内码
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("indicatorName", params.getString("indicatorName").trim());
            List<SrmPrcIndicatorHeadersEntity_HI> indicatorList1 = srmPrcIndicatorHeadersDAO_HI.findByProperty(map1);
            if (indicatorList1.size() > 0) { //存在
                indicatorRow.setIndicatorNumber(indicatorList1.get(0).getIndicatorNumber());
            } else {
                indicatorRow.setIndicatorNumber(dispIndicatorNumber);
            }
        } else {// 判断存在就是修改
            indicatorRow = srmPrcIndicatorHeadersDAO_HI.getById(params.getInteger("indicatorHeaderId"));
        }
        indicatorRow.setIndicatorName(params.getString("indicatorName"));
        indicatorRow.setDescription(params.getString("description"));
        if ("ACT".equals(params.getString("action"))) {
            if ("INACT".equals(indicatorRow.getIndicatorStatus()) || "ACT".equals(indicatorRow.getIndicatorStatus())) {
                throw new ServerException("已生效或失效的单据不能再次确认！");
            }
            indicatorRow.setStartDateActive(new Date()); //设置生效时间
        }
        indicatorRow.setIndicatorStatus(params.getString("action"));
        indicatorRow.setAdditionalInformation(params.getString("additionalInformation"));
        indicatorRow.setUnitOfMeasure(params.getString("unitOfMeasure"));
        indicatorRow.setComputationalFormula(params.getString("computationalFormula"));
        indicatorRow.setOperatorUserId(operatorUserId);
        srmPrcIndicatorHeadersDAO_HI.saveOrUpdate(indicatorRow);
        JSONObject result = SToolUtils.convertResultJSONObj("S", "保存材质指标成功", 1, indicatorRow.getIndicatorHeaderId());
        return result;
    }

    /**
     * 材质指标失效
     */
    @Override
    public JSONObject updateIndicatorInvalid(JSONObject params)
            throws Exception {
        Integer indicatorHeaderId = params.getInteger("indicatorHeaderId");
        Integer operatorUserId = params.getInteger("varUserId");
        if (indicatorHeaderId == null) {
            throw new ServerException("系统中找不到该单据！");
        }
        SrmPrcIndicatorHeadersEntity_HI indicatorRow = (SrmPrcIndicatorHeadersEntity_HI) srmPrcIndicatorHeadersDAO_HI
                .getById(indicatorHeaderId);
        if (null == indicatorRow) {
            throw new ServerException("系统中找不到该单据！");
        }
        indicatorRow.setIndicatorStatus("INACT");
        indicatorRow.setEndDateActive(new Date());
        indicatorRow.setOperatorUserId(operatorUserId);
        srmPrcIndicatorHeadersDAO_HI.update(indicatorRow);
        return SToolUtils.convertResultJSONObj("S", "失效成功", 1, null);
    }

    /**
     * 查询材质指标相关供应商列表
     */
    @Override
    public Pagination<SrmPrcIndicatorHeadersEntity_HI_RO> findIndicatorSupplierList(
            JSONObject parameters, Integer pageIndex, Integer pageRows)
            throws Exception {
        StringBuffer queryString = new StringBuffer(SrmPrcIndicatorHeadersEntity_HI_RO.QUERY_INDICATOR_SUPPLIER_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询过滤条件
        SaafToolUtils.parperParam(parameters, "spis.indicator_header_id", "indicatorHeaderId", queryString, map, "=");
        Pagination<SrmPrcIndicatorHeadersEntity_HI_RO> indicatorList = srmPrcIndicatorHeadersDAO_HI_RO
                .findPagination(queryString, map, pageIndex, pageRows);
        return indicatorList;
    }

    /**
     * 根据指标查询关联的物料和对应的长宽高
     */
    @Override
    public Pagination<SrmPrcIndicatorHeadersEntity_HI_RO> findIndicatorToItem(
            JSONObject parameters, Integer pageIndex, Integer pageRows)
            throws Exception {
        List<SrmPrcMappingHeadersEntity_HI> list = srmPrcMappingHeadersDAO_HI.findByProperty("indicatorNumber", parameters.getString("indicatorNumber"));
        if (list.size() > 0) {
            Integer mappingHeaderId = list.get(0).getMappingHeaderId();
            parameters.put("mappingHeaderId", mappingHeaderId);
            StringBuffer queryString = new StringBuffer(SrmPrcIndicatorHeadersEntity_HI_RO.QUERY_INDICATOR_TO_ITEM_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
            SaafToolUtils.parperParam(parameters, "spmi.mapping_header_id", "mappingHeaderId", queryString, map, "=");
            Pagination<SrmPrcIndicatorHeadersEntity_HI_RO> indicatorList = srmPrcIndicatorHeadersDAO_HI_RO
                    .findPagination(queryString, map, pageIndex, pageRows);
            return indicatorList;
        } else {
            return null;
        }
    }

    /**
     * 保存调价
     */
    @Override
    public JSONObject saveAdjustPrice(JSONObject params) throws Exception {
        Integer operatorUserId = params.getInteger("varUserId");
        for (int t = 0; t < params.getJSONArray("lineData").size(); t++) {
            JSONObject obj = params.getJSONArray("lineData").getJSONObject(t);
            //核价指标物料表
            SrmPrcMappingItemsEntity_HI mappingItems = srmPrcMappingItemsDAO_HI.getById(obj.getInteger("mappingItemId"));
            if (null != mappingItems) {
                //校验是否存在货源
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("supplierId", obj.getInteger("supplierId"));
                map.put("itemId", mappingItems.getItemId());
                map.put("enabledFlag", "Y");
                List<SrmPoApprovedListEntity_HI> approvedList = srmPoApprovedListDAO_HI.findByProperty(map);
                if (approvedList.size() > 0) {
                    //核价指标供应商表
                    SrmPrcIndicatorSuppliersEntity_HI indicatorSuppliers = srmPrcIndicatorSuppliersDAO_HI.getById(obj.getInteger("indicatorSupplierId"));
                    if (null != indicatorSuppliers) {
                        indicatorSuppliers.setOriginalPrice(obj.getBigDecimal("finallyPrice"));
                        indicatorSuppliers.setOperatorUserId(operatorUserId);
                        srmPrcIndicatorSuppliersDAO_HI.update(indicatorSuppliers);
                    }
//                     //指标关联供应商表
                    Map<String, Object> map1 = new HashMap<String, Object>();
                    map1.put("supplierId", obj.getInteger("supplierId"));
                    map1.put("mappingItemId", mappingItems.getMappingItemId());
                    map1.put("mappingHeaderId", obj.getInteger("mappingHeaderId"));
                    List<SrmPrcMappingSuppliersEntity_HI> supplierList = srmPrcMappingSuppliersDAO_HI.findByProperty(map1);
                    if (supplierList.size() > 0) {
                        SrmPrcMappingSuppliersEntity_HI mappingSupplier = supplierList.get(0);
                        mappingSupplier.setOriginalPrice(obj.getBigDecimal("total"));
                        mappingSupplier.setOperatorUserId(operatorUserId);
                        srmPrcMappingSuppliersDAO_HI.update(mappingSupplier);
                    }
                }
            }
        }
        JSONObject result = SToolUtils.convertResultJSONObj("S", "保存价格成功", 1, null);
        return result;
    }

    /**
     * 查询头信息
     */
    @Override
    public List<SrmPrcIndicatorHeadersEntity_HI_RO> findExpHeader(String indicatorNumber,
                                                                  String indicatorStatus, String creationDateS,
                                                                  String creationDateE) {
        StringBuffer queryString = new StringBuffer(SrmPrcIndicatorHeadersEntity_HI_RO.EXP_INDICATOR_HEADER_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询过滤条件

        if (!SrmUtils.isNvl(indicatorNumber)) {
            queryString.append(" AND t.INDICATOR_NUMBER =:indicatorNumber");
            map.put("indicatorNumber", indicatorNumber);
        }
        if (!SrmUtils.isNvl(indicatorStatus)) {
            queryString.append(" AND t.INDICATOR_STATUS =:indicatorStatus");
            map.put("indicatorStatus", indicatorStatus);
        }
        if (!SrmUtils.isNvl(creationDateS)) {
            queryString.append(" AND DATE_FORMAT(t.creation_date, '%Y-%m-%d') >=:creationDateS");
            map.put("creationDateS", creationDateS);
        }
        if (!SrmUtils.isNvl(creationDateE)) {
            queryString.append(" AND DATE_FORMAT(t.creation_date, '%Y-%m-%d') <=:creationDateS");
            map.put("creationDateS", creationDateS);
        }
        List<SrmPrcIndicatorHeadersEntity_HI_RO> list = srmPrcIndicatorHeadersDAO_HI_RO.findList(queryString, map);
        return list;
    }

    @Override
    public List<SrmPrcIndicatorHeadersEntity_HI_RO> findSupplier(String indicatorNumber,
                                                                 String indicatorStatus, String creationDateS,
                                                                 String creationDateE) {
        StringBuffer queryString = new StringBuffer(SrmPrcIndicatorHeadersEntity_HI_RO.EXP_INDICATOR_SUPPLIER_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        if (!SrmUtils.isNvl(indicatorNumber)) {
            queryString.append(" AND t.INDICATOR_NUMBER =:indicatorNumber");
            map.put("indicatorNumber", indicatorNumber);
        }
        if (!SrmUtils.isNvl(indicatorStatus)) {
            queryString.append(" AND t.INDICATOR_STATUS =:indicatorStatus");
            map.put("indicatorStatus", indicatorStatus);
        }
        if (!SrmUtils.isNvl(creationDateS)) {
            queryString.append(" AND DATE_FORMAT(t.creation_date, '%Y-%m-%d') >=:creationDateS");
            map.put("creationDateS", creationDateS);
        }
        if (!SrmUtils.isNvl(creationDateE)) {
            queryString.append(" AND DATE_FORMAT(t.creation_date, '%Y-%m-%d') <=:creationDateS");
            map.put("creationDateS", creationDateS);
        }
        List<SrmPrcIndicatorHeadersEntity_HI_RO> list = srmPrcIndicatorHeadersDAO_HI_RO.findList(queryString,
                map);
        return list;
    }

    @Override
    public List<SrmPrcIndicatorHeadersEntity_HI_RO> findSupplierPrice(Integer indicatorHeaderId) {
        StringBuffer queryString = new StringBuffer(SrmPrcIndicatorHeadersEntity_HI_RO.EXP_INDICATOR_SUPP_PRICE_SQL);
        queryString.append(" AND t.INDICATOR_HEADER_ID = " + indicatorHeaderId);
        List<SrmPrcIndicatorHeadersEntity_HI_RO> list = srmPrcIndicatorHeadersDAO_HI_RO.findList(queryString);
        return list;
    }
}
