package saaf.common.fmw.pon.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseNotificationsEntity_HI;
import saaf.common.fmw.base.model.inter.ISrmBaseNotifications;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.message.email.utils.SendMailUtil;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionGroupsEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionItemLaddersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionItemsEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionJuriesEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionScoreRulesEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionSuppliersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonBidHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonBidItemPricesEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.*;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierContactsEntity_HI;
import saaf.common.fmw.utils.SHA1Util;
import saaf.common.fmw.utils.SrmUtils;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonAuctionHeadersServer.java
 * Description：寻源--寻源单据头信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonAuctionHeadersServer")
public class SrmPonAuctionHeadersServer implements ISrmPonAuctionHeaders {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonAuctionHeadersServer.class);

    @Autowired
    private ViewObject<SrmPonAuctionHeadersEntity_HI> srmPonAuctionHeadersDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonAuctionHeadersEntity_HI_RO> srmPonAuctionHeadersDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPonAuctionGroupsEntity_HI> srmPonAuctionGroupsDAO_HI;

    @Autowired
    private ViewObject<SrmPonAuctionItemsEntity_HI> srmPonAuctionItemsDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierContactsEntity_HI> srmPosSupplierContactsDAO_HI;

    @Autowired
    private ViewObject<SrmPonAuctionSuppliersEntity_HI> srmPonAuctionSuppliersDAO_HI;

    @Autowired
    private ViewObject<SrmPonAuctionJuriesEntity_HI> srmPonAuctionJuriesDAO_HI;

    @Autowired
    private ViewObject<SrmPonAuctionItemLaddersEntity_HI> srmPonAuctionItemLaddersDAO_HI;

    @Autowired
    private ViewObject<SrmPonAuctionScoreRulesEntity_HI> srmPonAuctionScoreRulesDAO_HI;

    @Autowired
    private ViewObject<SrmPonBidItemPricesEntity_HI> srmPonBidItemPricesDAO_HI;//供应商报价行

    @Autowired
    private ViewObject<SrmPonBidHeadersEntity_HI> srmPonBidHeadersDAO_HI;

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;
    @Autowired
    private ISrmPonPriceLibrary srmPonPriceLibraryServer;
    @Autowired
    private ISrmPonAuctionGroups iSrmPonAuctionGroups; // 洽谈组别

    @Autowired
    private ISrmPonAuctionItems iSrmPonAuctionItems; // 洽谈行（标的物）

    @Autowired
    private ISrmPonAuctionSuppliers iSrmPonAuctionSuppliers; // 洽谈邀请供应商

    @Autowired
    private ISrmPonAuctionJuries iSrmPonAuctionJuries; // 洽谈评分人员

    @Autowired
    private ISrmPonAuctionScoreRules iSrmPonAuctionScoreRules; // 洽谈评分规则

    @Autowired
    private ViewObject<SrmPonBidHeadersEntity_HI> srmPonBidHeadersDao_HI;//供应商报价头表

    @Autowired
    private ViewObject<SrmBaseNotificationsEntity_HI> srmBaseNotificationsDAO_HI;//系统通知表
    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;  //用户DAO
    @Autowired
    private ISrmBaseNotifications iSrmBaseNotifications;//系统通知
    private static SendMailUtil sendMailUtil = new SendMailUtil(true);

    public SrmPonAuctionHeadersServer() {
        super();
    }

    /**
     * Description：查询拟标头
     * @param params 参数
     * @return SrmPonAuctionHeadersEntity_HI_RO
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public SrmPonAuctionHeadersEntity_HI_RO getAuctionHeaderInfo(JSONObject params) throws Exception {
        LOGGER.info("getAuctionHeaderInfo");
        StringBuffer querySql = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_AUCTION_HEADHER_INFO_SQL);
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        if (auctionHeaderId == null) {
            return null;
        }
        Map queryMap = new HashMap();
        Integer varUserId = params.getInteger("varUserId");
        queryMap.put("varUserId", varUserId);
        queryMap.put("auctionHeaderId", auctionHeaderId);
        try {
            List<SrmPonAuctionHeadersEntity_HI_RO> headerList = srmPonAuctionHeadersDAO_HI_RO.findList(querySql, queryMap);
            if (headerList.isEmpty()) {
                return null;
            }
            return headerList.get(0);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
            throw new Exception("查询拟标头信息失败");
        }
    }

    /**
     * Description：创建最新版的洽谈编号 ZB-yymmdd+流水编码（四位流水号）——招标 XJ-yymmdd+流水编码（四位流水号）——询价
     * @param flagName 标识（ZB或XJ）
     * @return String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public String getNewMaxAuctionNumber(String flagName) {
        String auctionNumber = "";
        if (flagName == null || "".equals(flagName)) {
            return auctionNumber;
        }
        try {
            if ("ZB".equals(flagName)) {
                auctionNumber = saafSequencesUtil.getDocSequences("srm_pon_auction_headers".toUpperCase(), "ZB-"
                        + DateUtil.date2Str(new Date(), "yyMMdd"), 4);
                return auctionNumber;
            } else if ("XJ".equals(flagName)) {
                auctionNumber = saafSequencesUtil.getDocSequences("srm_pon_auction_headers".toUpperCase(), "XJ-"
                        + DateUtil.date2Str(new Date(), "yyMMdd"), 4);
                return auctionNumber;
            }
        } catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
        }
        return auctionNumber;
    }

    /**
     * Description：获取最新版的洽谈轮次编号（ZB-yyMMdd+4位流水号-轮次号/XJ-yyMMdd+4位流水号-轮次号）
     *              需要传入洽谈编号（ZB-yyMMdd+4位流水号/XJ-yyMMdd+4位流水号）
     * @param auctionNumber 寻源单据编号
     * @return String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public String getNewRoundAuctionNumber(String auctionNumber) {
        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(auctionNumber)) {
            return null;
        }
        String auctionRoundNumber = "";
        if (auctionNumber == null || "".equals(auctionNumber)) {
            return auctionRoundNumber;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPonAuctionHeadersEntity_HI_RO.QUERY_NEWROUNDAUCTIONNUMBER);
        sb.append(" AND t.auction_round_number LIKE '" + auctionNumber + "-%' ");
        sb.append(" ORDER BY auctionRoundNumberBigInteger DESC ");
        Map<String, Object> map = null;
        List<SrmPonAuctionHeadersEntity_HI_RO> entityList = srmPonAuctionHeadersDAO_HI_RO
                .findList(sb, map);
        if (null != entityList && entityList.size() > 0) {
            SrmPonAuctionHeadersEntity_HI_RO entity = entityList.get(0);
            if (entity.getAuctionRoundNumberBigInteger() == null || "".equals(entity.getAuctionRoundNumberBigInteger())) {
                return "";
            }
            BigInteger number = entity.getAuctionRoundNumberBigInteger();
            number = number.add(new BigInteger("1"));
            auctionRoundNumber = auctionNumber + "-" + number;
            return auctionRoundNumber;
        } else {
            auctionRoundNumber = auctionNumber + "-1";// 表示第一次创建新一轮，首次创建洽谈轮次编号
            return auctionRoundNumber;
        }
    }

    /**
     * Description：获取当前系统时间
     * @return String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public String getDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * Description：根据物料行id获取报价的供应商id
     * @param params 参数
     * @return List<SrmPonAuctionHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public List<SrmPonAuctionHeadersEntity_HI_RO> getBidSupplierList(JSONObject params) throws Exception {
        LOGGER.info("getBidSupplierList参数:" + params.toString());
        StringBuffer querySql = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_BID_SUPPLIER_SQL);
        Integer auctionLineId = params.getInteger("auctionLineId");
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        String type = params.getString("type");// 保存传入的标识 按照最终得分排序；
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        if (auctionLineId != null) {
            querySql.append(" AND bip.AUCTION_LINE_ID = :auctionLineId ");
            paramsMap.put("auctionLineId", auctionLineId);
        } else if (auctionHeaderId != null) {
            querySql.append(" AND pbh.AUCTION_HEADER_ID = :auctionHeaderId");
            paramsMap.put("auctionHeaderId", auctionHeaderId);
        } else {
            return null;
        }
        if ("order".equals(type)) {
            querySql.append(" ORDER BY bip.FINALLY_SCORE DESC ");
        } else {
            querySql.append(" ORDER BY bip.TAX_PRICE ASC ");
        }

        try {
            return srmPonAuctionHeadersDAO_HI_RO.findList(querySql, paramsMap);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
            throw new Exception("查询失败");
        }
    }

    /**
     * Description：保存洽谈头层及其子表信息（招标）
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public JSONObject savePonAuctionHeadersAll(JSONObject jsonParams) throws Exception {
        try {
            JSONObject jsonData = new JSONObject();
            Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
            String action = jsonParams.getString("action"); // 操作按钮
            if (null == action || "".equals(action)) {
                return SToolUtils.convertResultJSONObj("E", "操作字符为空！", 0, null);
            }
            Integer auctionHeaderId;
            //洽谈头层
            SrmPonAuctionHeadersEntity_HI auctionHeader = JSON.parseObject(jsonParams.toJSONString(), SrmPonAuctionHeadersEntity_HI.class);

            //洽谈组别
            List<SrmPonAuctionGroupsEntity_HI> ponAuctionGroupsList = null;
            if (!(jsonParams.getJSONArray("ponAuctionGroupsList") == null || "".equals(jsonParams.getJSONArray("ponAuctionGroupsList")))) {
                JSONArray ponAuctionGroupsListJSON = jsonParams.getJSONArray("ponAuctionGroupsList");
                ponAuctionGroupsList = JSON.parseArray(ponAuctionGroupsListJSON.toJSONString(), SrmPonAuctionGroupsEntity_HI.class);
            }
            List<SrmPonAuctionItemsEntity_HI> ponAuctionItemsList = null;// 标的物
            if (!(jsonParams.getJSONArray("ponAuctionItemsList") == null || "".equals(jsonParams.getJSONArray("ponAuctionItemsList")))) {
                JSONArray ponAuctionItemsListJSON = jsonParams.getJSONArray("ponAuctionItemsList");
                ponAuctionItemsList = JSON.parseArray(ponAuctionItemsListJSON.toJSONString(), SrmPonAuctionItemsEntity_HI.class);
                if (ponAuctionItemsList != null && ponAuctionItemsList.size() > 0) {
                    for (SrmPonAuctionItemsEntity_HI k : ponAuctionItemsList) {
                        if (k.getAwardStatus() == null || "".equals(k.getAwardStatus())) {
                            k.setAwardStatus("1");// 未决标
                        }
                    }
                }
            }
            List<SrmPonAuctionSuppliersEntity_HI> ponAuctionSuppliersList = null;// 洽谈邀请供应商
            if (!(jsonParams.getJSONArray("ponAuctionSuppliersList") == null || "".equals(jsonParams.getJSONArray("ponAuctionSuppliersList")))) {
                JSONArray ponAuctionSuppliersListJSON = jsonParams.getJSONArray("ponAuctionSuppliersList");
                ponAuctionSuppliersList = JSON.parseArray(ponAuctionSuppliersListJSON.toJSONString(), SrmPonAuctionSuppliersEntity_HI.class);
                if (ponAuctionSuppliersList != null && ponAuctionSuppliersList.size() > 0) {
                    for (SrmPonAuctionSuppliersEntity_HI k : ponAuctionSuppliersList) {
                        if (k.getAwardedStatus() == null || "".equals(k.getAwardedStatus())) {
                            k.setAwardedStatus("3");// 未中标
                        }
                    }
                }
            }
            List<SrmPonAuctionJuriesEntity_HI> ponAuctionJuriesList = null;// 洽谈评分人员
            if (!(jsonParams.getJSONArray("ponAuctionJuriesList") == null || "".equals(jsonParams.getJSONArray("ponAuctionJuriesList")))) {
                JSONArray ponAuctionJuriesListJSON = jsonParams.getJSONArray("ponAuctionJuriesList");
                ponAuctionJuriesList = JSON.parseArray(ponAuctionJuriesListJSON.toJSONString(), SrmPonAuctionJuriesEntity_HI.class);
                if (ponAuctionJuriesList != null && ponAuctionJuriesList.size() > 0) {
                    for (SrmPonAuctionJuriesEntity_HI k : ponAuctionJuriesList) {
                        if (k.getJuryStatus() == null || "".equals(k.getJuryStatus())) {
                            k.setJuryStatus("1");// 未决标
                        }
                    }
                }
            }
            List<SrmPonAuctionScoreRulesEntity_HI> ponAuctionScoreRulesList = null;
            if (!(jsonParams.getJSONArray("ponAuctionScoreRulesList") == null || "".equals(jsonParams.getJSONArray("ponAuctionScoreRulesList")))) {
                JSONArray ponAuctionScoreRulesListJSON = jsonParams.getJSONArray("ponAuctionScoreRulesList");
                ponAuctionScoreRulesList = JSON.parseArray(ponAuctionScoreRulesListJSON.toJSONString(), SrmPonAuctionScoreRulesEntity_HI.class);
            }
            // 洽谈头层
            if (auctionHeader.getAuctionNumber() == null || "".equals(auctionHeader.getAuctionNumber())) {
                String auctionNumber = getNewMaxAuctionNumber("ZB"); // 洽谈编号——招标
                auctionHeader.setAuctionNumber(auctionNumber);
            }
            if (auctionHeader.getAwardStatus() == null || "".equals(auctionHeader.getAwardStatus())) {
                auctionHeader.setAwardStatus("1");
            }
            if (auctionHeader.getAwardApprovalStatus() == null || "".equals(auctionHeader.getAwardApprovalStatus())) {
                auctionHeader.setAwardApprovalStatus("DRAFT");
            }
            if (auctionHeader.getAuctionStatus() == null || "".equals(auctionHeader.getAuctionStatus())) {
                auctionHeader.setAuctionStatus("DRAFT"); // 洽谈状态
            }

            auctionHeader.setOperatorUserId(userId);
            if ("save".equals(action)) { // 保存按钮
                if (auctionHeader.getPublishApprovalStatus() == null || "".equals(auctionHeader.getPublishApprovalStatus())) {
                    auctionHeader.setPublishApprovalStatus("DRAFT"); // 发布审批状态初始化为拟定状态
                }
                srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeader);
                auctionHeaderId = auctionHeader.getAuctionHeaderId();
                iSrmPonAuctionGroups.savePonAuctionGroupsList(ponAuctionGroupsList, userId, auctionHeaderId);
                iSrmPonAuctionItems.savePonAuctionItemsList(ponAuctionItemsList, userId, auctionHeaderId);
                iSrmPonAuctionSuppliers.savePonAuctionSuppliersList(ponAuctionSuppliersList, userId, auctionHeaderId);
                iSrmPonAuctionJuries.savePonAuctionJuriesList(ponAuctionJuriesList, userId, auctionHeaderId);
                iSrmPonAuctionScoreRules.savePonAuctionScoreRulesList(ponAuctionScoreRulesList, userId, auctionHeaderId);
                jsonData.put("auctionHeaderId", auctionHeaderId);
                return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, jsonData);
            } else if ("submit".equals(action)) { // 提交按钮
                auctionHeader.setPublishApprovalStatus("APPROVING"); // /发布审批状态改为审批中状态
                srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeader);
                auctionHeaderId = auctionHeader.getAuctionHeaderId();
                iSrmPonAuctionGroups.savePonAuctionGroupsList(ponAuctionGroupsList, userId, auctionHeaderId);
                iSrmPonAuctionItems.savePonAuctionItemsList(ponAuctionItemsList, userId, auctionHeaderId);
                iSrmPonAuctionSuppliers.savePonAuctionSuppliersList(ponAuctionSuppliersList, userId, auctionHeaderId);
                iSrmPonAuctionJuries.savePonAuctionJuriesList(ponAuctionJuriesList, userId, auctionHeaderId);
                iSrmPonAuctionScoreRules.savePonAuctionScoreRulesList(ponAuctionScoreRulesList, userId, auctionHeaderId);
                jsonData.put("auctionHeaderId", auctionHeaderId);
                return SToolUtils.convertResultJSONObj("S", "提交成功！", 1, jsonData);
            } else if ("publish".equals(action)) { // 发布按钮
                auctionHeader.setAuctionStatus("PUBLISHED"); // 已发布状态
                auctionHeader.setPublishDate(new Date()); //记录发布时间
                srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeader);
                auctionHeaderId = auctionHeader.getAuctionHeaderId();
                iSrmPonAuctionGroups.savePonAuctionGroupsList(ponAuctionGroupsList, userId, auctionHeaderId);
                iSrmPonAuctionItems.savePonAuctionItemsList(ponAuctionItemsList, userId, auctionHeaderId);
                iSrmPonAuctionSuppliers.savePonAuctionSuppliersList(ponAuctionSuppliersList, userId, auctionHeaderId);
                iSrmPonAuctionJuries.savePonAuctionJuriesList(ponAuctionJuriesList, userId, auctionHeaderId);
                iSrmPonAuctionScoreRules.savePonAuctionScoreRulesList(ponAuctionScoreRulesList, userId, auctionHeaderId);
                jsonData.put("auctionHeaderId", auctionHeaderId);
                try {
                    sendingNotificationMail(jsonParams);
                } catch (Exception e) {
                    LOGGER.info("发送邮件失败:" + e.getMessage());
                }
                return SToolUtils.convertResultJSONObj("S", "发布成功！", 1, jsonData);
            }
            return SToolUtils.convertResultJSONObj("E", "没有操作！", 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
            throw new Exception(e);
        }
    }

    /**
     * Description：保存洽谈头层及其子表信息（询价）
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public JSONObject savePonAuctionHeadersAllBidding(JSONObject jsonParams) {
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        String action = jsonParams.getString("action"); // 操作按钮
        if (null == action || "".equals(action)) {
            return SToolUtils.convertResultJSONObj("E", "操作字符为空！", 0, null);
        }
        Integer auctionHeaderId;
        //洽谈头层
        SrmPonAuctionHeadersEntity_HI auctionHeader = JSON.parseObject(jsonParams.toJSONString(), SrmPonAuctionHeadersEntity_HI.class);

        List<SrmPonAuctionItemsEntity_HI> ponAuctionItemsList = null;// 标的物
        if (!(jsonParams.getJSONArray("ponAuctionItemsList") == null || "".equals(jsonParams.getJSONArray("ponAuctionItemsList")))) {
            JSONArray ponAuctionItemsListJSON = jsonParams.getJSONArray("ponAuctionItemsList");
            ponAuctionItemsList = JSON.parseArray(ponAuctionItemsListJSON.toJSONString(), SrmPonAuctionItemsEntity_HI.class);
            if (ponAuctionItemsList != null && ponAuctionItemsList.size() > 0) {
                for (SrmPonAuctionItemsEntity_HI k : ponAuctionItemsList) {
                    if (k.getAwardStatus() == null || "".equals(k.getAwardStatus())) {
                        k.setAwardStatus("1");// 未决标
                    }
                }
            }
        }
        List<SrmPonAuctionSuppliersEntity_HI> ponAuctionSuppliersList = null;// 洽谈邀请供应商
        if (!(jsonParams.getJSONArray("ponAuctionSuppliersList") == null || "".equals(jsonParams.getJSONArray("ponAuctionSuppliersList")))) {
            JSONArray ponAuctionSuppliersListJSON = jsonParams.getJSONArray("ponAuctionSuppliersList");
            ponAuctionSuppliersList = JSON.parseArray(ponAuctionSuppliersListJSON.toJSONString(), SrmPonAuctionSuppliersEntity_HI.class);
            if (ponAuctionSuppliersList != null && ponAuctionSuppliersList.size() > 0) {
                for (SrmPonAuctionSuppliersEntity_HI k : ponAuctionSuppliersList) {
                    if (k.getAwardedStatus() == null || "".equals(k.getAwardedStatus())) {
                        k.setAwardedStatus("3");// 未中标
                    }
                }
            }
        }
        List<SrmPonAuctionJuriesEntity_HI> ponAuctionJuriesList = null;// 洽谈评分人员
        if (!(jsonParams.getJSONArray("ponAuctionJuriesList") == null || "".equals(jsonParams.getJSONArray("ponAuctionJuriesList")))) {
            JSONArray ponAuctionJuriesListJSON = jsonParams.getJSONArray("ponAuctionJuriesList");
            ponAuctionJuriesList = JSON.parseArray(ponAuctionJuriesListJSON.toJSONString(), SrmPonAuctionJuriesEntity_HI.class);
            if (ponAuctionJuriesList != null && ponAuctionJuriesList.size() > 0) {
                for (SrmPonAuctionJuriesEntity_HI k : ponAuctionJuriesList) {
                    if (k.getJuryStatus() == null || "".equals(k.getJuryStatus())) {
                        k.setJuryStatus("1");// 未决标
                    }
                }
            }
        }
        // 洽谈头层
        if (auctionHeader.getAuctionNumber() == null || "".equals(auctionHeader.getAuctionNumber())) {
            String auctionNumber = getNewMaxAuctionNumber("XJ"); // 洽谈编号——询价
            auctionHeader.setAuctionNumber(auctionNumber);
        }
        if (auctionHeader.getAwardStatus() == null || "".equals(auctionHeader.getAwardStatus())) {
            auctionHeader.setAwardStatus("1");
        }
        if (auctionHeader.getAwardApprovalStatus() == null || "".equals(auctionHeader.getAwardApprovalStatus())) {
            auctionHeader.setAwardApprovalStatus("DRAFT");
        }
        if (auctionHeader.getAuctionStatus() == null || "".equals(auctionHeader.getAuctionStatus())) {
            auctionHeader.setAuctionStatus("DRAFT"); // 洽谈状态
        }
        auctionHeader.setOperatorUserId(userId);
        if ("save".equals(action)) { // 保存按钮
            if (auctionHeader.getPublishApprovalStatus() == null || "".equals(auctionHeader.getPublishApprovalStatus())) {
                auctionHeader.setPublishApprovalStatus("DRAFT"); // 发布审批状态初始化为拟定状态
            }
            srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeader);
            auctionHeaderId = auctionHeader.getAuctionHeaderId();
            iSrmPonAuctionItems.savePonAuctionItemsList(ponAuctionItemsList, userId, auctionHeaderId);
            iSrmPonAuctionSuppliers.savePonAuctionSuppliersList(ponAuctionSuppliersList, userId, auctionHeaderId);
            iSrmPonAuctionJuries.savePonAuctionJuriesList(ponAuctionJuriesList, userId, auctionHeaderId);
            jsonData.put("auctionHeaderId", auctionHeaderId);
            return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, jsonData);
        } else if ("submit".equals(action)) { // 提交按钮
            auctionHeader.setPublishApprovalStatus("APPROVING"); // /发布审批状态改为审批中状态
            srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeader);
            auctionHeaderId = auctionHeader.getAuctionHeaderId();
            iSrmPonAuctionItems.savePonAuctionItemsList(ponAuctionItemsList, userId, auctionHeaderId);
            iSrmPonAuctionSuppliers.savePonAuctionSuppliersList(ponAuctionSuppliersList, userId, auctionHeaderId);
            iSrmPonAuctionJuries.savePonAuctionJuriesList(ponAuctionJuriesList, userId, auctionHeaderId);
            jsonData.put("auctionHeaderId", auctionHeaderId);
            return SToolUtils.convertResultJSONObj("S", "提交成功！", 1, jsonData);
        } else if ("publish".equals(action)) { // 发布按钮
            auctionHeader.setAuctionStatus("PUBLISHED"); // 已发布状态
            auctionHeader.setPublishDate(new Date()); //记录发布时间
            srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeader);
            auctionHeaderId = auctionHeader.getAuctionHeaderId();
            iSrmPonAuctionItems.savePonAuctionItemsList(ponAuctionItemsList, userId, auctionHeaderId);
            iSrmPonAuctionSuppliers.savePonAuctionSuppliersList(ponAuctionSuppliersList, userId, auctionHeaderId);
            iSrmPonAuctionJuries.savePonAuctionJuriesList(ponAuctionJuriesList, userId, auctionHeaderId);
            jsonData.put("auctionHeaderId", auctionHeaderId);
            try {
                sendingNotificationMail(jsonParams);
            } catch (Exception e) {
                LOGGER.error("未知错误:{}" , e);
            }
            return SToolUtils.convertResultJSONObj("S", "发布成功！", 1, jsonData);
        }
        return SToolUtils.convertResultJSONObj("E", "未知错误！", 0, null);
    }

    /**
     * Description：发布时候发送通知邮件
     * @param jsonParams 参数
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    public void sendingNotificationMail(JSONObject jsonParams) {
        //获取对应供应商联系人邮件
        SendMailUtil sendMailUtil = new SendMailUtil(true);
        JSONArray suppliersList = jsonParams.getJSONArray("ponAuctionSuppliersList");
        int supplierId = 0;
        for (int i = 0; i < suppliersList.size(); i++) {
            supplierId = suppliersList.getJSONObject(i).getInteger("supplierId");
            List<SrmPosSupplierContactsEntity_HI> contactsList = srmPosSupplierContactsDAO_HI.findByProperty("supplierId", supplierId);
            if (!(supplierId == 0)) {
                if (contactsList != null && contactsList.size() > 0) {
                    for (int c = 0; c < contactsList.size(); c++) {
                        String emailAddress = contactsList.get(c).getEmailAddress();
                        //发送邮件给账号的联系人
                        sendMailUtil.doSendHtmlEmail("标书发布通知", "<p>您好！</p><br/>" + "您有新的寻源单据: " + jsonParams.getString("auctionTitle") + "于" + jsonParams.getString("bidStartDate") + "开始报价，" + "请回应，谢谢。", new String[]{emailAddress});
                    }
                }
                //发送通知
                List<SaafUsersEntity_HI> usersList = saafUsersDAO_HI.findByProperty("supplierId", supplierId);
                if (null != usersList && usersList.size() > 0) {
                    SaafUsersEntity_HI usersEntity = usersList.get(0);
                iSrmBaseNotifications.insertSrmBaseNotifications("标书发布通知", "您好！您有新的寻源单据:"+jsonParams.getString("auctionTitle")+jsonParams.getString("auctionNumber")+"于"+new Date()+"开始报价，请及时报价，谢谢。"
                            , usersEntity.getUserId(), "srm_pon_auction_headers", jsonParams.getInteger("auctionHeaderId"), "auctionHeaderId", "home.auctionDraftsBiddingDetail",jsonParams.getInteger("varUserId"));
                }
            }
        }
    }

    /**
     * Description：确定开标、发起评分等操作（已截止）
     * @param jsonParams 参数
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public JSONObject updateAuctionHeaderByAction(JSONObject jsonParams) {
        JSONObject jsonData = new JSONObject();
        //用户Id
        Integer userId = jsonParams.getInteger("varUserId");
        //操作按钮
        String action = jsonParams.getString("action");
        if (null == action || "".equals(action)) {
            return SToolUtils.convertResultJSONObj("E", "操作字符为空", 0, null);
        }
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId");
        if (null == auctionHeaderId || "".equals(auctionHeaderId)) {
            return SToolUtils.convertResultJSONObj("E", "参数为" + auctionHeaderId, 0, null);
        }
        SrmPonAuctionHeadersEntity_HI auctionHeadersEntity = srmPonAuctionHeadersDAO_HI.getById(auctionHeaderId);
        if (null == auctionHeadersEntity) {
            return SToolUtils.convertResultJSONObj("E", "不存在该记录，参数为" + auctionHeaderId, 0, null);
        }
        auctionHeadersEntity.setOperatorUserId(userId);
        if ("openBid".equals(action)) { //确定开标操作
            auctionHeadersEntity.setOpenBiddingFlag("Y");
            auctionHeadersEntity.setOpenBiddingDate(new Date());
            srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeadersEntity);
            jsonData.put("auctionHeaderId", auctionHeadersEntity.getAuctionHeaderId());
            return SToolUtils.convertResultJSONObj("S", "开标成功", 1, jsonData);
        } else if ("openScore".equals(action)) {  //发起评分
            auctionHeadersEntity.setAllowScoreFlag("Y");
            srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeadersEntity);
            jsonData.put("auctionHeaderId", auctionHeadersEntity.getAuctionHeaderId());
            return SToolUtils.convertResultJSONObj("S", "发起评分成功", 1, jsonData);
        } else if ("publish".equals(action)) { // 发布按钮-系统代办通知
            auctionHeadersEntity.setAuctionStatus("PUBLISHED"); // 已发布状态
            auctionHeadersEntity.setPublishDate(new Date()); //记录发布时间
            srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeadersEntity);
            auctionHeaderId = auctionHeadersEntity.getAuctionHeaderId();
            jsonData.put("auctionHeaderId", auctionHeaderId);
            //插入系统通知
            SrmBaseNotificationsEntity_HI notificationsEntity = new SrmBaseNotificationsEntity_HI();
            try {
                String notificationCode = saafSequencesUtil.getDocSequences("srm_base_notifications".toUpperCase(), "BS-" + DateUtil.date2Str(new Date(), "yyyyMMdd"), 4);
                notificationsEntity.setNotificationCode(notificationCode);
            } catch (Exception e) {
                LOGGER.error("创建系统通知编号出错！", e.getMessage());
                return SToolUtils.convertResultJSONObj("E", "创建系统通知编号出错！", 0, null);
            }
            notificationsEntity.setNotificationType("11");//标书发布
            notificationsEntity.setNotificationStatus("2");//已处理
            notificationsEntity.setTableId(auctionHeaderId);
            notificationsEntity.setTableIdName("auctionHeaderId");
            notificationsEntity.setTableName("srm_pon_auction_headers");
            if (null != auctionHeadersEntity.getAuctionType()) {
                if ("TENDER".equals(auctionHeadersEntity.getAuctionType())) {
                    notificationsEntity.setFunctionUrl("home.auctionDraftsTenderDetail");
                } else if ("INQUIRY".equals(auctionHeadersEntity.getAuctionType())) {
                    notificationsEntity.setFunctionUrl("home.auctionDraftsBiddingDetail");
                }
            }
            notificationsEntity.setOperatorUserId(userId);
            srmBaseNotificationsDAO_HI.save(notificationsEntity);

            return SToolUtils.convertResultJSONObj("S", "发布成功！", 1, jsonData);
        }
        return SToolUtils.convertResultJSONObj("E", "未知错误", 0, null);
    }

    /**
     * Description：模拟OA流程的审批、拒绝按钮——拟标、已截止
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public JSONObject updatePonAuctionHeadersApprove(JSONObject jsonParams) {
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        String action = jsonParams.getString("action"); // 操作按钮
        if (null == action || "".equals(action)) {
            return SToolUtils.convertResultJSONObj("E", "操作字符为空", 0, null);
        }
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId");
        if (null == auctionHeaderId || "".equals(auctionHeaderId)) {
            return SToolUtils.convertResultJSONObj("E", "参数为" + auctionHeaderId, 0, null);
        }
        SrmPonAuctionHeadersEntity_HI auctionHeadersEntity = srmPonAuctionHeadersDAO_HI.getById(auctionHeaderId);
        if (null == auctionHeadersEntity) {
            return SToolUtils.convertResultJSONObj("E", "不存在该记录，参数为" + auctionHeaderId, 0, null);
        }
        auctionHeadersEntity.setOperatorUserId(userId);
        if ("APPROVED".equals(action)) {//审批通过
            if ("DRAFT".equals(auctionHeadersEntity.getAuctionStatus())) {//拟标
                auctionHeadersEntity.setPublishApprovalStatus(action);
                srmPonAuctionHeadersDAO_HI.update(auctionHeadersEntity);//发布审批状态
                srmPonAuctionHeadersDAO_HI.fluch();
                jsonData.put("auctionHeaderId", auctionHeadersEntity.getAuctionHeaderId());
                return SToolUtils.convertResultJSONObj("S", "审批通过", 1, jsonData);

            } else if ("CLOSED".equals(auctionHeadersEntity.getAuctionStatus())) {//已截止
                auctionHeadersEntity.setAwardApprovalStatus(action);
                auctionHeadersEntity.setAwardStatus("2");//已决标
                auctionHeadersEntity.setAwardCompleteDate(new Date());//决标完成时间
                auctionHeadersEntity.setJudgeCompleteDate(new Date());//评标完成时间

                Map<String, Object> map = new HashMap<>();
                map.put("auctionHeaderId", auctionHeadersEntity.getAuctionHeaderId());
                map.put("bidStatus", "ACT");
                List<SrmPonBidHeadersEntity_HI> bidHeadersList = srmPonBidHeadersDao_HI.findByProperty(map);//查询所有供应商报价
                List<SrmPonBidItemPricesEntity_HI> bidItemPricesList = new ArrayList<>();//供应商报价行
                if (null != bidHeadersList && bidHeadersList.size() > 0) {
                    for (SrmPonBidHeadersEntity_HI k : bidHeadersList) {
                        //如果中标，把中标状态写到srm_pon_auction_suppliers表
                        if ("4".equals(k.getAwardStatus())) {
                            Map<String, Object> supplierMap = new HashMap<>();
                            supplierMap.put("auctionHeaderId", k.getAuctionHeaderId());
                            supplierMap.put("supplierId", k.getSupplierId());
                            List<SrmPonAuctionSuppliersEntity_HI> supplierList = srmPonAuctionSuppliersDAO_HI.findByProperty(supplierMap);
                            if (supplierList != null && supplierList.size() > 0) {
                                SrmPonAuctionSuppliersEntity_HI supplier = supplierList.get(0);
                                supplier.setAwardedStatus("4");
                                supplier.setOperatorUserId(userId);
                                srmPonAuctionSuppliersDAO_HI.saveOrUpdate(supplier);
                            }
                        }
                        List<SrmPonBidItemPricesEntity_HI> bidItemPricesListNew = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", k.getBidHeaderId()); //所有供应商报价行
                        if (null != bidItemPricesListNew && bidItemPricesListNew.size() > 0) {
                            bidItemPricesList.addAll(bidItemPricesListNew);
                        }
                    }
                }
                /*update by zwj start 2020/3/10 删除取消状态逻辑*/
                /*if (null == bidItemPricesList || bidItemPricesList.size() == 0 || "".equals(bidItemPricesList)) {//没有任何一条供应商报价行
                    auctionHeadersEntity.setAuctionStatus("CANCELED");//取消状态
                } else {
                    boolean temp = false;
                    for (SrmPonBidItemPricesEntity_HI tem : bidItemPricesList) {
                        if (null != tem.getAwardStatus() && "4".equals(tem.getAwardStatus())) {
                            temp = true;
                            break;
                        }
                    }
                    if (temp) {//标的物有中标
                        auctionHeadersEntity.setAuctionStatus("COMPLETED");//已完成状态
                    } else {//没有任何标的物中标
                        auctionHeadersEntity.setAuctionStatus("CANCELED");//取消状态
                    }
                }*/
                /*update by zwj end 2020/3/10 删除取消状态逻辑*/
                auctionHeadersEntity.setAuctionStatus("COMPLETED");//已完成状态
                List<SrmPonAuctionItemsEntity_HI> ponAuctionItemsList = srmPonAuctionItemsDAO_HI.findByProperty("auctionHeaderId", auctionHeadersEntity.getAuctionHeaderId());//标的物
                if (null != ponAuctionItemsList && ponAuctionItemsList.size() > 0) {
                    for (SrmPonAuctionItemsEntity_HI k : ponAuctionItemsList) {
                        k.setOperatorUserId(userId);
                        k.setAwardStatus("2");//已决标
                    }
                    srmPonAuctionItemsDAO_HI.update(ponAuctionItemsList);//所有标的物
                }
                srmPonAuctionHeadersDAO_HI.update(auctionHeadersEntity);
                srmPonAuctionHeadersDAO_HI.fluch();
                jsonData.put("auctionHeaderId", auctionHeadersEntity.getAuctionHeaderId());
                //生成价格库
                srmPonPriceLibraryServer.updateAndCreatePriceLibrary(jsonParams);

                //发送邮件
                List<SrmPonBidHeadersEntity_HI> bidHeadersEntityList = srmPonBidHeadersDAO_HI.findByProperty("auctionHeaderId",auctionHeadersEntity.getAuctionHeaderId());
                if(!ObjectUtils.isEmpty(bidHeadersEntityList)){
                   for(SrmPonBidHeadersEntity_HI bidHeadersEntity:bidHeadersEntityList){
                       List<SrmPosSupplierContactsEntity_HI> contactsList = srmPosSupplierContactsDAO_HI.findByProperty("supplierId", bidHeadersEntity.getSupplierId());
                       String content="";
                       if("4".equals(bidHeadersEntity.getAwardStatus())){
                           content="<p>您好！</p><br/>" + "恭喜您寻源单据: " + auctionHeadersEntity.getAuctionNumber() + "中标，请登录系统查看！";
                       }else if("3".equals(bidHeadersEntity.getAwardStatus())){
                           content="<p>您好！</p><br/>" + "很遗憾您寻源单据" + auctionHeadersEntity.getAuctionNumber() + "未中标！";
                       }
                       //邮件
                       if (contactsList != null && contactsList.size() > 0) {
                           for (int c = 0; c < contactsList.size(); c++) {
                               String emailAddress = contactsList.get(c).getEmailAddress();
                               //发送邮件给账号的联系人
                               sendMailUtil.doSendHtmlEmail("寻源结果通知", content, new String[]{emailAddress});
                           }
                       }

                       //发送通知
                       List<SaafUsersEntity_HI> usersList = saafUsersDAO_HI.findByProperty("supplierId", bidHeadersEntity.getSupplierId());
                       if (null != usersList && usersList.size() > 0) {
                           SaafUsersEntity_HI usersEntity = usersList.get(0);
                           iSrmBaseNotifications.insertSrmBaseNotifications("寻源结果通知", content
                                   , usersEntity.getUserId(), "srm_pon_bid_headers", bidHeadersEntity.getBidHeaderId(), "bidHeaderId", "home.inquiryBargainingDetail",userId);
                       }
                   }
                }
                return SToolUtils.convertResultJSONObj("S", "审批通过", 1, jsonData);
            }

        } else if ("REJECT".equals(action)) {//审批拒绝
            if ("DRAFT".equals(auctionHeadersEntity.getAuctionStatus())) {//拟标
                auctionHeadersEntity.setPublishApprovalStatus(action);//发布审批状态
                srmPonAuctionHeadersDAO_HI.update(auctionHeadersEntity);
                srmPonAuctionHeadersDAO_HI.fluch();
                jsonData.put("auctionHeaderId", auctionHeadersEntity.getAuctionHeaderId());
                return SToolUtils.convertResultJSONObj("S", "拒绝成功", 1, jsonData);

            } else if ("CLOSED".equals(auctionHeadersEntity.getAuctionStatus())) {//已截止
                auctionHeadersEntity.setAwardApprovalStatus(action);//决标审批状态
                auctionHeadersEntity.setOpenBiddingFlag("N");
                auctionHeadersEntity.setOpenBiddingDate(null);
                auctionHeadersEntity.setAllowScoreFlag("N");
                srmPonAuctionHeadersDAO_HI.update(auctionHeadersEntity);
                srmPonAuctionHeadersDAO_HI.fluch();
                jsonData.put("auctionHeaderId", auctionHeadersEntity.getAuctionHeaderId());
                return SToolUtils.convertResultJSONObj("S", "拒绝成功", 1, jsonData);
            }
        }
        return SToolUtils.convertResultJSONObj("E", "未知错误", 0, null);
    }

    /**
     * Description：查询草稿箱
     * @param params 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return Pagination<SrmPonAuctionHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPonAuctionHeadersEntity_HI_RO> findDraftsList(
            JSONObject params, Integer pageIndex, Integer pageRows)
            throws Exception {
        LOGGER.info("findDraftsList参数：" + params.toString());
        try {
            StringBuffer querySql = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_DRAFTS_SQL);
            Map<String, Object> queryParamMap = new HashMap();
            SaafToolUtils.parperParam(params, "ah.auction_number", "auctionNumber", querySql, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(params, "ah.auction_title", "auctionTitle", querySql, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(params, "ah.auction_type", "auctionType", querySql, queryParamMap, "=");
            SaafToolUtils.parperParam(params, "ah.auction_status", "auctionStatus", querySql, queryParamMap, "=");
            String fromStartDate = params.getString("fromStartDateActive");
            if (fromStartDate != null && !"".equals(fromStartDate.trim())) {
                querySql.append(" AND trunc(ah.bid_start_date) >= to_date(:fromStartDate, 'yyyy-mm-dd')\n");
                queryParamMap.put("fromStartDate", fromStartDate);
            }
            String toStartDate = params.getString("toStartDateActive");
            if (toStartDate != null && !"".equals(toStartDate.trim())) {
                querySql.append(" AND trunc(ah.bid_start_date) <= to_date(:toStartDate, 'yyyy-mm-dd')\n");
                queryParamMap.put("toStartDate", toStartDate);
            }

            String fromEndDate = params.getString("fromEndDateActive");
            if (fromEndDate != null && !"".equals(fromEndDate.trim())) {
                querySql.append(" AND trunc(ah.bid_end_date) >= to_date(:fromEndDate, 'yyyy-mm-dd')\n");
                queryParamMap.put("fromEndDate", fromEndDate);
            }
            String toEndDate = params.getString("toEndDateActive");
            if (toEndDate != null && !"".equals(toEndDate.trim())) {
                querySql.append(" AND trunc(ah.bid_end_date) <= to_date(:toEndDate, 'yyyy-mm-dd')\n");
                queryParamMap.put("toEndDate", toEndDate);
            }

            // 自有在自己的标才允许被看到 //除了管理员
            if ("N".equals(params.getString("varIsAdmin"))) {
                querySql.append(" AND (EXISTS(select 1 from srm_pon_auction_juries paj where paj.auction_header_id=ah.auction_header_id and  paj.user_id = :userId) or Ah.Created_By=:userId )");
                queryParamMap.put("userId", params.getInteger("varUserId"));
            }
            String countSql = "select count(1) from (" + querySql + ")";
            querySql.append(" ORDER BY ah.creation_date DESC");
            Pagination<SrmPonAuctionHeadersEntity_HI_RO> auctionList = srmPonAuctionHeadersDAO_HI_RO.findPagination(querySql,countSql, queryParamMap, pageIndex, pageRows);
            return auctionList;
        } catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
            throw new Exception("查询草稿箱失败");
        }
    }

    /**
     * Description：根据状态查询已发布、 已完成、 已截至的过程监控
     * @param params 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return Pagination<SrmPonAuctionHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPonAuctionHeadersEntity_HI_RO> findOtherList(
            JSONObject params, Integer pageIndex, Integer pageRows)
            throws Exception {
        LOGGER.info("findOtherList参数：" + params.toString());
        StringBuffer querySql = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_OTHER_STATUS_SQL);
        Map<String, Object> queryParamMap = new HashMap();
        SaafToolUtils.parperParam(params, "ah.auction_number", "auctionNumber", querySql, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(params, "ah.auction_title", "auctionTitle", querySql, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(params, "ah.auction_type", "auctionType", querySql, queryParamMap, "=");
        if ("COMPLETED".equals(params.getString("auctionStatus"))) {// 如果是查看已完成状态
            querySql.append(" AND ah.auction_status IN ('ROUND_COMPLATED','COMPLETED' )\n");
        } else if ("PUBLISHED".equals(params.getString("auctionStatus"))) {
            querySql.append(" AND    (ah.bid_end_date IS NULL OR ah.bid_end_date >= SYSDATE)\n");
            SaafToolUtils.parperParam(params, "ah.auction_status", "auctionStatus", querySql, queryParamMap, "=");
        } else {
            SaafToolUtils.parperParam(params, "ah.auction_status", "auctionStatus", querySql, queryParamMap, "=");
        }

        String fromEndDate = params.getString("fromEndDateActive");
        if (fromEndDate != null && !"".equals(fromEndDate.trim())) {
            querySql.append("  AND trunc(ah.bid_end_date) >= to_date(:fromEndDate, 'yyyy-mm-dd')\n");
            queryParamMap.put("fromEndDate", fromEndDate);
        }
        String toEndDate = params.getString("toEndDateActive");
        if (toEndDate != null && !"".equals(toEndDate.trim())) {
            querySql.append("  AND trunc(ah.bid_end_date) <= to_date(:toEndDate, 'yyyy-mm-dd')\n");
            queryParamMap.put("toEndDate", toEndDate);
        }

        Integer varUserId = params.getInteger("varUserId");
        queryParamMap.put("varUserId", varUserId);
        // 自有在自己的标才允许被看到 /除了管理员
        if ("N".equals(params.getString("varIsAdmin"))) {
            querySql.append(" AND (EXISTS (SELECT 1 FROM srm_pon_auction_juries paj WHERE paj.auction_header_id = ah.auction_header_id AND paj.user_id = :userId) or Ah.Created_By=:userId )\n");
            queryParamMap.put("userId", varUserId);
        }
        String countSql = "select count(1) from (" + querySql + ")";
        querySql.append(" ORDER BY ah.creation_date DESC\n");
        //System.out.println(querySql.toString());
        try {
            Pagination<SrmPonAuctionHeadersEntity_HI_RO> rowData = srmPonAuctionHeadersDAO_HI_RO.findPagination(querySql,countSql, queryParamMap, pageIndex, pageRows);
            return rowData;
        } catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
            throw new Exception("查询失败");
        }
    }

    /**
     * Description：将过时的已发布改成已截止
     * @param params 参数
     * @return String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public String saveListStatus(JSONObject params) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<>();
        try {
            List<SrmPonAuctionHeadersEntity_HI> findListResult = srmPonAuctionHeadersDAO_HI.findList("from SrmPonAuctionHeadersEntity_HI a WHERE a.auctionStatus = 'PUBLISHED' AND DATE_FORMAT(a.bidEndDate,'%Y-%m-%d %H:%i:%s') < SYSDATE()", queryParamMap);
            List<SrmPonAuctionHeadersEntity_HI> updateist = new ArrayList<>();
            for (SrmPonAuctionHeadersEntity_HI entity : findListResult) {
                entity.setAuctionStatus("CLOSED");
                entity.setOperatorUserId(entity.getLastUpdatedBy());
                updateist.add(entity);
            }
            if (updateist.size() > 0) {
                srmPonAuctionHeadersDAO_HI.save(updateist);
            }
            String resultData = JSON.toJSONString(findListResult);
            JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Description：删除拟定头
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteAuctionHeader(JSONObject params) throws Exception {
        LOGGER.info("删除拟定头信息，参数：" + params.toString());
        try {
            JSONArray headerIds = params.getJSONArray("data");
            List<SrmPonAuctionHeadersEntity_HI> headersList = null;
            for (int i = 0, j = headerIds.size(); i < j; i++) {
                Integer auctionHeaderId = headerIds.getInteger(i);
                headersList = srmPonAuctionHeadersDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
                if (!(auctionHeaderId == null || "".equals(auctionHeaderId))) {
                    List<SrmPonAuctionGroupsEntity_HI> groupsList = srmPonAuctionGroupsDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
                    List<SrmPonAuctionItemsEntity_HI> itemsList = srmPonAuctionItemsDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
                    List<SrmPonAuctionItemLaddersEntity_HI> laddersList = srmPonAuctionItemLaddersDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
                    List<SrmPonAuctionSuppliersEntity_HI> supplierList = srmPonAuctionSuppliersDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
                    List<SrmPonAuctionJuriesEntity_HI> juriesList = srmPonAuctionJuriesDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
                    List<SrmPonAuctionScoreRulesEntity_HI> rulesList = srmPonAuctionScoreRulesDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
                    if (headersList != null && headersList.size() > 0) {
                        srmPonAuctionHeadersDAO_HI.delete(headersList);
                    }

                    if (groupsList != null && groupsList.size() > 0) {
                        for (int a = 0; a < groupsList.size(); a++) {
                            srmPonAuctionGroupsDAO_HI.delete(groupsList.get(a)
                                    .getGroupId());
                        }
                    }

                    if (itemsList != null && itemsList.size() > 0) {
                        for (int c = 0; c < itemsList.size(); c++) {
                            srmPonAuctionItemsDAO_HI.delete(itemsList.get(c)
                                    .getAuctionLineId());
                        }
                    }

                    if (laddersList != null && laddersList.size() > 0) {
                        for (int k = 0; k < laddersList.size(); k++) {
                            srmPonAuctionItemLaddersDAO_HI.delete(laddersList
                                    .get(k).getItemLadderId());
                        }
                    }

                    if (supplierList != null && supplierList.size() > 0) {
                        for (int l = 0; l < supplierList.size(); l++) {
                            srmPonAuctionSuppliersDAO_HI.delete(supplierList
                                    .get(l).getInviteId());
                        }
                    }

                    if (juriesList != null && juriesList.size() > 0) {
                        for (int m = 0; m < juriesList.size(); m++) {
                            srmPonAuctionJuriesDAO_HI.delete(juriesList.get(m)
                                    .getJuryId());
                        }
                    }

                    if (rulesList != null && rulesList.size() > 0) {
                        for (int n = 0; n < rulesList.size(); n++) {
                            srmPonAuctionScoreRulesDAO_HI.delete(rulesList.get(
                                    n).getScoreRuleId());
                        }
                    }
                } else {
                    return SToolUtils.convertResultJSONObj("E", "删除草稿箱，" + params.getString("auctionHeaderId") + "不存在", 0, null);
                }
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功", headersList.size(), null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：新建一轮
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public JSONObject saveNewAcution(JSONObject params) throws Exception {
        LOGGER.info("saveNewAcution");
        Integer userId = params.getInteger("varUserId");
        // 接收到要新建的对象
        JSONObject resutDound = params.getJSONObject("bondData");
        // 类型
        String auctionCode = resutDound.getString("auctionType");
        // 获取询价头ID
        Integer auctionHeaderId = resutDound.getInteger("auctionHeaderId");
        // 是否复制附件
        String isCopyFile = resutDound.getString("isCopyFile");
        // 是否复制备注
        String isCopyNots = resutDound.getString("isCopyNots");

        // 初始化实体类
        SrmPonAuctionHeadersEntity_HI headersEntityHi = null;
        SrmPonAuctionHeadersEntity_HI newHeadersEntityHi = new SrmPonAuctionHeadersEntity_HI();
        // 获取到原来的询价头信息
        headersEntityHi = srmPonAuctionHeadersDAO_HI.getById(auctionHeaderId);
        if (headersEntityHi.getAuctionRoundNumber() == null || "".equals(headersEntityHi.getAuctionRoundNumber())) {
            headersEntityHi.setAuctionRoundNumber(headersEntityHi.getAuctionNumber()); //将首轮的轮次编号设为当前编号
        }
        headersEntityHi.setRounds(headersEntityHi.getRounds() == null ? 1 : (headersEntityHi.getRounds() + 1));
        if ("INQUIRY".equals(auctionCode)) {//询价
            headersEntityHi.setAuctionStatus("CANCELED");// 已取消
        } else {//招标
            headersEntityHi.setAuctionStatus("ROUND_COMPLATED");// 本轮已完成
        }

        headersEntityHi.setOperatorUserId(userId);
        srmPonAuctionHeadersDAO_HI.update(headersEntityHi);

        //标书的头信息
        // 复制附件
        if ("Y".equals(isCopyFile)) {
            newHeadersEntityHi.setToSupplierFileId(headersEntityHi.getToSupplierFileId());
            newHeadersEntityHi.setToJuryFileId(headersEntityHi.getToJuryFileId());
        }
        // 复制备注
        if ("Y".equals(isCopyNots)) {
            newHeadersEntityHi.setNoteToJury(headersEntityHi.getNoteToJury());
            newHeadersEntityHi.setNoteToSupplier(headersEntityHi.getNoteToSupplier());
        }
        // 生成单据
        String auctionNumber = headersEntityHi.getAuctionNumber();
        // 如果被复制的这一轮的第几轮数为空 那么就是第一轮 反正直接set
        if (headersEntityHi.getRounds() == 1) {
            newHeadersEntityHi.setFirstRound(headersEntityHi.getAuctionHeaderId());
        } else {
            newHeadersEntityHi.setFirstRound(headersEntityHi.getFirstRound());
        }
        //编号不变
        newHeadersEntityHi.setAuctionNumber(headersEntityHi.getAuctionNumber());
        newHeadersEntityHi.setAuctionTitle(headersEntityHi.getAuctionTitle());
        newHeadersEntityHi.setOrgId(headersEntityHi.getOrgId());
        newHeadersEntityHi.setAuctionType(headersEntityHi.getAuctionType());
        newHeadersEntityHi.setContractType(headersEntityHi.getContractType());
        newHeadersEntityHi.setAuctionStatus("DRAFT");// 状态为拟定
        /*update by zwj start 新标书中头信息的“创建人”改为当前账号登陆人*/
        /*newHeadersEntityHi.setBuyerId(headersEntityHi.getBuyerId());*/
        newHeadersEntityHi.setBuyerId(params.getInteger("varEmployeeId"));
        /*update by zwj end 新标书中头信息的“创建人”改为当前账号登陆人*/
        newHeadersEntityHi.setInvitingBidWay(headersEntityHi.getInvitingBidWay());
        newHeadersEntityHi.setReceiveToOrganizationId(headersEntityHi.getReceiveToOrganizationId());
        newHeadersEntityHi.setReceiveToAddress(headersEntityHi.getReceiveToAddress());
        newHeadersEntityHi.setPaymentCondition(headersEntityHi.getPaymentCondition());
        newHeadersEntityHi.setPaymentConditionUpdateFlag(headersEntityHi.getPaymentConditionUpdateFlag());
        newHeadersEntityHi.setSubsectionFlag(headersEntityHi.getSubsectionFlag());
        newHeadersEntityHi.setBidStartDate(headersEntityHi.getBidStartDate());
        newHeadersEntityHi.setBidEndDate(headersEntityHi.getBidEndDate());
        newHeadersEntityHi.setCurrencyCode(headersEntityHi.getCurrencyCode());
        newHeadersEntityHi.setNumberPriceDecimals(headersEntityHi.getNumberPriceDecimals());
        newHeadersEntityHi.setAllowUpdateTaxRate(headersEntityHi.getAllowUpdateTaxRate());
        newHeadersEntityHi.setShowCurrentRoundMinPrice(headersEntityHi.getShowCurrentRoundMinPrice());
        newHeadersEntityHi.setShowCurrentRoundRanking(headersEntityHi.getShowCurrentRoundRanking());
        newHeadersEntityHi.setAllItemBidFlag(headersEntityHi.getAllItemBidFlag());
        newHeadersEntityHi.setMultipleBidFlag(headersEntityHi.getMultipleBidFlag());
        newHeadersEntityHi.setMaxBidFrequency(headersEntityHi.getMaxBidFrequency());
        newHeadersEntityHi.setMinDecreasingRange(headersEntityHi.getMinDecreasingRange());
        newHeadersEntityHi.setBidBond(headersEntityHi.getBidBond());
        newHeadersEntityHi.setBidBondTerm(headersEntityHi.getBidBondTerm());
        newHeadersEntityHi.setBidBondAccountNumber(headersEntityHi.getBidBondAccountNumber());
        newHeadersEntityHi.setBidBondBankName(headersEntityHi.getBidBondBankName());
        newHeadersEntityHi.setTenderCost(headersEntityHi.getTenderCost());
        newHeadersEntityHi.setPublishApprovalStatus("DRAFT");
        newHeadersEntityHi.setRounds(headersEntityHi.getRounds());// 每次复制加1
        newHeadersEntityHi.setLastRound(headersEntityHi.getAuctionHeaderId());// 上一轮id
        newHeadersEntityHi.setAuctionRoundNumber(auctionNumber + "-" + headersEntityHi.getRounds());
        newHeadersEntityHi.setOpenBiddingFlag(headersEntityHi.getOpenBiddingFlag());
        newHeadersEntityHi.setOpenBiddingDate(headersEntityHi.getOpenBiddingDate());
        newHeadersEntityHi.setJudgeCompleteDate(headersEntityHi.getJudgeCompleteDate());
        newHeadersEntityHi.setAwardStatus(headersEntityHi.getAwardStatus());
        newHeadersEntityHi.setAwardApprovalStatus(headersEntityHi.getAwardApprovalStatus());
        newHeadersEntityHi.setTemplateId(headersEntityHi.getTemplateId());
        newHeadersEntityHi.setTemplateCode(headersEntityHi.getTemplateCode());
        newHeadersEntityHi.setTemplateName(headersEntityHi.getTemplateName());
        newHeadersEntityHi.setItemType(headersEntityHi.getItemType());
        newHeadersEntityHi.setEkpNumber(headersEntityHi.getEkpNumber());
        newHeadersEntityHi.setVersionNum(0);
        newHeadersEntityHi.setOperatorUserId(userId);
        try {
            srmPonAuctionHeadersDAO_HI.save(newHeadersEntityHi);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("复制头信息失败");
        }

        //标书的分组信息
        List<SrmPonAuctionGroupsEntity_HI> auctionGroupsEntityHis = srmPonAuctionGroupsDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
        List<SrmPonAuctionGroupsEntity_HI> newAuctionGroupsEntityHis = new ArrayList();
        SrmPonAuctionGroupsEntity_HI newGroupsEntityHi = null;
        for (SrmPonAuctionGroupsEntity_HI resultGroupEntity : auctionGroupsEntityHis) {
            newGroupsEntityHi = new SrmPonAuctionGroupsEntity_HI();
            newGroupsEntityHi.setAuctionHeaderId(newHeadersEntityHi.getAuctionHeaderId());
            newGroupsEntityHi.setGroupName(resultGroupEntity.getGroupName());
            newGroupsEntityHi.setAttribute2(resultGroupEntity.getGroupId().toString());// 把原来的id放入这个字段里
            newGroupsEntityHi.setOperatorUserId(userId);
            newAuctionGroupsEntityHis.add(newGroupsEntityHi);
        }
        try {
            if (!newAuctionGroupsEntityHis.isEmpty()) {
                srmPonAuctionGroupsDAO_HI.save(newAuctionGroupsEntityHis);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("物料信息新建错误");
        }

        //标书的物料信息
        List<SrmPonAuctionItemsEntity_HI> auctionItemsEntityHis = srmPonAuctionItemsDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
        List<SrmPonAuctionItemsEntity_HI> newAuctionItemsEntityHis = new ArrayList<SrmPonAuctionItemsEntity_HI>();
        SrmPonAuctionItemsEntity_HI newItemEntity = null;
        for (SrmPonAuctionItemsEntity_HI resultItemsEntity : auctionItemsEntityHis) {
            newItemEntity = new SrmPonAuctionItemsEntity_HI();
            newItemEntity.setAuctionHeaderId(newHeadersEntityHi.getAuctionHeaderId());
            newItemEntity.setGroupId(getGroupId(newAuctionGroupsEntityHis, resultItemsEntity.getGroupId()));
            newItemEntity.setItemId(resultItemsEntity.getItemId());
            newItemEntity.setItemDescription(resultItemsEntity.getItemDescription());
            newItemEntity.setUnitOfMeasure(resultItemsEntity.getUnitOfMeasure());
            newItemEntity.setTaxRateCode(resultItemsEntity.getTaxRateCode());
            newItemEntity.setCategoryId(resultItemsEntity.getCategoryId());
            newItemEntity.setQuantity(resultItemsEntity.getQuantity());
            newItemEntity.setStartDate(resultItemsEntity.getStartDate());
            newItemEntity.setEndDate(resultItemsEntity.getEndDate());
            newItemEntity.setNotes(resultItemsEntity.getNotes());
            newItemEntity.setAwardStatus("1");
            newItemEntity.setFileId(resultItemsEntity.getFileId());
            newItemEntity.setSpecification(resultItemsEntity.getSpecification());
            newItemEntity.setOperatorUserId(userId);
            newAuctionItemsEntityHis.add(newItemEntity);
        }
        try {
            if (!newAuctionItemsEntityHis.isEmpty()) {
                srmPonAuctionItemsDAO_HI.save(newAuctionItemsEntityHis);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("标的物信息复制失败");
        }

        if (headersEntityHi.getInvitingBidWay().equals("1")) {    //如果是邀请招标，则复制供应商信息
            List<SrmPonAuctionSuppliersEntity_HI> auctionSuppliersEntityHis = srmPonAuctionSuppliersDAO_HI
                    .findByProperty("auctionHeaderId", auctionHeaderId);
            List<SrmPonAuctionSuppliersEntity_HI> newAuctionSuppliersEntityHis = new ArrayList<SrmPonAuctionSuppliersEntity_HI>();
            SrmPonAuctionSuppliersEntity_HI newSuppliersEntity = null;
            for (SrmPonAuctionSuppliersEntity_HI resultSupplierEntity : auctionSuppliersEntityHis) {
                newSuppliersEntity = new SrmPonAuctionSuppliersEntity_HI();
                newSuppliersEntity.setAuctionHeaderId(newHeadersEntityHi.getAuctionHeaderId());
                newSuppliersEntity.setSupplierId(resultSupplierEntity.getSupplierId());
                newSuppliersEntity.setSupplierName(resultSupplierEntity.getSupplierName());
                newSuppliersEntity.setSupplierContactId(resultSupplierEntity.getSupplierContactId());
                newSuppliersEntity.setSupplierContactName(resultSupplierEntity.getSupplierContactName());
                newSuppliersEntity.setSupplierContactPhone(resultSupplierEntity.getSupplierContactPhone());
                newSuppliersEntity.setSupplierContactEmail(resultSupplierEntity.getSupplierContactEmail());
                newSuppliersEntity.setLastRoundAmount(resultSupplierEntity.getLastRoundAmount());
                newSuppliersEntity.setInvitingBidWay(resultSupplierEntity.getInvitingBidWay());
                newSuppliersEntity.setNumberOfAwarded(resultSupplierEntity.getNumberOfAwarded());
                newSuppliersEntity.setNumberOfInvitations(resultSupplierEntity.getNumberOfInvitations());
                newSuppliersEntity.setQuantityScore(resultSupplierEntity.getQuantityScore());
                newSuppliersEntity.setPerformanceScore(resultSupplierEntity.getPerformanceScore());
                newSuppliersEntity.setOperatorUserId(userId);
                newAuctionSuppliersEntityHis.add(newSuppliersEntity);
            }
            try {
                if (!newAuctionSuppliersEntityHis.isEmpty()) {
                    srmPonAuctionSuppliersDAO_HI.save(newAuctionSuppliersEntityHis);
                }
            } catch (Exception e) {
                //e.printStackTrace();
                throw new Exception("参与招标的供应商信息复制失败");
            }
        }

        List<SrmPonAuctionJuriesEntity_HI> auctionJuriesntityHis = srmPonAuctionJuriesDAO_HI
                .findByProperty("auctionHeaderId", auctionHeaderId);
        List<SrmPonAuctionJuriesEntity_HI> newAuctionJuriesEntityHis = new ArrayList<SrmPonAuctionJuriesEntity_HI>();
        SrmPonAuctionJuriesEntity_HI newJurieEntity = null;
        for (SrmPonAuctionJuriesEntity_HI resultJureEnityt : auctionJuriesntityHis) {
            newJurieEntity = new SrmPonAuctionJuriesEntity_HI();
            /*update by zwj start 协作小组页签中的“拟标人”字段要改为当前账号登陆人*/
			/*newJurieEntity.setAuctionHeaderId(newHeadersEntityHi
					.getAuctionHeaderId());
			newJurieEntity.setEmployeeId(resultJureEnityt.getEmployeeId());
			newJurieEntity.setUserId(resultJureEnityt.getUserId());
			newJurieEntity.setUserDuty(resultJureEnityt.getUserDuty());
			newJurieEntity.setOperatorUserId(userId);
			newAuctionJuriesEntityHis.add(newJurieEntity);*/
            if ("1".equals(resultJureEnityt.getUserDuty())) {
                newJurieEntity.setEmployeeId(params.getInteger("varEmployeeId"));
                newJurieEntity.setUserId(userId);
            } else {
                newJurieEntity.setEmployeeId(resultJureEnityt.getEmployeeId());
                newJurieEntity.setUserId(resultJureEnityt.getUserId());
            }
            newJurieEntity.setAuctionHeaderId(newHeadersEntityHi.getAuctionHeaderId());
            newJurieEntity.setUserDuty(resultJureEnityt.getUserDuty());
            newJurieEntity.setOperatorUserId(userId);
            newAuctionJuriesEntityHis.add(newJurieEntity);
            /*update by zwj end 协作小组页签中的“拟标人”字段要改为当前账号登陆人*/
        }
        try {
            if (!newAuctionJuriesEntityHis.isEmpty()) {
                srmPonAuctionJuriesDAO_HI.save(newAuctionJuriesEntityHis);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("评标小组信息复制失败");
        }

        List<SrmPonAuctionScoreRulesEntity_HI> ponAuctionScoreRulesOlds = srmPonAuctionScoreRulesDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
        if (ponAuctionScoreRulesOlds.size() > 0) {
            List<SrmPonAuctionScoreRulesEntity_HI> ponAuctionScoreRulesList = new ArrayList<SrmPonAuctionScoreRulesEntity_HI>();
            SrmPonAuctionScoreRulesEntity_HI ponAuctionScoreRules = null;
            for (SrmPonAuctionScoreRulesEntity_HI ponAuctionScoreRulesOld : ponAuctionScoreRulesOlds) {
                ponAuctionScoreRules = new SrmPonAuctionScoreRulesEntity_HI();
                ponAuctionScoreRules.setAuctionHeaderId(newHeadersEntityHi.getAuctionHeaderId());
                ponAuctionScoreRules.setScorePartition(ponAuctionScoreRulesOld.getScorePartition());
                ponAuctionScoreRules.setScoreItem(ponAuctionScoreRulesOld.getScoreItem());
                ponAuctionScoreRules.setPowerWeight(ponAuctionScoreRulesOld.getPowerWeight());
                ponAuctionScoreRules.setMaxScore(ponAuctionScoreRulesOld.getMaxScore());
                ponAuctionScoreRules.setOperatorUserId(userId);
                ponAuctionScoreRulesList.add(ponAuctionScoreRules);
            }
            try {
                if (!ponAuctionScoreRulesList.isEmpty()) {
                    srmPonAuctionScoreRulesDAO_HI.save(ponAuctionScoreRulesList);
                }
            } catch (Exception e) {
                //e.printStackTrace();
                throw new Exception("招标规则复制失败");
            }
        }

        // 供应商报价
        List<SrmPonBidHeadersEntity_HI> bidHeadersEntityHis = srmPonBidHeadersDAO_HI
                .findByProperty("auctionHeaderId", auctionHeaderId);
        List<SrmPonBidItemPricesEntity_HI> bidItemPricesEntityHis = null;
        for (SrmPonBidHeadersEntity_HI bidHeadersEntityHi : bidHeadersEntityHis) {
            bidHeadersEntityHi.setAwardStatus("4");// 未报价
            bidHeadersEntityHi.setOperatorUserId(userId);
            bidItemPricesEntityHis = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", bidHeadersEntityHi.getBidHeaderId());
            for (SrmPonBidItemPricesEntity_HI bidItemPricesEntityHi : bidItemPricesEntityHis) {
                bidItemPricesEntityHi.setAwardStatus("4");// 未报价
                bidItemPricesEntityHi.setOperatorUserId(userId);
            }
            if (bidItemPricesEntityHis != null) {
                srmPonBidItemPricesDAO_HI.update(bidItemPricesEntityHis);
            }
        }
        if (bidHeadersEntityHis != null) {
            srmPonBidHeadersDAO_HI.update(bidHeadersEntityHis);
        }
        return SToolUtils.convertResultJSONObj("S",
                "新建一轮" + newHeadersEntityHi.getAuctionNumber() + "-"
                        + newHeadersEntityHi.getRounds() + "成功", 1, newHeadersEntityHi.getAuctionHeaderId());
    }

    /**
     * Description：调整标书时间
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public JSONObject updateEndTime(JSONObject params) throws Exception {
        Integer userId = params.getInteger("varUserId"); // 用户Id
        Integer action = params.getInteger("type"); // 结束选择项
        Date endTime = params.getDate("newEndDate"); // 结束时间
        Integer auctionHeaderId = params.getInteger("auctionHeaderId"); // 主键ID
        try {
            SrmPonAuctionHeadersEntity_HI updateTimeEntity = srmPonAuctionHeadersDAO_HI.getById(auctionHeaderId);
            if (1 == action) {
                updateTimeEntity.setAuctionStatus("CLOSED");
                updateTimeEntity.setBidEndDate(new Date());
            } else if (2 == action) {
                updateTimeEntity.setBidEndDate(endTime);
            }
            updateTimeEntity.setOperatorUserId(userId);
            srmPonAuctionHeadersDAO_HI.saveOrUpdate(updateTimeEntity);
            return SToolUtils.convertResultJSONObj("S", "标书时间调整成功", 1, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
            //e.printStackTrace();
            throw new Exception("标书时间调整失败");
        }
    }

    /**
     * Description：根据上一轮的id查询本轮小组id
     * @param auctionGroupsEntityHis 寻源组别信息
     * @param lowGroupId 组别ID
     * @return Integer
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    private Integer getGroupId(
            List<SrmPonAuctionGroupsEntity_HI> auctionGroupsEntityHis,
            Integer lowGroupId) throws Exception {
        try {
            for (SrmPonAuctionGroupsEntity_HI groupsEntityHi : auctionGroupsEntityHis) {
                if (lowGroupId != null && lowGroupId.toString().equals(groupsEntityHi.getAttribute2())) {
                    return groupsEntityHi.getGroupId();
                }
            }
            return null;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：获取当前用户进入决标的操作权限
     * @param params 参数
     * @return SrmPonAuctionHeadersEntity_HI_RO
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public SrmPonAuctionHeadersEntity_HI_RO getUserAuthorization(
            JSONObject params) throws Exception {
        LOGGER.info("查询当前用户进入决标的操作权限：" + params);
        SrmPonAuctionHeadersEntity_HI_RO headersEntity = null;
        StringBuffer querySql = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_USER_AUTHORIZTION_SQL);
        Integer userId = params.getInteger("varUserId");
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        //如果是管理员
        if ("Y".equals(params.getString("varIsAdmin"))) {
            headersEntity = new SrmPonAuctionHeadersEntity_HI_RO();
            headersEntity.setUserDuty("3"); //就给评标人
            return headersEntity;
        }
        try {
            //查询当前用户的权限，一个人最多三个职责
            List<SrmPonAuctionHeadersEntity_HI_RO> auctionList = srmPonAuctionHeadersDAO_HI_RO.findList(querySql, new Object[]{userId, auctionHeaderId});
            for (SrmPonAuctionHeadersEntity_HI_RO resultEntity : auctionList) {
                if ("2".equals(resultEntity.getUserDuty())) {
                    return resultEntity;
                } else if ("1".equals(resultEntity.getUserDuty())) {
                    return resultEntity;
                }
            }
            return headersEntity;
        } catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
            throw new Exception("获取用户操作限制失败！");
        }
    }

    /**
     * Description：查询轮次编号下了框
     * @param jsonParams 参数
     * @return List<SrmPonAuctionHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    public List<SrmPonAuctionHeadersEntity_HI_RO> getAuctionByRound(JSONObject jsonParams) throws Exception {
        LOGGER.info("参数：" + jsonParams.toString());
        String auctionNumber = jsonParams.getString("auctionNumber");
        try {
            StringBuffer querySql;
            querySql = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_AUCTION_ROUND_NUMBERS);
            List<SrmPonAuctionHeadersEntity_HI_RO> result = srmPonAuctionHeadersDAO_HI_RO.findList(querySql.toString(), new Object[]{auctionNumber});
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：根据auctionHeaderId查询洽谈头表——用于决标汇总的导出（已截止）
     * @param auctionHeaderId 寻源单据ID
     * @return SrmPonAuctionHeadersEntity_HI
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public SrmPonAuctionHeadersEntity_HI findSrmPonAuctionHeaders(Integer auctionHeaderId) {
        SrmPonAuctionHeadersEntity_HI auctionHeaders = new SrmPonAuctionHeadersEntity_HI();
        auctionHeaders = srmPonAuctionHeadersDAO_HI.getById(auctionHeaderId);
        return auctionHeaders;
    }

    /**
     * Description：复制标书
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public JSONObject saveCopyAcution(JSONObject params) throws Exception {
        LOGGER.info("saveCopyAcution");
        Integer userId = params.getInteger("varUserId");
        // 接收到要新建的对象
        JSONObject acutionData = params.getJSONObject("acutionData");
        // 获取询价头id
        Integer auctionHeaderId = acutionData.getInteger("auctionHeaderId");
        // 是否复制附件
        String isCopyEnclosure = acutionData.getString("isCopyEnclosure");
        // 是否复制备注
        String isCopyRemarks = acutionData.getString("isCopyRemarks");

        // 初始化实体类
        SrmPonAuctionHeadersEntity_HI headersEntityHi = null;
        SrmPonAuctionHeadersEntity_HI newHeadersEntityHi = new SrmPonAuctionHeadersEntity_HI();
        // 获取到原来的询价头信息
        headersEntityHi = srmPonAuctionHeadersDAO_HI.getById(auctionHeaderId);
        if (null == headersEntityHi.getAuctionRoundNumber() || "".equals(headersEntityHi.getAuctionRoundNumber())) {
            headersEntityHi.setAuctionRoundNumber(headersEntityHi.getAuctionNumber());//将首轮的轮次编号设为当前编号
        }
        headersEntityHi.setRounds(headersEntityHi.getRounds() == null ? 1
                : (headersEntityHi.getRounds() + 1));

        headersEntityHi.setAuctionStatus("ROUND_COMPLATED");// 本轮已完成
        headersEntityHi.setOperatorUserId(userId);
        srmPonAuctionHeadersDAO_HI.update(headersEntityHi);
        // 复制附件
        if ("Y".equals(isCopyEnclosure)) {
            newHeadersEntityHi.setToSupplierFileId(headersEntityHi.getToSupplierFileId());
            newHeadersEntityHi.setToJuryFileId(headersEntityHi.getToJuryFileId());
        }
        // 复制备注
        if ("Y".equals(isCopyRemarks)) {
            newHeadersEntityHi.setNoteToJury(headersEntityHi.getNoteToJury());
            newHeadersEntityHi.setNoteToSupplier(headersEntityHi.getNoteToSupplier());
        }
        // 生成单据
        String auctionNumber = headersEntityHi.getAuctionNumber();

        // 如果被复制的这一轮的第几轮数为空 那么就是第一轮 反正直接set
        if (headersEntityHi.getRounds() == 1) {
            newHeadersEntityHi.setFirstRound(headersEntityHi.getAuctionHeaderId());
        } else {
            newHeadersEntityHi.setFirstRound(headersEntityHi.getFirstRound());
        }
        //ZB-yyyy-mm-dd+四位流水编码+_n（n为次数，第一次创建新一轮为1，第二次创建为2）
        newHeadersEntityHi.setAuctionNumber(auctionNumber + "-" + headersEntityHi.getRounds());

        newHeadersEntityHi.setAuctionTitle(headersEntityHi.getAuctionTitle());
        newHeadersEntityHi.setOrgId(headersEntityHi.getOrgId());
        newHeadersEntityHi.setAuctionType(headersEntityHi.getAuctionType());
        newHeadersEntityHi.setContractType(headersEntityHi.getContractType());
        newHeadersEntityHi.setAuctionStatus("DRAFT");// 状态为拟定
        newHeadersEntityHi.setBuyerId(headersEntityHi.getBuyerId());
        newHeadersEntityHi.setInvitingBidWay(headersEntityHi.getInvitingBidWay());
        newHeadersEntityHi.setReceiveToOrganizationId(headersEntityHi.getReceiveToOrganizationId());
        newHeadersEntityHi.setReceiveToAddress(headersEntityHi.getReceiveToAddress());
        newHeadersEntityHi.setPaymentCondition(headersEntityHi.getPaymentCondition());
        newHeadersEntityHi.setPaymentConditionUpdateFlag(headersEntityHi.getPaymentConditionUpdateFlag());
        newHeadersEntityHi.setSubsectionFlag(headersEntityHi.getSubsectionFlag());
        newHeadersEntityHi.setBidStartDate(headersEntityHi.getBidStartDate());
        newHeadersEntityHi.setBidEndDate(headersEntityHi.getBidEndDate());
        newHeadersEntityHi.setCurrencyCode(headersEntityHi.getCurrencyCode());
        newHeadersEntityHi.setNumberPriceDecimals(headersEntityHi.getNumberPriceDecimals());
        newHeadersEntityHi.setAllowUpdateTaxRate(headersEntityHi.getAllowUpdateTaxRate());
        newHeadersEntityHi.setShowCurrentRoundMinPrice(headersEntityHi.getShowCurrentRoundMinPrice());
        newHeadersEntityHi.setShowCurrentRoundRanking(headersEntityHi.getShowCurrentRoundRanking());
        newHeadersEntityHi.setAllItemBidFlag(headersEntityHi.getAllItemBidFlag());
        newHeadersEntityHi.setMultipleBidFlag(headersEntityHi.getMultipleBidFlag());
        newHeadersEntityHi.setMaxBidFrequency(headersEntityHi.getMaxBidFrequency());
        newHeadersEntityHi.setMinDecreasingRange(headersEntityHi.getMinDecreasingRange());
        newHeadersEntityHi.setBidBond(headersEntityHi.getBidBond());
        newHeadersEntityHi.setBidBondTerm(headersEntityHi.getBidBondTerm());
        newHeadersEntityHi.setBidBondAccountNumber(headersEntityHi.getBidBondAccountNumber());
        newHeadersEntityHi.setBidBondBankName(headersEntityHi.getBidBondBankName());
        newHeadersEntityHi.setTenderCost(headersEntityHi.getTenderCost());
        newHeadersEntityHi.setPublishApprovalStatus("DRAFT");
        newHeadersEntityHi.setRounds(headersEntityHi.getRounds());// 每次复制加1
        newHeadersEntityHi.setLastRound(headersEntityHi.getAuctionHeaderId());// 上一轮id
		/*开标状态、评分时间、中标状态没必要复制
		newHeadersEntityHi.setOpenBiddingFlag(headersEntityHi.getOpenBiddingFlag());
		newHeadersEntityHi.setOpenBiddingDate(headersEntityHi.getOpenBiddingDate());
		newHeadersEntityHi.setJudgeCompleteDate(headersEntityHi.getJudgeCompleteDate());
		newHeadersEntityHi.setAwardStatus(headersEntityHi.getAwardStatus());
		newHeadersEntityHi.setAwardApprovalStatus(headersEntityHi.getAwardApprovalStatus());
		*/
        newHeadersEntityHi.setVersionNum(0);
        newHeadersEntityHi.setOperatorUserId(userId);
        try {
            // 重新保存
            srmPonAuctionHeadersDAO_HI.save(newHeadersEntityHi);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("复制头信息失败");
        }
        List<SrmPonAuctionGroupsEntity_HI> auctionGroupsEntityHis = srmPonAuctionGroupsDAO_HI
                .findByProperty("auctionHeaderId", auctionHeaderId);
        List<SrmPonAuctionGroupsEntity_HI> newAuctionGroupsEntityHis = new ArrayList<SrmPonAuctionGroupsEntity_HI>();
        SrmPonAuctionGroupsEntity_HI newGroupsEntityHi = null;
        for (SrmPonAuctionGroupsEntity_HI resultGroupEntity : auctionGroupsEntityHis) {
            newGroupsEntityHi = new SrmPonAuctionGroupsEntity_HI();
            newGroupsEntityHi.setAuctionHeaderId(newHeadersEntityHi.getAuctionHeaderId());
            newGroupsEntityHi.setGroupName(resultGroupEntity.getGroupName());
            newGroupsEntityHi.setAttribute2(resultGroupEntity.getGroupId().toString());// 把原来的id放入这个字段里
            newGroupsEntityHi.setOperatorUserId(userId);
            newAuctionGroupsEntityHis.add(newGroupsEntityHi);
        }
        try {
            if (!newAuctionGroupsEntityHis.isEmpty()) {
                srmPonAuctionGroupsDAO_HI.save(newAuctionGroupsEntityHis);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("物料信息新建错误");
        }
        List<SrmPonAuctionItemsEntity_HI> auctionItemsEntityHis = srmPonAuctionItemsDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
        List<SrmPonAuctionItemsEntity_HI> newAuctionItemsEntityHis = new ArrayList<SrmPonAuctionItemsEntity_HI>();
        SrmPonAuctionItemsEntity_HI newItemEntity = null;
        for (SrmPonAuctionItemsEntity_HI resultItemsEntity : auctionItemsEntityHis) {
            newItemEntity = new SrmPonAuctionItemsEntity_HI();
            newItemEntity.setAuctionHeaderId(newHeadersEntityHi.getAuctionHeaderId());
            newItemEntity.setGroupId(getGroupId(newAuctionGroupsEntityHis, resultItemsEntity.getGroupId()));
            newItemEntity.setItemId(resultItemsEntity.getItemId());
            newItemEntity.setItemDescription(resultItemsEntity.getItemDescription());
            newItemEntity.setUnitOfMeasure(resultItemsEntity.getUnitOfMeasure());
            newItemEntity.setTaxRateCode(resultItemsEntity.getTaxRateCode());
            newItemEntity.setCategoryId(resultItemsEntity.getCategoryId());
            newItemEntity.setQuantity(resultItemsEntity.getQuantity());
            newItemEntity.setStartDate(resultItemsEntity.getStartDate());
            newItemEntity.setEndDate(resultItemsEntity.getEndDate());
            newItemEntity.setNotes(resultItemsEntity.getNotes());
            newItemEntity.setAwardStatus("1");
            newItemEntity.setOperatorUserId(userId);
            newAuctionItemsEntityHis.add(newItemEntity);
        }
        try {
            if (!newAuctionItemsEntityHis.isEmpty()) {
                srmPonAuctionItemsDAO_HI.save(newAuctionItemsEntityHis);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("标的物信息复制失败");
        }

        List<SrmPonAuctionSuppliersEntity_HI> auctionSuppliersEntityHis = srmPonAuctionSuppliersDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
        List<SrmPonAuctionSuppliersEntity_HI> newAuctionSuppliersEntityHis = new ArrayList<SrmPonAuctionSuppliersEntity_HI>();
        SrmPonAuctionSuppliersEntity_HI newSuppliersEntity = null;
        for (SrmPonAuctionSuppliersEntity_HI resultSupplierEntity : auctionSuppliersEntityHis) {
            newSuppliersEntity = new SrmPonAuctionSuppliersEntity_HI();
            newSuppliersEntity.setAuctionHeaderId(newHeadersEntityHi.getAuctionHeaderId());
            newSuppliersEntity.setSupplierId(resultSupplierEntity.getSupplierId());
            newSuppliersEntity.setSupplierName(resultSupplierEntity.getSupplierName());
            newSuppliersEntity.setSupplierContactId(resultSupplierEntity.getSupplierContactId());
            newSuppliersEntity.setSupplierContactName(resultSupplierEntity.getSupplierContactName());
            newSuppliersEntity.setSupplierContactPhone(resultSupplierEntity.getSupplierContactPhone());
            newSuppliersEntity.setSupplierContactEmail(resultSupplierEntity.getSupplierContactEmail());
            newSuppliersEntity.setLastRoundAmount(resultSupplierEntity.getLastRoundAmount());
            newSuppliersEntity.setNumberOfAwarded(resultSupplierEntity.getNumberOfAwarded());
            newSuppliersEntity.setNumberOfInvitations(resultSupplierEntity.getNumberOfInvitations());
            newSuppliersEntity.setQuantityScore(resultSupplierEntity.getQuantityScore());
            newSuppliersEntity.setPerformanceScore(resultSupplierEntity.getPerformanceScore());
            newSuppliersEntity.setInvitingBidWay("1");//邀请招标
            newSuppliersEntity.setOperatorUserId(userId);
            newAuctionSuppliersEntityHis.add(newSuppliersEntity);
        }
        try {
            if (!newAuctionSuppliersEntityHis.isEmpty()) {
                if ("1".equals(newHeadersEntityHi.getInvitingBidWay())) {//邀请招标就复制供应商信息
                    srmPonAuctionSuppliersDAO_HI.save(newAuctionSuppliersEntityHis);
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("参与招标的供应商信息复制失败");
        }
        List<SrmPonAuctionJuriesEntity_HI> auctionJuriesntityHis = srmPonAuctionJuriesDAO_HI
                .findByProperty("auctionHeaderId", auctionHeaderId);
        List<SrmPonAuctionJuriesEntity_HI> newAuctionJuriesEntityHis = new ArrayList<SrmPonAuctionJuriesEntity_HI>();
        SrmPonAuctionJuriesEntity_HI newJurieEntity = null;
        for (SrmPonAuctionJuriesEntity_HI resultJureEnityt : auctionJuriesntityHis) {
            newJurieEntity = new SrmPonAuctionJuriesEntity_HI();
            newJurieEntity.setAuctionHeaderId(newHeadersEntityHi.getAuctionHeaderId());
            newJurieEntity.setEmployeeId(resultJureEnityt.getEmployeeId());
            newJurieEntity.setUserId(resultJureEnityt.getUserId());
            newJurieEntity.setUserDuty(resultJureEnityt.getUserDuty());
            newJurieEntity.setOperatorUserId(userId);
            newAuctionJuriesEntityHis.add(newJurieEntity);
        }
        try {
            if (!newAuctionJuriesEntityHis.isEmpty()) {
                srmPonAuctionJuriesDAO_HI.save(newAuctionJuriesEntityHis);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("评标小组信息复制失败");
        }

        List<SrmPonAuctionScoreRulesEntity_HI> ponAuctionScoreRulesOlds = srmPonAuctionScoreRulesDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
        if (ponAuctionScoreRulesOlds.size() > 0) {
            List<SrmPonAuctionScoreRulesEntity_HI> ponAuctionScoreRulesList = new ArrayList<SrmPonAuctionScoreRulesEntity_HI>();
            SrmPonAuctionScoreRulesEntity_HI ponAuctionScoreRules = null;
            for (SrmPonAuctionScoreRulesEntity_HI ponAuctionScoreRulesOld : ponAuctionScoreRulesOlds) {
                ponAuctionScoreRules = new SrmPonAuctionScoreRulesEntity_HI();
                ponAuctionScoreRules.setAuctionHeaderId(newHeadersEntityHi.getAuctionHeaderId());
                ponAuctionScoreRules.setScorePartition(ponAuctionScoreRulesOld.getScorePartition());
                ponAuctionScoreRules.setScoreItem(ponAuctionScoreRulesOld.getScoreItem());
                ponAuctionScoreRules.setPowerWeight(ponAuctionScoreRulesOld.getPowerWeight());
                ponAuctionScoreRules.setMaxScore(ponAuctionScoreRulesOld.getMaxScore());
                ponAuctionScoreRules.setOperatorUserId(userId);
                ponAuctionScoreRulesList.add(ponAuctionScoreRules);
            }
            try {
                if (!ponAuctionScoreRulesList.isEmpty()) {
                    srmPonAuctionScoreRulesDAO_HI.save(ponAuctionScoreRulesList);
                }
            } catch (Exception e) {
                //e.printStackTrace();
                throw new Exception("招标规则复制失败");
            }
        }

        // 供应商报价
        List<SrmPonBidHeadersEntity_HI> bidHeadersEntityHis = srmPonBidHeadersDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
        List<SrmPonBidItemPricesEntity_HI> bidItemPricesEntityHis = null;
        for (SrmPonBidHeadersEntity_HI bidHeadersEntityHi : bidHeadersEntityHis) {
            bidHeadersEntityHi.setAwardStatus("4");// 未报价
            bidHeadersEntityHi.setOperatorUserId(userId);
            bidItemPricesEntityHis = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", bidHeadersEntityHi.getBidHeaderId());
            for (SrmPonBidItemPricesEntity_HI bidItemPricesEntityHi : bidItemPricesEntityHis) {
                bidItemPricesEntityHi.setAwardStatus("4");// 未报价
                bidItemPricesEntityHi.setOperatorUserId(userId);
            }
            if (bidItemPricesEntityHis != null) {
                srmPonBidItemPricesDAO_HI.update(bidItemPricesEntityHis);
            }
        }
        if (bidHeadersEntityHis != null) {
            srmPonBidHeadersDAO_HI.update(bidHeadersEntityHis);
        }
        return SToolUtils.convertResultJSONObj("S",
                "复制标书" + newHeadersEntityHi.getAuctionNumber() + "-"
                        + newHeadersEntityHi.getRounds() + "成功", 1, newHeadersEntityHi.getAuctionHeaderId());
    }

    /**
     * Description：获取已发布且截至报价时间，在指定时间段内的洽谈列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return List<SrmPonAuctionHeadersEntity_HI>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    public List<SrmPonAuctionHeadersEntity_HI> findAuctionToClose(Date startDate, Date endDate) {
        String queryString = "from SrmPonAuctionHeadersEntity_HI h where h.auctionStatus = 'PUBLISHED' and h.bidEndDate > :startDate and h.bidEndDate <= :endDate ";
        Map<String, Object> map = new HashMap<String, Object>();
        map.clear();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        List<SrmPonAuctionHeadersEntity_HI> list = srmPonAuctionHeadersDAO_HI.findList(queryString, map);
        return list;
    }

    /**
     * Description：根据指定的标书，截至报价
     * @param auctionHeaderId 寻源单据ID
     * @return String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    public String closeBidding(Integer auctionHeaderId) {
        LOGGER.info("closeBidding--auctionHeaderId:" + auctionHeaderId);
        if (auctionHeaderId == null) {
            return "缺少必要参数";
        }

        Date date = new Date();
        SrmPonAuctionHeadersEntity_HI row = srmPonAuctionHeadersDAO_HI.getById(auctionHeaderId);
        //只有已发布状态的标书到达截标时间才能自动截标
        if (row == null || !"PUBLISHED".equals(row.getAuctionStatus()) || date.before(row.getBidEndDate())) {
            return "截标失败";
        }
        row.setAuctionStatus("CLOSED");
        row.setCloseBiddingDate(date);
        row.setOperatorUserId(1);
        srmPonAuctionHeadersDAO_HI.save(row);
        return "自动截标成功";
    }

    /**
     * Description：登录界面招标公告
     * @param params 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return Pagination<SrmPonAuctionHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21      zwj     	   创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPonAuctionHeadersEntity_HI_RO> findAuctionList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer querySql = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_AUCTION_HEADER);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        if (params != null) {
            //详情
            SaafToolUtils.parperParam(params, "ah.auction_header_id", "auctionHeaderId", querySql, queryParamMap, "=");
        } else {
            //列表
            querySql.append(" AND ah.auction_status != 'DRAFT' AND ah.inviting_bid_way = '2'");
            querySql.append(" ORDER BY ah.last_update_date desc ");
        }
        String countSql = "select count(1) from (" + querySql + ")";
        Pagination<SrmPonAuctionHeadersEntity_HI_RO> list = srmPonAuctionHeadersDAO_HI_RO.findPagination(querySql,countSql, queryParamMap, pageIndex, pageRows);
        return list;
    }

    /**
     * Description：寻源自动截标定时任务,每五分钟运行一次
     * @return Integer
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public Integer updatePonAuctionHeaderJob() throws Exception {
        Integer count = 0;
        String queryString = "from SrmPonAuctionHeadersEntity_HI h where h.auctionStatus = 'PUBLISHED' and h.bidEndDate <= :endDate ";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("endDate", new Date());
        List<SrmPonAuctionHeadersEntity_HI> lists = srmPonAuctionHeadersDAO_HI.findList(queryString.toString(), map);
        if(null == lists || lists.isEmpty()){
            return count;
        }

        for(SrmPonAuctionHeadersEntity_HI list:lists){
            try{
                list.setAuctionStatus("CLOSED");
                list.setCloseBiddingDate(new Date());
                list.setOperatorUserId(1);
                srmPonAuctionHeadersDAO_HI.saveOrUpdate(list);
                count++;
            }catch (Exception e){
                LOGGER.error("未知错误:{}" , e);
                continue;
            }
        }
        return count;
    }
}
