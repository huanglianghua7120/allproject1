package saaf.common.fmw.pos.model.inter;

import java.util.List;

import saaf.common.fmw.pos.model.entities.readonly.SrmPosBlacklistsEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商黑名单
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     hgq             创建
 * ===========================================================================
 */
public interface IBlacklists {

	/**
	 * 查询黑名单列表
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
    Pagination<SrmPosBlacklistsEntity_HI_RO> findBlacklistsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;


    void  updateBlacklistsJob(JSONObject parameters) throws Exception;

    /**
     * Description：保存黑名单
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * blacklistId  黑名单ID  NUMBER  Y
     * blacklistNumber  黑名单单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:SRM_POS_SUPPLIER_INFO  NUMBER  Y
     * blacklistStatus  黑名单单据状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * permanentFlag  永久黑名单标识(Y/N)  VARCHAR2  N
     * relieveDate  解除时间  DATE  N
     * blacklistFileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedDate  审批时间  DATE  N
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * rejectReason  审批意见  VARCHAR2  N
     * blacklistId  黑名单ID  NUMBER  Y
     * blacklistNumber  黑名单单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:SRM_POS_SUPPLIER_INFO  NUMBER  Y
     * blacklistStatus  黑名单单据状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * permanentFlag  永久黑名单标识(Y/N)  VARCHAR2  N
     * relieveDate  解除时间  DATE  N
     * blacklistFileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedDate  审批时间  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    JSONObject saveBlacklists(JSONObject params) throws Exception;
    
	
    /**
     * 删除黑名单信息
     * @param params
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject deleteBlacklists(JSONObject params) throws Exception;
    
    
   /**
    * 根据id查询黑名单信息
    * @param blacklistId
    * @return
    * @throws Exception
    * ==============================================================================
    *  Version    Date           Updated By     Description
    *  -------    -----------    -----------    ---------------
    *  V1.0       2020-04-15     hgq             创建
    * ==============================================================================
    */
    List<SrmPosBlacklistsEntity_HI_RO> findBlacklists(Integer blacklistId) throws Exception;



    /**
     * Description：提交黑名单
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * blacklistId  黑名单ID  NUMBER  Y
     * blacklistNumber  黑名单单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:SRM_POS_SUPPLIER_INFO  NUMBER  Y
     * blacklistStatus  黑名单单据状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * permanentFlag  永久黑名单标识(Y/N)  VARCHAR2  N
     * relieveDate  解除时间  DATE  N
     * blacklistFileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedDate  审批时间  DATE  N
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * rejectReason  审批意见  VARCHAR2  N
     * blacklistId  黑名单ID  NUMBER  Y
     * blacklistNumber  黑名单单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:SRM_POS_SUPPLIER_INFO  NUMBER  Y
     * blacklistStatus  黑名单单据状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * permanentFlag  永久黑名单标识(Y/N)  VARCHAR2  N
     * relieveDate  解除时间  DATE  N
     * blacklistFileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedDate  审批时间  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    JSONObject saveApprovalBlacklists(JSONObject params) throws Exception;
    
    /**
     * 审批黑名单信息
     * @param params
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateApprovalBlacklists(JSONObject params) throws Exception;

    /**
     * 驳回黑名单信息
     * @param params
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateRejectBlacklists(JSONObject params) throws Exception;
}
