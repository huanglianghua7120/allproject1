package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseCategoriesEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseItemsEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.*;
import saaf.common.fmw.po.model.entities.readonly.SrmPoAgreementAssignsEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeadersEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLinesEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoFrameworkAgreement;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

/**
 * Project Name：SrmPoFrameworkAgreementServer
 * Company Name：SIE
 * Program Name：
 * Description：采购框架协议
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoFrameworkAgreementServer")
public class SrmPoFrameworkAgreementServer implements ISrmPoFrameworkAgreement {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoFrameworkAgreementServer.class);

    public SrmPoFrameworkAgreementServer() {
        super();
    }

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private BaseViewObject<SrmPoHeadersEntity_HI_RO> SrmPoHeadersDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPoLinesEntity_HI_RO> srmPoLinesDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPoHeadersEntity_HI> SrmPoHeadersDAO_HI;

    @Autowired
    private ViewObject<SrmPoLinesEntity_HI> SrmPoLinesDAO_HI;

    @Autowired
    private ViewObject<SrmBaseItemsEntity_HI> srmBaseItemsEntity_HI;

    @Autowired
    private ViewObject<SrmBaseCategoriesEntity_HI> srmBaseCategoriesDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    @Autowired
    private ViewObject<SrmPoAgreementAssignsEntity_HI> srmPoAgreementAssignsDAO_HI;

    @Autowired
    private BaseViewObject<SrmPoAgreementAssignsEntity_HI_RO> srmPoAgreementAssignsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPoHeaderArchivesEntity_HI> srmPoHeaderArchivesDAO_HI;

    @Autowired
    private ViewObject<SrmPoLineArchivesEntity_HI> srmPoLineArchivesDAO_HI;

    @Autowired
    private ViewObject<SrmPoAgreementAssignArchivesEntity_HI> srmPoAgreementAssignArchivesDAO_HI;

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;


    /**
     * Description：查询采购框架协议信息
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
    public Pagination<SrmPoHeadersEntity_HI_RO> findPoFrameworkAgreementList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = null;
        //如果没有版本号，则查询订单表，否则查询订单归档表
        if (jsonParams.get("poVersions") == null) {
            queryString = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_PO_FRAMEWORK_AGREEMEN_SQL);
        } else {
            queryString = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_HISTORY_PO_FRAMEWORK_AGREEMEN_SQL);
        }
        //采购协议号
        String poNumber = jsonParams.getString("poNumber");
        if (null != poNumber && poNumber.trim().length() != 0) {
            queryString.append(" AND ph.po_number like :poNumber ");
            queryParamMap.put("poNumber", "%" + poNumber + "%");
        }
        //业务实体
        SaafToolUtils.parperParam(jsonParams, "ph.org_id", "orgId", queryString, queryParamMap, "=");
        //供应商
        SaafToolUtils.parperParam(jsonParams, "ph.supplier_id", "supplierId", queryString, queryParamMap, "=");
        //版本号
        SaafToolUtils.parperParam(jsonParams, "ph.po_versions", "poVersions", queryString, queryParamMap, "=");
        //单据日期从
        String creationDateFrom = jsonParams.getString("creationDateFrom");
        if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
            queryString.append(" AND trunc(ph.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
            queryParamMap.put("creationDateFrom", creationDateFrom);
        }
        //单据日期至
        String creationDateTo = jsonParams.getString("creationDateTo");
        if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
            queryString.append(" AND trunc(ph.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
            queryParamMap.put("creationDateTo", creationDateTo);
        }
        //采购员
        SaafToolUtils.parperParam(jsonParams, "ph.buyer_id", "buyerId", queryString, queryParamMap, "=");
        //协议生效日期
        String startDate = jsonParams.getString("startDate");
        if (startDate != null && !"".equals(startDate.trim())) {
            queryString.append(" AND ph.start_date = to_date(:startDate, 'yyyy-mm-dd')\n");
            queryParamMap.put("startDate", startDate);
        }
        //协议失效日期
        String endDate = jsonParams.getString("endDate");
        if (endDate != null && !"".equals(endDate.trim())) {
            queryString.append(" AND ph.end_date = to_date(:endDate, 'yyyy-mm-dd')\n");
            queryParamMap.put("endDate", endDate);
        }
        //协议状态
        SaafToolUtils.parperParam(jsonParams, "ph.status", "status", queryString, queryParamMap, "=");
        //币种
        SaafToolUtils.parperParam(jsonParams, "ph.currency_code", "currencyCode", queryString, queryParamMap, "=");
        //税率
        SaafToolUtils.parperParam(jsonParams, "ph.tax_rate_code", "taxRateCode", queryString, queryParamMap, "=");
        //付款条件
        SaafToolUtils.parperParam(jsonParams, "ph.payment_condition", "paymentCondition", queryString, queryParamMap, "=");
        //结算方式
        SaafToolUtils.parperParam(jsonParams, "ph.settlement_way", "settlementWay", queryString, queryParamMap, "=");
        //头备注
        SaafToolUtils.parperParam(jsonParams, "ph.description", "description", queryString, queryParamMap, "like");
        //物料编码
        SaafToolUtils.parperParam(jsonParams, "sbi.item_code", "itemCode", queryString, queryParamMap, "like");
        //只能查看当前账号已分配的业务实体相关的数据
        queryString.append(" AND check_org_f(" + jsonParams.getInteger("varUserId") + ", ph.org_id) = 'Y' ");
        StringBuffer countSb = new StringBuffer("select count(1) from ("+queryString+")");
        queryString.append(" ORDER BY ph.po_header_id DESC");
        Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(), countSb.toString(),queryParamMap, pageIndex, pageRows);
        return result;
    }



    /**
     * Description：采购框架协议审批，拒绝操作(变更采购框架协议各种状态)
     * @param jsonParams 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updatePoFrameworkAgreement(JSONObject jsonParams) throws Exception {
        JSONArray ids = jsonParams.getJSONArray("data");
        if(null == ids || ids.isEmpty()){
            SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        String operatorType = jsonParams.getString("operatorType");
        Integer operatorUserId = jsonParams.getInteger("operatorUserId");
        SrmPoHeadersEntity_HI entity = null;
        //新建历史协议实体类
        SrmPoHeaderArchivesEntity_HI copyEntity = new SrmPoHeaderArchivesEntity_HI();
        List<SrmPoHeadersEntity_HI> list = new ArrayList<SrmPoHeadersEntity_HI>();
        String tipMsg = null;
        if ("REJECT".equals(operatorType)) {// 拒绝
            tipMsg = "拒绝";
            for (int i = 0; i < ids.size(); i++) {
                Integer poHeaderId = ids.getInteger(i);
                entity = SrmPoHeadersDAO_HI.getById(poHeaderId);
                entity.setStatus("REJECTED");//驳回
                entity.setOperatorUserId(operatorUserId);
                if (entity != null) {
                    list.add(entity);
                }
            }
        } else if ("APPROVE".equals(operatorType)) {// 审批
            tipMsg = "审批";
            for (int i = 0; i < ids.size(); i++) {
                Integer poHeaderId = ids.getInteger(i);
                entity = SrmPoHeadersDAO_HI.getById(poHeaderId);
                entity.setStatus("APPROVED");//已批准
                entity.setApprovalUserId(operatorUserId);
                entity.setApprovedDate(new Date());
                entity.setOperatorUserId(operatorUserId);
                if (entity != null) {
                    list.add(entity);
                }
            }
        } else if ("CANCEL".equals(operatorType)) {// 取消
            tipMsg = "取消";
            for (int i = 0; i < ids.size(); i++) {
                Integer poHeaderId = ids.getInteger(i);
                entity = SrmPoHeadersDAO_HI.getById(poHeaderId);
                entity.setStatus("CANCELLED");//取消
                entity.setOperatorUserId(operatorUserId);
                if (entity != null) {
                    list.add(entity);
                }
            }
        } else if ("CLOSE".equals(operatorType)) {// 关闭
            tipMsg = "关闭";
            for (int i = 0; i < ids.size(); i++) {
                Integer poHeaderId = ids.getInteger(i);
                entity = SrmPoHeadersDAO_HI.getById(poHeaderId);
                entity.setStatus("CLOSED");//关闭
                entity.setOperatorUserId(operatorUserId);
                if (entity != null) {
                    list.add(entity);
                }
            }
        } else if ("CHANGE".equals(operatorType)) {// 变更
            tipMsg = "变更";
            for (int i = 0; i < ids.size(); i++) {
                Integer poHeaderId = ids.getInteger(i);
                entity = SrmPoHeadersDAO_HI.getById(poHeaderId);
                //把旧的协议复制到新的协议中
                BeanUtils.copyProperties(entity, copyEntity);
                copyEntity.setOperatorUserId(operatorUserId);
                copyEntity.setCreationDate(new Date());
                srmPoHeaderArchivesDAO_HI.save(copyEntity);
                //保存采购订单行归档信息
                List<SrmPoLinesEntity_HI> poLineList = SrmPoLinesDAO_HI.findByProperty("po_header_id", poHeaderId);
                for (int j = 0; j < poLineList.size(); j++) {
                    SrmPoLineArchivesEntity_HI line = new SrmPoLineArchivesEntity_HI();
                    BeanUtils.copyProperties(poLineList.get(j), line);
                    line.setOperatorUserId(operatorUserId);
                    line.setCreationDate(new Date());
                    line.setPoArchiveId(copyEntity.getPoArchiveId());
                    srmPoLineArchivesDAO_HI.save(line);
                }
                //保存应用组织归档信息
                List<SrmPoAgreementAssignsEntity_HI> assignList = srmPoAgreementAssignsDAO_HI.findByProperty("po_header_id", poHeaderId);
                for (int j = 0; j < assignList.size(); j++) {
                    SrmPoAgreementAssignArchivesEntity_HI line = new SrmPoAgreementAssignArchivesEntity_HI();
                    BeanUtils.copyProperties(assignList.get(j), line);
                    line.setOperatorUserId(operatorUserId);
                    line.setCreationDate(new Date());
                    line.setPoArchiveId(copyEntity.getPoArchiveId());
                    srmPoAgreementAssignArchivesDAO_HI.save(line);
                }
                entity.setStatus("UPDATED");//更新
                entity.setPoVersions(new BigDecimal("1").add(entity.getPoVersions()));
                entity.setOperatorUserId(operatorUserId);
                if (entity != null) {
                    list.add(entity);
                }
            }
        }
        if (list.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无操作的数据", 0, null);
        }
        SrmPoHeadersDAO_HI.update(list);
        return SToolUtils.convertResultJSONObj("S", tipMsg + "成功!", list.size(), null);
    }


    /**
     * Description：删除采购框架协议行信息
     * @param paramJSON 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject deletePoFrameworkAgreement(JSONObject paramJSON) throws Exception {
        String operatorType = paramJSON.getString("type");
        JSONArray ids = paramJSON.getJSONArray("data");
        SrmPoLinesEntity_HI entity = null;
        SrmPoAgreementAssignsEntity_HI agreementAssignsEntity_hi = null;
        List<SrmPoLinesEntity_HI> list = new ArrayList<SrmPoLinesEntity_HI>();
        List<SrmPoAgreementAssignsEntity_HI> agreementAssignsList = new ArrayList<SrmPoAgreementAssignsEntity_HI>();
        if (null == ids || ids.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无可删除的数据", 0, null);
        }
        for (int i = 0, j = ids.size(); i < j; i++) {
            if ("ITEMINFO".equals(operatorType)) {
                Integer poLineId = ids.getInteger(i);
                entity = SrmPoLinesDAO_HI.getById(poLineId);
                if (entity != null) {
                    list.add(entity);
                }
            } else if ("APPLIEDORGANIZATION".equals(operatorType)) {
                Integer agreementAssignId = ids.getInteger(i);
                agreementAssignsEntity_hi = srmPoAgreementAssignsDAO_HI.getById(agreementAssignId);
                if (agreementAssignsEntity_hi != null) {
                    agreementAssignsList.add(agreementAssignsEntity_hi);
                }
            }
        }
        if ((null == list || list.isEmpty()) && (null == agreementAssignsList || agreementAssignsList.isEmpty())) {
            return SToolUtils.convertResultJSONObj("S", "无可删除的数据", 0, null);
        }
        SrmPoLinesDAO_HI.delete(list);
        srmPoAgreementAssignsDAO_HI.delete(agreementAssignsList);
        if ("ITEMINFO".equals(operatorType)) {
            return SToolUtils.convertResultJSONObj("S", "删除成功", list.size(), null);
        } else if ("APPLIEDORGANIZATION".equals(operatorType)) {
            return SToolUtils.convertResultJSONObj("S", "删除成功", agreementAssignsList.size(), null);
        } else {
            return SToolUtils.convertResultJSONObj("E", "删除失败", 0, null);
        }
    }


    /**
     * Description：采购框架协议保存，提交操作
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * shipLocationCode  收货方  VARCHAR2  N
     * locationCode  收单方  VARCHAR2  N
     * supPoFileId  供应商附件ID  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * agreementClause  协议条款  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * supplierReadTime  供应商查看日期
     DATE  N
     * exchangeRate  汇率  NUMBER  N
     * exchangeRateDate  汇率日期  DATE  N
     * syncErpFlag  是否同步到erp  VARCHAR2  N
     * syncErpMsg  同步到erp异常信息  VARCHAR2  N
     * isGlobal  是否全局  VARCHAR2  N
     * orderType  订单类型  VARCHAR2  N
     * erpOrderNo  erp订单编号  VARCHAR2  N
     * bazaarOrderNo  市场订单  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * buyerExecuteId  采购履行专员  NUMBER  N
     * organizationId  库存组织  NUMBER  N
     * prNumber  采购申请单号  VARCHAR2  N
     * locationCode  收货地点  VARCHAR2  N
     * poType  类型  VARCHAR2  N
     * shipToOrganizationId  收货组织  NUMBER  N
     * shipLocationCode    VARCHAR2  N
     * shipToLocationCode  收货方  VARCHAR2  N
     * billToLocationCode  收单方  VARCHAR2  N
     * shipToLocationId  收货方  NUMBER  N
     * billToLocationId  收单方  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * isGlobal  是否全局  VARCHAR2  N
     * agreementClause  协议条款  VARCHAR2  N
     * contractId  合同ID，关联表：srm_okc_contracts  NUMBER  N
     * contractCode  合同编号  VARCHAR2  N
     * contractTemplateId  合同模板ID，关联表：srm_okc_contract_templates  NUMBER  N
     * contractFileId  合同文档ID  NUMBER  N
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
    public JSONObject saveOrSubmitPoFrameworkAgreement(JSONObject jsonParams) throws Exception {
        SrmPoHeadersEntity_HI header = null;
        Integer poHeaderId = jsonParams.getInteger("poHeaderId");
        Integer operatorUserId = jsonParams.getInteger("operatorUserId");
        String operatorType = jsonParams.getString("operatorType");
        Date date = new Date();
        String tipMsg = null;
        String headerStatus = null;
        /**
         * 不同操作，状态不同
         */
        if ("SAVE".equals(operatorType)) {
            headerStatus = "DRAFT"; //新增
            tipMsg = "保存";
        } else if ("SUBMIT".equals(operatorType)) {
            headerStatus = "SUBMITTED"; //待审核
            tipMsg = "提交";
        }
        Integer supplierId = jsonParams.getInteger("supplierId");
        SrmPosSupplierInfoEntity_HI supplier = srmPosSupplierInfoDAO_HI.getById(supplierId);
        //判断供应商是否存在或黑名单
        if (null == supplier || (null != supplier.getBlacklistFlag() && supplier.getBlacklistFlag().equals("Y"))) {
            return SToolUtils.convertResultJSONObj("E", tipMsg + "失败,供应商不存在或是黑名单!", 0, null);
        }
        // 如果为空是新增，不为空是更新
        if (null == poHeaderId) {
            header = new SrmPoHeadersEntity_HI();
            // 自动生成采购协议号
            String poNumber = saafSequencesUtil.getDocSequences("srm_po_headers", "XY-", DateUtils.formatDate(date, "yyyyMMdd"), 3);
            header.setPoNumber(poNumber);
            header.setPoVersions(new BigDecimal("0"));
        } else {
            header = SrmPoHeadersDAO_HI.getById(poHeaderId);
        }
        header.setOrgId(jsonParams.getInteger("orgId"));
        header.setSupplierId(jsonParams.getInteger("supplierId"));
        //header.setCreationDate(jsonParams.getDate("creationDate"));
        header.setStartDate(jsonParams.getDate("startDate"));
        header.setEndDate(jsonParams.getDate("endDate"));
        header.setBuyerId(jsonParams.getInteger("employeeId"));
        header.setStatus(headerStatus);
        header.setCurrencyCode(jsonParams.getString("currencyCode"));
        header.setTaxRateCode(jsonParams.getString("taxRateCode"));
        header.setPaymentCondition(jsonParams.getString("paymentCondition"));
        header.setSettlementWay(jsonParams.getString("settlementWay"));
        header.setApprovedDate(null);
        header.setApprovalUserId(null);
        header.setPoFileId(jsonParams.getInteger("poFileId"));
        header.setDescription(jsonParams.getString("description"));
        header.setAgreementClause(jsonParams.getString("agreementClause"));
        header.setOperatorUserId(operatorUserId);
        header.setPoDocType("AGREEMENT");
        header.setPoFileId(jsonParams.getInteger("commonFileId") == null ? header.getPoFileId() : jsonParams.getInteger("commonFileId"));
        header.setIsGlobal(jsonParams.getString("isGlobal"));
        SrmPoHeadersDAO_HI.saveOrUpdate(header);
        //保存行(物料信息)
        JSONArray lineArray = jsonParams.getJSONArray("itemsLineData");
        if (null != lineArray && lineArray.size() > 0) {
            List<SrmPoLinesEntity_HI> lineList = new ArrayList<SrmPoLinesEntity_HI>();
            for (int i = 0; i < lineArray.size(); i++) {
                JSONObject jsonObj = lineArray.getJSONObject(i);
                SrmPoLinesEntity_HI line = null;
                Integer poLineId = jsonObj.getInteger("poLineId");
                if (null == poLineId) {
                    line = new SrmPoLinesEntity_HI();
                    line.setPoHeaderId(header.getPoHeaderId());
                } else {
                    line = SrmPoLinesDAO_HI.getById(poLineId);
                }
                line.setLineNumber((i + 1));
                line.setItemId(jsonObj.getInteger("itemId"));
                line.setItemName(jsonObj.getString("itemName"));
                line.setItemSpec(jsonObj.getString("itemSpec"));
                line.setCategoryId(jsonObj.getInteger("categoryId"));
                line.setDemandQty(jsonObj.getBigDecimal("demandQty"));
                line.setMinPoQty(jsonObj.getBigDecimal("minPoQty"));
                line.setDemandDate(jsonObj.getDate("demandDate"));
                line.setLadderPriceFlag(jsonObj.getString("ladderPriceFlag"));
                line.setLadderQty(jsonObj.getBigDecimal("ladderQty"));
                line.setNonTaxPrice(jsonObj.getBigDecimal("nonTaxPrice"));
                if (!"".equals(jsonObj.getString("nonTaxPrice")) && null != jsonObj.getString("nonTaxPrice")) {
                    line.setTaxPrice(computerTaxPrice(jsonObj.getString("nonTaxPrice"), header.getTaxRateCode()));// 计算得出
                } else {
                    line.setTaxPrice(null);
                }
                line.setAccumulativeFlag(jsonObj.getString("accumulativeFlag"));
                line.setStartDate(jsonObj.getDate("startDate"));
                line.setEndDate(jsonObj.getDate("endDate"));
                line.setDescription(jsonObj.getString("description"));
                line.setSourceNumber(jsonObj.getString("sourceNumber"));
                line.setStatus("OPEN");//行默认打开状态
                line.setOperatorUserId(operatorUserId);
                lineList.add(line);
            }
            SrmPoLinesDAO_HI.saveOrUpdateAll(lineList);
        }
        //保存行(应用组织)
        JSONArray orgLineArray = jsonParams.getJSONArray("orgLineData");
        if (null != orgLineArray && orgLineArray.size() > 0) {
            List<SrmPoAgreementAssignsEntity_HI> orgLineList = new ArrayList<SrmPoAgreementAssignsEntity_HI>();
            List<SrmPoAgreementAssignsEntity_HI> tempList = new ArrayList<SrmPoAgreementAssignsEntity_HI>();
            for (int i = 0; i < orgLineArray.size(); i++) {
                JSONObject obj = orgLineArray.getJSONObject(i);
                SrmPoAgreementAssignsEntity_HI entity = null;
                Integer agreementAssignId = obj.getInteger("agreementAssignId");
                if (null == agreementAssignId) {
                    entity = new SrmPoAgreementAssignsEntity_HI();
                    entity.setPoHeaderId(header.getPoHeaderId());
                    boolean flag = findExistsData(header.getPoHeaderId(), obj.getInteger("orgId"), obj.getInteger("billToOrganizationId"), obj.getInteger("receiveToOrganizationId"));
                    if (flag) {
                        return SToolUtils.convertResultJSONObj("E", "数据重复，保存失败!", 0, null);
                    }
                } else {
                    entity = srmPoAgreementAssignsDAO_HI.getById(agreementAssignId);
                }
                entity.setOrgId(obj.getInteger("orgId"));
                entity.setReceiveToOrganizationId(obj.getInteger("receiveToOrganizationId"));
                entity.setBillToOrganizationId(obj.getInteger("billToOrganizationId"));
                entity.setDefaultFlag(obj.getString("defaultFlag"));
                entity.setOperatorUserId(operatorUserId);
                orgLineList.add(entity);
                for (int n = 0; n < tempList.size(); n++) {
                    SrmPoAgreementAssignsEntity_HI tempObj = tempList.get(n);
                    if (obj.getInteger("orgId").intValue() == tempObj.getOrgId().intValue() &&
                            obj.getInteger("billToOrganizationId").intValue() == tempObj.getBillToOrganizationId().intValue() &&
                            obj.getInteger("receiveToOrganizationId").intValue() == tempObj.getReceiveToOrganizationId().intValue()) {
                        return SToolUtils.convertResultJSONObj("E", "数据重复，保存失败!", 0, null);
                    }
                }
                tempList.add(entity);
            }
            srmPoAgreementAssignsDAO_HI.saveOrUpdateAll(orgLineList);
        }
        return SToolUtils.convertResultJSONObj("S", tipMsg + "成功!", 1, header);
    }


    /**
     * Description：校验数据是否存在
     *
     * @param poHeaderId
     * @param orgId
     * @param billToOrganizationId
     * @param receiveToOrganizationId
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    public boolean findExistsData(Integer poHeaderId, Integer orgId, Integer billToOrganizationId, Integer receiveToOrganizationId) throws Exception {
        StringBuffer sql = new StringBuffer(SrmPoAgreementAssignsEntity_HI_RO.QUERY_COUNT);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("poHeaderId", poHeaderId);
        map.put("orgId", orgId);
        map.put("billToOrganizationId", billToOrganizationId);
        map.put("receiveToOrganizationId", receiveToOrganizationId);
        int count = srmPoAgreementAssignsDAO_HI_RO.findList(sql, map).get(0).getCount();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Description：计算含税价
     *
     * @param nonTaxPrice
     * @param taxRateCode
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private BigDecimal computerTaxPrice(String nonTaxPrice, String taxRateCode) {
        String taxRate = "0";
        if ("1".equals(taxRateCode)) {
            taxRate = "0.16";
        } else if ("2".equals(taxRateCode)) {
            taxRate = "0.1";
        }
        // 100 * (1+0.1)
        return new BigDecimal(nonTaxPrice).multiply(new BigDecimal(taxRate).add(new BigDecimal("1")));
    }


    /**
     * Description：查询采购框架协议头信息
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmPoHeadersEntity_HI_RO> findPoFrameworkAgreementHeaderInfo(JSONObject jsonParams) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryParamSql = null;
        //如果归档表id为空，则查询采购订单表，否则查询采购订单归档表
        if (jsonParams.get("poArchiveId") == null) {
            queryParamSql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_PO_FRAMEWORK_AGREEMEN_HEADER_SQL);
        } else {
            queryParamSql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_HISTORY_PO_FRAMEWORK_AGREEMEN_HEADER_SQL);
            queryParamSql = queryParamSql.append("and ph.po_archive_id = " + jsonParams.getInteger("poArchiveId") + "\r\n");
        }
        queryParamMap.put("poHeaderId", jsonParams.getString("poHeaderId"));
        return SrmPoHeadersDAO_HI_RO.findList(queryParamSql, queryParamMap);
    }


    /**
     * Description：查询采购框架协议行物料信息
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmPoHeadersEntity_HI_RO> findPoFrameworkAgreementLineItemInfo(JSONObject jsonParams) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryParamSql = null;
        //如果归档表id为空，则查询采购订单行表，否则查询采购订单行归档表
        if (jsonParams.get("poArchiveId") == null) {
            queryParamSql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_PO_FRAMEWORK_AGREEMEN_LINE_ITEM_INFO_SQL);
        } else {
            queryParamSql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_HISTORY_PO_FRAMEWORK_AGREEMEN_LINE_ITEM_INFO_SQL);
            queryParamSql = queryParamSql.append("and pl.po_archive_id = " + jsonParams.getInteger("poArchiveId") + "\r\n");
        }
        SaafToolUtils.parperParam(jsonParams, "pl.po_header_id", "poHeaderId", queryParamSql, queryParamMap, "=");
        String itemCodeOrName = jsonParams.getString("itemCodeOrName");
        if (null != itemCodeOrName && itemCodeOrName.trim().length() != 0) {
            queryParamSql.append(" and (tb1.item_code like :itemCodeOrName ");
            queryParamSql.append(" or tb1.item_name like :itemCodeOrName) ");
            queryParamMap.put("itemCodeOrName", "%" + itemCodeOrName + "%");
        }
        return SrmPoHeadersDAO_HI_RO.findList(queryParamSql, queryParamMap);
    }

    /**
     * Description：查询采购框架协议行物料信息分页查询
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
    public Pagination<SrmPoHeadersEntity_HI_RO> findPoFrameworkAgreementLineItemInfoPage(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryParamSql = null;
        //如果归档表id为空，则查询采购订单行表，否则查询采购订单行归档表
        if (jsonParams.get("poArchiveId") == null) {
            queryParamSql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_PO_FRAMEWORK_AGREEMEN_LINE_ITEM_INFO_SQL);
        } else {
            queryParamSql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_HISTORY_PO_FRAMEWORK_AGREEMEN_LINE_ITEM_INFO_SQL);
            queryParamSql = queryParamSql.append("AND pl.po_archive_id = " + jsonParams.getInteger("poArchiveId") + "\r\n");
        }
        SaafToolUtils.parperParam(jsonParams, "pl.po_header_id", "poHeaderId", queryParamSql, queryParamMap, "=");
        String itemCodeOrName = jsonParams.getString("itemCodeOrName");
        if (null != itemCodeOrName && itemCodeOrName.trim().length() != 0) {
            queryParamSql.append(" AND (bi.item_code like :itemCodeOrName ");
            queryParamSql.append(" OR bi.item_name like :itemCodeOrName) ");
            queryParamMap.put("itemCodeOrName", "%" + itemCodeOrName + "%");
        }
        String countSql = "select count(1) from (" + queryParamSql + ")";
        return SrmPoHeadersDAO_HI_RO.findPagination(queryParamSql,countSql, queryParamMap, pageIndex, pageRows);
    }

    /**
     * Description：查询采购框架协议行应用组织信息
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmPoHeadersEntity_HI_RO> findPoFrameworkAgreementLineAppliedOrganization(JSONObject jsonParams) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryParamSql = null;
        //如果归档表id为空，则查询应用组织表，否则查询应用组织归档表
        if (jsonParams.get("poArchiveId") == null) {
            queryParamSql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_PO_FRAMEWORK_AGREEMEN_LINE_APPLIED_ORGANIZATION_SQL);
        } else {
            queryParamSql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_HISTORY_PO_FRAMEWORK_AGREEMEN_LINE_APPLIED_ORGANIZATION_SQL);
            queryParamSql = queryParamSql.append("AND pa.po_archive_id = " + jsonParams.getInteger("poArchiveId") + "\r\n");
        }
        SaafToolUtils.parperParam(jsonParams, "pa.po_header_id", "poHeaderId", queryParamSql, queryParamMap, "=");
        String orgCodeOrName = jsonParams.getString("orgCodeOrName");
        if (null != orgCodeOrName && orgCodeOrName.trim().length() != 0) {
            queryParamSql.append(" AND (si.inst_code like :orgCodeOrName ");
            queryParamSql.append(" OR si.inst_name like :orgCodeOrName) ");
            queryParamMap.put("orgCodeOrName", "%" + orgCodeOrName + "%");
        }
        return SrmPoHeadersDAO_HI_RO.findList(queryParamSql, queryParamMap);
    }

    /**
     * Description：批量导入采购框架协议行物料数据
     * @param jsonParams 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveImportPoFrameworkAgreementLineItemInfo(JSONObject jsonParams) {
        Integer operatorUserId = jsonParams.getInteger("operatorUserId");
        if (jsonParams.getJSONArray("data") == null || "".equals(jsonParams.getJSONArray("data"))
                || jsonParams.getJSONArray("data").size() <= 0) {
            return SToolUtils.convertResultJSONObj("E", "导入的数据为空，不可导入", 0, null);
        }
        if (jsonParams.getJSONObject("info") == null || "".equals(jsonParams.getJSONObject("info"))
                || jsonParams.getJSONObject("info").getString("poHeaderId") == null
                || "".equals(jsonParams.getJSONObject("info").getString("poHeaderId"))) {
            return SToolUtils.convertResultJSONObj("E", "头信息未保存，不可导入", 0, null);
        }
        JSONArray jsonArray = jsonParams.getJSONArray("data");
        JSONObject info = jsonParams.getJSONObject("info");
        Integer poHeaderId = Integer.parseInt(info.getString("poHeaderId"));
        JSONArray error = cehckArray(jsonArray, poHeaderId);
        if (null != error && error.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存失败", error.size(), error);
        }
        SrmPoLinesEntity_HI line = null;
        Integer lineNumber = null;
        int count = 0;
        if(null != jsonArray && jsonArray.size()>0){
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                if (null == lineNumber) {
                    lineNumber = jsonObj.getInteger("lineNumber");
                }
                lineNumber++;
                line = new SrmPoLinesEntity_HI();
                line.setPoHeaderId(poHeaderId);
                line.setLineNumber(lineNumber);
                line.setItemId(jsonObj.getInteger("itemId"));
                line.setItemName(jsonObj.getString("itemName"));
                line.setItemSpec(jsonObj.getString("itemSpec"));
                line.setCategoryId(jsonObj.getInteger("categoryId"));
                line.setDemandQty(jsonObj.getBigDecimal("demandQty"));
                line.setLadderQty(jsonObj.getBigDecimal("ladderQty"));
                line.setNonTaxPrice(jsonObj.getBigDecimal("nonTaxPrice"));
                //line.setTaxPrice(computerTaxPrice(jsonObj.getString("nonTaxPrice"), jsonObj.getString("taxRateCode")));// 计算得出
                line.setDemandDate(jsonObj.getDate("demandDate"));
                line.setStartDate(jsonObj.getDate("startDate"));
                line.setEndDate(jsonObj.getDate("endDate"));
                line.setDescription(jsonObj.getString("description"));
                line.setStatus("OPEN");
                line.setOperatorUserId(operatorUserId);
                SrmPoLinesDAO_HI.save(line);
                count++;
            }
        }
        JSONObject resultObj = new JSONObject();
        resultObj.put("msg", "导入成功行数为:" + count + "行!");
        resultObj.put("status", "S");
        return resultObj;
    }


    /**
     * Description：检查批量导入的数据正确性
     *
     * @param jsonArray
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private JSONArray cehckArray(JSONArray jsonArray, Integer poHeaderId) {
        if (null == jsonArray || jsonArray.isEmpty()) {
            return null;
        }
        JSONArray error = new JSONArray();
        JSONObject e = null;
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String itemName = object.getString("itemName");
            String itemSpec = object.getString("itemSpec");
            String itemCode = object.getString("itemCode");
            String categoryCode = object.getString("categoryCode");
            String demandQty = object.getString("demandQty");
            String nonTaxPrice = object.getString("nonTaxPrice");
            String demandDate = object.getString("demandDate");
            String startDate = object.getString("startDate");
            String endDate = object.getString("endDate");
            //物料名称必填
            if (null == itemName || itemName.trim().length() == 0) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "物料名称不能为空");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            //分类编码必填
            if (null == categoryCode || categoryCode.trim().length() == 0) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "分类编码不能为空");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            //如果“不含税价格”有值则，“价格生效日期”为必填项
            if (null != nonTaxPrice || nonTaxPrice.trim().length() != 0) {
                if (null == startDate || startDate.trim().length() == 0) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "价格生效日期不能为空");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
            List<SrmBaseItemsEntity_HI> srmBaseItemsEntityList = srmBaseItemsEntity_HI.findByProperty("item_code", itemCode);
            if (srmBaseItemsEntityList == null || srmBaseItemsEntityList.size() < 1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "物料编码不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            object.put("itemId", srmBaseItemsEntityList.get(0).getItemId());

            List<SrmBaseCategoriesEntity_HI> srmBaseCategoriesEntityList = srmBaseCategoriesDAO_HI.findByProperty("category_code", categoryCode);
            if (srmBaseCategoriesEntityList == null || srmBaseCategoriesEntityList.size() < 1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "分类编码不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            object.put("categoryId", srmBaseCategoriesEntityList.get(0).getCategoryId());
            if (null != demandQty || demandQty.trim().length() != 0) {
                try {
                    Integer.parseInt(demandQty);
                } catch (NumberFormatException e1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "需求数量有误");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
            if (null != nonTaxPrice || nonTaxPrice.trim().length() != 0) {
                try {
                    Double.parseDouble(nonTaxPrice);
                } catch (NumberFormatException e1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "不含税价格有误");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
            if (null != demandDate || demandDate.trim().length() != 0) {
                try {
                    object.getDate("demandDate");
                } catch (Exception e1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "日期格式有误(yyyy-MM-dd),例如:2001-01-01");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
            if (null != startDate || startDate.trim().length() != 0) {
                try {
                    object.getDate("demandDate");
                } catch (Exception e1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "日期格式有误(yyyy-MM-dd),例如:2001-01-01");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
            if (null != endDate || endDate.trim().length() != 0) {
                try {
                    object.getDate("demandDate");
                } catch (Exception e1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "日期格式有误(yyyy-MM-dd),例如:2001-01-01");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
            //行号
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPoLinesEntity_HI_RO.QUERY_PO_FRAMEWORK_AGREEMEN_LINE_SQL);
            queryParamMap.put("poHeaderId", poHeaderId);
            List<SrmPoLinesEntity_HI_RO> list = srmPoLinesDAO_HI_RO.findList(queryString, queryParamMap);
            object.put("lineNumber", list.size());
        }
        return error;
    }


    /**
     * Description：框架协议转订单
     * @param jsonParam 框架协议参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */

    @Override
    public JSONObject saveOrderInfoByFrameworkAgreement(JSONObject jsonParam) throws Exception {
        JSONArray datas = jsonParam.getJSONArray("data");
        Integer operatorUserId = jsonParam.getInteger("operatorUserId");
        if (null == datas || datas.size() == 0) {
            return SToolUtils.convertResultJSONObj("E", "无操作的数据", 0, null);
        }
        List<Integer> poHeaderIds = new ArrayList<Integer>();
        SrmPoHeadersEntity_HI srmPoHeadersEntity_HI = null;
        SrmPoHeadersEntity_HI header = null;
        SrmPoAgreementAssignsEntity_HI_RO agreementAssignsEntity_hi = null;
        for (int i = 0; i < datas.size(); i++) {
            Integer poHeaderId = datas.getInteger(i);
            srmPoHeadersEntity_HI = SrmPoHeadersDAO_HI.getById(poHeaderId);
            if (srmPoHeadersEntity_HI.getStatus() != null && "APPROVED".equals(srmPoHeadersEntity_HI.getStatus())) {
                header = new SrmPoHeadersEntity_HI();
                Date date = new Date();
                String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
                // 自动生成采购订单号
                String poNumber = saafSequencesUtil.getDocSequences("srm_po_headers", "PO-", dateFromate, 4);
                header.setPoNumber(poNumber);
                header.setPoVersions(new BigDecimal("0"));
                header.setOrgId(srmPoHeadersEntity_HI.getOrgId());
                List<SrmPoAgreementAssignsEntity_HI_RO> info = findPoAgreementAssignsInfo(poHeaderId, srmPoHeadersEntity_HI.getOrgId());
                if (!info.isEmpty() && info.size() > 0) {
                    agreementAssignsEntity_hi = info.get(0);
                    header.setBillToOrganizationId(agreementAssignsEntity_hi.getBillToOrganizationId());
                }
                //header.setBillToOrganizationId(srmPoHeadersEntity_HI.getBillToOrganizationId());
                header.setPoDocType("ORDER");
                header.setSupplierId(srmPoHeadersEntity_HI.getSupplierId());
                header.setSupplierSiteId(srmPoHeadersEntity_HI.getSupplierSiteId());
                header.setCurrencyCode(srmPoHeadersEntity_HI.getCurrencyCode());
                header.setTaxRateCode(srmPoHeadersEntity_HI.getTaxRateCode());
                header.setBuyerId(srmPoHeadersEntity_HI.getBuyerId());
                header.setReturnGoodsType(srmPoHeadersEntity_HI.getReturnGoodsType());
                header.setPaymentCondition(srmPoHeadersEntity_HI.getPaymentCondition());
                header.setSettlementWay(srmPoHeadersEntity_HI.getSettlementWay());
                header.setStatus("DRAFT");
                header.setDescription(srmPoHeadersEntity_HI.getDescription());
                header.setSourceId(String.valueOf(srmPoHeadersEntity_HI.getPoHeaderId()));
                header.setOperatorUserId(operatorUserId);
                SrmPoHeadersDAO_HI.saveOrUpdate(header);


                List<SrmPoLinesEntity_HI> list = SrmPoLinesDAO_HI.findByProperty("po_header_id", poHeaderId);
                SrmPoLinesEntity_HI line = null;
                //for (SrmPoLinesEntity_HI srmPoLinesEntity_hi : list) {
                for (int j = 0; j < list.size(); j++) {
                    line = new SrmPoLinesEntity_HI();
                    SrmPoLinesEntity_HI srmPoLinesEntity_hi = list.get(j);
                    line.setPoHeaderId(header.getPoHeaderId());
                    line.setLineNumber((j + 1));
                    line.setItemId(srmPoLinesEntity_hi.getItemId());
                    line.setItemName(srmPoLinesEntity_hi.getItemName());
                    line.setItemSpec(srmPoLinesEntity_hi.getItemSpec());
                    line.setCategoryId(srmPoLinesEntity_hi.getCategoryId());
                    line.setDemandQty(srmPoLinesEntity_hi.getDemandQty());
                    //line.setMinPoQty(srmPoLinesEntity_hi.getMinPoQty());
                    line.setDemandDate(srmPoLinesEntity_hi.getDemandDate());
                    line.setNonTaxPrice(srmPoLinesEntity_hi.getNonTaxPrice());
                    line.setTaxPrice(srmPoLinesEntity_hi.getTaxPrice());
                    line.setDescription(srmPoLinesEntity_hi.getDescription());
                    line.setStatus("OPEN");
                    line.setReceiveToOrganizationId(agreementAssignsEntity_hi.getReceiveToOrganizationId());
                    line.setOnWayQty(new BigDecimal("0"));
                    line.setReceivedQty(new BigDecimal("0"));
                    line.setOriginalDemandQty(null);
                    line.setOriginalDemandDate(null);
                    line.setFeedbackAdjustDate(null);
                    line.setFeedbackAdjustQty(null);
                    line.setFeedbackStatus("NON_FEEDBACK");
                    line.setFeedbackResult("CONFIRM");
                    line.setRejectReason(null);
                    line.setMayNoticeQty((srmPoLinesEntity_hi.getDemandQty() == null ? new BigDecimal(0) : srmPoLinesEntity_hi.getDemandQty()).subtract(line.getOnWayQty()));
                    line.setOperatorUserId(operatorUserId);
                    SrmPoLinesDAO_HI.saveOrUpdate(line);
                }
                poHeaderIds.add(header.getPoHeaderId());
            }
        }
        return SToolUtils.convertResultJSONObj("S", "转订单成功", datas.size(), poHeaderIds);
    }


    /**
     * Description：采购协议分配组织表
     *
     * @param poHeaderId 协议号
     * @param orgId
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    public List<SrmPoAgreementAssignsEntity_HI_RO> findPoAgreementAssignsInfo(Integer poHeaderId, Integer orgId) throws Exception {
        if (poHeaderId == null || orgId == null) {
            throw new Exception("参数不能为空！");
        }
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryParamSql = new StringBuffer(SrmPoAgreementAssignsEntity_HI_RO.QUERY_PO_AGREEMENT_ASSIGNS_INFO);
        queryParamMap.put("poHeaderId", poHeaderId);
        queryParamMap.put("orgId", orgId);
        return srmPoAgreementAssignsDAO_HI_RO.findList(queryParamSql, queryParamMap);
    }

    /**
     * Description：删除采购框架协议头信息
     * @param params 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject deletePoFrameworkAgreementHeader(JSONObject params) throws Exception {
        LOGGER.info("删除参数：" + params.toString());
        if (null != params.getInteger("poHeaderId")) {
            SrmPoHeadersEntity_HI headersRow = SrmPoHeadersDAO_HI.getById(params.getInteger("poHeaderId"));
            if ("DRAFT".equals(headersRow.getStatus()) || "REJECTED".equals(headersRow.getStatus())) {
                if (null != headersRow) {
                    List<SrmPoLinesEntity_HI> linesList = SrmPoLinesDAO_HI.findByProperty("poHeaderId", headersRow.getPoHeaderId());
                    if (linesList != null && linesList.size() > 0) {
                        SrmPoLinesDAO_HI.delete(linesList);
                    }

                    List<SrmPoAgreementAssignsEntity_HI> assignList = srmPoAgreementAssignsDAO_HI.findByProperty("poHeaderId", headersRow.getPoHeaderId());
                    if (assignList != null && assignList.size() > 0) {
                        srmPoAgreementAssignsDAO_HI.delete(assignList);
                    }
                    SrmPoHeadersDAO_HI.delete(headersRow);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除框架协议失败,只能删除拟定和驳回的单据!", 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "删除框架协议失败，" + params.getString("poHeaderId") + "不存在", 0, null);
        }
        return SToolUtils.convertResultJSONObj("S", "删除框架协议成功", 1, null);
    }
    /**
     * Description：查询价格库信息（分页）
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
    public Pagination<SrmPoHeadersEntity_HI_RO> findAgreementPriceLibraryList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_AGREEMENT_PRICE_LIBRARY_LIST_SQL);
        if("reportQuery".equals(jsonParams.getString("reportQuery"))&&"CLASSIFICATION".equals(jsonParams.getString("priceChartType"))){
            queryString = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_PRICE_LIBRARY_REPORT);
        }

        //业务实体
        SaafToolUtils.parperParam(jsonParams, "t.org_id", "orgId", queryString, queryParamMap, "=");
        //供应商
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplierId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_id", "supplierId", queryString, queryParamMap, "=");
        //采购员
        SaafToolUtils.parperParam(jsonParams, "t.buyer_id", "buyerId", queryString, queryParamMap, "=");
        //物料
        SaafToolUtils.parperParam(jsonParams, "t.item_id", "itemId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.item_name", "itemName", queryString, queryParamMap, "LIKE");
        //物料分类
        SaafToolUtils.parperParam(jsonParams, "t.category_id", "categoryId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.price_library_version", "priceLibraryVersion", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.organization_id", "organizationId", queryString, queryParamMap, "=");
        //按最新有效价格统计
        if ("Y".equals(jsonParams.getString("calculateByLastestEabledPriceFlag"))){
            //queryString.append(" and t.price_library_status = 'ACT'  ");
            queryString.append(" and  t.price_library_version in\n" +
                    "       (select max(s.price_library_version) price_library_version\n" +
                    "          from srm_pon_price_library s\n" +
                    "         where s.org_id = t.org_id\n" +
                    "           and s.supplier_id = t.supplier_id\n" +
                    "           and s.item_id = t.item_id)");
        }

        if (!ObjectUtils.isEmpty(jsonParams.getString("creationDateFrom"))) {
            queryString.append(" AND trunc(t.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
            queryParamMap.put("creationDateFrom", jsonParams.getString("creationDateFrom").trim());
        }
        //统计周期
            /*if(!ObjectUtils.isEmpty(jsonParams.getString("priceChartCycle"))){
                Map map = new HashMap();
                map.put("lookupType", "SER_PRICE_CHART_CYCLE");
                map.put("lookupCode", jsonParams.getString("priceChartCycle"));
                List<SaafLookupValuesEntity_HI> lookupValues = saafLookupValuesDAO_HI.findByProperty(map);
                String lookupValue="";
                if(!ObjectUtils.isEmpty(lookupValues)){
                    lookupValue=lookupValues.get(0).getDescription();
                    if(!ObjectUtils.isEmpty(lookupValue)){
                        queryString.append(" AND trunc(t.creation_date) <= to_date(:creationDateFrom, 'yyyy-mm-dd') +" +Integer.valueOf(lookupValue));
                    }else{
                        throw  new RuntimeException("未找到相应统计周期数据值");
                    }
                }else{
                    throw  new RuntimeException("未找到相应统计周期数据值");
                }
            }else{
                if (!ObjectUtils.isEmpty(jsonParams.getString("creationDateTo"))) {
                    queryString.append(" AND trunc(t.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
                    queryParamMap.put("creationDateTo", jsonParams.getString("creationDateTo").trim());
                }
            }*/

        if (!ObjectUtils.isEmpty(jsonParams.getString("creationDateTo"))) {
            queryString.append(" AND trunc(t.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
            queryParamMap.put("creationDateTo", jsonParams.getString("creationDateTo").trim());
        }

        if(!ObjectUtils.isEmpty(jsonParams.getString("itemIds"))){
            String itemIds = jsonParams.getString("itemIds");
            queryString .append(" AND t.item_id IN (" + itemIds+ ") ");
        }


        //只能查看当前账号已分配的业务实体相关的数据
        //queryString.append(" AND check_org_f(" + jsonParams.getInteger("varUserId") + ", ph.org_id) = 'Y' ");
        //queryString.append(" ORDER BY ph.po_header_id DESC");

        queryString.append(" and t.org_id in (SELECT distinct (sua.inst_id) Organization_Id\n" +
                "                               FROM saaf_user_access_orgs sua,\n" +
                "                                    saaf_institution      si,\n" +
                "                                    saaf_users            su\n" +
                "                              WHERE sua.user_id = su.user_id\n" +
                "                                AND sua.inst_id = si.inst_id\n" +
                "                                and sua.platform_code = 'SAAF'\n" +
                "                                and si.inst_type='ORG'\n" +
                "                                and sua.user_id = "+jsonParams.getInteger("varUserId")+") "+
                "      AND t.item_id in (SELECT Bi.Item_Id\n" +
                "                       FROM Srm_Base_Items Bi\n" +
                "                      WHERE Bi.Category_Id IN  (" +getCategoryId(jsonParams.getInteger("varUserId"))+"))");

        if("reportQuery".equals(jsonParams.getString("reportQuery"))){
            if("CLASSIFICATION".equals(jsonParams.getString("priceChartType"))){
                queryString.append(" GROUP BY t.Category_Id\n" +
                        "         ,Sbc.Full_Category_Code\n" +
                        "         ,Sbc.Full_Category_Name\n" +
                        "         ,Trunc(t.Creation_Date)\n" +
                        " ORDER BY Trunc(t.Creation_Date) ASC");
            }else if("ITEM".equals(jsonParams.getString("priceChartType"))){
                queryString.append(" and t.Creation_Date IN\n" +
                        "       (SELECT MAX(m.Creation_Date) Creation_Date\n" +
                        "          FROM Srm_Pon_Price_Library m\n" +
                        "         WHERE m.Item_Id = t.Item_Id\n" +
                        "           AND m.Org_Id = t.Org_Id\n" +
                        "         GROUP BY To_Char(m.Creation_Date\n" +
                        "                         ,'yyyy-mm-dd')) ");
                queryString.append(" order by t.creation_date ASC");
            }
        }else{
            queryString.append(" order by t.price_library_status,t.creation_date desc,t.last_update_date desc ");
        }




        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：获取用户负责的品类ID
     * @param userId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private String getCategoryId(Integer userId){
        JSONObject json=new JSONObject();
        json.put("userId",userId);
        List<SrmBaseUserCategoriesEntity_HI_RO> userCategoriesList=srmBaseUserCategories.findUserCategories(json);
        List  categoryIds=new ArrayList();
        for(SrmBaseUserCategoriesEntity_HI_RO ro : userCategoriesList){
            categoryIds.add(ro.getCategoryId().toString());
        }
        String categoryId= String.valueOf(categoryIds.stream().distinct().collect(Collectors.joining(",")));
        return categoryId;
    }

}
