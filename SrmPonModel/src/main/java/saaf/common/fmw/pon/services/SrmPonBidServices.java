package saaf.common.fmw.pon.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.inter.ISrmPonBid;
import saaf.common.fmw.services.CommonAbstractServices;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Date;
import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonBidServices.java
 * Description：寻源--寻源报价信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Path("/srmPonBidServices")
@Component("srmPonBidServices")
public class SrmPonBidServices extends CommonAbstractServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonBidServices.class);

    @Autowired
    private ISrmPonBid iSrmPonBid;

    /**
     * Description：查询待报价的招标列表，发布状态且未报过价
     * @param params
     * @param pageRows
     * @param pageIndex
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("findUnbidInfo")
    public String findUnbidInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_ROWS) Integer pageRows, @FormParam(PAGE_INDEX) Integer pageIndex) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            /*if ("EX".equals(jsonParams.getString("varPlatformCode"))) { //是供应商
                jsonParams.put("supplierId", jsonParams.getIntValue("varSupplierId"));
            }*/
            return JSON.toJSONString(iSrmPonBid.getUnbidInfo(jsonParams, pageIndex, pageRows));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询异常" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：查询招标的物料--待报价
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getAuctionItemInfo")
    public String getAuctionItemInfo(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            if(null==jsonParams.getInteger("auctionHeaderId")){
                jsonParams.put("auctionHeaderId", -1);
            }
            List list  = iSrmPonBid.getAuctionItemInfo(jsonParams);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", list.size(), list);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询异常" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：查询报价头信息--报价中
     * @param params
     * @param pageRows
     * @param pageIndex
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getAuctionBidInfo")
    public String getAuctionBidInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_ROWS) Integer pageRows, @FormParam(PAGE_INDEX) Integer pageIndex) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            if ("EX".equals(jsonParams.getString("varPlatformCode"))) { //是供应商
                jsonParams.put("supplierId", jsonParams.getIntValue("varSupplierId"));
            }
            return JSON.toJSONString(iSrmPonBid.getAuctionBidInfo(jsonParams, pageIndex, pageRows));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询异常" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：待议价
     * @param params
     * @param pageRows
     * @param pageIndex
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getBargainInfo")
    public String getBargainInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_ROWS) Integer pageRows, @FormParam(PAGE_INDEX) Integer pageIndex) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            if ("EX".equals(jsonParams.getString("varPlatformCode"))) { //是供应商
                jsonParams.put("supplierId", jsonParams.getIntValue("varSupplierId"));
            }
            return JSON.toJSONString(iSrmPonBid.getBargainInfo(jsonParams, pageIndex, pageRows));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询异常" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：查询报价行信息-报价中
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getAuctionBidLineInfo")
    public String getAuctionBidLineInfo(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            List list  = iSrmPonBid.getAuctionBidLineInfo(jsonParams);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", list.size(), list);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询异常" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：查询中标总金额
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("findTotalBidAmount")
    @Produces("application/json")
    public String findTotalBidAmount(@FormParam(PARAMS) String params) {
        LOGGER.info("参数："+params.toString());
        if(StringUtils.isBlank(params)){
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
        }
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = iSrmPonBid.findTotalBidAmount(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询失败" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
        }
    }

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
    @POST
    @Path("getBidBondPayFlag")
    public String getBidBondPayFlag(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            if ("EX".equals(jsonParams.getString("varPlatformCode"))) { //是供应商
                jsonParams.put("varSupplierId", jsonParams.getIntValue("varSupplierId"));
            }
            String bidBondPayFlag = iSrmPonBid.getBidBondPayFlag(jsonParams);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", 1, bidBondPayFlag);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询异常" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常：" + e.getMessage(), 0, null);
        }
    }

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
    @POST
    @Path("findTenderCostPayFlag")
    public String findTenderCostPayFlag(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            if ("EX".equals(jsonParams.getString("varPlatformCode"))) { //是供应商
                jsonParams.put("varSupplierId", jsonParams.getIntValue("varSupplierId"));
            }
            String bidBondPayFlag = iSrmPonBid.findTenderCostPayFlag(jsonParams);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", 1, bidBondPayFlag);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询异常" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：查询招标的保证金信息
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getBidBondInfo")
    public String getBidBondInfo(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            SrmPonAuctionHeadersEntity_HI row = iSrmPonBid.getBidBondInfo(jsonParams);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", 1, row);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询异常" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：获取服务器当前时间
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getSystemTime")
    public String getSystemTime(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return CommonAbstractServices.convertResultJSONObj("S", "获取服务器当前时间成功！", 1, new Date());
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询异常" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "获取服务器当前时间异常：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：保存供应商的报价信息
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("saveBidInfo")
    public String saveBidInfo(@FormParam(PARAMS) String params) {
        try {
            JSONObject paramJSON = this.parseObject(params);
            JSONObject json = iSrmPonBid.saveBidInfo(paramJSON);
            return JSON.toJSONString(json);
        } catch (Exception e) {
            //e.printStackTrace();
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "保存失败!" + e, 0, null));
        }
    }

    /**
     * Description：保存供应商的报价信息
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("saveBargainInfo")
    public String saveBargainInfo(@FormParam(PARAMS) String params) {
        try {
            JSONObject paramJSON = this.parseObject(params);
            JSONObject json = iSrmPonBid.saveBargainInfo(paramJSON);
            return JSON.toJSONString(json);
        } catch (Exception e) {
            //e.printStackTrace();
            if (e instanceof StaleObjectStateException){
                return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "当前数据已过时，请刷新数据后重试", 0, null));
            }
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "保存失败!" + e, 0, null));
        }
    }

    /**
     * Description：通过报价头id,查询本次报价的状态和可以报价的次数
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getBidStatus")
    public String getBidStatus(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject json = iSrmPonBid.getBidStatus(jsonParams);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", 1, json);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询异常" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：通过招标id,查询供应商是否已报过价
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getBidOrNot")
    public String getBidOrNot(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject json = iSrmPonBid.getBidOrNot(jsonParams);
            return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", 1, json);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询异常" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：已发布的标书（招标），查看监控报价
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getMonitorBidPrice")
    public String getMonitorBidPrice(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmPonBid.getMonitorBidPrice(jsonParams));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查看监控报价失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：更新洽谈的结束时间
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("updateAuctionEndDate")
    public String updateAuctionEndDate(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmPonBid.updateAuctionEndDate(jsonParams));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("更新结束时间失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "更新结束时间失败：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：已发布的标书（询价），查看监控报价
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getMonitorBidPriceComp")
    public String getMonitorBidPriceComp(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmPonBid.getMonitorBidPriceComp(jsonParams));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查看监控报价失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：询价监控报价弹出框，查询供应商报价信息
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getMonitorBidPriceTable")
    public String getMonitorBidPriceTable(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmPonBid.getMonitorBidPriceTable(jsonParams));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查看监控报价失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：供应商报价统一降幅，计算
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("saveDescPerPrice")
    public String findDescPerPrice(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmPonBid.findDescPerPrice(jsonParams));
        } catch (Exception e) {
            LOGGER.error("保存报价统一降幅失败" + e, e);
            return CommonAbstractServices.convertResultJSONObj("E", "保存报价统一降幅失败：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：供应商询价，查看报价次数和当前排名
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("getBidCurrentRank")
    public String getBidCurrentRank(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmPonBid.getBidCurrentRank(jsonParams));
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查看监控报价失败" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：确认／拒绝跟价，保存
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("saveFollowPriceStatus")
    public String saveFollowPriceStatus(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmPonBid.saveFollowPriceStatus(jsonParams));
        } catch (Exception e) {
            LOGGER.error("确认／拒绝跟价失败" + e, e);
            return CommonAbstractServices.convertResultJSONObj("E", "确认／拒绝跟价失败：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：应标
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("respondTo")
    public String respondTo(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(iSrmPonBid.saveRespondTo(jsonParams));
        } catch (Exception e) {
            LOGGER.error("应标失败" + e, e);
            return CommonAbstractServices.convertResultJSONObj("E", "应标失败：" + e.getMessage(), 0, null);
        }
    }

    /**
     * Description：批量导入——供应商报价
     * @param params
     * @return java.lang.String
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       15:38 2020/2/21    zwj     	创建
     * =======================================================================
     */
    @POST
    @Path("saveBatchImportBidLineInfo")
    public String saveBatchImportBidLineInfo(@FormParam(PARAMS) String params){
        LOGGER.info("参数："+params.toString());
        UserInfoSessionBean sessionBean = getUserSessionBean();
        Integer userId = sessionBean.getUserId();
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = iSrmPonBid.saveBatchImportBidLineInfo(jsonParams,userId);
            return JSONObject.toJSONString(jsondata);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("导入失败，" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E",   "导入失败，"+e.getMessage(), 0, null);
        }
    }


}
