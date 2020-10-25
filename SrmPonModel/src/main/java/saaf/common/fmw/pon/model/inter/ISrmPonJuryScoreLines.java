package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;
import saaf.common.fmw.pon.model.entities.SrmPonJuryScoreLinesEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonJuryScoreLinesEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonJuryScoreLines.java
 * Description：寻源--评分明细信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonJuryScoreLines {

    /**
     * Description：查询洽谈评分明细list（不分页）——根据洽谈auctionHeaderId
     * @param auctionHeaderId
     * @param scorePartition
     * @param juryScoreId
     * @return List<SrmPonJuryScoreLinesEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public List<SrmPonJuryScoreLinesEntity_HI_RO> findSrmPonJuryScoreLinesList(Integer auctionHeaderId,String scorePartition,Integer juryScoreId);

    /**
     * Description：保存洽谈评分明细list
     * @param juryScoreLinesList
     * @param userId
     * @param juryScoreId
     * @param scorePartition
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public void savePonJuryScoreLinesList(List<SrmPonJuryScoreLinesEntity_HI> juryScoreLinesList,Integer userId,Integer juryScoreId,String scorePartition);

    /**
     * Description：评分汇总查询——评分明细list（不分页）
     * @param jsonParams
     * @return List<Object>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public List<Object> findSrmPonJuryScoreLinesAll(JSONObject jsonParams);

    /**
     * Description：招标评分汇总查询——评分明细list（不分页）
     * @param jsonParams
     * @return List<Object>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public List<Object> findSrmPonTenderJuryScoreLinesAll(JSONObject jsonParams);

    /**
     * Description：招标评分汇总显示价格得分
     * @param jsonParams
     * @return List<Object>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    /*public List<Object> findSrmPonTenderJuryScoreLinesPriceAll(JSONObject jsonParams);*/

    /**
     * Description：招标评分汇总计算价格得分
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject saveSrmPonTenderJuryScoreLinesPriceAll(JSONObject jsonParams);

    /**
     * Description：招标评分汇总保存手工填写分数
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject saveSrmPonTenderJuryScoreLinesPrice(JSONObject jsonParams);

    /**
     * Description：评分汇总查询——根据bidHeaderId获取一个供应商报价对应的评分明细
     * @param bidHeaderId
     * @return List<Object>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public List<Object> getJuryScoreLinesListByBidHeaderId(Integer bidHeaderId);
}
