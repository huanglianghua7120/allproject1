package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import saaf.common.fmw.pon.model.entities.SrmPonBidHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonBidItemPricesEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidItemPricesEntity_HI_RO;

import java.util.List;
import java.util.Set;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonBidItemPrices.java
 * Description：寻源--寻源报价物料行信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonBidItemPrices {

	/**
	 * Description：报价中——供应商，单个价格降幅判断
	 * @param jsonParams
	 * @return JSONObject
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public JSONObject findBidItemPricesByNoTaxPrice(JSONObject jsonParams);

	/**
	 * Description：已截止的保存操作（保存、提交按钮，包括保存中标份额、决标意见）
	 * @param jsonParams
	 * @return JSONObject
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public JSONObject saveAwardProportionAll(JSONObject jsonParams) throws Exception;

	/**
	 * Description：保存供应商标报价行list
	 * @param userId
	 * @param auctionHeaderId
	 * @param ponBidItemPricesList
	 * @return void
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public void savePonBidItemPricesList(Integer userId, Integer auctionHeaderId, List<SrmPonBidItemPricesEntity_HI> ponBidItemPricesList, JSONArray awardProportionListJSON);

	/**
	 * Description：查询供应商报价行list（不分页）——已截止
	 * @param params
	 * @return JSONObject
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public JSONObject findSrmPonBidItemPricesList(JSONObject params);

	public JSONObject findSrmPonActionItemPricesList(JSONObject params);

	/**
	 * Description：会签的模态框的中标记录——已截止
	 * @param jsonParams
	 * @return JSONObject
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public JSONObject findSrmPonBidItemPricesAll(JSONObject jsonParams);

	/**
	 * Description：根据供应商报价行来判断，该标的物——物料编码是否中标，用于获取供应商中标记录（会签的模态框的中标记录——已截止）
	 * @param auctionHeaderId
	 * @param auctionLineId
	 * @param bidHeadersList
	 * @return String[]
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public String[] getAwardStatusName(Integer auctionHeaderId,Integer auctionLineId,List<SrmPonBidHeadersEntity_HI> bidHeadersList);

	/**
	 * Description：决标汇总模态框的查询（已截止）
	 * @param jsonParams
	 * @return JSONObject
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public JSONObject findSrmPonBidItemPricesAllSummary(JSONObject jsonParams);

	/**
	 * Description：决标汇总模态框的查询（已截止）——获取供应商报价行的不含税单价，并且判断是否中标
	 * @param auctionHeaderId
	 * @param auctionLineId
	 * @param itemLadderId
	 * @param subsectionFlag
	 * @param bidHeadersList
	 * @return Object[]
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public Object[] getNoTaxPrice(Integer auctionHeaderId,Integer auctionLineId,Integer itemLadderId,String subsectionFlag,List<SrmPonBidHeadersEntity_HI_RO> bidHeadersList);

	/**
	 * Description：决标汇总模态框的查询（已截止）——获取供应商报价头表的报价总计金额与中标总计金额
	 * @param auctionLineIdList
	 * @param bidHeadersList
	 * @param subsectionFlag
	 * @return List<SrmPonBidHeadersEntity_HI_RO>
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public List<SrmPonBidHeadersEntity_HI_RO> getTotalAccount(Set<Integer> auctionLineIdList,List<SrmPonBidHeadersEntity_HI_RO> bidHeadersList, String subsectionFlag);

	/**
	 * Description：标的物的中标按钮=====供应商报价行的中标操作
	 * @param jsonParams
	 * @return JSONObject
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public JSONObject updatePonBidItemPriceAwardStatus(JSONObject jsonParams);

	/**
	 * Description：报价供应商中标按钮（已截止）——标的物中标状态改变，即是供应商报价行的中标状态变动
	 * @param userId
	 * @param auctionHeaderId
	 * @param bidHeaderId
	 * @param awardStatus
	 * @param subsectionFlag
	 * @return void
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public void updateBidItemPriceAwardStatusByBidHeaderSupplier(Integer userId,Integer auctionHeaderId,Integer bidHeaderId,String awardStatus,String subsectionFlag);

	/**
	 * Description：获取标的物的信息，根据auctionLineId，auctionHeaderId，bidHeaderId，flag——用于决标汇总的导出（已截止）
	 * @param auctionLineId
	 * @param auctionHeaderId
	 * @param bidHeadersList
	 * @param flag
	 * @return List<SrmPonBidItemPricesEntity_HI_RO>
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public List<SrmPonBidItemPricesEntity_HI_RO> findBidItemPricesList(Integer auctionLineId,Integer auctionHeaderId,List<SrmPonBidHeadersEntity_HI_RO> bidHeadersList,boolean flag);

	/**
	 * Description：查询供应商报价信息（定价）
	 * @param jsonParams
	 * @return JSONObject
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public JSONObject findSupplierBidPrice(JSONObject jsonParams);
    /**
     * Description：提交招标询价发送至EKP系统
     *
     * @param auctionHeaderId
     * @param userId
     * @return
     * @throws Exception
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-05-29     xiexiaoxia             创建
     * ==============================================================================
     */
    JSONObject savePonAuctionToEkp(Integer auctionHeaderId, Integer userId,JSONObject params) throws Exception;
    public JSONObject saveEkpStatus() throws Exception;
}
