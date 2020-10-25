package saaf.common.fmw.base.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import org.apache.commons.lang.StringUtils;

import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseCategoryDivisionEntity_HI_RO;

import saaf.common.fmw.base.model.inter.ISrmBaseCategories;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.base.model.inter.ISrmBaseCategoryDivision;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：采购分类
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseCategoryDivisionService")
@Path("/srmBaseCategoryDivisionService")
public class SrmBaseCategoryDivisionService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseCategoryDivisionService.class);
	private static final String PARAMS = "params";
	@Autowired
	private ISrmBaseCategoryDivision srmBaseCategoryDivisionServer;
	@Autowired
	private ISrmBaseCategories srmBaseCategoriesServer;
	public SrmBaseCategoryDivisionService() {
		super();
	}

	/**
	 * 查询采购分类lov
	 * @param params
	 * @return
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findPurchaseLov")
	public String findCategoryLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
		String flag = request.getParameter("flag");
		try {
			JSONObject jsonParams = this.parseObject(params);

			Pagination<SrmBaseCategoriesEntity_HI_RO> pag = srmBaseCategoriesServer.findCategoryLove(jsonParams, pageIndex, pageRows);
			LOGGER.info("-->>参数：" + params + "查询成功！");
			return JSON.toJSONString(pag);
		} catch (Exception e) {
			LOGGER.error("查询失败" + e,e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}
	/**
	 * 查询  品类分工
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findCategoryDivision")
	public String findCategoryDivision(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {

		try {
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SrmBaseCategoryDivisionEntity_HI_RO> pag = srmBaseCategoryDivisionServer.findCategoryDivision(jsonParams, pageIndex, pageRows);
			LOGGER.info("-->>参数：" + params + "查询成功！");
			return JSON.toJSONString(pag);
		} catch (Exception e) {
			LOGGER.error("查询失败" + e,e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}

	/**
	 * 崗位lov
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findPositionLov")
	public String findPositionLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SrmBaseCategoryDivisionEntity_HI_RO> pag = srmBaseCategoryDivisionServer.findPositionLov(jsonParams, pageIndex, pageRows);
			LOGGER.info("-->>参数：" + params + "查询成功！");
			return JSON.toJSONString(pag);
		} catch (Exception e) {
			LOGGER.error("查询失败" + e,e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}
	/**
	 * Description：保存品类分工
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * divisionId  分工ID  NUMBER  Y
	 * orgId  组织ID，关联表：SAAF_INSTITUTION  NUMBER  N
	 * departmentId  部门ID（备用）  NUMBER  N
	 * positionId  职位ID（备用）  NUMBER  N
	 * employeeId  员工ID，关联表：SAAF_EMPLOYEES  NUMBER  N
	 * userId  用户ID，关联表：SAAF_USERS  NUMBER  N
	 * allCategoryFlag  是否可操作全品类，关联表：SAAF_LOOKUP_VALUES（YSE_NO）  VARCHAR2  N
	 * categoryId  分类ID，关联表：SRM_BASE_CATEGORIES  NUMBER  N
	 * segment1  一级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_BIG_CATEGORY）  VARCHAR2  N
	 * segment2  二级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_MIDDLE_CATEGORY）  VARCHAR2  N
	 * segment3  三级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_SMALL_CATEGORY）  VARCHAR2  N
	 * segment4  四级分类编码（备用）  VARCHAR2  N
	 * divisionStatus  分工状态，关联表：SAAF_LOOKUP_VALUES（BASE_DIVISION_STATUS）  VARCHAR2  N
	 * startDate  生效日期  DATE  N
	 * endDate  失效日期  DATE  N
	 * divisionId  分工ID  NUMBER  Y
	 * orgId  组织ID，关联表：SAAF_INSTITUTION  NUMBER  N
	 * departmentId  部门ID（备用）  NUMBER  N
	 * positionId  职位ID（备用）  NUMBER  N
	 * employeeId  员工ID，关联表：SAAF_EMPLOYEES  NUMBER  N
	 * userId  用户ID，关联表：SAAF_USERS  NUMBER  N
	 * allCategoryFlag  是否可操作全品类，关联表：SAAF_LOOKUP_VALUES（YSE_NO）  VARCHAR2  N
	 * categoryId  分类ID，关联表：SRM_BASE_CATEGORIES  NUMBER  N
	 * segment1  一级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_BIG_CATEGORY）  VARCHAR2  N
	 * segment2  二级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_MIDDLE_CATEGORY）  VARCHAR2  N
	 * segment3  三级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_SMALL_CATEGORY）  VARCHAR2  N
	 * segment4  四级分类编码（备用）  VARCHAR2  N
	 * divisionStatus  分工状态，关联表：SAAF_LOOKUP_VALUES（BASE_DIVISION_STATUS）  VARCHAR2  N
	 * startDate  生效日期  DATE  N
	 * endDate  失效日期  DATE  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

	@Path("saveCategoryDivision")
	@POST
	public String saveCategoryDivision(@FormParam("params")
												String params) {
		UserInfoSessionBean sessionBean = null;
		sessionBean = getUserSessionBean();
		int userId = sessionBean.getUserId();
		try {
			JSONArray jsonParam = JSONArray.parseArray(params);
			JSONObject posJson = srmBaseCategoryDivisionServer.saveCategoryDivision(jsonParam,userId);
			return CommonAbstractServices.convertResultJSONObj(posJson.getString(STATUS),posJson.getString(MSG),posJson.getInteger(COUNT),posJson.getString(DATA));
		}catch (Exception e) {
			LOGGER.error("保存品分工失败！" + e,e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
		}
	}

	/**
	 * 批量生效
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("updateEffectCategoryDivision")
	@Produces("application/json")
	public String updateEffectCategoryDivision(@FormParam(PARAMS)String params){
		LOGGER.info("生效品类分工信息，参数为："+params.toString());
		UserInfoSessionBean sessionBean = null;
		sessionBean = getUserSessionBean();
		int userId = sessionBean.getUserId();
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"未知错误",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmBaseCategoryDivisionServer.updateEffectCategoryDivision(jsonParams,userId);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		}catch (Exception e){
			//e.printStackTrace();
			LOGGER.error("------------------->生效品类分工失败！参数："+params.toString()+"，异常："+e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return convertResultJSONObj(ERROR_STATUS,"生效品类分工失败！",0,null);
		}
	}
	/**
	 * 批量失效
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("updateInvalidCategoryDivision")
	public String updateInvalidCategoryDivision(@FormParam(PARAMS) String params){
		UserInfoSessionBean sessionBean = null;
		sessionBean = getUserSessionBean();
		int userId = sessionBean.getUserId();

		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONArray jsonArray = jsonParams.getJSONArray("array");
			JSONObject object = srmBaseCategoryDivisionServer.updateInvalidCategoryDivision(jsonArray,userId);
			return JSONObject.toJSONString(object);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("失效失败" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E",  "未知错误", 0, null);
		}
	}

	/**
	 * 品类分工导出
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
	@Path("findCategoryDivisionExcel")
	public String findCategoryDivisionExcel(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows){
		LOGGER.info("Excel导出，"+pageIndex + ",pageSize:" + pageRows + ",params:" + params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"未知错误",0,null);
		}
		try{
			JSONObject jsonParam = this.parseObject(params);
			LOGGER.info(jsonParam.toString());
			Pagination<SrmBaseCategoryDivisionEntity_HI_RO> resultStr = srmBaseCategoryDivisionServer.findCategoryDivision(jsonParam, pageIndex, pageRows);
			LOGGER.info(resultStr.toString());
			return JSON.toJSONString(resultStr);

		}catch (Exception e){
			//e.printStackTrace();
			LOGGER.error("品类分工列表异常：" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}
	/**
	 * 批量导入
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("batchImportCategoryDivision")
	public String batchImportSupply(@FormParam(PARAMS) String params){
		UserInfoSessionBean sessionBean = null;
		sessionBean = getUserSessionBean();
		int userId = sessionBean.getUserId();

		try {
			JSONObject jsonParams = this.parseObject(params);
			System.out.print(jsonParams.toJSONString());
			JSONArray jsonArray = jsonParams.getJSONArray("data");
			JSONObject object = srmBaseCategoryDivisionServer.batchImportCategoryDivision(jsonArray,userId);
			return JSONObject.toJSONString(object);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("导入失败" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E",  "未知错误", 0, null);
		}
	}
	/**
	 * 导入模板下载
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("importTemplatesExcel")
	public String importTemplatesExcel(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info("Excel导出，" + pageIndex + ",pageSize:" + pageRows + ",params:" + params.toString());
		if (StringUtils.isBlank(params)) {
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
		try {
			JSONObject jsonParam = this.parseObject(params);
			LOGGER.info(jsonParam.toString());
			Pagination<SrmBaseCategoryDivisionEntity_HI_RO> resultStr = srmBaseCategoryDivisionServer.findCategoryDivision(jsonParam, pageIndex, pageRows);
			LOGGER.info(resultStr.toString());
			return JSON.toJSONString(resultStr);
		}catch (Exception e) {
		//e.printStackTrace();
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
		return CommonAbstractServices.convertResultJSONObj("E",  "未知错误", 0, null);
	}
	}
	/**
	 * 批量删除
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("deleteCategoryDivision")
	public String deleteCategoryDivision(@FormParam(PARAMS) String params){
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONArray jsonArray = jsonParams.getJSONArray("array");
			JSONObject object = srmBaseCategoryDivisionServer.deleteCategoryDivision(jsonArray);
			return JSONObject.toJSONString(object);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("删除失败" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E",   "未知错误", 0, null);
		}
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
	@Path("findSrmBaseCategoryDivisionInfo")
	@Consumes("application/json")
	@Produces("application/json")
	//	/restServer/srmBaseCategoryDivisionService/findSrmBaseCategoryDivisionInfo
	public String findSrmBaseCategoryDivisionInfo(@FormParam("params")
        String params, @FormParam("pageIndex")
        @DefaultValue("1")
        Integer curIndex, @FormParam("pageRows")
        @DefaultValue("10")
        Integer pageSize) {
		LOGGER.info(params);
		JSONObject paramJSON = JSON.parseObject(params);
		String resultStr = JSONObject.toJSONString(srmBaseCategoryDivisionServer.findSrmBaseCategoryDivisionInfo(paramJSON));
		LOGGER.info(resultStr);
		return resultStr;
	}
}
