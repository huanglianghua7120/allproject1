package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonJuryScores.java
 * Description：寻源--评分信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonJuryScores {

    /**
     * Description：查询单条的洽谈评分及其子表的洽谈评分明细list
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject findSrmPonJuryScoresInfo(JSONObject jsonParams);

    /**
     * Description：保存洽谈评分及其评分明细（已截止的评分）
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject savePonJuryScoresAll(JSONObject jsonParams);

    /**
     * Description：判断协助小组的评分人是否全部评分
     * @param auctionHeaderId
     * @return boolean
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public boolean findJudgePonJuryScoresAll(Integer auctionHeaderId);
}
