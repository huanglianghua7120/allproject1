package saaf.common.fmw.po.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafUsers;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.po.model.entities.readonly.SrmPoForecastInfoEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoForecastLinesEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoForecastInfo;
import saaf.common.fmw.po.model.inter.ISrmPoForecastLines;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.utils.SHA1Util;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Project Name：SrmPoForecastInfoService
 * Company Name：SIE
 * Program Name：
 * Description：采购预测
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoForecastInfoService")
@Path("/srmPoForecastInfoService")
public class SrmPoForecastInfoService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoForecastInfoService.class);

    public SrmPoForecastInfoService() {
        super();
    }

    @Autowired
    private ISaafUsers iSaafUsers;
    @Autowired
    private ISrmPoForecastInfo srmPoForecastInfo;
    @Autowired
    private ISrmPoForecastLines srmPoForecastLines;

    /**
     * Description：采购预测物料查询
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
    @Path("findPoForecastItemLov")
    public String findPoForecastItemLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            Pagination<SrmPoForecastLinesEntity_HI_RO> pag = srmPoForecastLines.findPoForecastItemLov(jsonParams, pageIndex, pageRows);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：采购预测(供应商) 业务实体及收获组织下拉框
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("findPoForecastSupplierOption")
    public String findPoForecastSupplierOption(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject object = srmPoForecastInfo.findPoForecastSupplierOption(jsonParams);

            return JSON.toJSONString(object);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }


    /**
     * Description：查询采购预测详细信息
     * @param params 查询参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("queryForecastInfoList")
    public String findForecastInfoTop(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            List<SrmPoForecastInfoEntity_HI_RO> srmPoForecastInfoEntity = srmPoForecastInfo.queryForecastInfoList(jsonParams);
            jsonParams.put("srmPoForecastInfoEntity", srmPoForecastInfoEntity);
            jsonParams.put("status", "S");
            jsonParams.put("msg", "查询成功");
            return JSON.toJSONString(jsonParams);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：查询采购预测
     * @param params 查询参数
     * @param pageIndex 页码
     * @param pageRows 行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("findForecastInfoList")
    public String findForecastInfoList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            Pagination<SrmPoForecastInfoEntity_HI_RO> pag = srmPoForecastInfo.findForecastInfoList(jsonParams, pageIndex, pageRows);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：保存采购预测，输入头字段（forecastStatus，forecastName，predictionType，orgId，organizationId，operatorUserId）
     *      行字段（categoryId，itemId，supplierId，demandQuantity，demandDate，buyerId）
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * sourceId  数据来源ID  VARCHAR2  N
     * forecastType  预测分类  VARCHAR2  N
     * remark  备注  VARCHAR2  N
     * forecastNumber  预测单号  VARCHAR2  N
     * forecastId  预测ID  NUMBER  Y
     * forecastName  预测名称  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * predictionType  预测类型  VARCHAR2  N
     * forecastStatus  预测状态  VARCHAR2  N
     * invalidDate  失效日期  DATE  N
     * invalidBy  失效人  NUMBER  N
     * releaseDate  发布日期  DATE  N
     * categoryName  （废弃）类别名称  VARCHAR2  N
     * categoryCode  （废弃）类别编码  VARCHAR2  N
     * forecastDate  （废弃）预测日期  DATE  N
     * itemCode  （废弃）物料编码  VARCHAR2  N
     * itemDescription  （废弃）物料名称  VARCHAR2  N
     * employeeId  （废弃）采购员  NUMBER  N
     * needQuantity  （废弃）需求数量  NUMBER  N
     * needByDate  （废弃）需求日期  DATE  N
     * vendnameGroup  （废弃）供应商组合  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * forecastId  预测ID  NUMBER  Y
     * forecastName  预测名称  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * predictionType  预测类型  VARCHAR2  N
     * forecastStatus  预测状态  VARCHAR2  N
     * invalidDate  失效日期  DATE  N
     * invalidBy  失效人  NUMBER  N
     * releaseDate  （废弃）发布日期  DATE  N
     * categoryName  （废弃）类别名称  VARCHAR2  N
     * categoryCode  （废弃）类别编码  VARCHAR2  N
     * forecastDate  （废弃）预测日期  DATE  N
     * itemCode  （废弃）物料编码  VARCHAR2  N
     * itemDescription  （废弃）物料名称  VARCHAR2  N
     * employeeId  （废弃）采购员  NUMBER  N
     * needQuantity  （废弃）需求数量  NUMBER  N
     * needByDate  （废弃）需求日期  DATE  N
     * vendnameGroup  （废弃）供应商组合  VARCHAR2  N
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
    @Path("updatePoForecastInfo")
    @POST
    public String updatePoForecastInfo(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject poJson = srmPoForecastInfo.updatePoForecastInfo(jsonParam);
            return JSON.toJSONString(poJson);
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：导入模板下载
     * @param params 查询参数
     * @param pageIndex 页码
     * @param pageRows 行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("importPoForecastExcelDownload")
    public String importTemplatesExcel(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info("Excel导出，" + pageIndex + ",pageSize:" + pageRows + ",params:" + params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            Pagination<SrmPoForecastInfoEntity_HI_RO> pag = srmPoForecastInfo.findForecastInfoList(jsonParams, pageIndex, pageRows);
            LOGGER.info(pag.toString());
            return JSON.toJSONString(pag);
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：批量导入
     * @param params 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("saveImportForecast")
    public String saveImportForecast(@FormParam(PARAMS) String params) {
        UserInfoSessionBean sessionBean = null;
        sessionBean = getUserSessionBean();
        int userId = sessionBean.getUserId();
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject object = srmPoForecastInfo.saveImportForecast(jsonParams, userId);
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
     * Description：采购预测行删除
     * @param params 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("deleteForecastLine")
    @POST
    public String deleteForecastLine(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPoForecastLines.deleteForecastLine(jsonParam);
            return JSON.toJSONString(posJson);
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：发布采购预测
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("updatePublishForecast")
    @POST
    public String updatePublishForecast(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPoForecastInfo.updatePublishForecast(jsonParam);
            return JSON.toJSONString(posJson);
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：撤回采购预测
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("updateRetractForecast")
    @POST
    public String updateRetractForecast(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPoForecastInfo.updateRetractForecast(jsonParam);
            return JSON.toJSONString(posJson);
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：失效采购预测
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("updateInvalidForecast")
    @POST
    public String updateInvalidForecast(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPoForecastInfo.updateInvalidForecast(jsonParam);
            return JSON.toJSONString(posJson);
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：提交采购预测
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("updateSubmitForecast")
    @POST
    public String updateSubmitForecast(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPoForecastInfo.updateSubmitForecast(jsonParam);
            return JSON.toJSONString(posJson);
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：保存采购预测接口，输入头字段（forecastStatus，forecastName，predictionType，orgId，organizationId）
     * 行字段（categoryId，itemId，supplierId，demandQuantity，demandDate，buyerId）
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * sourceId  数据来源ID  VARCHAR2  N
     * forecastType  预测分类  VARCHAR2  N
     * remark  备注  VARCHAR2  N
     * forecastNumber  预测单号  VARCHAR2  N
     * forecastId  预测ID  NUMBER  Y
     * forecastName  预测名称  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * predictionType  预测类型  VARCHAR2  N
     * forecastStatus  预测状态  VARCHAR2  N
     * invalidDate  失效日期  DATE  N
     * invalidBy  失效人  NUMBER  N
     * releaseDate  发布日期  DATE  N
     * categoryName  （废弃）类别名称  VARCHAR2  N
     * categoryCode  （废弃）类别编码  VARCHAR2  N
     * forecastDate  （废弃）预测日期  DATE  N
     * itemCode  （废弃）物料编码  VARCHAR2  N
     * itemDescription  （废弃）物料名称  VARCHAR2  N
     * employeeId  （废弃）采购员  NUMBER  N
     * needQuantity  （废弃）需求数量  NUMBER  N
     * needByDate  （废弃）需求日期  DATE  N
     * vendnameGroup  （废弃）供应商组合  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * forecastId  预测ID  NUMBER  Y
     * forecastName  预测名称  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * predictionType  预测类型  VARCHAR2  N
     * forecastStatus  预测状态  VARCHAR2  N
     * invalidDate  失效日期  DATE  N
     * invalidBy  失效人  NUMBER  N
     * releaseDate  （废弃）发布日期  DATE  N
     * categoryName  （废弃）类别名称  VARCHAR2  N
     * categoryCode  （废弃）类别编码  VARCHAR2  N
     * forecastDate  （废弃）预测日期  DATE  N
     * itemCode  （废弃）物料编码  VARCHAR2  N
     * itemDescription  （废弃）物料名称  VARCHAR2  N
     * employeeId  （废弃）采购员  NUMBER  N
     * needQuantity  （废弃）需求数量  NUMBER  N
     * needByDate  （废弃）需求日期  DATE  N
     * vendnameGroup  （废弃）供应商组合  VARCHAR2  N
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
    @Path("updatePoForecastInfoExternal")
    @Produces("application/json")
    public String findprocurementListExternal(@FormParam(PARAMS) String params, @FormParam("userName") String userName, @FormParam("password") String password) {
        LOGGER.info("参数params：" + params.toString() + "，参数userName：" + userName + "，password：" + password);
        if (com.yhg.base.utils.StringUtils.isBlank(userName) || com.yhg.base.utils.StringUtils.isBlank(password)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "登录异常，用户名或密码为空！", 0, null);
        }
        Map<String, Object> map1 = new HashMap<>();
        SHA1Util shal = new SHA1Util();
        try {
            //String encryptedPassword = shal.getEncrypt(password);
            map1.put("userName", userName);
            map1.put("encryptedPassword", password);
            SaafUsersEntity_HI userEntity = iSaafUsers.findUserLogin(map1);  //验证用户登录
            Integer userId = null;
            if (null != userEntity) {
                userId = userEntity.getUserId();
            } else {
                return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "登录异常，请输入正确的用户名和密码！", 0, null);
            }
            JSONObject jsonParams = this.parseObjectExternal(params); //调用外部转换json 方法
            JSONObject poJson = srmPoForecastInfo.updatePoForecastInfoExternal(jsonParams, userId);
            return JSON.toJSONString(poJson);
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：采购预测物料查询
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
    @Path("findPoForecastItemLovForLine")
    public String findPoForecastItemLovForLine(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            Pagination<SrmPoForecastLinesEntity_HI_RO> pag = srmPoForecastLines.findPoForecastItemLovForLine(jsonParams, pageIndex, pageRows);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(pag);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
}
