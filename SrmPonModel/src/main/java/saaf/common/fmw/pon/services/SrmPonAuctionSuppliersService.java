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
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionSuppliersEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionSuppliers;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonAuctionSuppliersService.java
 * Description：寻源--寻源邀请供应商信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonAuctionSuppliersService")
@Path("/srmPonAuctionSuppliersService")
public class SrmPonAuctionSuppliersService extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonAuctionSuppliersService.class);
	@Autowired
	private ISrmPonAuctionSuppliers iSrmPonAuctionSuppliers;

	public SrmPonAuctionSuppliersService() {
		super();
	}

	/**
	 * Description：招标的洽谈邀请供应商——选择供应商弹出框查询（分页）
	 * @param params
	 * @param curIndex
	 * @param pageRows
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findSrmPonAuctionSuppliersLov")
	@Produces("application/json")
	public String findSrmPonAuctionSuppliersInfo(@FormParam(PARAMS) String params,
			@FormParam(PAGE_INDEX) @DefaultValue("1") Integer curIndex,
			@FormParam(PAGE_ROWS) @DefaultValue("10") Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			Pagination<SrmPonAuctionSuppliersEntity_HI_RO> list = iSrmPonAuctionSuppliers
					.findSrmPonAuctionSuppliersLov(paramJSON, curIndex, pageRows);
			return JSON.toJSONString(list);
		} catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
		}
	}

	/**
	 * Description：询价的洽谈邀请供应商——选择供应商弹出框查询（分页）
	 * @param params
	 * @param curIndex
	 * @param pageRows
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findSrmPonAuctionSuppliersBiddingLov")
	@Produces("application/json")
	public String findSrmPonAuctionSuppliersBiddingLov(@FormParam(PARAMS) String params,
			@FormParam(PAGE_INDEX) @DefaultValue("1") Integer curIndex,
			@FormParam(PAGE_ROWS) @DefaultValue("10") Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			Pagination<SrmPonAuctionSuppliersEntity_HI_RO> list = iSrmPonAuctionSuppliers
					.findSrmPonAuctionSuppliersBiddingLov(paramJSON, curIndex, pageRows);
			return JSON.toJSONString(list);
		} catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
		}
	}

	/**
	 * Description：查询招标的洽谈邀请供应商（不分页）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("getAuctionSupplierList")
	public String getAuctionSupplierList(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonAuctionSuppliers.getAuctionSupplierList(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询受邀供应商失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败", 0, null);
		}
	}

	/**
	 * Description：查询供应商是否有对应保证金
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("getIsAuctionSupplier")
	public String getIsAuctionSupplier(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonAuctionSuppliers.getIsAuctionSupplier(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询受邀供应商失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败", 0, null);
		}
	}

	/**
	 * Description：删除洽谈邀请供应商——根据主键ID（单条数据）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@Path("deleteSrmPonAuctionSupplierById")
	@POST
	@Produces("application/json")
	public String deleteSrmPonAuctionSupplierById(@FormParam(PARAMS) String params) {
		LOGGER.info("参数：" + params.toString());
		if (StringUtils.isBlank(params)) {
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可删除！", 0, null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonAuctionSuppliers.deleteSrmPonAuctionSupplierById(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG),
					jsondata.getInteger(COUNT), jsondata.get(DATA));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("删除失败！参数：" + params + ",异常：" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "删除失败!" + e, 0, null);
		}
	}

	/**
	 * Description：保存保证金和标书费用
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("saveBondPayStatus")
	public  String saveBondPayStatus(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonAuctionSuppliers.saveBondPayStatus(jsonParams));
		} catch (Exception e) {
			LOGGER.error("保存保证金和标书费用" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：招标的洽谈邀请供应商保证金查询
	 * @param params
	 * @param curIndex
	 * @param pageRows
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findSrmPonAuctionSuppliersBondList")
	@Produces("application/json")
	public String findSrmPonAuctionSuppliersBondList(@FormParam(PARAMS) String params,
												 @FormParam(PAGE_INDEX) @DefaultValue("1") Integer curIndex,
												 @FormParam(PAGE_ROWS) @DefaultValue("10") Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			Pagination<SrmPonAuctionSuppliersEntity_HI_RO> list = iSrmPonAuctionSuppliers
					.findSrmPonAuctionSuppliersBondList(paramJSON, curIndex, pageRows);
			return JSON.toJSONString(list);
		} catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
		}
	}

	/**
	 * Description：招标的供应商中标情况查询
	 * @param params
	 * @param curIndex
	 * @param pageRows
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("findSrmPonAuctionAnalysisList")
	@Produces("application/json")
	public String findSrmPonAuctionAnalysisList(@FormParam(PARAMS) String params,
											    @FormParam(PAGE_INDEX) @DefaultValue("1") Integer curIndex,
												@FormParam(PAGE_ROWS) @DefaultValue("10") Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			Pagination<SrmPonAuctionSuppliersEntity_HI_RO> list = iSrmPonAuctionSuppliers
					.findSrmPonAuctionAnalysisList(paramJSON, curIndex, pageRows);
			return JSON.toJSONString(list);
		} catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
		}
	}

	/**
	 * Description：保存保证金退还信息
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("saveBondReturn")
	public  String saveBondReturn(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonAuctionSuppliers.saveBondReturn(jsonParams));
		} catch (Exception e) {
			LOGGER.error("保存保证金退还信息" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}
}
