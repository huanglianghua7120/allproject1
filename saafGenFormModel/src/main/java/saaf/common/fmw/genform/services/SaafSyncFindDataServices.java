package saaf.common.fmw.genform.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import saaf.common.fmw.genform.utils.GenFormPropertiesUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.sie.common.utils.StringUtil;

@Component("saafSyncFindDataServices")
@Path("/saafSyncFindDataServices")
public class SaafSyncFindDataServices {
    private static final Logger LOGGER =
        LoggerFactory.getLogger(SaafSyncFindDataServices.class);
    @Context
    private HttpServletRequest request;
    @Context
    private HttpServletResponse response;

    public SaafSyncFindDataServices() {
        super();
    }

    @POST
    @Path("syncFindData")
    @Produces("application/json")
    public String syncFindData(@FormParam("dataType") String dataType,
    		@FormParam("dataUrl")String dataUrl,@FormParam("dataTextField")String dataTextField,
    		@FormParam("dataValueField")String dataValueField) {
        LOGGER.info("dataType:" + dataType+"\tdataUrl:"+dataUrl+"\tdataTextField:"+dataTextField+"\tdataValueField:"+dataValueField);

        java.io.InputStream is = null;
        java.io.ByteArrayOutputStream baos = null;
        try {
        	dataType = dataType==null?request.getParameter("dataType"):dataType;
        	
        	if(!dataType.equalsIgnoreCase("userCustom")){
        		String contextPath = GenFormPropertiesUtil.getProperty("server.context.path");
        		dataUrl = contextPath+GenFormPropertiesUtil.getProperty("genform.dataurl."+dataType.toLowerCase());
        		LOGGER.info("获取数据："+dataUrl);
        	}
        	
        	URL url = new URL(dataUrl);
    		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    		conn.setRequestMethod("POST");
    		is = conn.getInputStream();
    		baos = new java.io.ByteArrayOutputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            conn.disconnect();
            
            String resultStr = new String(baos.toByteArray(), "utf-8");
            JSONObject json = JSONObject.parseObject(resultStr);
            JSONArray datas = json.getJSONArray("data");
            
            JSONArray result = new JSONArray();
            String textField = StringUtil.isEmpty(dataTextField)?"text":dataTextField;//从结果取数据显示的字段，默认使用text
            String valueField = StringUtil.isEmpty(dataValueField)?"value":dataValueField;//从结果取数据值的字段，默认使用value
            for(int i=0;i<datas.size();i++){
            	JSONObject data = datas.getJSONObject(i);//查询取得的数据
            	
            	JSONObject retObj = new JSONObject();//最后返回的数据对象
            	retObj.put("text", data.get(textField));
            	retObj.put("value", data.get(valueField));
            	result.add(retObj);
            }
            
            return result.toJSONString();
        } catch (IOException e) {
            LOGGER.error("", e);
        } finally {
            try {
                if (is != null)
                    is.close();
                if (baos != null)
                    baos.close();
            } catch (IOException ioe) {
                LOGGER.error("",ioe);
            }
        }


        return new JSONArray().toJSONString();
    }
}
