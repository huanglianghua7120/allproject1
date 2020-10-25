package saaf.common.fmw.base.services;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.inter.IFndFlexValues;


@Component("fndFlexValuesService")
@Path("/fndFlexValuesService")
public class FndFlexValuesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FndFlexValuesService.class);
    @Autowired
    private IFndFlexValues fndFlexValuesServer; // = (FndFlexValuesServer)SaafToolUtils.context.getBean("fndFlexValuesServer");

    public FndFlexValuesService() {
        super();
    }

    //	/restServer/fndFlexValuesService/findFndFlexValuesInfo

    @POST
    @Path("findFndFlexValuesInfo")
    @Consumes("application/json")
    @Produces("application/json")
    public String findFndFlexValuesInfo(String postParam) {
        LOGGER.info(postParam);
        JSONObject paramJSON = JSON.parseObject(postParam);
        //		FndFlexValuesServer fndFlexValuesServer = (FndFlexValuesServer)SaafToolUtils.context.getBean("fndFlexValuesServer");
        String resultStr = fndFlexValuesServer.findFndFlexValuesInfo(paramJSON);
        LOGGER.info(resultStr);
        return resultStr;
    }
    //	/restServer/fndFlexValuesService/user

    @GET
    @Path("user")
    @Produces("text/plain")
    public String getUser(@QueryParam("name")
        String name) {
        return "hello " + name;
    }
}
