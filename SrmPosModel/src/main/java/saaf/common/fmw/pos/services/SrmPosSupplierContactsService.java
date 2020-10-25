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
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierContactsEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierContacts;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商联系人
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmPosSupplierContactsService")
@Path("/srmPosSupplierContactsService")
public class SrmPosSupplierContactsService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierContactsService.class);
	@Autowired
	private ISrmPosSupplierContacts iSrmPosSupplierContacts;
	public SrmPosSupplierContactsService() {
		super();
	}

	/**
	 * 查询供应商的联系人
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
	@POST
	@Path("findSupplierContacts")
	public String findSupplierContacts(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			if ("EX".equals(paramJSON.getString("varPlatformCode"))) { //是供应商查询
				paramJSON.put("supplier_id", paramJSON.getIntValue("varSupplierId"));
			}else if(null==paramJSON.getInteger("supplierId")){
				paramJSON.put("supplierId", -1);
			}
			Pagination<SrmPosSupplierContactsEntity_HI_RO> pag = iSrmPosSupplierContacts.findSupplierContacts(paramJSON, pageIndex, pageRows);
			return JSON.toJSONString(pag);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}
	/**
	 * 供应商的删除联系人
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("deleteContact")
	@POST
	public String deleteContact(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPosSupplierContacts.deleteContact(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.get(DATA));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "删除失败!" + e, 0, null);
		}
	}

}
