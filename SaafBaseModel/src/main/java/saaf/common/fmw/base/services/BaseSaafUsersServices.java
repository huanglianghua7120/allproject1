package saaf.common.fmw.base.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SaafUserEmployeesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafUserResp;
import saaf.common.fmw.base.model.inter.ISaafUsers;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.utils.SHA1Util;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*===========================================================+
  |   Copyright (c) 2012 赛意信息科技有限公司                                          |
+===========================================================+
  |  HISTORY                                                                        |
  | ============ ====== ============  ===========================                   |
  |  Date                     Ver.        liuwenjun                Content          |
  | ============ ====== ============  ===========================                   |
  |  Aug 20, 2016            1.0          创建                      Creation        |
  |  Desc:用来处理用户维护
 +===========================================================*/
@Component("baseSaafUsersServices")
@Path("/saafUsersServlet")
public class BaseSaafUsersServices extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafUsersServices.class);

    @Autowired
    private ISaafUsers baseSaafUsersServer;

    @Autowired
    private ISaafUserResp baseSaafUserRespServer;

    public BaseSaafUsersServices() {
        super();
    }

    /**
     * 查询用户列表
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Path("findSaafUsersList")
    @POST
    public String findSaafUsersList(@FormParam("params")
                                            String params, @FormParam("pageIndex")
                                            Integer pageIndex, @FormParam("pageRows")
                                            Integer pageRows) {
        LOGGER.info("findSaafUsersList:" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination usersList = baseSaafUsersServer.findSaafUsersList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(usersList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询用户列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询用户列表失败!", 0, null);
        }
    }

    /**
     * 查询用户员工信息
     *
     * @param params
     * @return
     */
    @Path("findSaafUsers")
    @POST
    public String findSaafUsers(@FormParam("params")
                                        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            SaafUserEmployeesEntity_HI_RO users = baseSaafUsersServer.findSaafUsersById(jsonParam);
            Map<String, Object> map = new HashMap();
            map.put("userId", jsonParam.get("userId"));
            List userRespList = baseSaafUserRespServer.findSaafUserRespByUserId(map);
            JSONObject jsonData = JSONObject.parseObject(JSONObject.toJSONString(users));
            jsonData.put("respdata", userRespList);
            return CommonAbstractServices.convertResultJSONObj("S", "查询用户信息成功!", 1, jsonData);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询用户信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询用户信息失败!", 0, null);
        }
    }

    /**
     * 查询用户名称（LOV）
     *
     * @param params
     * @return
     */
    @Path("findSaafUsersLov")
    @POST
    public String findSaafUsersLov(@FormParam("params")
                                           String params, @FormParam("pageIndex")
                                           Integer pageIndex, @FormParam("pageRows")
                                           Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //            ISaafUsers saafusersServer = (ISaafUsers)this.getServerBean("baseSaafUsersServer");
            return baseSaafUsersServer.findSaafUsersLov(jsonParam, pageIndex, pageRows);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询用户名称异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询用户名称失败!", 0, null);
        }
    }

    /**
     * 保存用户员工信息
     *
     * @param params
     * @return
     */
    @Path("saveSaafUsers")
    @POST
    public String saveSaafUsers(@FormParam("params")
                                        String params) {
        LOGGER.info(" saveSaafUsers params:" + params);
        try {
            String isAdmin = this.getIsAdmin();
            JSONObject jsonParam = this.parseObject(params);
            //超级管理员用户，参数获取
            if ("SUPER".equalsIgnoreCase(isAdmin)) {
                jsonParam.put("varPlatformCode", jsonParam.getString("platformCode"));
            } else {
                //管理员用户&普通用户 session获取
                jsonParam.put("varPlatformCode", getUserSessionBean().getPlatformCode());
            }
            //            ISaafUsers saafusersServer = (ISaafUsers)this.getServerBean("baseSaafUsersServer");
            JSONObject usersJson = baseSaafUsersServer.saveSaafUsersInfo(jsonParam);
            if ("E".equals(usersJson.getString("status"))) {
                String msg = usersJson.getString("msg");
                if (msg != null && msg.contains("Exception:")) {
                    msg = msg.substring(msg.indexOf("Exception:") + 10, msg.length());
                }
                return CommonAbstractServices.convertResultJSONObj("E", msg, 0, null);
            } else {
                return CommonAbstractServices.convertResultJSONObj("S", "保存用户列表成功！", 1, usersJson.get("data"));
            }
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "保存用户列表失败:"+e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("保存用户列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存用户列表失败:"+e.getMessage(), 0, null);
        }catch (Exception e) {
            LOGGER.error("保存用户列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存用户列表失败:"+e.getMessage(), 0, null);
        }
    }

    /**
     * 修改用户密码
     *
     * @param params
     * @return
     */
    @Path("updateSaafUserPassword")
    @POST
    public String updateSaafUserPassword(@FormParam("params") String params) {
        try {
            String isAdmin = this.getIsAdmin();
            JSONObject jsonParam = this.parseObject(params);
            //超级管理员用户，参数获取
            if ("SUPER".equalsIgnoreCase(isAdmin)) {
                jsonParam.put("varPlatformCode", jsonParam.getString("platformCode"));
            } else {
                //管理员用户&普通用户 session获取
                jsonParam.put("varPlatformCode", getUserSessionBean().getPlatformCode());
            }
            Integer varUserId = this.getUserSessionBean() == null ? null : this.getUserSessionBean().getUserId();
            if (varUserId == null) {
                return CommonAbstractServices.convertResultJSONObj("E", "修改密码失败，无法从session中获取userId", 0, null);
            }
            //            jsonParam.put("varUserId", varUserId == null ? -9999 : varUserId);
            //            ISaafUsers saafusersServer = (ISaafUsers)this.getServerBean("baseSaafUsersServer");
            return baseSaafUsersServer.updateUserPassword(jsonParam);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "修改用户密码失败！", 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("修改用户密码失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "修改用户密码失败！", 0, null);
        }
    }

    /**
     * 查询用户列表
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Path("findEmpInfo")
    @POST
    public String findEmpInfo(@FormParam("params")
                                      String params, @FormParam("pageIndex")
                                      Integer pageIndex, @FormParam("pageRows")
                                      Integer pageRows) {
        LOGGER.info("------jsonParam-----" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination empList = baseSaafUsersServer.findEmpInfo(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(empList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "查询员工信息lov失败！", 0, null);
        } catch (Exception e) {
            LOGGER.error("查询员工信息lov异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询员工信息lov失败！", 0, null);
        }
    }

    /**
     * 查询所有用户的基本信息，转为接口提供
     *
     * @return
     */
    @Path("findAllUser")
    @POST
    public String findAllUser() {
        try {
            JSONObject jsonParam = this.parseObject("");
            return baseSaafUsersServer.findAllUser().toJSONString();
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "查询失败！", 0, null);
        } catch (Exception e) {
            LOGGER.error("查询用户列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败！", 0, null);
        }
    }

    /**
     * 查询人员lov  查询字段以及条件employeeNumber,employeeName,positionName
     *
     * @param params
     * @return
     */
    @POST
    @Path("findEmployeesLov")
    public String findEmployeesLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            Pagination<SaafUserEmployeesEntity_HI_RO> pag = baseSaafUsersServer.findEmployeesLov(jsonParams, pageIndex, pageRows);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("查询失败" + e, e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
        }
    }

    /**
     * 創建者lov  查询字段及条件saaf_users.userName,saaf_employees.employeeName
     *
     * @param params
     * @return
     */
    @POST
    @Path("findCreatorLov")
    public String findCreatorLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            Pagination<SaafUserEmployeesEntity_HI_RO> pag = baseSaafUsersServer.findCreatorLov(jsonParams, pageIndex, pageRows);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("查询失败" + e, e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
        }
    }

    /**
     * 系统主页，查询用户的基本信息
     *
     * @param params
     * @return
     */
    @Path("findUserInfo")
    @POST
    public String findUserInfo(@FormParam("params") String params) {
        LOGGER.info("findUserInfo:" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            String varToken = jsonParam.getString("varToken");
            SHA1Util sha1 = new SHA1Util();
            String encryptUserId = sha1.getEncrypt("userId:" + jsonParam.get("userId"));
            if (varToken != null && varToken.equals(encryptUserId)) {
                SaafUserEmployeesEntity_HI_RO users = baseSaafUsersServer.findSaafUsersById(jsonParam);
                JSONObject jsonData = JSONObject.parseObject(JSONObject.toJSONString(users));
                return CommonAbstractServices.convertResultJSONObj("S", "查询用户信息成功!", 1, jsonData);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询用户信息失败!", 0, null);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "请登录系统!", 0, null);
        } catch (Exception e) {
            LOGGER.error("查询用户信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询用户信息失败!", 0, null);
        }
    }
}
