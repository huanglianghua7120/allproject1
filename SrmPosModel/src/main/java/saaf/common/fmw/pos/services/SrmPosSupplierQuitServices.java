package saaf.common.fmw.pos.services;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.intf.model.inter.IIntfSupplier;
import saaf.common.fmw.pos.model.entities.SrmPosReasonsEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierQuitSitesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierAddressesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierQuitEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierAddresses;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierQuit;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierQuitSites;
import saaf.common.fmw.services.CommonAbstractServices;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商退出头层
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Path("/srmPosSupplierQuitServices")
@Component
public class SrmPosSupplierQuitServices extends CommonAbstractServices {
	@Autowired
	private ISrmPosSupplierQuit srmPosSupplierQuitServer;
	@Autowired
	private ISrmPosSupplierAddresses srmPosSupplierAddressesServer;
	@Autowired
	private IIntfSupplier intfSupplierServer;

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierQuitServices.class);

	/**
	 * 查询供应商LOV
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
	@Path("findSupplierInfoLov")
	public String findSupplierInfoLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
			@FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SrmPosSupplierQuitEntity_HI_RO> rowSet = srmPosSupplierQuitServer.findSupplierInfoLov(jsonParams,
					pageIndex, pageRows);
			return JSON.toJSONString(rowSet);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

	/**
	 * 查询冻结单号
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
	@Path("findFrozenLov")
	public String findFrozenLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
			@FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SrmPosSupplierQuitEntity_HI_RO> rowSet = srmPosSupplierQuitServer.findFrozenLov(jsonParams,
					pageIndex, pageRows);

			return JSON.toJSONString(SToolUtils.convertResultJSONObj("S", "查询成功!", 1, rowSet.getData().get(0)));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("F", "查询失败!" + e, 0, null));
		}
	}

	/**
	 * 供应商退出查询
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
	@Path("findSupplierQuitInfo")
	public String findSupplierQuitInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
			@FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SrmPosSupplierQuitEntity_HI_RO> rowSet = srmPosSupplierQuitServer
					.findSupplierQuitInfo(jsonParams, pageIndex, pageRows);
			return JSON.toJSONString(rowSet);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

	/**
	 * 供应商退出原因查询
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findReasonInfo")
	public String findReasonInfo(@FormParam(PARAMS) String params) {
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPosReasonsEntity_HI> rowSet = srmPosSupplierQuitServer.findReasonInfo(jsonParams);
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("S", "查询成功!", 0, rowSet));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

	/**
	 * Description：保存数据
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * supplierQuitId  供应商退出单ID  NUMBER  Y
	 * documentNumber  退出单号  VARCHAR2  N
	 * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
	 * orgId  （废弃）组织ID  NUMBER  N
	 * frozenId  （废弃）冻结单ID,关联表:srm_pos_frozen_info  NUMBER  N
	 * documentStatus  状态(POS_APPROVAL_STATUS)  VARCHAR2  N
	 * inventoryCleanupFlag  是否完成库存清理(Y/N)  VARCHAR2  N
	 * paymentSettleFlag  是否完成尾款结算(Y/N)  VARCHAR2  N
	 * fileId  附件ID  NUMBER  N
	 * description  说明  VARCHAR2  N
	 * oaNum  oa审批编号  VARCHAR2  N
	 * organizationId  库存组织id  NUMBER  N
	 * rejectReason  驳回原因  VARCHAR2  N
	 * quitType  退出类型  VARCHAR2  N
	 * supplierQuitId  供应商退出单ID  NUMBER  Y
	 * documentNumber  退出单号  VARCHAR2  N
	 * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
	 * orgId  （废弃）组织ID  NUMBER  N
	 * frozenId  （废弃）冻结单ID,关联表:srm_pos_frozen_info  NUMBER  N
	 * documentStatus  状态(POS_APPROVAL_STATUS)  VARCHAR2  N
	 * inventoryCleanupFlag  是否完成库存清理(Y/N)  VARCHAR2  N
	 * paymentSettleFlag  是否完成尾款结算(Y/N)  VARCHAR2  N
	 * fileId  附件ID  NUMBER  N
	 * description  说明  VARCHAR2  N
	 * quitDate  退出日期  DATE  N
	 * quitForeverFlag  是否永久退出(Y/N)  VARCHAR2  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

	@POST
	@Path("saveQuitData")
	public String saveQuitData(@FormParam(PARAMS) String params) {
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject retJson = srmPosSupplierQuitServer.saveData(jsonParams);
			
			return retJson.toString();
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "保存失败!" + e.getMessage(), 0, null));
		}
	}

	/**
	 * 删除行信息
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("deleteQuitRow")
	public String deleteQuitRow(@FormParam(PARAMS) String params) {
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmPosSupplierQuitServer.deleteQuitRow(jsonParams));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "删除失败!" + e.getMessage(), 0, null));
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
	public  String findSupplierAddressesById(@FormParam(PARAMS) String params){
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPosSupplierAddressesEntity_HI_RO> rowSet = srmPosSupplierAddressesServer.findSupplierAddressesById(jsonParams);
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("S", "查询成功!", 0, rowSet));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

	/**
	 * 检查该供应商所有地点是否已退出，如果全已退出，更新所有品类为失效状态
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("updateSupplierCategories")
	public String updateSupplierCategories(@FormParam(PARAMS) String params) {
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject retJson = srmPosSupplierQuitServer.updateSupplierCategories(jsonParams);
			return retJson.toString();
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "保存失败!" + e.getMessage(), 0, null));
		}
	}
}
