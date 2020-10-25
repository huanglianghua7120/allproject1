package saaf.common.fmw.pos.services;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosLocaleReviewsEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosQualificationInfoEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierSitesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.IQualificationInfo;
import saaf.common.fmw.pos.model.inter.ISrmPosLocaleReviews;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierSites;
import saaf.common.fmw.services.CommonAbstractServices;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：现场考察
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
@Component("srmPosLocaleReviewsService")
@Path("/srmPosLocaleReviewsService")
public class SrmPosLocaleReviewsService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosLocaleReviewsService.class);
    @Autowired
    private ISrmPosLocaleReviews srmPosLocaleReviewsServer;

    @Autowired
    private ISrmPosSupplierSites srmPosSupplierSitesServer;

    @Autowired
    private IQualificationInfo srmPosQualificationInfoServer;

    /**
     * 分页查询现场考察数据
     *
     * @param params
     * @param curIndex
     * @param pageSize
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmPosLocaleReviewsInfoList")
    public String findSrmPosLocaleReviewsInfoList(@FormParam("params") String params,
                                                  @FormParam("pageIndex") @DefaultValue("1") Integer curIndex,
                                                  @FormParam("pageRows") @DefaultValue("10") Integer pageSize) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = JSON.parseObject(params);
            String resultStr = JSONObject.toJSONString(
                    srmPosLocaleReviewsServer.findSrmPosLocaleReviewsInfoList(paramJSON, curIndex, pageSize));
            LOGGER.info(resultStr);
            return resultStr;
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return convertResultJSONObj(ERROR_STATUS, "查询失败！", 0, null);
        }
    }

    /**
     * 查询现场考察信息
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSrmPosLocaleReviewsInfo")
    @POST
    public String findSrmPosLocaleReviewsInfo(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Integer localeReviewId = jsonParam.getInteger("localeReviewId");
            if (localeReviewId == null || localeReviewId.equals("")) {
                return convertResultJSONObj("E", "请检查localeReviewId参数", 0, null);
            }
            List<SrmPosLocaleReviewsEntity_HI_RO> localeReviewsInfo = srmPosLocaleReviewsServer
                    .findSrmPosLocaleReviewsInfo(localeReviewId);
            jsonParam.put("localeReviewsInfo", localeReviewsInfo);
            jsonParam.put("status", "S");
            jsonParam.put("msg", "查询成功");
            return jsonParam.toJSONString();
        } catch (Exception e) {
            LOGGER.error("查询现场考察信息失败！", e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return convertResultJSONObj("E", "查询现场考察信息失败!" + e, 0, null);
        }
    }

    /**
     * 查询供应商地点,本方法属于现场考察
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSupplierSiteOfReview")
    public String findSupplierSiteOfReview(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            List<SrmPosSupplierSitesEntity_HI_RO> LookupCodelist = srmPosSupplierSitesServer.findSupplierSiteOfReview(jsonParams);
            LOGGER.info("--->>findSupplierSiteOfReview-->>参数：" + params + "查询供应商地点成功！");
            return CommonAbstractServices.convertResultJSONObj("S", "查询供应商地点成功！", LookupCodelist.size(), LookupCodelist);
        } catch (Exception e) {
            LOGGER.error("查询供应商地点失败" + e, e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询供应商地点失败！", 0, null);
        }
    }

    /**
     * 查询现场考察品类
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmReviewCategories")
    public String findSrmReviewCategories(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            List<SrmPosQualificationInfoEntity_HI_RO> cateList = srmPosQualificationInfoServer.findSrmReviewCategories(jsonParams);
            LOGGER.info("--->>findSrmReviewCategories-->>参数：" + params + "查询考察品类成功！");
            return CommonAbstractServices.convertResultJSONObj("S", "查询考察品类成功！", cateList.size(), cateList);
        } catch (Exception e) {
            LOGGER.error("查询考察品类失败" + e, e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询考察品类失败!" + e, 0, null);
        }
    }

    /**
     * Description：保存或更新现场考察数据
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * localeReviewId  现场考察ID  NUMBER  Y
     * localeReviewNumber  现场考察  VARCHAR2  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
     * qualificationId  资质审查ID  NUMBER  N
     * localeReviewStatus  现场考察状态(POS_APPROVAL_STATUS)  VARCHAR2  N
     * localeReviewResult  现场考察结果(POS_EXAMINE_RESULT)  VARCHAR2  N
     * reviewDate  考察时间  DATE  N
     * reviewMember  考察小组成员  VARCHAR2  N
     * supplierMember  供方成员  VARCHAR2  N
     * supplyFlag  是否已供货(Y/N)  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * fileId  现场考察相关附件  NUMBER  N
     * approvedBy  (备用)审批人  NUMBER  N
     * approvedDate  审批时间  DATE  N
     * localeReviewId  现场考察ID  NUMBER  Y
     * localeReviewNumber  现场考察  VARCHAR2  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
     * qualificationId  资质审查ID  NUMBER  N
     * investigationPlanLinesId  考察计划行ID，关联表:srm_pos_investigation_plan_lines  NUMBER  N
     * localeReviewStatus  现场考察状态(POS_APPROVAL_STATUS)  VARCHAR2  N
     * localeReviewResult  现场考察结果(POS_EXAMINE_RESULT)  VARCHAR2  N
     * reviewDate  考察时间  DATE  N
     * reviewMember  考察小组成员  VARCHAR2  N
     * supplierMember  供方成员  VARCHAR2  N
     * supplyFlag  是否已供货(Y/N)  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * fileId  现场考察相关附件  NUMBER  N
     * approvedBy  (备用)审批人  NUMBER  N
     * approvedDate  审批时间  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Path("saveLocaleReviewsInfo")
    @POST
    public String saveLocaleReviewsInfo(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPosLocaleReviewsServer.saveLocaleReviewsInfo(jsonParams);
            return convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.get(DATA));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return convertResultJSONObj(ERROR_STATUS, "保存失败!" + e, 0, null);
        }
    }

    /**
     * 根据ID删除现场考察数据
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteSrmPosLocaleReviewsInfo")
    @POST
    public String deleteSrmPosLocaleReviewsInfo(@FormParam("params") String params) {
        LOGGER.info("参数：" + params.toString());
        if (StringUtils.isBlank(params)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可删除！", 0, null);
        }
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPosLocaleReviewsServer.deleteSrmPosLocaleReviewsInfo(jsonParams);
            return convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT),
                    jsondata.get(DATA));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return convertResultJSONObj(ERROR_STATUS, "删除失败!" + e, 0, null);
        }
    }

    /**
     * 根据ID更新现场考察数据
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("updateSrmPosLocaleReviewsInfo")
    @POST
    public String updateSrmPosLocaleReviewsInfo(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPosLocaleReviewsServer.updateSrmPosLocaleReviewsInfo(jsonParams);

            return convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT),
                    jsondata.get(DATA));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return convertResultJSONObj(ERROR_STATUS, "更新失败!" + e, 0, null);
        }
    }


    /**
     * 审批现场考察信息
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("approvalLocaleReviewInfo")
    @POST
    public String updateApprovalLocaleReviewInfo(@FormParam("params")
                                                         String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPosLocaleReviewsServer.updateApprovalLocaleReviewInfo(jsonParam);

            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (Exception e) {
            LOGGER.error("审批失败！" + e, e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "审批失败!" + e, 0, null);
        }
    }

    /**
     * 驳回现场考察信息
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("rejectLocaleReviewInfo")
    @POST
    public String updateRejectLocaleReviewInfo(@FormParam("params")
                                                       String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPosLocaleReviewsServer.updateRejectLocaleReviewInfo(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (Exception e) {
            LOGGER.error("驳回失败！" + e, e);
            if (e instanceof UtilsException) {
                LOGGER.error("服务异常:" + e);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "驳回失败!" + e, 0, null);
        }
    }


}
