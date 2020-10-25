package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionJuries;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonAuctionJuriesService.java
 * Description：寻源--寻源协作小组信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonAuctionJuriesService")
@Path("/srmPonAuctionJuriesService")
public class SrmPonAuctionJuriesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonAuctionJuriesService.class);
	@Autowired
	private ISrmPonAuctionJuries iSrmPonAuctionJuries;
	public SrmPonAuctionJuriesService() {
		super();
	}

	/**
	 * Description：删除洽谈协作小组——根据主键ID（单条数据）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@Path("deleteSrmPonAuctionJuriesById")
	@POST
	@Produces("application/json")
	public String deleteSrmPonAuctionJuriesById(@FormParam(PARAMS) String params){
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可删除！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonAuctionJuries.deleteSrmPonAuctionJuriesById(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.get(DATA));
		}catch (Exception e){
			//e.printStackTrace();
			LOGGER.error("删除失败！参数：" + params + ",异常：" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "删除失败!" + e, 0, null);
		}
	}

	/**
	 * Description：查询协作小组页签
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("getAuctionJuryList")
	public String getAuctionJuryList(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonAuctionJuries.getAuctionJuryList(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询受邀供应商失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}
}
