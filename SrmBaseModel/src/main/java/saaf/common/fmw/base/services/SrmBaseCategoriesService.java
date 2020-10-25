package saaf.common.fmw.base.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.alibaba.fastjson.JSONArray;
import com.yhg.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafUsers;
import saaf.common.fmw.base.model.inter.ISrmBaseCategories;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.utils.SHA1Util;

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
@Component("srmBaseCategoriesService")
@Path("/srmBaseCategoriesService")
public class SrmBaseCategoriesService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseCategoriesService.class);
    @Autowired
    private ISrmBaseCategories iSrmBaseCategories;
    @Autowired
    private ISaafUsers iSaafUsers;

    public SrmBaseCategoriesService() {
        super();
    }

    /**
     * 查询采购分类（分页，通用）
     * @param params
     * @param curIndex
     * @param pageSize
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmBaseCategoriesInfo")
    @Produces("application/json")
    public String findSrmBaseCategoriesInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1")
            Integer curIndex, @FormParam(PAGE_ROWS) @DefaultValue("10") Integer pageSize) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmBaseCategoriesEntity_HI_RO> pag = iSrmBaseCategories.findSrmBaseCategoriesInfo(paramJSON, curIndex, pageSize);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
        }
    }
    /**
     * 查询冻结品类
     *
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
    @Path("findSrmBaseCategories")
    public String findSrmBaseCategoriesById(@FormParam("params")
                                                    String params) {
        try {
            JSONObject jsonParams = JSONObject.parseObject(params);
            List<SrmBaseCategoriesEntity_HI_RO> LookupCodelist = iSrmBaseCategories.findSrmBaseCategoriesById(jsonParams);
            LOGGER.info("--->>findSrmPosSupplierSites-->>参数：" + params + "查询冻结品类成功！");
            return CommonAbstractServices.convertResultJSONObj("S", "查询冻结品类成功！", LookupCodelist.size(), LookupCodelist);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            LOGGER.error("查询冻结品类失败" + e,e);
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }
    /**
     * 采购分类维护初始化菜单
     * @param params
     * @param curIndex
     * @param pageSize
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findPurchaseClassificationMenu")
    @Produces("application/json")
    //	//srmBaseCategoriesService/findSrmBaseCategoriesInfo
    public String findPurchaseClassificationMenu(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1")
            Integer curIndex, @FormParam(PAGE_ROWS) @DefaultValue("99999") Integer pageSize) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmBaseCategoriesEntity_HI_RO> pag = iSrmBaseCategories.findSrmBaseCategoriesInfo(paramJSON, curIndex, pageSize);
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
        }
    }
    /**
     * 点击菜单查询采购分类维护
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
    @Path("findProcurementMaintenanceList")
    @Produces("application/json")
    //	//srmBaseCategoriesService/findSrmBaseCategoriesInfo
    public String findProcurementList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmBaseCategoriesEntity_HI_RO> result = iSrmBaseCategories.findProcurementMaintenanceList(paramJSON,pageIndex, pageRows);
            return JSON.toJSONString(result);
        } catch (Exception e) {
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
        }
    }
    /**
     * 批量生效采购分类（修改或保存）
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
    @Path("updateProcurementMaintenance")
    @Produces("application/json")
    //	//srmBaseCategoriesService/findSrmBaseCategoriesInfo
    public String updateProcurementMaintenance(@FormParam(PARAMS) String params) {
        LOGGER.info(params);
        try{
            JSONObject jsonParams = this.parseObject(params);
            int operatorUserId = jsonParams.getInteger("operatorUserId");
            JSONArray jsonArray = jsonParams.getJSONArray("array");
            JSONObject object = iSrmBaseCategories.updateProcurementMaintenance(jsonArray,operatorUserId);
            return JSONObject.toJSONString(object);
        } catch (Exception e) {
            LOGGER.error("生效失败" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E",  "未知错误", 0, null);
        }
    }
    /**
     * 批量失效采购分类（修改）
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
    @Path("updateInvalidProcurementMaintenance")
    @Produces("application/json")
    //	//srmBaseCategoriesService/findSrmBaseCategoriesInfo
    public String updateInvalidProcurementMaintenance(@FormParam(PARAMS) String params) {
        LOGGER.info(params);
        try{
            JSONObject jsonParams = this.parseObject(params);
            int operatorUserId = jsonParams.getInteger("operatorUserId");
            JSONArray jsonArray = jsonParams.getJSONArray("array");
            JSONObject object = iSrmBaseCategories.updateInvalidProcurementMaintenance(jsonArray,operatorUserId);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
            return JSONObject.toJSONString(object);
        } catch (Exception e) {
            LOGGER.error("失效失败" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E",  "未知错误", 0, null);
        }
    }
    /**
     * 接口（数据输出）——查询采购分类所有信息（用于外部访问的接口）参数fullCategoryCode，lastUpdateDateFrom，lastUpdateDateTo，parentFullCategoryCode，categoryLevel，parentFullCategoryCode
     * 查询结果：（分类编码fullCategoryCode、分类名称fullCategoryName、当前分类编码categoryCode、当前分类编码名称categoryName、上级分类编码parentFullCategoryCode
     * 上级分类编码名称parentFullCategoryName、状态enabledFlagDesc、最后更新时间lastUpdateDate）
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
    @Path("findProcurementListExternal")
    @Produces("application/json")
    public String findProcurementListExternal(@FormParam(PARAMS)String params,@FormParam("userName")String userName,@FormParam("password")String password) {
        LOGGER.info("参数params："+params.toString()+"，参数userName："+userName+"，password："+password);
        if(StringUtils.isBlank(userName)||StringUtils.isBlank(password)){
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"未知错误",0,null);
        }
        Map<String,Object> map1 = new HashMap<>();
        SHA1Util shal = new SHA1Util();
        try{
            String encryptedPassword = shal.getEncrypt(password);
            map1.put("userName",userName);
            map1.put("encryptedPassword",password);
            SaafUsersEntity_HI userEntity = iSaafUsers.findUserLogin(map1);  //验证用户登录
            Integer userId = null;
            if(null != userEntity){
                userId = userEntity.getUserId();
            }else {
                return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"未知错误",0,null);
            }
            JSONObject jsonParams = this.parseObjectExternal(params); //调用外部转换json 方法
            Map<String,Object> map = iSrmBaseCategories.pushFindProcurementListExternal(jsonParams,userId);
            return JSON.toJSONString(map);
        } catch (Exception e) {
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
        }
    }
    /**
     * 接口（数据输入）——保存采购分类信息（用于外部访问的接口）参数params：array{categoryId，enabledFlag，fullCategoryCode，fullCategoryName，parentFullCategoryCode
     * categoryLevel}
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
    @Path("updateProcurementMaintenanceExternal")
    @Produces("application/json")
    public String updateProcurementMaintenanceExternal(@FormParam(PARAMS)String params,@FormParam("userName")String userName,@FormParam("password")String password) {
        LOGGER.info("参数params："+params.toString()+"，参数userName："+userName+"，password："+password);
        if(StringUtils.isBlank(userName)||StringUtils.isBlank(password)){
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"未知错误",0,null);
        }
        Map<String,Object> map1 = new HashMap<>();
        SHA1Util shal = new SHA1Util();
        try{
            String encryptedPassword = shal.getEncrypt(password);
            map1.put("userName",userName);
            map1.put("encryptedPassword",password);
            SaafUsersEntity_HI userEntity = iSaafUsers.findUserLogin(map1);  //验证用户登录
            Integer userId = null;
            if(null != userEntity){
                userId = userEntity.getUserId();
            }else {
                return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"未知错误",0,null);
            }
            JSONObject jsonParams = this.parseObjectExternal(params); //调用外部转换json 方法
            JSONObject object = iSrmBaseCategories.updateProcurementMaintenanceExternal(jsonParams,userId);
            return JSONObject.toJSONString(object);
        } catch (Exception e) {
            LOGGER.error("生效失败" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E",  "未知错误", 0, null);
        }
    }

    /**
     * Description：查询所有有效的采购分类
     *
     * @param params    参数
     * @param pageIndex
     * @param pageRows
     * @return 采购分类的实体集合
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-06-04     秦晓钊          创建
     * ==============================================================================
     */
    @POST
    @Path("findAllCategoriesList")
    @Produces("application/json")
    public String findAllCategoriesList(@FormParam("params")
                                                String params, @FormParam("pageIndex")
                                                Integer pageIndex, @FormParam("pageRows")
                                                Integer pageRows) {
        LOGGER.info("参数params：" + params.toString() + "，pageIndex：" + pageIndex + "，pageRows：" + pageRows);
        Map<String, Object> map1 = new HashMap<>();
        SHA1Util shal = new SHA1Util();
        try {
            JSONObject jsonParams = this.parseObjectExternal(params); //调用外部转换json 方法
            Pagination<SrmBaseCategoriesEntity_HI_RO> object = iSrmBaseCategories.findAllCategoriesList(jsonParams, pageIndex, pageRows);
            return JSON.toJSONString(object);
        } catch (Exception e) {
            LOGGER.error("查询失败：" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E","未知错误", 0, null);
        }
    }

}
