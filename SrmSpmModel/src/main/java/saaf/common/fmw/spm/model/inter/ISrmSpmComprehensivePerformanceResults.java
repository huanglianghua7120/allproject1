package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmComprehensivePerformanceResultsEntity_HI_RO;

import java.util.List;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：综合绩效结果查询接口
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     liuwenjun             创建
 * ===========================================================================
 */
public interface ISrmSpmComprehensivePerformanceResults {

    /**
     * 综合绩效结果查询
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> findComprehensivePerformanceResults(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 导出综合绩效结果数据
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> findExportComprehensivePerformanceResults(JSONObject paramJSON)throws Exception;

    /**
     * 综合绩效结果详情头
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> findComprehensivePerformanceResultsHeader(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询综合绩效信息数据
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination findComprehensivePerformanceData(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询评价维度数据
     * @param parameters
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    String findEvaluateDimensionData(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

}
