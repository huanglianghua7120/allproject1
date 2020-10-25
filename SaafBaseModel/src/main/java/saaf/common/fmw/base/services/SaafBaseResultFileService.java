package saaf.common.fmw.base.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.inter.ISaafBaseResultFile;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;

@Component("saafBaseResultFileService")
@Path("/saafBaseResultFileService")
public class SaafBaseResultFileService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SaafBaseResultFileService.class);
	@Autowired
	private ISaafBaseResultFile iSaafBaseResultFile;
	public SaafBaseResultFileService() {
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
	@Path("findSaafBaseResultFileInfo")
	@Produces("application/json")
	//	//saafBaseResultFileService/findSaafBaseResultFileInfo
	public String findSaafBaseResultFileInfo(@FormParam("params")
        String params, @FormParam("pageIndex")
        @DefaultValue("1")
        Integer curIndex, @FormParam("pageRows")
        @DefaultValue("10")
        Integer pageSize) {
		LOGGER.info(params);
		return null;
	}
}
