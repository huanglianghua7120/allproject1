package saaf.common.fmw.apimanager.services;

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

import saaf.common.fmw.apimanager.model.inter.ISaafProjectManagerH;
import saaf.common.fmw.services.CommonAbstractServices;

@Component("saafProjectManagerService")
@Path("/saafProjectManagerService")
public class SaafProjectManagerHService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SaafProjectManagerHService.class);
	@Autowired
	private ISaafProjectManagerH saafProjectManagerServer;
	public SaafProjectManagerHService() {
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
	@Path("findProjectManagerInfo")
	//@Consumes("application/json")
	@Produces("application/json")
	//	/restServer/saafProjectManagerService/findProjectManagerInfo
	public String findProjectManagerInfo(@FormParam("params")
        String params, @FormParam("pageIndex")
        @DefaultValue("1")
        Integer curIndex, @FormParam("pageRows")
        @DefaultValue("10")
        Integer pageSize) {
		LOGGER.info(params);
		JSONObject paramJSON = JSON.parseObject(params);
		String resultStr = saafProjectManagerServer.findSaafProjectManagerInfo(paramJSON,curIndex,pageSize);
		LOGGER.info(resultStr);
		return resultStr;
	}
	@POST
	@Path("saveProjectManagerInfo")
	//@Consumes("application/json")
	@Produces("application/json")
	//	/restServer/saafProjectManagerService/saveProjectManagerInfo
	public String saveProjectManagerInfo(@FormParam("params") String postParam) {
		LOGGER.info(postParam);
		
		JSONObject paramJSON = JSON.parseObject(postParam);

		String resultStr = saafProjectManagerServer.saveProjectManagerAllInfo(paramJSON);
		LOGGER.info(resultStr);
		return resultStr;
	}
	
	@POST
	@Path("deleteProjectManagerInfo")
	//@Consumes("application/json")
	@Produces("application/json")
	//	/restServer/saafProjectManagerService/deleteProjectManagerInfo
	public String deleteProjectManagerInfo(@FormParam("params") String postParam) {
		LOGGER.info(postParam);
		
		JSONObject paramJSON = JSON.parseObject(postParam);
         String resultStr = saafProjectManagerServer.deleteSaafProjectManagerInfo(paramJSON);
		LOGGER.info(resultStr);
		return resultStr;
	}
}
