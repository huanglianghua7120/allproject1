package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;

import saaf.common.fmw.base.model.entities.readonly.SaafUserEmployeesEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseCategoryDivisionEntity_HI_RO;
import saaf.common.fmw.base.model.entities.SrmBaseCategoryDivisionEntity_HI;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：品类分工
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmBaseCategoryDivision {

	/**
	 * 批量删除
	 * @param jsonArray
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	JSONObject deleteCategoryDivision(JSONArray jsonArray) throws Exception ;
	/**
	 * 导入
	 * @param jsonArray
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	JSONObject batchImportCategoryDivision(JSONArray jsonArray, Integer userId)throws Exception;
	/**
	 * 批量失效
	 * @param jsonArray
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	JSONObject updateInvalidCategoryDivision(JSONArray jsonArray, Integer userId) throws Exception ;

	/**
	 * 批量生效
	 * @param jsonParams
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public JSONObject updateEffectCategoryDivision(JSONObject jsonParams, Integer userId);
	/**
	 * Description：保存品类分工
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * divisionId  分工ID  NUMBER  Y
	 * orgId  组织ID，关联表：SAAF_INSTITUTION  NUMBER  N
	 * departmentId  部门ID（备用）  NUMBER  N
	 * positionId  职位ID（备用）  NUMBER  N
	 * employeeId  员工ID，关联表：SAAF_EMPLOYEES  NUMBER  N
	 * userId  用户ID，关联表：SAAF_USERS  NUMBER  N
	 * allCategoryFlag  是否可操作全品类，关联表：SAAF_LOOKUP_VALUES（YSE_NO）  VARCHAR2  N
	 * categoryId  分类ID，关联表：SRM_BASE_CATEGORIES  NUMBER  N
	 * segment1  一级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_BIG_CATEGORY）  VARCHAR2  N
	 * segment2  二级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_MIDDLE_CATEGORY）  VARCHAR2  N
	 * segment3  三级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_SMALL_CATEGORY）  VARCHAR2  N
	 * segment4  四级分类编码（备用）  VARCHAR2  N
	 * divisionStatus  分工状态，关联表：SAAF_LOOKUP_VALUES（BASE_DIVISION_STATUS）  VARCHAR2  N
	 * startDate  生效日期  DATE  N
	 * endDate  失效日期  DATE  N
	 * divisionId  分工ID  NUMBER  Y
	 * orgId  组织ID，关联表：SAAF_INSTITUTION  NUMBER  N
	 * departmentId  部门ID（备用）  NUMBER  N
	 * positionId  职位ID（备用）  NUMBER  N
	 * employeeId  员工ID，关联表：SAAF_EMPLOYEES  NUMBER  N
	 * userId  用户ID，关联表：SAAF_USERS  NUMBER  N
	 * allCategoryFlag  是否可操作全品类，关联表：SAAF_LOOKUP_VALUES（YSE_NO）  VARCHAR2  N
	 * categoryId  分类ID，关联表：SRM_BASE_CATEGORIES  NUMBER  N
	 * segment1  一级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_BIG_CATEGORY）  VARCHAR2  N
	 * segment2  二级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_MIDDLE_CATEGORY）  VARCHAR2  N
	 * segment3  三级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_SMALL_CATEGORY）  VARCHAR2  N
	 * segment4  四级分类编码（备用）  VARCHAR2  N
	 * divisionStatus  分工状态，关联表：SAAF_LOOKUP_VALUES（BASE_DIVISION_STATUS）  VARCHAR2  N
	 * startDate  生效日期  DATE  N
	 * endDate  失效日期  DATE  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

	JSONObject saveCategoryDivision(JSONArray jsonParams, Integer userId) throws Exception;


	/**
	 * 查询品类分工
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
	Pagination<SrmBaseCategoryDivisionEntity_HI_RO> findCategoryDivision(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

	/**
	 * 崗位LOV
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
	Pagination<SrmBaseCategoryDivisionEntity_HI_RO> findPositionLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

	/**
	 * 崗位查询
	 * @param queryParamJSON
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	List<SrmBaseCategoryDivisionEntity_HI> findSrmBaseCategoryDivisionInfo(JSONObject queryParamJSON);

	/**
	 * 保存岗位
	 * @param queryParamJSON
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	Object saveSrmBaseCategoryDivisionInfo(JSONObject queryParamJSON);

}
