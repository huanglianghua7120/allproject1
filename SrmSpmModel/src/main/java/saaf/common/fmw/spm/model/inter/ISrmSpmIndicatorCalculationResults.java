package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorCalculationResultsEntity_HI_RO;

import java.util.List;


/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：绩效指标计算结果查询接口
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     liuwenjun             创建
 * ===========================================================================
 */
public interface ISrmSpmIndicatorCalculationResults {

    /**
     * 绩效指标计算结果查询
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmSpmIndicatorCalculationResultsEntity_HI_RO> findIndicatorCalculationResults(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 导出指标计算结果数据
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmSpmIndicatorCalculationResultsEntity_HI_RO> findExportIndicatorCalculationResults(JSONObject paramJSON)throws Exception;

    /**
     * 根据评价维度控制评价指标
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SaafLookupValuesEntity_HI_RO> selectChangeEvaluateDimension(JSONObject paramJSON)throws Exception;
}
