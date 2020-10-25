package saaf.common.fmw.pos.model.inter;

import java.util.List;

import saaf.common.fmw.pos.model.entities.readonly.SrmPosFrozenInfoEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商冻结、恢复
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     hgq             创建
 * ===========================================================================
 */
public interface IFrozenInfo {
	
	/**
	 * 查询冻结、恢复列表
	 * @param parameters
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmPosFrozenInfoEntity_HI_RO> findFrozenInfoList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;
    
    /**
     * 保存冻结、恢复信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveFrozenInfo(JSONObject params) throws Exception;
    
    /**
     * 保存供应商冻结、恢复信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveSupplierFrozenInfo(JSONObject params) throws Exception;
    
    /**
     * 删除冻结、恢复信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject deleteFrozenInfo(JSONObject params) throws Exception;
    
    
    /**
     * 根据id查询冻结、恢复信息
     * @param frozenId
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmPosFrozenInfoEntity_HI_RO> findFrozenInfo(Integer frozenId) throws Exception;



    /**
     * Description：提交供应商冻结、恢复信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * frozenId  冻结单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  业务实体ID,关联表:srm_base_orgs  NUMBER  N
     * freezeNumber  冻结单据号  VARCHAR2  N
     * freezeStatus  冻结单状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * freezingInstructions  (废弃)原因  VARCHAR2  N
     * forbidPurchaseFlag  禁止采购  VARCHAR2  N
     * forbidPaymentFlag  禁止付款  VARCHAR2  N
     * unfrozenDate  解冻时间  DATE  N
     * description  说明  VARCHAR2  N
     * frozenFileId  供应商冻结相关文件id  NUMBER  N
     * approveBy  审批人  NUMBER  N
     * approveDate  审批时间  DATE  N
     * freezeItem  冻结的条目（SNBC_TYPE：地点或者品类）  VARCHAR2  N
     * oaNum  oa审批编号  VARCHAR2  N
     * freezeType  区分冻结和恢复单据  VARCHAR2  N
     * organizationId  库存组织id  NUMBER  N
     * frozenId  冻结单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  (废弃)业务实体ID,关联表:srm_base_orgs  NUMBER  N
     * freezeType  类型(POS_FREEZE_TYPE  冻结或解冻)  VARCHAR2  N
     * freezeNumber  冻结单号  VARCHAR2  N
     * freezeStatus  冻结单状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * freezingInstructions  (废弃)原因  VARCHAR2  N
     * forbidPurchaseFlag  禁止采购  VARCHAR2  N
     * forbidPaymentFlag  禁止付款  VARCHAR2  N
     * unfrozenDate  解冻时间  DATE  N
     * description  说明  VARCHAR2  N
     * frozenFileId  供应商冻结相关文件id  NUMBER  N
     * approveBy  审批人  NUMBER  N
     * approveDate  审批时间  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    JSONObject saveApprovalFrozenInfo(JSONObject params) throws Exception;
    
    /**
     * 审批冻结信息
     * @param params
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateApprovalFrozenInfo(JSONObject params) throws Exception;
    
    /**
     * 驳回冻结、恢复信息
     * @param params
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateRejectFrozenInfo(JSONObject params) throws Exception;
    
    /**
     * 审批恢复信息
     * @param params
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateApprovalRecoveryInfo(JSONObject params) throws Exception;

    /**
     *  恢复冷冻供应商JOB
     * @param params
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateApprovalRecoveryJob(JSONObject params) throws Exception;

    
    /**
     * 根据id查询供应商品标识
     * @param supplierId
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmPosFrozenInfoEntity_HI_RO> findSupplierFlag(Integer supplierId) throws Exception;
    
    /**
     * 查询冻结、恢复供应商LOV
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Pagination<SrmPosFrozenInfoEntity_HI_RO> findFrozenOrRecoverySupplierLov(JSONObject params, Integer pageIndex,
			Integer pageRows) throws Exception;
}
