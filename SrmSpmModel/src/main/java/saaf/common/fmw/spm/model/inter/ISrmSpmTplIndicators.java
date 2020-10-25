package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.List;
import java.util.Map;

import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.SrmSpmTplIndicatorsEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplIndicatorsEntity_HI_RO;

public interface ISrmSpmTplIndicators {
    /**
     * 查询模板指标
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	List<SrmSpmTplIndicatorsEntity_HI> findSrmSpmTplIndicatorsInfo(JSONObject queryParamJSON);
    /**
     * 保存模板指标
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Object saveSrmSpmTplIndicatorsInfo(JSONObject queryParamJSON);
    /**
     * Description：查询绩效模板评价指标
     * @param jsonParams 评价指标查询参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	List<SrmSpmTplIndicatorsEntity_HI_RO> getInvoiceLineList(JSONObject jsonParams);
    /**
     * 查询模板指标CODE
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Map<String, Object> selectIndicatorCode(JSONObject jsonParam)throws Exception;
    /**
     *
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Pagination<SaafLookupValuesEntity_HI_RO> findInvoicetplPost(JSONObject paramJSON, Integer pageIndex,Integer pageRows) throws Exception;

    /**
     *查询绩效模板的指标信息
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Map<String, Object> findTplIndicatorsList(JSONObject jsonParam);
}
