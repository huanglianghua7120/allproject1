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
import saaf.common.fmw.pos.model.entities.readonly.SrmPosBlacklistsEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.IBlacklists;
import saaf.common.fmw.services.CommonAbstractServices;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商黑名单
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     hgq             创建
 * ===========================================================================
 */
@Path("/srmPosBlacklistsServices")
@Component
public class SrmPosBlacklistsServices extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(QualificationInfoServices.class);

    @Autowired
    private IBlacklists srmPosBlacklistsServer;

    public SrmPosBlacklistsServices() {
        super();
    }

    /**
     * 查询列表
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findBlacklistsList")
    @POST
    public String findBlacklistsList(@FormParam("params") String params,
                                     @FormParam("pageIndex") Integer pageIndex,
                                     @FormParam("pageRows") Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            System.out
                    .println("==========================================>>>>>>");
            LOGGER.info("------jsonParam-----" + jsonParam.toString());
            Pagination<SrmPosBlacklistsEntity_HI_RO> infoList = srmPosBlacklistsServer
                    .findBlacklistsList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询列表失败!"
                    + e, 0, null);
        }
    }

    /**
     * 黑名单自动解除JOB(需求不明确暂时停止开发)
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("updateBlacklistsJob")
    @POST
    public String updateBlacklistsJob(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = new JSONObject();
            if (null != params && !"".equals(params)) {
                jsonParam = JSON.parseObject(params);
            }
            srmPosBlacklistsServer.updateBlacklistsJob(jsonParam);
            return JSON.toJSONString(null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询列表失败!"
                    + e, 0, null);
        }
    }

    /**
     * Description：保存黑名单
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * blacklistId  黑名单ID  NUMBER  Y
     * blacklistNumber  黑名单单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:SRM_POS_SUPPLIER_INFO  NUMBER  Y
     * blacklistStatus  黑名单单据状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * permanentFlag  永久黑名单标识(Y/N)  VARCHAR2  N
     * relieveDate  解除时间  DATE  N
     * blacklistFileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedDate  审批时间  DATE  N
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * rejectReason  审批意见  VARCHAR2  N
     * blacklistId  黑名单ID  NUMBER  Y
     * blacklistNumber  黑名单单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:SRM_POS_SUPPLIER_INFO  NUMBER  Y
     * blacklistStatus  黑名单单据状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * permanentFlag  永久黑名单标识(Y/N)  VARCHAR2  N
     * relieveDate  解除时间  DATE  N
     * blacklistFileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedDate  审批时间  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Path("saveBlacklists")
    @POST
    public String saveBlacklists(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPosBlacklistsServer
                    .saveBlacklists(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(
                    posJson.getString("status"), posJson.getString("msg"),
                    posJson.getInteger("count"), posJson.get("data"));
        } catch (Exception e) {
            LOGGER.error("保存失败！" + e, e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E",
                    "保存失败!" + e, 0, null);
        }
    }

    /**
     * 删除
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteBlacklists")
    @POST
    public String deleteBlacklists(@FormParam("params") String params) {
        LOGGER.info("删除信息，参数：" + params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPosBlacklistsServer.deleteBlacklists(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(
                    jsondata.getString("status"), jsondata.getString("msg"),
                    jsondata.getInteger("count"), jsondata.get("data"));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败!", 0, null);
        }
    }

    /**
     * 查询
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findBlacklists")
    @POST
    public String findBlacklists(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            Integer blacklistId = jsonParam.getInteger("blacklistId");
            if (blacklistId == null || blacklistId.equals("")) {
                return convertResultJSONObj("E", "请检查blacklistId参数", 0, null);
            }
            List<SrmPosBlacklistsEntity_HI_RO> parameters = srmPosBlacklistsServer.findBlacklists(blacklistId);
            jsonParam.put("parameters", parameters);
            jsonParam.put("status", "S");
            jsonParam.put("msg", "查询成功");
            return jsonParam.toJSONString();
        } catch (Exception e) {
            LOGGER.error("查询失败！", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return convertResultJSONObj("E", "查询失败!" + e, 0, null);
        }
    }

    /**
     * Description：提交黑名单
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * blacklistId  黑名单ID  NUMBER  Y
     * blacklistNumber  黑名单单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:SRM_POS_SUPPLIER_INFO  NUMBER  Y
     * blacklistStatus  黑名单单据状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * permanentFlag  永久黑名单标识(Y/N)  VARCHAR2  N
     * relieveDate  解除时间  DATE  N
     * blacklistFileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedDate  审批时间  DATE  N
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * rejectReason  审批意见  VARCHAR2  N
     * blacklistId  黑名单ID  NUMBER  Y
     * blacklistNumber  黑名单单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:SRM_POS_SUPPLIER_INFO  NUMBER  Y
     * blacklistStatus  黑名单单据状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * permanentFlag  永久黑名单标识(Y/N)  VARCHAR2  N
     * relieveDate  解除时间  DATE  N
     * blacklistFileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedDate  审批时间  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Path("saveApprovalBlacklists")
    @POST
    public String saveApprovalBlacklists(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPosBlacklistsServer.saveApprovalBlacklists(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(
                    posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (Exception e) {
            LOGGER.error("提交失败！" + e, e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "提交失败!", 0, null);
        }
    }

    /**
     * 审批
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("updateApprovalBlacklists")
    @POST
    public String updateApprovalBlacklists(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPosBlacklistsServer.updateApprovalBlacklists(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(
                    posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (Exception e) {
            LOGGER.error("审批失败！" + e.getMessage());
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "审批失败!", 0, null);
        }
    }

    /**
     * 驳回
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("updateRejectBlacklists")
    @POST
    public String updateRejectBlacklists(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPosBlacklistsServer.updateRejectBlacklists(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(
                    posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (Exception e) {
            LOGGER.error("驳回失败！", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "驳回失败!", 0, null);
        }
    }
}
