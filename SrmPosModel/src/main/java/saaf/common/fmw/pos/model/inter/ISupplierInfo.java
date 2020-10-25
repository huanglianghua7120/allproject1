package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosQualificationInfoEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierInfoEntity_HI_RO;

import java.util.List;
import java.util.Map;
/**
 * 保存供应商及其子表信息（档案自助维护/内部创建供应商）
 *
 * @param
 * @return
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISupplierInfo {

    /**
     * 校验供应商的基础信息的必填项——供应商接口（数据输入）
     * @param supplierInfo
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public String judgeSpaceSupplierInfo(SrmPosSupplierInfoEntity_HI supplierInfo);
    /**
     * 供应商接口（数据输入）——保存/修改一个供应商所有信息（用于外部访问的接口）
     * @param jsonParams
     * @param userId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject saveSupplierAllExternal(JSONObject jsonParams,Integer userId);

    /**
     * 供应商接口（数据输出）——查询供应商所有信息（用于外部访问的接口）
     * 需要提供用户和密码
     * @param jsonParams
     * @param userId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Map<String, Object> pushFindSupplierListExternal(JSONObject jsonParams,Integer userId);
    /**
     * 查询引入进度
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmPosSupplierInfoEntity_HI_RO> findIntroducingProgress(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 判断供应商校验（公司名称，营业执照号，登录账号）-档案自助维护/内部创建供应商
     * @param checkList
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public String findSupplierCheck(Map<String, Object> checkList);
    /**
     * Description：保存供应商及其子表信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * supplierId  供应商ID  NUMBER  Y
     * supplierNumber  供应商编码  VARCHAR2  N
     * supplierName  供应商名称  VARCHAR2  Y
     * supplierShortName  供应商简称  VARCHAR2  N
     * supplierType  供应商类型(POS_SUPPLIER_TYPE)  VARCHAR2  N
     * supplierClassify  供应商类别(POS_SUPPLIER_CLASSIFY)  VARCHAR2  N
     * supplierIndustry  供应商行业(POS_SUPPLIER_INDUSTRY)  VARCHAR2  N
     * supplierStatus  供应商状态(POS_SUPPLIER_STATUS)  VARCHAR2  N
     * homeUrl  企业网址  VARCHAR2  N
     * companyPhone  公司电话  VARCHAR2  N
     * companyFax  公司传真  VARCHAR2  N
     * relatedSupplierId  关联供应商ID   NUMBER  N
     * parentSupplierId  父供应商ID   NUMBER  N
     * staffNum  员工数量  NUMBER  N
     * floorArea  占地面积  VARCHAR2  N
     * companyDescription  公司简介  CLOB  N
     * purchaseFlag  可采购标识  VARCHAR2  N
     * paymentFlag  可付款标识  VARCHAR2  N
     * finClassify  (废弃)财务分类(POS_FIN_CLASSIFY)  VARCHAR2  N
     * settleAcctType  (废弃)结算方式(POS_SETTLE_ACCT_TYPE)  VARCHAR2  N
     * acctCheckStaff  (废弃)对账人员编码(POS_ACCT_CHECK_CREW)  VARCHAR2  N
     * acctCheckType  (废弃)对账方式(POS_ACCT_CHECK_TYPE)  VARCHAR2  N
     * passU9Flag  (废弃)传U9标识  VARCHAR2  N
     * supplierFileId  公司简介附件  NUMBER  N
     * posTax  (废弃)税组合(POS_TAX_LIST)  VARCHAR2  N
     * posAcctCondition  (废弃)立账条件(POS_ACCOUNT_CONDITION)  VARCHAR2  N
     * ableCheckOrderFlag  (废弃)允许确认采购标识  VARCHAR2  N
     * ableEditFlag  (废弃)是否可修改  VARCHAR2  N
     * address  (废弃)供应商地址  VARCHAR2  N
     * srmDelivery  (废弃)允许平台送货  VARCHAR2  N
     * logisticsSupplier  (废弃)是否为物流供应商  VARCHAR2  N
     * blacklistFlag  黑名单供应商(Y/N)  VARCHAR2  N
     * sourceCode  供应商来源(REGISTER:注册，CREATE:创建，其他类型为其他系统来源)  VARCHAR2  N
     * sourceId  供应商来源ID（当供应商数据来源于其他系统时才有）  VARCHAR2  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核通过时间  DATE  N
     * approvalComments  审核意见  VARCHAR2  N
     * isManufacturer  制造商(Y/N)  VARCHAR2  N
     * isAgent  代理商(Y/N)  VARCHAR2  N
     * isAvl  AVL供应商(Y/N)  VARCHAR2  N
     * isPurchaser  代购商(Y/N)  VARCHAR2  N
     * isTraders  贸易商(Y/N)  VARCHAR2  N
     * feedbackFileId  供应商反馈信息附件ID  NUMBER  N
     * managementScope  经营范围  VARCHAR2  N
     * registeredCapital  注册资金  VARCHAR2  N
     * supplierEnterpriseType  企业类型  VARCHAR2  N
     * outputSales  年产值/年销售额  VARCHAR2  N
     * paymentTerm  付款条件  VARCHAR2  N
     * paymentMethod  付款方式  VARCHAR2  N
     * taxonomy  税分类  VARCHAR2  N
     * interfaceStart  接口开始时间  DATE  N
     * returnType  回货方式  VARCHAR2  N
     * isOther  其他供应商(Y/N)  VARCHAR2  N
     * monthly  月结  NUMBER  N
     * parentCompany  母公司  VARCHAR2  N
     * selfOutletsNumber  自营网点数量  NUMBER  N
     * ownVehiclesNumber  自有车辆数量  NUMBER  N
     * callingVehiclesNumber  挂靠车辆数量  NUMBER  N
     * isAdvancedCertification  高级认证企业(Y/N)  VARCHAR2  N
     * isGeneralCertification  一般认证企业(Y/N)  VARCHAR2  N
     * isGeneralCredit  一般信用企业(Y/N)  VARCHAR2  N
     * isUntrustworthy  失信企业(Y/N)  VARCHAR2  N
     * managersNumber  管理人员数量  NUMBER  N
     * techniciansNumber  技术人员数量  NUMBER  N
     * workersNumber  工人数量  NUMBER  N
     * aeoFileId  AEO认证附件  NUMBER  N
     * supplierEbsNumber  供应商EBS编号  VARCHAR2  N
     * temporaryFlag  临时供应商标记(Y/N)  VARCHAR2  N
     * requestId  报文请求ID  VARCHAR2  N
     * companyDescription  供应商简介  CLOB  N
     * companyRegisteredAddress  公司注册地址  VARCHAR2  N
     * registeredCapital  注册资金(万)  VARCHAR2  N
     * independentLegalPersonFlag  是否独立法人  VARCHAR2  N
     * valueAddedTaxInvoiceFlag  能否开6个税点的增值税专用发票  VARCHAR2  N
     * valueAddedTaxInvoiceDesc  能否开6个税点的增值税专用发票-说明  VARCHAR2  N
     * associatedCompany  关联公司  VARCHAR2  N
     * region  意向服务区域  VARCHAR2  N
     * supplierEbsNumber  供应商EBS编号  VARCHAR2  N
     * requestId  报文请求ID  VARCHAR2  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierNumber  供应商编码  VARCHAR2  N
     * supplierName  供应商名称  VARCHAR2  Y
     * supplierShortName  供应商简称  VARCHAR2  N
     * supplierType  供应商类型(POS_SUPPLIER_TYPE)  VARCHAR2  N
     * supplierClassify  (废弃)供应商分类(POS_SUPPLIER_CLASSIFY)  VARCHAR2  N
     * supplierIndustry  供应商行业(POS_SUPPLIER_INDUSTRY)  VARCHAR2  N
     * supplierStatus  供应商状态(POS_SUPPLIER_STATUS)  VARCHAR2  N
     * homeUrl  公司官网  VARCHAR2  N
     * companyPhone  公司电话  VARCHAR2  N
     * companyFax  公司传真  VARCHAR2  N
     * relatedSupplierId  关联供应商ID   NUMBER  N
     * parentSupplierId  父供应商ID   NUMBER  N
     * staffNum  员工数量  NUMBER  N
     * floorArea  占地面积  VARCHAR2  N
     * purchaseFlag  可采购标识  VARCHAR2  N
     * paymentFlag  可付款标识  VARCHAR2  N
     * finClassify  (废弃)财务分类(POS_FIN_CLASSIFY)  VARCHAR2  N
     * settleAcctType  (废弃)结算方式(POS_SETTLE_ACCT_TYPE)  VARCHAR2  N
     * acctCheckStaff  (废弃)对账人员编码(POS_ACCT_CHECK_CREW)  VARCHAR2  N
     * acctCheckType  (废弃)对账方式(POS_ACCT_CHECK_TYPE)  VARCHAR2  N
     * passU9Flag  (废弃)传U9标识  VARCHAR2  N
     * supplierFileId  公司简介附件  NUMBER  N
     * posTax  (废弃)税组合(POS_TAX_LIST)  VARCHAR2  N
     * posAcctCondition  (废弃)立账条件(POS_ACCOUNT_CONDITION)  VARCHAR2  N
     * ableCheckOrderFlag  (废弃)允许确认采购标识  VARCHAR2  N
     * ableEditFlag  (废弃)是否可修改  VARCHAR2  N
     * address  (废弃)供应商地址  VARCHAR2  N
     * srmDelivery  (废弃)允许平台送货  VARCHAR2  N
     * logisticsSupplier  (废弃)是否为物流供应商  VARCHAR2  N
     * blacklistFlag  黑名单供应商(Y/N)  VARCHAR2  N
     * sourceCode  供应商来源(REGISTER:注册，CREATE:创建，其他类型为其他系统来源)  VARCHAR2  N
     * sourceId  供应商来源ID（当供应商数据来源于其他系统时才有）  VARCHAR2  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核通过时间  DATE  N
     * approvalComments  审核意见  VARCHAR2  N
     * gradeLineId  供应商级别行ID，srm_pos_supplier_grade_lines  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    public JSONObject saveSupplierAll(JSONObject jsonParams) throws Exception;

    /**
     * 供应商基础信息查询（带有分页）
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierInfoEntity_HI_RO> findSupplierInfoForSelf(JSONObject jsonParams, Integer pageIndex, Integer pageRows);
    /**
     * 供应商基础信息查询（不带分页）
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmPosSupplierInfoEntity_HI_RO> findSupplierInfoForSelf(JSONObject jsonParams);
    /**
     * 删除供应商
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteSupplier(JSONObject params) throws Exception;
    /**
     * Description：注册审核
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * supplierId  供应商ID  NUMBER  Y
     * supplierNumber  供应商编码  VARCHAR2  N
     * supplierName  供应商名称  VARCHAR2  Y
     * supplierShortName  供应商简称  VARCHAR2  N
     * supplierType  供应商类型(POS_SUPPLIER_TYPE)  VARCHAR2  N
     * supplierClassify  供应商类别(POS_SUPPLIER_CLASSIFY)  VARCHAR2  N
     * supplierIndustry  供应商行业(POS_SUPPLIER_INDUSTRY)  VARCHAR2  N
     * supplierStatus  供应商状态(POS_SUPPLIER_STATUS)  VARCHAR2  N
     * homeUrl  企业网址  VARCHAR2  N
     * companyPhone  公司电话  VARCHAR2  N
     * companyFax  公司传真  VARCHAR2  N
     * relatedSupplierId  关联供应商ID   NUMBER  N
     * parentSupplierId  父供应商ID   NUMBER  N
     * staffNum  员工数量  NUMBER  N
     * floorArea  占地面积  VARCHAR2  N
     * companyDescription  公司简介  CLOB  N
     * purchaseFlag  可采购标识  VARCHAR2  N
     * paymentFlag  可付款标识  VARCHAR2  N
     * finClassify  (废弃)财务分类(POS_FIN_CLASSIFY)  VARCHAR2  N
     * settleAcctType  (废弃)结算方式(POS_SETTLE_ACCT_TYPE)  VARCHAR2  N
     * acctCheckStaff  (废弃)对账人员编码(POS_ACCT_CHECK_CREW)  VARCHAR2  N
     * acctCheckType  (废弃)对账方式(POS_ACCT_CHECK_TYPE)  VARCHAR2  N
     * passU9Flag  (废弃)传U9标识  VARCHAR2  N
     * supplierFileId  公司简介附件  NUMBER  N
     * posTax  (废弃)税组合(POS_TAX_LIST)  VARCHAR2  N
     * posAcctCondition  (废弃)立账条件(POS_ACCOUNT_CONDITION)  VARCHAR2  N
     * ableCheckOrderFlag  (废弃)允许确认采购标识  VARCHAR2  N
     * ableEditFlag  (废弃)是否可修改  VARCHAR2  N
     * address  (废弃)供应商地址  VARCHAR2  N
     * srmDelivery  (废弃)允许平台送货  VARCHAR2  N
     * logisticsSupplier  (废弃)是否为物流供应商  VARCHAR2  N
     * blacklistFlag  黑名单供应商(Y/N)  VARCHAR2  N
     * sourceCode  供应商来源(REGISTER:注册，CREATE:创建，其他类型为其他系统来源)  VARCHAR2  N
     * sourceId  供应商来源ID（当供应商数据来源于其他系统时才有）  VARCHAR2  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核通过时间  DATE  N
     * approvalComments  审核意见  VARCHAR2  N
     * isManufacturer  制造商(Y/N)  VARCHAR2  N
     * isAgent  代理商(Y/N)  VARCHAR2  N
     * isAvl  AVL供应商(Y/N)  VARCHAR2  N
     * isPurchaser  代购商(Y/N)  VARCHAR2  N
     * isTraders  贸易商(Y/N)  VARCHAR2  N
     * feedbackFileId  供应商反馈信息附件ID  NUMBER  N
     * managementScope  经营范围  VARCHAR2  N
     * registeredCapital  注册资金  VARCHAR2  N
     * supplierEnterpriseType  企业类型  VARCHAR2  N
     * outputSales  年产值/年销售额  VARCHAR2  N
     * paymentTerm  付款条件  VARCHAR2  N
     * paymentMethod  付款方式  VARCHAR2  N
     * taxonomy  税分类  VARCHAR2  N
     * interfaceStart  接口开始时间  DATE  N
     * returnType  回货方式  VARCHAR2  N
     * isOther  其他供应商(Y/N)  VARCHAR2  N
     * monthly  月结  NUMBER  N
     * parentCompany  母公司  VARCHAR2  N
     * selfOutletsNumber  自营网点数量  NUMBER  N
     * ownVehiclesNumber  自有车辆数量  NUMBER  N
     * callingVehiclesNumber  挂靠车辆数量  NUMBER  N
     * isAdvancedCertification  高级认证企业(Y/N)  VARCHAR2  N
     * isGeneralCertification  一般认证企业(Y/N)  VARCHAR2  N
     * isGeneralCredit  一般信用企业(Y/N)  VARCHAR2  N
     * isUntrustworthy  失信企业(Y/N)  VARCHAR2  N
     * managersNumber  管理人员数量  NUMBER  N
     * techniciansNumber  技术人员数量  NUMBER  N
     * workersNumber  工人数量  NUMBER  N
     * aeoFileId  AEO认证附件  NUMBER  N
     * supplierEbsNumber  供应商EBS编号  VARCHAR2  N
     * temporaryFlag  临时供应商标记(Y/N)  VARCHAR2  N
     * requestId  报文请求ID  VARCHAR2  N
     * companyDescription  供应商简介  CLOB  N
     * companyRegisteredAddress  公司注册地址  VARCHAR2  N
     * registeredCapital  注册资金(万)  VARCHAR2  N
     * independentLegalPersonFlag  是否独立法人  VARCHAR2  N
     * valueAddedTaxInvoiceFlag  能否开6个税点的增值税专用发票  VARCHAR2  N
     * valueAddedTaxInvoiceDesc  能否开6个税点的增值税专用发票-说明  VARCHAR2  N
     * associatedCompany  关联公司  VARCHAR2  N
     * region  意向服务区域  VARCHAR2  N
     * supplierEbsNumber  供应商EBS编号  VARCHAR2  N
     * requestId  报文请求ID  VARCHAR2  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierNumber  供应商编码  VARCHAR2  N
     * supplierName  供应商名称  VARCHAR2  Y
     * supplierShortName  供应商简称  VARCHAR2  N
     * supplierType  供应商类型(POS_SUPPLIER_TYPE)  VARCHAR2  N
     * supplierClassify  (废弃)供应商分类(POS_SUPPLIER_CLASSIFY)  VARCHAR2  N
     * supplierIndustry  供应商行业(POS_SUPPLIER_INDUSTRY)  VARCHAR2  N
     * supplierStatus  供应商状态(POS_SUPPLIER_STATUS)  VARCHAR2  N
     * homeUrl  公司官网  VARCHAR2  N
     * companyPhone  公司电话  VARCHAR2  N
     * companyFax  公司传真  VARCHAR2  N
     * relatedSupplierId  关联供应商ID   NUMBER  N
     * parentSupplierId  父供应商ID   NUMBER  N
     * staffNum  员工数量  NUMBER  N
     * floorArea  占地面积  VARCHAR2  N
     * purchaseFlag  可采购标识  VARCHAR2  N
     * paymentFlag  可付款标识  VARCHAR2  N
     * finClassify  (废弃)财务分类(POS_FIN_CLASSIFY)  VARCHAR2  N
     * settleAcctType  (废弃)结算方式(POS_SETTLE_ACCT_TYPE)  VARCHAR2  N
     * acctCheckStaff  (废弃)对账人员编码(POS_ACCT_CHECK_CREW)  VARCHAR2  N
     * acctCheckType  (废弃)对账方式(POS_ACCT_CHECK_TYPE)  VARCHAR2  N
     * passU9Flag  (废弃)传U9标识  VARCHAR2  N
     * supplierFileId  公司简介附件  NUMBER  N
     * posTax  (废弃)税组合(POS_TAX_LIST)  VARCHAR2  N
     * posAcctCondition  (废弃)立账条件(POS_ACCOUNT_CONDITION)  VARCHAR2  N
     * ableCheckOrderFlag  (废弃)允许确认采购标识  VARCHAR2  N
     * ableEditFlag  (废弃)是否可修改  VARCHAR2  N
     * address  (废弃)供应商地址  VARCHAR2  N
     * srmDelivery  (废弃)允许平台送货  VARCHAR2  N
     * logisticsSupplier  (废弃)是否为物流供应商  VARCHAR2  N
     * blacklistFlag  黑名单供应商(Y/N)  VARCHAR2  N
     * sourceCode  供应商来源(REGISTER:注册，CREATE:创建，其他类型为其他系统来源)  VARCHAR2  N
     * sourceId  供应商来源ID（当供应商数据来源于其他系统时才有）  VARCHAR2  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核通过时间  DATE  N
     * approvalComments  审核意见  VARCHAR2  N
     * gradeLineId  供应商级别行ID，srm_pos_supplier_grade_lines  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    public JSONObject saveRegisterAuditSta(JSONObject params) throws Exception;
    /**
     * 注册审核-重置密码
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject updateResetPassWord(JSONObject params) throws Exception;
    /**
     * 保存供应商的档案信息
     *
     * @param checkList
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public String findCheckForSupplier(Map<String, Object> checkList);
    /**
     * 查询供应商
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierInfoEntity_HI_RO> findSupplierInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * Description：供应商信息保存
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * supplierId  供应商ID  NUMBER  Y
     * supplierNumber  供应商编码  VARCHAR2  N
     * supplierName  供应商名称  VARCHAR2  Y
     * supplierShortName  供应商简称  VARCHAR2  N
     * supplierType  供应商类型(POS_SUPPLIER_TYPE)  VARCHAR2  N
     * supplierClassify  供应商类别(POS_SUPPLIER_CLASSIFY)  VARCHAR2  N
     * supplierIndustry  供应商行业(POS_SUPPLIER_INDUSTRY)  VARCHAR2  N
     * supplierStatus  供应商状态(POS_SUPPLIER_STATUS)  VARCHAR2  N
     * homeUrl  企业网址  VARCHAR2  N
     * companyPhone  公司电话  VARCHAR2  N
     * companyFax  公司传真  VARCHAR2  N
     * relatedSupplierId  关联供应商ID   NUMBER  N
     * parentSupplierId  父供应商ID   NUMBER  N
     * staffNum  员工数量  NUMBER  N
     * floorArea  占地面积  VARCHAR2  N
     * companyDescription  公司简介  CLOB  N
     * purchaseFlag  可采购标识  VARCHAR2  N
     * paymentFlag  可付款标识  VARCHAR2  N
     * finClassify  (废弃)财务分类(POS_FIN_CLASSIFY)  VARCHAR2  N
     * settleAcctType  (废弃)结算方式(POS_SETTLE_ACCT_TYPE)  VARCHAR2  N
     * acctCheckStaff  (废弃)对账人员编码(POS_ACCT_CHECK_CREW)  VARCHAR2  N
     * acctCheckType  (废弃)对账方式(POS_ACCT_CHECK_TYPE)  VARCHAR2  N
     * passU9Flag  (废弃)传U9标识  VARCHAR2  N
     * supplierFileId  公司简介附件  NUMBER  N
     * posTax  (废弃)税组合(POS_TAX_LIST)  VARCHAR2  N
     * posAcctCondition  (废弃)立账条件(POS_ACCOUNT_CONDITION)  VARCHAR2  N
     * ableCheckOrderFlag  (废弃)允许确认采购标识  VARCHAR2  N
     * ableEditFlag  (废弃)是否可修改  VARCHAR2  N
     * address  (废弃)供应商地址  VARCHAR2  N
     * srmDelivery  (废弃)允许平台送货  VARCHAR2  N
     * logisticsSupplier  (废弃)是否为物流供应商  VARCHAR2  N
     * blacklistFlag  黑名单供应商(Y/N)  VARCHAR2  N
     * sourceCode  供应商来源(REGISTER:注册，CREATE:创建，其他类型为其他系统来源)  VARCHAR2  N
     * sourceId  供应商来源ID（当供应商数据来源于其他系统时才有）  VARCHAR2  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核通过时间  DATE  N
     * approvalComments  审核意见  VARCHAR2  N
     * isManufacturer  制造商(Y/N)  VARCHAR2  N
     * isAgent  代理商(Y/N)  VARCHAR2  N
     * isAvl  AVL供应商(Y/N)  VARCHAR2  N
     * isPurchaser  代购商(Y/N)  VARCHAR2  N
     * isTraders  贸易商(Y/N)  VARCHAR2  N
     * feedbackFileId  供应商反馈信息附件ID  NUMBER  N
     * managementScope  经营范围  VARCHAR2  N
     * registeredCapital  注册资金  VARCHAR2  N
     * supplierEnterpriseType  企业类型  VARCHAR2  N
     * outputSales  年产值/年销售额  VARCHAR2  N
     * paymentTerm  付款条件  VARCHAR2  N
     * paymentMethod  付款方式  VARCHAR2  N
     * taxonomy  税分类  VARCHAR2  N
     * interfaceStart  接口开始时间  DATE  N
     * returnType  回货方式  VARCHAR2  N
     * isOther  其他供应商(Y/N)  VARCHAR2  N
     * monthly  月结  NUMBER  N
     * parentCompany  母公司  VARCHAR2  N
     * selfOutletsNumber  自营网点数量  NUMBER  N
     * ownVehiclesNumber  自有车辆数量  NUMBER  N
     * callingVehiclesNumber  挂靠车辆数量  NUMBER  N
     * isAdvancedCertification  高级认证企业(Y/N)  VARCHAR2  N
     * isGeneralCertification  一般认证企业(Y/N)  VARCHAR2  N
     * isGeneralCredit  一般信用企业(Y/N)  VARCHAR2  N
     * isUntrustworthy  失信企业(Y/N)  VARCHAR2  N
     * managersNumber  管理人员数量  NUMBER  N
     * techniciansNumber  技术人员数量  NUMBER  N
     * workersNumber  工人数量  NUMBER  N
     * aeoFileId  AEO认证附件  NUMBER  N
     * supplierEbsNumber  供应商EBS编号  VARCHAR2  N
     * temporaryFlag  临时供应商标记(Y/N)  VARCHAR2  N
     * requestId  报文请求ID  VARCHAR2  N
     * companyDescription  供应商简介  CLOB  N
     * companyRegisteredAddress  公司注册地址  VARCHAR2  N
     * registeredCapital  注册资金(万)  VARCHAR2  N
     * independentLegalPersonFlag  是否独立法人  VARCHAR2  N
     * valueAddedTaxInvoiceFlag  能否开6个税点的增值税专用发票  VARCHAR2  N
     * valueAddedTaxInvoiceDesc  能否开6个税点的增值税专用发票-说明  VARCHAR2  N
     * associatedCompany  关联公司  VARCHAR2  N
     * region  意向服务区域  VARCHAR2  N
     * supplierEbsNumber  供应商EBS编号  VARCHAR2  N
     * requestId  报文请求ID  VARCHAR2  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierNumber  供应商编码  VARCHAR2  N
     * supplierName  供应商名称  VARCHAR2  Y
     * supplierShortName  供应商简称  VARCHAR2  N
     * supplierType  供应商类型(POS_SUPPLIER_TYPE)  VARCHAR2  N
     * supplierClassify  (废弃)供应商分类(POS_SUPPLIER_CLASSIFY)  VARCHAR2  N
     * supplierIndustry  供应商行业(POS_SUPPLIER_INDUSTRY)  VARCHAR2  N
     * supplierStatus  供应商状态(POS_SUPPLIER_STATUS)  VARCHAR2  N
     * homeUrl  公司官网  VARCHAR2  N
     * companyPhone  公司电话  VARCHAR2  N
     * companyFax  公司传真  VARCHAR2  N
     * relatedSupplierId  关联供应商ID   NUMBER  N
     * parentSupplierId  父供应商ID   NUMBER  N
     * staffNum  员工数量  NUMBER  N
     * floorArea  占地面积  VARCHAR2  N
     * purchaseFlag  可采购标识  VARCHAR2  N
     * paymentFlag  可付款标识  VARCHAR2  N
     * finClassify  (废弃)财务分类(POS_FIN_CLASSIFY)  VARCHAR2  N
     * settleAcctType  (废弃)结算方式(POS_SETTLE_ACCT_TYPE)  VARCHAR2  N
     * acctCheckStaff  (废弃)对账人员编码(POS_ACCT_CHECK_CREW)  VARCHAR2  N
     * acctCheckType  (废弃)对账方式(POS_ACCT_CHECK_TYPE)  VARCHAR2  N
     * passU9Flag  (废弃)传U9标识  VARCHAR2  N
     * supplierFileId  公司简介附件  NUMBER  N
     * posTax  (废弃)税组合(POS_TAX_LIST)  VARCHAR2  N
     * posAcctCondition  (废弃)立账条件(POS_ACCOUNT_CONDITION)  VARCHAR2  N
     * ableCheckOrderFlag  (废弃)允许确认采购标识  VARCHAR2  N
     * ableEditFlag  (废弃)是否可修改  VARCHAR2  N
     * address  (废弃)供应商地址  VARCHAR2  N
     * srmDelivery  (废弃)允许平台送货  VARCHAR2  N
     * logisticsSupplier  (废弃)是否为物流供应商  VARCHAR2  N
     * blacklistFlag  黑名单供应商(Y/N)  VARCHAR2  N
     * sourceCode  供应商来源(REGISTER:注册，CREATE:创建，其他类型为其他系统来源)  VARCHAR2  N
     * sourceId  供应商来源ID（当供应商数据来源于其他系统时才有）  VARCHAR2  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核通过时间  DATE  N
     * approvalComments  审核意见  VARCHAR2  N
     * gradeLineId  供应商级别行ID，srm_pos_supplier_grade_lines  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    public JSONObject saveSupplierInfo(JSONObject jsonParams) throws Exception;
    /**
     * 提交审批供应商的档案信息
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject saveApproveUpdate(JSONObject jsonParams) throws Exception;
    /**
     * Description：保存供应商地址信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * supplierAddressId  供应商地址ID  NUMBER  Y
     * supplierId  供应商ID  NUMBER  Y
     * addressName  地址名称  VARCHAR2  N
     * country  国家(BASE_COUNTRY)  VARCHAR2  N
     * province  省(州)  VARCHAR2  N
     * city  城市  VARCHAR2  N
     * county  县(区)  VARCHAR2  N
     * detailAddress  详细地址  VARCHAR2  N
     * invalidDate  失效时间  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * contacts  联系人ID  NUMBER  N
     * addressCategory  地址类别，快码:ADDRESS_CATEGORY  VARCHAR2  N
     * supplierAddressId  供应商地址ID  NUMBER  Y
     * supplierId  供应商ID  NUMBER  Y
     * addressName  地址名称  VARCHAR2  N
     * country  国家(BASE_COUNTRY)  VARCHAR2  N
     * province  省(州)  VARCHAR2  N
     * city  城市  VARCHAR2  N
     * county  县(区)  VARCHAR2  N
     * detailAddress  详细地址  VARCHAR2  N
     * invalidDate  失效时间  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    JSONObject saveSupplierAddress(JSONObject paramJSON)throws Exception;
    /**
     * 查询供应商lov
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierInfoEntity_HI_RO> findSupplierUpdateLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 获取供应商编号
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public String getSupplierNumber();
    
    /**
     * 查询供应商LOV
     * @param jsonParam
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	public Pagination<SrmPosSupplierInfoEntity_HI_RO> findSupplierLov(JSONObject jsonParam, Integer pageIndex,
			Integer pageRows) throws Exception;

    /**
     * 供应商状态分布报表查询
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Map<String, Object> findSupplierStatusDistribution(JSONObject jsonParam);

    /**
     * 未产生交易的供应商数量报表
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Map<String, Object> findSupplierTransactionSituation(JSONObject jsonParam);
    /**
     * 供应商引入退出报表
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Map<String, Object> findIntroductionAndQuit(JSONObject jsonParam);
    /**
     * 供应商分级统计报表查询
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Map<String, Object> findSupplierGradeDistribution(JSONObject jsonParam);
    /**
     * 采购总金额统计报表
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Map<String, Object> findPurchaseAmountCount(JSONObject jsonParam);
    /**
     * 采购总金额统计报表详细
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierInfoEntity_HI_RO> findPurchaseAmountCountInfo(JSONObject jsonParams, Integer pageIndex,
                                                                                  Integer pageRows) throws Exception;

    /**
     * 查询供应商列表
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierInfoEntity_HI_RO> findSupplierList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：供应商档案信息——发送到EBS系统
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-11-20     zhj             创建
     * ==============================================================================
     */
    public JSONObject updateSrmPosSupplierInfoSendToEBS(JSONObject jsonParams) throws Exception;

    /**
     * Description：供应商冻结、恢复、退出——发送到EBS系统
     * @param supplierId  供应商ID
     * @param actionCode  创建和修改业务为NORMAL，冻结和冻结恢复为FREEZE
     * @param freezeRecoveryFlag 是否冻结恢复(Y/N)，Y为冻结恢复
     * @param frozenId srm_pos_frozen_info的主键ID
     * @param userId 当前用户Id
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-12-18     zhj             创建
     * ==============================================================================
     */
    public JSONObject updateSrmPosSupplierInfoSendToEBSInfo(Integer supplierId,String actionCode,String freezeRecoveryFlag,Integer frozenId,Integer userId) throws Exception;
}