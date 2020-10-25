package saaf.common.fmw.pos.model.inter;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.pos.model.entities.readonly.SrmPosQualificationInfoEntity_HI_RO;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：资质审查类
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     liuwenjun             创建
 * ===========================================================================
 */
public interface IQualificationInfo {

	/**
	 * 查询资质审查列表
	 * 
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
	Pagination<SrmPosQualificationInfoEntity_HI_RO> findQualificationInfoList(JSONObject parameters, Integer pageIndex,
			Integer pageRows) throws Exception;

	/**
	 * Description：保存或提交资质审查
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * qualificationId  资质审查ID  NUMBER  Y
	 * qualificationNumber  资质审查单号  VARCHAR2  Y
	 * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
	 * supplierAddrId  (废弃)地址ID，关联表:srm_pos_supplier_addrs  NUMBER  N
	 * orgId  (废弃)业务实体ID,关联表:srm_base_orgs  NUMBER  N
	 * enterpriseType  (废弃)企业性质(POS_ENTERPRISE_TYPE)  VARCHAR2  N
	 * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
	 * qualificationStatus  资质审查状态(POS_APPROVAL_STATUS)  VARCHAR2  N
	 * qualificationResult  资质审查结果(POS_EXAMINE_RESULT)  VARCHAR2  N
	 * needOnsiteInspection  (废弃)是否需要现场考察  VARCHAR2  N
	 * inspectionResult  (废弃)现场考察是否通过  VARCHAR2  N
	 * needSampleTrial  (废弃)是否需要样品试验  VARCHAR2  N
	 * sampleTrialResult  (废弃)样品试验是否通过  VARCHAR2  N
	 * reasonNoInvestigation  废弃)不考察原因  VARCHAR2  N
	 * temporaryQualifiedDate  废弃)临时合格至  DATE  N
	 * qualificationFileId  资质审查相关附件  NUMBER  N
	 * approveBy  (备用)审批人  NUMBER  N
	 * approveDate  审批时间  DATE  N
	 * description  说明  VARCHAR2  N
	 * qualityAgreement  签订质量协议  VARCHAR2  N
	 * procurementAgreement  签订采购协议  VARCHAR2  N
	 * oaNum  oa审批编号  VARCHAR2  N
	 * processId    NUMBER  N
	 * needSampleTrial  (废弃)是否需要样品试验  VARCHAR2  N
	 * sampleTrialResult  (废弃)样品试验是否通过  VARCHAR2  N
	 * reasonNoInvestigation  废弃)不考察原因  VARCHAR2  N
	 * temporaryQualifiedDate  废弃)临时合格至  DATE  N
	 * qualificationFileId  资质审查相关附件  NUMBER  N
	 * approveBy  (备用)审批人  NUMBER  N
	 * approveDate  审批时间  DATE  N
	 * description  说明  VARCHAR2  N
	 * reportAppendixFileId  现场考察报告附件  NUMBER  N
	 * projectReportFileId  样板工程报告附件  NUMBER  N
	 * qualificationId  资质审查ID  NUMBER  Y
	 * qualificationNumber  资质审查单号  VARCHAR2  Y
	 * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
	 * supplierAddrId  (废弃)地址ID，关联表:srm_pos_supplier_addrs  NUMBER  N
	 * orgId  (废弃)业务实体ID,关联表:srm_base_orgs  NUMBER  N
	 * enterpriseType  (废弃)企业性质(POS_ENTERPRISE_TYPE)  VARCHAR2  N
	 * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
	 * qualificationStatus  资质审查状态(POS_APPROVAL_STATUS)  VARCHAR2  N
	 * qualificationResult  资质审查结果(POS_EXAMINE_RESULT)  VARCHAR2  N
	 * needOnsiteInspection  (废弃)是否需要现场考察  VARCHAR2  N
	 * inspectionResult  (废弃)现场考察是否通过  VARCHAR2  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */
	JSONObject saveQualificationInfo(JSONObject params) throws Exception;

	JSONObject savePosQualificationToEkp(Integer supplierId,Integer userId,JSONObject params) throws Exception;

	/**
	 * 删除资质审查
	 * 
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	JSONObject deleteQualificationInfo(JSONObject params) throws Exception;

	/**
	 * LOV:供应商名称
	 * 
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
	Pagination<SrmPosQualificationInfoEntity_HI_RO> findSupplierName(JSONObject parameters, Integer pageIndex,
			Integer pageRows) throws Exception;
	/**
	 * LOV:供应商名称
	 *
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
	Pagination<SrmPosQualificationInfoEntity_HI_RO> findSupplierNameForQualif(JSONObject parameters, Integer pageIndex,
																	 Integer pageRows) throws Exception;


	/**
	 * 根据id查询资质审查信息
	 * 
	 * @param qualificationId
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	List<SrmPosQualificationInfoEntity_HI_RO> findQualificationInfo(Integer qualificationId) throws Exception;

	/**
	 * 资质审查审批
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	JSONObject updateApprovalQualification(JSONObject params) throws Exception;

	/**
	 * 资质审查驳回
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	JSONObject updateRejectQualification(JSONObject params) throws Exception;

	/**
	 * LOV:资质审查单号
	 * 
	 * @param jsonParam
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
	Pagination<SrmPosQualificationInfoEntity_HI_RO> findQualificationNumber(JSONObject jsonParam, Integer pageIndex,
			Integer pageRows) throws Exception;

	/**
	 * LOV:资质审查单号,现场考察使用
	 *
	 * @param jsonParam
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
	Pagination<SrmPosQualificationInfoEntity_HI_RO> findQualificationNumberForReviews(JSONObject jsonParam, Integer pageIndex,
																			Integer pageRows) throws Exception;

	/**
	 * 查询现场考察品类
	 * 
	 * @param jsonParams
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	List<SrmPosQualificationInfoEntity_HI_RO> findSrmReviewCategories(JSONObject jsonParams) throws Exception;

	/**
	 * 供应商引入数量计算——按照年份/月份（供应商引入退出报表）
	 * @param currentYear
	 * @param currentYearMonth
	 * @param i
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public Integer findSupplierIntroduceCount(String currentYear,String currentYearMonth, Integer i);
	/**
	 * 保存ekpStatus
	 *
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-05-24     谢晓霞             创建
	 * =======================================================================
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	void saveEkpStatus() throws Exception;
    /**
     * 资质审查废弃
     *
     * @param params
     * @return
     * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    JSONObject updateAbandonedQualification(JSONObject params) throws Exception;
    /**
     * 资质审查待审批
     *
     * @param params
     * @return
     * @throws Exception
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-10     谢晓霞             创建
     * ===========================================================================
     */
    public JSONObject updateSubmittdQualification(JSONObject params) throws Exception;
}
