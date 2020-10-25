package saaf.common.fmw.schedule.servlets;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.schedule.utils.SaafToolUtils;
import saaf.common.fmw.schedule.exception.NotLoginException;


public abstract class CommonAbstractServices {

    private static final Logger logger = LoggerFactory.getLogger(CommonAbstractServices.class);
    public static final String STATUS = "status";
    public static final String MSG = "msg";
    public static final String COUNT = "count";
    public static final String DATA = "data";


    @Context
    protected HttpServletRequest request;

    @Context
    protected HttpServletResponse response;

    //SessionUserId
    protected Integer sessionUserId;
    protected String isAdmin;
    protected String platformCode;
    protected String instIdData;

    /**
     * 获取SessionBean
     */
    //    public UserInfoSessionBean getUserSessionBean() {
    //        UserInfoSessionBean sessionBean = null;
    //        try {
    //            sessionBean = (UserInfoSessionBean)request.getSession().getAttribute(UserInfoSessionBean.SAAF_SESSION_BEAN_ATTRIBUTE);
    //            logger.info("获取session成功,SessionCode==" + request.getSession().hashCode());
    //        } catch (Exception e) {
    //            logger.error("未知错误:{}", e);
    //            logger.error("获取Session异常！" + e);
    //        }
    //        return sessionBean;
    //    }

    /**
     * 获取服务 ServerBean
     * @param beanName
     * @return
     */
   /* public Object getServerBean(String beanName) {
        return SaafToolUtils.context.getBean(beanName);
    }
*/
    /**
     * 请求参数处理
     * 1.转换参数为Json对象
     * 2.统一处理请求Token认证
     * 3.增加公共参数
     * @param params
     * @return
     */
    public JSONObject parseObject(String params) throws NotLoginException {
        JSONObject jsonParams = null;
        if (null != params && !"".equals(params)) {
            jsonParams = JSONObject.parseObject(params);
        } else {
            jsonParams = new JSONObject();
        }
        //验证登录Session
        if (null == getSessionUserId()) {
            throw new NotLoginException("请求失败！请登录……");
        } else {
            jsonParams.put("varUserId", this.getSessionUserId());
            jsonParams.put("varPlatformCode", this.getPlatformCode());
            jsonParams.put("varInstIdData", ',' + this.getInstIdData() + ',');
        }
        return jsonParams;
    }


    /**
     * 获取客户端IP
     * @return
     */
    public String getIpAddr() {
        String ip = request.getHeader("X-REAL-IP");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        }
        return ip;
    }

    /**
     * 返回一个JSON对象，主要用在服务求之后获取的数据转成一个json对象
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
        return (Integer)request.getSession().getAttribute("userId");
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    /**
     *获取Session信息中：platformCode
     * @return
     */
    public String getPlatformCode() {
        String platformCode = null;
        if (null != request.getSession().getAttribute("platformCode")) {
            platformCode = request.getSession().getAttribute("platformCode").toString();
        } else {
            platformCode = "SAAF";
        }
        return platformCode;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getIsAdmin() {
        return (String)request.getSession().getAttribute("isAdmin");
    }

    public void setInstIdData(String instIdData) {
        this.instIdData = instIdData;
    }

    public String getInstIdData() {
        return (String)request.getSession().getAttribute("instIdData");
    }

    /**
     *字符串替换
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
}
