package saaf.common.fmw.apimanager.services;

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
import saaf.common.fmw.apimanager.model.inter.ISaafProjectApiMamager;

@Component("saafApiMamagerService")
@Path("/saafApiMamagerService")
public class SaafProjectApiMamagerService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SaafProjectApiMamagerService.class);
	@Autowired
	private ISaafProjectApiMamager saafProjectApiMamagerServer;
	public SaafProjectApiMamagerService() {
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
	@Path("findApiMamagerInfo")
	//@Consumes("application/json")
	@Produces("application/json")
	//	/restServer/saafApiMamagerService/findApiMamagerInfo
	public String findApiMamagerInfo(@FormParam("params")
        String params, @FormParam("pageIndex")
        @DefaultValue("1")
        Integer curIndex, @FormParam("pageRows")
        @DefaultValue("10")
        Integer pageSize) {
		LOGGER.info(params);
		JSONObject paramJSON = JSON.parseObject(params);
		String resultStr = saafProjectApiMamagerServer.findSaafProjectApiMamagerInfo(paramJSON,curIndex,pageSize);
		LOGGER.info(resultStr);
		return resultStr;
	}
	
	@POST
	@Path("saveApiMamagerInfo")
	//@Consumes("application/json")
	@Produces("application/json")
	//	/restServer/saafApiMamagerService/saveApiMamagerInfo
	public String saveSaafProjectApiMamagerInfo(@FormParam("params") String postParam) {
		LOGGER.info(postParam);
		
		JSONObject paramJSON = JSON.parseObject(postParam);

		String resultStr = saafProjectApiMamagerServer.saveSaafProjectApiMamagerInfo(paramJSON);
		LOGGER.info(resultStr);
		return resultStr;
	}
	
	@POST
	@Path("deleteApiManagerInfo")
	//@Consumes("application/json")
	@Produces("application/json")
	//	/restServer/saafApiMamagerService/deleteApiManagerInfo
	public String deleteSaafProjectApiManagerInfo(@FormParam("params") String postParam) {
		LOGGER.info(postParam);
		
		JSONObject paramJSON = JSON.parseObject(postParam);
         String resultStr = saafProjectApiMamagerServer.deleteSaafProjectApiManagerInfo(paramJSON);
		LOGGER.info(resultStr);
		return resultStr;
	}
}
