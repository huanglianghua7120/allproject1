package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：IFndFlexValidationTables.java
 * Description：用来处理值集
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface IFndFlexValidationTables {
    /**
     * 查询值集信息
     *
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    String findFndFlexValidationTablesInfo(JSONObject queryParamJSON);
    /**
     * 保持值集
     *
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    String saveFndFlexValidationTablesInfo(JSONObject queryParamJSON);

    /**
     * 查询值集行集合
     * @param queryParamMap
     * @return
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     HLH             创建
     * ===========================================================================
     */
    public List findFndFlexValidation(Map queryParamMap);

}
