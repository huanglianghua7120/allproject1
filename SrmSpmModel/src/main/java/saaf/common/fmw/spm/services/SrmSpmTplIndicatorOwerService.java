package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.inter.ISrmSpmTplIndicatorOwer;

@Component("srmSpmTplIndicatorOwerService")
@Path("/srmSpmTplIndicatorOwerService")
public class SrmSpmTplIndicatorOwerService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmTplIndicatorOwerService.class);
	@Autowired
	private ISrmSpmTplIndicatorOwer srmSpmTplIndicatorOwerServer;
	public SrmSpmTplIndicatorOwerService() {
		super();
	}

	/**
	 * 
	 * @param params
	 * @param curIndex
	 * @param pageSize
	 * @return
	 */
	@POST
	@Path("findSrmSpmTplIndicatorOwerInfo")
	@Consumes("application/json")
	@Produces("application/json")
	//	/restServer/srmSpmTplIndicatorOwerService/findSrmSpmTplIndicatorOwerInfo
	public String findSrmSpmTplIndicatorOwerInfo(@FormParam("params")
        String params, @FormParam("pageIndex")
        @DefaultValue("1")
        Integer curIndex, @FormParam("pageRows")
        @DefaultValue("10")
        Integer pageSize) {
		LOGGER.info(params);
		JSONObject paramJSON = JSON.parseObject(params);
		String resultStr = JSONObject.toJSONString(srmSpmTplIndicatorOwerServer.findSrmSpmTplIndicatorOwerInfo(paramJSON));
		LOGGER.info(resultStr);
		return resultStr;
	}
}
