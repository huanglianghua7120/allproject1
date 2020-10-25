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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.gl.model.inter.ISrmGlDeductionHeaders;
import saaf.common.fmw.gl.model.inter.ISrmGlDeductionLines;
import saaf.common.fmw.services.CommonAbstractServices;

@Path("/srmGlDeductionLinesService")
@Component
public class SrmGlDeductionLinesService extends CommonAbstractServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmGlDeductionLinesService.class);
	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	public SrmGlDeductionLinesService() {
		super();
	}

	@Autowired
	private ISrmGlDeductionLines srmGlDeductionLinesServer;

	@Autowired
	private ISrmGlDeductionHeaders srmGlDeductionHeadersServer;


}

