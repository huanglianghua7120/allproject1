package saaf.common.fmw.apimanager.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import saaf.common.fmw.apimanager.model.inter.ISaafProjectManagerL;
import saaf.common.fmw.services.CommonAbstractServices;

@Component("saafManagerDetailService")
@Path("/saafManagerDetailService")
public class SaafProjectManagerLService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SaafProjectManagerLService.class);
	@Autowired
	private ISaafProjectManagerL saafProjectManagerLServer;
	public SaafProjectManagerLService() {
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
	@Path("findProjectManagerDetailInfo")
//	@Consumes("application/json")
	@Produces("application/json")
	//	/restServer/saafManagerDetailService/findProjectManagerDetailInfo
	public String findProjectManagerDetailInfo(@FormParam("params")
        String params, @FormParam("pageIndex")
        @DefaultValue("1")
        Integer curIndex, @FormParam("pageRows")
        @DefaultValue("10")
        Integer pageSize) {
		LOGGER.info(params);
		JSONObject paramJSON = JSON.parseObject(params);
		String resultStr = saafProjectManagerLServer.findSaafProjectManagerLInfo(paramJSON);
		LOGGER.info(resultStr);
		return resultStr;
	}
	@POST
	@Path("saveProjectManagerDetailInfo")
	//@Consumes("application/json")
	@Produces("application/json")
	//	/restServer/saafManagerDetailService/saveProjectManagerDetailInfo
	public String saveProjectManagerInfo(@FormParam("params") String postParam) {
		LOGGER.info(postParam);
		
		JSONObject paramJSON = JSON.parseObject(postParam);

		String resultStr = saafProjectManagerLServer.saveSaafProjectManagerLInfo(paramJSON);
		LOGGER.info(resultStr);
		return resultStr;
	}
	@POST
	@Path("deleteProjectManagerDetailInfo")
	//@Consumes("application/json")
	@Produces("application/json")
	//	/restServer/saafManagerDetailService/deleteProjectManagerDetailInfo
	public String deleteProjectManagerDetailInfo(@FormParam("params") String postParam) {
		LOGGER.info(postParam);
		JSONObject paramJSON = JSON.parseObject(postParam);
         String resultStr = saafProjectManagerLServer.deleteProjectManagerLInfo(paramJSON);
		LOGGER.info(resultStr);
		return resultStr;
	}
}
