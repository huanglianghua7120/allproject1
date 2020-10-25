package saaf.common.fmw.common.services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 统一异常处理器
 */
@Provider
public class ExceptionMapperSupport implements ExceptionMapper<Exception> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMapperSupport.class);

//	@Context
//	private HttpServletRequest request;
//
//	@Context
//	private ServletContext servletContext;

//	@Context
//	protected HttpServletResponse response;

	/**
	 * 异常处理
	 * 
	 * @param exception
	 * @return 异常处理后的Response对象
	 */
	@Override
	public Response toResponse(Exception exception) {
		Map<String, Object> entity = new HashMap<String, Object>();
		entity.put("msg", exception.getMessage());
		// 处理unchecked exception
		LOGGER.error(exception.getMessage(), exception);
		if (exception instanceof AuthorizationException) {
			entity.put("status", "UNAUTHORIZED");
			return Response.ok(entity, MediaType.APPLICATION_JSON).status(Status.UNAUTHORIZED).build();

		} else {
			entity.put("status", "E");
			return Response.ok(entity, MediaType.APPLICATION_JSON).status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
