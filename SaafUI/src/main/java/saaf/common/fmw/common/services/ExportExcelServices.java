//package saaf.common.fmw.common.services;
//
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.base.sie.common.utils.RestfulClientUtils;
//import com.yhg.base.utils.excel.OperationExcelDocUtils;
//
//import org.apache.poi.ss.usermodel.Workbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import saaf.common.fmw.common.utils.SaafToolUtils;
//import saaf.common.fmw.servlets.CommonAbstractServices;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.UriBuilder;
//import java.io.*;
//import java.net.InetAddress;
//import java.net.URI;
//import java.net.UnknownHostException;
//import java.util.*;
//
////import com.sun.jersey.api.client.Client;
////import com.sun.jersey.api.client.WebResource;
////import com.sun.jersey.api.client.config.ClientConfig;
////import com.sun.jersey.api.client.config.DefaultClientConfig;
////import com.sun.jersey.core.util.MultivaluedMapImpl;
////import javax.ws.rs.core.MultivaluedMap;
//
//
//@Path("/exportExcelServlet")
//public class ExportExcelServices extends CommonAbstractServices {
//    private static final Logger LOGGER = LoggerFactory.getLogger(ExportExcelServices.class);
//    private static final String pageIndex = "1";
//    private static final String pageRows = "-1";
//
//    public ExportExcelServices() {
//        super();
//    }
//
//
//    /**
//     * 导出Excel公共方法
//     * webserviceUrl：请求地址
//     * @param params
//     */
////    @POST
////    @Path("exportExcel")
////    @Produces(MediaType.APPLICATION_OCTET_STREAM)
////    public String exportExcel(@FormParam("params")
////                              String params) {
////        //参数验证
////        LOGGER.info("导出Excel，参数==params:" + params);
////        String msg = "";
////        if (params != null && !"".equals(params)) {
////            InetAddress addr = null;
////            try {
////                addr = InetAddress.getLocalHost();
////            } catch (UnknownHostException e) {
////                System.out.println();
////            }
//////            String url_ =SaafToolUtils.properties.getProperty("HTTP_HOST")+ SaafToolUtils.properties.getProperty("FILE_DOWNLOAD");
//////            System.out.println("==============>>url_:" + url_);
////            JSONObject reqParams = JSONObject.parseObject(params);
////            String webserviceUrl = reqParams.getString("webserviceUrl");
////            System.out.println("===========>>webserviceUrl:" + webserviceUrl);
////            String servicesParams = reqParams.getString("params");
////            LOGGER.info("===============>>servicesParams:" + servicesParams);
////            String sheetName = reqParams.getString("sheetName");
////            String labelName = reqParams.getString("labelName");
////            String name = reqParams.getString("name");
////            List<String> labelNameList = new ArrayList<String>(JSONArray.parseArray(labelName, String.class));
////            List<String> nameList = new ArrayList<String>(JSONArray.parseArray(name, String.class));
////            String[] allAttributeLabels = labelNameList.toArray(new String[labelNameList.size()]);
////            String[] allAttributeNames = nameList.toArray(new String[nameList.size()]);
////        
////            String url2=request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+request.getContextPath() + "/" + webserviceUrl;
////            List<Map<String, Object>> list = findListResult(url2 , servicesParams, String.valueOf(pageIndex), String.valueOf(pageRows));
////
////            //创建Excel导出
////            Workbook workBook = createExcelDoc(sheetName, allAttributeLabels, allAttributeNames, list);
////            try {
////                String fullPath = System.getProperty("java.io.tmpdir"); //获取操作系统临时文件夹路径
////                String targetFileName = sheetName + ".xlsx"; //真实的下载文件名
////                String fullFileName = UUID.randomUUID() + targetFileName;
////                LOGGER.info("生成临时文件:" + fullPath + File.separator + fullFileName);
////                File file = new File(fullPath + File.separator + fullFileName);
////                file.createNewFile();
////                FileOutputStream out = new FileOutputStream(file);
////                workBook.write(out);
////                out.close();
////                return fullFileName;
////            } catch (IOException e) {
////                //e.printStackTrace();
////                msg = "导出Excle异常！";
////                LOGGER.error(msg + e);
////            }
////        } else {
////            msg = "导出Excle参数错误！";
////            LOGGER.error(msg);
////        }
////        return convertResultJSONObj("E", msg, 0, null);
////    }
//
//    /**
//     * @param fullFileName 是一个临时文件的名字，有系统自动生成不重复的唯一的文件名字，只有机器能识别
//     * @param targetFileName 是用户下载之后显示的文件名字，即一个真实文件的名字
//     * 例如：fullFileName是a7fde56e-ec5b-449f-b949-fe2ba9300c83_log4j.properties，并且a7fde56e-ec5b-449f-b949-fe2ba9300c83_log4j.properties名字是有POST方执行之后返回的.
//     *  targetFileName是log4j.properties
//     */
//    @Path("/downloadFile")
//    @GET
//    public void downloadFile(@QueryParam("fulleFileName")
//                             String fullFileName, @QueryParam("targetFileName")
//                             String targetFileName) {
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//        response.addHeader("Access-Control-Max-Age", "1728000");
//        response.setContentType("application/x-download");
//
//        BufferedOutputStream output = null;
//        BufferedInputStream input = null;
//        String fullPath = System.getProperty("java.io.tmpdir");
//        String fileFullPathName = fullPath + File.separator + fullFileName;
//        try {
//            response.setHeader("Content-Disposition", "attachment;filename=" + new String(targetFileName.getBytes("utf-8"), "ISO-8859-1") + ".xlsx");
//            File exclFile = new File(fileFullPathName);
//            response.setContentLength((int)exclFile.length());
//
//            byte[] buffer = new byte[4096]; // 缓冲区
//            output = new BufferedOutputStream(response.getOutputStream());
//            input = new BufferedInputStream(new FileInputStream(exclFile));
//            int n = -1;
//            //遍历，开始下载
//            while ((n = input.read(buffer, 0, 4096)) > -1) {
//                output.write(buffer, 0, n);
//            }
//            output.flush();
//            response.flushBuffer();
//        } catch (FileNotFoundException e) {
//            //e.printStackTrace();
//        } catch (IOException e) {
//            //e.printStackTrace();
//        } finally {
//            try {
//                //关闭流，不可少
//                if (input != null) {
//                    input.close();
//                }
//                if (output != null) {
//                    output.close();
//                }
//                File dFile = new File(fileFullPathName);
//                dFile.delete();
//                //testFile.deleteOnExit(); //文件下载完之后将原有的文件删除
//            } catch (IOException e) {
//                //e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 查询services服务数据
//     * @param webserviceUrl
//     * @param params
//     * @param pageIndex
//     * @param pageRows
//     * @return
//     */
////    public List<Map<String, Object>> findListResult(String webserviceUrl, String params, String pageIndex, String pageRows) {
////        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
////        try {
////            LOGGER.info("--->>开始查询service服务数据,参数：" + webserviceUrl + ",params:" + params);
////            System.out.println("================>>>>查询services服务数据:" + params);
////            JSONObject json = JSON.parseObject(params);
////            String params_ = json.getString("params");
////            //            ClientConfig config = new DefaultClientConfig();
////            //            Client client = Client.create(config);
////            //            WebResource service = client.resource(getBaseURI(webserviceUrl));
////            //            MultivaluedMap<String, String> param = new MultivaluedMapImpl();
////            //            org.codehaus.jettison.json.JSONObject postParam = new org.codehaus.jettison.json.JSONObject();
////            //            postParam.optJSONObject(params_);
////            //            param.putSingle("params", params_);
////            //            param.putSingle("pageIndex", pageIndex);
////            //            param.putSingle("pageRows", pageRows);
////            Map<String, Object> formParam = new HashMap<String, Object>();
////            formParam.put("params", params_);
////            formParam.put("pageIndex", pageIndex);
////            formParam.put("pageRows", pageRows);
////
////
////            String token = request.getHeader("TokenCode");
////            //            Map<String, String> headerMap = new HashMap<String, String>();
////            //            headerMap.put("TokenCode", token);
////            //            headerMap.put("TokenKey", String.valueOf(request.getSession().getId()));
////
////            formParam.put("TokenCode", token);
////            formParam.put("TokenKey", String.valueOf(request.getSession().getId()));
////            formParam.put("UserId", this.getSessionUserId());
////            formParam.put("PlatformCode", this.getPlatformCode());
////            formParam.put("InstIdData", ',' + this.getInstIdData() + ',');
////
////            //            String findListResult = RestfulClientUtils.restfulPostFormParam(webserviceUrl, formParam, headerMap, 60 * 1000 * 60);
////
////            String findListResult = RestfulClientUtils.restfulPostFormParam(webserviceUrl, formParam);
////            LOGGER.info("==========findListResult ======" + findListResult);
////            //            String findListResult = service.queryParams(param).type(MediaType.APPLICATION_FORM_URLENCODED).post(String.class);
////            JSONObject result_ = JSON.parseObject(findListResult);
////            JSONArray array = result_.getJSONArray("data");
////            for (int i = 0; i < array.size(); i++) {
////                JSONObject jSONObject = array.getJSONObject(i);
////                Map<String, Object> map = JSON.parseObject(jSONObject.toString(), Map.class);
////                list.add(map);
////            }
////
////        } catch (Exception e) {
////            //e.printStackTrace();
////            LOGGER.error("导出Excel查询数据失败！" + e);
////        }
////        return list;
////    }
//
//
//    /**
//     * 生成Excel文件&输出文件流
//     * @param sheetName
//     * @param allAttributeLabels
//     * @param attributeNames
//     * @param findListResult
//     */
//    public Workbook createExcelDoc(String sheetName, String[] allAttributeLabels, String[] attributeNames, List findListResult) {
//        Workbook workBook = null;
//        try {
//            OperationExcelDocUtils excelDoc = new OperationExcelDocUtils(allAttributeLabels);
//            workBook = excelDoc.createExcel2007Doc(sheetName, findListResult, attributeNames);
//
//            //            ServletOutputStream outputStream = response.getOutputStream();
//            //            workBook.write(outputStream);
//            //            String fileName = sheetName + ".xlsx";
//            //            response.reset();
//            //            response.setContentType("application/msexcel;charset=utf-8");
//            //            response.setHeader("Content-disposition", "attachment;filename= " + fileName);
//            //
//            //            outputStream.flush();
//            //            outputStream.close();
//            LOGGER.info("导出Excel，输出Excel文件成功！");
//        } catch (Exception e) {
//            //e.printStackTrace();
//            LOGGER.error("导出Excel，输出Excel文件失败！" + e);
//        }
//        return workBook;
//    }
//
//    /**
//     * 获取URL
//     * @param webserviceUrl
//     * @return
//     */
//    private static URI getBaseURI(String webserviceUrl) {
//        return UriBuilder.fromUri(webserviceUrl).build();
//    }
//
//}
//
