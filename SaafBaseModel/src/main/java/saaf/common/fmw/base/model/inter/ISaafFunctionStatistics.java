package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONObject;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafFunctionStatistics.java
 * Description：保存用户记录
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     钟士元             创建
 * ===========================================================================
 */
public interface ISaafFunctionStatistics {

    /**
     * 保存用户记录（公共方法）
     * @param parameters
     * @return
     */
    JSONObject save(JSONObject parameters) throws Exception;


}
