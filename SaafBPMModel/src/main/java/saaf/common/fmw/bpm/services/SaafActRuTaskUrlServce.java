package saaf.common.fmw.bpm.services;

import com.alibaba.fastjson.JSONObject;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.bpm.model.inter.ISaafActRuTaskUrl;
import saaf.common.fmw.services.CommonAbstractServices;

@Component("saafActRuTaskUrlServce")
@Path("/saafActRuTaskUrlServce")
public class SaafActRuTaskUrlServce extends CommonAbstractServices{
    @Autowired
    private ISaafActRuTaskUrl saafActRuTaskUrlServer;
    public SaafActRuTaskUrlServce() {
        super();
    }
    
    /**
     * 查询当前用户的待办信息
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("findMyTaskInfo")
    public String findMyTaskInfo(@FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows){
        saafActRuTaskUrlServer.setUserInfoSessionBean(getUserSessionBean());
        UserInfoSessionBean sessionBean = getUserSessionBean();
        JSONObject queryParamJSON = new JSONObject();
        queryParamJSON.put("assignee_", sessionBean.getUserId());
        String findRunTaskURLInfo = saafActRuTaskUrlServer.findRunTaskURLInfo(queryParamJSON, pageIndex, pageRows);
        return findRunTaskURLInfo;
    }
}
