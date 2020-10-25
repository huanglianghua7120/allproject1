package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.pon.model.inter.ISrmPonPriceLibrary;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonPriceLibraryService.java
 * Description：寻源--价格库信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonPriceLibraryService")
@Path("/srmPonPriceLibraryService")
public class SrmPonPriceLibraryService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonPriceLibraryService.class);
	@Autowired
	private ISrmPonPriceLibrary srmPonPriceLibraryServer;
	public SrmPonPriceLibraryService() {
		super();
	}

	/**
	 * 
	 * @param params
	 * @param curIndex
	 * @param pageSize
	 * @return
	 */
	@POST
	@Path("findSrmPonPriceLibraryInfo")
	@Consumes("application/json")
	@Produces("application/json")
	//	/restServer/srmPonPriceLibraryService/findSrmPonPriceLibraryInfo
	public String findSrmPonPriceLibraryInfo(@FormParam("params")
        String params, @FormParam("pageIndex")
        @DefaultValue("1")
        Integer curIndex, @FormParam("pageRows")
        @DefaultValue("10")
        Integer pageSize) {
		LOGGER.info(params);
		JSONObject paramJSON = JSON.parseObject(params);
		String resultStr = JSONObject.toJSONString(srmPonPriceLibraryServer.findSrmPonPriceLibraryInfo(paramJSON));
		LOGGER.info(resultStr);
		return resultStr;
	}

	/**
	 * Description：招标询价-已完成-创建价格库
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("updateAndCreatePriceLibrary")
	public String updateAndCreatePriceLibrary(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmPonPriceLibraryServer.updateAndCreatePriceLibrary(jsonParams);
			return convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.get(DATA));
		} catch (Exception e) {
			LOGGER.error("创建价格库失败：" + e.getMessage());
			return convertResultJSONObj("E", "创建价格库失败！", 0, null);
		}
	}
}
