package saaf.common.fmw.po.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLinesEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRequisitionHeadersEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoRequisitionHeaders;
import saaf.common.fmw.po.model.inter.ISrmPoRequisitionLines;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
/**
 * Project Name：SrmPoRequisitionHeadersServices
 * Company Name：SIE
 * Program Name：
 * Description：采购申请头
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Path("/srmPoRequisitionHeadersServices")
@Component
public class SrmPoRequisitionHeadersServices extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRequisitionHeadersServices.class);

    public SrmPoRequisitionHeadersServices() {
        super();
    }

    @Autowired
    private ISrmPoRequisitionHeaders srmPoRequisitionHeadersServer;

    @Autowired
    private ISrmPoRequisitionLines srmPoRequisitionLinesServer;


    /**
     * Description：查询待处理列表
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
    @Path("findPendingList")
    @POST
    public String findPendingList(@FormParam("params") String params,
                                  @FormParam("pageIndex") Integer pageIndex,
                                  @FormParam("pageRows") Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPoRequisitionHeadersEntity_HI_RO> infoList = srmPoRequisitionHeadersServer.findPendingList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }


    /**
     * Description：查询已处理列表
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
    @Path("findHandledList")
    @POST
    public String findHandledList(@FormParam("params") String params,
                                  @FormParam("pageIndex") Integer pageIndex,
                                  @FormParam("pageRows") Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPoRequisitionHeadersEntity_HI_RO> infoList = srmPoRequisitionHeadersServer
                    .findHandledList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }


    /**
     * Description：转交采购员
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("updateRequisitionLines")
    @POST
    public String updateRequisitionLines(@FormParam("params") String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoRequisitionHeadersServer.updateRequisitionLines(jsonParams);
            return JSON.toJSONString(jsondata);
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }


    /**
     * Description：控制活动类型选项
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("selectDeliveryType")
    public String selectDeliveryType(@FormParam("params") String params) throws Exception {
        try{
            JSONObject jsonParam = this.parseObject(params);
            List<SaafLookupValuesEntity_HI_RO> list = srmPoRequisitionHeadersServer.selectDeliveryType(jsonParam);
            jsonParam.put("LookupValues", list);
            jsonParam.put("status", "S");
            jsonParam.put("msg", "查询成功");
            return jsonParam.toJSONString();
        }catch (Exception e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
        
    }

    /**
     * Description：提交，单框架协议
     * @param params 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("saveOrderInfoByDemandSumList")
    public String saveOrderInfoByDemandSumList(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject object = srmPoRequisitionHeadersServer.saveOrderInfoByDemandSumList(jsonParams);
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
     * Description：提交，多框架协议，订单处理页面
     * @param params 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findOrderDealList")
    @POST
    public String findOrderDealList(@FormParam("params") String params,
                                    @FormParam("pageIndex") Integer pageIndex,
                                    @FormParam("pageRows") Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPoRequisitionHeadersEntity_HI_RO> infoList = srmPoRequisitionHeadersServer
                    .findOrderDealList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：多框架协议申请，转订单
     * @param params 多框架参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("transferOrderByMultipleAgr")
    public String transferOrderByMultipleAgr(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject object = srmPoRequisitionHeadersServer.saveOrderByMultipleAgr(jsonParams);
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
     * Description：保存采购申请分配表数据
     * @param params 多框架参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("savePoReqDistributions")
    public String savePoReqDistributions(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject json = srmPoRequisitionHeadersServer.savePoReqDistributions(jsonParam);
            return CommonAbstractServices.convertResultJSONObj("S", "保存成功!", 1, json.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }


    /**
     * Description：查询采购员Lov
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
    @Path("findAgentLov")
    @POST
    public String findAgentLov(@FormParam("params") String params,
                               @FormParam("pageIndex") Integer pageIndex,
                               @FormParam("pageRows") Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info("------jsonParam-----" + jsonParam.toString());
            Pagination<SrmPoRequisitionHeadersEntity_HI_RO> infoList = srmPoRequisitionHeadersServer
                    .findAgentLov(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }


    /**
     * Description：查询采购申请
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
    @Path("findRequisition")
    public String findRequisition(@FormParam("params") String params, @FormParam("pageIndex") Integer pageIndex, @FormParam("pageRows") Integer pageRows) {
        try {

            JSONObject jsonParam = this.parseObject(params);
            pageRows = 200;//显示200行
            Pagination<SrmPoRequisitionHeadersEntity_HI_RO> list = srmPoRequisitionHeadersServer.findRequisition(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(list);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：查询详细信息
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("queryRequisitionInfo")
    public String findForecastInfoTop(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            List<SrmPoRequisitionHeadersEntity_HI_RO> srmPoRequisitionHeadersInfoEntity = srmPoRequisitionHeadersServer.queryRequisitionInfo(jsonParams);
            jsonParams.put("data", srmPoRequisitionHeadersInfoEntity);
            jsonParams.put("status", "S");
            jsonParams.put("msg", "查询成功");
            return JSON.toJSONString(jsonParams);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：保存采购申请
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("updateRequisition")
    public String updateRequisition(@FormParam("params") String params) {
        LOGGER.info("保存参数：" + params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            int operatorUserId = jsonParams.getInteger("operatorUserId");
            JSONObject jsondata = srmPoRequisitionHeadersServer.updateRequisition(jsonParams, operatorUserId);
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
     * Description：删除采购申请行
     * @param params 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("deleteRequisitionLines")
    public String deleteRequisitionLines(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(srmPoRequisitionHeadersServer.deleteRequisitionLines(jsonParams));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：删除采购申请头
     * @param params 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("deleteRequisitionHeaders")
    @POST
    public String deleteRequisitionHeaders(@FormParam("params") String params) {
        LOGGER.info("删除信息,参数：" + params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoRequisitionHeadersServer.deleteRequisitionHeaders(jsonParams);
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
     * Description：修改采购申请状态
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("updateStatusRequisition")
    public String updateStatusRequisition(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoRequisitionHeadersServer.updateStatusRequisition(jsonParams);
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
     * Description：采购申请导入
     * @param params 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("saveImportRequisition")
    public String batchImportSupply(@FormParam(PARAMS) String params) {
        try {
            int userId = 1;
            JSONObject jsonParams = this.parseObject(params);
            System.out.print(jsonParams.toJSONString());
            JSONArray jsonArray = jsonParams.getJSONArray("data");
            JSONObject object = srmPoRequisitionHeadersServer.saveImportRequisition(jsonParams, userId);
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
     * Description：查询行上已转订单信息
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("findPoHeaders")
    @POST
    public String findPoHeaders(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            List<SrmPoLinesEntity_HI_RO> infoList = srmPoRequisitionHeadersServer.findPoHeaders(jsonParam);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：采购申请按选择的行转换成采购订单
     * @param params 采购申请参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("saveReqTransferOrderByLines")
    public String saveReqTransferOrderByLines(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoRequisitionHeadersServer.saveReqTransferOrderByLines(jsonParams);
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
     * Description：采购申请查询页按选择的行转换成采购订单
     * @param params 采购申请参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-01     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("saveReqTransferOrder")
    public String saveReqTransferOrder(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPoRequisitionHeadersServer.saveReqTransferOrder(jsonParams);
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
     * Description：拆分申请行
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-02     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("saveSplitRequisitionLine")
    public String saveSplitRequisitionLine(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsonData = srmPoRequisitionLinesServer.saveSplitRequisitionLine(jsonParams);
            return convertResultJSONObj(jsonData.getString(STATUS),jsonData.getString(MSG),jsonData.getInteger(COUNT),jsonData.get(DATA));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }


    /**
     * Description：保存供应商
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-02     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("saveRequisitionLine")
    public String saveRequisitionLine(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsonData = srmPoRequisitionLinesServer.saveRequisitionLine(jsonParams);
            return convertResultJSONObj(jsonData.getString(STATUS),jsonData.getString(MSG),jsonData.getInteger(COUNT),jsonData.get(DATA));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }



}
