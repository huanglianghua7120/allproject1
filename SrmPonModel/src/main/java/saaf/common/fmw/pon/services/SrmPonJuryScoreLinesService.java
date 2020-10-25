package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.inter.ISrmPonJuryScoreLines;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonJuryScoreLinesService.java
 * Description：寻源--评分明细信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonJuryScoreLinesService")
@Path("/srmPonJuryScoreLinesService")
public class SrmPonJuryScoreLinesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonJuryScoreLinesService.class);
	@Autowired
	private ISrmPonJuryScoreLines iSrmPonJuryScoreLines;
	public SrmPonJuryScoreLinesService() {
		super();
	}

	/**
	 * Description：评分汇总查询——评分明细list（不分页）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findSrmPonJuryScoreLinesAll")
	@Produces("application/json")
	public String findSrmPonJuryScoreLinesAll(@FormParam(PARAMS) String params) {
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonJuryScoreLines.findSrmPonJuryScoreLinesAll(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询失败" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：招标评分汇总查询——评分明细list（不分页）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findSrmPonTenderJuryScoreLinesAll")
	@Produces("application/json")
	public String findSrmPonTenderJuryScoreLinesAll(@FormParam(PARAMS) String params) {
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonJuryScoreLines.findSrmPonTenderJuryScoreLinesAll(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询失败" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：招标评分汇总显示价格得分
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	/*@POST
	@Path("findSrmPonTenderJuryScoreLinesPriceAll")
	@Produces("application/json")
	public String findSrmPonTenderJuryScoreLinesPriceAll(@FormParam(PARAMS) String params) {
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonJuryScoreLines.findSrmPonTenderJuryScoreLinesPriceAll(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询失败" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}*/

	/**
	 * Description：招标评分汇总计算价格得分
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("saveSrmPonTenderJuryScoreLinesPriceAll")
	@Produces("application/json")
	public String saveSrmPonTenderJuryScoreLinesPriceAll(@FormParam(PARAMS)String params){
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonJuryScoreLines.saveSrmPonTenderJuryScoreLinesPriceAll(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		}catch (Exception e){
			LOGGER.error("--------------------------->操作失败！参数："+params.toString()+"，异常："+ e.getMessage());
			//e.printStackTrace();
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"操作失败！"+e.getMessage(),0,null);
		}
	}

	/**
	 * Description：招标评分汇总保存手工填写分数
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("saveSrmPonTenderJuryScoreLinesPrice")
	@Produces("application/json")
	public String saveSrmPonTenderJuryScoreLinesPrice(@FormParam(PARAMS)String params){
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonJuryScoreLines.saveSrmPonTenderJuryScoreLinesPrice(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		}catch (Exception e){
			LOGGER.error("--------------------------->操作失败！参数："+params.toString()+"，异常："+ e.getMessage());
			//e.printStackTrace();
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"操作失败！"+e.getMessage(),0,null);
		}
	}
}
