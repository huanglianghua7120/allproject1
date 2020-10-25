package saaf.common.fmw.base.model.inter;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.entities.readonly.SrmBaseCategoriesEntity_HI_RO;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：采购分类
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmBaseCategories {

    /**
     * 接口（数据输入）——保存采购分类（用于外部访问的接口）参数params：array{categoryId，enabledFlag，fullCategoryCode，fullCategoryName，parentFullCategoryCode
     * categoryLevel}
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject updateProcurementMaintenanceExternal(JSONObject jsonParams, int userId) throws Exception;

    /**
     * 接口（数据输出）——查询采购分类所有信息（用于外部访问的接口）参数fullCategoryCode，lastUpdateDateFrom，lastUpdateDateTo，parentFullCategoryCode，categoryLevel，parentFullCategoryCode
     * 查询结果：（分类编码fullCategoryCode、分类名称fullCategoryName、当前分类编码categoryCode、当前分类编码名称categoryName、上级分类编码parentFullCategoryCode
     * 上级分类编码名称parentFullCategoryName、状态enabledFlagDesc、最后更新时间lastUpdateDate）
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Map<String, Object> pushFindProcurementListExternal(JSONObject jsonParams, Integer userId);

    /**
     * 批量失效采购分类（修改）
     *
     * @param jsonArray
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateInvalidProcurementMaintenance(JSONArray jsonArray, int operatorUserId) throws Exception;

    /**
     * 批量生效采购分类（修改或保存）
     *
     * @param jsonArray
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateProcurementMaintenance(JSONArray jsonArray, int operatorUserId) throws Exception;

    /**
     * 点击菜单查询采购分类维护
     *
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
    public Pagination<SrmBaseCategoriesEntity_HI_RO> findProcurementMaintenanceList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询采购分类lov
     *
     * @param jsonParams
     *
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmBaseCategoriesEntity_HI_RO> findCategoryLove(JSONObject jsonParams, Integer pageIndex,
                                                               Integer pageRows) throws Exception;

    /**
     * 查询采购分类（分页，通用）
     *
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
    public Pagination<SrmBaseCategoriesEntity_HI_RO> findSrmBaseCategoriesInfo(JSONObject jsonParams, Integer pageIndex,
                                                                               Integer pageRows);

    /**
     * 查询冻结品类
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmBaseCategoriesEntity_HI_RO> findSrmBaseCategoriesById(JSONObject jsonParams) throws Exception;

    /**
     * Description：查询所有有效的采购分类
     *
     * @param jsonParams 参数
     * @param pageIndex
     * @param pageRows
     * @return 采购分类的实体集合
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-06-04     秦晓钊          创建
     * ==============================================================================
     */
    Pagination<SrmBaseCategoriesEntity_HI_RO> findAllCategoriesList(JSONObject jsonParams, Integer pageIndex,
                                                              Integer pageRows) throws Exception;

}
