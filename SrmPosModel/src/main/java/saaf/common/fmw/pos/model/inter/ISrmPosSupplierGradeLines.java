package saaf.common.fmw.pos.model.inter;

import java.util.List;

import saaf.common.fmw.pos.model.entities.SrmPosSupplierGradeLinesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierGradeLinesEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商等级行
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmPosSupplierGradeLines {
	
	/**
	  * 查询供应商级别行信息
	  * @param params
	  * @return
	  * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	 List<SrmPosSupplierGradeLinesEntity_HI_RO> findSupplierGradeLinesList(JSONObject params) throws Exception;
	     
	   
	 /**
	  * 保存供应商级别行
	  * @param linesList
	  * @param userId
	  * @param gradeId
	  * @throws Exception
	  * ==============================================================================
	  *  Version    Date           Updated By     Description
	  *  -------    -----------    -----------    ---------------
	  *  V1.0       2020-04-15     hgq             创建
	  * ==============================================================================
	  */
	 public void saveSupplierGradeLinesList(List<SrmPosSupplierGradeLinesEntity_HI> linesList,Integer userId,Integer gradeId) throws Exception;
	    
	 /**
	  * 删除供应商级别行
	  * @param params
	  * @return
	  * @throws Exception
	  * ==============================================================================
	  *  Version    Date           Updated By     Description
	  *  -------    -----------    -----------    ---------------
	  *  V1.0       2020-04-15     hgq             创建
	  * ==============================================================================
	  */
	 JSONObject deleteSupplierGradeLines(JSONObject params) throws Exception;
	 
	 
	 /**
	  * 查询导出级别行数据
	  * @param params
	  * @return
	  * @throws Exception
	  * ==============================================================================
	  *  Version    Date           Updated By     Description
	  *  -------    -----------    -----------    ---------------
	  *  V1.0       2020-04-15     hgq             创建
	  * ==============================================================================
	  */
	 List<SrmPosSupplierGradeLinesEntity_HI_RO> findSupplierGradeLinesExport(JSONObject params)throws Exception;
	 
	 /**
	  * 导入级别行数据
	  * @param params
	  * @return
	  * @throws Exception
	  * ==============================================================================
	  *  Version    Date           Updated By     Description
	  *  -------    -----------    -----------    ---------------
	  *  V1.0       2020-04-15     hgq             创建
	  * ==============================================================================
	  */
	 JSONObject saveSupplierGradeLinesImport(JSONObject params)throws Exception;
	 
	 /**
	  * 查询历史级别行数据
	  * @param params
	  * @return
	  * @throws Exception
	  * ==============================================================================
	  *  Version    Date           Updated By     Description
	  *  -------    -----------    -----------    ---------------
	  *  V1.0       2020-04-15     hgq             创建
	  * ==============================================================================
	  */
	 List<SrmPosSupplierGradeLinesEntity_HI_RO> findSupplierHistoryGradeLines(JSONObject params)throws Exception;
	 
	}