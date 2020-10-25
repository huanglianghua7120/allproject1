package saaf.common.fmw.gl.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/srmGlStatementLinesService")
public class SrmGlStatementLinesService{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmGlStatementLinesService.class);
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;
	public SrmGlStatementLinesService() {
		super();
	}

}

