package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.sie.common.utils.StringUtil;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.dao.SaafLookupValuesDAO_HI;
import saaf.common.fmw.base.model.entities.*;
import saaf.common.fmw.base.model.entities.readonly.*;
import saaf.common.fmw.base.model.inter.*;
import saaf.common.fmw.base.utils.ESBClientUtils;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.base.utils.enums.ESBParams;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.message.email.utils.SendMailUtil;
import saaf.common.fmw.okc.model.entities.SrmOkcContractItemsEntity_HI;
import saaf.common.fmw.okc.model.entities.SrmOkcContractsEntity_HI;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractsOrderEntity_HI_RO;
import saaf.common.fmw.po.model.dao.SrmPoLinesCombDAO_HI;
import saaf.common.fmw.po.model.entities.*;
import saaf.common.fmw.po.model.entities.readonly.*;
import saaf.common.fmw.po.model.inter.IPurchaseOrder;
import saaf.common.fmw.pon.model.entities.*;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionItemsEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionJuriesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierContactsEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierSitesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierInfoEntity_HI_RO;
import saaf.common.fmw.utils.SrmUtils;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static saaf.common.fmw.services.CommonAbstractServices.*;

/**
 * Project Name：purchaseOrderServer
 * Company Name：SIE
 * Program Name：
 * Description：采购订单
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */

@Component("purchaseOrderServer")
public class PurchaseOrderServer implements IPurchaseOrder {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseOrderServer.class);

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private BaseViewObject<SrmPoHeadersEntity_HI_RO> SrmPoHeadersDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPoRequisitionLinesEntity_HI_RO> SrmPoRequisitionLinesDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPoLinesEntity_HI_RO> srmPoLinesDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPoHeadersEntity_HI> SrmPoHeadersDAO_HI;

    @Autowired
    private ViewObject<SrmPoLinesEntity_HI> SrmPoLinesDAO_HI;

    @Autowired
    private ViewObject<SrmPoChangeInfoEntity_HI> SrmPoChangeInfoDAO_HI;

    @Autowired
    private ViewObject<SrmPoApprovedListEntity_HI> SrmPoApprovedListDAO_HI;

    @Autowired
    private ViewObject<SaafEmployeesEntity_HI> SaafEmployeesDAO_HI;

    @Autowired
    private BaseViewObject<SrmBaseItemsEntity_HI_RO> itemsDao_HI_RO;

    @Autowired
    private ViewObject<SrmBaseItemsEntity_HI> srmBaseItemsEntity_HI;

    @Autowired
    private BaseViewObject<SaafInstitutionEntity_HI_RO> Institution_HI_RO;

    @Autowired
    private ViewObject<SaafInstitutionEntity_HI> saafInstitutionDAO_HI;

    @Autowired
    private ViewObject<SrmBaseCategoriesEntity_HI> srmBaseCategoriesDAO_HI;

    @Autowired
    private ViewObject<SrmPonAuctionHeadersEntity_HI> srmPonAuctionHeadersDAO_HI;

    @Autowired
    private ViewObject<SrmPonAuctionItemsEntity_HI> srmPonAuctionItemsDAO_HI;
    @Autowired
    private ViewObject<SrmPonBidItemPricesEntity_HI> srmPonBidItemPricesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonAuctionItemsEntity_HI_RO> srmPonAuctionItemsDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPonAuctionJuriesEntity_HI_RO> srmPonAuctionJuriesDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmOkcContractsOrderEntity_HI_RO> srmOkcContractOrderEntityDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPonAuctionItemLaddersEntity_HI> srmPonAuctionItemLaddersDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    @Autowired
    private ViewObject<SrmIntfLogsEntity_HI> srmIntfLogsDAO_HI;//日志

    @Autowired
    private ViewObject<SrmPoAgreementAssignsEntity_HI> srmPoAgreementAssignsDAO_HI;

    @Autowired
    private ViewObject<SrmBaseParamsEntity_HI> srmBaseParamsDAO_HI;

    @Autowired
    private SaafLookupValuesDAO_HI saafLookupValuesDAO_hi;

    @Autowired
    private SrmPoLinesServer srmPoLinesServer;

    @Autowired
    private SrmPoHeaderArchivesServer srmPoHeaderArchivesServer;

    @Autowired
    private ISaafBaseOperlog iSaafBaseOperlog;

    @Autowired
    private BaseViewObject<SrmPosSupplierInfoEntity_HI_RO> srmPosSupplierInfoDAO_HI_RO;
    @Autowired
    private BaseViewObject<SrmPosSupplierSitesEntity_HI> srmPosSupplierSitesDao_HI;

    @Autowired
    private ViewObject<SrmPoLinesEntity_HI> srmPoLinesDAO_HI;
    @Autowired
    private ViewObject<SrmPoLineArchivesEntity_HI> srmPoLineArchivesDAO_HI;

    @Autowired
    private ViewObject<SrmPoLinesCombEntity_HI> srmPoLinesCombDAO_HI;

    @Autowired
    private BaseViewObject<SrmPoLinesCombEntity_HI_RO> srmPoLinesCombDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPoTechnicalNumEntity_HI> srmPoTechnicalNumDAO_HI;

    @Autowired
    private ViewObject<SrmPoSubprojectNumEntity_HI> srmPoSubprojectNumDAO_HI;

    @Autowired
    private BaseViewObject<SrmBaseLocationsEntity_HI_RO> srmBaseLocationsDAO_HI_RO;

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;
    @Autowired
    private ViewObject<SrmPosSupplierContactsEntity_HI> srmPosSupplierContactsDAO_HI;
    private static SendMailUtil sendMailUtil = new SendMailUtil(true);
    @Autowired
    private ISrmBaseNotifications iSrmBaseNotifications;//系统通知

    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;  //用户DAO
    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;
    private ESBClientUtils esbClientUtils = new ESBClientUtils();
    @Autowired
    private ISaafLookup baseSaafLookupServer;
    private static String TABLENAME = "srm_base_items_b";
    @Autowired
    private ViewObject<SrmBaseItemsBEntity_HI> srmBaseItemsBDAO_HI;

    @Autowired
    private ViewObject<SrmBaseItemsEntity_HI> srmBaseItemDAO_HI;

    @Autowired
    private ViewObject<SaafEmployeesEntity_HI> saafEmployeesDAO_HI;

    @Autowired
    private ISrmBaseItemsB iSrmBaseItemsB;

    @Autowired
    private ViewObject<SrmPonBidHeadersEntity_HI> srmPonBidHeadersDAO_HI;

    @Autowired
    private ViewObject<SrmOkcContractItemsEntity_HI> srmOkcContractItemsDAO_HI;

    @Autowired
    private ViewObject<SrmOkcContractsEntity_HI> srmOkcContractsDAO_HI;

    /**
     * Description：查询采购订单
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
    public Pagination<SrmPoHeadersEntity_HI_RO> findOrderInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer queryString = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_ORDER_LIST_SQL);
        //跳转
        JSONArray poHeaderIds = jsonParams.getJSONArray("poHeaderIds");
        StringBuffer buff = new StringBuffer();
        if (null != poHeaderIds) {
            buff.append("(");
            for (int i = 0; i < poHeaderIds.size(); i++) {
                Integer poHeaderId = poHeaderIds.getInteger(i);
                buff.append(poHeaderId);
                buff.append(",");
            }
            buff.deleteCharAt(buff.length() - 1);
            buff.append(")");

            if (!"".equals(buff.toString())) {
                queryString.append(" AND sph.po_header_id IN " + buff.toString());
            }
        }
        JSONArray poLineIds = jsonParams.getJSONArray("poLineIds");
        StringBuffer lineBuff = new StringBuffer();
        if (null != poLineIds) {
            lineBuff.append("(");
            for (int i = 0; i < poLineIds.size(); i++) {
                Integer poLineId = poLineIds.getInteger(i);
                lineBuff.append(poLineId);
                lineBuff.append(",");
            }
            lineBuff.deleteCharAt(lineBuff.length() - 1);
            lineBuff.append(")");

            if (!"".equals(lineBuff.toString())) {
                queryString.append(" AND spl.po_line_id IN " + lineBuff.toString());
            }
        }

        SaafToolUtils.parperParam(jsonParams, "sph.po_number", "poNumber", queryString, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "sph.org_id", "orgId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sph.bill_to_organization_id", "billToOrganizationId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spl.receive_to_organization_id", "receiveToOrganizationId", queryString, queryParamMap, "=");
        String creationDateFrom = jsonParams.getString("creationDateFrom");
        if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
            queryString.append(" AND trunc(spl.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
            queryParamMap.put("creationDateFrom", creationDateFrom);
        }
        String creationDateTo = jsonParams.getString("creationDateTo");
        if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
            queryString.append(" AND trunc(spl.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
            queryParamMap.put("creationDateTo", creationDateTo);
        }
        SaafToolUtils.parperParam(jsonParams, "sph.buyer_id", "buyerId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spl.category_id", "categoryId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spl.item_id", "itemId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sph.return_goods_type", "returnGoodsType", queryString, queryParamMap, "=");
        String demandDateFrom = jsonParams.getString("demandDateFrom");
        if (demandDateFrom != null && !"".equals(demandDateFrom.trim())) {
            queryString.append(" AND spl.demand_date >= to_date(:demandDateFrom, 'yyyy-mm-dd')\n");
            queryParamMap.put("demandDateFrom", demandDateFrom);
        }
        String demandDateTo = jsonParams.getString("demandDateTo");
        if (demandDateTo != null && !"".equals(demandDateTo.trim())) {
            queryString.append(" AND spl.demand_date <= to_date(:demandDateTo, 'yyyy-mm-dd')\n");
            queryParamMap.put("demandDateTo", demandDateTo);
        }
        SaafToolUtils.parperParam(jsonParams, "sph.status", "status", queryString, queryParamMap, "IN");
        SaafToolUtils.parperParam(jsonParams, "spl.status", "lineStatus", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sph.description", "description", queryString, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "spl.description", "lineDescription", queryString, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, "spl.feedback_status ", "feedbackStatus", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spl.feedback_result", "feedbackResult", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sph.supplier_id", "supplierId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sph.erp_po_number", "erpPoNumber", queryString, queryParamMap, "=");

        //根据合同行ID查询采购订单
        if (!ObjectUtils.isEmpty(jsonParams.getInteger("contractItemId"))) {
            List<SrmPoLinesEntity_HI> poLine = srmPoLinesDAO_HI.findByProperty("contractItemId", jsonParams.getInteger("contractItemId"));
            if (!ObjectUtils.isEmpty(poLine)) {
                String poHeaderIdByContract = String.valueOf(poLine.stream().map(SrmPoLinesEntity_HI::getPoHeaderId).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.toList()).stream().distinct().collect(Collectors.joining(",")));
                queryString.append(" and sph.po_header_id in (" + poHeaderIdByContract + ") ");
            } else {
                queryString.append(" and 1=2");
            }
        }

        if (!"EX".equals(jsonParams.getString("varPlatformCode"))) {
            queryString.append(" AND (check_org_f(" + jsonParams.getInteger("varUserId") + ", sph.org_id) = 'Y' or sph.created_by =" + jsonParams.getInteger("varUserId") + ") ");
        }

        String countSql = "select count(1) from (" + queryString + ")";

        queryString.append(" ORDER BY sph.po_header_id desc, spl.po_line_id asc, spl.line_number DESC");
        Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(), countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：查询采购订单头
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
    public Pagination<SrmPoHeadersEntity_HI_RO> findOrderHeader(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_ORDER_HEADER_SQL);
        SaafToolUtils.parperParam(jsonParams, "sph.supplier_id", "supplier_id", queryString, queryParamMap, "="); // 供应商查询
        SaafToolUtils.parperParam(jsonParams, "sph.po_header_id", "poHeaderId", queryString, queryParamMap, "=");
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(), countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：查询采购订单头-打印
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
    public Pagination<SrmPoHeadersEntity_HI_RO> findOrderHeaderPrint(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_PRINT_ORDER_HEADER_SQL);
        SaafToolUtils.parperParam(jsonParams, "a.po_header_id", "poHeaderId", queryString, queryParamMap, "=");
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(), countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：查询采购订单行
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
    public Pagination<SrmPoHeadersEntity_HI_RO> findOrderLine(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_ORDER_LINE_SQL);
        SaafToolUtils.parperParam(jsonParams, "spl.po_header_id", "poHeaderId", queryString, queryParamMap, "=");
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" ORDER BY spl.po_line_id");
        Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(), countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：查询组织下拉框
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public List findInstitutionList(JSONObject jsonParams) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_INSITITUTION_LIST_SQL);
        SaafToolUtils.parperParam(jsonParams, "si.order_inst_flag", "orderInstFlag", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "si.delivery_inst_flag", "deliveryInstFlag", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "si.outsource_flag", "outsourceFlag", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "si.inst_code", "instCode", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "si.inst_name", "instName", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "si.inst_type", "instType", queryString, queryParamMap, "=");
        List<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        return result;
    }

    /**
     * Description：查询采购订单lov
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
    public Pagination<SrmPoHeadersEntity_HI_RO> findOrderLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_ORDER_LOV_SQL);
        SaafToolUtils.parperParam(jsonParams, "sph.po_number", "poNumber", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sph.supplier_id", "supplier_id", queryString, queryParamMap, "="); // 供应商查询
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(), countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：匹配采购订单
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public List<SrmPoHeadersEntity_HI_RO> findPoLineOrder(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_ORDER_JOIN_SQL);
        SaafToolUtils.parperParam(jsonParams, "h.supplier_id", "supplierId", queryString, queryParamMap, "="); // 供应商查询
        if (jsonParams.getInteger("supplierId") == null || jsonParams.getInteger("documentType") == null
                || jsonParams.getInteger("instId") == null || jsonParams.getInteger("itemId") == null) {
            return null;
        }
        SaafToolUtils.parperParam(jsonParams, "h.PO_DOC_TYPE", "documentType", queryString, queryParamMap, "="); // 供应商查询
        SaafToolUtils.parperParam(jsonParams, "i.inst_id", "instId", queryString, queryParamMap, "="); // 组织
        SaafToolUtils.parperParam(jsonParams, "l.item_id", "itemId", queryString, queryParamMap, "="); // 组织
        String spectalUseNum = jsonParams.getString("specialUseNum");
        if (null == spectalUseNum || "".equals(spectalUseNum.trim())) {
            queryString.append(" AND ISNULL(l.SPECIAL_USE_NUM)");
        } else {
            queryString.append(" AND l.SPECIAL_USE_NUM = :specialUseNum");
            queryParamMap.put("specialUseNum", spectalUseNum);
        }
        SaafToolUtils.parperParam(jsonParams, "l.SPECIAL_USE_NUM", "specialUseNum", queryString, queryParamMap, "="); // 番号
        queryString.append(" ORDER BY h.CREATION_DATE");
        List<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        return result;
    }

    /**
     * Description：查询供应商lov
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
    public Pagination<SrmPoHeadersEntity_HI_RO> findSupplierLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_SUPPLIER_LOV_SQL);
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_name", "supplierName", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_number", "supplierNumber", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_id", "supplier_id", queryString, queryParamMap, "="); // 供应商查询
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_id", "supplierId", queryString, queryParamMap, "=");
        // 可采购
        SaafToolUtils.parperParam(jsonParams, "spsi.purchase_flag", "purchaseFlag", queryString, queryParamMap, "=");
        // 状态
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_status", "supplierStatus", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spsi.srm_delivery", "srmDelivery", queryString, queryParamMap, "=");
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(), countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：查询物料类别lov
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
    public Pagination<SrmPoHeadersEntity_HI_RO> findCategoryLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_CATEGORY_LOV_SQL);
        SaafToolUtils.parperParam(jsonParams, "sbc.category_level", "categoryLevel", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sbc.category_id", "categoryId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sbc.category_code", "categoryCode", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbc.category_name", "categoryName", queryString, queryParamMap, "like"); // 供应商查询
        SaafToolUtils.parperParam(jsonParams, "sbc.full_category_code", "fullCategoryCode", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbc.full_category_name", "fullCategoryName", queryString, queryParamMap, "like");
        queryString.append(" AND sbc.Category_Id IN (" + getCategoryId(jsonParams.getInteger("varUserId")) + ") and sbc.LEAF_FLAG ='Y' ");
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(), countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：查询物料lov
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
    public Pagination<SrmPoHeadersEntity_HI_RO> findItemLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_ITEM_LOV_SQL);
        SaafToolUtils.parperParam(jsonParams, "sbi.item_id", "itemId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_code", "itemCode", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_status", "itemStatus", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_name", "itemName", queryString, queryParamMap, "like"); // 供应商查询
        SaafToolUtils.parperParam(jsonParams, "sbc.category_name", "categoryName", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbc.category_code", "categoryCode", queryString, queryParamMap, "like");    //分类编码
        SaafToolUtils.parperParam(jsonParams, "sbc.full_category_name", "fullCategoryName", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbc.full_category_code", "fullCategoryCode", queryString, queryParamMap, "like");    //分类全编码
        if (null != jsonParams.getString("categoryId") && !"".equals(jsonParams.getString("categoryId"))) {
            SaafToolUtils.parperParam(jsonParams, "sbc.category_id", "categoryId", queryString, queryParamMap, "=");
        }
        if ("Y".equals(jsonParams.getString("masterOrg"))) {
            queryString.append(" AND si.master_organization_flag = 'Y' ");
        }
        if (null != jsonParams.getString("InstId") && !"".equals(jsonParams.getString("InstId") != null)) {
            queryString.append(" AND si.inst_id = '" + jsonParams.getInteger("InstId") + "'");
        }
        /*queryString.append(" and sbi.item_id in (SELECT Bi.Item_Id\n" +
                "  FROM Srm_Base_Items Bi\n" +
                " WHERE Bi.Organization_Id ="+jsonParams.getInteger("organizationId")+
                "      AND Bi.Category_Id IN (" +getCategoryId(jsonParams.getInteger("varUserId"))+"))");*/

        queryString.append(" and sbi.Organization_Id =" + jsonParams.getInteger("organizationId") +
                "      AND sbi.Category_Id IN (" + getCategoryId(jsonParams.getInteger("varUserId")) + ")");
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(), countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：查询用户品负责类ID
     *
     * @param userId 用户ID
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private String getCategoryId(Integer userId) {
        JSONObject json = new JSONObject();
        json.put("userId", userId);
        List<SrmBaseUserCategoriesEntity_HI_RO> userCategoriesList = srmBaseUserCategories.findUserCategories(json);
        List categoryIds = new ArrayList();
        for (SrmBaseUserCategoriesEntity_HI_RO ro : userCategoriesList) {
            categoryIds.add(ro.getCategoryId().toString());
        }
        String categoryId = String.valueOf(categoryIds.stream().distinct().collect(Collectors.joining(",")));
        return categoryId;
    }

    /**
     * Description：查询供应商能供的物料lov
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
    public Pagination<SrmPoHeadersEntity_HI_RO> findSupplierItemLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        StringBuffer appendString = new StringBuffer();
        StringBuffer countString = new StringBuffer();
        queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_SUPPLIER_ITEM_LOV_SQL);
        countString.append(SrmPoHeadersEntity_HI_RO.QUERY_SUPPLIER_ITEM_LOV_COUNT);
        SaafToolUtils.parperParam(jsonParams, "sbi.item_id", "itemId", appendString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_code", "itemCode", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_status", "itemStatus", appendString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_name", "itemName", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "spal.supplier_id", "supplier_id", appendString, queryParamMap, "="); // 供应商查询
        SaafToolUtils.parperParam(jsonParams, "spal.supplier_id", "supplierId", appendString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_name", "supplierName", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_number", "supplierNumber", appendString, queryParamMap, "like");
        queryString.append(appendString);
        countString.append(appendString);
        Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(), countString, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：保存订单信息
     * <p>
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * shipLocationCode  收货方  VARCHAR2  N
     * locationCode  收单方  VARCHAR2  N
     * supPoFileId  供应商附件ID  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * agreementClause  协议条款  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * supplierReadTime  供应商查看日期
     * DATE  N
     * exchangeRate  汇率  NUMBER  N
     * exchangeRateDate  汇率日期  DATE  N
     * syncErpFlag  是否同步到erp  VARCHAR2  N
     * syncErpMsg  同步到erp异常信息  VARCHAR2  N
     * isGlobal  是否全局  VARCHAR2  N
     * orderType  订单类型  VARCHAR2  N
     * erpOrderNo  erp订单编号  VARCHAR2  N
     * bazaarOrderNo  市场订单  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * buyerExecuteId  采购履行专员  NUMBER  N
     * organizationId  库存组织  NUMBER  N
     * prNumber  采购申请单号  VARCHAR2  N
     * locationCode  收货地点  VARCHAR2  N
     * poType  类型  VARCHAR2  N
     * shipToOrganizationId  收货组织  NUMBER  N
     * shipLocationCode    VARCHAR2  N
     * shipToLocationCode  收货方  VARCHAR2  N
     * billToLocationCode  收单方  VARCHAR2  N
     * shipToLocationId  收货方  NUMBER  N
     * billToLocationId  收单方  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * isGlobal  是否全局  VARCHAR2  N
     * agreementClause  协议条款  VARCHAR2  N
     * contractId  合同ID，关联表：srm_okc_contracts  NUMBER  N
     * contractCode  合同编号  VARCHAR2  N
     * contractTemplateId  合同模板ID，关联表：srm_okc_contract_templates  NUMBER  N
     * contractFileId  合同文档ID  NUMBER  N
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

    public JSONObject saveOrderInfo(JSONObject jsonParams) throws Exception {
        JSONObject json = new JSONObject();
        int userId = jsonParams.getInteger("varUserId");
        SrmPoHeadersEntity_HI po = null;
        String code = "";
        String status = jsonParams.getString("action");
        String ableCheckOrderFlag = jsonParams.getString("ableCheckOrderFlag");
        String ex = "";
        if ("85".equals(jsonParams.getString("supplyInstId"))) { // 奥马
            ex = "Homa_";
        } else if ("90".equals(jsonParams.getString("supplyInstId"))) { // 管道厂
            ex = "301_";
        } else if ("91".equals(jsonParams.getString("supplyInstId"))) { // 注塑厂
            ex = "302_";
        }
        if ("03".equals(jsonParams.getString("poDocType"))) { // POCS（五金材料）
            code = "POCS";
        } else if ("01".equals(jsonParams.getString("poDocType"))) { // POAS（生产材料）
            code = "POAS";
        } else if ("02".equals(jsonParams.getString("poDocType"))) { // PODS（委外采购）
            code = "PODS";
        }
        if (null == jsonParams.getInteger("supplierId")) {
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "供应商必填", 0, null);
        }
        if (null == jsonParams.getInteger("poHeaderId")) {
            po = new SrmPoHeadersEntity_HI();
            po.setStatus("NEW");
            po.setPoNumber(saafSequencesUtil.getDocSequences("srm_po_headers".toUpperCase(),
                    ex + code + DateUtil.date2Str(new Date(), "yyMMdd"), 4));
            po.setCreationDate(new Date());
            po.setCreatedBy(userId);
        } else {
            po = SrmPoHeadersDAO_HI.getById(jsonParams.getInteger("poHeaderId"));
            if (null == po) { // 单号不存在
                throw new UtilsException("单号不存在");
            }
        }
        po.setSupplierId(jsonParams.getInteger("supplierId"));
        po.setCurrencyCode(jsonParams.getString("currencyCode"));
        po.setDescription(jsonParams.getString("description"));
        po.setBuyerId(jsonParams.getInteger("buyerId"));
        po.setPoDocType(jsonParams.getString("poDocType"));
        po.setDescription(jsonParams.getString("description"));
        if (("NEW".equals(po.getStatus()) || "REJECT".equals(po.getStatus())) && "APPROVING".equals(status)) {
            po.setStatus("APPROVING");
        }
        po.setLastUpdateDate(new Date());
        po.setLastUpdatedBy(userId);
        po.setLastUpdateLogin(userId);
        po.setOperatorUserId(userId);
        SrmPoHeadersDAO_HI.save(po);
        json.put("poHeaderId", po.getPoHeaderId());
        if (null != jsonParams.get("lineData")) {
            saveLineInfo(jsonParams.getJSONArray("lineData"), userId, po.getPoHeaderId(), po.getSupplierId(),
                    jsonParams.getString("acctCheckType"), status, ableCheckOrderFlag);
        }
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, json);
    }

    /**
     * Description：保存订单行
     *
     * @param lineData
     * @param userId
     * @param poHeaderId
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    JSONObject saveLineInfo(JSONArray lineData, int userId, int poHeaderId, int supplierId, String acctCheckType, String status, String ableCheckOrderFlag) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject paramData = null;
        // 查找该订单头的最大行号
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_MAX_LINE_NUMBER_SQL);
        queryParamMap.put("poHeaderId", poHeaderId);
        List<SrmPoHeadersEntity_HI_RO> tempList = SrmPoHeadersDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        if (null == tempList || tempList.isEmpty()) {
            throw new UtilsException("查无数据，订单已被删除");
        }
        int count = 0;
        int lineNumber = 0;
        if (null == tempList.get(0).getLineNumber()) {
            lineNumber = 10;
        } else {
            lineNumber = tempList.get(0).getLineNumber() + 10;
        }
        for (int i = 0; i < lineData.size(); i++) {
            paramData = lineData.getJSONObject(i);
            SrmPoLinesEntity_HI row = null;
            if (null == paramData.getInteger("poLineId")) {
                row = new SrmPoLinesEntity_HI();
                row.setPoHeaderId(poHeaderId);
                row.setLineNumber(lineNumber + count * 10);
                row.setStatus("NEW");
                row.setCreationDate(new Date());
                row.setCreatedBy(userId);
                count++;
            } else {
                row = SrmPoLinesDAO_HI.getById(paramData.getInteger("poLineId"));
            }
            row.setItemId(paramData.getInteger("itemId"));
            row.setDemandQty(paramData.getBigDecimal("demandQty"));
            row.setDemandDate(paramData.getDate("demandDate"));
            row.setDescription(paramData.getString("lineDescription"));
            if (("NEW".equals(row.getStatus()) || "REJECT".equals(row.getStatus())) && "APPROVING".equals(status)) { // 提交
                // 校验供应商能否供该物料
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("supplierId", supplierId);
                map.put("itemId", row.getItemId());
                map.put("enabledFlag", "Y");
                List<SrmPoApprovedListEntity_HI> approvedList = SrmPoApprovedListDAO_HI.findByProperty(map);
                if (approvedList.size() < 1) {
                    throw new UtilsException("存在供应商不能供的物料，物料编码为" + paramData.getString("itemCode") + "，请删除！");
                }

                row.setStatus("APPROVING");
            }
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            SrmPoLinesDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * Description：提交，批准，拒绝订单
     * <p>
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * shipLocationCode  收货方  VARCHAR2  N
     * locationCode  收单方  VARCHAR2  N
     * supPoFileId  供应商附件ID  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * agreementClause  协议条款  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * supplierReadTime  供应商查看日期
     * DATE  N
     * exchangeRate  汇率  NUMBER  N
     * exchangeRateDate  汇率日期  DATE  N
     * syncErpFlag  是否同步到erp  VARCHAR2  N
     * syncErpMsg  同步到erp异常信息  VARCHAR2  N
     * isGlobal  是否全局  VARCHAR2  N
     * orderType  订单类型  VARCHAR2  N
     * erpOrderNo  erp订单编号  VARCHAR2  N
     * bazaarOrderNo  市场订单  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * buyerExecuteId  采购履行专员  NUMBER  N
     * organizationId  库存组织  NUMBER  N
     * prNumber  采购申请单号  VARCHAR2  N
     * locationCode  收货地点  VARCHAR2  N
     * poType  类型  VARCHAR2  N
     * shipToOrganizationId  收货组织  NUMBER  N
     * shipLocationCode    VARCHAR2  N
     * shipToLocationCode  收货方  VARCHAR2  N
     * billToLocationCode  收单方  VARCHAR2  N
     * shipToLocationId  收货方  NUMBER  N
     * billToLocationId  收单方  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * isGlobal  是否全局  VARCHAR2  N
     * agreementClause  协议条款  VARCHAR2  N
     * contractId  合同ID，关联表：srm_okc_contracts  NUMBER  N
     * contractCode  合同编号  VARCHAR2  N
     * contractTemplateId  合同模板ID，关联表：srm_okc_contract_templates  NUMBER  N
     * contractFileId  合同文档ID  NUMBER  N
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

    public JSONObject saveOrderStatus(JSONObject jsonParams) throws Exception {
        int userId = jsonParams.getInteger("varUserId");
        JSONArray array = jsonParams.getJSONArray("array");
        if (null == array || array.isEmpty()) {
            throw new UtilsException("暂无数据");
        }
        for (int i = 0; i < array.size(); i++) {
            SrmPoHeadersEntity_HI po = SrmPoHeadersDAO_HI.getById(array.getJSONObject(i).getInteger("poHeaderId"));
            if (null == po) {
                throw new UtilsException("系统不存在该订单号！");
            } else if ("APPROVING".equals(po.getStatus()) && "APPROVED".equals(jsonParams.getString("status"))) {
                SaafEmployeesEntity_HI emp = SaafEmployeesDAO_HI.getById(po.getBuyerId());
                if (null != emp) {
                    if ("N".equals(emp.getEnabledFlag())) {
                        throw new UtilsException("采购订单的业务员已失效，不允许批准！");
                    }
                } else {
                    throw new UtilsException("采购订单的业务员不存在该系统，不允许批准！");
                }
            } else if ("APPROVING".equals(po.getStatus()) && "REJECT".equals(jsonParams.getString("status"))) {

            } else {
                throw new UtilsException("不允许进行操作！");
            }
            po.setStatus(jsonParams.getString("status"));
            po.setLastUpdateDate(new Date());
            po.setLastUpdatedBy(userId);
            po.setLastUpdateLogin(userId);
            po.setOperatorUserId(userId);
            SrmPoHeadersDAO_HI.save(po);

            // 同时改变订单行的状态
            List<SrmPoLinesEntity_HI> list = SrmPoLinesDAO_HI.findByProperty("poHeaderId", po.getPoHeaderId());
            for (int t = 0; t < list.size(); t++) {
                SrmPoLinesEntity_HI line = list.get(t);
                line.setStatus(jsonParams.getString("status"));
                line.setLastUpdateDate(new Date());
                line.setLastUpdatedBy(userId);
                line.setLastUpdateLogin(userId);
                line.setOperatorUserId(userId);
                SrmPoLinesDAO_HI.save(line);
            }
        }
        return SToolUtils.convertResultJSONObj("S", "操作成功", 1, null);
    }


    /**
     * Description：删除订单
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject deleteOrder(JSONObject jsonParams) throws Exception {
        if (null != jsonParams.getInteger("poHeaderId")) {
            SrmPoHeadersEntity_HI po = SrmPoHeadersDAO_HI.getById(jsonParams.getInteger("poHeaderId"));
            if ("DRAFT".equals(po.getStatus()) || "REJECTED".equals(po.getStatus())) {
                if (po != null) {
                    List<SrmPoLinesEntity_HI> list = SrmPoLinesDAO_HI.findByProperty("poHeaderId",
                            jsonParams.getInteger("poHeaderId"));
                    if (list != null && list.size() > 0) {
                        SrmPoLinesDAO_HI.delete(list);
                    }
                    SrmPoHeadersDAO_HI.delete(po);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除采购订单失败,只能删除拟定和驳回的单据!", 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "删除采购订单失败，" + jsonParams.getInteger("poHeaderId") + "不存在", 0, null);
        }
        return SToolUtils.convertResultJSONObj("S", "删除采购订单成功", 1, null);
    }

    /**
     * Description：删除订单行
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */

    public JSONObject deleteOrderLine(JSONObject jsonParams) throws Exception {
        if (null == jsonParams.getInteger("poLineId")) {
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "参数为空", 0, null);
        }
        SrmPoLinesEntity_HI row = SrmPoLinesDAO_HI.getById(jsonParams.getInteger("poLineId"));
        if (null == row) {
            return SToolUtils.convertResultJSONObj("E", "删除失败,系统不存在该订单行！", 0, null);
        }
        SrmPoHeadersEntity_HI header = SrmPoHeadersDAO_HI.getById(row.getPoHeaderId());
        if (!("DRAFT".equals(header.getStatus()) || "REJECTED".equals(header.getStatus()))) {
            return SToolUtils.convertResultJSONObj("E", "删除失败,该状态不允许删除！", 0, null);
        } else {
            SrmPoLinesDAO_HI.delete(row);
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        }
    }

    /**
     * Description：删除订单行
     *
     * @param params 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteOrderLines(JSONObject params) throws Exception {
        LOGGER.info("删除信息，参数：" + params.toString());
        JSONArray lineIds = params.getJSONArray("data");
        SrmPoLinesEntity_HI line = null;
        for (int i = 0, j = lineIds.size(); i < j; i++) {
            Integer poLineId = lineIds.getInteger(i);
            if (!(poLineId == null || "".equals(poLineId))) {
                line = SrmPoLinesDAO_HI.getById(poLineId);
                if (line != null && "DRAFT".equals(line.getStatus())) {
                    SrmPoLinesDAO_HI.delete(line);
                }
            }
        }
        return SToolUtils.convertResultJSONObj("S", "删除成功", lineIds.size(), null);
    }

    /**
     * Description：复制订单
     * <p>
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * shipLocationCode  收货方  VARCHAR2  N
     * locationCode  收单方  VARCHAR2  N
     * supPoFileId  供应商附件ID  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * agreementClause  协议条款  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * supplierReadTime  供应商查看日期
     * DATE  N
     * exchangeRate  汇率  NUMBER  N
     * exchangeRateDate  汇率日期  DATE  N
     * syncErpFlag  是否同步到erp  VARCHAR2  N
     * syncErpMsg  同步到erp异常信息  VARCHAR2  N
     * isGlobal  是否全局  VARCHAR2  N
     * orderType  订单类型  VARCHAR2  N
     * erpOrderNo  erp订单编号  VARCHAR2  N
     * bazaarOrderNo  市场订单  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * buyerExecuteId  采购履行专员  NUMBER  N
     * organizationId  库存组织  NUMBER  N
     * prNumber  采购申请单号  VARCHAR2  N
     * locationCode  收货地点  VARCHAR2  N
     * poType  类型  VARCHAR2  N
     * shipToOrganizationId  收货组织  NUMBER  N
     * shipLocationCode    VARCHAR2  N
     * shipToLocationCode  收货方  VARCHAR2  N
     * billToLocationCode  收单方  VARCHAR2  N
     * shipToLocationId  收货方  NUMBER  N
     * billToLocationId  收单方  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * isGlobal  是否全局  VARCHAR2  N
     * agreementClause  协议条款  VARCHAR2  N
     * contractId  合同ID，关联表：srm_okc_contracts  NUMBER  N
     * contractCode  合同编号  VARCHAR2  N
     * contractTemplateId  合同模板ID，关联表：srm_okc_contract_templates  NUMBER  N
     * contractFileId  合同文档ID  NUMBER  N
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

    public JSONObject saveCopyOrder(JSONObject jsonParams) throws Exception {
        JSONObject json = new JSONObject();
        int userId = jsonParams.getInteger("varUserId");
        SrmPoHeadersEntity_HI po = null;
        SrmPoHeadersEntity_HI row = SrmPoHeadersDAO_HI.getById(jsonParams.getInteger("poHeaderId"));
        String code = "";
        if ("03".equals(row.getPoDocType())) { // POCS（五金材料）
            code = "POCS";
        } else if ("01".equals(row.getPoDocType())) { // POAS（生产材料）
            code = "POAS";
        } else if ("02".equals(row.getPoDocType())) { // PODS（委外采购）
            code = "PODS";
        }

        po = new SrmPoHeadersEntity_HI();
        po.setStatus("NEW");
        po.setPoNumber(saafSequencesUtil.getDocSequences("srm_po_headers".toUpperCase(),
                code + DateUtil.date2Str(new Date(), "yyMMdd"), 4));
        po.setCreationDate(new Date());
        po.setCreatedBy(userId);

        po.setSupplierId(row.getSupplierId());

        po.setCurrencyCode(row.getCurrencyCode());
        po.setDescription(row.getDescription());
        po.setBuyerId(row.getBuyerId());
        po.setPoDocType(row.getPoDocType());

        po.setLastUpdateDate(new Date());
        po.setLastUpdatedBy(userId);
        po.setLastUpdateLogin(userId);
        po.setOperatorUserId(userId);
        SrmPoHeadersDAO_HI.save(po);
        json.put("poHeaderId", po.getPoHeaderId());
        saveCopyLine(row.getPoHeaderId(), userId, po.getPoHeaderId(), jsonParams.getString("ableCheckOrderFlag"));
        return SToolUtils.convertResultJSONObj("S", "复制成功", 1, json);
    }

    /**
     * 复制订单行
     *
     * @param poHeaderIdOld
     * @param userId
     * @param poHeaderId
     * @return
     */
    JSONObject saveCopyLine(int poHeaderIdOld, int userId, int poHeaderId, String ableCheckOrderFlag) throws Exception {
        JSONObject resultjson = new JSONObject();
        List<SrmPoLinesEntity_HI> list = SrmPoLinesDAO_HI.findByProperty("poHeaderId", poHeaderIdOld);
        for (int i = 0; i < list.size(); i++) {
            SrmPoLinesEntity_HI poLine = list.get(i);
            SrmPoLinesEntity_HI row = new SrmPoLinesEntity_HI();
            row.setPoHeaderId(poHeaderId);
            row.setStatus("NEW");
            row.setCreationDate(new Date());
            row.setLineNumber(poLine.getLineNumber());
            row.setItemId(poLine.getItemId());
            // row.setPrice(poLine.getPrice());
            row.setDemandQty(poLine.getDemandQty());
            row.setDemandDate(poLine.getDemandDate());
            row.setDescription(poLine.getDescription());
            row.setCreatedBy(userId);
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            SrmPoLinesDAO_HI.saveEntity(row);
        }
        return resultjson;
    }

    /**
     * Description：拒绝订单
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject saveRejectReason(JSONObject jsonParams) throws Exception {
        int userId = jsonParams.getInteger("varUserId");
        SrmPoLinesEntity_HI row = SrmPoLinesDAO_HI.getById(jsonParams.getInteger("poLineId"));
        if ("REJECT".equals(jsonParams.getString("status"))) {
            row.setRejectReason(jsonParams.getString("rejectReason"));
        } else if ("AFFIRM".equals(jsonParams.getString("status"))) {

        } else {
            throw new UtilsException("请选择确认或拒绝！");
        }
        row.setLastUpdateDate(new Date());
        row.setLastUpdatedBy(userId);
        row.setLastUpdateLogin(userId);
        row.setOperatorUserId(userId);
        SrmPoLinesDAO_HI.saveEntity(row);
        return SToolUtils.convertResultJSONObj("S", "操作成功", 1, null);
    }

    /**
     * Description：关闭订单行
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject saveOrderLineStatus(JSONObject jsonParams) throws Exception {
        int userId = jsonParams.getInteger("varUserId");
        JSONArray array = jsonParams.getJSONArray("array");
        for (int i = 0; i < array.size(); i++) {
            SrmPoLinesEntity_HI row = SrmPoLinesDAO_HI.getById(array.getJSONObject(i).getInteger("poLineId"));
            if (null == row) {
                throw new UtilsException("系统不存在该订单行！");
            } else if ("APPROVED".equals(row.getStatus())) { // 关闭
                row.setStatus("SHORTAGE_CLOSED");
            } else {
                throw new UtilsException("不允许进行操作！");
            }
            row.setAttribute1("U");
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            SrmPoLinesDAO_HI.save(row);
            SrmPoHeadersEntity_HI header = SrmPoHeadersDAO_HI.getById(row.getPoHeaderId());
            if (null == header) {
                throw new UtilsException("找不到该订单行的订单头信息！");
            } else {
                header.setLastUpdateDate(new Date());
                header.setLastUpdatedBy(userId);
                header.setLastUpdateLogin(userId);
                header.setOperatorUserId(userId);
                SrmPoHeadersDAO_HI.save(header);
            }
        }

        return SToolUtils.convertResultJSONObj("S", "操作成功", 1, null);
    }

    /**
     * Description：根据订单行查询送货通知/送货单
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public List findDeliveryList(JSONObject jsonParams) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_DELIVERY_LIST_SQL);
        SaafToolUtils.parperParam(jsonParams, "p.poLineId", "poLineId", queryString, queryParamMap, "=");
        List<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        return result;
    }

    /**
     * Description：应用调整
     * <p>
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * poChangeId  表ID，主键，供其他表做外键  NUMBER  Y
     * planDate  计划日期  DATE  Y
     * poNumber    VARCHAR2  Y
     * poLineNum    NUMBER  Y
     * poHeaderId    NUMBER  Y
     * poLineId    NUMBER  Y
     * changeType  建议类型 C：取消，U，更新  VARCHAR2  Y
     * originQuantity  原始数量  NUMBER  N
     * originDate  原始需求日期  DATE  N
     * orderQuantity  订单数量  NUMBER  N
     * needByDate  需求日期  DATE  N
     * status  状态  VARCHAR2  Y
     * oldOrderQuantity  未调整前数量  NUMBER  N
     * oldNeedByDate  未调整前需求日期  DATE  N
     * poChangeId  表ID，主键，供其他表做外键  NUMBER  Y
     * planDate  计划日期  DATE  Y
     * poNumber    VARCHAR2  Y
     * poLineNum    NUMBER  Y
     * poHeaderId    NUMBER  Y
     * poLineId    NUMBER  Y
     * changeType  建议类型 C：取消，U，更新  VARCHAR2  Y
     * originQuantity  原始数量  NUMBER  N
     * originDate  原始需求日期  DATE  N
     * orderQuantity  订单数量  NUMBER  N
     * needByDate  需求日期  DATE  N
     * status  状态  VARCHAR2  Y
     * oldOrderQuantity  未调整前数量  NUMBER  N
     * oldNeedByDate  未调整前需求日期  DATE  N
     * <p>
     * Update History
     * =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */


    public JSONObject saveAdjust(JSONObject jsonParams) throws Exception {
        int userId = jsonParams.getInteger("varUserId");
        JSONArray array = jsonParams.getJSONArray("array");
        for (int i = 0; i < array.size(); i++) {
            if (null != array.getJSONObject(i).get("changeType")) {
                SrmPoLinesEntity_HI row = SrmPoLinesDAO_HI.getById(array.getJSONObject(i).getInteger("poLineId"));
                if (null == row) {
                    throw new UtilsException("系统不存在该订单行！");
                } else if (!"APPROVED".equals(row.getStatus())) {
                    throw new UtilsException("只有已审批状态的订单行才能调整！");
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("poLineId", row.getPoLineId());
                    map.put("status", "UNCHECK");
                    List<SrmPoChangeInfoEntity_HI> list = SrmPoChangeInfoDAO_HI.findByProperty(map);
                    SrmPoChangeInfoEntity_HI change = list.get(0);
                    Date oldNeedByDate = row.getDemandDate();
                    Date needByDate = change.getNeedByDate();
                    BigDecimal oldDemandQty = row.getDemandQty();
                    BigDecimal demandQty = change.getOrderQuantity();
                    if ("U".equals(change.getChangeType())) {
                        if (null != change.getNeedByDate()) {
                            change.setOldNeedByDate(oldNeedByDate);
                            row.setDemandDate(needByDate);

                        }
                        if (null != change.getOrderQuantity()) {
                            change.setOldOrderQuantity(oldDemandQty);
                            row.setDemandQty(demandQty);
                        }
                    } else if ("C".equals(change.getChangeType())) {
                        row.setStatus("SHORTAGE_CLOSED");
                    } else {
                        throw new UtilsException("系统不存在该调整类型，请联系管理员！");
                    }
                    change.setStatus("CHECKED");
                    change.setLastUpdateDate(new Date());
                    change.setLastUpdatedBy(userId);
                    change.setLastUpdateLogin(userId);
                    change.setOperatorUserId(userId);
                    SrmPoChangeInfoDAO_HI.save(change);
                    row.setAttribute1("U");
                    row.setLastUpdateDate(new Date());
                    row.setLastUpdatedBy(userId);
                    row.setLastUpdateLogin(userId);
                    row.setOperatorUserId(userId);
                    SrmPoLinesDAO_HI.save(row);
                    SrmPoHeadersEntity_HI header = SrmPoHeadersDAO_HI.getById(row.getPoHeaderId());
                    if (null == header) {
                        throw new UtilsException("找不到该订单行的订单头信息！");
                    } else {
                        header.setLastUpdateDate(new Date());
                        header.setLastUpdatedBy(userId);
                        header.setLastUpdateLogin(userId);
                        header.setOperatorUserId(userId);
                        SrmPoHeadersDAO_HI.save(header);
                    }
                }
            }
        }
        return SToolUtils.convertResultJSONObj("S", "操作成功", 1, null);
    }

    /**
     * Description：srm调整
     * <p>
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * onhandQty  现有量  NUMBER  N
     * consumeQty  消耗量  NUMBER  N
     * subpool  子库  VARCHAR2  N
     * noteToReceiver  通知接收人  VARCHAR2  N
     * accepterName  通知接收人  VARCHAR2  N
     * itemId  物料ID，关联表：srm_base_items  NUMBER  N
     * itemName  物料名称  VARCHAR2  N
     * itemSpec  物料规格  VARCHAR2  N
     * categoryId  采购分类ID，关联表：srm_base_categories  NUMBER  N
     * demandQty  需求数量  NUMBER  N
     * minPoQty  最小采购量  NUMBER  N
     * taxPrice  含税单价  NUMBER  N
     * nonTaxPrice  不含税单价  NUMBER  N
     * ladderPriceFlag  阶梯价标识（Y/N）  VARCHAR2  N
     * ladderQty  阶梯范围  NUMBER  N
     * accumulativeFlag  累计结算标识（Y/N）  VARCHAR2  N
     * demandDate  需求日期  DATE  N
     * receiveToOrganizationId  库存组织ID  NUMBER  N
     * status  行状态(PO_LINE_STATUS)  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * mayNoticeQty  可通知送货数量  NUMBER  N
     * onWayQty  在途数量(已创建送货单数量)  NUMBER  N
     * receivedQty  已接收数量  NUMBER  N
     * originalDemandQty  原需求数量  NUMBER  N
     * originalDemandDate  原需求日期  DATE  N
     * feedbackAdjustDate  反馈调整日期  DATE  N
     * feedbackAdjustQty  反馈调整数量  NUMBER  N
     * feedbackStatus  反馈状态  VARCHAR2  N
     * feedbackResult  反馈结果  VARCHAR2  N
     * rejectReason  供应商拒绝原因  VARCHAR2  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * sourceNumber  来源单号  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * feedbackOnwayDate  反馈在途时间  NUMBER  N
     * itemVersion  物料版本  VARCHAR2  N
     * shipmentNum  发运行号  NUMBER  N
     * shipToLocationId  收货方ID  NUMBER  N
     * firstFeedbackRecodeDate  第一次反馈记录时间  DATE  N
     * serialNo  流水号  VARCHAR2  N
     * process  工序  VARCHAR2  N
     * processScope  加工范围  VARCHAR2  N
     * orderId  工单ID  VARCHAR2  N
     * orderNo  工单号  VARCHAR2  N
     * soOrderNo  SO订单号  VARCHAR2  N
     * soOrderId  SO订单ID  VARCHAR2  N
     * plannerId  计划员ID  VARCHAR2  N
     * customerCode  客户代码  VARCHAR2  N
     * customerPartNo  客户零件号  VARCHAR2  N
     * materialsCodeId  物资编码ID  VARCHAR2  N
     * flag  标识  VARCHAR2  N
     * priceStatus  行价格状态  VARCHAR2  N
     * feedbackAdjustDate2  反馈时间2  DATE  N
     * feedbackAdjustQty2  反馈数量2  NUMBER  N
     * feedbackAdjustDate3  反馈时间3  DATE  N
     * feedbackAdjustQty3  反馈数量3  NUMBER  N
     * standPrice  市场参考价  NUMBER  N
     * feedbackAdjustPrice2  反馈单价2  NUMBER  N
     * feedbackAdjustPrice3  反馈单价3  NUMBER  N
     * feedbackAdjustPrice  反馈单价1  NUMBER  N
     * minConsumePrice  最低消费金额  NUMBER  N
     * deliverDate  送仓/到厂日期  DATE  N
     * feedbackNumber  反馈次数  NUMBER  N
     * priority  优先级  VARCHAR2  N
     * materialId  物资编码ID  NUMBER  N
     * isStricter  是否加严  VARCHAR2  N
     * isCheck  是否检验  VARCHAR2  N
     * contractNumber  合同号  VARCHAR2  N
     * receiveDate  接受日期  DATE  N
     * qualifiedQty  合格数量  NUMBER  N
     * nonQualifiedQty  不合格数量  NUMBER  N
     * requisitionLineId  采购申请行ID  NUMBER  N
     * inventoryUnit  库存单位  VARCHAR2  N
     * gapPrice  差异金额  NUMBER  N
     * remark  行备注  VARCHAR2  N
     * moq  MOQ  NUMBER  N
     * shipmentSite  出货站点  VARCHAR2  N
     * customerItemNo  客户物料号  VARCHAR2  N
     * noticeQty  已通知送货量  NUMBER  N
     * noticeReceiveUserId  通知接受人  NUMBER  N
     * lockOrderNo  锁定单号  VARCHAR2  N
     * lockLineNo  锁定行号  VARCHAR2  N
     * lockQty  锁定数量  NUMBER  N
     * respDesc  答交情况  VARCHAR2  N
     * free  免费  VARCHAR2  N
     * isClose  是否结算  VARCHAR2  N
     * uomCode  采购单位  VARCHAR2  N
     * returnType  回货类型  VARCHAR2  N
     * erpLineId  ERP订单行ID  NUMBER  N
     * carriageLineId  发运行ID  NUMBER  N
     * erpHeaderId  ERP订单头ID  NUMBER  N
     * groupTime  分组时间  VARCHAR2  N
     * poLineId  采购订单行ID  NUMBER  Y
     * poHeaderId  订单头ID  NUMBER  Y
     * lineNumber  行号  NUMBER  Y
     * nonTaxPrice  不含税单价  NUMBER  N
     * ladderPriceFlag  阶梯价标识（Y/N）  VARCHAR2  N
     * ladderQty  阶梯范围  NUMBER  N
     * accumulativeFlag  累计结算标识（Y/N）  VARCHAR2  N
     * demandDate  需求日期  DATE  N
     * receiveToOrganizationId  收货组织ID  NUMBER  N
     * status  行状态(PO_LINE_STATUS)  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * mayNoticeQty  可通知送货数量  NUMBER  N
     * onWayQty  在途数量(已创建送货单数量)  NUMBER  N
     * receivedQty  已接收数量  NUMBER  N
     * originalDemandQty  原需求数量  NUMBER  N
     * originalDemandDate  原需求日期  DATE  N
     * feedbackAdjustDate  反馈调整日期  DATE  N
     * feedbackAdjustQty  反馈调整数量  NUMBER  N
     * feedbackStatus  反馈状态  VARCHAR2  N
     * feedbackResult  反馈结果  VARCHAR2  N
     * rejectReason  供应商拒绝原因  VARCHAR2  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * sourceNumber  来源单号  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * returnQty  退货数量  VARCHAR2  N
     * erpPoNumber  ERP采购订单号  VARCHAR2  N
     * context  上下文  VARCHAR2  N
     * projectCategory  项目分类  VARCHAR2  N
     * projectType  项目类型  VARCHAR2  N
     * technicalTransNumber  技改编号  VARCHAR2  N
     * subprojectNumber  子项目编号  VARCHAR2  N
     * acceptanceProcessNumber  验收流程号  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * nonTaxTotalPrice  行总价(不含税)/元  NUMBER  N
     * taxTotalPrice  行总价(含税)/元  NUMBER  N
     * nonTaxActTotalPrice  实付行总价(不含税)/元  NUMBER  N
     * taxActTotalPrice  实付行总价(含税)/元  NUMBER  N
     * poLineCombId  采购订单合并行ID(含税)/元  NUMBER  N
     * poLineId  采购订单行ID  NUMBER  Y
     * poHeaderId  订单头ID  NUMBER  Y
     * lineNumber  行号  NUMBER  Y
     * itemId  物料ID，关联表：srm_base_items  NUMBER  N
     * itemName  物料名称  VARCHAR2  N
     * itemSpec  物料规格  VARCHAR2  N
     * categoryId  采购分类ID，关联表：srm_base_categories  NUMBER  N
     * demandQty  需求数量  NUMBER  N
     * minPoQty  最小采购量  NUMBER  N
     * taxPrice  含税单价  NUMBER  N
     * <p>
     * Update History
     * =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    public JSONObject saveSrmAdjust(JSONObject jsonParams) throws Exception {
        int userId = jsonParams.getInteger("varUserId");
        SrmPoLinesEntity_HI row = SrmPoLinesDAO_HI.getById(jsonParams.getInteger("poLineId"));
        if (null == row) {
            throw new UtilsException("系统不存在该订单行！");
        } else if (!"APPROVED".equals(row.getStatus())) {
            throw new UtilsException("只有已审批状态的订单行才能调整！");
        } else {
            if (null != SrmUtils.getString(jsonParams.getString("demandQtyAdjust"))) {
                row.setDemandQty(jsonParams.getBigDecimal("demandQtyAdjust"));
            }
            if (null != SrmUtils.getString(jsonParams.getString("demandDateAdjust"))) {
                row.setDemandDate(jsonParams.getDate("demandDateAdjust"));
            }
            row.setAttribute1("U");
            row.setLastUpdateDate(new Date());
            row.setLastUpdatedBy(userId);
            row.setLastUpdateLogin(userId);
            row.setOperatorUserId(userId);
            SrmPoLinesDAO_HI.save(row);
        }
        return SToolUtils.convertResultJSONObj("S", "操作成功", 1, null);
    }

    /**
     * Description：查询需求
     *
     * @param jsonParams 查询条件参数
     * @param pageIndex  页码
     * @param pageRows   行数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public String findNeedInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        StringBuffer appendString = new StringBuffer();
        StringBuffer countString = new StringBuffer();
        queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_NEED_LIST_SQL);
        countString.append(SrmPoHeadersEntity_HI_RO.QUERY_NEED_LIST_COUNT_SQL);
        SaafToolUtils.parperParam(jsonParams, "sppdl.supplier_id", "supplier_id", appendString, queryParamMap, "=");// 供应商查询
        SaafToolUtils.parperParam(jsonParams, "sppd.plan_type", "planType", appendString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sppd.need_by_date", "needByDateFrom", appendString, queryParamMap, ">=");
        SaafToolUtils.parperParam(jsonParams, "sppd.need_by_date", "needByDateTo", appendString, queryParamMap, "<=");
        SaafToolUtils.parperParam(jsonParams, "sppd.supply_inst_id", "supplyInstId", appendString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "se.employee_name", "employeeName", appendString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sppdl.supplier_id", "supplierId", appendString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sppd.inst_id", "instId", appendString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "sbc.category_code", "categoryCode", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbc.category_name", "categoryName", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_code", "itemCode", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sbi.item_name", "itemName", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sppd.demand_classify", "demandClassify", appendString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "sppd.special_use_num", "specialUseNum", appendString, queryParamMap, "like");
        queryString.append(appendString);
        countString.append(appendString);
        if (jsonParams.containsKey("periodTypeFlag") && jsonParams.getBoolean("periodTypeFlag")) {
            queryString.append(" AND sbi.period_type ='Y' ");
            countString.append(" AND sbi.period_type ='Y' ");
        }
        if (jsonParams.containsKey("difficultItemFlag") && jsonParams.getBoolean("difficultItemFlag")) {
            queryString.append(" AND sbi.difficult_item = 'Y' ");
            countString.append(" AND sbi.difficult_item = 'Y' ");
        }
        if (jsonParams.containsKey("customerDesignFlag") && jsonParams.getBoolean("customerDesignFlag")) {
            queryString.append(" AND sbi.customer_design = 'Y' ");
            countString.append(" AND sbi.customer_design = 'Y' ");
        }
        if (jsonParams.containsKey("customerSupplyFlag") && jsonParams.getBoolean("customerSupplyFlag")) {
            queryString.append(" AND sbi.customer_supply = 'Y' ");
            countString.append(" AND sbi.customer_supply = 'Y' ");
        }
        if (jsonParams.containsKey("cutDayFlag") && jsonParams.getBoolean("cutDayFlag")) {
            queryString.append(" AND datediff(sppd.advise_order_date,curdate()) < 0 ");
            countString.append(" AND datediff(sppd.advise_order_date,curdate()) < 0 ");
        }
        if (null == SrmUtils.getString(jsonParams.getString("employeeName"))) {
            queryString.append(" AND se.employee_name is null ");
            countString.append(" AND se.employee_name is null ");
        }
        queryString.append("ORDER BY sppd.plan_demand_num, sbi.item_code, sppd.inst_id");
        Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(),
                countString, queryParamMap, pageIndex, pageRows);
        JSONObject obj = JSON.parseObject(JSONArray.toJSONString(result));
        JSONArray array = obj.getJSONArray("data");
        for (int i = 0; i < array.size(); i++) {
            BigDecimal number = array.getJSONObject(i).getBigDecimal("distributeQuantity")
                    .subtract(array.getJSONObject(i).getBigDecimal("poQuantity"));
            array.getJSONObject(i).put("oldCanOrderQty", number);
            array.getJSONObject(i).put("canOrderQty", number);
        }
        obj.put("data", array);
        return obj.toJSONString();
    }

    /**
     * Description：需求创建采购订单
     * <p>
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * shipLocationCode  收货方  VARCHAR2  N
     * locationCode  收单方  VARCHAR2  N
     * supPoFileId  供应商附件ID  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * agreementClause  协议条款  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * supplierReadTime  供应商查看日期
     * DATE  N
     * exchangeRate  汇率  NUMBER  N
     * exchangeRateDate  汇率日期  DATE  N
     * syncErpFlag  是否同步到erp  VARCHAR2  N
     * syncErpMsg  同步到erp异常信息  VARCHAR2  N
     * isGlobal  是否全局  VARCHAR2  N
     * orderType  订单类型  VARCHAR2  N
     * erpOrderNo  erp订单编号  VARCHAR2  N
     * bazaarOrderNo  市场订单  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * buyerExecuteId  采购履行专员  NUMBER  N
     * organizationId  库存组织  NUMBER  N
     * prNumber  采购申请单号  VARCHAR2  N
     * locationCode  收货地点  VARCHAR2  N
     * poType  类型  VARCHAR2  N
     * shipToOrganizationId  收货组织  NUMBER  N
     * shipLocationCode    VARCHAR2  N
     * shipToLocationCode  收货方  VARCHAR2  N
     * billToLocationCode  收单方  VARCHAR2  N
     * shipToLocationId  收货方  NUMBER  N
     * billToLocationId  收单方  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * isGlobal  是否全局  VARCHAR2  N
     * agreementClause  协议条款  VARCHAR2  N
     * contractId  合同ID，关联表：srm_okc_contracts  NUMBER  N
     * contractCode  合同编号  VARCHAR2  N
     * contractTemplateId  合同模板ID，关联表：srm_okc_contract_templates  NUMBER  N
     * contractFileId  合同文档ID  NUMBER  N
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

    public JSONObject saveNeedToOrder(JSONObject jsonParams) throws Exception {
        JSONObject json = new JSONObject();
        int userId = jsonParams.getInteger("varUserId");
        JSONArray array = jsonParams.getJSONArray("array");
        for (int i = 0; i < array.size(); i++) {
            String code = "";
            if ("03".equals(array.getJSONObject(i).getString("planType"))) { // POCS（五金材料）
                code = "POCS";
            } else if ("01".equals(array.getJSONObject(i).getString("planType"))) { // POAS（生产材料）
                code = "POAS";
            } else if ("02".equals(array.getJSONObject(i).getString("planType"))) { // PODS（委外采购）
                code = "PODS";
            }
            String ex = "";
            if ("85".equals(array.getJSONObject(i).getString("supplyInstId"))) { // 奥马
                ex = "Homa_";
            } else if ("90".equals(array.getJSONObject(i).getString("supplyInstId"))) { // 管道厂
                ex = "301_";
            } else if ("91".equals(array.getJSONObject(i).getString("supplyInstId"))) { // 注塑厂
                ex = "302_";
            }
            SrmPoHeadersEntity_HI po = new SrmPoHeadersEntity_HI();
            po.setSupplierId(array.getJSONObject(i).getInteger("supplierId"));
            po.setCurrencyCode(array.getJSONObject(i).getString("currencyCode"));
            po.setBuyerId(jsonParams.getInteger("varEmployeeId"));
            po.setPoNumber(saafSequencesUtil.getDocSequences("srm_po_headers".toUpperCase(), ex + code + DateUtil.date2Str(new Date(), "yyMMdd"), 4));
            po.setPoDocType(array.getJSONObject(i).getString("planType"));
            po.setStatus(array.getJSONObject(i).getString("status"));
            po.setLastUpdateDate(new Date());
            po.setLastUpdatedBy(userId);
            po.setLastUpdateLogin(userId);
            po.setOperatorUserId(userId);
            SrmPoHeadersDAO_HI.save(po);
            JSONArray arrayLine = array.getJSONObject(i).getJSONArray("lineData");
            // 查找该订单头的最大行号
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_MAX_LINE_NUMBER_SQL);
            queryParamMap.put("poHeaderId", po.getPoHeaderId());
            List<SrmPoHeadersEntity_HI_RO> tempList = SrmPoHeadersDAO_HI_RO.findList(queryString.toString(),
                    queryParamMap);
            int count = 0;
            int lineNumber = 0;
            if (null == tempList.get(0).getLineNumber()) {
                lineNumber = 10;
            } else {
                lineNumber = tempList.get(0).getLineNumber() + 10;
            }
            for (int t = 0; t < arrayLine.size(); t++) {
                JSONObject line = arrayLine.getJSONObject(t);
                SrmPoLinesEntity_HI row = new SrmPoLinesEntity_HI();
                row.setLineNumber(lineNumber + count * 10);
                row.setPoHeaderId(po.getPoHeaderId());
                row.setItemId(line.getInteger("itemId"));
                row.setStatus(po.getStatus());
                row.setDemandQty(line.getBigDecimal("canOrderQty"));
                row.setDemandDate(line.getDate("needByDate"));
                row.setDescription(line.getString("description"));
                row.setLastUpdateDate(new Date());
                row.setLastUpdatedBy(userId);
                row.setLastUpdateLogin(userId);
                row.setOperatorUserId(userId);
                SrmPoLinesDAO_HI.saveEntity(row);
                count++;
            }
        }
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, json);
    }

    /**
     * Description：月度查询采购订单
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONArray searchMonthOrder(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        if ("EX".equals(jsonParams.getString("varPlatformCode"))) { // 是供应商查询
            jsonParams.put("supplier_id", jsonParams.getInteger("varSupplierId"));
        }
        String[] day = new String[31];
        JSONArray jsonArray = new JSONArray();
        JSONArray resultArray = new JSONArray();
        for (int i = 0; i < 31; i++) {
            day[i] = SrmUtils.dateAdd(jsonParams.getString("dateFrom"), i);
            jsonParams.put("day" + i, day[i]);
        }
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_MONTH_ORDER_SQL);
        SaafToolUtils.parperParam(jsonParams, "sph.supplier_id", "supplier_id", queryString, queryParamMap, "=");// 供应商查询
        SaafToolUtils.parperParam(jsonParams, "sph.supplier_id", "supplierId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_number", "supplierNumber", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "spsi.supplier_name", "supplierName", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "spl.item_id", "itemId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "a.itemCode", "itemCode", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "a.itemName", "itemName", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "a.categoryCode", "categoryCode", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "spl.demand_date", "day0", queryString, queryParamMap, ">=");
        SaafToolUtils.parperParam(jsonParams, "spl.demand_date", "day30", queryString, queryParamMap, "<=");
        if (jsonParams.containsKey("deliveryFlag") && jsonParams.getBoolean("deliveryFlag")) {
            queryString.append(" AND spl.demand_qty != IFNULL(spl.delivery_qty,0) ");
        }
        if (null != SrmUtils.getString(jsonParams.getString("instId"))
                && !"all".equals(jsonParams.getString("instId"))) { // 选择了某个分厂
            SaafToolUtils.parperParam(jsonParams, "spl.inst_id", "instId", queryString, queryParamMap, "=");
        }
        queryString.append(" GROUP BY spl.item_id,spl.demand_date,sph.supplier_id");
        if (null == SrmUtils.getString(jsonParams.getString("instId"))) { // 选择了空，不分组，汇总

        } else {
            queryString.append(",spl.inst_id");
        }
        List<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findList(queryString.toString(),
                queryParamMap);
        jsonArray = JSON.parseArray(JSONArray.toJSONString(result));
        if (null == SrmUtils.getString(jsonParams.getString("instId"))) { // 分厂选择了空，不分组,汇总
            for (int t = 0; t < jsonArray.size(); t++) {
                Boolean flag = false;
                for (int c = 0; c < resultArray.size(); c++) {
                    String itemId = jsonArray.getJSONObject(t).getString("itemId");
                    String itemIdAfter = resultArray.getJSONObject(c).getString("itemId");
                    String supplierId = jsonArray.getJSONObject(t).getString("supplierId");
                    String supplierIdAfter = resultArray.getJSONObject(c).getString("supplierId");
                    if (itemId.equals(itemIdAfter) && supplierId.equals(supplierIdAfter)) {
                        flag = true;
                        for (int a = 0; a < 31; a++) {
                            if (jsonArray.getJSONObject(t).getString("demandDate").equals(day[a])) {
                                resultArray.getJSONObject(c).put("day" + a,
                                        jsonArray.getJSONObject(t).getBigDecimal("demandQty"));
                                break;
                            }
                        }
                        break;
                    }
                }
                if (!flag) {
                    for (int a = 0; a < 31; a++) {
                        if (jsonArray.getJSONObject(t).getString("demandDate").equals(day[a])) {
                            jsonArray.getJSONObject(t).put("day" + a,
                                    jsonArray.getJSONObject(t).getBigDecimal("demandQty"));
                            resultArray.add(jsonArray.getJSONObject(t));
                        }
                    }
                }
            }
        } else { // 选择'某个分厂'或'全部'，按分厂分组
            for (int t = 0; t < jsonArray.size(); t++) {
                Boolean flag = false;
                String itemId = jsonArray.getJSONObject(t).getString("itemId");
                String supplierId = jsonArray.getJSONObject(t).getString("supplierId");
                String instId = jsonArray.getJSONObject(t).getString("instId");

                for (int c = 0; c < resultArray.size(); c++) {
                    String itemIdAfter = resultArray.getJSONObject(c).getString("itemId");
                    String supplierIdAfter = resultArray.getJSONObject(c).getString("supplierId");
                    String instIdAfter = resultArray.getJSONObject(c).getString("instId");
                    if (itemId.equals(itemIdAfter) && supplierId.equals(supplierIdAfter)
                            && instId.equals(instIdAfter)) {
                        flag = true;
                        for (int a = 0; a < 31; a++) {
                            if (jsonArray.getJSONObject(t).getString("demandDate").equals(day[a])) {
                                resultArray.getJSONObject(c).put("day" + a,
                                        jsonArray.getJSONObject(t).getBigDecimal("demandQty"));
                                break;
                            }
                        }
                        break;
                    }
                }
                if (!flag) {
                    for (int a = 0; a < 31; a++) {
                        if (jsonArray.getJSONObject(t).getString("demandDate").equals(day[a])) {
                            jsonArray.getJSONObject(t).put("day" + a,
                                    jsonArray.getJSONObject(t).getBigDecimal("demandQty"));
                            resultArray.add(jsonArray.getJSONObject(t));
                        }
                    }
                }
            }
        }
        for (int q = 0; q < resultArray.size(); q++) {
            BigDecimal sum = new BigDecimal(0);
            for (int j = 0; j < 31; j++) {
                if (null != SrmUtils.getString(resultArray.getJSONObject(q).getString("day" + j))) {
                    sum = sum.add(resultArray.getJSONObject(q).getBigDecimal("day" + j));
                }
            }
            resultArray.getJSONObject(q).put("sum", sum);
        }
        return resultArray;
    }

    /**
     * Description：批量导入
     *
     * @param jsonParams
     * @return
     * @throws Exception ==============================================================================
     *                   Version    Date           Updated By     Description
     *                   -------    -----------    -----------    ---------------
     *                   V1.0       2020-06-20     hgq             modify
     *                   ==============================================================================
     */
    @Override
    public JSONObject saveList(JSONObject jsonParams) throws Exception {
        SrmPoLinesEntity_HI lineEntity = null;
        SrmBaseItemsEntity_HI_RO itemsEntity = null;
        JSONArray error = new JSONArray();
        LOGGER.info("params: +" + jsonParams);
        Map<String, Object> map = new HashMap<String, Object>();
        Integer poHeaderId = jsonParams.getJSONObject("info").getInteger("date");
        Integer varUserId = jsonParams.getInteger("varUserId");
        if (null == poHeaderId) {
            throw new UtilsException("请先保存头信息");
        }
        JSONArray jsonArray = jsonParams.getJSONArray("data");
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_MAX_LINE_NUMBER_SQL);
        queryParamMap.put("poHeaderId", poHeaderId);
        List<SrmPoHeadersEntity_HI_RO> tempList = SrmPoHeadersDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        int lineNumber = 0;
        if (null == tempList.get(0).getLineNumber()) {
            lineNumber = 0;
        } else {
            lineNumber = tempList.get(0).getLineNumber();
        }
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            try {
                lineEntity = new SrmPoLinesEntity_HI();
                // 根据物料编码去查询物料id
                itemsEntity = getItem(obj.getString("itemCode"));
                // 根据分厂名称查询id
                Integer instId = getInstId(obj.getString("instName"));

                Date demandDate = obj.getDate("demandDate");
                BigDecimal demandQty = obj.getBigDecimal("demandQty");
                if (demandQty.compareTo(new BigDecimal(0)) <= 0) {
                    obj.put("ERR_MESSAGE", "需求数量小于1");
                    obj.put("ROW_NUM", i + 1);
                    error.add(obj);
                    continue;
                }
                if (null == demandDate) {
                    obj.put("ERR_MESSAGE", "需求日期错误");
                    obj.put("ROW_NUM", i + 1);
                    error.add(obj);
                    continue;
                }
                if (itemsEntity == null || null == itemsEntity.getItemId()) {
                    obj.put("ERR_MESSAGE", "物料错误");
                    obj.put("ROW_NUM", i + 1);
                    error.add(obj);
                    continue;
                }
                if (null == instId) {
                    obj.put("ERR_MESSAGE", "分厂错误");
                    obj.put("ROW_NUM", i + 1);
                    error.add(obj);
                    continue;
                }
                lineEntity.setLineNumber(lineNumber += 10);
                // 保存头DELIVERY_TYPE
                lineEntity.setPoHeaderId(poHeaderId);
                lineEntity.setItemId(itemsEntity.getItemId());
                lineEntity.setDemandDate(demandDate);
                lineEntity.setDemandQty(demandQty);
                lineEntity.setDescription(SrmUtils.getString(obj.getString("description")));
                lineEntity.setStatus("NEW");
                lineEntity.setCreationDate(new Date());
                lineEntity.setCreatedBy(varUserId);
                lineEntity.setLastUpdateDate(new Date());
                lineEntity.setLastUpdatedBy(varUserId);
                lineEntity.setOperatorUserId(varUserId);
                SrmPoLinesDAO_HI.save(lineEntity);
            } catch (Exception e) {
                LOGGER.error("批量导入采购单,第" + (i + 1) + "失败" + JSONObject.toJSONString(obj));
                obj.put("ERR_MESSAGE", "error");
                obj.put("ROW_NUM", i + 1);
                error.add(obj);
            }
        }
        if (null != error && error.size() == 0) {
            return SToolUtils.convertResultJSONObj("S", "保存成功行数为" + jsonArray.size() + "行", 0, null);
        }
        return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存成功行数为" + (jsonArray.size() - error.size()) + "行", error.size(), error);
    }

    /**
     * Description：根据组织名称获取组织
     *
     * @param instName
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private Integer getInstId(String instName) {
        if (null == instName || "".equals(instName.trim())) {
            return null;
        }
        SaafInstitutionEntity_HI_RO instEntity = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("instName", instName.trim());
            instEntity = Institution_HI_RO.findList(SaafInstitutionEntity_HI_RO.QUERY_INST_ID_BY_INST_CODE, map).get(0);
            return instEntity.getInstId();
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Description：根据物料编码获取物料信息
     *
     * @param itemCode 物料编码
     * @return ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private SrmBaseItemsEntity_HI_RO getItem(String itemCode) {
        if (null == itemCode || "".equals(itemCode.trim()))
            return null;
        SrmBaseItemsEntity_HI_RO itemsEntity = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("itemCode", itemCode.trim());
            itemsEntity = itemsDao_HI_RO.findList(SrmBaseItemsEntity_HI_RO.QUERY_BASE_ITEM_BY_ITEM_CODE, map).get(0);
            return itemsEntity;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Description：供应商查询采购订单
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public Pagination<SrmPoHeadersEntity_HI_RO> findOrderInfoForSupplier(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_ORDER_LIST_SQL);
        SaafToolUtils.parperParam(jsonParams, "a.po_number", "poNumber", queryString, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "a.org_id", "orgId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "a.bill_to_organization_id", "billToOrganizationId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "date_format(a.creation_date,'%Y-%m-%d')", "creationDateFrom", queryString, queryParamMap, ">=");
        SaafToolUtils.parperParam(jsonParams, "date_format(a.creation_date,'%Y-%m-%d')", "creationDateTo", queryString, queryParamMap, "<=");
        SaafToolUtils.parperParam(jsonParams, "a.buyer_id", "buyerId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "b.category_id", "categoryId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "b.item_id", "itemId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "a.return_goods_type", "returnGoodsType", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "date_format(b.demand_date,'%Y-%m-%d')", "demandDateFrom", queryString, queryParamMap, ">=");
        SaafToolUtils.parperParam(jsonParams, "date_format(b.demand_date,'%Y-%m-%d')", "demandDateTo", queryString, queryParamMap, "<=");
        SaafToolUtils.parperParam(jsonParams, "a.status", "status", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "b.status", "lineStatus", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "a.description", "description", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "b.description", "lineDescription", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "b.feedback_status ", "feedbackStatus", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "b.feedback_result", "feedbackResult", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "a.supplier_id", "supplierId", queryString, queryParamMap, "=");
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" ORDER BY a.po_header_id DESC, b.po_line_id ASC, b.line_number DESC");
        Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(), countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：供应商确认
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveSupplierConfirmOrderInfo(JSONObject jsonParams) throws Exception {
        JSONArray jsonArray = jsonParams.getJSONArray("lineData");
        if (null == jsonArray || jsonArray.isEmpty()) {
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "暂无数据", 0, null);
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            if ("Y".equals(jsonArray.getJSONObject(i).getInteger("selectFlag"))) {
                Integer poLineId = jsonArray.getJSONObject(i).getInteger("poLineId");
                SrmPoLinesEntity_HI entity = SrmPoLinesDAO_HI.findByProperty("poLineId", poLineId).get(0);
                entity.setFeedbackStatus(jsonArray.getJSONObject(i).getString("feedbackStatus"));
                SrmPoLinesDAO_HI.update(entity);
            }
        }
        return SToolUtils.convertResultJSONObj("S", "确认成功", 1, null);
    }

    /**
     * Description：供应商汇总查询
     *
     * @param jsonParams 查询条件参数
     * @param pageIndex  页码
     * @param pageRows   行数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoHeadersEntity_HI_RO> findSupplierGatherOrderInfo(JSONObject jsonParams, Integer pageIndex,
                                                                            Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer sb = new StringBuffer(SrmPoHeadersEntity_HI_RO.GET_PO_GETHER_ORDER_SQL);
        SaafToolUtils.parperParam(jsonParams, "a.org_id", "orgId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "b.item_id", "itemId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "b.receive_to_organization_id", "receiveToOrganizationId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "a.return_goods_type", "returnGoodsType", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "date_format(b.demand_date,'%Y-%m-%d')", "demandDateFrom", sb, queryParamMap, ">=");
        SaafToolUtils.parperParam(jsonParams, "date_format(b.demand_date,'%Y-%m-%d')", "demandDateTo", sb, queryParamMap, "<=");
        String poLineIds = jsonParams.getString("poLineId");
        if (null != poLineIds && poLineIds.contains(",")) {
            SaafToolUtils.parperParam(jsonParams, "b.po_line_id", "poLineId", sb, queryParamMap, "in");
        } else {
            SaafToolUtils.parperParam(jsonParams, "b.po_line_id", "poLineId", sb, queryParamMap, "=");
        }
        String groupSql = " GROUP  BY e.meaning, f.inst_name, b.demand_date, d.item_code, d.item_name, z.meaning";
        sb.append(groupSql);
        String countSql = "select count(1) from (" + sb + ")";
        return SrmPoHeadersDAO_HI_RO.findPagination(sb.toString(), countSql, queryParamMap, pageIndex, pageRows);
    }

    /**
     * Description：供应商查询采购订单头
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmPoHeadersEntity_HI_RO> findOrderHeaderBySupplier(JSONObject jsonParams) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryParamSql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_ORDER_HEADER_BY_SUPPLIER_SQL);
        queryParamMap.put("poHeaderId", jsonParams.getString("poHeaderId"));
        return SrmPoHeadersDAO_HI_RO.findList(queryParamSql, queryParamMap);
    }


    /**
     * Description：从合同转订单
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject findOrderHeaderByContract(JSONObject jsonParams) throws Exception {
        //合同信息，转换为订单的头
        List<SrmPoHeadersEntity_HI_RO> poList = new ArrayList<>();
        JSONObject json = new JSONObject();
        //头
        StringBuffer queryString = new StringBuffer(SrmOkcContractsOrderEntity_HI_RO.SRM_OKC_CONTRACTS_QUERY_SQL);
        queryString.append(" AND soc.contract_id = :contractId");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contractId", jsonParams.getInteger("contractId"));
        List<SrmOkcContractsOrderEntity_HI_RO> srmOkcContractsEntitylIST = srmOkcContractOrderEntityDAO_HI_RO.findList(queryString, map);

        //行
        //合同的标的物，转换为订单的行
        List<SrmPoHeadersEntity_HI_RO> itemList = new ArrayList<>();
        StringBuffer queryIetmString = new StringBuffer(SrmOkcContractsOrderEntity_HI_RO.SRM_OKC_CONTRACT_ITEMS_QUERY_SQL);
        //queryIetmString.append(" AND t.contract_id = :contractId");
        Map<String, Object> itemMap = new HashMap();
        itemMap.put("contractId", jsonParams.getInteger("contractId"));
        List<SrmOkcContractsOrderEntity_HI_RO> srmOkcContractsItemEntitylIST = srmOkcContractOrderEntityDAO_HI_RO.findList(queryIetmString, itemMap);
        Integer organizationId = ObjectUtils.isEmpty(srmOkcContractsItemEntitylIST.get(0).getOrganizationId()) ? null : srmOkcContractsItemEntitylIST.get(0).getOrganizationId();
        String organizationCode = ObjectUtils.isEmpty(srmOkcContractsItemEntitylIST.get(0).getInstCode()) ? null : srmOkcContractsItemEntitylIST.get(0).getInstCode();
        String organizationName = ObjectUtils.isEmpty(srmOkcContractsItemEntitylIST.get(0).getInstName()) ? null : srmOkcContractsItemEntitylIST.get(0).getInstName();

        if (srmOkcContractsEntitylIST.size() > 0) {
            SrmOkcContractsOrderEntity_HI_RO srmOkcContractsEntity_HI_RO = srmOkcContractsEntitylIST.get(0);
            SrmPoHeadersEntity_HI_RO poRo = new SrmPoHeadersEntity_HI_RO();
            poRo.setOrgName(srmOkcContractsEntity_HI_RO.getPartyAName());
            poRo.setOrgId(srmOkcContractsEntity_HI_RO.getPartyAId());
            poRo.setSupplierName(srmOkcContractsEntity_HI_RO.getPartyBName());
            poRo.setSupplierId(srmOkcContractsEntity_HI_RO.getPartyBId());
            poRo.setSupplierSiteId(srmOkcContractsEntity_HI_RO.getPartyBSiteId());
            poRo.setSiteName(srmOkcContractsEntity_HI_RO.getSiteName());
            poRo.setCurrencyCode(srmOkcContractsEntity_HI_RO.getCurrencyCode());
            //poRo.setTaxRateCode(srmOkcContractsEntity_HI_RO.getTaxRateCode());
            poRo.setPaymentTermName(srmOkcContractsEntity_HI_RO.getPaymentTermName());
            poRo.setPaymentTermId(srmOkcContractsEntity_HI_RO.getPaymentTermId());
            poRo.setPaymentCondition(srmOkcContractsEntity_HI_RO.getPaymentTermCode());
            poRo.setPaymentTermCode(srmOkcContractsEntity_HI_RO.getPaymentTermCode());
            poRo.setContractId(srmOkcContractsEntity_HI_RO.getContractId());
            poRo.setContractCode(srmOkcContractsEntity_HI_RO.getContractCode());
            poRo.setContractName(srmOkcContractsEntity_HI_RO.getContractName());
            poRo.setContractDate(srmOkcContractsEntity_HI_RO.getPartyASignDate());
            poRo.setOrganizationId(organizationId);
            poRo.setOrganizationCode(organizationCode);
            poRo.setOrganizationName(organizationName);
            String contractType = srmOkcContractsEntity_HI_RO.getContractType();
            if ("H1".equals(contractType)) {
                poRo.setPoType("LOGISTICS");
            } else if ("R1".equals(contractType)) {
                poRo.setPoType("HUMAN_RESOURCES");
            } else if ("I1".equals(contractType)) {
                poRo.setPoType("INFORMATION_TECHNOLOGY");
            } else if ("W1".equals(contractType)) {
                poRo.setPoType("ENGINEERING");
            }
            poRo.setStatus("DRAFT");
            poList.add(poRo);
        }
        json.put("poRo", poList);

        /*//合同的标的物，转换为订单的行
        List<SrmPoHeadersEntity_HI_RO> itemList = new ArrayList<>();
        StringBuffer queryIetmString = new StringBuffer(SrmOkcContractsOrderEntity_HI_RO.SRM_OKC_CONTRACT_ITEMS_QUERY_SQL);
        //queryIetmString.append(" AND t.contract_id = :contractId");
        Map<String, Object> itemMap = new HashMap();
        itemMap.put("contractId", jsonParams.getInteger("contractId"));
        List<SrmOkcContractsOrderEntity_HI_RO> srmOkcContractsItemEntitylIST = srmOkcContractOrderEntityDAO_HI_RO.findList(queryIetmString, itemMap);*/
        String lingIds = jsonParams.getString("lingIds");
        if (lingIds != null && !"".equals(lingIds.trim())) {
            String[] lingId = lingIds.split(",");
            SrmPoHeadersEntity_HI_RO itemRO = null;
            for (SrmOkcContractsOrderEntity_HI_RO item : srmOkcContractsItemEntitylIST) {
                boolean isContains = Arrays.asList(lingId).contains(item.getContractItemId().toString());
                if (isContains) {
                    itemRO = new SrmPoHeadersEntity_HI_RO();
                    itemRO.setItemId(item.getItemId());
                    itemRO.setItemName(item.getItemName());
                    itemRO.setItemCode(item.getItemCode());
                    itemRO.setUomCodeName(item.getUomCodeName());
                    itemRO.setCategoryId(item.getCategoryId());
                    itemRO.setCategoryCode(item.getCategoryCode());
                    itemRO.setCategoryName(item.getCategoryName());
                    itemRO.setDemandQty(item.getPurchaseQuantity());
                    itemRO.setNonTaxPrice(item.getNonTaxPrice());
                    itemRO.setTaxPrice(item.getTaxPrice());
                    itemRO.setDemandDate(item.getDemandDate());
                    itemRO.setLineDescription(item.getContractItemComments());
                    itemRO.setContractId(item.getContractId());
                    itemRO.setContractItemId(item.getContractItemId());
                    itemRO.setSpecification(item.getSpecification());
                    itemRO.setItemSpec(item.getSpecification());
                    itemRO.setLineStatus("DRAFT");
                    itemRO.setLineStatusStr("拟定");
                    itemRO.setTaxRateCode(item.getTaxRateCode());
                    itemRO.setDemandDate(item.getDemandDate());
                    itemList.add(itemRO);
                }
            }
        }
        json.put("itemRO", itemList);
        return json;
    }


    /**
     * Description：供应商查询采购订单行
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmPoHeadersEntity_HI_RO> findOrderLineBySupplier(JSONObject jsonParams) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryParamSql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_ORDER_LINE_BY_SUPPLIER_SQL);
        SaafToolUtils.parperParam(jsonParams, "spl.po_header_id", "poHeaderId", queryParamSql, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "spl.po_line_id", "poLineId", queryParamSql, queryParamMap, "=");
        String itemCodeOrName = jsonParams.getString("itemCodeOrName");
        if (null != itemCodeOrName && itemCodeOrName.trim().length() != 0) {
            queryParamSql.append(" AND (sbi.item_code like :itemCodeOrName ");
            queryParamSql.append(" OR sbi.item_name like :itemCodeOrName) ");
            queryParamMap.put("itemCodeOrName", "%" + itemCodeOrName + "%");
        }
        queryParamSql.append(" ORDER BY spl.line_number");
        return SrmPoHeadersDAO_HI_RO.findList(queryParamSql, queryParamMap);
    }

    /**
     * Description：查找订单版本
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmPoHeadersEntity_HI_RO> findPoVersions(JSONObject jsonParams) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryParamSql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_PO_VERSIONS_SQL);
        queryParamMap.put("poHeaderId", jsonParams.getInteger("poHeaderId"));
        return SrmPoHeadersDAO_HI_RO.findList(queryParamSql, queryParamMap);
    }

    /**
     * Description：保存，更新，提交订单信息
     * <p>
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * shipLocationCode  收货方  VARCHAR2  N
     * locationCode  收单方  VARCHAR2  N
     * supPoFileId  供应商附件ID  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * agreementClause  协议条款  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * supplierReadTime  供应商查看日期
     * DATE  N
     * exchangeRate  汇率  NUMBER  N
     * exchangeRateDate  汇率日期  DATE  N
     * syncErpFlag  是否同步到erp  VARCHAR2  N
     * syncErpMsg  同步到erp异常信息  VARCHAR2  N
     * isGlobal  是否全局  VARCHAR2  N
     * orderType  订单类型  VARCHAR2  N
     * erpOrderNo  erp订单编号  VARCHAR2  N
     * bazaarOrderNo  市场订单  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * buyerExecuteId  采购履行专员  NUMBER  N
     * organizationId  库存组织  NUMBER  N
     * prNumber  采购申请单号  VARCHAR2  N
     * locationCode  收货地点  VARCHAR2  N
     * poType  类型  VARCHAR2  N
     * shipToOrganizationId  收货组织  NUMBER  N
     * shipLocationCode    VARCHAR2  N
     * shipToLocationCode  收货方  VARCHAR2  N
     * billToLocationCode  收单方  VARCHAR2  N
     * shipToLocationId  收货方  NUMBER  N
     * billToLocationId  收单方  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * isGlobal  是否全局  VARCHAR2  N
     * agreementClause  协议条款  VARCHAR2  N
     * contractId  合同ID，关联表：srm_okc_contracts  NUMBER  N
     * contractCode  合同编号  VARCHAR2  N
     * contractTemplateId  合同模板ID，关联表：srm_okc_contract_templates  NUMBER  N
     * contractFileId  合同文档ID  NUMBER  N
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
    public JSONObject saveOrSubmitOrderInfo(JSONObject jsonParams) throws Exception {
        Integer poHeaderId = jsonParams.getInteger("poHeaderId");
        Integer operatorUserId = jsonParams.getInteger("operatorUserId");
        String operatorType = jsonParams.getString("operatorType");
        BigDecimal zero = new BigDecimal("0");
        SrmPoHeadersEntity_HI header = null;
        String tipMsg = null;
        String headerStatus = null;
        Date date = new Date();
        String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
        Integer supplierId = jsonParams.getInteger("supplierId");
        if (null == supplierId) {
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "供应商必填", 0, null);
        }
        if (null == jsonParams.getInteger("orgId")) {
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "业务实体必填", 0, null);
        }
        if (null == jsonParams.getInteger("supplierSiteId")) {
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "地点必填", 0, null);
        }
        SrmPosSupplierInfoEntity_HI supplier = srmPosSupplierInfoDAO_HI.getById(supplierId);
        /**
         * 不同操作，状态不同
         */
        if ("SAVE".equals(operatorType)) {
            headerStatus = jsonParams.getString("status");
            tipMsg = "保存";
        } else if ("UPDATE".equals(operatorType)) {
            headerStatus = "DRAFT";
            tipMsg = "更新";
        } else if ("SUBMIT".equals(operatorType)) {
            headerStatus = "SUBMITTED";
            tipMsg = "提交";
        } else if ("RECEIVE".equals(operatorType)) {
            headerStatus = jsonParams.getString("status");
            tipMsg = "保存";
        }
        //判断供应商是否存在或黑名单
        if (null == supplier || (null != supplier.getBlacklistFlag() && supplier.getBlacklistFlag().equals("Y"))) {
            return SToolUtils.convertResultJSONObj("E", tipMsg + "失败,供应商不存在或是黑名单!", 0, null);
        }
        // 如果为空是新增，不为空是更新
        if (null == poHeaderId) {
            header = new SrmPoHeadersEntity_HI();
            // 自动生成采购订单号
            String poNumber = saafSequencesUtil.getDocSequences("srm_po_headers", "PO-", dateFromate, 4);
            //update by xie shaoan 2018-08-27
//				String poNumberStr = "PO-"+dateFromate;
//				String poNumber = iSrmPoHeaders.getNewPoNumber(poNumberStr);//订单编号
            header.setPoNumber(poNumber);
            header.setPoVersions(new BigDecimal("0"));
        } else {
            header = SrmPoHeadersDAO_HI.getById(poHeaderId);
        }
        header.setOrgId(jsonParams.getInteger("orgId"));
        header.setPoType(jsonParams.getString("poType"));
        if ("LOGISTICS".equals(jsonParams.getString("poType"))) {
            if (!ObjectUtils.isEmpty(jsonParams.getString("logiShipToLocationCode"))) {
                header.setLogiShipToLocationCode(jsonParams.getString("logiShipToLocationCode"));
            }
            if (!ObjectUtils.isEmpty(jsonParams.getString("logiBillToLocationCode"))) {
                header.setLogiBillToLocationCode(jsonParams.getString("logiBillToLocationCode"));
            }
            header.setBillToLocationId(null);
            header.setShipToLocationId(null);
        } else {
            if (!ObjectUtils.isEmpty(jsonParams.getInteger("billToLocationId"))) {
                header.setBillToLocationId(jsonParams.getInteger("billToLocationId"));
            }
            if (!ObjectUtils.isEmpty(jsonParams.getInteger("shipToLocationId"))) {
                header.setShipToLocationId(jsonParams.getInteger("shipToLocationId"));
            }
            header.setLogiShipToLocationCode(null);
            header.setLogiBillToLocationCode(null);
        }

        header.setBillToOrganizationId(jsonParams.getInteger("billToOrganizationId"));
        header.setShipToOrganizationId(jsonParams.getInteger("shipToOrganizationId"));
        header.setPoDocType("ORDER");
        header.setSupplierId(jsonParams.getInteger("supplierId"));
        header.setSupplierSiteId(jsonParams.getInteger("supplierSiteId"));
        header.setCurrencyCode(jsonParams.getString("currencyCode"));
        header.setTaxRateCode(jsonParams.getString("taxRateCode"));
        header.setBuyerId(jsonParams.getInteger("employeeId"));
        header.setReturnGoodsType(jsonParams.getString("returnGoodsType"));
        header.setPaymentCondition(jsonParams.getString("paymentTermCode"));
        header.setSettlementWay(jsonParams.getString("settlementWay"));
        header.setContractId(jsonParams.getInteger("contractId"));
        header.setContractCode(jsonParams.getString("contractCode"));
        header.setStatus(headerStatus);
        header.setDescription(jsonParams.getString("description"));
        header.setPoFileId(jsonParams.getInteger("commonFileId") == null ? header.getPoFileId() : jsonParams.getInteger("commonFileId"));
        header.setLocationCode(jsonParams.getString("locationCode"));
        header.setOrganizationId(jsonParams.getInteger("organizationId"));
        header.setOperatorUserId(operatorUserId);
        SrmPoHeadersDAO_HI.saveOrUpdate(header);
        boolean demandFlag = true;
        JSONArray lineArray = jsonParams.getJSONArray("lineData");
        if (null != lineArray && lineArray.size() != 0) {
            List<SrmPoLinesEntity_HI> lineList = new ArrayList<SrmPoLinesEntity_HI>();
            for (int i = 0; i < lineArray.size(); i++) {
                JSONObject jsonObj = lineArray.getJSONObject(i);
                SrmPoLinesEntity_HI line = null;
                Integer poLineId = jsonObj.getInteger("poLineId");
                if (null == poLineId) {
                    line = new SrmPoLinesEntity_HI();
                    line.setPoHeaderId(header.getPoHeaderId());
                    line.setLineNumber((i + 1));
                    if ("SUBMITTED".equals(headerStatus)) {
                        line.setStatus("OPEN");
                    } else {
                        line.setStatus("DRAFT");
                    }

                    line.setOnWayQty(new BigDecimal("0"));
                    line.setReceivedQty(new BigDecimal("0"));
                    line.setReturnQty(new BigDecimal("0"));
                    line.setOriginalDemandQty(null);
                    line.setOriginalDemandDate(null);
                    line.setFeedbackAdjustDate(null);
                    line.setFeedbackAdjustQty(null);
                    line.setFeedbackStatus("NON_FEEDBACK");
                    line.setFeedbackResult("CONFIRM");
                    line.setRejectReason(null);
                } else {
                    line = SrmPoLinesDAO_HI.getById(poLineId);
                    if ("SUBMITTED".equals(headerStatus) && "DRAFT".equals(line.getStatus())) {
                        line.setStatus("OPEN");
                    } else {
                        line.setStatus(jsonObj.getString("lineStatus"));
                    }
                    line.setReceivedQty(jsonObj.getBigDecimal("receivedQty"));
                    line.setReturnQty(jsonObj.getBigDecimal("returnQty"));
                }
                line.setItemId(jsonObj.getInteger("itemId"));
                line.setItemName(jsonObj.getString("itemName"));
                line.setItemSpec(jsonObj.getString("itemSpec"));
                line.setCategoryId(jsonObj.getInteger("categoryId"));
                line.setContractId(jsonObj.getInteger("contractId"));
                line.setContractItemId(jsonObj.getInteger("contractItemId"));
                line.setDemandQty(jsonObj.getBigDecimal("demandQty"));
                line.setNonTaxPrice(jsonObj.getBigDecimal("nonTaxPrice"));
                //判断是否验收
                if ("RECEIVE".equals(operatorType) && (line.getReceivedQty().compareTo(zero) != 0 || line.getReturnQty().compareTo(zero) != 0)) {
                    line.setStatus("RECEIVED");
                }

                line.setTaxRateCode(jsonObj.getString("taxRateCode"));
                line.setTaxPrice(computerTaxPrice(jsonObj.getString("nonTaxPrice"), line.getTaxRateCode()));// 计算得出
                line.setTaxTotalPrice(line.getTaxPrice().multiply(line.getDemandQty()).setScale(4, BigDecimal.ROUND_HALF_UP));
                line.setNonTaxTotalPrice(line.getNonTaxPrice().multiply(line.getDemandQty()).setScale(4, BigDecimal.ROUND_HALF_UP));
                line.setTaxActTotalPrice(line.getTaxPrice().multiply(line.getReceivedQty()).setScale(4, BigDecimal.ROUND_HALF_UP));
                line.setNonTaxActTotalPrice(line.getNonTaxPrice().multiply(line.getReceivedQty()).setScale(4, BigDecimal.ROUND_HALF_UP));
                line.setDemandDate(jsonObj.getDate("demandDate"));
                line.setReceiveToOrganizationId(jsonObj.getInteger("receiveToOrganizationId"));
                line.setDescription(jsonObj.getString("lineDescription"));
                line.setMayNoticeQty(jsonObj.getBigDecimal("demandQty").subtract(line.getOnWayQty()));
                line.setContext(jsonObj.getString("context"));
                line.setProjectCategory(jsonObj.getString("projectCategory"));
                line.setProjectType(jsonObj.getString("projectType"));
                line.setTechnicalTransNumber(jsonObj.getString("technicalTransNumber"));
                line.setSubprojectNumber(jsonObj.getString("subprojectNumber"));
                line.setAcceptanceProcessNumber(jsonObj.getString("acceptanceProcessNumber"));
                line.setPoLineCombId(jsonObj.getInteger("poLineCombId"));
                line.setExpenseItemCode(jsonObj.getString("expenseItemCode"));
                line.setOperatorUserId(operatorUserId);
                line.setLineNumber(i + 1);//重新设置行号
                lineList.add(line);
            }
            SrmPoLinesDAO_HI.saveOrUpdateAll(lineList);
            SrmPoLinesDAO_HI.fluch();
            for (int i = 0; i < lineArray.size(); i++) {
                JSONObject jsonObj = lineArray.getJSONObject(i);
                //检查采购订单数量是否超过采购申请
                if ("SUBMIT".equals(operatorType)) {
                    demandFlag = checkDemandQtyCount(header, jsonObj);
                    if (!demandFlag) {
                        System.out.println("采购订单数量超过采购申请");
                        break;
                    }
                }
            }
        }
        if ("SUBMIT".equals(operatorType)) {
            if(!demandFlag){
                header.setStatus("DRAFT");
                SrmPoHeadersDAO_HI.saveOrUpdate(header);
                return SToolUtils.convertResultJSONObj("E", tipMsg + "失败!采购订单数量超过采购申请", 0, null);
            }

            if (!ObjectUtils.isEmpty(header.getContractId())) {
                if ("SUBMITTED".equals(headerStatus)) {
                    //提交回写合同已转订单数及剩余数量
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("contractId", header.getContractId());
                    StringBuffer hql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_TRANSFERREDPOQUANTITY_BY_CONTRACT);
                    List<SrmPoHeadersEntity_HI_RO> sumReceivedQtyList = SrmPoHeadersDAO_HI_RO.findList(hql.toString(), map);
                    if (!ObjectUtils.isEmpty(sumReceivedQtyList)) {
                        for (int i = 0; i < sumReceivedQtyList.size(); i++) {
                            SrmPoHeadersEntity_HI_RO sumReceivedQty = sumReceivedQtyList.get(i);
                            SrmOkcContractItemsEntity_HI okcItem = srmOkcContractItemsDAO_HI.getById(sumReceivedQty.getContractItemId());
                            if (!ObjectUtils.isEmpty(sumReceivedQty.getTransferredPoQuantity())) {
                                if(sumReceivedQty.getTransferredPoQuantity().compareTo(okcItem.getTransferredPoQuantity())==1){
                                    throw new UtilsException("物料需求数量不得大于对应合同标的物行剩余数量");
                                }
                                okcItem.setTransferredPoQuantity(sumReceivedQty.getTransferredPoQuantity());
                                okcItem.setRemainQuantity(okcItem.getPurchaseQuantity().subtract(okcItem.getTransferredPoQuantity()).setScale(4, BigDecimal.ROUND_HALF_UP));
                                okcItem.setOperatorUserId(operatorUserId);
                                srmOkcContractItemsDAO_HI.saveOrUpdate(okcItem);
                                srmOkcContractItemsDAO_HI.fluch();
                            }
                        }
                    }
                }
            }
        }


        if ("RECEIVE".equals(operatorType)) {
            if (!ObjectUtils.isEmpty(header.getContractId())) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("contractId", header.getContractId());
                StringBuffer sql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_NONTAXACTTOTALPRICE_BY_CONTRACT);
                List<SrmPoHeadersEntity_HI_RO> list = SrmPoHeadersDAO_HI_RO.findList(sql.toString(), map);
                //回写合同头验收金额
                if (!ObjectUtils.isEmpty(list) && !ObjectUtils.isEmpty(list.get(0).getNonTaxActTotalPrice())) {
                    SrmOkcContractsEntity_HI okcHeader = srmOkcContractsDAO_HI.getById(header.getContractId());
                    okcHeader.setReceivedAmount(list.get(0).getNonTaxActTotalPrice());
                    okcHeader.setOperatorUserId(operatorUserId);
                    srmOkcContractsDAO_HI.saveOrUpdate(okcHeader);
                    srmOkcContractsDAO_HI.fluch();
                }

                //回写行验收数量（已验收行实收数量）
                StringBuffer hql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_RECEIVED_QTY_BY_CONTRACT);
                List<SrmPoHeadersEntity_HI_RO> sumReceivedQtyList = SrmPoHeadersDAO_HI_RO.findList(hql.toString(), map);

                //回写已转订单数(未验收行需求数量+已验收行实收数量)
                StringBuffer tql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_DEMAND_QTY_BY_CONTRACT);
                List<SrmPoHeadersEntity_HI_RO> sumDemandQtyList = SrmPoHeadersDAO_HI_RO.findList(tql.toString(), map);

                if (!ObjectUtils.isEmpty(sumReceivedQtyList)) {
                    for (int i = 0; i < sumReceivedQtyList.size(); i++) {
                        SrmPoHeadersEntity_HI_RO sumReceivedQty = sumReceivedQtyList.get(i);
                        SrmOkcContractItemsEntity_HI okcItem = srmOkcContractItemsDAO_HI.getById(sumReceivedQty.getContractItemId());
                        if (!ObjectUtils.isEmpty(sumReceivedQty.getSumReceivedQty())) {
                            okcItem.setReceivedQuantity(sumReceivedQty.getSumReceivedQty());
                            if (!ObjectUtils.isEmpty(sumDemandQtyList)) {
                                for (int n = 0; n < sumDemandQtyList.size(); n++) {
                                    SrmPoHeadersEntity_HI_RO sumDemandQty = sumDemandQtyList.get(n);
                                    if (sumReceivedQty.getContractItemId().equals(sumDemandQty.getContractItemId()) && !ObjectUtils.isEmpty(sumDemandQty.getSumDemandQty())) {
                                        okcItem.setTransferredPoQuantity(sumReceivedQty.getSumReceivedQty().add(sumDemandQty.getSumDemandQty()).setScale(4, BigDecimal.ROUND_HALF_UP));
                                        okcItem.setRemainQuantity(okcItem.getPurchaseQuantity().subtract(okcItem.getTransferredPoQuantity()).setScale(4, BigDecimal.ROUND_HALF_UP));
                                    }
                                }
                            }
                            okcItem.setOperatorUserId(operatorUserId);
                            srmOkcContractItemsDAO_HI.saveOrUpdate(okcItem);
                        }
                    }
                }
            }
            updatePoStatus(header, operatorUserId);
        }
        return SToolUtils.convertResultJSONObj("S", tipMsg + "成功!", 1, header);
    }

    /**
     * Description：招标-已完成-创建采购订单
     * <p>
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * winningSupplierQuantity  推荐中标供应商数量  NUMBER  N
     * winningWay  推荐中标方式，关联表：SAAF_LOOKUP_VALUES（SNBC_ZB_WINNING_WAY）  VARCHAR2  N
     * oaCode  OA编号  VARCHAR2  N
     * technologyBidFlag  是否是技术标（Y/N）  VARCHAR2  N
     * technologyFileId  技术标附件id  NUMBER  N
     * expenseConfirm  是否费用确认（Y/N）  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * exchangeRateDate  汇率日期  DATE  N
     * exchangeRate  汇率  NUMBER  N
     * itemType  洽谈类型，快码：PON_AUCTION_ITEM_TYPE  VARCHAR2  N
     * itemPurchaseFlag  是否为代购物料（Y/N）  VARCHAR2  N
     * auctionHeaderId  洽谈ID  NUMBER  Y
     * auctionNumber  洽谈编号  VARCHAR2  Y
     * auctionTitle  洽谈标题  VARCHAR2  Y
     * orgId  洽谈主体单位，关联表：SAAF_INSTITUTION  NUMBER  Y
     * auctionType  洽谈类型，快码：PON_AUCTION_TYPE  VARCHAR2  N
     * contractType  合同类型（招标结果），快码：PON_AUCTION_RESULT  VARCHAR2  N
     * auctionStatus  洽谈状态，快码：PON_AUCTION_STATUS  VARCHAR2  N
     * buyerId  采购员(创建人)，关联表：SAAF_EMPLOYEES 的 EMPLOYEE_ID  NUMBER  N
     * invitingBidWay  邀标方式  VARCHAR2  N
     * receiveToOrganizationId  收货组织ID  NUMBER  N
     * receiveToAddress  收货/收单地址  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * paymentConditionUpdateFlag  允许供应商修改付款条件（Y/N）  VARCHAR2  N
     * subsectionFlag  分段价格（Y/N）  VARCHAR2  N
     * bidStartDate  报价开始时间  DATE  N
     * bidEndDate  报价截止时间  DATE  N
     * currencyCode  币种，关联表：SAAF_LOOKUP_VALUES（BANK_CURRENCY）  VARCHAR2  N
     * numberPriceDecimals  报价的小数位  NUMBER  N
     * allowUpdateTaxRate  允许修改税率（Y/N）  VARCHAR2  N
     * showCurrentRoundMinPrice  向供应商展示本轮最低价格（Y/N）  VARCHAR2  N
     * showCurrentRoundRanking  向供应商展示本轮排名（Y/N）  VARCHAR2  N
     * allItemBidFlag  供应商必须回应所有行（Y/N）  VARCHAR2  N
     * multipleBidFlag  允许多次报价（Y/N）  VARCHAR2  N
     * maxBidFrequency  多次报价次数  NUMBER  N
     * minDecreasingRange  最小降幅  NUMBER  N
     * bidBond  保证金金额  NUMBER  N
     * bidBondTerm  保证金缴纳期限  DATE  N
     * bidBondAccountNumber  保证金缴纳账户  VARCHAR2  N
     * bidBondBankName  保证金缴纳账户开户行  VARCHAR2  N
     * tenderCost  标书费用  NUMBER  N
     * publishApprovalStatus  发布审批状态  VARCHAR2  N
     * publishApprovalDate  发布批准时间  DATE  N
     * publishDate  发布时间  DATE  N
     * closeBiddingDate  截标时间  DATE  N
     * noteToSupplier  给供应商的附注  VARCHAR2  N
     * noteToJury  给评标小组的附注  VARCHAR2  N
     * toSupplierFileId  给供应商的附件ID  NUMBER  N
     * toJuryFileId  给评标小组的附件ID  NUMBER  N
     * allowScoreFlag  是否可评分标识  VARCHAR2  N
     * rounds  第几轮  NUMBER  N
     * firstRound  第一轮ID  NUMBER  N
     * lastRound  上一轮ID  NUMBER  N
     * auctionRoundNumber  洽谈轮次编号  VARCHAR2  N
     * openBiddingFlag  是否开标（Y/N）  VARCHAR2  N
     * openBiddingDate  开标时间  DATE  N
     * judgeCompleteDate  评标完成时间  DATE  N
     * awardStatus  决标状态  VARCHAR2  N
     * awardApprovalStatus  决标审批状态  VARCHAR2  N
     * awardCompleteDate  决标完成时间  DATE  N
     * awardComments  决标说明  VARCHAR2  N
     * poCreateFlag  是否已创建采购订单（Y/N）  VARCHAR2  N
     * poCreateDate  采购订单创建时间  DATE  N
     * percent    NUMBER  N
     * taxRateCode  税率  VARCHAR2  N
     * auctionRoundNumber  洽谈轮次编号  VARCHAR2  N
     * openBiddingFlag  是否开标（Y/N）  VARCHAR2  N
     * openBiddingDate  开标时间  DATE  N
     * judgeCompleteDate  评标完成时间  DATE  N
     * awardStatus  决标状态  VARCHAR2  N
     * awardApprovalStatus  决标审批状态  VARCHAR2  N
     * awardCompleteDate  决标完成时间  DATE  N
     * awardComments  决标说明  VARCHAR2  N
     * poCreateFlag  是否已创建采购订单（Y/N）  VARCHAR2  N
     * poCreateDate  采购订单创建时间  DATE  N
     * templateId  合同模板ID  NUMBER  N
     * templateName  合同模板名称  VARCHAR2  N
     * templateCode  合同模板编码  VARCHAR2  N
     * itemType  寻源物料类型  VARCHAR2  N
     * ekpNumber  EKP编号  VARCHAR2  N
     * auctionHeaderId  洽谈ID  NUMBER  Y
     * auctionNumber  洽谈编号  VARCHAR2  Y
     * auctionTitle  洽谈标题  VARCHAR2  Y
     * orgId  洽谈主体单位，关联表：SAAF_INSTITUTION  NUMBER  Y
     * auctionType  洽谈类型，快码：PON_AUCTION_TYPE  VARCHAR2  N
     * contractType  合同类型（招标结果），快码：PON_AUCTION_RESULT  VARCHAR2  N
     * auctionStatus  洽谈状态，快码：PON_AUCTION_STATUS  VARCHAR2  N
     * buyerId  采购员，关联表：SAAF_EMPLOYEES 的 EMPLOYEE_ID  NUMBER  N
     * invitingBidWay  邀标方式  VARCHAR2  N
     * receiveToOrganizationId  收货组织ID  NUMBER  N
     * receiveToAddress  收货/收单地址  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * paymentConditionUpdateFlag  允许供应商修改付款条件（Y/N）  VARCHAR2  N
     * subsectionFlag  分段价格（Y/N）  VARCHAR2  N
     * bidStartDate  报价开始时间  DATE  N
     * bidEndDate  报价截止时间  DATE  N
     * currencyCode  币种，关联表：SAAF_LOOKUP_VALUES（BANK_CURRENCY）  VARCHAR2  N
     * numberPriceDecimals  报价的小数位  NUMBER  N
     * allowUpdateTaxRate  允许修改税率（Y/N）  VARCHAR2  N
     * showCurrentRoundMinPrice  向供应商展示本轮最低价格（Y/N）  VARCHAR2  N
     * showCurrentRoundRanking  向供应商展示本轮排名（Y/N）  VARCHAR2  N
     * allItemBidFlag  供应商必须回应所有行（Y/N）  VARCHAR2  N
     * multipleBidFlag  允许多次报价（Y/N）  VARCHAR2  N
     * maxBidFrequency  多次报价次数  NUMBER  N
     * minDecreasingRange  最小降幅  NUMBER  N
     * bidBond  保证金金额  NUMBER  N
     * bidBondTerm  保证金缴纳期限  DATE  N
     * bidBondAccountNumber  保证金缴纳账户  VARCHAR2  N
     * bidBondBankName  保证金缴纳账户开户行  VARCHAR2  N
     * tenderCost  标书费用  NUMBER  N
     * publishApprovalStatus  发布审批状态  VARCHAR2  N
     * publishApprovalDate  发布批准时间  DATE  N
     * publishDate  发布时间  DATE  N
     * closeBiddingDate  截标时间  DATE  N
     * noteToSupplier  给供应商的附注  VARCHAR2  N
     * noteToJury  给评标小组的附注  VARCHAR2  N
     * toSupplierFileId  给供应商的附件ID  NUMBER  N
     * toJuryFileId  给评标小组的附件ID  NUMBER  N
     * allowScoreFlag  是否可评分标识  VARCHAR2  N
     * rounds  第几轮  NUMBER  N
     * firstRound  第一轮ID  NUMBER  N
     * lastRound  上一轮ID  NUMBER  N
     * <p>
     * Update History
     * =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞             创建
     * =======================================================================
     */

    @Override
    public JSONObject updateAndCreatePurchaseOrders(JSONObject jsonParams) throws Exception {
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId");
        Integer bidHeaderId = jsonParams.getInteger("bidHeaderId");
        Integer operatorUserId = jsonParams.getInteger("operatorUserId");
        SrmPonAuctionHeadersEntity_HI auctionHeader = srmPonAuctionHeadersDAO_HI.getById(auctionHeaderId);
        if ("Y".equals(auctionHeader.getPoCreateFlag())) {
            return SToolUtils.convertResultJSONObj("E", "已创建订单或协议！", 1, null);
        }
        StringBuffer queryBidSuppliersSql = new StringBuffer(SrmPonAuctionItemsEntity_HI_RO.QUERY_AUCTION_ITEMS_BID_SQL);
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("auctionHeaderId", auctionHeaderId);
        queryBidSuppliersSql.append(" and pbh.bid_header_id = " + bidHeaderId);
        List<SrmPonAuctionItemsEntity_HI_RO> distinctItems = srmPonAuctionItemsDAO_HI_RO.findList(queryBidSuppliersSql, queryMap);

        StringBuffer queryjurieSql = new StringBuffer(SrmPonAuctionJuriesEntity_HI_RO.QUERY_AUCTION_JURY_SQL);
        queryjurieSql.append(" AND t.auction_header_id =" + auctionHeaderId + " ");
        queryjurieSql.append(" AND t.user_duty = 1 ");
        SrmPonAuctionJuriesEntity_HI_RO jurie = srmPonAuctionJuriesDAO_HI_RO.findList(queryjurieSql).get(0);

        SrmPonAuctionItemsEntity_HI_RO ponAuctionItemsEntity = null;
        SrmPoHeadersEntity_HI header = null;
        Date date = new Date();
        String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
        List<SrmPoLinesEntity_HI> lineList = new ArrayList();
        String poNumber = null;
        SrmPoAgreementAssignsEntity_HI assignsEntityHI = null;
        int lineNumber = 1;
        StringBuffer querySuppliersSql = null;
        List<SrmPonAuctionItemsEntity_HI_RO> itemsList = null;
        SrmPoLinesEntity_HI poLine = null;
        SrmPonAuctionItemLaddersEntity_HI laddersEntity = null;
        ArrayList<Date> startDateArr = null;
        ArrayList<Date> endDateArr = null;
        //创建订单或协议
        for (int l = 0; l < distinctItems.size(); l++) {
            ponAuctionItemsEntity = distinctItems.get(l);
            header = new SrmPoHeadersEntity_HI();
            if ("1".equals(auctionHeader.getContractType())) {
                header.setPoDocType("ORDER");
                poNumber = saafSequencesUtil.getDocSequences("srm_po_headers", "PO-", dateFromate, 4);
            } else if ("2".equals(auctionHeader.getContractType())) {
                header.setPoDocType("AGREEMENT");
                poNumber = saafSequencesUtil.getDocSequences("srm_po_headers", "XY-", dateFromate, 3);
            }
            // 自动生成采购订单号
            header.setPoNumber(poNumber);
            header.setPoVersions(new BigDecimal("0"));
            header.setOrgId(auctionHeader.getOrgId());
            header.setOrganizationId(auctionHeader.getOrganizationId());
            header.setBillToOrganizationId(auctionHeader.getReceiveToOrganizationId());
            header.setSupplierId(ponAuctionItemsEntity.getSupplierId());
            header.setSupplierSiteId(ponAuctionItemsEntity.getSupplierSiteId());
            header.setCurrencyCode(auctionHeader.getCurrencyCode());
            header.setTaxRateCode(ponAuctionItemsEntity.getTaxRateCode());
            header.setBuyerId(jurie.getEmployeeId());
            header.setPaymentCondition(auctionHeader.getPaymentCondition());
            header.setStatus("DRAFT");
            header.setSourceCode("srm_pon_bid_headers");
            header.setSourceId(ponAuctionItemsEntity.getBidHeaderId().toString());
            header.setOperatorUserId(operatorUserId);
            header.setAuctionNumber(auctionHeader.getAuctionNumber());
            header.setPoType(auctionHeader.getItemType());
            SrmPoHeadersDAO_HI.save(header);

            //如果是协议，则需要生成协议的组织分配信息
            if ("2".equals(auctionHeader.getContractType())) {
                assignsEntityHI = new SrmPoAgreementAssignsEntity_HI();
                assignsEntityHI.setPoHeaderId(header.getPoHeaderId());
                assignsEntityHI.setOrgId(auctionHeader.getOrgId());
                    /*assignsEntityHI.setBillToOrganizationId(auctionHeader.getReceiveToOrganizationId());
                    assignsEntityHI.setReceiveToOrganizationId(auctionHeader.getReceiveToOrganizationId());*/
                assignsEntityHI.setDefaultFlag("N");
                assignsEntityHI.setOperatorUserId(operatorUserId);
                srmPoAgreementAssignsDAO_HI.save(assignsEntityHI);
            }

            //先生成物料主数据，再创建订单行
            JSONObject jsonObject = iSrmBaseItemsB.createPonMaterialMaster(jsonParams);

            //创建订单行
            querySuppliersSql = new StringBuffer(SrmPonAuctionItemsEntity_HI_RO.QUERY_AUCTION_ITEMS_BID_LINE_SQL);
            queryMap = new HashMap();
            queryMap.put("taxRateCode", ponAuctionItemsEntity.getTaxRateCode());
            queryMap.put("bidHeaderId", ponAuctionItemsEntity.getBidHeaderId());
            itemsList = srmPonAuctionItemsDAO_HI_RO.findList(querySuppliersSql, queryMap);

            lineNumber = 1;
            startDateArr = new ArrayList();
            endDateArr = new ArrayList();
            for (SrmPonAuctionItemsEntity_HI_RO item : itemsList) {
                poLine = new SrmPoLinesEntity_HI();
                poLine.setPoHeaderId(header.getPoHeaderId());
                poLine.setOnWayQty(new BigDecimal("0"));
                poLine.setReceivedQty(new BigDecimal("0"));
                poLine.setLineNumber(lineNumber);
                //poLine.setStatus("OPEN");
                poLine.setStatus("DRAFT");
                poLine.setItemId(item.getItemId());
                poLine.setItemName(item.getItemDescription());
                poLine.setCategoryId(item.getCategoryId());
                //订单数量为中标数量
                poLine.setDemandQty(item.getAwardQuantity());
                //协议日期，最小采购数量
                if ("2".equals(auctionHeader.getContractType())) {
                    poLine.setMinPoQty(item.getQuantity());
                    poLine.setStartDate(item.getStartDate());
                    poLine.setEndDate(item.getEndDate());
                    startDateArr.add(item.getStartDate());
                    endDateArr.add(item.getEndDate());
                }
                poLine.setNonTaxPrice(item.getNoTaxPrice());
                poLine.setTaxPrice(item.getTaxPrice());
                poLine.setDemandDate(item.getStartDate());
                poLine.setReceiveToOrganizationId(header.getBillToOrganizationId());
                //可通知数量为中标数量
                poLine.setMayNoticeQty(item.getAwardQuantity());
                poLine.setOriginalDemandQty(item.getQuantity());
                poLine.setOriginalDemandDate(item.getEndDate());
                poLine.setSourceId(String.valueOf(auctionHeaderId));
                poLine.setSourceCode(auctionHeader.getAuctionType());
                //存在阶梯数量
                if ("Y".equals(auctionHeader.getSubsectionFlag())) {
                    laddersEntity = srmPonAuctionItemLaddersDAO_HI.getById(item.getItemLadderId());
                    if (laddersEntity != null) {
                        poLine.setLadderPriceFlag("Y");
                        poLine.setLadderQty(laddersEntity.getLadderQuantity());
                    }
                }
                poLine.setSourceCode("srm_pon_bid_item_prices");
                poLine.setSourceId(item.getBidLineId().toString());
                poLine.setFeedbackStatus("NON_FEEDBACK");
                poLine.setFeedbackResult("CONFIRM");
                poLine.setTaxRateCode(item.getTaxRateCode());
                poLine.setOperatorUserId(operatorUserId);
                lineList.add(poLine);
                lineNumber++;
            }
            SrmPoLinesDAO_HI.save(lineList);

            //更新订单头的起始日期
            if (startDateArr.size() > 0) {
                header.setStartDate(Collections.min(startDateArr));
            }
            if (endDateArr.size() > 0) {
                header.setEndDate(Collections.max(endDateArr));
            }
            SrmPoHeadersDAO_HI.update(header);

            //招标询价转订单，回写招标询价转订单标识
            if (!ObjectUtils.isEmpty(bidHeaderId)) {
                SrmPonBidHeadersEntity_HI bidHeader = srmPonBidHeadersDAO_HI.getById(bidHeaderId);
                bidHeader.setPoCreateFlag("Y");
                bidHeader.setPoCreateDate(date);
                bidHeader.setOperatorUserId(operatorUserId);
                srmPonBidHeadersDAO_HI.saveOrUpdate(bidHeader);
                List<SrmPonBidHeadersEntity_HI> bidHeaderList = srmPonBidHeadersDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
                Boolean flag = true;
                if (!ObjectUtils.isEmpty(bidHeaderList)) {
                    for (int n = 0; n < bidHeaderList.size(); n++) {
                        SrmPonBidHeadersEntity_HI bid = bidHeaderList.get(n);
                        if (!bidHeader.getBidHeaderId().equals(bid.getBidHeaderId()) && "4".equals(bid.getAwardStatus()) && !"Y".equals(bid.getPoCreateFlag()) && "ACT".equals(bid.getBidStatus())) {
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    auctionHeader.setPoCreateFlag("Y");
                    auctionHeader.setPoCreateDate(date);
                    auctionHeader.setOperatorUserId(operatorUserId);
                    srmPonAuctionHeadersDAO_HI.saveOrUpdate(auctionHeader);
                }
            }

        }
        /*//回写标书的状态
        auctionHeader.setPoCreateFlag("Y");
        auctionHeader.setPoCreateDate(date);
        auctionHeader.setOperatorUserId(operatorUserId);
        srmPonAuctionHeadersDAO_HI.update(auctionHeader);*/
        return SToolUtils.convertResultJSONObj("S", "创建订单或协议成功！", 1, header);
    }

    /**
     * Description：采购订单内部操作(订单头) 拒绝,确认,审批,关闭,取消,变更
     *
     * @param jsonParams 条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updateOrderHeaderStatus(JSONObject jsonParams) throws Exception {
        String operatorType = jsonParams.getString("operatorType");
        Integer poHeaderId = jsonParams.getInteger("poHeaderId");
        Integer operatorUserId = jsonParams.getInteger("operatorUserId");
        String feedbackStatus = null;
        String headerStatus = null;
        String tipMsg = null;

        //try {
        SrmPoHeadersEntity_HI header = SrmPoHeadersDAO_HI.getById(poHeaderId);

        if (header == null) {
            return SToolUtils.convertResultJSONObj("E", tipMsg + "失败,找不到订单数据!", 0, null);
        }

        if ("REJECT".equals(operatorType)) {// 拒绝
            headerStatus = "REJECTED";
            tipMsg = "拒绝";
            List<SrmPoLinesEntity_HI> lineList = SrmPoLinesDAO_HI.findByProperty("poHeaderId", poHeaderId);
            if (lineList != null && lineList.size() != 0) {
                for (SrmPoLinesEntity_HI line : lineList) {
                    if ("OPEN".equals(line.getStatus())) {
                        line.setStatus("DRAFT");
                        line.setOperatorUserId(operatorUserId);
                        SrmPoLinesDAO_HI.update(line);
                    }
                }
            }
        } else if ("APPROVE".equals(operatorType)) {// 审批
            headerStatus = "APPROVED";
            tipMsg = "审批";

            SrmPosSupplierInfoEntity_HI supplier = srmPosSupplierInfoDAO_HI.getById(header.getSupplierId());
            //判断供应商是否存在或黑名单
            if (null == supplier || (null != supplier.getBlacklistFlag() && supplier.getBlacklistFlag().equals("Y"))) {
                return SToolUtils.convertResultJSONObj("E", tipMsg + "失败,供应商不存在或是黑名单!", 0, null);
            }

            header.setApprovedDate(new Date());
            header.setStatus(headerStatus);

            if ("INFORMATION_TECHNOLOGY".equals(header.getPoType()) || "ENGINEERING".equals(header.getPoType())) {
                //保存合并行
                List<SrmPoLinesCombEntity_HI> linesCombList = this.saveOrderLineComb(header, operatorUserId);
                //订单同步至EBS系统,失败时不回退事务

                for (SrmPoLinesCombEntity_HI linesCombEntityHi : linesCombList) {
                    JSONArray jsonArray = pushOrderToEBS(tipMsg, header, linesCombEntityHi);
                    if (!ObjectUtils.isEmpty(jsonArray)) {
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        if ("S".equals(jsonObject.getString("PROCESS_CODE"))) {
                            if (ObjectUtils.isEmpty(linesCombEntityHi.getErpPoNumber())) {
                                linesCombEntityHi.setErpPoNumber(jsonObject.getString("DOCUMENT_NUM"));
                                linesCombEntityHi.setOperatorUserId(operatorUserId);
                                srmPoLinesCombDAO_HI.saveOrUpdate(linesCombEntityHi);
                                Map<String, Object> map = new HashMap<>();
                                map.put("poHeaderId", poHeaderId);
                                map.put("poLineCombId", linesCombEntityHi.getPoLineCombId());
                                List<SrmPoLinesEntity_HI> lineList = SrmPoLinesDAO_HI.findByProperty(map);
                                List<SrmPoLinesEntity_HI> newLineList = new ArrayList<>();
                                for (SrmPoLinesEntity_HI ro : lineList) {
                                    ro.setErpPoNumber(jsonObject.getString("DOCUMENT_NUM"));
                                    ro.setOperatorUserId(operatorUserId);
                                    newLineList.add(ro);
                                }
                                SrmPoLinesDAO_HI.updateAll(newLineList);
                            }
                        }
                    }
                }
            }

        } else if ("CLOSE".equals(operatorType)) {// 关闭
            headerStatus = "CLOSED";
            tipMsg = "关闭";
            JSONArray lineArray = jsonParams.getJSONArray("lineData");
            // 采购订单回货
            if ("BASE_ON_PO".equals(header.getReturnGoodsType())) {
                for (int i = 0; i < lineArray.size(); i++) {
                    JSONObject jsonObj = lineArray.getJSONObject(i);
                    BigDecimal demandQty = new BigDecimal(jsonObj.getString("demandQty") == null ? "0" : jsonObj.getString("demandQty"));
                    BigDecimal receivedQty = new BigDecimal(jsonObj.getString("receivedQty") == null ? "0" : jsonObj.getString("receivedQty"));
                    if (demandQty.subtract(receivedQty).intValue() <= 0) {
                        return SToolUtils.convertResultJSONObj("E", tipMsg + "失败!存在未完成的送货单，请先完成送货单接收，或取消送货单，再进行关闭。",
                                0, null);
                    }
                }
            } // 送货通知回货
            else if ("BASE_ON_NOTICE".equals(header.getReturnGoodsType())) {
                for (int i = 0; i < lineArray.size(); i++) {
                    JSONObject jsonObj = lineArray.getJSONObject(i);
                    BigDecimal demandQty = new BigDecimal(jsonObj.getString("demandQty") == null ? "0" : jsonObj.getString("demandQty"));
                    BigDecimal receivedQty = new BigDecimal(jsonObj.getString("receivedQty") == null ? "0" : jsonObj.getString("receivedQty"));
                    if (demandQty.subtract(receivedQty).intValue() <= 0) {
                        return SToolUtils.convertResultJSONObj("E",
                                tipMsg + "失败!存在未完成的送货通知单，请先完成送货通知单，或取消送货通知单，再进行关闭。", 0, null);
                    }
                }
            }

        } else if ("CANCEL".equals(operatorType)) {// 取消
            headerStatus = "CANCELLED";
            tipMsg = "取消";

                /*StringBuffer queryString = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_PO_CONVERTED_QTY);
                Map<String, Object> queryMap = new HashMap();
                queryMap.put("poHeaderId", header.getPoHeaderId());
                List<SrmPoHeadersEntity_HI_RO> poList = SrmPoHeadersDAO_HI_RO.findList(queryString, queryMap);
                if (poList != null && poList.size() > 0) {
                    SrmPoHeadersEntity_HI_RO poHeadersEntity = poList.get(0);
                    if (poHeadersEntity.getNotifiedQty().doubleValue() > 0 || poHeadersEntity.getDeliveryQty().doubleValue() > 0) {
                        return SToolUtils.convertResultJSONObj("E", tipMsg + "失败，已创建送货通知或送货单，不可取消！", 0, null);
                    }
                }*/
            List<SrmPoLinesEntity_HI> lineList = SrmPoLinesDAO_HI.findByProperty("poHeaderId", poHeaderId);
            List<SrmPoLinesEntity_HI> newLineList = new ArrayList<>();
            if (lineList != null && lineList.size() != 0) {
                for (SrmPoLinesEntity_HI line : lineList) {
                    if ("RECEIVED".equals(line.getStatus())) {
                        return SToolUtils.convertResultJSONObj("E", tipMsg + "失败，第" + line.getLineNumber() + "行已验收，不可取消整个订单！", 0, null);
                    } else {
                        line.setStatus("CANCELLED");
                        line.setOperatorUserId(operatorUserId);
                        newLineList.add(line);
                    }
                }
                SrmPoLinesDAO_HI.updateAll(newLineList);

                header.setStatus(headerStatus);

                    /*//取消合并行
                    List<SrmPoLinesCombEntity_HI> linesCombList = this.saveCancelOrderLineComb(header, operatorUserId);
                    //订单同步至EBS系统,失败时不回退事务
                    for (SrmPoLinesCombEntity_HI linesCombEntityHi : linesCombList){
                        JSONArray jsonArray = pushOrderToEBS(tipMsg, header, linesCombEntityHi);
                    }*/
                if ("INFORMATION_TECHNOLOGY".equals(header.getPoType()) || "ENGINEERING".equals(header.getPoType())) {
                    //取消合并行
                    List<SrmPoLinesCombEntity_HI> linesCombList = this.saveCancelOrderLineComb(header, operatorUserId);
                    //订单同步至EBS系统,失败时不回退事务
                    for (SrmPoLinesCombEntity_HI linesCombEntityHi : linesCombList) {
                        JSONArray jsonArray = pushOrderToEBS(tipMsg, header, linesCombEntityHi);
                    }
                }
            }
        } else if ("CHANGE".equals(operatorType)) {// 变更
            headerStatus = "UPDATED";
            feedbackStatus = "NON_FEEDBACK";
            tipMsg = "变更";
            //查询是否有状态为 打开 的行
            JSONObject params = new JSONObject();
            params.put("poHeaderId", poHeaderId);
            params.put("lineStatus", "OPEN");
            List<SrmPoLinesEntity_HI_RO> list = srmPoLinesServer.findPoLineSimpleList(params);
            if (null == list || list.size() == 0) {
                return SToolUtils.convertResultJSONObj("E",
                        tipMsg + "失败!不存在状态为打开的订单行。", 0, null);
            }

            //保存历史
            srmPoHeaderArchivesServer.savePoArchives(jsonParams);

            //新增版本
            header.setPoVersions(new BigDecimal("1").add(header.getPoVersions()));

            List<SrmPoLinesEntity_HI> lineList = SrmPoLinesDAO_HI.findByProperty("poHeaderId", poHeaderId);
            if (lineList != null && lineList.size() != 0) {
                for (SrmPoLinesEntity_HI line : lineList) {
                    if ("OPEN".equals(line.getStatus())) {
                        // line.setStatus("DRAFT");
                        line.setFeedbackStatus(feedbackStatus);
                        line.setFeedbackAdjustDate(null);
                        line.setFeedbackAdjustQty(null);
                        line.setRejectReason(null);
                        line.setOriginalDemandQty(line.getDemandQty());
                        line.setOriginalDemandDate(line.getDemandDate());
                        line.setOperatorUserId(operatorUserId);
                        SrmPoLinesDAO_HI.update(line);
                    }
                }
            }
        }
        header.setStatus(headerStatus);
        header.setOperatorUserId(operatorUserId);
        SrmPoHeadersDAO_HI.update(header);
        SrmPoHeadersDAO_HI.fluch();

        //审批动作过后需校验审批状态是否正确
        if ("APPROVE".equals(operatorType)) {
            updatePoStatus(header, operatorUserId);

            List<SrmPosSupplierContactsEntity_HI> contactsList = srmPosSupplierContactsDAO_HI.findByProperty("supplierId", header.getSupplierId());
            String content = "<p>您好！</p><br/>" + "您有新的销售订单: " + header.getPoNumber() + "等待处理！";
            //邮件
            if (contactsList != null && contactsList.size() > 0) {
                for (int c = 0; c < contactsList.size(); c++) {
                    String emailAddress = contactsList.get(c).getEmailAddress();
                    //发送邮件给账号的联系人
                    sendMailUtil.doSendHtmlEmail("订单通知", content, new String[]{emailAddress});
                }
            }

            //发送通知
            List<SaafUsersEntity_HI> usersList = saafUsersDAO_HI.findByProperty("supplierId", header.getSupplierId());
            if (null != usersList && usersList.size() > 0) {
                SaafUsersEntity_HI usersEntity = usersList.get(0);
                iSrmBaseNotifications.insertSrmBaseNotifications("订单通知", content
                        , usersEntity.getUserId(), "srm_po_headers", header.getPoHeaderId(), "poHeaderId", "home.purchaseOrderDetail", operatorUserId);
            }
            //发送通知-创建人
            iSrmBaseNotifications.insertSrmBaseNotifications("订单确认通知", "<p>您好！</p><br/>" + "您的订单: " + header.getPoNumber() + "已确认！"
                    , header.getCreatedBy(), "srm_po_headers", header.getPoHeaderId(), "poHeaderId", "home.purchaseOrderDetail", operatorUserId);
        }

        if ("CANCEL".equals(operatorType)||"REJECT".equals(operatorType)) {
            //回写已转订单数、剩余数
            if(!ObjectUtils.isEmpty(header.getContractId())){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("contractId", header.getContractId());
                StringBuffer hql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_TRANSFERREDPOQUANTITY_BY_CONTRACT);
                List<SrmPoHeadersEntity_HI_RO> sumReceivedQtyList = SrmPoHeadersDAO_HI_RO.findList(hql.toString(), map);
                if (!ObjectUtils.isEmpty(sumReceivedQtyList)) {
                    for (int i = 0; i < sumReceivedQtyList.size(); i++) {
                        SrmPoHeadersEntity_HI_RO sumReceivedQty = sumReceivedQtyList.get(i);
                        SrmOkcContractItemsEntity_HI okcItem = srmOkcContractItemsDAO_HI.getById(sumReceivedQty.getContractItemId());
                        if (!ObjectUtils.isEmpty(sumReceivedQty.getTransferredPoQuantity())) {
                            okcItem.setTransferredPoQuantity(sumReceivedQty.getTransferredPoQuantity());
                            okcItem.setRemainQuantity(okcItem.getPurchaseQuantity().subtract(okcItem.getTransferredPoQuantity()).setScale(4, BigDecimal.ROUND_HALF_UP));
                            okcItem.setOperatorUserId(operatorUserId);
                            srmOkcContractItemsDAO_HI.saveOrUpdate(okcItem);
                            srmOkcContractItemsDAO_HI.fluch();
                        }
                    }
                }
            }

        }



        return SToolUtils.convertResultJSONObj("S", tipMsg + "成功!", 1, null);
        /*} catch (Exception e) {
            return SToolUtils.convertResultJSONObj("E", tipMsg + "失败!" + e.getMessage(), 0, null);
        }*/
    }

    /**
     * Description：采购订单内部操作(订单行)
     * 拒绝,确认,审批,关闭,取消,变更
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updateOrderLineStatus(JSONObject jsonParams) {
        JSONArray lineArray = jsonParams.getJSONArray("lineData");
        String operatorType = jsonParams.getString("operatorType");
        Integer operatorUserId = jsonParams.getInteger("operatorUserId");
        String feedbackStatus = null;
        String lineStatus = null;
        String tipMsg = null;
        try {
            if ("REJECT".equals(operatorType)) {// 拒绝
                feedbackStatus = "REJECTED";
                lineStatus = "CANCELLED";
                tipMsg = "拒绝";

                for (int i = 0; i < lineArray.size(); i++) {
                    JSONObject jsonObj = lineArray.getJSONObject(i);
                    if ("Y".equals(jsonObj.getString("selectFlag"))) {
                        SrmPoLinesEntity_HI line = (SrmPoLinesEntity_HI) SrmPoLinesDAO_HI
                                .getById(jsonObj.getInteger("poLineId"));
                        if (null == line) {
                            return SToolUtils.convertResultJSONObj("E", tipMsg + "失败,找不到订单行数据!", 0, null);
                        }
                        line.setFeedbackStatus(feedbackStatus);
                        line.setStatus(lineStatus);
                        line.setOperatorUserId(operatorUserId);
                        SrmPoLinesDAO_HI.update(line);
                    }
                }
            } else if ("APPROVE".equals(operatorType)) {// 确认
                feedbackStatus = "APPROVED";
                tipMsg = "确认";

                for (int i = 0; i < lineArray.size(); i++) {
                    JSONObject jsonObj = lineArray.getJSONObject(i);
                    if ("Y".equals(jsonObj.getString("selectFlag"))) {
                        SrmPoLinesEntity_HI line = (SrmPoLinesEntity_HI) SrmPoLinesDAO_HI
                                .getById(jsonObj.getInteger("poLineId"));
                        if (null == line) {
                            return SToolUtils.convertResultJSONObj("E", tipMsg + "失败,找不到订单行数据!", 0, null);
                        }

                        line.setFeedbackStatus(feedbackStatus);

                        if (line.getFeedbackAdjustDate() != null) {
                            line.setOriginalDemandDate(line.getDemandDate());
                            line.setDemandDate(line.getFeedbackAdjustDate());
                        }

                        if (line.getFeedbackAdjustQty() != null) {
                            line.setOriginalDemandQty(line.getDemandQty());
                            line.setDemandQty(line.getFeedbackAdjustQty());
                            line.setMayNoticeQty(line.getFeedbackAdjustQty());//如果供应商反馈的送货数量存在，则将可通知送货量设为该值
                        }
                        line.setOperatorUserId(operatorUserId);
                        SrmPoLinesDAO_HI.update(line);
                    }
                }
            } else if ("S_APPROVE".equals(operatorType)) {
                feedbackStatus = "APPROVED";
                tipMsg = "供应商确认";

                for (int i = 0; i < lineArray.size(); i++) {
                    JSONObject tempObj = lineArray.getJSONObject(i);
                    SrmPoLinesEntity_HI line = (SrmPoLinesEntity_HI) SrmPoLinesDAO_HI
                            .getById(tempObj.getInteger("poLineId"));
                    if (null == line) {
                        return SToolUtils.convertResultJSONObj("E", tipMsg + "失败,找不到订单行数据!", 0, null);
                    }

                    // 若反馈内容选择“确认”，则修改对应订单行的反馈状态改为“已确认”；
                    if ("CONFIRM".equals(tempObj.getString("feedbackResult"))) {
                        line.setFeedbackStatus("CONFIRMED");
                    }
                    // 若反馈内容选择“需调整”，则修改对应订单行的反馈状态改为“已反馈待审核”；
                    else if ("ADJUST".equals(tempObj.getString("feedbackResult"))) {
                        line.setFeedbackStatus("SUBMITTED");
                    }
                    // 若反馈内容选择“拒绝”，则修改对应订单行的反馈状态改为“已拒绝”。
                    else if ("REFUSE".equals(tempObj.getString("feedbackResult"))) {
                        line.setFeedbackStatus("REFUSED");
                    }
                    line.setFeedbackAdjustQty(tempObj.getBigDecimal("feedbackAdjustQty"));
                    line.setFeedbackAdjustDate(tempObj.getDate("feedbackAdjustDate"));
                    line.setFeedbackResult(tempObj.getString("feedbackResult"));
                    line.setRejectReason(tempObj.getString("rejectReason"));
                    line.setOperatorUserId(operatorUserId);
                    SrmPoLinesDAO_HI.update(line);
                }
                //全部确认后发送创建人邮件
                SrmPoHeadersEntity_HI header = SrmPoHeadersDAO_HI.getById(lineArray.getJSONObject(0).getInteger("poHeaderId"));
                if (!ObjectUtils.isEmpty(header)) {
                    List<SaafEmployeesEntity_HI> se = saafEmployeesDAO_HI.findByProperty("userId", header.getCreatedBy());
                    if (!ObjectUtils.isEmpty(se)) {
                        if (!StringUtils.isEmpty(se.get(0).getEmail())) {
                            String content = "<p>您好！</p><br/>" + "您创建的采购订单" + header.getPoNumber() + "供应商已确认！";
                            String emailAddress = se.get(0).getEmail();
                            //发送邮件
                            sendMailUtil.doSendHtmlEmail("采购订单确认通知", content, new String[]{emailAddress});
                        }
                    }
                }

            }

            return SToolUtils.convertResultJSONObj("S", tipMsg + "成功!", 1, null);
        } catch (Exception e) {
            return SToolUtils.convertResultJSONObj("E", tipMsg + "失败!" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：采购订单接口（数据输入）——保存/修改一个采购订单所有信息（用于外部访问的接口） 需要提供用户和密码
     * <p>
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * shipLocationCode  收货方  VARCHAR2  N
     * locationCode  收单方  VARCHAR2  N
     * supPoFileId  供应商附件ID  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * agreementClause  协议条款  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * sourceId  数据来源ID  VARCHAR2  N
     * supplierReadTime  供应商查看日期
     * DATE  N
     * exchangeRate  汇率  NUMBER  N
     * exchangeRateDate  汇率日期  DATE  N
     * syncErpFlag  是否同步到erp  VARCHAR2  N
     * syncErpMsg  同步到erp异常信息  VARCHAR2  N
     * isGlobal  是否全局  VARCHAR2  N
     * orderType  订单类型  VARCHAR2  N
     * erpOrderNo  erp订单编号  VARCHAR2  N
     * bazaarOrderNo  市场订单  VARCHAR2  N
     * receiveToOrganizationId  库存组织  NUMBER  N
     * buyerExecuteId  采购履行专员  NUMBER  N
     * organizationId  库存组织  NUMBER  N
     * prNumber  采购申请单号  VARCHAR2  N
     * locationCode  收货地点  VARCHAR2  N
     * poType  类型  VARCHAR2  N
     * shipToOrganizationId  收货组织  NUMBER  N
     * shipLocationCode    VARCHAR2  N
     * shipToLocationCode  收货方  VARCHAR2  N
     * billToLocationCode  收单方  VARCHAR2  N
     * shipToLocationId  收货方  NUMBER  N
     * billToLocationId  收单方  NUMBER  N
     * poHeaderId  采购订单ID  NUMBER  Y
     * poNumber  采购订单号  VARCHAR2  Y
     * orgId  业务实体ID  NUMBER  N
     * billToOrganizationId  收单组织ID  NUMBER  N
     * poDocType  单据类型，ORDER：订单，AGREEMENT：协议  VARCHAR2  N
     * supplierId  供应商ID，关联表：srm_pos_supplier_info  NUMBER  N
     * supplierSiteId  供应商地点ID，关联表：srm_pos_supplier_sites  NUMBER  N
     * currencyCode  币种(BANK_CURRENCY)  VARCHAR2  N
     * taxRateCode  税率  VARCHAR2  N
     * buyerId  采购员ID，关联表：saaf_employees  NUMBER  N
     * returnGoodsType  回货类型  VARCHAR2  N
     * paymentCondition  付款条件  VARCHAR2  N
     * settlementWay  结算方式  VARCHAR2  N
     * poVersions  订单版本  NUMBER  N
     * status  状态  VARCHAR2  N
     * approvalUserId  审批用户ID  NUMBER  N
     * approvedDate  批准时间  DATE  N
     * startDate  有效开始日期  DATE  N
     * endDate  有效结束日期  DATE  N
     * description  说明  VARCHAR2  N
     * poFileId  附件ID  NUMBER  N
     * isGlobal  是否全局  VARCHAR2  N
     * agreementClause  协议条款  VARCHAR2  N
     * contractId  合同ID，关联表：srm_okc_contracts  NUMBER  N
     * contractCode  合同编号  VARCHAR2  N
     * contractTemplateId  合同模板ID，关联表：srm_okc_contract_templates  NUMBER  N
     * contractFileId  合同文档ID  NUMBER  N
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


    public JSONObject saveOrderInfoForExternal(JSONObject jsonParams, Integer userId) {
        SrmIntfLogsEntity_HI logsEntity = new SrmIntfLogsEntity_HI();
        JSONObject jsonData = new JSONObject(); // 最终结果的返回
        try {
            SrmPoHeadersEntity_HI orderHeaderInfo = null;
            if (!(jsonParams.getJSONObject("orderHeaderInfo") == null
                    || "".equals(jsonParams.getJSONObject("orderHeaderInfo")))) {
                JSONObject supplierInfoJSON = jsonParams.getJSONObject("orderHeaderInfo"); // 供应商基信息
                orderHeaderInfo = JSON.parseObject(supplierInfoJSON.toJSONString(), SrmPoHeadersEntity_HI.class);
            }

            List<SrmPoLinesEntity_HI> orderLineList = null;
            if (!(jsonParams.getJSONArray("orderLineList") == null
                    || "".equals(jsonParams.getJSONArray("orderLineList")))) {
                JSONArray supplierCategoryInfoListJSON = jsonParams.getJSONArray("orderLineList"); // 产品和服务
                orderLineList = JSON.parseArray(supplierCategoryInfoListJSON.toJSONString(), SrmPoLinesEntity_HI.class);
            }

            /**
             * 校验重复性和必填项
             */
            // 校验采购订单的基础信息的必填项
            String resultOrderHeaderInfo = judgeSpaceOrderHeaderInfo(orderHeaderInfo);
            if (!(resultOrderHeaderInfo == null || "".equals(resultOrderHeaderInfo))) {
                return SToolUtils.convertResultJSONObj("E", resultOrderHeaderInfo, 0, null);
            }
            // 校验采购订单行 信息必填项
            String resultOrderLineInfo = judgeSpaceOrderLineInfo(orderLineList);
            if (!(resultOrderLineInfo == null || "".equals(resultOrderLineInfo))) {
                return SToolUtils.convertResultJSONObj("E", resultOrderLineInfo, 0, null);
            }

            SrmPoHeadersEntity_HI newHeader = null;
            if (orderHeaderInfo.getPoHeaderId() != null) {
                newHeader = SrmPoHeadersDAO_HI.getById(orderHeaderInfo.getPoHeaderId());
                newHeader.setOrgId(orderHeaderInfo.getOrgId());
                newHeader.setBillToOrganizationId(orderHeaderInfo.getBillToOrganizationId());
                newHeader.setPoDocType(orderHeaderInfo.getPoDocType());
                newHeader.setSupplierId(orderHeaderInfo.getSupplierId());
                newHeader.setSupplierSiteId(orderHeaderInfo.getSupplierSiteId());
                newHeader.setOperatorUserId(userId);
                SrmPoHeadersDAO_HI.update(newHeader);
            } else {
                newHeader = orderHeaderInfo;
                if (orderHeaderInfo.getPoNumber() == null || "".equals(orderHeaderInfo.getPoNumber())) { // insert时，采购订单编码为空时自动赋值
                    Date date = new Date();
                    String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
                    //String poNumber = saafSequencesUtil.getDocSequences("SRM_PO_HEADERS", "PO-", dateFromate, 0);
                    String poNumber = saafSequencesUtil.getDocSequences("srm_po_headers", "PO-", dateFromate, 4);//订单编号
                    //update by xie shaoan 2018-08-27
//					String poNumberStr = "PO-"+dateFromate;
//					String poNumber = iSrmPoHeaders.getNewPoNumber(poNumberStr);//订单编号 注释原因：生成编号冲突，更换生成方法
                    newHeader.setPoNumber(poNumber);
                }
                newHeader.setOperatorUserId(userId);
                SrmPoHeadersDAO_HI.save(newHeader);
                jsonData.put("poHeaderId", newHeader.getPoHeaderId());
                jsonData.put("orderHeaderInfo", newHeader);
            }

            jsonData.put("poHeaderId", newHeader.getPoHeaderId());
            jsonData.put("orderHeaderInfo", orderHeaderInfo);

            /**
             * 处理行数据
             */
            List<SrmPoLinesEntity_HI> newLineList = new ArrayList<SrmPoLinesEntity_HI>();
            for (SrmPoLinesEntity_HI k : orderLineList) {
                SrmPoLinesEntity_HI newLine = null;
                if (k.getPoLineId() != null) {
                    newLine = SrmPoLinesDAO_HI.getById(k.getPoLineId());
                    newLine.setPoHeaderId(newHeader.getPoHeaderId());
                    newLine.setLineNumber(k.getLineNumber());
                    newLine.setItemId(k.getItemId());
                    newLine.setCategoryId(k.getCategoryId());
                    newLine.setDemandQty(k.getDemandQty());
                    newLine.setOperatorUserId(userId);
                    SrmPoLinesDAO_HI.update(newLine);
                } else {
                    newLine = k;
                    newLine.setPoHeaderId(newHeader.getPoHeaderId());
                    newLine.setOperatorUserId(userId);
                    SrmPoLinesDAO_HI.insert(newLine);

                }
                newLineList.add(newLine);
            }

            jsonData.put("orderLineList", newLineList);
            //保存日志
            logsEntity.setIntfType("PO_ORDER_IN");//接口类型BASE_INTF_TYPE
            logsEntity.setTableName("srm_po_headers");
            logsEntity.setTableId(newHeader.getPoHeaderId());//接口取数对应的表ID
            logsEntity.setDataDirection("IN");//数据方向(IN：输入， OUT：输出)
            logsEntity.setSendSystem(newHeader.getSourceCode());//数据发送方
            logsEntity.setReceiveSystem("SRM");
            logsEntity.setInData(jsonParams.toJSONString());//输入报文
            logsEntity.setOutData(jsonData.toJSONString());//输出报文
            logsEntity.setDescription("采购订单输入接口");
            logsEntity.setOperatorUserId(userId);
            logsEntity.setIntfStatus("S");
            srmIntfLogsDAO_HI.save(logsEntity);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);

            logsEntity.setIntfType("PO_ORDER_IN");//接口类型BASE_INTF_TYPE
            logsEntity.setTableName("srm_po_headers");
            logsEntity.setIntfStatus("E");
            logsEntity.setErrorMsg(e.getMessage());
            srmIntfLogsDAO_HI.save(logsEntity);
            return SToolUtils.convertResultJSONObj("E", "保存失败！" + e.getMessage(), 0, jsonData);
        }

        return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, jsonData);
    }

    /**
     * Description：采购订单接口（数据输出）——查询采购订单所有信息（用于外部访问的接口）
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Map<String, Object> findOrderInfoForExternal(JSONObject jsonParams) {
        //保存日志
        SrmIntfLogsEntity_HI logsEntity = new SrmIntfLogsEntity_HI();
        logsEntity.setIntfType("PO_ORDER_OUT");//接口类型BASE_INTF_TYPE
        logsEntity.setTableName("srm_po_headers");
        logsEntity.setDataDirection("OUT");//数据方向(IN：输入， OUT：输出)
        logsEntity.setSendSystem("SRM");//数据发送方
        logsEntity.setInData(jsonParams.toJSONString());//输入报文
        logsEntity.setDescription("采购订单输出接口");
        logsEntity.setOperatorUserId(jsonParams.getInteger("varUserId"));

        Map<String, Object> map = new HashMap<>(); // 最终结果的返回
        try {
            List<SrmPoHeadersEntity_HI_RO> orderHeaderList = findOrderInfoForSupplierSelf(jsonParams);
            if (null == orderHeaderList || orderHeaderList.size() <= 0) {
                map.put("status", "E");
                map.put("msg", "查无此数据！");
                map.put("data", new ArrayList<>());
                map.put("count", 0);
                logsEntity.setIntfStatus("E");//查无数据状态为E，查有数据状态为S
                srmIntfLogsDAO_HI.save(logsEntity);
                return map;
            }

            List<Map<String, Object>> orderHeaderInfoListMap = new ArrayList<>(); // 采购订单所有信息List
            for (SrmPoHeadersEntity_HI_RO k : orderHeaderList) {
                Map<String, Object> orderHeaderAllList = new HashMap<>(); // 存放一个采购订单的信息及其子表的所有信息
                JSONObject supplierParams = new JSONObject(); // 存放采购订单Id
                supplierParams.put("poHeaderId", k.getPoHeaderId());

                // 采购订单基础信息
                orderHeaderAllList.put("orderHeaderInfo", k);
                // 采购订单的资质信息
                Pagination<SrmPoLinesEntity_HI_RO> orderLineListPagination = findOrderLineBySupplierSelf(supplierParams,
                        1, 2000);
                List<SrmPoLinesEntity_HI_RO> orderLineList = orderLineListPagination.getData();
                if (null != orderLineList && orderLineList.size() > 0) {
                    orderHeaderAllList.put("orderLineList", orderLineList);
                }

                orderHeaderInfoListMap.add(orderHeaderAllList);
            }
            map.put("data", orderHeaderInfoListMap);
            map.put("count", 1);
            map.put("msg", "查询成功");
            map.put("status", "S");
            logsEntity.setIntfStatus("S");//查无数据状态为E，查有数据状态为S
            srmIntfLogsDAO_HI.save(logsEntity);
            return map;
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            map.put("status", "E");
            map.put("msg", e.getMessage());
            map.put("data", new ArrayList<>());
            map.put("count", 0);
            logsEntity.setIntfType("PO_ORDER_OUT");//接口类型BASE_INTF_TYPE
            logsEntity.setTableName("srm_po_headers");
            logsEntity.setErrorMsg(e.getMessage());
            srmIntfLogsDAO_HI.save(logsEntity);
            return map;
        }
    }

    /**
     * 校验采购订单基础信息的必填项——接口（数据输入）
     *
     * @param orderHeaderInfo
     * @return
     */
    private String judgeSpaceOrderHeaderInfo(SrmPoHeadersEntity_HI orderHeaderInfo) {
        String result = "";
        if (null == orderHeaderInfo || "".equals(orderHeaderInfo)) {
            result += "采购订单的基础信息不可为空";
            return result;
        }
        if (null == orderHeaderInfo.getPoNumber() || "".equals(orderHeaderInfo.getPoNumber())) {
            result += "请填写采购订单的编号——PoNumber";
            return result;
        }
        if (null == orderHeaderInfo.getOrgId() || "".equals(orderHeaderInfo.getOrgId())) {
            result += "请填写采购订单的业务实体ID——OrgId";
            return result;
        }
        if (null == orderHeaderInfo.getBillToOrganizationId() || "".equals(orderHeaderInfo.getBillToOrganizationId())) {
            result += "请填写采购订单的收单组织ID——BillToOrganizationId";
            return result;
        }
        if (null == orderHeaderInfo.getSupplierId() || "".equals(orderHeaderInfo.getSupplierId())) {
            result += "请填写采购订单的供应商ID——SupplierId";
            return result;
        }
        if (null == orderHeaderInfo.getSupplierSiteId() || "".equals(orderHeaderInfo.getSupplierSiteId())) {
            result += "请填写采购订单的供应商地点ID——SupplierSiteId";
            return result;
        }
        return result;
    }

    /**
     * 校验采购订单基本行信息的必填项——接口（数据输入）
     *
     * @param orderLineInfoList
     * @return
     */
    private String judgeSpaceOrderLineInfo(List<SrmPoLinesEntity_HI> orderLineInfoList) {
        String result = "";
        if (null == orderLineInfoList || orderLineInfoList.size() <= 0) {
            return result;
        }
        Integer index = 0;

        for (SrmPoLinesEntity_HI k : orderLineInfoList) {
            index++;
            if (null == k.getPoHeaderId() || "".equals(k.getPoHeaderId())) {
                result += "请填采购订单头ID" + "第" + index + "行的采购订单头ID——PoHeaderId";
                return result;
            }
            if (null == k.getLineNumber() || "".equals(k.getLineNumber())) {
                result += "请填写行号" + "第" + index + "行的行号——LineNumber";
                return result;
            }
            if (null == k.getItemId() || "".equals(k.getItemId())) {
                result += "请填物料ID" + "第" + index + "行的物料ID——ItemId";
                return result;
            }
            if (null == k.getCategoryId() || "".equals(k.getCategoryId())) {
                result += "请填写分类ID" + "第" + index + "行的分类ID——CategoryId";
                return result;
            }
            if (null == k.getDemandQty() || "".equals(k.getDemandQty())) {
                result += "请填写需求数量" + "第" + index + "行的需求数量——DemandQty";
                return result;
            }

        }

        return result;
    }

    /**
     * 查询采购订单头项——接口（数据输出）
     *
     * @param jsonParams
     * @return
     * @throws Exception
     */
    private List<SrmPoHeadersEntity_HI_RO> findOrderInfoForSupplierSelf(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_ORDER_LIST_SQL);
            SaafToolUtils.parperParam(jsonParams, "a.po_number", "poNumber", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "a.org_id", "orgId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "a.bill_to_organization_id", "billToOrganizationId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "date_format(a.creation_date,'%Y-%m-%d')", "creationDateFrom", queryString, queryParamMap, ">=");
            SaafToolUtils.parperParam(jsonParams, "date_format(a.creation_date,'%Y-%m-%d')", "creationDateTo", queryString, queryParamMap, "<=");
            SaafToolUtils.parperParam(jsonParams, "a.buyer_id", "buyerId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "b.category_id", "categoryId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "b.item_id", "itemId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "a.return_goods_type", "returnGoodsType", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "date_format(b.demand_date,'%Y-%m-%d')", "demandDateFrom", queryString, queryParamMap, ">=");
            SaafToolUtils.parperParam(jsonParams, "date_format(b.demand_date,'%Y-%m-%d')", "demandDateTo", queryString, queryParamMap, "<=");
            SaafToolUtils.parperParam(jsonParams, "a.status", "status", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "b.status", "lineStatus", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "a.description", "description", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "b.description", "lineDescription", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "b.feedback_status ", "feedbackStatus", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "b.feedback_result", "feedbackResult", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "a.supplier_id", "supplierId", queryString, queryParamMap, "=");
            queryString.append(" ORDER BY a.po_header_id DESC, b.po_line_id ASC, b.line_number DESC");
            return SrmPoHeadersDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        } catch (Exception e) {
            throw new UtilsException(e);
        }
    }

    /**
     * 查询采购订单行项——接口（数据输出）
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRow
     * @return
     * @throws Exception
     */
    private Pagination<SrmPoLinesEntity_HI_RO> findOrderLineBySupplierSelf(JSONObject jsonParams, Integer pageIndex,
                                                                           Integer pageRow) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryParamSql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_ORDER_LINE_BY_SUPPLIER_SQL);
        try {
            SaafToolUtils.parperParam(jsonParams, "b.po_header_id", "poHeaderId", queryParamSql, queryParamMap, "=");
            if (!StringUtil.isEmpty(jsonParams.getString("jsonParams"))) {
                SaafToolUtils.parperParam(jsonParams, "d.item_code", "itemCode", queryParamSql, queryParamMap, "like");
                queryParamSql.append(" OR d.item_name LIKE %:itemName% ");
                queryParamMap.put("itemName", jsonParams.getString("itemCode"));
            }
            String countSql = "select count(1) from (" + queryParamSql + ")";
            return srmPoLinesDAO_HI_RO.findPagination(queryParamSql, countSql, queryParamMap, pageIndex, pageRow);
        } catch (Exception e) {
            throw new UtilsException(e);
        }
    }

    /**
     * 计算含税价
     *
     * @param nonTaxPrice
     * @param taxRateCode
     * @return
     */
    private BigDecimal computerTaxPrice(String nonTaxPrice, String taxRateCode) {
        String taxRate = "0";
        Map<String, Object> map = new HashMap<>();
        map.put("lookup_type", "PON_TAX_LIST");
        map.put("lookup_code", taxRateCode);
        List<SaafLookupValuesEntity_HI> lookupValues = saafLookupValuesDAO_hi.findByProperty(map);
        if (lookupValues.size() > 0) {
            taxRate = lookupValues.get(0).getTag();
        }
        // 100 * (1+0.1)
        return new BigDecimal(nonTaxPrice).multiply(new BigDecimal(taxRate).divide(new BigDecimal(100)).add(new BigDecimal("1"))).setScale(4, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Description：计算含税价
     *
     * @param jsonParams 查询条件参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public SrmPoLinesEntity_HI findComputerTaxPrice(JSONObject jsonParams) {
        String taxRateCode = jsonParams.getString("taxRateCode");
        String nonTaxPrice = jsonParams.getString("nonTaxPrice");
        String demandQty = jsonParams.getString("demandQty");
        BigDecimal taxPrice = computerTaxPrice(nonTaxPrice, taxRateCode);
        BigDecimal nonTaxTotalPrice = new BigDecimal(nonTaxPrice).multiply(new BigDecimal(demandQty)).setScale(4, BigDecimal.ROUND_HALF_UP);
        BigDecimal taxTotalPrice = taxPrice.multiply(new BigDecimal(demandQty)).setScale(4, BigDecimal.ROUND_HALF_UP);
        SrmPoLinesEntity_HI item = new SrmPoLinesEntity_HI();
        item.setTaxPrice(taxPrice);
        item.setNonTaxTotalPrice(nonTaxTotalPrice);
        item.setTaxTotalPrice(taxTotalPrice);
        return item;
    }

    /**
     * Description：查询送货通知采购订单
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
    public Pagination<SrmPoHeadersEntity_HI_RO> findNoticeOrderInfo(JSONObject jsonParams, Integer pageIndex,
                                                                    Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        try {
            StringBuffer queryString = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_ORDER_NOTICE_LIST_SQL);
            SaafToolUtils.parperParam(jsonParams, "a.po_number", "poNumber", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "a.org_id", "orgId", queryString, queryParamMap, "=");
            //收货组织
            SaafToolUtils.parperParam(jsonParams, "b.receive_to_organization_id", "receiveToOrganizationId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "date_format(a.creation_date,'%Y-%m-%d')", "creationDateFrom", queryString, queryParamMap, ">=");
            SaafToolUtils.parperParam(jsonParams, "date_format(a.creation_date,'%Y-%m-%d')", "creationDateTo", queryString, queryParamMap, "<=");
            SaafToolUtils.parperParam(jsonParams, "a.buyer_id", "buyerId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "b.category_id", "categoryId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "b.item_id", "itemId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "date_format(b.demand_date,'%Y-%m-%d')", "demandDateFrom", queryString, queryParamMap, ">=");
            SaafToolUtils.parperParam(jsonParams, "date_format(b.demand_date,'%Y-%m-%d')", "demandDateTo", queryString, queryParamMap, "<=");
            SaafToolUtils.parperParam(jsonParams, "a.description", "description", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "b.description", "lineDescription", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "a.supplier_id", "supplierId", queryString, queryParamMap, "=");
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" ORDER BY a.po_header_id DESC, b.po_line_id ASC, b.line_number DESC");
            Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(), countSql, queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new UtilsException(e);
        }
    }

    /**
     * Description：批量导入订单行数据
     *
     * @param jsonParams 导入参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject saveImportOrderLineInfo(JSONObject jsonParams) {
        Integer operatorUserId = jsonParams.getInteger("operatorUserId");
        if (jsonParams.getJSONArray("data") == null || "".equals(jsonParams.getJSONArray("data"))
                || jsonParams.getJSONArray("data").size() <= 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "导入的数据为空，不可导入", 0, null);
        }
        if (jsonParams.getJSONObject("info") == null || "".equals(jsonParams.getJSONObject("info"))
                || jsonParams.getJSONObject("info").getString("poHeaderId") == null
                || "".equals(jsonParams.getJSONObject("info").getString("poHeaderId"))) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "头层未保存，不可导入", 0, null);
        }
        JSONArray jsonArray = jsonParams.getJSONArray("data");
        JSONObject info = jsonParams.getJSONObject("info");
        Integer poHeaderId = Integer.parseInt(info.getString("poHeaderId"));

        JSONArray error = cehckArray(jsonArray, info);
        if (error.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存失败", error.size(), error);
        }
        SrmPoLinesEntity_HI line = null;
        Integer lineNumber = null;
        int count = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            if (null == lineNumber) {
                lineNumber = jsonObj.getInteger("lineNumber");
            }
            lineNumber++;
            line = new SrmPoLinesEntity_HI();
            line.setLineNumber(lineNumber);
            line.setStatus("DRAFT");// 订单行默认拟定状态
            // line.setStatus("OPEN");// 订单行默认打开状态
            // OPEN 打开
            // CLOSED 关闭
            // CANCELLED 取消
            /*line.setPoHeaderId(jsonObj.getInteger("poHeaderId"));*/
            line.setPoHeaderId(poHeaderId);
            line.setItemId(jsonObj.getInteger("itemId"));
            line.setItemName(jsonObj.getString("itemName"));
            line.setItemSpec(jsonObj.getString("itemSpec"));
            line.setCategoryId(jsonObj.getInteger("categoryId"));
            line.setDemandQty(jsonObj.getBigDecimal("demandQty"));
            line.setTaxRateCode(jsonObj.getString("taxRateCode"));
            line.setNonTaxPrice(jsonObj.getBigDecimal("nonTaxPrice"));
            line.setTaxPrice(computerTaxPrice(jsonObj.getString("nonTaxPrice"), jsonObj.getString("taxRateCode")));// 计算得出
            line.setDemandDate(jsonObj.getDate("demandDate"));
            line.setReceiveToOrganizationId(jsonObj.getInteger("receiveToOrganizationId"));
            line.setExpenseItemCode(jsonObj.getString("expenseItemCode"));
            line.setDescription(jsonObj.getString("lineDescription"));
            if (!ObjectUtils.isEmpty(jsonObj.getString("expenseItemCode"))) {
                line.setExpenseItemCode(jsonObj.getString("expenseItemCode"));
            }
            line.setOnWayQty(new BigDecimal(0));
            line.setFeedbackStatus("NON_FEEDBACK"/* jsonObj.getString("feedbackStatus") */);
            line.setFeedbackResult("CONFIRM"/* jsonObj.getString("feedbackResult") */);
            line.setOperatorUserId(operatorUserId);
            SrmPoLinesDAO_HI.save(line);
            count++;
        }

        JSONObject resultObj = new JSONObject();
        resultObj.put("msg", "导入成功行数为:" + count + "行!");
        resultObj.put("status", "S");
        return resultObj;
    }

    /**
     * 检查批量导入的数据正确性
     *
     * @param jsonArray
     * @return
     */
    private JSONArray cehckArray(JSONArray jsonArray, JSONObject info) {
        Integer poHeaderId = Integer.parseInt(info.getString("poHeaderId"));
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPoLinesEntity_HI_RO.QUERY_ORDER_LINE_INFO);
        if (jsonArray.isEmpty()) {
            return null;
        }
        JSONArray error = new JSONArray();
        JSONObject e = null;
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String itemCode = object.getString("itemCode");
            String demandQty = object.getString("demandQty");
            String nonTaxPrice = object.getString("nonTaxPrice");
            String demandDate = object.getString("demandDate");
            String instCode = object.getString("instCode");
            String expenseItemCode = object.getString("expenseItemCode");
            SrmPoHeadersEntity_HI headerList = SrmPoHeadersDAO_HI.getById(poHeaderId);
            if (ObjectUtils.isEmpty(headerList)) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "订单不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            } else {
                if ("INFORMATION_TECHNOLOGY".equals(headerList.getPoType()) || "ENGINEERING".equals(headerList.getPoType())) {
                    if (null == expenseItemCode || expenseItemCode.trim().length() == 0) {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "费用编码不能为空");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                }
            }

            List<SrmBaseItemsEntity_HI> srmBaseItemsEntityList = srmBaseItemsEntity_HI.findByProperty("item_code",
                    itemCode);
            if (srmBaseItemsEntityList == null || srmBaseItemsEntityList.size() < 1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "物料编号不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            object.put("itemId", srmBaseItemsEntityList.get(0).getItemId());
            object.put("itemName", srmBaseItemsEntityList.get(0).getItemName());
            object.put("itemSpec", srmBaseItemsEntityList.get(0).getSpecification());
            //List<SrmBaseCategoriesEntity_HI> srmBaseCategoriesEntityList = srmBaseCategoriesDAO_HI
            //        .findByProperty("category_code", categoryCode);
            //if (srmBaseCategoriesEntityList == null || srmBaseCategoriesEntityList.size() < 1) {
            //    e = new JSONObject();
            //    e.put("ERR_MESSAGE", "分类编号不存在");
            //    e.put("ROW_NUM", i + 1);
            //    error.add(e);
            //    continue;
            //}
            object.put("categoryId", srmBaseItemsEntityList.get(0).getCategoryId());

            if (null == demandQty || demandQty.trim().length() == 0) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "需求数量不能为空");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            } else {
                try {
                    Integer.parseInt(demandQty);
                } catch (NumberFormatException e1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "需求数量不能识别");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }

            // 判断税率的快码值
            if (!(object.getString("taxRateName") == null || "".equals(object.getString("taxRateName")))) {
                String meaning = object.getString("taxRateName"); // 检查输入的税率
                Map<String, Object> mapV = new HashMap<>();
                mapV.put("lookupType", "PON_TAX_LIST");
                mapV.put("meaning", meaning);
                List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                if (lookupValueList == null || lookupValueList.size() < 1 || "".equals(lookupValueList)) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "税率" + meaning + "输入有误");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                } else {
                    SaafLookupValuesEntity_HI entity = lookupValueList.get(0);
                    if (null != entity.getEnabledFlag() && "Y".equals(entity.getEnabledFlag())) {
                        object.put("taxRateCode", lookupValueList.get(0).getLookupCode());
                    } else {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "税率" + meaning + "是未生效状态");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                }
            }


            if (null == nonTaxPrice || nonTaxPrice.trim().length() == 0) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "价格(不含税)不能为空");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            } else {
                try {
                    Double.parseDouble(demandQty);
                } catch (NumberFormatException e1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "价格(不含税)不能识别");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }

            if (null == demandDate || demandDate.trim().length() == 0) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "需求日期不能为空");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            } else {
                try {
                    object.getDate("demandDate");
                } catch (Exception e1) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "需求日期不能识别(yyyy-MM-dd),例如:2001-01-01");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }

            List<SaafInstitutionEntity_HI> saafInstitutionEntityList = saafInstitutionDAO_HI.findByProperty("inst_code", instCode);
            if (saafInstitutionEntityList == null || saafInstitutionEntityList.size() < 1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "组织不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            object.put("receiveToOrganizationId", saafInstitutionEntityList.get(0).getInstId());
            queryString.append(" and t.po_header_id=" + poHeaderId);
            List<SrmPoLinesEntity_HI_RO> list = srmPoLinesDAO_HI_RO.findList(queryString.toString());
            object.put("lineNumber", list.size());
        }
        return error;
    }

    /**
     * Description：查询物料LOV
     *
     * @param jsonParams 查询条件参数
     * @param pageIndex  页码
     * @param pageRows   行数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoHeadersEntity_HI_RO> findItemListByASL(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            String varSupplierId = jsonParams.getString("supplierId");
            queryParamMap.put("varSupplierId", varSupplierId);
            String asl = "SOURCE_GOODS_ENABLE";
            String ItemReturn = "ITEM_RETURN_ENABLE";
            SrmBaseParamsEntity_HI paramsOne = srmBaseParamsDAO_HI.findByProperty("param_code", asl).get(0);
            SrmBaseParamsEntity_HI paramsTwo = srmBaseParamsDAO_HI.findByProperty("param_code", ItemReturn).get(0);
            StringBuffer queryString = new StringBuffer();
            if ("NOTENABLED".equals(paramsOne.getParamValue1()) && "NOTENABLED".equals(paramsTwo.getParamValue1())) {
                queryString.append(SrmBaseItemsEntity_HI_RO.QUERY_PON_BASE_ITEM_SQL);
                Integer parentInstId = jsonParams.getInteger("parentInstId");
                if (null != parentInstId && !"".equals(parentInstId)) {
                    queryString.append(" AND EXISTS\n"
                            + "  (SELECT 1 FROM saaf_institution t3\n"
                            + "  WHERE t3.inst_id = t.organization_id\n"
                            + "    AND t3.inst_type = 'ORGANIZATION'\n"
                            + "    AND t3.parent_inst_id = " + parentInstId + ") ");
                }
            } else if ("ENABLE".equals(paramsOne.getParamValue1()) && "ENABLE".equals(paramsTwo.getParamValue1())) {
                queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_BASE_ASL_RETURN_ITEM_SQL);
                queryString.append(" and (a.type = '" + jsonParams.getString("deliverType") + "' or a.type = '') ");
            } else if ("ENABLE".equals(paramsOne.getParamValue1()) && "NOTENABLED".equals(paramsTwo.getParamValue1())) {
                queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_BASE_ASL_ITEM_SQL);
                queryString.append(" and t.list_status in ('NEW','APPROVED') ");
                queryString.append(" and t.disabled_flag = 'N' ");
            } else if ("NOTENABLED".equals(paramsOne.getParamValue1()) && "ENABLE".equals(paramsTwo.getParamValue1())) {
                queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_BASE_RETURN_ITEM_SQL);
                queryString.append(" and t.deliver_status = 'ACT'");
                queryString.append(" and t.deliver_type = '" + jsonParams.getString("deliverType") + "'");
            }

            SaafToolUtils.parperParam(jsonParams, "a.itemCode", "itemCode", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "a.itemName", "itemName", queryString, queryParamMap, "like");
            String countSql = "select count(1) from (" + queryString + ")";
            Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(), countSql, queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new UtilsException(e);
        }
    }

    /**
     * @param jsonObj
     * @return boolean
     * @Author HZZ
     * @Description 检查该品类的采购订单的采购总数量是否超过采购申请的申请数量
     * @Date 10:03 2019/3/25
     * @Param @param header
     */
    public boolean checkDemandQtyCount(SrmPoHeadersEntity_HI header, JSONObject jsonObj) {
        Integer poHeaderId = header.getPoHeaderId();
        Integer itemId = jsonObj.getInteger("itemId");
        Integer poLineId = jsonObj.getInteger("poLineId");
        StringBuffer queryString = new StringBuffer();
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        //查询对应采购申请的申请数量
        queryString.append(SrmPoRequisitionLinesEntity_HI_RO.QUERY_REQ_DEMANDQTY_SQL);
        queryParamMap.put("poHeaderId", poHeaderId);
        queryParamMap.put("poLineId", poLineId);
        queryParamMap.put("itemId", itemId);
        System.out.println(queryParamMap.toString());
        List<SrmPoRequisitionLinesEntity_HI_RO> result1 = SrmPoRequisitionLinesDAO_HI_RO.findList(queryString.toString(),
                queryParamMap);
        //查不到对应的采购申请----手动新增采购订单
        //但是手动新增采购订单没关联采购申请的不做处理
        if (result1 == null || result1.size() <= 0) {
            return true;
        }
        BigDecimal reqDemandQty = result1.get(0).getDemandQty();
        //查询相关订单的总数量
        queryString = new StringBuffer();
        Map<String, Object> queryParamMap2 = new HashMap<String, Object>();
        queryParamMap2.put("itemId", itemId);
        queryParamMap2.put("poHeaderId", poHeaderId);
        queryString.append(SrmPoHeadersEntity_HI_RO.GET_SUM_DEMANDQTY_SQL);
        List<SrmPoHeadersEntity_HI_RO> result2 = SrmPoHeadersDAO_HI_RO.findList(queryString.toString(),
                queryParamMap2);
        BigDecimal sumDemandQty = result2.get(0).getSumDemandQty();
        System.out.println("所有相关的采购订单总数量sumDemandQty" + sumDemandQty);
        System.out.println("采购申请的申请数量reqDemandQty" + reqDemandQty);
        if (sumDemandQty.compareTo(reqDemandQty) <= 0) {
            return true;
        }
        return false;
    }

    /**
     * Description：检查订单头状态
     *
     * @param header 订单头数据
     * @param userId
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public void updatePoStatus(SrmPoHeadersEntity_HI header, Integer userId) {
        int rowCount = 0;
        int cancelledCount = 0;
        int receivedCount = 0;
        int settledCount = 0;
        Map<String, Object> map = new HashMap<>();
        map.put("poHeaderId", header.getPoHeaderId());
        //所有行
        List<SrmPoLinesEntity_HI> lines = SrmPoLinesDAO_HI.findByProperty(map);
        if (null != lines) {
            rowCount = lines.size();
        }
        //检查是否所有行都已取消
        map.put("status", "CANCELLED");
        List<SrmPoLinesEntity_HI> cancelledLines = SrmPoLinesDAO_HI.findByProperty(map);
        if (null != cancelledLines) {
            cancelledCount = cancelledLines.size();
        }
        if (rowCount == cancelledCount) {
            header.setStatus("CANCELLED");
            header.setOperatorUserId(userId);
            SrmPoHeadersDAO_HI.update(header);
            return;
        }
        //检查是否已验收完毕
        map.put("status", "RECEIVED");
        List<SrmPoLinesEntity_HI> receivedLines = SrmPoLinesDAO_HI.findByProperty(map);
        if (null != receivedLines) {
            receivedCount = receivedLines.size();
        }
        if (receivedCount > 0 && rowCount == cancelledCount + receivedCount) {
            header.setStatus("RECEIVED");
            header.setOperatorUserId(userId);
            SrmPoHeadersDAO_HI.update(header);
            return;
        }
        //检查是否已结算完毕
        map.put("status", "SETTLED");
        List<SrmPoLinesEntity_HI> settledLines = SrmPoLinesDAO_HI.findByProperty(map);
        if (null != settledLines) {
            settledCount = settledLines.size();
        }
        if (settledCount > 0 && rowCount == cancelledCount + settledCount) {
            header.setStatus("SETTLED");
            header.setOperatorUserId(userId);
            SrmPoHeadersDAO_HI.update(header);
            return;
        }
    }

    /**
     * 保存合并订单行
     *
     * @param poHeader
     * @param userId
     */
    public List<SrmPoLinesCombEntity_HI> saveOrderLineComb(SrmPoHeadersEntity_HI poHeader, Integer userId) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<>();
        Integer poHeaderId = poHeader.getPoHeaderId();
        String poNumber = poHeader.getPoNumber();
        queryParamMap.put("poHeaderId", poHeaderId);
        //查询新合并行情况
        List<SrmPoLinesCombEntity_HI_RO> newLineCombList = srmPoLinesCombDAO_HI_RO.findList(SrmPoLinesCombEntity_HI_RO.QUERY_LINES_COMB_SQL, queryParamMap);
        //查询已记录的合并行情况
        List<SrmPoLinesCombEntity_HI> oldLineCombList = srmPoLinesCombDAO_HI.findByProperty(queryParamMap);

        List<SrmPoLinesCombEntity_HI> returnLineCombList = new ArrayList<>();
        int lineNumber = 0;
        for (SrmPoLinesCombEntity_HI_RO newLineCombEntity : newLineCombList) {
            if (null != oldLineCombList && oldLineCombList.size() > 0) {
                //取费用CODE
                List<String> itemCodeList = oldLineCombList.stream().map(SrmPoLinesCombEntity_HI::getItemCode).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
                lineNumber = oldLineCombList.size();//行号
                if (!StringUtils.isEmpty(newLineCombEntity.getItemCode()) && itemCodeList.contains(newLineCombEntity.getItemCode())) {
                    for (SrmPoLinesCombEntity_HI oldLineCombEntity : oldLineCombList) {
                        if (newLineCombEntity.getItemCode().equals(oldLineCombEntity.getItemCode())) {
                            //如果费用物料行已存在则更新合并行
                            oldLineCombEntity.setItemName(newLineCombEntity.getItemName());
                            oldLineCombEntity.setDemandQty(newLineCombEntity.getDemandQty());
                            oldLineCombEntity.setDemandDate(newLineCombEntity.getDemandDate());
                            oldLineCombEntity.setNonTaxTotalPrice(newLineCombEntity.getNonTaxTotalPrice());
                            oldLineCombEntity.setTaxTotalPrice(newLineCombEntity.getTaxTotalPrice());
                            //如果不含税价为0，则视为该费用物料对应的所有订单行都已取消
                            if (newLineCombEntity.getNonTaxTotalPrice().compareTo(new BigDecimal(0)) == 0) {
                                oldLineCombEntity.setStatus("CANCELLED");
                            }
                            oldLineCombEntity.setContext(newLineCombEntity.getContext());
                            oldLineCombEntity.setProjectCategory(newLineCombEntity.getProjectCategory());
                            oldLineCombEntity.setProjectType(newLineCombEntity.getProjectType());
                            oldLineCombEntity.setTechnicalTransNumber(newLineCombEntity.getTechnicalTransNumber());
                            oldLineCombEntity.setSubprojectNumber(newLineCombEntity.getSubprojectNumber());
                            oldLineCombEntity.setAcceptanceProcessNumber(newLineCombEntity.getAcceptanceProcessNumber());
                            oldLineCombEntity.setOperatorUserId(userId);

                            srmPoLinesCombDAO_HI.update(oldLineCombEntity);
                            returnLineCombList.add(oldLineCombEntity);
                            //回写合并行id至订单行
                            savePoLineCombId(newLineCombEntity, oldLineCombEntity, userId);
                        }
                    }
                } else if (!StringUtils.isEmpty(newLineCombEntity.getItemCode()) && !itemCodeList.contains(newLineCombEntity.getItemCode())) {
                    //如果费用物料行未存在则新增合并行
                    lineNumber++;
                    SrmPoLinesCombEntity_HI entityHi = new SrmPoLinesCombEntity_HI();
                    entityHi.setPoHeaderId(poHeaderId);
                    entityHi.setLineNumber(lineNumber);
                    entityHi.setSrmOrderNumber(poNumber + "-" + entityHi.getLineNumber()); //设置订单编号
                    entityHi.setStatus("OPEN");
                    entityHi.setItemCode(newLineCombEntity.getItemCode());
                    entityHi.setItemName(newLineCombEntity.getItemName());
                    entityHi.setDemandQty(newLineCombEntity.getDemandQty());
                    entityHi.setDemandDate(newLineCombEntity.getDemandDate());
                    entityHi.setNonTaxTotalPrice(newLineCombEntity.getNonTaxTotalPrice());
                    entityHi.setTaxTotalPrice(newLineCombEntity.getTaxTotalPrice());
                    entityHi.setContext(newLineCombEntity.getContext());
                    entityHi.setProjectCategory(newLineCombEntity.getProjectCategory());
                    entityHi.setProjectType(newLineCombEntity.getProjectType());
                    entityHi.setTechnicalTransNumber(newLineCombEntity.getTechnicalTransNumber());
                    entityHi.setSubprojectNumber(newLineCombEntity.getSubprojectNumber());
                    entityHi.setAcceptanceProcessNumber(newLineCombEntity.getAcceptanceProcessNumber());
                    entityHi.setOperatorUserId(userId);

                    srmPoLinesCombDAO_HI.save(entityHi);
                    returnLineCombList.add(entityHi);
                    //回写合并行id至订单行
                    savePoLineCombId(newLineCombEntity, entityHi, userId);
                }



                /*
                for (SrmPoLinesCombEntity_HI oldLineCombEntity : oldLineCombList) {
                    if (null != newLineCombEntity.getItemCode() && null != oldLineCombEntity.getItemCode()) {
                        if (!newLineCombEntity.getItemCode().equals(oldLineCombEntity.getItemCode())) {
                            //如果费用物料行未存在则新增合并行
                            lineNumber ++;
                            SrmPoLinesCombEntity_HI entityHi = new SrmPoLinesCombEntity_HI();
                            entityHi.setPoHeaderId(poHeaderId);
                            entityHi.setLineNumber(lineNumber);
                            entityHi.setSrmOrderNumber(poNumber + "-" + entityHi.getLineNumber()); //设置订单编号
                            entityHi.setStatus("OPEN");
                            entityHi.setItemCode(newLineCombEntity.getItemCode());
                            entityHi.setItemName(newLineCombEntity.getItemName());
                            entityHi.setDemandQty(newLineCombEntity.getDemandQty());
                            entityHi.setDemandDate(newLineCombEntity.getDemandDate());
                            entityHi.setNonTaxTotalPrice(newLineCombEntity.getNonTaxTotalPrice());
                            entityHi.setTaxTotalPrice(newLineCombEntity.getTaxTotalPrice());
                            entityHi.setContext(newLineCombEntity.getContext());
                            entityHi.setProjectCategory(newLineCombEntity.getProjectCategory());
                            entityHi.setProjectType(newLineCombEntity.getProjectType());
                            entityHi.setTechnicalTransNumber(newLineCombEntity.getTechnicalTransNumber());
                            entityHi.setSubprojectNumber(newLineCombEntity.getSubprojectNumber());
                            entityHi.setAcceptanceProcessNumber(newLineCombEntity.getAcceptanceProcessNumber());
                            entityHi.setOperatorUserId(userId);

                            srmPoLinesCombDAO_HI.save(entityHi);
                            returnLineCombList.add(entityHi);
                            //回写合并行id至订单行
                            savePoLineCombId(newLineCombEntity, entityHi, userId);
                        } else {
                            //如果费用物料行已存在则更新合并行
                            oldLineCombEntity.setItemName(newLineCombEntity.getItemName());
                            oldLineCombEntity.setDemandQty(newLineCombEntity.getDemandQty());
                            oldLineCombEntity.setDemandDate(newLineCombEntity.getDemandDate());
                            oldLineCombEntity.setNonTaxTotalPrice(newLineCombEntity.getNonTaxTotalPrice());
                            oldLineCombEntity.setTaxTotalPrice(newLineCombEntity.getTaxTotalPrice());
                            //如果不含税价为0，则视为该费用物料对应的所有订单行都已取消
                            if (newLineCombEntity.getNonTaxTotalPrice().compareTo(new BigDecimal(0)) == 0) {
                                oldLineCombEntity.setStatus("CANCELLED");
                            }
                            oldLineCombEntity.setContext(newLineCombEntity.getContext());
                            oldLineCombEntity.setProjectCategory(newLineCombEntity.getProjectCategory());
                            oldLineCombEntity.setProjectType(newLineCombEntity.getProjectType());
                            oldLineCombEntity.setTechnicalTransNumber(newLineCombEntity.getTechnicalTransNumber());
                            oldLineCombEntity.setSubprojectNumber(newLineCombEntity.getSubprojectNumber());
                            oldLineCombEntity.setAcceptanceProcessNumber(newLineCombEntity.getAcceptanceProcessNumber());
                            oldLineCombEntity.setOperatorUserId(userId);

                            srmPoLinesCombDAO_HI.update(oldLineCombEntity);
                            returnLineCombList.add(oldLineCombEntity);
                            //回写合并行id至订单行
                            savePoLineCombId(newLineCombEntity, oldLineCombEntity, userId);
                        }
                    }
                }*/
            } else {
                //新增合并行
                lineNumber++;
                SrmPoLinesCombEntity_HI entityHi = new SrmPoLinesCombEntity_HI();
                entityHi.setPoHeaderId(poHeaderId);
                entityHi.setLineNumber(lineNumber);
                entityHi.setSrmOrderNumber(poNumber + "-" + entityHi.getLineNumber()); //设置订单编号
                entityHi.setStatus("OPEN");
                entityHi.setItemCode(newLineCombEntity.getItemCode());
                entityHi.setItemName(newLineCombEntity.getItemName());
                entityHi.setDemandQty(newLineCombEntity.getDemandQty());
                entityHi.setDemandDate(newLineCombEntity.getDemandDate());
                entityHi.setNonTaxTotalPrice(newLineCombEntity.getNonTaxTotalPrice());
                entityHi.setTaxTotalPrice(newLineCombEntity.getTaxTotalPrice());
                entityHi.setContext(newLineCombEntity.getContext());
                entityHi.setProjectCategory(newLineCombEntity.getProjectCategory());
                entityHi.setProjectType(newLineCombEntity.getProjectType());
                entityHi.setTechnicalTransNumber(newLineCombEntity.getTechnicalTransNumber());
                entityHi.setSubprojectNumber(newLineCombEntity.getSubprojectNumber());
                entityHi.setAcceptanceProcessNumber(newLineCombEntity.getAcceptanceProcessNumber());

                entityHi.setOperatorUserId(userId);

                srmPoLinesCombDAO_HI.save(entityHi);
                returnLineCombList.add(entityHi);
                //回写合并行id至订单行
                savePoLineCombId(newLineCombEntity, entityHi, userId);
            }

        }
        return returnLineCombList;
    }

    /**
     * 取消合并订单行
     *
     * @param poHeader
     * @param userId
     */
    public List<SrmPoLinesCombEntity_HI> saveCancelOrderLineComb(SrmPoHeadersEntity_HI poHeader, Integer userId) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<>();
        Integer poHeaderId = poHeader.getPoHeaderId();
        queryParamMap.put("poHeaderId", poHeaderId);
        //查询已记录的合并行情况
        List<SrmPoLinesCombEntity_HI> oldLineCombList = srmPoLinesCombDAO_HI.findByProperty(queryParamMap);
        List<SrmPoLinesCombEntity_HI> returnLineCombList = new ArrayList<>();
        for (SrmPoLinesCombEntity_HI oldLineCombEntity : oldLineCombList) {
            //取消合并行
            oldLineCombEntity.setDemandQty(new BigDecimal("0"));
            oldLineCombEntity.setDemandDate(null);
            oldLineCombEntity.setNonTaxTotalPrice(new BigDecimal("0"));
            oldLineCombEntity.setTaxTotalPrice(new BigDecimal("0"));
            oldLineCombEntity.setStatus("CANCELLED");
            oldLineCombEntity.setOperatorUserId(userId);

            srmPoLinesCombDAO_HI.update(oldLineCombEntity);
            returnLineCombList.add(oldLineCombEntity);
        }
        return returnLineCombList;
    }

    /**
     * 回写合并行id至订单行
     *
     * @param newLineCombEntity
     * @param updatedLineEntity
     * @param userId
     */
    public void savePoLineCombId(SrmPoLinesCombEntity_HI_RO newLineCombEntity, SrmPoLinesCombEntity_HI updatedLineEntity, Integer userId) {
        String poLineIdStr = newLineCombEntity.getPoLineIdStr();
        String[] poLineIdArray = poLineIdStr.split(",");
        for (int i = 0; i < poLineIdArray.length; i++) {
            SrmPoLinesEntity_HI linesEntityHi = srmPoLinesDAO_HI.getById(Integer.parseInt(poLineIdArray[i]));
            linesEntityHi.setPoLineCombId(updatedLineEntity.getPoLineCombId());
            linesEntityHi.setOperatorUserId(userId);
            srmPoLinesDAO_HI.update(linesEntityHi);
        }
    }

    /**
     * 推送订单至EBS
     *
     * @param header
     * @param line
     */
    public JSONArray pushOrderToEBS(String mes, SrmPoHeadersEntity_HI header, SrmPoLinesCombEntity_HI line) throws Exception {
        String orgName = null;
        if (header.getOrgId() != null) {
            StringBuffer sql = new StringBuffer(SaafInstitutionEntity_HI_RO.QUERY_ORG_NAME_BY_ID);
            sql.append(" and t.inst_id = " + header.getOrgId());
            List<SaafInstitutionEntity_HI_RO> orgObj = Institution_HI_RO.findList(sql);
            if (orgObj != null && orgObj.size() > 0) {
                orgName = orgObj.get(0).getInstName();
            }
        }
        //String supplierName = null;
        String supplierEbsNumber = null;
        if (header.getSupplierId() != null) {
            StringBuffer sql = new StringBuffer(SrmPosSupplierInfoEntity_HI_RO.GET_SUPPLIER_INFO_NAME);
            sql.append(" and pos.supplier_id = " + header.getSupplierId());
            List<SrmPosSupplierInfoEntity_HI_RO> supplierObj = srmPosSupplierInfoDAO_HI_RO.findList(sql);
            if (supplierObj != null && supplierObj.size() > 0) {
                SrmPosSupplierInfoEntity_HI_RO supplierInfo = supplierObj.get(0);
                //supplierName = supplierInfo.getSupplierName();
                supplierEbsNumber = supplierInfo.getSupplierEbsNumber();
            }
        }
        String supplierSite = null;
        if (header.getSupplierSiteId() != null) {
            SrmPosSupplierSitesEntity_HI supplierSiteObj = srmPosSupplierSitesDao_HI.getById(header.getSupplierSiteId());
            if (supplierSiteObj != null) {
                supplierSite = supplierSiteObj.getSiteName();
            }
        }
        String billToLocationCode = null; // 收单组织
        if (header.getBillToLocationId() != null) {
            StringBuffer sql = new StringBuffer(SrmBaseLocationsEntity_HI_RO.QUERY_LOC_SQL);
            sql.append(" and sbl.location_id = " + header.getBillToLocationId());
            List<SrmBaseLocationsEntity_HI_RO> billLocObj = srmBaseLocationsDAO_HI_RO.findList(sql);
            if (billLocObj != null && billLocObj.size() > 0) {
                SrmBaseLocationsEntity_HI_RO billLocInfo = billLocObj.get(0);
                billToLocationCode = billLocInfo.getLocationCode();
            }
        }
        String shipToLocationCode = null; // 收货组织
        if (header.getShipToLocationId() != null) {
            StringBuffer sql = new StringBuffer(SrmBaseLocationsEntity_HI_RO.QUERY_LOC_SQL);
            sql.append(" and sbl.location_id = " + header.getShipToLocationId());
            List<SrmBaseLocationsEntity_HI_RO> shipLocObj = srmBaseLocationsDAO_HI_RO.findList(sql);
            if (shipLocObj != null && shipLocObj.size() > 0) {
                SrmBaseLocationsEntity_HI_RO shipLocInfo = shipLocObj.get(0);
                shipToLocationCode = shipLocInfo.getLocationCode();
            }
        }
        String buyerName = null;
        String buyerNumber = null;
        if (header.getBuyerId() != null) {
            SaafEmployeesEntity_HI employeesEntity_hi = SaafEmployeesDAO_HI.getById(header.getBuyerId());
            if (employeesEntity_hi != null) {
                buyerName = employeesEntity_hi.getEmployeeName();
                buyerNumber = employeesEntity_hi.getEmployeeNumber();
            }
        }
        JSONObject baseQueryParams = new JSONObject();
        Date nowDate = new Date();
        String dateStr = DateUtil.date2Str(nowDate, "yyyy-MM-dd HH:mm:ss");
        baseQueryParams.put("lastUpdateDateF", dateStr); // 必传字段

        JSONObject headerData = new JSONObject();
        headerData.put("SOURCE_HEADER_ID", header.getPoHeaderId() + ""); //SRM头ID
        headerData.put("VER", header.getVersionNum()); // 版本号
        headerData.put("ORG_NAME", orgName);    // 业务实体`
        headerData.put("DOCUMENT_TYPE_CODE", "STANDARD");
        //headerData.put("VENDOR_NAME", supplierName);  // 供应商名称  死值
        headerData.put("VENDOR_NUMBER", supplierEbsNumber);  // ebs供应商编码  死值
        headerData.put("VENDOR_SITE_CODE", supplierSite);  // 地点名字 死值
        headerData.put("SHIP_TO_LOCATION", shipToLocationCode);  // 收货方 死值
        headerData.put("BILL_TO_LOCATION", billToLocationCode);  // 收单方 死值
        headerData.put("COMMENTS", header.getDescription()); // 说明
        headerData.put("PAYMENT_TERMS", "");
        headerData.put("BUYER_NAME", buyerName);
        headerData.put("EMPLOYEE_NUMBER", buyerNumber);
        headerData.put("CURRENCY_CODE", header.getCurrencyCode());
        headerData.put("APPROVAL_STATUS", getOrderStatic(header.getStatus()));
        headerData.put("SRM_ORDER_NUMBER", line.getSrmOrderNumber()); // SRM采购单号
        headerData.put("ERP_ORDER_NUMBER", line.getErpPoNumber()); //ERP采购订单
        headerData.put("TAX_TOTAL_PRICE", line.getTaxTotalPrice()); //含税总价

        JSONArray lineArray = new JSONArray(); // 订单行

        JSONObject lineData = new JSONObject();
        lineData.put("LINE_NUM", 1);//一单一行

        //1. 实际物料传“1”；服务费用传“2”；外协加工传“3”
        lineData.put("LINE_TYPE_ID", 1);
        lineData.put("ITEM_NUMBER", line.getItemCode()); // 物料编码 死值
        //lineData.put("UOM_CODE", linesEntityHi.getUomCode()); // 计量单位
        lineData.put("LINE_UNIT_PRICE", 1); // 不含税单价
        lineData.put("TAX_NAME", ""); // 税代码
        lineData.put("MARKET_PRICE", 1); // 含税单价
        lineData.put("SOURCE_LINE_ID", line.getPoLineCombId() + "");
        lineData.put("QUANTITY", line.getNonTaxTotalPrice()); // 需求数量
        lineData.put("LINE_STATUS", getLineState(line.getStatus())); // 行状态
        lineData.put("PROMISED_DATE", DateUtil.date2Str(line.getDemandDate(), "yyyy-MM-dd")); // 承诺日期
        lineData.put("NEED_BY_DATE", DateUtil.date2Str(line.getDemandDate(), "yyyy-MM-dd")); // 需求日期

        lineData.put("ATTRIBUTE_CATEGORY_LINES", line.getContext()); // 行弹性域


        //查询快码
        Map<String, Object> mapV = new HashMap<>();
        mapV.put("lookupType", "PROJECT_CLASSIFICATION");
        mapV.put("lookupCode", line.getProjectCategory());
        try {
            List<SaafLookupValuesEntity_HI> lookupValueList1 = saafLookupValuesDAO_HI.findByProperty(mapV);
            if (null != lookupValueList1 && lookupValueList1.size() > 0) {
                lineData.put("PROJECT_CATEGORY", lookupValueList1.get(0).getMeaning()); // 项目分类
            }
        } catch (Exception e) {
            line.setProjectCategory(null);
        }

        mapV.clear();
        mapV.put("lookupType", "PROJECT_TYPES");
        mapV.put("lookupCode", line.getProjectType() + line.getProjectCategory());
        try {
            List<SaafLookupValuesEntity_HI> lookupValueList2 = saafLookupValuesDAO_HI.findByProperty(mapV);
            if (null != lookupValueList2 && lookupValueList2.size() > 0) {
                lineData.put("PROJECT_TYPE", lookupValueList2.get(0).getMeaning()); // 项目类型
                //line.setProjectType(lookupValueList2.get(0).getMeaning());
            }
        } catch (Exception e) {
            line.setProjectType(null);
        }


        // lineData.put("PROJECT_CATEGORY", line.getProjectCategory()); // 项目分类
        // lineData.put("PROJECT_TYPE", line.getProjectType()); // 项目类型
        lineData.put("TECHNICAL_TRANS_NUMBER", line.getTechnicalTransNumber()); // 技改编号
        lineData.put("SUBPROJECT_NUMBER", line.getSubprojectNumber()); // 子项目编号
        lineData.put("ACCEPTANCE_PROCESS_NUMBER", line.getAcceptanceProcessNumber()); // 验收流程号


        lineArray.add(lineData);

        headerData.put("Line", lineArray);
        JSONArray bussData = new JSONArray();
        bussData.add(headerData);
        ESBClientUtils esbClient = new ESBClientUtils();
        JSONObject result = esbClient.doPost(ESBParams.SystemName.PCB_SRM.getValue(),
                ESBParams.SystemName.SRM_SERVER.getValue(),
                ESBParams.SystemName.EBS.getValue(),
                ESBParams.ServiceName.callSrmProcedure.getValue(),
                ESBParams.BusinessServiceName.REST_SRM_PUSHPOORDLIST.getValue(),
                "E",
                baseQueryParams,
                bussData);
        String isSuccess = null;
        JSONObject bussObj = null;
        JSONArray jsonArray = null;
        JSONObject jsonObject = result.getJSONObject("data");
        if (jsonObject != null) {
            JSONObject objData = jsonObject.getJSONObject("obj");
            if (objData != null) {
                jsonArray = objData.getJSONArray("businessData");
                if (jsonArray != null && jsonArray.size() > 0) {
                    bussObj = jsonArray.getJSONObject(0);
                    if ("S".equals(bussObj.getString("PROCESS_CODE"))) {
                        isSuccess = "成功";
                    } else {
                        //isSuccess = "失败";
                        throw new UtilsException(bussObj.getString("PROCESS_MSG"));
                    }
                }
            }
        }
        String title = mes + ">>>[采购订单]推送[EBS]>>>" + isSuccess;
        String busType = "SAVE_ORDER_INFO_FOR_EBS";
        String method = "saaf.common.fmw.po.model.inter.server.pushOrderToEBS";
        String operType = "pushOrderToEBS";
        //String errorMes = "需求汇总产生采购订单推送EBS日志";
        saveLog(headerData, result, nowDate, title, busType, method, operType, result.getString("msg"));
        return jsonArray;
    }

    /**
     * 错误数据信息入库日志
     */
    public void saveLog(JSONObject reqObject, JSONObject respObject, Date nowDate, String title, String busType, String method, String operType, String errorMes) {
        SaafBaseOperlogEntity_HI operlogEntity_hi = new SaafBaseOperlogEntity_HI();
        operlogEntity_hi.setTitle(title);
        operlogEntity_hi.setBusinessType(busType);
        operlogEntity_hi.setMethod(method);
        operlogEntity_hi.setOperatorType(operType);
        operlogEntity_hi.setStatus("FAIL");
        operlogEntity_hi.setErrorMsg(errorMes);
        operlogEntity_hi.setCreatedBy(1);
        operlogEntity_hi.setCreationDate(nowDate);
        operlogEntity_hi.setLastUpdateDate(nowDate);
        operlogEntity_hi.setLastUpdatedBy(1);
        operlogEntity_hi.setOperatorUserId(1);
        operlogEntity_hi.setOperParam(reqObject.toJSONString()); // 请求参数
        operlogEntity_hi.setOperRespParam(respObject.toJSONString()); // 响应参数
        try {
            iSaafBaseOperlog.saveOperLog(operlogEntity_hi);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
        }
    }

    public String getOrderStatic(String state) {
        String result = null;
        if (StringUtils.isNotBlank(state)) {
            if ("APPROVED".equals(state)) {
                result = "已批准";
            } else if ("CANCELLED".equals(state)) {
                result = "已取消";
            }
        }
        return result;
    }

    private String getLineState(String state) {
        String result = null;
        if (StringUtils.isNotBlank(state)) {
            if ("CANCELLED".equals(state)) {
                result = "已取消";
            } else {
                result = "已批准";
            }
        }
        return result;
    }

    public JSONObject settleToEbs(JSONObject jsonParams) throws Exception {
        Integer operatorUserId = jsonParams.getInteger("operatorUserId");
        //查询行
        List<SrmPoHeadersEntity_HI_RO> lineData = JSONObject.parseArray(jsonParams.getJSONArray("settleData").toJSONString(), SrmPoHeadersEntity_HI_RO.class);
        String linesIds = String.valueOf(lineData.stream().map(SrmPoHeadersEntity_HI_RO::getPoLineId).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.toList()).stream().distinct().collect(Collectors.joining("','")));
        linesIds = ObjectUtils.isEmpty(linesIds) ? "" : "'" + linesIds + "'";
        StringBuffer lineSql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_LINE_TOEBS);
        lineSql.append(" and Spl.Po_Line_Id IN (" + linesIds + ")");
        List<SrmPoHeadersEntity_HI_RO> list = SrmPoHeadersDAO_HI_RO.findList(lineSql.toString());

        //根据合并行ID更新实付行总价
        if (!ObjectUtils.isEmpty(list)) {
            //获取合并行ID
            List<Integer> combIdList = list.stream().map(SrmPoHeadersEntity_HI_RO::getPoLineCombId).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList()).stream().filter(Integer -> Integer != null).collect(Collectors.toList());
            //查询合并行
            if (!ObjectUtils.isEmpty(combIdList) && combIdList != null) {
                for (Integer ro : combIdList) {
                    SrmPoLinesCombEntity_HI comb = srmPoLinesCombDAO_HI.getById(ro);
                    BigDecimal nonTaxActTotalPrice = new BigDecimal("0");//不含税
                    BigDecimal taxActTotalPrice = new BigDecimal("0");//含税
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getPoLineCombId().equals(ro)) {
                            nonTaxActTotalPrice = nonTaxActTotalPrice.add(ObjectUtils.isEmpty(list.get(i).getNonTaxActTotalPrice()) ? new BigDecimal("0") : list.get(i).getNonTaxActTotalPrice());
                            taxActTotalPrice = taxActTotalPrice.add(ObjectUtils.isEmpty(list.get(i).getTaxActTotalPrice()) ? new BigDecimal("0") : list.get(i).getTaxActTotalPrice());
                        }
                    }
                    comb.setNonTaxActTotalPrice(nonTaxActTotalPrice);
                    comb.setOperatorUserId(operatorUserId);
                    srmPoLinesCombDAO_HI.saveOrUpdate(comb);
                    srmPoLinesCombDAO_HI.fluch();
                }
                String combIds = String.valueOf(list.stream().map(SrmPoHeadersEntity_HI_RO::getPoLineCombId).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.toList()).stream().distinct().collect(Collectors.joining("','")));
                combIds = ObjectUtils.isEmpty(combIds) ? "" : "'" + combIds + "'";
                //传输数据
                StringBuffer sql = new StringBuffer(SrmPoHeadersEntity_HI_RO.QUERY_COMB_TOEBS);
                sql.append(" and Splc.Po_Line_Comb_Id IN (" + combIds + ")");
                List<SrmPoHeadersEntity_HI_RO> dataToEbs = SrmPoHeadersDAO_HI_RO.findList(sql.toString());
                //将合并行传到EBS
                JSONArray dataArray = new JSONArray();
                for (SrmPoHeadersEntity_HI_RO ro : dataToEbs) {
                    JSONObject data = new JSONObject();
                    data.put("SOURCE_LINE_ID", ro.getPoLineCombId());
                    data.put("ORG_CODE", ro.getOrgCode());
                    data.put("TO_ORGANIZATION_CODE", ro.getOrganizationCode());
                    data.put("PO_NUM", ro.getErpPoNumber());
                    data.put("PO_LINE_NUM", 1);
                    data.put("ITEM_CODE", ro.getItemCode());
                    data.put("QUANTITY", ro.getNonTaxActTotalPrice());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    data.put("TRANSACTION_DATE", simpleDateFormat.format(new Date()));
                    data.put("DELIVER_TO_LOCATION_CODE", ro.getShipToLocationCode());
                    data.put("TAX_TOTAL_PRICE", ro.getTaxTotalPrice());
                    dataArray.add(data);
                }
                JSONObject baseQueryParams = new JSONObject();
                Date nowDate = new Date();
                String dateStr = DateUtil.date2Str(nowDate, "yyyy-MM-dd HH:mm:ss");
                baseQueryParams.put("lastUpdateDateF", dateStr); // 必传字段

                ESBClientUtils esbClient = new ESBClientUtils();
                JSONObject result = esbClient.doPost(ESBParams.SystemName.PCB_SRM.getValue(),
                        ESBParams.SystemName.SRM_SERVER.getValue(),
                        ESBParams.SystemName.EBS.getValue(),
                        ESBParams.ServiceName.callSrmProcedure.getValue(),
                        ESBParams.BusinessServiceName.REST_SRM_PUSHRCVLIST.getValue(),
                        "E",
                        baseQueryParams,
                        dataArray);
                String isSuccess = null;
                JSONObject bussObj = null;
                JSONArray jsonArray = null;
                JSONObject jsonObject = result.getJSONObject("data");
                if (jsonObject != null) {
                    JSONObject objData = jsonObject.getJSONObject("obj");
                    if (objData != null) {
                        jsonArray = objData.getJSONArray("businessData");
                        if (jsonArray != null && jsonArray.size() > 0) {
                            for (int i = 0; i < jsonArray.size(); i++) {
                                bussObj = jsonArray.getJSONObject(i);
                                if ("S".equals(bussObj.getString("PROCESS_CODE"))) {
                                    Integer revTransactionId = bussObj.getInteger("REV_TRANSACTION_ID");
                                    //更新合并行状态
                                    Integer combId = bussObj.getInteger("SOURCE_LINE_ID");
                                /*SrmPoLinesCombEntity_HI comb=srmPoLinesCombDAO_HI.getById(combId);
                                comb.setOperatorUserId(operatorUserId);
                                comb.setStatus("SETTLED");
                                srmPoLinesCombDAO_HI.saveOrUpdate(comb);
                                srmPoLinesCombDAO_HI.fluch();*/
                                    for (int n = 0; n < list.size(); n++) {
                                        if (list.get(n).getPoLineCombId().equals(combId)) {
                                            List<SrmPoLinesEntity_HI> lines = SrmPoLinesDAO_HI.findByProperty("poLineId", list.get(n).getPoLineId());
                                            List<SrmPoLinesEntity_HI> lineList = new ArrayList<>();
                                            if (!ObjectUtils.isEmpty(lines)) {
                                                for (SrmPoLinesEntity_HI lineRo : lines) {
                                                    lineRo.setOperatorUserId(operatorUserId);
                                                    lineRo.setStatus("SETTLED");
                                                    lineList.add(lineRo);
                                                }
                                                SrmPoLinesDAO_HI.saveOrUpdateAll(lineList);
                                                SrmPoLinesDAO_HI.fluch();
                                                //更新头状态
                                                List<SrmPoLinesEntity_HI> lineAll = SrmPoLinesDAO_HI.findByProperty("poHeaderId", list.get(n).getPoHeaderId());
                                                List<String> lineStatus = lineAll.stream().map(SrmPoLinesEntity_HI::getStatus).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
                                                if (!ObjectUtils.isEmpty(lineStatus) && lineStatus.size() == 1 && "SETTLED".equals(lineStatus.get(0))) {
                                                    SrmPoHeadersEntity_HI header = SrmPoHeadersDAO_HI.getById(list.get(n).getPoHeaderId());
                                                    header.setOperatorUserId(operatorUserId);
                                                    header.setStatus("SETTLED");
                                                    SrmPoHeadersDAO_HI.saveOrUpdate(header);
                                                    SrmPoHeadersDAO_HI.fluch();
                                                }


                                            }

                                        }
                                    }
                                } else {
                                    throw new UtilsException(bussObj.getString("PROCESS_MSG"));
                                }
                            }

                        }
                    }
                }
            }


        }


        return SToolUtils.convertResultJSONObj("S", "成功!", 1, null);

    }


    /**
     * Description：获取EBS技改编号
     *
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject savePoTechnicalNum() throws Exception {
        Date lastUpdateDateF = DateUtil.addDay(new Date(), -3);
        JSONObject baseQueryParams = new JSONObject();
        baseQueryParams.put("lastUpdateDateF", DateUtil.date2Str(lastUpdateDateF, "yyyy-MM-dd HH:mm:ss"));
        JSONArray businessData = new JSONArray();
        JSONObject json = new JSONObject();
        json.put("LAST_UPDATE_DATE_F", DateUtil.date2Str(lastUpdateDateF, "yyyy-MM-dd HH:mm:ss"));
        json.put("LAST_UPDATE_DATE_T", DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //获取技改编号
        json.put("DATA_SOURCE", "JG");
        businessData.add(json);
        JSONObject poTechnicalNumResult = esbClientUtils.doPost(ESBParams.SystemName.PCB_SRM.getValue(),
                ESBParams.SystemName.PCB_SRM.getValue(),
                ESBParams.SystemName.EBS.getValue(),
                ESBParams.ServiceName.callSrmProcedure.getValue(),
                ESBParams.BusinessServiceName.REST_SRM_PULLJGLIST.getValue(),
                SUCCESS_STATUS, baseQueryParams, businessData);
        if (null == poTechnicalNumResult.getString(STATUS) || "".equals(poTechnicalNumResult.getString(STATUS)) || !SUCCESS_STATUS.equals(poTechnicalNumResult.getString(STATUS))) {
            LOGGER.error("获取技改编号请求失败，错误：{}", poTechnicalNumResult.getString(MSG));
            return SToolUtils.convertResultJSONObj(ERROR_STATUS, "获取技改编号请求失败，错误：" + poTechnicalNumResult.getString(MSG), 0, poTechnicalNumResult);
        }
        JSONObject data = poTechnicalNumResult.getJSONObject(DATA);
        if (null == data) {
            LOGGER.info("获取技改编号定时接口，暂无数据");
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "暂无数据", 0, poTechnicalNumResult);
        }
        JSONObject obj = data.getJSONObject("obj");
        if (null == obj) {
            LOGGER.info("获取技改编号定时接口，暂无数据");
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "暂无数据", 0, poTechnicalNumResult);
        }
        JSONObject returnSystemParam = obj.getJSONObject(ESBParams.StatusCode.SYSTEM_PARAMS.getValue());
        if (null != returnSystemParam && null != returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()) && ESBParams.StatusCode.ESB_CODE_OK.getValue().equals(returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()))) {
            JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
            if (null == businessDataValue || businessDataValue.isEmpty()) {
                LOGGER.info("获取技改编号接口定时获取数据成功，获取数据：0");
                return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "获取技改编号接口没有可更新的数据，更新数据：0", 0, poTechnicalNumResult);
            }
            //更新技改编号表数据
            Integer count = updateSrmPoTechnicalNum(businessDataValue);
            LOGGER.info("技改编号接口获取数据成功，获取数据：" + businessDataValue.size());
            LOGGER.info("技改编号更新成功，更新数据：" + count);
            return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "技改编号更新成功，更新数据：" + count, count, poTechnicalNumResult);
        } else {
            JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
            if (null != businessDataValue && !businessDataValue.isEmpty()) {
                String processMsg = businessDataValue.getJSONObject(0).getString("PROCESS_MSG");
                LOGGER.info("技改编号接口获取数据：" + processMsg);
                return com.yhg.base.utils.SToolUtils.convertResultJSONObj(ERROR_STATUS, "技改编号接口获取数据，错误：" + processMsg, 0, poTechnicalNumResult);
            } else {
                LOGGER.error("技改编号调用接口异常");
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "技改编号调用接口异常", 0, poTechnicalNumResult);
            }
        }
    }


    /**
     * Description：获取EBS子项目编号
     *
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    public JSONObject savePoSubprojectNum() throws Exception {
        Date lastUpdateDateF = DateUtil.addDay(new Date(), -3);
        JSONObject baseQueryParams = new JSONObject();
        baseQueryParams.put("lastUpdateDateF", DateUtil.date2Str(lastUpdateDateF, "yyyy-MM-dd HH:mm:ss"));
        JSONArray businessData = new JSONArray();
        JSONObject json = new JSONObject();
        json.put("LAST_UPDATE_DATE_F", DateUtil.date2Str(lastUpdateDateF, "yyyy-MM-dd HH:mm:ss"));
        json.put("LAST_UPDATE_DATE_T", DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //获取技改编号
        json.put("DATA_SOURCE", "ZXM");
        businessData.add(json);
        JSONObject result = esbClientUtils.doPost(ESBParams.SystemName.PCB_SRM.getValue(),
                ESBParams.SystemName.PCB_SRM.getValue(),
                ESBParams.SystemName.EBS.getValue(),
                ESBParams.ServiceName.callSrmProcedure.getValue(),
                ESBParams.BusinessServiceName.REST_SRM_PULLJGLIST.getValue(),
                SUCCESS_STATUS, baseQueryParams, businessData);
        if (null == result.getString(STATUS) || "".equals(result.getString(STATUS)) || !SUCCESS_STATUS.equals(result.getString(STATUS))) {
            LOGGER.error("获取子项目编号请求失败，错误：{}", result.getString(MSG));
            return com.yhg.base.utils.SToolUtils.convertResultJSONObj(ERROR_STATUS, "获取子项目编号请求失败，错误：" + result.getString(MSG), 0, result);
        }
        JSONObject data = result.getJSONObject(DATA);
        if (null == data) {
            LOGGER.info("获取子项目编号定时接口，暂无数据");
            return com.yhg.base.utils.SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "暂无数据", 0, result);
        }
        JSONObject obj = data.getJSONObject("obj");
        if (null == obj) {
            LOGGER.info("获取子项目编号定时接口，暂无数据");
            return com.yhg.base.utils.SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "暂无数据", 0, result);
        }
        JSONObject returnSystemParam = obj.getJSONObject(ESBParams.StatusCode.SYSTEM_PARAMS.getValue());
        if (null != returnSystemParam && null != returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()) && ESBParams.StatusCode.ESB_CODE_OK.getValue().equals(returnSystemParam.getString(ESBParams.StatusCode.ESB_CODE.getValue()))) {
            JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
            if (null == businessDataValue || businessDataValue.isEmpty()) {
                LOGGER.info("获取子项目编号接口定时获取数据成功，获取数据：0");
                return com.yhg.base.utils.SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "获取子项目编号接口没有可更新的数据，更新数据：0", 0, result);
            }
            //更新技改编号表数据
            Integer count = updateSrmPoSubprojectNum(businessDataValue);
            LOGGER.info("子项目编号接口获取数据成功，获取数据：" + businessDataValue.size());
            LOGGER.info("子项目编号更新成功，更新数据：" + count);
            return com.yhg.base.utils.SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "子项目编号更新成功，更新数据：" + count, count, result);
        } else {
            JSONArray businessDataValue = obj.getJSONArray(ESBParams.StatusCode.BUSINESS_DATA.getValue());
            if (null != businessDataValue && !businessDataValue.isEmpty()) {
                String processMsg = businessDataValue.getJSONObject(0).getString("PROCESS_MSG");
                LOGGER.info("子项目编号接口获取数据：" + processMsg);
                return com.yhg.base.utils.SToolUtils.convertResultJSONObj(ERROR_STATUS, "子项目编号接口获取数据，错误：" + processMsg, 0, result);
            } else {
                LOGGER.error("子项目编号调用接口异常");
                return SToolUtils.convertResultJSONObj(ERROR_STATUS, "子项目编号调用接口异常", 0, result);
            }
        }
    }


    /**
     * Description：获取EBS技改编号-保存
     *
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Integer updateSrmPoTechnicalNum(JSONArray businessData) throws Exception {
        Integer count = 0;
        if (null != businessData && businessData.size() > 0) {
            for (int i = 0; i < businessData.size(); i++) {
                JSONObject obj = businessData.getJSONObject(i);
                try {
                    String technicalCode = obj.getString("JGBH");
                    String technicalName = obj.getString("JG_DESC");
                    String enable = obj.getString("JG_ENABLE_FLAG");
                    Date effectiveDate = null;
                    Date expirationDate = null;
                    Date creationDate = null;
                    Date lastUpdateDate = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (!ObjectUtils.isEmpty(obj.getString("JG_START_DATE"))) {
                        effectiveDate = sdf.parse(obj.getString("JG_START_DATE"));
                    }
                    if (!ObjectUtils.isEmpty(obj.getString("JG_END_DATE"))) {
                        expirationDate = sdf.parse(obj.getString("JG_END_DATE"));
                    }
                    if (!ObjectUtils.isEmpty(obj.getString("JG_CREATION_DATE"))) {
                        creationDate = sdf.parse(obj.getString("JG_CREATION_DATE"));
                    }
                    if (!ObjectUtils.isEmpty(obj.getString("JG_LAST_UPDATE_DATE"))) {
                        lastUpdateDate = sdf.parse(obj.getString("JG_LAST_UPDATE_DATE"));
                    }

                    String sourceId = obj.getString("JG_ID");
                    List<SrmPoTechnicalNumEntity_HI> poTechnicalNumList = srmPoTechnicalNumDAO_HI.findByProperty("sourceId", sourceId);
                    SrmPoTechnicalNumEntity_HI poTechnicalNum = new SrmPoTechnicalNumEntity_HI();
                    if (!ObjectUtils.isEmpty(poTechnicalNumList)) {
                        poTechnicalNum = poTechnicalNumList.get(0);
                    }
                    poTechnicalNum.setTechnicalCode(technicalCode);
                    poTechnicalNum.setTechnicalName(technicalName);
                    poTechnicalNum.setSourceId(sourceId);
                    poTechnicalNum.setEnable(enable);
                    poTechnicalNum.setEffectiveDate(effectiveDate);
                    poTechnicalNum.setExpirationDate(expirationDate);
                    poTechnicalNum.setCreationDate(creationDate);
                    poTechnicalNum.setLastUpdateDate(lastUpdateDate);
                    poTechnicalNum.setCreatedBy(-1);
                    poTechnicalNum.setLastUpdatedBy(-1);
                    poTechnicalNum.setOperatorUserId(-1);
                    count++;
                    srmPoTechnicalNumDAO_HI.saveOrUpdate(poTechnicalNum);
                    srmPoTechnicalNumDAO_HI.fluch();
                } catch (Exception e) {
                    LOGGER.error("保存技改编号参数失败！参数：{}", obj.toJSONString() + "，异常：{}", e.getMessage());
                    throw new UtilsException("保存技改编号参数失败！参数：" + obj.toJSONString() + "，异常：" + e.getMessage());
                }
            }
        }
        return count;
    }

    /**
     * Description：获取EBS子项目编号-保存
     *
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-21     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Integer updateSrmPoSubprojectNum(JSONArray businessData) throws Exception {
        Integer count = 0;
        if (null != businessData && businessData.size() > 0) {
            for (int i = 0; i < businessData.size(); i++) {
                JSONObject obj = businessData.getJSONObject(i);
                try {
                    String subprojectCode = obj.getString("ZXM_NUM");
                    String subprojectName = obj.getString("ZXM_DESC");
                    String jgId = obj.getString("JG_ID");
                    String enable = obj.getString("ZXM_ENABLE_FLAG");
                    Integer technicalId = null;
                    Date effectiveDate = null;
                    Date expirationDate = null;
                    Date creationDate = null;
                    Date lastUpdateDate = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (!ObjectUtils.isEmpty(obj.getString("ZXM_START_DATE"))) {
                        effectiveDate = sdf.parse(obj.getString("ZXM_START_DATE"));
                    }
                    if (!ObjectUtils.isEmpty(obj.getString("ZXM_END_DATE"))) {
                        expirationDate = sdf.parse(obj.getString("ZXM_END_DATE"));
                    }
                    if (!ObjectUtils.isEmpty(obj.getString("ZXM_CREATION_DATE"))) {
                        creationDate = sdf.parse(obj.getString("ZXM_CREATION_DATE"));
                    }
                    if (!ObjectUtils.isEmpty(obj.getString("ZXM_LAST_UPDATE_DATE"))) {
                        lastUpdateDate = sdf.parse(obj.getString("ZXM_LAST_UPDATE_DATE"));
                    }
                    String sourceId = obj.getString("ZXM_ID");
                    List<SrmPoSubprojectNumEntity_HI> poSubprojectNumList = srmPoSubprojectNumDAO_HI.findByProperty("sourceId", sourceId);
                    SrmPoSubprojectNumEntity_HI poSubprojectNum = new SrmPoSubprojectNumEntity_HI();
                    if (!ObjectUtils.isEmpty(poSubprojectNumList)) {
                        poSubprojectNum = poSubprojectNumList.get(0);
                    } else {
                        List<SrmPoTechnicalNumEntity_HI> poTechnicalNumList = srmPoTechnicalNumDAO_HI.findByProperty("sourceId", jgId);
                        if (ObjectUtils.isEmpty(poTechnicalNumList)) {
                            throw new UtilsException("系统中未存在子项目编号" + subprojectCode + " " + subprojectName + "所对应的技改编号");
                        } else {
                            technicalId = poTechnicalNumList.get(0).getTechnicalId();
                        }
                    }
                    poSubprojectNum.setSubprojectCode(subprojectCode);
                    poSubprojectNum.setSubprojectName(subprojectName);
                    poSubprojectNum.setTechnicalId(technicalId);
                    poSubprojectNum.setSourceId(sourceId);
                    poSubprojectNum.setEnable(enable);
                    poSubprojectNum.setEffectiveDate(effectiveDate);
                    poSubprojectNum.setExpirationDate(expirationDate);
                    poSubprojectNum.setCreationDate(creationDate);
                    poSubprojectNum.setLastUpdateDate(lastUpdateDate);
                    poSubprojectNum.setCreatedBy(-1);
                    poSubprojectNum.setLastUpdatedBy(-1);
                    poSubprojectNum.setOperatorUserId(-1);
                    count++;
                    srmPoSubprojectNumDAO_HI.saveOrUpdate(poSubprojectNum);
                    srmPoSubprojectNumDAO_HI.fluch();
                } catch (Exception e) {
                    LOGGER.error("保存子项目编号参数失败！参数：{}", obj.toJSONString() + "，异常：{}", e.getMessage());
                    throw new UtilsException("保存子项目编号参数失败！参数：" + obj.toJSONString() + "，异常：" + e.getMessage());
                }
            }
        }
        return count;
    }

    /**
     * Description：合同采购订单详情查询采购订单
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
    public Pagination<SrmPoHeadersEntity_HI_RO> findOrderInfoByContract(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer queryString = new StringBuffer();
        if (!ObjectUtils.isEmpty(jsonParams.getInteger("contractId"))) {
            Integer contractId=jsonParams.getInteger("contractId");
            SrmOkcContractsEntity_HI okcContract=srmOkcContractsDAO_HI.getById(contractId);
            if(!ObjectUtils.isEmpty(okcContract)){
                List<SrmOkcContractsEntity_HI> okcContractList=srmOkcContractsDAO_HI.findByProperty("contractCode",okcContract.getContractCode());
                //取版本最大值
                List<Integer> versionNumberList = okcContractList.stream().map(SrmOkcContractsEntity_HI::getVersionNumber).collect(Collectors.toList());
                Integer maxVersionNumber=Collections.max(versionNumberList);
                //当前版本，走订单表查询
                if(okcContract.getVersionNumber().equals(maxVersionNumber)){
                    queryString.append(SrmPoHeadersEntity_HI_RO.QUERY_ORDER_LIST_SQL);
                    queryString.append(" and sph.contract_Id="+contractId);
                    if (!ObjectUtils.isEmpty(jsonParams.getInteger("contractItemId"))) {
                        List<SrmPoLinesEntity_HI> poLine = srmPoLinesDAO_HI.findByProperty("contractItemId", jsonParams.getInteger("contractItemId"));
                        if (!ObjectUtils.isEmpty(poLine)) {
                            String poHeaderIdByContract = String.valueOf(poLine.stream().map(SrmPoLinesEntity_HI::getPoHeaderId).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.toList()).stream().distinct().collect(Collectors.joining(",")));
                            queryString.append(" and sph.po_header_id in (" + poHeaderIdByContract + ") ");
                            if (!"EX".equals(jsonParams.getString("varPlatformCode"))) {
                                queryString.append(" AND (check_org_f(" + jsonParams.getInteger("varUserId") + ", sph.org_id) = 'Y' or sph.created_by =" + jsonParams.getInteger("varUserId") + ") ");
                            }
                            queryString.append(" ORDER BY sph.po_header_id desc, spl.po_line_id asc, spl.line_number DESC");
                        } else {
                            queryString.append(" and 1=2");
                        }
                    }

                }else{
                    queryString.append(SrmPoHeaderArchivesEntity_HI_RO.QUERY_ORDER_HEADER_ARCHIVES_BY_SUPPLIER_SQL);
                    queryString.append(" and a.contract_Id="+contractId);
                    if (!ObjectUtils.isEmpty(jsonParams.getInteger("contractItemId"))) {
                        List<SrmPoLineArchivesEntity_HI> poLine = srmPoLineArchivesDAO_HI.findByProperty("contractItemId", jsonParams.getInteger("contractItemId"));
                        if (!ObjectUtils.isEmpty(poLine)) {
                            String poHeaderIdByContract = String.valueOf(poLine.stream().map(SrmPoLineArchivesEntity_HI::getPoHeaderId).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.toList()).stream().distinct().collect(Collectors.joining(",")));
                            queryString.append(" and a.po_header_id in (" + poHeaderIdByContract + ") ");
                            if (!"EX".equals(jsonParams.getString("varPlatformCode"))) {
                                queryString.append(" AND (check_org_f(" + jsonParams.getInteger("varUserId") + ", a.org_id) = 'Y' or a.created_by =" + jsonParams.getInteger("varUserId") + ") ");
                            }
                            queryString.append(" ORDER BY a.po_header_id desc");
                        } else {
                            queryString.append(" and 1=2");
                        }
                    }
                }

            }
        }
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPoHeadersEntity_HI_RO> result = SrmPoHeadersDAO_HI_RO.findPagination(queryString.toString(), countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }
}
