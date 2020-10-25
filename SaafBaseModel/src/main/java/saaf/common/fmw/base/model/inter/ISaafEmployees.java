package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：findEmployeeLov.java
 * Description：查询采购人员
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     钟士元             创建
 * ===========================================================================
 */
public interface ISaafEmployees {

	  /**
     * 查询采购人员
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     钟士元             创建
	 * ===========================================================================
	 */
	Pagination<SaafEmployeesEntity_HI> findEmployeeLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows)throws Exception;

}
