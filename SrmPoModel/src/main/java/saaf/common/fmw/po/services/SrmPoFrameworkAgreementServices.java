package saaf.common.fmw.po.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeadersEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoFrameworkAgreement;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Project Name：SrmPoFrameworkAgreementServices
 * Company Name：SIE
 * Program Name：
 * Description：采购框架协议
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Path("/srmPoFrameworkAgreementServices")
@Component
public class SrmPoFrameworkAgreementServices extends CommonAbstractServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoFrameworkAgreementServices.class);

	public SrmPoFrameworkAgreementServices() {
		super();
	}

	@Autowired
	private ISrmPoFrameworkAgreement srmPoFrameworkAgreementServer;


    /**
     * Description：查询采购框架协议信息
     * @param params 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("findPoFrameworkAgreementList")
	public String findOrderInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
			@FormParam(PAGE_ROWS) Integer pageRows) {
		try {
			LOGGER.info("-->>参数：" + params);
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SrmPoHeadersEntity_HI_RO> data = srmPoFrameworkAgreementServer.findPoFrameworkAgreementList(jsonParams, pageIndex, pageRows);
			return JSONObject.toJSONString(data);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}


    /**
     * Description：采购框架协议审批，拒绝操作(变更采购框架协议各种状态)
     * @param params 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("updatePoFrameworkAgreement")
	public String updatePoFrameworkAgreement(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject data = srmPoFrameworkAgreementServer.updatePoFrameworkAgreement(jsonParams);
			return convertResultJSONObj(data.getString(STATUS), data.getString(MSG), data.getInteger(COUNT), data.get(DATA));
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}


    /**
     * Description：删除采购框架协议行信息
     * @param params 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("deletePoFrameworkAgreement")
	public String deletePoFrameworkAgreement(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmPoFrameworkAgreementServer.deletePoFrameworkAgreement(jsonParams));
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}


    /**
     * Description：采购框架协议保存，提交操作
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * shipLocationCode  收货方  VARCHAR2  N
     * locationCode  收单方  VARCHAR2  N
     * supPoFileId  供应商附件ID  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * agreementClause  协议条款  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * supplierReadTime  供应商查看日期
     DATE  N
     * exchangeRate  汇率  NUMBER  N
     * exchangeRateDate  汇率日期  DATE  N
     * syncErpFlag  是否同步到erp  VARCHAR2  N
     * syncErpMsg  同步到erp异常信息  VARCHAR2  N
     * isGlobal  是否全局  VARCHAR2  N
     * orderType  订单类型  VARCHAR2  N
     * erpOrderNo  erp订单编号  VARCHAR2  N
     * bazaarOrderNo  市场订单  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * buyerExecuteId  采购履行专员  NUMBER  N
     * organizationId  库存组织  NUMBER  N
     * prNumber  采购申请单号  VARCHAR2  N
     * locationCode  收货地点  VARCHAR2  N
     * poType  类型  VARCHAR2  N
     * shipToOrganizationId  收货组织  NUMBER  N
     * shipLocationCode    VARCHAR2  N
     * shipToLocationCode  收货方  VARCHAR2  N
     * billToLocationCode  收单方  VARCHAR2  N
     * shipToLocationId  收货方  NUMBER  N
     * billToLocationId  收单方  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * isGlobal  是否全局  VARCHAR2  N
     * agreementClause  协议条款  VARCHAR2  N
     * contractId  合同ID，关联表：srm_okc_contracts  NUMBER  N
     * contractCode  合同编号  VARCHAR2  N
     * contractTemplateId  合同模板ID，关联表：srm_okc_contract_templates  NUMBER  N
     * contractFileId  合同文档ID  NUMBER  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    @POST
	@Path("saveOrSubmitPoFrameworkAgreement")
	public String saveOrSubmitPoFrameworkAgreement(@FormParam(PARAMS) String params) throws Exception{
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject data = srmPoFrameworkAgreementServer.saveOrSubmitPoFrameworkAgreement(jsonParams);
			return convertResultJSONObj(data.getString(STATUS), data.getString(MSG), data.getInteger(COUNT), data.get(DATA));
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}


    /**
     * Description：查询采购框架协议头信息
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("findPoFrameworkAgreementHeaderInfo")
	public String findPoFrameworkAgreementHeaderInfo(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			if (null == jsonParams.getInteger("poHeaderId")) {
				return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
			}
			List<SrmPoHeadersEntity_HI_RO> data = srmPoFrameworkAgreementServer.findPoFrameworkAgreementHeaderInfo(jsonParams);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功!", 1, data);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}


    /**
     * Description：查询采购框架协议行物料信息
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("findPoFrameworkAgreementLine_itemInfo_page")
	public String findPoFrameworkAgreementLine_itemInfo_page(@FormParam(PARAMS) String params) {
		try {
			Pagination<SrmPoHeadersEntity_HI_RO> page = new Pagination<SrmPoHeadersEntity_HI_RO>();
			JSONObject jsonParams = this.parseObject(params);
			if (null == jsonParams.getInteger("poHeaderId")) {
				return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
			}
			List<SrmPoHeadersEntity_HI_RO> data = srmPoFrameworkAgreementServer.findPoFrameworkAgreementLineItemInfo(jsonParams);
			page.setData(data);
			return JSONObject.toJSONString(page);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}
    /**
     * Description：查询采购框架协议行物料信息分页查询
     * @param params 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("findPoFrameworkAgreementLineItemInfoPage")
	public String findPoFrameworkAgreementLineItemInfoPage(@FormParam(PARAMS) String params,@FormParam(PAGE_INDEX) @DefaultValue("1") Integer pageIndex,
															 @FormParam(PAGE_ROWS) @DefaultValue("10") Integer pageRows) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			if (null == jsonParams.getInteger("poHeaderId")) {
				return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
			}
			Pagination<SrmPoHeadersEntity_HI_RO> data = srmPoFrameworkAgreementServer.findPoFrameworkAgreementLineItemInfoPage(jsonParams, pageIndex, pageRows);
			return JSONObject.toJSONString(data);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}

    /**
     * Description：查询采购框架协议行应用组织信息
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("findPoFrameworkAgreementLine_appliedOrganization_page")
	public String findPoFrameworkAgreementLine_appliedOrganization_page(@FormParam(PARAMS) String params) {
		try {
			Pagination<SrmPoHeadersEntity_HI_RO> page = new Pagination<SrmPoHeadersEntity_HI_RO>();
			JSONObject jsonParams = this.parseObject(params);
			if (null == jsonParams.getInteger("poHeaderId")) {
				return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
			}
			List<SrmPoHeadersEntity_HI_RO> data = srmPoFrameworkAgreementServer.findPoFrameworkAgreementLineAppliedOrganization(jsonParams);
			page.setData(data);
			return JSONObject.toJSONString(page);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}



    /**
     * Description：查询采购框架协议行物料信息
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("findPoFrameworkAgreementLineItemInfo")
	public String findPoFrameworkAgreementLineItemInfo(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmPoFrameworkAgreementServer.findPoFrameworkAgreementLineItemInfo(jsonParams));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}



    /**
     * Description：查询采购框架协议行应用组织信息
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("findPoFrameworkAgreementLineAppliedOrganization")
	public String findPoFrameworkAgreementLineAppliedOrganization(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmPoFrameworkAgreementServer.findPoFrameworkAgreementLineAppliedOrganization(jsonParams));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}



    /**
     * Description：导入模板下载
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("importPoFrameworkAgreementTemplatesExcel")
	public String importPoFrameworkAgreementTemplatesExcel(@FormParam(PARAMS) String params) {
		if (StringUtils.isBlank(params)) {
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可导出！", 0, null);
		}
		try {
			Pagination<SrmPoHeadersEntity_HI_RO> page = new Pagination<SrmPoHeadersEntity_HI_RO>();
			JSONObject jsonParam = this.parseObject(params);
			List<SrmPoHeadersEntity_HI_RO> data = srmPoFrameworkAgreementServer.findPoFrameworkAgreementHeaderInfo(jsonParam);
			page.setData(data);
			return JSONObject.toJSONString(page);
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}


    /**
     * Description：查询采购框架协议行物料信息
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("findPoFrameworkAgreementLineItemInfoExport")
	public String findPoFrameworkAgreementLineItemInfoExport(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,@FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = JSON.parseObject(params);
			List<SrmPoHeadersEntity_HI_RO> list =srmPoFrameworkAgreementServer.findPoFrameworkAgreementLineItemInfo(paramJSON);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功", list.size(), list);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}


    /**
     * Description：批量导入采购框架协议行物料数据
     * @param params 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("saveImportPoFrameworkAgreementLineItemInfo")
	public String saveImportPoFrameworkAgreementLineItemInfo(@FormParam(PARAMS) String params) {

		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject object = srmPoFrameworkAgreementServer.saveImportPoFrameworkAgreementLineItemInfo(jsonParams);
			return JSONObject.toJSONString(object);
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}


    /**
     * Description：框架协议转订单
     * @param params 框架协议参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("saveOrderInfoByFrameworkAgreement")
	public String saveOrderInfoByFrameworkAgreement(@FormParam(PARAMS) String params){
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject object = srmPoFrameworkAgreementServer.saveOrderInfoByFrameworkAgreement(jsonParams);
			return JSONObject.toJSONString(object);
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}

    /**
     * Description：删除采购框架协议头信息
     * @param params 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@Path("deletePoFrameworkAgreementHeader")
	@POST
	public String deletePoFrameworkAgreementHeader(@FormParam("params") String params) {
		LOGGER.info("删除信息,参数：" + params.toString());
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmPoFrameworkAgreementServer.deletePoFrameworkAgreementHeader(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(
					jsondata.getString("status"), jsondata.getString("msg"),
					jsondata.getInteger("count"), jsondata.get("data"));
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}

    /**
     * Description：查询价格库信息（分页）
     * @param params 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("findAgreementPriceLibraryList")
	public String findAgreementPriceLibraryList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
								@FormParam(PAGE_ROWS) Integer pageRows) {
		try {
			LOGGER.info("-->>参数：" + params);
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SrmPoHeadersEntity_HI_RO> data = srmPoFrameworkAgreementServer.findAgreementPriceLibraryList(jsonParams, pageIndex, pageRows);
			return JSONObject.toJSONString(data);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}

}
