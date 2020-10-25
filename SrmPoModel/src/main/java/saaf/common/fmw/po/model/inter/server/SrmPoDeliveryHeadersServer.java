package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoDeliveryHeadersEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoDeliveryLinesEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoLinesEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoNoticeLineEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoDeliveryHeadersEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLinesEntity_HI_RO;
import saaf.common.fmw.po.model.inter.IDeliveryHeaders;

import java.math.BigDecimal;
import java.util.*;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

/**
 * Project Name：SrmPoDeliveryHeadersServer
 * Company Name：SIE
 * Program Name：
 * Description：送货单列表
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoDeliveryHeadersServer")
public class SrmPoDeliveryHeadersServer implements IDeliveryHeaders {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoDeliveryHeadersServer.class);

    @Autowired
    private BaseViewObject<SrmPoDeliveryHeadersEntity_HI_RO> srmPoDeliveryHeadersDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPoDeliveryHeadersEntity_HI> srmPoDeliveryHeadersDAO_HI;

    @Autowired
    private BaseViewObject<SrmPoLinesEntity_HI_RO> lineEntityDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPoDeliveryLinesEntity_HI> srmPoDeliveryLinesDAO_HI;

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ViewObject<SrmPoLinesEntity_HI> srmPoLinesDAO_HI;

    @Autowired
    private ViewObject<SrmPoNoticeLineEntity_HI> srmPoNoticeLineDAO_HI;

    /**
     * Description：查询送货单列表-订单回货
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
    public Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryHeadersList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer queryString = new StringBuffer(SrmPoDeliveryHeadersEntity_HI_RO.QUERY_DELIVERY_PO_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询过滤条件
        SaafToolUtils.parperParam(params, "ph.po_Number", "poNumber", queryString, map, "like");
        SaafToolUtils.parperParam(params, "ph.org_id", "orgId", queryString, map, "=");
        SaafToolUtils.parperParam(params, "pl.receive_to_organization_id", "receiveToOrganizationId", queryString, map, "=");
        SaafToolUtils.parperParam(params, "DATE_FORMAT(ph.approved_date,'%Y-%m-%d')", "approvedDateStartDate", queryString, map, ">=");
        SaafToolUtils.parperParam(params, "DATE_FORMAT(ph.approved_date,'%Y-%m-%d')", "approvedDateEndDate", queryString, map, "<=");
        SaafToolUtils.parperParam(params, "se.employee_Name", "employeeName", queryString, map, "like");
        SaafToolUtils.parperParam(params, "bc.category_Name", "categoryName", queryString, map, "like");
        SaafToolUtils.parperParam(params, "bi.item_Name", "itemName", queryString, map, "like");
        SaafToolUtils.parperParam(params, "ph.description", "descriptionPh", queryString, map, "like");
        SaafToolUtils.parperParam(params, "DATE_FORMAT(pl.demand_date,'%Y-%m-%d')", "demandDateStartDate", queryString, map, ">=");
        SaafToolUtils.parperParam(params, "DATE_FORMAT(pl.demand_date,'%Y-%m-%d')", "demandDateEndDate", queryString, map, "<=");
        SaafToolUtils.parperParam(params, "pl.description", "descriptionPl", queryString, map, "like");
        map.put("supplierId", params.getInteger("varSupplierId"));
        queryString.append(" AND ph.supplier_Id = :supplierId");
        queryString.append(" AND ph.status = 'APPROVED'");//采购订单状态为“已批准”
        queryString.append(" AND ph.return_goods_type = 'BASE_ON_PO'");//采购订单回货类型为“送货订单回货”
        queryString.append(" AND pl.status = 'OPEN'");//采购订单行状态为“打开”
        queryString.append(" AND pl.may_notice_qty > 0");//采购订单行可送货量大于0
        queryString.append(" AND pl.feedback_status IN ('CONFIRMED', 'APPROVED')");//采购订单行反馈状态为“已确认，反馈审核通过”
        queryString.append(" AND ins.inst_type = 'ORG'");//类型为“org”
        //总行数
        StringBuffer countSb = new StringBuffer("select count(1) from ("+queryString+")");
        // 排序
        queryString.append(" ORDER BY ph.last_update_date DESC");
        Pagination<SrmPoDeliveryHeadersEntity_HI_RO> deliveryList = srmPoDeliveryHeadersDAO_HI_RO.findPagination(queryString,countSb, map, pageIndex, pageRows);
        return deliveryList;
    }

    /**
     * Description：查询送货单列表-通知回货
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
    public Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryHeadersNoticeList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer queryString = new StringBuffer(SrmPoDeliveryHeadersEntity_HI_RO.QUERY_DELIVERY_NOTICE_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询过滤条件
        SaafToolUtils.parperParam(params, "pn.po_notice_code", "poNoticeCode", queryString, map, "like");
        SaafToolUtils.parperParam(params, "ph.org_id", "orgId", queryString, map, "=");
        SaafToolUtils.parperParam(params, "pl.receive_to_organization_id", "receiveToOrganizationId", queryString, map, "=");
        SaafToolUtils.parperParam(params, "DATE_FORMAT(pn.issued_date,'%Y-%m-%d')", "issuedDateStartDate", queryString, map, ">=");
        SaafToolUtils.parperParam(params, "DATE_FORMAT(pn.issued_date,'%Y-%m-%d')", "issuedDateEndDate", queryString, map, "<=");
        SaafToolUtils.parperParam(params, "se.employee_Name", "employeeName", queryString, map, "like");
        SaafToolUtils.parperParam(params, "bc.category_Name", "categoryName", queryString, map, "like");
        SaafToolUtils.parperParam(params, "bi.item_Name", "itemName", queryString, map, "like");
        SaafToolUtils.parperParam(params, "pn.comments", "commentsPn", queryString, map, "like");
        SaafToolUtils.parperParam(params, "DATE_FORMAT(pl.demand_date,'%Y-%m-%d')", "demandDateStartDate", queryString, map, ">=");
        SaafToolUtils.parperParam(params, "DATE_FORMAT(pl.demand_date,'%Y-%m-%d')", "demandDateEndDate", queryString, map, "<=");
        SaafToolUtils.parperParam(params, "pnl.line_comments", "lineComments", queryString, map, "like");
        map.put("supplierId", params.getInteger("varSupplierId"));
        queryString.append(" AND pn.supplier_Id = :supplierId");
        queryString.append(" AND pn.po_notice_status = 'APPROVED'");//采购订单状态为“已批准”
        queryString.append(" AND ph.return_goods_type = 'BASE_ON_NOTICE'");//采购订单回货类型为“送货通知回货”
        queryString.append(" AND pnl.line_status = 'OPEN'");//送货通知行状态为“打开”
        queryString.append(" AND pnl.feedback_status IN ('CONFIRMED', 'APPROVED')");//送货通知行反馈状态为“已确认，反馈审核通过”
        //queryString.append(" AND ins.inst_type='ORG'");//类型为“org”
        //总行数
        StringBuffer countSb = new StringBuffer("select count(1) from ("+queryString+")");
        // 排序
        queryString.append(" ORDER BY pn.last_update_date DESC");
        Pagination<SrmPoDeliveryHeadersEntity_HI_RO> deliveryList = srmPoDeliveryHeadersDAO_HI_RO.findPagination(queryString,countSb, map, pageIndex, pageRows);
        return deliveryList;
    }

    /**
     * Description：送货单查询-内部
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
    public Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info("查询参数:=====findDeliveryList=====>>>" + params + "\t" + pageIndex + "\t" + pageRows);
        StringBuffer queryString = new StringBuffer(SrmPoDeliveryHeadersEntity_HI_RO.QUERY_DELIVERY_SQL);
        Map<String, Object> map = new HashMap();
        // 查询过滤条件
        SaafToolUtils.parperParam(params, "dh.delivery_Number", "deliveryNumber", queryString, map, "LIKE");
        SaafToolUtils.parperParam(params, "dh.receive_to_organization_id", "receiveToOrganizationId", queryString, map, "=");
        SaafToolUtils.parperParam(params, "dh.status", "status", queryString, map, "=");
        String creationDateFrom = params.getString("creationDateFrom");
        if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
            queryString.append(" AND trunc(dh.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
            map.put("creationDateFrom", creationDateFrom);
        }
        String creationDateTo = params.getString("creationDateTo");
        if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
            queryString.append(" AND trunc(dh.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
            map.put("creationDateTo", creationDateTo);
        }
        SaafToolUtils.parperParam(params, "dh.return_Goods_Type", "returnGoodsType", queryString, map, "=");
        SaafToolUtils.parperParam(params, "bc.category_Name", "categoryName", queryString, map, "LIKE");
        SaafToolUtils.parperParam(params, "bi.item_Name", "itemName", queryString, map, "LIKE");
        SaafToolUtils.parperParam(params, "ph.po_Number", "poNumber", queryString, map, "LIKE");
        String deliveryDateStartDate = params.getString("deliveryDateStartDate");
        if (deliveryDateStartDate != null && !"".equals(deliveryDateStartDate.trim())) {
            queryString.append(" AND trunc(dh.delivery_date) >= to_date(:deliveryDateStartDate, 'yyyy-mm-dd')\n");
            map.put("deliveryDateStartDate", deliveryDateStartDate);
        }
        String deliveryDateEndDate = params.getString("deliveryDateEndDate");
        if (deliveryDateEndDate != null && !"".equals(deliveryDateEndDate.trim())) {
            queryString.append(" AND trunc(dh.delivery_date) <= to_date(:deliveryDateEndDate, 'yyyy-mm-dd')\n");
            map.put("deliveryDateEndDate", deliveryDateEndDate);
        }
        SaafToolUtils.parperParam(params, "pn.po_notice_code", "poNoticeCode", queryString, map, "LIKE");
        SaafToolUtils.parperParam(params, "si.supplier_name", "supplierName", queryString, map, "LIKE");
        //只能查看当前账号已分配的业务实体相关的数据
        queryString.append(" AND check_org_f(" + params.getInteger("varUserId") + ", dh.org_id) = 'Y'\n");
        //总行数
        StringBuffer countSb = new StringBuffer("select count(1) from ("+queryString+")");
        // 排序
        queryString.append(" ORDER BY dh.last_update_date DESC");
        Pagination<SrmPoDeliveryHeadersEntity_HI_RO> deliveryList = srmPoDeliveryHeadersDAO_HI_RO.findPagination(queryString,countSb, map, pageIndex, pageRows);
        return deliveryList;
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
        LOGGER.info(">>>>>>saveDeliveryOrder>>>>>：" + params.toJSONString());
        SrmPoDeliveryHeadersEntity_HI headersEntity_hi = null;
        SrmPoDeliveryLinesEntity_HI linesEntity_hi = null;
        SrmPoLinesEntity_HI poLinesEntity_hi = null;
        SrmPoNoticeLineEntity_HI poNoticeLineEntity_hi = null;
        JSONObject jsonData = new JSONObject();
        Integer userId = params.getInteger("varUserId");
        Integer supplierId = params.getInteger("varSupplierId");
        if (supplierId == null) {
            return SToolUtils.convertResultJSONObj("E", "供应商不存在", 0, null);
        }
        Integer poLineId = 0;//采购订单行id
        Integer lineId = 0;//送货通知行id
        Integer deliveryHeaderId = 0;//送货单id
        List<SrmPoDeliveryHeadersEntity_HI> deliveryHeadersList = new ArrayList<SrmPoDeliveryHeadersEntity_HI>();
        List<SrmPoDeliveryLinesEntity_HI> deliveryLinesList = new ArrayList<SrmPoDeliveryLinesEntity_HI>();
        List<SrmPoLinesEntity_HI> poLinesList = new ArrayList<SrmPoLinesEntity_HI>();
        List<SrmPoNoticeLineEntity_HI> poNoticeLineList = new ArrayList<SrmPoNoticeLineEntity_HI>();
        headersEntity_hi = new SrmPoDeliveryHeadersEntity_HI();
        JSONArray jsonArray = params.getJSONArray("arrayData");//获取列表数组data
        if(null == jsonArray || jsonArray.isEmpty()){
            SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        String deliverNumber = "";
        for (int a = 0, b = jsonArray.size(); a < b; a++) {
            JSONObject obj = jsonArray.getJSONObject(a);//获取一行数据
            headersEntity_hi.setOrgId(obj.getInteger("instId"));//业务实体Id
            headersEntity_hi.setBillToOrganizationId(obj.getInteger("billToOrganizationId"));//收单组织Id
            headersEntity_hi.setReceiveToOrganizationId(obj.getInteger("receiveToOrganizationId"));//收货组织Id
            headersEntity_hi.setStatus("OPEN");//状态为“打开”
            BigDecimal deliveryQty = obj.getBigDecimal("deliveryQty");//本次送货量
            poLineId = obj.getInteger("poLineId");//采购订单行id
            if ((null != poLineId)) {
                poLinesEntity_hi = new SrmPoLinesEntity_HI();
                poLinesEntity_hi = (SrmPoLinesEntity_HI) srmPoLinesDAO_HI.getById(poLineId);
                poLinesEntity_hi.setLastUpdatedBy(userId);
                poLinesEntity_hi.setLastUpdateDate(new Date());
                poLinesEntity_hi.setOperatorUserId(userId);
            } else {
                return com.yhg.base.utils.SToolUtils.convertResultJSONObj("E", "采购订单行ID不存在", 0, null);
            }
            String action = params.getString("action");//操作标识“po，notice”
            if ("po".equals(action)) {//订单回货
                headersEntity_hi.setReturnGoodsType("BASE_ON_PO");//状态为“采购订单回货”
                //操作采购订单行表
                if (deliveryQty != null) {
                    if (poLinesEntity_hi.getOnWayQty() != null) {//判断在途数量是否为空
                        BigDecimal deliveryQtyPl = deliveryQty.add(poLinesEntity_hi.getOnWayQty());//本次送货量+在途数量
                        poLinesEntity_hi.setOnWayQty(deliveryQtyPl);//在途数量
                    } else {
                        //为空情况下直接set本次送货量
                        poLinesEntity_hi.setOnWayQty(deliveryQty);//在途数量
                    }
                    BigDecimal mayNoticeQty = poLinesEntity_hi.getMayNoticeQty();//采购订单行-可送货量
                    BigDecimal mayNoticeQtyPl = mayNoticeQty.subtract(deliveryQty);
                    poLinesEntity_hi.setMayNoticeQty(mayNoticeQtyPl);//可送货量
                }
                poLinesList.add(poLinesEntity_hi);
            } else if ("notice".equals(action)) {//通知回货
                headersEntity_hi.setReturnGoodsType("BASE_ON_NOTICE");//状态为“采购通知回货”
                //操作送货通知行表
                lineId = obj.getInteger("lineId");//送货通知行Id
                if ((null != lineId) || (0 == lineId)) {
                    poNoticeLineEntity_hi = new SrmPoNoticeLineEntity_HI();
                    poNoticeLineEntity_hi = (SrmPoNoticeLineEntity_HI) srmPoNoticeLineDAO_HI.getById(lineId);
                } else {
                    return com.yhg.base.utils.SToolUtils.convertResultJSONObj("E", "送货通知回货行ID不存在", 0, null);
                }
                if (deliveryQty != null) {
                    if (poNoticeLineEntity_hi.getOnWayQty() != null) {//判断在途数量是否为空
                        BigDecimal deliveryQtyPnl = deliveryQty.add(poNoticeLineEntity_hi.getOnWayQty());//本次送货量+在途数量
                        poNoticeLineEntity_hi.setOnWayQty(deliveryQtyPnl);//在途数量
                    } else {
                        //为空情况下直接set本次送货量
                        poNoticeLineEntity_hi.setOnWayQty(deliveryQty);//在途数量
                    }
                    BigDecimal mayNoticeQty = poLinesEntity_hi.getMayNoticeQty();//采购订单行-可送货量
                    BigDecimal mayNoticeQtyPl = mayNoticeQty.subtract(deliveryQty);//可送货量= 可送货量-本次送货量
                    poLinesEntity_hi.setMayNoticeQty(mayNoticeQtyPl);//回写-采购订单可送货量
						/* 本次通知送货量不需要更新
						if (poNoticeLineEntity_hi.getNoticeDeliveryQty()!=null) {//判断通知送货数量是否为空
							BigDecimal noticeDeliveryQty = poNoticeLineEntity_hi.getNoticeDeliveryQty().subtract(deliveryQty);//通知送货数量-本次送货量
							poNoticeLineEntity_hi.setNoticeDeliveryQty(noticeDeliveryQty);
						}*/
                }
                poNoticeLineEntity_hi.setLastUpdatedBy(userId);
                poNoticeLineEntity_hi.setLastUpdateDate(new Date());
                poNoticeLineEntity_hi.setOperatorUserId(userId);
                poLinesList.add(poLinesEntity_hi);//采购订单行表修改-可送货量
                poNoticeLineList.add(poNoticeLineEntity_hi);
                //修改送货通知回货行表(在途数量)
                srmPoNoticeLineDAO_HI.saveOrUpdateAll(poNoticeLineList);
            }
            headersEntity_hi.setDeliveryDate(new Date());// 需求时间
            headersEntity_hi.setSupplierId(supplierId);// 供应商

            linesEntity_hi = new SrmPoDeliveryLinesEntity_HI();
            linesEntity_hi.setDeliveryLineStatus("OPEN");//行状态"打开"
            linesEntity_hi.setDeliveryQty(obj.getBigDecimal("deliveryQty"));//本次送货数量
            linesEntity_hi.setReceivedQty(obj.getBigDecimal("receivedQty"));//接收数量
            linesEntity_hi.setItemId(obj.getInteger("itemId"));//物料Id
            linesEntity_hi.setCategoryId(obj.getInteger("categoryId"));//分类Id
            if ("".equals(deliverNumber)) {
                // 生成送货单号
                deliverNumber = saafSequencesUtil.getDocSequences("srm_po_delivery_headers".toUpperCase(), "SH" + DateUtil.date2Str(new Date(), "yyMMdd"), 5);
            }
            headersEntity_hi.setDeliveryNumber(deliverNumber);
            headersEntity_hi.setCreatedBy(userId);
            headersEntity_hi.setCreationDate(new Date());
            headersEntity_hi.setLastUpdateDate(new Date());
            headersEntity_hi.setLastUpdatedBy(userId);
            headersEntity_hi.setOperatorUserId(userId);
            deliveryHeadersList.add(headersEntity_hi);
            //保存送货单头表
            srmPoDeliveryHeadersDAO_HI.saveOrUpdateAll(deliveryHeadersList);

            linesEntity_hi.setPoLineId(poLineId);//采购订单行ID
            linesEntity_hi.setPoNoticeLineId(lineId);//送货通知行ID
            //linesEntity_hi.setReceivedDate(new Date());//接收日期
            linesEntity_hi.setCreationDate(new Date());
            linesEntity_hi.setCreatedBy(userId);
            linesEntity_hi.setLastUpdatedBy(userId);
            linesEntity_hi.setLastUpdateDate(new Date());
            linesEntity_hi.setOperatorUserId(userId);
            linesEntity_hi.setDeliveryHeaderId(headersEntity_hi.getDeliveryHeaderId());//送货单头表Id
            deliveryLinesList.add(linesEntity_hi);
        }
        //保存送货单行表
        srmPoDeliveryLinesDAO_HI.saveOrUpdateAll(deliveryLinesList);
        //修改采购订单行表
        srmPoLinesDAO_HI.saveOrUpdateAll(poLinesList);
        //保存后返回送货单Id
        deliveryHeaderId = headersEntity_hi.getDeliveryHeaderId();
        jsonData.put("deliveryHeaderId", deliveryHeaderId);
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, jsonData);
    }

    /**
     * Description：送货单-内部-确认/拒绝
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveDeliveryAffReg(JSONObject params) throws Exception {
        LOGGER.info("》》》》》》》》》》》》》确认/拒绝信息，参数：" + params.toString());
        Integer userId = params.getInteger("varUserId");  //用户Id
        String action = params.getString("action");  //操作按钮
        SrmPoDeliveryLinesEntity_HI linesEntity = null;
        SrmPoLinesEntity_HI poLinesEntity_hi = null;
        SrmPoNoticeLineEntity_HI poNoticeLineEntity_hi = null;
        List<SrmPoDeliveryLinesEntity_HI> deliveryLinesList = new ArrayList<SrmPoDeliveryLinesEntity_HI>();
        JSONArray jsonArray = params.getJSONArray("arrayData");
        if (null == action || "".equals(action)) {
            return SToolUtils.convertResultJSONObj("E", "操作字符为空", 0, null);
        }
        if(null == jsonArray || jsonArray.isEmpty()){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        Integer deliveryHeaderId = 0;//送货单id
        Integer deliveryLinesId = 0;//送货单行id
        Integer poLineId = 0;//采购订单行id
        Integer lineId = 0;//送货通知行Id
        List<SrmPoLinesEntity_HI> poLinesList = new ArrayList<SrmPoLinesEntity_HI>();
        List<SrmPoNoticeLineEntity_HI> poNoticeLineList = new ArrayList<SrmPoNoticeLineEntity_HI>();
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            linesEntity = new SrmPoDeliveryLinesEntity_HI();
            JSONObject obj = jsonArray.getJSONObject(i);// 获取到一行
            deliveryLinesId = obj.getInteger("deliveryLineId");//送货单行Id
            deliveryHeaderId = obj.getInteger("deliveryHeaderId");//送货单头表
            poLineId = obj.getInteger("poLineId");//采购订单行Id
            if (deliveryLinesId != null && deliveryLinesId != 0) {
                linesEntity = srmPoDeliveryLinesDAO_HI.getById(deliveryLinesId);//查询送货单行Id
            }
            linesEntity.setLastUpdateDate(new Date());
            linesEntity.setLastUpdatedBy(userId);
            linesEntity.setOperatorUserId(userId);
            if ("Affirm".equals(action)) {//确认    将本次的送货量释放掉，退回到可送货量
                //操作采购订单行表
                if ((null != poLineId) || (0 != poLineId)) {
                    poLinesEntity_hi = new SrmPoLinesEntity_HI();
                    poLinesEntity_hi = (SrmPoLinesEntity_HI) srmPoLinesDAO_HI.getById(poLineId);//根据采购订单行id查询
                    poLinesEntity_hi.setLastUpdatedBy(userId);
                    poLinesEntity_hi.setLastUpdateDate(new Date());
                    poLinesEntity_hi.setOperatorUserId(userId);
                } else {
                    return com.yhg.base.utils.SToolUtils.convertResultJSONObj("E", "采购订单行ID不存在", 0, null);
                }
                lineId = linesEntity.getPoNoticeLineId();
                if ((null != lineId) && (0 != lineId)) {//送货通知回货
                    poNoticeLineEntity_hi = new SrmPoNoticeLineEntity_HI();
                    poNoticeLineEntity_hi = (SrmPoNoticeLineEntity_HI) srmPoNoticeLineDAO_HI.getById(lineId);//根据通知回货id查询
                    BigDecimal onWayQtyPnl = poNoticeLineEntity_hi.getOnWayQty();//在途数量
                    BigDecimal deliveryQtyPnl = linesEntity.getDeliveryQty();//本次送货量
                    if ((null != deliveryQtyPnl)) {
                        BigDecimal onWayQtyPnl2 = onWayQtyPnl.subtract(deliveryQtyPnl);//在途数量=在途数量-本次送货量
                        BigDecimal mayNoticeQtyPnl = deliveryQtyPnl.add(poLinesEntity_hi.getMayNoticeQty());//需要加回到  可送货量= 本次送货量+原可送货量
                        poNoticeLineEntity_hi.setOnWayQty(onWayQtyPnl2);//此次最终在途数量
                        poLinesEntity_hi.setMayNoticeQty(mayNoticeQtyPnl);//可送货量
                    }
                    poNoticeLineEntity_hi.setLastUpdatedBy(userId);
                    poNoticeLineEntity_hi.setLastUpdateDate(new Date());
                    poNoticeLineEntity_hi.setOperatorUserId(userId);
                    poLinesList.add(poLinesEntity_hi);//采购订单行-add修改过的可送货量
                    poNoticeLineList.add(poNoticeLineEntity_hi);
                    srmPoNoticeLineDAO_HI.saveOrUpdateAll(poNoticeLineList);//修改(在途数量)
                } else {//采购订单回货
                    BigDecimal onWayQtyPl = poLinesEntity_hi.getOnWayQty();//在途数量
                    BigDecimal deliveryQtyPl = linesEntity.getDeliveryQty();//本次送货量
                    if (deliveryQtyPl != null) {
                        BigDecimal onWayQtyPl2 = onWayQtyPl.subtract(deliveryQtyPl);//在途数量=在途数量-本次送货量
                        BigDecimal mayNoticeQtyPl = deliveryQtyPl.add(poLinesEntity_hi.getMayNoticeQty());//需要加回到  可送货量= 本次送货量+原可送货量
                        poLinesEntity_hi.setOnWayQty(onWayQtyPl2);//此次最终在途数量
                        poLinesEntity_hi.setMayNoticeQty(mayNoticeQtyPl);//可送货量
                    }
                    poLinesList.add(poLinesEntity_hi);
                }
                linesEntity.setDeliveryLineStatus("CANCELLED");//行状态为"取消"
                linesEntity.setDeliveryQty(new BigDecimal(0));//将本次送货量制空
            } else {//拒绝
                linesEntity.setDeliveryLineStatus("OPEN");//行状态为"打开"
            }
            deliveryLinesList.add(linesEntity);
        }
        srmPoDeliveryLinesDAO_HI.saveOrUpdateAll(deliveryLinesList);//保存送货单行表
        srmPoLinesDAO_HI.saveOrUpdateAll(poLinesList);//保存采购订单行表
        return SToolUtils.convertResultJSONObj("S", "操作成功", 1, null);
    }

    /**
     * Description：送货通知查询-供应商/外部
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
    public Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryExternalList(
            JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info("查询参数:=====findDeliveryExternalList=====>>>" + params);
        StringBuffer queryString = new StringBuffer(SrmPoDeliveryHeadersEntity_HI_RO.QUERY_DELIVERY_SUPPLIER_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询过滤条件
        SaafToolUtils.parperParam(params, "pn.po_notice_code", "poNoticeCode", queryString, map, "like");
        SaafToolUtils.parperParam(params, "dh.receive_to_organization_id", "receiveToOrganizationId", queryString, map, "=");
        SaafToolUtils.parperParam(params, "dh.delivery_Number", "deliveryNumber", queryString, map, "like");
        SaafToolUtils.parperParam(params, "dh.status", "deliveryStatus", queryString, map, "=");
        SaafToolUtils.parperParam(params, "dh.document_type", "documentType", queryString, map, "=");
        SaafToolUtils.parperParam(params, "DATE_FORMAT(dh.CREATION_DATE,'%Y-%m-%d')", "creationDateTo", queryString, map, ">=");
        SaafToolUtils.parperParam(params, "DATE_FORMAT(dh.CREATION_DATE,'%Y-%m-%d')", "creationDateFrom", queryString, map, "<=");
        SaafToolUtils.parperParam(params, "bc.category_Name", "categoryName", queryString, map, "like");
        SaafToolUtils.parperParam(params, "bi.item_Name", "itemName", queryString, map, "like");
        SaafToolUtils.parperParam(params, "ph.po_Number", "poNumber", queryString, map, "like");
        SaafToolUtils.parperParam(params, "DATE_FORMAT(dh.delivery_date,'%Y-%m-%d')", "deliveryDateTo", queryString, map, ">=");
        SaafToolUtils.parperParam(params, "DATE_FORMAT(dh.delivery_date,'%Y-%m-%d')", "deliveryDateFrom", queryString, map, "<=");
        map.put("supplierId", params.getInteger("varSupplierId"));
        queryString.append(" AND dh.supplier_Id = :supplierId");
        //总行数
        StringBuffer countSb = new StringBuffer("select count(1) from ("+queryString+")");
        // 排序
        queryString.append(" ORDER BY dh.last_update_date DESC");
        Pagination<SrmPoDeliveryHeadersEntity_HI_RO> deliveryList = srmPoDeliveryHeadersDAO_HI_RO.findPagination(queryString,countSb, map, pageIndex, pageRows);
        return deliveryList;
    }

    /**
     * Description：送货通知查询-申请取消
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveApplyCancel(JSONObject params) throws Exception {
        LOGGER.info("申请取消信息，参数：" + params.toString());
        Integer userId = params.getInteger("varUserId");  //用户Id
        SrmPoDeliveryHeadersEntity_HI headersEntity = null;
        SrmPoDeliveryLinesEntity_HI linesEntity = null;
        List<SrmPoDeliveryLinesEntity_HI> deliveryLinesList = new ArrayList<SrmPoDeliveryLinesEntity_HI>();
        JSONArray jsonArray = params.getJSONArray("arrayListData");//获取列表数组data
        if(null == jsonArray || jsonArray.isEmpty()){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        Integer deliveryHeaderId = 0;
        Integer deliveryLinesId = 0;
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            linesEntity = new SrmPoDeliveryLinesEntity_HI();
            JSONObject obj = jsonArray.getJSONObject(i);// 获取到一行
            deliveryLinesId = obj.getInteger("deliveryLineId");
            deliveryHeaderId = obj.getInteger("deliveryHeaderId");//送货单头表
            if (deliveryLinesId != null && deliveryHeaderId != null) {
                linesEntity = srmPoDeliveryLinesDAO_HI.getById(deliveryLinesId);//查询送货单行Id
            }
            linesEntity.setDeliveryHeaderId(deliveryHeaderId);
            linesEntity.setLastUpdateDate(new Date());
            linesEntity.setLastUpdatedBy(userId);
            linesEntity.setOperatorUserId(userId);
            linesEntity.setDeliveryLineStatus("CANCEL_APPLYING");//行状态为"取消申请中"
            deliveryLinesList.add(linesEntity);
        }
        srmPoDeliveryLinesDAO_HI.saveOrUpdateAll(deliveryLinesList);
        return SToolUtils.convertResultJSONObj("S", "操作成功", 1, null);
    }

    /**
     * Description：打印送货单-订单回货查询-头
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
    public Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryPoPrint(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info("查询参数:==========>>>" + params);
        StringBuffer queryString = new StringBuffer(SrmPoDeliveryHeadersEntity_HI_RO.QUERY_DELIVERY_PO_PRINT);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("deliveryHeaderId", params.getInteger("deliveryHeaderId"));
        queryString.append(" AND dh.delivery_Header_Id = :deliveryHeaderId");
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPoDeliveryHeadersEntity_HI_RO> deliveryList = srmPoDeliveryHeadersDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
        return deliveryList;
    }

    /**
     * Description：打印送货单-订单回货查询-行
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
    public Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryPoLinePrint(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info("查询参数:==========>>>" + params);
        StringBuffer queryString = new StringBuffer(SrmPoDeliveryHeadersEntity_HI_RO.QUERY_DELIVERY_PO_LINE_PRINT);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("deliveryHeaderId", params.getInteger("deliveryHeaderId"));
        queryString.append(" AND dl.delivery_Header_Id = :deliveryHeaderId");
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPoDeliveryHeadersEntity_HI_RO> deliveryList = srmPoDeliveryHeadersDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
        return deliveryList;
    }

    /**
     * Description：送货单-查询(重新打印)
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
    public Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryExternalSupplierPrint(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info("查询参数:===========>>>" + params);
        JSONArray jsonArray = params.getJSONArray("deliveryLineIdArray");
        StringBuffer queryString = new StringBuffer(SrmPoDeliveryHeadersEntity_HI_RO.QUERY_DELIVERY_HEADER_PRINT);
        Map<String, Object> map = new HashMap<String, Object>();
        Integer deliveryHeaderId = 0;
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);// 获取到一行
            deliveryHeaderId = obj.getInteger("deliveryHeaderId");
        }
        map.put("deliveryHeaderId", deliveryHeaderId);
        queryString.append(" AND dh.delivery_Header_Id = :deliveryHeaderId");
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPoDeliveryHeadersEntity_HI_RO> deliveryList = srmPoDeliveryHeadersDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
        return deliveryList;
    }

    /**
     * Description：查询送货单行打印(重新打印)
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
    public Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryDetailLinePrint(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info("查询参数:=====findDeliveryDetailLinePrint=====>>>" + params);
        JSONArray jsonArray = params.getJSONArray("deliveryLineIdArray");
        StringBuffer queryString = new StringBuffer();
        Map<String, Object> map = new HashMap<String, Object>();
        queryString.append(SrmPoDeliveryHeadersEntity_HI_RO.QUERY_DELIVERY_SUPPLIER_LINE_PRINT);
        Integer deliveryHeaderId = 0;
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);// 获取到一行
            deliveryHeaderId = obj.getInteger("deliveryHeaderId");
        }
        map.put("deliveryHeaderId", deliveryHeaderId);
        queryString.append(" AND dl.delivery_Header_Id = :deliveryHeaderId");
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPoDeliveryHeadersEntity_HI_RO> deliveryList = srmPoDeliveryHeadersDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
        return deliveryList;
    }

    /**
     * Description：LOV:查询送货单号
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
    public Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryNumberLov(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info("==========>>>>" + params);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPoDeliveryHeadersEntity_HI_RO.LOV_QUERY_DELIVERY_SQL);
        SaafToolUtils.parperParam(params, "dh.delivery_header_id", "deliveryHeaderId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(params, "dh.delivery_number", "deliveryNumber", sb, queryParamMap, "like");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPoDeliveryHeadersEntity_HI_RO> rowSet = srmPoDeliveryHeadersDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return rowSet;
    }

    /**
     * Description：取消收货单
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updateReceipt(JSONObject params) throws Exception {
        Integer deliveryHeaderId = params.getInteger("deliveryHeaderId");
        if (deliveryHeaderId == null) {
            return SToolUtils.convertResultJSONObj("E", "取消收货单失败,请传入参数", 0, null);
        }
        SrmPoDeliveryHeadersEntity_HI deliveryRow = srmPoDeliveryHeadersDAO_HI.getById(deliveryHeaderId);
        Integer operatorUserId = params.getInteger("varUserId");
        if (!deliveryRow.getStatus().equals("CLOSED")) {
            deliveryRow.setStatus("CANCEL");
            deliveryRow.setLastUpdatedBy(operatorUserId);
            deliveryRow.setOperatorUserId(operatorUserId);
            srmPoDeliveryHeadersDAO_HI.update(deliveryRow);
        }
        return SToolUtils.convertResultJSONObj("S", "取消收货单成功", 1, null);
    }

    /**
     * Description：LOV:查询送货单行
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
    public Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryDetailLine(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info("查询参数:==========>>>" + params + "\t" + pageIndex + "\t" + pageRows);
        StringBuffer queryString = new StringBuffer(SrmPoDeliveryHeadersEntity_HI_RO.QUERY_DELIVERY_LINE);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(params, "dl.delivery_header_id", "deliveryHeaderId", queryString, map, "=");
        // 排序
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" order by bi.item_code,pn.demand_date desc");
        Pagination<SrmPoDeliveryHeadersEntity_HI_RO> deliveryList = srmPoDeliveryHeadersDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
        return deliveryList;
    }

    /**
     * Description：查询送货单行打印汇总
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
    public Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryDetailLineCount(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info("查询参数:==========>>>" + params + "\t" + pageIndex + "\t" + pageRows);
        StringBuffer queryString = new StringBuffer(SrmPoDeliveryHeadersEntity_HI_RO.QUERY_DELIVERY_LINE_PRINT_COUNT);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(params, "dl.delivery_header_id", "deliveryHeaderId", queryString, map, "=");
        // 分组
        queryString.append(" GROUP BY bi.item_code,bi.item_name");
        // 排序
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" ORDER BY bi.item_code DESC");
        Pagination<SrmPoDeliveryHeadersEntity_HI_RO> deliveryList = srmPoDeliveryHeadersDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
        return deliveryList;
    }
}
