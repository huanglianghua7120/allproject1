package saaf.common.fmw.pos.model.inter;

import java.util.List;

import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierGradeEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商等级
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmPosSupplierGrade {
	
	/**
	 * 查询供应商级别管理列表
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
    Pagination<SrmPosSupplierGradeEntity_HI_RO> findSupplierGradeList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;
    
    /**
     * 查询供应商级别信息(不分页)
     * @param params
     * @return
     * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    List<SrmPosSupplierGradeEntity_HI_RO> findSupplierGradeInfo(JSONObject params) throws Exception;

	/**
	 * Description：保存和提交供应商级别信息
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * gradeId  供应商级别头ID  NUMBER  Y
	 * gradeCode  供应商级别编号  VARCHAR2  Y
	 * gradeStatus  供应商级别状态，快码  VARCHAR2  Y
	 * evaluatePeriod  评定维度，快码：SPM_TEMPLATE_FREQUENCY  VARCHAR2  Y
	 * evaluateStartDate  评价开始时间  DATE  N
	 * evaluateEndDate  评价结束时间  DATE  N
	 * approvalEmployeeId  审核人  NUMBER  N
	 * approvalDate  审核通过时间  DATE  N
	 * fileId  附件ID  NUMBER  N
	 * gradeNote  备注  VARCHAR2  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */
    JSONObject saveSupplierGrade(JSONObject params) throws Exception;
    
    /**
	 * 删除供应商级别信息
	 * @param params
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    JSONObject deleteSupplierGrade(JSONObject params) throws Exception;
    
    /**
     * 审批供应商级别信息
     * @param params
     * @return
     * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    JSONObject updateApprovalSupplierGrade(JSONObject params) throws Exception;
    
    /**
     * 驳回供应商级别信息
     * @param params
     * @return
     * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    JSONObject updateRejectSupplierGrade(JSONObject params) throws Exception;
    
    /**
     * 重新审批供应商级别信息
     * @param params
     * @return
     * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    JSONObject updateChangeSupplierGrade(JSONObject params) throws Exception;
    
    /**
	  * 查询导出级别头数据
	  * @param params
	  * @return
	  * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	 List<SrmPosSupplierGradeEntity_HI_RO> findSupplierGradeExport(JSONObject params)throws Exception;

}
