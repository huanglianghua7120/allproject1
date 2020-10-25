package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import saaf.common.fmw.base.model.entities.FndFlexValuesEntity_HI;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：IFndFlexValues.java
 * Description：用来处理值集
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface IFndFlexValues {
    /**
     * 获取活动信息
     *
     * @param
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    String findFndFlexValuesInfo(JSONObject queryParamJSON);

    /**
     * 保存活动信息
     *
     * @param
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    String saveFndFlexValuesInfo(JSONObject queryParamJSON);

    /**
     * 查询值集行集合
     * @param queryParamMap
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public List findFndFlexValues(Map queryParamMap);

    /**
     * 删除值集行表值
     *
     * @param params
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public JSONObject deleteFlexValues(JSONObject params) throws Exception;

    /**
     * 查询值集行表ById
     *
     * @param flexValueId
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public FndFlexValuesEntity_HI findFlexValueById(Integer flexValueId) throws Exception;
}
