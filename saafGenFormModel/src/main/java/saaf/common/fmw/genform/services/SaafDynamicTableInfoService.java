package saaf.common.fmw.genform.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.genform.model.inter.ISaafDynamicTableInfo;

import com.alibaba.fastjson.JSONObject;

@Component("saafDynamicTableInfoService")
@Path("/saafDynamicTableInfoService")
public class SaafDynamicTableInfoService{
private static final Logger LOGGER = LoggerFactory.getLogger(SaafDynamicTableInfoService.class);
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;
	@Autowired
	private ISaafDynamicTableInfo saafDynamicTableInfoServer;
	
	public SaafDynamicTableInfoService() {
		super();
	}
	
	@POST
	@Path("findColumns")
	@Produces("application/json")
	public String findColumns(@FormParam("table") String table) {
		LOGGER.info("table:"+table);
//		ISaafDynamicTableInfo saafDynamicTableInfoServer = (ISaafDynamicTableInfo)SaafToolUtils.context.getBean("saafDynamicTableInfoServer");
		JSONObject resultStr = saafDynamicTableInfoServer.findColumns(table);
		LOGGER.info(resultStr.toJSONString());
		
		return resultStr.toJSONString();
	}
	
	@POST
	@Path("findTableInfo")
	@Produces("application/json")
	public String findTableInfo(@FormParam("table") String table) {
		LOGGER.info("table:"+table);
//		ISaafDynamicTableInfo saafDynamicTableInfoServer = (ISaafDynamicTableInfo)SaafToolUtils.context.getBean("saafDynamicTableInfoServer");
		JSONObject resultStr = saafDynamicTableInfoServer.findTableInfo(table);
		LOGGER.info(resultStr.toJSONString());
		
		return resultStr.toJSONString();
	}
}
