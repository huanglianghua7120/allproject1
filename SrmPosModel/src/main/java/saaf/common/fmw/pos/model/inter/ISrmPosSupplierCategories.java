package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierCategoriesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierCategoriesEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商银行
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmPosSupplierCategories {

	/**
	 * 查询供应商的产品和服务
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
	public Pagination<SrmPosSupplierCategoriesEntity_HI_RO> findSupplierCategory(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

	JSONObject saveSupplierCategory(JSONArray dataList, int userId, int supplierId) throws Exception ;
	/**
	 * 删除产品与服务行
	 * @param jsonParams
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public JSONObject deleteCategory(JSONObject jsonParams);

	/**
	 * 保存供应商的产品和服务（档案自助维护/内部创建供应商）
	 * @param categoriesList
	 * @param userId
	 * @param supplierId
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public boolean saveSrmPosSupplierCategoriesInfo(List<SrmPosSupplierCategoriesEntity_HI> categoriesList, Integer userId, Integer supplierId);

	/**
	 * 保存供应商的产品和服务——供应商接口（数据输入）
	 * @param supplierCategoryInfoList
	 * @param userId
	 * @param supplierId
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public void saveSupplierCategoryInfoListExternal(List<SrmPosSupplierCategoriesEntity_HI> supplierCategoryInfoList, Integer userId, Integer supplierId);

	/**
	 * 校验供应商的产品和服务重复与必填项——供应商接口（数据输入）
	 * @param supplierCategoryInfoList
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public String judgeSpaceSupplierCategories(List<SrmPosSupplierCategoriesEntity_HI> supplierCategoryInfoList);
	/**
	 * 查询冻结品类
	 * @param jsonParams
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	List<SrmPosSupplierCategoriesEntity_HI_RO> findSrmPosSupplierCategoriesById(JSONObject jsonParams) throws Exception;

	/**
	 * 查询供应商有效的品类
	 * @param jsonParams
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	List<SrmPosSupplierCategoriesEntity_HI_RO> findSupplierCategoriesByEff(JSONObject jsonParams) throws Exception;


}
