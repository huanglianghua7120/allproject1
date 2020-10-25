package saaf.common.fmw.gl.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.gl.model.entities.readonly.SrmGlStatementHeadersEntity_HI_RO;
import saaf.common.fmw.gl.model.inter.ISrmGlStatementHeaders;
import saaf.common.fmw.services.CommonAbstractServices;

@Path("/srmGlStatementHeadersService")
@Component
public class SrmGlStatementHeadersService extends CommonAbstractServices {
private static final Logger LOGGER = LoggerFactory.getLogger(SrmGlStatementHeadersService.class);
	private static final String PARAMS = "params";
	private static final String PAGE_INDEX = "pageIndex";
	private static final String PAGE_ROWS = "pageRows";
	@Autowired
	private ISrmGlStatementHeaders srmGlStatementHeadersServer;

	public SrmGlStatementHeadersService() {
		super();
	}

	/**
	 * 查询对账单
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 */
	@POST
	@Path("findStatementList")
	public String findStatementList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			if ("EX".equals(jsonParams.getString("varPlatformCode"))) { //是供应商查询
				jsonParams.put("supplier_id", jsonParams.getIntValue("varSupplierId"));
			}
			Pagination<SrmGlStatementHeadersEntity_HI_RO> pag= srmGlStatementHeadersServer.findStatementList(jsonParams, pageIndex, pageRows);
			return JSON.toJSONString(pag);
		} catch (Exception e) {
			LOGGER.error("查询失败" + e,e);
			return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e.getMessage(), 0, null);
		}
	}

	/**
	 * 查询对账单lov
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 */
	@POST
	@Path("findStatementLov")
	public String findStatementLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			if ("EX".equals(jsonParams.getString("varPlatformCode"))) { //是供应商查询
				jsonParams.put("supplier_id", jsonParams.getIntValue("varSupplierId"));
			}
			Pagination<SrmGlStatementHeadersEntity_HI_RO> pag = srmGlStatementHeadersServer.findStatementLov(jsonParams, pageIndex, pageRows);
			LOGGER.info("-->>参数：" + params + "查询成功！");
			return JSON.toJSONString(pag);
		} catch (Exception e) {
			LOGGER.error("查询失败" + e,e);
			return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e.getMessage(), 0, null);
		}
	}


}

