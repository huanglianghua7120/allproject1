package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import java.util.Map;

import saaf.common.fmw.spm.model.entities.SrmSpmIndicatorsEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorItemsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorsEntity_HI_RO;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：绩效指标查询接口
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     liuwenjun             创建
 * ===========================================================================
 */
public interface ISrmSpmIndicators {
    /**
     * 绩效指标查询
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	List<SrmSpmIndicatorsEntity_HI> findSrmSpmIndicatorsInfo(JSONObject queryParamJSON);
    /**
     * 绩效指标保存
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Object saveSrmSpmIndicatorsInfo(JSONObject queryParamJSON);

    /**
     * Description：查询绩效指标
     * @param paramJSON 绩效指标查询参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	Pagination<SrmSpmIndicatorsEntity_HI_RO> SpmIndicatorsInfo(JSONObject paramJSON, Integer pageIndex,Integer pageRows);
    /**
     * 删除合同
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	JSONObject deleteContact(JSONObject jsonParams)throws Exception;
    /**
     * Description：保存绩效指标
     * @param jsonParams 绩效指标数据
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	JSONObject saveIndicators(JSONObject jsonParams)throws Exception;
    /**
     * 删除绩效指标
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	JSONObject deleteIndicatorsList(JSONObject jsonParams)throws Exception;
    /**
     * 查询绩效指标
     * @param indicatorId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Map<String, Object> findIndicators(Integer indicatorId)throws Exception;
    /**
     * Description：生效绩效指标
     * @param jsonParams 绩效指标生效所需参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	JSONObject updateApproveIndicators(JSONObject jsonParams)throws Exception;
    /**
     * 修改绩效指标
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	JSONObject updateRejectedIndicator(JSONObject jsonParams)throws Exception;
    /**
     * 修改绩效指标状态
     * @param indicatorId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	boolean updateStatusIndicators(Integer indicatorId, String type);
    /**
     * 汇总绩效指标数量
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	boolean countIndicator(JSONObject jsonParams)throws Exception;
    /**
     * 查询绩效指标行
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Pagination<SrmSpmIndicatorItemsEntity_HI_RO> queryIndicatorsItemList(JSONObject paramJSON, Integer pageIndex,Integer pageRows);
    /**
     * 查询绩效指标导出
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	List<SrmSpmIndicatorsEntity_HI_RO> queryIndicatorsExport(JSONObject paramJSON)throws Exception;
    /**
     * 汇总绩效指标数量
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	boolean countRejectedIndicator(JSONObject jsonParams)throws Exception;

}
