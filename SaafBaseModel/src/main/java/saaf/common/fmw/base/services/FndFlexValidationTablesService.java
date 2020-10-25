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

import saaf.common.fmw.base.model.inter.IFndFlexValidationTables;


@Component("fndFlexValidationTablesService")
@Path("/fndFlexValidationTablesService")
public class FndFlexValidationTablesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FndFlexValidationTablesService.class);
    @Autowired
    private IFndFlexValidationTables fndFlexValidationTablesServer; // = (FndFlexValidationTablesServer)SaafToolUtils.context.getBean("fndFlexValidationTablesServer");

    public FndFlexValidationTablesService() {
        super();
    }

    //	/restServer/fndFlexValidationTablesService/findFndFlexValidationTablesInfo

    @POST
    @Path("findFndFlexValidationTablesInfo")
    @Consumes("application/json")
    @Produces("application/json")
    public String findFndFlexValidationTablesInfo(String postParam) {
        LOGGER.info(postParam);
        JSONObject paramJSON = JSON.parseObject(postParam);
        //FndFlexValidationTablesServer fndFlexValidationTablesServer = (FndFlexValidationTablesServer)SaafToolUtils.context.getBean("fndFlexValidationTablesServer");
        String resultStr = fndFlexValidationTablesServer.findFndFlexValidationTablesInfo(paramJSON);
        LOGGER.info(resultStr);
        return resultStr;
    }
    //	/restServer/fndFlexValidationTablesService/user

    @GET
    @Path("user")
    @Produces("text/plain")
    public String getUser(@QueryParam("name")
        String name) {
        return "hello " + name;
    }
}
