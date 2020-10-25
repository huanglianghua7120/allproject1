package saaf.common.fmw.po.services;


import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.yhg.base.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.po.model.entities.readonly.SrmPoNoticeEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoNoticeLineEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISaafUsers;
import saaf.common.fmw.po.model.inter.ISrmPoNotice;
import saaf.common.fmw.services.CommonAbstractServices;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

/**
 * Project Name：SrmPoNoticeServices
 * Company Name：SIE
 * Program Name：
 * Description：送货通知
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoNoticeServices")
@Path("/srmNoticeServlet")
public class SrmPoNoticeServices extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoNoticeServices.class);

    @Autowired
    private ISrmPoNotice srmPoNotice;

    @Autowired
    private ISaafUsers iSaafUsers;


    /**
     * Description：查询送货通知(1周)
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findNoticeInfo")
    @POST
    public String findNoticeList(@FormParam("params")
                                         String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            if ("N".equals(jsonParam.getString("show"))) {
                return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", 0, null);
            }
            List<SrmPoNoticeEntity_HI_RO> noticeList = srmPoNotice.findNoticeList(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", noticeList.size(), noticeList);
        } catch (NotLoginException e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：根据CODE查询送货通知
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findNoticeCodeLov")
    @POST
    public String findNoticeCodeLov(@FormParam("params")
                                            String params, @FormParam("pageIndex")
                                            Integer pageIndex, @FormParam("pageRows")
                                            Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPoNoticeEntity_HI_RO> noticeList = srmPoNotice.findNoticeCodeLov(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(noticeList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：查询送货通知信息-内部
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
    @Path("findNoticeList")
    @POST
    public String findNoticeList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
                                 @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            if ("EX".equals(jsonParam.getString("varPlatformCode"))) { //是供应商
                jsonParam.put("supplierId", jsonParam.getIntValue("varSupplierId"));
            }
            Pagination<SrmPoNoticeEntity_HI_RO> noticeList = srmPoNotice.findNoticeListsNew(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(noticeList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：查询送货通知总数
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findNoticeListSum")
    @POST
    public String findNoticeListSum(@FormParam("params")
                                            String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            SrmPoNoticeEntity_HI_RO noticeList = srmPoNotice.findNoticeListSum(jsonParam);
            return JSON.toJSONString(noticeList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：查询送货通知行信息
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findNoticeLineListById")
    @POST
    public String findNoticeLineListById(@FormParam("params")
                                                 String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPoNoticeLineEntity_HI_RO> noticeList = srmPoNotice.findNoticeLineList(jsonParam);
            return JSON.toJSONString(noticeList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：根据ID查询送货通知
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findDeliveryByNoticeId")
    @POST
    public String findDeliveryByNoticeId(@FormParam("params")
                                                 String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            List<SrmPoNoticeEntity_HI_RO> noticeList = srmPoNotice.findDeliveryByNoticeId(jsonParam);
            return JSON.toJSONString(noticeList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：关闭送货单
     * @param params 关闭条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("updateColseNotice")
    @POST
    public String updateColseNotice(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoNotice.updateColseNotice(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }



    /**
     * Description：关闭未收货的送货单
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("updateNoticeStatus")
    @POST
    public String updateNoticeStatus(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoNotice.updateNoticeStatus(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：保存通知
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */
    @Path("saveNotice")
    @POST
    public String saveNotice(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoNotice.saveNotice(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        }catch (UtilsException e){
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,e.getMessage(),0,null);
        }catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：保存送货通知行-供应商
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * accepterName  通知接收人  VARCHAR2  N
     * uomCode  采购单位  VARCHAR2  N
     * firstFeedbackRecodeDate  第一次反馈记录时间  DATE  N
     * allotLineId  调拨计划差异行ID  NUMBER  N
     * noticeLineNumber  通知单行号  NUMBER  N
     * safeStock  安全库存  NUMBER  N
     * serialNo  流水号  VARCHAR2  N
     * maxStock  最大库存量  NUMBER  N
     * nowStock  现有量  NUMBER  N
     * itemPlanType  物料计划方式  VARCHAR2  N
     * lineId  送货通知单行ID  NUMBER  Y
     * poNoticeId  送货通知单ID  NUMBER  Y
     * poHeaderId  采购订单ID  NUMBER  N
     * poLineId  采购订单行ID  NUMBER  N
     * noticeDeliveryDate  通知送货日期  DATE  N
     * noticeDeliveryQty  通知送货数量  NUMBER  N
     * onWayQty  在途数量（已创建送货单数量）  NUMBER  N
     * lineComments  送货备注  VARCHAR2  N
     * lineStatus  行状态  VARCHAR2  N
     * originalDeliveryDate  原通知送货日期  DATE  N
     * originalDeliveryQty  原通知送货数量  NUMBER  N
     * feedbackAdjustDate  反馈调整日期  DATE  N
     * feedbackAdjustQty  反馈调整数量  NUMBER  N
     * feedbackStatus  反馈状态  VARCHAR2  N
     * feedbackResult  反馈结果  VARCHAR2  N
     * rejectReason  供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * lineId  送货通知单行ID  NUMBER  Y
     * poNoticeId  送货通知单ID  NUMBER  Y
     * poHeaderId  采购订单ID  NUMBER  N
     * poLineId  采购订单行ID  NUMBER  N
     * noticeDeliveryDate  通知送货日期  DATE  N
     * noticeDeliveryQty  通知送货数量  NUMBER  N
     * onWayQty  在途数量（已创建送货单数量）  NUMBER  N
     * lineComments  送货备注  VARCHAR2  N
     * lineStatus  行状态  VARCHAR2  N
     * originalDeliveryDate  原通知送货日期  DATE  N
     * originalDeliveryQty  原通知送货数量  NUMBER  N
     * feedbackAdjustDate  反馈调整日期  DATE  N
     * feedbackAdjustQty  反馈调整数量  NUMBER  N
     * feedbackStatus  反馈状态  VARCHAR2  N
     * feedbackResult  反馈结果  VARCHAR2  N
     * rejectReason  供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */
    @Path("saveNoticeLineForSupplier")
    @POST
    public String saveNoticeLineForSupplier(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoNotice.saveNoticeLineForSupplier(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
           LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }


    /**
     * Description：采购订单lov
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
    @Path("findNoticeLov")
    @POST
    public String findNoticeLov(@FormParam("params")
                                        String params, @FormParam("pageIndex")
                                        Integer pageIndex, @FormParam("pageRows")
                                        Integer pageRows) {

        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPoNoticeEntity_HI_RO> noticeLov = srmPoNotice.findNoticeLov(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(noticeLov);
        } catch (Exception e) {
           LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }

    }


    /**
     * Description：供应商导出送货通知(已废弃)
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findSupplyNoticeExport")
    @POST
    public String findSupplyNoticeExport(@FormParam("params")
                                                 String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            if ("N".equals(jsonParam.getString("show"))) {
                return CommonAbstractServices.convertResultJSONObj("S", "", 0, null);
            }
            List<SrmPoNoticeEntity_HI_RO> noticeList = srmPoNotice.findSupplyExport(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功", noticeList.size(), noticeList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：根据订单保存送货通知行信息
     * @param params 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("savePoNoticeLine")
    @POST
    public String savePoNoticeLine(@FormParam("params")
                                           String params) {
        LOGGER.error("saveNoticeLine-->根据订单保存送货通知行信息");
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoNotice.saveNoticeLine(jsonParams);
            //request.getRequestDispatcher("home.noticeDetail").forward(request, response);;
            return CommonAbstractServices.convertResultJSONObj(
                    jsondata.getString("status"), jsondata.getString("msg"),
                    jsondata.getInteger("count"), jsondata.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：根据订单保存送货通知行信息
     * @param params 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("saveSrmPoNotice")
    @POST
    public String saveSrmPoNotice(@FormParam("params") String params) {
        LOGGER.info("saveSrmPoNotice-->保存送货通知信息");
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoNotice.saveSrmPoNotice(jsonParams);
            // request.getRequestDispatcher("home.noticeDetail").forward(request,// response);
            return CommonAbstractServices.convertResultJSONObj(
                    jsondata.getString("status"), jsondata.getString("msg"),
                    jsondata.getInteger("count"), jsondata.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：查询送货通知行信息
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
    @POST
    @Path("findNoticeLineInfo")
    public String findNoticeLineInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
                                     @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            jsonParams.put("supplierId", jsonParams.getIntValue("varSupplierId"));
            jsonParams.put("status", "APPROVED"); // 供应商只能查询已审批的单据
            if (null == pageIndex) {
                pageIndex = 1;
            }
            if (null == pageRows) {
                pageRows = 30;
            }
            Pagination<SrmPoNoticeLineEntity_HI_RO> page = srmPoNotice.findNoticeLineInfo(jsonParams, pageIndex,
                    pageRows);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSONObject.toJSONString(page);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：根据id查询送货通知表
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findSrmPoNoticeInfo")
    @POST
    public String findSrmPoNoticeInfo(@FormParam("params")
                                              String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            Integer poNoticeId = jsonParam.getInteger("poNoticeId");
            if (poNoticeId == null || "".equals(poNoticeId)) {
                return convertResultJSONObj("E", "查询失败", 0, null);
            }
            List<SrmPoNoticeEntity_HI_RO> srmPoNoticeEntity = srmPoNotice.findSrmPoNoticeInfo(poNoticeId);
            jsonParam.put("data", srmPoNoticeEntity);
            jsonParam.put("status", "S");
            jsonParam.put("msg", "查询成功");
            return jsonParam.toJSONString();
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }


    /**
     * Description：删除送货通知行
     * @param params 删除参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("deleteNoticeLineInfo")
    @POST
    public String deleteNoticeLineInfo(@FormParam("params")
                                               String params) {
        LOGGER.info("删除信息，参数：" + params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoNotice.deleteNoticeLineInfo(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
           LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：更新送货通知状态
     * @param params 更新条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("updateSrmPoNotice")
    @POST
    public String updateSrmPoNotice(@FormParam("params") String params) {
        LOGGER.info("修改送货通知表参数：" + params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoNotice.updateSrmPoNotice(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(
                    jsondata.getString("status"), jsondata.getString("msg"),
                    jsondata.getInteger("count"), jsondata.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：送货通知接口（数据输入,用于外部访问的接口）需要提供用户和密码
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("saveNoticeInfoForExternal")
    @Produces("application/json")
    public String saveNoticeInfoForExternal(@FormParam(PARAMS) String params, @FormParam("userName") String userName,
                                            @FormParam("password") String password) {
        LOGGER.info("参数params：" + params.toString() + "，参数userName：" + userName + "，password：" + password);
        if (StringUtils.isBlank(params)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可操作！", 0, null);
        }
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "登录异常，用户名或密码为空！", 0, null);
        }
        boolean flag = iSaafUsers.userLoginByDatabase(userName, password); // 验证用户登录
        if (!flag) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "登录异常，请输入正确的用户名和密码！", 0, null);
        }
        SaafUsersEntity_HI userEntity = iSaafUsers.userByDatabase(userName, password);
        Integer userId = null;
        if (null != userEntity) {
            userId = userEntity.getUserId();
        }
        try {
            JSONObject jsonParams = this.parseObjectExternal(params); // 调用外部转换json方法
            JSONObject jsondata = srmPoNotice.saveNoticeInfoForExternal(jsonParams, userId);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG),
                    jsondata.getInteger(COUNT), jsondata.getString(DATA));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：送货通知接口（数据输出,用于外部访问的接口）
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("findNoticeInfoForExternal")
    @Produces("application/json")
    public String findNoticeInfoForExternal(@FormParam(PARAMS) String params, @FormParam("userName") String userName,
                                            @FormParam("password") String password) {
        LOGGER.info("参数params：" + params.toString() + "，参数userName：" + userName + "，password：" + password);
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "登录异常，用户名或密码为空！", 0, null);
        }
        boolean flag = iSaafUsers.userLoginByDatabase(userName, password); // 验证用户登录
        if (!flag) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "登录异常，请输入正确的用户名和密码！", 0, null);
        }
        try {
            JSONObject jsonParams = this.parseObjectExternal(params); // 调用外部转换json方法
            Map<String, Object> map = srmPoNotice.findNoticeInfoForExternal(jsonParams);
            return JSON.toJSONString(map);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：删除拟定的送货通知单
     * @param params 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("deleteNoticeInfo")
    @POST
    public String deleteNoticeInfo(@FormParam("params") String params) {
        LOGGER.info("删除信息,参数：" + params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoNotice.deleteNoticeInfo(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(
                    jsondata.getString("status"), jsondata.getString("msg"),
                    jsondata.getInteger("count"), jsondata.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

}
