package saaf.common.fmw.pos.model.inter;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.pos.model.entities.readonly.SrmPosLocaleReviewsEntity_HI_RO;

import javax.ws.rs.DefaultValue;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：现场考察
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
public interface ISrmPosLocaleReviews {

	/**
	 * 分页查询现场考察数据
	 * 
	 * @param jsonParams
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * Update History
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     zhj             创建
	 * ===========================================================================
	 */
	public Pagination<SrmPosLocaleReviewsEntity_HI_RO> findSrmPosLocaleReviewsInfoList(JSONObject jsonParams,
			Integer pageIndex, Integer pageRows);

	/**
	 * Description：保存或更新现场考察数据
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * localeReviewId  现场考察ID  NUMBER  Y
	 * localeReviewNumber  现场考察  VARCHAR2  Y
	 * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
	 * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
	 * qualificationId  资质审查ID  NUMBER  N
	 * localeReviewStatus  现场考察状态(POS_APPROVAL_STATUS)  VARCHAR2  N
	 * localeReviewResult  现场考察结果(POS_EXAMINE_RESULT)  VARCHAR2  N
	 * reviewDate  考察时间  DATE  N
	 * reviewMember  考察小组成员  VARCHAR2  N
	 * supplierMember  供方成员  VARCHAR2  N
	 * supplyFlag  是否已供货(Y/N)  VARCHAR2  N
	 * description  说明  VARCHAR2  N
	 * fileId  现场考察相关附件  NUMBER  N
	 * approvedBy  (备用)审批人  NUMBER  N
	 * approvedDate  审批时间  DATE  N
	 * localeReviewId  现场考察ID  NUMBER  Y
	 * localeReviewNumber  现场考察  VARCHAR2  Y
	 * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
	 * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
	 * qualificationId  资质审查ID  NUMBER  N
	 * investigationPlanLinesId  考察计划行ID，关联表:srm_pos_investigation_plan_lines  NUMBER  N
	 * localeReviewStatus  现场考察状态(POS_APPROVAL_STATUS)  VARCHAR2  N
	 * localeReviewResult  现场考察结果(POS_EXAMINE_RESULT)  VARCHAR2  N
	 * reviewDate  考察时间  DATE  N
	 * reviewMember  考察小组成员  VARCHAR2  N
	 * supplierMember  供方成员  VARCHAR2  N
	 * supplyFlag  是否已供货(Y/N)  VARCHAR2  N
	 * description  说明  VARCHAR2  N
	 * fileId  现场考察相关附件  NUMBER  N
	 * approvedBy  (备用)审批人  NUMBER  N
	 * approvedDate  审批时间  DATE  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

	JSONObject saveLocaleReviewsInfo(JSONObject jsonParams) throws Exception;

	/**
	 * 根据ID删除现场考察数据
	 * 
	 * @param jsonParams
	 * @return
	 * Update History
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     zhj             创建
	 * ===========================================================================
	 */
	JSONObject deleteSrmPosLocaleReviewsInfo(JSONObject jsonParams);

	/**
	 * 根据ID更新现场考察数据
	 * 
	 * @param jsonParams
	 * @return
	 * Update History
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     zhj             创建
	 * ===========================================================================
	 */
	JSONObject updateSrmPosLocaleReviewsInfo(JSONObject jsonParams);

	/**
	 * 查询现场考察信息
	 * 
	 * @param localeReviewId
	 * @return
	 * Update History
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     zhj             创建
	 * ===========================================================================
	 */
	public List<SrmPosLocaleReviewsEntity_HI_RO> findSrmPosLocaleReviewsInfo(Integer localeReviewId) throws Exception;

	/**
	 * 审批现场考察信息
	 * 
	 * @param jsonParam
	 * @return
	 * Update History
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     zhj             创建
	 * ===========================================================================
	 */
	public JSONObject updateApprovalLocaleReviewInfo(JSONObject jsonParam) throws Exception;

	/**
	 * 驳回现场考察信息
	 * 
	 * @param jsonParam
	 * @return
	 * Update History
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     zhj             创建
	 * ===========================================================================
	 */
	public JSONObject updateRejectLocaleReviewInfo(JSONObject jsonParam) throws Exception;

	/**
	 * 根据供应商id查询考察计划单号
	 *
	 * @param
	 * @return
	 * Update History
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     zhj             创建
	 * ===========================================================================
	 */
//	public Pagination<SrmPosLocaleReviewsEntity_HI_RO> findLocaleReviewsStatus(JSONObject parameters, @DefaultValue("1")Integer pageIndex, @DefaultValue("10")Integer pageRows) throws Exception;


}
