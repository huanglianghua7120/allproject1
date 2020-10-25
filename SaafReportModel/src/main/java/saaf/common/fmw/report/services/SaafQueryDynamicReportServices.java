package saaf.common.fmw.report.services;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.common.model.inter.server.ShortDescMessageServer;
import saaf.common.fmw.report.model.inter.ISaafQueryDynamicReport;
import saaf.common.fmw.services.CommonAbstractServices;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.excel.OperationExcelDocUtils;


@Component
@Path("/queryDynamicReportServices")
public class SaafQueryDynamicReportServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafQueryDynamicReportServices.class);
    @Autowired
    private ISaafQueryDynamicReport saafQueryDynamicReportServer;
    @Autowired
    private ShortDescMessageServer shortDescMessageServer;
    
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

    @POST
    @Path("findList")
    public String findList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        LOGGER.info(params);
        JSONObject parameters = JSON.parseObject(params);
        String querySetNum = parameters.getString("querySetNum");
        if (querySetNum == null || querySetNum.equals("")) {
            return shortDescMessageServer.getJsonResultStr("QUERY-FAILURE", new Object[] { "报表编码为空，请联系管理员！" });
        }
        try {
            JSONObject jsonParams = parameters.getJSONObject("queryParams");
            Map<String, String> sessionMap = this.getSessionMap();
            String findListResult = saafQueryDynamicReportServer.findList(querySetNum, jsonParams, sessionMap, pageIndex, pageRows);
            return findListResult;
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询报表异常！报表编码：" + querySetNum, e);
            return shortDescMessageServer.getJsonResultStr("QUERY-FAILURE", new Object[] { e.getMessage() });
        }
    }

    @POST
    @Path("findLov")
    public String findLov(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        LOGGER.info(params);
        JSONObject parameters = JSON.parseObject(params);
        String querySetLineId = parameters.getString("querySetLineId");
        if (querySetLineId == null || querySetLineId.equals("")) {
            return shortDescMessageServer.getJsonResultStr("QUERY-FAILURE", new Object[] { "LOV_ID为空，请联系管理员！" });
        }
        try {
            Map<String, String> sessionMap = this.getSessionMap();

            return saafQueryDynamicReportServer.findLov(parameters, sessionMap, pageIndex, pageRows);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询LOV异常！LOV_ID：" + querySetLineId, e);
            return shortDescMessageServer.getJsonResultStr("QUERY-FAILURE", new Object[] { e.getMessage() });
        }
    }

    @POST
    @Path("findService")
    public String findService(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        LOGGER.info(params);
        JSONObject parameters = JSON.parseObject(params);
        String querySetNum = parameters.getString("querySetNum");
        if (querySetNum == null || querySetNum.equals("")) {
            return shortDescMessageServer.getJsonResultStr("QUERY-FAILURE", new Object[] { "报表编码为空，请联系管理员！" });
        }
        try {
            JSONObject jsonParams = parameters.getJSONObject("queryParams");
            Map<String, String> sessionMap = this.getSessionMap();
            return saafQueryDynamicReportServer.findService(querySetNum, jsonParams, sessionMap, pageIndex, pageRows);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询报表异常！报表编码：" + querySetNum, e);
            return shortDescMessageServer.getJsonResultStr("QUERY-FAILURE", new Object[] { e.getMessage() });
        }
    }

}
