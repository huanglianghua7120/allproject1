package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.inter.ISrmPonJuryCountersigns;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonJuryCountersignsService.java
 * Description：寻源--会签信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonJuryCountersignsService")
@Path("/srmPonJuryCountersignsService")
public class SrmPonJuryCountersignsService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonJuryCountersignsService.class);
	@Autowired
	private ISrmPonJuryCountersigns iSrmPonJuryCountersigns;
	public SrmPonJuryCountersignsService() {
		super();
	}

	/**
	 * Description：会签查询（不分页）——已截止
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findSrmPonJuryCountersignsList")
	@Produces("application/json")
	public String findSrmPonJuryCountersignsList(@FormParam(PARAMS) String params) {
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			if(null == jsonParams.getInteger("auctionHeaderId") || "".equals(jsonParams.getString("auctionHeaderId"))){
				return CommonAbstractServices.convertResultJSONObj("E", "操作失败,洽谈Id为" + jsonParams.getInteger("auctionHeaderId"), 0, null);
			}
			return JSON.toJSONString(iSrmPonJuryCountersigns.findSrmPonJuryCountersignsList(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询失败" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：会签的发起、同意、拒绝按钮操作（已截止）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("saveJuryCountersigns")
	@Produces("application/json")
	public String saveJuryCountersigns(@FormParam(PARAMS) String params) {
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonJuryCountersigns.saveJuryCountersigns(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		}catch (Exception e){
			LOGGER.error("--------------------------->操作失败！参数："+params.toString()+"，异常："+ e.getMessage());
			//e.printStackTrace();
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"操作失败！"+e.getMessage(),0,null);
		}
	}
}
