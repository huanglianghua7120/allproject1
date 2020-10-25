package saaf.common.fmw.base.services;


import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.exception.NotLoginException;

import saaf.common.fmw.base.model.entities.SrmBaseItemsBEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseItemsBEntity_HI_RO;
import saaf.common.fmw.base.model.dao.SrmBaseItemsBDAO_HI;
import saaf.common.fmw.base.model.dao.readonly.SrmBaseItemsBDAO_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseItemsB;
import saaf.common.fmw.base.model.inter.server.SrmBaseItemsBServer;
import saaf.common.fmw.base.services.SrmBaseItemsBService;
import saaf.common.fmw.services.CommonAbstractServices;

import java.util.List;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmBaseItemsBService.java
 * Description：物料基表的Service层
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019-05-29     秦晓钊          创建
 * ==============================================================================
 */
@Component("srmBaseItemsBService")
@Path("/srmBaseItemsBService")
public class SrmBaseItemsBService extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseItemsBService.class);

    @Autowired
    private ISrmBaseItemsB srmBaseItemsBServer;

    public SrmBaseItemsBService() {
        super();
    }

    /**
     * Description：保存物料，自动生成的方法
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * itemId  物料ID  NUMBER  Y
     * itemCode  物料编码  VARCHAR2  Y
     * itemName  物料名称  VARCHAR2  Y
     * itemDescription  物料说明  VARCHAR2  N
     * globalFlag  全局标识  VARCHAR2  N
     * enabledAsl  启用ASL  VARCHAR2  N
     * uomCode  计量单位  VARCHAR2  N
     * itemStatus  物料状态  VARCHAR2  N
     * purchasingFlag  可采购标识  VARCHAR2  N
     * agentId  采购员ID  NUMBER  N
     * categoryId  采购分类ID  NUMBER  Y
     * purchaseCycle  采购周期  NUMBER  N
     * purchasingLeadTime  采购提前期  NUMBER  N
     * minPacking  最小包装量  NUMBER  N
     * benchmarkPrice  基准价  NUMBER  N
     * invalidDate  失效时间  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * imageId  图片ID  NUMBER  N
     * itemCode  物料编码  VARCHAR2  Y
     * itemName  物料名称  VARCHAR2  Y
     * itemDescription  物料说明  VARCHAR2  N
     * globalFlag  全局标识(Y/N)  VARCHAR2  N
     * enabledAsl  启用ASL  VARCHAR2  N
     * uomCode  计量单位  VARCHAR2  N
     * itemStatus  物料状态  VARCHAR2  N
     * purchasingFlag  可采购标识  VARCHAR2  N
     * agentId  采购员ID  NUMBER  N
     * categoryId  采购分类ID  NUMBER  Y
     * purchaseCycle  采购周期  NUMBER  N
     * purchasingLeadTime  采购提前期  NUMBER  N
     * minPacking  最小包装量  NUMBER  N
     * benchmarkPrice  基准价  NUMBER  N
     * invalidDate  失效时间  DATE  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * imageId  图片ID  NUMBER  N
     * cost  成本  NUMBER  N
     * specification  规格型号  VARCHAR2  N
     * region  组织区域  VARCHAR2  N
     * itemId  物料ID  NUMBER  Y
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Path("saveSrmBaseItemsB")
    @POST
    public String saveSrmBaseItemsB(@FormParam("params")
                                            String params) {
        LOGGER.info(params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            String resultStr = JSONObject.toJSONString(srmBaseItemsBServer.saveSrmBaseItemsB(jsonParam));
            return resultStr;
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("保存物料异常：" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * Description：查询列表，自动生成的方法
     *
     * @param params    查询参数，JSON格式数据
     * @param pageIndex 页码
     * @param pageRows  每页显示的行数
     * @return 对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    /*@Path("findSrmBaseItemsBList")
    @Produces("application/json")
    public String findSrmBaseItemsBList(@FormParam("params")
                                                String params, @FormParam("pageIndex")
                                                Integer pageIndex, @FormParam("pageRows")
                                                Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("------jsonParam-----" + jsonParam.toString());
            Pagination<SrmBaseItemsBEntity_HI_RO> list = srmBaseItemsBServer.findSrmBaseItemsBList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(list);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("查询物料列表异常：" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }
*/

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
    @Path("findSrmBaseItemsBList")
    @Produces("application/json")
    public String findSrmBaseItemsBList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            Pagination<SrmBaseItemsBEntity_HI_RO> result = srmBaseItemsBServer.findSrmBaseItemsBList(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(result);
        } catch (Exception e) {
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e, 0, null));
        }
    }

    /**
     * Description：查询指定ID的物料，自动生成的方法
     *
     * @param params 查询参数，JSON格式数据
     * @return 物料对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Path("findSrmBaseItemsB")
    @POST
    public String findSrmBaseItemsB(@FormParam("params")
                                            String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            //物料id
            if (null != jsonParam.get("itemId") && !"".equals(jsonParam.get("itemId"))) {
                SrmBaseItemsBEntity_HI_RO obj = srmBaseItemsBServer.findSrmBaseItemsBById(jsonParam);
                return CommonAbstractServices.convertResultJSONObj("S", "查询物料信息成功!", 1, obj);
            } else {
                return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
            }
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询物料信息异常：" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * Description：根据指定的业务实体ID，查询物料
     *
     * @param params    查询参数，JSON格式数据
     * @param pageIndex 页码
     * @param pageRows  每页显示的行数
     * @return 对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Path("findItemsBByOrgId")
    @POST
    public String findItemsBByOrgId(@FormParam("params")
                                            String params, @FormParam("pageIndex")
                                            Integer pageIndex, @FormParam("pageRows")
                                            Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("------jsonParam-----" + jsonParam.toString());
            Pagination list = srmBaseItemsBServer.findItemsBByOrgId(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(list);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询物料列表异常：" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * Description：生效/失效物料基表
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
    @Path("updateInvalidBaseItemB")
    @POST
    public String updateInvalidBaseItemB(@FormParam("params")
                                            String params) {
        LOGGER.info(params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            String resultStr = JSONObject.toJSONString(srmBaseItemsBServer.updateInvalidBaseItemB(jsonParam));
            return resultStr;
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (UtilsException e){
            LOGGER.error("失效物料异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "失效物料异常：" +e.getMessage(), 0, null);
        }catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("失效物料异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", e.getMessage(), 0, null);
        }
    }

    /**
     * Description：删除物料
     *
     * @param params 物料的JSON格式数据
     * @return 对象
     * <p>
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-03     SIE 谢晓霞          创建
     * ==============================================================================
     */
    @POST
    @Path("deleteBaseItemsB")
    public String deleteBaseItemsB(@FormParam("params") String params) {
        LOGGER.info("=======deleteBaseItemsB=====》》》》》》》" + params);
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject json = srmBaseItemsBServer.deleteBaseItemsB(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(json.getString("status"), json.getString("msg"), json.getInteger("count"), json.get("data"));
        } catch (Exception e) {
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }


    /**
     * Description：新增物料主数据导入
     * @param params 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-07     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("saveImportMaterialMaster")
    public String saveImportMaterialMaster(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            System.out.print(jsonParams.toJSONString());
            JSONArray jsonArray = jsonParams.getJSONArray("data");
            JSONObject object = srmBaseItemsBServer.saveImportMaterialMaster(jsonParams);
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
     * Description：招标询价转订单/合同生成物料主数据
     * @param params 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-07     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("createPonMaterialMaster")
    public String createPonMaterialMaster(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject object = srmBaseItemsBServer.createPonMaterialMaster(jsonParams);
            return JSONObject.toJSONString(object);
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

}
