package saaf.common.fmw.base.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.inter.ISrmBaseNotifications;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：系统代办通知
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseNotificationsService")
@Path("/srmBaseNotificationsService")
public class SrmBaseNotificationsService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseNotificationsService.class);
    @Autowired
    private ISrmBaseNotifications iSrmBaseNotifications;

    public SrmBaseNotificationsService() {
        super();
    }

    /**
     * 标书发布查询——内部（带分页）——系统代办通知
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmBaseNotificationsByAuction")
    @Produces("application/json")
    public String findSrmBaseNotificationsByAuction(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1") Integer pageIndex,
                                                    @FormParam(PAGE_ROWS) @DefaultValue("15") Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmBaseNotifications.findSrmBaseNotificationsByAuction(jsonParams, pageIndex, pageRows));
        } catch (Exception e) {
            LOGGER.error("查询失败" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * 订单确认查询——内部（带分页）——系统代办通知
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmBaseNotificationsByPoHeader")
    @Produces("application/json")
    public String findSrmBaseNotificationsByPoHeader(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1") Integer pageIndex,
                                                     @FormParam(PAGE_ROWS) @DefaultValue("15") Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmBaseNotifications.findSrmBaseNotificationsByPoHeader(jsonParams, pageIndex, pageRows));
        } catch (Exception e) {
            LOGGER.error("查询失败：" + e.getMessage());
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * 订单反馈查询——供应商（带分页）——系统代办通知
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmBaseNotificationsByPoHeaderFeedBack")
    @Produces("application/json")
    public String findSrmBaseNotificationsByPoHeaderFeedBack(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1") Integer pageIndex,
                                                             @FormParam(PAGE_ROWS) @DefaultValue("15") Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmBaseNotifications.findSrmBaseNotificationsByPoHeaderFeedBack(jsonParams, pageIndex, pageRows));
        } catch (Exception e) {
            LOGGER.error("查询失败：" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * 送货通知确认查询——内部（带分页）——系统代办通知
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmBaseNotificationsByPoNotice")
    @Produces("application/json")
    public String findSrmBaseNotificationsByPoNotice(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1") Integer pageIndex,
                                                     @FormParam(PAGE_ROWS) @DefaultValue("15") Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmBaseNotifications.findSrmBaseNotificationsByPoNotice(jsonParams, pageIndex, pageRows));
        } catch (Exception e) {
            LOGGER.error("查询失败" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * 送货通知反馈查询——供应商（带分页）——系统代办通知
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmBaseNotificationsByPoNoticeFeedBack")
    @Produces("application/json")
    public String findSrmBaseNotificationsByPoNoticeFeedBack(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1") Integer pageIndex,
                                                             @FormParam(PAGE_ROWS) @DefaultValue("15") Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmBaseNotifications.findSrmBaseNotificationsByPoNoticeFeedBack(jsonParams, pageIndex, pageRows));
        } catch (Exception e) {
            LOGGER.error("查询失败" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * 通知查询（带分页）——系统代办通知
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmBaseNotificationList")
    @Produces("application/json")
    public String findSrmBaseNotificationList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1") Integer pageIndex,
                                              @FormParam(PAGE_ROWS) @DefaultValue("15") Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmBaseNotifications.findSrmBaseNotificationList(jsonParams, pageIndex, pageRows));
        } catch (Exception e) {
            LOGGER.error("查询失败" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * 通知查看，查看标识viewedFlag改为已读Y——系统代办通知
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("updateNotificationByViewe")
    @Produces("application/json")
    public String updateNotificationByViewe(@FormParam(PARAMS) String params) {
        LOGGER.info("参数：" + params.toString());
        if (StringUtils.isBlank(params)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = iSrmBaseNotifications.updateNotificationByViewe(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.getString(DATA));
        } catch (Exception e) {
            LOGGER.error("--------------------------->操作失败！参数：" + params.toString() + "，异常：" + e.getMessage());
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * 待报价查询——供应商（带分页）——系统代办通知
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmBaseNotificationsByBidHeader")
    @Produces("application/json")
    public String findSrmBaseNotificationsByBidHeader(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1") Integer pageIndex,
                                                      @FormParam(PAGE_ROWS) @DefaultValue("15") Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmBaseNotifications.findSrmBaseNotificationsByBidHeader(jsonParams, pageIndex, pageRows));
        } catch (Exception e) {
            LOGGER.error("查询失败" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * 待议价查询——供应商（带分页）——系统代办通知
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findSrmBaseNotificationsByBargain")
    @Produces("application/json")
    public String findSrmBaseNotificationsByBargain(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1") Integer pageIndex,
                                                      @FormParam(PAGE_ROWS) @DefaultValue("15") Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmBaseNotifications.findSrmBaseNotificationsByBargain(jsonParams, pageIndex, pageRows));
        } catch (Exception e) {
            LOGGER.error("查询失败" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * 查询资质预警信息（带分页）
     *
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findWarningList")
    @Produces("application/json")
    public String findWarningList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1") Integer pageIndex,
                                  @FormParam(PAGE_ROWS) @DefaultValue("15") Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmBaseNotifications.findWarningList(jsonParams, pageIndex, pageRows));
        } catch (Exception e) {
            LOGGER.error("查询失败" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * 查询资质预警未处理条数
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("findCountWarningList")
    @Produces("application/json")
    public String findCountWarningList(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmBaseNotifications.findCountWarningList(jsonParams));
        } catch (Exception e) {
            LOGGER.error("查询失败" + e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * 更改资质预警信息状态（已完成操作）
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("updateWarning")
    public String updateWarning(@FormParam("params") String params) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            JSONObject posJson = iSrmBaseNotifications.updateWarning(jsonParam);
            return CommonAbstractServices.convertResultJSONObj(
                    posJson.getString("status"), posJson.getString("msg"),
                    posJson.getInteger("count"), posJson.get("data"));
        } catch (Exception e) {
            LOGGER.error("操作失败！" + e, e);
            if (e instanceof UtilsException) {
                return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
            }
            return CommonAbstractServices.convertResultJSONObj("E",
                    "未知错误", 0, null);
        }
    }
}
