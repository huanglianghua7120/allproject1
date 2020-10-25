package saaf.common.fmw.common.services;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.yhg.redis.framework.WSSecurityPolicy;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;

import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafUserInstEntity_HI_RO;
import saaf.common.fmw.base.model.inter.*;
import saaf.common.fmw.base.utils.IpUtils;
import saaf.common.fmw.base.utils.Base64Utils;
import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.common.model.inter.server.ShortDescMessageServer;
import saaf.common.fmw.shiro.ShiroConstant;
import saaf.common.fmw.utils.LDAPAuthentication;
import saaf.common.fmw.utils.SHA1Util;


/*===========================================================+
  |   Copyright (c) 2012 赛意信息科技有限公司                                         |
+===========================================================+
  |  HISTORY                                                                        |
  | ============ ====== ============  ===========================                   |
  |  Date                     Ver.        Gavin                   Content          |
  | ============ ====== ============  ===========================                   |
  |  Aug 20, 2016            1.0           XXX                      Creation        |
  |  Desc:用来处理用户登录退出的服务
 +===========================================================*/

@Component("saafLoginOutServer")
@Path("/saafLoginServlet")
public class SaafLoginOutService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaafLoginOutService.class);

    @Context
    private HttpServletRequest request;

    //@Context
    //private HttpServletResponse response;

    @Autowired
    private ShortDescMessageServer shortDescMessageServer;

    //private static final Integer EXPIRESECONDS = 1000 * 60 * 60; // Token失效时间（单位秒），
    //小于或等于0，则默认最大2小时过期

    @Autowired
    private ISaafLoginsHis saafLoginsHisServer;

    @Autowired
    private ISaafUsers baseSaafUsersServer;

    @Autowired
    private ISaafUserResp baseSaafUserRespServer;

    @Autowired
    private ISaafUsersInst baseSaafUsersInstServer;
    @Autowired
    private WSSecurityPolicy wsSecurityPolicy;
    @Autowired
    private ISaafLookup baseSaafLookupServer;

    /**
     * 用户退出
     *
     * @return @Consumes("application/json") @Produces("application/json")
     */
    @POST
    @Path("logout")
    public String doLogout() {
        LOGGER.info("logout success................");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return SToolUtils.convertResultJSONObj("S", "退出成功", 1, "").toString();
    }

    /**
     * 用户登录
     *
     * @param params
     * @return @Consumes("application/json") @Produces("application/json")
     */
    @POST
    @Path("login")
    public String doLogin(@FormParam("params") String params, @Context HttpServletResponse response) {
        JSONObject jsonObject = JSON.parseObject(params);
        if (jsonObject == null) {
            return shortDescMessageServer.getJsonResultStr("NEED-PARAMS");
        }
        HttpSession session = request.getSession();
        UserInfoSessionBean sessionBean = new UserInfoSessionBean();
        String sessionId = session.getId();
        String name = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String ldapPassword = jsonObject.getString("ldapPassword");
        //此段暂时注释登录验证码(2018-04-23)
        if (!(null != jsonObject.get("check") && jsonObject.getString("check").toUpperCase().equals(session.getAttribute("captcha")))) {
            JSONObject resultJSON = SToolUtils.convertResultJSONObj("E", "验证码输入错误!", 0, "");
            return resultJSON.toString();
        }
        if (name == null || "".equals(name) || password == null || "".equals(password)) {
            LOGGER.info("Login----->>登录失败，请输入正确的用户名和密码!");
            JSONObject resultJSON = SToolUtils.convertResultJSONObj("E", "请输入正确的用户名和密码!", 0, "");
            return resultJSON.toString();
        }

        //获取当前IP，本地运行执行ldap方法
        String userIp = IpUtils.getIpAddress(request);

        //不验证密码先判断当前账号是否存在再获取当前账号是否内部人员，和管理员。非管理员的内部人员要用ldap服务来验证密码
        SaafUsersEntity_HI user = userLoginByDatabaseReturnUser(name, sessionBean);
        if (user == null) {
            return SToolUtils.convertResultJSONObj("E", "账户不存在，请先创建账户！", 0, "").toString();
        } else {
            //判断当前账号是否存在再获取当前账号是否内部人员，和管理员。非管理员的内部人员要用ldap服务来验证密码
//            if(!"Y".equals(user.getIsadmin())&&user.getSupplierId()==null&&!"127.0.0.1".equals(userIp)){
            if (true) {
                //内部人员统一ldap认证
                ldapPassword = Base64Utils.decode2PreToString(name, ldapPassword);
                boolean flag = LDAPAuthentication.checkUser(name, ldapPassword);
                if (!flag) {
                    //todo 暂时同时开启ldap验证和密码验证。以后将以下代码删除
                    if (!password.equals(user.getEncryptedPassword())) {
                        return SToolUtils.convertResultJSONObj("E", "请输入正确的用户名和密码", 0, "").toString();
                    }
                    //todo 正式环境将以下放开将以上注释
//                    r
// eturn SToolUtils.convertResultJSONObj("E", "该账户未通过ldap认证，请检查重新登录。", 0, "").toString();
                }

            }//当前账号是供应商或者管理员则用账号密码登录
            else {
                if (!password.equals(user.getEncryptedPassword())) {
                    return SToolUtils.convertResultJSONObj("E", "请输入正确的用户名和密码", 0, "").toString();
                }
            }
        }


        sessionBean.setLanguage(request.getLocalName());
        sessionBean.setSessionId(sessionId);
        sessionBean.setUserName(name);
        sessionBean.setUserIpAddress(request.getRemoteHost());//判断数据库登录是否成功
//        boolean flag = userLoginByDatabase(name, password, sessionBean);
        String platForm = request.getServletContext().getInitParameter("PLATFORM");
        if (null != platForm) {
            if (!sessionBean.getPlatformCode().equals(platForm)) {
                return SToolUtils.convertResultJSONObj("E", "请联系内部管理员授权登陆!", 0, "").toString();
            }
        }
        if (user != null) {
            // 内网用户过滤
            String platCode = sessionBean.getPlatformCode();
            if (platCode != null && "SAAF".equals(platCode)) {
                // 内网用户
                LOGGER.info("内部用户登录IP：" + userIp);
                boolean isLoginIp = checkUserIp(userIp);
                LOGGER.info("内部用户登录，是否通过登录IP校验：" + isLoginIp);
                // 校验不通过
                if (!isLoginIp) {
                    sessionBean.setLoginSuccessFlag(false);
                    JSONObject resultJSON = SToolUtils.convertResultJSONObj("E", "当前IP无法登陆系统！", 0, "LOGIN_N");
                    return resultJSON.toString();
                }
            }
            Integer userId = sessionBean.getUserId();
            sessionBean.setLoginSuccessFlag(true);
            String token =wsSecurityPolicy.getTokenByKey(sessionId);
            sessionBean.setTokenCode(token);
            //查用户组织
            findUserInst(sessionBean);
            //查询用户职责
            Object[] param = new Object[]{userId};
            try {
                List userRespList = baseSaafUserRespServer.findSaafUserResp(param);
                sessionBean.setUserRespList(userRespList);
                //查询员工名称
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("employeeId", sessionBean.getEmployeeId());
                SaafEmployeesEntity_HI saafEmployees = null;
                saafEmployees = baseSaafUsersServer.findEmployeesByEmpId(map);
                if (null != saafEmployees) {
                    sessionBean.setEmployeeName(saafEmployees.getEmployeeName());
                    sessionBean.setEmployeeId(saafEmployees.getEmployeeId());
                    sessionBean.setEmployeeNumber(saafEmployees.getEmployeeNumber());
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            LOGGER.info(params + "登录sessionCode===" + session.hashCode() + ",UserId===" + sessionBean.getUserId());
            JSONObject resultJSON = SToolUtils.convertResultJSONObj("S", "登录成功!", 1, Base64Utils.encodeToString(JSON.toJSONString(sessionBean)));
            try {
                saafLoginsHisServer.saveLoginHistory(JSONObject.parseObject(JSONObject.toJSONString(sessionBean)));
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            LOGGER.info("登录返回信息==>" + resultJSON.toString());
            session.setAttribute(UserInfoSessionBean.SAAF_SESSION_BEAN_ATTRIBUTE, sessionBean);
            return resultJSON.toString();
        } else {
            sessionBean.setLoginSuccessFlag(false);
            JSONObject resultJSON = SToolUtils.convertResultJSONObj("E", "请输入正确的用户名和密码！", 0, "LOGIN_N");
            return resultJSON.toString();
        }
    }

    private boolean checkUserIp(String userIp) {
        boolean flag = false;
        Map<String, Object> queryLookupMap = new HashMap<String, Object>();
        queryLookupMap.put("lookupType", "INNER_IP_CONFIG");
        try {
            List<SaafLookupValuesEntity_HI> lookups = baseSaafLookupServer.findLookupValues(queryLookupMap);
            if (lookups != null && lookups.size() > 0) {
                for (SaafLookupValuesEntity_HI lookup : lookups) {
                    LOGGER.info("内部用户登录，用户配置IP：" + lookup.getLookupCode());
                    if (userIp.startsWith(lookup.getLookupCode())) {
                        // 如果当前用户IP以配置IP开头 ，校验通过
                        flag = true;
                        break;
                    }
                }
            } else {
                flag = true;
            }
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
        }
        return flag;
    }

    /**
     * 数据库用户登录验证方法
     *
     * @param name
     * @param password
     * @return
     */
    public boolean userLoginByDatabase(String name, String password, UserInfoSessionBean sessionBean) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userName", name);
            map.put("encryptedPassword", password);
            //ISaafUsers saafUserServer = (ISaafUsers) SaafToolUtils.context.getBean("baseSaafUsersServer");
            SaafUsersEntity_HI userEntity = baseSaafUsersServer.findUserLogin(map);
            if (null != userEntity) {
                sessionBean.setIsAdmin(userEntity.getIsadmin());
                sessionBean.setUserId(userEntity.getUserId());
                sessionBean.setUserFullName(userEntity.getUserFullName());
                sessionBean.setPlatformCode(userEntity.getPlatformCode());
                sessionBean.setMemberId(userEntity.getMemberId());
                sessionBean.setEmployeeId(userEntity.getEmployeeId());
//                sessionBean.setAgreeLegal(userEntity.getAgreeLegal());
                sessionBean.setPlatformCode(userEntity.getPlatformCode());
                System.out.println("SupplierId:" + userEntity.getSupplierId());
                sessionBean.setSupplierId(userEntity.getSupplierId());
                return true;
            }
        } catch (Exception e) {
            LOGGER.error("登录异常==>" + e);
            return false;
        }
        return false;
    }


    /**
     * 数据库用户登录验证方法
     *
     * @param name
     * @param
     * @return
     */
    public SaafUsersEntity_HI userLoginByDatabaseReturnUser(String name, UserInfoSessionBean sessionBean) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userName", name);
//            map.put("encryptedPassword", password);
            //ISaafUsers saafUserServer = (ISaafUsers) SaafToolUtils.context.getBean("baseSaafUsersServer");
            SaafUsersEntity_HI userEntity = baseSaafUsersServer.findUserLoginNopossword(map);
            if (null != userEntity) {
                sessionBean.setIsAdmin(userEntity.getIsadmin());
                sessionBean.setUserId(userEntity.getUserId());
                sessionBean.setUserFullName(userEntity.getUserFullName());
                sessionBean.setPlatformCode(userEntity.getPlatformCode());
                sessionBean.setMemberId(userEntity.getMemberId());
                sessionBean.setEmployeeId(userEntity.getEmployeeId());
                sessionBean.setPlatformCode(userEntity.getPlatformCode());
                System.out.println("SupplierId:" + userEntity.getSupplierId());
                sessionBean.setSupplierId(userEntity.getSupplierId());
                return userEntity;
            }
        } catch (Exception e) {
            LOGGER.error("登录异常==>" + e);
            return null;
        }
        return null;
    }
    //    /**
    //     * 查询用户所有职责
    //     * @param sessionBean
    //     */
    //    public void findUserResp(UserInfoSessionBean sessionBean) {
    //        Object[] param = new Object[1];
    //        param[0] = sessionBean.getUserId();
    //        try {
    //            List userRespList = baseSaafUserRespServer.findSaafUserResp(param);
    //            //if (null != userRespList && userRespList.size() > 0) {
    //            sessionBean.setUserRespList(userRespList);
    //            //request.setAttribute("userRespList", userRespList);
    //            //            } else {
    //            //                request.setAttribute("userRespList", null);
    //            //}
    //        } catch (Exception e) {
    //            LOGGER.error("查询用户所有职责异常==>" + e);
    //        }
    //    }

    //    public void findEmployeeName(UserInfoSessionBean sessionBean) {
    //        try {
    //            Object userId = sessionBean.getUserId();
    //            Map<String, Object> map = new HashMap<String, Object>();
    //            int userIdInt = Integer.parseInt(userId.toString());
    //            map.put("userId", userIdInt);
    //            SaafEmployeesEntity_HI saafEmployees = null;
    //            saafEmployees = baseSaafUsersServer.findEmployees(map);
    //            if (null != saafEmployees) {
    //                sessionBean.setEmployeeName(saafEmployees.getEmployeeName());
    //                //request.setAttribute("employeeName", saafEmployees.getEmployeeName());
    //                //            } else {
    //                //                request.setAttribute("employeeName", null);
    //            }
    //        } catch (Exception e) {
    //            LOGGER.error(e.getMessage());
    //        }
    //    }

    /**
     * 查询用户组织ID(1,2,3)使用逗号分隔的全部组织ID
     *
     * @param sessionBean
     */
    public void findUserInst(UserInfoSessionBean sessionBean) {
        try {
            Integer userId = sessionBean.getUserId();
            String platformCode = sessionBean.getPlatformCode();
            Integer instId = null;
            String instIdData = null;
            // 查询用户组织Id
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("userId", userId);
            jsonParam.put("varPlatformCode", platformCode);
            List instUserlist = baseSaafUsersInstServer.findUsersInstTree(jsonParam);
            LOGGER.info("查询用户组织异常==>参数：" + jsonParam.toString() + ",用户组织：" + instUserlist.size());
            if (null != instUserlist) {
                for (int i = 0; i < instUserlist.size(); i++) {
                    SaafUserInstEntity_HI_RO userInstRow = (SaafUserInstEntity_HI_RO) instUserlist.get(i);
                    instId = userInstRow.getInstId();
                    if (instIdData == null) {
                        instIdData = String.valueOf(instId);
                    } else {
                        instIdData = instIdData + "," + instId;
                    }
                }
                sessionBean.setInstIdData(instIdData);
            }
        } catch (Exception e) {
            LOGGER.error("查询用户组织异常==>" + e);
        }
    }


//    private void saveUserInfo2redis(UserInfoSessionBean userInfoSessionBean, String token) {
//        try {
//            jedisCluster.set(token, JSON.toJSONString(userInfoSessionBean));
//            jedisCluster.expire(token, 1200);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//        }
//    }
}
