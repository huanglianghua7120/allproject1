package saaf.common.fmw.pos.services;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosInvestigationPlanLinesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosInvestigationPlanLines;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：现场考察品类
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
@Component("srmPosInvestigationPlanLinesService")
@Path("/srmPosInvestigationPlanLinesService")
public class SrmPosInvestigationPlanLinesService extends CommonAbstractServices{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosInvestigationPlanLinesService.class);
	@Autowired
	private ISrmPosInvestigationPlanLines srmPosInvestigationPlanLinesServer;
	public SrmPosInvestigationPlanLinesService() {
		super();
	}
	/**
	 * 查询计划考察单号LOV
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
	@Path("findInvestigationPlanLov")
	@POST
	public String findInvestigationPlanLov(@FormParam("params")
											   String params, @FormParam("pageIndex")
											   Integer pageIndex, @FormParam("pageRows")
											   Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			Pagination<SrmPosInvestigationPlanLinesEntity_HI_RO> investigationPlanlov = srmPosInvestigationPlanLinesServer.findInvestigationPlanNumber(jsonParam, pageIndex, pageRows);
			return JSONObject.toJSONString(investigationPlanlov);
		}catch (Exception e) {
			LOGGER.error("查询资质审查单号LOV失败！" + e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询资质审查单号LOV失败!" + e, 0, null);
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
	@Path("findSrmReviewPlanCategories")
	public String findSrmReviewPlanCategories(@FormParam("params") String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPosInvestigationPlanLinesEntity_HI_RO> cateList = srmPosInvestigationPlanLinesServer
					.findSrmReviewPlanCategories(jsonParams);
			LOGGER.info("--->>findSrmReviewCategories-->>参数：" + params + "查询考察品类成功！");
			return CommonAbstractServices.convertResultJSONObj("S", "查询考察品类成功！", cateList.size(), cateList);
		} catch (Exception e) {

			LOGGER.error("查询考察品类失败" + e, e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询考察品类失败!" + e, 0, null);
		}
	}

	/**
	 * 批量导入
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("batchImportPlanLines")
	public String batchImportIndicators(@FormParam(PARAMS) String params){
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject object = srmPosInvestigationPlanLinesServer.saveList(jsonParams);
			return JSONObject.toJSONString(object);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E",   e.getMessage(), 0, null);
		}
	}

}
