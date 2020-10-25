package saaf.common.fmw.okc.services;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.sun.jersey.api.view.Viewable;

import com.yhg.fastdfs.core.bean.FastDFSFileEntity;
import com.yhg.fastdfs.core.bean.ResutlFileEntity;
import com.yhg.fastdfs.core.utils.FileManagerUtils;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import com.zhuozhengsoft.pageoffice.*;
import com.zhuozhengsoft.pageoffice.wordwriter.DataRegion;
import com.zhuozhengsoft.pageoffice.wordwriter.Table;
import com.zhuozhengsoft.pageoffice.wordwriter.WordDocument;
import org.apache.commons.io.IOUtils;
import org.jodconverter.JodConverter;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.jodconverter.document.DocumentFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafBaseResultFileEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafBaseResultFileEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafUserEmployeesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafBaseResultFile;
import saaf.common.fmw.base.model.inter.ISaafUsers;
import saaf.common.fmw.base.model.inter.ISrmBaseNotifications;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.exception.NotLoginException;

import saaf.common.fmw.okc.model.entities.SrmOkcContractTemplatesEntity_HI;
import saaf.common.fmw.okc.model.entities.SrmOkcContractTextsEntity_HI;
import saaf.common.fmw.okc.model.entities.SrmOkcWatermarksEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.*;
import saaf.common.fmw.okc.model.entities.SrmOkcContractsEntity_HI;
import saaf.common.fmw.okc.model.inter.ISrmOkcContracts;
import saaf.common.fmw.okc.model.inter.ISrmOkcWatermarks;
import saaf.common.fmw.okc.model.inter.server.OfficeManagerServer;
import saaf.common.fmw.okc.utils.WordToPdf;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionHeaders;
import saaf.common.fmw.pon.model.inter.ISrmPonBid;
import saaf.common.fmw.pon.model.inter.ISrmPonBidHeaders;
import saaf.common.fmw.pon.model.inter.ISrmPonBidItemPrices;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierBankEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierContactsEntity_HI_RO;
import saaf.common.fmw.services.CommonAbstractServices;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmOkcContractsService.java
 * Description：合同管理控制类
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date     @Author(Updated By)     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019/6/4      陈庆超          创建
 * <p>
 * ==============================================================================
 */
@Component("srmOkcContractsService")
@Path("/srmOkcContractsService")
public class SrmOkcContractsService extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmOkcContractsService.class);

    private static final String configFilePath = "/fdfs_client.properties";

    @Autowired
    private ISrmOkcContracts srmOkcContractsServer;

    @Autowired
    private ISrmPonAuctionHeaders iSrmPonAuctionHeaders;

    @Autowired
    private ISrmPonBidItemPrices iSrmPonBidItemPrices;

    @Autowired
    private ViewObject<SaafBaseResultFileEntity_HI> saafBaseResultFileDAO_HI;

    @Autowired
    private ISaafBaseResultFile iSaafBaseResultFile;

    @Autowired
    private ISrmPonBidHeaders iSrmPonBidHeaders;

    @Autowired
    private ISrmOkcWatermarks srmOkcWatermarksServer;

    @Autowired
    private ISaafUsers iSaafUsers;

    @Autowired
    private ISrmBaseNotifications iSrmBaseNotifications;

    @Autowired
    private ISrmPonBid iSrmPonBid;

    /**
     * 查询合同的交易方
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSrmOkcContractPartiesList")
    @POST
    public String findSrmOkcContractPartiesList(@FormParam("params")
                                                        String params, @FormParam("pageIndex")
                                                        Integer pageIndex, @FormParam("pageRows")
                                                        Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            List list = srmOkcContractsServer.findSrmOkcContractPartiesList(jsonParam, pageIndex, pageRows);
            return CommonAbstractServices.convertResultJSONObj("S", "查询合同交易方信息成功!", 1, list);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询合同交易方异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同交易方失败："+e.getMessage(), 0, null);
        }catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询合同交易方异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同交易方失败:"+e.getMessage(), 0, null);
        }
    }

    /**
     * 查询合同银行
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("initPartyInfo")
    @POST
    public String initPartyInfo(@FormParam("params")
                                        String params, @FormParam("pageIndex")
                                        Integer pageIndex, @FormParam("pageRows")
                                        Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            Map<String, Object> result = new HashMap<>();
            Pagination<SrmPosSupplierContactsEntity_HI_RO> p = srmOkcContractsServer.findSrmOkcContractPerson(jsonParam, 1, Integer.MAX_VALUE);
            if (p.getData() != null && p.getData().size() == 1) {
                result.put("supplierContactId", p.getData().get(0).getSupplierContactId());
                result.put("contactName", p.getData().get(0).getContactName());
                result.put("mobilePhone", p.getData().get(0).getMobilePhone());
                result.put("emailAddress", p.getData().get(0).getEmailAddress());
                result.put("fixedPhone", p.getData().get(0).getFixedPhone());
                result.put("faxPhone", p.getData().get(0).getFaxPhone());
            }
            Pagination<SrmPosSupplierBankEntity_HI_RO> b = srmOkcContractsServer.findSrmOkcContractBank(jsonParam, 1, Integer.MAX_VALUE);
            if (b.getData() != null && b.getData().size() == 1) {
                result.put("bankAccountId", b.getData().get(0).getBankAccountId());
                result.put("bankName", b.getData().get(0).getBankName());
                result.put("bankAccountNumber", b.getData().get(0).getBankAccountNumber());
                result.put("branchName", b.getData().get(0).getBranchName());
            }
//            Pagination<JSONObject> s = srmOkcContractsServer.findSrmOkcContractSite(jsonParam, 1, Integer.MAX_VALUE);
//            if (s.getData() != null && s.getData().size() == 1) {
//                result.put("supplierSiteId", s.getData().get(0).getInteger("supplierSiteId"));
//                result.put("siteName", s.getData().get(0).getString("siteName"));
//                result.put("detailAddress", s.getData().get(0).getString("detailAddress"));
//            }
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功!", 1, result);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询合同交易方联系人异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同交易方联系人异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同交易方联系人异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同交易方联系人异常："+e.getMessage(), 0, null);
        }
    }
    /**
     * 查询合同交易方联系人
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSrmOkcContractPerson")
    @POST
    public String findSrmOkcContractPerson(@FormParam("params")
                                                   String params, @FormParam("pageIndex")
                                                   Integer pageIndex, @FormParam("pageRows")
                                                   Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination list = srmOkcContractsServer.findSrmOkcContractPerson(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(list);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询合同交易方联系人异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同交易方联系人异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同交易方联系人异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同交易方联系人异常："+e.getMessage(), 0, null);
        }
    }

    /**
     * 查询合同银行
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSrmOkcContractBank")
    @POST
    public String findSrmOkcContractBank(@FormParam("params")
                                                 String params, @FormParam("pageIndex")
                                                 Integer pageIndex, @FormParam("pageRows")
                                                 Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination list = srmOkcContractsServer.findSrmOkcContractBank(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(list);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询合同交易方银行信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同交易方银行信息异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同交易方银行信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同交易方银行信息异常："+e.getMessage(), 0, null);
        }
    }


    /**
     * Description：合同查询供应商地点
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findSrmOkcContractSite")
    @POST
    public String findSrmOkcContractSite(@FormParam("params")
                                                 String params, @FormParam("pageIndex")
                                                 Integer pageIndex, @FormParam("pageRows")
                                                 Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination list = srmOkcContractsServer.findSrmOkcContractSite(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(list);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询合同交易方站点信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同交易方站点信息异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同交易方站点信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同交易方站点信息异常："+e.getMessage(), 0, null);
        }
    }

    /**
     * Description：查询合同标的物列表
     * @param params 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findSrmOkcContractItemsList")
    @POST
    public String findSrmOkcContractItemsList(@FormParam("params")
                                                      String params, @FormParam("pageIndex")
                                                      Integer pageIndex, @FormParam("pageRows")
                                                      Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            List<SrmOkcContractItemsEntity_HI_RO> list = srmOkcContractsServer.findSrmOkcContractItemsList(jsonParam, pageIndex, pageRows);
            return CommonAbstractServices.convertResultJSONObj("S", "查询合同标的物列表成功!", 1, list);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询合同标的物列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同标的物列表异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同标的物列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同标的物列表失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 删除合同的标的物
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("deleteContractItem")
    public String deleteContractItem(@FormParam("params") String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            srmOkcContractsServer.deleteSrmOkcContractItem(paramJSON);
            return CommonAbstractServices.convertResultJSONObj("S", "删除标的物成功!", 0, null);
        }catch (UtilsException e){
            LOGGER.error("删除标的物异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除标的物异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("删除标的物异常：" + e);
            return convertResultJSONObj("E", "删除标的物失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 删除运输方式
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("deleteSrmOkcContractFreightExpense")
    public String deleteSrmOkcContractFreightExpense(@FormParam("params") String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            srmOkcContractsServer.deleteSrmOkcContractFreightExpense(paramJSON);
            return CommonAbstractServices.convertResultJSONObj("S", "删除运输方式成功!", 0, null);
        }catch (UtilsException e){
            LOGGER.error("删除运输方式异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除运输方式异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("删除运输方式异常：" + e);
            return convertResultJSONObj("E", "删除运输方式失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 删除合同付款阶段
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("deleteSrmOkcContractPayment")
    public String deleteSrmOkcContractPayment(@FormParam("params") String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            srmOkcContractsServer.deleteSrmOkcContractPayment(paramJSON);
            return CommonAbstractServices.convertResultJSONObj("S", "删除付款阶段成功!", 0, null);
        }catch (UtilsException e){
            LOGGER.error("删除付款阶段异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除付款阶段异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("删除付款阶段异常：" + e);
            return convertResultJSONObj("E", "删除付款阶段失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 查询合同文本
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSrmOkcContractTextsList")
    @POST
    public String findSrmOkcContractTextsList(@FormParam("params")
                                                      String params, @FormParam("pageIndex")
                                                      Integer pageIndex, @FormParam("pageRows")
                                                      Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            List<SrmOkcContractTextsEntity_HI_RO> list = srmOkcContractsServer.findSrmOkcContractTextsList(jsonParam, pageIndex, pageRows);
            return CommonAbstractServices.convertResultJSONObj("S", "查询合同文本信息成功!", 1, list);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询合同文本信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同文本信息异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同文本信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同文本信息失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 查询运输方式列表
     * @param params
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSrmOkcContractFreightsList")
    @POST
    public String findSrmOkcContractFreightsList(@FormParam("params")
                                                         String params, @FormParam("pageIndex")
                                                         Integer pageIndex, @FormParam("pageRows")
                                                         Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            List<SrmOkcContractFreightsEntity_HI_RO> list = srmOkcContractsServer.findSrmOkcContractFreightsList(jsonParam, pageIndex, pageRows);
            return CommonAbstractServices.convertResultJSONObj("S", "查询运输方式列表成功!", 1, list);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询运输方式列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询运输方式列表异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询运输方式列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询运输方式列表失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 保存合同附件
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("saveSrmOkcContractAttachments")
    @POST
    public String saveSrmOkcContractAttachments(@FormParam("params")
                                                        String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            Object obj = srmOkcContractsServer.saveSrmOkcContractAttachments(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "保存成功!", 1, obj);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("保存合同附件异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存合同附件异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("保存合同附件异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存合同附件失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 查询合同附件列表
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSrmOkcContractAttachmentsList")
    @POST
    public String findSrmOkcContractAttachmentsList(@FormParam("params")
                                                            String params, @FormParam("pageIndex")
                                                            Integer pageIndex, @FormParam("pageRows")
                                                            Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            List<SrmOkcContractAttachmentsEntity_HI_RO> list = srmOkcContractsServer.findSrmOkcContractAttachmentsList(jsonParam, pageIndex, pageRows);
            return CommonAbstractServices.convertResultJSONObj("S", "查询合同附件信息成功!", 1, list);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询合同附件信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同附件信息异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同附件信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同附件信息失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 根据合同ID，查询该合同的付款阶段信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSrmOkcContractPaymentsList")
    @POST
    public String findSrmOkcContractPaymentsList(@FormParam("params")
                                                         String params, @FormParam("pageIndex")
                                                         Integer pageIndex, @FormParam("pageRows")
                                                         Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            List<SrmOkcContractPaymentsEntity_HI_RO> list = srmOkcContractsServer.findSrmOkcContractPaymentsList(jsonParam, pageIndex, pageRows);
            return CommonAbstractServices.convertResultJSONObj("S", "查询付款方式列表成功!", 1, list);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询付款方式列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询付款方式列表异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询付款方式列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询付款方式列表失败："+e.getMessage(), 0, null);
        }

    }

    /**
     * 查询合同采购订单
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSrmOkcContractPOList")
    @POST
    public String findSrmOkcContractPOList(@FormParam("params")
                                                   String params, @FormParam("pageIndex")
                                                   Integer pageIndex, @FormParam("pageRows")
                                                   Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("------jsonParam-----" + jsonParam.toString());
            List<SrmPoHeadersEntity_HI_RO> list = srmOkcContractsServer.findSrmOkcContractPoList(jsonParam, pageIndex, pageRows);
            return CommonAbstractServices.convertResultJSONObj("S", "查询合同采购订单成功!", 1, list);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询合同采购订单异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同采购订单异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同采购订单异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同采购订单失败："+e.getMessage(), 0, null);
        }

    }
    /**
     * 保存合同
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("saveSrmOkcContracts")
    @POST
    public String saveSrmOkcContracts(@FormParam("params")String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            Object obj = srmOkcContractsServer.saveSrmOkcContracts(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "保存合同信息成功!", 1, obj);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("保存合同信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存合同信息异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("保存合同信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存合同信息失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 保存合同列表
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("renewSrmOkcContracts")
    @POST
    public String renewSrmOkcContracts(@FormParam("params")
                                               String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("contractApprovalStatus", "1");
            jsonParam.put("contractStatus", "3");
            Object obj = srmOkcContractsServer.saveRenewSrmOkcContracts(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "保存成功!", 1, obj);

        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("保存合同列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存合同列表异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("保存合同列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 批准合同
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("approveRenewSrmOkcContracts")
    @POST
    public String approveRenewSrmOkcContracts(@FormParam("params")
                                                      String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("contractApprovalStatus", "4");
            jsonParam.put("contractStatus", "1");
            srmOkcContractsServer.updateSrmOkcContractStatus(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "批准成功!", 1, jsonParam);

        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("批准合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "批准合同异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("批准合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "批准失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 驳回合同
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("rejectRenewSrmOkcContracts")
    @POST
    public String rejectRenewSrmOkcContracts(@FormParam("params")
                                                     String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("contractApprovalStatus", "5");
            jsonParam.put("contractStatus", "1");
            srmOkcContractsServer.updateSrmOkcContractStatus(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "驳回成功!", 1, jsonParam);

        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("驳回合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "驳回合同异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("驳回合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "驳回失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 取消合同
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("cancelRenewSrmOkcContracts")
    @POST
    public String cancelRenewSrmOkcContracts(@FormParam("params")
                                                     String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("contractApprovalStatus", "6");
            jsonParam.put("contractStatus", "1");
            srmOkcContractsServer.updateSrmOkcContractStatus(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "取消成功!", 1, jsonParam);

        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("取消合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "取消合同异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("取消合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "取消失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 保存合同
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("terminalSrmOkcContracts")
    @POST
    public String terminalSrmOkcContracts(@FormParam("params")
                                                  String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("contractApprovalStatus", "2");
            Object obj = srmOkcContractsServer.saveSrmOkcContracts(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "保存成功!", 1, obj);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        }catch (UtilsException e){
            LOGGER.error("保存合同列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存合同列表异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("保存合同列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 审批合同
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("approveTerminalSrmOkcContracts")
    @POST
    public String approveTerminalSrmOkcContracts(@FormParam("params")
                                                         String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("contractApprovalStatus", "4");
            jsonParam.put("contractStatus", "8");
            srmOkcContractsServer.updateSrmOkcContractStatus(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "保存成功!", 1, jsonParam);
        }catch (UtilsException e){
            LOGGER.error("处理异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "处理异常："+e.getMessage(), 0, null);
        } catch (NotLoginException e) {
            LOGGER.error("处理异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "处理失败："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("处理异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "处理失败！", 0, null);
        }
    }

    /**
     * 驳回合同
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("rejectTerminalSrmOkcContracts")
    @POST
    public String rejectTerminalSrmOkcContracts(@FormParam("params")String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("contractApprovalStatus", "5");
            jsonParam.put("contractStatus", "1");
            srmOkcContractsServer.updateSrmOkcContractStatus(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "保存成功!", 1, jsonParam);
        } catch (NotLoginException e) {
            LOGGER.error("处理异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "处理失败！", 0, null);
        }catch (UtilsException e){
            LOGGER.error("处理异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "处理异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("处理异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "处理失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 取消合同
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("cancelTerminalSrmOkcContracts")
    @POST
    public String cancelTerminalSrmOkcContracts(@FormParam("params")String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("contractApprovalStatus", "6");
            jsonParam.put("contractStatus", "1");
            srmOkcContractsServer.updateSrmOkcContractStatus(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "保存成功!", 1, jsonParam);
        } catch (NotLoginException e) {
            LOGGER.error("处理异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "处理失败！", 0, null);
        } catch (UtilsException e){
            LOGGER.error("处理异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "处理异常："+e.getMessage(), 0, null);
        }catch (Exception e) {
            LOGGER.error("取消异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "处理失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 提交合同
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("submitSrmOkcContracts")
    @POST
    public String submitSrmOkcContracts(@FormParam("params")String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("contractApprovalStatus", "2");
            jsonParam.put("contractStatus", "2");
            Object obj = srmOkcContractsServer.saveSrmOkcContracts(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "提交成功!", 1, obj);
        } catch (NotLoginException e) {
            LOGGER.error("提交合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "提交失败！", 0, null);
        }catch (UtilsException e){
            LOGGER.error("提交合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "提交合同异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("提交合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "提交失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 批准合同
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("approveSrmOkcContracts")
    @POST
    public String approveSrmOkcContracts(@FormParam("params")
                                                 String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("contractApprovalStatus", "4");
            srmOkcContractsServer.updateSrmOkcContractStatus(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "批准成功！", 1, jsonParam);
        } catch (NotLoginException e) {
            LOGGER.error("批准合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "批准失败！", 0, null);
        }catch (UtilsException e){
            LOGGER.error("批准合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "批准合同异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("批准合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "批准失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 驳回合同
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("rejectSrmOkcContracts")
    @POST
    public String rejectSrmOkcContracts(@FormParam("params")
                                                String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("contractApprovalStatus", "5");
            jsonParam.put("contractStatus", "1");
            srmOkcContractsServer.updateSrmOkcContractStatus(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "驳回成功！", 1, jsonParam);
        } catch (NotLoginException e) {
            LOGGER.error("驳回合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "驳回失败！", 0, null);
        }catch (UtilsException e){
            LOGGER.error("驳回合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "驳回合同异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("驳回合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "驳回失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 取消合同
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("cancelSrmOkcContracts")
    @POST
    public String cancelSrmOkcContracts(@FormParam("params")
                                                String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("contractApprovalStatus", "6");
            jsonParam.put("contractStatus", "1");
            srmOkcContractsServer.updateSrmOkcContractStatus(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "取消成功！", 1, jsonParam);
        } catch (NotLoginException e) {
            LOGGER.error("取消合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "取消失败！", 0, null);
        }catch (UtilsException e){
            LOGGER.error("取消合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "取消合同异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("取消合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "取消失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 发送合同
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("sendConfirmRequest")
    @POST
    public String sendConfirmRequest(@FormParam("params")
                                             String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("contractApprovalStatus", "4");
            jsonParam.put("contractStatus", "5");
            srmOkcContractsServer.updateSrmOkcContractStatus(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "处理成功！", 1, jsonParam);
        } catch (NotLoginException e) {
            LOGGER.error("处理合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "处理失败！", 0, null);
        }catch (UtilsException e){
            LOGGER.error("处理合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "处理合同异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("处理合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "处理失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 查询合同预警
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSrmOkcContractsWarnList")
    @POST
    public String findSrmOkcContractsWarnList(@FormParam("params")
                                                      String params, @FormParam("pageIndex")
                                                      Integer pageIndex, @FormParam("pageRows")
                                                      Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination list = srmOkcContractsServer.findSrmOkcContractsWarnList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(list);
        } catch (NotLoginException e) {
            LOGGER.error("查询合同预警信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "查询合同预警信息失败！", 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询合同预警信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同预警信息异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同预警信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同预警信息失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * Description：查询合同列表
     * @param params 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findSrmOkcContractsList")
    @POST
    public String findSrmOkcContractsList(@FormParam("params")
                                                  String params, @FormParam("pageIndex")
                                                  Integer pageIndex, @FormParam("pageRows")
                                                  Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            if (jsonParam.getInteger("varUserId") > 0) {
                Pagination list = srmOkcContractsServer.findSrmOkcContractsList(jsonParam, pageIndex, pageRows);
                return JSON.toJSONString(list);
            } else {
                return CommonAbstractServices.convertResultJSONObj("E", "查询合同列表失败，请先登录系统！", 0, null);
            }
        } catch (NotLoginException e) {
            LOGGER.error("查询合同列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "查询合同列表失败！", 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询合同列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同列表异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同列表失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 查询合同的补充合同列表
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSrmOkcSubContractsList")
    @POST
    public String findSrmOkcSubContractsList(@FormParam("params")
                                                     String params, @FormParam("pageIndex")
                                                     Integer pageIndex, @FormParam("pageRows")
                                                     Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject p = new JSONObject();
            if (jsonParam.getInteger("contractId") != null) {
                p.put("mainContractId", jsonParam.getInteger("contractId"));
            } else {
                p.put("mainContractId", new Integer(-1));
            }
            Pagination list = srmOkcContractsServer.findSubContractsList(p, pageIndex, Integer.MAX_VALUE);
            return CommonAbstractServices.convertResultJSONObj("S", "查询合同列表成功!", 1, list.getData());
        } catch (NotLoginException e) {
            LOGGER.error("查询合同列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "查询合同列表失败！", 0, null);
        } catch (UtilsException e){
            LOGGER.error("查询合同列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同列表异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同列表异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同列表失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * 初始化合同信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("initSrmOkcContracts")
    @POST
    public String initSrmOkcContracts(@FormParam("params")String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            UserInfoSessionBean userInfoSessionBean = this.getUserSessionBean();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            JSONObject obj = new JSONObject();

            obj.put("createdBy", userInfoSessionBean.getUserId());
            obj.put("createdByName", userInfoSessionBean.getUserFullName());
            obj.put("creationDate", simpleDateFormat.format(new Date()));
            obj.put("lastUpdateDate", simpleDateFormat.format(new Date()));
            obj.put("lastUpdateBy", userInfoSessionBean.getUserId());
            obj.put("lastUpdateByName", userInfoSessionBean.getUserFullName());
            JSONObject s = new JSONObject();
            s.put("userId", userInfoSessionBean.getUserId());
            SaafUserEmployeesEntity_HI_RO employeesEntity_hi_ro = iSaafUsers.findSaafUsersById(s);
            obj.put("handleDepartmentName", employeesEntity_hi_ro.getInstName());
            obj.put("handleDepartmentId", employeesEntity_hi_ro.getInstId());

            obj.put("versionNumber", new Integer(0));
            obj.put("contractStatus", new Integer(1));
            obj.put("contractApprovalStatus", new Integer(1));
            obj.put("mainContractFlag", "Y");

            return CommonAbstractServices.convertResultJSONObj("S", "初始化合同信息成功！", 1, obj);
        } catch (NotLoginException e) {
            LOGGER.error("初始化合同信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "初始化合同信息失败！", 0, null);
        }catch (UtilsException e){
            LOGGER.error("初始化合同信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "初始化合同信息异常："+e.getMessage(), 0, null);
        }  catch (Exception e) {
            LOGGER.error("初始化合同信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "初始化合同信息失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * Description：根据ID查询合同
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findSrmOkcContracts")
    @POST
    public String findSrmOkcContracts(@FormParam("params")String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            //查询合同账号&员工信息
            if (null != jsonParam.get("contractId") && !"".equals(jsonParam.get("contractId"))) {
                SrmOkcContractsEntity_HI_RO obj = srmOkcContractsServer.findSrmOkcContractsById(jsonParam);
                return CommonAbstractServices.convertResultJSONObj("S", "查询合同信息成功！", 1, obj);
            } else {
                return CommonAbstractServices.convertResultJSONObj("E", "查询合同信息参数为空！", 0, null);
            }
        } catch (NotLoginException e) {
            LOGGER.error("查询合同信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "查询合同信息失败！", 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询合同信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同信息异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同信息异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同信息失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * Description：寻源转合同，查询合同信息
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findAuciionContracts")
    @POST
    public String findAuciionContracts(@FormParam("params")String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("auctionHeaderId", jsonParam.get("auctionId"));
            //查询合同账号&员工信息
            if (null != jsonParam.get("auctionId") && !"".equals(jsonParam.get("auctionId"))) {
                SrmPonAuctionHeadersEntity_HI_RO obj = iSrmPonAuctionHeaders.getAuctionHeaderInfo(jsonParam);
                if("LOGISTICS".equals(obj.getItemType())){
                    obj.setContractType("H1");
                }else if("HUMAN_RESOURCES".equals(obj.getItemType())){
                    obj.setContractType("R1");
                }else if("INFORMATION_TECHNOLOGY".equals(obj.getItemType())){
                    obj.setContractType("I1");
                }else if("ENGINEERING".equals(obj.getItemType())){
                    obj.setContractType("W1");
                }
                obj.setBidHeaderId(jsonParam.getInteger("bidHeaderId"));

                jsonParam.put("subsectionFlag", obj.getSubsectionFlag());
                List<SrmPonBidHeadersEntity_HI_RO> supObj = iSrmPonBidHeaders.findBidHeadersSupplierList(jsonParam);
                if(!ObjectUtils.isEmpty(supObj)){
                    obj.setSupplierId(supObj.get(0).getSupplierId());
                    obj.setSupplierName(supObj.get(0).getSupplierName());
                    obj.setSupplierNumber(supObj.get(0).getSupplierNumber());
                    obj.setTaxRateCode(supObj.get(0).getTaxRateCode());
                    obj.setTaxRateName(supObj.get(0).getTaxRateName());
                    if("ENGINEERING".equals(obj.getItemType())){
                        obj.setTotalAmount(supObj.get(0).getTotalProjectCost());
                    }else{
                        obj.setTotalAmount(supObj.get(0).getTotalAccountOffer());
                    }
                }
               // int supplierId = supObj.getSupplierId();

               /* JSONObject itemObj = iSrmPonBidItemPrices.findSrmPonBidItemPricesList(jsonParam);
                JSONArray itemArr = itemObj.getJSONObject("data").getJSONArray("bidItemPricesList");
                JSONArray supItemArr = new JSONArray();
                for (int i = 0; i < itemArr.size(); i++) {
                    JSONObject item = jsonParam.parseObject(itemArr.getString(i));
                    if ("4".equals(item.getString("awardStatus")) && supObj.getSupplierId().equals(item.getInteger("supplierId"))) {
//                        purchaseQuantity        "noTaxPrice" -> "15.000000"   "quantity" -> "20.000"
                        item.put("purchaseQuantity", item.get("quantity"));
                        item.put("nonTaxPrice", item.get("noTaxPrice"));
                        supItemArr.add(item);
                    }
                }*/
                List<SrmPonBidEntity_HI_RO> itemArr = iSrmPonBid.getAuctionBidLineInfo(jsonParam);
                JSONArray supItemArr = new JSONArray();
                for (int i = 0; i < itemArr.size(); i++) {
                    JSONObject item =new JSONObject();
                    item.put("bidLineId",itemArr.get(i).getBidLineId());
                    item.put("bidHeaderId",itemArr.get(i).getBidHeaderId());
                    item.put("auctionHeaderId",itemArr.get(i).getAuctionHeaderId());
                    item.put("auctionLineId",itemArr.get(i).getAuctionLineId());
                    item.put("itemId",itemArr.get(i).getItemId());
                    item.put("itemCode",itemArr.get(i).getItemNumber());
                    item.put("itemName",itemArr.get(i).getItemDescription());
                    item.put("uomCodeName",itemArr.get(i).getUnitOfMeasure());
                    item.put("categoryId",itemArr.get(i).getCategoryId());
                    item.put("categoryCode",itemArr.get(i).getFullCategoryCode());
                    item.put("categoryName",itemArr.get(i).getFullCategoryName());
                    item.put("itemSpec",itemArr.get(i).getSpecification());
                    item.put("purchaseQuantity",itemArr.get(i).getQuantity());
                    item.put("taxRateCode",itemArr.get(i).getTaxRate());
                    item.put("taxPrice",itemArr.get(i).getTaxPrice());
                    item.put("nonTaxPrice",itemArr.get(i).getNoTaxPrice());
                    item.put("contractItemComments",itemArr.get(i).getNotes());
                    item.put("organizationId",obj.getOrganizationId());
                    item.put("instName",obj.getOrganizationName());
                    supItemArr.add(item);
                }
                JSONObject returnObj = new JSONObject();
                returnObj.put("obj", obj);
                //returnObj.put("supObj", supObj);
                returnObj.put("supItemArr", supItemArr);
                return CommonAbstractServices.convertResultJSONObj("S", "查询成功!", 1, returnObj);
            } else {
                return CommonAbstractServices.convertResultJSONObj("E", "查询寻源参数为空!", 0, null);
            }
        } catch (NotLoginException e) {
            LOGGER.error("查询异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "查询失败！", 0, null);
        }catch (UtilsException e){
            LOGGER.error("查询异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败："+e.getMessage(), 0, null);
        }
    }

    /**
     * Description：上传合同附件
     * @param params 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("updateAttachContract")
    @POST
    public String updateAttachContract(@FormParam("params")String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            /*SrmOkcContractsEntity_HI obj = srmOkcContractsServer.loadSrmOkcContractsById(jsonParam.getInteger("contractId"));
            obj.setPartyASignDate(jsonParam.getDate("partyASignDate"));
            obj.setPartyBSignDate(jsonParam.getDate("partyBSignDate"));
            obj.setPartyCSignDate(jsonParam.getDate("partyCSignDate"));
            obj.setContractStatus("3");
            obj.setOperatorUserId(this.getSessionUserId());*/
            Object obj = srmOkcContractsServer.saveSrmOkcContractAttachments(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "上传合同附件成功!", 1, obj);
            //srmOkcContractsServer.updateSrmOkcContractsById(obj);
        } catch (UtilsException e){
            LOGGER.error("查询异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "上传合同附件失败："+e.getMessage(), 0, null);
        }catch (Exception e){
            LOGGER.error("查询异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "上传合同附件失败："+e.getMessage(), 0, null);
        }catch (Throwable e) {
            //e.printStackTrace();
            return CommonAbstractServices.convertResultJSONObj("E", "上传合同附件失败："+e.getMessage(), 0, null);
        }
        //return CommonAbstractServices.convertResultJSONObj("S", "查询合同信息成功!", 0, null);
        // return new Viewable("/WEB-INF/page/empty.jsp",null);
    }

    @Path("generateContract/{contractId}")
    @POST
    public String generateContract(@PathParam("contractId") Integer contractId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        try {
            JSONObject p = this.parseObject("{}");
            p.put("contractId", contractId);
            SrmOkcContractsEntity_HI_RO obj = srmOkcContractsServer.findSrmOkcContractsById(p);
            if (obj == null) {
                return CommonAbstractServices.convertResultJSONObj("E", "合同不存在，转换失败！", 0, null);
            }

            SaafBaseResultFileEntity_HI file = saafBaseResultFileDAO_HI.get(SaafBaseResultFileEntity_HI.class, obj.getAttribute16());
            URL url = new URL(file.getAccessPath());
            InputStream is = url.openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            OfficeManagerServer.startOffice();
            DocumentFormat documentFormat = DefaultDocumentFormatRegistry.DOCX;
            JodConverter.convert(is, true).as(documentFormat).to(out).as(DefaultDocumentFormatRegistry.PDF).execute();
            OfficeManagerServer.stopOffice();

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            if (obj.getWatermarkId() != null) {
                os = new ByteArrayOutputStream();
                SrmOkcWatermarksEntity_HI watermarksEntity = srmOkcWatermarksServer.findSrmOkcWatermarksEntity_HI(obj.getWatermarkId());
                SaafBaseResultFileEntity_HI watermarksFile = saafBaseResultFileDAO_HI.get(SaafBaseResultFileEntity_HI.class, watermarksEntity.getWatermarkFileId());
                PdfReader reader = new PdfReader(out.toByteArray());
                PdfStamper stamp = new PdfStamper(reader, os);
                PdfGState gs1 = new PdfGState();
                gs1.setFillOpacity(0.1f);// 透明度设置
                URL bkImgurl = new URL(watermarksFile.getAccessPath());
//        Image image = Image.getInstance(IOUtils.toByteArray(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/images/sielogo.png")));
                Image image = Image.getInstance(IOUtils.toByteArray(bkImgurl.openStream()));
                image.setRotation(-20);// 旋转 弧度
                image.setRotationDegrees(45);// 旋转 角度
                image.setAbsolutePosition(50, 150);// 坐标
//        image.scaleAbsolute(300, 300);// 自定义大小
                image.scalePercent(watermarksEntity.getAttribute16());// 依照比例缩放

                int n = reader.getNumberOfPages();
                for (int i = 1; i <= n; i++) {
                    PdfContentByte pdfContentByte = stamp.getOverContent(i);
                    pdfContentByte.setGState(gs1);
                    pdfContentByte.addImage(image);
                }

                stamp.close();
                reader.close();
            } else {
                os = out;
            }

            FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
            FastDFSFileEntity DFSFileEntity = new FastDFSFileEntity(obj.getContractName(), os.toByteArray(), "pdf");
            ResutlFileEntity resutlFileEntity = fileManager.uploadFile2FastDFS(DFSFileEntity);
            SaafBaseResultFileEntity_HI row = iSaafBaseResultFile.saveResult(resutlFileEntity, this.getSessionUserId());

            List<SrmOkcContractTextsEntity_HI_RO> list = srmOkcContractsServer.findSrmOkcContractTextsList(p, 1, Integer.MAX_VALUE);
            if (list != null && list.size() == 0) {
                p.put("contractId", contractId);
                p.put("textFileId", row.getFileId());
                p.put("textFileName", obj.getContractName());
                p.put("varUserId", this.getSessionUserId());
                srmOkcContractsServer.saveSrmOkcContractTexts(p);
            } else {
                SrmOkcContractTextsEntity_HI_RO srmOkcContractTextsEntity_hi_ro = list.get(0);
                SrmOkcContractTextsEntity_HI srmOkcContractTextsEntity_hi = new SrmOkcContractTextsEntity_HI();
                srmOkcContractTextsEntity_hi.setContractTextId(srmOkcContractTextsEntity_hi_ro.getContractTextId());
                srmOkcContractTextsEntity_hi.setContractId(srmOkcContractTextsEntity_hi_ro.getContractId());
                srmOkcContractTextsEntity_hi.setTextFileId(row.getFileId());
                srmOkcContractTextsEntity_hi.setTextFileName(srmOkcContractTextsEntity_hi_ro.getTextFileName());
                srmOkcContractTextsEntity_hi.setTemplateModifyFlag(srmOkcContractTextsEntity_hi_ro.getTemplateModifyFlag());
                srmOkcContractTextsEntity_hi.setVersionNum(srmOkcContractTextsEntity_hi_ro.getVersionNum());
                srmOkcContractTextsEntity_hi.setOperatorUserId(this.getSessionUserId());
                srmOkcContractsServer.saveSrmOkcContractTexts(srmOkcContractTextsEntity_hi);
            }
        }catch (UtilsException e){
            LOGGER.error("合同转换失败：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "合同转换失败："+e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            return CommonAbstractServices.convertResultJSONObj("E", "合同转换失败："+e.getMessage(), 0, null);
        }
        return CommonAbstractServices.convertResultJSONObj("S", "合同转换成功！", 0, null);
    }

    @Path("openEdit/{contractId}")
    @GET
    public Viewable openEdit(@PathParam("contractId") String contractId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        try {
            JSONObject params = this.parseObject("{}");
            params.put("contractId", contractId);
            SrmOkcContractsEntity_HI_RO obj = srmOkcContractsServer.findSrmOkcContractsById(params);
            if (obj == null) {
                request.setAttribute("editor", "No Access right");
                return new Viewable("/WEB-INF/page/edit.jsp", null);
            }

            SaafBaseResultFileEntity_HI file = saafBaseResultFileDAO_HI.get(SaafBaseResultFileEntity_HI.class, obj.getAttribute16());
            request.setAttribute("contractId", contractId);
            request.setAttribute("tokenCode", request.getSession(false).getId());

            PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
            poCtrl.setServerPage(request.getContextPath() + "/poserver.zz");
            WordDocument doc = new WordDocument();
            doc.openDataTag("@@contactCode@@").setValue(obj.getContractCode());
            doc.openDataTag("@@contactName@@").setValue(obj.getContractName());
            doc.openDataTag("@@partyA@@").setValue(obj.getPartyAName());
            doc.openDataTag("@@partyB@@").setValue(obj.getPartyBName());
            doc.openDataTag("@@partyC@@").setValue(obj.getPartyCName() == null ? "" : obj.getPartyCName());
            doc.openDataTag("@@payTerm@@").setValue(obj.getPaymentTermName());
            doc.openDataTag("@@totalAmount@@").setValue(String.valueOf(obj.getTotalAmount()));

            //开始写入物料行，表格的书签必须是PO_开头的
            DataRegion dataRegion = doc.openDataRegion("PO_Items");
            if (dataRegion != null) {
                Table table = dataRegion.openTable(1);
                params.put("varUserId", this.getSessionUserId());
                List<SrmOkcContractItemsEntity_HI_RO> itemsList = srmOkcContractsServer.findSrmOkcContractItemsList(params, 1, 10000);
                SrmOkcContractItemsEntity_HI_RO itemEntity = null;
                for (int i = 0; i < itemsList.size(); i++) {
                    itemEntity = itemsList.get(i);
                    //判断行是否已存在
                    if (table.openRow(i + 2) == null) {
                        table.insertRowAfter(table.openCellRC(i + 1, 8));
                    }
//                    table.openCellRC(i + 2, 1).setValue("A1");
//                    table.openCellRC(i + 2, 2).setValue("A2");
//                    table.openCellRC(i + 2, 3).setValue("A3");
//                    table.openCellRC(i + 2, 4).setValue("A4");
//                    table.openCellRC(i + 2, 5).setValue("A5");
//                    table.openCellRC(i + 2, 6).setValue("A6");
//                    table.openCellRC(i + 2, 7).setValue("A7");
//                    table.openCellRC(i + 2, 8).setValue("A8");
                    //序号
                    table.openCellRC(i + 2, 1).setValue(String.valueOf(i + 1));
                    //物料名称
                    if (itemEntity.getItemName() != null) {
                        table.openCellRC(i + 2, 2).setValue(itemEntity.getItemName());
                    } else {
                        table.openCellRC(i + 2, 2).setValue("物料");
                    }
                    //规格
                    table.openCellRC(i + 2, 3).setValue("规格");
                    //单位
                    if (itemEntity.getUomCodeName() != null) {
                        table.openCellRC(i + 2, 4).setValue(itemEntity.getUomCodeName());
                    } else {
                        table.openCellRC(i + 2, 4).setValue("单位");
                    }
                    //采购数量
                    if (itemEntity.getPurchaseQuantity() != null) {
                        table.openCellRC(i + 2, 5).setValue(itemEntity.getPurchaseQuantity().toString());
                    } else {
                        table.openCellRC(i + 2, 5).setValue("0");
                    }
                    //单价
                    if (itemEntity.getTaxPrice() != null) {
                        table.openCellRC(i + 2, 6).setValue(itemEntity.getTaxPrice().toString());
                    } else {
                        table.openCellRC(i + 2, 6).setValue("0");
                    }
                    //金额
                    if (itemEntity.getPurchaseQuantity() != null && itemEntity.getTaxPrice() != null) {
                        table.openCellRC(i + 2, 7).setValue(itemEntity.getPurchaseQuantity().multiply(itemEntity.getPurchaseQuantity()).toString());
                    } else {
                        table.openCellRC(i + 2, 7).setValue("0");
                    }
                    //备注
                    if (itemEntity.getContractItemComments() != null) {
                        table.openCellRC(i + 2, 8).setValue(itemEntity.getContractItemComments());
                    } else {
                        table.openCellRC(i + 2, 8).setValue("备注");
                    }
                }
            } else {
                LOGGER.info("没有找到物料表格，写入失败！");
                throw new UtilsException("没有找到物料表格，写入失败！");
            }
            request.setAttribute("poCtrl", poCtrl);
            poCtrl.setWriter(doc);
            poCtrl.setSaveFilePage(request.getContextPath() + "/restServer/srmOkcContractsService/saveEdit/" + contractId);
            poCtrl.webOpen(request.getContextPath() + "/restServer/srmOkcContractsService/openDoc/" + file.getFileId(), OpenModeType.docNormalEdit, this.getUserName());
            String editor = poCtrl.getHtmlCode("PageOfficeCtrl1");
            poCtrl.setTagId("PageOfficeCtrl1");
            request.setAttribute("Tokencode", request.getSession(false).getId());
            request.setAttribute("editor", editor);
        }catch (UtilsException e){
            LOGGER.error("失败：" + e);
            request.setAttribute("editor", e.getMessage());
            return new Viewable("/WEB-INF/page/edit.jsp", e.getMessage());
        }  catch (Exception e) {
            //e.printStackTrace();
            request.setAttribute("editor", e.getMessage());
            return new Viewable("/WEB-INF/page/edit.jsp", null);
        }
        return new Viewable("/WEB-INF/page/edit.jsp", null);
    }

    @Path("openDoc/{fileId}")
    @GET
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public HttpServletResponse openDoc(@PathParam("fileId") Integer fileId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SaafBaseResultFileEntity_HI file = saafBaseResultFileDAO_HI.get(SaafBaseResultFileEntity_HI.class, fileId);
        InputStream is = null;
        try {
            URL url = new URL(file.getAccessPath());
            is = url.openStream();
            response.reset();
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
                    throw new RuntimeException("关闭字节流异常");
                }
            }
        }
        return response;
    }

    @Path("contractqrcode/{contractId}")
    @GET
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public HttpServletResponse generateCodeToShow(@PathParam("contractId") String contractId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        JSONObject p = new JSONObject();
        String codeValue = "none";
        p.put("contractId", contractId);
        try {
            SrmOkcContractsEntity_HI_RO obj = srmOkcContractsServer.findSrmOkcContractsById(p);
            codeValue = obj.getContractCode();
        } catch (Exception e) {
            //e.printStackTrace();
            throw new RuntimeException("查询合同异常！");
        }

        //定义位图矩阵BitMatrix
        BitMatrix matrix = null;
        int height = 64;
        int width = 64;

        //定义二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");//设置编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//设置容错等级
        hints.put(EncodeHintType.MARGIN, 2);//设置边距默认是5
        try {
            // 使用code_128格式进行编码生成100*25的条形码
            MultiFormatWriter writer = new MultiFormatWriter();
            matrix = writer.encode(codeValue, BarcodeFormat.QR_CODE, width, height, hints);
            //matrix = writer.encode(code,BarcodeFormat.EAN_13, width, height, null);
        } catch (WriterException e) {
            //e.printStackTrace();
        }
        //将位图矩阵BitMatrix保存为图片
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            ImageIO.write(MatrixToImageWriter.toBufferedImage(matrix), "png",
                    out);
            byte[] outByte = out.toByteArray();
            response.getOutputStream().write(outByte);
            response.setContentType("image/png");
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            //e.printStackTrace();
            throw new RuntimeException("将位图矩阵保存为图片异常！");

        }
        return response;
    }

    @Path("saveEdit/{contractId}")
    @POST
    public void saveEdit(@PathParam("contractId") Integer contractId, @Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            JSONObject p = this.parseObject("{}");
            p.put("contractId", contractId);
            SrmOkcContractsEntity_HI_RO obj = srmOkcContractsServer.findSrmOkcContractsById(p);
            if (obj == null) {
                res.getOutputStream().println("no access right");
                return;
            }
            FileSaver fs = new FileSaver(req, res);
            FileManagerUtils fileManager = FileManagerUtils.getInstance(configFilePath);
            FastDFSFileEntity DFSFileEntity = new FastDFSFileEntity("file", fs.getFileBytes(), "doc");
            ResutlFileEntity resutlFileEntity = fileManager.uploadFile2FastDFS(DFSFileEntity);
            SaafBaseResultFileEntity_HI row = iSaafBaseResultFile.saveResult(resutlFileEntity, this.getSessionUserId());
            srmOkcContractsServer.updateEditFile(contractId, row.getFileId(), this.getSessionUserId());
            fs.close();
            res.flushBuffer();
        } catch (Throwable e) {
            //e.printStackTrace();
            throw new RuntimeException("保存异常！");
        }
        // return new Viewable("/WEB-INF/page/empty.jsp",null);
    }

    /**
     * 根据ID查询合同模板
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("getTemplateFileInfo")
    @POST
    public String getTemplateFileInfo(@FormParam("params") String params) {
        try {
            // JSONObject jsonParam = this.parseObject(params);
            JSONObject jsonParam = JSON.parseObject(params);
            if (null != jsonParam.get("templateId") && !"".equals(jsonParam.get("templateId"))) {
                SaafBaseResultFileEntity_HI_RO saafBaseResultFileEntity_hi_ro = srmOkcContractsServer.findTemplateFileById(jsonParam.getInteger("templateId"));
                return CommonAbstractServices.convertResultJSONObj("S", "查询合同信息成功!", 1, saafBaseResultFileEntity_hi_ro);
            } else {
                return CommonAbstractServices.convertResultJSONObj("E", "查询合同信息参数为空!", 0, null);
            }
        }catch (Exception e) {
            LOGGER.error("查询合同信息异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同信息失败：" + e.getMessage(), 0, null);
        }
    }

    @POST
    @Path("sendContractNotification")
    public String sendContractNotification(@FormParam("params") String params) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            // iSrmBaseNotifications.insertSrmBaseNotifications()
            iSrmBaseNotifications.insertSrmBaseNotifications("合同到期预警", "您好！合同：" + params + "付款阶段" + params + "预计付款日期" + params + ",请悉知"
                    , paramJSON.getInteger("varUserId"), "srm_okc_contracts", paramJSON.getInteger("contractId"), "contractId", "home.SrmOkcContractsDetail", paramJSON.getInteger("varUserId"));
            return CommonAbstractServices.convertResultJSONObj("S", "发送成功!", 0, null);
        }catch (UtilsException e) {
            LOGGER.error("发送异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "发送异常：" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("发送异常：" + e.getMessage());
            return convertResultJSONObj("E", "发送失败！" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：合同确认
     * @param params 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("updateConfirmContract")
    @POST
    public String updateConfirmContract(@FormParam("params")String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            SrmOkcContractsEntity_HI obj = srmOkcContractsServer.loadSrmOkcContractsById(jsonParam.getInteger("contractId"));
            obj.setPartyASignDate(jsonParam.getDate("partyASignDate"));
            obj.setPartyBSignDate(jsonParam.getDate("partyBSignDate"));
            obj.setPartyCSignDate(jsonParam.getDate("partyCSignDate"));
            obj.setContractStatus("3");
            obj.setOperatorUserId(jsonParam.getInteger("varUserId"));
            srmOkcContractsServer.updateSrmOkcContractsById(obj);
        }catch (UtilsException e) {
            LOGGER.error("查询合同信息失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同信息失败：" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询合同信息失败：" + e.getMessage());
            return convertResultJSONObj("E", "查询合同信息失败！" + e.getMessage(), 0, null);
        }
        return CommonAbstractServices.convertResultJSONObj("S", "查询合同信息成功!", 0, null);
        // return new Viewable("/WEB-INF/page/empty.jsp",null);
    }

    /**
     * 删除合同附件
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("deleteSrmOkcContractAttachments")
    public String deleteSrmOkcContractAttachments(@FormParam("params") String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            srmOkcContractsServer.deleteSrmOkcContractAttachments(paramJSON);
            return CommonAbstractServices.convertResultJSONObj("S", "删除合同附件成功!", 0, null);
        }catch (UtilsException e){
            LOGGER.error("删除合同附件异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除合同附件异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("删除合同附件异常：" + e);
            return convertResultJSONObj("E", "删除合同附件失败："+e.getMessage(), 0, null);
        }
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
    @Path("/openPdf/{textFileId}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public HttpServletResponse openPdf(@PathParam("textFileId")
                                                              Integer textFileId) {
        /*SrmOkcContractTemplatesEntity_HI tempObj = srmOkcContractTemplatesServer.findSrmOkcContractTemplatesEntity_HI(templateId);
        SaafBaseResultFileEntity_HI file = saafBaseResultFileDAO_HI.get(SaafBaseResultFileEntity_HI.class, tempObj.getTemplateFileId());
        SrmOkcWatermarksEntity_HI watermarksEntity = srmOkcWatermarksServer.findSrmOkcWatermarksEntity_HI(tempObj.getWatermarkId());
        SaafBaseResultFileEntity_HI watermarksFile = saafBaseResultFileDAO_HI.get(SaafBaseResultFileEntity_HI.class, watermarksEntity.getWatermarkFileId());*/
        InputStream is = null;
        try {
           /* URL url = new URL(file.getAccessPath());
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
            WordToPdf.imageWatermark(pbos, tempPath, watermarksFile.getAccessPath(), watermarksEntity.getAttribute16());*/
            URL url = new URL("http://10.10.90.82:80/group1/M00/00/19/CgpaUl8i3AKACnTEAAOBwhQl7Qk938.pdf");
            is = url.openStream();
            response.reset();
            response.setContentType("application/pdf;charset=utf-8");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + new String(("CgpaUl8i3AKACnTEAAOBwhQl7Qk938.pdf").getBytes(), "ISO-8859-1"));
            OutputStream outputStream = response.getOutputStream();
            OfficeManagerServer.startOffice();
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
     * 撤回合同
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("revokeSrmOkcContracts")
    @POST
    public String revokeSrmOkcContracts(@FormParam("params")
                                                 String params) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            jsonParam.put("contractStatus", "1");
            jsonParam.put("contractApprovalStatus", "1");
            srmOkcContractsServer.updateSrmOkcContractStatus(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "撤回成功！", 1, jsonParam);
        } catch (NotLoginException e) {
            LOGGER.error("撤回合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", "撤回失败！", 0, null);
        }catch (UtilsException e){
            LOGGER.error("撤回合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "撤回合同异常："+e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("撤回合同异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "撤回失败："+e.getMessage(), 0, null);
        }
    }

}
