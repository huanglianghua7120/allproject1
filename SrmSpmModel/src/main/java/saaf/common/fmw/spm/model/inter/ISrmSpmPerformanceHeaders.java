package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import java.util.Map;

import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceHeadersEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceIndicatorsEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceLinesEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceHeadersEntity_HI_RO;

public interface ISrmSpmPerformanceHeaders {

    /**
     * 查询绩效列表（分页）
     * @param paramJSON
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Pagination<SrmSpmPerformanceHeadersEntity_HI_RO> queryPerformanceHeadersList(JSONObject paramJSON, Integer pageIndex, Integer pageRows);

    /**
     * 供应商-查询绩效列表（分页）
     * @param paramJSON
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Pagination<SrmSpmPerformanceHeadersEntity_HI_RO> queryPerformanceResultList(JSONObject paramJSON, Integer pageIndex, Integer pageRows);

    /**
     * 查询绩效头信息
     * @param performanceId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Map<String, Object> findPerformanceHeaderById(Integer performanceId);

    /**
     * 保存绩效信息
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	JSONObject savePerformance(JSONObject jsonParams) throws Exception;

    /**
     * 生成绩效打分明细信息
     * @param indicatorsList
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	void createPerformanceEvaluateInfo(List<SrmSpmPerformanceIndicatorsEntity_HI> indicatorsList, Integer userId) throws Exception;

    /**
     * 计算绩效行得分
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	JSONObject saveCalculateScore(JSONObject jsonParams) throws Exception;

    /**
     * 删除绩效信息
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	JSONObject deletePerformance(JSONObject jsonParams) throws Exception;

}
