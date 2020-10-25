package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.base.utils.DateUtil;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoDeliveryDetailsEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoDeliveryHeadersEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoDeliveryLinesEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLinesEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoNoticeLineEntity_HI_RO;
import saaf.common.fmw.po.model.inter.IDeliveryOrder;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

/**
 * Project Name：DeliveryOrderServer
 * Company Name：SIE
 * Program Name：
 * Description：送货单
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("deliveryOrderServer")
public class DeliveryOrderServer implements IDeliveryOrder {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryOrderServer.class);

    @Autowired
    private ViewObject<SrmPoDeliveryHeadersEntity_HI> srmPoDeliveryHeadersDAO_HI;

    @Autowired
    private ViewObject<SrmPoDeliveryLinesEntity_HI> deliveryLinesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPoLinesEntity_HI_RO> lineEntityDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPoDeliveryDetailsEntity_HI> deliveryDetailsDAO_HI;

    @Autowired
    private BaseViewObject<SrmPoNoticeLineEntity_HI_RO> srmPoNoticeLineDAO_HI_RO;

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> supplierInfoDAO_HI;


    /**
     * Description：匹配送货通知订单(送货单)
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
    public Pagination<SrmPoLinesEntity_HI_RO> findNoitceLineList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer sqlBuff = new StringBuffer(SrmPoLinesEntity_HI_RO.QUERY_NOTICE_SQL);
        StringBuffer countSql = new StringBuffer(SrmPoLinesEntity_HI_RO.QUERY_NOTICE_COUNT_SQL);
        StringBuffer whereSql = new StringBuffer();
        Map<String, Object> map = new HashMap<String, Object>();
        Integer supplierId = params.getInteger("varSupplierId");
        SaafToolUtils.parperParam(params, "si.inst_id", "instId", whereSql, map, "=");
        SaafToolUtils.parperParam(params, "n.document_type", "poDocType", whereSql, map, "=");
        SaafToolUtils.parperParam(params, "sbi.item_code", "itemCode", whereSql, map, "=");
        SaafToolUtils.parperParam(params, "sbc.CATEGORY_CODE", "categoryCode", whereSql, map, "=");
        SaafToolUtils.parperParam(params, "n.po_notice_code", "poNoticeCode", whereSql, map, "=");
        SaafToolUtils.parperParam(params, "l.SPECIAL_USE_NUM", "specialUseNum", whereSql, map, "=");
        SaafToolUtils.parperParam(params, "n.document_type", "documentType", whereSql, map, "=");
        SaafToolUtils.parperParam(params, "n.delivery_site_id", "deliverySiteId", whereSql, map, "=");
        if (supplierId != null) {
            SaafToolUtils.parperParam(params, "n.supplier_id", "varSupplierId", whereSql, map, "=");
        }
        String startDate = null;
        String endDate = null;
        if (params.getString("startDate") != null && !"".equals(params.getString("startDate").trim())) {
            startDate = params.getString("startDate");
        }
        if (params.getString("endDate") != null && !"".equals(params.getString("endDate").trim())) {
            endDate = params.getString("endDate");
        }
        if (startDate != null && endDate != null) {
            whereSql.append(" AND n.demand_date between :startDate and :endDate");
            map.put("startDate", startDate);
            map.put("endDate", endDate);
        } else if (startDate != null && endDate == null) {
            whereSql.append(" AND n.demand_date >= :startDate");
            map.put("startDate", startDate);
        } else if (endDate != null && startDate == null) {
            whereSql.append(" AND n.demand_date <= :endDate");
            map.put("endDate", endDate);
        }
        sqlBuff.append(whereSql);
        countSql.append(whereSql);
        sqlBuff.append(" ORDER BY n.demand_date, n.item_id ");
        return lineEntityDAO_HI_RO.findPagination(sqlBuff, countSql, map, pageIndex, pageRows);
    }

    /**
     * Description：匹配采购订单(送货单)
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
    public Pagination<SrmPoLinesEntity_HI_RO> findLinesList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info("params:" + JSONObject.toJSONString(params));
        StringBuffer sqlBuff = new StringBuffer(SrmPoLinesEntity_HI_RO.QUERY_LINES_SQL);
        StringBuffer countSql = new StringBuffer(SrmPoLinesEntity_HI_RO.QUERY_LINES_COUNT_SQL);
        StringBuffer whereSql = new StringBuffer();
        Map<String, Object> map = new HashMap<String, Object>();
        Integer supplierId = params.getInteger("varSupplierId");

        SaafToolUtils.parperParam(params, "sfi.inst_id", "instId", whereSql, map, "=");
        SaafToolUtils.parperParam(params, "h.PO_DOC_TYPE", "poDocType", whereSql, map, "=");
        SaafToolUtils.parperParam(params, "sbi.item_code", "itemCode", whereSql, map, "=");
        SaafToolUtils.parperParam(params, "sbc.CATEGORY_CODE", "categoryCode", whereSql, map, "=");
        SaafToolUtils.parperParam(params, "h.po_number", "poNumber", whereSql, map, "=");
        SaafToolUtils.parperParam(params, "l.SPECIAL_USE_NUM", "specialUseNum", whereSql, map, "like");
        SaafToolUtils.parperParam(params, "l.demand_classify", "demandClassify", whereSql, map, "like");
        SaafToolUtils.parperParam(params, "h.PO_DOC_TYPE", "documentType", whereSql, map, "=");

        if (supplierId != null) {
            SaafToolUtils.parperParam(params, "h.supplier_id", "varSupplierId", whereSql, map, "=");
        }
        String startDate = null;
        String endDate = null;
        if (params.getString("startDate") != null && !"".equals(params.getString("startDate").trim())) {
            startDate = params.getString("startDate");
        }
        if (params.getString("endDate") != null && !"".equals(params.getString("endDate").trim())) {
            endDate = params.getString("endDate");
        }
        if (startDate != null && endDate != null) {
            whereSql.append(" and l.demand_date between :startDate and :endDate");
            map.put("startDate", startDate);
            map.put("endDate", endDate);
        } else if (startDate != null && endDate == null) {
            whereSql.append(" and l.demand_date >= :startDate");
            map.put("startDate", startDate);
        } else if (endDate != null && startDate == null) {
            whereSql.append(" and l.demand_date <= :endDate");
            map.put("endDate", endDate);
        }
        sqlBuff.append(whereSql);
        countSql.append(whereSql);
        sqlBuff.append(" ORDER BY l.demand_date, l.item_id");
        return lineEntityDAO_HI_RO.findPagination(sqlBuff, countSql, map, pageIndex, pageRows);
    }

    /**
     * Description：查询
     * @param type
     * @param id
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    public BigDecimal getCanCreateDelivery(String type, Integer id) {
        try {
            if ("poNotice".equals(type)) {
                StringBuffer sqlBuff = new StringBuffer(SrmPoLinesEntity_HI_RO.QUERY_NOTICE_CAN_DELIVER);
                sqlBuff.append("  and  t.po_notice_id = " + id);
                return lineEntityDAO_HI_RO.findList(sqlBuff).get(0).getDeliverableQty();
            } else if ("poLines".equals(type)) {
                StringBuffer sqlBuff = new StringBuffer(SrmPoLinesEntity_HI_RO.QUERY_PO_CAN_DELIVERY);
                sqlBuff.append("  and  t.po_line_id = " + id);
                return lineEntityDAO_HI_RO.findList(sqlBuff).get(0).getDeliverableQty();
            }
        } catch (Exception e) {
        }
        return new BigDecimal(0);
    }

    /**
     * Description：创建送货单
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * syncResult  状态，默认0（新建）,1（成功），2（失败）  NUMBER  N
     * errorMsg  失败原因  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * temperature  温度  VARCHAR2  N
     * humidity  湿度  VARCHAR2  N
     * deliveryAddress  送货地址  VARCHAR2  N
     * deliveryHeaderId  送货单ID  NUMBER  Y
     * deliveryNumber  送货单编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * receiveToOrganizationId  收货组织ID  NUMBER  N
     * status  送货单状态(POS_DELIVERY_STATUS)  VARCHAR2  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * deliveryDate  送货日期  DATE  N
     * transportNumber  （废弃）运单号  VARCHAR2  N
     * instId  （废弃）分厂id  NUMBER  N
     * receiveBy  （废弃）接收人id  NUMBER  N
     * receiveFlag  （废弃）是否已接收  VARCHAR2  N
     * documentType  （废弃）单据类型  VARCHAR2  N
     * isPassU9  （废弃）是否传U9(N：不需要 Y：需要 R：需要重传 S：成功)  VARCHAR2  N
     * u9DocNumber  （废弃）U9接收单号  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * deliveryHeaderId  送货单ID  NUMBER  Y
     * deliveryNumber  送货单编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * receiveToOrganizationId  收货组织ID  NUMBER  N
     * status  送货单状态(POS_DELIVERY_STATUS)  VARCHAR2  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * deliveryDate  送货日期  DATE  N
     * transportNumber  （废弃）运单号  VARCHAR2  N
     * instId  （废弃）分厂id  NUMBER  N
     * receiveBy  （废弃）接收人id  NUMBER  N
     * receiveFlag  （废弃）是否已接收  VARCHAR2  N
     * documentType  （废弃）单据类型  VARCHAR2  N
     * isPassU9  （废弃）是否传U9(N：不需要 Y：需要 R：需要重传 S：成功)  VARCHAR2  N
     * u9DocNumber  （废弃）U9接收单号  VARCHAR2  N
     * description  说明  VARCHAR2  N
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
    public JSONObject saveDeliveryOrder(JSONObject params) throws Exception {
        LOGGER.info(params.toJSONString());
        SrmPoDeliveryHeadersEntity_HI headersEntity_hi = null;
        SrmPoDeliveryLinesEntity_HI linesEntity_hi = null;
        Integer userId = params.getInteger("varUserId");
        Integer supplierId = params.getInteger("varSupplierId");
        if (supplierId == null) {
            return com.yhg.base.utils.SToolUtils.convertResultJSONObj("E", "供应商不存在", 0, null);
        }
        JSONArray jsonArray = params.getJSONArray("arrayData");
        if(null == jsonArray || jsonArray.isEmpty()){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        headersEntity_hi = new SrmPoDeliveryHeadersEntity_HI();
        // 生成送货单号
        String deliverNumber = saafSequencesUtil.getDocSequences("srm_po_delivery_headers".toUpperCase(),
                "SH" + DateUtil.date2Str(new Date(), "yyMMdd"), 5);
        headersEntity_hi.setDeliveryNumber(deliverNumber);
        // 需求时间
        headersEntity_hi.setDeliveryDate(new Date());
        // 供应商
        headersEntity_hi.setSupplierId(supplierId);
        headersEntity_hi.setCreationDate(new Date());
        headersEntity_hi.setCreatedBy(userId);
        headersEntity_hi.setLastUpdateDate(new Date());
        headersEntity_hi.setCreatedBy(userId);
        headersEntity_hi.setOperatorUserId(userId);
        Integer instId = 0;
        for (int a = 0, b = jsonArray.size(); a < b; a++) {
            JSONObject obj = jsonArray.getJSONObject(a);
            instId = obj.getInteger("instId");
        }
        LOGGER.info("instId：" + instId);
        headersEntity_hi.setOrgId(instId);//业务实体Id
        headersEntity_hi.setBillToOrganizationId(instId);//收单组织Id
        headersEntity_hi.setReceiveToOrganizationId(instId);//收货组织Id
        headersEntity_hi.setStatus("OPEN");//状态为“打开”
        headersEntity_hi.setReturnGoodsType("BASE_ON_PO");//状态为“采购订单回货”
        // 保存头
        srmPoDeliveryHeadersDAO_HI.save(headersEntity_hi);
        // 获取行data
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            linesEntity_hi = new SrmPoDeliveryLinesEntity_HI();
            // 获取到一行
            JSONObject obj = jsonArray.getJSONObject(i);
            linesEntity_hi.setDeliveryHeaderId(headersEntity_hi.getDeliveryHeaderId());//头表id
            linesEntity_hi.setItemId(obj.getInteger("itemId"));//物料Id
            linesEntity_hi.setCategoryId(obj.getInteger("categoryId"));//分类Id
            linesEntity_hi.setPoLineId(obj.getInteger("poLineId"));//采购订单行ID
            linesEntity_hi.setPoNoticeLineId(obj.getInteger("poNoticeLineId"));//送货通知行ID

            BigDecimal deliveryQty = obj.getBigDecimal("onWayQty");//本次送货量deliveryQty
            //BigDecimal receivedQty = obj.getBigDecimal("receivedQty");//接收数量
            linesEntity_hi.setDeliveryQty(deliveryQty);
            //linesEntity_hi.setReceivedQty(receivedQty);
            linesEntity_hi.setCreationDate(new Date());
            linesEntity_hi.setCreatedBy(userId);
            linesEntity_hi.setLastUpdateDate(new Date());
            linesEntity_hi.setCreatedBy(userId);
            linesEntity_hi.setOperatorUserId(userId);
            //保存行表
            deliveryLinesDAO_HI.save(linesEntity_hi);
        }
        return SToolUtils.convertResultJSONObj("S", "保存成功", jsonArray.size(), headersEntity_hi);
    }
}
