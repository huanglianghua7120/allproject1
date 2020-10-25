package saaf.common.fmw.base.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.base.utils.DateUtil;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseNotificationsEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafResponsibilitysEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseNotificationsEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseNotifications;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeadersEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoNoticeEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidEntity_HI_RO;
import saaf.common.fmw.utils.SrmUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：公告
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseNotificationsServer")
public class SrmBaseNotificationsServer implements ISrmBaseNotifications {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseNotificationsServer.class);

    @Autowired
    private ViewObject<SrmBaseNotificationsEntity_HI> srmBaseNotificationsDAO_HI;

    @Autowired
    private BaseViewObject<SrmBaseNotificationsEntity_HI_RO> srmBaseNotificationsDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPonAuctionHeadersEntity_HI_RO> srmPonAuctionHeadersDAO_HI_RO;//洽谈头层

    @Autowired
    private BaseViewObject<SrmPoHeadersEntity_HI_RO> srmPoHeadersDAO_HI_RO;//订单头

    @Autowired
    private BaseViewObject<SrmPoNoticeEntity_HI_RO> srmPoNoticeDao_HI_RO;//送货通知头

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;//规则编号生成

    @Autowired
    private BaseViewObject<SrmPonBidEntity_HI_RO> srmPonBidDAO_HI_RO;//供应商报价

    @Autowired
    private BaseViewObject<SaafResponsibilitysEntity_HI_RO> saafResponsibilitysDAO_HI_RO;

    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;

    public SrmBaseNotificationsServer() {
        super();
    }

    /**
     * 通知插入insert——系统代办通知
     *
     * @param menuType
     * @param notificationContent
     * @param receiverId
     * @param tableName
     * @param tableId
     * @param tableIdName
     * @param functionUrl
     * @param userId
     * @return
     */
    @Override
    public String insertSrmBaseNotifications(String menuType, String notificationContent, Integer receiverId, String tableName, Integer tableId, String tableIdName, String functionUrl, Integer userId) {
        String result = "";
        SrmBaseNotificationsEntity_HI notificationsEntity = new SrmBaseNotificationsEntity_HI();
        try {
            String notificationCode = saafSequencesUtil.getDocSequences("srm_base_notifications".toUpperCase(), "TZ-" + DateUtil.date2Str(new Date(), "yyyyMMdd"), 4);
            notificationsEntity.setNotificationCode(notificationCode);
        } catch (Exception e) {
            LOGGER.error("创建系统通知编号出错！", e.getMessage());
            result = "创建系统通知编号出错";
            return result;
        }
        notificationsEntity.setNotificationType("14");//通知
        notificationsEntity.setNotificationStatus("1");//未处理
        notificationsEntity.setViewedFlag("N");//未查看
        notificationsEntity.setMenuType(menuType);
        notificationsEntity.setNotificationContent(notificationContent);
        notificationsEntity.setReceiverId(receiverId);
        notificationsEntity.setTableName(tableName);
        notificationsEntity.setTableId(tableId);
        notificationsEntity.setTableIdName(tableIdName);
        notificationsEntity.setFunctionUrl(functionUrl);
        notificationsEntity.setOperatorUserId(userId);
        srmBaseNotificationsDAO_HI.saveEntity(notificationsEntity);
        return result;
    }

    /**
     * 标书发布查询——内部（带分页）——系统代办通知
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SrmPonAuctionHeadersEntity_HI_RO> findSrmBaseNotificationsByAuction(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        String auctionTitleOrNumber = jsonParams.getString("auctionTitleOrNumber");//标题或编码
        String notificationStatus = jsonParams.getString("notificationStatus");//处理状态
        String timeFrame = jsonParams.getString("timeFrame");//时间范围
        String userId = jsonParams.getString("varUserId");

        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(auctionTitleOrNumber) || SrmUtils.isContainSQL(timeFrame)) {
            return null;
        }

        //添加根据当前人职责查询数据
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sbr = new StringBuffer(SrmBaseNotificationsEntity_HI_RO.QUERY_COUNT_RESPONS_SQL);
        SaafToolUtils.parperParam(jsonParams, "sur.user_id", "varUserId", sbr, map, "=");
        List<SrmPonAuctionHeadersEntity_HI_RO> counList = srmPonAuctionHeadersDAO_HI_RO.findList(sbr, map);
        SrmPonAuctionHeadersEntity_HI_RO countEntity = counList.get(0);

        StringBuffer sb = new StringBuffer(SrmBaseNotificationsEntity_HI_RO.QUERY_AUCTIONPUBLISHLIST);
        //已处理
        if (null != notificationStatus && "2".equals(notificationStatus)) {
            sb.append(" LEFT JOIN srm_base_notifications sbn ON sbn.table_id = t.auction_header_id ");
        }
        sb.append(" WHERE 1 = 1 ");
        if (countEntity.getCount() == 0) {
            SaafToolUtils.parperParam(jsonParams, "t.created_by", "userId", sb, map, "=");//当前登录用户Id
        }
        if (null != auctionTitleOrNumber && !"".equals(auctionTitleOrNumber)) {
            sb.append(" AND (t.auction_title LIKE '%" + auctionTitleOrNumber + "%' OR t.auction_number LIKE '%" + auctionTitleOrNumber + "%') ");
        }

        //未处理
        if (null != notificationStatus && "1".equals(notificationStatus)) {
            sb.append(" AND t.auction_status = 'DRAFT' AND t.publish_approval_status = 'APPROVED' ");
            if (null != timeFrame && !"".equals(timeFrame)) {
                sb.append(" AND trunc(t.creation_date) >= trunc(SYSDATE - " + timeFrame + ") ");
            }
        }

        //已处理
        if (null != notificationStatus && "2".equals(notificationStatus)) {
            sb.append(" AND sbn.notification_type= '11'");
            if (null != timeFrame && !"".equals(timeFrame)) {
                sb.append(" AND trunc(sbn.creation_date) >= trunc(SYSDATE - " + timeFrame + ") ");
            }
            SaafToolUtils.parperParam(jsonParams, "sbn.notification_status", "notificationStatus", sb, map, "=");
            SaafToolUtils.parperParam(jsonParams, "sbn.created_by", "varUserId", sb, map, "=");//当前登录用户Id
        }
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY t.bid_start_date DESC"); //排序
        Pagination<SrmPonAuctionHeadersEntity_HI_RO> result = srmPonAuctionHeadersDAO_HI_RO.findPagination(sb,countSql, map, pageIndex, pageRows);
        return result;
    }

    /**
     * 订单确认查询——内部（带分页）——系统代办通知
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SrmPoHeadersEntity_HI_RO> findSrmBaseNotificationsByPoHeader(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        String poNumberOrOrgIdOrSupplierName = jsonParams.getString("poNumberOrOrgIdOrSupplierName");
        String notificationStatus = jsonParams.getString("notificationStatus");//处理状态
        String timeFrame = jsonParams.getString("timeFrame");//时间范围

        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(poNumberOrOrgIdOrSupplierName) || SrmUtils.isContainSQL(timeFrame)) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer(SrmBaseNotificationsEntity_HI_RO.QUERY_POHEADERLIST);
        //已处理
        if (null != notificationStatus && "2".equals(notificationStatus)) {
            sb.append(" LEFT JOIN srm_base_notifications sbn ON sbn.table_id = t.po_header_id ");
        }
        sb.append(" WHERE 1 = 1 ");
        if (null != poNumberOrOrgIdOrSupplierName && !"".equals(poNumberOrOrgIdOrSupplierName)) {
            sb.append(" AND (t.po_number LIKE '%" + poNumberOrOrgIdOrSupplierName + "%' OR si.inst_name like '%" + poNumberOrOrgIdOrSupplierName + "%' OR sps.supplier_name like '%" + poNumberOrOrgIdOrSupplierName + "%') ");
        }

        //未处理
        if (null != notificationStatus && "1".equals(notificationStatus)) {
            sb.append(" AND t.po_doc_type = 'ORDER' AND t.status ='APPROVED' ");
            sb.append(" AND t.po_header_id IN \n" +
                    "(SELECT spl.po_header_id FROM srm_po_lines spl WHERE spl.status = 'OPEN' AND spl.feedback_status = 'SUBMITTED' AND (spl.feedback_result = 'REFUSE' OR spl.feedback_result = 'ADJUST')) ");
            if (null != timeFrame && !"".equals(timeFrame)) {
                sb.append(" AND trunc(t.creation_date) >= trunc(SYSDATE - " + timeFrame + ") ");
            }
            if (null == jsonParams.get("varEmployeeId")) {
                throw new RuntimeException("请联系系统管理员，维护人员信息");
            }
            SaafToolUtils.parperParam(jsonParams, "t.buyer_id", "varEmployeeId", sb, map, "=");//当前登录用户的人员varEmployeeId
        }
        //已处理
        if (null != notificationStatus && "2".equals(notificationStatus)) {
            sb.append(" AND sbn.notification_type = '12'");
            if (null != timeFrame && !"".equals(timeFrame)) {
                sb.append(" AND trunc(t.creation_date) >= trunc(SYSDATE - " + timeFrame + ") ");
            }
            SaafToolUtils.parperParam(jsonParams, "sbn.notification_status", "notificationStatus", sb, map, "=");
            SaafToolUtils.parperParam(jsonParams, "sbn.created_by", "varUserId", sb, map, "=");//当前登录用户Id
        }
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY t.creation_date DESC"); //排序
        Pagination<SrmPoHeadersEntity_HI_RO> result = srmPoHeadersDAO_HI_RO.findPagination(sb,countSql, map, pageIndex, pageRows);
        if (null != result.getData() && result.getData().size() > 0) {
            String flag = "";
            //已处理
            if (null != notificationStatus && "2".equals(notificationStatus)) {
                flag = "Y";
            }
            //未处理
            if (null != notificationStatus && "1".equals(notificationStatus)) {
                flag = "N";
            }
            for (SrmPoHeadersEntity_HI_RO k : result.getData()) {
                k.setAttribute1(flag);
            }
        }
        return result;
    }

    /**
     * 订单反馈查询——供应商（带分页）——系统代办通知
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SrmPoHeadersEntity_HI_RO> findSrmBaseNotificationsByPoHeaderFeedBack(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Pagination<SrmPoHeadersEntity_HI_RO> result = new Pagination<>();//最终结果
        if (null == jsonParams.getInteger("varSupplierId") || "".equals(jsonParams.getInteger("varSupplierId"))) {
            return result;
        }
        String poNumberOrOrgIdOrOrganizationName = "";
        String notificationStatus = "";//处理状态
        String timeFrame = "";//时间范围
        if (null != jsonParams.getString("poNumberOrOrgIdOrOrganizationName") && !"".equals(jsonParams.getString("poNumberOrOrgIdOrOrganizationName"))) {
            poNumberOrOrgIdOrOrganizationName = jsonParams.getString("poNumberOrOrgIdOrOrganizationName");//订单号、业务实体、收货组织
        }
        if (null != jsonParams.getString("notificationStatus") && !"".equals(jsonParams.getString("notificationStatus"))) {
            notificationStatus = jsonParams.getString("notificationStatus");
        }
        if (null != jsonParams.getString("timeFrame") && !"".equals(jsonParams.getString("timeFrame"))) {
            timeFrame = jsonParams.getString("timeFrame");
        }

        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(poNumberOrOrgIdOrOrganizationName) || SrmUtils.isContainSQL(timeFrame)) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer(SrmBaseNotificationsEntity_HI_RO.QUERY_POLINELIST);
        if (null != poNumberOrOrgIdOrOrganizationName && !"".equals(poNumberOrOrgIdOrOrganizationName)) {
            sb.append(" AND (sph.po_number LIKE '%" + poNumberOrOrgIdOrOrganizationName + "%' OR si.inst_name LIKE '%" + poNumberOrOrgIdOrOrganizationName + "%' OR si2.inst_name LIKE '%" + poNumberOrOrgIdOrOrganizationName + "%')\n");
        }
        //未处理
        if (null != notificationStatus && "1".equals(notificationStatus)) {
            sb.append(" AND sph.po_doc_type = 'ORDER' AND sph.status = 'APPROVED'\n");
            sb.append(" AND spl.status = 'OPEN' AND spl.feedback_status = 'NON_FEEDBACK'\n");
            if (null != timeFrame && !"".equals(timeFrame)) {
                sb.append(" AND trunc(SYSDATE - " + timeFrame + ") <= trunc(spl.creation_date)\n");
            }
            //当前登录用户的供应商varSupplierId
            SaafToolUtils.parperParam(jsonParams, "sph.supplier_id", "varSupplierId", sb, map, "=");
        }
        //已处理
        if (null != notificationStatus && "2".equals(notificationStatus)) {
            sb.append("AND    EXISTS (SELECT 1\n" +
                      "        FROM   srm_base_notifications sbn\n" +
                      "        WHERE  sbn.table_id = spl.po_line_id\n" +
                      "        AND    sbn.table_name = 'srm_po_lines'\n" +
                      "        AND    sbn.notification_type = '22'\n");
            if (timeFrame != null && !"".equals(timeFrame)) {
                sb.append("AND    trunc(sbn.creation_date) >= trunc(SYSDATE - " + timeFrame + ")\n");
            }
            if (jsonParams.getString("notificationStatus") != null && !"".equals(jsonParams.getString("notificationStatus").trim())) {
                sb.append("AND    sbn.notification_status = " + jsonParams.getString("notificationStatus") + "\n");
            }
            //当前登录用户Id
            if (jsonParams.getInteger("varUserId") != null) {
                sb.append("AND    sbn.created_by = " + jsonParams.getInteger("varUserId") + "\n");
            }
            sb.append(")\n");
        }
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY spl.creation_date DESC"); //排序
        result = srmPoHeadersDAO_HI_RO.findPagination(sb,countSql, map, pageIndex, pageRows);
        String flag = null;
        if (null != result.getData() && result.getData().size() > 0) {
            //已处理
            if ("2".equals(notificationStatus)) {
                flag = "Y";
            } else if ("1".equals(notificationStatus)) {  //未处理
                flag = "N";
            }
            for (SrmPoHeadersEntity_HI_RO k : result.getData()) {
                k.setAttribute1(flag);
            }
        }
        return result;
    }

    /**
     * 送货通知确认查询——内部（带分页）——系统代办通知
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SrmPoNoticeEntity_HI_RO> findSrmBaseNotificationsByPoNotice(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        if (jsonParams.get("varEmployeeId") == null) {
            return new Pagination<SrmPoNoticeEntity_HI_RO>();
        }
        String poNoticeCodeOrOrgIdOrSupplierName = "";
        String notificationStatus = "";//处理状态
        String timeFrame = "";//时间范围
        if (null != jsonParams.getString("poNoticeCodeOrOrgIdOrSupplierName") && !"".equals(jsonParams.getString("poNoticeCodeOrOrgIdOrSupplierName"))) {
            poNoticeCodeOrOrgIdOrSupplierName = jsonParams.getString("poNoticeCodeOrOrgIdOrSupplierName");//通知单号、业务实体、供应商
        }
        if (null != jsonParams.getString("notificationStatus") && !"".equals(jsonParams.getString("notificationStatus"))) {
            notificationStatus = jsonParams.getString("notificationStatus");
        }
        if (null != jsonParams.getString("timeFrame") && !"".equals(jsonParams.getString("timeFrame"))) {
            timeFrame = jsonParams.getString("timeFrame");
        }

        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(poNoticeCodeOrOrgIdOrSupplierName) || SrmUtils.isContainSQL(timeFrame)) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer(SrmBaseNotificationsEntity_HI_RO.QUERY_PONOTICELIST);
        //已处理
        if (null != notificationStatus && "2".equals(notificationStatus)) {
            sb.append(" LEFT JOIN srm_base_notifications sbn ON sbn.table_id = t.po_notice_id ");
        }
        sb.append(" WHERE 1 = 1 ");
        if (null != poNoticeCodeOrOrgIdOrSupplierName && !"".equals(poNoticeCodeOrOrgIdOrSupplierName)) {
            sb.append(" AND (t.po_notice_code LIKE '%" + poNoticeCodeOrOrgIdOrSupplierName + "%' OR si.inst_name LIKE '%" + poNoticeCodeOrOrgIdOrSupplierName + "%' OR sps.supplier_name like '%" + poNoticeCodeOrOrgIdOrSupplierName + "%') ");
        }

        //未处理
        if (null != notificationStatus && "1".equals(notificationStatus)) {
            sb.append(" AND t.po_notice_status = 'APPROVED' ");
            sb.append(" AND t.po_notice_id IN \n" +
                    "(SELECT spn.po_notice_id FROM srm_po_notice_line spn WHERE spn.line_status = 'OPEN' AND spn.feedback_status = 'SUBMITTED') ");
            if (null != timeFrame && !"".equals(timeFrame)) {
                sb.append(" AND trunc(t.creation_date) >= trunc(SYSDATE - " + timeFrame + ")\n");
            }
            if (jsonParams.get("varEmployeeId") == null) {
                throw new RuntimeException("请联系系统管理员，维护人员信息");
            }
            SaafToolUtils.parperParam(jsonParams, "t.buyer_id", "varEmployeeId", sb, map, "=");//当前登录用户的人员varEmployeeId
        }
        //已处理
        if (null != notificationStatus && "2".equals(notificationStatus)) {
            sb.append(" AND sbn.notification_type= '13'");
            if (null != timeFrame && !"".equals(timeFrame)) {
                sb.append(" AND DATE(sbn.creation_date) >= trunc(SYSDATE - " + timeFrame + ")\n");
            }
            SaafToolUtils.parperParam(jsonParams, "sbn.notification_status", "notificationStatus", sb, map, "=");
            SaafToolUtils.parperParam(jsonParams, "sbn.created_by", "varUserId", sb, map, "=");//当前登录用户Id
        }
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY t.creation_date DESC"); //排序
        Pagination<SrmPoNoticeEntity_HI_RO> result = srmPoNoticeDao_HI_RO.findPagination(sb,countSql, map, pageIndex, pageRows);
        if (null != result.getData() && result.getData().size() > 0) {
            String flag = "";
            //已处理
            if (null != notificationStatus && "2".equals(notificationStatus)) {
                flag = "Y";
            }
            //未处理
            if (null != notificationStatus && "1".equals(notificationStatus)) {
                flag = "N";
            }
            for (SrmPoNoticeEntity_HI_RO k : result.getData()) {
                k.setAttribute1(flag);
            }
        }
        return result;
    }

    /**
     * 送货通知反馈查询——供应商（带分页）——系统代办通知
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SrmPoNoticeEntity_HI_RO> findSrmBaseNotificationsByPoNoticeFeedBack(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Pagination<SrmPoNoticeEntity_HI_RO> result = new Pagination<>();//最终结果
        if (null == jsonParams.getInteger("varSupplierId") || "".equals(jsonParams.getInteger("varSupplierId"))) {
            return result;
        }
        //通知单号、业务实体、收货组织
        String poNoticeCodeOrOrgIdOrOrganizationName = jsonParams.getString("poNoticeCodeOrOrgIdOrOrganizationName");
        //处理状态
        String notificationStatus = jsonParams.getString("notificationStatus");
        //时间范围
        String timeFrame = jsonParams.getString("timeFrame");
        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(poNoticeCodeOrOrgIdOrOrganizationName) || SrmUtils.isContainSQL(timeFrame)) {
            return null;
        }

        Map<String, Object> map = new HashMap();
        StringBuffer sb = new StringBuffer(SrmBaseNotificationsEntity_HI_RO.QUERY_PONOTICELINELIST);
        sb.append(" WHERE 1 = 1 ");
        if (null != poNoticeCodeOrOrgIdOrOrganizationName && !"".equals(poNoticeCodeOrOrgIdOrOrganizationName)) {
            sb.append(" AND (spn.po_notice_code LIKE '%" + poNoticeCodeOrOrgIdOrOrganizationName + "%' OR si.inst_name LIKE '%" + poNoticeCodeOrOrgIdOrOrganizationName + "%' OR si2.inst_name LIKE '%" + poNoticeCodeOrOrgIdOrOrganizationName + "%') ");
        }
        //未处理
        if ("1".equals(notificationStatus)) {
            sb.append(" AND spn.po_notice_status = 'APPROVED'\n");
            sb.append(" AND t.line_status = 'OPEN' AND t.feedback_status = 'NON_FEEDBACK'\n");
            if (null != timeFrame && !"".equals(timeFrame)) {
                sb.append(" AND trunc(SYSDATE - " + timeFrame + ") <= trunc(t.creation_date) ");
            }
            SaafToolUtils.parperParam(jsonParams, "spn.supplier_id", "varSupplierId", sb, map, "=");//当前登录用户的供应商varSupplierId
        }
        //已处理
        if (null != notificationStatus && "2".equals(notificationStatus)) {
            sb.append(" LEFT JOIN srm_base_notifications sbn ON sbn.table_id = t.line_id\n");
            sb.append(" AND sbn.notification_type = '23'\n");
            if (null != timeFrame && !"".equals(timeFrame)) {
                sb.append(" AND trunc(SYSDATE - " + timeFrame + ") <= trunc(sbn.creation_date) ");
            }
            SaafToolUtils.parperParam(jsonParams, "sbn.notification_status", "notificationStatus", sb, map, "=");
            SaafToolUtils.parperParam(jsonParams, "sbn.created_by", "varUserId", sb, map, "=");//当前登录用户Id
        }
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY t.creation_date DESC"); //排序
        String flag = null;
        result = srmPoNoticeDao_HI_RO.findPagination(sb,countSql, map, pageIndex, pageRows);
        if (null != result.getData() && result.getData().size() > 0) {
            //已处理
            if (null != notificationStatus && "2".equals(notificationStatus)) {
                flag = "Y";
            }
            //未处理
            if (null != notificationStatus && "1".equals(notificationStatus)) {
                flag = "N";
            }
            for (SrmPoNoticeEntity_HI_RO k : result.getData()) {
                k.setAttribute1(flag);
            }
        }
        return result;
    }

    /**
     * 通知查询（带分页）——系统代办通知
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SrmBaseNotificationsEntity_HI_RO> findSrmBaseNotificationList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Integer userId = jsonParams.getInteger("varUserId");//当前登录用户Id
        String notificationMenuTypeOrContent = jsonParams.getString("notificationMenuTypeOrContent");
        SaafUsersEntity_HI user = saafUsersDAO_HI.getById(userId);

        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(notificationMenuTypeOrContent)) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer(
                        "SELECT *\n" +
                        "FROM   srm_base_notifications t\n" +
                        "WHERE  t.notification_type = '14'\n" +
                        "AND    (t.receiver_id = " + userId + " OR\n" +
                        "      t.receiver_id =\n" +
                        "      (SELECT sr.responsibility_id\n" +
                        "         FROM   saaf_user_resp       sur\n" +
                        "               ,saaf_responsibilitys sr\n" +
                        "         WHERE  sr.responsibility_name = '开发专员'\n" +
                        "         AND    sr.responsibility_id = sur.responsibility_id\n" +
                        "         AND    sur.user_id = " + userId + "))\n");
        if (null != notificationMenuTypeOrContent && !"".equals(notificationMenuTypeOrContent)) {
            sb.append(" AND (t.menu_type LIKE '%" + notificationMenuTypeOrContent + "%' OR t.notification_content LIKE '%" + notificationMenuTypeOrContent + "%') ");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String creationDate = null;
        try {
            creationDate = dateFormat.format(user.getCreationDate());
        } catch(Exception e) {
            creationDate = dateFormat.format(new Date());
        }
        sb.append(" AND    t.creation_date > to_date('" + creationDate + "', 'yyyy-mm-dd')\n"); //排序
        SaafToolUtils.parperParam(jsonParams, "t.viewed_flag", "viewedFlag", sb, map, "=");//未读标识N，已读标识Y
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY t.creation_date DESC"); //排序
        Pagination<SrmBaseNotificationsEntity_HI_RO> result = srmBaseNotificationsDAO_HI_RO
                .findPagination(sb,countSql, map, pageIndex, pageRows);
        return result;
    }

    /**
     * 通知查看，查看标识viewedFlag改为已读Y——系统代办通知
     *
     * @param jsonParams
     * @return
     */
    @Override
    public JSONObject updateNotificationByViewe(JSONObject jsonParams) {
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        String action = jsonParams.getString("action"); // 操作按钮
        if (null == action || "".equals(action)) {
            return SToolUtils.convertResultJSONObj("E", "操作字符为空", 0, null);
        }
        Integer notificationId = jsonParams.getInteger("notificationId");
        if (null == notificationId || "".equals(notificationId)) {
            return SToolUtils.convertResultJSONObj("E", "参数为" + notificationId, 0, null);
        }
        SrmBaseNotificationsEntity_HI notificationsEntity = srmBaseNotificationsDAO_HI.getById(notificationId);
        if (null == notificationsEntity) {
            return SToolUtils.convertResultJSONObj("E", "不存在该记录，参数为" + notificationId, 0, null);
        }
        if ("VIEW".equals(action)) {
            if (null != notificationsEntity.getViewedFlag() && "N".equals(notificationsEntity.getViewedFlag())) {//未查看时
                notificationsEntity.setNotificationStatus("2");//已处理
                notificationsEntity.setViewedFlag("Y");
                notificationsEntity.setViewDate(new Date());
                notificationsEntity.setOperatorUserId(userId);
                srmBaseNotificationsDAO_HI.saveEntity(notificationsEntity);
                jsonData.put("notificationId", notificationsEntity.getNotificationId());
                return SToolUtils.convertResultJSONObj("S", "查看成功", 1, jsonData);
            }
        }
        return SToolUtils.convertResultJSONObj("E", "没有操作", 0, null);
    }

    /**
     * 待报价查询（带分页）——供应商——系统代办通知
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SrmPonBidEntity_HI_RO> findSrmBaseNotificationsByBidHeader(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Pagination<SrmPonBidEntity_HI_RO> result = new Pagination<>();//最终结果
        Integer supplierId = jsonParams.getInteger("varSupplierId");
        if (supplierId == null || "".equals(supplierId)) {
            return result;
        }
        Map<String, Object> map = new HashMap();
        //时间范围
        String timeFrame = jsonParams.getString("timeFrame");
        //处理状态
        String notificationStatus = jsonParams.getString("notificationStatus");
        //标题或编码
        String ponBidHeaderTitleOrNumber = jsonParams.getString("ponBidHeaderTitleOrNumber");

        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(timeFrame) || SrmUtils.isContainSQL(ponBidHeaderTitleOrNumber)) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        //未处理
        if ("1".equals(notificationStatus)) {
            if (supplierId != null) {
                map.put("supplierId", supplierId);
            } else {
                map.put("supplierId", -1);
            }
            sb.append(SrmPonBidEntity_HI_RO.QUERY_UNBID_SQL);
            SaafToolUtils.parperParam(jsonParams, "ah.auctionStatusNo", "auctionStatus", sb, map, "=");
            if ("EX".equals(jsonParams.getString("varPlatformCode"))) {
                sb.append(" AND NOT EXISTS (\r\n" +
                          "SELECT 1 FROM srm_pon_bid_headers spbh\r\n" +
                          " WHERE spbh.auction_header_id = ah.auctionHeaderId\r\n" +
                          "   AND spbh.supplier_id = " + supplierId + " )\r\n");
            }
            if ("EX".equals(jsonParams.getString("varPlatformCode"))) {
                sb.append(" AND ((ah.invitingBidWay = '1' AND ah.supplierId = " + supplierId + " ) OR ah.invitingBidWay = '2' )\r\n ");
            }
            if (null != timeFrame && !"".equals(timeFrame)) {//时间范围——系统代办通知，待报价
                sb.append(" AND trunc(SYSDATE - " + timeFrame + ") <= trunc(ah.bidStartDate) ");
            }
            if (null != ponBidHeaderTitleOrNumber && !"".equals(ponBidHeaderTitleOrNumber)) {
                sb.append(" AND (ah.auctionTitle LIKE '%" + ponBidHeaderTitleOrNumber + "%' OR ah.auctionNumber LIKE '%" + ponBidHeaderTitleOrNumber + "%')\n");
            }
            if ("EX".equals(jsonParams.getString("varPlatformCode"))) {
                map.put("varSupplierId", supplierId);
            }
            String countSql = "select count(1) from (" + sb + ")";
            // 排序
            sb.append(" ORDER BY ah.bidStartDate DESC");
            result = srmPonBidDAO_HI_RO.findPagination(sb,countSql, map, pageIndex, pageRows);
            return result;
        }
        //已处理
        if (null != notificationStatus && "2".equals(notificationStatus)) {
            sb.append(SrmBaseNotificationsEntity_HI_RO.QUERY_PONBIDHEADERLIST);
            if (null != timeFrame && !"".equals(timeFrame)) {
                sb.append(" AND trunc(SYSDATE - " + timeFrame + ") <= trunc(sbn.creation_date)\n");
            }
            if (null != ponBidHeaderTitleOrNumber && !"".equals(ponBidHeaderTitleOrNumber)) {
                sb.append(" AND (spah.auction_title LIKE '%" + ponBidHeaderTitleOrNumber + "%' OR spah.auction_number LIKE '%" + ponBidHeaderTitleOrNumber + "%')\n");
            }
            SaafToolUtils.parperParam(jsonParams, "sbn.notification_status", "notificationStatus", sb, map, "=");
            SaafToolUtils.parperParam(jsonParams, "sbn.created_by", "varUserId", sb, map, "=");//当前登录用户Id
            String countSql = "select count(1) from (" + sb + ")";
            sb.append(" ORDER BY spah.bid_start_date DESC"); //排序
            result = srmPonBidDAO_HI_RO.findPagination(sb,countSql, map, pageIndex, pageRows);
            return result;
        }
        return result;
    }

    /**
     * 待议价查询（带分页）——供应商——系统代办通知
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SrmPonBidEntity_HI_RO> findSrmBaseNotificationsByBargain(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Pagination<SrmPonBidEntity_HI_RO> result = new Pagination<>();//最终结果
        Integer supplierId = jsonParams.getInteger("varSupplierId");
        if (supplierId == null || "".equals(supplierId)) {
            return result;
        }
        Map<String, Object> map = new HashMap();
        //时间范围
        String timeFrame = jsonParams.getString("timeFrame");
        //处理状态
        String notificationStatus = jsonParams.getString("notificationStatus");
        //标题或编码
        String ponBidHeaderTitleOrNumber = jsonParams.getString("ponBidHeaderTitleOrNumber");

        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(timeFrame) || SrmUtils.isContainSQL(ponBidHeaderTitleOrNumber)) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        /*if (supplierId != null) {
            map.put("supplierId", supplierId);
        } else {
            map.put("supplierId", -1);
        }*/
        sb.append(SrmPonBidEntity_HI_RO.QUERY_AUCTION_BID_SQL);
        sb.append(" AND bh.bargainFlag = '2' ");
        /*if (null != timeFrame && !"".equals(timeFrame)) {//时间范围——系统代办通知，待报价
            sb.append(" AND trunc(SYSDATE - " + timeFrame + ") <= trunc(ah.bidStartDate) ");
        }*/
        if (null != ponBidHeaderTitleOrNumber && !"".equals(ponBidHeaderTitleOrNumber)) {
            sb.append(" AND (bh.auctionType LIKE '%" + ponBidHeaderTitleOrNumber + "%' OR bh.auctionNumber LIKE '%" + ponBidHeaderTitleOrNumber + "%')\n");
        }
        if ("EX".equals(jsonParams.getString("varPlatformCode"))) {
            sb.append(" and bh.supplierId = :varSupplierId ");
            map.put("varSupplierId", supplierId);
        }
        String countSql = "select count(1) from (" + sb + ")";
        // 排序
        sb.append(" ORDER BY bh.bidStartDate DESC");
        result = srmPonBidDAO_HI_RO.findPagination(sb,countSql, map, pageIndex, pageRows);
        return result;
    }

    /**
     * 查询资质预警信息（带分页）
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SrmBaseNotificationsEntity_HI_RO> findWarningList(JSONObject params, Integer pageIndex, Integer pageRows) {
        Integer userId = params.getInteger("varUserId");//当前登录用户Id
        StringBuffer queryString = new StringBuffer(SrmBaseNotificationsEntity_HI_RO.QUERY_WARNING_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafUsersEntity_HI user = saafUsersDAO_HI.getById(userId);
        Integer supplierId = params.getInteger("varSupplierId");

        if (supplierId != null) {
            queryString.append(" and sbn.receiver_id = " + supplierId);
        } else {
            queryString.append(" and sbn.receiver_id =(SELECT sr.responsibility_id FROM (saaf_user_resp sur,saaf_responsibilitys sr)\n"
                    + "WHERE sr.responsibility_name='开发专员' AND sr.responsibility_id=sur.responsibility_id AND sur.user_id=" + userId + ")  ");
        }
        SaafToolUtils.parperParam(params, "sbn.menu_type", "menuType", queryString, map, "like");
        SaafToolUtils.parperParam(params, "sbn.notification_content", "notificationContent", queryString, map, "like");
        SaafToolUtils.parperParam(params, "sbn.notification_status", "notificationStatus", queryString, map, "=");
        queryString.append(" and sbn.creation_date > '" + user.getCreationDate() + "'\n");
        String countSql = "select count(1) from (" + queryString + ")";
        queryString.append(" order by sbn.last_update_date desc");
        Pagination<SrmBaseNotificationsEntity_HI_RO> viewersList = srmBaseNotificationsDAO_HI_RO
                .findPagination(queryString,countSql, map, pageIndex, pageRows);
        return viewersList;
    }

    /**
     * 查询资质预警未处理条数
     *
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<SrmBaseNotificationsEntity_HI_RO> findCountWarningList(JSONObject params) throws Exception {
        Integer userId = params.getInteger("varUserId");//当前登录用户Id
        StringBuffer queryString = new StringBuffer(SrmBaseNotificationsEntity_HI_RO.QUERY_COUNT_WARNING_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        Integer supplierId = params.getInteger("varSupplierId");
        if (supplierId != null) {
            queryString.append(" AND bn.receiver_id = " + supplierId);
        } else {
            queryString.append(
                            " AND    bn.receiver_id = (SELECT sr.responsibility_id\n" +
                            "                          FROM   saaf_user_resp       sur\n" +
                            "                                ,saaf_responsibilitys sr\n" +
                            "                          WHERE  sr.responsibility_name = '开发专员'\n" +
                            "                          AND    sr.responsibility_id = sur.responsibility_id\n" +
                            "                          AND    sur.user_id = " + userId + ")");
        }
        List<SrmBaseNotificationsEntity_HI_RO> countList = srmBaseNotificationsDAO_HI_RO.findList(queryString.toString(), map);
        return countList;
    }

    /**
     * 更改资质预警信息状态（已完成操作）
     *
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject updateWarning(JSONObject params) throws Exception {
        LOGGER.info("保存参数：" + params.toString());
        SrmBaseNotificationsEntity_HI baseRow = null;
        try {
            baseRow = srmBaseNotificationsDAO_HI.getById(params.getInteger("notificationId"));
            Integer operatorUserId = params.getInteger("varUserId");
            baseRow.setNotificationStatus("2");
            baseRow.setOperatorUserId(operatorUserId);
            srmBaseNotificationsDAO_HI.update(baseRow);

            return SToolUtils.convertResultJSONObj("S", "保存成功", 1, baseRow);
        } catch (Exception e) {
            //LOGGER.error("未知错误:{}", e)();
            throw new Exception(e);
        }
    }
}
