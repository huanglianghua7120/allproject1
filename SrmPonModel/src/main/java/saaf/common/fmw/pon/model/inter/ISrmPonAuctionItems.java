package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionItemsEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionItemsEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidItemPricesEntity_HI_RO;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonAuctionItems.java
 * Description：寻源--寻源标的物信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonAuctionItems {

    /**
     * Description：根据传入的字符串判断是不是整数，是整数就返回true，否则为false
     * @param str
     * @return boolean
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public boolean isInteger(String str);

    /**
     * Description：根据传入的字符串判断是不是正浮点数，是正浮点数就返回true，否则为false
     * @param str
     * @return boolean
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public boolean isPositiveNumber(String str);

    /**
     * Description：判断日期格式是否正确，且日期范围是否正确
     * @param date
     * @return boolean
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public boolean isDate(String date);

    /**
     * Description： 删除洽谈行（同时删除对应的供应商行的阶梯数量的数据）——根据主键ID（单条数据）
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject deleteSrmPonAuctionItemById(JSONObject jsonParams);

    /**
     * Description： 查询标的物（不分页）
     * @param params
     * @return List<SrmPonAuctionItemsEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public List<SrmPonAuctionItemsEntity_HI_RO> getAuctionItemsList(JSONObject params);

    /**
     * Description： 查询标的物（根据物料编码）
     * @param params
     * @return List<SrmPonAuctionItemsEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public List<SrmPonAuctionItemsEntity_HI_RO> getAuctionItemsListByItemCode(JSONObject params);

    /**
     * Description： 保存拟标洽谈行==标的物（招标）
     * @param ponAuctionItemsList
     * @param userId
     * @param auctionHeaderId
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public void savePonAuctionItemsList(List<SrmPonAuctionItemsEntity_HI> ponAuctionItemsList,Integer userId,Integer auctionHeaderId);

    /**
     * Description： 批量导入——洽谈行数据==标的物
     * @param jsonParams
     * @param userId
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject saveBatchImportPonAuctionItems(JSONObject jsonParams,Integer userId);

    /**
     * Description： 批量导入——询价-网站参考价
     * @param jsonParams
     * @param userId
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject saveBatchImportPonWebReferencePrice(JSONObject jsonParams,Integer userId);

    /**
     * Description：导入校验——洽谈行数据==标的物
     * @param jsonArray
     * @param info
     * @param userId
     * @return JSONArray
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONArray checkArray(JSONArray jsonArray,JSONObject info,Integer userId);

    /**
     * Description：导入校验——询价-网站参考价
     * @param jsonArray
     * @param info
     * @return JSONArray
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONArray checkWebArray(JSONArray jsonArray,JSONObject info);

    /**
     * Description：登录界面招标公告详情行
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return Pagination<SrmPonAuctionItemsEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public Pagination<SrmPonAuctionItemsEntity_HI_RO> findAuctionListLines(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：导入模板下载
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return Pagination<SrmPonBidItemPricesEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    Pagination<SrmPonBidItemPricesEntity_HI_RO> findAuctionItemsPriceList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;


    /**
     * Description：批量删除洽谈行（同时删除对应的供应商行的阶梯数量的数据）
     * @param jsonParams
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-07-07          SIE 谢晓霞    	创建
     * =======================================================================
     */
    public JSONObject deleteSrmPonAuctionItem(JSONObject jsonParams) throws Exception;

}
