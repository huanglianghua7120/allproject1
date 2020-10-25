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
import saaf.common.fmw.pos.model.entities.readonly.SrmPosQualificationSitesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierSitesEntity_HI_RO;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.pos.model.inter.ISrmPosQualificationSites;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商资质
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
@Component("srmPosQualificationSitesService")
@Path("/srmPosQualificationSitesService")
public class SrmPosQualificationSitesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosQualificationSitesService.class);
	@Autowired
	private ISrmPosQualificationSites srmPosQualificationSitesServer;
	public SrmPosQualificationSitesService() {
		super();
	}

	/**
	 * 
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
	@Path("findSrmPosQualificationSitesInfo")
	@Consumes("application/json")
	@Produces("application/json")
	public String findSrmPosQualificationSitesInfo(@FormParam("params")
        String params, @FormParam("pageIndex")
        @DefaultValue("1")
        Integer curIndex, @FormParam("pageRows")
        @DefaultValue("10")
        Integer pageSize) {
		
		return null;
	}

	/**
	 * 根据地址ID和资质审查ID，查询资质审查单下面有效的供应商地点
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findQualificationSiteListByA")
	public String findQualificationSiteListByA(@FormParam(PARAMS) String params){
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPosQualificationSitesEntity_HI_RO> rowSet = srmPosQualificationSitesServer.findQualificationSiteListByA(jsonParams);
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
	 * 根据地址ID和资质审查ID，查询资质审查单下面新增的供应商地点
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findQualificationSiteListByNotA")
	public String findQualificationSiteListByNotA(@FormParam(PARAMS) String params){
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPosQualificationSitesEntity_HI_RO> rowSet = srmPosQualificationSitesServer.findQualificationSiteListByNotA(jsonParams);
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
	 * Description：保存资质审查的地点信息
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * qualificationSiteId  资质审查地点ID  NUMBER  Y
	 * qualificationId  资质审查ID，关联表:srm_pos_qualification_info  NUMBER  Y
	 * supplierSiteId  供应商地点ID  NUMBER  N
	 * supplierAddressId  供应商地址ID  NUMBER  N
	 * siteName  地点名称  VARCHAR2  N
	 * orgId  业务实体ID(关联saaf_institution)  NUMBER  N
	 * siteStatus  地点状态(POS_SUPPLIER_SITE_STATUS)  VARCHAR2  N
	 * purchaseFlag  可采购(Y/N)  VARCHAR2  N
	 * paymentFlag  可付款(Y/N)  VARCHAR2  N
	 * frozeFlag  已冻结(Y/N)  VARCHAR2  N
	 * unfrozeDate  解冻时间  DATE  N
	 * qualifiedDate  合格时间  DATE  N
	 * invalidDate  失效时间  DATE  N
	 * temporarySiteFlag  临时地点标识  VARCHAR2  N
	 * addFlag  新增标识(Y/N)  VARCHAR2  N
	 * qualificationSiteId  资质审查地点ID  NUMBER  Y
	 * qualificationId  资质审查ID，关联表:srm_pos_qualification_info  NUMBER  Y
	 * supplierSiteId  供应商地点ID  NUMBER  N
	 * supplierAddressId  供应商地址ID  NUMBER  N
	 * siteName  地点名称  VARCHAR2  N
	 * orgId  业务实体ID(关联saaf_institution)  NUMBER  N
	 * siteStatus  地点状态(POS_SUPPLIER_SITE_STATUS)  VARCHAR2  N
	 * purchaseFlag  可采购(Y/N)  VARCHAR2  N
	 * paymentFlag  可付款(Y/N)  VARCHAR2  N
	 * frozeFlag  已冻结(Y/N)  VARCHAR2  N
	 * unfrozeDate  解冻时间  DATE  N
	 * qualifiedDate  合格时间  DATE  N
	 * invalidDate  失效时间  DATE  N
	 * temporarySiteFlag  临时地点标识  VARCHAR2  N
	 * addFlag  新增标识(Y/N)  VARCHAR2  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

	@Path("saveQualificationSites")
	@POST
	public String saveQualificationSites(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject posJson = srmPosQualificationSitesServer.saveQualificationSites(jsonParam);
			return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
		}catch (Exception e) {
			LOGGER.error("保存资质审查的地点信息失败！" + e,e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "保存资质审查的地点信息失败!" + e, 0, null);
		}
	}

}
