package saaf.common.fmw.common.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.utils.HttpClient;
import saaf.common.fmw.utils.ImportExcelUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/importExcelServlet")
public class ImportExcelServices extends CommonAbstractServices {
    private static final Logger log = LoggerFactory.getLogger(ImportExcelServices.class);
    public ImportExcelServices() {
        super();
    }
    
    private static final int maxSize = 52428800;/*单位byte*/
    private static final int BUFFER_SIZE = 2048;
    /**
     * 导出Excel公共方法
     * webserviceUrl：请求地址
     * @param params
     */
    @POST
    @Path("importExcel")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String importExcel() {
        String contentLength = request.getHeader("Content-Length");
        if (StringUtils.isNumeric(contentLength) && Integer.valueOf(contentLength) > maxSize) {
            return convertResultJSONObj("L", "file size limit", 0, "");
        }
        ServletFileUpload upload = new ServletFileUpload();
        if (upload.isMultipartContent(request) == false)
            return convertResultJSONObj("F", "Please set the enctype property of the form", 0, "");
        

        
//        String[] allAttributeNames = fieldNameList.toArray(new String[fieldNameList.size()]);
        try {
            upload.setHeaderEncoding("UTF-8");
            FileItemIterator iter = upload.getItemIterator(request);
            Map params = new HashMap<>();
            InputStream fileStream=null;
            String fileName=null;
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                InputStream stream = item.openStream();
                if (item.isFormField()){//如果是非文件域,设置进入map,这里要注意多值处理
                    params=setFormParam(params,item.getFieldName(),stream);//如果不是文件上传,处理
                }else{
                    fileStream=new ByteArrayInputStream(inputStreamTOByte(stream));
                    fileName=item.getName();
                }
            }
            String webserviceUrl =(String) params.get("wsUrl");
            System.out.println("===========>>webserviceUrl:" + webserviceUrl);
            String servicesParams =(String) params.get("params");
            log.info("===============>>servicesParams:" + servicesParams);
            String voName =(String) params.get("className");
            String fieldName =(String) params.get("fieldName");
//            List<String> voNameList = new ArrayList<String>(JSONArray.parseArray(voName, String.class));
//            List<String> fieldNameList = new ArrayList<String>(JSONArray.parseArray(fieldName, String.class));
            String[] allTables =null;
            if(voName.indexOf(",")>-1){ 
            allTables = voName.split(",");
            }
            else{
                allTables =new String[1];
                allTables[0]=voName;
            }
            String[] fieldNameList=null;
            if(fieldName.indexOf("|")>-1){ 
            fieldNameList = fieldName.split("|");
            }
            else{
                fieldNameList = new String[1];
                fieldNameList[0]=fieldName;
            }
            Map<String,List<Object>> map = ImportExcelUtil.getMapByExcel(fileStream,fileName,allTables,fieldNameList);
            Map parMap = new HashMap();
            parMap.put("params", JSON.toJSONString(map));
            return callBatchImport(webserviceUrl,JSON.toJSONString(parMap),null,null);
            
        } catch (Exception e) {
            log.error("", e);
            return convertResultJSONObj("E", e.getMessage(), 0, "");
        }
       
    }
  
  
    /**
     * 将InputStream转换成byte数组
     *
     * @param in InputStream
     * @return byte[]
     * @throws IOException
     */
    private byte[] inputStreamTOByte(InputStream in) throws IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return outStream.toByteArray();
    }
  
    /**
         * 处理非上传的表单
         * @param name
         * @param is
         */
        private Map setFormParam(Map params,String name, InputStream is) {
            try { 
                params.put(name,Streams.asString(is));//直接存入参数中
            } catch (IOException e) {
                //e.printStackTrace();
            }
            return params;
                             
        }
     
     
    /**
     * 查询services服务数据
     * @param webserviceUrl
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public  String callBatchImport(String webserviceUrl, String params, String pageIndex, String pageRows) {
        
        try {
            
            System.out.println("================>>>>查询services服务数据:" + params);
            JSONObject json = JSON.parseObject(params);
            String params_ = json.getString("params");
           
            Map<String, Object> formParam = new HashMap<String, Object>();
            formParam.put("params", params_);
            formParam.put("pageIndex", pageIndex);
            formParam.put("pageRows", pageRows);


            String token = request.getHeader("TokenCode");
            //            Map<String, String> headerMap = new HashMap<String, String>();
            //            headerMap.put("TokenCode", token);
            //            headerMap.put("TokenKey", String.valueOf(request.getSession().getId()));

            formParam.put("TokenCode", token);
            formParam.put("TokenKey", String.valueOf(request.getSession().getId()));
            formParam.put("UserId", this.getSessionUserId());
            formParam.put("PlatformCode", getUserSessionBean().getPlatformCode());
            formParam.put("InstIdData", ',' + this.getInstIdData() + ',');
//            String saveResult = RestfulClientUtils.restfulPostFormParam(webserviceUrl, formParam);
//            log.info("==========findListResult ======" + saveResult);
            
            return HttpClient.postForm(getHostName()+webserviceUrl, formParam);
            

        } catch (Exception e) {
            //e.printStackTrace();
            log.error("导入Excel数据失败！" + e);
            
            return null;
        }
       
    }
    
    private String getHostName(){
        URL url=null;
        try {
            url = new URL(request.getRequestURL().toString());
        } catch (MalformedURLException e) {
        }
        int port = request.getLocalPort();
        return "http://"+url.getHost()+":"+port;
        }
    public static void main(String[] args) {
        ImportExcelServices importExcelServices = new ImportExcelServices();
    }
}
