
package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.base.utils.StringUtils;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.intf.model.inter.server.SrmUserInstSqlUtil;
import saaf.common.fmw.message.email.utils.SendMailUtil;
import saaf.common.fmw.po.model.entities.*;
import saaf.common.fmw.po.model.entities.readonly.*;
import saaf.common.fmw.po.model.inter.ISrmPoFrameworkAgreement;
import saaf.common.fmw.po.model.inter.ISrmPoRequisitionHeaders;
import saaf.common.fmw.utils.SrmUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.Global.undefined;
import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

/**
 * Project Name：SrmPoRequisitionHeadersServer
 * Company Name：SIE
 * Program Name：
 * Description：采购申请头
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoRequisitionHeadersServer")
public class SrmPoRequisitionHeadersServer implements ISrmPoRequisitionHeaders {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRequisitionHeadersServer.class);
    @Autowired
    private SaafSequencesUtil saafSequencesUtil;
    @Autowired
    private ViewObject<SrmPoRequisitionHeadersEntity_HI> srmPoRequisitionHeadersDAO_HI;
    @Autowired
    private ViewObject<SrmPoRequisitionLinesEntity_HI> srmPoRequisitionLinesDAO_HI;
    @Autowired
    private BaseViewObject<SrmPoRequisitionHeadersEntity_HI_RO> srmPoRequisitionHeadersDAO_HI_RO;
    @Autowired
    private BaseViewObject<SrmPoForecastLinesEntity_HI_RO> srmPoForecastLinesDAO_HI_RO;
    @Autowired
    private BaseViewObject<SrmBaseCategoriesEntity_HI_RO> srmBaseCategoriesDAO_HI_RO;
    @Autowired
    private BaseViewObject<SaafLookupValuesEntity_HI_RO> saafLookupValuesDAO_HI_RO;
    @Autowired
    private ViewObject<SaafEmployeesEntity_HI> saafEmployeesDAO_HI;
    @Autowired
    private ViewObject<SrmPoHeadersEntity_HI> srmPoHeadersDAO_HI;
    @Autowired
    private ViewObject<SrmPoLinesEntity_HI> srmPoLinesDAO_HI;
    @Autowired
    private BaseViewObject<SrmPoLinesEntity_HI_RO> srmPoLinesDAO_HI_RO;
    @Autowired
    private BaseViewObject<SaafLookupValuesEntity_HI_RO> lookupValuesEntityDAO_HI_RO;
    @Autowired
    private ViewObject<SrmPoReqDistributionsEntity_HI> srmPoReqDistributionsDAO_HI;
    @Autowired
    private SrmUserInstSqlUtil srmUserInstSqlUtil;
    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;
    @Autowired
    private ISrmPoFrameworkAgreement iSrmPoFrameworkAgreement;

    private static SendMailUtil sendMailUtil = new SendMailUtil(true);

    public SrmPoRequisitionHeadersServer() {
        super();
    }

    /**
     * Description：查询待处理列表
     *
     * @param params    查询条件参数
     * @param pageIndex 页码
     * @param pageRows  页行数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoRequisitionHeadersEntity_HI_RO> findPendingList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer queryString = new StringBuffer(SrmPoRequisitionHeadersEntity_HI_RO.QUERY_PENDING_SQL);
        Map<String, Object> map = new HashMap();
        //采购员
        SaafToolUtils.parperParam(params, "ds.employee_name", "employeeName", queryString, map, "LIKE");
        //业务实体
        SaafToolUtils.parperParam(params, "ds.org_id", "orgId", queryString, map, "=");
        //申请部门
        SaafToolUtils.parperParam(params, "ds.department_id", "departmentId", queryString, map, "=");
        //需求类型
        SaafToolUtils.parperParam(params, "ds.requisition_type", "requisitionType", queryString, map, "=");
        //库存组织
        SaafToolUtils.parperParam(params, "ds.organization_id", "receiveToOrganizationId", queryString, map, "=");
        //申请人
        SaafToolUtils.parperParam(params, "ds.requisition_emp_id", "applyId", queryString, map, "=");
        //单据日期从
        String creationDateFrom = params.getString("creationDateFrom");
        if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
            queryString.append(" AND trunc(ds.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
            map.put("creationDateFrom", creationDateFrom);
        }
        //单据日期至
        String creationDateTo = params.getString("creationDateTo");
        if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
            queryString.append(" AND trunc(ds.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
            map.put("creationDateTo", creationDateTo);
        }
        //单据状态
        SaafToolUtils.parperParam(params, "ds.requisition_status", "requisitionStatus", queryString, map, "=");
        //来源单号
        SaafToolUtils.parperParam(params, "ds.source_number", "sourceNumber", queryString, map, "LIKE");
        //物料名称
        SaafToolUtils.parperParam(params, "ds.item_id", "itemId", queryString, map, "=");
        //分类名称
        SaafToolUtils.parperParam(params, "ds.category_id", "categoryId", queryString, map, "=");
        //申请单号
        SaafToolUtils.parperParam(params, "ds.requisition_number", "requisitionNumber", queryString, map, "LIKE");
        //申请头备注
        SaafToolUtils.parperParam(params, "ds.comments", "comments", queryString, map, "LIKE");
        //是否有协议
        Integer deliveryType = params.getInteger("deliveryType");
        System.out.println("deliveryType：" + deliveryType);
        if (deliveryType != null && deliveryType == 1) {
            queryString.append(" AND ds.agreement_count = 0");
        } else if (deliveryType != null && deliveryType == 2) {
            queryString.append(" AND ds.agreement_count = 1");
        } else if (deliveryType != null && deliveryType == 3) {
            queryString.append(" AND ds.agreement_count > 1");
        }
        //只能查看当前账号已分配的业务实体相关的数据
        //srmUserInstSqlUtil.concatUserInstSql(params.getInteger("varUserId"), queryString, map, "prh");
        //queryString.append(" AND srm_sys_check_access_f(" + params.getInteger("varUserId") + ", prh.org_id, prl.category_id, null, null, null, null, null) = 'Y' ");
        map.put("userId", params.getInteger("varUserId"));
        //总行数
        StringBuffer countSb = new StringBuffer("select count(1) from (" + queryString + ")");
        // 排序
        queryString.append(" ORDER BY ds.creation_date DESC");
        Pagination<SrmPoRequisitionHeadersEntity_HI_RO> requisitionList = srmPoRequisitionHeadersDAO_HI_RO.findPagination(queryString, countSb, map, pageIndex, pageRows);
        return requisitionList;
    }


    /**
     * Description：查询已处理列表
     *
     * @param params    查询条件参数
     * @param pageIndex 页码
     * @param pageRows  页行数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoRequisitionHeadersEntity_HI_RO> findHandledList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer queryString = new StringBuffer(SrmPoRequisitionHeadersEntity_HI_RO.QUERY_HANDLED_SQL);
        Map<String, Object> map = new HashMap();
        //采购员
        SaafToolUtils.parperParam(params, "se2.employee_name", "employeeName", queryString, map, "like");
        /*SaafToolUtils.parperParam(params, "prl.buyer_id", "employeeId", queryString, map, "=");*/
        //业务实体
        SaafToolUtils.parperParam(params, "prh.org_id", "instId", queryString, map, "=");
        //申请部门
        SaafToolUtils.parperParam(params, "prh.department_id", "departmentId", queryString, map, "=");
        //需求类型
        SaafToolUtils.parperParam(params, "prh.requisition_type", "requisitionType", queryString, map, "=");
        //库存组织
        SaafToolUtils.parperParam(params, "prh.organization_id", "organizationId", queryString, map, "=");
        //申请人
        SaafToolUtils.parperParam(params, "prh.requisition_emp_id", "applyId", queryString, map, "=");
        //单据日期从
        SaafToolUtils.parperParam(params, "date_format(prh.creation_date,'%Y-%m-%d')", "creationDateFrom", queryString, map, ">=");
        //单据日期至
        SaafToolUtils.parperParam(params, "date_format(prh.creation_date,'%Y-%m-%d')", "creationDateTo", queryString, map, "<=");
        //单据状态
        SaafToolUtils.parperParam(params, "prh.requisition_status", "requisitionStatus", queryString, map, "=");
        //来源单号
        SaafToolUtils.parperParam(params, "prh.source_number", "sourceNumber", queryString, map, "like");
        //物料名称
        SaafToolUtils.parperParam(params, "prl.item_id", "itemId", queryString, map, "=");
        //分类名称
        SaafToolUtils.parperParam(params, "prl.category_id", "categoryId", queryString, map, "=");
        //申请单号
        SaafToolUtils.parperParam(params, "prh.requisition_number", "requisitionNumber", queryString, map, "like");
        //申请头备注
        SaafToolUtils.parperParam(params, "prh.comments", "comments", queryString, map, "like");
        //是否有协议
        Integer deliveryType = params.getInteger("deliveryType");
        if (null != deliveryType && deliveryType == 1) {//无协议
            queryString.append(" AND\n" +
                    "(SELECT\n" +
                    "  COUNT(*)\n" +
                    "FROM\n" +
                    "  srm_po_headers ph\n" +
                    "  LEFT JOIN srm_po_lines pl ON ph.po_header_id = pl.po_header_id\n" +
                    "  LEFT JOIN srm_pos_supplier_info spsi ON ph.supplier_id = spsi.supplier_id\n" +
                    "WHERE ph.po_doc_type = 'AGREEMENT'\n" +
                    "  AND ph.status = 'APPROVED'\n" +
                    "  AND pl.status = 'OPEN'\n" +
                    "  AND ((ph.start_date < prl.demand_date AND ph.end_date IS NULL) OR (ph.start_date < prl.demand_date AND prl.demand_date < ph.end_date AND ph.end_date IS NOT NULL))\n" +
                    "  AND prl.item_id = pl.item_id)=0\r\n");
        } else if (null != deliveryType && deliveryType == 2) {//单协议
            queryString.append(" AND\n" +
                    "(SELECT\n" +
                    "  COUNT(*)\n" +
                    "FROM\n" +
                    "  srm_po_headers ph\n" +
                    "  LEFT JOIN srm_po_lines pl ON ph.po_header_id = pl.po_header_id\n" +
                    "  LEFT JOIN srm_pos_supplier_info spsi ON ph.supplier_id = spsi.supplier_id\n" +
                    "WHERE ph.po_doc_type = 'AGREEMENT'\n" +
                    "  AND ph.status = 'APPROVED'\n" +
                    "  AND pl.status = 'OPEN'\n" +
                    "  AND ((ph.start_date < prl.demand_date AND ph.end_date IS NULL) OR (ph.start_date < prl.demand_date AND prl.demand_date < ph.end_date AND ph.end_date IS NOT NULL))\n" +
                    "  AND prl.item_id = pl.item_id)=1");
        } else if (null != deliveryType && deliveryType == 3) {//多协议
            queryString.append(" AND\n" +
                    "(SELECT\n" +
                    "  COUNT(*)\n" +
                    "FROM\n" +
                    "  srm_po_headers ph\n" +
                    "  LEFT JOIN srm_po_lines pl ON ph.po_header_id = pl.po_header_id\n" +
                    "  LEFT JOIN srm_pos_supplier_info spsi ON ph.supplier_id = spsi.supplier_id\n" +
                    "WHERE ph.po_doc_type = 'AGREEMENT'\n" +
                    "  AND ph.status = 'APPROVED'\n" +
                    "  AND pl.status = 'OPEN'\n" +
                    "  AND ((ph.start_date < prl.demand_date AND ph.end_date IS NULL) OR (ph.start_date < prl.demand_date AND prl.demand_date < ph.end_date AND ph.end_date IS NOT NULL))\n" +
                    "  AND prl.item_id = pl.item_id) > 1");
        }
        //只能查看当前账号已分配的业务实体相关的数据
        //srmUserInstSqlUtil.concatUserInstSql(params.getInteger("varUserId"), queryString, map, "prh");
        queryString.append(" AND check_org_f(" + params.getInteger("varUserId") + ", prh.org_id) = 'Y' ");
        // 排序
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" ORDER BY prh.creation_date DESC");
        Pagination<SrmPoRequisitionHeadersEntity_HI_RO> requisitionList = srmPoRequisitionHeadersDAO_HI_RO.findPagination(queryString, countSql, map, pageIndex, pageRows);
        //取data
        JSONArray data = JSON.parseObject(JSONArray.toJSONString(requisitionList)).getJSONArray("data");
        String message = null;
        String str = null;
        if (null != data && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                JSONObject obj = data.getJSONObject(i);
                String itemId = data.getJSONObject(i).getString("itemId");
                Integer orgId = data.getJSONObject(i).getInteger("instId");
                //采购申请需求日期
                Date demandDate = data.getJSONObject(i).getDate("demandDate");
                Map<String, Object> queryParamMap = new HashMap();
                StringBuffer buffer = new StringBuffer();
                buffer.append(SrmPoRequisitionHeadersEntity_HI_RO.QUERY_FRAMEWORK_AGREEMEN_SQL);
                queryParamMap.put("itemId", itemId);
                queryParamMap.put("orgId", orgId);
                List<SrmPoRequisitionHeadersEntity_HI_RO> list = srmPoRequisitionHeadersDAO_HI_RO.findList(buffer, queryParamMap);
                if (list.size() == 0) {
                    message = "无协议";
                } else if (list.size() == 1) {
                    message = "单框架协议";
                } else if (list.size() > 1) {
                    message = "多框架协议";
                }
                obj.put("isFrameworkAgreemen", message);
                if (null != list && list.size() > 0) {
                    int effectCount = 0;
                    for (SrmPoRequisitionHeadersEntity_HI_RO ls : list) {
                        Date nowDate = new Date();
                        Date phStartDate = ls.getPhStartDate();
                        Date phEndDate = ls.getPhEndDate();
                        Date plStartDate = ls.getPlStartDate();
                        Date plEndDate = ls.getPlEndDate();
                        //根据采购申请行需求日期demandDate判断协议价格是否有效
                        if (null != phStartDate && null == phEndDate && phStartDate.compareTo(demandDate) <= 0) {//有效的协议头
                            if (null != plStartDate && null == plEndDate && plStartDate.compareTo(demandDate) <= 0) {
                                str = "有效";
                            } else if (null != plStartDate && null != plEndDate && plStartDate.compareTo(demandDate) <= 0 && plEndDate.compareTo(demandDate) >= 0) {
                                str = "有效";
                            } else {
                                str = "无效";
                            }
                        } else if (null != phStartDate && null != phEndDate && phStartDate.compareTo(demandDate) <= 0 && phEndDate.compareTo(demandDate) >= 0) {//有效的协议头
                            if (null != plStartDate && null == plEndDate && plStartDate.compareTo(demandDate) <= 0) {
                                str = "有效";
                            } else if (null != plStartDate && null != plEndDate && plStartDate.compareTo(demandDate) <= 0 && plEndDate.compareTo(demandDate) >= 0) {
                                str = "有效";
                            } else {
                                str = "无效";
                            }
                            //没有根据今天的日期进行判断是否有效
                            //新增判断协议头有效日期与今天日期进行判断------add by hzz
                            if (phEndDate.compareTo(nowDate) <= 0) {
                                str = "无效";
                            }
                        } else {
                            str = "无效";
                        }
                        ls.setIsPriceEffective(str);
                        //有效的协议计数------add by hzz
                        if ("有效".equals(str)) {
                            effectCount++;
                        }
                    }
                    //若框架协议中的协议都为无效,那么应该算无框架协议还是?
                    //根据有效协议计数来判断框架协议------add by hzz
                    switch (effectCount) {
                        case 0:
                            obj.put("isFrameworkAgreemen", "无协议");
                            break;
                        case 1:
                            obj.put("isFrameworkAgreemen", "单框架协议");
                            break;
                        default:
                            obj.put("isFrameworkAgreemen", "多框架协议");
                            break;
                    }
                    obj.put("frameworkAgreemenData", list);
                }
            }
            List<SrmPoRequisitionHeadersEntity_HI_RO> dataNew = data.toJavaList(SrmPoRequisitionHeadersEntity_HI_RO.class);
            requisitionList.setData(dataNew);
        }
        return requisitionList;
    }


    /**
     * Description：查询采购员Lov
     *
     * @param params    查询条件参数
     * @param pageIndex 页码
     * @param pageRows  页行数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoRequisitionHeadersEntity_HI_RO> findAgentLov(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPoRequisitionHeadersEntity_HI_RO.QUERY_AGENT_LOV);
        SaafToolUtils.parperParam(params, "se.employee_id", "employeeId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(params, "se.employee_name", "employeeName", sb, queryParamMap, "like");
        SaafToolUtils.parperParam(params, "se.employee_number", "employeeNumber", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(params, "se.po_flag", "poFlag", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(params, "se.enabled_flag", "enabledFlag", sb, queryParamMap, "=");
        String countSql = "select count(1) from (" + sb + ")";
        Pagination<SrmPoRequisitionHeadersEntity_HI_RO> result = srmPoRequisitionHeadersDAO_HI_RO.findPagination(sb.toString(), countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：转交采购员
     *
     * @param params 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updateRequisitionLines(JSONObject params) throws Exception {
        JSONArray ids = params.getJSONArray("data");
        Integer buyerId = params.getInteger("employeeId");
        Integer varUserId = params.getInteger("varUserId");
        if (null == ids || ids.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无操作的数据！", 0, null);
        }
        Integer requisitionLineId = null;
        SrmPoRequisitionLinesEntity_HI entity = null;
        List<SrmPoRequisitionLinesEntity_HI> list = new ArrayList<SrmPoRequisitionLinesEntity_HI>();
        for (int i = 0, j = ids.size(); i < j; i++) {
            requisitionLineId = ids.getInteger(i);
            entity = srmPoRequisitionLinesDAO_HI.getById(requisitionLineId);
            if (entity != null) {
                entity.setBuyerId(buyerId);
                entity.setOperatorUserId(varUserId);
                list.add(entity);
            }
        }
        if (null == list || list.isEmpty()) {
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "暂无数据", 0, null);
        }
        srmPoRequisitionLinesDAO_HI.update(list);
        return SToolUtils.convertResultJSONObj("S", "操作成功", list.size(), null);
    }


    /**
     * Description：控制活动类型选项
     *
     * @param paramJSON 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SaafLookupValuesEntity_HI_RO> selectDeliveryType(JSONObject paramJSON) throws Exception {
        String rowId = paramJSON.getString("deliveryType");
        StringBuffer queryString = new StringBuffer();
        Map<String, Object> queryParamMap = new HashMap();
        queryString.append(SaafLookupValuesEntity_HI_RO.QUERY_DELIVERY_TYPE_SQL);
        if ("1".equals(rowId)) {//无协议
            queryString.append(" AND slv.tag in (1,2)");
        } else if ("2".equals(rowId)) {//单框架协议
            queryString.append(" AND slv.tag in (1,3)");
        } else if ("3".equals(rowId)) {//多框架协议
            queryString.append(" AND slv.tag in (1,4)");
        } else {
            queryString.append(" AND slv.tag in (0)");
        }
        queryString.append(" AND slv.enabled_flag = 'Y'");
        List<SaafLookupValuesEntity_HI_RO> list = lookupValuesEntityDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        return list;
    }


    /**
     * Description：提交，单框架协议
     * <p>
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
     * <p>
     * Update History
     * =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveOrderInfoByDemandSumList(JSONObject jsonParam) throws Exception {
        JSONArray datas = jsonParam.getJSONArray("data");
        Integer varUserId = jsonParam.getInteger("varUserId");
        if (null == datas || datas.size() == 0) {
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "暂无数据", 0, null);
        }
        List<Integer> poHeaderIds = new ArrayList();
        SrmPoRequisitionHeadersEntity_HI srmPoRequisitionHeadersEntity = null;
        SrmPoHeadersEntity_HI srmPoHeadersEntity_HI = null;
        SrmPoRequisitionLinesEntity_HI srmPoRequisitionLines = null;
        SrmPoLinesEntity_HI srmPoLinesEntity_HI = null;
        SrmPoRequisitionHeadersEntity_HI_RO requisitionHeadersEntity = null;
        for (int i = 0; i < datas.size(); i++) {
            JSONObject object = datas.getJSONObject(i);
            if (object.getInteger("requisitionHeaderId") != null && object.getInteger("requisitionHeaderId") > 0
                    && object.getInteger("requisitionLineId") != null && object.getInteger("requisitionLineId") > 0) {
                srmPoRequisitionHeadersEntity = srmPoRequisitionHeadersDAO_HI.getById(object.getInteger("requisitionHeaderId"));
                if (srmPoRequisitionHeadersEntity.getRequisitionStatus() != null && "APPROVED".equals(srmPoRequisitionHeadersEntity.getRequisitionStatus())) {
                    srmPoRequisitionLines = srmPoRequisitionLinesDAO_HI.getById(object.getInteger("requisitionLineId"));

                    //获取协议信息
                    requisitionHeadersEntity = getPriceToSingleAgreement(srmPoRequisitionHeadersEntity.getOrgId(), srmPoRequisitionLines.getItemId(), srmPoRequisitionLines.getDemandQty());
                    if (requisitionHeadersEntity == null) {
                        return SToolUtils.convertResultJSONObj("E", "没有获取到有效的协议价格，提交失败！", 0, null);
                    }

                    //根据申请头生成订单头
                    srmPoHeadersEntity_HI = new SrmPoHeadersEntity_HI();
                    Date date = new Date();
                    String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
                    String poNumber = saafSequencesUtil.getDocSequences("srm_po_headers", "PO-", dateFromate, 4);//订单编号
                    srmPoHeadersEntity_HI.setPoNumber(poNumber);
                    srmPoHeadersEntity_HI.setSupplierId(requisitionHeadersEntity.getSupplierId());
                    srmPoHeadersEntity_HI.setPoVersions(new BigDecimal("0"));
                    srmPoHeadersEntity_HI.setOrgId(srmPoRequisitionHeadersEntity.getOrgId());
                    srmPoHeadersEntity_HI.setPoDocType("ORDER");
                    srmPoHeadersEntity_HI.setStatus("DRAFT");
                    srmPoHeadersEntity_HI.setCurrencyCode(requisitionHeadersEntity.getCurrencyCode());
                    srmPoHeadersEntity_HI.setBuyerId(object.getInteger("buyerId") == null ? requisitionHeadersEntity.getBuyerId() : object.getInteger("buyerId"));
                    srmPoHeadersEntity_HI.setTaxRateCode(requisitionHeadersEntity.getTaxRateCode());
                    srmPoHeadersEntity_HI.setReturnGoodsType(requisitionHeadersEntity.getReturnGoodsType());
                    srmPoHeadersEntity_HI.setPaymentCondition(requisitionHeadersEntity.getPaymentCondition());
                    srmPoHeadersEntity_HI.setSettlementWay(requisitionHeadersEntity.getSettlementWay());
                    srmPoHeadersEntity_HI.setBillToOrganizationId(requisitionHeadersEntity.getBillToOrganizationId());
                    srmPoHeadersEntity_HI.setDescription(srmPoRequisitionHeadersEntity.getComments());
                    srmPoHeadersEntity_HI.setSourceCode(String.valueOf(srmPoRequisitionHeadersEntity.getRequisitionNumber()));
                    srmPoHeadersEntity_HI.setSourceId(String.valueOf(srmPoRequisitionHeadersEntity.getRequisitionHeaderId()));
                    srmPoHeadersEntity_HI.setOperatorUserId(varUserId);
                    srmPoHeadersDAO_HI.save(srmPoHeadersEntity_HI);
                    //处理申请行信息转订单行

                    //订单行信息与申请行信息绑定数据:根据申请行信息生成订单行信息
                    //因为需求汇总转订单只是勾选申请行信息直接转,所以不需要list
                    srmPoLinesEntity_HI = new SrmPoLinesEntity_HI();
                    srmPoLinesEntity_HI.setPoHeaderId(srmPoHeadersEntity_HI.getPoHeaderId());
                    srmPoLinesEntity_HI.setLineNumber(1);
                    srmPoLinesEntity_HI.setItemId(srmPoRequisitionLines.getItemId());
                    srmPoLinesEntity_HI.setItemName(srmPoRequisitionLines.getItemName());
                    srmPoLinesEntity_HI.setItemSpec(srmPoRequisitionLines.getItemSpec());
                    srmPoLinesEntity_HI.setCategoryId(srmPoRequisitionLines.getCategoryId());
                    srmPoLinesEntity_HI.setStatus("OPEN");
                    srmPoLinesEntity_HI.setDemandQty(srmPoRequisitionLines.getDemandQty());
                    srmPoLinesEntity_HI.setDemandDate(srmPoRequisitionLines.getDemandDate());
                    srmPoLinesEntity_HI.setMinPoQty(srmPoRequisitionLines.getMinPacking());
                    srmPoLinesEntity_HI.setDescription(srmPoRequisitionLines.getLineComments());
                    srmPoLinesEntity_HI.setOnWayQty(new BigDecimal("0"));
                    srmPoLinesEntity_HI.setReceivedQty(new BigDecimal("0"));
                    srmPoLinesEntity_HI.setTaxPrice(requisitionHeadersEntity.getTaxPrice());
                    srmPoLinesEntity_HI.setNonTaxPrice(requisitionHeadersEntity.getNonTaxPrice());
                    srmPoLinesEntity_HI.setFeedbackStatus("NON_FEEDBACK");
                    srmPoLinesEntity_HI.setFeedbackResult("CONFIRM");
                    srmPoLinesEntity_HI.setRejectReason(null);
                    srmPoLinesEntity_HI.setReceiveToOrganizationId(object.getInteger("receiveToOrganizationId"));
                    srmPoLinesEntity_HI.setMayNoticeQty((srmPoRequisitionLines.getDemandQty() == null ? new BigDecimal(0) : srmPoRequisitionLines.getDemandQty()).subtract(srmPoLinesEntity_HI.getOnWayQty()));
                    //srmPoLinesEntity_HI.setSourceCode("PR");
                    //申请行信息与订单行信息关联
                    srmPoLinesEntity_HI.setSourceId(String.valueOf(srmPoRequisitionLines.getRequisitionLineId()));
                    srmPoLinesEntity_HI.setSourceCode("PR");
                    srmPoLinesEntity_HI.setOperatorUserId(varUserId);
                    srmPoLinesDAO_HI.save(srmPoLinesEntity_HI);
                    //订单行保存后,记录申请行的处理方式
                    srmPoRequisitionLines.setHandleWay("3");
                    srmPoRequisitionLines.setOperatorUserId(varUserId);
                    srmPoRequisitionLinesDAO_HI.update(srmPoRequisitionLines);
                    poHeaderIds.add(srmPoHeadersEntity_HI.getPoHeaderId());
                }
            }
        }
        return SToolUtils.convertResultJSONObj("S", "提交成功！", datas.size(), poHeaderIds);
    }

    /**
     * Description：多框架协议申请，转订单
     *
     * @param jsonParam 多框架参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveOrderByMultipleAgr(JSONObject jsonParam) throws Exception {
        JSONArray datas = jsonParam.getJSONArray("data");
        Integer varUserId = jsonParam.getInteger("varUserId");
        if (null == datas || datas.size() == 0) {
            return SToolUtils.convertResultJSONObj("S", "无操作的数据！", 0, null);
        }
        List<Integer> poHeaderIds = new ArrayList();
        //将数据按【业务实体+供应商+币种+税率+付款方式+回货方式+采购员】进行分组
        JSONObject obj = null;
        Map<String, List<JSONObject>> map = new HashMap<>();
        StringBuffer sb = null;
        String mapKey = null;
        for (int i = 0; i < datas.size(); i++) {
            obj = datas.getJSONObject(i);
            sb = new StringBuffer().append(obj.getString("orgId")).append(obj.getString("supplierId")).append(obj.getString("currencyCode"))
                    .append(obj.getString("taxRateCode")).append(obj.getString("paymentCondition")).append(obj.getString("returnGoodsType"))
                    .append(obj.getString("buyerId"));
            mapKey = sb.toString();
            if (map.containsKey(mapKey)) {
                map.get(mapKey).add(obj);
            } else {
                List<JSONObject> list = new ArrayList<>();
                list.add(obj);
                map.put(mapKey, list);
            }
        }
        //生成订单
        List<JSONObject> listJSON = null;
        SrmPoHeadersEntity_HI srmPoHeadersEntity_HI = null;
        JSONObject objectH = null;
        String poNumber = null;
        Date date = new Date();
        String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
        SrmPoLinesEntity_HI srmPoLinesEntity_HI = null;
        Integer lineNumber = 0;
        BigDecimal demandQty = null;
        BigDecimal distributionProportion = null;
        SrmPoRequisitionLinesEntity_HI requisitionLinesEntity = null;
        for (Map.Entry<String, List<JSONObject>> m : map.entrySet()) {
            listJSON = m.getValue();
            objectH = listJSON.get(0);
            srmPoHeadersEntity_HI = new SrmPoHeadersEntity_HI();
            //订单编号
            poNumber = saafSequencesUtil.getDocSequences("srm_po_headers", "PO-", dateFromate, 4);
            srmPoHeadersEntity_HI.setPoNumber(poNumber);
            srmPoHeadersEntity_HI.setOrgId(objectH.getInteger("orgId"));
            srmPoHeadersEntity_HI.setPoDocType("ORDER");
            srmPoHeadersEntity_HI.setSupplierId(objectH.getInteger("supplierId"));
            srmPoHeadersEntity_HI.setSupplierSiteId(objectH.getInteger("supplierSiteId"));
            srmPoHeadersEntity_HI.setCurrencyCode(objectH.getString("currencyCode"));
            srmPoHeadersEntity_HI.setTaxRateCode(objectH.getString("taxRateCode"));
            srmPoHeadersEntity_HI.setBuyerId(objectH.getInteger("buyerId"));
            srmPoHeadersEntity_HI.setReturnGoodsType(objectH.getString("returnGoodsType"));
            srmPoHeadersEntity_HI.setPaymentCondition(objectH.getString("paymentCondition"));
            srmPoHeadersEntity_HI.setSettlementWay(objectH.getString("settlementWay"));
            srmPoHeadersEntity_HI.setPoVersions(new BigDecimal("0"));
            srmPoHeadersEntity_HI.setStatus("DRAFT");
            srmPoHeadersEntity_HI.setSourceCode("PR");
            srmPoHeadersEntity_HI.setOperatorUserId(varUserId);
            srmPoHeadersDAO_HI.save(srmPoHeadersEntity_HI);
            //创建订单行
            lineNumber = 1;
            for (JSONObject objectL : listJSON) {
                srmPoLinesEntity_HI = new SrmPoLinesEntity_HI();
                srmPoLinesEntity_HI.setPoHeaderId(srmPoHeadersEntity_HI.getPoHeaderId());
                srmPoLinesEntity_HI.setLineNumber(lineNumber);
                srmPoLinesEntity_HI.setItemId(objectL.getInteger("itemId"));
                srmPoLinesEntity_HI.setItemName(objectL.getString("itemName"));
                srmPoLinesEntity_HI.setItemSpec(objectL.getString("itemSpec"));
                srmPoLinesEntity_HI.setCategoryId(objectL.getInteger("categoryId"));
                //按分配比例计算订单的数量
                demandQty = objectL.getBigDecimal("demandQty");
                distributionProportion = objectL.getBigDecimal("distributionProportion");
                srmPoLinesEntity_HI.setDemandQty(demandQty.multiply(distributionProportion).divide(new BigDecimal(100)));
                srmPoLinesEntity_HI.setTaxPrice(objectL.getBigDecimal("taxPrice"));
                srmPoLinesEntity_HI.setNonTaxPrice(objectL.getBigDecimal("nonTaxPrice"));
                srmPoLinesEntity_HI.setDemandDate(objectL.getDate("demandDate"));
                srmPoLinesEntity_HI.setStatus("OPEN");
                srmPoLinesEntity_HI.setDescription(objectL.getString("lineComments"));
                srmPoLinesEntity_HI.setOnWayQty(new BigDecimal("0"));
                srmPoLinesEntity_HI.setReceivedQty(new BigDecimal("0"));
                srmPoLinesEntity_HI.setFeedbackStatus("NON_FEEDBACK");
                srmPoLinesEntity_HI.setFeedbackResult("CONFIRM");
                //申请行信息与订单行信息关联
                srmPoLinesEntity_HI.setSourceId(objectL.getString("requisitionLineId"));
                srmPoLinesEntity_HI.setSourceCode("PR");
                srmPoLinesEntity_HI.setOperatorUserId(varUserId);
                srmPoLinesDAO_HI.save(srmPoLinesEntity_HI);
                lineNumber++;
                //更新申请行的处理方式为转订单
                requisitionLinesEntity = srmPoRequisitionLinesDAO_HI.getById(objectL.getInteger("requisitionLineId"));
                requisitionLinesEntity.setHandleWay("3");
                requisitionLinesEntity.setOperatorUserId(varUserId);
                srmPoRequisitionLinesDAO_HI.update(requisitionLinesEntity);
            }
            poHeaderIds.add(srmPoHeadersEntity_HI.getPoHeaderId());
        }
        return SToolUtils.convertResultJSONObj("S", "转订单成功！", datas.size(), poHeaderIds);
    }

    /**
     * 单框架协议生成订单时，获取价格
     *
     * @param orgId
     * @param itemId
     * @param demandQty
     * @return
     */
    private SrmPoRequisitionHeadersEntity_HI_RO getPriceToSingleAgreement(Integer orgId, Integer itemId, BigDecimal demandQty) {
        StringBuffer queryString = new StringBuffer(SrmPoRequisitionHeadersEntity_HI_RO.QUERY_FRAMEWORK_AGREEMEN_SQL);
        Map<String, Object> map = new HashMap();
        map.put("orgId", orgId);
        map.put("itemId", itemId);
        queryString.append(" ORDER BY pl.ladder_qty DESC ");
        List<SrmPoRequisitionHeadersEntity_HI_RO> list = srmPoRequisitionHeadersDAO_HI_RO.findList(queryString, map);
        SrmPoRequisitionHeadersEntity_HI_RO requisitionHeadersEntity = null;
        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                requisitionHeadersEntity = list.get(i);
                if ("Y".equals(requisitionHeadersEntity.getLadderPriceFlag())) {
                    if (demandQty.compareTo(requisitionHeadersEntity.getLadderQty()) == 1) {
                        return requisitionHeadersEntity;
                    }
                } else {
                    return requisitionHeadersEntity;
                }
            }
        }
        return null;
    }

    /**
     * 多框架协议生成订单时，获取价格
     *
     * @param requisitionLineId
     * @return
     */
    private List<SrmPoRequisitionHeadersEntity_HI_RO> getPriceToMultipleAgreement(Integer requisitionLineId) {
        StringBuffer queryString = new StringBuffer(SrmPoRequisitionHeadersEntity_HI_RO.QUERY_FRAMEWORK_AGREEMEN_SQL2);
        Map<String, Object> map = new HashMap();
        map.put("requisitionLineId", requisitionLineId);
        queryString.append(" ORDER BY poh.po_header_id, pol.ladder_qty DESC ");
        List<SrmPoRequisitionHeadersEntity_HI_RO> list = srmPoRequisitionHeadersDAO_HI_RO.findList(queryString, map);
        SrmPoRequisitionHeadersEntity_HI_RO requisitionHeadersEntity = null;
        Integer poHeaderId = 0;
        //删除List元素，需要使用迭代器进行删除
        Iterator<SrmPoRequisitionHeadersEntity_HI_RO> iterator = list.iterator();
        BigDecimal demandQty = null;
        BigDecimal ladderQty = null;
        while (iterator.hasNext()) {
            requisitionHeadersEntity = iterator.next();
            demandQty = requisitionHeadersEntity.getDemandQty();
            if ("Y".equals(requisitionHeadersEntity.getLadderPriceFlag())) {
                ladderQty = requisitionHeadersEntity.getLadderQty();
                if (demandQty.compareTo(ladderQty) == 0 || demandQty.compareTo(ladderQty) == 1) {
                    if (poHeaderId.intValue() != 0 && poHeaderId.equals(requisitionHeadersEntity.getPoHeaderId())) {
                        iterator.remove();
                        continue;
                    }
                    poHeaderId = requisitionHeadersEntity.getPoHeaderId();
                } else {
                    iterator.remove();
                    continue;
                }
            }
        }
        //按含税价进行排序-升序
        Collections.sort(list, new Comparator<SrmPoRequisitionHeadersEntity_HI_RO>() {
            @Override
            public int compare(SrmPoRequisitionHeadersEntity_HI_RO u1, SrmPoRequisitionHeadersEntity_HI_RO u2) {
                BigDecimal diff = u1.getTaxPrice().subtract(u2.getTaxPrice());
                if (diff.doubleValue() > 0) {
                    return 1;
                } else if (diff.doubleValue() < 0) {
                    return -1;
                }
                return 0;
            }
        });
        //根据含税价自动分配比例，含税价最低的默认100%
        BigDecimal taxPrice = null;
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            requisitionHeadersEntity = list.get(i);
            if (i == 0) {
                requisitionHeadersEntity.setDistributionProportion(new BigDecimal(100));
            } else {
                requisitionHeadersEntity.setDistributionProportion(new BigDecimal(0));
            }
//            if (taxPrice == null) {
//                taxPrice = requisitionHeadersEntity.getTaxPrice();
//                requisitionHeadersEntity.setDistributionProportion(new BigDecimal(100));
//                j = i;
//            } else if (taxPrice.compareTo(requisitionHeadersEntity.getTaxPrice()) == 0 || taxPrice.compareTo(requisitionHeadersEntity.getTaxPrice()) == 1) {
//                taxPrice = requisitionHeadersEntity.getTaxPrice();
//                list.get(j).setDistributionProportion(new BigDecimal(0));
//                requisitionHeadersEntity.setDistributionProportion(new BigDecimal(100));
//                j = i;
//            } else {
//                requisitionHeadersEntity.setDistributionProportion(new BigDecimal(0));
//            }
        }
        return list;
    }

    /**
     * Description：提交，多框架协议，订单处理页面
     * <p>
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
     * <p>
     * Update History
     * =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoRequisitionHeadersEntity_HI_RO> findOrderDealList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        JSONArray requisitionHeaderIds = params.getJSONArray("requisitionHeaderIds");
        if (requisitionHeaderIds.size() > 0 && null != requisitionHeaderIds) {
            Pagination<SrmPoRequisitionHeadersEntity_HI_RO> result = new Pagination();
            List<SrmPoRequisitionHeadersEntity_HI_RO> list = new ArrayList<>();
            for (int i = 0; i < requisitionHeaderIds.size(); i++) {
                JSONObject obj = requisitionHeaderIds.getJSONObject(i);
                Integer requisitionLineId = obj.getInteger("requisitionLineId");
                list.addAll(getPriceToMultipleAgreement(requisitionLineId));
            }
            result.setData(list);
            result.setCurIndex(1);
            result.setNextIndex(1);
            result.setPagesCount(1);
            result.setPageSize(100);
            result.setPreIndex(1);
            result.setCount(list.size());
            return result;
        }
        return null;
    }
//    public Pagination<SrmPoRequisitionHeadersEntity_HI_RO> findOrderDealList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
//        Map<String, Object> queryParamMap = new HashMap();
//        try {
//            StringBuffer queryString = new StringBuffer(SrmPoRequisitionHeadersEntity_HI_RO.QUERY_PO_FRAMEWORK_AGREEMEN_REQUISITION_SQL);
//            JSONArray requisitionHeaderIds = params.getJSONArray("requisitionHeaderIds");
//            StringBuffer buff = new StringBuffer();
//            StringBuffer stringBuffer = new StringBuffer();
//            //ArrayList tempList=new ArrayList();
//            if (requisitionHeaderIds.size() > 0 && null != requisitionHeaderIds) {
//                buff.append("(");
//                for (int i = 0; i < requisitionHeaderIds.size(); i++) {
//                    JSONObject obj = requisitionHeaderIds.getJSONObject(i);
//                    Integer requisitionLineId = obj.getInteger("requisitionLineId");
//                    buff.append(requisitionLineId);
//                    buff.append(",");
//                    //协议数据
//                    JSONArray frameworkAgreemenData = obj.getJSONArray("frameworkAgreemenData");
//                    if (frameworkAgreemenData.size() > 0 && null != frameworkAgreemenData) {
//                        stringBuffer.append("(");
//                        for (int j = 0; j < frameworkAgreemenData.size(); j++) {
//                            JSONObject jsonObject = frameworkAgreemenData.getJSONObject(j);
//                            //将无效的协议筛选掉
//                            if (jsonObject.getString("isPriceEffective") != null &&
//                                    !"".equals(jsonObject.getString("isPriceEffective")) &&
//                                    "无效".equals(jsonObject.getString("isPriceEffective"))) {
//                                continue;
//                            }
//                            Integer poLineId = jsonObject.getInteger("poLineId");
//                            stringBuffer.append(poLineId);
//                            stringBuffer.append(",");
//                        }
//                        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
//                        stringBuffer.append(")");
//                    }
//
//                }
//                buff.deleteCharAt(buff.length() - 1);
//                buff.append(")");
//
//                if (!"".equals(buff.toString())) {
//                    queryString.append(" AND tb1.requisitionLineId in" + buff.toString());
//                }
//                if (!"".equals(stringBuffer.toString())) {
//                    queryString.append(" AND tb2.poLineId in" + stringBuffer.toString());
//                }
//                queryString.append(")tb\r\n");
//                queryString.append(" LEFT JOIN srm_po_req_distributions prd ON tb.requisitionLineId = prd.requisition_line_id AND tb.poLineId = prd.po_line_id\r\n");
//
//                queryString.append(" ORDER BY tb.taxPrice "); //排序
//                Pagination<SrmPoRequisitionHeadersEntity_HI_RO> result = srmPoRequisitionHeadersDAO_HI_RO.findPagination(queryString.toString(), queryParamMap, pageIndex, pageRows);
//                //取data，处理分配比例
//                JSONArray data = JSON.parseObject(JSONArray.toJSONString(result)).getJSONArray("data");
//                if (data.size() > 0) {
//                    for (int i = 0; i < data.size(); i++) {
//                        JSONObject obj = data.getJSONObject(i);
//                        //BigDecimal taxPrice = data.getJSONObject(i).getBigDecimal("taxPrice");
//                        BigDecimal distributionProportion = data.getJSONObject(i).getBigDecimal("distributionProportion");
//                        if (distributionProportion == null) {
//                            if (i == 0) {
//                                distributionProportion = new BigDecimal(100);
//                                obj.put("distributionProportion", distributionProportion);
//                            } else {
//                                distributionProportion = new BigDecimal(0);
//                                obj.put("distributionProportion", distributionProportion);
//                            }
//                        }
//                    }
//                    List<SrmPoRequisitionHeadersEntity_HI_RO> dataNew = data.toJavaList(SrmPoRequisitionHeadersEntity_HI_RO.class);
//                    result.setData(dataNew);
//                }
//
//                return result;
//            }
//            return null;
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }


    /**
     * Description：保存采购申请分配表数据
     * <p>
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
     * <p>
     * Update History
     * =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    @Override
    public JSONObject savePoReqDistributions(JSONObject paramJSON) throws Exception {
        Integer varUserId = paramJSON.getInteger("varUserId");
        JSONArray datas = paramJSON.getJSONArray("data");
        if (null == datas || datas.isEmpty()) {
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "暂无数据", 0, null);
        }
        SrmPoReqDistributionsEntity_HI entity_hi = null;
        for (int i = 0; i < datas.size(); i++) {
            JSONObject obj = datas.getJSONObject(i);
            //如果id为空则新增 否则为修改
            if (null == obj.getInteger("reqDistributionId")) {
                entity_hi = new SrmPoReqDistributionsEntity_HI();
                entity_hi.setRequisitionLineId(obj.getInteger("requisitionLineId"));
                entity_hi.setDistributionProportion(obj.getBigDecimal("distributionProportion"));
                entity_hi.setPoLineId(obj.getInteger("poLineId"));
                entity_hi.setVersionNum(0);
                entity_hi.setOperatorUserId(varUserId);
                srmPoReqDistributionsDAO_HI.save(entity_hi);
            } else { // 更新
                entity_hi = srmPoReqDistributionsDAO_HI.findByProperty("reqDistributionId", obj.get("reqDistributionId")).get(0);
                entity_hi.setRequisitionLineId(obj.getInteger("requisitionLineId"));
                entity_hi.setDistributionProportion(obj.getBigDecimal("distributionProportion"));
                entity_hi.setPoLineId(obj.getInteger("poLineId"));
                entity_hi.setVersionNum(entity_hi.getVersionNum() + 1);
                entity_hi.setOperatorUserId(varUserId);
                srmPoReqDistributionsDAO_HI.update(entity_hi);
            }
        }
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, entity_hi);
    }

    /**
     * Description：查询采购申请
     *
     * @param jsonParams 查询条件参数
     * @param pageIndex  页码
     * @param pageRows   页行数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoRequisitionHeadersEntity_HI_RO> findRequisition(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
            pageRows = 10;
        }
        String requisitionHeaderId = jsonParams.getString("requisitionHeaderId");
        Map<String, Object> map = new HashMap();
        StringBuffer sb = new StringBuffer(SrmPoRequisitionHeadersEntity_HI_RO.QUERY_PO_REQUISITION_SQL);
        if (jsonParams.getInteger("requisitionHeaderId") != null && jsonParams.getInteger("requisitionHeaderId") > 0) {
            sb.append(" AND t.requisitionHeaderId =" + requisitionHeaderId);
        } else if (jsonParams.getInteger("requisitionHeaderId") != null && jsonParams.getInteger("requisitionHeaderId") == -0) {
            sb.append(" AND t.requisitionHeaderId =" + requisitionHeaderId);
        } else {
            SaafToolUtils.parperParam(jsonParams, "t.requisitionNumber", "requisitionNumber", sb, map, "LIKE");
            SaafToolUtils.parperParam(jsonParams, "t.orgName", "orgName", sb, map, "LIKE");
            SaafToolUtils.parperParam(jsonParams, "t.departmentId", "departmentId", sb, map, "=");
            SaafToolUtils.parperParam(jsonParams, "t.requisitionType", "requisitionType", sb, map, "=");
            SaafToolUtils.parperParam(jsonParams, "t.organizationName", "organizationName", sb, map, "LIKE");
            SaafToolUtils.parperParam(jsonParams, "t.employeeId", "employeeId", sb, map, "LIKE");
            SaafToolUtils.parperParam(jsonParams, "t.requisitionStatus", "requisitionStatus", sb, map, "=");
            String creationDateFrom = jsonParams.getString("creationDateFrom");
            if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
                sb.append(" AND t.requisitionDate >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
                map.put("creationDateFrom", creationDateFrom);
            }
            String creationDateTo = jsonParams.getString("creationDateTo");
            if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
                sb.append(" AND t.requisitionDate <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
                map.put("creationDateTo", creationDateTo);
            }
            SaafToolUtils.parperParam(jsonParams, "t.sourceNumberh", "sourceNumberh", sb, map, "=");
            SaafToolUtils.parperParam(jsonParams, "t.itemId", "itemId", sb, map, "=");
            SaafToolUtils.parperParam(jsonParams, "t.fullCategoryName", "fullCategoryName", sb, map, "=");
            SaafToolUtils.parperParam(jsonParams, "t.buyerId", "buyerId", sb, map, "=");
            SaafToolUtils.parperParam(jsonParams, "t.comments", "comments", sb, map, "=");
            SaafToolUtils.parperParam(jsonParams, "t.lineComments", "lineComments", sb, map, "=");

            if ("Y".equals(jsonParams.getString("isToPo"))) {
                sb.append(" and t.poHeaderId is not null ");
            }

            if ("Y".equals(jsonParams.getString("unToPo"))) {
                sb.append(" and t.poHeaderId is null ");
            }


        }
        String linesQuery = jsonParams.getString("linesQuery");
        if (linesQuery != null && !"".equals(linesQuery)) {
            //验证字符串是否含有SQL关键字及字符，有则返回NULL
            if (SrmUtils.isContainSQL(linesQuery)) {
                return null;
            }
            sb.append(" AND (t.itemCode LIKE '%" + linesQuery + "%' OR t.itemName LIKE '%" + linesQuery + "%' OR t.fullCategoryCode LIKE '%" + linesQuery + "%' OR t.fullCategoryName LIKE '%" + linesQuery + "%')");
        }
        //只能查看当前账号已分配的业务实体相关的数据
        srmUserInstSqlUtil.concatUserInstSql(jsonParams.getInteger("varUserId"), sb, map, "t", "orgId");
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY t.lastUpdateDate DESC, t.lineNumber");
        Pagination<SrmPoRequisitionHeadersEntity_HI_RO> result = srmPoRequisitionHeadersDAO_HI_RO.findPagination(sb, countSql, map, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：查询详细信息
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmPoRequisitionHeadersEntity_HI_RO> queryRequisitionInfo(JSONObject jsonParams) {
        Map<String, Object> queryParamMap1 = new HashMap();
        StringBuffer queryString1 = new StringBuffer();
        queryString1.append(SrmPoRequisitionHeadersEntity_HI_RO.QUERY_REQUISITON_HEADER_SQL);
        SaafToolUtils.parperParam(jsonParams, "rh.requisition_header_id", "requisitionHeaderId", queryString1, queryParamMap1, "=");
        List<SrmPoRequisitionHeadersEntity_HI_RO> result = srmPoRequisitionHeadersDAO_HI_RO.findList(queryString1.toString(), queryParamMap1);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoRequisitionHeadersEntity_HI_RO.QUERY_REQUISITON_LINES_SQL);
        SaafToolUtils.parperParam(jsonParams, "rh.requisition_header_id", "requisitionHeaderId", queryString, queryParamMap, "=");
        String linesQuery = jsonParams.getString("linesQuery");
        if (linesQuery != null && !"".equals(linesQuery)) {
            queryString.append(" AND (sbi.item_code LIKE '%" + linesQuery + "%' OR rl.item_name LIKE '%" + linesQuery + "%' OR bc.full_category_code LIKE '%" + linesQuery + "%' OR bc.full_category_name LIKE '%" + linesQuery + "%')");
        }
        queryString.append(" ORDER BY rh.last_update_date DESC,rl.line_number");
        List<SrmPoRequisitionHeadersEntity_HI_RO> linesResult = srmPoRequisitionHeadersDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        result.get(0).setLineList(linesResult);
        return result;
    }

    /**
     * Description：保存采购申请
     * <p>
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
     * <p>
     * Update History
     * =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    @Override
    public JSONObject updateRequisition(JSONObject jsonParams, int operatorUserId) throws Exception {
        JSONArray linesArray = jsonParams.getJSONArray("lines");
        SrmPoRequisitionHeadersEntity_HI srmPoRequisitionHeadersEntity = new SrmPoRequisitionHeadersEntity_HI();
        if (jsonParams.getInteger("requisitionHeaderId") != null && jsonParams.getInteger("requisitionHeaderId") > 0) {
            srmPoRequisitionHeadersEntity = srmPoRequisitionHeadersDAO_HI.getById(jsonParams.getInteger("requisitionHeaderId"));
            srmPoRequisitionHeadersEntity.setRequisitionNumber(jsonParams.getString("requisitionNumber"));
            srmPoRequisitionHeadersEntity.setRequisitionDate(jsonParams.getDate("requisitionDate"));
            srmPoRequisitionHeadersEntity.setRequisitionType(jsonParams.getString("requisitionType"));
            srmPoRequisitionHeadersEntity.setRequisitionStatus(jsonParams.getString("requisitionStatus"));
        } else {
            String requisitionNumber = null;
            Date date = new Date(System.currentTimeMillis());
            String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
            requisitionNumber = saafSequencesUtil.getDocSequences("srm_po_requisition_headers", "PR-", dateFromate + "-", 3);
            srmPoRequisitionHeadersEntity.setRequisitionNumber(requisitionNumber);
            srmPoRequisitionHeadersEntity.setRequisitionDate(jsonParams.getDate("requisitionDate"));
            srmPoRequisitionHeadersEntity.setRequisitionType(jsonParams.getString("requisitionType"));
            srmPoRequisitionHeadersEntity.setRequisitionStatus("DRAFT");
        }
        if (jsonParams.getString("flag") != null && "submit".equals(jsonParams.getString("flag"))) {
            srmPoRequisitionHeadersEntity.setRequisitionStatus("SUBMITTED");
        }
        srmPoRequisitionHeadersEntity.setOrgId(jsonParams.getInteger("orgId"));
        srmPoRequisitionHeadersEntity.setOrganizationId(jsonParams.getInteger("organizationId"));
        srmPoRequisitionHeadersEntity.setDepartmentId(jsonParams.getInteger("departmentId"));
        srmPoRequisitionHeadersEntity.setRequisitionEmpId(jsonParams.getInteger("employeeId"));
        srmPoRequisitionHeadersEntity.setSourceNumber(jsonParams.getString("sourceNumberh"));
        srmPoRequisitionHeadersEntity.setComments(jsonParams.getString("comments"));
        if (!ObjectUtils.isEmpty(jsonParams.getInteger("buyerId")) && !undefined.equals(jsonParams.getInteger("buyerId"))) {
            srmPoRequisitionHeadersEntity.setBuyerId(jsonParams.getInteger("buyerId"));
        }
        srmPoRequisitionHeadersEntity.setOperatorUserId(operatorUserId);
        srmPoRequisitionHeadersDAO_HI.saveOrUpdate(srmPoRequisitionHeadersEntity);
        SrmPoRequisitionLinesEntity_HI srmPoRequisitionLinesEntity = null;
        List<SrmPoRequisitionLinesEntity_HI> list = new ArrayList<SrmPoRequisitionLinesEntity_HI>();
        if (null != linesArray && linesArray.size() > 0) {
            for (int i = 0; i < linesArray.size(); i++) {
                JSONObject object = linesArray.getJSONObject(i);
                srmPoRequisitionLinesEntity = new SrmPoRequisitionLinesEntity_HI();
                if (object.getInteger("requisitionLineId") != null && object.getInteger("requisitionLineId") > 0) {
                    srmPoRequisitionLinesEntity = srmPoRequisitionLinesDAO_HI.findByProperty("requisitionLineId", object.getInteger("requisitionLineId")).get(0);
                    srmPoRequisitionLinesEntity.setLineStatus(object.getString("lineStatus"));
                } else {
                    srmPoRequisitionLinesEntity.setLineStatus("OPEN");
                }
                StringBuffer queryString = new StringBuffer();
                Map<String, Object> queryParamMap = new HashMap<>();
                queryString.append(SaafLookupValuesEntity_HI_RO.QUERY_SQL);
                SaafToolUtils.parperParam(object, "slv.meaning", "uomCodeDesc", queryString, queryParamMap, "=");
                List<SaafLookupValuesEntity_HI_RO> result = saafLookupValuesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
                if (result != null && result.size() > 0) {
                    for (SaafLookupValuesEntity_HI_RO Values : result) {
                        srmPoRequisitionLinesEntity.setUomCode(Values.getLookupCode());
                    }
                } else {
                    return SToolUtils.convertResultJSONObj("E", "单位不存在！", 1, "");
                }
                srmPoRequisitionLinesEntity.setRequisitionHeaderId(srmPoRequisitionHeadersEntity.getRequisitionHeaderId());
                srmPoRequisitionLinesEntity.setLineNumber(object.getInteger("lineNumber"));
                srmPoRequisitionLinesEntity.setItemId(object.getInteger("itemId"));
                srmPoRequisitionLinesEntity.setItemName(object.getString("itemName"));
                srmPoRequisitionLinesEntity.setItemSpec(object.getString("itemSpec"));
                srmPoRequisitionLinesEntity.setCategoryId(object.getInteger("categoryId"));
                srmPoRequisitionLinesEntity.setMinPacking(object.getBigDecimal("minPacking"));
                if (!ObjectUtils.isEmpty(object.getString("buyerId")) && !undefined.equals(object.getString("buyerId"))) {
                    srmPoRequisitionLinesEntity.setBuyerId(object.getInteger("buyerId"));
                }
                srmPoRequisitionLinesEntity.setDemandQty(object.getBigDecimal("demandQty"));
                srmPoRequisitionLinesEntity.setDemandDate(object.getDate("demandDate"));
                srmPoRequisitionLinesEntity.setLineComments(object.getString("lineComments"));
                srmPoRequisitionLinesEntity.setSourceNumber(object.getString("sourceNumberi"));
                if (!ObjectUtils.isEmpty(object.getInteger("supplierId"))) {
                    srmPoRequisitionLinesEntity.setSupplierId(object.getInteger("supplierId"));
                }
                srmPoRequisitionLinesEntity.setOperatorUserId(operatorUserId);
                list.add(srmPoRequisitionLinesEntity);
            }
            if (null != list && list.size() > 0) {
                srmPoRequisitionLinesDAO_HI.saveOrUpdateAll(list);
            }
        }
        JSONObject result = null;
        if (jsonParams.getString("flag") != null && "submit".equals(jsonParams.getString("flag"))) {
            result = SToolUtils.convertResultJSONObj("S", "提交成功", 1, srmPoRequisitionHeadersEntity);
        } else if (jsonParams.getString("flag") != null && "save".equals(jsonParams.getString("flag"))) {
            result = SToolUtils.convertResultJSONObj("S", "保存成功", 1, srmPoRequisitionHeadersEntity);
        }

        if ("SUBMITTED".equals(srmPoRequisitionHeadersEntity.getRequisitionStatus())) {
            //查询创建人上级岗位
            SaafEmployeesEntity_HI se = saafEmployeesDAO_HI.getById(srmPoRequisitionHeadersEntity.getRequisitionEmpId());
            if (!ObjectUtils.isEmpty(se)) {
                if(StringUtils.isEmpty(se.getParentsQuartersCode())){
                    throw new UtilsException("未存在上级岗位审批人！");
                }else{
                    List<SaafEmployeesEntity_HI> userList = saafEmployeesDAO_HI.findByProperty("quartersCode", se.getParentsQuartersCode());
                    if (!ObjectUtils.isEmpty(userList)) {
                        String content = "<p>您好！</p><br/>" + "您有新的采购申请单据: " + srmPoRequisitionHeadersEntity.getRequisitionNumber() + "需要审批！";
                        for (int c = 0; c < userList.size(); c++) {
                            String emailAddress = userList.get(c).getEmail();
                            //发送邮件
                            sendMailUtil.doSendHtmlEmail("采购申请审批通知", content, new String[]{emailAddress});
                        }
                    }
                }
            }
        }


        result.put("requisitionHeaderId", srmPoRequisitionHeadersEntity.getRequisitionHeaderId());
        result.put("requisitionStatus", srmPoRequisitionHeadersEntity.getRequisitionStatus());
        return result;
    }

    /**
     * Description：修改采购申请状态
     * <p>
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
     * <p>
     * Update History
     * =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */
    @Override
    public JSONObject updateStatusRequisition(JSONObject jsonParam) throws Exception {
        LOGGER.info("updateStatusRequisition:" + jsonParam.toJSONString());
        JSONObject flag = null;
        JSONArray linesArray = jsonParam.getJSONArray("arr");
        if (null == linesArray || linesArray.isEmpty()) {
            SToolUtils.convertResultJSONObj(ERROR_STATUS, "暂无数据", 0, null);
        }
        for (int i = 0; i < linesArray.size(); i++) {
            JSONObject object = linesArray.getJSONObject(i);
            if (object.getInteger("requisitionHeaderId") != null && object.getInteger("requisitionHeaderId") > 0) {
                SrmPoRequisitionHeadersEntity_HI srmPoRequisitionHeadersEntity = srmPoRequisitionHeadersDAO_HI.getById(object.getInteger("requisitionHeaderId"));
                //转订单
                if ("transferOrder".equals(jsonParam.getString("lineStatus"))) {
                    if (srmPoRequisitionHeadersEntity != null && srmPoRequisitionHeadersEntity.getSourceId() != null && !"".equals(srmPoRequisitionHeadersEntity.getSourceId())) {
                        flag = SToolUtils.convertResultJSONObj("E", "单据已转换采购订单或不存在！", 0, null);
                        continue;
                    } else {
                        if (srmPoRequisitionHeadersEntity.getRequisitionStatus() != null && "APPROVED".equals(srmPoRequisitionHeadersEntity.getRequisitionStatus())) {
                            SrmPoHeadersEntity_HI srmPoHeadersEntity_HI = new SrmPoHeadersEntity_HI();
                            String poHeadersNumber = null;
                            Date date = new Date(System.currentTimeMillis());
                            String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
                            poHeadersNumber = saafSequencesUtil.getDocSequences("srm_po_headers", "PO-", dateFromate, 4);
                            srmPoHeadersEntity_HI.setPoNumber(poHeadersNumber);
                            /*srmPoHeadersEntity_HI.setBillToOrganizationId(srmPoRequisitionHeadersEntity.getOrganizationId());
                            srmPoHeadersEntity_HI.setShipToOrganizationId(srmPoRequisitionHeadersEntity.getOrganizationId());*/
                            srmPoHeadersEntity_HI.setOrganizationId(srmPoRequisitionHeadersEntity.getOrganizationId());
                            srmPoHeadersEntity_HI.setOrgId(srmPoRequisitionHeadersEntity.getOrgId());
                            srmPoHeadersEntity_HI.setPoDocType("ORDER");
                            srmPoHeadersEntity_HI.setCurrencyCode("CNY");
                            srmPoHeadersEntity_HI.setStatus("DRAFT");
                            srmPoHeadersEntity_HI.setSettlementWay("TRANSFER");
                            srmPoHeadersEntity_HI.setPoType(srmPoRequisitionHeadersEntity.getRequisitionType());
                            srmPoHeadersEntity_HI.setPoVersions(new BigDecimal("0"));
                            srmPoHeadersEntity_HI.setBuyerId(srmPoRequisitionHeadersEntity.getRequisitionEmpId());
                            srmPoHeadersEntity_HI.setSourceId(String.valueOf(srmPoRequisitionHeadersEntity.getRequisitionHeaderId()));
                            srmPoHeadersEntity_HI.setSourceCode("PR");
                            srmPoHeadersEntity_HI.setPrNumber(srmPoRequisitionHeadersEntity.getRequisitionNumber());
                            srmPoHeadersEntity_HI.setOperatorUserId(jsonParam.getInteger("operatorUserId"));
                            /*srmPoHeadersDAO_HI.save(srmPoHeadersEntity_HI);
                            srmPoRequisitionHeadersEntity.setSourceId(String.valueOf(srmPoHeadersEntity_HI.getPoHeaderId()));
                            srmPoRequisitionHeadersEntity.setSourceCode("PO");
                            srmPoRequisitionHeadersEntity.setSourceNumber(srmPoHeadersEntity_HI.getPoNumber());
                            srmPoRequisitionHeadersDAO_HI.update(srmPoRequisitionHeadersEntity);*/
                            List<SrmPoRequisitionLinesEntity_HI> list = srmPoRequisitionLinesDAO_HI.findByProperty("requisition_header_id", object.getInteger("requisitionHeaderId"));
                            srmPoHeadersEntity_HI.setSupplierId(list.get(0).getSupplierId());
                            srmPoHeadersDAO_HI.save(srmPoHeadersEntity_HI);
                            srmPoRequisitionHeadersEntity.setSourceId(String.valueOf(srmPoHeadersEntity_HI.getPoHeaderId()));
                            srmPoRequisitionHeadersEntity.setSourceCode("PO");
                            srmPoRequisitionHeadersEntity.setSourceNumber(srmPoHeadersEntity_HI.getPoNumber());
                            srmPoRequisitionHeadersDAO_HI.update(srmPoRequisitionHeadersEntity);

                            SrmPoLinesEntity_HI srmPoLinesEntity_HI = null;
                            int a = 0;
                            for (SrmPoRequisitionLinesEntity_HI srmPoRequisitionLines : list) {
                                if ("1".equals(srmPoRequisitionLines.getHandleWay()) || "2".equals(srmPoRequisitionLines.getHandleWay()) || "3".equals(srmPoRequisitionLines.getHandleWay())) {
                                    return SToolUtils.convertResultJSONObj("E", "申请单" + srmPoRequisitionHeadersEntity.getRequisitionNumber() + "，存在行已转采购订单或其他单据，转换失败！", 0, null);
                                }
                                srmPoLinesEntity_HI = new SrmPoLinesEntity_HI();
                                srmPoLinesEntity_HI.setLineNumber(srmPoRequisitionLines.getLineNumber());
                                srmPoLinesEntity_HI.setItemName(srmPoRequisitionLines.getItemName());
                                srmPoLinesEntity_HI.setItemSpec(srmPoRequisitionLines.getItemSpec());
                                srmPoLinesEntity_HI.setStatus("DRAFT");
                                srmPoLinesEntity_HI.setOnWayQty(new BigDecimal("0"));
                                srmPoLinesEntity_HI.setReceivedQty(new BigDecimal("0"));
                                srmPoLinesEntity_HI.setReturnQty(new BigDecimal("0"));
                                srmPoLinesEntity_HI.setOriginalDemandQty(null);
                                srmPoLinesEntity_HI.setOriginalDemandDate(null);
                                srmPoLinesEntity_HI.setFeedbackAdjustDate(null);
                                srmPoLinesEntity_HI.setFeedbackAdjustQty(null);
                                srmPoLinesEntity_HI.setFeedbackStatus("NON_FEEDBACK");
                                srmPoLinesEntity_HI.setFeedbackResult("CONFIRM");
                                srmPoLinesEntity_HI.setRejectReason(null);
                                srmPoLinesEntity_HI.setMayNoticeQty((srmPoRequisitionLines.getDemandQty() == null ? new BigDecimal(0) : srmPoRequisitionLines.getDemandQty()).subtract(srmPoLinesEntity_HI.getOnWayQty()));
                                srmPoLinesEntity_HI.setPoHeaderId(srmPoHeadersEntity_HI.getPoHeaderId());
                                srmPoLinesEntity_HI.setItemId(srmPoRequisitionLines.getItemId());
                                srmPoLinesEntity_HI.setCategoryId(srmPoRequisitionLines.getCategoryId());
                                srmPoLinesEntity_HI.setDemandQty(srmPoRequisitionLines.getDemandQty());
                                srmPoLinesEntity_HI.setMinPoQty(srmPoRequisitionLines.getMinPacking());
                                srmPoLinesEntity_HI.setDemandDate(srmPoRequisitionLines.getDemandDate());
                                srmPoLinesEntity_HI.setDescription(srmPoRequisitionLines.getLineComments());
                                srmPoLinesEntity_HI.setOperatorUserId(jsonParam.getInteger("operatorUserId"));
                                srmPoLinesEntity_HI.setSourceId(String.valueOf(srmPoRequisitionLines.getRequisitionLineId()));
                                srmPoLinesEntity_HI.setSourceCode("PR");
                                srmPoLinesEntity_HI.setContext("项目采购");
                                srmPoLinesDAO_HI.saveOrUpdate(srmPoLinesEntity_HI);
                                srmPoRequisitionLines.setHandleWay("3");
                                srmPoRequisitionLines.setSourceId(String.valueOf(srmPoLinesEntity_HI.getPoLineId()));
                                srmPoRequisitionLines.setSourceCode("PO");
                                srmPoRequisitionLines.setSourceNumber(srmPoHeadersEntity_HI.getPoNumber());
                                srmPoRequisitionLines.setPoHeaderId(srmPoLinesEntity_HI.getPoHeaderId());
                                srmPoRequisitionLinesDAO_HI.update(srmPoRequisitionLines);
                            }
                        }
                    }
                } else {
                    //如果是关闭或取消采购申请,则需要判断是否已经生成采购订单
                    if ("CLOSED".equals(jsonParam.getString("requisitionStatus")) || "CANCELLED".equals(jsonParam.getString("requisitionStatus"))) {
                        List<SrmPoHeadersEntity_HI> poHeadersList = srmPoHeadersDAO_HI.findByProperty("source_id", String.valueOf(srmPoRequisitionHeadersEntity.getRequisitionHeaderId()));
                        if (poHeadersList.size() > 0) {
                            return SToolUtils.convertResultJSONObj("E", "操作失败!采购申请已经生成采购订单不允许取消、关闭。", 1, srmPoRequisitionHeadersEntity);
                        }
                    }
                    srmPoRequisitionHeadersEntity.setRequisitionStatus(jsonParam.getString("requisitionStatus"));
                    srmPoRequisitionHeadersEntity.setOperatorUserId(jsonParam.getInteger("operatorUserId"));
                    srmPoRequisitionHeadersDAO_HI.update(srmPoRequisitionHeadersEntity);
                }
                if ("CLOSED".equals(jsonParam.getString("requisitionStatus"))) {
                    flag = SToolUtils.convertResultJSONObj("S", "关闭成功", 1, srmPoRequisitionHeadersEntity);
                } else if ("CANCELLED".equals(jsonParam.getString("requisitionStatus"))) {
                    flag = SToolUtils.convertResultJSONObj("S", "取消成功", 1, srmPoRequisitionHeadersEntity);
                } else if ("transferOrder".equals(jsonParam.getString("lineStatus"))) {
                    flag = SToolUtils.convertResultJSONObj("S", "转订单成功", 1, srmPoRequisitionHeadersEntity);
                } else if ("APPROVED".equals(jsonParam.getString("requisitionStatus"))) {
                    flag = SToolUtils.convertResultJSONObj("S", "审批成功", 1, srmPoRequisitionHeadersEntity);
                } else if ("REJECTED".equals(jsonParam.getString("requisitionStatus"))) {
                    flag = SToolUtils.convertResultJSONObj("S", "拒绝成功", 1, srmPoRequisitionHeadersEntity);
                }
//					flag.put("srmPoRequisitionHeadersEntity",srmPoRequisitionHeadersEntity);
                flag.put("requisitionHeaderId", srmPoRequisitionHeadersEntity.getRequisitionHeaderId());
                flag.put("requisitionStatus", srmPoRequisitionHeadersEntity.getRequisitionStatus());
                flag.put("sourceId", srmPoRequisitionHeadersEntity.getSourceId());
            }
        }
        return flag;
    }

    /**
     * Description：删除采购申请行
     *
     * @param params 删除条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteRequisitionLines(JSONObject params) throws Exception {
        LOGGER.info("删除信息，参数：" + params.toString());
        JSONArray lineIds = params.getJSONArray("data");
        if (null == lineIds || lineIds.isEmpty()) {
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "暂无数据", 0, null);
        }
        List<SrmPoRequisitionLinesEntity_HI> linesList = null;
        for (int i = 0, j = lineIds.size(); i < j; i++) {
            Integer requisitionLineId = lineIds.getInteger(i);
            linesList = srmPoRequisitionLinesDAO_HI.findByProperty("requisitionLineId", requisitionLineId);
            if (!(requisitionLineId == null || "".equals(requisitionLineId))) {
                if (linesList != null && linesList.size() > 0) {
                    srmPoRequisitionLinesDAO_HI.delete(linesList);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除采购申请行，" + params.getString("requisitionLineId") + "不存在", 0, null);
            }
        }
        return SToolUtils.convertResultJSONObj("S", "删除成功", linesList.size(), null);
    }

    /**
     * Description：采购申请导入
     *
     * @param jsonParams 导入参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveImportRequisition(JSONObject jsonParams, Integer userId) throws Exception {
        int operatorUserId = jsonParams.getInteger("operatorUserId");
        JSONObject entity = jsonParams.getJSONObject("info");
        JSONArray jsonArray = jsonParams.getJSONArray("data");
        if (null == jsonArray || jsonArray.isEmpty()) {
            SToolUtils.convertResultJSONObj(ERROR_STATUS, "暂无数据", 0, null);
        }
        JSONArray error = cehckArray(jsonArray, entity.getInteger("requisitionHeaderId"), operatorUserId);
        if (null != error && error.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存失败", error.size(), error);
        }
        Map<String, Object> queryParamMap = null;
        StringBuffer queryString = null;
        SrmPoRequisitionLinesEntity_HI srmPoRequisitionLinesEntity = null;
        int count = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            srmPoRequisitionLinesEntity = new SrmPoRequisitionLinesEntity_HI();
            srmPoRequisitionLinesEntity.setRequisitionHeaderId(entity.getInteger("requisitionHeaderId"));
            JSONObject object = jsonArray.getJSONObject(i);
            if (object.getString("itemCode") != null && !"".equals(object.getString("itemCode"))) {
                queryParamMap = new HashMap<String, Object>();
                queryString = new StringBuffer(SrmPoForecastLinesEntity_HI_RO.QUERY_ITEM_LOV_SQL);
                SaafToolUtils.parperParam(object, "sbi.item_code", "itemCode", queryString, queryParamMap, "=");
                // SaafToolUtils.parperParam(object, "emp.employee_name", "buyerName", queryString, queryParamMap, "=");

                SrmPoForecastLinesEntity_HI_RO result = srmPoForecastLinesDAO_HI_RO.findList(queryString.toString(), queryParamMap).get(0);

                if (result != null) {
                    srmPoRequisitionLinesEntity.setItemId(result.getItemId());
                    srmPoRequisitionLinesEntity.setItemName(result.getItemName());
                    srmPoRequisitionLinesEntity.setUomCode(result.getUomCode());
                    srmPoRequisitionLinesEntity.setMinPacking(result.getMinPacking());
                    // srmPoRequisitionLinesEntity.setBuyerId(result.getEmployeeId());
                    srmPoRequisitionLinesEntity.setCategoryId(result.getCategoryId());
                }
            } else {
                srmPoRequisitionLinesEntity.setItemName(object.getString("itemName"));
                srmPoRequisitionLinesEntity.setItemSpec(object.getString("itemSpec"));
                queryParamMap = new HashMap<String, Object>();
                queryString = new StringBuffer(SrmBaseCategoriesEntity_HI_RO.GET_PO_HEADER_SQLS);
                SaafToolUtils.parperParam(object, "t.full_category_code", "fullCategoryCode", queryString, queryParamMap, "like");
                SrmBaseCategoriesEntity_HI_RO result = srmBaseCategoriesDAO_HI_RO.findList(queryString.toString(), queryParamMap).get(0);
                srmPoRequisitionLinesEntity.setCategoryId(result.getCategoryId());

                queryParamMap = new HashMap<String, Object>();
                queryString = new StringBuffer(SaafLookupValuesEntity_HI_RO.QUERY_SQL);
                SaafToolUtils.parperParam(object, "slv.meaning", "uomCodeDesc", queryString, queryParamMap, "like");
                SaafLookupValuesEntity_HI_RO rowSet = saafLookupValuesDAO_HI_RO.findList(queryString.toString(), queryParamMap).get(0);
                srmPoRequisitionLinesEntity.setUomCode(rowSet.getLookupCode());

                queryParamMap = new HashMap<String, Object>();
                queryString = new StringBuffer(" from SaafEmployeesEntity_HI where 1=1 ");
                SaafToolUtils.parperParam(object, "employeeName", "employeeName", queryString, queryParamMap, "=");
                SaafEmployeesEntity_HI result1 = saafEmployeesDAO_HI.findList(queryString, queryParamMap).get(0);
                srmPoRequisitionLinesEntity.setBuyerId(result1.getEmployeeId());
            }
            //自动生成行号lineNumber
            if (true) {
                queryParamMap = new HashMap<String, Object>();
                //queryParamMap.put("requisitionHeaderId",entity.getInteger("requisitionHeaderId"));	SQL里面没有此参数
                queryString = new StringBuffer(SrmPoForecastLinesEntity_HI_RO.QUERY_ITEM_LOV_MAXLINENUMBER);
                SaafToolUtils.parperParam(object, "sprl.requisition_header_id", "requisitionHeaderId", queryString, queryParamMap, "=");
                if (entity.getInteger("requisitionHeaderId") != null) {    //传入头id
                    queryString.append(" AND sprl.requisition_header_id = " + entity.getInteger("requisitionHeaderId") + "\n");
                }
                SrmPoForecastLinesEntity_HI_RO result = srmPoForecastLinesDAO_HI_RO.findList(queryString.toString(), queryParamMap).get(0);
                Integer maxLineNumber = (!ObjectUtils.isEmpty(result.getMaxLineNumber()) ? result.getMaxLineNumber() : 0);
                srmPoRequisitionLinesEntity.setLineNumber(++maxLineNumber);
            }
            srmPoRequisitionLinesEntity.setItemSpec(object.getString("itemSpec"));
            srmPoRequisitionLinesEntity.setDemandQty(object.getBigDecimal("demandQty"));
            //srmPoRequisitionLinesEntity.setDemandDate(object.getDate("demandDate"));
            //日期轉換
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                srmPoRequisitionLinesEntity.setDemandDate(dateFormat.parse(object.getString("demandDate")));
            } catch (Exception e) {
                throw new UtilsException("日期转换出错，请输入格式为yyyy-MM-dd的日期");
            }

            srmPoRequisitionLinesEntity.setLineComments(object.getString("lineComments"));
            srmPoRequisitionLinesEntity.setSourceNumber(object.getString("sourceNumber"));
            srmPoRequisitionLinesEntity.setBuyerId(object.getInteger("buyerId"));
            srmPoRequisitionLinesEntity.setLineStatus("OPEN");
            // srmPoRequisitionLinesEntity.setLineStatus("DRAFT");
            srmPoRequisitionLinesEntity.setOperatorUserId(operatorUserId);
            srmPoRequisitionLinesDAO_HI.save(srmPoRequisitionLinesEntity);
            srmPoRequisitionLinesDAO_HI.fluch();
            ++count;
        }
        /*JSONObject resultObj = new JSONObject();
        resultObj.put("msg", "导入成功行数为:" + count + "行!");
        resultObj.put("status", "S");
        return resultObj;*/
        return SToolUtils.convertResultJSONObj("S", "成功导入" + count + "行数据", 1, null);

    }

    /**
     * Description：导入数据校验
     *
     * @param jsonArray
     * @param requisitionHeaderId
     * @param operatorUserId
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private JSONArray cehckArray(JSONArray jsonArray, Integer requisitionHeaderId, Integer operatorUserId) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        if (null == jsonArray || jsonArray.isEmpty()) {
            return null;
        }
        JSONArray error = new JSONArray();
        JSONObject e = null;
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            if (object.getString("itemCode") != null && !"".equals(object.getString("itemCode"))) {
                queryParamMap = new HashMap<String, Object>();
                queryString = new StringBuffer(SrmPoForecastLinesEntity_HI_RO.QUERY_ITEM_LOV_SQL);
                String categoriesIdStr = srmBaseUserCategories.findUserCategoriesIdStr(operatorUserId);
                if (null != categoriesIdStr && !"".equals(categoriesIdStr)) {
                    queryString.append(" AND sbi.category_id in (" + categoriesIdStr + ")");
                }
                SaafToolUtils.parperParam(object, "sbi.item_code", "itemCode", queryString, queryParamMap, "=");
                System.out.println("queryString====" + queryString + "++++++++" + object.getString("itemCode"));
                List<SrmPoForecastLinesEntity_HI_RO> result = srmPoForecastLinesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
                if (result == null || result.size() < 1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "物料编码不存在");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
                //检查物料编码是否有重复的
                queryParamMap = new HashMap<String, Object>();
                queryString = new StringBuffer(SrmPoForecastLinesEntity_HI_RO.QUERY_ITEM_EXIST);
                queryParamMap.put("requisitionHeaderId", requisitionHeaderId);
                queryString.append("\tAND sprl.requisition_header_id =:requisitionHeaderId\n");
                SaafToolUtils.parperParam(object, "sbi.item_code", "itemCode", queryString, queryParamMap, "=");
                //SaafToolUtils.parperParam(object, "sbc.full_category_code", "fullCategoryCode", queryString, queryParamMap, "=");
                queryString.append("\tGROUP BY sbi.item_id\n");
                System.out.println("queryString:========" + queryString);
                List<SrmPoForecastLinesEntity_HI_RO> resultExit = srmPoForecastLinesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
                if (!(resultExit == null || resultExit.size() < 1)) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "该物料编码重复");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                } else {
                    if (result.size() > 0) {
                        object.put("itemSpec", result.get(0).getSpecification());
                        object.put("categoryId", result.get(0).getCategoryId());
                    }
                }
            } else {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "请填入物料编码");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            if (object.getString("itemName") != null && !"".equals(object.getString("itemName"))) {
                Map<String, Object> map = new HashMap<String, Object>();
                StringBuffer String = new StringBuffer(SrmPoForecastLinesEntity_HI_RO.QUERY_ITEM_LOV_SQL);
                SaafToolUtils.parperParam(object, "sbi.item_name", "itemName", String, map, "=");
                String categoriesIdStr = srmBaseUserCategories.findUserCategoriesIdStr(operatorUserId);
                if (null != categoriesIdStr && !"".equals(categoriesIdStr)) {
                    queryString.append(" AND sbi.category_id in (" + categoriesIdStr + ")");
                }
                List<SrmPoForecastLinesEntity_HI_RO> resulta = srmPoForecastLinesDAO_HI_RO.findList(String.toString(), map);
                if (resulta == null || resulta.size() < 1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "物料名称不存在");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
            if (object.getString("fullCategoryCode") != null && !"".equals(object.getString("fullCategoryCode"))) {
                queryParamMap = new HashMap<String, Object>();
                queryString = new StringBuffer(SrmBaseCategoriesEntity_HI_RO.GET_PO_HEADER_SQLS);
                SaafToolUtils.parperParam(object, "t.full_category_code", "fullCategoryCode", queryString, queryParamMap, "=");
                List<SrmBaseCategoriesEntity_HI_RO> result = srmBaseCategoriesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
                if (result == null || result.size() < 1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "分类编码不存在");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
            if (object.getString("uomCodeDesc") != null && !"".equals(object.getString("uomCodeDesc"))) {
                queryParamMap = new HashMap<String, Object>();
                queryString = new StringBuffer();
                queryString.append(SaafLookupValuesEntity_HI_RO.QUERY_SQL);
                SaafToolUtils.parperParam(object, "slv.meaning", "uomCodeDesc", queryString, queryParamMap, "like");
                List<SaafLookupValuesEntity_HI_RO> result = saafLookupValuesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
                if (result == null || result.size() < 1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "单位不存在");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
            if (object.getString("buyerName") != null && !"".equals(object.getString("buyerName"))) {
                queryParamMap = new HashMap<String, Object>();
                queryString = new StringBuffer(" from SaafEmployeesEntity_HI where 1=1 ");
                SaafToolUtils.parperParam(object, "employeeName", "buyerName", queryString, queryParamMap, "like");
                List<SaafEmployeesEntity_HI> result = saafEmployeesDAO_HI.findList(queryString, queryParamMap);
                if (result == null || result.size() < 1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "采购员不存在");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                } else {
                    object.put("buyerId", result.get(0).getEmployeeId());
                }
            }
        }
        return error;
    }

    /**
     * Description：查询行上已转订单信息
     *
     * @param params 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmPoLinesEntity_HI_RO> findPoHeaders(JSONObject params) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPoLinesEntity_HI_RO.FIND_PO_HEADERS);
        sb.append(" AND rl.requisition_header_id =" + params.getInteger("requisitionHeaderId"));
        sb.append("\n AND pl.source_id =" + params.getInteger("requisitionLineId"));
        sb.append("\n GROUP BY pl.po_line_id ");
        sb.append("UNION");
        sb.append(SrmPoLinesEntity_HI_RO.FIND_PON_AUCTION);
        sb.append("\n AND api.attribute1 = " + params.getInteger("requisitionHeaderId"));
        sb.append("\n AND api.attribute2 = " + params.getInteger("requisitionLineId"));
        List<SrmPoLinesEntity_HI_RO> list = srmPoLinesDAO_HI_RO.findList(sb.toString(), queryParamMap);
        return list;
    }


    /**
     * Description：删除采购申请头
     *
     * @param params 删除条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteRequisitionHeaders(JSONObject params) throws Exception {
        LOGGER.info("删除参数：" + params.toString());
        if (null != params.getInteger("requisitionHeaderId")) {
            SrmPoRequisitionHeadersEntity_HI headersRow = srmPoRequisitionHeadersDAO_HI.getById(params.getInteger("requisitionHeaderId"));
            if ("DRAFT".equals(headersRow.getRequisitionStatus()) || "REJECTED".equals(headersRow.getRequisitionStatus())) {
                if (null != headersRow) {
                    List<SrmPoRequisitionLinesEntity_HI> linesList = srmPoRequisitionLinesDAO_HI.findByProperty("requisitionHeaderId", headersRow.getRequisitionHeaderId());
                    if (linesList != null && linesList.size() > 0) {
                        srmPoRequisitionLinesDAO_HI.delete(linesList);
                    }
                    srmPoRequisitionHeadersDAO_HI.delete(headersRow);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除采购申请失败,只能删除拟定和驳回的单据!", 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "删除采购申请失败，" + params.getString("requisitionHeaderId") + "不存在", 0, null);
        }
        return SToolUtils.convertResultJSONObj("S", "删除采购申请成功", 1, null);
    }

    /**
     * Description：采购申请按选择的行转换成采购订单
     *
     * @param jsonParam 采购申请参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject saveReqTransferOrderByLines(JSONObject jsonParam) throws Exception {
        LOGGER.info("saveReqTransferOrderByLines:" + jsonParam.toJSONString());
        Integer operatorUserId = jsonParam.getInteger("operatorUserId");
        Integer requisitionHeaderId = jsonParam.getInteger("requisitionHeaderId");
        SrmPoRequisitionHeadersEntity_HI requisitionHeadersEntity = srmPoRequisitionHeadersDAO_HI.getById(requisitionHeaderId);
        if (requisitionHeadersEntity == null || !"APPROVED".equals(requisitionHeadersEntity.getRequisitionStatus())) {
            return SToolUtils.convertResultJSONObj("E", "申请单不存在或不是已批准状态，转换失败！", 0, null);
        }
        JSONArray lineIdsArray = jsonParam.getJSONArray("arr");
        List<SrmPoRequisitionLinesEntity_HI> lineList = new ArrayList();
        SrmPoRequisitionLinesEntity_HI requisitionLinesEntity = null;
        if (null != lineIdsArray && lineIdsArray.size() > 0) {
            for (int i = 0; i < lineIdsArray.size(); i++) {
                requisitionLinesEntity = srmPoRequisitionLinesDAO_HI.getById(lineIdsArray.getJSONObject(i).getInteger("requisitionLineId"));
                if ("1".equals(requisitionLinesEntity.getHandleWay()) || "2".equals(requisitionLinesEntity.getHandleWay()) || "3".equals(requisitionLinesEntity.getHandleWay())) {
                    return SToolUtils.convertResultJSONObj("E", "存在行已转采购订单或其他单据，转换失败！", 0, null);
                }
                lineList.add(requisitionLinesEntity);
            }
        }
        //数据没有问题，开始生成订单
        SrmPoHeadersEntity_HI poHeadersEntity = new SrmPoHeadersEntity_HI();
        Date date = new Date(System.currentTimeMillis());
        String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
        String poHeadersNumber = saafSequencesUtil.getDocSequences("srm_po_headers", "PO-", dateFromate + "-", 4);
        poHeadersEntity.setPoNumber(poHeadersNumber);
        poHeadersEntity.setBillToOrganizationId(requisitionHeadersEntity.getOrganizationId());
        poHeadersEntity.setShipToOrganizationId(requisitionHeadersEntity.getOrganizationId());
        poHeadersEntity.setOrgId(requisitionHeadersEntity.getOrgId());
        poHeadersEntity.setPoDocType("ORDER");
        poHeadersEntity.setCurrencyCode("CNY");
        poHeadersEntity.setStatus("DRAFT");
        poHeadersEntity.setBuyerId(requisitionHeadersEntity.getRequisitionEmpId());
        poHeadersEntity.setSourceId(String.valueOf(requisitionHeadersEntity.getRequisitionHeaderId()));
        poHeadersEntity.setPrNumber(requisitionHeadersEntity.getRequisitionNumber());
        poHeadersEntity.setSettlementWay("TRANSFER");
        poHeadersEntity.setPoVersions(new BigDecimal("0"));
        poHeadersEntity.setSourceCode("PR");
        poHeadersEntity.setPoType(requisitionHeadersEntity.getRequisitionType());
        poHeadersEntity.setOperatorUserId(operatorUserId);
        poHeadersEntity.setSupplierId(lineList.get(0).getSupplierId());
        srmPoHeadersDAO_HI.save(poHeadersEntity);
        SrmPoLinesEntity_HI srmPoLinesEntity_HI = null;
        List<SrmPoRequisitionLinesEntity_HI> result = new ArrayList<>();
        for (SrmPoRequisitionLinesEntity_HI requisitionLines : lineList) {
            srmPoLinesEntity_HI = new SrmPoLinesEntity_HI();
            srmPoLinesEntity_HI.setLineNumber(requisitionLines.getLineNumber());
            srmPoLinesEntity_HI.setItemName(requisitionLines.getItemName());
            srmPoLinesEntity_HI.setItemSpec(requisitionLines.getItemSpec());
            srmPoLinesEntity_HI.setStatus("OPEN");
            srmPoLinesEntity_HI.setOnWayQty(new BigDecimal("0"));
            srmPoLinesEntity_HI.setReceivedQty(new BigDecimal("0"));
            srmPoLinesEntity_HI.setReturnQty(new BigDecimal("0"));
            srmPoLinesEntity_HI.setOriginalDemandQty(null);
            srmPoLinesEntity_HI.setOriginalDemandDate(null);
            srmPoLinesEntity_HI.setFeedbackAdjustDate(null);
            srmPoLinesEntity_HI.setFeedbackAdjustQty(null);
            srmPoLinesEntity_HI.setFeedbackStatus("NON_FEEDBACK");
            srmPoLinesEntity_HI.setFeedbackResult("CONFIRM");
            srmPoLinesEntity_HI.setRejectReason(null);
            srmPoLinesEntity_HI.setMayNoticeQty((requisitionLines.getDemandQty() == null ? new BigDecimal(0) : requisitionLines.getDemandQty()).subtract(srmPoLinesEntity_HI.getOnWayQty()));
            srmPoLinesEntity_HI.setPoHeaderId(poHeadersEntity.getPoHeaderId());
            srmPoLinesEntity_HI.setItemId(requisitionLines.getItemId());
            srmPoLinesEntity_HI.setCategoryId(requisitionLines.getCategoryId());
            srmPoLinesEntity_HI.setDemandQty(requisitionLines.getDemandQty());
            srmPoLinesEntity_HI.setMinPoQty(requisitionLines.getMinPacking());
            srmPoLinesEntity_HI.setDemandDate(requisitionLines.getDemandDate());
            srmPoLinesEntity_HI.setDescription(requisitionLines.getLineComments());
            srmPoLinesEntity_HI.setOperatorUserId(jsonParam.getInteger("operatorUserId"));
            srmPoLinesEntity_HI.setSourceId(String.valueOf(requisitionLines.getRequisitionLineId()));
            srmPoLinesEntity_HI.setSourceCode("PR");
            srmPoLinesEntity_HI.setContext("项目采购");

            //取价格库税率
            JSONObject jsonParams = new JSONObject();
            jsonParam.put("supplierId", requisitionLines.getSupplierId());
            jsonParam.put("organizationId", requisitionHeadersEntity.getOrganizationId());
            jsonParam.put("itemId", requisitionLines.getItemId());
            jsonParam.put("calculateByLastestEabledPriceFlag", 'Y');
            Pagination<SrmPoHeadersEntity_HI_RO> priceList = iSrmPoFrameworkAgreement.findAgreementPriceLibraryList(jsonParam, 1, 1000);
            if (!ObjectUtils.isEmpty(priceList.getData())) {
                srmPoLinesEntity_HI.setTaxRateCode(priceList.getData().get(0).getTaxRateCode());
                srmPoLinesEntity_HI.setTaxPrice(priceList.getData().get(0).getTaxPrice());
                srmPoLinesEntity_HI.setNonTaxPrice(priceList.getData().get(0).getNonTaxPrice());
            }

            srmPoLinesDAO_HI.saveOrUpdate(srmPoLinesEntity_HI);
            requisitionLines.setHandleWay("3");
            requisitionLines.setSourceId(String.valueOf(srmPoLinesEntity_HI.getPoLineId()));
            requisitionLines.setSourceCode("PO");
            requisitionLines.setSourceNumber(poHeadersEntity.getPoNumber());
            requisitionLines.setPoHeaderId(srmPoLinesEntity_HI.getPoHeaderId());
            srmPoRequisitionLinesDAO_HI.update(requisitionLines);
            result.add(requisitionLines);
        }

       /* //若全部行已转订单，改头状态为已转采购订单
        List<SrmPoRequisitionLinesEntity_HI> lines=srmPoRequisitionLinesDAO_HI.findByProperty("poHeaderId",requisitionHeadersEntity.getRequisitionHeaderId());
        if(!ObjectUtils.isEmpty(lines)){
            boolean flag=true;
            for(SrmPoRequisitionLinesEntity_HI ro:lines){
                if("3".equals(ro.getHandleWay())){
                    flag=false;
                }
            }
            if(flag){
                requisitionHeadersEntity.setRequisitionStatus("PURCHASE_ORDER_TRANS");
                requisitionHeadersEntity.setOperatorUserId(operatorUserId);
                requisitionHeadersEntity.setSourceCode("PO");
                srmPoRequisitionHeadersDAO_HI.update(requisitionHeadersEntity);
            }
        }*/
        JSONObject obj = new JSONObject();
        obj.put("header", requisitionHeadersEntity);
        obj.put("lines", result);
        return SToolUtils.convertResultJSONObj("S", "转订单成功", obj.size(), obj);
    }

    /**
     * Description：采购申请查询页按选择的行转换成采购订单
     *
     * @param jsonParam 采购申请参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-01     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveReqTransferOrder(JSONObject jsonParam) throws Exception {
        LOGGER.info("saveReqTransferOrder:" + jsonParam.toJSONString());
        JSONObject flag = null;
        JSONArray linesArray = jsonParam.getJSONArray("arr");
        if (null == linesArray || linesArray.isEmpty()) {
            SToolUtils.convertResultJSONObj(ERROR_STATUS, "暂无数据", 0, null);
        }

        List<SrmPoRequisitionLinesEntity_HI_RO> lineRoList = new ArrayList<>();
        for (int i = 0; i < linesArray.size(); i++) {
            JSONObject object = linesArray.getJSONObject(i);
            if (object.getInteger("requisitionHeaderId") != null && object.getInteger("requisitionHeaderId") > 0) {
                SrmPoRequisitionHeadersEntity_HI srmPoRequisitionHeadersEntity = srmPoRequisitionHeadersDAO_HI.getById(object.getInteger("requisitionHeaderId"));
                if (srmPoRequisitionHeadersEntity != null && !"APPROVED".equals(srmPoRequisitionHeadersEntity.getRequisitionStatus())) {
                    return SToolUtils.convertResultJSONObj("E", "采购申请" + srmPoRequisitionHeadersEntity.getRequisitionNumber() + "不存在或状态非已批准！", 0, null);
                } else if (ObjectUtils.isEmpty(srmPoRequisitionHeadersEntity.getOrganizationId())) {
                    return SToolUtils.convertResultJSONObj("E", "采购申请" + srmPoRequisitionHeadersEntity.getRequisitionNumber() + "库存组织不能为空", 0, null);
                } else if (ObjectUtils.isEmpty(srmPoRequisitionHeadersEntity.getOrgId())) {
                    return SToolUtils.convertResultJSONObj("E", "采购申请" + srmPoRequisitionHeadersEntity.getRequisitionNumber() + "业务实体不能为空", 0, null);
                } else if (ObjectUtils.isEmpty(srmPoRequisitionHeadersEntity.getRequisitionType())) {
                    return SToolUtils.convertResultJSONObj("E", "采购申请" + srmPoRequisitionHeadersEntity.getRequisitionNumber() + "类型不能为空", 0, null);
                }
                if (object.getInteger("requisitionLineId") != null && object.getInteger("requisitionLineId") > 0) {
                    SrmPoRequisitionLinesEntity_HI srmPoRequisitionLinesEntity = srmPoRequisitionLinesDAO_HI.getById(object.getInteger("requisitionLineId"));
                    if (srmPoRequisitionLinesEntity != null && srmPoRequisitionLinesEntity.getPoHeaderId() != null && !"".equals(srmPoRequisitionLinesEntity.getPoHeaderId())) {
                        return SToolUtils.convertResultJSONObj("E", "采购申请行已转换采购订单或已不存在！", 0, null);
                    } else if (srmPoRequisitionLinesEntity.getSupplierId() == null || "".equals(srmPoRequisitionLinesEntity.getSupplierId()) || ObjectUtils.isEmpty(object.getInteger("supplierId"))) {
                        return SToolUtils.convertResultJSONObj("E", "采购申请" + srmPoRequisitionHeadersEntity.getRequisitionNumber() + "行未选择供应商，不予以转订单", 0, null);
                    } else {
                        SrmPoRequisitionLinesEntity_HI_RO roT = new SrmPoRequisitionLinesEntity_HI_RO();
                        BeanUtils.copyProperties(srmPoRequisitionLinesEntity, roT);
                        roT.setOrganizationId(srmPoRequisitionHeadersEntity.getOrganizationId());
                        roT.setOrgId(srmPoRequisitionHeadersEntity.getOrgId());
                        roT.setRequisitionType(srmPoRequisitionHeadersEntity.getRequisitionType());
                        roT.setRequisitionEmpId(srmPoRequisitionHeadersEntity.getRequisitionEmpId());
                        roT.setRequisitionNumber(srmPoRequisitionHeadersEntity.getRequisitionNumber());
                        roT.setSupplierId(object.getInteger("supplierId"));
                        lineRoList.add(roT);
                    }
                }
            }
        }


        //业务实体、库存组织、类型、供应商均一致则合并成一张订单，否则，生成多张订单
        //相同数据放入一个List中

        JSONObject obj = new JSONObject();
        List<SrmPoRequisitionLinesEntity_HI> po = new ArrayList<>();
        while (lineRoList.size() > 0 && null != lineRoList) {
            List<SrmPoRequisitionLinesEntity_HI_RO> poList = new ArrayList<>();
            //for (int i = 0; i < lineRoList.size(); i++) {
            SrmPoRequisitionLinesEntity_HI_RO headerB = lineRoList.get(0);
            poList.add(headerB);
            for (int n = 1; n < lineRoList.size(); n++) {
                SrmPoRequisitionLinesEntity_HI_RO headerT = lineRoList.get(n);
                if (headerB.getOrganizationId().equals(headerT.getOrganizationId()) && headerB.getOrgId().equals(headerT.getOrgId()) && headerB.getRequisitionType().equals(headerT.getRequisitionType()) && headerB.getSupplierId().equals(headerT.getSupplierId())) {
                    poList.add(headerT);
                }
            }
            if (!ObjectUtils.isEmpty(poList)) {
                //生成订单头
                SrmPoHeadersEntity_HI srmPoHeadersEntity_HI = new SrmPoHeadersEntity_HI();
                SrmPoRequisitionLinesEntity_HI_RO poHeader = poList.get(0);
                //取采购订单号
                String requisitionHeaderId = String.valueOf(poList.stream().map(SrmPoRequisitionLinesEntity_HI_RO::getRequisitionHeaderId).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.toList()).stream().distinct().collect(Collectors.joining(",")));
                String requisitionNumber = String.valueOf(poList.stream().map(SrmPoRequisitionLinesEntity_HI_RO::getRequisitionNumber).collect(Collectors.toList()).stream().distinct().collect(Collectors.joining(",")));
                String poHeadersNumber = null;
                Date date = new Date(System.currentTimeMillis());
                String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
                poHeadersNumber = saafSequencesUtil.getDocSequences("srm_po_headers", "PO-", dateFromate, 4);
                srmPoHeadersEntity_HI.setPoNumber(poHeadersNumber);
                srmPoHeadersEntity_HI.setBillToOrganizationId(poHeader.getOrganizationId());
                srmPoHeadersEntity_HI.setShipToOrganizationId(poHeader.getOrganizationId());
                srmPoHeadersEntity_HI.setOrganizationId(poHeader.getOrganizationId());
                srmPoHeadersEntity_HI.setOrgId(poHeader.getOrgId());
                srmPoHeadersEntity_HI.setPoDocType("ORDER");
                srmPoHeadersEntity_HI.setCurrencyCode("CNY");
                srmPoHeadersEntity_HI.setStatus("DRAFT");
                srmPoHeadersEntity_HI.setSettlementWay("TRANSFER");
                srmPoHeadersEntity_HI.setPoType(poHeader.getRequisitionType());
                srmPoHeadersEntity_HI.setPoVersions(new BigDecimal("0"));
                srmPoHeadersEntity_HI.setBuyerId(poHeader.getRequisitionEmpId());
                //srmPoHeadersEntity_HI.setSourceId(String.valueOf(poHeader.getRequisitionHeaderId()));
                srmPoHeadersEntity_HI.setSourceId(requisitionHeaderId);
                srmPoHeadersEntity_HI.setSourceCode("PR");
                srmPoHeadersEntity_HI.setPrNumber(requisitionNumber);
                srmPoHeadersEntity_HI.setSupplierId(poHeader.getSupplierId());
                srmPoHeadersEntity_HI.setOperatorUserId(jsonParam.getInteger("operatorUserId"));
                srmPoHeadersDAO_HI.save(srmPoHeadersEntity_HI);
                srmPoHeadersDAO_HI.fluch();
                //生成订单行
                for (SrmPoRequisitionLinesEntity_HI_RO line : poList) {
                    SrmPoLinesEntity_HI srmPoLinesEntity_HI = null;
                    srmPoLinesEntity_HI = new SrmPoLinesEntity_HI();
                    srmPoLinesEntity_HI.setLineNumber(line.getLineNumber());
                    srmPoLinesEntity_HI.setItemName(line.getItemName());
                    srmPoLinesEntity_HI.setItemSpec(line.getItemSpec());
                    srmPoLinesEntity_HI.setStatus("DRAFT");
                    srmPoLinesEntity_HI.setOnWayQty(new BigDecimal("0"));
                    srmPoLinesEntity_HI.setReceivedQty(new BigDecimal("0"));
                    srmPoLinesEntity_HI.setReturnQty(new BigDecimal("0"));
                    srmPoLinesEntity_HI.setOriginalDemandQty(null);
                    srmPoLinesEntity_HI.setOriginalDemandDate(null);
                    srmPoLinesEntity_HI.setFeedbackAdjustDate(null);
                    srmPoLinesEntity_HI.setFeedbackAdjustQty(null);
                    srmPoLinesEntity_HI.setFeedbackStatus("NON_FEEDBACK");
                    srmPoLinesEntity_HI.setFeedbackResult("CONFIRM");
                    srmPoLinesEntity_HI.setRejectReason(null);
                    srmPoLinesEntity_HI.setMayNoticeQty((line.getDemandQty() == null ? new BigDecimal(0) : line.getDemandQty()).subtract(srmPoLinesEntity_HI.getOnWayQty()));
                    srmPoLinesEntity_HI.setPoHeaderId(srmPoHeadersEntity_HI.getPoHeaderId());
                    srmPoLinesEntity_HI.setItemId(line.getItemId());
                    srmPoLinesEntity_HI.setCategoryId(line.getCategoryId());
                    srmPoLinesEntity_HI.setDemandQty(line.getDemandQty());
                    srmPoLinesEntity_HI.setMinPoQty(line.getMinPacking());
                    srmPoLinesEntity_HI.setDemandDate(line.getDemandDate());
                    srmPoLinesEntity_HI.setDescription(line.getLineComments());
                    srmPoLinesEntity_HI.setOperatorUserId(jsonParam.getInteger("operatorUserId"));
                    srmPoLinesEntity_HI.setSourceId(String.valueOf(line.getRequisitionLineId()));
                    srmPoLinesEntity_HI.setSourceCode("PR");
                    srmPoLinesEntity_HI.setContext("项目采购");
                    //取价格库税率
                    JSONObject jsonParams = new JSONObject();
                    jsonParams.put("varUserId", jsonParam.getInteger("operatorUserId"));
                    jsonParams.put("supplierId", line.getSupplierId());
                    jsonParams.put("organizationId", srmPoHeadersEntity_HI.getOrganizationId());
                    jsonParams.put("itemId", line.getItemId());
                    jsonParams.put("calculateByLastestEabledPriceFlag", 'Y');
                    Pagination<SrmPoHeadersEntity_HI_RO> priceList = iSrmPoFrameworkAgreement.findAgreementPriceLibraryList(jsonParams, 1, 1000);
                    if (!ObjectUtils.isEmpty(priceList.getData())) {
                        srmPoLinesEntity_HI.setTaxRateCode(priceList.getData().get(0).getTaxRateCode());
                        srmPoLinesEntity_HI.setTaxPrice(priceList.getData().get(0).getTaxPrice());
                        srmPoLinesEntity_HI.setNonTaxPrice(priceList.getData().get(0).getNonTaxPrice());
                    }

                    srmPoLinesDAO_HI.saveOrUpdate(srmPoLinesEntity_HI);
                    srmPoLinesDAO_HI.fluch();
                    //回写行
                    SrmPoRequisitionLinesEntity_HI lineEntity = srmPoRequisitionLinesDAO_HI.getById(line.getRequisitionLineId());
                    lineEntity.setHandleWay("3");
                    lineEntity.setSourceId(String.valueOf(srmPoLinesEntity_HI.getPoLineId()));
                    lineEntity.setSourceCode("PO");
                    lineEntity.setSourceNumber(srmPoHeadersEntity_HI.getPoNumber());
                    lineEntity.setPoHeaderId(srmPoLinesEntity_HI.getPoHeaderId());
                    lineEntity.setSupplierId(line.getSupplierId());
                    srmPoRequisitionLinesDAO_HI.update(lineEntity);
                    srmPoRequisitionLinesDAO_HI.fluch();
                    po.add(lineEntity);
                }
                //从lineRoList中删除已生成采购订单的行
                System.out.println(poList);
                lineRoList.removeAll(poList);
                //lineRoList = lineRoList.stream().filter(item -> !poList.contains(item)).collect(toList());
                System.out.println(lineRoList);
            }


            //}
        }
        obj.put("lines", po);
        return SToolUtils.convertResultJSONObj("S", "转订单成功", obj.size(), obj);
    }

}
