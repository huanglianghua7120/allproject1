package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeaderArchivesEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLineArchivesEntity_HI_RO;

import java.util.List;
/**
 * Project Name：ISrmPoHeaderArchives
 * Company Name：SIE
 * Program Name：
 * Description：采购订单历史
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */

public interface ISrmPoHeaderArchives {

    /**
     * Description：查询采购订单历史头
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public List<SrmPoHeaderArchivesEntity_HI_RO> findPoHeaderArchivesBySupplier(JSONObject jsonParams) throws Exception;


    /**
     * Description：查询采购订单历史行
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public List<SrmPoHeaderArchivesEntity_HI_RO> findPoLineArchivesBySupplier(JSONObject jsonParams) throws Exception;


    /**
     * Description：保存订单历史
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * poHeaderId    NUMBER  Y
     * poNumber    VARCHAR2  Y
     * orgId    NUMBER  N
     * billToOrganizationId    NUMBER  N
     * poDocType    VARCHAR2  N
     * supplierId    NUMBER  N
     * supplierSiteId    NUMBER  N
     * currencyCode    VARCHAR2  N
     * taxRateCode    VARCHAR2  N
     * buyerId    NUMBER  N
     * returnGoodsType    VARCHAR2  N
     * paymentCondition    VARCHAR2  N
     * settlementWay    VARCHAR2  N
     * poVersions    NUMBER  N
     * status    VARCHAR2  N
     * approvalUserId    NUMBER  N
     * approvedDate    DATE  N
     * startDate    DATE  N
     * endDate    DATE  N
     * description    VARCHAR2  N
     * poFileId    NUMBER  N
     * agreementClause    VARCHAR2  N
     * sourceCode    VARCHAR2  N
     * sourceId    VARCHAR2  N
     * supplierReadTime    DATE  N
     * exchangeRate    NUMBER  N
     * exchangeRateDate    DATE  N
     * syncErpFlag    VARCHAR2  N
     * syncErpMsg    VARCHAR2  N
     * isGlobal    VARCHAR2  N
     * orderType    VARCHAR2  N
     * erpOrderNo    VARCHAR2  N
     * bazaarOrderNo    VARCHAR2  N
     * receiveToOrganizationId    NUMBER  N
     * buyerExecuteId    NUMBER  N
     * supPoFileId    NUMBER  N
     * shipLocationCode    VARCHAR2  N
     * shipToLocationCode    VARCHAR2  N
     * billToLocationCode    VARCHAR2  N
     * shipToLocationId    NUMBER  N
     * billToLocationId    NUMBER  N
     * organizationId    NUMBER  N
     * poArchiveId  采购历史头ID  NUMBER  Y
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
     * prNumber  采购申请单号  VARCHAR2  N
     * locationCode  收货地点  VARCHAR2  N
     * poType  类型  VARCHAR2  N
     * shipToOrganizationId  收货组织  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */
    public void savePoArchives(JSONObject jsonParams);
}
