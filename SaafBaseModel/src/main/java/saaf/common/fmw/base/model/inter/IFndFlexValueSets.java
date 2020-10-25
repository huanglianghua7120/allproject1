package saaf.common.fmw.base.model.inter;


import saaf.common.fmw.base.model.entities.FndFlexValidationTablesEntity_HI;
import saaf.common.fmw.base.model.entities.FndFlexValueSetsEntity_HI;
import saaf.common.fmw.base.model.entities.FndFlexValuesEntity_HI;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：IFndFlexValueSets.java
 * Description：用来处理值集
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface IFndFlexValueSets {
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
    String findFndFlexValueSetsInfo(JSONObject queryParamJSON);
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
    String saveFndFlexValueSetsInfo(JSONObject queryParamJSON);

    /**
     * 查询值集头表
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findFlexValueSets(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询值集头表ById
     *
     * @param flexValueSetId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    FndFlexValueSetsEntity_HI findFlexValueSetById(Integer flexValueSetId) throws Exception;

    /**
     * 查询值集行表ById
     *
     * @param flexValueId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    FndFlexValuesEntity_HI findFlexValueById(Integer flexValueId) throws Exception;

    /**
     * 编辑值集头表
     *
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject saveFlexValueSet(JSONObject params) throws Exception;

    /**
     * 验证值集头表flexValueSetName是否存在
     *
     * @param flexValueSetName
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    boolean findExistsFlexValueSet(String flexValueSetName, Object flexValueSetId) throws Exception;

    /**
     * 验证值集行表flexValue是否重复
     *
     * @param flexValue
     * @param flexValueSetId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    boolean findExistsFlexValue(String flexValue, String flexValueSetId, Object flexValueId) throws Exception;

    /**
     * 验证SQL
     *
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject validateSql(JSONObject params) throws Exception;

    /**
     * 获取值集的值
     *
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findFlexValueByName(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;
    
    /**
     * 查询值集行表ById
     *
     * @param flexValueSetId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    FndFlexValidationTablesEntity_HI findFlexValidationById(Integer flexValueSetId) throws Exception;
}
