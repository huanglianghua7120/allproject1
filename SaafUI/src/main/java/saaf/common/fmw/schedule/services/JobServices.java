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

@Component("jobServices")
@Path("/jobServices")
public class JobServices extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobServices.class);
    private static final String CONFIG_FILE = "/saaf/common/fmw/config/config.properties";
    private static final HttpConnectionUtil httpConn = new HttpConnectionUtil();
    private static Properties properties;
    private static String scheduleUrl;
    private static String userPwd;

    public JobServices() {
        super();
        InputStream in = null;
        try {
        	LOGGER.info("-------->初始化 JobServices");
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
//    @Path("findJobs")
//    public String findJobs(@FormParam("params")
//        String params, @FormParam("pageIndex")
//        Integer pageIndex, @FormParam("pageRows")
//        Integer pageRows) {
//        try {
//            JSONObject jsonParam = JSON.parseObject(params);
//            IJobs jobsServer = (IJobs)this.getServerBean("jobsServer");
//            return jobsServer.findJobs(jsonParam, pageIndex, pageRows);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "查询Job失败!" + e, 0, null);
//        }
//    }
//
//    @POST
//    @Path("deleteJob")
//    public String deleteJob(@FormParam("params")
//        String params) {
//        try {
//            JSONObject jsonParam = JSON.parseObject(params);
//            IJobs jobsServer = (IJobs)this.getServerBean("jobsServer");
//            return jobsServer.deleteJob(jsonParam);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "删除Job失败!" + e, 0, null);
//        }
//    }
//
//    @POST
//    @Path("saveJob")
//    public String saveJob(@FormParam("params")
//        String params) {
//        try {
//            JSONObject jsonParam = JSON.parseObject(params);
//            jsonParam.put("varUserId", this.getUserSessionBean().getUserId());
//            //jsonParam.put("varUserId", -9999);
//            IJobs jobsServer = (IJobs)this.getServerBean("jobsServer");
//            return jobsServer.saveJob(jsonParam);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "保存Job失败!" + e, 0, null);
//        }
//    }
//
//    @POST
//    @Path("updateJob")
//    public String updateJob(@FormParam("params")
//        String params) {
//        try {
//            JSONObject jsonParam = JSON.parseObject(params);
//            jsonParam.put("varUserId", this.getUserSessionBean().getUserId());
//            //jsonParam.put("varUserId", -9999);
//            IJobs jobsServer = (IJobs)this.getServerBean("jobsServer");
//            return jobsServer.updateJob(jsonParam);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "更新Job失败!" + e, 0, null);
//        }
//    }
    
    @POST
    @Path("findJobs")
    public String findJobs(@FormParam("params")
    String params, @FormParam("pageIndex") @DefaultValue("1")
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
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"jobServices/findJobs", param.toString());
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "查询Job失败!" + e, 0, null);
        }
    }

    @POST
    @Path("deleteJob")
    public String deleteJob(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"jobServices/deleteJob", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "删除Job失败!" + e, 0, null);
        }
    }

    @POST
    @Path("saveJob")
    public String saveJob(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"jobServices/saveJob", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "保存Job失败!" + e, 0, null);
        }
    }

    @POST
    @Path("updateJob")
    public String updateJob(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"jobServices/updateJob", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "更新Job失败!" + e, 0, null);
        }
    }
}
