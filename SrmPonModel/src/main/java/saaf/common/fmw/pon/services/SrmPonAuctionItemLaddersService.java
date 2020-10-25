package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionItemLadders;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonAuctionItemLaddersService.java
 * Description：寻源--寻源标的物阶梯信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonAuctionItemLaddersService")
@Path("/srmPonAuctionItemLaddersService")
public class SrmPonAuctionItemLaddersService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonAuctionItemLaddersService.class);
	@Autowired
	private ISrmPonAuctionItemLadders iSrmPonAuctionItemLadders;
	public SrmPonAuctionItemLaddersService() {
		super();
	}

	/**
	 * Description：查询阶梯数量（不分页）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("getPonAuctionItemLaddersList")
	@Produces("application/json")
	public String getPonAuctionItemLaddersList(@FormParam(PARAMS) String params) {
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonAuctionItemLadders.getPonAuctionItemLaddersList(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询失败" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：删除供应商行的阶梯数量——根据阶梯数量的主键ID（单条数据）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@Path("deleteSrmPonAuctionItemLadderById")
	@POST
	@Produces("application/json")
	public String deleteSrmPonAuctionItemLadderById(@FormParam(PARAMS)String params){
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可删除！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonAuctionItemLadders.deleteSrmPonAuctionItemLadderById(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.get(DATA));
		}catch (Exception e){
			//e.printStackTrace();
			LOGGER.error("删除失败！参数：" + params + ",异常：" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "删除失败!" + e, 0, null);
		}
	}

	/**
	 * Description：保存阶梯数量List（需要传入标的物的行的auctionLineId,auctionHeaderId）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@Path("savePonAuctionItemLaddersList")
	@POST
	@Produces("application/json")
	public String savePonAuctionItemLaddersList(@FormParam(PARAMS)String params){
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可删除！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonAuctionItemLadders.savePonAuctionItemLaddersList(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		}catch (Exception e){
			LOGGER.error("--------------------------->操作失败！参数："+params.toString()+"，异常："+ e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"操作失败！"+e.getMessage(),0,null);
		}
	}
}
