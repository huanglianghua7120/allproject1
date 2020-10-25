package saaf.common.fmw.pos.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierSitesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierSites;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
/**
 * 供应商地址
 *
 * @param
 * @return
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmPosSupplierSitesService")
@Path("/srmPosSupplierSitesService")
public class SrmPosSupplierSitesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierSitesService.class);
	@Autowired
	private ISrmPosSupplierSites srmPosSupplierSitesServer;
	public SrmPosSupplierSitesService() {
		super();
	}

	/**
	 * 查询配置文件行
	 *
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("findSupplierSites")
	@POST
	public String findSupplierSites(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject jsonData = new JSONObject();
			List<SrmPosSupplierSitesEntity_HI_RO> siteList = srmPosSupplierSitesServer.findSupplierSites(jsonParam);
			jsonData.put("siteList", siteList);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功!", 1, jsonData);
		} catch (NotLoginException e) {
			return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询配置文件失败!" + e, 0, null);
		}
	}

	/**
	 * 根据供应商ID，查询有效的供应商地点
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findQuitSites")
	public  String findQuitSites(@FormParam(PARAMS) String params){
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPosSupplierSitesEntity_HI_RO> rowSet = srmPosSupplierSitesServer.findQuitSites(jsonParams);
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("S", "查询成功!", rowSet.size(), rowSet));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

	/**
	 * 根据供应商ID，查询有效的供应商地点
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSiteListByEffective")
	public String findSiteListByStatus(@FormParam(PARAMS) String params){
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPosSupplierSitesEntity_HI_RO> rowSet = srmPosSupplierSitesServer.findSiteListByEffective(jsonParams);
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("S", "查询成功!", 0, rowSet));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

	/**
	 * 根据供应商ID，查询可新增的地点
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSiteListByNew")
	public String findSiteListByNew(@FormParam(PARAMS) String params){
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPosSupplierSitesEntity_HI_RO> rowSet = srmPosSupplierSitesServer.findSiteListByNew(jsonParams);
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("S", "查询成功!", 0, rowSet));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}
	
	
	/**
	 * 根据供应商ID查询地点LOV
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSupplierSiteLov")
	public String findSupplierSiteLov(@FormParam(PARAMS) String params,@FormParam(PAGE_INDEX) Integer pageIndex,
			@FormParam(PAGE_ROWS) Integer pageRows){
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SrmPosSupplierSitesEntity_HI_RO> rowSet = srmPosSupplierSitesServer.findSupplierSiteLov(jsonParams,pageIndex,pageRows);
			return JSON.toJSONString(rowSet);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}
	
	/**
	 * 送货通知查询供应商地点
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSupplierSiteForNotice")
	public String findSupplierSiteForNotice(@FormParam(PARAMS) String params){
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPosSupplierSitesEntity_HI_RO> rowSet = srmPosSupplierSitesServer.findSupplierSiteForNotice(jsonParams);
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("S", "查询成功!", 0, rowSet));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

	/**
	 * 查询配置文件行
	 *
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("findSupplierPlanSites")
	@POST
	public String findSupplierPlanSites(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			List<SrmPosSupplierSitesEntity_HI_RO> siteList = srmPosSupplierSitesServer.findSupplierPlanSites(jsonParam);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功!", siteList.size(), siteList);
		} catch (NotLoginException e) {
			return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询配置文件失败!" + e, 0, null);
		}
	}

}
