package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import java.util.Map;

import saaf.common.fmw.base.model.entities.SaafResponsibilitysEntity_HI;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafResponsibilitys.java
 * Description：用来处理职责维护
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface ISaafResponsibilitys {


    /**
     * 查询职责名称（LOV）
     * @param parameters
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findRespNameLov(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;


    /**
     * 查询有效职责列表
     * @param parameters
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findRespist(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询职责列表
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
    Pagination findResponsibilityList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询职责ById
     * @param map
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    SaafResponsibilitysEntity_HI findRespById(Map<String, Object> map) throws Exception;

    /**
     * 编辑职责
     * @param parameters
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject saveResponsibility(JSONObject parameters) throws Exception;

    /**
     * 验证职责简称responsibilityKey
     * @param responsibilityKey
     * @param responsibilityId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    boolean findExistsRespKeyRepeat(String responsibilityKey, Object responsibilityId);

    /**
     * 验证职责名称responsibilityName
     * @param responsibilityName
     * @param responsibilityId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    boolean findExistsRespNameRepeat(String responsibilityName, Object responsibilityId);

}
