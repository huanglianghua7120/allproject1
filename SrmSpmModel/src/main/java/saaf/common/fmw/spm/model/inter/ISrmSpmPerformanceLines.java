package saaf.common.fmw.spm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import java.util.Map;

import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceLinesEntity_HI;

public interface ISrmSpmPerformanceLines {

    /**
     * 查询绩效行信息
     * @param performanceId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Map<String, Object> findPerformanceLinesById(Integer performanceId);

    /**
     * 保存绩效行信息
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject savePerformanceLine(JSONObject jsonParams) throws Exception;
}
