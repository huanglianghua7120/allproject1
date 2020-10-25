package saaf.common.fmw.base.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.yhg.spring.aop.aspect.exception.customexcept.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafMenuFuncEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafMenuFunctions;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.services.CommonAbstractServices;

import com.alibaba.fastjson.JSONObject;
import saaf.common.fmw.utils.SHA1Util;


/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：BaseSaafLookupServices.java
 * Description：菜单管理控制类
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     钟士元             创建
 * ===========================================================================
 */
@Component("baseSaafMenuFunctionsServices")
@Path("/saafMenuFuncServlet")
public class BaseSaafMenuFunctionsServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafMenuFunctionsServices.class);

    @Autowired
    private ISaafMenuFunctions baseSaafMenuFunctionsServer;

    public BaseSaafMenuFunctionsServices() {
        super();
    }

    /**
     * 首页查询菜单(首页登录)
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    @POST
    @Path("findMenuTree")
    public String findMenuFuncTree(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            String varToken = jsonParams.getString("varToken");
            SHA1Util sha1 = new SHA1Util();
            String encryptRespId = sha1.getEncrypt("varRespId:" + jsonParams.get("respId"));
            if (varToken == null || !varToken.equals(encryptRespId)) {
                return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
            }

            Object[] objparams = new Object[2];
            //参数为空则从session获取第一个职责ID
            if (jsonParams.get("respId") != null) {
                objparams[0] = jsonParams.getInteger("respId");
            } else {
                List userRespList = getUserSessionBean().getUserRespList();
                Map userResp = (Map)userRespList.get(0);
                objparams[0] = userResp.get("responsibilityId");
                jsonParams.put("respId", userResp.get("responsibilityId"));
            }
            objparams[1] = "SAAF";
            List menuFunclist = baseSaafMenuFunctionsServer.findSaafMenuList(jsonParams);

            JSONObject json = new JSONObject();
            json.put(CommonAbstractServices.STATUS, "S");
            json.put(CommonAbstractServices.MSG, "查询菜单成功！");
            json.put(CommonAbstractServices.COUNT, menuFunclist.size());
            json.put(CommonAbstractServices.DATA, menuFunclist);
            
            if(!(jsonParams.containsKey("isMobileShow") && "Y".equals(jsonParams.get("isMobileShow")))){
            	List menuBtnlist = baseSaafMenuFunctionsServer.findSaafMenuBtnList(objparams);
            	json.put("buttonData", menuBtnlist);
            }
            return json.toJSONString();
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("--->>findMenuTree--查询菜单失败！参数：" + params + ",异常：" + e);
            if (e instanceof BusinessException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }


    /**
     * 查询菜单树结构(菜单维护)
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    @POST
    @Path("findMenuFuncList")
    public String findMenuFuncList(@FormParam("params")
        String params) {
        Object[] objparams = new Object[1];
        try {
            objparams[0] = "SAAF"; //getUserSessionBean().getPlatformCode();
            List menuFuncTree = baseSaafMenuFunctionsServer.findSaafMenuFuncAll(objparams);
            return CommonAbstractServices.convertResultJSONObj("S", "查询菜单成功！", menuFuncTree.size(), menuFuncTree);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("--->>findMenuTree--查询菜单失败！参数：" + params + ",异常：" + e);
            if (e instanceof BusinessException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * 查询菜单一行数据&功能(菜单维护)
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    @POST
    @Path("findMenuFuncLine")
    public String findMenuFuncLine(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            Object[] objparams = new Object[1];
            objparams[0] = jsonParams.getInteger("menuId");
            //ISaafMenuFunctions saafMenuFunctionsServer = (ISaafMenuFunctions)SaafToolUtils.context.getBean("baseSaafMenuFunctionsServer");
            //SaafRespMenuEntity_HI_RO entity_HI_RO = baseSaafMenuFunctionsServer.findSaafMenuFuncLine(params);
            SaafMenuFuncEntity_HI_RO menuFuncLine = baseSaafMenuFunctionsServer.findSaafMenuFuncLine(objparams);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("menuId", jsonParams.getInteger("menuId"));
            List menuBtnList = baseSaafMenuFunctionsServer.findMenuBtnList(map);
            JSONObject JsonData = new JSONObject();
            JsonData.put("menudata", menuFuncLine);
            JsonData.put("menubtndata", menuBtnList);
            LOGGER.info("--->>findMenuTree-->>参数：" + params + "查询菜单成功！");
            return CommonAbstractServices.convertResultJSONObj("S", "查询菜单成功！", 1, JsonData);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("--->>findMenuTree--查询菜单失败！参数：" + params + ",异常：" + e);
            if (e instanceof BusinessException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * 保存菜单&&功能(菜单维护)
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    @POST
    @Path("saveMenuFunc")
    public String saveMenuFunc(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            //            ISaafMenuFunctions saafMenuFunctionsServer = (ISaafMenuFunctions)SaafToolUtils.context.getBean("baseSaafMenuFunctionsServer");
            JSONObject jsondata = baseSaafMenuFunctionsServer.saveSaafMenu(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("--->>findMenuTree--保存菜单失败！参数：" + params + ",异常：" + e);
            if (e instanceof BusinessException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }


    /**
     * 删除菜单功能按钮(菜单维护)
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    @POST
    @Path("deleteMenuBtn")
    public String deleteMenuBtn(@FormParam("params")
        String params) {
        try {
            //            System.out.println("------params-------" + params);
            JSONObject jsonParams = this.parseObject(params);
            //            ISaafMenuFunctions saafMenuFunctionsServer = (ISaafMenuFunctions)SaafToolUtils.context.getBean("baseSaafMenuFunctionsServer");
            JSONObject jsondata = baseSaafMenuFunctionsServer.deleteSaafMenuBtn(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("--->>findMenuTree--删除菜单按钮失败！参数：" + params + ",异常：" + e);
            if (e instanceof BusinessException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }
}
