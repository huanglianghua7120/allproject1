package saaf.common.fmw.pos.model.inter;

import java.util.List;

import saaf.common.fmw.pos.model.entities.readonly.SrmPosQualificationInfoEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSampleTrialsEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：样品试验
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     liuwenjun             创建
 * ===========================================================================
 */
public interface ISampleTrials {
	
	/**
	 * 查询样品试验列表
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
    Pagination<SrmPosSampleTrialsEntity_HI_RO> findSampleTrialsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：保存样品试验
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * trialId  样品试验ID  NUMBER  Y
     * trialsNumber  样品试验编码  VARCHAR2  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  N
     * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
     * qualificationId  资质审查ID，关联表:srm_pos_qualification_info  NUMBER  N
     * trialsStatus  状态(POS_APPROVAL_STATUS)  VARCHAR2  N
     * trialsResult  样品试验结果(POS_EXAMINE_RESULT)  VARCHAR2  N
     * sampleFileId  样品试验相关附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedBy  审批人  NUMBER  N
     * approvedDate  审批时间  DATE  N
     * orgId  业务实体ID，关联表saaf_institution  NUMBER  N
     * organizationId  库粗组织ID，关联表saaf_institution  NUMBER  N
     * ekpNumber  EKP认证流程单号  VARCHAR2  N
     * theme  主题  VARCHAR2  N
     * trialId  样品试验ID  NUMBER  Y
     * trialsNumber  样品试验编码  VARCHAR2  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  N
     * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
     * qualificationId  资质审查ID，关联表:srm_pos_qualification_info  NUMBER  N
     * trialsStatus  状态(POS_APPROVAL_STATUS)  VARCHAR2  N
     * trialsResult  样品试验结果(POS_EXAMINE_RESULT)  VARCHAR2  N
     * sampleFileId  样品试验相关附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedBy  审批人  NUMBER  N
     * approvedDate  审批时间  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    JSONObject saveOrUpdateOrSubmitSampleTrials(JSONObject params) throws Exception;
    
    /**
     * 删除样品试验
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject deleteSampleTrials(JSONObject params) throws Exception;
    
    
    /**
     * 根据id查询样品试验信息
     * @param trialId
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmPosSampleTrialsEntity_HI_RO> findSampleTrials(Integer trialId) throws Exception;


    /**
     * 根据id查询样品试验品类信息
     * @param jsonParam
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmPosQualificationInfoEntity_HI_RO> findSampleCategoryInfo(JSONObject jsonParam) throws Exception;
    

    /**
     * 样品试验审批
     * @param params
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateApprovalSample(JSONObject params) throws Exception;
    
    /**
     * 样品试验驳回
     * @param params
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateRejectSample(JSONObject params) throws Exception;
}
