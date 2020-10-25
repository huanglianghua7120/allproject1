package saaf.common.fmw.common.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.fastdfs.core.bean.FastDFSFileEntity;
import com.yhg.fastdfs.core.bean.ResutlFileEntity;
import com.yhg.fastdfs.core.utils.FileManagerUtils;
import com.yhg.hibernate.core.dao.ViewObject;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SaafBaseResultFileEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafBaseResultFile;
import saaf.common.fmw.bean.ImageConfig;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.utils.FileUtil;
import saaf.common.fmw.utils.ImageUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：文件附件
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Path("/fileManagerServices")
@Component("fileManagerServices")
public class FileManagerServices extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileManagerServices.class);
    private static final String configFilePath = "/fdfs_client.properties";
    private static final String IMAGE_FILE_EXT = "bmp,jpg,tiff,gif,psd,cdr,pcd,png,jpeg";
    private static String WATERMARK_TEXT = "我是水印";
    private static Properties properties = new Properties();
    private static Map<String, ImageConfig> IMAGE_TYPE_MAP = new HashMap<String, ImageConfig>();
    private static final int BUFFER_SIZE = 2048;
    private static final int maxSize = 524288000;/*单位byte*/  //原有50M（52428800） 现系统单个文件的大小限制为20M,需要更改为限制在50M，加30M，
    @Autowired
    private ISaafBaseResultFile iSaafBaseResultFile;//附件文件

    @Autowired
    private ViewObject<SaafBaseResultFileEntity_HI> saafBaseResultFileDAO_HI;

    public FileManagerServices() {
        super();
    }

    static {
        try {
            InputStreamReader in = new InputStreamReader(FileUtil.getUserResourceAsStream("/fileuploadConfig.properties"), "UTF-8");
            properties.load(in);
            WATERMARK_TEXT = properties.getProperty("logotext", "");
            Set<String> keys = properties.stringPropertyNames();
            String stiff = "targettype";
            for (String key : keys) {
                if (key.contains(stiff)) {
                    String pre = key.substring(0, key.indexOf(stiff));
                    Integer height = Integer.valueOf(properties.getProperty(pre + "height", "400"));
                    Integer width = Integer.valueOf(properties.getProperty(pre + "width", "400"));
                    Boolean islogo = new Boolean(properties.getProperty(pre + "islogo", "false"));
                    Boolean iscompress = new Boolean(properties.getProperty(pre + "iscompress", "false"));
                    ImageConfig imageConfig = new ImageConfig(height, width, islogo, iscompress);
                    IMAGE_TYPE_MAP.put(properties.getProperty(key), imageConfig);
                }
            }
            IMAGE_TYPE_MAP.put("", new ImageConfig());
            in.close();
        } catch (Exception e) {
            LOGGER.error("文件上传类初始化异常", e);
        }
    }


    /**
     * Streaming API  方式上传，不会创建临时文件，直接以流的方式传给文件服务器，
     * 但是 只能限制整个 request content 的大小，不能限制一次请求中单个 FileItem 大小
     * 上传附件功能
     *
     * @param
     * @return
     */
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String uploadFile() {
        return upload().toString();
    }

    /**
     * Description：附件删除方法
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-22     zhj          创建
     * ==============================================================================
     */
    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@FormParam(PARAMS) String params) {
        try {
            JSONObject paramJSON = this.parseObject(params);
            Integer fileId = paramJSON.getInteger("fileId");
            if(null == fileId || "".equals(fileId)){
                return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空", 0, null);
            }
            return JSON.toJSONString(iSaafBaseResultFile.deleteFile(fileId));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * 文件下载功能
     *
     * @return 返回错误信息
     * @author xuwen
     * @date 2019/12/18
     */
    @GET
    @Path("/download")
    public String download() {
        //通过参数获取文件的下载路径
        String accessPath = request.getParameter("accessPath");
        if (accessPath == null || "".equals(accessPath)) {
            return SToolUtils.convertResultJSONObj("E", "路径为空", 0, "").toJSONString();
        }

        //查询数据库查询文件的组信息和服务器文件名
        List<SaafBaseResultFileEntity_HI> fileList = saafBaseResultFileDAO_HI.findByProperty("accessPath", accessPath);
        if (fileList.isEmpty()) {
            return SToolUtils.convertResultJSONObj("E", "没有找到该文件", 0, "").toJSONString();
        }

        SaafBaseResultFileEntity_HI file = fileList.get(0);
        ResutlFileEntity resutlFileEntity;
        try {
            //下载文件
            FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
            resutlFileEntity = fileManager.downloadFileFromFastDSF(file.getGroupName(), file.getRemoteFilename());
        } catch (Exception e) {
            //e.printStackTrace();
            return SToolUtils.convertResultJSONObj("E", "连接超时", 0, "").toJSONString();
        }

        //处理文件名的编码
        String fileName = file.getFileName();
        response.setContentType("application/x-download");

        fileName = fileNameEncoding(fileName, request);

        //设置响应头，准备下载文件
        if("pdf".equals(file.getFileType())){
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName+".pdf");
        }else{
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        }

        ServletOutputStream os = null;
        try {
            //打开流写入文件
            os = response.getOutputStream();
            os.write(resutlFileEntity.getFileContent());
        } catch (Exception e) {
            //e.printStackTrace();
            try {
                response.sendRedirect(request.getServletContext().getContextPath() + "/templates/index/404.html");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return SToolUtils.convertResultJSONObj("E", "下载失败", 0, "").toJSONString();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 下载硬盘上的文件（在webContent/mould里面的文件）
     *
     * @return 返回下载错误信息
     */
    @GET
    @Path("/downloadDiskFile")
    public String downloadDiskFile() {
        String prefix = "mould/";
        String fileName = request.getParameter("fileName");
        String filePath = request.getParameter("filePath");
        if (filePath == null || "".equals(filePath)) {
            return SToolUtils.convertResultJSONObj("E", "路径为空", 0, null).toJSONString();
        }
        if (fileName == null || "".equals(fileName)) {
            /*return SToolUtils.convertResultJSONObj("E", "文件名为空", 0, null).toJSONString();*/
            //没有文件名参数，则从路径获取文件名
            fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        }

        InputStream is = request.getServletContext().getResourceAsStream(filePath);
        if (is == null) {
            //如果路径没有添加mould开头，则自动加上
            if (!filePath.startsWith(prefix)) {
                filePath = prefix + filePath;
                is = request.getServletContext().getResourceAsStream(filePath);
                if (is == null) {
                    return SToolUtils.convertResultJSONObj("E", "文件不存在", 0, null).toJSONString();
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "文件不存在", 0, null).toJSONString();
            }
        }
        response.setContentType("application/x-download");

        fileName = fileNameEncoding(fileName, request);

        //设置响应头，准备下载文件
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        ServletOutputStream os = null;
        try {
            //打开流写入文件
            os = response.getOutputStream();
            int len;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            try {
                response.sendRedirect(request.getServletContext().getContextPath() + "/templates/index/404.html");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return SToolUtils.convertResultJSONObj("E", "下载失败！", 0, "").toJSONString();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 文件url编码
     *
     * @param fileName 文件名
     * @param request  http请求
     * @return 返回转码后的文件名
     */
    private String fileNameEncoding(String fileName, HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT");
        try {
            if (StringUtils.contains(userAgent, "Firefox")) {
                //google,火狐浏览器
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } else {
                fileName = URLEncoder.encode(fileName, "UTF8");
            }
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 处理tomcat7的get请求的乱码问题
     *
     * @param param 参数值
     * @return 处理后的参数值
     */
    private String tomcat7GetEncoding(String param) {
        try {
            param = new String(param.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        }
        return param;
    }

    private JSONObject upload() {
        String contentLength = request.getHeader("Content-Length");
        if (StringUtils.isNumeric(contentLength) && Integer.valueOf(contentLength) > maxSize) {
            return SToolUtils.convertResultJSONObj("L", "file size limit", 0, "");
        }
        ServletFileUpload upload = new ServletFileUpload();
        if (upload.isMultipartContent(request) == false)
            return SToolUtils.convertResultJSONObj("F", "Please set the enctype property of the form", 0, "");
        ImageConfig imageConfig = new ImageConfig();
        List<SaafBaseResultFileEntity_HI> resultData = new ArrayList<SaafBaseResultFileEntity_HI>();
//           ISaafBaseResultFile saafBaseResultFileServer    = (ISaafBaseResultFile)SaafToolUtils.context.getBean("saafBaseResultFileServer");
        try {
            upload.setHeaderEncoding("UTF-8");
            FileItemIterator iter = upload.getItemIterator(request);
            String name = null;
            String ext = null;
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                InputStream stream = item.openStream();

                if (item.isFormField()) {
                    name = item.getFieldName();
                    if ("imageType".equals(name))
                        imageConfig = IMAGE_TYPE_MAP.get(Streams.asString(stream));
                    if (imageConfig == null)
                        throw new Exception("no config mapped with " + name);
                    continue;
                }
                if (stream == null)
                    continue;
                name = item.getName();
                ext = name.substring(name.lastIndexOf(".") + 1);
                LOGGER.info("File field " + item.getFieldName() + " with file name " + name + " detected.");

                //输入流只能read一次，将其转为字节数组，在进行水印、压缩操作时再通过字节数组重新创建流
                byte[] filecontent = InputStreamTOByte(stream);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(filecontent);
                /*添加水印*/
                if (ext != null && IMAGE_FILE_EXT.indexOf(ext.toLowerCase()) > -1 && imageConfig.isLogo())
                    stream = ImageUtil.imageWaterMark(WATERMARK_TEXT, byteArrayInputStream, name);
                else
                    stream = new ByteArrayInputStream(filecontent);
                FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
                FastDFSFileEntity DFSFileEntity = new FastDFSFileEntity(name, InputStreamTOByte(stream,contentLength), ext);
                ResutlFileEntity resutlFileEntity = fileManager.uploadFile2FastDFS(DFSFileEntity);
                SaafBaseResultFileEntity_HI row = iSaafBaseResultFile.saveResult(resutlFileEntity, this.getSessionUserId());
                resultData.add(row);
//                /*压缩*/
//                if (ext != null && IMAGE_FILE_EXT.indexOf(ext.toLowerCase()) > -1 && imageConfig.isCompress()) {
//                    /*重新创建输入流*/
//                    byteArrayInputStream = new ByteArrayInputStream(filecontent);
//                    stream = ImageUtil.zipImageFile(byteArrayInputStream, imageConfig.getWidth(), imageConfig.getHieght(), 1f);
//                    if (imageConfig.isLogo())
//                        stream = ImageUtil.imageWaterMark(WATERMARK_TEXT, stream, name);
//                    DFSFileEntity = new FastDFSFileEntity("compress_" + name, InputStreamTOByte(stream,contentLength), ext);
//                    ResutlFileEntity resutlFileEntity1 = fileManager.uploadFile2FastDFS(DFSFileEntity);
//                    SaafBaseResultFileEntity_HI row1 = iSaafBaseResultFile.saveResult(resutlFileEntity1, this.getSessionUserId());
//                    resultData.add(row1);
//                }
                stream.close();
                byteArrayInputStream.close();
                filecontent = null;
            }
        } catch (Exception e) {
            LOGGER.error("异常：", e.getMessage());
            //updated by xuwen at 2018/11/30，捕获文件名过长插入失败的异常
            if (e instanceof org.springframework.dao.QueryTimeoutException && e.getCause() != null
                    && e.getCause().getCause() != null && e.getCause().getCause() instanceof java.sql.BatchUpdateException) {
                String message = e.getCause().getCause().getMessage();
                if (message != null && message.contains("SAAF_BASE_RESULT_FILE") && message.contains("FILE_NAME") && message.contains("actual") && message.contains("maximum")) {
                    return SToolUtils.convertResultJSONObj("E", "文件名过长", 0, "");
                }
            }
            return SToolUtils.convertResultJSONObj("E", e.getMessage(), 0, "");
        }
        return SToolUtils.convertResultJSONObj("S", "上传成功", resultData.size(), resultData);
    }


    /**
     * 将InputStream转换成byte数组
     *
     * @param in InputStream
     * @return byte[]
     * @throws IOException
     */
    private byte[] InputStreamTOByte(InputStream in,String contentLength) throws IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        byte[] data1 = new byte[Integer.valueOf(contentLength)+BUFFER_SIZE];

        int byteCount = 0;
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
        {
//            long startTime=System.nanoTime();   //获取开始时间
            for (byte b : data) {
                data1[byteCount] = b;
                byteCount++;
            }
//            long endTime=System.nanoTime(); //获取结束时间
//            System.out.println(endTime-startTime);
        }
        return data1;
    }


    /**
     * 将InputStream转换成byte数组
     *
     * @param in InputStream
     * @return byte[]
     * @throws IOException
     */
    private byte[] InputStreamTOByte(InputStream in) throws IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];

        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
        {
            outStream.write(data, 0, count);
        }
        data = null;
        return outStream.toByteArray();
    }


    private void save(String fileName, InputStream inputStream) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(new File("D:\\temp"), fileName)));
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte[] buffer = new byte[1024];
        int len = -1;
        while (-1 != (len = bis.read(buffer))) {
            bos.write(buffer, 0, len);
        }
        bis.close();
        bos.flush();
        bos.close();
    }


    public static void main(String[] args) {
        FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
        ResutlFileEntity resutlFileEntity = fileManager.deleteFileFromFastDSF("group1", "M00/00/00/cEoOe1f7TZuwPBeLAAA0ghWBnPw984.jpg");

        //FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
        //ResutlFileEntity fileEntity = fileManager.downloadFileFromFastDSF("group1", "M00/00/00/cEoOe1f3SaOcZ2oGAAAPMS2gWJA500.jpg");


    }

}
