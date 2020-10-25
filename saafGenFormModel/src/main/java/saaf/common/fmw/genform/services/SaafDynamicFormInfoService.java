package saaf.common.fmw.genform.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.genform.model.entities.SaafDynamicFormInfoEntity_HI;
import saaf.common.fmw.genform.model.inter.ISaafDynamicFormInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.base.adf.common.utils.SToolUtils;

import saaf.common.fmw.services.CommonAbstractServices;

@Component("saafDynamicFormInfoService")
@Path("/saafDynamicFormInfoService")
public class SaafDynamicFormInfoService extends CommonAbstractServices{
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafDynamicFormInfoService.class);
    @Autowired
    private ISaafDynamicFormInfo saafDynamicFormInfoServer;

    public SaafDynamicFormInfoService() {
        super();
    }

    @POST
    @Path("findPagenation")
    @Produces("application/json")
    public String findPagenation(@FormParam("params")
        String postParam, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageSize")
        Integer pageSize) {
        LOGGER.info(pageIndex + "," + pageSize + "," + postParam);
        JSONObject paramJSON = JSON.parseObject(postParam);
        if (pageSize == null) {
            pageSize = 10;
        }

        //		SaafDynamicFormInfoServer saafDynamicFormInfoServer = (SaafDynamicFormInfoServer)SaafToolUtils.context.getBean("saafDynamicFormInfoServer");
        String resultStr = saafDynamicFormInfoServer.findPagenation(pageIndex, pageSize, paramJSON);
        LOGGER.info(resultStr);

        return resultStr;
    }

    //	//saafDynamicFormInfoService/findSaafDynamicFormInfoInfo
    @POST
    @Path("findSaafDynamicFormInfoInfo")
    @Produces("application/json")
    public String findSaafDynamicFormInfoInfo(@FormParam("params")
        String postParam) {
        LOGGER.info(postParam);
        JSONObject paramJSON = JSON.parseObject(postParam);
        //		SaafDynamicFormInfoServer saafDynamicFormInfoServer = (SaafDynamicFormInfoServer)SaafToolUtils.context.getBean("saafDynamicFormInfoServer");
        String resultStr = saafDynamicFormInfoServer.findSaafDynamicFormInfoInfo(paramJSON);
        LOGGER.info(resultStr);

        return resultStr;
    }

    @POST
    @Path("getSaafDynamicFormInfo")
    @Produces("application/json")
    public String getSaafDynamicFormInfo(@FormParam("id")
        String id) {
        LOGGER.info("id:" + id);
        SaafDynamicFormInfoEntity_HI model = saafDynamicFormInfoServer.getById(Integer.parseInt(id));
        String retData = JSON.toJSONString(model);
        JSONObject ret = SToolUtils.convertResultJSONObj("S", "", 1, retData);
        return ret.toJSONString();
    }

    @POST
    @Path("deleteSaafDynamicFormInfo")
    @Produces("application/json")
    public String deleteSaafDynamicFormInfo(@FormParam("id")
        String id) {
        LOGGER.info("id:" + id);

        boolean ret = saafDynamicFormInfoServer.deleteSaafDynamicFormInfo(Integer.parseInt(id));

        JSONObject retJson = new JSONObject();
        if (ret) {
            retJson.put("msg", "success");
            retJson.put("status", "S");
        } else {
            retJson.put("msg", "fail");
            retJson.put("status", "F");
        }
        String resultStr = retJson.toJSONString();
        LOGGER.info(resultStr);

        return resultStr;
    }

    //	//saafDynamicFormInfoService/user
    @GET
    @Path("user")
    @Produces("text/plain")
    public String getUser(@QueryParam("name")
        String name) {
        return "hello " + name;
    }
}
