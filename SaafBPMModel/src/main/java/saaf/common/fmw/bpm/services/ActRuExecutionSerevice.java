package saaf.common.fmw.bpm.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import saaf.common.fmw.bpm.model.inter.IActRuExecution;
import saaf.common.fmw.services.CommonAbstractServices;

@Component("actRuExecutionSerevice")
@Path("actRuExecutionSerevice")
public class ActRuExecutionSerevice extends CommonAbstractServices {
	
	@Autowired
	private IActRuExecution actRuExecutionServer;
	
	@POST
	@Path("findActExecutions")
	public String findActExecutions(@FormParam(PAGE_INDEX)Integer pageIndex,
			@FormParam(PAGE_ROWS)Integer pageRows,@FormParam("params") String params){
		JSONObject paramsJSON = JSON.parseObject(params);
		
		String retStr = actRuExecutionServer.findActRuExecutionInfo(paramsJSON, pageIndex, pageRows);
		return retStr;
	}
	
}
