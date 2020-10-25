package saaf.common.fmw.po.model.inter;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLinesEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRequisitionHeadersEntity_HI_RO;

import java.util.List;
/**
 * Project Name：ISrmPoRequisitionHeaders
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
public interface ISrmPoRequisitionHeaders {

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
	Pagination<SrmPoRequisitionHeadersEntity_HI_RO> findPendingList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;


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
	Pagination<SrmPoRequisitionHeadersEntity_HI_RO> findHandledList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;
	

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
	Pagination<SrmPoRequisitionHeadersEntity_HI_RO> findAgentLov(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;
	

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
	JSONObject updateRequisitionLines(JSONObject params) throws Exception;

    /**
     * Description：查询采购申请
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
    public Pagination<SrmPoRequisitionHeadersEntity_HI_RO> findRequisition(JSONObject jsonParams, Integer pageIndex, Integer pageRows)throws Exception;

    /**
     * Description：保存采购申请
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * requisitionHeaderId  采购申请ID  NUMBER  Y
     * requisitionNumber  采购申请编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * departmentId  部门ID  NUMBER  N
     * requisitionType  申请类型  VARCHAR2  N
     * requisitionEmpId  申请人ID  NUMBER  N
     * requisitionDate  申请日期  DATE  N
     * requisitionStatus  申请状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvalDate  批准时间  DATE  N
     * sourceNumber  来源单号  VARCHAR2  N
     * comments  说明  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * supplierId  供应商ID  NUMBER  N
     * orderType  订单类型  VARCHAR2  N
     * erpHeaderId  ERP订单头ID  NUMBER  N
     * supplierName  供应商名称  VARCHAR2  N
     * currencyCode  币种  VARCHAR2  N
     * supplierSiteId  供应商地点ID  NUMBER  N
     * requisitionHeaderId  采购申请ID  NUMBER  Y
     * requisitionNumber  采购申请编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * departmentId  部门ID  NUMBER  N
     * requisitionType  申请类型  VARCHAR2  N
     * requisitionEmpId  申请人ID  NUMBER  N
     * requisitionDate  申请日期  DATE  N
     * requisitionStatus  申请状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvalDate  批准时间  DATE  N
     * sourceNumber  来源单号  VARCHAR2  N
     * comments  说明  VARCHAR2  N
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

    JSONObject updateRequisition(JSONObject jsonParams, int operatorUserId) throws Exception ;

    /**
     * Description：修改采购申请状态
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * requisitionHeaderId  采购申请ID  NUMBER  Y
     * requisitionNumber  采购申请编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * departmentId  部门ID  NUMBER  N
     * requisitionType  申请类型  VARCHAR2  N
     * requisitionEmpId  申请人ID  NUMBER  N
     * requisitionDate  申请日期  DATE  N
     * requisitionStatus  申请状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvalDate  批准时间  DATE  N
     * sourceNumber  来源单号  VARCHAR2  N
     * comments  说明  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * supplierId  供应商ID  NUMBER  N
     * orderType  订单类型  VARCHAR2  N
     * erpHeaderId  ERP订单头ID  NUMBER  N
     * supplierName  供应商名称  VARCHAR2  N
     * currencyCode  币种  VARCHAR2  N
     * supplierSiteId  供应商地点ID  NUMBER  N
     * requisitionHeaderId  采购申请ID  NUMBER  Y
     * requisitionNumber  采购申请编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * departmentId  部门ID  NUMBER  N
     * requisitionType  申请类型  VARCHAR2  N
     * requisitionEmpId  申请人ID  NUMBER  N
     * requisitionDate  申请日期  DATE  N
     * requisitionStatus  申请状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvalDate  批准时间  DATE  N
     * sourceNumber  来源单号  VARCHAR2  N
     * comments  说明  VARCHAR2  N
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

    JSONObject updateStatusRequisition(JSONObject jsonParam)throws Exception;

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
    JSONObject deleteRequisitionHeaders(JSONObject params) throws Exception;

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
    JSONObject deleteRequisitionLines(JSONObject params) throws Exception ;

    /**
     * Description：采购申请导入
     * @param jsonParams 导入参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject saveImportRequisition(JSONObject jsonParams, Integer userId)throws Exception;

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
	List<SrmPoLinesEntity_HI_RO> findPoHeaders(JSONObject params) throws Exception;

    /**
     * Description：控制活动类型选项
     * @param paramJSON 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	List<SaafLookupValuesEntity_HI_RO> selectDeliveryType(JSONObject paramJSON) throws Exception;

    /**
     * Description：提交，单框架协议
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * requisitionHeaderId  采购申请ID  NUMBER  Y
     * requisitionNumber  采购申请编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * departmentId  部门ID  NUMBER  N
     * requisitionType  申请类型  VARCHAR2  N
     * requisitionEmpId  申请人ID  NUMBER  N
     * requisitionDate  申请日期  DATE  N
     * requisitionStatus  申请状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvalDate  批准时间  DATE  N
     * sourceNumber  来源单号  VARCHAR2  N
     * comments  说明  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * supplierId  供应商ID  NUMBER  N
     * orderType  订单类型  VARCHAR2  N
     * erpHeaderId  ERP订单头ID  NUMBER  N
     * supplierName  供应商名称  VARCHAR2  N
     * currencyCode  币种  VARCHAR2  N
     * supplierSiteId  供应商地点ID  NUMBER  N
     * requisitionHeaderId  采购申请ID  NUMBER  Y
     * requisitionNumber  采购申请编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * departmentId  部门ID  NUMBER  N
     * requisitionType  申请类型  VARCHAR2  N
     * requisitionEmpId  申请人ID  NUMBER  N
     * requisitionDate  申请日期  DATE  N
     * requisitionStatus  申请状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvalDate  批准时间  DATE  N
     * sourceNumber  来源单号  VARCHAR2  N
     * comments  说明  VARCHAR2  N
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

    JSONObject saveOrderInfoByDemandSumList(JSONObject jsonParam) throws Exception;

    /**
     * Description：提交，多框架协议，订单处理页面
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * requisitionHeaderId  采购申请ID  NUMBER  Y
     * requisitionNumber  采购申请编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * departmentId  部门ID  NUMBER  N
     * requisitionType  申请类型  VARCHAR2  N
     * requisitionEmpId  申请人ID  NUMBER  N
     * requisitionDate  申请日期  DATE  N
     * requisitionStatus  申请状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvalDate  批准时间  DATE  N
     * sourceNumber  来源单号  VARCHAR2  N
     * comments  说明  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * supplierId  供应商ID  NUMBER  N
     * orderType  订单类型  VARCHAR2  N
     * erpHeaderId  ERP订单头ID  NUMBER  N
     * supplierName  供应商名称  VARCHAR2  N
     * currencyCode  币种  VARCHAR2  N
     * supplierSiteId  供应商地点ID  NUMBER  N
     * requisitionHeaderId  采购申请ID  NUMBER  Y
     * requisitionNumber  采购申请编号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * departmentId  部门ID  NUMBER  N
     * requisitionType  申请类型  VARCHAR2  N
     * requisitionEmpId  申请人ID  NUMBER  N
     * requisitionDate  申请日期  DATE  N
     * requisitionStatus  申请状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvalDate  批准时间  DATE  N
     * sourceNumber  来源单号  VARCHAR2  N
     * comments  说明  VARCHAR2  N
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
	Pagination<SrmPoRequisitionHeadersEntity_HI_RO> findOrderDealList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：保存采购申请分配表数据
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * reqDistributionId  申请分配ID  NUMBER  Y
     * requisitionLineId  采购申请行ID  NUMBER  Y
     * distributionProportion  分配比例(%)  NUMBER  Y
     * poLineId  采购协议行ID  NUMBER  Y
     * poLineId  采购协议行ID  NUMBER  Y
     * reqDistributionId  申请分配ID  NUMBER  Y
     * requisitionLineId  采购申请行ID  NUMBER  Y
     * distributionProportion  分配比例(%)  NUMBER  Y
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    JSONObject savePoReqDistributions(JSONObject paramJSON) throws Exception;

    /**
     * Description：查询详细信息
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	List<SrmPoRequisitionHeadersEntity_HI_RO> queryRequisitionInfo(JSONObject jsonParams);

    /**
     * Description：多框架协议申请，转订单
     * @param jsonParam 多框架参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	JSONObject saveOrderByMultipleAgr(JSONObject jsonParam) throws Exception;


    /**
     * Description：采购申请按选择的行转换成采购订单
     * @param jsonParam 采购申请参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject saveReqTransferOrderByLines(JSONObject jsonParam) throws Exception;


    /**
     * Description：采购申请查询页按选择的行转换成采购订单
     * @param jsonParam 采购申请参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-01     SIE 谢晓霞       创建
     * =======================================================================
     */
    JSONObject saveReqTransferOrder(JSONObject jsonParam) throws Exception;
	
}
