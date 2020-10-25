package saaf.common.fmw.base.services;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import saaf.common.fmw.base.model.inter.ISrmBaseParams;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：配置样式
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Path("/srmBaseParamsService")
@Component
public class SrmBaseParamsService extends CommonAbstractServices {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SrmBaseParamsService.class);

	@Autowired
	private ISrmBaseParams SrmBaseParamsServer;

	/**
	 * 查询样式
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findBaseParams")
	public String findBaseParams(@FormParam(PARAMS) String params) {
		try {
			//JSONObject jsonParams = this.parseObject(params);
			JSONObject jsonParams = JSON.parseObject(params);
			List list = SrmBaseParamsServer.findBaseParams(jsonParams);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功！",
					list.size(), list);
		} catch (Exception e) {
			LOGGER.error("查询失败" + e, e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E",
					"未知错误", 0, null);
		}
	}


	/**
	 * Description：保存样式
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * paramTitle  参数标题  VARCHAR2  N
	 * paramId  参数ID  NUMBER  Y
	 * paramCode  参数编号  VARCHAR2  N
	 * paramComments  参数说明  VARCHAR2  Y
	 * paramValue1  参数值1  VARCHAR2  N
	 * paramValue2  参数值2  VARCHAR2  N
	 * paramValue3  参数值3  VARCHAR2  N
	 * paramValue4  参数值4  VARCHAR2  N
	 * paramValue5  参数值5  VARCHAR2  N
	 * description  描述  VARCHAR2  N
	 * explaining  说明  VARCHAR2  N
	 * paramId  参数ID  NUMBER  Y
	 * paramCode  参数编号  VARCHAR2  N
	 * paramComments  参数说明  VARCHAR2  Y
	 * paramValue1  参数值1  VARCHAR2  N
	 * paramValue2  参数值2  VARCHAR2  N
	 * paramValue3  参数值3  VARCHAR2  N
	 * paramValue4  参数值4  VARCHAR2  N
	 * paramValue5  参数值5  VARCHAR2  N
	 * description  描述  VARCHAR2  N
	 * explaining  说明  VARCHAR2  N
	 * paramTitle  参数标题  VARCHAR2  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

	@Path("saveBaseParams")
	@POST
	public String saveBaseParams(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject posJson = SrmBaseParamsServer
					.saveBaseParams(jsonParam);
			return CommonAbstractServices.convertResultJSONObj(
					posJson.getString("status"), posJson.getString("msg"),
					posJson.getInteger("count"), posJson.get("data"));
		} catch (Exception e) {
			LOGGER.error("保存失败！" + e, e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E",
					"未知错误", 0, null);
		}
	}
}
