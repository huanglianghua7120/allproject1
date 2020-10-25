package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pos.model.entities.SrmPosReasonsEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierQuitEntity_HI_RO;

import java.util.List;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商退出头层
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmPosSupplierQuit {

	/**
	 * 查询供应商Lov
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
    public Pagination<SrmPosSupplierQuitEntity_HI_RO> findSupplierInfoLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
	/**
	 * 查询冻结单号Lov
	 * 
	 * @param jsonParams
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
	public Pagination<SrmPosSupplierQuitEntity_HI_RO> findFrozenLov(JSONObject jsonParams, Integer pageIndex,
			Integer pageRows) throws Exception ;
    /**
     * 查询供应商退出
     * @param jsonParams
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
    public Pagination<SrmPosSupplierQuitEntity_HI_RO> findSupplierQuitInfo (JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

	/**
	 * Description：保存数据
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * supplierQuitId  供应商退出单ID  NUMBER  Y
	 * documentNumber  退出单号  VARCHAR2  N
	 * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
	 * orgId  （废弃）组织ID  NUMBER  N
	 * frozenId  （废弃）冻结单ID,关联表:srm_pos_frozen_info  NUMBER  N
	 * documentStatus  状态(POS_APPROVAL_STATUS)  VARCHAR2  N
	 * inventoryCleanupFlag  是否完成库存清理(Y/N)  VARCHAR2  N
	 * paymentSettleFlag  是否完成尾款结算(Y/N)  VARCHAR2  N
	 * fileId  附件ID  NUMBER  N
	 * description  说明  VARCHAR2  N
	 * oaNum  oa审批编号  VARCHAR2  N
	 * organizationId  库存组织id  NUMBER  N
	 * rejectReason  驳回原因  VARCHAR2  N
	 * quitType  退出类型  VARCHAR2  N
	 * supplierQuitId  供应商退出单ID  NUMBER  Y
	 * documentNumber  退出单号  VARCHAR2  N
	 * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
	 * orgId  （废弃）组织ID  NUMBER  N
	 * frozenId  （废弃）冻结单ID,关联表:srm_pos_frozen_info  NUMBER  N
	 * documentStatus  状态(POS_APPROVAL_STATUS)  VARCHAR2  N
	 * inventoryCleanupFlag  是否完成库存清理(Y/N)  VARCHAR2  N
	 * paymentSettleFlag  是否完成尾款结算(Y/N)  VARCHAR2  N
	 * fileId  附件ID  NUMBER  N
	 * description  说明  VARCHAR2  N
	 * quitDate  退出日期  DATE  N
	 * quitForeverFlag  是否永久退出(Y/N)  VARCHAR2  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

    public JSONObject  saveData (JSONObject jsonParams)throws Exception;
    
  /**
   * 查询理由
   * @param jsonParams
   * @return
   * @throws Exception
   * ==============================================================================
   *  Version    Date           Updated By     Description
   *  -------    -----------    -----------    ---------------
   *  V1.0       2020-04-15     hgq             创建
   * ==============================================================================
   */
    public List<SrmPosReasonsEntity_HI> findReasonInfo (JSONObject jsonParams) throws Exception;

	/**
	 * 删除数据
	 * 
	 * @param jsonParams
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public JSONObject deleteQuitRow(JSONObject jsonParams) throws Exception;

	/**
	 * 供应商退出数量计算——按照年份（供应商引入退出表）
	 * @param currentYear
	 * @param i
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public Integer findSupplierQuitCount(String currentYear,Integer i);

	/**
	 * 检查该供应商所有地点是否已退出，如果全已退出，更新所有品类为失效状态
	 * @param jsonParams
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public JSONObject  updateSupplierCategories (JSONObject jsonParams)throws Exception;
}
