package saaf.common.fmw.po.model.inter;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.po.model.entities.SrmPoHeadersEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoLinesEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeadersEntity_HI_RO;

/**
 * Project Name：IPurchaseOrder
 * Company Name：SIE
 * Program Name：
 * Description：采购订单
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
public interface IPurchaseOrder {


    /**
     * Description：查询采购订单
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
	Pagination<SrmPoHeadersEntity_HI_RO> findOrderInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;


    /**
     * Description：查询送货通知采购订单
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
	Pagination<SrmPoHeadersEntity_HI_RO> findNoticeOrderInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;


    /**
     * Description：查询采购订单头
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
    Pagination<SrmPoHeadersEntity_HI_RO> findOrderHeader(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;


    /**
     * Description：查询采购订单头-打印
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
    Pagination<SrmPoHeadersEntity_HI_RO> findOrderHeaderPrint(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;


    /**
     * Description：查询采购订单行
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
    Pagination<SrmPoHeadersEntity_HI_RO> findOrderLine(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;


    /**
     * Description：查询组织下拉框
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    List findInstitutionList(JSONObject jsonParams) throws Exception;

    /**
     * Description：查询采购订单lov
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
    Pagination<SrmPoHeadersEntity_HI_RO> findOrderLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：查询供应商lov
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
    Pagination<SrmPoHeadersEntity_HI_RO> findSupplierLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：查询物料类别lov
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
    Pagination<SrmPoHeadersEntity_HI_RO> findCategoryLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;


    /**
     * Description：查询物料lov
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
    Pagination<SrmPoHeadersEntity_HI_RO> findItemLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：查询供应商能供的物料lov
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
    Pagination<SrmPoHeadersEntity_HI_RO> findSupplierItemLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：保存订单信息
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

    public JSONObject saveOrderInfo(JSONObject jsonParams) throws Exception;

    /**
     * Description：匹配采购订单
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public List<SrmPoHeadersEntity_HI_RO> findPoLineOrder(JSONObject jsonParams)throws Exception;


    /**
     * Description：提交，批准，拒绝订单
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

    public JSONObject saveOrderStatus(JSONObject jsonParams) throws Exception;

    /**
     * Description：删除订单
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject deleteOrder(JSONObject jsonParams) throws Exception;

    /**
     * Description：删除订单行
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject deleteOrderLine(JSONObject jsonParams) throws Exception;


    /**
     * Description：删除订单行
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	public JSONObject deleteOrderLines(JSONObject params) throws Exception;

    /**
     * Description：复制订单
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


    public JSONObject saveCopyOrder(JSONObject jsonParams) throws Exception;

    /**
     * Description：拒绝订单
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject saveRejectReason(JSONObject jsonParams) throws Exception;


    /**
     * Description：关闭订单行
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject saveOrderLineStatus(JSONObject jsonParams) throws Exception;


    /**
     * Description：根据订单行查询送货通知/送货单
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    List findDeliveryList(JSONObject jsonParams) throws Exception;

    /**
     * Description：应用调整
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * poChangeId  表ID，主键，供其他表做外键  NUMBER  Y
     * planDate  计划日期  DATE  Y
     * poNumber    VARCHAR2  Y
     * poLineNum    NUMBER  Y
     * poHeaderId    NUMBER  Y
     * poLineId    NUMBER  Y
     * changeType  建议类型 C：取消，U，更新  VARCHAR2  Y
     * originQuantity  原始数量  NUMBER  N
     * originDate  原始需求日期  DATE  N
     * orderQuantity  订单数量  NUMBER  N
     * needByDate  需求日期  DATE  N
     * status  状态  VARCHAR2  Y
     * oldOrderQuantity  未调整前数量  NUMBER  N
     * oldNeedByDate  未调整前需求日期  DATE  N
     * poChangeId  表ID，主键，供其他表做外键  NUMBER  Y
     * planDate  计划日期  DATE  Y
     * poNumber    VARCHAR2  Y
     * poLineNum    NUMBER  Y
     * poHeaderId    NUMBER  Y
     * poLineId    NUMBER  Y
     * changeType  建议类型 C：取消，U，更新  VARCHAR2  Y
     * originQuantity  原始数量  NUMBER  N
     * originDate  原始需求日期  DATE  N
     * orderQuantity  订单数量  NUMBER  N
     * needByDate  需求日期  DATE  N
     * status  状态  VARCHAR2  Y
     * oldOrderQuantity  未调整前数量  NUMBER  N
     * oldNeedByDate  未调整前需求日期  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    public JSONObject saveAdjust(JSONObject jsonParams) throws Exception;

    /**
     * Description：srm调整
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * onhandQty  现有量  NUMBER  N
     * consumeQty  消耗量  NUMBER  N
     * subpool  子库  VARCHAR2  N
     * noteToReceiver  通知接收人  VARCHAR2  N
     * accepterName  通知接收人  VARCHAR2  N
     * itemId  物料ID，关联表：srm_base_items  NUMBER  N
     * itemName  物料名称  VARCHAR2  N
     * itemSpec  物料规格  VARCHAR2  N
     * categoryId  采购分类ID，关联表：srm_base_categories  NUMBER  N
     * demandQty  需求数量  NUMBER  N
     * minPoQty  最小采购量  NUMBER  N
     * taxPrice  含税单价  NUMBER  N
     * nonTaxPrice  不含税单价  NUMBER  N
     * ladderPriceFlag  阶梯价标识（Y/N）  VARCHAR2  N
     * ladderQty  阶梯范围  NUMBER  N
     * accumulativeFlag  累计结算标识（Y/N）  VARCHAR2  N
     * demandDate  需求日期  DATE  N
     * receiveToOrganizationId  库存组织ID  NUMBER  N
     * status  行状态(PO_LINE_STATUS)  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * mayNoticeQty  可通知送货数量  NUMBER  N
     * onWayQty  在途数量(已创建送货单数量)  NUMBER  N
     * receivedQty  已接收数量  NUMBER  N
     * originalDemandQty  原需求数量  NUMBER  N
     * originalDemandDate  原需求日期  DATE  N
     * feedbackAdjustDate  反馈调整日期  DATE  N
     * feedbackAdjustQty  反馈调整数量  NUMBER  N
     * feedbackStatus  反馈状态  VARCHAR2  N
     * feedbackResult  反馈结果  VARCHAR2  N
     * rejectReason  供应商拒绝原因  VARCHAR2  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * sourceNumber  来源单号  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * feedbackOnwayDate  反馈在途时间  NUMBER  N
     * itemVersion  物料版本  VARCHAR2  N
     * shipmentNum  发运行号  NUMBER  N
     * shipToLocationId  收货方ID  NUMBER  N
     * firstFeedbackRecodeDate  第一次反馈记录时间  DATE  N
     * serialNo  流水号  VARCHAR2  N
     * process  工序  VARCHAR2  N
     * processScope  加工范围  VARCHAR2  N
     * orderId  工单ID  VARCHAR2  N
     * orderNo  工单号  VARCHAR2  N
     * soOrderNo  SO订单号  VARCHAR2  N
     * soOrderId  SO订单ID  VARCHAR2  N
     * plannerId  计划员ID  VARCHAR2  N
     * customerCode  客户代码  VARCHAR2  N
     * customerPartNo  客户零件号  VARCHAR2  N
     * materialsCodeId  物资编码ID  VARCHAR2  N
     * flag  标识  VARCHAR2  N
     * priceStatus  行价格状态  VARCHAR2  N
     * feedbackAdjustDate2  反馈时间2  DATE  N
     * feedbackAdjustQty2  反馈数量2  NUMBER  N
     * feedbackAdjustDate3  反馈时间3  DATE  N
     * feedbackAdjustQty3  反馈数量3  NUMBER  N
     * standPrice  市场参考价  NUMBER  N
     * feedbackAdjustPrice2  反馈单价2  NUMBER  N
     * feedbackAdjustPrice3  反馈单价3  NUMBER  N
     * feedbackAdjustPrice  反馈单价1  NUMBER  N
     * minConsumePrice  最低消费金额  NUMBER  N
     * deliverDate  送仓/到厂日期  DATE  N
     * feedbackNumber  反馈次数  NUMBER  N
     * priority  优先级  VARCHAR2  N
     * materialId  物资编码ID  NUMBER  N
     * isStricter  是否加严  VARCHAR2  N
     * isCheck  是否检验  VARCHAR2  N
     * contractNumber  合同号  VARCHAR2  N
     * receiveDate  接受日期  DATE  N
     * qualifiedQty  合格数量  NUMBER  N
     * nonQualifiedQty  不合格数量  NUMBER  N
     * requisitionLineId  采购申请行ID  NUMBER  N
     * inventoryUnit  库存单位  VARCHAR2  N
     * gapPrice  差异金额  NUMBER  N
     * remark  行备注  VARCHAR2  N
     * moq  MOQ  NUMBER  N
     * shipmentSite  出货站点  VARCHAR2  N
     * customerItemNo  客户物料号  VARCHAR2  N
     * noticeQty  已通知送货量  NUMBER  N
     * noticeReceiveUserId  通知接受人  NUMBER  N
     * lockOrderNo  锁定单号  VARCHAR2  N
     * lockLineNo  锁定行号  VARCHAR2  N
     * lockQty  锁定数量  NUMBER  N
     * respDesc  答交情况  VARCHAR2  N
     * free  免费  VARCHAR2  N
     * isClose  是否结算  VARCHAR2  N
     * uomCode  采购单位  VARCHAR2  N
     * returnType  回货类型  VARCHAR2  N
     * erpLineId  ERP订单行ID  NUMBER  N
     * carriageLineId  发运行ID  NUMBER  N
     * erpHeaderId  ERP订单头ID  NUMBER  N
     * groupTime  分组时间  VARCHAR2  N
     * poLineId  采购订单行ID  NUMBER  Y
     * poHeaderId  订单头ID  NUMBER  Y
     * lineNumber  行号  NUMBER  Y
     * nonTaxPrice  不含税单价  NUMBER  N
     * ladderPriceFlag  阶梯价标识（Y/N）  VARCHAR2  N
     * ladderQty  阶梯范围  NUMBER  N
     * accumulativeFlag  累计结算标识（Y/N）  VARCHAR2  N
     * demandDate  需求日期  DATE  N
     * receiveToOrganizationId  收货组织ID  NUMBER  N
     * status  行状态(PO_LINE_STATUS)  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * mayNoticeQty  可通知送货数量  NUMBER  N
     * onWayQty  在途数量(已创建送货单数量)  NUMBER  N
     * receivedQty  已接收数量  NUMBER  N
     * originalDemandQty  原需求数量  NUMBER  N
     * originalDemandDate  原需求日期  DATE  N
     * feedbackAdjustDate  反馈调整日期  DATE  N
     * feedbackAdjustQty  反馈调整数量  NUMBER  N
     * feedbackStatus  反馈状态  VARCHAR2  N
     * feedbackResult  反馈结果  VARCHAR2  N
     * rejectReason  供应商拒绝原因  VARCHAR2  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * sourceNumber  来源单号  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * returnQty  退货数量  VARCHAR2  N
     * erpPoNumber  ERP采购订单号  VARCHAR2  N
     * context  上下文  VARCHAR2  N
     * projectCategory  项目分类  VARCHAR2  N
     * projectType  项目类型  VARCHAR2  N
     * technicalTransNumber  技改编号  VARCHAR2  N
     * subprojectNumber  子项目编号  VARCHAR2  N
     * acceptanceProcessNumber  验收流程号  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * nonTaxTotalPrice  行总价(不含税)/元  NUMBER  N
     * taxTotalPrice  行总价(含税)/元  NUMBER  N
     * nonTaxActTotalPrice  实付行总价(不含税)/元  NUMBER  N
     * taxActTotalPrice  实付行总价(含税)/元  NUMBER  N
     * poLineCombId  采购订单合并行ID(含税)/元  NUMBER  N
     * poLineId  采购订单行ID  NUMBER  Y
     * poHeaderId  订单头ID  NUMBER  Y
     * lineNumber  行号  NUMBER  Y
     * itemId  物料ID，关联表：srm_base_items  NUMBER  N
     * itemName  物料名称  VARCHAR2  N
     * itemSpec  物料规格  VARCHAR2  N
     * categoryId  采购分类ID，关联表：srm_base_categories  NUMBER  N
     * demandQty  需求数量  NUMBER  N
     * minPoQty  最小采购量  NUMBER  N
     * taxPrice  含税单价  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    public JSONObject saveSrmAdjust(JSONObject jsonParams) throws Exception;


    /**
     * Description：查询需求
     * @param jsonParams 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    String findNeedInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：需求创建采购订单
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


    public JSONObject saveNeedToOrder(JSONObject jsonParams) throws Exception;


    /**
     * Description：月度查询采购订单
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONArray searchMonthOrder(JSONObject jsonParams) throws Exception;

    /**
     * Description：excle批量导入
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject saveList(JSONObject jsonParams) throws Exception;
    

    /**
     * Description：供应商查询采购订单
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	Pagination<SrmPoHeadersEntity_HI_RO> findOrderInfoForSupplier(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;


    /**
     * Description：供应商确认
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	JSONObject saveSupplierConfirmOrderInfo(JSONObject jsonParams) throws Exception;
	
	

    /**
     * Description：供应商汇总查询
     * @param jsonParams 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	Pagination<SrmPoHeadersEntity_HI_RO> findSupplierGatherOrderInfo(JSONObject jsonParams,Integer pageIndex,Integer pageRows) throws Exception;


    /**
     * Description：供应商查询采购订单头
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	List<SrmPoHeadersEntity_HI_RO> findOrderHeaderBySupplier(JSONObject jsonParams) throws Exception;


    /**
     * Description：从合同转订单
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	JSONObject findOrderHeaderByContract(JSONObject jsonParams) throws Exception;



    /**
     * Description：供应商查询采购订单行
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	List<SrmPoHeadersEntity_HI_RO> findOrderLineBySupplier(JSONObject jsonParams) throws Exception;


    /**
     * Description：查找订单版本
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	public List<SrmPoHeadersEntity_HI_RO> findPoVersions(JSONObject jsonParams) throws Exception;

    /**
     * Description：保存，更新，提交订单信息
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

    JSONObject saveOrSubmitOrderInfo(JSONObject jsonParams)throws Exception;

    /**
     * Description：招标-已完成-创建采购订单
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * winningSupplierQuantity  推荐中标供应商数量  NUMBER  N
     * winningWay  推荐中标方式，关联表：SAAF_LOOKUP_VALUES（SNBC_ZB_WINNING_WAY）  VARCHAR2  N
     * oaCode  OA编号  VARCHAR2  N
     * technologyBidFlag  是否是技术标（Y/N）  VARCHAR2  N
     * technologyFileId  技术标附件id  NUMBER  N
     * expenseConfirm  是否费用确认（Y/N）  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * exchangeRateDate  汇率日期  DATE  N
     * exchangeRate  汇率  NUMBER  N
     * itemType  洽谈类型，快码：PON_AUCTION_ITEM_TYPE  VARCHAR2  N
     * itemPurchaseFlag  是否为代购物料（Y/N）  VARCHAR2  N
     * auctionHeaderId  洽谈ID  NUMBER  Y
     * auctionNumber  洽谈编号  VARCHAR2  Y
     * auctionTitle  洽谈标题  VARCHAR2  Y
     * orgId  洽谈主体单位，关联表：SAAF_INSTITUTION  NUMBER  Y
     * auctionType  洽谈类型，快码：PON_AUCTION_TYPE  VARCHAR2  N
     * contractType  合同类型（招标结果），快码：PON_AUCTION_RESULT  VARCHAR2  N
     * auctionStatus  洽谈状态，快码：PON_AUCTION_STATUS  VARCHAR2  N
     * buyerId  采购员(创建人)，关联表：SAAF_EMPLOYEES 的 EMPLOYEE_ID  NUMBER  N
     * invitingBidWay  邀标方式  VARCHAR2  N
     * receiveToOrganizationId  收货组织ID  NUMBER  N
     * receiveToAddress  收货/收单地址  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * paymentConditionUpdateFlag  允许供应商修改付款条件（Y/N）  VARCHAR2  N
     * subsectionFlag  分段价格（Y/N）  VARCHAR2  N
     * bidStartDate  报价开始时间  DATE  N
     * bidEndDate  报价截止时间  DATE  N
     * currencyCode  币种，关联表：SAAF_LOOKUP_VALUES（BANK_CURRENCY）  VARCHAR2  N
     * numberPriceDecimals  报价的小数位  NUMBER  N
     * allowUpdateTaxRate  允许修改税率（Y/N）  VARCHAR2  N
     * showCurrentRoundMinPrice  向供应商展示本轮最低价格（Y/N）  VARCHAR2  N
     * showCurrentRoundRanking  向供应商展示本轮排名（Y/N）  VARCHAR2  N
     * allItemBidFlag  供应商必须回应所有行（Y/N）  VARCHAR2  N
     * multipleBidFlag  允许多次报价（Y/N）  VARCHAR2  N
     * maxBidFrequency  多次报价次数  NUMBER  N
     * minDecreasingRange  最小降幅  NUMBER  N
     * bidBond  保证金金额  NUMBER  N
     * bidBondTerm  保证金缴纳期限  DATE  N
     * bidBondAccountNumber  保证金缴纳账户  VARCHAR2  N
     * bidBondBankName  保证金缴纳账户开户行  VARCHAR2  N
     * tenderCost  标书费用  NUMBER  N
     * publishApprovalStatus  发布审批状态  VARCHAR2  N
     * publishApprovalDate  发布批准时间  DATE  N
     * publishDate  发布时间  DATE  N
     * closeBiddingDate  截标时间  DATE  N
     * noteToSupplier  给供应商的附注  VARCHAR2  N
     * noteToJury  给评标小组的附注  VARCHAR2  N
     * toSupplierFileId  给供应商的附件ID  NUMBER  N
     * toJuryFileId  给评标小组的附件ID  NUMBER  N
     * allowScoreFlag  是否可评分标识  VARCHAR2  N
     * rounds  第几轮  NUMBER  N
     * firstRound  第一轮ID  NUMBER  N
     * lastRound  上一轮ID  NUMBER  N
     * auctionRoundNumber  洽谈轮次编号  VARCHAR2  N
     * openBiddingFlag  是否开标（Y/N）  VARCHAR2  N
     * openBiddingDate  开标时间  DATE  N
     * judgeCompleteDate  评标完成时间  DATE  N
     * awardStatus  决标状态  VARCHAR2  N
     * awardApprovalStatus  决标审批状态  VARCHAR2  N
     * awardCompleteDate  决标完成时间  DATE  N
     * awardComments  决标说明  VARCHAR2  N
     * poCreateFlag  是否已创建采购订单（Y/N）  VARCHAR2  N
     * poCreateDate  采购订单创建时间  DATE  N
     * percent    NUMBER  N
     * taxRateCode  税率  VARCHAR2  N
     * auctionRoundNumber  洽谈轮次编号  VARCHAR2  N
     * openBiddingFlag  是否开标（Y/N）  VARCHAR2  N
     * openBiddingDate  开标时间  DATE  N
     * judgeCompleteDate  评标完成时间  DATE  N
     * awardStatus  决标状态  VARCHAR2  N
     * awardApprovalStatus  决标审批状态  VARCHAR2  N
     * awardCompleteDate  决标完成时间  DATE  N
     * awardComments  决标说明  VARCHAR2  N
     * poCreateFlag  是否已创建采购订单（Y/N）  VARCHAR2  N
     * poCreateDate  采购订单创建时间  DATE  N
     * templateId  合同模板ID  NUMBER  N
     * templateName  合同模板名称  VARCHAR2  N
     * templateCode  合同模板编码  VARCHAR2  N
     * itemType  寻源物料类型  VARCHAR2  N
     * ekpNumber  EKP编号  VARCHAR2  N
     * auctionHeaderId  洽谈ID  NUMBER  Y
     * auctionNumber  洽谈编号  VARCHAR2  Y
     * auctionTitle  洽谈标题  VARCHAR2  Y
     * orgId  洽谈主体单位，关联表：SAAF_INSTITUTION  NUMBER  Y
     * auctionType  洽谈类型，快码：PON_AUCTION_TYPE  VARCHAR2  N
     * contractType  合同类型（招标结果），快码：PON_AUCTION_RESULT  VARCHAR2  N
     * auctionStatus  洽谈状态，快码：PON_AUCTION_STATUS  VARCHAR2  N
     * buyerId  采购员，关联表：SAAF_EMPLOYEES 的 EMPLOYEE_ID  NUMBER  N
     * invitingBidWay  邀标方式  VARCHAR2  N
     * receiveToOrganizationId  收货组织ID  NUMBER  N
     * receiveToAddress  收货/收单地址  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * paymentConditionUpdateFlag  允许供应商修改付款条件（Y/N）  VARCHAR2  N
     * subsectionFlag  分段价格（Y/N）  VARCHAR2  N
     * bidStartDate  报价开始时间  DATE  N
     * bidEndDate  报价截止时间  DATE  N
     * currencyCode  币种，关联表：SAAF_LOOKUP_VALUES（BANK_CURRENCY）  VARCHAR2  N
     * numberPriceDecimals  报价的小数位  NUMBER  N
     * allowUpdateTaxRate  允许修改税率（Y/N）  VARCHAR2  N
     * showCurrentRoundMinPrice  向供应商展示本轮最低价格（Y/N）  VARCHAR2  N
     * showCurrentRoundRanking  向供应商展示本轮排名（Y/N）  VARCHAR2  N
     * allItemBidFlag  供应商必须回应所有行（Y/N）  VARCHAR2  N
     * multipleBidFlag  允许多次报价（Y/N）  VARCHAR2  N
     * maxBidFrequency  多次报价次数  NUMBER  N
     * minDecreasingRange  最小降幅  NUMBER  N
     * bidBond  保证金金额  NUMBER  N
     * bidBondTerm  保证金缴纳期限  DATE  N
     * bidBondAccountNumber  保证金缴纳账户  VARCHAR2  N
     * bidBondBankName  保证金缴纳账户开户行  VARCHAR2  N
     * tenderCost  标书费用  NUMBER  N
     * publishApprovalStatus  发布审批状态  VARCHAR2  N
     * publishApprovalDate  发布批准时间  DATE  N
     * publishDate  发布时间  DATE  N
     * closeBiddingDate  截标时间  DATE  N
     * noteToSupplier  给供应商的附注  VARCHAR2  N
     * noteToJury  给评标小组的附注  VARCHAR2  N
     * toSupplierFileId  给供应商的附件ID  NUMBER  N
     * toJuryFileId  给评标小组的附件ID  NUMBER  N
     * allowScoreFlag  是否可评分标识  VARCHAR2  N
     * rounds  第几轮  NUMBER  N
     * firstRound  第一轮ID  NUMBER  N
     * lastRound  上一轮ID  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    JSONObject updateAndCreatePurchaseOrders(JSONObject jsonParams)throws Exception;


    /**
     * Description：采购订单内部操作(订单头) 拒绝,确认,审批,关闭,取消,变更
     * @param jsonParams 条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	JSONObject updateOrderHeaderStatus(JSONObject jsonParams) throws Exception;


    /**
     * Description：采购订单内部操作(订单行)
     * 拒绝,确认,审批,关闭,取消,变更
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */

	JSONObject updateOrderLineStatus(JSONObject jsonParams);

    /**
     * Description：采购订单接口（数据输入）——保存/修改一个采购订单所有信息（用于外部访问的接口） 需要提供用户和密码
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
	JSONObject saveOrderInfoForExternal(JSONObject jsonParams, Integer userId);


    /**
     * Description：采购订单接口（数据输出）——查询采购订单所有信息（用于外部访问的接口）
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	Map<String, Object> findOrderInfoForExternal(JSONObject jsonParams);


    /**
     * Description：计算含税价
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	SrmPoLinesEntity_HI findComputerTaxPrice(JSONObject jsonParams);

    /**
     * Description：批量导入订单行数据
     * @param jsonParams 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */

    JSONObject saveImportOrderLineInfo(JSONObject jsonParams);


    /**
     * Description：查询物料LOV
     * @param jsonParams 查询条件参数
     *@param pageIndex 页码
     *@param pageRows  行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	Pagination<SrmPoHeadersEntity_HI_RO> findItemListByASL(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;


    /**
     * Description：检查订单头状态
     * @param header 订单头数据
     *@param userId
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	public void updatePoStatus(SrmPoHeadersEntity_HI header, Integer userId);

    /**
     * Description：采购订单结算
     * @param jsonParams 条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject settleToEbs(JSONObject jsonParams) throws Exception;


    /**
     * Description：获取EBS技改编号
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject savePoTechnicalNum() throws Exception;


    /**
     * Description：获取EBS子项目编号
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject savePoSubprojectNum() throws Exception;

    /**
     * Description：获取EBS技改编号-保存
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    public Integer updateSrmPoTechnicalNum(JSONArray businessData) throws Exception;

    /**
     * Description：获取EBS子项目编号-保存
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    public Integer updateSrmPoSubprojectNum(JSONArray businessData) throws Exception;
    /**
     * Description：合同采购订单详情查询采购订单
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
    Pagination<SrmPoHeadersEntity_HI_RO> findOrderInfoByContract(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

}

