package saaf.common.fmw.common.services;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.fastdfs.core.bean.ResutlFileEntity;
import com.yhg.fastdfs.core.utils.FileManagerUtils;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import saaf.common.fmw.bean.ImageConfig;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.utils.FileUtil;
import saaf.common.fmw.utils.ImageUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

//import com.base.adf.common.utils.FileUtil;


/**
 * Created by huangtao on 2016/10/6.
 */
@Path("/appFile")
public class FileAppMngServices extends CommonAbstractServices {
    private static final Logger log = LoggerFactory.getLogger(FileManagerServices.class);
    private static final String configFilePath = "/fdfs_client.properties";
    private static final String IMAGE_FILE_EXT = "bmp,jpg,tiff,gif,psd,cdr,pcd,png,jpeg";
    private static  String WATERMARK_TEXT = "我是水印";
    
    private static Properties properties = new Properties();

    private static Map<String,ImageConfig> IMAGE_TYPE_MAP=new HashMap<String,ImageConfig>();

    private static final int BUFFER_SIZE = 2048;

    private static final int maxSize = 52428800;/*单位byte*/

    private static  String UPLOAD_PATH = "";
    private static  String UPLOAD_DIR = "";



    static {
        try {
            InputStreamReader in= new InputStreamReader(FileUtil.getUserResourceAsStream("/fileuploadConfig.properties"), "UTF-8");
            properties.load(in);
            WATERMARK_TEXT=properties.getProperty("logotext","");
            UPLOAD_PATH = properties.getProperty("uploadpath","");
            UPLOAD_DIR = properties.getProperty("uploaddir","");
            Set<String> keys= properties.stringPropertyNames();
            String stiff="targettype";
            for (String key:keys){
                if (key.contains(stiff)){
                    String pre=key.substring(0,key.indexOf(stiff));
                    Integer height=  Integer.valueOf(properties.getProperty(pre+"height","400"));
                    Integer width=  Integer.valueOf(properties.getProperty(pre+"width","400"));
                    Boolean islogo= new Boolean(properties.getProperty(pre+"islogo","false")) ;
                    Boolean iscompress= new Boolean(properties.getProperty(pre+"iscompress","false")) ;
                    ImageConfig imageConfig=new ImageConfig(height,width,islogo,iscompress);
                    IMAGE_TYPE_MAP.put(properties.getProperty(key),imageConfig);
                }
            }
            IMAGE_TYPE_MAP.put("",new ImageConfig());
            in.close();
        }catch (Exception e){
            log.error("文件上传类初始化异常",e);
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

    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@FormParam("groupName") String groupName,@FormParam("remoteFileName") String remoteFileName) {
        if (StringUtils.isBlank(groupName) || StringUtils.isBlank(remoteFileName))
            return SToolUtils.convertResultJSONObj("F","parameter error",0,"").toString();
        try {
//            FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
//            fileManager.deleteFileFromFastDSF(groupName,remoteFileName);
            return SToolUtils.convertResultJSONObj("S","删除成功",1,"").toString();
        }catch (Exception e){
            log.error("",e);
            return SToolUtils.convertResultJSONObj("E",e.getMessage(),0,"").toString();
        }
    }



    private JSONObject upload() {
        String contentLength = request.getHeader("Content-Length");
        if (StringUtils.isNumeric(contentLength) && Integer.valueOf(contentLength) > maxSize) {
            return SToolUtils.convertResultJSONObj("L", "file size limit", 0, "");
        }
        ServletFileUpload upload = new ServletFileUpload();
        if (upload.isMultipartContent(request) == false)
            return SToolUtils.convertResultJSONObj("F", "Please set the enctype property of the form", 0, "");
        ImageConfig imageConfig=new ImageConfig();
        List<ResutlFileEntity> resultData = new ArrayList<ResutlFileEntity>();
        try {
            upload.setHeaderEncoding("UTF-8");
            FileItemIterator iter = upload.getItemIterator(request);
            String name = null;
            String ext = null;
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                InputStream stream = item.openStream();
                if (item.isFormField()) {
                    name=item.getFieldName();
                    if ("imageType".equals(name))
                        imageConfig=IMAGE_TYPE_MAP.get(Streams.asString(stream));
                    if(imageConfig==null)
                        throw new Exception("no config mapped with "+name);
                    continue;
                }
                if (stream == null)
                    continue;
                name = item.getName();
//                ext = name.substring(name.lastIndexOf(".") + 1);
                log.info("File field " + item.getFieldName() + " with file name " + name + " detected.");
                //输入流只能read一次，将其转为字节数组，在进行水印、压缩操作时再通过字节数组重新创建流
                byte[] filecontent=InputStreamTOByte(stream);
                ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(filecontent);
                /*添加水印*/
                if (ext != null && IMAGE_FILE_EXT.indexOf(ext.toLowerCase()) > -1 && imageConfig.isLogo())
                    stream = ImageUtil.imageWaterMark(WATERMARK_TEXT, byteArrayInputStream, name);
                else
                    stream=new ByteArrayInputStream(filecontent);
                
                ResutlFileEntity e=save(name,stream);
                resultData.add(e);
                
                /*压缩*/
                if ( ext != null && IMAGE_FILE_EXT.indexOf(ext.toLowerCase()) > -1 && imageConfig.isCompress()) {
                    /*重新创建输入流*/
                    byteArrayInputStream=new ByteArrayInputStream(filecontent);
                    stream = ImageUtil.zipImageFile(byteArrayInputStream, imageConfig.getWidth(), imageConfig.getHieght(), 1f);
                    if (imageConfig.isLogo())
                        stream = ImageUtil.imageWaterMark(WATERMARK_TEXT, stream, name);
                   
                    ResutlFileEntity e1=save(name,stream);
                    resultData.add(e1);
                }
                stream.close();
                
            }
        } catch (Exception e) {
            log.error("", e);
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
    private byte[] InputStreamTOByte(InputStream in) throws IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return outStream.toByteArray();
    }



    private ResutlFileEntity save(String fileName, InputStream inputStream) throws IOException {
        String randomName=makeFileName(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(new File(UPLOAD_PATH),randomName)));
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte[] buffer = new byte[1024];
        int len = -1;
        while (-1 != (len = bis.read(buffer))) {
            bos.write(buffer, 0, len);
        }
        bis.close();
        bos.flush();
        bos.close();
        ResutlFileEntity e= new ResutlFileEntity();
        URL url =new URL(request.getRequestURL().toString());
        int port = request.getLocalPort();
        String path ="http://"+url.getHost()+":"+port+"/"+UPLOAD_DIR+"/"+randomName;
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        e.setAccessPath(path);
        e.setGroupName(UPLOAD_DIR);
        e.setFileType(ext);
        e.setRemoteFileName(randomName);
        e.setFileName(randomName);
        return e;
    }
    /**
         * 生成随机新文件名
         * @param itemName
         * @return
         */
        private String makeFileName(String itemName) {
            String fileName = itemName;
            // 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
            // c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
            // 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
            // 得到上传文件的扩展名
            String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
            return (sdf.format(date) + UUID.randomUUID().toString().replace("-","") + "." +
                    fileExtName).toString();
        }

    public static void main(String[] args) {
        FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
        ResutlFileEntity resutlFileEntity=fileManager.deleteFileFromFastDSF("group1","M00/00/00/cEoOe1f7TZuwPBeLAAA0ghWBnPw984.jpg");
        System.out.println(JSONObject.toJSON(resutlFileEntity));

        //FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
        //ResutlFileEntity fileEntity = fileManager.downloadFileFromFastDSF("group1", "M00/00/00/cEoOe1f3SaOcZ2oGAAAPMS2gWJA500.jpg");
        //System.out.println(JSONObject.toJSON(fileEntity));


    }

}
