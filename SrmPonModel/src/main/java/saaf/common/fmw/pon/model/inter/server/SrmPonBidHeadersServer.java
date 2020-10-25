package saaf.common.fmw.pon.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

import javafx.beans.binding.ObjectBinding;
import org.apache.poi.ss.formula.functions.Rank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.inter.ISrmBaseNotifications;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.message.email.utils.SendMailUtil;
import saaf.common.fmw.pon.model.entities.*;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionSuppliersEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonBidHeaders;
import saaf.common.fmw.pon.model.inter.ISrmPonBidItemPrices;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierContactsEntity_HI;

import java.math.BigDecimal;
import java.util.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonBidHeadersServer.java
 * Description：寻源--寻源报价头信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonBidHeadersServer")
public class SrmPonBidHeadersServer implements ISrmPonBidHeaders {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonBidHeadersServer.class);

    @Autowired
    private ViewObject<SrmPonBidHeadersEntity_HI> srmPonBidHeadersDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonBidHeadersEntity_HI_RO> srmPonBidHeadersDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPonJuryScoreLinesEntity_HI> srmPonJuryScoreLinesDAO_HI;//评分明细

    @Autowired
    private ViewObject<SrmPonAuctionScoreRulesEntity_HI> srmPonAuctionScoreRulesDAO_HI;//招标规则

    @Autowired
    private ISrmPonBidItemPrices iSrmPonBidItemPrices;//供应商报价行

    @Autowired
    private ViewObject<SrmPonBidItemPricesEntity_HI> srmPonBidItemPricesDAO_HI;//供应商报价行表

    @Autowired
    private BaseViewObject<SrmPonAuctionSuppliersEntity_HI_RO> srmPonAuctionSuppliersDAO_HI_RO;//洽谈受邀请的供应商
    @Autowired
    private ViewObject<SrmPosSupplierContactsEntity_HI> srmPosSupplierContactsDAO_HI;
    private static SendMailUtil sendMailUtil = new SendMailUtil(true);
    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;  //用户DAO
    @Autowired
    private ISrmBaseNotifications iSrmBaseNotifications;//系统通知

    /**
     * Description：供应商报价头表中标（已截止）
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject updateBidHeaderSupplierAwardStatus(JSONObject params) {
        LOGGER.info("参数：" + params.toString());
        Integer userId = params.getInteger("varUserId");
        Integer bidHeaderId = params.getInteger("bidHeaderId");//供应商报价头表的主键Id
        String subsectionFlag = "";//分段价格
        if (null != params.getString("subsectionFlag") && !"".equals(params.getString("subsectionFlag"))) {
            subsectionFlag = params.getString("subsectionFlag");
        }
        if (bidHeaderId == null || "".equals(bidHeaderId)) {
            return SToolUtils.convertResultJSONObj("E", "参数为" + bidHeaderId, 0, null);
        }
        SrmPonBidHeadersEntity_HI bidHeadersEntity = srmPonBidHeadersDAO_HI.getById(bidHeaderId);//供应商报价头表
        if (null != bidHeadersEntity) {
            bidHeadersEntity.setOperatorUserId(userId);
            String awardStatusStr = null;
            if (bidHeadersEntity.getAwardStatus() == null || "".equals(bidHeadersEntity.getAwardStatus())) {
                bidHeadersEntity.setAwardStatus("4");
                bidHeadersEntity.setAwardDate(new Date());
                awardStatusStr = "已中标";
            } else if ("3".equals(bidHeadersEntity.getAwardStatus())) {
                bidHeadersEntity.setAwardStatus("4");
                bidHeadersEntity.setAwardDate(new Date());
                awardStatusStr = "已中标";
            } else if ("4".equals(bidHeadersEntity.getAwardStatus())) {
                bidHeadersEntity.setAwardStatus("3");
                bidHeadersEntity.setAwardDate(null);
                awardStatusStr = "未中标";
            }
            iSrmPonBidItemPrices.updateBidItemPriceAwardStatusByBidHeaderSupplier(userId, bidHeadersEntity.getAuctionHeaderId(), bidHeadersEntity.getBidHeaderId(), bidHeadersEntity.getAwardStatus(), subsectionFlag);
            srmPonBidHeadersDAO_HI.saveEntity(bidHeadersEntity); ////供应商报价头表的update
            /*List<SrmPosSupplierContactsEntity_HI> contactsList = srmPosSupplierContactsDAO_HI.findByProperty("supplierId", bidHeadersEntity.getSupplierId());
            String content="";
            if("4".equals(bidHeadersEntity.getAwardStatus())){
                content="<p>您好！</p><br/>" + "恭喜您寻源单据: " + bidHeadersEntity.getBidNumber() + "中标，请登录系统查看！";
            }else if("3".equals(bidHeadersEntity.getAwardStatus())){
                content="<p>您好！</p><br/>" + "很遗憾您寻源单据" + bidHeadersEntity.getBidNumber() + "未中标！";
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
            }*/
            return SToolUtils.convertResultJSONObj("S", awardStatusStr, 1, null);
        } else {
            return SToolUtils.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * Description：查询供应商
     * @param jsonParams 参数
     * @return SrmPonBidHeadersEntity_HI_RO
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public SrmPonBidHeadersEntity_HI_RO findBidHeadersSupplier(JSONObject jsonParams) {
        LOGGER.info("params:" + jsonParams.toString());
        Integer bidHeaderId = jsonParams.getInteger("bidHeaderId");//洽谈Id
        if (bidHeaderId == null || "".equals(bidHeaderId)) {
            return null;
        }

        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer(SrmPonBidHeadersEntity_HI_RO.QUERY_BIDHEADERSVLIST_SQL);
        sb.append(" AND t.bid_status = 'ACT'");
        sb.append(" AND t.bid_header_id = " + bidHeaderId);//查询所有的供应商报价
        List<SrmPonBidHeadersEntity_HI_RO> result = srmPonBidHeadersDAO_HI_RO.findList(sb);
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    /**
     * Description：查询供应商报价头表（不分页）——带有报价总价与报价总价排名（用于已截止）
     * @param jsonParams 参数
     * @return List<SrmPonBidHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<SrmPonBidHeadersEntity_HI_RO> findBidHeadersSupplierList(JSONObject jsonParams) {
        LOGGER.info("params:" + jsonParams.toString());
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId");//洽谈Id
        String subsectionFlag = jsonParams.getString("subsectionFlag");
        Integer bidHeaderId = jsonParams.getInteger("bidHeaderId");//洽谈Id
        if (auctionHeaderId == null || "".equals(auctionHeaderId)) {
            return null;
        }

        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer(SrmPonBidHeadersEntity_HI_RO.QUERY_BIDHEADERSVLIST_SQL);
        sb.append(" AND t.bid_status='ACT'");
        sb.append(" AND t.auction_header_id=" + auctionHeaderId);//查询所有的供应商报价
        if(!ObjectUtils.isEmpty(bidHeaderId)){
            sb.append(" AND t.bid_header_id=" + bidHeaderId);
        }
        //邀标方式
        SaafToolUtils.parperParam(jsonParams, "t.inviting_bid_way", "invitingBidWay", sb, queryParamMap, "=");
        List<SrmPonBidHeadersEntity_HI_RO> result = srmPonBidHeadersDAO_HI_RO.findList(sb);
        if (null == result || result.size() == 0 || "".equals(result)) {
            //受邀请的供应商
            StringBuffer querySql = new StringBuffer(SrmPonAuctionSuppliersEntity_HI_RO.QUERY_AUCTION_SUPPLIER_LIST_SQL);
            List<SrmPonAuctionSuppliersEntity_HI_RO> supplierResultListV = srmPonAuctionSuppliersDAO_HI_RO.findList(querySql, new Object[]{auctionHeaderId});
            if (null != supplierResultListV && supplierResultListV.size() > 0) {
                for (SrmPonAuctionSuppliersEntity_HI_RO k : supplierResultListV) {
                    SrmPonBidHeadersEntity_HI_RO entity = new SrmPonBidHeadersEntity_HI_RO();
                    entity.setSupplierId(k.getSupplierId());
                    entity.setSupplierName(k.getSupplierName());
                    entity.setSupplierContactId(k.getSupplierContactId());
                    entity.setSupplierContactName(k.getSupplierContactName());
                    entity.setSupplierContactPhone(k.getSupplierContactPhone());
                    entity.setSupplierContactEmail(k.getSupplierContactEmail());
                    entity.setSupplierSiteId(k.getSupplierSiteId());
                    entity.setInviteId(k.getInviteId());
                    entity.setNumberOfAwarded(k.getNumberOfAwarded());
                    entity.setNumberOfInvitations(k.getNumberOfInvitations());
                    entity.setBidBondPayStatus(k.getBidBondPayStatus());
                    entity.setBidBondPayStatusName(k.getBidBondPayStatusName());
                    entity.setTenderCostPayStatus(k.getTenderCostPayStatus());
                    entity.setTenderCostPayStatusName(k.getTenderCostPayStatusName());
                    //entity.setBidHeaderId(-1);
                    entity.setBidHeaderId(0 - k.getSupplierId());
                    result.add(entity);
                }
            }
            return result;
        }

        List<SrmPonBidHeadersEntity_HI_RO> resultRanking = new ArrayList<>();//排序结果
        for (SrmPonBidHeadersEntity_HI_RO k : result) {
            if (null == k.getAwardStatus() || "".equals(k.getAwardStatus())) {
                k.setAwardStatusName("未中标");
            }
            //计算报价总计金额
            Set<Integer> auctionLineIdList = new HashSet<>();//存放不重复的所有报价行的洽谈行Id
            List<SrmPonBidItemPricesEntity_HI> bidItemPricesList = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", k.getBidHeaderId());
            if (null != bidItemPricesList && bidItemPricesList.size() > 0) {
                for (SrmPonBidItemPricesEntity_HI tem : bidItemPricesList) {
                    auctionLineIdList.add(tem.getAuctionLineId());
                }
            }
            if (null != auctionLineIdList && auctionLineIdList.size() > 0) {//计算报价总金额
                List<SrmPonBidHeadersEntity_HI_RO> bidHeadersList = new ArrayList<>();
                bidHeadersList.add(k);
                List<SrmPonBidHeadersEntity_HI_RO> bidHeadersListNew = iSrmPonBidItemPrices.getTotalAccount(auctionLineIdList, bidHeadersList, subsectionFlag);
                SrmPonBidHeadersEntity_HI_RO bidHeaders = bidHeadersListNew.get(0);
                if (null != bidHeaders.getTotalAccountOffer() && !"".equals(bidHeaders.getTotalAccountOffer())) {
                    k.setTotalAccountOffer(bidHeaders.getTotalAccountOffer());
                    resultRanking.add(k);
                }
                k.setTotalAccountBiddinng(bidHeaders.getTotalAccountBiddinng());
            }
        }
        if (resultRanking.size() > 0) {//判断是否有报价金额，若有进行排序
            Collections.sort(resultRanking, new Comparator<SrmPonBidHeadersEntity_HI_RO>() {
                @Override
                public int compare(SrmPonBidHeadersEntity_HI_RO o1, SrmPonBidHeadersEntity_HI_RO o2) {
                    BigDecimal number = o1.getTotalAccountOffer().subtract(o2.getTotalAccountOffer());//升序
                    Integer num = number.compareTo(new BigDecimal(0));
                    return num;
                }
            });
        }
        for (SrmPonBidHeadersEntity_HI_RO tem : result) {
            for (Integer i = 0; i < resultRanking.size(); i++) {
                SrmPonBidHeadersEntity_HI_RO k = resultRanking.get(i);
                if (k.getBidHeaderId().equals(tem.getBidHeaderId())) {
                    tem.setTotalAccountOfferRanking(i + 1);
                    break;
                }
            }
        }

        //工程报价排序
       if("ENGINEERING".equals(jsonParams.getString("itemType"))){
           if(result.size()>0){
               List<SrmPonBidHeadersEntity_HI_RO> engineerRank=new ArrayList<>();
               for (int n=0;n<result.size();n++){
                   if(!ObjectUtils.isEmpty(result.get(n).getTotalProjectCost())){
                       engineerRank.add(result.get(n));
                   }
               }
               if(!ObjectUtils.isEmpty(engineerRank)){
                   Collections.sort(engineerRank, new Comparator<SrmPonBidHeadersEntity_HI_RO>() {
                       @Override
                       public int compare(SrmPonBidHeadersEntity_HI_RO o1, SrmPonBidHeadersEntity_HI_RO o2) {
                           BigDecimal number = o1.getTotalProjectCost().subtract(o2.getTotalProjectCost());//升序
                           Integer num = number.compareTo(new BigDecimal(0));
                           return num;
                       }
                   });
                   for (SrmPonBidHeadersEntity_HI_RO engineer : result) {
                       for (Integer i = 0; i < engineerRank.size(); i++) {
                           SrmPonBidHeadersEntity_HI_RO k = engineerRank.get(i);
                           if (k.getBidHeaderId().equals(engineer.getBidHeaderId())) {
                               engineer.setTotalProjectCostRank(i + 1);
                               break;
                           }
                       }
                   }
               }


           }
       }

        if(ObjectUtils.isEmpty(bidHeaderId)){
            //受邀请的供应商
            StringBuffer querySql = new StringBuffer(SrmPonAuctionSuppliersEntity_HI_RO.QUERY_AUCTION_SUPPLIER_LIST_SQL);
            List<SrmPonAuctionSuppliersEntity_HI_RO> supplierResultList = srmPonAuctionSuppliersDAO_HI_RO.findList(querySql, new Object[]{auctionHeaderId});
            if (null != supplierResultList && supplierResultList.size() > 0) {
                Iterator<SrmPonAuctionSuppliersEntity_HI_RO> it = supplierResultList.iterator();
                while (it.hasNext()) {
                    SrmPonAuctionSuppliersEntity_HI_RO k = it.next();
                    for (SrmPonBidHeadersEntity_HI_RO tem : result) {
                        if (null != k.getSupplierId() && null != tem.getSupplierId() && tem.getSupplierId().equals(k.getSupplierId())) {
                            it.remove();//去除已经报价的洽谈受邀请的供应商
                            break;
                        }
                    }
                }
            }
            //排除筛选后的洽谈受邀请供应商
            if (null != supplierResultList && supplierResultList.size() > 0) {
                for (SrmPonAuctionSuppliersEntity_HI_RO k : supplierResultList) {
                    SrmPonBidHeadersEntity_HI_RO entity = new SrmPonBidHeadersEntity_HI_RO();
                    entity.setAuctionHeaderId(k.getAuctionHeaderId());
                    entity.setInviteId(k.getInviteId());
                    //entity.setBidHeaderId(-1);
                    entity.setBidHeaderId(0 - k.getSupplierId());
                    entity.setSupplierId(k.getSupplierId());
                    entity.setSupplierName(k.getSupplierName());
                    entity.setSupplierContactId(k.getSupplierContactId());
                    entity.setSupplierContactName(k.getSupplierContactName());
                    entity.setSupplierContactPhone(k.getSupplierContactPhone());
                    entity.setSupplierContactEmail(k.getSupplierContactEmail());
                    entity.setSupplierSiteId(k.getSupplierSiteId());
                    entity.setNumberOfAwarded(k.getNumberOfAwarded());
                    entity.setNumberOfInvitations(k.getNumberOfInvitations());
                    entity.setBidBondPayStatus(k.getBidBondPayStatus());
                    entity.setBidBondPayStatusName(k.getBidBondPayStatusName());
                    entity.setTenderCostPayStatus(k.getTenderCostPayStatus());
                    entity.setTenderCostPayStatusName(k.getTenderCostPayStatusName());
                    result.add(entity);
                }
            }
        }
        return result;
    }

    /**
     * Description：查询供应商报价信息
     * @param params 参数
     * @return List<SrmPonBidHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<SrmPonBidHeadersEntity_HI_RO> getBidHeadersList(JSONObject params) throws Exception {
        StringBuffer querySql = new StringBuffer(SrmPonBidHeadersEntity_HI_RO.QUERY_BIDHEADERS);
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        Integer supplierId = params.getInteger("supplierId");
        if (auctionHeaderId == null) {
            return null;
        }
        querySql.append(" AND a.auction_header_id = " + auctionHeaderId + " AND a.supplier_id = " + supplierId);
        try {
            return srmPonBidHeadersDAO_HI_RO.findList(querySql, new Object[]{});
        } catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
            throw new Exception("查询供应商报价失败");
        }
    }

    /**
     * Description：计算综合得分（已报价的供应商）
     * @param bidHeaderId 报价头ID
     * @param auctionHeaderId 寻源单据ID
     * @return BigDecimal
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public BigDecimal getScoreSum(Integer bidHeaderId, Integer auctionHeaderId) {
        BigDecimal scoreSum = new BigDecimal(0);//综合得分
        List<SrmPonJuryScoreLinesEntity_HI> juryScoreLinesList = srmPonJuryScoreLinesDAO_HI.findByProperty("bidHeaderId", bidHeaderId);
        if (null != juryScoreLinesList && juryScoreLinesList.size() > 0) {
            for (SrmPonJuryScoreLinesEntity_HI k : juryScoreLinesList) {
                //计算综合得分
                Map<String, Object> mapRule = new HashMap<>();
                mapRule.put("auctionHeaderId", auctionHeaderId);
                mapRule.put("scoreRuleId", k.getScoreRuleId());
                List<SrmPonAuctionScoreRulesEntity_HI> scoreRulesList = srmPonAuctionScoreRulesDAO_HI.findByProperty(mapRule);
                if (null != scoreRulesList && scoreRulesList.size() > 0) {
                    SrmPonAuctionScoreRulesEntity_HI rulesEntity = scoreRulesList.get(0);
                    if (null != k.getScore() && !"".equals(k.getScore()) && null != rulesEntity.getPowerWeight() && !"".equals(rulesEntity.getPowerWeight())) {
                        scoreSum = scoreSum.add(rulesEntity.getPowerWeight().multiply(k.getScore()));
                    }
                }
            }
            scoreSum = scoreSum.divide(new BigDecimal("100")); //除以100
            return scoreSum;
        }
        return scoreSum;
    }

    /**
     * Description：根据auctionHeaderId查询所有有效的供应商报价头表——用于决标汇总的导出（已截止）
     * @param auctionHeaderId 寻源单据ID
     * @return List<SrmPonBidHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<SrmPonBidHeadersEntity_HI_RO> findBidHeadersList(Integer auctionHeaderId) {
        List<SrmPonBidHeadersEntity_HI_RO> bidHeadersList = null;
        StringBuffer sbBid = new StringBuffer(SrmPonBidHeadersEntity_HI_RO.QUERY_BIDHEADERSLIST_SQL);
        sbBid.append(" AND t.bid_status = 'ACT'");
        sbBid.append(" AND t.auction_header_id = " + auctionHeaderId);
        bidHeadersList = srmPonBidHeadersDAO_HI_RO.findList(sbBid.toString());
        return bidHeadersList;
    }

    /**
     * Description：存放不重复的所有报价行的洽谈行Id——用于决标汇总的导出（已截止）
     * @param bidHeadersList 报价头信息列表
     * @return Set<Integer>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public Set<Integer> findAuctionLineIdList(List<SrmPonBidHeadersEntity_HI_RO> bidHeadersList) {
        Set<Integer> auctionLineIdList = new HashSet<>();//存放不重复的所有报价行的洽谈行Id
        for (SrmPonBidHeadersEntity_HI_RO k : bidHeadersList) {
            List<SrmPonBidItemPricesEntity_HI> bidItemPricesList = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", k.getBidHeaderId());
            if (null != bidItemPricesList && bidItemPricesList.size() > 0) {
                for (SrmPonBidItemPricesEntity_HI tem : bidItemPricesList) {
                    auctionLineIdList.add(tem.getAuctionLineId());
                }
            }
        }
        return auctionLineIdList;
    }

    /**
     * Description：查询监控报价供应商
     * @param params 参数
     * @return List<Object>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<Object> getBidSupplierList(JSONObject params) {
        LOGGER.info("params:" + params.toString());
        ArrayList<Object> arrayList = new ArrayList<>();
        StringBuffer querySql = new StringBuffer(SrmPonBidHeadersEntity_HI_RO.QUERY_BID_SUPPLIER_LIST_SQL);
        querySql.append(" AND t.bid_status = 'ACT' ");
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        if (auctionHeaderId == null || "".equals(auctionHeaderId)) {
            return null;
        }
        List<SrmPonBidHeadersEntity_HI_RO> result = srmPonBidHeadersDAO_HI_RO.findList(querySql, new Object[]{auctionHeaderId});
        if (null != result && result.size() > 0) {
            for (SrmPonBidHeadersEntity_HI_RO k : result) {
                if (null == k.getAwardStatus() || "".equals(k.getAwardStatus())) {
                    k.setAwardStatusName("未中标");
                }
            }
        }

        for (int i = 0; i < result.size(); i++) {
            StringBuffer queryBidSql = new StringBuffer(SrmPonBidHeadersEntity_HI_RO.QUERY_BIDHEADERS);
            Integer supplierId = result.get(i).getSupplierId();
            String supplierName = result.get(i).getSupplierName();
            queryBidSql.append(" AND a.auction_header_id = " + auctionHeaderId + " AND a.supplier_id = " + supplierId);

            Map<String, Object> mapB = new HashMap<>();
            List<SrmPonBidHeadersEntity_HI_RO> bidList = srmPonBidHeadersDAO_HI_RO.findList(queryBidSql, new Object[]{});
            mapB.put("bidList", bidList);
            mapB.put("supplierId", supplierId);
            mapB.put("supplierName", supplierName);
            arrayList.add(mapB);
        }
        return arrayList;
    }

    /**
     * Description：删除拟定状态下的报价单据
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteBidHeaders(JSONObject params) throws Exception {
        LOGGER.info("删除参数：" + params.toString());
        try {
            if (null != params.getInteger("bidHeaderId")) {
                SrmPonBidHeadersEntity_HI headersRow = srmPonBidHeadersDAO_HI.getById(params.getInteger("bidHeaderId"));
                if (null != headersRow) {
                    List<SrmPonBidItemPricesEntity_HI> itemList = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", headersRow.getBidHeaderId());
                    if (itemList != null && itemList.size() > 0) {
                        srmPonBidItemPricesDAO_HI.delete(itemList);
                    }
                    srmPonBidHeadersDAO_HI.delete(headersRow);
                } else {
                    return SToolUtils.convertResultJSONObj("E", "未知错误", 0, null);
                }
            }
            return SToolUtils.convertResultJSONObj("S", "未知错误", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：发起议价
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject updateBidHeaderBargainFlag(JSONObject params) throws Exception {
        Integer bidHeaderId = params.getInteger("bidHeaderId");
        Integer userId = params.getInteger("varUserId");
        if (bidHeaderId == null) {
            return SToolUtils.convertResultJSONObj("E", "发起议价操作失败，该供应商没有报价", 1, null);
        }
        SrmPonBidHeadersEntity_HI srmPonBidHeadersEntity = srmPonBidHeadersDAO_HI.getById(bidHeaderId);
        if ("2".equals(srmPonBidHeadersEntity.getBargainFlag()) || "3".equals(srmPonBidHeadersEntity.getBargainFlag()) || "5".equals(srmPonBidHeadersEntity.getBargainFlag())) {
            return SToolUtils.convertResultJSONObj("E", "已发起议价，请勿重复发起", 1, null);
        }
        srmPonBidHeadersEntity.setBargainFlag("2");//待议价
        srmPonBidHeadersEntity.setBargainReason(params.getString("bargainReason"));//议价原因
        srmPonBidHeadersEntity.setOperatorUserId(userId);
        srmPonBidHeadersDAO_HI.saveOrUpdate(srmPonBidHeadersEntity);
        List<SrmPosSupplierContactsEntity_HI> contactsList = srmPosSupplierContactsDAO_HI.findByProperty("supplierId", srmPonBidHeadersEntity.getSupplierId());
        String content="<p>您好！</p><br/>" + "您有新的议价: " + srmPonBidHeadersEntity.getBidNumber() + "请及时报价，谢谢。";
        //邮件
        if (contactsList != null && contactsList.size() > 0) {
            for (int c = 0; c < contactsList.size(); c++) {
                String emailAddress = contactsList.get(c).getEmailAddress();
                //发送邮件给账号的联系人
                sendMailUtil.doSendHtmlEmail("待议价", content, new String[]{emailAddress});
            }
        }

        //发送通知
        List<SaafUsersEntity_HI> usersList = saafUsersDAO_HI.findByProperty("supplierId", srmPonBidHeadersEntity.getSupplierId());
        if (null != usersList && usersList.size() > 0) {
            SaafUsersEntity_HI usersEntity = usersList.get(0);
            iSrmBaseNotifications.insertSrmBaseNotifications("待议价", content
                    , usersEntity.getUserId(), "srm_pon_bid_headers", srmPonBidHeadersEntity.getBidHeaderId(), "bidHeaderId", "home.inquiryBargainingDetail",userId);
        }
        return SToolUtils.convertResultJSONObj("S", "议价发起成功", 1, null);
    }
}
