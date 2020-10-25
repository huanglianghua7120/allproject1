package saaf.common.fmw.bpm.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.inter.IActReProcdef;
import saaf.common.fmw.services.CommonAbstractServices;


@Component("actReProcdefService")
@Path("actReProcdefService")
public class ActReProcdefService extends CommonAbstractServices {

    @Autowired
    private IActReProcdef actReProcdefServer;

    public ActReProcdefService() {
        super();
    }

    @POST
    @Path("findProdcessModelInfo")
    public String findProdcessModelInfo(@FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        JSONObject queryParamJSON = new JSONObject();
        String actReProcdefInfo = actReProcdefServer.findActReProcdefInfo(queryParamJSON, pageIndex, pageRows);
        return actReProcdefInfo;
    }

    @POST
    @Path("findProcessDefinitions")
    public String findProcessDefinitions(@FormParam(PAGE_INDEX) Integer pageIndex, 
    		@FormParam(PAGE_ROWS) Integer pageRows,@FormParam("params")String params){
    	 JSONObject queryParamJSON = JSON.parseObject(params);
    	 String retStr = actReProcdefServer.findProcessDefinitions(queryParamJSON, pageIndex, pageRows);
         return retStr;
    }
}
