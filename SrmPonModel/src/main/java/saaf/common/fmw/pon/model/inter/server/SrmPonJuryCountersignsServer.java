package saaf.common.fmw.pon.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.entities.SrmPonJuryCountersignsEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonJuryCountersignsEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonJuryCountersigns;
import saaf.common.fmw.pon.model.inter.ISrmPonJuryScores;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonJuryCountersignsServer.java
 * Description：寻源--会签信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonJuryCountersignsServer")
public class SrmPonJuryCountersignsServer implements ISrmPonJuryCountersigns {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonJuryCountersignsServer.class);

    @Autowired
    private ViewObject<SrmPonJuryCountersignsEntity_HI> srmPonJuryCountersignsDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonJuryCountersignsEntity_HI_RO> srmPonJuryCountersignsDAO_HI_RO;

    @Autowired
    private ISrmPonJuryScores iSrmPonJuryScores;//洽谈评分头表

    public SrmPonJuryCountersignsServer() {
        super();
    }

    /**
     * Description：找出最大的轮次
     * @param juryCountersignsList 会签信息列表
     * @return Integer
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public Integer getMaxRound(List<SrmPonJuryCountersignsEntity_HI> juryCountersignsList) {
        Integer maxRound = 0;
        if (null == juryCountersignsList || juryCountersignsList.size() == 0) {
            return 1;
        }
        maxRound = juryCountersignsList.get(0).getRound();
        for (SrmPonJuryCountersignsEntity_HI k : juryCountersignsList) {
            if (!(k.getRound() == null || "".equals(k.getRound()))) {
                if (maxRound < k.getRound()) {
                    maxRound = k.getRound();
                }
            }
        }
        return maxRound;
    }

    /**
     * Description：会签查询（不分页）——已截止
     * @param jsonParams 参数
     * @return List<SrmPonJuryCountersignsEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<SrmPonJuryCountersignsEntity_HI_RO> findSrmPonJuryCountersignsList(JSONObject jsonParams) {
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId"); //洽谈Id
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPonJuryCountersignsEntity_HI_RO.QUERY_JURYCOUNTERSIGNSLIST_SQL);
        sb.append(" AND t.auction_header_id=" + auctionHeaderId);
        sb.append(" ORDER BY t.jury_countersign_id ASC ");
        List<SrmPonJuryCountersignsEntity_HI_RO> juryCountersignsList = srmPonJuryCountersignsDAO_HI_RO.findList(sb);
        if (null != juryCountersignsList && juryCountersignsList.size() > 0) {
            for (SrmPonJuryCountersignsEntity_HI_RO k : juryCountersignsList) {
                k.setRoundName("第" + k.getRound() + "轮会签");
                if ("AGREE".equals(k.getCountersignOpinion())) {
                    k.setCountersignOpinionName("同意");
                } else if ("REJECT".equals(k.getCountersignOpinion())) {
                    k.setCountersignOpinionName("拒绝");
                }
            }
        }
        return juryCountersignsList;
    }

    /**
     * Description：会签的发起、同意、拒绝按钮操作（已截止）
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveJuryCountersigns(JSONObject jsonParams) {
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId"); //洽谈Id
        Integer juryId = jsonParams.getInteger("juryId");//评标小组Id
        Integer juryIdV = jsonParams.getInteger("juryIdV");
        Integer juryIdVV = jsonParams.getInteger("juryIdVV");
        String action = jsonParams.getString("action");
        if (juryId == null || "".equals(juryId)) {
            return SToolUtils.convertResultJSONObj("E", "评标小组Id为" + juryId, 0, null);
        }
        if (auctionHeaderId == null || "".equals(auctionHeaderId)) {
            return SToolUtils.convertResultJSONObj("E", "洽谈Id为" + auctionHeaderId, 0, null);
        }
        if (null == action || "".equals(action)) {
            return SToolUtils.convertResultJSONObj("E", "操作字符为空！", 0, null);
        }
        List<SrmPonJuryCountersignsEntity_HI> juryCountersignsList = srmPonJuryCountersignsDAO_HI.findByProperty("auctionHeaderId", auctionHeaderId);
        Integer maxRound = 0;//最大轮次
        jsonData.put("auctionHeaderId", auctionHeaderId);
        SrmPonJuryCountersignsEntity_HI juryCountersignsEntity = new SrmPonJuryCountersignsEntity_HI();
        juryCountersignsEntity.setAuctionHeaderId(auctionHeaderId);
        juryCountersignsEntity.setJuryId(juryId);
        juryCountersignsEntity.setOperatorUserId(userId);
        String result = "";
        if ("START".equals(action)) {
            boolean flag = iSrmPonJuryScores.findJudgePonJuryScoresAll(auctionHeaderId);//判断评分是否全部完成，全部完成为true
            if (!flag) {
                return SToolUtils.convertResultJSONObj("E", "评分全部完成才可以发起会签", 0, null);
            }
            if (null == juryCountersignsList || "".equals(juryCountersignsList) || juryCountersignsList.size() == 0) {
                juryCountersignsEntity.setRound(1);
                juryCountersignsEntity.setCountersignOpinion("AGREE");
                srmPonJuryCountersignsDAO_HI.saveEntity(juryCountersignsEntity);
                jsonData.put("juryCountersignId", juryCountersignsEntity.getJuryCountersignId());
                if (!(juryIdV == null || "".equals(juryIdV) || juryIdV == -1)) {
                    SrmPonJuryCountersignsEntity_HI juryCountersignsEntityV = new SrmPonJuryCountersignsEntity_HI();
                    juryCountersignsEntityV.setAuctionHeaderId(auctionHeaderId);
                    juryCountersignsEntityV.setJuryId(juryIdV);
                    juryCountersignsEntityV.setOperatorUserId(userId);
                    juryCountersignsEntityV.setRound(1);
                    juryCountersignsEntityV.setCountersignOpinion("AGREE");
                    srmPonJuryCountersignsDAO_HI.saveEntity(juryCountersignsEntityV);
                    jsonData.put("juryCountersignIdV", juryCountersignsEntityV.getJuryCountersignId());
                    result = "，您具有多种职责,会出现会签多次";
                }
                if (!(juryIdVV == null || "".equals(juryIdVV) || juryIdVV == -1)) {
                    SrmPonJuryCountersignsEntity_HI juryCountersignsEntityVV = new SrmPonJuryCountersignsEntity_HI();
                    juryCountersignsEntityVV.setAuctionHeaderId(auctionHeaderId);
                    juryCountersignsEntityVV.setJuryId(juryIdVV);
                    juryCountersignsEntityVV.setOperatorUserId(userId);
                    juryCountersignsEntityVV.setRound(1);
                    juryCountersignsEntityVV.setCountersignOpinion("AGREE");
                    srmPonJuryCountersignsDAO_HI.saveEntity(juryCountersignsEntityVV);
                    jsonData.put("juryCountersignIdVV", juryCountersignsEntityVV.getJuryCountersignId());
                    result = "，您具有多种职责,会出现会签多次";
                }
            } else {
                maxRound = getMaxRound(juryCountersignsList) + 1;
                juryCountersignsEntity.setRound(maxRound);
                juryCountersignsEntity.setCountersignOpinion("AGREE");
                srmPonJuryCountersignsDAO_HI.saveEntity(juryCountersignsEntity);
                jsonData.put("juryCountersignId", juryCountersignsEntity.getJuryCountersignId());
                if (!(juryIdV == null || "".equals(juryIdV) || juryIdV == -1)) {
                    SrmPonJuryCountersignsEntity_HI juryCountersignsEntityV = new SrmPonJuryCountersignsEntity_HI();
                    juryCountersignsEntityV.setAuctionHeaderId(auctionHeaderId);
                    juryCountersignsEntityV.setJuryId(juryIdV);
                    juryCountersignsEntityV.setOperatorUserId(userId);
                    juryCountersignsEntityV.setRound(maxRound);
                    juryCountersignsEntityV.setCountersignOpinion("AGREE");
                    srmPonJuryCountersignsDAO_HI.saveEntity(juryCountersignsEntityV);
                    jsonData.put("juryCountersignIdV", juryCountersignsEntityV.getJuryCountersignId());
                    result = "，您具有多种职责,会出现会签多次";
                }
                if (!(juryIdVV == null || "".equals(juryIdVV) || juryIdVV == -1)) {
                    SrmPonJuryCountersignsEntity_HI juryCountersignsEntityVV = new SrmPonJuryCountersignsEntity_HI();
                    juryCountersignsEntityVV.setAuctionHeaderId(auctionHeaderId);
                    juryCountersignsEntityVV.setJuryId(juryIdVV);
                    juryCountersignsEntityVV.setOperatorUserId(userId);
                    juryCountersignsEntityVV.setRound(maxRound);
                    juryCountersignsEntityVV.setCountersignOpinion("AGREE");
                    srmPonJuryCountersignsDAO_HI.saveEntity(juryCountersignsEntityVV);
                    jsonData.put("juryCountersignIdVV", juryCountersignsEntityVV.getJuryCountersignId());
                    result = "，您具有多种职责,会出现会签多次";
                }
            }
            return SToolUtils.convertResultJSONObj("S", "发起成功" + result, 1, jsonData);
        } else if ("AGREE".equals(action)) {
            maxRound = getMaxRound(juryCountersignsList);
            juryCountersignsEntity.setRound(maxRound);
            juryCountersignsEntity.setCountersignOpinion(action);
            srmPonJuryCountersignsDAO_HI.saveEntity(juryCountersignsEntity);
            jsonData.put("juryCountersignId", juryCountersignsEntity.getJuryCountersignId());
            if (!(juryIdV == null || "".equals(juryIdV) || juryIdV == -1)) {
                SrmPonJuryCountersignsEntity_HI juryCountersignsEntityV = new SrmPonJuryCountersignsEntity_HI();
                juryCountersignsEntityV.setAuctionHeaderId(auctionHeaderId);
                juryCountersignsEntityV.setJuryId(juryIdV);
                juryCountersignsEntityV.setOperatorUserId(userId);
                juryCountersignsEntityV.setRound(maxRound);
                juryCountersignsEntityV.setCountersignOpinion(action);
                srmPonJuryCountersignsDAO_HI.saveEntity(juryCountersignsEntityV);
                jsonData.put("juryCountersignIdV", juryCountersignsEntityV.getJuryCountersignId());
                result = "您具有商务标与技术标两种职责,会出现会签两次，";
            }

            return SToolUtils.convertResultJSONObj("S", result + "同意成功", 1, jsonData);
        } else if ("REJECT".equals(action)) {
            maxRound = getMaxRound(juryCountersignsList);
            juryCountersignsEntity.setRound(maxRound);
            juryCountersignsEntity.setCountersignOpinion(action);
            srmPonJuryCountersignsDAO_HI.saveEntity(juryCountersignsEntity);
            jsonData.put("juryCountersignId", juryCountersignsEntity.getJuryCountersignId());
            if (!(juryIdV == null || "".equals(juryIdV) || juryIdV == -1)) {
                SrmPonJuryCountersignsEntity_HI juryCountersignsEntityV = new SrmPonJuryCountersignsEntity_HI();
                juryCountersignsEntityV.setAuctionHeaderId(auctionHeaderId);
                juryCountersignsEntityV.setJuryId(juryIdV);
                juryCountersignsEntityV.setOperatorUserId(userId);
                juryCountersignsEntityV.setRound(maxRound);
                juryCountersignsEntityV.setCountersignOpinion(action);
                srmPonJuryCountersignsDAO_HI.saveEntity(juryCountersignsEntityV);
                jsonData.put("juryCountersignIdV", juryCountersignsEntityV.getJuryCountersignId());
                result = "您具有商务标与技术标两种职责,会出现会签两次，";
            }
            return SToolUtils.convertResultJSONObj("S", result + "拒绝成功", 1, jsonData);
        }
        return SToolUtils.convertResultJSONObj("E", "没有操作！", 0, null);
    }
}
