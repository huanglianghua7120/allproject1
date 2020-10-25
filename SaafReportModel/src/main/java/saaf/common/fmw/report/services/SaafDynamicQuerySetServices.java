package saaf.common.fmw.report.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.hibernate.exception.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.common.model.inter.server.ShortDescMessageServer;
import saaf.common.fmw.report.model.inter.ISaafDynamicQuerySet;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.utils.SrmUtils;


@Component
@Path("/dynamicQuerySetServices")
public class SaafDynamicQuerySetServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafDynamicQuerySetServices.class);
    @Autowired
    private ISaafDynamicQuerySet saafDynamicQuerySetServer;
    @Autowired
    private ShortDescMessageServer shortDescMessageServer;

    @POST
    @Path("findHeadersList")
    public String findHeadersList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject parameters = this.parseObject(params);

            return saafDynamicQuerySetServer.findHeadersList(parameters, pageIndex, pageRows);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询动态报表配置头异常!" + e);
            return shortDescMessageServer.getJsonResultStr("QUERY-FAILURE", new Object[] { e.getMessage() });
        }
    }

    
    private Map<String,String> getSessionMap(){
    	Map<String, String> sessionMap = new HashMap<String, String>();
    	
    	 UserInfoSessionBean sessionBean = this.getUserSessionBean();
        sessionMap.put("instId", this.getInstIdData());
        sessionMap.put("platformCode", this.getPlatformCode()!=null?this.getPlatformCode():"SAAF");
        sessionMap.put("userName", this.getUserName());
        sessionMap.put("userId", String.valueOf(this.getSessionUserId()));
        sessionMap.put("memberId", String.valueOf(this.getMemberId()));
        sessionMap.put("isAdmin", this.getIsAdmin());
        sessionMap.put("supplierId",String.valueOf( sessionBean.getSupplierId()));
        sessionMap.put("employeeId", String.valueOf(sessionBean.getEmployeeId()));
        sessionMap.put("employeeName", sessionBean.getEmployeeName());
        
        return sessionMap;
    }
    
    private String replaceSystemVariable(String sql, Map<String, String> sessionBean) {
        String str = sql;
        str = str.replace("{system.instId}", sessionBean.get("instId") != null ? sessionBean.get("instId") : "-999999");
        str = str.replace("{system.platformCode}", sessionBean.get("platformCode"));
        str = str.replace("{system.userName}", sessionBean.get("userName"));
        str = str.replace("{system.userId}", sessionBean.get("userId"));
        str = str.replace("{system.memberId}", sessionBean.get("memberId") != null ? sessionBean.get("memberId") : "-999999");
        str = str.replace("{system.isAdmin}", sessionBean.get("isAdmin"));
        str = str.replace("{system.supplierId}",!SrmUtils.isNvl( sessionBean.get("supplierId")) ? sessionBean.get("supplierId") : "-999999");
        str = str.replace("{system.employeeId}", !SrmUtils.isNvl(sessionBean.get("employeeId")) ? sessionBean.get("employeeId") : "-999999");
        str = str.replace("{system.employeeName}",!SrmUtils.isNvl(sessionBean.get("employeeName"))?sessionBean.get("employeeName"):"");
        return str;
    }
    
    @POST
    @Path("findLinesList")
    public String findLinesList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject parameters = this.parseObject(params);
            Map<String, String> sessionMap = this.getSessionMap();
            if("dynamicReportQuery".equals(  parameters.getString("pageType"))){
            return replaceSystemVariable(saafDynamicQuerySetServer.findLinesList(parameters,pageIndex, pageRows),sessionMap);
            }
           return saafDynamicQuerySetServer.findLinesList(parameters,pageIndex, pageRows);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询动态报表配置行异常!" + e);
            return shortDescMessageServer.getJsonResultStr("QUERY-FAILURE", new Object[] { e.getMessage() });
        }
    }

    @POST
    @Path("saveHeader")
    public String saveHeader(@FormParam("params") String params) {
        LOGGER.info(params);
        if (!"Y".equals(getIsAdmin())) {
            LOGGER.info("无权限用户尝试配置动态报表！！！");
            return shortDescMessageServer.getJsonResultStr("SAVE-FAILURE", new Object[] { "您没有权限" });
        }
        try {
            JSONObject parameters = this.parseObject(params);
            JSONObject resultStr = saafDynamicQuerySetServer.saveHeader(parameters);
            return JSON.toJSONString(resultStr);
        } catch (Exception e) {
            final Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                LOGGER.error("保存动态报表配置头失败：动态报表编码，违反唯一索引" + e);
                return shortDescMessageServer.getJsonResultStr("UNIQUE-CONSTRAINT", new Object[] { "动态报表编码" });
            }
            LOGGER.error("保存异常!" + e);
            return shortDescMessageServer.getJsonResultStr("SAVE-FAILURE", new Object[] { e.getMessage() });
        }
    }

    @POST
    @Path("deleteHeaderAndLines")
    public String deleteHeaderAndLines(@FormParam("params")
        String params) {
        LOGGER.info(params);
        if (!"Y".equals(getIsAdmin())) {
            LOGGER.info("无权限用户尝试删除动态报表！！！");
            return shortDescMessageServer.getJsonResultStr("SAVE-FAILURE", new Object[] { "您没有权限" });
        }
        try {
            JSONObject parameters = this.parseObject(params);
            JSONObject resultStr = saafDynamicQuerySetServer.deleteHeaderAndLines(parameters);
            return resultStr.toJSONString();
        } catch (Exception e) {
            LOGGER.error("删除异常!" + e);
            return shortDescMessageServer.getJsonResultStr("DELETE-FAILURE", new Object[] { e.getMessage() });
        }
    }

    @POST
    @Path("saveLine")
    public String saveLine(@FormParam("params")
        String params) {
        LOGGER.info(params);
        if (!"Y".equals(getIsAdmin())) {
            LOGGER.info("无权限用户尝试配置动态报表！！！");
            return shortDescMessageServer.getJsonResultStr("SAVE-FAILURE", new Object[] { "您没有权限" });
        }
        try {
            JSONObject parameters = this.parseObject(params);
            JSONObject resultStr = saafDynamicQuerySetServer.saveLine(parameters);
            return JSON.toJSONString(resultStr);
        } catch (Exception e) {
            LOGGER.error("保存异常!" + e);
            return shortDescMessageServer.getJsonResultStr("SAVE-FAILURE", new Object[] { e.getMessage() });
        }
    }

    @POST
    @Path("deleteLine")
    public String deleteLine(@FormParam("params")
        String params) {
        LOGGER.info(params);
        if (!"Y".equals(getIsAdmin())) {
            LOGGER.info("无权限用户尝试删除动态报表！！！");
            return shortDescMessageServer.getJsonResultStr("DELETE-FAILURE", new Object[] { "您没有权限" });
        }
        try {
            JSONObject parameters = this.parseObject(params);
            JSONArray jsonArray = parameters.getJSONArray("querySetLineIds");
            JSONObject json = new JSONObject();
            int i;
            for (i = 0; i < jsonArray.size(); i++) {
                json.put("querySetLineId", jsonArray.getInteger(i));
                saafDynamicQuerySetServer.deleteLine(json);
            }
            return shortDescMessageServer.getJsonResultStr("DELETE-SUCCESS", new Object[] { "共删除" + i + "行数据" });
        } catch (Exception e) {
            LOGGER.error("删除异常!" + e);
            return shortDescMessageServer.getJsonResultStr("DELETE-FAILURE", new Object[] { e.getMessage() });
        }
    }

    @POST
    @Path("generateColumns")
    public String generateColumns(@FormParam("params")
        String params) {
        LOGGER.info(params);
        if (!"Y".equals(getIsAdmin())) {
            LOGGER.info("无权限用户尝试配置动态报表！！！");
            return shortDescMessageServer.getJsonResultStr("DELETE-FAILURE", new Object[] { "您没有权限" });
        }
        try {
            JSONObject parameters = this.parseObject(params);
            return saafDynamicQuerySetServer.generateColumns(parameters);
        } catch (Exception e) {
            LOGGER.error("保存异常!" + e);
            return shortDescMessageServer.getJsonResultStr("DELETE-FAILURE", new Object[] { e.getMessage() });
        }
    }
}
