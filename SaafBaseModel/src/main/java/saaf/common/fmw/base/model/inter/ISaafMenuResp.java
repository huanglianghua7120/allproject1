package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafMenuResp.java
 * Description：用来处理职责维护
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface ISaafMenuResp {

    /**
     * 职责菜单,功能，按钮（已分配、未分配）
     * @param map
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    List findMenuFuncBtn(Map<String, Object> map) throws Exception;

    /**
     * 职责菜单,功能，按钮（已分配）
     * @param params
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    List findRespMenuORBtn(Object[] params) throws Exception;

    /**
     * 保存职责菜单,功能，按钮
     * @param params
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject saveSaafRespMenuBtn(JSONObject params) throws Exception;

    /**
     * 删除职责菜单,功能，按钮
     * @param params
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject deleteSaafRespMenuBtn(JSONObject params) throws Exception;

    /**
     * 查询不在快捷菜单中的用户菜单
     * @param queryParamJSON
     * @param pageIndex
     * @param pageSize
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    String queryUserAllMenuNotInShortcut(JSONObject queryParamJSON,Integer pageIndex, Integer pageSize);
}
