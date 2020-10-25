package saaf.common.fmw.bpm.services;


import java.io.UnsupportedEncodingException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.ActGeBytearrayEntity_HI;
import saaf.common.fmw.bpm.model.inter.IActGeBytearray;
import saaf.common.fmw.services.CommonAbstractServices;

import com.alibaba.fastjson.JSONObject;


@Component("actGeBytearrayService")
@Path("actGeBytearrayService")
public class ActGeBytearrayService extends CommonAbstractServices {

    
    @Autowired
    private IActGeBytearray actGeBytearrayServer;

    public ActGeBytearrayService() {
        super();
    }

    @POST
    @Path("findProcdefXml")
    public String findProcdefXml(@FormParam("resourceName") String resourceName,
    		@FormParam("deploymentId")String deploymentId) throws UnsupportedEncodingException {
       
    	ActGeBytearrayEntity_HI actGeBytearrayEntity_HI = actGeBytearrayServer.getActGeBytearrayEntity(resourceName, deploymentId);
    	
    	JSONObject retJSON = new JSONObject();
    	retJSON.put("descriptor", new String(actGeBytearrayEntity_HI.getBytes_(),"utf-8"));
    	
        return retJSON.toJSONString();
    }

}
