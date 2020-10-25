package saaf.common.fmw.po.services;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.po.model.inter.IDeliveryOrder;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Project Name：SrmPoDeliveryOrderServices
 * Company Name：SIE
 * Program Name：
 * Description：送货单
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Path("/srmPoDeliveryOrderServices")
@Component("srmPoDeliveryOrderServices")
public class SrmPoDeliveryOrderServices extends CommonAbstractServices {
	
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoDeliveryOrderServices.class);
	
	@Autowired
	private IDeliveryOrder deliveryOrder;

    /**
     * Description：采购匹配送货单
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
    @Path("findPoLinesList")
    @POST
    public String findPoLinesList(@FormParam("params")
        String params,@FormParam(PAGE_INDEX)Integer pageIndex,@FormParam(PAGE_ROWS)Integer pageRows) {
        System.out.println ("========"+params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            return JSONObject.toJSONString(deliveryOrder.findLinesList(jsonParam,pageIndex,pageRows));
        }  catch (Exception e) {
           LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：匹配采购订单(送货单)
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
    @Path("findNoticesList")
    @POST
    public String findNoticesList(@FormParam("params")
        String params,@FormParam(PAGE_INDEX)Integer pageIndex,@FormParam(PAGE_ROWS)Integer pageRows) {
        System.out.println ("========"+params);
        try {
            JSONObject jsonParam = this.parseObject(params);
            return JSONObject.toJSONString(deliveryOrder.findNoitceLineList(jsonParam,pageIndex,pageRows));
        }  catch (Exception e) {
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
    public String saveDeliveryOrder(@FormParam("params")
        String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            if(jsonParam.getInteger("varSupplierId")==null){
                return CommonAbstractServices.convertResultJSONObj("E", "非供应商不能创建送货通知!", 0, null);
            }
            JSONObject posJson = deliveryOrder.saveDeliveryOrder(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
        }  catch (UtilsException e){
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        }  catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }
}
