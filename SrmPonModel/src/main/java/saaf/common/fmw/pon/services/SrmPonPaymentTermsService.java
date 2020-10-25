package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonPaymentTermsEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonPaymentTerms;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonPaymentTermsService.java
 * Description：寻源--付款条件信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonPaymentTermsService")
@Path("/srmPonPaymentTermsService")
public class SrmPonPaymentTermsService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonPaymentTermsService.class);
	@Autowired
	private ISrmPonPaymentTerms iSrmPonPaymentTerms;
	public SrmPonPaymentTermsService() {
		super();
	}

	/**
	 * Description：付款条件查询（分页）
	 * @param params
	 * @param curIndex
	 * @param pageSize
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findSrmPonPaymentTermsInfo")
	@Produces("application/json")
	public String findSrmPonPaymentTermsInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1")
			Integer curIndex, @FormParam(PAGE_ROWS) @DefaultValue("15") Integer pageSize) {
		LOGGER.info(params);
		try{
			JSONObject paramJSON = this.parseObject(params);
			Pagination<SrmPonPaymentTermsEntity_HI_RO> list = iSrmPonPaymentTerms.findSrmPonPaymentTermsInfo(paramJSON,curIndex,pageSize);
			return JSON.toJSONString(list);
		}catch (Exception e){
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
		}
	}

	/**
	 * Description：保存付款条件及其子表
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("savePonPaymentTermsAll")
	@Produces("application/json")
	public String savePonPaymentTermsAll(@FormParam(PARAMS) String params) {
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonPaymentTerms.savePonPaymentTermsAll(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		}catch (Exception e){
			LOGGER.error("--------------------------->操作失败！参数："+params.toString()+"，异常："+ e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"操作失败！",0,null);
		}
	}

	/**
	 * Description：批量删除付款条件
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("deletePonPaymentTermByBatch")
	public String deleteAuctionHeader(@FormParam(PARAMS) String params) {
        LOGGER.info("参数："+params.toString());
        if(StringUtils.isBlank(params)){
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
        }
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonPaymentTerms.deletePonPaymentTermByBatch(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("删除失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E","操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：批量失效付款条件
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("updateInvalidPonPaymentTermByBatch")
	public String updateInvalidPonPaymentTermByBatch(@FormParam(PARAMS) String params) {
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonPaymentTerms.updateInvalidPonPaymentTermByBatch(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("失效失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}
}
