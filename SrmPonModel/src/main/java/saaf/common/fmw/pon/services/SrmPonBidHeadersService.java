package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.pon.model.inter.ISrmPonBidHeaders;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonBidHeadersService.java
 * Description：寻源--寻源报价头信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonBidHeadersService")
@Path("/srmPonBidHeadersService")
public class SrmPonBidHeadersService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonBidHeadersService.class);
	@Autowired
	private ISrmPonBidHeaders iSrmPonBidHeaders;
	public SrmPonBidHeadersService() {
		super();
	}

	/**
	 * Description：查询供应商报价头表（不分页）——带有报价总价与报价总价排名（用于已截止）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findBidHeadersSupplierList")
	public String findBidHeadersSupplierList(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonBidHeaders.findBidHeadersSupplierList(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询受邀供应商失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：供应商报价头表中标（已截止）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("updateBidHeaderSupplierAwardStatus")
	@Produces("application/json")
	public String updateBidHeaderSupplierAwardStatus(@FormParam(PARAMS) String params) {
		if (StringUtils.isBlank(params)) {
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可操作！", 0, null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonBidHeaders.updateBidHeaderSupplierAwardStatus(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		} catch (Exception e) {
			LOGGER.error("供应商操作失败" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：查询供应商报价（不分页）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("getBidHeadersList")
	public String getBidHeadersList(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonBidHeaders.getBidHeadersList(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询供应商报价失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：查询监控报价供应商
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("getBidSupplierList")
	public String getBidSupplierList(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonBidHeaders.getBidSupplierList(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询受邀供应商失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：删除拟定状态下的报价单据
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@Path("deleteBidHeaders")
	@POST
	public String deleteBidHeaders(@FormParam("params") String params) {
		LOGGER.info("删除信息,参数：" + params.toString());
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonBidHeaders.deleteBidHeaders(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(
					jsondata.getString("status"), jsondata.getString("msg"),
					jsondata.getInteger("count"), jsondata.get("data"));
		} catch (Exception e) {
			LOGGER.error("--->>删除失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "删除失败!", 0,
					null);
		}
	}

	/**
	 * Description：发起议价
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("updateBidHeaderBargainFlag")
	@Produces("application/json")
	public String updateBidHeaderBargainFlag(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonBidHeaders.updateBidHeaderBargainFlag(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		} catch (Exception e) {
			LOGGER.error("发起议价操作失败" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}
}
