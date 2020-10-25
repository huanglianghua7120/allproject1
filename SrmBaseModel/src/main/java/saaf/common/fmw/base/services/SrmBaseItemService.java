package saaf.common.fmw.base.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.FormParam;

import com.alibaba.fastjson.JSONArray;

import javax.ws.rs.Produces;

import com.base.adf.common.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafUsers;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafUsers2;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.base.model.inter.ISrmBaseItem;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseItemsEntity_HI_RO;
import saaf.common.fmw.utils.SHA1Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：物料
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseItemService")
@Path("/srmBaseItemService")
public class SrmBaseItemService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseItemService.class);

    @Autowired
    private ISrmBaseItem iSrmBaseItemServer;

    @Autowired
    private ISaafUsers2 iSaafUsers; //pos模块的用户

    public SrmBaseItemService() {
        super();
    }

    /**
     * 查看物料（招标模块）——分页
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
    @Path("findSrmBaseItemList")
    public String findSrmBaseItemList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmBaseItemsEntity_HI_RO> result = iSrmBaseItemServer.findSrmBaseItemList(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(result);
        } catch (Exception e) {
            //e.printStackTrace();
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e.getMessage(), 0, null));
        }
    }

    /**
     * 点击菜单查询物料数据管理
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findMatterDataManageList")
    @Produces("application/json")
    public String findMatterDataManageList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmBaseItemsEntity_HI_RO> result = iSrmBaseItemServer.findMatterDataManageList(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(result);
        } catch (Exception e) {
            //e.printStackTrace();
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
        }
    }

    /**
     * 根据物料ID串查询物料数据
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findMatterDataManageListByIdList")
    public String findMatterDataManageListByIdList(@FormParam("params") String params) {
        LOGGER.info("查询参数：" + params.toString());
        try {
            JSONObject jsonParams = JSONObject.parseObject(params);
            List<SrmBaseItemsEntity_HI_RO> list = iSrmBaseItemServer.findMatterDataManageListByIdList(jsonParams);
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "查询成功!",0, list));
        } catch (Exception e) {
            //e.printStackTrace();
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
        }
    }

    /**
     * 根据物料ID查询组织分配情况
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findMatterDataManageOrgList")
    public String findMatterDataManageOrgList(@FormParam("params") String params) {
        LOGGER.info("查询参数：" + params.toString());
        try {
            JSONObject jsonParams = JSONObject.parseObject(params);
            if (null != jsonParams.getInteger("itemId")) {
                List<SrmBaseItemsEntity_HI_RO> list = iSrmBaseItemServer.findMatterDataManageOrgList(jsonParams);
                return CommonAbstractServices.convertResultJSONObj("S", "查询成功!", 1, list);
            } else {
                return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
        }
    }

    /**
     * 保存物料
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("updateMatterDataManage")
    public String updateMatterDataManage(@FormParam("params") String params) {
        LOGGER.info("保存参数：" + params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            int operatorUserId = jsonParams.getInteger("operatorUserId");
            JSONArray jsonArray = jsonParams.getJSONArray("array");
            JSONObject jsondata = iSrmBaseItemServer.updateMatterDataManage(jsonArray, operatorUserId);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.getString(DATA));
        } catch (Exception e) {
            LOGGER.error("--------------------------->操作失败！参数：" + params.toString() + "，异常：" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * 保存物料
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("updateOneMatterData")
    public String updateOneMatterData(@FormParam("params") String params) {
        LOGGER.info("保存参数：" + params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            int operatorUserId = jsonParams.getInteger("operatorUserId");
            JSONObject jsondata = iSrmBaseItemServer.updateOneMatterData(jsonParams, operatorUserId);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.getString(DATA));
        } catch (Exception e) {
            LOGGER.error("--------------------------->操作失败！参数：" + params.toString() + "，异常：" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * 物料-失效
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("updateInvalidBaseItem")
    public String updateInvalidBaseItem(@FormParam("params") String params) {
        LOGGER.info("=======updateInvalidBaseItem=====》》》》》》》" + params);
        try {
            JSONObject jsonParams = this.parseObject(params);
            int operatorUserId = jsonParams.getInteger("operatorUserId");
            JSONArray jsonArray = jsonParams.getJSONArray("array");
            JSONObject json = iSrmBaseItemServer.updateInvalidBaseItem(jsonArray, operatorUserId);
            return CommonAbstractServices.convertResultJSONObj(json.getString("status"), json.getString("msg"), json.getInteger("count"), json.get("data"));
        } catch (Exception e) {
            //e.printStackTrace();
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * Description：生效/失效组织物料
     *
     * @param params 物料的JSON格式数据
     * @return 对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-03-04     吴嘉利          创建
     * ==============================================================================
     */
    @POST
    @Path("updateValidOrInvalidBaseItems")
    public String updateValidOrInvalidBaseItems(@FormParam("params") String params) {
        LOGGER.info("=======updateValidOrInvalidBaseItems=====》》》》》》》" + params);
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject json = iSrmBaseItemServer.updateValidOrInvalidBaseItems(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(json.getString("status"), json.getString("msg"), json.getInteger("count"), json.get("data"));
        } catch (Exception e) {
            //e.printStackTrace();
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * Description：删除组织物料
     *
     * @param params 物料的JSON格式数据
     * @return 对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-03-04     吴嘉利          创建
     * ==============================================================================
     */
    @POST
    @Path("deleteBaseItems")
    public String deleteBaseItems(@FormParam("params") String params) {
        LOGGER.info("=======deleteBaseItems=====》》》》》》》" + params);
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject json = iSrmBaseItemServer.deleteBaseItems(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(json.getString("status"), json.getString("msg"), json.getInteger("count"), json.get("data"));
        } catch (Exception e) {
            //e.printStackTrace();
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * Description：保存/修改物料数据
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * consignmentFlag  是否寄售(Y/N)  VARCHAR2  N
     * itemId  物料ID  NUMBER  Y
     * itemCode  物料编码  VARCHAR2  Y
     * itemName  物料名称  VARCHAR2  Y
     * itemDescription  物料说明  VARCHAR2  N
     * globalFlag  全局标识  VARCHAR2  N
     * enabledAsl  启用ASL  VARCHAR2  N
     * organizationId  库存组织ID  NUMBER  Y
     * uomCode  计量单位  VARCHAR2  N
     * itemStatus  物料状态  VARCHAR2  N
     * purchasingFlag  可采购标识  VARCHAR2  N
     * agentId  采购员ID  NUMBER  N
     * categoryId  采购分类ID  NUMBER  N
     * purchaseCycle  采购周期  VARCHAR2  N
     * purchasingLeadTime  采购提前期  NUMBER  N
     * minPacking  最小包装量  NUMBER  N
     * benchmarkPrice  基准价  NUMBER  N
     * imageId  图片ID  NUMBER  N
     * invalidDate  失效时间  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * minPoQuantity  最小采购量  NUMBER  N
     * purchaseStatus    VARCHAR2  N
     * modelCode  型号编码  VARCHAR2  N
     * modelName  物料型号  VARCHAR2  N
     * modelStorageDuration    DATE  N
     * temperatureUpper    NUMBER  N
     * temperatureLower    NUMBER  N
     * humidityUpper    NUMBER  N
     * humidityLower    NUMBER  N
     * ulType    VARCHAR2  N
     * copper    VARCHAR2  N
     * sizec    NUMBER  N
     * ppLength    NUMBER  N
     * realitySize    NUMBER  N
     * moqc    NUMBER  N
     * itemRank    VARCHAR2  N
     * conversionRatio  转换比例  VARCHAR2  N
     * inventoryCode  库存单位  VARCHAR2  N
     * isTest  是否检验  VARCHAR2  N
     * isTighten  是否加严  VARCHAR2  N
     * itemPlanWay  物料计划方式  VARCHAR2  N
     * copperFoilType  铜箔类型  VARCHAR2  N
     * standardSize  标准尺寸  VARCHAR2  N
     * moqOrderQuantity  MOQ（起订量）  NUMBER  N
     * itemId  物料ID  NUMBER  Y
     * itemCode  物料编码  VARCHAR2  Y
     * itemName  物料名称  VARCHAR2  Y
     * itemDescription  物料说明  VARCHAR2  N
     * globalFlag  全局标识  VARCHAR2  N
     * enabledAsl  启用ASL  VARCHAR2  N
     * organizationId  库存组织ID  NUMBER  Y
     * uomCode  计量单位  VARCHAR2  N
     * itemStatus  物料状态  VARCHAR2  N
     * purchasingFlag  可采购标识  VARCHAR2  N
     * agentId  采购员ID  NUMBER  N
     * categoryId  采购分类ID  NUMBER  N
     * purchaseCycle  采购周期  VARCHAR2  N
     * purchasingLeadTime  采购提前期  NUMBER  N
     * minPacking  最小包装量  NUMBER  N
     * benchmarkPrice  基准价  NUMBER  N
     * imageId  图片ID  NUMBER  N
     * invalidDate  失效时间  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * cost  成本  NUMBER  N
     * specification  规格型号  VARCHAR2  N
     * region  组织区域  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @POST
    @Path("saveMatterDataManageExternal")
    public String saveMatterDataManageExternal(@FormParam(PARAMS) String params, @FormParam("userName") String userName, @FormParam("password") String password) {
        LOGGER.info("参数params：" + params.toString() + "，参数userName：" + userName + "，password：" + password);
        if (StringUtils.isBlank(params)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
        Map<String, Object> map1 = new HashMap<>();
        SHA1Util shal = new SHA1Util();
        try {
            String encryptedPassword = shal.getEncrypt(password);
            map1.put("userName", userName);
            map1.put("encryptedPassword", password);
            SaafUsersEntity_HI userEntity = iSaafUsers.findUserLogin(map1);  //验证用户登录
            Integer userId = null;
            if (null != userEntity) {
                userId = userEntity.getUserId();
            } else {
                return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
            }
            JSONObject jsonParams = this.parseObjectExternal(params); //调用外部转换json 方法
            JSONObject jsondata = iSrmBaseItemServer.updateMatterDataManageExternal(jsonParams, userId);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.getString(DATA));
        } catch (Exception e) {
            LOGGER.error("--------------------------->操作失败！参数：" + params.toString() + "，异常：" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * 物料数据接口（数据输出）——查询物料数据（用于外部访问的接口）
     * 需要提供用户和密码
     *
     * @param params
     * @param userName
     * @param password
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findMatterDataManageExternal")
    public String findSupplierListExternal(@FormParam(PARAMS) String params, @FormParam("userName") String userName, @FormParam("password") String password,
                                           @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info("参数params：" + params.toString() + "，参数userName：" + userName + "，password：" + password);
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
        Map<String, Object> map1 = new HashMap<>();
        SHA1Util shal = new SHA1Util();
        try {
            String encryptedPassword = shal.getEncrypt(password);
            map1.put("userName", userName);
            map1.put("encryptedPassword", password);
            SaafUsersEntity_HI userEntity = iSaafUsers.findUserLogin(map1);  //验证用户登录
            Integer userId = null;
            if (null != userEntity) {
                userId = userEntity.getUserId();
            } else {
                return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
            }
            JSONObject jsonParams = this.parseObjectExternal(params); //调用外部转换json 方法
            Map<String, Object> map = iSrmBaseItemServer.pushFindMatterDataManageList(jsonParams, userId);
            return JSON.toJSONString(map);
        } catch (Exception e) {
            //e.printStackTrace();
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
        }
    }

    /**
     * 物料查询Lov，按组织查询
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findItemListByOrganization")
    @Produces("application/json")
    public String findItemListByOrganization(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmBaseItemsEntity_HI_RO> result = iSrmBaseItemServer.findItemListByOrganization(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(result);
        } catch (Exception e) {
            //e.printStackTrace();
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
        }
    }

}

