package saaf.common.fmw.gl.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.gl.model.entities.readonly.SrmGlDeductionHeadersEntity_HI_RO;
import saaf.common.fmw.gl.model.inter.ISrmGlDeductionHeaders;
import saaf.common.fmw.gl.model.inter.ISrmGlDeductionLines;
import saaf.common.fmw.services.CommonAbstractServices;

@Path("/srmGlDeductionHeadersService")
@Component
public class SrmGlDeductionHeadersService extends CommonAbstractServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmGlDeductionHeadersService.class);
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	public SrmGlDeductionHeadersService() {
		super();
	}

	@Autowired
	private ISrmGlDeductionHeaders srmGlDeductionHeadersServer;

	@Autowired
	private ISrmGlDeductionLines srmGlDeductionLinesServer;






	/**
	 * 查询扣款单列表
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 */
	@Path("findDeductionInfoList")
	@POST
	public String findDeductionInfoList(@FormParam("params")
		String params, @FormParam("pageIndex")
		Integer pageIndex, @FormParam("pageRows")
		Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			LOGGER.info("------jsonParam------" + jsonParam.toString());
			Pagination<SrmGlDeductionHeadersEntity_HI_RO> infoList = srmGlDeductionHeadersServer.findDeductionInfoList(jsonParam, pageIndex, pageRows);
			return JSON.toJSONString(infoList);
		}catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("扣款单列表异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "查询扣款单列表失败!" + e, 0, null);
		}
	}

	/**
	 * 查询扣款单号
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 */
	@Path("findDeductionNumber")
	@POST
	public String findDeductionNumber(@FormParam("params")
		String params, @FormParam("pageIndex")
		Integer pageIndex, @FormParam("pageRows")
		Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			Pagination<SrmGlDeductionHeadersEntity_HI_RO> deductionNumbers = srmGlDeductionHeadersServer.findDeductionNumber(jsonParam, pageIndex, pageRows);
			return JSONObject.toJSONString(deductionNumbers);
		} catch (Exception e) {
			LOGGER.error("findDeductionNumber-->查询扣款单号：" + e);
			//e.printStackTrace();
			return CommonAbstractServices.convertResultJSONObj("E", "查询扣款单号失败!" + e, 0, null);
		}

	}


	/**
	 * 查询扣款单创建人
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 */
	@Path("findDeductionCreatedByUser")
	@POST
	public String findDeductionCreatedByUser(@FormParam("params")
		String params, @FormParam("pageIndex")
	  	Integer pageIndex, @FormParam("pageRows")
	  	Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			Pagination<SrmGlDeductionHeadersEntity_HI_RO> deductionCreatedByUsers = srmGlDeductionHeadersServer.findDeductionCreatedByUser(jsonParam, pageIndex, pageRows);
			return JSONObject.toJSONString(deductionCreatedByUsers);
		} catch (Exception e) {
			LOGGER.error("findDeductionNumber-->查询扣款单创建人：" + e);
			//e.printStackTrace();
			return CommonAbstractServices.convertResultJSONObj("E", "查询扣款单创建人失败!" + e, 0, null);
		}

	}





}

