package saaf.common.fmw.schedule.services;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.utils.HttpConnectionUtil;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component("scheduleServices")
@Path("/scheduleServices")
public class ScheduleServices extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleServices.class);
    private static final String CONFIG_FILE = "/saaf/common/fmw/config/config.properties";
    private static final HttpConnectionUtil httpConn = new HttpConnectionUtil();
    private static Properties properties;
    private static String scheduleUrl;
    private static String userPwd;
    
    public ScheduleServices() {
        super();
        InputStream in = null;
        try {
        	LOGGER.info("-------->初始化 ScheduleServices");
            in = getClass().getResourceAsStream(CONFIG_FILE);
            properties = new Properties();
            properties.load(in);
            scheduleUrl = properties.getProperty("schedule_url");
            StringBuffer sb = new StringBuffer();
            sb.append("&user=");
            sb.append(properties.getProperty("schedule_user"));
            sb.append("&pwd=");
            sb.append(properties.getProperty("schedule_pwd"));
            userPwd = sb.toString();
            httpConn.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		}
    }

//    @POST
//    @Path("cancelRequest")
//    public String cancelRequest(@FormParam("params")
//        String params) {
//        try {
//            JSONObject jsonParam = this.parseObject(params);
//            ISchedules schedulesServer = (ISchedules)this.getServerBean("schedulesServer");
//            return schedulesServer.cancelRequest(jsonParam);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "取消失败!" + e, 0, null);
//        }
//    }
//
//    @POST
//    @Path("resumeRequest")
//    public String resumeRequest(@FormParam("params")
//        String params) {
//        try {
//            JSONObject jsonParam = this.parseObject(params);
//            ISchedules schedulesServer = (ISchedules)this.getServerBean("schedulesServer");
//            return schedulesServer.resumeRequest(jsonParam);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "启动（resume）失败!" + e, 0, null);
//        }
//    }
//
//    @POST
//    @Path("deleteSchedule")
//    public String deleteSchedule(@FormParam("params")
//        String params) {
//        try {
//            JSONObject jsonParam = this.parseObject(params);
//            ISchedules schedulesServer = (ISchedules)this.getServerBean("schedulesServer");
//            return schedulesServer.deleteSchedule(jsonParam);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "删除失败!" + e, 0, null);
//        }
//    }
//
//    @POST
//    @Path("findRequestLog")
//    public String findRequestLog(@FormParam("params")
//        String params) {
//        try {
//            JSONObject jsonParam = this.parseObject(params);
//            ISchedules schedulesServer = (ISchedules)this.getServerBean("schedulesServer");
//            return schedulesServer.getRequestLog(jsonParam);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "查询请求日志失败!" + e, 0, null);
//        }
//    }
//
//    @POST
//    @Path("findRequests")
//    public String findRequests(@FormParam("params")
//        String params, @FormParam("pageIndex")
//        Integer pageIndex, @FormParam("pageRows")
//        Integer pageRows) {
//        try {
//            JSONObject jsonParam = this.parseObject(params);
//            jsonParam.put("varUserId", this.getUserSessionBean().getUserId());
//            jsonParam.put("varUserName", this.getUserSessionBean().getUserName());
////            jsonParam.put("varUserId", -9999);
////            jsonParam.put("varUserName", "System");
//            ISchedules schedulesServer = (ISchedules)this.getServerBean("schedulesServer");
//            return schedulesServer.findRequests(jsonParam, pageIndex, pageRows);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "查询请求称失败!" + e, 0, null);
//        }
//    }
//
//    @POST
//    @Path("launchRequest")
//    public String launchRequest(@FormParam("params")
//        String params) {
//        try {
//            JSONObject jsonParam = this.parseObject(params);
//            ISchedules schedulesServer = (ISchedules)this.getServerBean("schedulesServer");
//            return schedulesServer.launchRequest(jsonParam);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "启动失败!" + e, 0, null);
//        }
//    }
//
//    @POST
//    @Path("pauseRequest")
//    public String pauseRequest(@FormParam("params")
//        String params) {
//        try {
//            JSONObject jsonParam = this.parseObject(params);
//            ISchedules schedulesServer = (ISchedules)this.getServerBean("schedulesServer");
//            return schedulesServer.pauseRequest(jsonParam);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "暂停失败!" + e, 0, null);
//        }
//    }
//
//    @POST
//    @Path("saveRequest")
//    public String saveRequest(@FormParam("params")
//        String params) {
//        try {
//            JSONObject jsonParam = this.parseObject(params);
//            jsonParam.put("varUserId", this.getUserSessionBean().getUserId());
//            //jsonParam.put("varUserId", -9999);
//            ISchedules schedulesServer = (ISchedules)this.getServerBean("schedulesServer");
//            return schedulesServer.saveRequest(jsonParam);
//        } catch (Exception e2) {
//            e2.printStackTrace();
//            return convertResultJSONObj("E", "提交请求失败!" + e2, 0, null);
//        }
//    }
    
    @POST
    @Path("cancelRequest")
    public String cancelRequest(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"scheduleServices/cancelRequest", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "取消失败!" + e, 0, null);
        }
    }

    @POST
    @Path("resumeRequest")
    public String resumeRequest(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"scheduleServices/resumeRequest", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "启动（resume）失败!" + e, 0, null);
        }
    }

    @POST
    @Path("deleteSchedule")
    public String deleteSchedule(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"scheduleServices/deleteSchedule", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "删除失败!" + e, 0, null);
        }
    }

    @POST
    @Path("findRequestLog")
    public String findRequestLog(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"scheduleServices/findRequestLog", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "查询请求日志失败!" + e, 0, null);
        }
    }

    @POST
    @Path("findRequests")
    public String findRequests(@FormParam("params")
        String params, @FormParam("pageIndex")@DefaultValue("1")
        Integer pageIndex, @FormParam("pageRows") @DefaultValue("10")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            StringBuffer param = new StringBuffer();
            param.append("params=");
            param.append(jsonParam.toString());
            param.append("&pageIndex=");
            param.append(pageIndex==null?"":pageIndex);
            param.append("&pageRows=");
            param.append(pageRows==null?"":pageRows);
            param.append(userPwd);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"scheduleServices/findRequests", param.toString());
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "查询请求称失败!" + e, 0, null);
        }
    }

    @POST
    @Path("launchRequest")
    public String launchRequest(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"scheduleServices/launchRequest", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "启动失败!" + e, 0, null);
        }
    }

    @POST
    @Path("pauseRequest")
    public String pauseRequest(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"scheduleServices/pauseRequest", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "暂停失败!" + e, 0, null);
        }
    }

    @POST
    @Path("saveRequest")
    public String saveRequest(@FormParam("params")
        String params) {
    	try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"scheduleServices/saveRequest", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e2) {
            e2.printStackTrace();
            return convertResultJSONObj("E", "提交请求失败!" + e2, 0, null);
        }
    }
}
