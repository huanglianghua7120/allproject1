package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import java.util.Map;

import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceEvaluateEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceEvaluateEntity_HI_RO;

public interface ISrmSpmPerformanceEvaluate {

    /**
     * 查询绩效的评分信息
     * @param paramsMap
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmSpmPerformanceEvaluateEntity_HI_RO> queryPerformanceEvaluateList(Map<String, Object> paramsMap);

    /**
     * 查询绩效的某一评分人的评分信息
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Map<String, Object> findPerformanceEvaluateById(JSONObject jsonParam);

    /**
     * 查询绩效详细评分信息
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Map<String, Object> findPerformanceEvaluateDetail(JSONObject jsonParam);

    /**
     * 查询绩效的某一评分人的评分信息-excel导出
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    List<SrmSpmPerformanceEvaluateEntity_HI_RO> findPerformanceEvaluateExport(JSONObject paramJSON) throws Exception;

    /**
     * 保存绩效评分信息
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveEvaluate(JSONObject jsonParams) throws Exception;

    /**
     * 查询绩效的监控评分信息
     * @param performanceId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Map<String, Object> findPerformanceEvaluateMonitorById(Integer performanceId);

    /**
     * 根据绩效ID、供应商编码、评价维度、指标名称、用户ID 确认当前行对应的evaluateId
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Integer findEvaluateId(JSONObject paramJSON, Integer userId);

    /**
     * 根据传入的字符串判断检查输入的分数是否为0-100的正浮点数，是就返回true，否则为false
     * @param str
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    boolean isPositiveNumber(String str);

    /**
     * 导入校验——绩效评分
     * @param jsonArray
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONArray checkArray(JSONArray jsonArray, JSONObject info, Integer userId);

    /**
     * 批量导入——绩效评分
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject saveBatchImportEvaluateScore(JSONObject jsonParams) throws Exception;

    /**
     * 检查绩效的评分是否已经全部提交
     * @param performanceId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Boolean QueryPerformanceEvaluateSubmitFlag(Integer performanceId);

}
