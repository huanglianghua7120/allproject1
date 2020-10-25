package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.inter.ISrmPonJuryScores;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonJuryScoresService.java
 * Description：寻源--评分信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonJuryScoresService")
@Path("/srmPonJuryScoresService")
public class SrmPonJuryScoresService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonJuryScoresService.class);
	@Autowired
	private ISrmPonJuryScores iSrmPonJuryScores;
	public SrmPonJuryScoresService() {
		super();
	}

	/**
	 * Description：查询单条的洽谈评分及其子表的洽谈评分明细list
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findSrmPonJuryScoresInfo")
	@Produces("application/json")
	public String findSrmPonJuryScoresInfo(@FormParam(PARAMS) String params) {
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonJuryScores.findSrmPonJuryScoresInfo(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		}catch (Exception e){
			LOGGER.error("--------------------------->操作失败！参数："+params.toString()+"，异常："+ e.getMessage());
			//e.printStackTrace();
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"操作失败！"+e.getMessage(),0,null);
		}
	}

	/**
	 * Description：保存洽谈评分及其评分明细（已截止的评分）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("savePonJuryScoresAll")
	@Produces("application/json")
	public String savePonJuryScoresAll(@FormParam(PARAMS)String params){
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonJuryScores.savePonJuryScoresAll(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		}catch (Exception e){
			LOGGER.error("--------------------------->操作失败！参数："+params.toString()+"，异常："+ e.getMessage());
			//e.printStackTrace();
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"操作失败！"+e.getMessage(),0,null);
		}
	}
}
