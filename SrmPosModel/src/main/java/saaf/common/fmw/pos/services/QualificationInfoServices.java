package saaf.common.fmw.pos.services;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosQualificationInfoEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.IQualificationInfo;
import saaf.common.fmw.services.CommonAbstractServices;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：资质审查类
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019-04-15     liuwenjun             创建
 * ===========================================================================
 */
@Path("/srmQualificationInfoServices")
@Component
public class QualificationInfoServices extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(QualificationInfoServices.class);

    @Autowired
    private IQualificationInfo srmQualificationInfoServer;

    public QualificationInfoServices() {
        super();
    }

    /**
     * 查询资质审查列表
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findQualificationInfoList")
    @POST
    public String findQualificationInfoList(@FormParam("params")
                                                    String params, @FormParam("pageIndex")
                                                    Integer pageIndex, @FormParam("pageRows")
                                                    Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("------jsonParam------" + jsonParam.toString());
            Pagination<SrmPosQualificationInfoEntity_HI_RO> infoList = srmQualificationInfoServer.findQualificationInfoList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("资质审查列表异常：" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询资质审查列表失败！", 0, null);
        }
    }

    /**
     * Description：保存或提交资质审查
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * qualificationId  资质审查ID  NUMBER  Y
     * qualificationNumber  资质审查单号  VARCHAR2  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * supplierAddrId  (废弃)地址ID，关联表:srm_pos_supplier_addrs  NUMBER  N
     * orgId  (废弃)业务实体ID,关联表:srm_base_orgs  NUMBER  N
     * enterpriseType  (废弃)企业性质(POS_ENTERPRISE_TYPE)  VARCHAR2  N
     * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
     * qualificationStatus  资质审查状态(POS_APPROVAL_STATUS)  VARCHAR2  N
     * qualificationResult  资质审查结果(POS_EXAMINE_RESULT)  VARCHAR2  N
     * needOnsiteInspection  (废弃)是否需要现场考察  VARCHAR2  N
     * inspectionResult  (废弃)现场考察是否通过  VARCHAR2  N
     * needSampleTrial  (废弃)是否需要样品试验  VARCHAR2  N
     * sampleTrialResult  (废弃)样品试验是否通过  VARCHAR2  N
     * reasonNoInvestigation  废弃)不考察原因  VARCHAR2  N
     * temporaryQualifiedDate  废弃)临时合格至  DATE  N
     * qualificationFileId  资质审查相关附件  NUMBER  N
     * approveBy  (备用)审批人  NUMBER  N
     * approveDate  审批时间  DATE  N
     * description  说明  VARCHAR2  N
     * qualityAgreement  签订质量协议  VARCHAR2  N
     * procurementAgreement  签订采购协议  VARCHAR2  N
     * oaNum  oa审批编号  VARCHAR2  N
     * processId    NUMBER  N
     * needSampleTrial  (废弃)是否需要样品试验  VARCHAR2  N
     * sampleTrialResult  (废弃)样品试验是否通过  VARCHAR2  N
     * reasonNoInvestigation  废弃)不考察原因  VARCHAR2  N
     * temporaryQualifiedDate  废弃)临时合格至  DATE  N
     * qualificationFileId  资质审查相关附件  NUMBER  N
     * approveBy  (备用)审批人  NUMBER  N
     * approveDate  审批时间  DATE  N
     * description  说明  VARCHAR2  N
     * reportAppendixFileId  现场考察报告附件  NUMBER  N
     * projectReportFileId  样板工程报告附件  NUMBER  N
     * qualificationId  资质审查ID  NUMBER  Y
     * qualificationNumber  资质审查单号  VARCHAR2  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * supplierAddrId  (废弃)地址ID，关联表:srm_pos_supplier_addrs  NUMBER  N
     * orgId  (废弃)业务实体ID,关联表:srm_base_orgs  NUMBER  N
     * enterpriseType  (废弃)企业性质(POS_ENTERPRISE_TYPE)  VARCHAR2  N
     * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
     * qualificationStatus  资质审查状态(POS_APPROVAL_STATUS)  VARCHAR2  N
     * qualificationResult  资质审查结果(POS_EXAMINE_RESULT)  VARCHAR2  N
     * needOnsiteInspection  (废弃)是否需要现场考察  VARCHAR2  N
     * inspectionResult  (废弃)现场考察是否通过  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Path("saveQualificationInfo")
    @POST
    public String saveQualificationInfo(@FormParam("params")
                                                String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmQualificationInfoServer.saveQualificationInfo(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (Exception e) {
            LOGGER.error("保存资质审查失败！" + e, e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", e.getCause().getMessage(), 0, null);
        }
    }

    /**
     * 删除资质审查
     *
     * @param params
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteQualificationInfo")
    @POST
    public String deleteQualificationInfo(@FormParam("params")
                                                  String params) {
        LOGGER.info("删除资质审查信息，参数：" + params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmQualificationInfoServer.deleteQualificationInfo(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (Exception e) {
            LOGGER.error("--->>删除资质审查失败！参数：" + params + ",异常：" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "删除资质审查失败！", 0, null);
        }
    }

    /**
     * 查询供应商LOV
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSupplierLov")
    @POST
    public String findSupplierLov(@FormParam("params")
                                          String params, @FormParam("pageIndex")
                                          Integer pageIndex, @FormParam("pageRows")
                                          Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            if (jsonParam.getString("varPlatformCode") != null && "EX".equals(jsonParam.getString("varPlatformCode"))) { //是供应商查询
                jsonParam.put("supplier_id", jsonParam.getIntValue("varSupplierId"));
            }
            Pagination<SrmPosQualificationInfoEntity_HI_RO> supplierlov = srmQualificationInfoServer.findSupplierName(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(supplierlov);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询供应商LOV失败!" + e, 0, null);
        }
    }

    /**
     * 查询供应商LOV
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSupplierLovForQualif")
    @POST
    public String findSupplierLovForQualif(@FormParam("params")
                                                   String params, @FormParam("pageIndex")
                                                   Integer pageIndex, @FormParam("pageRows")
                                                   Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            if (jsonParam.getString("varPlatformCode") != null && "EX".equals(jsonParam.getString("varPlatformCode"))) { //是供应商查询
                jsonParam.put("supplier_id", jsonParam.getIntValue("varSupplierId"));
            }
            Pagination<SrmPosQualificationInfoEntity_HI_RO> supplierlov = srmQualificationInfoServer.findSupplierNameForQualif(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(supplierlov);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询供应商LOV失败！", 0, null);
        }
    }


    /**
     * 查询资质审查单号LOV
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findQualificationLov")
    @POST
    public String findQualificationLov(@FormParam("params")
                                               String params, @FormParam("pageIndex")
                                               Integer pageIndex, @FormParam("pageRows")
                                               Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPosQualificationInfoEntity_HI_RO> qualificationlov = srmQualificationInfoServer.findQualificationNumber(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(qualificationlov);
        } catch (Exception e) {
            LOGGER.error("查询资质审查单号LOV失败！" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询资质审查单号LOV失败!" + e, 0, null);
        }
    }

    /**
     * 查询资质审查单号LOV
     * 现场考察使用
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findQualificationLovForReviews")
    @POST
    public String findQualificationLovForReviews(@FormParam("params")
                                                         String params, @FormParam("pageIndex")
                                                         Integer pageIndex, @FormParam("pageRows")
                                                         Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPosQualificationInfoEntity_HI_RO> qualificationlov = srmQualificationInfoServer.findQualificationNumberForReviews(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(qualificationlov);
        } catch (Exception e) {
            LOGGER.error("查询资质审查单号LOV失败！" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询资质审查单号LOV失败!" + e, 0, null);
        }
    }

    /**
     * 查询资质审查信息
     *
     * @param params
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findQualificationInfo")
    @POST
    public String findQualificationInfo(@FormParam("params")
                                                String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Integer qualificationId = jsonParam.getInteger("qualificationId");
            if (qualificationId == null || qualificationId.equals("")) {
                return convertResultJSONObj("E", "请检查qualificationId参数", 0, null);
            }
            List<SrmPosQualificationInfoEntity_HI_RO> qualificationInfo = srmQualificationInfoServer.findQualificationInfo(qualificationId);
            jsonParam.put("qualificationInfo", qualificationInfo);
            jsonParam.put("status", "S");
            jsonParam.put("msg", "查询成功");
            return jsonParam.toJSONString();
        } catch (Exception e) {
            LOGGER.error("查询资质审查信息失败！", e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return convertResultJSONObj("E", "查询资质审查信息失败!" + e, 0, null);
        }
    }

    /**
     * 提交资质审查
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
//    @Path("saveSubmissionApproval")
//    @POST
//    public String saveSubmissionApproval(@FormParam("params")
//        String params) {
//        try {
//            JSONObject jsonParam = this.parseObject(params);
//            JSONObject posJson = srmQualificationInfoServer.saveSubmissionApproval(jsonParam);
//            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
//        } catch (Exception e) {
//            LOGGER.error("提交资质审查失败！" + e,e);
//            return CommonAbstractServices.convertResultJSONObj("E", "提交失败:" + e.getMessage(), 0, null);
//        }
//    }

    /**
     * 审批资质审查
     *
     * @param params
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("updateApprovalQualification")
    @POST
    public String updateApprovalQualification(@FormParam("params")
                                                      String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmQualificationInfoServer.updateApprovalQualification(jsonParam);
            /*srmQualificationInfoServer.saveEkpStatus();
            return "aaaaaaa";*/
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (Exception e) {
            LOGGER.error("审批资质审查失败！" + e, e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "审批资质审查失败!" + e, 0, null);
        }
    }

    /**
     * 驳回资质审查
     *
     * @param params
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("updateRejectQualification")
    @POST
    public String updateRejectQualification(@FormParam("params")
                                                    String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmQualificationInfoServer.updateRejectQualification(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (Exception e) {
            LOGGER.error("驳回资质审查失败！" + e, e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "驳回资质审查失败!" + e, 0, null);
        }
    }

    /**
     * 保存ekpStatus
     *
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-05-24     谢晓霞             创建
     * =======================================================================
     */
    public void saveEkpStatus() {
        try {
            srmQualificationInfoServer.saveEkpStatus();
        } catch (Exception e) {
            if (e instanceof UtilsException) {
                LOGGER.error("供应商引入定时查询EKP状态服务异常:" + e);
            }
            LOGGER.error("供应商引入定时查询EKP状态服务异常:" + e);
        }
    }

}
