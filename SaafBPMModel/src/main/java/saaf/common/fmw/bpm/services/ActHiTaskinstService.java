package saaf.common.fmw.bpm.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.inter.IActHiTaskinst;
import saaf.common.fmw.services.CommonAbstractServices;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Component("actHiTaskinstService")
@Path("actHiTaskinstService")
public class ActHiTaskinstService extends CommonAbstractServices {

	@Autowired
	private IActHiTaskinst actHiTaskinstServer;
	
	public ActHiTaskinstService() {
		super();
	}

	@POST
    @Path("findActHiTaskinsts")
    public String findActHiTaskinsts(@FormParam(PAGE_INDEX) Integer pageIndex,
    		@FormParam(PAGE_ROWS)Integer pageRows,@FormParam("params")String params) {
		JSONObject queryParamJSON = JSON.parseObject(params);
		String retStr = actHiTaskinstServer.findActHiTaskinsts(pageIndex, pageRows, queryParamJSON);
		return retStr;
	}
	
}
