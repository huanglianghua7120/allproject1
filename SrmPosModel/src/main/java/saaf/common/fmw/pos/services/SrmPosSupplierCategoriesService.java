package saaf.common.fmw.pos.services;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.paging.Pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SrmBaseCategoriesEntity_HI_RO;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierCategoriesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierCategories;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商产品与服务
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmPosSupplierCategoriesService")
@Path("/srmPosSupplierCategoriesService")
public class SrmPosSupplierCategoriesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierCategoriesService.class);
	@Autowired
	private ISrmPosSupplierCategories iSrmPosSupplierCategories;
	public SrmPosSupplierCategoriesService() {
		super();
	}

	/**
	 * 查询供应商的产品与服务
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
	@Path("findSupplierCategory")
	public String findSupplierCategory(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			if ("EX".equals(paramJSON.getString("varPlatformCode"))) { //是供应商查询
				paramJSON.put("supplier_id", paramJSON.getIntValue("varSupplierId"));
			}else if(null==paramJSON.getInteger("supplierId")){
				paramJSON.put("supplierId", -1);
			}
			Pagination<SrmPosSupplierCategoriesEntity_HI_RO> pag = iSrmPosSupplierCategories.findSupplierCategory(paramJSON, pageIndex, pageRows);
			return JSON.toJSONString(pag);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

	/**
	 * 删除产品与服务行
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("deleteCategory")
	@POST
	@Produces("application/json")
	public String deleteCategory(@FormParam(PARAMS) String params) {
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可删除！",0,null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPosSupplierCategories.deleteCategory(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.get(DATA));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "删除失败!" + e, 0, null);
		}
	}
	
	/**
     * 查询冻结品类
     * @param params
     * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    @POST
    @Path("findSrmPosSupplierCategories")
    public String findSrmPosSupplierCategoriesById(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            List<SrmPosSupplierCategoriesEntity_HI_RO> LookupCodelist = iSrmPosSupplierCategories.findSrmPosSupplierCategoriesById(jsonParams);
//            if(LookupCodelist == null){
//				return CommonAbstractServices.convertResultJSONObj("E", "重复做单，保存失败!该供应商存在有未处理的冻结单据，请优先处理前面的单据！",0, null);
//			}
            LOGGER.info("--->>findSrmPosSupplierSites-->>参数：" + params + "查询冻结品类成功！");
            return CommonAbstractServices.convertResultJSONObj("S", "查询冻结品类成功！", LookupCodelist.size(), LookupCodelist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            
            LOGGER.error("查询冻结品类失败" + e,e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
            return CommonAbstractServices.convertResultJSONObj("E", "查询冻结品类失败!" + e, 0, null);
        }
    }

	/**
	 * 查询指定供应商的有效品类
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSupplierCategoriesByEff")
	public String findSupplierCategoriesByEff(@FormParam("params") String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPosSupplierCategoriesEntity_HI_RO> supplierCategorylist = iSrmPosSupplierCategories.findSupplierCategoriesByEff(jsonParams);
			LOGGER.info("--->>findSrmPosSupplierSites-->>参数：" + params + "查询冻结品类成功！");
			return CommonAbstractServices.convertResultJSONObj("S", "查询冻结品类成功！", supplierCategorylist.size(), supplierCategorylist);
		} catch (NotLoginException e) {
			return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("查询冻结品类失败" + e,e);
			if (e instanceof UtilsException) {
				LOGGER.error("服务异常:" + e);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询冻结品类失败!" + e, 0, null);
		}
	}

}
