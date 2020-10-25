package saaf.common.fmw.filter;

import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafUserInstEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafUsers;
import saaf.common.fmw.base.model.inter.ISaafUsersInst;
import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.utils.DESEncrypt;
import saaf.common.fmw.utils.SHA1Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import saaf.common.fmw.common.model.inter.server.ShortDescMessageServer;

public class RequestFilter implements ContainerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);

    @Context
    private HttpServletRequest servletRequest;
    @Context
    private HttpServletResponse servletResponse;
    @Autowired
    private ShortDescMessageServer shortDescMessageServer;
    @Autowired
    private ISaafUsers baseSaafUsersServer;
    @Autowired
    private ISaafUsersInst baseSaafUsersInstServer;
    
    @Override
    public ContainerRequest filter(ContainerRequest request) {
        LOGGER.debug("------------RequestFilter------------");
        //addHeadersFor200Response(servletResponse);
        if (true)
            return request;

        //请求路径
        if (!servletRequest.getRequestURL().toString().endsWith("/login") && !servletRequest.getMethod().equals("OPTIONS")) {
            HttpSession session = servletRequest.getSession(false);
            boolean check = true;
            if (session != null) {
                UserInfoSessionBean sessionBean = (UserInfoSessionBean) session.getAttribute(UserInfoSessionBean.SAAF_SESSION_BEAN_ATTRIBUTE);
                if (sessionBean != null && sessionBean.isLoginSuccessFlag()) {
                    check = false;
                }
            }
            Form formParameters = request.getFormParameters();
            if (check) {
                String user = formParameters.getFirst("user");
                String pwd = formParameters.getFirst("pwd");
//            	String user = "ZqA6lmcsMQk3DaSeYes5Nw==";//sysadmin
//                String pwd = "x6Sdlb/Nwew=";//111111

                if (user != null && pwd != null) {
                    if (!this.userLoginCheck(user, pwd)) {
                        Response response = Response.ok(shortDescMessageServer.getJsonResult("NOT-LOGGED-IN")).status(401).type(MediaType.APPLICATION_JSON).build();
                        throw new WebApplicationException(response);
                    }
                } else {
                    Response response = Response.ok(shortDescMessageServer.getJsonResult("NOT-LOGGED-IN")).status(401).type(MediaType.APPLICATION_JSON).build();
                    throw new WebApplicationException(response);
                }
            }
        }

        return request;
    }

    private void addHeadersFor200Response(HttpServletResponse response) {
        //TODO: externalize the Allow-Origin
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept,TokenCode");
        response.addHeader("Access-Control-Max-Age", "1728000");
    }

    /**
     * 数据库用户登录验证方法
     *
     * @param user
     * @param pwd
     * @return
     */
    private boolean userLoginCheck(String user, String pwd) {
        try {
            DESEncrypt des = new DESEncrypt();
            String userStr = des.decryption(user);
            String pwdStr = des.decryption(pwd);
            SHA1Util sha1 = new SHA1Util();
            String password = null;
            try {
                password = sha1.getEncrypt(pwdStr);
            } catch (NoSuchAlgorithmException e) {
                //e.printStackTrace();
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userName", userStr);
            map.put("encryptedPassword", password);
            SaafUsersEntity_HI userEntity = baseSaafUsersServer.findUserLogin(map);
            if (null != userEntity) {
                HttpSession session = servletRequest.getSession();
                UserInfoSessionBean sessionBean = new UserInfoSessionBean();
                sessionBean.setLanguage(servletRequest.getLocalName());
                sessionBean.setSessionId(servletRequest.getSession().getId());
                sessionBean.setUserId(userEntity.getUserId());
                sessionBean.setUserName(userStr);
                sessionBean.setUserFullName(userEntity.getUserFullName());
                sessionBean.setUserIpAddress(servletRequest.getRemoteHost());
                sessionBean.setPlatformCode(userEntity.getPlatformCode());
                sessionBean.setMemberId(userEntity.getMemberId());
                sessionBean.setIsAdmin(userEntity.getIsadmin());
                sessionBean.setLoginSuccessFlag(true);
                sessionBean.setInstIdData(this.findUserInst(userEntity.getUserId(), userEntity.getPlatformCode()));
                session.setAttribute(UserInfoSessionBean.SAAF_SESSION_BEAN_ATTRIBUTE, sessionBean);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * 查询用户组织ID(1,2,3)使用逗号分隔的全部组织ID
     *
     * @param userId platformCode
     */
    private String findUserInst(int userId, String platformCode) {
        String instIdData = null;
        try {
            Integer instId = null;
            // 查询用户组织Id
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("userId", userId);
            jsonParam.put("varPlatformCode", platformCode);
            List instUserlist = baseSaafUsersInstServer.findUsersInstTree(jsonParam);

            if (instUserlist.size() > 0) {
                for (int i = 0; i < instUserlist.size(); i++) {
                    SaafUserInstEntity_HI_RO userInstRow = (SaafUserInstEntity_HI_RO) instUserlist.get(i);
                    instId = userInstRow.getInstId();
                    if (instIdData == null) {
                        instIdData = String.valueOf(instId);
                    } else {
                        instIdData = instIdData + "," + instId;
                    }
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return instIdData;
    }

}
