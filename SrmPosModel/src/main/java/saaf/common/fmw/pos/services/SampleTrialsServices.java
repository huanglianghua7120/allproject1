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
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSampleTrialsEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISampleTrials;
import saaf.common.fmw.services.CommonAbstractServices;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：样品试验
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     liuwenjun             创建
 * ===========================================================================
 */
@Path("/srmSampleTrialsServices")
@Component
public class SampleTrialsServices extends CommonAbstractServices {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleTrialsServices.class);
	
	@Autowired
	private ISampleTrials srmSampleTrialsServer;
	
	public SampleTrialsServices(){
		super();
	}
	
	/**   
     * 查询样品试验列表
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
    @Path("findSampleTrialsList")
    @POST
    public String findSampleTrialsList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("==========================================>>>>>>");
            LOGGER.info("------jsonParam-----" + jsonParam.toString());
            Pagination<SrmPosSampleTrialsEntity_HI_RO> infoList = srmSampleTrialsServer.findSampleTrialsList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "查询样品试验列表失败!" + e, 0, null);
        }
    }
    
    /**
     *查询样品试验信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSampleTrials")
    public String findSampleTrials(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            Integer trialId = jsonParam.getInteger("trialId");
            if (trialId == null || trialId.equals("")) {
                return convertResultJSONObj("E", "请检查trialId参数", 0, null);
            }
            List<SrmPosSampleTrialsEntity_HI_RO> sampleTrials = srmSampleTrialsServer.findSampleTrials(trialId);
            jsonParam.put("sampleTrials", sampleTrials);
            jsonParam.put("status", "S");
            jsonParam.put("msg", "查询成功");
            return jsonParam.toJSONString();
        } catch (Exception e) {
            LOGGER.error("查询样品试验信息失败！", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return convertResultJSONObj("E", "查询样品试验信息失败!" + e, 0, null);
        }
    }

    /**
     * Description：保存样品试验
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * trialId  样品试验ID  NUMBER  Y
     * trialsNumber  样品试验编码  VARCHAR2  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  N
     * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
     * qualificationId  资质审查ID，关联表:srm_pos_qualification_info  NUMBER  N
     * trialsStatus  状态(POS_APPROVAL_STATUS)  VARCHAR2  N
     * trialsResult  样品试验结果(POS_EXAMINE_RESULT)  VARCHAR2  N
     * sampleFileId  样品试验相关附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedBy  审批人  NUMBER  N
     * approvedDate  审批时间  DATE  N
     * orgId  业务实体ID，关联表saaf_institution  NUMBER  N
     * organizationId  库粗组织ID，关联表saaf_institution  NUMBER  N
     * ekpNumber  EKP认证流程单号  VARCHAR2  N
     * theme  主题  VARCHAR2  N
     * trialId  样品试验ID  NUMBER  Y
     * trialsNumber  样品试验编码  VARCHAR2  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  N
     * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
     * qualificationId  资质审查ID，关联表:srm_pos_qualification_info  NUMBER  N
     * trialsStatus  状态(POS_APPROVAL_STATUS)  VARCHAR2  N
     * trialsResult  样品试验结果(POS_EXAMINE_RESULT)  VARCHAR2  N
     * sampleFileId  样品试验相关附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedBy  审批人  NUMBER  N
     * approvedDate  审批时间  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Path("saveSampleTrials")
    @POST
    public String saveSampleTrials(@FormParam("params")
        String params) {
    	LOGGER.info("保存样品试验信息，参数：" + params.toString());
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmSampleTrialsServer.saveOrUpdateOrSubmitSampleTrials(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (Exception e) {
            LOGGER.error("保存样品试验失败！" + e,e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "保存样品试验失败!" + e, 0, null);
        }
    }

    /**
     * 删除样品试验
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("deleteSampleTrials")
    @POST
    public String deleteSampleTrials(@FormParam("params")
        String params) {
    	LOGGER.info("删除样品试验信息，参数：" + params.toString());
    	try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmSampleTrialsServer.deleteSampleTrials(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        }catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "删除样品试验失败!", 0, null);
        }
    }
    
    
    
    
    
    /**
     * 提交样品试验
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("saveSubmissionSample")
    @POST
    public String saveSubmissionSample(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmSampleTrialsServer.saveOrUpdateOrSubmitSampleTrials(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }catch (Exception e) {
            LOGGER.error("提交样品试验失败！" + e,e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "提交样品试验失败!" + e, 0, null);
        }
    }
    
    /**
     * 审批样品试验
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("updateApprovalSample")
    @POST
    public String updateApprovalSample(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmSampleTrialsServer.updateApprovalSample(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (Exception e) {
            LOGGER.error("审批样品试验失败！" + e,e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "审批样品试验失败!" + e, 0, null);
        }
    }
    
    /**
     * 驳回样品试验
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("updateRejectSample")
    @POST
    public String updateRejectSample(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmSampleTrialsServer.updateRejectSample(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (Exception e) {
            LOGGER.error("驳回样品试验失败！" + e,e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "驳回样品试验失败!" + e, 0, null);
        }
    }
    
    /**
     *查询样品试验品类信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Path("findSampleCategoryInfo")
    @POST
    public String findSampleCategoryInfo(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = JSONObject.parseObject(params);
            List<SrmPosQualificationInfoEntity_HI_RO> cateList = srmSampleTrialsServer.findSampleCategoryInfo(jsonParam);
            LOGGER.info("--->>findSampleCategoryInfo-->>参数：" + params + "查询样品试验成功！");
            return CommonAbstractServices.convertResultJSONObj("S", "查询样品试验成功！", cateList.size(), cateList);
        } catch (Exception e) {
            LOGGER.error("查询样品试验品类信息失败！", e);
            if (e instanceof UtilsException) {
                return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
            }
            return convertResultJSONObj("E", "查询样品试验品类信息失败!" + e, 0, null);
        }
    }
}
