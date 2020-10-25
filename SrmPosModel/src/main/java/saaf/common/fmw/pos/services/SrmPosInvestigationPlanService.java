package saaf.common.fmw.pos.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosInvestigationPlanEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosInvestigationPlan;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：考察计划
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
@Component("srmPosInvestigationPlanService")
@Path("/srmPosInvestigationPlanService")
public class SrmPosInvestigationPlanService extends CommonAbstractServices{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosInvestigationPlanService.class);
	@Autowired
	private ISrmPosInvestigationPlan srmPosInvestigationPlanServer;
	public SrmPosInvestigationPlanService() {
		super();
	}


	/**
	 * 分页查询考察计划数据
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
	@Path("findSrmPosInvestigationPlanInfoList")
	public String findSrmPosInvestigationPlanInfoList(@FormParam("params") String params,
												  @FormParam("pageIndex") @DefaultValue("1") Integer curIndex,
												  @FormParam("pageRows") @DefaultValue("10") Integer pageSize) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			String resultStr = JSONObject.toJSONString(
					srmPosInvestigationPlanServer.findSrmPosInvestigationPlanInfoList(paramJSON, curIndex, pageSize));
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
	 * 根据ID查询考察计划信息
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
	@Path("findInvestigationPlanHeaderInfoById")
	public String findInvestigationPlanHeaderInfoById(@FormParam("params") String params) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			List<SrmPosInvestigationPlanEntity_HI_RO> result = srmPosInvestigationPlanServer.findInvestigationPlanHeaderInfoById(paramJSON);
			return  convertResultJSONObj("S", "查询成功!" , 1, result);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return convertResultJSONObj(ERROR_STATUS, "查询失败！", 0, null);
		}
	}

	/**
	 * Description：保存或更新现场考察数据
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * investigationPlanId  考察计划ID  NUMBER  Y
	 * planName  计划名称  VARCHAR2  Y
	 * planNumber  单据编号  VARCHAR2  Y
	 * planStatus  单据状态（POS_APPROVAL_STATUS）  VARCHAR2  Y
	 * description  备注说明  VARCHAR2  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

	@Path("saveInvestigationPlanInfo")
	@POST
	public String saveInvestigationPlanInfo(@FormParam("params") String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmPosInvestigationPlanServer.saveInvestigationPlanInfo(jsonParams);
			return convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT),
					jsondata.get(DATA));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return convertResultJSONObj(ERROR_STATUS, "保存失败!" + e, 0, null);
		}
	}

	/**
	 * 根据行ID删除考察计划的供应商数据
	 *
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("deleteInvestigationPlanInfo")
	@POST
	public String deleteInvestigationPlanInfo(@FormParam("params") String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmPosInvestigationPlanServer.deleteInvestigationPlanInfo(jsonParams);
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

}
