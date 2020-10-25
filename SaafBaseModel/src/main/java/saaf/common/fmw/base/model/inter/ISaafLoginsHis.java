package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONObject;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafLoginsHis.java
 * Description：日记记录
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     钟士元             创建
 * ===========================================================================
 */
public interface ISaafLoginsHis {

    /**
     * 保存日记记录
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    JSONObject saveLoginHistory(JSONObject params) throws Exception;

}
