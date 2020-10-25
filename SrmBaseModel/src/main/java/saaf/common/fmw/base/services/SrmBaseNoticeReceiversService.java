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

import saaf.common.fmw.base.model.entities.readonly.SrmBaseNoticesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseNoticeReceivers;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：公告
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Path("/srmBaseNoticeReceiversService")
@Component
public class SrmBaseNoticeReceiversService extends CommonAbstractServices{
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SrmBaseNoticeReceiversService.class);

	@Autowired
	private ISrmBaseNoticeReceivers srmBaseNoticeReceiversServer;
	
	
	/**
	 * 查询公告接收者(组织)
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("findInstReceiversList")
	@POST
	public String findInstReceiversList(@FormParam("params") String params,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageRows") Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			LOGGER.info("------jsonParam-----" + jsonParam.toString());
			Pagination<SrmBaseNoticesEntity_HI_RO> instList = srmBaseNoticeReceiversServer
					.findInstReceiversList(jsonParam, pageIndex, pageRows);
			return JSON.toJSONString(instList);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询列表异常：" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询列表失败!"
					+ e, 0, null);
		}
	}
	
	/**
	 * 查询公告接收者(员工)
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("findEmployeeReceiversList")
	@POST
	public String findEmployeeReceiversList(@FormParam("params") String params,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageRows") Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			LOGGER.info("------jsonParam-----" + jsonParam.toString());
			Pagination<SrmBaseNoticesEntity_HI_RO> employeeList = srmBaseNoticeReceiversServer
					.findEmployeeReceiversList(jsonParam, pageIndex, pageRows);
			return JSON.toJSONString(employeeList);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询列表异常：" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询列表失败!"
					+ e, 0, null);
		}
	}
	
	/**
	 * 查询公告接收者(供应商)
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("findSupplierReceiversList")
	@POST
	public String findSupplierReceiversList(@FormParam("params") String params,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageRows") Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			LOGGER.info("------jsonParam-----" + jsonParam.toString());
			Pagination<SrmBaseNoticesEntity_HI_RO> supplierList = srmBaseNoticeReceiversServer
					.findSupplierReceiversList(jsonParam, pageIndex, pageRows);
			return JSON.toJSONString(supplierList);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询列表异常：" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询列表失败!"
					+ e, 0, null);
		}
	}
	
	/**
	 * 删除公告接收者
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("deleteNoticeReceivers")
	@POST
	public String deleteNoticeReceivers(@FormParam("params") String params) {
		LOGGER.info("删除信息，参数：" + params.toString());
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmBaseNoticeReceiversServer.deleteNoticeReceivers(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(
					jsondata.getString("status"), jsondata.getString("msg"),
					jsondata.getInteger("count"), jsondata.get("data"));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("--->>删除失败！参数：" + params + ",异常：" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "删除失败!", 0,
					null);
		}
	}
}
