package saaf.common.fmw.spm.model.inter;


import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceSchemeEntity_HI_RO;

import java.util.List;

public interface ISrmSpmPerformanceScheme {

    /**
     * 查询方案编号
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmSpmPerformanceSchemeEntity_HI_RO> findSchemeNumber(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 应用绩效模板
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmSpmPerformanceSchemeEntity_HI_RO> findTplLov(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 绩效方案查询
     * @param paramJSON 查询条件
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmSpmPerformanceSchemeEntity_HI_RO> findSchemeInfoList(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 保存绩效方案数据
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveScheme(JSONObject paramJSON) throws Exception;
    /**
     * 导出绩效方案数据
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmSpmPerformanceSchemeEntity_HI_RO> findExportScheme(JSONObject paramJSON)throws Exception;
    /**
     * 删除绩效方案数据
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject deleteScheme(JSONObject paramJSON) throws Exception;
    /**
     * 绩效方案作废
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateSchemeCancel(JSONObject paramJSON) throws Exception;

    /**
     * 绩效方案发布
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateSchemePublished(JSONObject paramJSON) throws Exception;
    /**
     * 更新绩效方案数据表
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updatePerformanceScheme(JSONObject paramJSON) throws Exception;

}
