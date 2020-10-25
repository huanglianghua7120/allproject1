package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionSuppliersEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionSuppliersEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonAuctionSuppliers.java
 * Description：寻源--寻源邀请供应商信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonAuctionSuppliers {

	/**
	 * Description：删除洽谈邀请供应商——根据主键ID（单条数据）
	 * @param jsonParams
	 * @return JSONObject
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public JSONObject deleteSrmPonAuctionSupplierById(JSONObject jsonParams);

	/**
	 * Description：招标的洽谈邀请供应商——选择供应商弹出框查询（分页）
	 * @param jsonParams
	 * @param pageIndex
	 * @param pageRows
	 * @return Pagination<SrmPonAuctionSuppliersEntity_HI_RO>
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	Pagination<SrmPonAuctionSuppliersEntity_HI_RO> findSrmPonAuctionSuppliersLov(JSONObject jsonParams,
			Integer pageIndex, Integer pageRows);

	/**
	 * Description：询价的洽谈邀请供应商——选择供应商弹出框查询（分页）
	 * @param jsonParams
	 * @param pageIndex
	 * @param pageRows
	 * @return Pagination<SrmPonAuctionSuppliersEntity_HI_RO>
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
    Pagination<SrmPonAuctionSuppliersEntity_HI_RO> findSrmPonAuctionSuppliersBiddingLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

	/**
	 * Description：查询被邀请的供应商（不分页）
	 * @param params
	 * @return List<SrmPonAuctionSuppliersEntity_HI_RO>
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	List<SrmPonAuctionSuppliersEntity_HI_RO> getAuctionSupplierList(JSONObject params);

	/**
	 * Description：查询供应商是否有对应保证金
	 * @param params
	 * @return java.lang.String
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	String getIsAuctionSupplier(JSONObject params);

	/**
	 * Description：保存拟标——洽谈邀请供应商（招标）
	 * @param ponAuctionSuppliersList
	 * @param userId
	 * @param auctionHeaderId
	 * @return void
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public void savePonAuctionSuppliersList(List<SrmPonAuctionSuppliersEntity_HI> ponAuctionSuppliersList, Integer userId, Integer auctionHeaderId);

	/**
	 * Description：保存保证金和标书费用
	 * @param jsonParams
	 * @return Object
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public Object saveBondPayStatus(JSONObject jsonParams) throws Exception;

	/**
	 * Description：招标的洽谈邀请供应商保证金查询
	 * @param jsonParams
	 * @param pageIndex
	 * @param pageRows
	 * @return Pagination<SrmPonAuctionSuppliersEntity_HI_RO>
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	Pagination<SrmPonAuctionSuppliersEntity_HI_RO> findSrmPonAuctionSuppliersBondList(JSONObject jsonParams,
																				 Integer pageIndex, Integer pageRows);

	/**
	 * Description：招标的供应商中标情况查询
	 * @param jsonParams
	 * @param pageIndex
	 * @param pageRows
	 * @return Pagination<SrmPonAuctionSuppliersEntity_HI_RO>
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	Pagination<SrmPonAuctionSuppliersEntity_HI_RO> findSrmPonAuctionAnalysisList(JSONObject jsonParams,
																					  Integer pageIndex, Integer pageRows);

	/**
	 * Description：保存保证金退还信息
	 * @param jsonParams
	 * @return Object
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public Object saveBondReturn(JSONObject jsonParams) throws Exception;
}
