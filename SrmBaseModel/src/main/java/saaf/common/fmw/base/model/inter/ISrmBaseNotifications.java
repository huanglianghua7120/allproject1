package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseNotificationsEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeadersEntity_HI_RO;
import saaf.common.fmw.po.model.entities.readonly.SrmPoNoticeEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：系统代办
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmBaseNotifications {
    /**
     * 通知插入insert——系统代办通知
     * @param menuType
     * @param notificationContent
     * @param receiverId
     * @param tableName
     * @param tableId
     * @param tableIdName
     * @param functionUrl
     * @param userId
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public String insertSrmBaseNotifications(String menuType,String notificationContent,Integer receiverId,String tableName,Integer tableId,String tableIdName,String functionUrl,Integer userId);
    /**
     * 标书发布查询——内部（带分页）——系统代办通知
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPonAuctionHeadersEntity_HI_RO> findSrmBaseNotificationsByAuction(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

    /**
     * 订单确认查询——内部（带分页）——系统代办通知
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPoHeadersEntity_HI_RO> findSrmBaseNotificationsByPoHeader(JSONObject jsonParams, Integer pageIndex, Integer pageRows);
    /**
     * 订单反馈查询——供应商（带分页）——系统代办通知
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPoHeadersEntity_HI_RO> findSrmBaseNotificationsByPoHeaderFeedBack(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

    /**
     * 送货通知确认查询——内部（带分页）——系统代办通知
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPoNoticeEntity_HI_RO> findSrmBaseNotificationsByPoNotice(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

    /**
     * 送货通知反馈查询——供应商（带分页）——系统代办通知
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPoNoticeEntity_HI_RO> findSrmBaseNotificationsByPoNoticeFeedBack(JSONObject jsonParams, Integer pageIndex, Integer pageRows);
    /**
     * 通知查询（带分页）——系统代办通知
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmBaseNotificationsEntity_HI_RO> findSrmBaseNotificationList(JSONObject jsonParams, Integer pageIndex, Integer pageRows);
    /**
     * 通知查看，查看标识viewedFlag改为已读Y——系统代办通知
     * @param jsonParams
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public JSONObject updateNotificationByViewe(JSONObject jsonParams);

    /**
     * 待报价查询（带分页）——供应商——系统代办通知
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPonBidEntity_HI_RO> findSrmBaseNotificationsByBidHeader(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

    /**
     * 待议价查询（带分页）——供应商——系统代办通知
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPonBidEntity_HI_RO> findSrmBaseNotificationsByBargain(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

    /**
     * 查询资质预警信息（带分页）
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmBaseNotificationsEntity_HI_RO> findWarningList(JSONObject params, Integer pageIndex, Integer pageRows);

    /**
     * 查询资质预警未处理条数
     * @param params
     * @return
     * @throws Exception
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmBaseNotificationsEntity_HI_RO> findCountWarningList(JSONObject params) throws Exception;

    /**
     * 更改资质预警信息状态（已完成操作）
     * @param params
     * @return
     * @throws Exception
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    JSONObject updateWarning(JSONObject params) throws Exception;
}

