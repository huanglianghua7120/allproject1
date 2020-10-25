package saaf.common.fmw.pos.services;

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
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierCertificateEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierCertificate;
import saaf.common.fmw.services.CommonAbstractServices;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商认证与证书
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmPosSupplierCertificateService")
@Path("/srmPosSupplierCertificateService")
public class SrmPosSupplierCertificateService extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierCertificateService.class);
	@Autowired
	private ISrmPosSupplierCertificate iSrmPosSupplierCertificate;

	public SrmPosSupplierCertificateService() {
		super();
	}

	/**
	 * 删除认证与证书
	 * 
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("deleteCertificate")
	@POST
	@Produces("application/json")
	public String deleteCertificate(@FormParam(PARAMS) String params) {
		LOGGER.info("参数：" + params.toString());
		if (StringUtils.isBlank(params)) {
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可删除！", 0, null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPosSupplierCertificate.deleteCertificate(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG),
					jsondata.getInteger(COUNT), jsondata.get(DATA));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "删除失败!", 0, null);
		}
	}

	/**
	 * 查询供应商的认证证书
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
	@Path("findSupplierCertificate")
	public String findSupplierCertificate(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
			@FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			if ("EX".equals(paramJSON.getString("varPlatformCode"))) { // 是供应商查询
				paramJSON.put("supplier_id", paramJSON.getIntValue("varSupplierId"));
			} else if (null == paramJSON.getInteger("supplierId")) {
				paramJSON.put("supplierId", -1);
			}
			Pagination<SrmPosSupplierCertificateEntity_HI_RO> pag = iSrmPosSupplierCertificate
					.findSupplierCertificate(paramJSON, pageIndex, pageRows);
			return JSON.toJSONString(pag);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!", 0, null));
		}
	}

	/**
	 * 资质到期查询
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
	@Path("findSupplierCertificateDue")
	public String findSupplierCertificateDue(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
			@FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);

		try {
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SrmPosSupplierCertificateEntity_HI_RO> page = iSrmPosSupplierCertificate.findSupplierCertificateDue(jsonParams, pageIndex, pageRows);
			return JSONObject.toJSONString(page);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return convertResultJSONObj(ERROR_STATUS, "查询失败!", 0, null);
		}
	}
    /**
     * 保存ekpStatus
     *
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-05-24     谢晓霞             创建
     * =======================================================================
     */
    public void saveCreateWarning() {
        try {
            iSrmPosSupplierCertificate.saveCreateWarning();
        } catch (Exception e) {
            if (e instanceof UtilsException) {
                LOGGER.error("资质到期预警执行服务异常:" + e);
            }
            LOGGER.error("资质到期预警执行服务异常:" + e);
        }
    }
}
