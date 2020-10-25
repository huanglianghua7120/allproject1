package saaf.common.fmw.pos.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosQualificationInfoEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierInfoEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISaafUsers;
import saaf.common.fmw.pos.model.inter.ISupplierInfo;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Path("/supplierInfoServices")
@Component
public class SupplierInfoServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierInfoServices.class);
    private static final String PARAMS = "params";
    private static final String PAGE_INDEX = "pageIndex";
    private static final String PAGE_ROWS = "pageRows";
    @Autowired
    private ISupplierInfo iSupplierInfo;
    @Autowired
    private ISaafUsers iSaafUsers;  //pos模块的用户

    public SupplierInfoServices() {
        super();
    }

    /**
     * 供应商接口（数据输入）——保存/修改一个供应商所有信息（用于外部访问的接口）
     * 需要提供用户和密码
     *
     * @param params
     * @param userName
     * @param password
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("saveSupplierAllExternal")
    @Produces("application/json")
    public String saveSupplierAllExternal(@FormParam(PARAMS) String params, @FormParam("userName") String userName, @FormParam("password") String password) {
        LOGGER.info("参数params：" + params.toString() + "，参数userName：" + userName + "，password：" + password);
        if (StringUtils.isBlank(params)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可操作！", 0, null);
        }
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "登录异常，用户名或密码为空！", 0, null);
        }
        boolean flag = iSaafUsers.userLoginByDatabase(userName, password);  //验证用户登录
        if (!flag) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "登录异常，请输入正确的用户名和密码！", 0, null);
        }
        SaafUsersEntity_HI userEntity = iSaafUsers.userByDatabase(userName, password);
        Integer userId = null;
        if (null != userEntity) {
            userId = userEntity.getUserId();
        }
        try {
            JSONObject jsonParams = this.parseObjectExternal(params); //调用外部转换json 方法
            JSONObject jsondata = iSupplierInfo.saveSupplierAllExternal(jsonParams, userId);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.getString(DATA));
        } catch (Exception e) {
            LOGGER.error("--------------------------->操作失败！参数：" + params.toString() + "，异常：" + e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "操作失败！" + e, 0, null);
        }
    }

    /**
     * 供应商接口（数据输出）——查询供应商所有信息（用于外部访问的接口）
     * 需要提供用户和密码
     *
     * @param params
     * @param userName
     * @param password
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSupplierListExternal")
    @Produces("application/json")
    public String findSupplierListExternal(@FormParam(PARAMS) String params, @FormParam("userName") String userName, @FormParam("password") String password) {
        LOGGER.info("参数params：" + params.toString() + "，参数userName：" + userName + "，password：" + password);
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "登录异常，用户名或密码为空！", 0, null);
        }
        boolean flag = iSaafUsers.userLoginByDatabase(userName, password);  //验证用户登录
        if (!flag) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "登录异常，请输入正确的用户名和密码！", 0, null);
        }
        SaafUsersEntity_HI userEntity = iSaafUsers.userByDatabase(userName, password);
        Integer userId = null;
        if (null != userEntity) {
            userId = userEntity.getUserId();
        }
        try {
            JSONObject jsonParams = this.parseObjectExternal(params); //调用外部转换json 方法
            Map<String, Object> map = iSupplierInfo.pushFindSupplierListExternal(jsonParams, userId);
            return JSON.toJSONString(map);
        } catch (Exception e) {
            LOGGER.error("--------------------------->操作失败！参数：" + params.toString() + "，异常：" + e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "查询失败！" + e, 0, null);
        }
    }

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

    @POST
    @Path("saveSupplierAll")
    @Produces("application/json")
    public String saveSupplierAll(@FormParam("params") String params) {
        LOGGER.info("参数：" + params.toString());
        if (StringUtils.isBlank(params)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可操作！", 0, null);
        }
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = iSupplierInfo.saveSupplierAll(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.getString(DATA));
        } catch (Exception e) {
            LOGGER.error("--------------------------->操作失败！参数：" + params.toString() + "，异常：" + e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "操作失败！", 0, null);
        }
    }

    /**
     * 查询供应商
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSupplierInfo")
    public String findSupplierInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info("findSupplierInfo参数:" + params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmPosSupplierInfoEntity_HI_RO> pag = iSupplierInfo.findSupplierInfo(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!", 0, null));
        }
    }

    /**
     * 供应商基础信息查询
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSupplierInfoForSelf")
    @Produces("application/json")
    public String findSupplierInfoForSelf(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info("findSupplierInfoForSelf参数：" + params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            if ("EX".equals(paramJSON.getString("varPlatformCode"))) { //是供应商查询
                paramJSON.put("supplier_id", paramJSON.getIntValue("varSupplierId"));
            }
            Pagination<SrmPosSupplierInfoEntity_HI_RO> supplierList = iSupplierInfo.findSupplierInfoForSelf(paramJSON, pageIndex, pageRows);
            if (supplierList != null) {
                return JSON.toJSONString(supplierList);
            } else {
                return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "参数中存在敏感字符，查询失败！", 0, null));
            }
        } catch (Exception e) {
            LOGGER.error("查询供应商异常：" + e.getMessage());
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败！", 0, null));
        }
    }


    /**
     * 删除供应商
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteSupplier")
    @POST
    public String deleteSupplier(@FormParam("params") String params) {
        LOGGER.info("删除供应商信息，参数：" + params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = iSupplierInfo.deleteSupplier(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "删除供应商信息失败!", 0, null);
        }
    }

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

    @POST
    @Path("saveRegisterAuditSta")
    public String saveRegisterAuditSta(@FormParam("params") String params) {
        LOGGER.info("=======6666666666666=====" + params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            JSONObject json = iSupplierInfo.saveRegisterAuditSta(paramJSON);
            return CommonAbstractServices.convertResultJSONObj(json.getString("status"), json.getString("msg"), json.getInteger("count"), json.get("data"));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "审核失败!", 0, null);
        }
    }


    /**
     * 注册审核-重置密码
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("updateResetPassWord")
    public String updateResetPassWord(@FormParam("params") String params) {
        LOGGER.info("重置密码参数：" + params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            JSONObject json = iSupplierInfo.updateResetPassWord(paramJSON);
            return CommonAbstractServices.convertResultJSONObj(json.getString("status"), json.getString("msg"), json.getInteger("count"), json.get("data"));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "重置密码失败!", 0, null);
        }
    }

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

    @POST
    @Path("saveSupplierInfo")
    public String saveSupplierInfo(@FormParam(PARAMS) String params) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            JSONObject json = iSupplierInfo.saveSupplierInfo(paramJSON);
            return JSON.toJSONString(json);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "保存失败!", 0, null));
        }
    }

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

    @POST
    @Path("saveSupplierAddress")
    public String saveSupplierhddress(@FormParam(PARAMS) String params) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            JSONObject json = iSupplierInfo.saveSupplierAddress(paramJSON);
            return JSON.toJSONString(json);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "保存失败!", 0, null));
        }
    }

    /**
     * 查询引入进度
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findIntroducingProgress")
    public String findCategoryDivision(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {

        try {
            JSONObject jsonParams = this.parseObject(params);
            Pagination<SrmPosSupplierInfoEntity_HI_RO> pag = iSupplierInfo.findIntroducingProgress(jsonParams, pageIndex, pageRows);
            LOGGER.info("-->>参数：" + JSON.toJSONString(pag) + "查询成功！");
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("查询失败" + e, e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
        }
    }

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
    @POST
    @Path("findSupplierUpdateLov")
    public String findSupplierUpdateLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            if ("EX".equals(jsonParams.getString("varPlatformCode"))) { //是供应商查询
                jsonParams.put("supplier_id", jsonParams.getIntValue("varSupplierId"));
            }
            Pagination<SrmPosSupplierInfoEntity_HI_RO> pag = iSupplierInfo.findSupplierUpdateLov(jsonParams, pageIndex, pageRows);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(pag);
        } catch (Exception e) {

            LOGGER.error("查询失败" + e, e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
        }
    }

    /**
     * 查询供应商LOV
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSupplierLov")
    @POST
    public String findSupplierLov(@FormParam("params")
                                          String params, @FormParam("pageIndex")
                                          Integer pageIndex, @FormParam("pageRows")
                                          Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            if ("EX".equals(jsonParam.getString("varPlatformCode"))) { //是供应商查询
                jsonParam.put("supplier_id", jsonParam.getIntValue("varSupplierId"));
            }
            Pagination<SrmPosSupplierInfoEntity_HI_RO> supplierlov = iSupplierInfo.findSupplierLov(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(supplierlov);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询供应商LOV失败!", 0, null);
        }
    }

    /**
     * 供应商状态分布报表查询
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSupplierStatusDistribution")
    @Produces("application/json")
    public String findSupplierStatusDistribution(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Map<String, Object> map = iSupplierInfo.findSupplierStatusDistribution(jsonParam);
            return JSON.toJSONString(map);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询供应商状态分布失败!", 0, null);
        }
    }

    /**
     * 未产生交易的供应商数量报表
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSupplierTransactionSituation")
    @Produces("application/json")
    public String findSupplierTransactionSituation(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Map<String, Object> map = iSupplierInfo.findSupplierTransactionSituation(jsonParam);
            return JSON.toJSONString(map);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询未产生交易的供应商数量失败!", 0, null);
        }
    }

    /**
     * 供应商引入退出报表
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findIntroductionAndQuit")
    @Produces("application/json")
    public String findIntroductionAndQuit(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Map<String, Object> map = iSupplierInfo.findIntroductionAndQuit(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功", 1, map);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询供应商引入退出报表失败!", 0, null);
        }
    }

    /**
     * 获取服务器当前时间
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSystemTime")
    public String getSystemTime(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsonData = new JSONObject();
            SimpleDateFormat currentYearSdf = new SimpleDateFormat("yyyy");
            SimpleDateFormat currentYearMonthSdf = new SimpleDateFormat("yyyy-MM");
            String currentYear = currentYearSdf.format(new Date());
            String currentYearMonth = currentYearMonthSdf.format(new Date());
            jsonData.put("currentYear", currentYear);
            jsonData.put("currentYearMonth", currentYearMonth);
            return CommonAbstractServices.convertResultJSONObj("S", "获取服务器当前时间成功！", 1, jsonData);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "获取服务器当前时间异常：", 0, null);
        }
    }

    /**
     * 供应商分级状态报表查询
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSupplierGradeDistribution")
    @Produces("application/json")
    public String findSupplierGradeDistribution(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Map<String, Object> map = iSupplierInfo.findSupplierGradeDistribution(jsonParam);
            return JSON.toJSONString(map);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询供应商分级状态失败!", 0, null);
        }
    }

    /**
     * 采购总金额统计报表
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findPurchaseAmountCount")
    @Produces("application/json")
    public String findPurchaseAmountCount(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Map<String, Object> map = iSupplierInfo.findPurchaseAmountCount(jsonParam);
            return JSON.toJSONString(map);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询订单总金额统计报表失败!", 0, null);
        }
    }

    /**
     * 采购总金额统计报表详细
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findPurchaseAmountCountInfo")
    @Produces("application/json")
    public String findPurchaseAmountCountInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {

        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPosSupplierInfoEntity_HI_RO> pag = iSupplierInfo.findPurchaseAmountCountInfo(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询订单总金额统计报表失败!", 0, null);
        }
    }

    /**
     * 查询供应商列表
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSupplierList")
    public String findSupplierList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmPosSupplierInfoEntity_HI_RO> supplierList = iSupplierInfo.findSupplierList(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(supplierList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!", 0, null));
        }
    }

    /**
     * Description：供应商档案信息——发送到EBS系统
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-11-20     zhj             创建
     * ==============================================================================
     */
    @POST
    @Path("updateSrmPosSupplierInfoSendToEBS")
    @Produces("application/json")
    public String updateSrmPosSupplierInfoSendToEBS(@FormParam("params") String params) {
        LOGGER.info("参数：{}" , params.toString());
        if (StringUtils.isBlank(params)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可操作", 0, null);
        }
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsonData = iSupplierInfo.updateSrmPosSupplierInfoSendToEBS(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsonData.getString(STATUS), jsonData.getString(MSG), jsonData.getInteger(COUNT), jsonData.getString(DATA));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "操作失败", 0, null);
        }
    }

}
	
