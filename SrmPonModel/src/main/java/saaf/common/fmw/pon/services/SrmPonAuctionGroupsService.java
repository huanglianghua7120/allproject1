package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionGroups;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonAuctionGroupsService.java
 * Description：寻源--寻源单据组别信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonAuctionGroupsService")
@Path("/srmPonAuctionGroupsService")
public class SrmPonAuctionGroupsService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonAuctionGroupsService.class);
	@Autowired
	private ISrmPonAuctionGroups iSrmPonAuctionGroups;
	public SrmPonAuctionGroupsService() {
		super();
	}

	/**
	 * Description：查询拟标头组别（不分页）
	 * @param params 参数
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("getAuctionGroupList")
	public String getAuctionGroupList(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonAuctionGroups.getAuctionGroupList(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询组别失败" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}


	/**
	 * Description：删除洽谈组别——根据主键ID（单条数据）
	 * @param params 参数
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@Path("deleteSrmPonAuctionGroupsById")
	@POST
	@Produces("application/json")
	public String deleteSrmPonAuctionGroupsById(@FormParam(PARAMS) String params){
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可删除！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonAuctionGroups.deleteSrmPonAuctionGroupsById(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.get(DATA));
		}catch (Exception e){
			//e.printStackTrace();
			LOGGER.error("删除失败！参数：" + params + ",异常：" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "删除失败!" + e, 0, null);
		}
	}
}
