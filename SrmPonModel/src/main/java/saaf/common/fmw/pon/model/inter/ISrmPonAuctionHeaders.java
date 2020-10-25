package saaf.common.fmw.pon.model.inter;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionHeadersEntity_HI_RO;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonAuctionHeaders.java
 * Description：寻源--寻源单据头信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonAuctionHeaders {

    /**
     * Description： 创建最新版的洽谈编号
     * ZB-yymmdd+流水编码（四位流水号）——招标
     * XJ-yymmdd+流水编码（四位流水号）——询价
     * @param flagName——标识（ZB或XJ）
     * @return String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public String getNewMaxAuctionNumber(String flagName);

    /**
     * Description： 获取最新版的洽谈轮次编号（ZB-yyMMdd+4位流水号-轮次号/XJ-yyMMdd+4位流水号-轮次号）
     * 需要传入洽谈编号（ZB-yyMMdd+4位流水号/XJ-yyMMdd+4位流水号）
     * @param auctionNumber
     * @return String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public String getNewRoundAuctionNumber(String auctionNumber);

    /**
     * Description： 根据auctionHeaderId查询洽谈头表——用于决标汇总的导出（已截止）
     * @param auctionHeaderId
     * @return SrmPonAuctionHeadersEntity_HI
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public SrmPonAuctionHeadersEntity_HI findSrmPonAuctionHeaders(Integer auctionHeaderId);

    /**
     * Description：保存洽谈头层及其子表信息（招标）
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject savePonAuctionHeadersAll(JSONObject jsonParams) throws Exception;

    /**
     * Description：保存洽谈头层及其子表信息（询价）
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject savePonAuctionHeadersAllBidding(JSONObject jsonParams);

    /**
     * Description：确定开标、发起评分等操作（已截止）
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject updateAuctionHeaderByAction(JSONObject jsonParams);

    /**
     * Description：模拟OA流程的审批、拒绝按钮——拟标、已截止
     * @param jsonParams
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    public JSONObject updatePonAuctionHeadersApprove(JSONObject jsonParams);

    /**
     * Description：查询拟标头信息
     * @param params 参数
     * @return SrmPonAuctionHeadersEntity_HI_RO
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    SrmPonAuctionHeadersEntity_HI_RO getAuctionHeaderInfo(JSONObject params) throws Exception;

    /**
     * Description：查询草稿箱
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return Pagination<SrmPonAuctionHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    Pagination<SrmPonAuctionHeadersEntity_HI_RO> findDraftsList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：根据状态查询已发布、 已完成、 已截至的过程监控
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return Pagination<SrmPonAuctionHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    Pagination<SrmPonAuctionHeadersEntity_HI_RO> findOtherList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：将过时的已发布改成已截止
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    String saveListStatus(JSONObject params ) throws Exception;

    /**
     * Description：批量删除
     * @param params
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject deleteAuctionHeader(JSONObject params) throws Exception;

    /**
     * Description：新建一轮
     * @param params
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject saveNewAcution(JSONObject params) throws Exception;

    /**
     * Description：调整标书时间
     * @param params
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject updateEndTime(JSONObject params) throws Exception;

    /**
     * Description：获取当前系统时间
     * @param
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    String getDateTime();

    /**
     * Description：根据物料行id获取报价的供应商id
     * @param params
     * @return List<SrmPonAuctionHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    List<SrmPonAuctionHeadersEntity_HI_RO> getBidSupplierList(JSONObject params)throws Exception;

    /**
     * Description：获取当前用户进入决标的操作权限
     * @param params
     * @return SrmPonAuctionHeadersEntity_HI_RO
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    SrmPonAuctionHeadersEntity_HI_RO getUserAuthorization(JSONObject params)throws Exception;

    /**
     * Description：查询轮次编号下了框
     * @param jsonParams
     * @return List<SrmPonAuctionHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    List<SrmPonAuctionHeadersEntity_HI_RO> getAuctionByRound(JSONObject jsonParams)throws Exception;

    /**
     * Description：复制标书
     * @param params
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    JSONObject saveCopyAcution(JSONObject params) throws Exception;

    /**
     * Description：获取已发布且截至报价时间，在指定时间段内的洽谈列表
     * @param startDate
     * @param endDate
     * @return List<SrmPonAuctionHeadersEntity_HI>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    List<SrmPonAuctionHeadersEntity_HI> findAuctionToClose(Date startDate, Date endDate);

    /**
     * Description：根据指定的标书，截至报价
     * @param auctionHeaderId
     * @return String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    String closeBidding(Integer auctionHeaderId);

    /**
     * Description：登录界面招标公告详情
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return Pagination<SrmPonAuctionHeadersEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    Pagination<SrmPonAuctionHeadersEntity_HI_RO> findAuctionList(JSONObject params,Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：寻源自动截标定时任务,每五分钟运行一次
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-03-19     zwj             创建
     * ==============================================================================
     */
    public Integer updatePonAuctionHeaderJob()throws Exception;
}
