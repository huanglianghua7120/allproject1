package saaf.common.fmw.base.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafLookupTypesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafLookup;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.services.CommonAbstractServices;


/*===========================================================+
  |   Copyright (c) 2012 赛意信息科技有限公司                                          |
+===========================================================+
  |  HISTORY                                                                        |
  | ============ ====== ============  ===========================                   |
  |  Date                     Ver.        liuwenjun                Content          |
  | ============ ====== ============  ===========================                   |
  |  Aug 20, 2016            1.0          创建                      Creation        |
  |  Desc:用来处理快码管理
 +===========================================================*/

@Path("/saafLookupServlet")
@Component
public class BaseSaafLookupServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafLookupServices.class);
    @Context
    protected ServletContext application;
    @Autowired
    private ISaafLookup baseSaafLookupServer;

    public BaseSaafLookupServices() {
        super();
    }


    /**
     * 查询快码lov（分页，通用）
     *
     * @param params
     * @return
     */
    @POST
    @Path("findLookupCodeLov")
    public String findLookupCodeLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SaafLookupValuesEntity_HI_RO> pag = baseSaafLookupServer.findLookupCodeLov(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {

            LOGGER.error("查询快码失败" + e,e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null);
        }
    }
    /**
     * 查询快码lov（分页，通用-外部）
     *
     * @param params
     * @return
     */
    @POST
    @Path("findLookupCodeLovE")
    public String findLookupCodeLovE(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject paramJSON = this.parseObjectExternal(params);
            Pagination<SaafLookupValuesEntity_HI_RO> pag = baseSaafLookupServer.findLookupCodeLov(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(pag);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {

            LOGGER.error("查询快码失败" + e,e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null);
        }
    }

    /**
     * 查询下拉框(查询全部，非通用版本)(新版本)
     * @param params
     * @return
     */
    @POST
    @Path("findLookupCodeNew")
    public String findLookupCodeNew(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = JSONObject.parseObject(params);
            String requestSaafLookupCodes_Version = "";
            if (jsonParams.getString("saafLookupCodes_Version") != null) {
                requestSaafLookupCodes_Version = jsonParams.getString("saafLookupCodes_Version");
            }
            if (application.getAttribute("saafLookupCodes_Version") == null) {
                List LookupCodelist = baseSaafLookupServer.findLookupCode(null);
                application.setAttribute("saafLookupCodes", LookupCodelist);
                application.setAttribute("saafLookupCodes_Version", new Date().toString());
            }
            String saafLookupCodes_Version =
                application.getAttribute("saafLookupCodes_Version") == null ? new Date().toString() : (String)application.getAttribute("saafLookupCodes_Version");

            if (requestSaafLookupCodes_Version.equals(saafLookupCodes_Version)) {
                LOGGER.info("--->>findLookupCode-->>参数：" + params + "查询快码成功！");
                return CommonAbstractServices.convertResultJSONObj("S", application.getAttribute("saafLookupCodes_Version").toString(), 0, null); //0表示浏览器版本的快码，是最新的，不需要更新
            } else {
                List saafLookupCodes = (List)application.getAttribute("saafLookupCodes");
                LOGGER.info("--->>findLookupCode-->>参数：" + params + "查询快码成功！");
                //1表示浏览器版本的快码，不是最新的，需要更新
                return CommonAbstractServices.convertResultJSONObj("S", application.getAttribute("saafLookupCodes_Version").toString(), saafLookupCodes.size(), saafLookupCodes);
            }
        } catch (Exception e) {
            LOGGER.error("查询快码失败" + e, e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询快码失败!", 0, null);
        }
    }

    /**
     * 查询下拉框(通用）
     *
     * @param params
     * @return
     */
    @POST
    @Path("findLookupCode")
    public String findLookupCode(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParams = JSONObject.parseObject(params);
            List LookupCodelist = baseSaafLookupServer.findLookupCode(jsonParams);
            LOGGER.info("--->>findLookupCode-->>参数：" + params + "查询快码成功！");
            return CommonAbstractServices.convertResultJSONObj("S", "查询快码成功！", LookupCodelist.size(), LookupCodelist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询快码失败" + e,e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询快码失败!", 0, null);
        }
    }


    /**
     * 查询快码（可分页）
     *
     * @param params
     * @return
     */
    @POST
    @Path("findLookupCodePagination")
    public String findLookupCodePagination(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("lookupType", jsonParams.getString("lookupType"));
            Pagination lookupCodelist;
            if (pageIndex == null || pageRows == null || pageIndex == 0 || pageRows == 0) {
                lookupCodelist = baseSaafLookupServer.findLookupCodePagination(map);
            } else {
                lookupCodelist = baseSaafLookupServer.findLookupCodePagination(jsonParams, pageIndex, pageRows);
            }

            LOGGER.info("--->>findLookupCode-->>参数：" + params + "查询快码成功！");
            return JSONObject.toJSONString(lookupCodelist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            
            LOGGER.error("查询快码失败" + e,e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询快码失败!" + e, 0, null);
        }
    }


    /**
     * 查询快码列表
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("findLookupType")
    public String findLookupType(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            System.out.println("--->>findLookupType-->>参数：" + params + ",pageIndex:" + pageIndex + "pageRows" + pageRows + "查询快码成功！");

            JSONObject jsonParams = this.parseObject(params);
            //ISaafLookup saafLookupServer = (ISaafLookup) getServerBean("baseSaafLookupServer");
            Pagination LookupTypelist = baseSaafLookupServer.findLookupType(jsonParams, pageIndex, pageRows);
            LOGGER.info("--->>findLookupType-->>参数：" + params + ",pageIndex:" + pageIndex + "pageRows" + pageRows + "查询快码成功！");
            return JSONObject.toJSONString(LookupTypelist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            
            LOGGER.error("查询快码失败" + e,e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询快码失败!" + e, 0, null);
        }
    }

    /**
     * 查询快码值表列表
     *
     * @param params
     * @return
     */
    @POST
    @Path("findLookupTypeLine")
    public String findLookupTypeLine(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            //            ISaafLookup saafLookupServer = (ISaafLookup) getServerBean("baseSaafLookupServer");
            Integer lookupTypeId = jsonParams.getInteger("lookupTypeId");
            SaafLookupTypesEntity_HI lookupTypeRow = baseSaafLookupServer.findLookupTypeById(lookupTypeId);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("lookupType", lookupTypeRow.getLookupType());
            List lookupValuesList = baseSaafLookupServer.findLookupValues(map);
            JSONObject jsonData = new JSONObject();
            jsonData.put("lookupTypeRow", lookupTypeRow);
            jsonData.put("lookupValuesList", lookupValuesList);
            return CommonAbstractServices.convertResultJSONObj("S", "查询快码值成功！", 1, jsonData);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            
            LOGGER.error("查询快码值失败" + e,e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询快码值失败!" + e, 0, null);
        }
    }

    /**
     * 保存快码
     *
     * @param params
     * @return
     */
    @POST
    @Path("saveLookupType")
    public String saveLookupType(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject json = baseSaafLookupServer.saveLookupType(jsonParams);
            if ("E".equals(json.getString("status"))) {
                return CommonAbstractServices.convertResultJSONObj("E", json.getString("msg"), 0, null);
            } else {
                List LookupCodelist = baseSaafLookupServer.findLookupCode(null);
                application.setAttribute("saafLookupCodes", LookupCodelist);
                application.setAttribute("saafLookupCodes_Version", new Date().toString());
                return CommonAbstractServices.convertResultJSONObj("S", "保存快码成功！", 1, json.get("data"));
            }
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("保存快码失败:" + e, e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存快码失败！", 0, null);
        }
    }

    /**
     * 删除快码
     *
     * @param params
     * @return
     */
    @POST
    @Path("deleteLookupValues")
    public String deleteLookupValues(@FormParam("params")
        String params) {
        try {
            //ISaafLookup saafLookupServer = (ISaafLookup) getServerBean("baseSaafLookupServer");
            JSONObject jsonParams = this.parseObject(params);
            JSONObject json = baseSaafLookupServer.deleteLookupValues(jsonParams);
            if ("E".equals(json.getString("status"))) {
                return CommonAbstractServices.convertResultJSONObj("E", json.getString("msg"), 0, null);
            } else {
                List LookupCodelist = baseSaafLookupServer.findLookupCode(null);
                application.setAttribute("saafLookupCodes", LookupCodelist);
                application.setAttribute("saafLookupCodes_Version", new Date().toString());
                return CommonAbstractServices.convertResultJSONObj("S", "删除快码成功!", 1, null);
            }
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            
            LOGGER.error("删除快码失败" + e, e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除快码失败!" + e, 0, null);
        }
    }
}
