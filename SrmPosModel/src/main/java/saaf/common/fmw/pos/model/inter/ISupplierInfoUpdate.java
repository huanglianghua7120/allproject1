package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosChangeInfoEntity_HI_RO;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商变更
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISupplierInfoUpdate {
    /**
     * 查询变更头信息
     *
     * @param
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findSupplierInfoUpdate(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询变更头信息-门户
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findDoorSupplierInfoUpdate(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询变更基础信息
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findBaseInfoUpdate(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询变更的产品与服务
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeCategory(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询供应商的认证证书
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeCertificate(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询供应商的认证证书(变更前)
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeCertificateBf(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询供应商的认证证书(变更后)
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeCertificateAf(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询供应商的银行信息
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeBankInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询供应商的银行信息(变更前)
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeBankBfInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询供应商的银行信息（变更后）
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeBankAfInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询供应商的联系人
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeContacts(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询供应商的联系人（变更前）
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeContactsBf(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询供应商的联系人（变更后）
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findChangeContactsAf(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询供应商的地址簿（变更前）
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findAddressesBf(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询供应商的地址簿（变更后）
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findAddressesAf(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询供应商旧信息
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findSupplierInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询供应商旧信息-门户
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findDoorSupplierInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询变更前供应商的银行信息
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findOldBankInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询供应商的认证证书
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
    public Pagination<SrmPosChangeInfoEntity_HI_RO> findUpdateSupplierCertificate(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * Description：保存档案变更
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * changeId  资料变更ID  NUMBER  Y
     * changeNumber  资料变更编号  VARCHAR2  N
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * sourceType  来源类型（BUYER：采购员，SUPPLIER：供应商）  VARCHAR2  N
     * changeStatus  变更状态（POS_APPROVAL_STATUS）  VARCHAR2  Y
     * changeReason  变更原因  VARCHAR2  N
     * fileId  变更函附件ID  NUMBER  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核时间  DATE  N
     * approvalOpinion  审核意见  VARCHAR2  N
     * changeId  资料变更ID  NUMBER  Y
     * changeNumber  资料变更编号  VARCHAR2  N
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * sourceType  来源类型（BUYER：采购员，SUPPLIER：供应商）  VARCHAR2  N
     * changeStatus  变更状态（POS_APPROVAL_STATUS）  VARCHAR2  Y
     * changeReason  变更原因  VARCHAR2  N
     * fileId  变更函附件ID  NUMBER  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核时间  DATE  N
     * approvalOpinion  审核意见  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    public JSONObject saveChangeInfo(JSONObject jsonParams) throws Exception;
    /**
     * Description：审批通过档案变更
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * changeContactAfId  供应商联系人变更后ID  NUMBER  Y
     * changeId  变更ID  NUMBER  Y
     * supplierContactId  供应商联系人ID  NUMBER  N
     * contactName  联系人姓名  VARCHAR2  N
     * mobilePhone  手机号码  VARCHAR2  N
     * fixedPhone  固定电话  VARCHAR2  N
     * faxPhone  传真号码  VARCHAR2  N
     * emailAddress  联系人邮箱  VARCHAR2  N
     * departmentName  部门  VARCHAR2  N
     * positionName  职位  VARCHAR2  N
     * needAccountFlag  付款标识  VARCHAR2  N
     * userName  用户名  VARCHAR2  N
     * birthDate  出生日期  DATE  N
     * failureDate  失效日期  DATE  N
     * contactFileIdAf  联系人附件ID  NUMBER  N
     * position  职位，快码:POSITION  VARCHAR2  N
     * superiors  上级领导，快码:SUPERIORS  VARCHAR2  N
     * changeContactAfId  供应商联系人变更后ID  NUMBER  Y
     * changeId  变更ID  NUMBER  Y
     * supplierContactId  供应商联系人ID  NUMBER  N
     * contactName  联系人姓名  VARCHAR2  N
     * mobilePhone  手机号码  VARCHAR2  N
     * fixedPhone  固定电话  VARCHAR2  N
     * faxPhone  传真号码  VARCHAR2  N
     * emailAddress  联系人邮箱  VARCHAR2  N
     * departmentName  部门  VARCHAR2  N
     * positionName  职位  VARCHAR2  N
     * needAccountFlag  付款标识  VARCHAR2  N
     * userName  用户名  VARCHAR2  N
     * birthDate  出生日期  DATE  N
     * failureDate  失效日期  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    public JSONObject approveUpdate(JSONObject jsonParams, int userId) throws Exception;
    /**
     * 删除变更单据
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteChangeInfo(JSONObject jsonParams) throws Exception;
    /**
     * 删除变更产品与服务行
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteChangeCategory(JSONObject jsonParams) throws Exception;
    /**
     * 删除供应商的认证证书(变更后)
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteChangeCertificateAf(JSONObject jsonParams) throws Exception;
    /**
     * 删除供应商的银行信息(变更后)
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteChangeBankAf(JSONObject jsonParams) throws Exception;
    /**
     * 删除联系人
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteChangeContact(JSONObject jsonParams) throws Exception;
    /**
     * 删除供应商的地址簿(变更后)
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject deleteAddressesAf(JSONObject jsonParams) throws Exception;
    /**
     * Description：驳回档案变更
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * changeId  资料变更ID  NUMBER  Y
     * changeNumber  资料变更编号  VARCHAR2  N
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * sourceType  来源类型（BUYER：采购员，SUPPLIER：供应商）  VARCHAR2  N
     * changeStatus  变更状态（POS_APPROVAL_STATUS）  VARCHAR2  Y
     * changeReason  变更原因  VARCHAR2  N
     * fileId  变更函附件ID  NUMBER  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核时间  DATE  N
     * approvalOpinion  审核意见  VARCHAR2  N
     * changeId  资料变更ID  NUMBER  Y
     * changeNumber  资料变更编号  VARCHAR2  N
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * sourceType  来源类型（BUYER：采购员，SUPPLIER：供应商）  VARCHAR2  N
     * changeStatus  变更状态（POS_APPROVAL_STATUS）  VARCHAR2  Y
     * changeReason  变更原因  VARCHAR2  N
     * fileId  变更函附件ID  NUMBER  N
     * approvalUserId  审核人  NUMBER  N
     * approvalDate  审核时间  DATE  N
     * approvalOpinion  审核意见  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    public JSONObject saveRejectUpdate(JSONObject jsonParams) throws Exception;

}
