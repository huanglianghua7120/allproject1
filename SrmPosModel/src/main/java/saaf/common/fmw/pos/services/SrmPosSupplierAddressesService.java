package saaf.common.fmw.pos.services;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierAddressesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierAddresses;
import saaf.common.fmw.services.CommonAbstractServices;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商地址
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmPosSupplierAddressesService")
@Path("/srmPosSupplierAddressesService")
public class SrmPosSupplierAddressesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierAddressesService.class);
	@Autowired
	private ISrmPosSupplierAddresses iSrmPosSupplierAddresses;
	public SrmPosSupplierAddressesService() {
		super();
	}

	/**
	 * 查询供应商地址
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
	@Path("findSupplierAddresses")
	public String findSupplierhAddress(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			if ("EX".equals(paramJSON.getString("varPlatformCode"))) { //是供应商查询
				paramJSON.put("supplier_id", paramJSON.getIntValue("varSupplierId"));
			}else if(null==paramJSON.getInteger("supplierId")){
				paramJSON.put("supplierId", -1);
			}
			Pagination<SrmPosSupplierAddressesEntity_HI_RO> pag = iSrmPosSupplierAddresses.findSupplierAddresses(paramJSON,pageIndex,pageRows);
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
	 * 查询供应商地址
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
    @Path("findSupplierAddressesById")
    public String findSupplierAddressesById(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            List<SrmPosSupplierAddressesEntity_HI_RO> LookupCodelist = iSrmPosSupplierAddresses.findSupplierAddressesById(jsonParams);
            LOGGER.info("--->>findSupplierAddressesList-->>参数：" + params + "查询供应商地址成功！");
            return CommonAbstractServices.convertResultJSONObj("S", "查询供应商地址成功！", LookupCodelist.size(), LookupCodelist);
       
        } catch (Exception e) {
            
            LOGGER.error("查询供应商地址失败" + e,e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
            return CommonAbstractServices.convertResultJSONObj("E", "查询供应商地址失败!" + e, 0, null);
        }
    }

	/**
	 * 删除供应商地址及其子表地点
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("deleteSupplierAddress")
	@POST
	@Produces("application/json")
	public String deleteSupplierBank(@FormParam(PARAMS)String params){
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可删除！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPosSupplierAddresses.deleteSupplierAddress(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.get(DATA));
		}catch (Exception e){
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "删除失败!" + e, 0, null);
		}
	}
}
