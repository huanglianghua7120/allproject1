package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafUsersInst.java
 * Description：区域所有用户
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     钟士元             创建
 * ===========================================================================
 */
public interface ISaafUsersInst {

    List findUserInsts(Integer userId) throws Exception;

    /**
     * 查询用户所属区域
     * @param userId
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    List findUsersInstRegion(Integer userId) throws Exception;

    /**
     * 查询区域所有用户
     * @param region
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    List findInstRegionUsers(String region) throws Exception;

    /**
     * 查询组织下所有用户
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    List findInstUsers(JSONObject parameters) throws Exception;

    /**
     * 查询用用户属于所有组织树结构
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    List findUsersInstTree(JSONObject parameters) throws Exception;

    /**
     * LOV：给用户分配组织{不显示已分配的组织)
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    Pagination findRemainderInst(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * LOV：给组织分配用户{不显示当前组织已分配的用户)
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    Pagination findRemainderUser(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 保存用户组织关系
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    JSONObject saveUserInsts(JSONObject parameters) throws Exception;

    /**
     * 保存组织用户关系
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    JSONObject saveInstUsers(JSONObject parameters) throws Exception;

    /**
     * 删除用户组织关系
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    Boolean deleteUserInst(JSONObject parameters) throws Exception;

    /**
     * 删除组织用户关系
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    Boolean deleteInstUser(JSONObject parameters) throws Exception;

    /**
     * 查询用用户属于所有组织树结构
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    Pagination findUsersInstByself(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

}
