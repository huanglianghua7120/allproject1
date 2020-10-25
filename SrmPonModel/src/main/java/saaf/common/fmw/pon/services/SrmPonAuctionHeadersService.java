package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;

import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionItemsEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidItemPricesEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionHeaders;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionItems;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonAuctionHeadersService.java
 * Description：寻源--寻源单据头信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonAuctionHeadersService")
@Path("/srmPonAuctionHeadersService")
public class SrmPonAuctionHeadersService extends CommonAbstractServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonAuctionHeadersService.class);
    @Autowired
    private ISrmPonAuctionHeaders iSrmPonAuctionHeaders;
    @Autowired
    private ISrmPonAuctionItems iSrmPonAuctionItems;

    public SrmPonAuctionHeadersService() {
        super();
    }

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
    @POST
    @Path("getDateTime")
    public String getDateTime() {
        try {
            return CommonAbstractServices.convertResultJSONObj("S", "获取时间成功", 1, iSrmPonAuctionHeaders.getDateTime());
        } catch (Exception e) {
            return CommonAbstractServices.convertResultJSONObj("E", "获取时间失败", 0, null);

        }
    }

    /**
     * Description：保存洽谈头层及其子表信息（招标）
     * @param params 参数
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("savePonAuctionHeadersAll")
    @Produces("application/json")
    public String savePonAuctionHeadersAll(@FormParam(PARAMS) String params) {
        LOGGER.info("参数：" + params.toString());
        if (StringUtils.isBlank(params)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可操作！", 0, null);
        }
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = iSrmPonAuctionHeaders.savePonAuctionHeadersAll(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.getString(DATA));
        } catch (Exception e) {
            LOGGER.error("--------------------------->操作失败！参数：" + params.toString() + "，异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "操作失败！", 0, null);
        }
    }

    /**
     * Description：保存洽谈头层及其子表信息（询价）
     * @param params 参数
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("savePonAuctionHeadersAllBidding")
    @Produces("application/json")
    public String savePonAuctionHeadersAllBidding(@FormParam(PARAMS) String params) {
        LOGGER.info("参数：" + params.toString());
        if (StringUtils.isBlank(params)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可操作！", 0, null);
        }
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = iSrmPonAuctionHeaders.savePonAuctionHeadersAllBidding(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.getString(DATA));
        } catch (Exception e) {
             LOGGER.error("--------------------------->操作失败！参数：" + params.toString() + "，异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "操作失败！" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：确定开标、发起评分等操作（已截止）
     * @param params 参数
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("updateAuctionHeaderByAction")
    @Produces("application/json")
    public String updateAuctionHeaderOpenBid(@FormParam(PARAMS) String params) {
        LOGGER.info("参数：" + params.toString());
        if (StringUtils.isBlank(params)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可操作！", 0, null);
        }
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = iSrmPonAuctionHeaders.updateAuctionHeaderByAction(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.getString(DATA));
        } catch (Exception e) {
            LOGGER.error("--------------------------->操作失败！参数：" + params.toString() + "，异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "操作失败！" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：模拟OA流程的审批、拒绝按钮——拟标、已截止
     * @param params 参数
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("updatePonAuctionHeadersApprove")
    @Produces("application/json")
    public String updatePonAuctionHeadersApprove(@FormParam(PARAMS) String params) {
        LOGGER.info("参数：" + params.toString());
        if (StringUtils.isBlank(params)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可操作！", 0, null);
        }
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = iSrmPonAuctionHeaders.updatePonAuctionHeadersApprove(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.getString(DATA));
        } catch (Exception e) {
            LOGGER.error("--------------------------->操作失败！参数：" + params.toString() + "，异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "操作失败！" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：查询拟标头(洽谈头表信息 lizheng)
     * @param params 参数
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getAuctionHeaderInfo")
    public String getAuctionHeaderInfo(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmPonAuctionHeaders.getAuctionHeaderInfo(jsonParams));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询拟标头失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败", 0, null);
        }
    }

    /**
     * Description：获取草稿箱
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("findDraftsList")
    public String findDraftsList(@FormParam(PARAMS) String params,
                                 @FormParam(PAGE_INDEX) Integer pageIndex,
                                 @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmPonAuctionHeaders.findDraftsList(jsonParams, pageIndex, pageRows));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询草稿箱失败：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败！", 0, null);
        }
    }

    /**
     * Description：根据状态获取已发布，已截至，已完成的过程监控
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("findOtherList")
    public String findOtherList(@FormParam(PARAMS) String params,
                                @FormParam(PAGE_INDEX) Integer pageIndex,
                                @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmPonAuctionHeaders.findOtherList(jsonParams, pageIndex, pageRows));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询已发布失败：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败！", 0, null);
        }
    }

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
    @POST
    @Path("saveListStatus")
    public String saveListStatus(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return iSrmPonAuctionHeaders.saveListStatus(jsonParams);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    /**
     * Description：删除头部
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("deleteAuctionHeader")
    public String deleteAuctionHeader(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmPonAuctionHeaders.deleteAuctionHeader(jsonParams));
        } catch (Exception e) {
            LOGGER.error("删除草稿箱失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E",
                    "操作失败：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：创建新一轮
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("saveNewAuction")
    public String saveNewAuction(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return iSrmPonAuctionHeaders.saveNewAcution(jsonParams).toJSONString();
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("创建新一轮" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
        }
    }


    /**
     * Description：调整标书时间
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("updateEndTime")
    public String updateEndTime(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return iSrmPonAuctionHeaders.updateEndTime(jsonParams).toJSONString();
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("调整标书时间" + e);
            return CommonAbstractServices.convertResultJSONObj("E",
                    "操作失败：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：获取报价的供应商
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getBidSupplierList")
    public String getBidSupplierList(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmPonAuctionHeaders.getBidSupplierList(jsonParams));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询报价的供应商失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：决标汇总导出
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("findUserAuthorization")
    public String findUserAuthorization(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            SrmPonAuctionHeadersEntity_HI_RO auctionEntityHiRo = iSrmPonAuctionHeaders.getUserAuthorization(jsonParams);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", 1, auctionEntityHiRo);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("决标汇总导出失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：查询轮次编号下了框
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getAuctionByRound")
    public List<SrmPonAuctionHeadersEntity_HI_RO> getAuctionByRound(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            List<SrmPonAuctionHeadersEntity_HI_RO> auctionEntityHiRos = iSrmPonAuctionHeaders.getAuctionByRound(jsonParams);
            return auctionEntityHiRos;
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询轮次编号失败" + e);
            return null;
        }
    }

    /**
     * Description：复制标书
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("saveCopyAcution")
    public String saveCopyAcution(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return iSrmPonAuctionHeaders.saveCopyAcution(jsonParams)
                    .toJSONString();
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("复制标书" + e);
            return CommonAbstractServices.convertResultJSONObj("E",
                    "操作失败：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：登录界面招标公告列表
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("findAuctionList")
    public String findAuctionList(@FormParam("params") String params, @FormParam("pageIndex") Integer pageIndex, @FormParam("pageRows") Integer pageRows) {
        try {
            JSONObject jsonParam = null;
            Pagination<SrmPonAuctionHeadersEntity_HI_RO> list = iSrmPonAuctionHeaders.findAuctionList(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(list);
        } catch (Exception e) {
            return CommonAbstractServices.convertResultJSONObj("E",
                    "操作失败：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：登录界面招标公告详情头
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("findAuctionListById")
    public String findAuctionListById(@FormParam("params") String params) {
        LOGGER.info(params);
        try {
            Pagination<SrmPonAuctionHeadersEntity_HI_RO> list = null;
            JSONObject jsonParam = JSON.parseObject(params);
            list = iSrmPonAuctionHeaders.findAuctionList(jsonParam, 1, 10);
            return JSON.toJSONString(list);
        } catch (Exception e) {
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
        }
    }

    /**
     * Description：登录界面招标公告详情行
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("findAuctionListLines")
    public String findAuctionListLines(@FormParam("params") String params, @FormParam("pageIndex") Integer pageIndex, @FormParam("pageRows") Integer pageRows) {
        LOGGER.info(params);
        try {
            JSONObject jsonParam = JSON.parseObject(params);
            Pagination<SrmPonAuctionItemsEntity_HI_RO> list = iSrmPonAuctionItems.findAuctionListLines(jsonParam, pageIndex, pageRows);
            return JSON.toJSONString(list);
        } catch (Exception e) {
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
        }
    }

    /**
     * Description：导入模板下载
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("importPonItemPriceModuleExcel")
    public String importPonItemPriceModuleExcel(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
                                            @FormParam(PAGE_ROWS) Integer pageRows) {
        LOGGER.info("Excel导出，" + pageIndex + ",pageSize:" + pageRows + ",params:" + params.toString());
        if (StringUtils.isBlank(params)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可导出！", 0, null);
        }
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info(jsonParam.toString());
            Pagination<SrmPonBidItemPricesEntity_HI_RO> resultStr = iSrmPonAuctionItems.findAuctionItemsPriceList(jsonParam, pageIndex,pageRows);
            LOGGER.info(resultStr.toString());
            return JSON.toJSONString(resultStr);
        } catch (Exception e) {
            //e.printStackTrace();
            return CommonAbstractServices.convertResultJSONObj("E", e.getMessage(), 0, null);
        }
    }
}
