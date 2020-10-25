package saaf.common.fmw.okc.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractTermsEntity_HI_RO;
import saaf.common.fmw.okc.model.inter.ISrmOkcContractTerms;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmOkcContractTermsService.java
 * Description：合同条款控制类
 *
 * Update History
 * ==============================================================================
 *  Version    Date     @Author(Updated By)     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019/6/4      欧阳岛          创建

 * ==============================================================================
 */
@Component("srmOkcContractTermsService")
@Path("/srmOkcContractTermsService")
public class SrmOkcContractTermsService extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmOkcContractTermsService.class);

    @Autowired
    private ISrmOkcContractTerms srmOkcContractTermsServer;

    public SrmOkcContractTermsService() {
        super();
    }

    /**
     * Description：合同条款分页查询列表
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmOkcContractTermsList")
    public String findSrmOkcContractTermsList(@FormParam(PARAMS)
                                                      String params, @FormParam(PAGE_INDEX)
                                                      Integer pageIndex, @FormParam(PAGE_ROWS)
                                                      Integer pageRows) {
        try {
            JSONObject paramJSON = JSON.parseObject(params);
            Pagination<SrmOkcContractTermsEntity_HI_RO> infoList = srmOkcContractTermsServer.findSrmOkcContractTermsList(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("查询合同条款列表异常：", e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询合同条款列表失败!" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：保存合同条款
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @Path("saveSrmOkcContractTerms")
    @POST
    public String saveSrmOkcContractTerms(@FormParam("params")
                                                  String params) {
        LOGGER.debug("保存合同条款信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmOkcContractTermsServer.saveSrmOkcContractTerms(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (UtilsException e) {
            LOGGER.error("保存合同条款失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "保存合同条款失败：" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("保存合同条款失败！", e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存合同条款失败!" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：提交合同条款
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @Path("submitSrmOkcContractTerms")
    @POST
    public String submitSrmOkcContractTerms(@FormParam("params")
                                                    String params) {
        LOGGER.debug("提交合同条款信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmOkcContractTermsServer.doSubmitSrmOkcContractTerms(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (UtilsException e) {
            LOGGER.error("提交合同条款失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "提交合同条款失败：" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("提交合同条款失败！", e);
            return CommonAbstractServices.convertResultJSONObj("E", "提交合同条款失败!" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：审批合同条款
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @Path("approvalSrmOkcContractTerms")
    @POST
    public String approvalSrmOkcContractTerms(@FormParam("params")
                                                      String params) {
        LOGGER.debug("审批合同条款信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmOkcContractTermsServer.doApprovalSrmOkcContractTerms(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (UtilsException e) {
            LOGGER.error("审批合同条款失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "审批合同条款失败：" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("审批合同条款失败！", e);
            return CommonAbstractServices.convertResultJSONObj("E", "审批合同条款失败!" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：驳回合同条款
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @Path("rejectSrmOkcContractTerms")
    @POST
    public String rejectSrmOkcContractTerms(@FormParam("params")
                                                    String params) {
        LOGGER.debug("驳回合同条款信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmOkcContractTermsServer.doRejectSrmOkcContractTerms(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (UtilsException e) {
            LOGGER.error("驳回合同条款失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "驳回合同条款失败：" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("驳回合同条款失败！", e);
            return CommonAbstractServices.convertResultJSONObj("E", "驳回合同条款失败!" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：变更合同条款版本
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @Path("changeSrmOkcContractTerms")
    @POST
    public String changeSrmOkcContractTerms(@FormParam("params")
                                                    String params) {
        LOGGER.debug("更新合同条款信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmOkcContractTermsServer.doChangeSrmOkcContractTerms(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (UtilsException e) {
            LOGGER.error("更新合同条款失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "更新合同条款失败：" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("更新合同条款失败！", e);
            return CommonAbstractServices.convertResultJSONObj("E", "更新合同条款失败!" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：删除合同条款
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @Path("deleteSrmOkcContractTerms")
    @POST
    public String deleteSrmOkcContractTerms(@FormParam("params")
                                                    String params) {
        LOGGER.debug("删除合同条款信息，参数：{}", params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmOkcContractTermsServer.deleteSrmOkcContractTerms(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (UtilsException e) {
            LOGGER.error("删除合同条款失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "删除合同条款失败：" + e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("删除合同条款失败！", e);
            return CommonAbstractServices.convertResultJSONObj("E", "删除合同条款失败!" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：查询单条合同条款记录
     * @param 
     * @return 
     *
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      欧阳岛          创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmOkcContractTerms")
    public String findSrmOkcContractTerms(@FormParam("params")
                                                  String params) {
        LOGGER.debug("查询合同条款信息，参数：{}", params);
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            JSONObject resultJson = srmOkcContractTermsServer.findSrmOkcContractTerms(jsonParam);
            return resultJson.toJSONString();
        } catch (Exception e) {
            LOGGER.error("查询合同条款信息失败！", e);
            return convertResultJSONObj("E", "查询合同条款信息失败!" + e.getMessage(), 0, null);
        }
    }

}
