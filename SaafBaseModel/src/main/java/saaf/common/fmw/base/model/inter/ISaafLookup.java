package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import java.util.List;
import java.util.Map;

import saaf.common.fmw.base.model.entities.SaafLookupTypesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafLookup.java
 * Description：快码接口类
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     钟士元             创建
 * ===========================================================================
 */
public interface ISaafLookup {

    /**
     * 查询快码lov（分页，通用）
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public Pagination<SaafLookupValuesEntity_HI_RO> findLookupCodeLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

    /**
     * 查询下拉框(通用）
     * @param
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    List findLookupCode(JSONObject jsonParams) throws Exception;

    /**
     * 查询快码（可分页）
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    Pagination findLookupCodePagination(Map<String, Object> map) throws Exception;

    /**
     *  查询快码（可分页）
     * @param parameters
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public Pagination findLookupCodePagination(JSONObject parameters, Integer pageIndex, Integer pageSize) throws Exception;

    /**
     * 查询快码头表
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    Pagination findLookupType(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询快码头表ById
     * @param lookupTypeId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    SaafLookupTypesEntity_HI findLookupTypeById(Integer lookupTypeId) throws Exception;

    /**
     * 查询快码行表
     * @param map
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    List<SaafLookupValuesEntity_HI> findLookupValues(Map<String, Object> map) throws Exception;


    /**
     * 查询快码行表ById
     * @param lookupValuesId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    SaafLookupValuesEntity_HI findLookupValuesById(Integer lookupValuesId) throws Exception;

    /**
     * 保存快码
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    JSONObject saveLookupType(JSONObject params) throws Exception;

    /**
     * 删除快码行表值
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    JSONObject deleteLookupValues(JSONObject params) throws Exception;

    /**
     * 验证块码头表lookupType是否存在
     * @param lookupType
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    boolean findExistsLookupType(String lookupType, Object lookupTypeId) throws Exception;

    /**
     * 验证块码行表lookupCode是否重复
     * @param lookupCode
     * @param lookupType
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    boolean findExistsLookupCode(String lookupCode, String lookupType, Object lookupCodeId) throws Exception;


    /**
     * 根据块码类型及块码名称查询 不考虑重复，返回第一个，否则抛出异常
     *
     * @param lookupType
     * @param lookupCode
     * @return
     * @throws Exception
     * @date 2016-12-09
     * @author 作者
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public Map findLookUpValuesByTypeCode(String lookupType, String lookupCode) throws Exception;

}
