package saaf.common.fmw.po.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.intf.model.inter.IIntfDelivery;
import saaf.common.fmw.po.model.entities.readonly.SrmPoDeliveryHeadersEntity_HI_RO;
import saaf.common.fmw.po.model.inter.IDeliveryHeaders;
import saaf.common.fmw.po.model.inter.ISaafUsers;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Project Name：SrmPoDeliveryHeaderServices
 * Company Name：SIE
 * Program Name：
 * Description：送货单列表
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */

@Path("/srmPoDeliveryHeaderServices")
@Component
public class SrmPoDeliveryHeaderServices extends CommonAbstractServices {
	
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoDeliveryHeaderServices.class);
	
	@Autowired
	private IDeliveryHeaders srmPoDeliveryHeadersServer;
	
	@Autowired
    private IIntfDelivery  intfDeliveryServer;

    @Autowired
    private ISaafUsers iSaafUsers;


    /**
     * Description：查询送货单列表-订单回货
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
    @Path("findDeliveryHeadersList")
    @POST
    public String findDeliveryHeadersList(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            LOGGER.info("------jsonParam------" + jsonParams.toString());
            if ("EX".equals(jsonParams.getString("varPlatformCode"))) { //是供应商查询
                jsonParams.put("supplier_id", jsonParams.getIntValue("varSupplierId"));
            }
            Pagination<SrmPoDeliveryHeadersEntity_HI_RO> infoList = srmPoDeliveryHeadersServer.findDeliveryHeadersList(jsonParams, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：查询送货单列表-通知回货
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
    @Path("findDeliveryHeadersNoticeList")
    @POST
    public String findDeliveryHeadersNoticeList(@FormParam("params")
                                                  String params, @FormParam("pageIndex")
                                                  Integer pageIndex, @FormParam("pageRows")
                                                  Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            LOGGER.info("------jsonParam------" + jsonParams.toString());
            if ("EX".equals(jsonParams.getString("varPlatformCode"))) { //是供应商查询
                jsonParams.put("supplier_id", jsonParams.getIntValue("varSupplierId"));
            }
            Pagination<SrmPoDeliveryHeadersEntity_HI_RO> infoList = srmPoDeliveryHeadersServer.findDeliveryHeadersNoticeList(jsonParams, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }


    /**
     * Description：送货单查询-内部
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
    @Path("findDeliveryList")
    @POST
    public String findDeliveryList(@FormParam("params")
                                                        String params, @FormParam("pageIndex")
                                                        Integer pageIndex, @FormParam("pageRows")
                                                        Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            LOGGER.info("------findDeliveryList------" + jsonParams.toString());
            Pagination<SrmPoDeliveryHeadersEntity_HI_RO> infoList = srmPoDeliveryHeadersServer.findDeliveryList(jsonParams, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    /**
     * Description：创建送货单
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * syncResult  状态，默认0（新建）,1（成功），2（失败）  NUMBER  N
     * errorMsg  失败原因  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * temperature  温度  VARCHAR2  N
     * humidity  湿度  VARCHAR2  N
     * deliveryAddress  送货地址  VARCHAR2  N
     * deliveryHeaderId  送货单ID  NUMBER  Y
     * deliveryNumber  送货单编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * receiveToOrganizationId  收货组织ID  NUMBER  N
     * status  送货单状态(POS_DELIVERY_STATUS)  VARCHAR2  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * deliveryDate  送货日期  DATE  N
     * transportNumber  （废弃）运单号  VARCHAR2  N
     * instId  （废弃）分厂id  NUMBER  N
     * receiveBy  （废弃）接收人id  NUMBER  N
     * receiveFlag  （废弃）是否已接收  VARCHAR2  N
     * documentType  （废弃）单据类型  VARCHAR2  N
     * isPassU9  （废弃）是否传U9(N：不需要 Y：需要 R：需要重传 S：成功)  VARCHAR2  N
     * u9DocNumber  （废弃）U9接收单号  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * deliveryHeaderId  送货单ID  NUMBER  Y
     * deliveryNumber  送货单编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * receiveToOrganizationId  收货组织ID  NUMBER  N
     * status  送货单状态(POS_DELIVERY_STATUS)  VARCHAR2  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * deliveryDate  送货日期  DATE  N
     * transportNumber  （废弃）运单号  VARCHAR2  N
     * instId  （废弃）分厂id  NUMBER  N
     * receiveBy  （废弃）接收人id  NUMBER  N
     * receiveFlag  （废弃）是否已接收  VARCHAR2  N
     * documentType  （废弃）单据类型  VARCHAR2  N
     * isPassU9  （废弃）是否传U9(N：不需要 Y：需要 R：需要重传 S：成功)  VARCHAR2  N
     * u9DocNumber  （废弃）U9接收单号  VARCHAR2  N
     * description  说明  VARCHAR2  N
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

    @Path("saveDeliveryOrder")
    @POST
    public String saveDeliveryOrder(@FormParam("params")String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            if(jsonParam.getInteger("varSupplierId")==null){
                return CommonAbstractServices.convertResultJSONObj("E", "非供应商不能创建送货通知!", 0, null);
            }
            JSONObject posJson = srmPoDeliveryHeadersServer.saveDeliveryOrder(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：送货单-内部-确认/拒绝
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("saveDeliveryAffRegBtn")
    public String saveDeliveryAffRegBtn(@FormParam("params")String params) {
        LOGGER.info("=======saveDeliveryAffRegBtn》》》》》》》》》====="+params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            JSONObject json = srmPoDeliveryHeadersServer.saveDeliveryAffReg(paramJSON);
            return CommonAbstractServices.convertResultJSONObj(json.getString("status"), json.getString("msg"), json.getInteger("count"), json.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：送货单数据接口（数据输出）——查询送货单数据（用于外部访问的接口）
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
    @Path("findDeliveryExternal")
    public String findDeliveryExternal(@FormParam(PARAMS)String params,@FormParam("userName")String userName,@FormParam("password")String password,
                                           @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows){
        LOGGER.info("参数params："+params.toString()+"，参数userName："+userName+"，password："+password);
        if(StringUtils.isBlank(userName)||StringUtils.isBlank(password)){
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"登录异常，用户名或密码为空！",0,null);
        }
        boolean flag = iSaafUsers.userLoginByDatabase(userName,password);  //验证用户登录
        if(!flag){
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"登录异常，请输入正确的用户名和密码！",0,null);
        }
        try {
            JSONObject paramJSON = this.parseObjectExternal(params);//调用外部转换json 方法
            Pagination<SrmPoDeliveryHeadersEntity_HI_RO> infoList = srmPoDeliveryHeadersServer.findDeliveryList(paramJSON, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：送货通知查询-供应商/外部
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
    @Path("findDeliveryExternalList")
    @POST
    public String findDeliveryExternalList(@FormParam("params")
                                                        String params, @FormParam("pageIndex")
                                                        Integer pageIndex, @FormParam("pageRows")
                                                        Integer pageRows) {
        LOGGER.info("参数：" + params);
        try {
            JSONObject jsonParams = this.parseObject(params);
            if ("EX".equals(jsonParams.getString("varPlatformCode"))) { //是供应商查询
                jsonParams.put("supplier_id", jsonParams.getIntValue("varSupplierId"));
            }
            Pagination<SrmPoDeliveryHeadersEntity_HI_RO> infoList = srmPoDeliveryHeadersServer.findDeliveryExternalList(jsonParams, pageIndex, pageRows);
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：送货通知查询-申请取消
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @POST
    @Path("saveApplyCancel")
    public String saveApplyCancel(@FormParam("params")String params) {
        LOGGER.info("=======saveApplyCancel====="+params);
        try {
            JSONObject paramJSON = this.parseObject(params);
            JSONObject json = srmPoDeliveryHeadersServer.saveApplyCancel(paramJSON);
            return CommonAbstractServices.convertResultJSONObj(json.getString("status"), json.getString("msg"), json.getInteger("count"), json.get("data"));
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：打印送货单-订单回货查询-头
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
    @Path("findDeliveryPoPrint")
    @POST
    public String findDeliveryPoPrint(@FormParam("params")String params,
                                             @FormParam("pageIndex")Integer pageIndex,
                                             @FormParam("pageRows")Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            LOGGER.info("查询参数:===findDeliveryPoPrint=======>>>" + jsonParams);
            Pagination<SrmPoDeliveryHeadersEntity_HI_RO> infoList = srmPoDeliveryHeadersServer.findDeliveryPoPrint(jsonParams, pageIndex, pageRows);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }


    /**
     * Description：打印送货单-订单回货查询-行
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
    @Path("findDeliveryPoLinePrint")
    @POST
    public String findDeliveryPoLinePrint(@FormParam("params")String params, @FormParam("pageIndex")Integer pageIndex, @FormParam("pageRows") Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            Pagination<SrmPoDeliveryHeadersEntity_HI_RO> infoList = srmPoDeliveryHeadersServer.findDeliveryPoLinePrint(jsonParams, pageIndex, pageRows);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    /**
     * 打印送货单-通知回货查询
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    /*@Path("findPrintDeliveryNoticeHeader")
    @POST
    public String findPrintDeliveryNoticeHeader(@FormParam("params")String params,
                                            @FormParam("pageIndex")Integer pageIndex,
                                            @FormParam("pageRows")Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            LOGGER.info("查询参数:===findPrintDeliveryNoticeHeader=======>>>" + jsonParams);
            Pagination<SrmPoDeliveryHeadersEntity_HI_RO> infoList = srmPoDeliveryHeadersServer.findDeliveryDetailHeader(jsonParams, pageIndex, pageRows);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }*/

    /**
     * Description：送货单-查询(重新打印)
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
    @Path("findDeliveryExternalSupplierPrint")
    @POST
    public String findDeliveryExternalSupplierPrint(@FormParam("params")String params,
                                            @FormParam("pageIndex")Integer pageIndex,
                                            @FormParam("pageRows")Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            LOGGER.info("查询参数:===findDeliveryExternalSupplierPrint=======>>>" + jsonParams);
            Pagination<SrmPoDeliveryHeadersEntity_HI_RO> infoList = srmPoDeliveryHeadersServer.findDeliveryExternalSupplierPrint(jsonParams, pageIndex, pageRows);
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：查询送货单行打印(重新打印)
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
    @Path("findDeliveryDetailLinePrint")
    @POST
    public String findDeliveryDetailLinePrint(@FormParam("params")
                                                      String params, @FormParam("pageIndex")
                                                      Integer pageIndex, @FormParam("pageRows")
                                                      Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            if(null==jsonParams.get("deliveryHeaderId") || "".equals(jsonParams.getString("deliveryHeaderId"))){
                jsonParams.put("deliveryHeaderId", -1);
            }
            Pagination<SrmPoDeliveryHeadersEntity_HI_RO> infoList = srmPoDeliveryHeadersServer.findDeliveryDetailLinePrint(jsonParams, pageIndex, pageRows);

            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    /**
     * Description：LOV:查询送货单号
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
    @Path("findDeliveryNumberLov")
    @POST
    public String findDeliveryNumberLov(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmPoDeliveryHeadersEntity_HI_RO> supplierlov = srmPoDeliveryHeadersServer.findDeliveryNumberLov(jsonParam, pageIndex, pageRows);
            return JSONObject.toJSONString(supplierlov);
        }catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    
    
    /**   
     * 查询送货单明细头
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    /*@Path("findDeliveryDetailHeader")
    @POST
    public String findDeliveryDetailHeader(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
        	JSONObject jsonParams = this.parseObject(params);
            if(null==jsonParams.get("deliveryHeaderId") || "".equals(jsonParams.getString("deliveryHeaderId"))){
                jsonParams.put("deliveryHeaderId", -1);
            }
            Pagination<SrmPoDeliveryHeadersEntity_HI_RO> infoList = srmPoDeliveryHeadersServer.findDeliveryDetailHeader(jsonParams, pageIndex, pageRows);
            
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }*/


    /**
     * Description：LOV:查询送货单行
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
    @Path("findDeliveryDetailLine")
    @POST
    public String findDeliveryDetailLine(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
        	JSONObject jsonParams = this.parseObject(params);
            if(null==jsonParams.get("deliveryHeaderId") || "".equals(jsonParams.getString("deliveryHeaderId"))){
                jsonParams.put("deliveryHeaderId", -1);
            }
            Pagination<SrmPoDeliveryHeadersEntity_HI_RO> infoList = srmPoDeliveryHeadersServer.findDeliveryDetailLine(jsonParams, pageIndex, pageRows);
            
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    /**
     * Description：查询送货单行打印汇总
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
    @Path("findDeliveryDetailLineCount")
    @POST
    public String findDeliveryDetailLineCount(@FormParam("params")
        String params, @FormParam("pageIndex")
        Integer pageIndex, @FormParam("pageRows")
        Integer pageRows) {
        try {
        	JSONObject jsonParams = this.parseObject(params);
            if(null==jsonParams.get("deliveryHeaderId") || "".equals(jsonParams.getString("deliveryHeaderId"))){
                jsonParams.put("deliveryHeaderId", -1);
            }
            Pagination<SrmPoDeliveryHeadersEntity_HI_RO> infoList = srmPoDeliveryHeadersServer.findDeliveryDetailLineCount(jsonParams, pageIndex, pageRows);
            
            LOGGER.info("-->>参数：" + params + "查询成功！");
            return JSON.toJSONString(infoList);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }


    /**
     * Description：取消收货单
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Path("updateReceipt")
    @POST
    public String updateReceipt(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = srmPoDeliveryHeadersServer.updateReceipt(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
    
    /**
     * Description：接收
     * @param params 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq            创建
     * =======================================================================
     */
    @Path("pushDelivery")
    @POST
    public String pushDelivery(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Integer varUserId = jsonParam.getInteger("varUserId");
            Integer deliveryHeaderId = jsonParam.getInteger("deliveryHeaderId");
            JSONObject json = intfDeliveryServer.pushDelivery(deliveryHeaderId, varUserId);
            return CommonAbstractServices.convertResultJSONObj(json.getString("status"), json.getString("msg"), json.getInteger("count"), json.get("data"));
        } catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
           LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

}
