package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.spm.model.entities.SrmSpmCalculateResultEntity_HI;

public interface ISrmSpmCalculateResult {
    /**
     * 查询绩效计算结果
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	List<SrmSpmCalculateResultEntity_HI> findSrmSpmCalculateResultInfo(JSONObject queryParamJSON);
    /**
     * 保存绩效计算结果
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	Object saveSrmSpmCalculateResultInfo(JSONObject queryParamJSON);

}
