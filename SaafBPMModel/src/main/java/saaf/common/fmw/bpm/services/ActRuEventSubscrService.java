package saaf.common.fmw.bpm.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.bpm.model.inter.IActRuEventSubscr;
import saaf.common.fmw.services.CommonAbstractServices;

@Component("actRuEventSubscrService")
@Path("actRuEventSubscrService")
public class ActRuEventSubscrService extends CommonAbstractServices {
	
	@Autowired
	private IActRuEventSubscr actRuEventSubscrServer;
	
	@POST
    @Path("findActRuEventSubscrInfo")
    public String findActRuEventSubscrInfo(@FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows,@FormParam("params")String params) {
	        UserInfoSessionBean sessionBean = getUserSessionBean();
	        JSONObject queryParamJSON = JSON.parseObject(params);
	        queryParamJSON.put("assignee_", sessionBean.getUserId());
	        String findRunTaskURLInfo = actRuEventSubscrServer.findActRuEventSubscrInfo(queryParamJSON, pageIndex, pageRows);
	        return findRunTaskURLInfo;
    }
}
