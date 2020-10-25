package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import saaf.common.fmw.base.model.entities.readonly.SaafMenuFuncEntity_HI_RO;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafMenuFunctions.java
 * Description：菜单管理
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     钟士元             创建
 * ===========================================================================
 */
public interface ISaafMenuFunctions {

    /**
     * 查询职责菜单(登录首页)
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    List findSaafMenuList(JSONObject params) throws Exception;

    /**
     * 查询职责菜单按钮(登录首页)
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    List findSaafMenuBtnList(Object[] params) throws Exception;

    /**
     * 查询职责菜单
     * @param params
     * @return
     */
    List findSaafMenuFuncAll(Object[] params) throws Exception;

    /**
     * 查询菜单一行
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    SaafMenuFuncEntity_HI_RO findSaafMenuFuncLine(Object[] params) throws Exception;

    /**
     * 查询菜单按钮
     * @param map
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    List findMenuBtnList(Map<String, Object> map) throws Exception;

    /**
     * 保存菜单
     * @param parameters
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    JSONObject saveSaafMenu(JSONObject parameters) throws Exception;

    /**
     * 删除菜单按钮
     * @param params
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    JSONObject deleteSaafMenuBtn(JSONObject params) throws Exception;
}
