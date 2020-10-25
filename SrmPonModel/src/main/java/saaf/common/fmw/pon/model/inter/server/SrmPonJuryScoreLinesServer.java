package saaf.common.fmw.pon.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.entities.*;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonJuryScoreLinesEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonBidHeaders;
import saaf.common.fmw.pon.model.inter.ISrmPonJuryScoreLines;
import saaf.common.fmw.utils.SrmUtils;

import java.math.BigDecimal;
import java.util.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonJuryScoreLinesServer.java
 * Description：寻源--评分明细信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonJuryScoreLinesServer")
public class SrmPonJuryScoreLinesServer implements ISrmPonJuryScoreLines {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonJuryScoreLinesServer.class);

    @Autowired
    private ViewObject<SrmPonJuryScoreLinesEntity_HI> srmPonJuryScoreLinesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonJuryScoreLinesEntity_HI_RO> srmPonJuryScoreLinesDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPonBidHeadersEntity_HI> srmPonBidHeadersDAO_HI;//供应商报价头表

    @Autowired
    private ISrmPonBidHeaders iSrmPonBidHeaders;//供应商报价头表

    @Autowired
    private ViewObject<SrmPonAuctionScoreRulesEntity_HI> srmPonAuctionScoreRulesDAO_HI;

    @Autowired
    private ViewObject<SrmPonBidItemPricesEntity_HI> srmPonBidItemPricesDAO_HI;

    @Autowired
    private ViewObject<SrmPonJuryScoresEntity_HI> srmPonJuryScoresDAO_HI;

    public SrmPonJuryScoreLinesServer() {
        super();
    }

    /**
     * Description：查询洽谈评分明细list（不分页）——根据洽谈auctionHeaderId
     * @param auctionHeaderId 寻源单据ID
     * @return List<SrmPonJuryScoreLinesEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<SrmPonJuryScoreLinesEntity_HI_RO> findSrmPonJuryScoreLinesList(Integer auctionHeaderId, String scorePartition, Integer juryScoreId) {
        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(scorePartition)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPonJuryScoreLinesEntity_HI_RO.QUERY_JURYSCORELINESLIST_SQL);
        List<SrmPonJuryScoreLinesEntity_HI_RO> result = null;
        sb.append(" AND t.score_partition = " + scorePartition);
        sb.append(" WHERE spbh.bid_status = 'ACT' ");
        sb.append(" AND spbh.auction_header_id = " + auctionHeaderId);
        sb.append(" AND spjs.jury_score_id = " + juryScoreId);
        result = srmPonJuryScoreLinesDAO_HI_RO.findList(sb.toString());
        return result;
    }

    /**
     * Description：保存洽谈评分明细list
     * @param juryScoreLinesList 评分行信息
     * @param userId 操作者ID
     * @param juryScoreId 评分ID
     * @param scorePartition 评标分区
     * @return List<SrmPonJuryScoreLinesEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public void savePonJuryScoreLinesList(List<SrmPonJuryScoreLinesEntity_HI> juryScoreLinesList, Integer userId, Integer juryScoreId, String scorePartition) {
        if (null != juryScoreLinesList && juryScoreLinesList.size() > 0) {
            for (SrmPonJuryScoreLinesEntity_HI k : juryScoreLinesList) {
                if (k.getBidHeaderId() == null || "".equals(k.getBidHeaderId())) {
                    k.setBidHeaderId(k.getBidHeaderIdV());
                }
                k.setJuryScoreId(juryScoreId);
                if (k.getScorePartition() == null || "".equals(k.getScorePartition())) {
                    k.setScorePartition(scorePartition);
                }
                k.setOperatorUserId(userId);
            }
            srmPonJuryScoreLinesDAO_HI.saveOrUpdateAll(juryScoreLinesList);
        }
    }

    /**
     * Description：评分汇总查询——根据bidHeaderId获取一个供应商报价对应的评分明细
     * @param bidHeaderId 报价头ID
     * @return List<Object>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<Object> getJuryScoreLinesListByBidHeaderId(Integer bidHeaderId) {
        //评分区间为商务
        StringBuffer sb1 = new StringBuffer();
        sb1.append(SrmPonJuryScoreLinesEntity_HI_RO.QUERY_JURYSCORELINESBYBIDHEADERIDLIST_SQL);
        sb1.append(" WHERE t.bid_header_id = " + bidHeaderId);
        sb1.append(" AND t.score_partition = " + "1");
        List<SrmPonJuryScoreLinesEntity_HI_RO> result1 = null;
        result1 = srmPonJuryScoreLinesDAO_HI_RO.findList(sb1.toString());
        //评分区间为技术标
        StringBuffer sb2 = new StringBuffer();
        sb2.append(SrmPonJuryScoreLinesEntity_HI_RO.QUERY_JURYSCORELINESBYBIDHEADERIDLIST_SQL);
        sb2.append(" WHERE t.bid_header_id = " + bidHeaderId);
        sb2.append(" AND t.score_partition = " + "2");
        List<SrmPonJuryScoreLinesEntity_HI_RO> result2 = null;
        result2 = srmPonJuryScoreLinesDAO_HI_RO.findList(sb2.toString());

        if ((result1 == null || result1.size() == 0) && (result2 == null || result2.size() == 0)) {
            return new ArrayList<>();
        }
        List<Object> juryScoreLinesList = new ArrayList<>();
        if (result1.size() == result2.size()) {
            for (Integer i = 0; i < result1.size(); i++) {
                List<SrmPonJuryScoreLinesEntity_HI_RO> result = new ArrayList<>();
                result.add(result1.get(i));
                result.add(result2.get(i));
                juryScoreLinesList.add(result);
            }
            return juryScoreLinesList;
        }
        if (result1.size() > result2.size()) {
            Integer num = result1.size() - result2.size();
            for (Integer i = 0; i < num; i++) {
                result2.add(new SrmPonJuryScoreLinesEntity_HI_RO());
            }
            for (Integer j = 0; j < result1.size(); j++) {
                List<SrmPonJuryScoreLinesEntity_HI_RO> result = new ArrayList<>();
                result.add(result1.get(j));
                result.add(result2.get(j));
                juryScoreLinesList.add(result);
            }
            return juryScoreLinesList;
        }
        if (result1.size() < result2.size()) {
            Integer num = result2.size() - result1.size();
            for (Integer i = 0; i < num; i++) {
                result1.add(new SrmPonJuryScoreLinesEntity_HI_RO());
            }
            for (Integer j = 0; j < result2.size(); j++) {
                List<SrmPonJuryScoreLinesEntity_HI_RO> result = new ArrayList<>();
                result.add(result1.get(j));
                result.add(result2.get(j));
                juryScoreLinesList.add(result);
            }
            return juryScoreLinesList;
        }
        return new ArrayList<>();
    }

    /**
     * Description：评分汇总查询——评分明细list（不分页）
     * @param jsonParams 参数
     * @return List<Object>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<Object> findSrmPonJuryScoreLinesAll(JSONObject jsonParams) {
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId"); //洽谈Id
        Map<String, Object> map = new HashMap<>();
        map.put("auctionHeaderId", auctionHeaderId);
        map.put("bidStatus", "ACT");
        List<SrmPonBidHeadersEntity_HI> bidHeadersList = srmPonBidHeadersDAO_HI.findByProperty(map);
        if (null == bidHeadersList || bidHeadersList.size() == 0 || "".equals(bidHeadersList)) {
            return null;
        }
        ArrayList<Object> arrayList = new ArrayList<>();
        List<SrmPonBidHeadersEntity_HI> bidHeadersListRanking = new ArrayList<>();//综合得分排名
        for (SrmPonBidHeadersEntity_HI k : bidHeadersList) {
            BigDecimal scoreSum = iSrmPonBidHeaders.getScoreSum(k.getBidHeaderId(), k.getAuctionHeaderId());
            Integer number = scoreSum.compareTo(new BigDecimal(0));
            if (number > 0) {
                k.setScoreSum(scoreSum);//综合得分赋值
                bidHeadersListRanking.add(k);
            }
        }
        if (null != bidHeadersListRanking && bidHeadersListRanking.size() > 0) {
            Collections.sort(bidHeadersListRanking, new Comparator<SrmPonBidHeadersEntity_HI>() {
                @Override
                public int compare(SrmPonBidHeadersEntity_HI o1, SrmPonBidHeadersEntity_HI o2) {
                    BigDecimal number = o2.getScoreSum().subtract(o1.getScoreSum());//降序
                    Integer num = number.compareTo(new BigDecimal(0));
                    return num;
                }
            });
        }
        for (SrmPonBidHeadersEntity_HI k : bidHeadersList) {
            for (Integer i = 0; i < bidHeadersListRanking.size(); i++) {
                SrmPonBidHeadersEntity_HI tem = bidHeadersListRanking.get(i);
                if (k.getBidHeaderId().equals(tem.getBidHeaderId()) && null != tem.getScoreSum() && !"".equals(tem.getScoreSum())) {
                    k.setScoreSumRanking(i + 1);
                }
            }
            Map<String, Object> mapB = new HashMap<>();
            List<Object> juryScoreLinesList = getJuryScoreLinesListByBidHeaderId(k.getBidHeaderId());
            mapB.put("juryScoreLinesList", juryScoreLinesList);
            mapB.put("supplier", k);
            arrayList.add(mapB);
        }
        return arrayList;
    }

    /**
     * Description：招标评分汇总查询——评分明细list（不分页）
     * @param jsonParams 参数
     * @return List<Object>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<Object> findSrmPonTenderJuryScoreLinesAll(JSONObject jsonParams) {
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId"); //洽谈Id
        StringBuffer checkSorce = new StringBuffer(SrmPonJuryScoreLinesEntity_HI_RO.QUERY_SCORE_LINES_ALL_SQL);
        Map<String,Object> checkSorceMap = new HashMap<>();
        checkSorce.append(" AND spasr.auction_header_id = :auctionHeaderId ");
        checkSorce.append(" AND spasr.score_item = '价格得分' AND spjsl.score is not null ");
        checkSorceMap.put("auctionHeaderId",auctionHeaderId);
        List<SrmPonJuryScoreLinesEntity_HI_RO> checkSorceList = srmPonJuryScoreLinesDAO_HI_RO.findList(checkSorce,checkSorceMap);
        ArrayList<Object> arrayList = new ArrayList<>();
        if(checkSorceList.size()>0){
            Map<String, Object> map = new HashMap<>();
            map.put("auctionHeaderId", auctionHeaderId);
            map.put("bidStatus", "ACT");
            List<SrmPonBidHeadersEntity_HI> bidHeadersList = srmPonBidHeadersDAO_HI.findByProperty(map);
            if (null == bidHeadersList || bidHeadersList.size() == 0 || "".equals(bidHeadersList)) {
                return null;
            }
            List<SrmPonBidHeadersEntity_HI> bidHeadersListRanking = new ArrayList<>();//综合得分排名
            for (SrmPonBidHeadersEntity_HI k : bidHeadersList) {
                BigDecimal scoreSum = iSrmPonBidHeaders.getScoreSum(k.getBidHeaderId(), k.getAuctionHeaderId());
                Integer number = scoreSum.compareTo(new BigDecimal(0));
                if (number > 0) {
                    k.setScoreSum(scoreSum);//综合得分赋值
                    bidHeadersListRanking.add(k);
                }
            }
            if (null != bidHeadersListRanking && bidHeadersListRanking.size() > 0) {
                Collections.sort(bidHeadersListRanking, new Comparator<SrmPonBidHeadersEntity_HI>() {
                    @Override
                    public int compare(SrmPonBidHeadersEntity_HI o1, SrmPonBidHeadersEntity_HI o2) {
                        BigDecimal number = o2.getScoreSum().subtract(o1.getScoreSum());//降序
                        Integer num = number.compareTo(new BigDecimal(0));
                        return num;
                    }
                });
            }
            List<SrmPonAuctionScoreRulesEntity_HI> scoreRulesList = srmPonAuctionScoreRulesDAO_HI.findByProperty("auctionHeaderId",auctionHeaderId);
            for (SrmPonBidHeadersEntity_HI k : bidHeadersList) {
                for (Integer i = 0; i < bidHeadersListRanking.size(); i++) {
                    SrmPonBidHeadersEntity_HI tem = bidHeadersListRanking.get(i);
                    if (k.getBidHeaderId().equals(tem.getBidHeaderId()) && null != tem.getScoreSum() && !"".equals(tem.getScoreSum())) {
                        k.setScoreSumRanking(i + 1);
                    }
                }
                StringBuffer querySorce = new StringBuffer(SrmPonJuryScoreLinesEntity_HI_RO.QUERY_SCORE_LINES_ALL_SQL);
                Map<String,Object> querySorceMap = new HashMap<>();
                querySorce.append(" and spjsl.bid_header_id = spbh.bid_header_id ");
                querySorce.append(" AND spasr.auction_header_id = :auctionHeaderId ");
                querySorce.append(" AND spas.supplier_id = :supplierId ");
                querySorceMap.put("auctionHeaderId",auctionHeaderId);
                querySorceMap.put("supplierId",k.getSupplierId());
                List<SrmPonJuryScoreLinesEntity_HI_RO> sorceList = srmPonJuryScoreLinesDAO_HI_RO.findList(querySorce,querySorceMap);
                Map<String, Object> mapB = new HashMap<>();
                mapB.put("juryScoreLinesList", sorceList);
                mapB.put("supplier", k);
                mapB.put("scoreRulesListSize",scoreRulesList.size());
                mapB.put("showTenderScore","Y");
                arrayList.add(mapB);
            }
        }else{
            StringBuffer checkOtherSorce = new StringBuffer(SrmPonJuryScoreLinesEntity_HI_RO.QUERY_SCORE_LINES_ALL_SQL);
            Map<String,Object> checkOtherSorceMap = new HashMap<>();
            checkOtherSorce.append(" AND spasr.auction_header_id = :auctionHeaderId ");
            checkOtherSorce.append(" AND spasr.score_item <> '价格得分' ");
            checkOtherSorceMap.put("auctionHeaderId",auctionHeaderId);
            List<SrmPonJuryScoreLinesEntity_HI_RO> checkOtherSorceList = srmPonJuryScoreLinesDAO_HI_RO.findList(checkOtherSorce,checkOtherSorceMap);
            int count = 0;
            for(SrmPonJuryScoreLinesEntity_HI_RO checkOther :checkOtherSorceList){
                if(null!=checkOther.getScore()){
                    count ++;
                }
            }
            Map<String, Object> map = new HashMap<>();
            map.put("auctionHeaderId", auctionHeaderId);
            map.put("bidStatus", "ACT");
            List<SrmPonBidHeadersEntity_HI> bidHeadersList = srmPonBidHeadersDAO_HI.findByProperty(map);
            if (null == bidHeadersList || bidHeadersList.size() == 0 || "".equals(bidHeadersList)) {
                return null;
            }
            List<SrmPonAuctionScoreRulesEntity_HI> scoreRulesList = srmPonAuctionScoreRulesDAO_HI.findByProperty("auctionHeaderId",auctionHeaderId);
            for (SrmPonBidHeadersEntity_HI k : bidHeadersList) {
                StringBuffer querySorce = new StringBuffer(SrmPonJuryScoreLinesEntity_HI_RO.QUERY_SCORE_LINES_ALL_SQL);
                Map<String,Object> querySorceMap = new HashMap<>();
                querySorce.append(" and spjsl.bid_header_id = spbh.bid_header_id ");
                querySorce.append(" AND spasr.auction_header_id = :auctionHeaderId ");
                querySorce.append(" AND spas.supplier_id = :supplierId ");
                querySorceMap.put("auctionHeaderId",auctionHeaderId);
                querySorceMap.put("supplierId",k.getSupplierId());
                List<SrmPonJuryScoreLinesEntity_HI_RO> sorceList = srmPonJuryScoreLinesDAO_HI_RO.findList(querySorce,querySorceMap);
                if(sorceList.size()<=0){
                    sorceList = null;
                    StringBuffer queryFirstSorce = new StringBuffer(SrmPonJuryScoreLinesEntity_HI_RO.QUERY_SCORE_LINES_ALL_SQL);
                    Map<String,Object> queryFirstSorceMap = new HashMap<>();
                    queryFirstSorce.append(" AND spasr.auction_header_id = :auctionHeaderId ");
                    queryFirstSorce.append(" AND spas.supplier_id = :supplierId ");
                    queryFirstSorceMap.put("auctionHeaderId",auctionHeaderId);
                    queryFirstSorceMap.put("supplierId",k.getSupplierId());
                    sorceList = srmPonJuryScoreLinesDAO_HI_RO.findList(queryFirstSorce,queryFirstSorceMap);
                }
                Map<String, Object> mapB = new HashMap<>();
                mapB.put("juryScoreLinesList", sorceList);
                mapB.put("supplier", k);
                mapB.put("scoreRulesListSize",scoreRulesList.size());
                if(count == checkOtherSorceList.size()){
                    mapB.put("showTenderScore","Y");
                }else{
                    mapB.put("showTenderScore","N");
                }
                arrayList.add(mapB);
            }
        }

        return arrayList;
    }

    /**
     * Description：招标评分汇总显示价格得分
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveSrmPonTenderJuryScoreLinesPriceAll(JSONObject jsonParams) {
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId"); //洽谈Id
        Map<String, Object> map = new HashMap<>();
        map.put("auctionHeaderId", auctionHeaderId);
        map.put("bidStatus", "ACT");
        List<SrmPonBidHeadersEntity_HI> bidHeadersList = srmPonBidHeadersDAO_HI.findByProperty(map);
        if (null == bidHeadersList || bidHeadersList.size() == 0 || "".equals(bidHeadersList)) {
            return null;
        }
        BigDecimal sumPrice = new BigDecimal(0);
        double priceScore;
        for(SrmPonBidHeadersEntity_HI bidHeaders:bidHeadersList){
            List<SrmPonBidItemPricesEntity_HI> bidItemPricesList = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId",bidHeaders.getBidHeaderId());
            if(bidItemPricesList.size()>0){
                for(SrmPonBidItemPricesEntity_HI bidItemPrices:bidItemPricesList){
                    sumPrice = sumPrice.add(bidItemPrices.getNoTaxPrice().multiply(bidItemPrices.getPromisedQuantity()));
                }
            }
        }
        BigDecimal bestPrice = sumPrice.divide(new BigDecimal(bidHeadersList.size()),6,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(0.95));
        for(SrmPonBidHeadersEntity_HI bidHeaders:bidHeadersList){
            BigDecimal sumSupplierPrice = new BigDecimal(0);
            List<SrmPonBidItemPricesEntity_HI> bidItemPricesList = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId",bidHeaders.getBidHeaderId());
            if(bidItemPricesList.size()>0){
                for(SrmPonBidItemPricesEntity_HI bidItemPrices:bidItemPricesList){
                    sumSupplierPrice = sumSupplierPrice.add(bidItemPrices.getNoTaxPrice().multiply(bidItemPrices.getPromisedQuantity()));
                }
            }
            if(sumSupplierPrice.divide(bestPrice,6,BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(1))== -1){
                priceScore = (new BigDecimal(1).subtract(new BigDecimal(0.5).multiply((new BigDecimal(1).subtract(sumSupplierPrice.divide(bestPrice,6,BigDecimal.ROUND_HALF_UP)).abs())))).doubleValue()*60;
            }else{
                priceScore = (new BigDecimal(1).subtract(new BigDecimal(1).multiply((new BigDecimal(1).subtract(sumSupplierPrice.divide(bestPrice,6,BigDecimal.ROUND_HALF_UP)).abs())))).doubleValue()*60;
            }
            StringBuffer checkSorce = new StringBuffer(SrmPonJuryScoreLinesEntity_HI_RO.QUERY_SCORE_LINES_ALL_SQL);
            Map<String,Object> checkSorceMap = new HashMap<>();
            checkSorce.append(" and spjsl.bid_header_id = spbh.bid_header_id ");
            checkSorce.append(" AND spasr.auction_header_id = :auctionHeaderId ");
            checkSorce.append(" AND spasr.score_item = '价格得分' AND spas.supplier_id = :supplierId ");
            checkSorceMap.put("auctionHeaderId",auctionHeaderId);
            checkSorceMap.put("supplierId",bidHeaders.getSupplierId());
            List<SrmPonJuryScoreLinesEntity_HI_RO> checkSorceList = srmPonJuryScoreLinesDAO_HI_RO.findList(checkSorce,checkSorceMap);
            SrmPonJuryScoreLinesEntity_HI scoreLine = null;
            if(checkSorceList.size()>0){
                scoreLine = srmPonJuryScoreLinesDAO_HI.getById(checkSorceList.get(0).getJuryScoreLineId());
                if(null!=scoreLine){
                    scoreLine.setScore(new BigDecimal(priceScore).setScale(2,BigDecimal.ROUND_HALF_UP));
                    scoreLine.setOperatorUserId(1);
                    srmPonJuryScoreLinesDAO_HI.saveOrUpdate(scoreLine);
                }else{
                    scoreLine = new SrmPonJuryScoreLinesEntity_HI();
                    scoreLine.setScore(new BigDecimal(priceScore).setScale(2,BigDecimal.ROUND_HALF_UP));
                    scoreLine.setOperatorUserId(1);
                    srmPonJuryScoreLinesDAO_HI.saveOrUpdate(scoreLine);
                }
            }
        }
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, null);
    }

    /**
     * Description：招标评分汇总保存手工填写分数
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveSrmPonTenderJuryScoreLinesPrice(JSONObject jsonParams) {
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        JSONArray jurySupplierScoreLinesListJSON = null;
        if (!(jsonParams.getJSONArray("juryScoreLinesList") == null || "".equals(jsonParams.getJSONArray("juryScoreLinesList")))) {
            jurySupplierScoreLinesListJSON = jsonParams.getJSONArray("juryScoreLinesList");
            for(int i = 0;i<jurySupplierScoreLinesListJSON.size();i++){
                List<SrmPonJuryScoreLinesEntity_HI_RO> juryScoreLinesList = null;
                juryScoreLinesList = JSON.parseArray(jurySupplierScoreLinesListJSON.get(i).toString(), SrmPonJuryScoreLinesEntity_HI_RO.class);
                if(null!=juryScoreLinesList&&juryScoreLinesList.size()>0){
                    SrmPonJuryScoresEntity_HI header = null;
                    if(null==juryScoreLinesList.get(0).getJuryScoreId()){
                        header = new SrmPonJuryScoresEntity_HI();
                        header.setAuctionHeaderId(juryScoreLinesList.get(0).getAuctionHeaderId());
                        header.setJuryId(juryScoreLinesList.get(0).getJuryId());
                        header.setOperatorUserId(userId);
                        srmPonJuryScoresDAO_HI.saveOrUpdate(header);
                    }else{
                        header = srmPonJuryScoresDAO_HI.getById(juryScoreLinesList.get(0).getJuryScoreId());
                    }
                    for(SrmPonJuryScoreLinesEntity_HI_RO juryScoreLines:juryScoreLinesList){
                        if(null!= juryScoreLines){
                            SrmPonJuryScoreLinesEntity_HI line = null;
                            if(null==juryScoreLines.getJuryScoreLineId()){
                                line = new SrmPonJuryScoreLinesEntity_HI();
                                line.setJuryScoreId(header.getJuryScoreId());
                                line.setBidHeaderId(juryScoreLines.getBidHeaderId());
                                line.setScoreRuleId(juryScoreLines.getScoreRuleId());
                            }else{
                                line = srmPonJuryScoreLinesDAO_HI.getById(juryScoreLines.getJuryScoreLineId());
                            }
                            line.setOperatorUserId(userId);
                            line.setScore(juryScoreLines.getScore());
                            srmPonJuryScoreLinesDAO_HI.saveOrUpdate(line);
                        }
                    }
                }
            }
        }

        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, null);
    }
}
