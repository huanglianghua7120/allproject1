package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;

import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionSuppliersEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonJuryScoreLinesEntity_HI_RO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonBidHeaders.java
 * Description：寻源--寻源报价头信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonBidHeaders {

    /**
     * Description：查询供应商报价头表（不分页）——带有报价总价与报价总价排名（用于已截止）
     * @param jsonParams
     * @return List<SrmPonBidHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    List<SrmPonBidHeadersEntity_HI_RO> findBidHeadersSupplierList(JSONObject jsonParams);

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
    SrmPonBidHeadersEntity_HI_RO findBidHeadersSupplier(JSONObject jsonParams);

    /**
     * Description：供应商报价头表中标（已截止）
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject updateBidHeaderSupplierAwardStatus(JSONObject jsonParams);

    /**
     * Description：删除
     * @param params
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject deleteBidHeaders(JSONObject params) throws Exception;

    /**
     * Description：查询供应商报价（不分页）
     * @param params
     * @return List<SrmPonBidHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    List<SrmPonBidHeadersEntity_HI_RO> getBidHeadersList(JSONObject params) throws Exception;

    /**
     * Description：计算综合得分（已报价的供应商）
     * @param bidHeaderId
     * @param auctionHeaderId
     * @return BigDecimal
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public BigDecimal getScoreSum(Integer bidHeaderId,Integer auctionHeaderId);

    /**
     * Description：根据auctionHeaderId查询所有有效的供应商报价头表——用于决标汇总的导出（已截止）
     * @param auctionHeaderId
     * @return List<SrmPonBidHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public List<SrmPonBidHeadersEntity_HI_RO> findBidHeadersList(Integer auctionHeaderId);

    /**
     * Description：存放不重复的所有报价行的洽谈行Id——用于决标汇总的导出（已截止）
     * @param bidHeadersList
     * @return Set<Integer>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public Set<Integer> findAuctionLineIdList(List<SrmPonBidHeadersEntity_HI_RO> bidHeadersList);

    /**
     * Description：查询监控报价供应商
     * @param params
     * @return List<Object>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    List<Object> getBidSupplierList(JSONObject params);

    /**
     * Description：发起议价
     * @param params
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject updateBidHeaderBargainFlag(JSONObject params) throws Exception;
}
