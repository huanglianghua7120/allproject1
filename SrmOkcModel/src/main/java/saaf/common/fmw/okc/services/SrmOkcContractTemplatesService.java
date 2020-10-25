package saaf.common.fmw.okc.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.view.Viewable;
import com.yhg.fastdfs.core.bean.FastDFSFileEntity;
import com.yhg.fastdfs.core.bean.ResutlFileEntity;
import com.yhg.fastdfs.core.utils.FileManagerUtils;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;

import saaf.common.fmw.base.model.entities.SaafBaseResultFileEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafBaseResultFile;
import saaf.common.fmw.base.model.inter.ISaafLookup;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.okc.model.entities.SrmOkcContractTemplatesEntity_HI;
import saaf.common.fmw.okc.model.entities.SrmOkcWatermarksEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractTemplatesEntity_HI_RO;
import saaf.common.fmw.okc.model.inter.ISrmOkcContractTemplates;
import saaf.common.fmw.okc.model.inter.ISrmOkcWatermarks;
import saaf.common.fmw.okc.model.inter.server.OfficeManagerServer;
import saaf.common.fmw.okc.utils.WordToPdf;
import saaf.common.fmw.services.CommonAbstractServices;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmOkcContractTemplatesService.java
 * Description：合同模版控制类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date     @Author(Updated By)     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019/5/31      欧阳岛          创建
 * V1.1       2019/7/24      秦晓钊          修改，新增方法findContractTemplatesToLov
 * <p>
 * ==============================================================================
 */
@Component
@Path("/srmOkcContractTemplatesService")
public class SrmOkcContractTemplatesService extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmOkcContractTemplatesService.class);

    private static final String configFilePath = "/fdfs_client.properties";

    @Autowired
    private ViewObject<SaafBaseResultFileEntity_HI> saafBaseResultFileDAO_HI;

    @Autowired
    private ISrmOkcContractTemplates srmOkcContractTemplatesServer;

    @Autowired
    private ISaafBaseResultFile iSaafBaseResultFile;//附件文件

    @Autowired
    private ISaafLookup baseSaafLookupServer;

    @Autowired
    private ISrmOkcWatermarks srmOkcWatermarksServer;

    public SrmOkcContractTemplatesService() {
        super();
    }

    /**
     * Description：查询合同模版列表
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmOkcContractTemplatesList")
    public String findSrmOkcContractTemplatesList(@FormParam(PARAMS)
                                                          String params, @FormParam(PAGE_INDEX)
                                                          Integer pageIndex, @FormParam(PAGE_ROWS)
                                                          Integer pageRows) {
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmOkcContractTemplatesEntity_HI_RO> infoList = srmOkcContractTemplatesServer.findSrmOkcContractTemplatesList(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        }catch (UtilsException e){
            LOGGER.error("查询合同模版列表异常：", e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同模版列表失败!"+e.getMessage(), 0, null);
        }catch (Exception e) {
            LOGGER.error("查询合同模版列表异常：", e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同模版列表失败!"+e.getMessage(), 0, null);
        }
    }

    /**
     * Description：保存合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @Path("saveSrmOkcContractTemplates")
    @POST
    public String saveSrmOkcContractTemplates(@FormParam("params")
                                                      String params) {
        LOGGER.debug("保存合同模版信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmOkcContractTemplatesServer.saveSrmOkcContractTemplates(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (UtilsException e){
            LOGGER.error("保存合同模版失败：", e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存合同模版失败!"+e.getMessage(), 0, null);
        }catch (Exception e) {
            LOGGER.error("保存合同模版失败！", e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存合同模版失败!"+e.getMessage(), 0, null);
        }
    }

    /**
     * Description：提交合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @Path("submitSrmOkcContractTemplates")
    @POST
    public String submitSrmOkcContractTemplates(@FormParam("params")
                                                        String params) {
        LOGGER.debug("提交合同模版信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmOkcContractTemplatesServer.doSubmitSrmOkcContractTemplates(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (UtilsException e){
            LOGGER.error("提交合同模版失败：", e);
            return CommonAbstractServices.convertResultJSONObj("E", "提交合同模版失败!"+e.getMessage(), 0, null);
        }catch (Exception e) {
            LOGGER.error("提交合同模版失败！", e);
            return CommonAbstractServices.convertResultJSONObj("E", "提交合同模版失败!"+e.getMessage(), 0, null);
        }
    }

    /**
     * Description：审批合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @Path("approvalSrmOkcContractTemplates")
    @POST
    public String approvalSrmOkcContractTemplates(@FormParam("params")
                                                          String params) {
        LOGGER.debug("审批合同模版信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmOkcContractTemplatesServer.doApprovalSrmOkcContractTemplates(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (UtilsException e){
            LOGGER.error("审批合同模版失败：", e);
            return CommonAbstractServices.convertResultJSONObj("E", "审批合同模版失败!"+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("审批合同模版失败！", e);
            return CommonAbstractServices.convertResultJSONObj("E", "审批合同模版失败!"+e.getMessage(), 0, null);
        }
    }

    /**
     * Description：驳回合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @Path("rejectSrmOkcContractTemplates")
    @POST
    public String rejectSrmOkcContractTemplates(@FormParam("params")
                                                        String params) {
        LOGGER.debug("驳回合同模版信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmOkcContractTemplatesServer.doRejectSrmOkcContractTemplates(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (UtilsException e){
            LOGGER.error("驳回合同模版失败：", e);
            return CommonAbstractServices.convertResultJSONObj("E", "驳回合同模版失败!"+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("驳回合同模版失败！", e);
            return CommonAbstractServices.convertResultJSONObj("E", "驳回合同模版失败!"+e.getMessage(), 0, null);
        }
    }

    /**
     * Description：更新合同模版版本
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @Path("changeSrmOkcContractTemplates")
    @POST
    public String changeSrmOkcContractTemplates(@FormParam("params")
                                                        String params) {
        LOGGER.debug("更新合同模版信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmOkcContractTemplatesServer.doChangeSrmOkcContractTemplates(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (UtilsException e){
            LOGGER.error("更新合同模版失败：", e);
            return CommonAbstractServices.convertResultJSONObj("E", "更新合同模版失败!"+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("更新合同模版失败！", e);
            return CommonAbstractServices.convertResultJSONObj("E", "更新合同模版失败!"+e.getMessage(), 0, null);
        }
    }

    /**
     * Description：删除合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @Path("deleteSrmOkcContractTemplates")
    @POST
    public String deleteSrmOkcContractTemplates(@FormParam("params")
                                                        String params) {
        LOGGER.debug("删除合同模版信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmOkcContractTemplatesServer.deleteSrmOkcContractTemplates(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (UtilsException e){
            LOGGER.error("删除合同模版失败：", e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除合同模版失败!"+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("删除合同模版失败！", e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除合同模版失败!"+e.getMessage(), 0, null);
        }
    }

    /**
     * Description：查询单个合同模版信息
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/5/31      欧阳岛          创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmOkcContractTemplate")
    public String findSrmOkcContractTemplate(@FormParam("params")
                                                     String params) {
        LOGGER.debug("查询合同模版信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject resultJson = srmOkcContractTemplatesServer.findSrmOkcContractTemplate(jsonParam);
            return resultJson.toJSONString();
        } catch (UtilsException e){
            LOGGER.error("查询合同模版信息失败：", e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同模版信息失败!"+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同模版信息失败！", e);
            return convertResultJSONObj("E", "查询合同模版信息失败!"+e.getMessage(), 0, null);
        }
    }

    /**
     * Description：在线编辑合同模板文件
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/13      欧阳岛          创建
     * ==============================================================================
     */
    @GET
    @Path("/editTemplateFile/{templateId}")
    @Produces("text/plain;charset=gbk")
    public Viewable editTemplateFile(@PathParam("templateId")
                                             Integer templateId) {
        LOGGER.debug("templateId: {}", templateId);
//        String ofp = downloadFileFromFastDSF(templateId);

        PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
        //设置服务器页面
        poCtrl.setServerPage(request.getContextPath() + "/poserver.zz");
//        poCtrl.setJsFunction_AfterDocumentOpened("AfterDocumentOpened");
//        poCtrl.addCustomToolButton("保存", "Save", 1);
        poCtrl.setTitlebar(false); //隐藏标题栏
        poCtrl.setMenubar(false); //隐藏菜单栏
//        poCtrl.setOfficeToolbars(false);//隐藏Office工具条
        poCtrl.setCustomToolbar(false);//隐藏自定义工具栏
        // 设置文件保存之后执行的事件
        poCtrl.setJsFunction_AfterDocumentSaved("AfterDocumentSaved()");
        poCtrl.setSaveFilePage(request.getContextPath() + "/restServer/srmOkcContractTemplatesService/saveTemplateFile/" + templateId);
//        poCtrl.webOpen(ofp, OpenModeType.docNormalEdit, this.getUserSessionBean().getUserFullName());
        poCtrl.webOpen(request.getContextPath() + "/restServer/srmOkcContractTemplatesService/openTemplateFileStream/" + templateId, OpenModeType.docNormalEdit, this.getUserSessionBean() == null ? "匿名" : this.getUserSessionBean().getUserFullName());
        request.setAttribute("pohc", poCtrl.getHtmlCode("PageOfficeCtrl1"));

        try {
            Map<String, Object> map = new HashMap<String, Object>();
//            合同模板变量快码类型
            map.put("lookupType", "CON_TEMPLATE_VARIABLE");
            List lookupValuesList = baseSaafLookupServer.findLookupValues(map);
            request.setAttribute("lookupValuesList", lookupValuesList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
        return new Viewable("/WEB-INF/page/editTemplate.jsp", null);
    }

    /**
     * Description：文件流的方式远程打开合同模板文件
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/13      欧阳岛          创建
     * ==============================================================================
     */
    @GET
    @Path("/openTemplateFileStream/{templateId}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public HttpServletResponse openTemplateFileStream(@PathParam("templateId")
                                                              Integer templateId) {
        SrmOkcContractTemplatesEntity_HI tempObj = srmOkcContractTemplatesServer.findSrmOkcContractTemplatesEntity_HI(templateId);
        SaafBaseResultFileEntity_HI file = saafBaseResultFileDAO_HI.get(SaafBaseResultFileEntity_HI.class, tempObj.getTemplateFileId());
        InputStream is = null;
        try {
            URL url = new URL(file.getAccessPath());
            is = url.openStream();
            response.reset();
            // application/x-excel, application/ms-powerpoint, application/pdf
            //;charset=utf-8
            response.setContentType("application/msword");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + new String(file.getFileName().getBytes(), "ISO-8859-1"));
            OutputStream outputStream = response.getOutputStream();
            IOUtils.copy(is, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e.getCause());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
        }
        return response;
    }

    /**
     * Description：下载合同模板文件到应用服务器
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/13      欧阳岛          创建
     * ==============================================================================
     */
    private String downloadFileFromFastDSF(Integer templateId) {
        SrmOkcContractTemplatesEntity_HI tempObj = srmOkcContractTemplatesServer.findSrmOkcContractTemplatesEntity_HI(templateId);
        SaafBaseResultFileEntity_HI file = saafBaseResultFileDAO_HI.get(SaafBaseResultFileEntity_HI.class, tempObj.getTemplateFileId());
        //下载文件
        FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
        ResutlFileEntity resutlFileEntity = fileManager.downloadFileFromFastDSF(file.getGroupName(), file.getRemoteFilename());
        String fileName = file.getRemoteFilename();
        String ofp = System.getProperty("user.dir") + "/tmp/" + fileName;
        FileOutputStream fileOutputStream = null;
        try {
            File tempf = new File(ofp);
            if (!tempf.getParentFile().exists()) {
                tempf.getParentFile().mkdirs();
            }
            fileOutputStream = new FileOutputStream(tempf);
            fileOutputStream.write(resutlFileEntity.getFileContent());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e.getCause());
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e1) {
                    LOGGER.error(e1.getMessage(), e1);
                }
            }
        }
        return ofp;
    }

    /**
     * Description：合同模板在线预览,且word文件转pdf
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/13      欧阳岛          创建
     * ==============================================================================
     */
    @GET
    @Path("/previewTemplateFilePdf/{templateId}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public HttpServletResponse previewTemplateFilePdf(@PathParam("templateId")
                                                              Integer templateId) {
        SrmOkcContractTemplatesEntity_HI tempObj = srmOkcContractTemplatesServer.findSrmOkcContractTemplatesEntity_HI(templateId);
        SaafBaseResultFileEntity_HI file = saafBaseResultFileDAO_HI.get(SaafBaseResultFileEntity_HI.class, tempObj.getTemplateFileId());
        SrmOkcWatermarksEntity_HI watermarksEntity = srmOkcWatermarksServer.findSrmOkcWatermarksEntity_HI(tempObj.getWatermarkId());
        SaafBaseResultFileEntity_HI watermarksFile = saafBaseResultFileDAO_HI.get(SaafBaseResultFileEntity_HI.class, watermarksEntity.getWatermarkFileId());
        InputStream is = null;
        try {
            URL url = new URL(file.getAccessPath());
            is = url.openStream();
            response.reset();
            response.setContentType("application/pdf;charset=utf-8");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + new String((file.getFileName() + ".pdf").getBytes(), "ISO-8859-1"));
            OutputStream outputStream = response.getOutputStream();
            OfficeManagerServer.startOffice();
            String filename = file.getFileName();
            String ext = filename.substring(filename.lastIndexOf(".") + 1);
            String tempPath = System.getProperty("user.dir") + "/pdf/" + System.currentTimeMillis() + ".pdf";
            WordToPdf.word2Pdf(is, tempPath, ext);
            BufferedOutputStream pbos = new BufferedOutputStream(outputStream);
            WordToPdf.imageWatermark(pbos, tempPath, watermarksFile.getAccessPath(), watermarksEntity.getAttribute16());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e.getCause());
        } finally {
            try {
                OfficeManagerServer.stopOffice();
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        //e.printStackTrace();
                        throw new RuntimeException(e.getMessage(), e.getCause());
                    }catch (Exception e){
                        throw new RuntimeException(e.getMessage(), e.getCause());
                    }
                }
            }catch (UtilsException e){
                throw new RuntimeException(e.getMessage(), e.getCause());
            }catch (Exception e){
                throw new RuntimeException(e.getMessage(), e.getCause());
            }

        }
        return response;
    }

    /**
     * Description：合同模板文件在线编辑修改后上传文件服务器并更新文件编号
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019/6/13      欧阳岛          创建
     * ==============================================================================
     */
    @POST
    @Path("/saveTemplateFile/{templateId}")
    public void saveTemplateFile(@PathParam("templateId")
                                         Integer templateId, @Context HttpServletResponse res) {
        System.out.println("1111111111111111");
        FileSaver fs = new FileSaver(request, res);
        try {
            SrmOkcContractTemplatesEntity_HI tempObj = srmOkcContractTemplatesServer.findSrmOkcContractTemplatesEntity_HI(templateId);
            String filename = tempObj.getTemplateFileName();
            // 上传文件
            FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
            String ext = filename.substring(filename.lastIndexOf(".") + 1);
            FastDFSFileEntity DFSFileEntity = new FastDFSFileEntity(filename, fs.getFileBytes(), ext);
            ResutlFileEntity resutlFileEntity = fileManager.uploadFile2FastDFS(DFSFileEntity);
//            fileManager.deleteFileFromFastDSF("", "");
            SaafBaseResultFileEntity_HI temp = iSaafBaseResultFile.saveResult(resutlFileEntity, this.getSessionUserId());
            // 更新模板文件ID
            tempObj.setTemplateFileId(temp.getFileId());
            tempObj.setOperatorUserId(this.getSessionUserId());
            srmOkcContractTemplatesServer.updateSrmOkcContractTemplates(tempObj);
            fs.setCustomSaveResult(templateId.toString());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e.getCause());
        } finally {
            fs.close();
            try {
                res.flushBuffer();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }

    /**
     * Description：Lov分页查询合同模版
     *
     * @param
     * @return Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.1       2019/7/24      秦晓钊          创建
     * ==============================================================================
     */
    @POST
    @Path("findContractTemplatesToLov")
    public String findContractTemplatesToLov(@FormParam(PARAMS)
                                                     String params, @FormParam(PAGE_INDEX)
                                                     Integer pageIndex, @FormParam(PAGE_ROWS)
                                                     Integer pageRows) {
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmOkcContractTemplatesEntity_HI_RO> infoList = srmOkcContractTemplatesServer.findContractTemplatesToLov(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        }catch (UtilsException e){
            LOGGER.error("查询合同模版列表异常：", e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同模版列表异常!"+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同模版列表异常：", e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同模版列表失败!"+e.getMessage(), 0, null);
        }
    }

}
