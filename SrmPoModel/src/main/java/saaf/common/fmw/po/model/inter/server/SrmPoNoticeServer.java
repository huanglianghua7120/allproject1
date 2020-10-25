package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.sie.common.utils.StringUtil;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoHeadersEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoLinesEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoNoticeEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoNoticeLineEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoNoticeEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoNoticeLineEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoNotice;
import saaf.common.fmw.utils.SrmUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import java.math.BigDecimal;
import java.util.*;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

/**
 * Project Name：SrmPoNoticeServer
 * Company Name：SIE
 * Program Name：
 * Description：送货通知
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("SrmPoNoticeServer")
public class SrmPoNoticeServer implements ISrmPoNotice {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoNoticeServer.class);

    @Autowired
    private BaseViewObject<SrmPoNoticeEntity_HI_RO> srmPoNoticeDao_HI_RO;

    @Autowired
    private ViewObject<SrmPoNoticeEntity_HI> srmPoNoticeDAO_HI;

    @Autowired
    private BaseViewObject<SrmPoNoticeLineEntity_HI_RO> srmPoNoticeLineDao_HI_RO;

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ViewObject<SrmPoNoticeLineEntity_HI> lineDAO_HI;

    @Autowired
    private ViewObject<SrmPoLinesEntity_HI> poLinesDAO_HI;

    @Autowired
    private ViewObject<SrmPoHeadersEntity_HI> poHeadersDAO_HI;

    @Autowired
    private ViewObject<SaafEmployeesEntity_HI> employeesDAO_HI;

    @Context
    protected HttpServletRequest request;

    @Autowired
    private ViewObject<SrmIntfLogsEntity_HI> srmIntfLogsDAO_HI;//日志

    /**
     * Description：送货通知
     * @param parameters 查询条件参数
     * @param pageIndex 页码
     * @param pagerows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoNoticeEntity_HI_RO> findNoticeLists(JSONObject parameters, Integer pageIndex, Integer pagerows) throws Exception {
        LOGGER.info("findNoticeList--->>" + parameters);
        StringBuffer whereSql = new StringBuffer();
        StringBuffer countSql = new StringBuffer(SrmPoNoticeEntity_HI_RO.QUERY_SRM_PO_NOTICE_COUNT_SQL);
        StringBuffer sqlBuffer = new StringBuffer(SrmPoNoticeEntity_HI_RO.QUERY_SRM_PO_NOTICE_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(parameters, " spn.po_notice_code", "poNoticeCode", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " spn.org_id", "orgId", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " spn.bill_to_organization_id", "billToOrganizationId", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " spn.comments", "comments", whereSql, map, "like");
        SaafToolUtils.parperParam(parameters, " nl.line_status", "lineStatus", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " nl.line_comments", "lineComments", whereSql, map, "like");
        SaafToolUtils.parperParam(parameters, " nl.line_status", "lineStatus", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " nl.feedback_status", "feedbackStatus", whereSql, map, "=");
        // 物料类别
        SaafToolUtils.parperParam(parameters, " sbc.CATEGORY_CODE", "categoryCode", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " sbc.category_name", "categoryName", whereSql, map, "like");
        // 物料编码
        SaafToolUtils.parperParam(parameters, " sbi.item_code", "itemCode", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " sbi.item_name", "itemName", whereSql, map, "like");
        // 供应商
        SaafToolUtils.parperParam(parameters, " spn.supplier_id", "supplierId", whereSql, map, "=");
        // 采购员
        SaafToolUtils.parperParam(parameters, " spn.buyer_id", "employeeId", whereSql, map, "=");
        // 组织编码
        SaafToolUtils.parperParam(parameters, " si.inst_id", "instId", whereSql, map, "=");
        // 送货通知状态
        SaafToolUtils.parperParam(parameters, " spn.po_notice_status", "status", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " spn.SPECIAL_USE_NUM", "specialUseNum", whereSql, map, "like");
        SaafToolUtils.parperParam(parameters, " spsi.supplier_name", "supplierName", whereSql, map, "like");

        String startIssuedDate = parameters.getString("startIssuedDate");
        String endIssuedDate = parameters.getString("endIssuedDate");
        String startDeliveryDate = parameters.getString("startNoticeDeliveryDate");
        String endDeliveryDate = parameters.getString("endNoticeDeliveryDate");

        if (StringUtil.isNotEmptyStr(startIssuedDate) && StringUtil.isNotEmptyStr(endIssuedDate)) {
            whereSql.append(" and spn.issued_date between :startIssuedDate and :endIssuedDate");
            map.put("startIssuedDate", startIssuedDate);
            map.put("endIssuedDate", endIssuedDate);
        } else if (StringUtil.isNotEmptyStr(startIssuedDate) && StringUtil.isEmpty(endIssuedDate)) {
            whereSql.append(" and spn.issued_date >= :startIssuedDate");
            map.put("startIssuedDate", startIssuedDate);
        } else if (StringUtil.isNotEmptyStr(endIssuedDate) && StringUtil.isEmpty(startIssuedDate)) {
            whereSql.append(" and spn.issued_date <= :endIssuedDate");
            map.put("endIssuedDate", endIssuedDate);
        }
        if (StringUtil.isNotEmptyStr(startDeliveryDate) && StringUtil.isNotEmptyStr(endDeliveryDate)) {
            whereSql.append(" and nl.notice_delivery_date between :startDeliveryDate and :endDeliveryDate");
            map.put("startDeliveryDate", startDeliveryDate);
            map.put("endDeliveryDate", endDeliveryDate);
        } else if (StringUtil.isNotEmptyStr(startDeliveryDate) && StringUtil.isEmpty(endDeliveryDate)) {
            whereSql.append(" and nl.notice_delivery_date >= :startDeliveryDate");
            map.put("startDeliveryDate", startDeliveryDate);
        } else if (StringUtil.isNotEmptyStr(endDeliveryDate) && StringUtil.isEmpty(startDeliveryDate)) {
            whereSql.append(" and nl.notice_delivery_date <= :endDeliveryDate");
            map.put("endDeliveryDate", endDeliveryDate);
        }
        sqlBuffer.append(whereSql);
        countSql.append(whereSql);
        sqlBuffer.append(" ORDER BY spn.CREATION_DATE DESC");
        Pagination<SrmPoNoticeEntity_HI_RO> noticeList = srmPoNoticeDao_HI_RO.findPagination(sqlBuffer, countSql, map, pageIndex, pagerows);
        return noticeList;
    }

    /**
     * Description：查询送货通知
     * @param parameters 查询条件参数
     * @param pageIndex 页码
     * @param pagerows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoNoticeEntity_HI_RO> findNoticeList(JSONObject parameters, Integer pageIndex, Integer pagerows) throws Exception {
        LOGGER.info("findNoticeList--->>" + parameters);
        StringBuffer whereSql = new StringBuffer();
        StringBuffer countSql = new StringBuffer(SrmPoNoticeEntity_HI_RO.QUERY_NOTICE_COUNT_SQL);
        StringBuffer sqlBuffer = new StringBuffer(SrmPoNoticeEntity_HI_RO.QUERY_NOTICE_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        Integer supplierId = parameters.getInteger("varSupplierId");
        if (supplierId != null) {
            whereSql.append(" and spn.supplier_id = :var_supplier_id  ");
            map.put("var_supplier_id", supplierId);
        }
        // 物料类别
        SaafToolUtils.parperParam(parameters, " sbc.CATEGORY_CODE", "categoryCode", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " sbc.category_name", "categoryName", whereSql, map, "like");
        // 物料编码
        SaafToolUtils.parperParam(parameters, " sbi.item_code", "itemCode", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " sbi.item_name", "itemName", whereSql, map, "like");
        // 供应商
        SaafToolUtils.parperParam(parameters, " spn.supplier_id", "supplierId", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " spn.delivery_site_id", "deliverySiteId", whereSql, map, "=");

        // 采购员
        SaafToolUtils.parperParam(parameters, " se.employee_id", "employeeId", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " se.employee_name", "employeeName", whereSql, map, "=");

        // 组织编码
        SaafToolUtils.parperParam(parameters, " si.inst_id", "instId", whereSql, map, "=");
        // 送货通知状态
        SaafToolUtils.parperParam(parameters, " spn.status", "status", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " spsi.supplier_number", "supplierNumber", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " spn.SPECIAL_USE_NUM", "specialUseNum", whereSql, map, "like");
        SaafToolUtils.parperParam(parameters, " spsi.supplier_name", "supplierName", whereSql, map, "like");
        SaafToolUtils.parperParam(parameters, " spn.po_notice_code", "poNoticeCode", whereSql, map, "=");
        String startDate = null;
        String endDate = null;
        if (parameters.getString("startDate") != null && !"".equals(parameters.getString("startDate").trim())) {
            startDate = parameters.getString("startDate");
        }
        if (parameters.getString("endDate") != null && !"".equals(parameters.getString("endDate").trim())) {
            endDate = parameters.getString("endDate");
        }
        if (startDate != null && endDate != null) {
            whereSql.append(" AND spn.demand_date between :startDate and :endDate");
            map.put("startDate", startDate);
            map.put("endDate", endDate);
        } else if (startDate != null && endDate == null) {
            whereSql.append(" AND spn.demand_date >= :startDate");
            map.put("startDate", startDate);
        } else if (endDate != null && startDate == null) {
            whereSql.append(" AND spn.demand_date <= :endDate");
            map.put("endDate", endDate);
        }

        if (parameters.getBoolean("numFlag") != null && parameters.getBoolean("numFlag") == true) {
            whereSql.append(" AND spn.quantity > spn.delivery_qty");
        }
        sqlBuffer.append(whereSql);
        countSql.append(whereSql);
        sqlBuffer.append(" ORDER BY spn.CREATION_DATE DESC");
        Pagination<SrmPoNoticeEntity_HI_RO> noticeList = srmPoNoticeDao_HI_RO.findPagination(sqlBuffer, countSql, map, pageIndex, pagerows);
        return noticeList;
    }
    /**
     * Description：查询送货通知总数
     * @param parameters 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public SrmPoNoticeEntity_HI_RO findNoticeListSum(JSONObject parameters) throws Exception {
        StringBuffer whereSql = new StringBuffer(SrmPoNoticeEntity_HI_RO.QUERY_NOTICE_SUM_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        Integer supplierId = parameters.getInteger("varSupplierId");
        if (supplierId != null) {
            whereSql.append(" AND spn.supplier_id = :var_supplier_id  ");
            map.put("var_supplier_id", supplierId);
        }
        // 物料类别
        SaafToolUtils.parperParam(parameters, " sbc.CATEGORY_CODE", "categoryCode", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " sbc.category_name", "categoryName", whereSql, map, "like");
        // 物料编码
        SaafToolUtils.parperParam(parameters, " sbi.item_code", "itemCode", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " sbi.item_name", "itemName", whereSql, map, "like");
        // 供应商
        SaafToolUtils.parperParam(parameters, " spn.supplier_id", "supplierId", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " spn.delivery_site_id", "deliverySiteId", whereSql, map, "=");
        // 采购员
        SaafToolUtils.parperParam(parameters, " se.employee_id", "employeeId", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " se.employee_name", "employeeName", whereSql, map, "=");
        // 组织编码
        SaafToolUtils.parperParam(parameters, " si.inst_id", "instId", whereSql, map, "=");
        // 送货通知状态
        SaafToolUtils.parperParam(parameters, " spn.status", "status", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " spsi.supplier_number", "supplierNumber", whereSql, map, "=");
        SaafToolUtils.parperParam(parameters, " spn.SPECIAL_USE_NUM", "specialUseNum", whereSql, map, "like");
        SaafToolUtils.parperParam(parameters, " spsi.supplier_name", "supplierName", whereSql, map, "like");
        SaafToolUtils.parperParam(parameters, " spn.po_notice_code", "poNoticeCode", whereSql, map, "=");
        String startDate = null;
        String endDate = null;
        if (parameters.getString("startDate") != null && !"".equals(parameters.getString("startDate").trim())) {
            startDate = parameters.getString("startDate");
        }
        if (parameters.getString("endDate") != null && !"".equals(parameters.getString("endDate").trim())) {
            endDate = parameters.getString("endDate");
        }
        if (startDate != null && endDate != null) {
            whereSql.append(" AND spn.demand_date between :startDate and :endDate");
            map.put("startDate", startDate);
            map.put("endDate", endDate);
        } else if (startDate != null && endDate == null) {
            whereSql.append(" AND spn.demand_date >= :startDate");
            map.put("startDate", startDate);
        } else if (endDate != null && startDate == null) {
            whereSql.append(" AND spn.demand_date <= :endDate");
            map.put("endDate", endDate);
        }
        if (parameters.getBoolean("numFlag") != null && parameters.getBoolean("numFlag") == true) {
            whereSql.append(" AND spn.quantity > spn.delivery_qty");
        }
        List<SrmPoNoticeEntity_HI_RO> list = srmPoNoticeDao_HI_RO.findList(whereSql, map);
        if(null != list && list.size()>0){
            return list.get(0);
        }else{
            return new SrmPoNoticeEntity_HI_RO();
        }

    }

    /**
     * Description：采购订单lov
     * @param params 查询条件参数
     * @param pageIndex 页码
     * @param pagerows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoNoticeEntity_HI_RO> findNoticeLov(JSONObject params, Integer pageIndex, Integer pagerows) throws Exception {
        StringBuffer sqlBuffer = new StringBuffer(SrmPoNoticeEntity_HI_RO.QUERY_NOTICE_LOV_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(params, " n.po_notice_code", "poNoticeCode", sqlBuffer, map, "like");
        String countSql = "select count(1) from (" + sqlBuffer + ")";
        Pagination<SrmPoNoticeEntity_HI_RO> list = srmPoNoticeDao_HI_RO.findPagination(sqlBuffer,countSql, map, pageIndex, pagerows);
        return list;
    }

    /**
     * Description：查询送货通知行信息
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoNoticeLineEntity_HI_RO> findNoticeLineList(JSONObject params) throws Exception {
        LOGGER.info("findNoticeLineList-->:" + params);
        StringBuffer sqlBuffer = new StringBuffer(SrmPoNoticeLineEntity_HI_RO.QUERY_NOTICE_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        Integer poNoticeId = params.getInteger("poNoticeId");
        if (poNoticeId == null) {
            return null;
        }
        sqlBuffer.append(" AND spnl.po_notice_id = :poNoticeId");
        map.put("poNoticeId", poNoticeId);
        String countSql = "select count(1) from (" + sqlBuffer + ")";
        sqlBuffer.append(" ORDER BY spnl.last_update_date DESC");
        Pagination<SrmPoNoticeLineEntity_HI_RO> lineList = srmPoNoticeLineDao_HI_RO.findPagination(sqlBuffer,countSql, map, 0, 0);
        return lineList;
    }

    /**
     * Description：关闭送货单
     * @param params 关闭条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updateColseNotice(JSONObject params) throws Exception {
        LOGGER.info("updateColseNotice:-->:" + params);
        SrmPoNoticeEntity_HI entity_HI = null;
        JSONArray jsonArray = params.getJSONArray("poNoticeIds");
        if(null == jsonArray || jsonArray.isEmpty()){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        Integer userId = params.getInteger("varUserId");
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            entity_HI = srmPoNoticeDAO_HI.getById(jsonArray.getInteger(i));
            entity_HI.setStatus("SHORTAGE_CLOSED");
            entity_HI.setLastUpdateDate(new Date());
            entity_HI.setLastUpdatedBy(userId);
            entity_HI.setOperatorUserId(userId);
            srmPoNoticeDAO_HI.update(entity_HI);
        }
        return SToolUtils.convertResultJSONObj("S", "关闭成功", 1, null);
    }

    /**
     * Description：修改送货通知状态
     * @param params 修改条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updateNoticeStatus(JSONObject params) throws Exception {
        LOGGER.info("updateNoticeStatus:-->:" + params);
        SrmPoNoticeEntity_HI entity_HI = null;
        Integer userId = params.getInteger("varUserId");
        if(null == params.getInteger("poNoticeId")){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"参数为空",0,null);
        }
        entity_HI = srmPoNoticeDAO_HI.getById(params.getInteger("poNoticeId"));
        entity_HI.setStatus(params.getString("affirmStatus"));
        entity_HI.setAffirmStatus(params.getString("affirmStatus"));
        entity_HI.setLastUpdateDate(new Date());
        entity_HI.setLastUpdatedBy(userId);
        entity_HI.setOperatorUserId(userId);
        srmPoNoticeDAO_HI.update(entity_HI);
        return SToolUtils.convertResultJSONObj("S", "修改成功", 1, null);
    }

    /**
     * Description：保存通知
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveNotice(JSONObject params) throws Exception {
        LOGGER.info("saveNotice:-->:" + params);
        if(null == params.getString("noticeType") || "".equals(params.getString("noticeType"))){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"公告类型必填",0,null);
        }
        if(null == params.getString("noticeTitle") || "".equals(params.getString("noticeTitle"))){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"公告标题必填",0,null);
        }
        if(null == params.getString("receiverType") || "".equals(params.getString("receiverType"))){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"发布对象必填",0,null);
        }
        SrmPoNoticeEntity_HI entity_HI = null;
        SrmPoNoticeLineEntity_HI lineEntityHi = null;
        SrmPoLinesEntity_HI entity_hi = null;
        entity_HI = new SrmPoNoticeEntity_HI();
        Integer userId = params.getInteger("varUserId");
        entity_HI.setPoNoticeCode(saafSequencesUtil.getDocSequences("srm_po_notice".toUpperCase(), DateUtil.date2Str(new Date(), "yyyyMMdd"), 6));
        entity_HI.setInstId(params.getInteger("instId"));
        entity_HI.setItemId(params.getInteger("itemId"));
        entity_HI.setSupplierId(params.getInteger("supplierId"));
        entity_HI.setDemandDate(params.getDate("demandDate"));

        Integer varEmployeeId = params.getInteger("varEmployeeId");
        String employeeNumber = params.getString("employeeNumber");
        SaafEmployeesEntity_HI employeesEntityHi = null;
        if (employeeNumber == null || "".equals(employeeNumber.trim())) {
            employeesEntityHi = employeesDAO_HI.getById(varEmployeeId);
            if (!"Y".equals(employeesEntityHi.getEnabledFlag())) {
                throw new Exception("采购员已失效，不能创建送货通知");
            }
            entity_HI.setEmployeeNum(employeesEntityHi.getEmployeeNumber());
        } else {
            entity_HI.setEmployeeNum(employeeNumber);
        }
        entity_HI.setStatus("CREATED");// 保存为已创建
        entity_HI.setSpecialUseNum(SrmUtils.getString(params.getString("specialUseNum")));
        // 如果送货地地点为空 那么则默认为货主组织
        if (params.getInteger("deliverySiteId") == null || "".equals(params.getString("deliverySiteId").trim())) {
            entity_HI.setDeliverySiteId(params.getInteger("instId"));
        } else {
            entity_HI.setDeliverySiteId(params.getInteger("deliverySiteId"));
        }
        entity_HI.setDeliveryOrderQty(params.getBigDecimal("veryQty"));
        entity_HI.setDeliveryQty(new BigDecimal(0));
        entity_HI.setQuantity(params.getBigDecimal("numberSum"));// 需求数量
        entity_HI.setCreatedBy(userId);
        entity_HI.setDocumentType(params.getString("documentType"));
        entity_HI.setOperatorUserId(userId);
        entity_HI.setCreationDate(new Date());
        entity_HI.setLastUpdatedBy(userId);
        entity_HI.setLastUpdateDate(new Date());
        try {
            srmPoNoticeDAO_HI.save(entity_HI);
        } catch (DataIntegrityViolationException e) {
            // 抛出此异常是生成的编码数据库里已存在
            throw new UtilsException("生成的单号:" + entity_HI.getPoNoticeCode() + " 已存在,请通知管理员");
        } catch (Exception e) {
            throw new UtilsException("未知异常,请联系管理员");
        }
        JSONArray jsonArray = params.getJSONArray("data");
        if(null != jsonArray && jsonArray.size()>0){
            for (int i = 0, j = jsonArray.size(); i < j; i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                lineEntityHi = new SrmPoNoticeLineEntity_HI();
                lineEntityHi.setPoNoticeId(entity_HI.getPoNoticeId());
                lineEntityHi.setPoHeaderId(obj.getInteger("poHeaderId"));
                lineEntityHi.setPoLineId(obj.getInteger("poLineId"));
                SrmPoLinesEntity_HI resutLine = poLinesDAO_HI.getById(obj.getInteger("poLineId"));
                if (resutLine == null) {
                    throw new Exception("采购订单:" + obj.getString("poNumber") + "不存在;");
                }
                if (!resutLine.getItemId().equals(params.getInteger("itemId"))) {
                    throw new Exception("物料不一致;");
                }

                SrmPoHeadersEntity_HI resultHead = poHeadersDAO_HI.getById(resutLine.getPoHeaderId());
                if (resultHead == null) {
                    throw new Exception("采购订单:" + obj.getString("poNumber") + "没有头信息;");
                }
                if (!resultHead.getPoDocType().equals(params.getString("documentType"))) {
                    throw new Exception("单据类型不一致");
                }
                lineEntityHi.setCreationDate(new Date());
                lineEntityHi.setCreatedBy(userId);
                lineEntityHi.setLastUpdateDate(new Date());
                lineEntityHi.setLastUpdatedBy(userId);
                lineEntityHi.setOperatorUserId(userId);
                // 比较大小可以用 a.compareTo(b)
                // 返回值 -1 小于 0 等于 1 大于
                if (obj.getBigDecimal("inputQty").compareTo(new BigDecimal(0)) == 1) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("poLineId", obj.getInteger("poLineId"));
                    SrmPoNoticeLineEntity_HI_RO noticeLineEntity = srmPoNoticeLineDao_HI_RO
                            .findList(new StringBuffer(SrmPoNoticeLineEntity_HI_RO.QUERY_LINE_MATCHNUMBER_SQL), map).get(0);
                    lineDAO_HI.save(lineEntityHi);
                    // 修改行表的数量
                    entity_hi = poLinesDAO_HI.getById(obj.getInteger("poLineId"));
                    entity_hi.setLastUpdateDate(new Date());
                    entity_hi.setLastUpdatedBy(userId);
                    entity_hi.setOperatorUserId(userId);
                    poLinesDAO_HI.update(entity_hi);
                }
            }
        }
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, entity_HI);
    }

    /**
     * Description：查询送货通知(1周)
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmPoNoticeEntity_HI_RO> findNoticeList(JSONObject params) throws Exception {
        StringBuffer sql = new StringBuffer(SrmPoNoticeEntity_HI_RO.QUERT_NOTICE_LIST_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        LOGGER.info("=======" + JSON.toJSONString(params));
        String instId = params.getString("instId");
        Integer supplierId = params.getInteger("varSupplierId");
        SaafToolUtils.parperParam(params, "pn.item_id", "itemId", sql, map, "=");
        if (null != supplierId) {
            sql.append(" AND pn.supplier_id = :supplierId ");
            map.put("supplierId", supplierId);
        } else {
            SaafToolUtils.parperParam(params, "pn.supplier_id", "supplierId", sql, map, "=");
        }
        if (!"all".equals(instId)) {
            // 送货地点
            SaafToolUtils.parperParam(params, "pn.delivery_site_id", "instId", sql, map, "=");
        }
        SaafToolUtils.parperParam(params, "bc.category_code", "categoryCode", sql, map, "=");
        if ("".equals(instId) || null == instId) {
            // 分厂为空
            sql.append(
                    " group by bi.ITEM_CODE,pn.supplier_id,pn.demand_date order by bi.ITEM_CODE,pn.delivery_site_id");
        } /*
         * else if ("all".equals(instId)) { //所有 sql.append(
         * " group by bi.ITEM_CODE,pn.inst_id,pn.supplier_id,pn.demand_date order by bi.ITEM_CODE,pn.inst_id"
         * ); }
         */ else {
            // 指定
            sql.append(
                    " group by bi.ITEM_CODE,pn.delivery_site_id,pn.supplier_id,pn.demand_date order by bi.ITEM_CODE,pn.delivery_site_id");
        }
        int days[] = new int[7];
        for (int i = 0; i < 7; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, i);
            days[i] = calendar.get(Calendar.DATE);
        }
        List<SrmPoNoticeEntity_HI_RO> list = srmPoNoticeDao_HI_RO.findList(sql, map);
        List<SrmPoNoticeEntity_HI_RO> noticeList = new ArrayList<SrmPoNoticeEntity_HI_RO>();
        if(null != list && list.size()>0){
            for (SrmPoNoticeEntity_HI_RO entity : list) {
                int mark = -1;
                if ("".equals(instId) || null == instId) {
                    mark = indexOf(noticeList, entity);
                } else {
                    mark = indexOf(noticeList, entity, 0);
                }
                if (mark == -1) {
                    if (entity.getDay() == days[0]) {
                        entity.setSumNumber1(entity.getSumNumber());
                    } else if (entity.getDay() == days[1]) {
                        entity.setSumNumber2(entity.getSumNumber());
                    } else if (entity.getDay() == days[2]) {
                        entity.setSumNumber3(entity.getSumNumber());
                    } else if (entity.getDay() == days[3]) {
                        entity.setSumNumber4(entity.getSumNumber());
                    } else if (entity.getDay() == days[4]) {
                        entity.setSumNumber5(entity.getSumNumber());
                    } else if (entity.getDay() == days[5]) {
                        entity.setSumNumber6(entity.getSumNumber());
                    } else if (entity.getDay() == days[6]) {
                        entity.setSumNumber7(entity.getSumNumber());
                    }
                    noticeList.add(entity);
                } else {
                    SrmPoNoticeEntity_HI_RO notice = noticeList.get(mark);
                    if (entity.getDay() == days[0]) {
                        if (notice.getSumNumber1() == null)
                            notice.setSumNumber1(new BigDecimal(0));
                        notice.setSumNumber1(notice.getSumNumber1().add(entity.getSumNumber()));
                    } else if (entity.getDay() == days[1]) {
                        if (notice.getSumNumber2() == null)
                            notice.setSumNumber2(new BigDecimal(0));
                        notice.setSumNumber2(notice.getSumNumber2().add(entity.getSumNumber()));
                    } else if (entity.getDay() == days[2]) {
                        if (notice.getSumNumber3() == null)
                            notice.setSumNumber3(new BigDecimal(0));
                        notice.setSumNumber3(notice.getSumNumber3().add(entity.getSumNumber()));
                    } else if (entity.getDay() == days[3]) {
                        if (notice.getSumNumber4() == null)
                            notice.setSumNumber4(new BigDecimal(0));
                        notice.setSumNumber4(notice.getSumNumber4().add(entity.getSumNumber()));
                    } else if (entity.getDay() == days[4]) {
                        if (notice.getSumNumber5() == null)
                            notice.setSumNumber5(new BigDecimal(0));
                        notice.setSumNumber5(notice.getSumNumber5().add(entity.getSumNumber()));
                    } else if (entity.getDay() == days[5]) {
                        if (notice.getSumNumber6() == null)
                            notice.setSumNumber6(new BigDecimal(0));
                        notice.setSumNumber6(notice.getSumNumber6().add(entity.getSumNumber()));
                    } else if (entity.getDay() == days[6]) {
                        if (notice.getSumNumber7() == null)
                            notice.setSumNumber7(new BigDecimal(0));
                        notice.setSumNumber7(notice.getSumNumber7().add(entity.getSumNumber()));
                    }
                    noticeList.set(mark, notice);
                }
            }
        }
        return noticeList;
    }
    /**
     * Description：根据CODE查询送货通知
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoNoticeEntity_HI_RO> findNoticeCodeLov(JSONObject params, Integer pageIndex, Integer pagetRows) throws Exception {
        StringBuffer sqlBuff = new StringBuffer(SrmPoNoticeEntity_HI_RO.QUERY_NOTICE_CODE_LOV);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(params, " n.po_notice_code", "poNoticeCode", sqlBuff, map, "like");
        String countSql = "select count(1) from (" + sqlBuff + ")";
        sqlBuff.append(" ORDER BY n.po_notice_code DESC");
        return srmPoNoticeDao_HI_RO.findPagination(sqlBuff,countSql, map, pageIndex, pagetRows);
    }

    /**
     * Description：
     * @param noticeList
     * @param entity
     * @param flag
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private int indexOf(List<SrmPoNoticeEntity_HI_RO> noticeList, SrmPoNoticeEntity_HI_RO entity, int flag) {
        for (int i = 0, j = noticeList.size(); i < j; i++) {
            SrmPoNoticeEntity_HI_RO notice = noticeList.get(i);
            if (notice.getItemId().equals(entity.getItemId()) && notice.getSupplierId().equals(entity.getSupplierId())
                    && notice.getInstId().equals(entity.getInstId())) {
                return i;
            }
        }
        return -1;
    }
    /**
     * Description：分厂为空的时候根据供应商来分组
     * @param noticeList
     * @param entity
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private int indexOf(List<SrmPoNoticeEntity_HI_RO> noticeList, SrmPoNoticeEntity_HI_RO entity) {
        for (int i = 0, j = noticeList.size(); i < j; i++) {
            SrmPoNoticeEntity_HI_RO notice = noticeList.get(i);
            if (notice.getItemId().equals(entity.getItemId())
                    && notice.getSupplierId().equals(entity.getSupplierId())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Description：供应商导出送货通知(已废弃)
     * @param parameters 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Deprecated
    @Override
    public List<SrmPoNoticeEntity_HI_RO> findSupplyExport(JSONObject parameters) throws Exception {
        StringBuffer sqlBuffer = new StringBuffer(SrmPoNoticeEntity_HI_RO.QUERY_SUPPLY_NOTICE_EXPORT_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        Integer supplierId = parameters.getInteger("varSupplierId");
        // 如果供应商不为null 就查询供应商的信息
        // supplierId=1;
        if (supplierId != null) {
            sqlBuffer.append(" and EXISTS ( SELECT 1 FROM srm_pos_supplier_info l WHERE spn.supplier_id = l.supplier_id AND spn.supplier_id = :var_supplier_id ) ");
            map.put("var_supplier_id", supplierId);
        }
        // 物料类别
        SaafToolUtils.parperParam(parameters, " sbc.CATEGORY_CODE", "categoryCode", sqlBuffer, map, "like");
        // 物料编码
        SaafToolUtils.parperParam(parameters, " sbi.item_code", "itemCode", sqlBuffer, map, "like");
        // 供应商
        SaafToolUtils.parperParam(parameters, " spn.supplier_id", "supplierId", sqlBuffer, map, "like");
        // 采购员
        SaafToolUtils.parperParam(parameters, " spn.employee_num", "employeeNum", sqlBuffer, map, "like");
        SaafToolUtils.parperParam(parameters, " se.employee_name", "employeeName", sqlBuffer, map, "=");
        // 组织编码
        SaafToolUtils.parperParam(parameters, " si.inst_id", "instId", sqlBuffer, map, "like");
        // 送货通知状态
        SaafToolUtils.parperParam(parameters, " spn.status", "status", sqlBuffer, map, "=");
        SaafToolUtils.parperParam(parameters, " spsi.supplier_number", "supplierNumber", sqlBuffer, map, "=");
        // 供应商
        String startDate = null;
        String endDate = null;
        if (parameters.getString("startDate") != null && !"".equals(parameters.getString("startDate").trim())) {
            startDate = parameters.getString("startDate");
        }
        if (parameters.getString("endDate") != null && !"".equals(parameters.getString("endDate").trim())) {
            endDate = parameters.getString("endDate");
        }
        if (startDate != null && endDate != null) {
            sqlBuffer.append(" AND spn.demand_date between '" + startDate + "' AND '" + endDate + "'");
        }
        if (parameters.getBoolean("numFlag") != null && parameters.getBoolean("numFlag") == true) {
            sqlBuffer.append(" AND spn.quantity > spn.delivery_qty");
        }
        sqlBuffer.append(" ORDER BY spn.CREATION_DATE DESC");
        List<SrmPoNoticeEntity_HI_RO> noticeList = srmPoNoticeDao_HI_RO.findList(sqlBuffer, map);
        return noticeList;
    }
    /**
     * Description：根据ID查询送货通知
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmPoNoticeEntity_HI_RO> findDeliveryByNoticeId(JSONObject params) throws Exception {
        StringBuffer sqlBuff = new StringBuffer(SrmPoNoticeEntity_HI_RO.QUERY_DELIVER_BY_NOTICE_ID);
        Map<String, Object> map = new HashMap<String, Object>();
        Integer noticeId = params.getInteger("noticeId");
        if (noticeId == null) {
            LOGGER.error("findDeliveryByNoticeId: 查询送货单失败,id为空");
            throw new Exception("查询失败");
        }
        map.put("noticeId", noticeId);
        return srmPoNoticeDao_HI_RO.findList(sqlBuff, map);

    }
    /**
     * Description：保存送货通知行
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * accepterName  通知接收人  VARCHAR2  N
     * uomCode  采购单位  VARCHAR2  N
     * firstFeedbackRecodeDate  第一次反馈记录时间  DATE  N
     * allotLineId  调拨计划差异行ID  NUMBER  N
     * noticeLineNumber  通知单行号  NUMBER  N
     * safeStock  安全库存  NUMBER  N
     * serialNo  流水号  VARCHAR2  N
     * maxStock  最大库存量  NUMBER  N
     * nowStock  现有量  NUMBER  N
     * itemPlanType  物料计划方式  VARCHAR2  N
     * lineId  送货通知单行ID  NUMBER  Y
     * poNoticeId  送货通知单ID  NUMBER  Y
     * poHeaderId  采购订单ID  NUMBER  N
     * poLineId  采购订单行ID  NUMBER  N
     * noticeDeliveryDate  通知送货日期  DATE  N
     * noticeDeliveryQty  通知送货数量  NUMBER  N
     * onWayQty  在途数量（已创建送货单数量）  NUMBER  N
     * lineComments  送货备注  VARCHAR2  N
     * lineStatus  行状态  VARCHAR2  N
     * originalDeliveryDate  原通知送货日期  DATE  N
     * originalDeliveryQty  原通知送货数量  NUMBER  N
     * feedbackAdjustDate  反馈调整日期  DATE  N
     * feedbackAdjustQty  反馈调整数量  NUMBER  N
     * feedbackStatus  反馈状态  VARCHAR2  N
     * feedbackResult  反馈结果  VARCHAR2  N
     * rejectReason  供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * lineId  送货通知单行ID  NUMBER  Y
     * poNoticeId  送货通知单ID  NUMBER  Y
     * poHeaderId  采购订单ID  NUMBER  N
     * poLineId  采购订单行ID  NUMBER  N
     * noticeDeliveryDate  通知送货日期  DATE  N
     * noticeDeliveryQty  通知送货数量  NUMBER  N
     * onWayQty  在途数量（已创建送货单数量）  NUMBER  N
     * lineComments  送货备注  VARCHAR2  N
     * lineStatus  行状态  VARCHAR2  N
     * originalDeliveryDate  原通知送货日期  DATE  N
     * originalDeliveryQty  原通知送货数量  NUMBER  N
     * feedbackAdjustDate  反馈调整日期  DATE  N
     * feedbackAdjustQty  反馈调整数量  NUMBER  N
     * feedbackStatus  反馈状态  VARCHAR2  N
     * feedbackResult  反馈结果  VARCHAR2  N
     * rejectReason  供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveNoticeLine(JSONObject params) throws Exception {
        LOGGER.info("saveNoticeLine:-->:" + params);
        SrmPoNoticeLineEntity_HI lineEntityHi = null;
        Date date = new Date(System.currentTimeMillis());
        StringBuffer radomBuffer = new StringBuffer();
        String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
        Random rand = new Random();
        int radomNumber = rand.nextInt(90) + 10;
        radomBuffer.append(dateFromate);
        radomBuffer.append(radomNumber);
        Integer poNoticeIdTemp = Integer.valueOf(radomBuffer.toString());
        Integer poNoticeId = Integer.valueOf(poNoticeIdTemp);
        List<SrmPoNoticeLineEntity_HI> lineList = new ArrayList<SrmPoNoticeLineEntity_HI>();
        Integer userId = params.getInteger("varUserId");
        JSONArray jsonArray = params.getJSONArray("lineData");
        if(null == jsonArray || jsonArray.isEmpty()){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        if(null != jsonArray && jsonArray.size()>0){
            for (int i = 0, j = jsonArray.size(); i < j; i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                lineEntityHi = new SrmPoNoticeLineEntity_HI();
                lineEntityHi.setPoNoticeId(poNoticeId);
                lineEntityHi.setPoHeaderId(obj.getInteger("poHeaderId"));
                lineEntityHi.setPoLineId(obj.getInteger("poLineId"));
                lineEntityHi.setLineStatus("OPEN");
                lineEntityHi.setFeedbackAdjustDate(obj.getDate("feedbackAdjustDate"));
                lineEntityHi.setFeedbackAdjustQty(obj.getBigDecimal("feedbackAdjustQty"));
                lineEntityHi.setFeedbackStatus("NON_FEEDBACK");
                lineEntityHi.setFeedbackResult(obj.getString("feedbackResult"));
                lineEntityHi.setRejectReason(obj.getString("rejectReason"));
                lineEntityHi.setOperatorUserId(userId);
                lineList.add(lineEntityHi);
            }
            lineDAO_HI.saveAll(lineList);
        }
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, lineEntityHi);
    }
    /**
     * Description：查询送货通知行信息
     * @param jsonParams 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoNoticeLineEntity_HI_RO> findNoticeLineInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoNoticeLineEntity_HI_RO.QUERY_SRMPONOTICElINE_SQL);
        SaafToolUtils.parperParam(jsonParams, "pnl.po_notice_id", "poNoticeId", queryString, queryParamMap, "=");
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" ORDER BY pnl.line_id");
        Pagination<SrmPoNoticeLineEntity_HI_RO> result = srmPoNoticeLineDao_HI_RO.findPagination(queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
    /**
     * Description：保存送货通知信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveSrmPoNotice(JSONObject params) throws Exception {
        LOGGER.info("saveSrmPoNotice:-->:" + params);
        Integer poNoticeId = params.getInteger("poNoticeId");
        Integer operatorUserId = params.getInteger("operatorUserId");
        Date date = new Date(System.currentTimeMillis());
        SrmPoNoticeEntity_HI noticeEntity = null;
        List<SrmPoNoticeLineEntity_HI> lineList = new ArrayList<SrmPoNoticeLineEntity_HI>();
        Integer orgId = params.getInteger("orgId");
        Integer billToOrganizationId = params.getInteger("billToOrganizationId");
        Integer buyerId = params.getInteger("employeeId");
        Integer supplierId = params.getInteger("supplierId");
        if(null == supplierId){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"供应商必填",0,null);
        }
        Integer userId = params.getInteger("varUserId");
        String comments = params.getString("comments");
        Integer supplierSiteId = params.getInteger("supplierSiteId");
        StringBuffer buffer = new StringBuffer();
        // 有就更新没有就新增
        if (null == poNoticeId) {
            noticeEntity = new SrmPoNoticeEntity_HI();
            // 自动生成采购订单号
            String poNoticeCode = saafSequencesUtil.getDocSequences("srm_po_notice_line".toUpperCase(),
                    DateUtil.date2Str(new Date(), "yyyyMMdd"), 2);
            noticeEntity.setPoNoticeCode(poNoticeCode);

        } else {
            noticeEntity = srmPoNoticeDAO_HI.getById(poNoticeId);
        }
        noticeEntity.setOrgId(orgId);
        noticeEntity.setComments(comments);
        noticeEntity.setBuyerId(buyerId);
        noticeEntity.setBillToOrganizationId(billToOrganizationId);
        noticeEntity.setSupplierId(supplierId);
        noticeEntity.setCreationDate(date);
        noticeEntity.setSupplierSiteId(supplierSiteId);
        noticeEntity.setPoNoticeStatus("DRAFT");
        noticeEntity.setPoNoticeVersions(BigDecimal.valueOf(1));
        noticeEntity.setFileId(params.getInteger("commonFileId") == null ? noticeEntity.getFileId() : params.getInteger("commonFileId"));
        noticeEntity.setCreatedBy(userId);
        noticeEntity.setOperatorUserId(userId);
        srmPoNoticeDAO_HI.saveOrUpdate(noticeEntity);
        JSONArray jsonArray = params.getJSONArray("lineData");
        if (null != jsonArray && jsonArray.size() != 0) {
            for (int i = 0, j = jsonArray.size(); i < j; i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                SrmPoNoticeLineEntity_HI lineEntity = null;
                Integer lineId = obj.getInteger("lineId");
                if (null == lineId) {
                    lineEntity = new SrmPoNoticeLineEntity_HI();
                } else {
                    lineEntity = lineDAO_HI.getById(lineId);
                }
                lineEntity.setPoNoticeId(noticeEntity.getPoNoticeId());
                lineEntity.setNoticeDeliveryDate(obj.getDate("noticeDeliveryDate"));
                lineEntity.setNoticeDeliveryQty(obj.getBigDecimal("noticeDeliveryQty"));
                lineEntity.setLineComments(obj.getString("lineComments"));
                //实现方式变了
                lineEntity.setPoHeaderId(obj.getInteger("poHeaderId"));
                lineEntity.setPoLineId(obj.getInteger("poLineId"));
                lineEntity.setLineStatus("OPEN");
                lineEntity.setFeedbackAdjustDate(obj.getDate("feedbackAdjustDate"));
                lineEntity.setFeedbackAdjustQty(obj.getBigDecimal("feedbackAdjustQty"));
                lineEntity.setFeedbackStatus("NON_FEEDBACK");
                lineEntity.setFeedbackResult(obj.getString("feedbackResult"));
                lineEntity.setRejectReason(obj.getString("rejectReason"));
                lineEntity.setOperatorUserId(userId);
                lineList.add(lineEntity);
            }
            lineDAO_HI.saveOrUpdateAll(lineList);
        }
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, noticeEntity);
    }

    /**
     * Description：保存送货通知信息(采购订单创建送货通知单)
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveSrmOrderToPoNotice(JSONObject params) throws Exception {
        LOGGER.info("saveSrmOrderToPoNotice:-->:" + params);
        Date date = new Date(System.currentTimeMillis());
        Map<String, SrmPoNoticeEntity_HI> headMap = new HashMap<String, SrmPoNoticeEntity_HI>();
        Map<String, List<SrmPoNoticeLineEntity_HI>> lineMap = new HashMap<String, List<SrmPoNoticeLineEntity_HI>>();
        JSONArray jsonArray = params.getJSONArray("lineData");
        SrmPoNoticeEntity_HI noticeEntity = null;
        SrmPoNoticeLineEntity_HI lineEntity = null;
        if(null != jsonArray && jsonArray.size()>0){
            for (int i = 0, j = jsonArray.size(); i < j; i++) {
                JSONObject jsonLine = jsonArray.getJSONObject(i);
                String poNumber = jsonLine.getString("poNumber");
                noticeEntity = new SrmPoNoticeEntity_HI();
                noticeEntity.setOrgId(jsonLine.getInteger("orgId"));// 业务实体
                noticeEntity.setComments(jsonLine.getString("description"));
                noticeEntity.setBuyerId(jsonLine.getInteger("buyerId"));
                noticeEntity.setBillToOrganizationId(jsonLine.getInteger("billToOrganizationId"));// 收单组织instType:'ORGANIZATION'
                noticeEntity.setSupplierId(jsonLine.getInteger("supplierId"));
                noticeEntity.setSupplierSiteId(jsonLine.getInteger("supplierSiteId"));
                noticeEntity.setPoNoticeStatus("DRAFT");
                noticeEntity.setCreationDate(date);
                noticeEntity.setPoNoticeStatus("DRAFT");
                noticeEntity.setPoNoticeVersions(BigDecimal.valueOf(1));
                noticeEntity.setCreatedBy(params.getInteger("varUserId"));
                noticeEntity.setOperatorUserId(params.getInteger("varUserId"));
                if (!headMap.containsKey(poNumber)) {
                    String poNoticeCode = saafSequencesUtil.getDocSequences("srm_po_notice_line".toUpperCase(), DateUtil.date2Str(new Date(), "yyyyMMdd"), 2);
                    noticeEntity.setPoNoticeCode(poNoticeCode);
                    headMap.put(poNumber, noticeEntity);
                }
                lineEntity = new SrmPoNoticeLineEntity_HI();
                lineEntity.setPoNoticeId(noticeEntity.getPoNoticeId());
                lineEntity.setPoHeaderId(jsonLine.getInteger("poHeaderId"));
                lineEntity.setPoLineId(jsonLine.getInteger("poLineId"));
                lineEntity.setNoticeDeliveryDate(jsonLine.getDate("demandDate"));
                lineEntity.setNoticeDeliveryQty(jsonLine.getBigDecimal("demandQty"));
                lineEntity.setLineComments(jsonLine.getString("lineDescription"));
                lineEntity.setCreationDate(date);
                lineEntity.setCreatedBy(params.getInteger("varUserId"));
                lineEntity.setLastUpdateDate(date);
                lineEntity.setLastUpdatedBy(params.getInteger("varUserId"));
                lineEntity.setOperatorUserId(noticeEntity.getCreatedBy());
                if (lineMap.containsKey(poNumber)) {
                    lineMap.get(poNumber).add(lineEntity);
                } else {
                    List<SrmPoNoticeLineEntity_HI> lineList = new ArrayList<SrmPoNoticeLineEntity_HI>();
                    lineList.add(lineEntity);
                    lineMap.put(poNumber, lineList);
                }
            }
        }
        if(null != headMap && headMap.size()>0){
            for (String key : headMap.keySet()) {
                noticeEntity = headMap.get(key);
                srmPoNoticeDAO_HI.save(noticeEntity);
                List<SrmPoNoticeLineEntity_HI> lineList = lineMap.get(key);
                if (null != lineList && lineList.size() != 0) {
                    for (SrmPoNoticeLineEntity_HI line : lineList) {
                        line.setPoNoticeId(headMap.get(key).getPoNoticeId());
                    }
                    lineDAO_HI.save(lineList);
                }
            }
        }
        return SToolUtils.convertResultJSONObj("S", "创建送货单成功", 1, noticeEntity);
    }
    /**
     * Description：根据id查询送货通知表
     * @param poNoticeId 送货通知ID
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmPoNoticeEntity_HI_RO> findSrmPoNoticeInfo(Integer poNoticeId) throws Exception {
        StringBuffer buffer = new StringBuffer();
        String sql = SrmPoNoticeEntity_HI_RO.QUERY_SRMPONOTICE_SQL;
        buffer.append("AND po_notice_id = ");
        buffer.append(poNoticeId);
        List<SrmPoNoticeEntity_HI_RO> list = srmPoNoticeDao_HI_RO.findList(sql + buffer, new HashMap<String, Object>());
        return list;
    }

    /**
     * Description：删除送货通知行
     * @param params 删除参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteNoticeLineInfo(JSONObject params) throws Exception {
        LOGGER.info("删除送货通知，参数：" + params.toString());
        Integer lineId = params.getInteger("lineId");
        if(null == lineId){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"参数为空",0,null);
        }
        SrmPoNoticeLineEntity_HI line = lineDAO_HI.getById(lineId);
        if(null == line){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"查无记录，已被删除",0,null);
        }
        lineDAO_HI.delete(line);
        return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
    }
    /**
     * Description：更新送货通知状态
     * @param params 修改条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updateSrmPoNotice(JSONObject params) throws Exception {
        LOGGER.info("updateSrmPoNotice:-->:" + params);
        String flag = params.getString("buttonFlag");
        System.out.println("flag:" + flag);
        Integer userId = params.getInteger("varUserId");
        Date date = new Date(System.currentTimeMillis());
        SrmPoNoticeEntity_HI entity_HI = null;
        SrmPoNoticeLineEntity_HI lineEntity = null;
        List<SrmPoNoticeLineEntity_HI> list = new ArrayList<>();
        SrmPoLinesEntity_HI poLinesEntity_hi = null;
        List<SrmPoLinesEntity_HI> poLinesEntityList = new ArrayList<SrmPoLinesEntity_HI>();
        if(null == params.getInteger("poNoticeId")){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"参数为空",0,null);
        }
        entity_HI = srmPoNoticeDAO_HI.getById(params.getInteger("poNoticeId"));
        JSONArray jsonArray = params.getJSONArray("lineData");
        if ("SUBMMIT".equals(flag)) { //提交
            entity_HI.setPoNoticeStatus("SUBMITTED");
            if (null != jsonArray && jsonArray.size() != 0) {
                for (int i = 0, j = jsonArray.size(); i < j; i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    Integer lineId = obj.getInteger("lineId");
                    if (null == lineId) {
                        lineEntity = new SrmPoNoticeLineEntity_HI();
                    } else {
                        lineEntity = lineDAO_HI.getById(lineId);
                    }
                    lineEntity.setPoNoticeId(entity_HI.getPoNoticeId());
                    lineEntity.setNoticeDeliveryDate(obj.getDate("noticeDeliveryDate"));
                    lineEntity.setNoticeDeliveryQty(obj.getBigDecimal("noticeDeliveryQty"));
                    lineEntity.setLineComments(obj.getString("lineComments"));
                    lineEntity.setOperatorUserId(userId);
                    list.add(lineEntity);

                    //更新订单行表可通知数量
                    poLinesEntity_hi = poLinesDAO_HI.getById(obj.getInteger("poLineId"));
                    BigDecimal noticeDeliveryQty = obj.getBigDecimal("noticeDeliveryQty");
                    BigDecimal mayNoticeQty = obj.getBigDecimal("mayNoticeQty");
                    poLinesEntity_hi.setMayNoticeQty(mayNoticeQty.subtract(noticeDeliveryQty == null ? BigDecimal.ZERO : noticeDeliveryQty));
                    poLinesEntity_hi.setOperatorUserId(userId);
                    poLinesEntityList.add(poLinesEntity_hi);
                }
                lineDAO_HI.saveOrUpdateAll(list);
                poLinesDAO_HI.update(poLinesEntityList);
            }
        } else if ("REJECTED".equals(flag)) {//驳回
            entity_HI.setPoNoticeStatus("REJECTED");
            //更新订单行表可通知数量
            for (int i = 0, j = jsonArray.size(); i < j; i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                poLinesEntity_hi = poLinesDAO_HI.getById(obj.getInteger("poLineId"));
                BigDecimal noticeDeliveryQty = obj.getBigDecimal("noticeDeliveryQty");
                BigDecimal mayNoticeQty = obj.getBigDecimal("mayNoticeQty");
                poLinesEntity_hi.setMayNoticeQty(mayNoticeQty.add(noticeDeliveryQty == null ? BigDecimal.ZERO : noticeDeliveryQty));
                poLinesEntity_hi.setOperatorUserId(userId);
                poLinesEntityList.add(poLinesEntity_hi);
            }
        } else if ("APPROVED".equals(flag)) {//审批
            entity_HI.setPoNoticeStatus("APPROVED");//反馈审核通过
            entity_HI.setApprovedDate(date);
            entity_HI.setIssuedDate(date);
        } else if ("CLOSE".equals(flag)) {//关闭
            entity_HI.setPoNoticeStatus("CLOSE");
            //更新订单行表可通知数量
            for (int i = 0, j = jsonArray.size(); i < j; i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                poLinesEntity_hi = poLinesDAO_HI.getById(obj.getInteger("poLineId"));
                BigDecimal noticeDeliveryQty = obj.getBigDecimal("noticeDeliveryQty");
                BigDecimal mayNoticeQty = obj.getBigDecimal("mayNoticeQty");
                poLinesEntity_hi.setMayNoticeQty(mayNoticeQty.add(noticeDeliveryQty == null ? BigDecimal.ZERO : noticeDeliveryQty));
                poLinesEntity_hi.setOperatorUserId(userId);
                poLinesEntityList.add(poLinesEntity_hi);
            }
        } else if ("CANCEL".equals(flag)) {//取消
            entity_HI.setPoNoticeStatus("CANCEL");
            //更新订单行表可通知数量
            for (int i = 0, j = jsonArray.size(); i < j; i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                poLinesEntity_hi = poLinesDAO_HI.getById(obj.getInteger("poLineId"));
                BigDecimal noticeDeliveryQty = obj.getBigDecimal("noticeDeliveryQty");
                BigDecimal mayNoticeQty = obj.getBigDecimal("mayNoticeQty");
                poLinesEntity_hi.setMayNoticeQty(mayNoticeQty.add(noticeDeliveryQty == null ? BigDecimal.ZERO : noticeDeliveryQty));
                poLinesEntity_hi.setOperatorUserId(userId);
                poLinesEntityList.add(poLinesEntity_hi);
            }
        } else if ("UPDATE".equals(flag)) {//变更
            entity_HI.setPoNoticeStatus("UPDATE");
            BigDecimal versions = entity_HI.getPoNoticeVersions().add(BigDecimal.valueOf(1));
            entity_HI.setPoNoticeVersions(versions);
            if(null != jsonArray && jsonArray.size()>0){
                for (int i = 0, j = jsonArray.size(); i < j; i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    Integer lineId = obj.getInteger("lineId");
                    lineEntity = lineDAO_HI.getById(lineId);
                    lineEntity.setFeedbackStatus("NON_FEEDBACK");
                    lineEntity.setFeedbackAdjustDate(null);
                    lineEntity.setFeedbackAdjustQty(null);
                    lineEntity.setRejectReason(null);
                    lineEntity.setOperatorUserId(userId);
                    list.add(lineEntity);
                    //更新订单行表可通知数量
                    poLinesEntity_hi = poLinesDAO_HI.getById(obj.getInteger("poLineId"));
                    BigDecimal noticeDeliveryQty = obj.getBigDecimal("noticeDeliveryQty");
                    BigDecimal mayNoticeQty = obj.getBigDecimal("mayNoticeQty");
                    poLinesEntity_hi.setMayNoticeQty(mayNoticeQty.add(noticeDeliveryQty == null ? BigDecimal.ZERO : noticeDeliveryQty));
                    poLinesEntity_hi.setOperatorUserId(userId);
                    poLinesEntityList.add(poLinesEntity_hi);
                }
            }
        } else if ("REFUSE".equals(flag)) {//拒绝
            if(null != jsonArray && jsonArray.size()>0){
                for (int i = 0, j = jsonArray.size(); i < j; i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    if ("Y".equals(obj.getString("selectFlag"))) {
                        Integer lineId = obj.getInteger("lineId");
                        lineEntity = lineDAO_HI.getById(lineId);
                        lineEntity.setLineStatus("CANCEL");
                        lineEntity.setFeedbackStatus("REJECTED");
                        lineEntity.setOperatorUserId(userId);
                        list.add(lineEntity);
                    }
                }
            }
        } else if ("CONFIRM".equals(flag)) {//确认
            if(null != jsonArray && jsonArray.size()>0){
                for (int i = 0, j = jsonArray.size(); i < j; i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    if ("Y".equals(obj.getString("selectFlag"))) {
                        Date feedbackAdjustDate = obj.getDate("feedbackAdjustDate");
                        BigDecimal feedbackAdjustQty = obj.getBigDecimal("feedbackAdjustQty");
                        Integer lineId = obj.getInteger("lineId");
                        if (null != feedbackAdjustDate || null != feedbackAdjustQty) {
                            lineEntity = lineDAO_HI.getById(lineId);
                        }
                        if (null != feedbackAdjustDate) {
                            lineEntity.setOriginalDeliveryDate(lineEntity.getNoticeDeliveryDate());
                            lineEntity.setNoticeDeliveryDate(feedbackAdjustDate);
                        }
                        if (null != feedbackAdjustQty) {
                            lineEntity.setOriginalDeliveryQty(lineEntity.getNoticeDeliveryQty());
                            lineEntity.setNoticeDeliveryQty(feedbackAdjustQty);
                        }
                        if (null != lineEntity) {
                            lineEntity.setFeedbackStatus("APPROVED");
                            lineEntity.setOperatorUserId(userId);
                            list.add(lineEntity);
                        }
                    }
                }
            }
        } else if ("S_APPROVE".equals(flag)) {//供应商确认
            if(null != jsonArray && jsonArray.size()>0){
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject tempObj = jsonArray.getJSONObject(i);
                    SrmPoNoticeLineEntity_HI line = (SrmPoNoticeLineEntity_HI) lineDAO_HI.getById(tempObj.getInteger("lineId"));
                    if (null == line) {
                        return SToolUtils.convertResultJSONObj("E", "供应商确认失败,找不到订单行数据!", 0, null);
                    }
                    // 若反馈内容选择“确认”，则修改对应通知行的反馈状态改为“已确认”；
                    if ("CONFIRM".equals(tempObj.getString("feedbackResult"))) {
                        line.setFeedbackStatus("CONFIRMED");
                    }
                    // 若反馈内容选择“需调整”，则修改对应通知行的反馈状态改为“已反馈待审核”；
                    else if ("ADJUST".equals(tempObj.getString("feedbackResult"))) {
                        line.setFeedbackStatus("SUBMITTED");
                    }
                    // 若反馈内容选择“拒绝”，则修改对应通知行的反馈状态改为“已拒绝”。
                    else if ("REFUSE".equals(tempObj.getString("feedbackResult"))) {
                        line.setFeedbackStatus("REFUSED");
                    }
                    line.setFeedbackAdjustQty(tempObj.getBigDecimal("feedbackAdjustQty"));
                    line.setFeedbackAdjustDate(tempObj.getDate("feedbackAdjustDate"));
                    line.setFeedbackResult(tempObj.getString("feedbackResult"));
                    line.setRejectReason(tempObj.getString("rejectReason"));
                    line.setOperatorUserId(userId);
                    lineDAO_HI.update(line);
                }
            }
        }
        if (null != entity_HI && (!"CONFIRM".equals(flag) || !"REFUSE".equals(flag))) {
            entity_HI.setOperatorUserId(userId);
            srmPoNoticeDAO_HI.update(entity_HI);
        }
        if ("REFUSE".equals(flag) || "CONFIRM".equals(flag) || "UPDATE".equals(flag)) {
            if (null != list && !list.isEmpty()) {
                lineDAO_HI.saveOrUpdateAll(list);
            }
            SrmPoLinesEntity_HI poLineEntity = null;
            List<SrmPoLinesEntity_HI> poLineList = new ArrayList<>();
            if ("CONFIRM".equals(flag)) {
                if(null != jsonArray && jsonArray.size()>0){
                    for (int i = 0, j = jsonArray.size(); i < j; i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        if ("Y".equals(obj.getString("selectFlag"))) {
                            Integer poLineId = obj.getInteger("poLineId");
                            poLineEntity = poLinesDAO_HI.getById(poLineId);
                            poLineEntity.setFeedbackStatus("APPROVED");
                            poLineEntity.setOperatorUserId(userId);
                            poLineList.add(poLineEntity);
                        }
                    }
                }
                if (null != poLineList && !poLineList.isEmpty()) {
                    poLinesDAO_HI.updateAll(poLineList);
                }
            }
        }
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, entity_HI);
    }
    /**
     * Description：保存送货通知行-供应商
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * accepterName  通知接收人  VARCHAR2  N
     * uomCode  采购单位  VARCHAR2  N
     * firstFeedbackRecodeDate  第一次反馈记录时间  DATE  N
     * allotLineId  调拨计划差异行ID  NUMBER  N
     * noticeLineNumber  通知单行号  NUMBER  N
     * safeStock  安全库存  NUMBER  N
     * serialNo  流水号  VARCHAR2  N
     * maxStock  最大库存量  NUMBER  N
     * nowStock  现有量  NUMBER  N
     * itemPlanType  物料计划方式  VARCHAR2  N
     * lineId  送货通知单行ID  NUMBER  Y
     * poNoticeId  送货通知单ID  NUMBER  Y
     * poHeaderId  采购订单ID  NUMBER  N
     * poLineId  采购订单行ID  NUMBER  N
     * noticeDeliveryDate  通知送货日期  DATE  N
     * noticeDeliveryQty  通知送货数量  NUMBER  N
     * onWayQty  在途数量（已创建送货单数量）  NUMBER  N
     * lineComments  送货备注  VARCHAR2  N
     * lineStatus  行状态  VARCHAR2  N
     * originalDeliveryDate  原通知送货日期  DATE  N
     * originalDeliveryQty  原通知送货数量  NUMBER  N
     * feedbackAdjustDate  反馈调整日期  DATE  N
     * feedbackAdjustQty  反馈调整数量  NUMBER  N
     * feedbackStatus  反馈状态  VARCHAR2  N
     * feedbackResult  反馈结果  VARCHAR2  N
     * rejectReason  供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * lineId  送货通知单行ID  NUMBER  Y
     * poNoticeId  送货通知单ID  NUMBER  Y
     * poHeaderId  采购订单ID  NUMBER  N
     * poLineId  采购订单行ID  NUMBER  N
     * noticeDeliveryDate  通知送货日期  DATE  N
     * noticeDeliveryQty  通知送货数量  NUMBER  N
     * onWayQty  在途数量（已创建送货单数量）  NUMBER  N
     * lineComments  送货备注  VARCHAR2  N
     * lineStatus  行状态  VARCHAR2  N
     * originalDeliveryDate  原通知送货日期  DATE  N
     * originalDeliveryQty  原通知送货数量  NUMBER  N
     * feedbackAdjustDate  反馈调整日期  DATE  N
     * feedbackAdjustQty  反馈调整数量  NUMBER  N
     * feedbackStatus  反馈状态  VARCHAR2  N
     * feedbackResult  反馈结果  VARCHAR2  N
     * rejectReason  供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveNoticeLineForSupplier(JSONObject params) throws Exception {
        LOGGER.info("saveNoticeLineForSupplier:-->:" + params);
        Date date = new Date(System.currentTimeMillis());
        SrmPoNoticeLineEntity_HI lineEntity = null;
        List<SrmPoNoticeLineEntity_HI> lineList = new ArrayList<SrmPoNoticeLineEntity_HI>();
        Integer userId = params.getInteger("varUserId");
        JSONArray jsonArray = params.getJSONArray("lineData");
        if(null == jsonArray || jsonArray.isEmpty()){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        if(null != jsonArray && jsonArray.size()>0){
            for (int i = 0, j = jsonArray.size(); i < j; i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Integer lineId = obj.getInteger("lineId");
                lineEntity = lineDAO_HI.getById(lineId);
                lineEntity.setLastUpdateDate(date);
                Date feedbackAdjustDate = obj.getDate("feedbackAdjustDate");
                BigDecimal feedbackAdjustQty = obj.getBigDecimal("feedbackAdjustQty");
                String feedbackResult = obj.getString("feedbackResult");
                String rejectReason = obj.getString("rejectReason");
                if (null != feedbackAdjustDate) {
                    lineEntity.setFeedbackAdjustDate(feedbackAdjustDate);
                }
                if (null != feedbackAdjustQty) {
                    lineEntity.setFeedbackAdjustQty(feedbackAdjustQty);
                }
                if (StringUtil.isNotEmptyStr(feedbackResult)) {
                    lineEntity.setFeedbackResult(feedbackResult);
                    if ("CONFIRM".equals(feedbackResult)) {
                        lineEntity.setFeedbackStatus("CONFIRMED");
                    }
                    if ("ADJUST".equals(feedbackResult)) {
                        lineEntity.setFeedbackStatus("SUBMITTED");
                    }
                    if ("REFUSED".equals(feedbackResult)) {
                        lineEntity.setFeedbackStatus("REJECTED");
                    }
                }
                if (StringUtil.isNotEmptyStr(rejectReason)) {
                    lineEntity.setRejectReason(rejectReason);
                }
                lineEntity.setLastUpdatedBy(userId);
                lineEntity.setOperatorUserId(userId);
                lineList.add(lineEntity);
            }
            lineDAO_HI.saveOrUpdateAll(lineList);
        }
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, lineList);
    }


    /**
     * Description：查询送货通知信息-内部
     * @param params 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoNoticeEntity_HI_RO> findNoticeListsNew(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(params));
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer queryString = new StringBuffer(SrmPoNoticeEntity_HI_RO.QUERY_PO_NOTICE_INFO);
        //送货通知号
        SaafToolUtils.parperParam(params, "pn.po_notice_code", "poNoticeCode", queryString, queryParamMap, "LIKE");
        //业务实体
        SaafToolUtils.parperParam(params, "pn.org_id", "orgId", queryString, queryParamMap, "=");
        //收货组织
        SaafToolUtils.parperParam(params, "pl.receive_to_organization_id", "receiveToOrganizationId", queryString, queryParamMap, "=");
        // 采购员
        SaafToolUtils.parperParam(params, "pn.buyer_id", "employeeId", queryString, queryParamMap, "=");
        //下达日期从
        String startIssuedDate = params.getString("startIssuedDate");
        if (startIssuedDate != null && !"".equals(startIssuedDate.trim())) {
            queryString.append(" AND trunc(pn.issued_date) >= to_date(:startIssuedDate, 'yyyy-mm-dd')\n");
            queryParamMap.put("startIssuedDate", startIssuedDate);
        }
        //下达日期至
        String endIssuedDate = params.getString("endIssuedDate");
        if (endIssuedDate != null && !"".equals(endIssuedDate.trim())) {
            queryString.append(" AND trunc(pn.issued_date) <= to_date(:endIssuedDate, 'yyyy-mm-dd')\n");
            queryParamMap.put("endIssuedDate", endIssuedDate);
        }
        //通知送货日期从
        String startNoticeDeliveryDate = params.getString("startNoticeDeliveryDate");
        if (startNoticeDeliveryDate != null && !"".equals(startNoticeDeliveryDate.trim())) {
            queryString.append(" AND trunc(pnl.notice_delivery_date) >= to_date(:startNoticeDeliveryDate, 'yyyy-mm-dd')\n");
            queryParamMap.put("startNoticeDeliveryDate", startNoticeDeliveryDate);
        }
        //通知日期至
        String endNoticeDeliveryDate = params.getString("endNoticeDeliveryDate");
        if (endNoticeDeliveryDate != null && !"".equals(endNoticeDeliveryDate.trim())) {
            queryString.append(" AND trunc(pnl.notice_delivery_date) <= to_date(:endNoticeDeliveryDate, 'yyyy-mm-dd')\n");
            queryParamMap.put("endNoticeDeliveryDate", endNoticeDeliveryDate);
        }
        //物料
        SaafToolUtils.parperParam(params, "bi.item_code", "itemCode", queryString, queryParamMap, "=");
        //物料名称
        SaafToolUtils.parperParam(params, "bi.item_name", "itemName", queryString, queryParamMap, "LIKE");
        //物料类别
        SaafToolUtils.parperParam(params, "bc.category_code", "categoryCode", queryString, queryParamMap, "=");
        //类别名称
        SaafToolUtils.parperParam(params, "bc.category_name", "categoryName", queryString, queryParamMap, "LIKE");
        // 供应商
        SaafToolUtils.parperParam(params, "spsi.supplier_id", "supplierId", queryString, queryParamMap, "=");
        //供应商名称
        SaafToolUtils.parperParam(params, "spsi.supplier_name", "supplierName", queryString, queryParamMap, "LIKE");
        //通知行状态
        SaafToolUtils.parperParam(params, "pnl.line_status", "lineStatus", queryString, queryParamMap, "=");
        //反馈状态
        SaafToolUtils.parperParam(params, "pnl.feedback_status", "feedbackStatus", queryString, queryParamMap, "=");
        //送货通知备注
        SaafToolUtils.parperParam(params, "pn.comments", "comments", queryString, queryParamMap, "LIKE");
        //通知行备注
        SaafToolUtils.parperParam(params, "pnl.line_comments", "lineComments", queryString, queryParamMap, "LIKE");
        //送货通知状态
        SaafToolUtils.parperParam(params, "pn.po_notice_status", "status", queryString, queryParamMap, "=");
        //反馈结果
        SaafToolUtils.parperParam(params, "pnl.feedback_result", "feedbackResult", queryString, queryParamMap, "=");
        //只能查看当前账号已分配的业务实体相关的数据
        if (!"EX".equals(params.getString("varPlatformCode"))) {
            queryString.append(" AND check_org_f(" + params.getInteger("varUserId") + ", pn.org_id) = 'Y' ");
        }
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" ORDER BY pn.po_notice_id DESC");
        Pagination<SrmPoNoticeEntity_HI_RO> result = srmPoNoticeDao_HI_RO.findPagination(queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：送货通知接口（数据输入,用于外部访问的接口）需要提供用户和密码
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */


    public JSONObject saveNoticeInfoForExternal(JSONObject jsonParams, Integer userId) {
        SrmIntfLogsEntity_HI logsEntity = new SrmIntfLogsEntity_HI();
        JSONObject jsonData = new JSONObject(); // 最终结果的返回
        try {
            SrmPoNoticeEntity_HI noticeHeaderInfo = null;
            if (!(jsonParams.getJSONObject("noticeHeaderInfo") == null
                    || "".equals(jsonParams.getJSONObject("noticeHeaderInfo")))) {
                JSONObject noticeHeaderInfoJSON = jsonParams.getJSONObject("noticeHeaderInfo");
                noticeHeaderInfo = JSON.parseObject(noticeHeaderInfoJSON.toJSONString(), SrmPoNoticeEntity_HI.class);
            }

            List<SrmPoNoticeLineEntity_HI> noticeLineList = null;
            if (!(jsonParams.getJSONArray("noticeLineList") == null
                    || "".equals(jsonParams.getJSONArray("noticeLineList")))) {
                JSONArray noticeLineListJSON = jsonParams.getJSONArray("noticeLineList");
                noticeLineList = JSON.parseArray(noticeLineListJSON.toJSONString(), SrmPoNoticeLineEntity_HI.class);
            }

            /**
             * 校验重复性和必填项
             */
            // 校验送货通知头信息必填项
            String resultNoticeHeaderInfo = judgeSpaceNoticeHeaderInfo(noticeHeaderInfo);
            if (!(resultNoticeHeaderInfo == null || "".equals(resultNoticeHeaderInfo))) {
                return SToolUtils.convertResultJSONObj("E", resultNoticeHeaderInfo, 0, null);
            }
            // 校验送货通知行信息必填项
            String resultNoticeLineInfo = judgeSpaceNoticeLineInfo(noticeLineList);
            if (!(resultNoticeLineInfo == null || "".equals(resultNoticeLineInfo))) {
                return SToolUtils.convertResultJSONObj("E", resultNoticeLineInfo, 0, null);
            }

            SrmPoNoticeEntity_HI newHeader = null;
            if (noticeHeaderInfo.getPoNoticeId() != null) {
                newHeader = srmPoNoticeDAO_HI.getById(noticeHeaderInfo.getPoNoticeId());
                newHeader.setPoNoticeCode(noticeHeaderInfo.getPoNoticeCode());//送货通知编号
                newHeader.setOrgId(noticeHeaderInfo.getOrgId());//业务实体
                newHeader.setBillToOrganizationId(noticeHeaderInfo.getBillToOrganizationId());//收单组织
                newHeader.setPoNoticeStatus(noticeHeaderInfo.getPoNoticeStatus());//送货通知状态
                newHeader.setSupplierId(noticeHeaderInfo.getSupplierId());//供应商ID
                newHeader.setOperatorUserId(userId);
                srmPoNoticeDAO_HI.update(newHeader);
            } else {
                newHeader = noticeHeaderInfo;
                if (noticeHeaderInfo.getPoNoticeCode() == null || "".equals(noticeHeaderInfo.getPoNoticeCode())) { // insert时,送货通知编号为空时自动赋值
                    // 自动生成送货通知编号
                    String poNoticeCode = saafSequencesUtil.getDocSequences("srm_po_notice_line".toUpperCase(),
                            DateUtil.date2Str(new Date(), "yyyyMMdd"), 2);
                    newHeader.setPoNoticeCode(poNoticeCode);
                }
                newHeader.setOperatorUserId(userId);
                srmPoNoticeDAO_HI.save(newHeader);
                jsonData.put("poNoticeId", newHeader.getPoNoticeId());
                jsonData.put("noticeHeaderInfo", newHeader);
            }

            jsonData.put("poNoticeId", newHeader.getPoNoticeId());
            jsonData.put("noticeHeaderInfo", noticeHeaderInfo);

            /**
             * 处理行数据
             */
            List<SrmPoNoticeLineEntity_HI> newLineList = new ArrayList<SrmPoNoticeLineEntity_HI>();
            for (SrmPoNoticeLineEntity_HI po : noticeLineList) {
                SrmPoNoticeLineEntity_HI newLine = null;
                if (po.getLineId() != null) {
                    newLine = lineDAO_HI.getById(po.getLineId());
                    newLine.setPoNoticeId(newHeader.getPoNoticeId());
                    //通知送货日期
                    newLine.setNoticeDeliveryDate(po.getNoticeDeliveryDate());
                    //收货组织
                    //物料编码
                    //本次通知数量
                    newLine.setNoticeDeliveryQty(po.getNoticeDeliveryQty());
                    //通知行状态
                    newLine.setLineStatus(po.getLineStatus());
                    //采购订单号
                    newLine.setPoHeaderId(po.getPoHeaderId());
                    //采购订单行号
                    newLine.setPoLineId(po.getPoLineId());
                    newLine.setOperatorUserId(userId);
                    lineDAO_HI.update(newLine);
                } else {
                    newLine = po;
                    newLine.setPoNoticeId(newHeader.getPoNoticeId());
                    newLine.setOperatorUserId(userId);
                    lineDAO_HI.insert(newLine);

                }
                newLineList.add(newLine);
            }

            jsonData.put("noticeLineList", newLineList);

            //保存日志
            logsEntity.setIntfType("PO_NOTICE_IN");//接口类型BASE_INTF_TYPE
            logsEntity.setTableName("srm_po_notice");
            logsEntity.setTableId(newHeader.getPoNoticeId());//接口取数对应的表ID
            logsEntity.setDataDirection("IN");//数据方向(IN：输入， OUT：输出)
            logsEntity.setSendSystem(newHeader.getSourceCode());//数据发送方
            logsEntity.setReceiveSystem("SRM");
            logsEntity.setInData(jsonParams.toJSONString());//输入报文
            logsEntity.setOutData(jsonData.toJSONString());//输出报文
            logsEntity.setDescription("送货通知输入接口");
            logsEntity.setOperatorUserId(userId);
            logsEntity.setIntfStatus("S");
            srmIntfLogsDAO_HI.save(logsEntity);
            return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, jsonData);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            logsEntity.setIntfType("PO_NOTICE_IN");//接口类型BASE_INTF_TYPE
            logsEntity.setTableName("srm_po_notice");
            logsEntity.setIntfStatus("E");
            logsEntity.setErrorMsg(e.getMessage());
            srmIntfLogsDAO_HI.save(logsEntity);
            return SToolUtils.convertResultJSONObj("E", "保存失败！" + e.getMessage(), 0, jsonData);
        }
    }

    /**
     * Description：校验送货通知头信息的必填项——接口（数据输入）
     *
     * @param noticeHeaderInfo
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private String judgeSpaceNoticeHeaderInfo(SrmPoNoticeEntity_HI noticeHeaderInfo) {
        String result = "";
        if (null == noticeHeaderInfo || "".equals(noticeHeaderInfo)) {
            result += "送货通知基础信息不可为空";
            return result;
        }
        if (null == noticeHeaderInfo.getPoNoticeCode() || "".equals(noticeHeaderInfo.getPoNoticeCode())) {
            result += "请填写送货通知编号——PoNoticeCode";
            return result;
        }
        if (null == noticeHeaderInfo.getOrgId() || "".equals(noticeHeaderInfo.getOrgId())) {
            result += "请填写业务实体ID——OrgId";
            return result;
        }
        if (null == noticeHeaderInfo.getBillToOrganizationId() || "".equals(noticeHeaderInfo.getBillToOrganizationId())) {
            result += "请填写收单组织ID——BillToOrganizationId";
            return result;
        }
        if (null == noticeHeaderInfo.getSupplierId() || "".equals(noticeHeaderInfo.getSupplierId())) {
            result += "请填写供应商ID——SupplierId";
            return result;
        }
        if (null == noticeHeaderInfo.getPoNoticeStatus() || "".equals(noticeHeaderInfo.getPoNoticeStatus())) {
            result += "请填写送货通知状态——PoNoticeStatus";
            return result;
        }
        return result;
    }


    /**
     * Description：校验送货通知行信息的必填项——接口（数据输入）
     *
     * @param noticeLineList
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private String judgeSpaceNoticeLineInfo(List<SrmPoNoticeLineEntity_HI> noticeLineList) {
        String result = "";
        if (null == noticeLineList || noticeLineList.size() <= 0) {
            return result;
        }
        Integer index = 0;
        for (SrmPoNoticeLineEntity_HI po : noticeLineList) {
            index++;
            if (null == po.getPoNoticeId() || "".equals(po.getPoNoticeId())) {
                result += "请填送货通知单头ID" + "第" + index + "行";
                return result;
            }
            if (null == po.getNoticeDeliveryDate() || "".equals(po.getNoticeDeliveryDate())) {
                result += "请填写送货通知日期" + "第" + index + "行";
                return result;
            }
            if (null == po.getNoticeDeliveryQty() || "".equals(po.getNoticeDeliveryQty())) {
                result += "请填写送货通知数量" + "第" + index + "行";
                return result;
            }
            if (null == po.getLineStatus() || "".equals(po.getLineStatus())) {
                result += "请填写行状态" + "第" + index + "行";
                return result;
            }
            if (null == po.getPoHeaderId() || "".equals(po.getPoHeaderId())) {
                result += "请填写采购订单号" + "第" + index + "行";
                return result;
            }
            if (null == po.getPoLineId() || "".equals(po.getPoLineId())) {
                result += "请填写采购订单行号" + "第" + index + "行";
                return result;
            }
        }
        return result;
    }
    /**
     * Description：送货通知接口（数据输出,用于外部访问的接口）
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public Map<String, Object> findNoticeInfoForExternal(JSONObject jsonParams) {
        //保存日志
        SrmIntfLogsEntity_HI logsEntity = new SrmIntfLogsEntity_HI();
        logsEntity.setIntfType("PO_NOTICE_OUT");//接口类型BASE_INTF_TYPE
        logsEntity.setTableName("srm_po_notice");
        logsEntity.setDataDirection("OUT");//数据方向(IN：输入， OUT：输出)
        logsEntity.setSendSystem("SRM");//数据发送方
        logsEntity.setInData(jsonParams.toJSONString());//输入报文
        logsEntity.setDescription("送货通知输出接口");
        logsEntity.setOperatorUserId(jsonParams.getInteger("varUserId"));
        Map<String, Object> map = new HashMap<String, Object>(); // 最终结果的返回
        try {
            List<SrmPoNoticeEntity_HI_RO> noticeHeaderList = findNoticeInfoForSupplierSelf(jsonParams);
            if (null == noticeHeaderList || noticeHeaderList.size() <= 0) {
                map.put("status", "E");
                map.put("msg", "查无此数据！");
                map.put("data", new ArrayList<>());
                map.put("count", 0);
                logsEntity.setIntfStatus("E");//查无数据状态为E，查有数据状态为S
                srmIntfLogsDAO_HI.save(logsEntity);
                return map;
            }

            List<Map<String, Object>> noticeHeaderInfoListMap = new ArrayList<>(); // 送货通知所有信息List
            for (SrmPoNoticeEntity_HI_RO ro : noticeHeaderList) {
                Map<String, Object> noticeHeaderAllList = new HashMap<>(); // 存放一个送货通知信息及其子表的所有信息
                JSONObject params = new JSONObject(); // 存放送货通知Id
                params.put("poNoticeId", ro.getPoNoticeId());

                // 送货通知头信息
                noticeHeaderAllList.put("noticeHeaderInfo", ro);
                // 送货通知行信息
                Pagination<SrmPoNoticeLineEntity_HI_RO> noticeLineListPagination = findNoticeLineBySupplierSelf(params, 1, 2000);
                List<SrmPoNoticeLineEntity_HI_RO> noticeLineList = noticeLineListPagination.getData();
                if (null != noticeLineList && noticeLineList.size() > 0) {
                    noticeHeaderAllList.put("noticeLineList", noticeLineList);
                }
                noticeHeaderInfoListMap.add(noticeHeaderAllList);
            }
            map.put("data", noticeHeaderInfoListMap);
            map.put("count", 1);
            map.put("msg", "查询成功");
            map.put("status", "S");
            logsEntity.setIntfStatus("S");//查无数据状态为E，查有数据状态为S
            srmIntfLogsDAO_HI.save(logsEntity);
            return map;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            map.put("status", "E");
            map.put("msg", e.getMessage());
            map.put("data", new ArrayList<>());
            map.put("count", 0);
            logsEntity.setIntfType("PO_NOTICE_OUT");//接口类型BASE_INTF_TYPE
            logsEntity.setTableName("srm_po_notice");
            logsEntity.setErrorMsg(e.getMessage());
            srmIntfLogsDAO_HI.save(logsEntity);
            return map;
        }
    }


    /**
     * Description：查询送货通知头项——接口（数据输出）
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private List<SrmPoNoticeEntity_HI_RO> findNoticeInfoForSupplierSelf(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer whereSql = new StringBuffer();
        //StringBuffer countSql = new StringBuffer(SrmPoNoticeEntity_HI_RO.QUERY_SRM_PO_NOTICE_LINE_COUNT);
        StringBuffer sqlBuffer = new StringBuffer(SrmPoNoticeEntity_HI_RO.QUERY_SRM_PO_NOTICE_LINE);
        SaafToolUtils.parperParam(jsonParams, " c.po_notice_code", "poNoticeCode", whereSql, map, "=");
        SaafToolUtils.parperParam(jsonParams, " c.org_id", "orgId", whereSql, map, "=");
        SaafToolUtils.parperParam(jsonParams, " c.bill_to_organization_id", "billToOrganizationId", whereSql, map, "=");
        SaafToolUtils.parperParam(jsonParams, " c.comments", "comments", whereSql, map, "like");
        SaafToolUtils.parperParam(jsonParams, " a.line_status", "lineStatus", whereSql, map, "=");
        SaafToolUtils.parperParam(jsonParams, " a.line_comments", "lineComments", whereSql, map, "like");
        SaafToolUtils.parperParam(jsonParams, " a.feedback_status", "feedbackStatus", whereSql, map, "=");
        // 物料类别
        SaafToolUtils.parperParam(jsonParams, " sbc.category_code", "categoryCode", whereSql, map, "=");
        SaafToolUtils.parperParam(jsonParams, " sbc.category_name", "categoryName", whereSql, map, "like");
        // 物料编码
        SaafToolUtils.parperParam(jsonParams, " d.item_code", "itemCode", whereSql, map, "=");
        SaafToolUtils.parperParam(jsonParams, " d.item_name", "itemName", whereSql, map, "like");
        // 供应商
        SaafToolUtils.parperParam(jsonParams, " spsi.supplier_id", "supplierId", whereSql, map, "=");
        SaafToolUtils.parperParam(jsonParams, " spsi.supplier_name", "supplierName", whereSql, map, "like");
        // 采购员
        SaafToolUtils.parperParam(jsonParams, " c.buyer_id", "employeeId", whereSql, map, "=");
        // 收货组织
        SaafToolUtils.parperParam(jsonParams, " b.receive_to_organization_id", "instId", whereSql, map, "=");
        // 送货通知状态
        SaafToolUtils.parperParam(jsonParams, " c.po_notice_status", "status", whereSql, map, "=");
        String startIssuedDate = jsonParams.getString("startIssuedDate");
        String endIssuedDate = jsonParams.getString("endIssuedDate");
        String startDeliveryDate = jsonParams.getString("startNoticeDeliveryDate");
        String endDeliveryDate = jsonParams.getString("endNoticeDeliveryDate");
        if (StringUtil.isNotEmptyStr(startIssuedDate) && StringUtil.isNotEmptyStr(endIssuedDate)) {
            whereSql.append(" and c.issued_date between :startIssuedDate and :endIssuedDate");
            map.put("startIssuedDate", startIssuedDate);
            map.put("endIssuedDate", endIssuedDate);
        } else if (StringUtil.isNotEmptyStr(startIssuedDate) && StringUtil.isEmpty(endIssuedDate)) {
            whereSql.append(" and c.issued_date >= :startIssuedDate");
            map.put("startIssuedDate", startIssuedDate);
        } else if (StringUtil.isNotEmptyStr(endIssuedDate) && StringUtil.isEmpty(startIssuedDate)) {
            whereSql.append(" and c.issued_date <= :endIssuedDate");
            map.put("endIssuedDate", endIssuedDate);
        }
        if (StringUtil.isNotEmptyStr(startDeliveryDate) && StringUtil.isNotEmptyStr(endDeliveryDate)) {
            whereSql.append(" and a.notice_delivery_date between :startDeliveryDate and :endDeliveryDate");
            map.put("startDeliveryDate", startDeliveryDate);
            map.put("endDeliveryDate", endDeliveryDate);
        } else if (StringUtil.isNotEmptyStr(startDeliveryDate) && StringUtil.isEmpty(endDeliveryDate)) {
            whereSql.append(" and a.notice_delivery_date >= :startDeliveryDate");
            map.put("startDeliveryDate", startDeliveryDate);
        } else if (StringUtil.isNotEmptyStr(endDeliveryDate) && StringUtil.isEmpty(startDeliveryDate)) {
            whereSql.append(" and a.notice_delivery_date <= :endDeliveryDate");
            map.put("endDeliveryDate", endDeliveryDate);
        }
        sqlBuffer.append(whereSql);
        sqlBuffer.append(" ORDER BY a.notice_delivery_date DESC");
        return srmPoNoticeDao_HI_RO.findList(sqlBuffer.toString(), map);
    }

    /**
     * Description：查询送货通知行项——接口（数据输出）
     *
     * @param jsonParams 参数
     * @param pageIndex 起始页
     * @param pageRow 行数
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private Pagination<SrmPoNoticeLineEntity_HI_RO> findNoticeLineBySupplierSelf(JSONObject jsonParams, Integer pageIndex, Integer pageRow) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryParamSql = new StringBuffer(SrmPoNoticeLineEntity_HI_RO.QUERY_SRMPONOTICElINE_SQL);
        SaafToolUtils.parperParam(jsonParams, "a.po_notice_id", "poNoticeId", queryParamSql, queryParamMap, "=");
        String countSql = "select count(1) from (" + queryParamSql + ")";
        return srmPoNoticeLineDao_HI_RO.findPagination(queryParamSql,countSql, queryParamMap, pageIndex, pageRow);
    }

    /**
     * Description：删除拟定的送货通知单
     * @param params 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteNoticeInfo(JSONObject params) throws Exception {
        LOGGER.info("删除参数：" + params.toString());
        if (null != params.getInteger("poNoticeId")) {
            SrmPoNoticeEntity_HI noticeRow = (SrmPoNoticeEntity_HI) srmPoNoticeDAO_HI.getById(params.getInteger("poNoticeId"));
            if ("DRAFT".equals(noticeRow.getPoNoticeStatus()) || "REJECTED".equals(noticeRow.getPoNoticeStatus())) {
                if (null != noticeRow) {
                    List<SrmPoNoticeLineEntity_HI> linesList = lineDAO_HI.findByProperty("poNoticeId", noticeRow.getPoNoticeId());
                    if (linesList != null && linesList.size() > 0) {
                        lineDAO_HI.delete(linesList);
                    }
                    srmPoNoticeDAO_HI.delete(noticeRow);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除送货通知失败,只能删除拟定和驳回的单据!", 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "删除送货通知失败，" + params.getString("poNoticeId") + "不存在", 0, null);
        }
        return SToolUtils.convertResultJSONObj("S", "删除送货通知成功", 1, null);
    }
}