package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionItemLaddersEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonAuctionItemLadders.java
 * Description：寻源--寻源标的物阶梯信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonAuctionItemLadders {

    /**
     * Description：删除供应商行的阶梯数量——根据阶梯数量的主键ID（单条数据）
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject deleteSrmPonAuctionItemLadderById(JSONObject jsonParams);

    /**
     * Description：查询阶梯数量（不分页）
     * @param params
     * @return List<SrmPonAuctionItemLaddersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    List<SrmPonAuctionItemLaddersEntity_HI_RO> getPonAuctionItemLaddersList(JSONObject params);

    /**
     * Description：保存阶梯数量List（需要传入标的物的行的auctionLineId,auctionHeaderId）
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject savePonAuctionItemLaddersList(JSONObject jsonParams);
}
