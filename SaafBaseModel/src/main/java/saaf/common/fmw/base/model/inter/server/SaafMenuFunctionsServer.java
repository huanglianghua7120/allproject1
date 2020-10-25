package saaf.common.fmw.base.model.inter.server;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafFunctionsAuthEntity_HI;
import saaf.common.fmw.base.model.entities.SaafMenuFunctionsEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafMenuFuncEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafRespMenuEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafMenuFunctions;
import saaf.common.fmw.common.utils.SaafToolUtils;


@Component("baseSaafMenuFunctionsServer")
public class SaafMenuFunctionsServer implements ISaafMenuFunctions {
    public SaafMenuFunctionsServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SaafRespMenuEntity_HI_RO> saafRespMenuDAO_HI_RO;

    @Autowired
    private BaseViewObject<SaafMenuFuncEntity_HI_RO> saafRespMenuFuncDAO_HI_RO;

    @Autowired
    private ViewObject<SaafFunctionsAuthEntity_HI> saafFunctionsAuthDAO_HI;
    @Autowired
    private ViewObject<SaafMenuFunctionsEntity_HI> saafMenuFunctionsDAO_HI;

    /**
     * 查询职责菜单(登录)
     * @param params
     * @return
     */
    public List findSaafMenuList(JSONObject params) throws Exception {
        try {
        	StringBuffer sb = new StringBuffer();
        	sb.append(SaafRespMenuEntity_HI_RO.QUERY_SQL);
        	
        	Map<String,Object> queryParamMap = new HashMap<String, Object>();
        	queryParamMap.put("platformCode", "SAAF");
        	queryParamMap.put("respId", params.getString("respId"));
        	SaafToolUtils.parperParam(params, "smf.is_mobile_show","isMobileShow", sb, queryParamMap, "=");
        	sb.append(" ORDER BY smf.menu_parent_id, smf.entry_sequence");
            List resrMenuList = saafRespMenuDAO_HI_RO.findList(sb, queryParamMap);
            return resrMenuList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询职责菜单按钮(登录)
     * @param params
     * @return
     */
    public List findSaafMenuBtnList(Object[] params) throws Exception {
        try {
            List resrMenuBtnList = saafRespMenuDAO_HI_RO.findList(SaafRespMenuEntity_HI_RO.QUERY_BUTTON_SQL, params);
            return resrMenuBtnList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询菜单全部Tree
     * @param params
     * @return
     * @throws Exception
     */
    public List findSaafMenuFuncAll(Object[] params) throws Exception {
        try {
            List resrMenuList = saafRespMenuDAO_HI_RO.findList(SaafMenuFuncEntity_HI_RO.QUERY_TREE_SQL, params);
            return resrMenuList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询菜单一行
     * @param params
     * @return
     * @throws Exception
     */
    public SaafMenuFuncEntity_HI_RO findSaafMenuFuncLine(Object[] params) throws Exception {
        try {
            SaafMenuFuncEntity_HI_RO muenLine = null;
            List<SaafMenuFuncEntity_HI_RO> resrMenuList = saafRespMenuFuncDAO_HI_RO.findList(SaafMenuFuncEntity_HI_RO.QUERY_LINE_SQL, params);
            if (resrMenuList.size() > 0) {
                muenLine = resrMenuList.get(0);
            }
            return muenLine;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询菜单按钮
     * @param map
     * @return
     * @throws Exception
     */
    public List findMenuBtnList(Map<String, Object> map) throws Exception {
        try {
            List resrMenuButtonList = saafFunctionsAuthDAO_HI.findByProperty(map);
            return resrMenuButtonList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 保存菜单&功能
     * @param params
     * @return
     * @throws Exception
     */
    public JSONObject saveSaafMenu(JSONObject params) throws Exception {
        try {
            //System.out.println("------params--Server-----" + params);
            SaafMenuFunctionsEntity_HI menuRow = null;
            Integer operatorUserId = params.getInteger("varUserId");
            if (null == params.get("menuId") || "".equals(params.getString("menuId"))) {
                boolean flag = findExistsMenuTitle(params.getString("menuTittle"), params.getString("prompt"));
                if (flag) {
                    return SToolUtils.convertResultJSONObj("E", "保存失败!菜单标题或菜单名称：" + params.getString("menuTittle") + "/" + params.getString("prompt") + "已存在", 0, null);
                } else {
                    menuRow = new SaafMenuFunctionsEntity_HI();
                }
            } else { //判断存在就是修改
                menuRow = saafMenuFunctionsDAO_HI.findByProperty("menuId", params.getInteger("menuId")).get(0);
            }
            menuRow.setMenuParentId(params.getInteger("menuParentId"));
            menuRow.setPlatformCode(params.getString("varPlatformCode"));
            menuRow.setEntrySequence(params.getInteger("entrySequence"));
            menuRow.setMenuTittle(params.getString("menuTittle"));
            menuRow.setPrompt(params.getString("prompt"));
            menuRow.setDescription(params.getString("description"));
            menuRow.setEnableFlag(params.getString("enableFlag"));
            menuRow.setFunctionsType(params.getString("functionsType"));
            menuRow.setIsMenu(params.getString("isMenu"));
            menuRow.setWebHtmlCall(params.getString("webHtmlCall"));
            menuRow.setImageLink(params.getString("imageLink"));
            menuRow.setIsMobileShow(params.getString("isMobileShow"));
            menuRow.setImageColor(params.getString("imageColor"));
            menuRow.setCategoryFlag(params.getString("categoryFlag"));
            if (null != params.get("startDateActive") && !"".equals(params.getString("startDateActive").trim())) {
                Date startDateActive = SToolUtils.string2DateTime(params.getString("startDateActive"), "yyyy-MM-dd");
                menuRow.setStartDateActive(startDateActive);
            }
            if (null != params.get("endDateActive") && !"".equals(params.getString("endDateActive").trim())) {
                Date endDateActive = SToolUtils.string2DateTime(params.getString("endDateActive"), "yyyy-MM-dd");
                menuRow.setEndDateActive(endDateActive);
            } else {
                menuRow.setEndDateActive(null);
            }
            menuRow.setOperatorUserId(operatorUserId);
            //保存菜单
            saafMenuFunctionsDAO_HI.saveOrUpdate(menuRow);
            //保存按钮
            //如果存在按保存，否则不处理
            if (params.get("btndata") != null && !"btndata".equals(params.getString(""))) {
                if (params.get("btndata") instanceof JSONArray) {
                    saveSaafMenuButton(params);
                }
            }
            
            if(params.getBooleanValue("updateParent")){
            	SaafMenuFunctionsEntity_HI parentMenu = saafMenuFunctionsDAO_HI.getById(menuRow.getMenuParentId());
            	parentMenu.setIsMenu(params.getString("parentIsMenu"));
            	saafMenuFunctionsDAO_HI.saveOrUpdate(parentMenu);
            }
            
            return SToolUtils.convertResultJSONObj("S", "保存菜单成功", 1, menuRow);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception(e);
        }
    }

    /**
     * 保存菜单按钮
     * @param params
     * @return
     * @throws Exception
     */
    public JSONObject saveSaafMenuButton(JSONObject params) throws Exception {
        Integer operatorUserId = params.getInteger("varUserId");
        try {
            SaafFunctionsAuthEntity_HI funcBtnRow = null;
            JSONArray btndata = params.getJSONArray("btndata");
            if (null == btndata || btndata.size() <= 0) {
                return SToolUtils.convertResultJSONObj("S", "保存菜单按钮成功", 1, btndata);
            }
            List<SaafFunctionsAuthEntity_HI> btnList = new ArrayList<SaafFunctionsAuthEntity_HI>();
            for (int i = 0; i < btndata.size(); i++) {
                JSONObject btnRow = btndata.getJSONObject(i);
                if (null == btnRow.get("funButtonId") || "".equals(SToolUtils.object2String(btnRow.get("funButtonId")))) {
                    funcBtnRow = new SaafFunctionsAuthEntity_HI();
                } else { //判断存在就是修改
                    funcBtnRow = saafFunctionsAuthDAO_HI.findByProperty("funButtonId", btnRow.getInteger("funButtonId")).get(0);
                }
                funcBtnRow.setPlatformCode(params.getString("varPlatformCode"));
                funcBtnRow.setButtonCode(SToolUtils.object2String(btnRow.get("buttonCode")));
                funcBtnRow.setButtonName(SToolUtils.object2String(btnRow.get("buttonName")));
                funcBtnRow.setButtonPermission(SToolUtils.object2String(btnRow.get("buttonPermission")));
                funcBtnRow.setMenuId(SToolUtils.object2Int(params.get("menuId")));
                funcBtnRow.setOperatorUserId(operatorUserId);
                btnList.add(funcBtnRow);
            }
            saafFunctionsAuthDAO_HI.saveOrUpdateAll(btnList);
            return SToolUtils.convertResultJSONObj("S", "保存菜单按钮成功", 1, btnList);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 删除菜单按钮
     * @param params
     * @return
     * @throws Exception
     */
    public JSONObject deleteSaafMenuBtn(JSONObject params) throws Exception {
        try {
            //验证是否允许删除XXX
            SaafFunctionsAuthEntity_HI funcBtnRow = null;
            List btnlist = saafFunctionsAuthDAO_HI.findByProperty("funButtonId", params.getInteger("funButtonId"));
            if (btnlist != null && btnlist.size() > 0) {
                funcBtnRow = (SaafFunctionsAuthEntity_HI)btnlist.get(0);
                saafFunctionsAuthDAO_HI.delete(funcBtnRow);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除菜单按钮失败，" + params.getString("funButtonId") + "不存在", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "删除菜单按钮成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 验证菜单名称和标题是否重复
     * @param menuTittle
     * @param prompt
     * @return
     */
    public boolean findExistsMenuTitle(String menuTittle, String prompt) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from SaafMenuFunctionsEntity_HI where  menuTittle ='" + menuTittle + "'");
        sql.append(" or prompt ='" + prompt + "'");
        List<SaafMenuFunctionsEntity_HI> list = saafMenuFunctionsDAO_HI.findList(sql.toString());
        if (list.size() >= 1) {
            return true;
        }
        return false;

    }

    /**
     * 验证按钮编码是否重复
     * @param buttonCode
     * @return
     */
    public boolean findExistsButtonCode(String buttonCode) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from SaafFunctionsAuthEntity_HI where  buttonCode ='" + buttonCode + "'");
        List<SaafFunctionsAuthEntity_HI> list = saafFunctionsAuthDAO_HI.findList(sql.toString());
        if (list.size() >= 1) {
            return true;
        }
        return false;

    }

}
