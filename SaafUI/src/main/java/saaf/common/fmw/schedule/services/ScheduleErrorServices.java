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
@Component("scheduleErrorServices")
@Path("/scheduleErrorServices")
public class ScheduleErrorServices extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleErrorServices.class);
    private static final String CONFIG_FILE = "/saaf/common/fmw/config/config.properties";
    private static final HttpConnectionUtil httpConn = new HttpConnectionUtil();
    private static Properties properties;
    private static String scheduleUrl;
    private static String userPwd;
    
    public ScheduleErrorServices() {
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
    
    @POST
	@Path("findErrors")
	public String findErrors(@FormParam("params") String params,
			@FormParam("pageIndex") @DefaultValue("1") Integer pageIndex ,
			@FormParam("pageRows") @DefaultValue("10") Integer pageRows) {
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
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"scheduleErrorServices/findErrors", param.toString());
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "查询调度异常信息失败!" + e, 0, null);
        }
	}
	
	@POST
	@Path("save")
	public String save(@FormParam("params") String params) {
		try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"scheduleErrorServices/save", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "保存调度异常信息失败!" + e, 0, null);
        }
	}
	
	@POST
	@Path("doProcessReqId")
	public String doProcessReqId(@FormParam("params") String params) {
		try {
            JSONObject jsonParam = this.parseObject(params);
            return httpConn.httpUrlConnection(request,"scheduleSessionId",scheduleUrl+"scheduleErrorServices/doProcessReqId", "params="+jsonParam.toString()+userPwd);
        } catch (Exception e) {
            //e.printStackTrace();
            return convertResultJSONObj("E", "保存调度异常信息失败!" + e, 0, null);
        }
	}

}
