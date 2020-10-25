package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.po.model.entities.readonly.SrmPoNoticeEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoNoticeLineEntity_HI_RO;

import java.util.List;
import java.util.Map;

/**
 * Project Name：ISrmPoNotice
 * Company Name：SIE
 * Program Name：
 * Description：送货通知
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
public interface ISrmPoNotice {

    /**
     * Description：查询送货通知
     * @param params 查询条件参数
     * @param pageIndex 页码
     * @param pagerows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    Pagination<SrmPoNoticeEntity_HI_RO> findNoticeList(JSONObject params, Integer pageIndex, Integer pagerows) throws Exception;

    /**
     * Description：送货通知
     * @param params 查询条件参数
     * @param pageIndex 页码
     * @param pagerows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    Pagination<SrmPoNoticeEntity_HI_RO> findNoticeLists(JSONObject params, Integer pageIndex, Integer pagerows) throws Exception;


    /**
     * Description：查询送货通知信息-内部
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
    Pagination<SrmPoNoticeEntity_HI_RO> findNoticeListsNew(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * Description：查询送货通知行信息
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    Pagination<SrmPoNoticeLineEntity_HI_RO> findNoticeLineList(JSONObject params) throws Exception;
    /**
     * Description：关闭送货单
     * @param params 关闭条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject updateColseNotice(JSONObject params) throws Exception;
    /**
     * Description：修改送货通知状态
     * @param params 修改条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject updateNoticeStatus(JSONObject params) throws Exception;

    /**
     * Description：更新送货通知状态
     * @param params 更新条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject updateSrmPoNotice(JSONObject params) throws Exception;
    /**
     * Description：保存通知
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
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

    JSONObject saveNotice(JSONObject params) throws Exception;
    /**
     * Description：采购订单lov
     * @param params 查询条件参数
     * @param pageIndex 页码
     * @param pagerows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    Pagination<SrmPoNoticeEntity_HI_RO> findNoticeLov(JSONObject params, Integer pageIndex, Integer pagerows) throws Exception;
    /**
     * Description：查询送货通知(1周)
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    List<SrmPoNoticeEntity_HI_RO> findNoticeList(JSONObject params) throws Exception;
    /**
     * Description：查询送货通知总数
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    SrmPoNoticeEntity_HI_RO findNoticeListSum(JSONObject params) throws Exception;
    /**
     * Description：根据ID查询送货通知
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    List<SrmPoNoticeEntity_HI_RO> findDeliveryByNoticeId(JSONObject params)throws Exception;
    /**
     * Description：根据CODE查询送货通知
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    Pagination<SrmPoNoticeEntity_HI_RO> findNoticeCodeLov(JSONObject params,Integer pageIndex,Integer pagetRows)throws Exception;

    /**
     * Description：供应商导出送货通知(已废弃)
     * @param parameters 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    List<SrmPoNoticeEntity_HI_RO> findSupplyExport(JSONObject parameters) throws Exception;

    /**
     * Description：保存送货通知行
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * accepterName  通知接收人  VARCHAR2  N
     * uomCode  采购单位  VARCHAR2  N
     * firstFeedbackRecodeDate  第一次反馈记录时间  DATE  N
     * allotLineId  调拨计划差异行ID  NUMBER  N
     * noticeLineNumber  通知单行号  NUMBER  N
     * safeStock  安全库存  NUMBER  N
     * serialNo  流水号  VARCHAR2  N
     * maxStock  最大库存量  NUMBER  N
     * nowStock  现有量  NUMBER  N
     * itemPlanType  物料计划方式  VARCHAR2  N
     * lineId  送货通知单行ID  NUMBER  Y
     * poNoticeId  送货通知单ID  NUMBER  Y
     * poHeaderId  采购订单ID  NUMBER  N
     * poLineId  采购订单行ID  NUMBER  N
     * noticeDeliveryDate  通知送货日期  DATE  N
     * noticeDeliveryQty  通知送货数量  NUMBER  N
     * onWayQty  在途数量（已创建送货单数量）  NUMBER  N
     * lineComments  送货备注  VARCHAR2  N
     * lineStatus  行状态  VARCHAR2  N
     * originalDeliveryDate  原通知送货日期  DATE  N
     * originalDeliveryQty  原通知送货数量  NUMBER  N
     * feedbackAdjustDate  反馈调整日期  DATE  N
     * feedbackAdjustQty  反馈调整数量  NUMBER  N
     * feedbackStatus  反馈状态  VARCHAR2  N
     * feedbackResult  反馈结果  VARCHAR2  N
     * rejectReason  供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * lineId  送货通知单行ID  NUMBER  Y
     * poNoticeId  送货通知单ID  NUMBER  Y
     * poHeaderId  采购订单ID  NUMBER  N
     * poLineId  采购订单行ID  NUMBER  N
     * noticeDeliveryDate  通知送货日期  DATE  N
     * noticeDeliveryQty  通知送货数量  NUMBER  N
     * onWayQty  在途数量（已创建送货单数量）  NUMBER  N
     * lineComments  送货备注  VARCHAR2  N
     * lineStatus  行状态  VARCHAR2  N
     * originalDeliveryDate  原通知送货日期  DATE  N
     * originalDeliveryQty  原通知送货数量  NUMBER  N
     * feedbackAdjustDate  反馈调整日期  DATE  N
     * feedbackAdjustQty  反馈调整数量  NUMBER  N
     * feedbackStatus  反馈状态  VARCHAR2  N
     * feedbackResult  反馈结果  VARCHAR2  N
     * rejectReason  供应商拒绝原因  VARCHAR2  N
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

    JSONObject saveNoticeLine(JSONObject params) throws Exception;

    /**
     * Description：保存送货通知行-供应商
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * accepterName  通知接收人  VARCHAR2  N
     * uomCode  采购单位  VARCHAR2  N
     * firstFeedbackRecodeDate  第一次反馈记录时间  DATE  N
     * allotLineId  调拨计划差异行ID  NUMBER  N
     * noticeLineNumber  通知单行号  NUMBER  N
     * safeStock  安全库存  NUMBER  N
     * serialNo  流水号  VARCHAR2  N
     * maxStock  最大库存量  NUMBER  N
     * nowStock  现有量  NUMBER  N
     * itemPlanType  物料计划方式  VARCHAR2  N
     * lineId  送货通知单行ID  NUMBER  Y
     * poNoticeId  送货通知单ID  NUMBER  Y
     * poHeaderId  采购订单ID  NUMBER  N
     * poLineId  采购订单行ID  NUMBER  N
     * noticeDeliveryDate  通知送货日期  DATE  N
     * noticeDeliveryQty  通知送货数量  NUMBER  N
     * onWayQty  在途数量（已创建送货单数量）  NUMBER  N
     * lineComments  送货备注  VARCHAR2  N
     * lineStatus  行状态  VARCHAR2  N
     * originalDeliveryDate  原通知送货日期  DATE  N
     * originalDeliveryQty  原通知送货数量  NUMBER  N
     * feedbackAdjustDate  反馈调整日期  DATE  N
     * feedbackAdjustQty  反馈调整数量  NUMBER  N
     * feedbackStatus  反馈状态  VARCHAR2  N
     * feedbackResult  反馈结果  VARCHAR2  N
     * rejectReason  供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * lineId  送货通知单行ID  NUMBER  Y
     * poNoticeId  送货通知单ID  NUMBER  Y
     * poHeaderId  采购订单ID  NUMBER  N
     * poLineId  采购订单行ID  NUMBER  N
     * noticeDeliveryDate  通知送货日期  DATE  N
     * noticeDeliveryQty  通知送货数量  NUMBER  N
     * onWayQty  在途数量（已创建送货单数量）  NUMBER  N
     * lineComments  送货备注  VARCHAR2  N
     * lineStatus  行状态  VARCHAR2  N
     * originalDeliveryDate  原通知送货日期  DATE  N
     * originalDeliveryQty  原通知送货数量  NUMBER  N
     * feedbackAdjustDate  反馈调整日期  DATE  N
     * feedbackAdjustQty  反馈调整数量  NUMBER  N
     * feedbackStatus  反馈状态  VARCHAR2  N
     * feedbackResult  反馈结果  VARCHAR2  N
     * rejectReason  供应商拒绝原因  VARCHAR2  N
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

    JSONObject saveNoticeLineForSupplier(JSONObject params) throws Exception;

    /**
     * Description：保存送货通知信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
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

    JSONObject saveSrmPoNotice(JSONObject params) throws Exception;

    /**
     * Description：保存送货通知信息(采购订单创建送货通知单)
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
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

    JSONObject saveSrmOrderToPoNotice(JSONObject params) throws Exception;

    /**
     * Description：查询送货通知行信息
     * @param jsonParams 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	Pagination<SrmPoNoticeLineEntity_HI_RO> findNoticeLineInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
	

    /**
     * Description：根据id查询送货通知表
     * @param poNoticeId 送货通知ID
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	List<SrmPoNoticeEntity_HI_RO> findSrmPoNoticeInfo(Integer poNoticeId) throws Exception;
	

    /**
     * Description：删除送货通知行
     * @param params 删除参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject deleteNoticeLineInfo(JSONObject params) throws Exception;


    /**
     * Description：送货通知接口（数据输入,用于外部访问的接口）需要提供用户和密码
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeId  送货通知ID  NUMBER  Y
     * poNoticeCode  送货通知编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * supplierId  供应商ID  NUMBER  Y
     * supplierSiteId  供应商地点ID  NUMBER  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * poNoticeStatus  送货通知状态  VARCHAR2  N
     * approvedDate  批准时间  DATE  N
     * issuedDate  下达时间  DATE  N
     * comments  说明  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * poNoticeVersions  送货通知版本  NUMBER  N
     * instId  （废弃）分厂(货主组织)  NUMBER  N
     * deliverySiteId  （废弃）送货地点id(saaf_institution inst_id)  NUMBER  N
     * itemId  （废弃）物料id  NUMBER  N
     * poStarvingId  （废弃）工单缺料id  NUMBER  N
     * demandDate  （废弃）需求日期  DATE  N
     * quantity  （废弃）需求数量  NUMBER  N
     * employeeNum  （废弃）采购员编码  VARCHAR2  N
     * status  （废弃）送货通知单状态(PO_NOTICE_STATUS)  VARCHAR2  N
     * specialUseNum  （废弃）番号  VARCHAR2  N
     * demandClassify  （废弃）需求分类  VARCHAR2  N
     * deliveryOrderQty  （废弃）已创建送货单数量  NUMBER  N
     * deliveryQty  （废弃）已收货数量  NUMBER  N
     * affirmStatus  （废弃）供应商确认状态(PO_AFFIRM_STATUS)  VARCHAR2  N
     * documentType  （废弃）单据类型(PO_DOC_TYPE)  VARCHAR2  N
     * rejectReason  （废弃）供应商拒绝原因  VARCHAR2  N
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

    JSONObject saveNoticeInfoForExternal(JSONObject jsonParams, Integer userId);

    /**
     * Description：送货通知接口（数据输出,用于外部访问的接口）
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    Map<String, Object> findNoticeInfoForExternal(JSONObject jsonParams);

    /**
     * Description：删除拟定的送货通知单
     * @param params 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject deleteNoticeInfo(JSONObject params) throws Exception;

}
