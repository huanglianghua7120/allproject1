package saaf.common.fmw.po.model.inter;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import saaf.common.fmw.po.model.entities.readonly.SrmPoDeliveryHeadersEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLinesEntity_HI_RO;
/**
 * Project Name：IDeliveryHeaders
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
public interface IDeliveryHeaders {

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
    Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryHeadersList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

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
    Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryHeadersNoticeList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

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
	Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

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

    JSONObject saveDeliveryOrder(JSONObject params) throws Exception;


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
	Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryPoPrint(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

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
	Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryPoLinePrint(JSONObject params,Integer pageIndex,Integer pageRows) throws Exception;

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
	Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryExternalSupplierPrint(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;


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
	Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryDetailLinePrint(JSONObject params,Integer pageIndex,Integer pageRows) throws Exception;


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
    Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryNumberLov(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

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

    public JSONObject saveDeliveryAffReg(JSONObject params) throws Exception;

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
	Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryExternalList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;


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
	public JSONObject saveApplyCancel(JSONObject params) throws Exception;
    

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
    Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryDetailLine(JSONObject params,Integer pageIndex,Integer pageRows) throws Exception;
    
    
    

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
    Pagination<SrmPoDeliveryHeadersEntity_HI_RO> findDeliveryDetailLineCount(JSONObject params,Integer pageIndex,Integer pageRows) throws Exception;


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
    JSONObject updateReceipt(JSONObject params) throws Exception;

}
