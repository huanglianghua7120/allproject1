package saaf.common.fmw.bpm.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import saaf.common.fmw.bpm.model.inter.IActHiActinst;
import saaf.common.fmw.services.CommonAbstractServices;

@Component("actHiActinstService")
@Path("actHiActinstService")
public class ActHiActinstService extends CommonAbstractServices {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActHiActinstService.class);
	
	@Autowired
	private IActHiActinst actHiActinstServer;
	
	
	@POST
	@Path("findActHiActinstInfo")
	public String findActHiActinstInfo(@FormParam("params")String params){
		
		JSONObject queryParamJSON = JSONObject.parseObject(params);
		String result = actHiActinstServer.findActHiActinst(queryParamJSON);
		
		return result;
	}
	
}
