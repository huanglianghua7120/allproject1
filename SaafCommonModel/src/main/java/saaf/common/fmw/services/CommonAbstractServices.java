package saaf.common.fmw.services;


import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.exception.NotLoginException;


public abstract class CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonAbstractServices.class);
    public static final String STATUS = "status";
    public static final String MSG = "msg";
    public static final String COUNT = "count";
    public static final String DATA = "data";
    public static final String PARAMS = "params";
    public static final String PAGE_INDEX = "pageIndex";
    public static final String PAGE_ROWS = "pageRows";
    public static final String ENABLE_FLAG = "Y";
    public static final String ERROR_STATUS = "E";
    public static final String SUCCESS_STATUS = "S";
    public static final String FORCEDDOWNLOAD_FLAG = "Y";
    @Context
    protected HttpServletRequest request;
    @Context
    protected HttpServletResponse response;

    public CommonAbstractServices() {
        super();
    }

    protected Integer sessionUserId;
    protected String isAdmin;
    protected String platformCode;
    protected String instIdData;
    protected Integer memberId;
    protected Integer supplierId;
    protected String userName;

    /**
     * 获取SessionBean
     */
    public UserInfoSessionBean getUserSessionBean() {
        UserInfoSessionBean sessionBean = null;
        try {
        	HttpSession session = request.getSession(false);
        	if(session == null) {
        		SessionKey sessionKey = new SessionKey() {
                    @Override
                    public Serializable getSessionId() {
                        return request.getHeader("TokenCode");
                    }
                };
        		Session ses =  SecurityUtils.getSecurityManager().getSession(sessionKey);
        		sessionBean = (UserInfoSessionBean) ses.getAttribute(UserInfoSessionBean.SAAF_SESSION_BEAN_ATTRIBUTE);
        	}else {
        		LOGGER.debug("session:{}", session);
        		sessionBean = (UserInfoSessionBean) session.getAttribute(UserInfoSessionBean.SAAF_SESSION_BEAN_ATTRIBUTE);
        		LOGGER.debug("获取session成功,SessionCode==" + session.hashCode());
        	}
        } catch (Exception e) {
            LOGGER.error("获取Session异常！", e);
        }
//        if(sessionBean == null){
//        	//如果获取不到用户信息，默认使用sysadmin管理员信息
//        	//此处用于开发测试阶段跨域调用服务时不能获取用户信息情况
//        	//String str = "{\"employeeName\":\"系统超级管理员\",\"executePageLoadFlag\":false,\"isAdmin\":\"Y\",\"language\":\"172.18.49.62\",\"loginSuccessFlag\":true,\"platformCode\":\"SAAF\",\"screenHeight\":0,\"screenWidth\":0,\"sessionId\":\"3321F21ED137FDA3CF69189F33FE9D18\",\"tableDefaultRows\":0,\"tablesDefaultRows\":0,\"tokeCode\":\"2a85d02bf059c55d1d6a22d9d987c56e\",\"topSpageHeight\":0,\"userFullName\":\"系统超级管理员\",\"userId\":1,\"userIpAddress\":\"183.238.193.154\",\"userName\":\"sysadmin\",\"userRespList\":[{\"responsibilityId\":1,\"responsibilityName\":\"系统超级管理员职责\",\"startDateActive\":\"2016-12-30\",\"userRespId\":1},{\"responsibilityId\":10,\"responsibilityName\":\"销售职责\",\"startDateActive\":\"2017-02-17\",\"userRespId\":77},{\"responsibilityId\":30,\"responsibilityName\":\"物流职责\",\"startDateActive\":\"2017-02-20\",\"userRespId\":78},{\"responsibilityId\":73,\"responsibilityName\":\"财务职责\",\"startDateActive\":\"2017-05-01\",\"userRespId\":83},{\"responsibilityId\":76,\"responsibilityName\":\"财务审批职责\",\"startDateActive\":\"2017-05-01\",\"userRespId\":96}]}";
//        	String str = jedisCluster.get("1_QuickQuery");
//        	LOGGER.info("获取管理员session:"+str);
//        	sessionBean = StringUtils.isBlank(str) ? null : JSON.toJavaObject(JSON.parseObject(str), UserInfoSessionBean.class);
//        }
        LOGGER.debug("获取管理员session:" + sessionBean);
        return sessionBean;
    }

    /**
     * 获取服务 ServerBean
     * @param beanName
     * @return
     */
    //    public Object getServerBean(String beanName) {
    //        return SaafToolUtils.context.getBean(beanName);
    //    }

    /**
     * 请求参数处理
     * 1.转换参数为Json对象
     * 2.统一处理请求Token认证
     * 3.增加公共参数
     *
     * @param params
     * @return
     */
    public JSONObject parseObject(String params) throws Exception {
        JSONObject jsonParams = null;
        if (null != params && !"".equals(params)) {
            jsonParams = JSON.parseObject(params);
        } else {
            jsonParams = new JSONObject();
        }
        UserInfoSessionBean sessionBean = getUserSessionBean();
        if (sessionBean != null) {
            jsonParams.put("varUserId", sessionBean.getUserId());
            jsonParams.put("varPlatformCode", sessionBean.getPlatformCode());
            jsonParams.put("varInstIdData", sessionBean.getInstIdData());
            jsonParams.put("varMemberId", sessionBean.getMemberId());
            jsonParams.put("varUserName", sessionBean.getUserName());
            jsonParams.put("varIsAdmin", sessionBean.getIsAdmin());
            jsonParams.put("varSupplierId", sessionBean.getSupplierId());
            jsonParams.put("operatorUserId", sessionBean.getUserId());
            jsonParams.put("varEmployeeId", sessionBean.getEmployeeId());
            jsonParams.put("varEmployeeName", sessionBean.getEmployeeName());
        } else {
            throw new NotLoginException("请求失败！请登录……");
        }

        return jsonParams;
    }

    /**
     * 请求参数处理-外部使用（在没有登录的情况下）
     * 1.转换参数为Json对象
     * 2.统一处理请求Token认证
     * 3.增加公共参数
     *
     * @param params
     * @return
     */
    public JSONObject parseObjectExternal(String params) throws Exception {
        JSONObject jsonParams = null;
        if (null != params && !"".equals(params)) {
            jsonParams = JSON.parseObject(params);
        } else {
            jsonParams = new JSONObject();
        }
        return jsonParams;
    }

    /**
     * 获取客户端IP
     *
     * @return
     */
    public String getIpAddr() {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 返回一个JSON对象，主要用在服务求之后获取的数据转成一个json对象
     *
     * @param resultStatus
     * @param resultMessage
     * @param resultCount
     * @param resultData
     * @return
     */
    public static String convertResultJSONObj(String resultStatus, String resultMessage, long resultCount, Object resultData) {
        JSONObject json = new JSONObject();
        json.put(STATUS, resultStatus);
        json.put(MSG, resultMessage);
        json.put(COUNT, resultCount);
        json.put(DATA, resultData);
        return json.toJSONString();
    }


    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setSessionUserId(Integer sessionUserId) {
        this.sessionUserId = sessionUserId;
    }

    public Integer getSessionUserId() {
        UserInfoSessionBean sessionBean = getUserSessionBean();
        if (sessionBean == null || sessionBean.getUserId() == null)
            return -1;
        return sessionBean.getUserId();
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getIsAdmin() {
        return getUserSessionBean() != null ? getUserSessionBean().getIsAdmin() : "Y";
    }

    public void setInstIdData(String instIdData) {
        this.instIdData = instIdData;
    }

    public String getInstIdData() {
        return getUserSessionBean() != null ? getUserSessionBean().getInstIdData() : "-9999";
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMemberId() {
        return (getUserSessionBean() != null && getUserSessionBean().getMemberId() != null) ? getUserSessionBean().getMemberId() : -9999;
    }


    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getSupplierId() {
        return (getUserSessionBean() != null && getUserSessionBean().getSupplierId() != null) ? getUserSessionBean().getSupplierId() : -9999;
    }

    public String getUserName() {
        return getUserSessionBean() != null ? getUserSessionBean().getUserName() : "sysadmin";
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 字符串替换
     *
     * @param template
     * @param mark
     * @param replacement
     * @return
     */
    public static String replaceTemplate(String template, String mark, String replacement) {
        if (!template.contains(mark)) {
            return template;
        }
        if (replacement == null) {
            replacement = "";
        }
        return template.replace(mark, replacement);
    }

    public String getPlatformCode() {
        return platformCode;
    }
}
