package saaf.common.fmw.spm.model.inter;



import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceSupplyRatioEntity_HI_RO;

import java.util.List;

public interface ISrmSpmPerformanceSupplyRatio {

    /**
     * 查询来源绩效方案版本
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
    Pagination<SrmSpmPerformanceSupplyRatioEntity_HI_RO> findSourceSchemeNumber(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 绩效供货比例维护查询
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
    Pagination<SrmSpmPerformanceSupplyRatioEntity_HI_RO> findPerformanceSupplyRatioList(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 绩效供货比例维护数据导出
     *
     * @param paramJSON 查询条件
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmSpmPerformanceSupplyRatioEntity_HI_RO> findPerformanceSupplyRatioExport(JSONObject paramJSON)throws Exception;

    /**
     * 确认
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updatePerformanceSupplyRatio(JSONObject paramJSON) throws Exception;

}
