package saaf.common.fmw.base.model.inter.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafRespFunEntity_HI;
import saaf.common.fmw.base.model.entities.SaafRespMenuEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafRespMenuEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafMenuResp;
import saaf.common.fmw.common.utils.SaafToolUtils;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：baseSaafMenuRespServer.java
 * Description：用来处理职责维护
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
@Component("baseSaafMenuRespServer")
public class SaafMenuRespServer implements ISaafMenuResp {

    public SaafMenuRespServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SaafRespMenuEntity_HI_RO> saafRespMenuDAO_HI_RO;

    @Autowired
    private ViewObject<SaafRespMenuEntity_HI> saafRespMenuDAO_HI;

    @Autowired
    private ViewObject<SaafRespFunEntity_HI> saafRespFunDAO_HI;

    /**
     * 职责菜单,功能，按钮（已分配、未分配）
     *
     * @param paramsmap
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public List findMenuFuncBtn(Map<String, Object> paramsmap) throws Exception {
        try {
            List resrMenuList = saafRespMenuDAO_HI_RO.findList(SaafRespMenuEntity_HI_RO.QUERY_RESP_MENU_BTN_SQL, paramsmap);
            return resrMenuList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 职责菜单,功能，按钮（已分配）
     *
     * @param params
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public List findRespMenuORBtn(Object[] params) throws Exception {
        try {
            List resrMenuList = saafRespMenuDAO_HI_RO.findList(SaafRespMenuEntity_HI_RO.QUERY_RESP_MENU_BTN_CHECKED_SQL, params);
            return resrMenuList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 保存职责菜单,功能，按钮
     *
     * @param params
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public JSONObject saveSaafRespMenuBtn(JSONObject params) throws Exception {
        Integer operatorUserId = params.getInteger("varUserId");
        try {
            SaafRespMenuEntity_HI respMenuRow = null;
            SaafRespFunEntity_HI respFuncRow = null;
            List menuList = new ArrayList();
            List funcList = new ArrayList();
            JSONObject menuJson = null;
            JSONArray menudata = params.getJSONArray("menudata");
            boolean dtlFlag = deleteSaafRespMenuBtn(params.getInteger("respId"));
            if (dtlFlag) {
                for (int i = 0; i < menudata.size(); i++) {
                    menuJson = menudata.getJSONObject(i);
                    if ("MENU".equals(menuJson.getString("menuorbtn"))) {
                        respMenuRow = new SaafRespMenuEntity_HI();
                        respMenuRow.setMenuId(menuJson.getInteger("menuId"));
                        respMenuRow.setRespId(params.getInteger("respId"));
                        respMenuRow.setPlatformCode(params.getString("varPlatformCode"));
                        respMenuRow.setOperatorUserId(operatorUserId);
                        menuList.add(respMenuRow);
                    } else {
                        respFuncRow = new SaafRespFunEntity_HI();
                        respFuncRow.setMenuId(menuJson.getInteger("menuParentId"));
                        respFuncRow.setRespId(params.getInteger("respId"));
                        respFuncRow.setPlatformCode(params.getString("varPlatformCode"));
                        respFuncRow.setFunButtonId(menuJson.getInteger("funButtonId"));
                        respFuncRow.setIfOperation("Y");
                        respFuncRow.setOperatorUserId(operatorUserId);
                        funcList.add(respFuncRow);
                    }
                }
                saafRespMenuDAO_HI.saveAll(menuList);
                saafRespFunDAO_HI.saveAll(funcList);
            }
            return SToolUtils.convertResultJSONObj("S", "保存菜单成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 删除职责菜单,功能，按钮（职责ID删除）
     *
     * @param respId
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public boolean deleteSaafRespMenuBtn(Integer respId) throws Exception {
        boolean returnResult = false;
        try {
            List<SaafRespMenuEntity_HI> saafRespMenuEntity_HI = saafRespMenuDAO_HI.findByProperty("respId", respId);
            saafRespMenuDAO_HI.delete(saafRespMenuEntity_HI); //.executeSqlUpdate(respMenuHql);
            List<SaafRespFunEntity_HI> saafRespFunEntity_HI = saafRespFunDAO_HI.findByProperty("respId", respId);
            saafRespFunDAO_HI.delete(saafRespFunEntity_HI);
            returnResult = true;
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (!returnResult) {
            throw new Exception("职责分配用户异常，请联系管理员！");
        }
        return returnResult;
    }

    /**
     * 删除职责菜单,功能，按钮（单个删除）
     *
     * @param params
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public JSONObject deleteSaafRespMenuBtn(JSONObject params) throws Exception {
        try {
            SaafRespMenuEntity_HI respMenuRow = null;
            SaafRespFunEntity_HI respFuncRow = null;
            JSONObject menuJson = null;
            Map<String, Object> menumap = new HashMap<String, Object>();
            Map<String, Object> funmap = new HashMap<String, Object>();
            JSONArray menudata = params.getJSONArray("menudata");
            for (int i = 0; i < menudata.size(); i++) {
                menuJson = menudata.getJSONObject(i);
                if ("MENU".equals(menuJson.getString("menuorbtn"))) {
                    menumap.put("respId", params.getInteger("respId"));
                    menumap.put("menuId", menuJson.getInteger("menuId"));
                    respMenuRow = saafRespMenuDAO_HI.findByProperty(menumap).get(0);
                    saafRespMenuDAO_HI.delete(respMenuRow);
                } else {
                    funmap.put("respId", params.getInteger("respId"));
                    funmap.put("respFunId", menuJson.getInteger("respFunId"));
                    respFuncRow = saafRespFunDAO_HI.findByProperty(funmap).get(0);
                    saafRespFunDAO_HI.delete(respFuncRow);
                }
            }
            return SToolUtils.convertResultJSONObj("S", "删除菜单成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询不在快捷菜单中的用户菜单
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public String queryUserAllMenuNotInShortcut(JSONObject queryParamJSON, Integer pageIndex, Integer pageSize) {

        StringBuffer query = new StringBuffer();
        query.append(SaafRespMenuEntity_HI_RO.QUERY_USER_ALL_MENU_NOTIN_SHORTCUT);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        queryParamMap.put("varInputRespId", queryParamJSON.get("varInputRespId"));
        queryParamMap.put("varUserId", queryParamJSON.get("varUserId"));
        SaafToolUtils.parperParam(queryParamJSON, "saaf_menu_functions.prompt", "var_like_prompt", query, queryParamMap, "like");
        String countSql = "select count(1) from (" + query + ")";
        Pagination findListResult = saafRespMenuDAO_HI_RO.findPagination(query.toString(),countSql, queryParamMap, pageIndex, pageSize);

        return JSON.toJSONString(findListResult);
    }
}
