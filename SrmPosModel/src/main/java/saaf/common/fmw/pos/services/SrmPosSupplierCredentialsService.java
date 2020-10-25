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
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierCredentialsEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierInfoEntity_HI_RO;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierCredentials;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商资质信息
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmPosSupplierCredentialsService")
@Path("/srmPosSupplierCredentialsService")
public class SrmPosSupplierCredentialsService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierCredentialsService.class);
	@Autowired
	private ISrmPosSupplierCredentials iSrmPosSupplierCredentials;
	public SrmPosSupplierCredentialsService() {
		super();
	}

	/**
	 *查询供应商的资质信息
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
	@Path("findSrmPosSupplierCredentialsInfo")
	@Produces("application/json")
	//	//srmPosSupplierCredentialsService/findSrmPosSupplierCredentialsInfo
	public String findSrmPosSupplierCredentialsInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX)
        @DefaultValue("1") Integer curIndex, @FormParam(PAGE_ROWS) @DefaultValue("10") Integer pageSize) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			if ("EX".equals(paramJSON.getString("varPlatformCode"))) { //是供应商查询
				paramJSON.put("supplier_id", paramJSON.getIntValue("varSupplierId"));
			}else if(null==paramJSON.getInteger("supplierId")){
				paramJSON.put("supplierId", -1);
			}
			Pagination<SrmPosSupplierCredentialsEntity_HI_RO> pag = iSrmPosSupplierCredentials.findSrmPosSupplierCredentialsInfo(paramJSON,curIndex,pageSize);
			return JSON.toJSONString(pag);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
		}
	}
}
