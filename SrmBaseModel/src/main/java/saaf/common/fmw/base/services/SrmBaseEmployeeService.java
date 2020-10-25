package saaf.common.fmw.base.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafEmployees;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：员工
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseEmployeeService")
@Path("/srmBaseEmployeeService")
public class SrmBaseEmployeeService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseEmployeeService.class);
	@Autowired
	private ISaafEmployees iSrmBaseEmployeeServer;


	/**
	 * 查询采购人员LOV
	 * 
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findEmployeeLov")
	public String findEmployeeLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
			@FormParam(PAGE_ROWS) Integer pageRows) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SaafEmployeesEntity_HI> pag = iSrmBaseEmployeeServer.findEmployeeLov(jsonParams, pageIndex, pageRows);
			LOGGER.info("-->>参数：" + params + "查询成功！");
			return JSON.toJSONString(pag);
		} catch (Exception e) {

			LOGGER.error("查询失败" + e, e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
		}
	}
}
