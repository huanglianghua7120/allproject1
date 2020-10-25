package saaf.common.fmw.bpm.services;

import com.alibaba.fastjson.JSONObject;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.bpm.model.inter.ISaafActHiTaskUrl;
import saaf.common.fmw.services.CommonAbstractServices;


@Component("saafActHiTaskUrlService")
@Path("saafActHiTaskUrlService")
public class SaafActHiTaskUrlService extends CommonAbstractServices{
    @Autowired
    private ISaafActHiTaskUrl saafActHiTaskUrlServer;
    public SaafActHiTaskUrlService() {
        super();
    }
    
    /**
     * 查询历史任务
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @POST
    @Path("findMyTaskHistoryInfo")
    public String findMyTaskHistoryInfo(@FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows){
        saafActHiTaskUrlServer.setUserInfoSessionBean(getUserSessionBean());
        UserInfoSessionBean sessionBean = getUserSessionBean();
        JSONObject queryParamJSON = new JSONObject();
        if(!sessionBean.getIsAdmin().equals("Y")){
        	queryParamJSON.put("assignee_", sessionBean.getUserId());
        }
        String findRunTaskURLInfo = saafActHiTaskUrlServer.findActTaskHistoryUrl(queryParamJSON, pageIndex, pageRows);
        return findRunTaskURLInfo;
    }
}
