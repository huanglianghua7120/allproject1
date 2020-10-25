package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonBid.java
 * Description：寻源--寻源报价信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonBid {

    /**
     * Description：查询待报价的招标列表，发布状态且未报过价
     * @param params
     * @param pageRows
     * @param pageIndex
     * @return Pagination<SrmPonBidEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    Pagination<SrmPonBidEntity_HI_RO> getUnbidInfo(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：查询招标的物料--待报价
     * @param params
     * @return List<SrmPonBidEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    List<SrmPonBidEntity_HI_RO> getAuctionItemInfo(JSONObject params) throws Exception;

    /**
     * Description：查询报价头信息--报价中
     * @param params
     * @param pageRows
     * @param pageIndex
     * @return Pagination<SrmPonBidEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    Pagination<SrmPonBidEntity_HI_RO> getAuctionBidInfo(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：查询报价行信息-报价中
     * @param params
     * @return List<SrmPonBidEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    List<SrmPonBidEntity_HI_RO> getAuctionBidLineInfo(JSONObject params) throws Exception;

    /**
     * Description：查询供应商保证金的缴纳状态
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    String getBidBondPayFlag(JSONObject params) throws Exception;

    /**
     * Description：查询招标的保证金信息
     * @param params
     * @return SrmPonAuctionHeadersEntity_HI
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    SrmPonAuctionHeadersEntity_HI getBidBondInfo(JSONObject params) throws Exception;

    /**
     * Description：保存供应商的报价信息
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject saveBidInfo(JSONObject jsonParams) throws Exception;

    /**
     * Description：保存供应商的议价信息
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject saveBargainInfo(JSONObject jsonParams) throws Exception;

    /**
     * Description：通过报价头id,查询本次报价的状态和可以报价的次数
     * @param params
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject getBidStatus(JSONObject params) throws Exception;

    /**
     * Description：通过招标id,查询供应商是否已报过价
     * @param params
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject getBidOrNot(JSONObject params) throws Exception;

    /**
     * Description：已发布的标书（招标），查看监控报价
     * @param params
     * @return Pagination<SrmPonBidEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    Pagination<SrmPonBidEntity_HI_RO> getMonitorBidPrice(JSONObject params) throws Exception;

    /**
     * Description：更新洽谈的结束时间
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject updateAuctionEndDate(JSONObject jsonParams) throws Exception;

    /**
     * Description：已发布的标书（询价），查看监控报价
     * @param params
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject getMonitorBidPriceComp(JSONObject params) throws Exception;

    /**
     * Description：询价监控报价弹出框，查询供应商报价信息
     * @param params
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject getMonitorBidPriceTable(JSONObject params) throws Exception;

    /**
     * Description：供应商报价统一降幅，计算
     * @param JSONObject
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject findDescPerPrice(JSONObject JSONObject);

    /**
     * Description：供应商询价，查看报价次数和当前排名
     * @param params
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject getBidCurrentRank(JSONObject params) throws Exception;

    /**
     * Description：确认／拒绝跟价，保存
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject saveFollowPriceStatus(JSONObject jsonParams) throws Exception;

    /**
     * Description：查询中标总金额
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject findTotalBidAmount(JSONObject jsonParams);

    /**
     * Description：查询供应商标书费用的缴纳状态
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    String findTenderCostPayFlag(JSONObject params) throws Exception;

    /**
     * Description：应标
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject saveRespondTo(JSONObject jsonParams) throws Exception;

    /**
     * Description：获取待议价信息
     * @param params
     * @param pageRows
     * @param pageIndex
     * @return Pagination<SrmPonBidEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    Pagination<SrmPonBidEntity_HI_RO> getBargainInfo(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：批量导入——供应商报价
     * @param jsonParams
     * @param userId
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject saveBatchImportBidLineInfo(JSONObject jsonParams,Integer userId) throws Exception;
}
