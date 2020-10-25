package saaf.common.fmw.pon.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionScoreRulesEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionScoreRulesEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionScoreRules;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonAuctionScoreRulesServer.java
 * Description：寻源--寻源评分规则信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonAuctionScoreRulesServer")
public class SrmPonAuctionScoreRulesServer implements ISrmPonAuctionScoreRules {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonAuctionScoreRulesServer.class);

    @Autowired
    private ViewObject<SrmPonAuctionScoreRulesEntity_HI> srmPonAuctionScoreRulesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonAuctionScoreRulesEntity_HI_RO> srmPonAuctionScoreRulesDAO_HI_RO;

    public SrmPonAuctionScoreRulesServer() {
        super();
    }

    /**
     * Description：查询招标的招标规则（不分页）
     * @param params 参数
     * @return List<SrmPonAuctionScoreRulesEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<SrmPonAuctionScoreRulesEntity_HI_RO> getPonAuctionScoreRulesList(JSONObject params) {
        LOGGER.info("参数：" + params.toString());
        StringBuffer querySql = new StringBuffer(SrmPonAuctionScoreRulesEntity_HI_RO.QUERY_AUCTION_SCORERULES_LIST_SQL);
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        if (auctionHeaderId == null || "".equals(auctionHeaderId)) {
            return null;
        }
        return srmPonAuctionScoreRulesDAO_HI_RO.findList(querySql, new Object[]{auctionHeaderId});
    }

    /**
     * Description：保存拟标——洽谈评分规则（招标）
     * @param ponAuctionScoreRulesList 评分规则信息
     * @param userId 操作者ID
     * @param auctionHeaderId 寻源单据ID
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public void savePonAuctionScoreRulesList(List<SrmPonAuctionScoreRulesEntity_HI> ponAuctionScoreRulesList, Integer userId, Integer auctionHeaderId) {
        if (null != ponAuctionScoreRulesList && ponAuctionScoreRulesList.size() > 0) {
            for (SrmPonAuctionScoreRulesEntity_HI k : ponAuctionScoreRulesList) {
                k.setAuctionHeaderId(auctionHeaderId);
                k.setOperatorUserId(userId);
            }
            srmPonAuctionScoreRulesDAO_HI.saveOrUpdateAll(ponAuctionScoreRulesList);
        }
    }

    /**
     * Description：删除洽谈评分规则——根据主键ID（单条数据）
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteSrmPonAuctionScoreRuleById(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        Integer scoreRuleId = jsonParams.getInteger("scoreRuleId");
        if (!(scoreRuleId == null || "".equals(scoreRuleId))) {
            SrmPonAuctionScoreRulesEntity_HI row = srmPonAuctionScoreRulesDAO_HI.getById(scoreRuleId);
            if (null != row) {
                srmPonAuctionScoreRulesDAO_HI.delete(scoreRuleId);
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "未知错误", 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }
}
