package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionItemsEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionItems;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonAuctionItemsService.java
 * Description：寻源--寻源标的物信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonAuctionItemsService")
@Path("/srmPonAuctionItemsService")
public class SrmPonAuctionItemsService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonAuctionItemsService.class);
	@Autowired
	private ISrmPonAuctionItems iSrmPonAuctionItems;
	public SrmPonAuctionItemsService() {
		super();
	}

	/**
	 * Description：查询标的物（不分页）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("getAuctionItemsList")
	public String getAuctionGroupList(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPonAuctionItems.getAuctionItemsList(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询失败" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：导入模板下载——标的物==洽谈行
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("importPonItemModuleExcel")
	public String importPonItemModuleExcel(@FormParam(PARAMS) String params) {
		LOGGER.info("参数,params:" + params.toString());
		try {
			JSONObject jsonParam = this.parseObject(params);
			Pagination<SrmPonAuctionItemsEntity_HI_RO> result = new Pagination<>();
			result.setCurIndex(1);
			result.setPreIndex(1);
			result.setCount(0);
			result.setPagesCount(1);
			result.setPageSize(0);
			result.setNextIndex(1);
			List<SrmPonAuctionItemsEntity_HI_RO> list = new ArrayList<>();
			result.setData(list);
			return JSON.toJSONString(result);
		}catch (Exception e) {
			//e.printStackTrace();
			return CommonAbstractServices.convertResultJSONObj("E",   "导入模板下载出错，"+e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：批量导入——洽谈行数据==标的物
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("batchImportPonAuctionItems")
	public String saveBatchImportPonAuctionItems(@FormParam(PARAMS) String params){
		LOGGER.info("参数："+params.toString());
		UserInfoSessionBean sessionBean = getUserSessionBean();
		Integer userId = sessionBean.getUserId();
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonAuctionItems.saveBatchImportPonAuctionItems(jsonParams,userId);
			return JSONObject.toJSONString(jsondata);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("导入失败，" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E",   "导入失败，"+e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：批量导入——询价-网站参考价
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@POST
	@Path("batchImportPonWebReferencePrice")
	public String saveBatchImportPonWebReferencePrice(@FormParam(PARAMS) String params){
		LOGGER.info("参数："+params.toString());
		UserInfoSessionBean sessionBean = getUserSessionBean();
		Integer userId = sessionBean.getUserId();
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonAuctionItems.saveBatchImportPonWebReferencePrice(jsonParams,userId);
			return JSONObject.toJSONString(jsondata);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("导入失败，" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj("E",   "导入失败，"+e.getMessage(), 0, null);
		}
	}

	/**
	 * Description：删除洽谈行（同时删除对应的供应商行的阶梯数量的数据）——根据主键ID（单条数据）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	@Path("deleteSrmPonAuctionItemById")
	@POST
	@Produces("application/json")
	public String deleteSrmPonAuctionItemById(@FormParam(PARAMS)String params){
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可删除！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonAuctionItems.deleteSrmPonAuctionItemById(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.get(DATA));
		}catch (Exception e){
			//e.printStackTrace();
			LOGGER.error("删除失败！参数：" + params + ",异常：" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "删除失败!" + e, 0, null);
		}
	}

	/**
	 * Description：批量删除洽谈行（同时删除对应的供应商行的阶梯数量的数据）
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       2020-07-07          SIE 谢晓霞    	创建
	 * =======================================================================
	 */
	@Path("deleteSrmPonAuctionItem")
	@POST
	@Produces("application/json")
	public String deleteSrmPonAuctionItem(@FormParam(PARAMS)String params){
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可删除！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPonAuctionItems.deleteSrmPonAuctionItem(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.get(DATA));
		}catch (UtilsException e){
			LOGGER.error("删除失败:"+ e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "删除失败!" + e.getMessage(), 0, null);
		}catch (Exception e){
			//e.printStackTrace();
			LOGGER.error("删除失败！参数：" + params + ",异常：" + e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "删除失败!" + e.getMessage(), 0, null);
		}
	}
	
}
