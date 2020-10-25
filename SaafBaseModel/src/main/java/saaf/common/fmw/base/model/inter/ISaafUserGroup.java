package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafUserGroup.java
 * Description：用户分组维护控制类
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface ISaafUserGroup {
    /**
     * 查询用户列表
     *
     * @param pageIndex
     * @param pageRows
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    Pagination findSaafUserGroup(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 保存用户组信息
     *
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    JSONObject saveSaafUserGroup(JSONObject params) throws Exception;

    /**
     * 多选中心
     *
     * @param
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    Pagination findMulLov(JSONObject jsonParam, Integer pageIndex, Integer pageRows);

}
