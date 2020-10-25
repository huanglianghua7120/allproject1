package saaf.common.fmw.base.model.inter.server;

import com.yhg.base.utils.SToolUtils;
import com.yhg.fastdfs.core.bean.ResutlFileEntity;
import com.yhg.fastdfs.core.utils.FileManagerUtils;
import com.yhg.hibernate.core.dao.ViewObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import javax.ws.rs.core.Context;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SaafBaseResultFileEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafBaseResultFile;
import saaf.common.fmw.utils.SHA1Util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

@Component("saafBaseResultFileServer")
public class SaafBaseResultFileServer implements ISaafBaseResultFile {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaafBaseResultFileServer.class);

    private static final String configFilePath = "/fdfs_client.properties";

    @Context
    protected HttpServletRequest request;

    @Context
    protected HttpServletResponse response;

    @Autowired
    private ViewObject<SaafBaseResultFileEntity_HI> saafBaseResultFileDAO_HI;

    public SaafBaseResultFileServer() {
        super();
    }

    public SaafBaseResultFileEntity_HI saveResult(ResutlFileEntity resutlFileEntity, Integer varUserId) throws Exception {
        if (varUserId == null) {
            varUserId = -1;
        }
        SaafBaseResultFileEntity_HI row = new SaafBaseResultFileEntity_HI();
        row.setFileName(resutlFileEntity.getFileName());
        row.setFileType(resutlFileEntity.getFileType());
        if (resutlFileEntity.getFileSize() != null) {
            row.setFileSize(new BigDecimal(resutlFileEntity.getFileSize()));
        }
        row.setFilePath(resutlFileEntity.getFilePath());
        //设置accessPath加密，前台链接看到的也是加密后的编码，暂时先注释，必要时放开，对合同模块有影响
//        SHA1Util sha1 = new SHA1Util();
//        String accessPath = sha1.getEncrypt(resutlFileEntity.getAccessPath());
//        row.setAccessPath(accessPath);
        row.setAccessPath(resutlFileEntity.getAccessPath());
        row.setRemoteFilename(resutlFileEntity.getRemoteFileName());
        row.setGroupName(resutlFileEntity.getGroupName());
        row.setUploadAuthor(resutlFileEntity.getUploadAuthor());
        row.setUploadDate(resutlFileEntity.getUploadDate());
        row.setOperatorUserId(varUserId);
        saafBaseResultFileDAO_HI.save(row);

        return row;
    }

    /**
     * 下载附件
     *
     * @param request
     * @param response
     * @return
     */
    public String download(HttpServletRequest request, HttpServletResponse response) {
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
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
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
        return SToolUtils.convertResultJSONObj("S", "下载成功", 0, "").toJSONString();
    }

    /**
     * 删除附件
     *
     * @param fileId
     * @return
     */
    @Override
    public String deleteFile(Integer fileId) {
        SaafBaseResultFileEntity_HI fileEntity = saafBaseResultFileDAO_HI.getById(fileId);
        if (null == fileEntity) {
            return SToolUtils.convertResultJSONObj("E", "删除失败", 0, "").toString();
        }
        try {
            FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
            fileManager.deleteFileFromFastDSF(fileEntity.getGroupName(), fileEntity.getRemoteFilename());
            saafBaseResultFileDAO_HI.delete(fileId);
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, "").toString();
        } catch (Exception e) {
            return SToolUtils.convertResultJSONObj("E", e.getMessage(), 0, "").toString();
        }
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
    @Override
    public byte[] findFileToByte(Integer fileId) {
        if(null == fileId){
            return null;
        }
        SaafBaseResultFileEntity_HI fileEntity = saafBaseResultFileDAO_HI.getById(fileId);
        if(null == fileEntity){
            return null;
        }
        ResutlFileEntity resutlFileEntity;
        try{
            FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
            resutlFileEntity = fileManager.downloadFileFromFastDSF(fileEntity.getGroupName(), fileEntity.getRemoteFilename());
            return resutlFileEntity.getFileContent();
        }catch (Exception e){
            LOGGER.error("未知错误:{}", e);
        }
        return null;
    }
}