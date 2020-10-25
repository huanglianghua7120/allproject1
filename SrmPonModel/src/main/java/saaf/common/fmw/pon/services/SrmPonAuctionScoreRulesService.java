package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionScoreRules;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonAuctionScoreRulesService.java
 * Description：寻源--寻源评分规则信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonAuctionScoreRulesService")
@Path("/srmPonAuctionScoreRulesService")
public class SrmPonAuctionScoreRulesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonAuctionScoreRulesService.class);
	@Autowired
	private ISrmPonAuctionScoreRules iSrmPonAuctionScoreRules;
	public SrmPonAuctionScoreRulesService() {
		super();
	}

	/**
	 * Description：查询招标的招标规则（不分页）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("getPonAuctionScoreRulesList")
	@Produces("application/json")
	public String getPonAuctionScoreRulesList(@FormParam(PARAMS) String params) {
		LOGGER.info(params);
		try{
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonAuctionScoreRules.getPonAuctionScoreRulesList(jsonParams));
		}catch (Exception e){
			//e.printStackTrace();
			LOGGER.error("查询失败" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：删除洽谈评分规则——根据主键ID（单条数据）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@Path("deleteSrmPonAuctionScoreRuleById")
	@POST
	@Produces("application/json")
	public String deleteSrmPonAuctionScoreRuleById(@FormParam(PARAMS) String params){
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可删除！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonAuctionScoreRules.deleteSrmPonAuctionScoreRuleById(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.get(DATA));
		}catch (Exception e){
			//e.printStackTrace();
			LOGGER.error("删除失败！参数：" + params + ",异常：" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "删除失败!" + e, 0, null);
		}
	}
}
