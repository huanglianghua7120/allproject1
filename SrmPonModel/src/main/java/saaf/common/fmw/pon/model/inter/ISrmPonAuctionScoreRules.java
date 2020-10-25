package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionScoreRulesEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionScoreRulesEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonAuctionScoreRules.java
 * Description：寻源--寻源评分规则信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonAuctionScoreRules {

    /**
     * Description：查询招标的招标规则（不分页）
     * @param params
     * @return List<SrmPonAuctionScoreRulesEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    List<SrmPonAuctionScoreRulesEntity_HI_RO> getPonAuctionScoreRulesList(JSONObject params);

    /**
     * Description：保存拟标——洽谈评分规则（招标）
     * @param ponAuctionScoreRulesList
     * @param userId
     * @param auctionHeaderId
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public void savePonAuctionScoreRulesList(List<SrmPonAuctionScoreRulesEntity_HI> ponAuctionScoreRulesList,Integer userId,Integer auctionHeaderId);

    /**
     * Description：删除洽谈评分规则——根据主键ID（单条数据）
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject deleteSrmPonAuctionScoreRuleById(JSONObject jsonParams);
}
