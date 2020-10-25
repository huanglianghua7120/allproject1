package saaf.common.fmw.pos.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

import com.base.adf.common.utils.SToolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierQuitSitesEntity_HI_RO;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierQuitSites;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商退出地点
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmPosSupplierQuitSitesService")
@Path("/srmPosSupplierQuitSitesService")
public class SrmPosSupplierQuitSitesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierQuitSitesService.class);
	@Autowired
	private ISrmPosSupplierQuitSites srmPosSupplierQuitSitesServer;
	public SrmPosSupplierQuitSitesService() {
		super();
	}

	/**
	 *  获取供应商地址
	 * @param params
	 * @param curIndex
	 * @param pageSize
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSrmPosSupplierQuitSitesInfo")
	@Consumes("application/json")
	@Produces("application/json")
	//	/restServer/srmPosSupplierQuitSitesService/findSrmPosSupplierQuitSitesInfo
	public String findSrmPosSupplierQuitSitesInfo(@FormParam("params")
        String params, @FormParam("pageIndex")
        @DefaultValue("1")
        Integer curIndex, @FormParam("pageRows")
        @DefaultValue("10")
        Integer pageSize) throws Exception {
		LOGGER.info(params);
		JSONObject paramJSON = this.parseObject(params);
		String resultStr = JSONObject.toJSONString(srmPosSupplierQuitSitesServer.findSrmPosSupplierQuitSitesInfo(paramJSON));
		LOGGER.info(resultStr);
		return resultStr;
	}

	/**
	 * 查询供应商地点
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSupplierSites")
	public  String findSupplierSites(@FormParam(PARAMS) String params){
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPosSupplierQuitSitesEntity_HI_RO> rowSet = srmPosSupplierQuitSitesServer.findSupplierSites(jsonParams);
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("S", "查询成功!", 0, rowSet));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}
}
