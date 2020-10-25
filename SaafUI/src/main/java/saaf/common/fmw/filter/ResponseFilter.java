package saaf.common.fmw.filter;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import saaf.common.fmw.utils.ExcelUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

public class ResponseFilter implements ContainerResponseFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseFilter.class);
	
	@Context
	private HttpServletRequest servletRequest;
	@Context
	private HttpServletResponse servletResponse;

	@Override
	public ContainerResponse filter(ContainerRequest request,
            ContainerResponse response) {
		if (!servletRequest.getRequestURL().toString().endsWith("/login") && !servletRequest.getMethod().equals("OPTIONS")) {
			if(response.getStatus()==200){
				//request.getFormParameters();//获取参数需要执行两遍（或许是BUG），此处为第一遍
				//Form formParameters = request.getFormParameters();
				Map<String,Object> reqProp = request.getProperties();
				if(reqProp.containsKey("com.sun.jersey.api.representation.form")){
					Form formParameters = (Form)reqProp.get("com.sun.jersey.api.representation.form");
					String exportExcel = formParameters.getFirst("exportExcel");
					if("Y".equals(exportExcel)){
						LOGGER.info("参数："+formParameters);
						String xlsParams = formParameters.getFirst("xlsParams");
						this.exportExcel(xlsParams, String.valueOf(response.getEntity()));
					}
				}
			}
		}
		return response;
	}
	
	/**
	 * 导出Excel
	 * @param xlsParams
	 * @param result
	 */
	public void exportExcel(String xlsParams,String result) {
        //参数验证
        LOGGER.info("导出Excel，参数：" + xlsParams);
        servletResponse.setContentType("application/x-download");
        JSONObject paramsJson = JSON.parseObject(xlsParams);
        if (null != paramsJson && !"".equals(paramsJson)) {
            JSONArray attributeNames = paramsJson.getJSONArray("name");
            JSONArray labelNames = paramsJson.getJSONArray("labelName");
            String[] allAttributeLabels = new String[labelNames.size()];
            String[] allAttributeNames = new String[attributeNames.size()];
            String sheetName = paramsJson.getString("sheetName");
            String targetFileName = sheetName!=null&&!sheetName.equals("")?sheetName:"Export"; //真实的下载文件名
            for (int i = 0; i < allAttributeLabels.length; i++) {
                allAttributeLabels[i] = labelNames.getString(i);
            }
            for (int i = 0; i < allAttributeNames.length; i++) {
                allAttributeNames[i] = attributeNames.getString(i);
            }

            List<Map<String, Object>> data = getListResult(result);
            BufferedOutputStream output = null;
            try {
            	//创建Excel导出
                Workbook workBook = ExcelUtils.createExcel2007Doc(sheetName, data, allAttributeLabels, allAttributeNames, null);
                
            	String userAgent = servletRequest.getHeader("USER-AGENT");
            	//System.out.println("userAgent:"+userAgent);
				if(StringUtils.contains(userAgent, "Firefox")){//google,火狐浏览器 
					targetFileName = new String(targetFileName.getBytes(), "ISO8859-1");
				}else{
					targetFileName = URLEncoder.encode(targetFileName,"UTF8");
				}

            	servletResponse.setHeader("Content-Disposition", "attachment;filename=" + targetFileName + ".xlsx");
                
                output = new BufferedOutputStream(servletResponse.getOutputStream());
                workBook.write(output);
                output.flush();
                servletResponse.flushBuffer();
            } catch (Exception e) {
            	LOGGER.error(e.getLocalizedMessage(),e);
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                	LOGGER.error(e.getLocalizedMessage(),e);
                }
            }
        }
    }
	
	
	public List<Map<String, Object>> getListResult(String result) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        JSONObject result_ = JSON.parseObject(result);
        JSONArray array = result_.getJSONArray("data");
        if(array!=null){
	        for (int i = 0; i < array.size(); i++) {
	            JSONObject jSONObject = array.getJSONObject(i);
	            Map<String, Object> map = JSON.parseObject(jSONObject.toString(), Map.class);
	            list.add(map);
	        }
        }
        return list;
    }
}
