package saaf.common.fmw.bpm.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.inter.IActHiProcinst;
import saaf.common.fmw.services.CommonAbstractServices;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Component("actHiProcinstService")
@Path("actHiProcinstService")
public class ActHiProcinstService extends CommonAbstractServices {

	@Autowired
	private IActHiProcinst actHiProcinstServer;
	
	/**
	 * 查询历史流程
	 * @param pageIndex
	 * @param pageRows
	 * @param params
	 * @return
	 */
	@POST
    @Path("findActHiProcinsts")
    public String findActHiProcinsts(@FormParam(PAGE_INDEX) Integer pageIndex,
    		@FormParam(PAGE_ROWS)Integer pageRows,@FormParam("params")String params) {
		JSONObject queryParamJSON = JSON.parseObject(params);
		String retStr = actHiProcinstServer.findActHiProcinsts(pageIndex, pageRows, queryParamJSON);
		return retStr;
	}
	
	/**
	 * 查询我的流程
	 * 
	 * @return
	 */
	@POST
	@Path("findMyFlows")
	public String findMyFlows(@FormParam(PAGE_INDEX) Integer pageIndex,
			@FormParam(PAGE_ROWS) Integer pageRows,
			@FormParam("params") String params) {
		JSONObject paramsJSON = JSON.parseObject(params);
		paramsJSON.put("start_user_id_", getUserSessionBean().getUserId());
		String retJSONStr = actHiProcinstServer.findMyFlows(pageIndex, pageRows, paramsJSON);
		return retJSONStr;
	}
}
