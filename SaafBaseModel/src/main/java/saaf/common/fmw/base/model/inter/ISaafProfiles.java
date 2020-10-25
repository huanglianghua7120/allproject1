package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import java.util.List;

import saaf.common.fmw.base.model.entities.SaafProfilesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafProfilesEntity_HI_RO;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafProfiles.java
 * Description：用来处理配置文件维护
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface ISaafProfiles {

    /**
     * 查询配置文件值（公共方法）
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findProfileValues(JSONObject parameters) throws Exception;

    /**
     * LOV查询
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findProfileLov(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     *  查询配置文件列表
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findSaafProfilesList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询配置文件行  add by liujun on 2016/12/12
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    List findEnabledList(JSONObject parameters) throws Exception;

    /**
     *  查询配置文件行
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    SaafProfilesEntity_HI_RO findSaafProfilesLine(JSONObject parameters) throws Exception;

    /**
     * 查询配置文件值
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findProfileValue(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 编辑配置文件
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject saveProfilesInfo(JSONObject parameters) throws Exception;

    /**
     * 编辑配置文件
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    SaafProfilesEntity_HI saveProfiles(JSONObject parameters) throws Exception;

    /**
     * 编辑配置文件值
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    List saveProfileValues(JSONObject parameters) throws Exception;


    String findProfileValueByProNum(String profileNumber) throws Exception;
}
