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

@Component("jobParameterServices")
@Path("/jobParameterServices")
public class JobParameterServices extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobParameterServices.class);
    private static final String CONFIG_FILE = "/saaf/common/fmw/config/config.properties";
    private static final HttpConnectionUtil httpConn = new HttpConnectionUtil();
    private static Properties properties;
    private static String scheduleUrl;
    private static String userPwd;
    
    public JobParameterServices() {
        super();
        InputStream in = null;
        try {
        	LOGGER.info("-------->初始化 JobParameterServices");
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
//    @Path("deleteJobParameter")
//    public String deleteJobParameter(@FormParam("params")
//        String params) {
//        try {
//            JSONObject jsonParam = JSON.parseObject(params);
//            IJobParameters jobParameterServer = (IJobParameters)this.getServerBean("jobParametersServer");
//            return jobParameterServer.deleteJobParameter(jsonParam);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "删除Job参数失败!" + e, 0, null);
//        }
//    }
//
//    @POST
//    @Path("findJobParameters")
//    public String findJobParameters(@FormParam("params")
//        String params, @FormParam("pageIndex")
//        Integer pageIndex, @FormParam("pageRows")
//        Integer pageRows) {
//        try {
//            JSONObject jsonParam = JSON.parseObject(params);
//            IJobParameters jobParameterServer = (IJobParameters)this.getServerBean("jobParametersServer");
//            return jobParameterServer.findJobParameters(jsonParam, pageIndex, pageRows);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "查询Job参数失败!" + e, 0, null);
//        }
//    }
//
//    @POST
//    @Path("saveJobParameter")
//    public String saveJobParameter(@FormParam("params")
//        String params) {
//        try {
//            JSONObject jsonParam = JSON.parseObject(params);
//            jsonParam.put("varUserId", this.getUserSessionBean().getUserId());
//            //jsonParam.put("varUserId", -9999);
//            IJobParameters jobParameterServer = (IJobParameters)this.getServerBean("jobParametersServer");
//            return jobParameterServer.saveParameter(jsonParam);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "保存Job参数失败!" + e, 0, null);
//        }
//    }
//
//    @POST
//    @Path("updateJobParameter")
//    public String updateJobParameter(@FormParam("params")
//        String params) {
//        try {
//            JSONObject jsonParam = JSON.parseObject(params);
//            jsonParam.put("varUserId", this.getUserSessionBean().getUserId());
//            //jsonParam.put("varUserId", -9999);
//            IJobParameters jobParameterServer = (IJobParameters)this.getServerBean("jobParametersServer");
//            return jobParameterServer.updateJobParameter(jsonParam);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return convertResultJSONObj("E", "更新Job参数失败!" + e, 0, null);
//        }
//    }
    
    @POST
    @Path("deleteJobParameter")
    public String deleteJobParameter(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"jobParameterServices/deleteJobParameter", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "删除Job参数失败!" + e, 0, null);
        }
    }

    @POST
    @Path("findJobParameters")
    public String findJobParameters(@FormParam("params")
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
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"jobParameterServices/findJobParameters", param.toString());
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "查询Job参数失败!" + e, 0, null);
        }
    }

    @POST
    @Path("saveJobParameter")
    public String saveJobParameter(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"jobParameterServices/saveJobParameter", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "保存Job参数失败!" + e, 0, null);
        }
    }

    @POST
    @Path("updateJobParameter")
    public String updateJobParameter(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"jobParameterServices/updateJobParameter", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "更新Job参数失败!" + e, 0, null);
        }
    }
}

