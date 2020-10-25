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
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierGradeEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierGrade;
import saaf.common.fmw.services.CommonAbstractServices;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商等级
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Path("/srmPosSupplierGradeService")
@Component
public class SrmPosSupplierGradeService extends CommonAbstractServices {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SrmPosSupplierGradeService.class);

	@Autowired
	private ISrmPosSupplierGrade srmPosSupplierGradeServer;

	public SrmPosSupplierGradeService() {
		super();
	}
	
	/**
	 * 查询供应商级别管理列表
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
	@Path("findSupplierGradeList")
	@POST
	public String findSupplierGradeList(@FormParam("params") String params,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageRows") Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			LOGGER.info("------jsonParam-----" + jsonParam.toString());
			Pagination<SrmPosSupplierGradeEntity_HI_RO> infoList = srmPosSupplierGradeServer.findSupplierGradeList(jsonParam, pageIndex, pageRows);
			return JSON.toJSONString(infoList);
		} catch (Exception e) {
			LOGGER.error("查询列表异常：" + e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询列表失败！", 0, null);
		}
	}
	
	/**
	 * 查询供应商级别信息
	 * 
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("findSupplierGradeInfo")
	@POST
	public String findSupplierGradeInfo(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = JSONObject.parseObject(params);
			Integer gradeId = jsonParam.getInteger("gradeId");
			if (gradeId == null || gradeId.equals("")) {
				return convertResultJSONObj("E", "请检查gradeId参数", 0, null);
			}
			List<SrmPosSupplierGradeEntity_HI_RO> gradeList = srmPosSupplierGradeServer
					.findSupplierGradeInfo(jsonParam);
			return JSON.toJSONString(gradeList);
		} catch (Exception e) {
			LOGGER.error("查询失败！", e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}


	/**
	 * Description：保存和提交供应商级别信息
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * gradeId  供应商级别头ID  NUMBER  Y
	 * gradeCode  供应商级别编号  VARCHAR2  Y
	 * gradeStatus  供应商级别状态，快码  VARCHAR2  Y
	 * evaluatePeriod  评定维度，快码：SPM_TEMPLATE_FREQUENCY  VARCHAR2  Y
	 * evaluateStartDate  评价开始时间  DATE  N
	 * evaluateEndDate  评价结束时间  DATE  N
	 * approvalEmployeeId  审核人  NUMBER  N
	 * approvalDate  审核通过时间  DATE  N
	 * fileId  附件ID  NUMBER  N
	 * gradeNote  备注  VARCHAR2  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */
	@Path("saveSupplierGrade")
	@POST
	public String saveSupplierGrade(@FormParam("params") String params) {
		LOGGER.info("保存和提交,参数：" + params.toString());
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject posJson = srmPosSupplierGradeServer
					.saveSupplierGrade(jsonParam);
			return CommonAbstractServices.convertResultJSONObj(
					posJson.getString("status"), posJson.getString("msg"),
					posJson.getInteger("count"), posJson.get("data"));
		} catch (Exception e) {
			LOGGER.error("保存失败！" + e, e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return CommonAbstractServices.convertResultJSONObj("E",
					"保存失败!" + e, 0, null);
		}
	}
	
	
	/**
	 * 删除供应商级别信息
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("deleteSupplierGrade")
	@POST
	public String deleteSupplierGrade(@FormParam("params") String params) {
		LOGGER.info("删除信息,参数：" + params.toString());
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmPosSupplierGradeServer.deleteSupplierGrade(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(
					jsondata.getString("status"), jsondata.getString("msg"),
					jsondata.getInteger("count"), jsondata.get("data"));
		} catch (Exception e) {
			LOGGER.error("--->>删除失败！参数：" + params + ",异常：" + e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "删除失败!", 0,
					null);
		}
	}

	/**
	 * 审批供应商级别信息
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("updateApprovalSupplierGrade")
	@POST
	public String updateApprovalSupplierGrade(@FormParam("params") String params) {
		LOGGER.info("审批,参数：" + params.toString());
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject posJson = srmPosSupplierGradeServer
					.updateApprovalSupplierGrade(jsonParam);
			return CommonAbstractServices.convertResultJSONObj(
					posJson.getString("status"), posJson.getString("msg"),
					posJson.getInteger("count"), posJson.get("data"));
		} catch (Exception e) {
			LOGGER.error("审批失败！" + e, e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return CommonAbstractServices.convertResultJSONObj("E",
					"审批失败!" + e, 0, null);
		}
	}
	
	/**
	 * 驳回供应商级别信息
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("updateRejectSupplierGrade")
	@POST
	public String updateRejectSupplierGrade(@FormParam("params") String params) {
		LOGGER.info("驳回,参数：" + params.toString());
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject posJson = srmPosSupplierGradeServer
					.updateRejectSupplierGrade(jsonParam);
			return CommonAbstractServices.convertResultJSONObj(
					posJson.getString("status"), posJson.getString("msg"),
					posJson.getInteger("count"), posJson.get("data"));
		} catch (Exception e) {
			LOGGER.error("驳回失败！" + e, e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return CommonAbstractServices.convertResultJSONObj("E",
					"驳回失败!" + e, 0, null);
		}
	}
	
	/**
	 * 重新审批供应商级别信息
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("updateChangeSupplierGrade")
	@POST
	public String updateChangeSupplierGrade(@FormParam("params") String params) {
		LOGGER.info("重新审批,参数：" + params.toString());
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject posJson = srmPosSupplierGradeServer
					.updateChangeSupplierGrade(jsonParam);
			return CommonAbstractServices.convertResultJSONObj(
					posJson.getString("status"), posJson.getString("msg"),
					posJson.getInteger("count"), posJson.get("data"));
		} catch (Exception e) {
			LOGGER.error("重新审批失败！" + e, e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return CommonAbstractServices.convertResultJSONObj("E",
					"重新审批失败!" + e, 0, null);
		}
	}
	
	/**
	 * 查询导出级别头数据
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSupplierGradeExport")
	public String findSupplierGradeExport(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPosSupplierGradeEntity_HI_RO> list = srmPosSupplierGradeServer.findSupplierGradeExport(jsonParams);
			return CommonAbstractServices.convertResultJSONObj("S", "导出成功", list.size(), list);
		}  catch (Exception e) {
			LOGGER.error("导出级别头数据异常：" + e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "导出异常!" + e, 0, null);
		}
	}
}
