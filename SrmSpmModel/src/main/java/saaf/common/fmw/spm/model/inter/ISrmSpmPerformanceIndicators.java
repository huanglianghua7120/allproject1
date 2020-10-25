package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import java.util.Map;

import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceIndicatorsEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceIndicatorsEntity_HI_RO;

public interface ISrmSpmPerformanceIndicators {

    /**
     * 查询绩效的指标信息
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmSpmPerformanceIndicatorsEntity_HI_RO> queryPerformanceIndicators(JSONObject jsonParam);

    /**
     * 查询绩效的指标信息
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Map<String, Object> findPerformanceIndicatorsList(JSONObject jsonParam);

    /**
     * 查询绩效的指标信息-excel导出
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmSpmPerformanceIndicatorsEntity_HI_RO> findPerformanceIndicatorsForExport(JSONObject jsonParam);

    /**
     * 计算指标得分
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    void saveCalculateIndicatorScore(JSONObject jsonParams) throws Exception;
    /**
     * 查询指标得分
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmSpmPerformanceIndicatorsEntity_HI_RO> findManualScoreList(Integer supplierId, String happenedDateFrom, String happenedDateTo, String manualScoreIdStr);

}
