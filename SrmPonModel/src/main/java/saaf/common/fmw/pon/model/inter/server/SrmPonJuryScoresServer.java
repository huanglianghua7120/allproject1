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
import saaf.common.fmw.pon.model.entities.readonly.SrmPonJuryScoreLinesEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonJuryScoresEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonJuryScoreLines;
import saaf.common.fmw.pon.model.inter.ISrmPonJuryScores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonJuryScoresServer.java
 * Description：寻源--评分信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonJuryScoresServer")
public class SrmPonJuryScoresServer implements ISrmPonJuryScores {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonJuryScoresServer.class);

    @Autowired
    private ViewObject<SrmPonJuryScoresEntity_HI> srmPonJuryScoresDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonJuryScoresEntity_HI_RO> srmPonJuryScoresDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPonAuctionScoreRulesEntity_HI> srmPonAuctionScoreRulesDAO_HI; //洽谈评分规则

    @Autowired
    private ISrmPonJuryScoreLines iSrmPonJuryScoreLines; //洽谈评分明细

    @Autowired
    private ViewObject<SrmPonBidHeadersEntity_HI> srmPonBidHeadersDAO_HI;//供应商报价头表

    @Autowired
    private ViewObject<SrmPonAuctionJuriesEntity_HI> srmPonAuctionJuriesDAO_HI;//协助小组

    @Autowired
    private ViewObject<SrmPonJuryScoreLinesEntity_HI> srmPonJuryScoreLinesDAO_HI;//洽谈评分明细

    public SrmPonJuryScoresServer() {
        super();
    }

    /**
     * Description：查询单条的洽谈评分及其子表的洽谈评分明细list
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject findSrmPonJuryScoresInfo(JSONObject jsonParams) {
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId"); //洽谈Id
        Integer juryId = jsonParams.getInteger("juryId");//评标小组Id
        String scorePartition = jsonParams.getString("scorePartition");//评标分区
        if (juryId == null || "".equals(juryId)) {
            return SToolUtils.convertResultJSONObj("E", "评标小组Id为" + juryId, 0, null);
        }
        if (auctionHeaderId == null || "".equals(auctionHeaderId)) {
            return SToolUtils.convertResultJSONObj("E", "洽谈Id为" + auctionHeaderId, 0, null);
        }
        JSONObject jsonData = new JSONObject();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPonJuryScoresEntity_HI_RO.QUERY_JURYSCORESLIST_SQL);
        sb.append(" AND t.auction_header_id = " + auctionHeaderId);
        sb.append(" AND t.jury_id = " + juryId);
        List<SrmPonJuryScoresEntity_HI_RO> list = srmPonJuryScoresDAO_HI_RO.findList(sb.toString());
        if (null == list || "".equals(list) || list.size() == 0) {
            SrmPonJuryScoresEntity_HI_RO juryScoresEntityRO = new SrmPonJuryScoresEntity_HI_RO();
            juryScoresEntityRO.setAuctionHeaderId(auctionHeaderId);
            juryScoresEntityRO.setJuryId(juryId);
            jsonData.put("juryScoresEntity", juryScoresEntityRO);
            Map<String, Object> map = new HashMap<>();
            map.put("auctionHeaderId", auctionHeaderId);
            map.put("bidStatus", "ACT");
            List<SrmPonBidHeadersEntity_HI> bidHeadersList = srmPonBidHeadersDAO_HI.findByProperty(map);//查出有效的供应商报价头表
            List<SrmPonJuryScoreLinesEntity_HI_RO> juryScoreLinesList = new ArrayList<>();
            if (null != bidHeadersList && bidHeadersList.size() > 0) {
                for (SrmPonBidHeadersEntity_HI k : bidHeadersList) {
                    SrmPonJuryScoreLinesEntity_HI_RO juryScoreLinesEntity = new SrmPonJuryScoreLinesEntity_HI_RO();
                    juryScoreLinesEntity.setSupplierName(k.getSupplierName());
                    juryScoreLinesEntity.setSupplierId(k.getSupplierId());
                    juryScoreLinesEntity.setBidHeaderId(k.getBidHeaderId());
                    juryScoreLinesEntity.setBidHeaderIdV(k.getBidHeaderId());
                    juryScoreLinesEntity.setBidStatus(k.getBidStatus());
                    juryScoreLinesEntity.setAuctionHeaderId(k.getAuctionHeaderId());
                    juryScoreLinesList.add(juryScoreLinesEntity);
                }
                jsonData.put("juryScoreLinesList", juryScoreLinesList);
            }
        } else {
            SrmPonJuryScoresEntity_HI_RO juryScoresEntityRO = list.get(0);
            jsonData.put("juryScoresEntity", juryScoresEntityRO);
            List<SrmPonJuryScoreLinesEntity_HI_RO> juryScoreLinesList = iSrmPonJuryScoreLines.findSrmPonJuryScoreLinesList(auctionHeaderId, scorePartition, juryScoresEntityRO.getJuryScoreId());
            jsonData.put("juryScoreLinesList", juryScoreLinesList);
        }

        List<SrmPonAuctionScoreRulesEntity_HI> rulesEntityList = srmPonAuctionScoreRulesDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
        if (null != rulesEntityList && rulesEntityList.size() > 0) {
            for (SrmPonAuctionScoreRulesEntity_HI k : rulesEntityList) {
                if (scorePartition.equals(k.getScorePartition())) {
                    SrmPonAuctionScoreRulesEntity_HI rulesEntity = rulesEntityList.get(0);
                    jsonData.put("scoreRuleId", rulesEntity.getScoreRuleId());
                    jsonData.put("maxScore", rulesEntity.getMaxScore()); //查出最高分
                    break;
                }
            }
        }
        return SToolUtils.convertResultJSONObj("S", "查询成功", 1, jsonData);
    }

    /**
     * Description：保存洽谈评分及其评分明细（已截止的评分）
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject savePonJuryScoresAll(JSONObject jsonParams) {
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        String scorePartition = jsonParams.getString("scorePartition");//评标分区
        SrmPonJuryScoresEntity_HI juryScoresEntity = JSON.parseObject(jsonParams.toJSONString(), SrmPonJuryScoresEntity_HI.class);
        List<SrmPonJuryScoreLinesEntity_HI> juryScoreLinesList = null;
        if (!(jsonParams.getJSONArray("juryScoreLinesList") == null || "".equals(jsonParams.getJSONArray("juryScoreLinesList")))) {
            JSONArray juryScoreLinesListJSON = jsonParams.getJSONArray("juryScoreLinesList");
            juryScoreLinesList = JSON.parseArray(juryScoreLinesListJSON.toJSONString(), SrmPonJuryScoreLinesEntity_HI.class);
        }
        juryScoresEntity.setOperatorUserId(userId);
        srmPonJuryScoresDAO_HI.saveEntity(juryScoresEntity);
        iSrmPonJuryScoreLines.savePonJuryScoreLinesList(juryScoreLinesList, userId, juryScoresEntity.getJuryScoreId(), scorePartition);
        jsonData.put("juryScoreId", juryScoresEntity.getJuryScoreId());
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, jsonData);
    }

    /**
     * Description：判断协助小组的评分人是否全部评分
     * @param auctionHeaderId 寻源单据ID
     * @return boolean
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public boolean findJudgePonJuryScoresAll(Integer auctionHeaderId) {
        boolean result = true;//默认为true
        List<SrmPonAuctionJuriesEntity_HI> ponAuctionJuriesList = srmPonAuctionJuriesDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);//洽谈协助小组
        if (null == ponAuctionJuriesList || ponAuctionJuriesList.size() == 0 || "".equals(ponAuctionJuriesList)) {
            return result;
        }
        Map<String, Object> mapV = new HashMap<>();
        mapV.put("auctionHeaderId", auctionHeaderId);
        mapV.put("bidStatus", "ACT");
        List<SrmPonBidHeadersEntity_HI> bidHeadersList = srmPonBidHeadersDAO_HI.findByProperty(mapV);//查出有效的供应商报价头表
        if (null == bidHeadersList || bidHeadersList.size() == 0 || "".equals(bidHeadersList)) {//没有有效的供应商报价
            return result;
        }
        boolean flag = false;
        for (SrmPonAuctionJuriesEntity_HI tem : ponAuctionJuriesList) {//洽谈评分头表
            //商务标3，技术标4
            if ("3".equals(tem.getUserDuty()) || "4".equals(tem.getUserDuty())) {
                Map<String, Object> map = new HashMap<>();
                map.put("auctionHeaderId", auctionHeaderId);
                map.put("juryId", tem.getJuryId());
                List<SrmPonJuryScoresEntity_HI> juryScoresList = srmPonJuryScoresDAO_HI.findByProperty(map);
                if (null == juryScoresList || juryScoresList.size() == 0 || "".equals(juryScoresList)) {
                    flag = true;//存在评分未完成的现象
                    return false;
                } else {
                    for (SrmPonJuryScoresEntity_HI k : juryScoresList) {
                        List<SrmPonJuryScoreLinesEntity_HI> juryScoreLinesList = srmPonJuryScoreLinesDAO_HI.findByProperty("juryScoreId", k.getJuryScoreId());//洽谈评分明细
                        if (null == juryScoreLinesList || juryScoreLinesList.size() == 0 || "".equals(juryScoreLinesList)) {
                            flag = true;
                            return false;
                        } else {
                            for (SrmPonJuryScoreLinesEntity_HI h : juryScoreLinesList) {
                                if (null == h.getScore() || "".equals(h.getScore())) {//评分明细未填写得分
                                    flag = true;
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!flag) {
            result = true;
            return result;
        }
        return result;
    }
}
