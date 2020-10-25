package saaf.common.fmw.pon.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseNotificationsEntity_HI;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pon.model.entities.*;
import saaf.common.fmw.pon.model.entities.readonly.*;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionItems;
import saaf.common.fmw.pon.model.inter.ISrmPonBid;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionItemLaddersEntity_HI;
import saaf.common.fmw.pon.model.inter.ISrmPonBidItemPrices;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierContactsEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonBidServer.java
 * Description：寻源--寻源报价信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonBidServer")
public class SrmPonBidServer implements ISrmPonBid {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonBidServer.class);

    @Autowired
    private ViewObject<SrmPosSupplierContactsEntity_HI> supplierContactsDAO_HI;

    @Autowired
    private ViewObject<SrmPonAuctionHeadersEntity_HI> auctionHeadersDAO_HI;

    @Autowired
    private ViewObject<SrmPonAuctionItemsEntity_HI> auctionItemsDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonAuctionItemsEntity_HI_RO> srmPonAuctionItemsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPonAuctionSuppliersEntity_HI> auctionSuppliersDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonAuctionSuppliersEntity_HI_RO> auctionSuppliersDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> supplierDAO_HI;

    @Autowired
    private ViewObject<SrmPonBidHeadersEntity_HI> srmPonBidHeadersDAO_HI;

    @Autowired
    private ViewObject<SrmPonBidItemPricesEntity_HI> srmPonBidItemPricesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonBidItemPricesEntity_HI_RO> srmPonBidItemPricesDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPonBidEntity_HI_RO> srmPonBidDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPonBidHeadersEntity_HI_RO> srmPonBidHeadersDAO_HI_RO;//供应商报价头表

    @Autowired
    private ViewObject<SrmPonBidHeadersEntity_HI> srmPonBidHeadersDao_HI;//供应商报价头表

    @Autowired
    private ViewObject<SrmPonAuctionItemLaddersEntity_HI> srmPonAuctionItemLaddersDAO_HI;//阶梯数量表

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;

    @Autowired
    private ISrmPonBidItemPrices iSrmPonBidItemPrices;

    @Autowired
    private ISrmPonAuctionItems iSrmPonAuctionItems;

    @Autowired
    private ViewObject<SrmBaseNotificationsEntity_HI> srmBaseNotificationsDAO_HI;//系统通知表

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;//规则编号生成

    /**
     * Description：查询待报价的招标列表，发布状态且未报过价
     * @param params 参数
     * @param pageRows 起始页
     * @param pageIndex 行数
     * @return Pagination<SrmPonBidEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public Pagination<SrmPonBidEntity_HI_RO> getUnbidInfo(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap();
        try {
            Integer supplierId = params.getInteger("varSupplierId");
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPonBidEntity_HI_RO.QUERY_UNBID_SQL);
            SaafToolUtils.parperParam(params, "ah.auctionHeaderId", "auctionHeaderId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(params, "ah.auctionNumber", "auctionNumber", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(params, "ah.auctionTitle", "auctionTitle", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(params, "ah.auctionType", "auctionType", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(params, "ah.auctionStatusNo", "auctionStatus", queryString, queryParamMap, "=");
            if (supplierId != null) {
                queryParamMap.put("supplierId", supplierId);
            } else {
                queryParamMap.put("supplierId", -1);
            }
            if ("EX".equals(params.getString("varPlatformCode"))) {
                queryString.append(" AND    NOT EXISTS (SELECT 1\n" +
                                   "         FROM   srm_pon_bid_headers spbh\n" +
                                   "         WHERE  spbh.auction_header_id = ah.auctionheaderid\n" +
                                   "         AND    spbh.supplier_id = " + supplierId + ")\n");
            }

            if ("SAAF".equals(params.getString("varPlatformCode"))) {
                queryString.append(" AND NOT EXISTS (\r\n" +
                        "SELECT 1 FROM srm_pon_bid_headers spbh\r\n" +
                        "WHERE spbh.auction_header_id = ah.auctionHeaderId\r\n" +
                        "AND spbh.supplier_id = ah.supplierId )\r\n");
            }

            if ("EX".equals(params.getString("varPlatformCode"))) {
                queryParamMap.put("varSupplierId", supplierId);
            } else {
                queryParamMap.put("varSupplierId", -1);
            }

            if ("EX".equals(params.getString("varPlatformCode"))) {
                queryString.append(" AND ((ah.invitingBidWay = '1' AND ah.supplierId = " + supplierId + " ) OR ah.invitingBidWay = '2' )\r\n ");
            }
            String countSql = "select count(1) from (" + queryString + ")";
            // 排序
            queryString.append(" ORDER BY ah.bidStartDate DESC");
            System.out.println(queryString);
            return srmPonBidDAO_HI_RO.findPagination(queryString,countSql, queryParamMap, pageIndex, pageRows);
        } catch (Exception e) {
            throw new Exception("查询失败");
        }
    }

    /**
     * Description：查询招标的物料--待报价
     * @param params 参数
     * @return List<SrmPonBidEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public List<SrmPonBidEntity_HI_RO> getAuctionItemInfo(JSONObject params) throws Exception {
        Map<String, Object> queryParamMap = new HashMap();
        try {
            StringBuffer queryString = new StringBuffer();
            List<SrmPonBidEntity_HI_RO> headerList = null;
            List<SrmPonBidEntity_HI_RO> priceList = null;
            //阶梯数量
            if ("Y".equals(params.getString("subsectionFlag"))) {
                queryString.append(SrmPonBidEntity_HI_RO.QUERY_AUCTION_ITEM_LADDER_SQL);
            } else {
                queryString.append(SrmPonBidEntity_HI_RO.QUERY_AUCTION_ITEM_SQL);
            }
            SaafToolUtils.parperParam(params, "spai.auction_header_id", "auctionHeaderId", queryString, queryParamMap, "=");
            headerList = srmPonBidDAO_HI_RO.findList(queryString, queryParamMap);
            for (SrmPonBidEntity_HI_RO line : headerList) {
                ArrayList<BigDecimal> priceArr = new ArrayList();
                StringBuffer querylines = new StringBuffer();
                querylines.append(SrmPonBidEntity_HI_RO.QUERY_ALL_SUPPLIER_PRICE);
                querylines.append(" AND y.auction_line_id =" + line.getAuctionLineId());
                if (null != line.getItemId()) {
                    querylines.append(" AND y.item_id = " + line.getItemId());
                }
                querylines.append(" AND y.bid_header_id IN ( SELECT bid_header_id FROM srm_pon_bid_headers t WHERE t.bid_status = 'ACT' and t.auction_header_id =" + line.getAuctionHeaderId() + " )");
                if (null != line.getItemLadderId()) {
                    querylines.append(" AND y.item_ladder_id = " + line.getItemLadderId());
                }
                priceList = srmPonBidDAO_HI_RO.findList(querylines, queryParamMap);
                if (priceList.size() > 0) {
                    for (int i = 0; i < priceList.size(); i++) {
                        priceArr.add(priceList.get(i).getNoTaxPrice());
                    }
                    BigDecimal minPrice = Collections.min(priceArr);
                    line.setRoundMinPrice(minPrice);
                }
            }

            if ("INQUIRY".equals(headerList.get(0).getAuctionType()) && null != headerList.get(0).getLastRound()) {//洽谈类型为询价且上一轮不为空，查询初始价
                //List<SrmPonAuctionItemsEntity_HI> lastItemList = auctionItemsDAO_HI.findByProperty("auctionHeaderId", headerList.get(0).getLastRound());//上一轮的洽谈行
                Integer supplierId = params.getInteger("varSupplierId");//当前登陆的供应商
                Map<String, Object> map = new HashMap();
                map.put("auctionHeaderId", headerList.get(0).getLastRound());//上一轮洽谈头
                map.put("bidStatus", "ACT");
                map.put("supplierId", supplierId);
                List<SrmPonBidHeadersEntity_HI> bidHeaderList = srmPonBidHeadersDAO_HI.findByProperty(map);//查询生效的记录
                List<SrmPonBidItemPricesEntity_HI> bidLineList = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", bidHeaderList.get(0).getBidHeaderId());
                for (int t = 0; t < headerList.size(); t++) {
                    for (int i = 0; i < bidLineList.size(); i++) {
                        SrmPonAuctionItemsEntity_HI oldItem = auctionItemsDAO_HI.getById(bidLineList.get(i).getAuctionLineId());
                        if (headerList.get(i).getItemId().intValue() == oldItem.getItemId().intValue()) {
                            headerList.get(i).setInitialPrice(bidLineList.get(i).getTaxPrice());
                        }
                    }
                }
            }
            return headerList;
        } catch (Exception e) {
            throw new Exception("查询失败");
        }
    }

    /**
     * Description：查询报价头--报价中
     * @param params 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return Pagination<SrmPonBidEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public Pagination<SrmPonBidEntity_HI_RO> getAuctionBidInfo(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPonBidEntity_HI_RO.QUERY_AUCTION_BID_SQL);
            SaafToolUtils.parperParam(params, "bh.bidHeaderId", "bidHeaderId", queryString, queryParamMap, "=");//报价头id
            SaafToolUtils.parperParam(params, "bh.bidNumber", "bidNumber", queryString, queryParamMap, "LIKE");//报价状态
            SaafToolUtils.parperParam(params, "bh.bidStatus", "bidStatus", queryString, queryParamMap, "=");//报价状态
            SaafToolUtils.parperParam(params, "bh.supplierId", "supplierId", queryString, queryParamMap, "=");//供应商id
            SaafToolUtils.parperParam(params, "bh.auctionNumber", "auctionNumber", queryString, queryParamMap, "LIKE");//招标编号
            SaafToolUtils.parperParam(params, "bh.auctionTitle", "auctionTitle", queryString, queryParamMap, "LIKE");//招标标题
            SaafToolUtils.parperParam(params, "bh.auctionType", "auctionType", queryString, queryParamMap, "=");//招标类型
            SaafToolUtils.parperParam(params, "bh.auctionHeaderId", "auctionHeaderId", queryString, queryParamMap, "=");//招标类型

            if ("COMPLETED".equals(params.getString("auctionStatus"))) {
                queryString.append(" AND bh.auctionStatus IN ('COMPLETED','ROUND_COMPLATED') ");//招标状态，包含已完成／本轮已完成
            } else if ("PUBLISHED".equals(params.getString("auctionStatus"))) {
                queryString.append(" AND bh.auctionStatus = 'PUBLISHED' ");//招标状态，包含已完成／本轮已完成
            } else {
                SaafToolUtils.parperParam(params, "bh.auctionStatus", "auctionStatus", queryString, queryParamMap, "=");//招标状态
            }
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" ORDER BY bh.bidNumber DESC");
            return srmPonBidDAO_HI_RO.findPagination(queryString, countSql,queryParamMap, pageIndex, pageRows);
        } catch (Exception e) {
            throw new Exception("查询失败");
        }
    }

    /**
     * Description：获取待议价信息
     * @param params 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return Pagination<SrmPonBidEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPonBidEntity_HI_RO> getBargainInfo(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPonBidEntity_HI_RO.QUERY_AUCTION_BID_SQL);
            SaafToolUtils.parperParam(params, "bh.bidHeaderId", "bidHeaderId", queryString, queryParamMap, "=");//报价头id
            SaafToolUtils.parperParam(params, "bh.bidNumber", "bidNumber", queryString, queryParamMap, "like");//报价状态
            SaafToolUtils.parperParam(params, "bh.bidStatus", "bidStatus", queryString, queryParamMap, "=");//报价状态
            SaafToolUtils.parperParam(params, "bh.supplierId", "supplierId", queryString, queryParamMap, "=");//供应商id
            SaafToolUtils.parperParam(params, "bh.auctionNumber", "auctionNumber", queryString, queryParamMap, "like");//招标编号
            SaafToolUtils.parperParam(params, "bh.auctionTitle", "auctionTitle", queryString, queryParamMap, "like");//招标标题
            SaafToolUtils.parperParam(params, "bh.auctionType", "auctionType", queryString, queryParamMap, "=");//招标类型
            if (params.getString("bargainFlag") != null && !"".equals(params.getString("bargainFlag"))) {
                SaafToolUtils.parperParam(params, "bh.bargainFlag", "bargainFlag", queryString, queryParamMap, "=");//招标类型
            } else {
                queryString.append(" AND bh.bargainFlag IN ('2','3') ");
            }
            //queryString.append(" and bh.bargainFlag = '3' ");//招标状态，包含已完成／本轮已完成
            queryString.append(" AND bh.auctionStatus = 'CLOSED' ");//招标状态，包含已完成／本轮已完成
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" ORDER BY bh.bidNumber DESC");
            return srmPonBidDAO_HI_RO.findPagination(queryString,countSql, queryParamMap, pageIndex, pageRows);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("查询失败");
        }
    }

    /**
     * Description：查询报价行--报价中
     * @param params 参数
     * @return List<SrmPonBidEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public List<SrmPonBidEntity_HI_RO> getAuctionBidLineInfo(JSONObject params) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            List<SrmPonBidEntity_HI_RO> headerList = null;
            List<SrmPonBidEntity_HI_RO> priceList = null;
            if (null != params.getInteger("bidHeaderId")) {
                //阶梯数量
                if ("Y".equals(params.getString("subsectionFlag"))) {
                    queryString.append(SrmPonBidEntity_HI_RO.QUERY_AUCTION_BID_LINE_LADDER_SQL);
                } else {
                    queryString.append(SrmPonBidEntity_HI_RO.QUERY_AUCTION_BID_LINE_SQL);
                }
                queryString.append(" AND pbip.bid_header_id = " + params.getInteger("bidHeaderId") + "\n");
                queryString.append(" ORDER BY pbip.auction_line_id");
                headerList = srmPonBidDAO_HI_RO.findList(queryString, queryParamMap);
            } else {
                //阶梯数量
                if ("Y".equals(params.getString("subsectionFlag"))) {
                    queryString.append(SrmPonBidEntity_HI_RO.QUERY_AUCTION_ITEMS_LADDER_LIST_SQL);
                } else {
                    queryString.append(SrmPonBidEntity_HI_RO.QUERY_AUCTION_ITEMS_LIST_SQL);
                }
                queryString.append(" AND pai.auction_header_id = " + params.getInteger("auctionHeaderId") + "\n");
                queryString.append(" ORDER BY pai.auction_line_id");
                headerList = srmPonBidDAO_HI_RO.findList(queryString, queryParamMap);
            }

            //模板下载头税率处理
            if(!ObjectUtils.isEmpty(params.getString("headerTaxRate"))){
                String headerTaxRateName="";
                Map<String, Object> mapV = new HashMap<>();
                mapV.put("lookupType", "PON_TAX_LIST");
                mapV.put("lookupCode", params.getString("headerTaxRate"));
                List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                if (null != lookupValueList && lookupValueList.size() > 0) {
                    headerTaxRateName = lookupValueList.get(0).getMeaning();
                }
                for(int i=0;i<headerList.size();i++){
                    if(StringUtils.isEmpty(headerList.get(i).getTaxCode())){
                        headerList.get(i).setTaxRateName(headerTaxRateName);
                        headerList.get(i).setHeaderTaxRateCode(params.getString("headerTaxRate"));
                        headerList.get(i).setHeaderTaxRateName(headerTaxRateName);
                    }
                }
            }
            return headerList;
        } catch (Exception e) {
            throw new Exception("查询失败");
        }
    }

    /**
     * Description：查询中标总金额
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject findTotalBidAmount(JSONObject jsonParams) {
        JSONObject jsonData = new JSONObject();
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId"); //洽谈Id
        Integer supplierId = jsonParams.getInteger("supplierId"); //供应商Id
        String subsectionFlag = jsonParams.getString("subsectionFlag");//分段价格
        StringBuffer sbBid = new StringBuffer(SrmPonBidHeadersEntity_HI_RO.QUERY_BIDHEADERSLIST_SQL);
        sbBid.append(" AND t.bid_status='ACT'");
        sbBid.append(" AND t.auction_header_id=" + auctionHeaderId);
        sbBid.append(" AND t.supplier_id=" + supplierId);
        List<SrmPonBidHeadersEntity_HI_RO> bidHeadersList = srmPonBidHeadersDAO_HI_RO.findList(sbBid.toString());
        if (null == bidHeadersList || bidHeadersList.size() == 0 || "".equals(bidHeadersList)) {
            return SToolUtils.convertResultJSONObj("E", "不存在有效的供应商报价", 0, null);
        }
        Set<Integer> auctionLineIdList = new HashSet<>();//存放不重复的所有报价行的洽谈行Id
        for (Integer i = 0; i < bidHeadersList.size(); i++) {
            SrmPonBidHeadersEntity_HI_RO k = bidHeadersList.get(i);
            List<SrmPonBidItemPricesEntity_HI> bidItemPricesList = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", k.getBidHeaderId());
            if (null != bidItemPricesList && bidItemPricesList.size() > 0) {
                for (SrmPonBidItemPricesEntity_HI tem : bidItemPricesList) {
                    auctionLineIdList.add(tem.getAuctionLineId());
                }
            }
        }

        List<SrmPonBidHeadersEntity_HI_RO> bidHeadersListNew = iSrmPonBidItemPrices.getTotalAccount(auctionLineIdList, bidHeadersList, subsectionFlag);//计算供应商报价的报价总计金额，中标总计金额
        jsonData.put("supplierList", bidHeadersListNew);
        return SToolUtils.convertResultJSONObj("S", "查询成功", 1, jsonData);
    }

    /**
     * Description：查询供应商保证金的缴纳状态
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public String getBidBondPayFlag(JSONObject params) throws Exception {
        try {
            String bidBondPayFlag = "";
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("supplierId", params.getIntValue("varSupplierId"));
            map.put("auctionHeaderId", params.getInteger("auctionHeaderId"));
            SrmPonAuctionHeadersEntity_HI auctionHeadersEntityHi = auctionHeadersDAO_HI.getById(params.getInteger("auctionHeaderId"));
            //1缴纳 3免除,可以报价
            /* 文档没有这个需求
            if(auctionHeadersEntityHi.getLastRound()!=null){
                return  "1";//如果上一轮id存在，就默认为1
            }*/
            if (auctionHeadersEntityHi.getBidBond() == null) {
                return "3";
            } else {
                List<SrmPonAuctionSuppliersEntity_HI> list = auctionSuppliersDAO_HI.findByProperty(map);
                if (list.size() > 0) {
                    bidBondPayFlag = list.get(0).getBidBondPayStatus();
                } else {
                    bidBondPayFlag = "-1";//您没有被要请到本次招标！
                }
                return bidBondPayFlag;
            }

        } catch (Exception e) {
            throw new Exception("查询失败");
        }
    }

    /**
     * Description：查询供应商标书费用的缴纳状态
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public String findTenderCostPayFlag(JSONObject params) throws Exception {
        try {
            String tenderCostPayFlag = "";
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("supplierId", params.getIntValue("varSupplierId"));
            map.put("auctionHeaderId", params.getInteger("auctionHeaderId"));
            SrmPonAuctionHeadersEntity_HI auctionHeadersEntityHi = auctionHeadersDAO_HI.getById(params.getInteger("auctionHeaderId"));
            
            /* 文档没有这个需求
            if(auctionHeadersEntityHi.getInvitingBidWay().equals("2")){
            	return "1";
            }*/

            if (auctionHeadersEntityHi.getTenderCost() == null || auctionHeadersEntityHi.getTenderCost().doubleValue() <= 0.0
                    || auctionHeadersEntityHi.getBidBond() == null || auctionHeadersEntityHi.getBidBond().doubleValue() <= 0.0) {
                return "3";
            } else {
            	/* 文档没有这个需求
            	if(auctionHeadersEntityHi.getLastRound()!=null){
                    return  "1";//如果上一轮id存在，就默认为1
                }*/
                List<SrmPonAuctionSuppliersEntity_HI> list = auctionSuppliersDAO_HI.findByProperty(map);
                if (list.size() > 0) {
                    SrmPonAuctionSuppliersEntity_HI auctionSuppliersEntity_HI = list.get(0);
                    if ("1".equals(auctionSuppliersEntity_HI.getBidBondPayStatus()) || "3".equals(auctionSuppliersEntity_HI.getBidBondPayStatus())) {
                        tenderCostPayFlag = auctionSuppliersEntity_HI.getTenderCostPayStatus();
                    } else {
                        tenderCostPayFlag = auctionSuppliersEntity_HI.getBidBondPayStatus();
                    }
                } else {
                    tenderCostPayFlag = "-1";
                }
                return tenderCostPayFlag;
            }
        } catch (Exception e) {
            throw new Exception("查询失败");
        }
    }

    /**
     * Description：查询招标的保证金信息
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public SrmPonAuctionHeadersEntity_HI getBidBondInfo(JSONObject params) throws Exception {
        try {
            SrmPonAuctionHeadersEntity_HI row = null;
            row = auctionHeadersDAO_HI.getById(params.getInteger("auctionHeaderId"));
            if (null == row) {
                throw new Exception("没有查到相关的信息！");
            }
            return row;
        } catch (Exception e) {
            throw new Exception("查询失败");
        }
    }

    /**
     * Description：保存供应商的报价信息
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public JSONObject saveBidInfo(JSONObject jsonParams) throws Exception {
        if (null == jsonParams.getInteger("varSupplierId") || "".equals(jsonParams.getInteger("varSupplierId"))) {
            return SToolUtils.convertResultJSONObj("E", "非供应商不允许报价！", 0, null);
        }
        JSONObject json = new JSONObject();
        int userId = jsonParams.getInteger("varUserId");
        String biddingType = jsonParams.getString("biddingType");
        String auctionType = jsonParams.getString("auctionType");
        String bidStatus = jsonParams.getString("bidStatus");
        Integer numberPriceDecimals = jsonParams.getInteger("numberPriceDecimals");//报价位数
        String taxRateCode=jsonParams.getString("taxRateCode");

        SrmPonAuctionHeadersEntity_HI findAuctionHeader = auctionHeadersDAO_HI.getById(jsonParams.getInteger("auctionHeaderId"));

        //保存头税率
        findAuctionHeader.setOperatorUserId(userId);
        //findAuctionHeader.setTaxRateCode(taxRateCode);
        auctionHeadersDAO_HI.save(findAuctionHeader);

        if (null == numberPriceDecimals) {
            numberPriceDecimals = 4;
        }
        Integer originalBidHeaderId = null;//上一次有效报价ID
        BigDecimal basePrice = jsonParams.getBigDecimal("basePrice");//基准价
        String subsectionFlag = jsonParams.getString("subsectionFlag");//是否阶梯
        if ("ACT".equals(bidStatus)) {//提交报价
            //查询是否存在生效的报价记录
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("supplierId", jsonParams.getInteger("varSupplierId"));
            map.put("auctionHeaderId", jsonParams.getInteger("auctionHeaderId"));
            map.put("bidStatus", "ACT");
            List<SrmPonBidHeadersEntity_HI> list1 = srmPonBidHeadersDAO_HI.findByProperty(map);//查询生效的记录
            if (list1.size() > 0) {
                originalBidHeaderId = list1.get(0).getBidHeaderId();//记录上一次有效报价ID
                SrmPonBidHeadersEntity_HI row = srmPonBidHeadersDAO_HI.getById(list1.get(0).getBidHeaderId());
                row.setBidStatus("INACT");//设置为失效
                row.setOperatorUserId(userId);
                srmPonBidHeadersDAO_HI.save(row);
            }
        }
        SrmPonBidHeadersEntity_HI header = null;
        if (null == jsonParams.getInteger("bidHeaderId")) {//新增报价
            header = new SrmPonBidHeadersEntity_HI();
            header.setAuctionHeaderId(jsonParams.getInteger("auctionHeaderId"));//招标头
            header.setSupplierId(jsonParams.getInteger("varSupplierId"));//当前登陆的供应商
            header.setPaymentCondition(jsonParams.getString("paymentCondition"));//付款条件
            //税率
            header.setTaxRateCode(taxRateCode);
            //运杂及管理费
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("tranManagePercen"))){
                header.setTranManagePercen(jsonParams.getBigDecimal("tranManagePercen"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("tranManageFees"))){
                header.setTranManageFees(jsonParams.getBigDecimal("tranManageFees"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("measuresPercen"))){
                header.setMeasuresPercen(jsonParams.getBigDecimal("measuresPercen"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("measuresFees"))){
                header.setMeasuresFees(jsonParams.getBigDecimal("measuresFees"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("projectCosts"))){
                header.setProjectCosts(jsonParams.getBigDecimal("projectCosts"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("engineeringTax"))){
                header.setEngineeringTax(jsonParams.getBigDecimal("engineeringTax"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("totalProjectCost"))){
                header.setTotalProjectCost(jsonParams.getBigDecimal("totalProjectCost"));
            }
            //判断邀标方式
            if ("1".equals(findAuctionHeader.getInvitingBidWay())) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("supplierId", jsonParams.getInteger("varSupplierId"));
                map.put("auctionHeaderId", jsonParams.getInteger("auctionHeaderId"));
                List<SrmPonAuctionSuppliersEntity_HI> list = auctionSuppliersDAO_HI.findByProperty(map);
                if (list.size() > 0) {
                    header.setSupplierName(list.get(0).getSupplierName());
                    header.setSupplierContactId(list.get(0).getSupplierContactId());
                    header.setSupplierContactName(list.get(0).getSupplierContactName());
                    header.setSupplierContactEmail(list.get(0).getSupplierContactEmail());
                    header.setSupplierContactPhone(list.get(0).getSupplierContactPhone());
                    header.setSupplierSiteId(list.get(0).getSupplierSiteId());
                }
            } else if ("2".equals(findAuctionHeader.getInvitingBidWay())) {
                SrmPosSupplierInfoEntity_HI list2 = supplierDAO_HI.getById(jsonParams.getInteger("varSupplierId"));
                Map<String, Object> contactMap = new HashMap<String, Object>();
                contactMap.put("supplierId", jsonParams.getInteger("varSupplierId"));
                List<SrmPosSupplierContactsEntity_HI> contactsList = supplierContactsDAO_HI.findByProperty(contactMap);
                if (null != list2) {
                    header.setSupplierName(list2.getSupplierName());
                    if (contactsList.size() > 0) {
                        header.setSupplierContactId(contactsList.get(0).getSupplierContactId());
                        header.setSupplierContactName(contactsList.get(0).getContactName());
                        header.setSupplierContactEmail(contactsList.get(0).getEmailAddress());
                        header.setSupplierContactPhone(contactsList.get(0).getMobilePhone());
                    }
                }
            }

            header.setCurrencyCode(jsonParams.getString("currencyCode"));//币种
        } else {//修改报价信息
            header = srmPonBidHeadersDAO_HI.getById(jsonParams.getInteger("bidHeaderId"));
            //税率
            header.setTaxRateCode(taxRateCode);
            //运杂及管理费
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("tranManagePercen"))){
                header.setTranManagePercen(jsonParams.getBigDecimal("tranManagePercen"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("tranManageFees"))){
                header.setTranManageFees(jsonParams.getBigDecimal("tranManageFees"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("measuresPercen"))){
                header.setMeasuresPercen(jsonParams.getBigDecimal("measuresPercen"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("measuresFees"))){
                header.setMeasuresFees(jsonParams.getBigDecimal("measuresFees"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("projectCosts"))){
                header.setProjectCosts(jsonParams.getBigDecimal("projectCosts"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("engineeringTax"))){
                header.setEngineeringTax(jsonParams.getBigDecimal("engineeringTax"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("totalProjectCost"))){
                header.setTotalProjectCost(jsonParams.getBigDecimal("totalProjectCost"));
            }
        }
        header.setBidStatus(bidStatus);//报价状态
        if ("ACT".equals(bidStatus)) {//提交报价,设置报价提交时间
            header.setPublishDate(new Date());
            header.setOriginalBidHeaderId(originalBidHeaderId);//设置上一次的有效报价ID
            if ("INQUIRY".equals(auctionType)) {//询价，判断是否是在最后一分钟报的价，如果是，则自动延长报价结束时间
                SrmPonAuctionHeadersEntity_HI auctionHeader = auctionHeadersDAO_HI.getById(jsonParams.getInteger("auctionHeaderId"));
                long time2 = (auctionHeader.getBidEndDate()).getTime();//询价结束时间
                long time1 = (new Date()).getTime();//当前时间
                long diff = (time2 - time1) / (60 * 1000);//相差的分钟数
                if (diff <= 1 && diff >= 0) {//在最后一分钟内报价
                    Integer num = 0;//报错暂时置为0
                    time2 = time2 + num * 60 * 1000;//询价结束时间自动延长num分钟
                    auctionHeader.setBidEndDate(new Date(time2));
                    auctionHeader.setOperatorUserId(userId);
                    auctionHeadersDAO_HI.save(auctionHeader);
                }
            }
        }
        header.setNoteToAuctionOwner(jsonParams.getString("noteToAuctionOwner"));//供应商备注
        header.setToOwnerFileId(jsonParams.getInteger("toOwnerFileId"));//供应商附件
        header.setOperatorUserId(userId);
        header.setBidNumber(DateUtil.date2Str(new Date(), "yyyyMMddHHmmss"));//报价编号
        if (null != jsonParams.getString("paymentCondition") || !"".equals(jsonParams.getString("paymentCondition"))) {
            header.setPaymentCondition(jsonParams.getString("paymentCondition"));
        }
        header.setOperatorUserId(userId);
        srmPonBidHeadersDAO_HI.save(header);
        if (null != jsonParams.getJSONArray("lineData")) {
            saveLine(jsonParams.getJSONArray("lineData"), userId, header.getBidHeaderId(), biddingType, bidStatus, basePrice, subsectionFlag);
        }
        //插入通知——系统代办通知
        if (null != jsonParams.getString("notificationsFlag") && !"".equals(jsonParams.getString("notificationsFlag"))) {
            String notificationsFlag = jsonParams.getString("notificationsFlag");
            if (null != notificationsFlag && "Y".equals(notificationsFlag)) {
                List<SrmBaseNotificationsEntity_HI> notificationsList = srmBaseNotificationsDAO_HI.findByProperty("tableId", header.getBidHeaderId());
                if (null == notificationsList || notificationsList.size() == 0 || "".equals(notificationsList)) {
                    SrmBaseNotificationsEntity_HI notificationsEntity = new SrmBaseNotificationsEntity_HI();
                    try {
                        String notificationCode = saafSequencesUtil.getDocSequences("srm_base_notifications".toUpperCase(), "BJ-" + DateUtil.date2Str(new Date(), "yyyyMMdd"), 4);
                        notificationsEntity.setNotificationCode(notificationCode);
                    } catch (Exception e) {
                        LOGGER.error("创建系统通知编号出错！", e.getMessage());
                        return SToolUtils.convertResultJSONObj("E", "创建系统通知编号出错！", 0, null);
                    }
                    notificationsEntity.setNotificationType("21");//待报价
                    notificationsEntity.setNotificationStatus("2");//已处理
                    notificationsEntity.setTableId(header.getBidHeaderId());
                    notificationsEntity.setTableIdName("bidHeaderId");
                    notificationsEntity.setTableName("srm_pon_bid_headers");
                    if (null != findAuctionHeader.getAuctionType() && "TENDER".equals(findAuctionHeader.getAuctionType())) {
                        notificationsEntity.setFunctionUrl("home.supplierBidDetail");
                    }
                    if (null != findAuctionHeader.getAuctionType() && "INQUIRY".equals(findAuctionHeader.getAuctionType())) {
                        notificationsEntity.setFunctionUrl("home.inInquiryBidCompetitiveDetail");
                    }
                    notificationsEntity.setOperatorUserId(userId);
                    srmBaseNotificationsDAO_HI.save(notificationsEntity);
                }
            }
        }
        json.put("bidHeaderId", header.getBidHeaderId());
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, json);
    }

    /**
     * Description：保存报价行
     * @param lineData 报价行信息
     * @param userId 操作者ID
     * @param bidHeaderId 报价头ID
     * @param biddingType 寻源类型
     * @param bidStatus 寻源单据状态
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    JSONObject saveLine(JSONArray lineData, int userId, int bidHeaderId, String biddingType, String bidStatus, BigDecimal basePrice, String subsectionFlag) throws Exception {
        JSONObject resultjson = new JSONObject();
        JSONObject param = null;
        Integer auctionHeaderId = null;
        String taxRateCode = null;
        BigDecimal taxRate = null;
        //不含税总计
        BigDecimal sumNoTaxPrice=new BigDecimal(BigInteger.ZERO);
        for (int i = 0; i < lineData.size(); i++) {
            param = lineData.getJSONObject(i);
            auctionHeaderId = param.getInteger("auctionHeaderId");
            SrmPonAuctionHeadersEntity_HI auctionHeadersEntity = auctionHeadersDAO_HI.getById(auctionHeaderId);
            Integer numberPriceDecimals = auctionHeadersEntity.getNumberPriceDecimals();
            if (null == numberPriceDecimals) {
                numberPriceDecimals = 4;
            }
            SrmPonBidItemPricesEntity_HI row = null;
            if (null == param.getInteger("bidLineId")) {
                row = new SrmPonBidItemPricesEntity_HI();
                row.setAuctionHeaderId(param.getInteger("auctionHeaderId"));//招标头
                row.setAuctionLineId(param.getInteger("auctionLineId"));//招标行
                row.setBidHeaderId(bidHeaderId);//报价头
                row.setItemId(param.getInteger("itemId"));//物料id
                row.setItemDescription(param.getString("itemDescription"));
                row.setUnitOfMeasure(param.getString("unitOfMeasure"));
                row.setSpecification(param.getString("specification"));
                if (null != param.getString("notes")) {
                    row.setNotes(param.getString("notes"));
                }
                if (null != param.getInteger("itemLadderId")) {
                    row.setItemLadderId(param.getInteger("itemLadderId"));//阶梯id
                    SrmPonAuctionItemLaddersEntity_HI itemLadder = srmPonAuctionItemLaddersDAO_HI.getById(param.getInteger("itemLadderId"));
                    row.setPromisedQuantity(itemLadder.getLadderQuantity());
                } else {
                    row.setPromisedQuantity(param.getBigDecimal("quantity"));
                }
                if (null != param.getInteger("bidLineFileId")) {
                    row.setBidLineFileId(param.getInteger("bidLineFileId"));
                }
                row.setTaxRate(!StringUtils.isEmpty(param.getString("taxRateCode"))?param.getString("taxRateCode"):param.getString("taxRate"));//新建报价行的税率
            } else {
                row = srmPonBidItemPricesDAO_HI.getById(param.getInteger("bidLineId"));
                row.setTaxRate(!StringUtils.isEmpty(param.getString("taxRateCode"))?param.getString("taxRateCode"):param.getString("taxRate"));//update报价行税率
            }
            if (null != param.getBigDecimal("bargain")) {
                row.setBargain(param.getBigDecimal("bargain"));
            }
            if (null != param.getBigDecimal("bargainTax")) {
                row.setBargainTax(param.getBigDecimal("bargainTax"));
            }
            if (!"LOGISTICS".equals(param.getString("itemType"))) {
                if(null != param.getBigDecimal("materialsPrice")){
                    row.setMaterialsPrice(param.getBigDecimal("materialsPrice").setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if(null != param.getBigDecimal("artificialPrice")){
                    row.setArtificialPrice(param.getBigDecimal("artificialPrice").setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                row.setNoTaxPrice(param.getBigDecimal("noTaxPrice").setScale(2, BigDecimal.ROUND_HALF_UP));
                //taxRateCode = param.getString("taxRateName");
                //if (taxRateCode != null) {
                    Map<String, Object> mapV = new HashMap<>();
                    mapV.put("lookupType", "PON_TAX_LIST");
                    mapV.put("lookupCode", row.getTaxRate());
                    try {
                        List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                        if (null != lookupValueList && lookupValueList.size() > 0) {
                            taxRate = new BigDecimal(lookupValueList.get(0).getTag());
                        }
                    } catch (Exception e) {
                        taxRate = new BigDecimal(0);
                    }
                /*} else {
                    taxRate = new BigDecimal(0);
                }*/
                row.setTaxPrice(param.getBigDecimal("noTaxPrice").multiply(taxRate.divide(new BigDecimal(100)).add(new BigDecimal(1))).setScale(2, BigDecimal.ROUND_HALF_UP));
            //工程费用计算
                if("ENGINEERING".equals(param.getString("itemType"))){
                    sumNoTaxPrice=sumNoTaxPrice.add(param.getBigDecimal("noTaxPrice").multiply(param.getBigDecimal("quantity")));
                }
            }else if("LOGISTICS".equals(param.getString("itemType"))){
                row.setTaxPrice(param.getBigDecimal("taxPrice").setScale(2,BigDecimal.ROUND_HALF_UP));
                /*taxRateCode = param.getString("taxRateName");
                if (taxRateCode != null&&!"".equals(taxRateCode)) {*/
                    Map<String, Object> mapV = new HashMap<>();
                    mapV.put("lookupType", "PON_TAX_LIST");
                    mapV.put("lookupCode", row.getTaxRate());
                    try {
                        List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                        if (null != lookupValueList && lookupValueList.size() > 0) {
                            taxRate = new BigDecimal(lookupValueList.get(0).getTag());
                        }
                    } catch (Exception e) {
                        taxRate = new BigDecimal(0);
                    }
                /*} else {
                    taxRate = new BigDecimal(0);
                }*/
                /*row.setNoTaxPrice(param.getBigDecimal("taxPrice").multiply(new BigDecimal(1).subtract(taxRate.divide(new BigDecimal(100)))).setScale(numberPriceDecimals, BigDecimal.ROUND_HALF_UP));*/
                row.setNoTaxPrice(param.getBigDecimal("taxPrice").divide(new BigDecimal(1).add(taxRate.divide(new BigDecimal(100))),2,BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            row.setOperatorUserId(userId);
            srmPonBidItemPricesDAO_HI.saveEntity(row);
        }

        if ("ACT".equals(bidStatus)) {
            //如果是提交，更新价格排名
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("auctionHeaderId", auctionHeaderId);
            map.put("bidStatus", "ACT");
            //查询关于招标头auctionHeaderId的所有生效的报价记录
            List<SrmPonBidHeadersEntity_HI> list = srmPonBidHeadersDAO_HI.findByProperty(map);
            //查询关于招标auctionHeaderId的所有招标行
            StringBuffer sb = new StringBuffer();
            sb.append(SrmPonAuctionItemsEntity_HI_RO.QUERY_ITEMS_BID_LINE_SQL);
            sb.append(" AND spai.auction_header_id = " + auctionHeaderId);
            List<SrmPonAuctionItemsEntity_HI_RO> itemList = srmPonAuctionItemsDAO_HI_RO.findList(sb.toString());

            JSONArray array = new JSONArray();//用来存储报价信息

            for (int t = 0; t < list.size(); t++) {//遍历所有生效的报价头
                for (int i = 0; i < lineData.size(); i++) {
                    param = lineData.getJSONObject(i);
                    List<SrmPonBidItemPricesEntity_HI> list1 = null;
                    Map<String, Object> ladderMap = new HashMap<String, Object>();
                    ladderMap.put("bidHeaderId", list.get(t).getBidHeaderId());
                    if (null != param.getInteger("itemId")) {
                        ladderMap.put("itemId", param.getInteger("itemId"));
                    }
                    if (null != param.getInteger("itemLadderId")) {
                        ladderMap.put("itemLadderId", param.getInteger("itemLadderId"));
                    }
                    if (null != param.getInteger("auctionLineId")) {
                        ladderMap.put("auctionLineId", param.getInteger("auctionLineId"));
                    }
                    list1 = srmPonBidItemPricesDAO_HI.findByProperty(ladderMap);
                    for (int n = 0; n < list1.size(); n++) {
                        if (null != list1.get(n).getTaxPrice()) {//含税价不为空
                            JSONObject json = new JSONObject();
                            //取出每个报价头对应的报价行，放进一个对象json
                            if (null != list1.get(n).getItemLadderId()) {
                                json.put("itemLadderId", list1.get(n).getItemLadderId());//阶梯id
                            } else {
                                json.put("itemLadderId", "");//阶梯id
                            }
                            json.put("itemId", list1.get(n).getItemId());//物料id
                            json.put("supplierId", list.get(t).getSupplierId());//供应商id
                            json.put("bidLineId", list1.get(n).getBidLineId());//报价行id
                            json.put("bidHeaderId", list1.get(n).getBidHeaderId());//报价头id
                            json.put("taxPrice", list1.get(n).getTaxPrice().doubleValue());//价格
                            json.put("auctionLineId", list1.get(n).getAuctionLineId());//招标行id
                            array.add(json);//将对象放到数组里
                        }
                    }
                }
            }
            //array里有招标头auctionHeaderId的所有生效的报价记录，包括对应的招标行，供应商，报价行，价格
            for (int z = 0; z < itemList.size(); z++) {//遍历每一个招标行，取出价格，进行排序
                ArrayList<Double> al = new ArrayList<Double>();
                for (int a = 0; a < array.size(); a++) {
                    if (!"".equals(array.getJSONObject(a).getString("itemLadderId")) && !"null".equals(array.getJSONObject(a).getString("itemLadderId")) && null != array.getJSONObject(a).getString("itemLadderId")) {
                        if (itemList.get(z).getAuctionLineId().intValue() == array.getJSONObject(a).getIntValue("auctionLineId") && itemList.get(z).getItemLadderId().intValue() == array.getJSONObject(a).getIntValue("itemLadderId")) {
                            al.add(array.getJSONObject(a).getDoubleValue("taxPrice"));//取出每个价格
                        }
                    } else {
                        if (itemList.get(z).getAuctionLineId().intValue() == array.getJSONObject(a).getIntValue("auctionLineId")) {
                            al.add(array.getJSONObject(a).getDoubleValue("taxPrice"));//取出每个价格
                        }
                    }

                }
                Collections.sort(al);//价格排序[0,1,2,3],对应的招标行的价格的排序
                for (int p = 0; p < al.size(); p++) {//将排序结果对应到相应的报价
                    for (int q = 0; q < array.size(); q++) {
                        if (itemList.get(z).getAuctionLineId().intValue() == array.getJSONObject(q).getIntValue("auctionLineId") &&
                                al.get(p) == array.getJSONObject(q).getDoubleValue("taxPrice")) {
                            array.getJSONObject(q).put("rank", p + 1);
                            if (p == 0) {//最低价，更新洽谈行的最优价
                                SrmPonAuctionItemsEntity_HI item = auctionItemsDAO_HI.getById(itemList.get(z).getAuctionLineId());
                                item.setOperatorUserId(userId);
                                auctionItemsDAO_HI.save(item);
                            }
                        }
                    }
                }
            }
            //此时array里的数据已经有排名，将排名set到实体里
            for (int b = 0; b < array.size(); b++) {
                SrmPonBidItemPricesEntity_HI line = srmPonBidItemPricesDAO_HI.getById(array.getJSONObject(b).getInteger("bidLineId"));
                line.setRank(array.getJSONObject(b).getInteger("rank"));
                line.setOperatorUserId(userId);
                srmPonBidItemPricesDAO_HI.saveEntity(line);
            }
        }

        //计算工程费用
        if("ENGINEERING".equals(param.getString("itemType"))&&!ObjectUtils.isEmpty(sumNoTaxPrice)){
            //查询头
            SrmPonBidHeadersEntity_HI bidHeader = srmPonBidHeadersDAO_HI.getById(bidHeaderId);
            //运杂及管理费
            if(!ObjectUtils.isEmpty(bidHeader.getTranManagePercen())){
                bidHeader.setTranManageFees(sumNoTaxPrice.multiply(bidHeader.getTranManagePercen().divide(new BigDecimal("100"))).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if(!ObjectUtils.isEmpty(bidHeader.getMeasuresPercen())){
                bidHeader.setMeasuresFees(sumNoTaxPrice.multiply(bidHeader.getMeasuresPercen().divide(new BigDecimal("100"))).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if(!ObjectUtils.isEmpty(bidHeader.getTranManageFees())||!ObjectUtils.isEmpty(bidHeader.getMeasuresFees())){
                bidHeader.setProjectCosts(sumNoTaxPrice.add(bidHeader.getTranManageFees()).add(bidHeader.getMeasuresFees()).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if(!ObjectUtils.isEmpty(bidHeader.getProjectCosts())){
                Map<String, Object> mapV = new HashMap<>();
                mapV.put("lookupType", "PON_TAX_LIST");
                mapV.put("lookupCode", bidHeader.getTaxRateCode());
                try {
                    List<SaafLookupValuesEntity_HI> headerLookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                    if (null != headerLookupValueList && headerLookupValueList.size() > 0) {
                        taxRate = new BigDecimal(headerLookupValueList.get(0).getTag());
                    }
                } catch (Exception e) {
                    taxRate = new BigDecimal(0);
                }
                bidHeader.setEngineeringTax(bidHeader.getProjectCosts().multiply(taxRate.divide(new BigDecimal("100"))).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if(!ObjectUtils.isEmpty(bidHeader.getProjectCosts())||!ObjectUtils.isEmpty(bidHeader.getEngineeringTax())){
                bidHeader.setTotalProjectCost(bidHeader.getProjectCosts().add(bidHeader.getEngineeringTax()).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            bidHeader.setOperatorUserId(userId);
            srmPonBidHeadersDAO_HI.save(bidHeader);
        }
        return resultjson;
    }

    /**
     * Description：通过报价头id,查询本次报价的状态和还可以报价的次数
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public JSONObject getBidStatus(JSONObject params) throws Exception {
        try {
            JSONObject json = new JSONObject();
            String status = "";
            String bargainFlag = "";
            SrmPonBidHeadersEntity_HI row = null;
            row = srmPonBidHeadersDAO_HI.getById(params.getInteger("bidHeaderId"));
            if (null != row) {
                status = row.getBidStatus();
                bargainFlag = row.getBargainFlag();
            } else {
                throw new Exception("查询报价状态失败");
            }
            //查询关于这次招标报了多少次价（包括生效和失效）
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("supplierId", params.getInteger("varSupplierId"));
            map.put("auctionHeaderId", row.getAuctionHeaderId());
            map.put("bidStatus", "ACT");
            List<SrmPonBidHeadersEntity_HI> list = srmPonBidHeadersDAO_HI.findByProperty(map);//查询生效的记录

            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("supplierId", params.getInteger("varSupplierId"));
            map1.put("auctionHeaderId", row.getAuctionHeaderId());
            map1.put("bidStatus", "INACT");
            List<SrmPonBidHeadersEntity_HI> list1 = srmPonBidHeadersDAO_HI.findByProperty(map1);//查询失效的记录
            int hasBidCount = list1.size() + list.size();

            Integer actId = null;//生效状态的报价头id
            if (list.size() > 0) { //存在生效的状态
                actId = list.get(0).getBidHeaderId();
            }

            Integer draftId = null;//拟定状态的报价头id
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("supplierId", params.getInteger("varSupplierId"));
            map2.put("auctionHeaderId", row.getAuctionHeaderId());
            map2.put("bidStatus", "DRAFT");
            List<SrmPonBidHeadersEntity_HI> list2 = srmPonBidHeadersDAO_HI.findByProperty(map2);//查询拟定状态的记录
            if (list2.size() > 0) {
                draftId = list2.get(0).getBidHeaderId();
            }

            Integer bidCount = 1;
            SrmPonAuctionHeadersEntity_HI auctionHeader = auctionHeadersDAO_HI.getById(row.getAuctionHeaderId());
            if (null != auctionHeader.getMultipleBidFlag() && "Y".equals(auctionHeader.getMultipleBidFlag())) {//是否允许多次报价YES，NO
                if (null == auctionHeader.getMaxBidFrequency() || "".equals(auctionHeader.getMaxBidFrequency())) {
                    bidCount = 1;
                } else {
                    bidCount = auctionHeader.getMaxBidFrequency();//允许报价的次数
                }
            }

            json.put("draftId", draftId);//拟定的报价头ID
            json.put("actId", actId);//生效的报价头ID
            json.put("status", status);//当前报价的状态
            json.put("bargainFlag", bargainFlag);//当前议价的状态
            json.put("hasBidCount", hasBidCount);//生效+失效的报价次数
            json.put("bidCount", bidCount);//允许报价的次数
            return json;
        } catch (Exception e) {
            throw new Exception("查询失败");
        }
    }

    /**
     * Description：通过招标id,查询供应商是否报过价
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public JSONObject getBidOrNot(JSONObject params) throws Exception {
        try {
            JSONObject json = new JSONObject();
            String flag = "";
            String bidHeaderId = "";
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("supplierId", params.getInteger("varSupplierId"));
            map.put("auctionHeaderId", params.getInteger("auctionHeaderId"));
            List<SrmPonBidHeadersEntity_HI> list = srmPonBidHeadersDAO_HI.findByProperty(map);

            if (list.size() > 0) {
                bidHeaderId = String.valueOf(list.get(0).getBidHeaderId());
                flag = "Y";
            } else {
                flag = "N";
            }
            json.put("flag", flag);
            json.put("bidHeaderId", bidHeaderId);
            return json;
        } catch (Exception e) {
            throw new Exception("查询失败");
        }
    }

    /**
     * Description：已发布的标书（招标），查看监控报价
     * @param params 参数
     * @return Pagination<SrmPonBidEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPonBidEntity_HI_RO> getMonitorBidPrice(JSONObject params) throws Exception {
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        if (auctionHeaderId == null) {
            throw new Exception("招标头auctionHeaderId 是必须的");
        }
        try {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer query = new StringBuffer();
            query.append(SrmPonBidEntity_HI_RO.QUERY_AUCTION_SUPPLIER_LIST_SQL);
            SaafToolUtils.parperParam(params, "pas.auction_header_id", "auctionHeaderId", query, queryParamMap, "=");
            String countSql = "select count(1) from (" + query + ")";
            Pagination<SrmPonBidEntity_HI_RO> supplierList = srmPonBidDAO_HI_RO.findPagination(query,countSql, queryParamMap, 0, 0);
            for (int i = 0; i < supplierList.getData().size(); i++) {
                StringBuffer sb = new StringBuffer();
                sb.append(SrmPonBidEntity_HI_RO.QUERY_MONITOR_BID_PRICE_SQL);
                List<SrmPonBidEntity_HI_RO> linesList = srmPonBidDAO_HI_RO.findList(sb.toString(), new Object[]{supplierList.getData().get(i).getSupplierId(), auctionHeaderId});
                supplierList.getData().get(i).setLineDataList(linesList);
            }
            supplierList.setCount(supplierList.getData().size());
            return supplierList;
        } catch (Exception e) {
            throw new Exception("查看报价失败");
        }
    }

    /**
     * Description：更新洽谈的结束时间
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public JSONObject updateAuctionEndDate(JSONObject jsonParams) throws Exception {
        int userId = jsonParams.getInteger("varUserId");
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId");
        String type = jsonParams.getString("type");
        SrmPonAuctionHeadersEntity_HI header = auctionHeadersDAO_HI.getById(auctionHeaderId);
        if ("1".equals(type)) {//立即结束
            header.setBidEndDate(new Date());
            header.setAuctionStatus("CLOSED");
            header.setCloseBiddingDate(new Date());
            header.setOperatorUserId(userId);
        } else {
            Date newEndDate = jsonParams.getDate("newEndDate");
            header.setBidEndDate(newEndDate);
            header.setOperatorUserId(userId);
        }
        auctionHeadersDAO_HI.save(header);
        return SToolUtils.convertResultJSONObj("S", "更新洽谈结束时间成功", 1, null);
    }

    /**
     * Description：已发布的标书(询价)，查看监控报价
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject getMonitorBidPriceComp(JSONObject params) throws Exception {
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        if (auctionHeaderId == null) {
            throw new Exception("招标头auctionHeaderId 是必须的");
        }
        JSONObject res = new JSONObject();//用来存储返回信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Map<String, Object> supplierMap = new HashMap<String, Object>();
            supplierMap.put("bidStatus", "ACT");
            supplierMap.put("auctionHeaderId", auctionHeaderId);
            List<SrmPonBidHeadersEntity_HI> supplierList = srmPonBidHeadersDAO_HI.findByProperty(supplierMap);
            JSONArray jsonArray = new JSONArray();//用来存储所有供应商，所有报价信息
            ArrayList supplierName = new ArrayList();//用来存储供应商名称
            for (int i = 0; i < supplierList.size(); i++) {
                SrmPonBidHeadersEntity_HI supplier = supplierList.get(i);
                JSONObject json = new JSONObject();//用来存储一个供应商的数据列
                JSONArray array = new JSONArray();//用来存储报价时间-单价信息
                //查询失效状态和生效状态的报价头
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("supplierId", supplier.getSupplierId());
                map.put("bidStatus", "DRAFT");//不包含拟定状态的
                map.put("auctionHeaderId", auctionHeaderId);
                StringBuffer queryString = new StringBuffer("from SrmPonBidHeadersEntity_HI l where " +
                        "l.supplierId =:supplierId and l.auctionHeaderId =:auctionHeaderId and " +
                        "l.bidStatus <> :bidStatus order by l.publishDate asc");
                List<SrmPonBidHeadersEntity_HI> bidList = srmPonBidHeadersDAO_HI.findList(queryString, map);
                if (bidList.size() > 0) {
                    JSONArray al = null;
                    for (int t = 0; t < bidList.size(); t++) {
                        al = new JSONArray();
                        List<SrmPonBidItemPricesEntity_HI> itemPricesList = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", bidList.get(t).getBidHeaderId());
                        Double sum = 0.0;
                        for (int a = 0; a < itemPricesList.size(); a++) {
                            BigDecimal quantity = itemPricesList.get(a).getPromisedQuantity();
                            BigDecimal price = itemPricesList.get(a).getTaxPrice();
                            if (null == price) {
                                price = new BigDecimal(0);
                            }
                            sum = sum + quantity.multiply(price).doubleValue();
                        }
                        al.add(sdf.format(bidList.get(t).getPublishDate()));//al 的数据格式['2018-03-15 15:32:03']
                        al.add(sum);//al 的数据格式['2018-03-15 15:32:03', 2]
                        array.add(al);//array 的数据格式[['2018-03-15 15:32:03', 2],['2018-03-15 15:32:03', 2]]
                    }
                    supplierName.add(supplier.getSupplierName());
                    json.put("name", supplier.getSupplierName());
                    json.put("type", "line");
                    json.put("data", array);
                    jsonArray.add(json);
                }
            }
            res.put("legend", supplierName);
            res.put("series", jsonArray);
            return SToolUtils.convertResultJSONObj("S", "查询报价成功", 1, res);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("监控报价失败");
        }
    }

    /**
     * Description：询价，监控报价弹出框，查询供应商报价信息
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject getMonitorBidPriceTable(JSONObject params) throws Exception {
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        if (auctionHeaderId == null) {
            throw new Exception("招标头auctionHeaderId 是必须的");
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer query = new StringBuffer();
            query.append(SrmPonBidEntity_HI_RO.QUERY_PRICE_ITEM_SQL);//查询询价行
            SaafToolUtils.parperParam(params, "spai.auction_header_id", "auctionHeaderId", query, queryParamMap, "=");
            String countSql = "select count(1) from (" + query + ")";
            Pagination<SrmPonBidEntity_HI_RO> itemList = srmPonBidDAO_HI_RO.findPagination(query,countSql, queryParamMap, 0, 0);
            JSONObject obj = JSON.parseObject(JSONArray.toJSONString(itemList));//2
            JSONArray arrReturn = obj.getJSONArray("data");
            //参与询价的供应商
            Map<String, Object> supplierMap = new HashMap<String, Object>();
            supplierMap.put("bidStatus", "ACT");
            supplierMap.put("auctionHeaderId", auctionHeaderId);
            List<SrmPonBidHeadersEntity_HI> supplierList = srmPonBidHeadersDAO_HI.findByProperty(supplierMap);
            JSONArray array = new JSONArray();//用来存储报价信息

            for (int i = 0; i < supplierList.size(); i++) {//遍历每个询价的供应商
                //BigDecimal quantityScore = supplierList.get(i).getQuantityScore();
                String supplierName = supplierList.get(i).getSupplierName();
                Integer supplierId = supplierList.get(i).getSupplierId();

                //查询最新的报价
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("supplierId", supplierId);
                map.put("bidStatus", "ACT");
                map.put("auctionHeaderId", auctionHeaderId);
                List<SrmPonBidHeadersEntity_HI> bidHeaderList = srmPonBidHeadersDAO_HI.findByProperty(map);//1
                if (bidHeaderList.size() > 0) {//供应商报过价
                    SrmPonBidHeadersEntity_HI bidHeader = bidHeaderList.get(0);//得到生效状态（即最新）的报价

                    //查询供应商的第一次报价
                    StringBuffer queryFirst = new StringBuffer();
                    queryFirst.append(SrmPonBidEntity_HI_RO.QUERY_FIRST_BID_SQL);
                    List<SrmPonBidEntity_HI_RO> firstList = srmPonBidDAO_HI_RO.findList(queryFirst, new Object[]{auctionHeaderId, supplierId});//1

                    //生效的报价行
                    for (int k = 0; k < itemList.getData().size(); k++) {
                        Map<String, Object> bidMap = new HashMap<String, Object>();
                        if (null != itemList.getData().get(k).getItemLadderId()) {
                            bidMap.put("itemLadderId", itemList.getData().get(k).getItemLadderId());
                        }
                        if (null != itemList.getData().get(k).getItemId()) {
                            bidMap.put("itemId", itemList.getData().get(k).getItemId());
                        }
                        bidMap.put("auctionLineId", itemList.getData().get(k).getAuctionLineId());
                        bidMap.put("bidHeaderId", bidHeader.getBidHeaderId());
                        List<SrmPonBidItemPricesEntity_HI> bidLineList = srmPonBidItemPricesDAO_HI.findByProperty(bidMap);//2
                        for (int t = 0; t < bidLineList.size(); t++) {//遍历每个报价行
                            JSONObject json = new JSONObject();
                            //取出每个报价头对应的报价行，放进一个对象json
                            //json.put("quantityScore", quantityScore);
                            json.put("supplierName", supplierName);
                            if (null == bidLineList.get(t).getItemLadderId()) {
                                json.put("ladderQuantity", "");
                                json.put("itemLadderId", "");
                            } else {
                                SrmPonAuctionItemLaddersEntity_HI ladder = srmPonAuctionItemLaddersDAO_HI.getById(bidLineList.get(t).getItemLadderId());
                                json.put("ladderQuantity", ladder.getLadderQuantity());
                                json.put("itemLadderId", ladder.getItemLadderId());
                            }
                            if (null == bidLineList.get(t).getRank()) {
                                json.put("rank", "");//排名
                            } else {
                                json.put("rank", bidLineList.get(t).getRank());//排名
                            }
                            if (null == bidLineList.get(t).getTaxPrice()) {
                                json.put("newTaxPrice", "");//最新的价格
                            } else {
                                json.put("newTaxPrice", bidLineList.get(t).getTaxPrice().doubleValue());//最新的价格
                            }
                            if (null == bidHeader.getPublishDate()) {
                                json.put("publishDate", "");//报价时间
                            } else {
                                json.put("publishDate", bidHeader.getPublishDate());//报价时间
                            }
                            json.put("auctionLineId", bidLineList.get(t).getAuctionLineId());//询价行
                            if (firstList.size() > 0) {//得到供应商的第一次报价
                                Map<String, Object> fBidMap = new HashMap<String, Object>();
                                fBidMap.put("bidHeaderId", firstList.get(0).getBidHeaderId());
                                if (null != itemList.getData().get(k).getItemLadderId()) {
                                    fBidMap.put("itemLadderId", itemList.getData().get(k).getItemLadderId());
                                }
                                List<SrmPonBidItemPricesEntity_HI> firstBidLineList = srmPonBidItemPricesDAO_HI.findByProperty(fBidMap);//2
                                for (int n = 0; n < firstBidLineList.size(); n++) {
                                    if (bidLineList.get(t).getAuctionLineId().intValue() == firstBidLineList.get(n).getAuctionLineId().intValue()) {//取出同一个招标行的，第一次报价价格
                                        if (null == firstBidLineList.get(n).getTaxPrice()) {
                                            json.put("firstTaxPrice", "");//第一次报价为空
                                            json.put("desc", "");
                                            json.put("descPer", "");

                                        } else {
                                            json.put("firstTaxPrice", firstBidLineList.get(n).getTaxPrice().doubleValue());//第一次报价的价格
                                            if (null == bidLineList.get(t).getTaxPrice()) {//最新的价格为空
                                                json.put("desc", "");
                                                json.put("descPer", "");
                                            } else {
                                                BigDecimal desc = firstBidLineList.get(n).getTaxPrice().subtract(bidLineList.get(t).getTaxPrice()); //升降金额
                                                BigDecimal descPer = desc.divide(firstBidLineList.get(n).getTaxPrice(), 4);  //升降额度
                                                descPer = descPer.multiply(new BigDecimal(100));  //升降额度
                                                json.put("desc", desc);
                                                json.put("descPer", descPer);
                                            }
                                        }
                                    }
                                }
                            } else {
                                json.put("firstTaxPrice", "");//第一次报价的价格
                                json.put("desc", "");
                                json.put("descPer", "");
                            }
                            array.add(json);//将对象放到数组里
                        }
                    }
                } else {//没报过价
                    for (int x = 0; x < arrReturn.size(); x++) {
                        JSONObject json = new JSONObject();
                        //取出每个报价头对应的报价行，放进一个对象json
                        json.put("supplierName", supplierName);
                        json.put("rank", "");//排名
                        json.put("basePrice", "");//基准价
                        json.put("newTaxPrice", "");//最新价格,用null来占位
                        json.put("publishDate", "");//报价时间
                        json.put("firstTaxPrice", "");//第一次报价的价格
                        json.put("desc", "");
                        json.put("descPer", "");
                        json.put("auctionLineId", arrReturn.getJSONObject(x).getInteger("auctionLineId"));//招标行
                        array.add(json);//将对象放到数组里
                    }
                }
            }
            for (int p = 0; p < arrReturn.size(); p++) {
                ArrayList supplierNameList = new ArrayList();
                ArrayList basePriceList = new ArrayList();
                ArrayList newTaxPriceList = new ArrayList();
                ArrayList firstTaxPriceList = new ArrayList();
                ArrayList publishDateList = new ArrayList();
                ArrayList descList = new ArrayList();
                ArrayList descPerList = new ArrayList();
                ArrayList rankList = new ArrayList();
                for (int s = 0; s < array.size(); s++) {
                    if ("Y".equals(arrReturn.getJSONObject(p).getString("subsectionFlag"))) {
                        if (arrReturn.getJSONObject(p).getIntValue("auctionLineId") == array.getJSONObject(s).getIntValue("auctionLineId") && arrReturn.getJSONObject(p).getIntValue("itemLadderId") == array.getJSONObject(s).getIntValue("itemLadderId")) {
                            supplierNameList.add(array.getJSONObject(s).getString("supplierName"));
                            if ("".equals(array.getJSONObject(s).getString("basePrice"))) {
                                basePriceList.add("0");
                            } else {
                                basePriceList.add(array.getJSONObject(s).getBigDecimal("basePrice"));
                            }
                            if ("".equals(array.getJSONObject(s).getString("newTaxPrice"))) {
                                newTaxPriceList.add("--");
                            } else {
                                newTaxPriceList.add(array.getJSONObject(s).getBigDecimal("newTaxPrice"));
                            }
                            if ("".equals(array.getJSONObject(s).getString("firstTaxPrice"))) {
                                firstTaxPriceList.add("--");
                            } else {
                                firstTaxPriceList.add(array.getJSONObject(s).getBigDecimal("firstTaxPrice"));
                            }
                            if ("".equals(array.getJSONObject(s).getString("publishDate"))) {
                                publishDateList.add("--");
                            } else {
                                publishDateList.add(sdf.format(array.getJSONObject(s).getDate("publishDate")));
                            }
                            if ("".equals(array.getJSONObject(s).getString("rank"))) {
                                rankList.add("--");
                            } else {
                                rankList.add(array.getJSONObject(s).getInteger("rank"));
                            }
                            if ("".equals(array.getJSONObject(s).getString("desc"))) {
                                descList.add("--");
                            } else {
                                descList.add(array.getJSONObject(s).getBigDecimal("desc"));
                            }
                            if ("".equals(array.getJSONObject(s).getString("descPer"))) {
                                descPerList.add("--");
                            } else {
                                descPerList.add(array.getJSONObject(s).getBigDecimal("descPer"));
                            }
                        }
                    } else {
                        if (arrReturn.getJSONObject(p).getIntValue("auctionLineId") == array.getJSONObject(s).getIntValue("auctionLineId")) {
                            supplierNameList.add(array.getJSONObject(s).getString("supplierName"));
                            if ("".equals(array.getJSONObject(s).getString("basePrice"))) {
                                basePriceList.add("--");
                            } else {
                                basePriceList.add(array.getJSONObject(s).getBigDecimal("basePrice"));
                            }
                            if ("".equals(array.getJSONObject(s).getString("newTaxPrice"))) {
                                newTaxPriceList.add("--");
                            } else {
                                newTaxPriceList.add(array.getJSONObject(s).getBigDecimal("newTaxPrice"));
                            }
                            if ("".equals(array.getJSONObject(s).getString("firstTaxPrice"))) {
                                firstTaxPriceList.add("--");
                            } else {
                                firstTaxPriceList.add(array.getJSONObject(s).getBigDecimal("firstTaxPrice"));
                            }
                            if ("".equals(array.getJSONObject(s).getString("publishDate"))) {
                                publishDateList.add("--");
                            } else {
                                publishDateList.add(sdf.format(array.getJSONObject(s).getDate("publishDate")));
                            }
                            if ("".equals(array.getJSONObject(s).getString("rank"))) {
                                rankList.add("--");
                            } else {
                                rankList.add(array.getJSONObject(s).getInteger("rank"));
                            }
                            if ("".equals(array.getJSONObject(s).getString("desc"))) {
                                descList.add("--");
                            } else {
                                descList.add(array.getJSONObject(s).getBigDecimal("desc"));
                            }
                            if ("".equals(array.getJSONObject(s).getString("descPer"))) {
                                descPerList.add("--");
                            } else {
                                descPerList.add(array.getJSONObject(s).getBigDecimal("descPer"));
                            }
                        }
                    }
                }
                arrReturn.getJSONObject(p).put("supplierName", supplierNameList);
                arrReturn.getJSONObject(p).put("basePrice", basePriceList);
                arrReturn.getJSONObject(p).put("newTaxPrice", newTaxPriceList);
                arrReturn.getJSONObject(p).put("firstTaxPrice", firstTaxPriceList);
                arrReturn.getJSONObject(p).put("publishDate", publishDateList);
                arrReturn.getJSONObject(p).put("desc", descList);
                arrReturn.getJSONObject(p).put("descPer", descPerList);
                arrReturn.getJSONObject(p).put("rank", rankList);
            }
            return SToolUtils.convertResultJSONObj("S", "查询报价成功", 1, arrReturn);
        } catch (Exception e) {
            throw new Exception("监控报价失败");
        }
    }

    /**
     * Description：供应商报价统一降幅，计算
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public JSONObject findDescPerPrice(JSONObject jsonParams) {
        Integer supplierId = jsonParams.getInteger("varSupplierId");//当前用户为供应商身份
        if (null == supplierId || "".equals(supplierId)) {
            return SToolUtils.convertResultJSONObj("E", "非供应商不允许报价", 0, null);
        }
        Integer auctionHeaderId = null;
        if (null != jsonParams.getInteger("auctionHeaderId") && !"".equals(jsonParams.getInteger("auctionHeaderId"))) {
            auctionHeaderId = jsonParams.getInteger("auctionHeaderId");
        }
        if (null == auctionHeaderId || "".equals(auctionHeaderId)) {
            return SToolUtils.convertResultJSONObj("E", "洽谈头ID为" + auctionHeaderId, 0, null);
        }
        //查询出有效的供应商报价头
        Map<String, Object> map = new HashMap<>();
        map.put("auctionHeaderId", auctionHeaderId);
        map.put("supplierId", supplierId);
        map.put("bidStatus", "ACT");
        List<SrmPonBidHeadersEntity_HI> bidHeadersList = srmPonBidHeadersDao_HI.findByProperty(map);
        if (null == bidHeadersList || bidHeadersList.size() <= 0) {//不存在有效的供应商报价头
            return SToolUtils.convertResultJSONObj("E", "不存在有效供应商报价，不能降幅", 0, null);
        }

        SrmPonBidHeadersEntity_HI bidHeader = bidHeadersList.get(0);//存在有效的供应商报价头
        String action = jsonParams.getString("action");
        SrmPonAuctionHeadersEntity_HI auctionHeader = auctionHeadersDAO_HI.getById(auctionHeaderId);
        Integer numberPriceDecimals = auctionHeader.getNumberPriceDecimals();//报价位数
        if (null == numberPriceDecimals) {
            numberPriceDecimals = 4;
        }
        JSONObject json = new JSONObject();
        if (null != action && "EXECUTE".equals(action)) {//修改报价行信息，只渲染页面，不保存到数据库
            List<SrmPonBidItemPricesEntity_HI> bidLineList = new ArrayList<SrmPonBidItemPricesEntity_HI>();
            List<SrmPonBidItemPricesEntity_HI> itemList = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", bidHeader.getBidHeaderId());
            BigDecimal percentage = jsonParams.getBigDecimal("descPercentage");
            for (int i = 0; i < itemList.size(); i++) {
                SrmPonBidItemPricesEntity_HI bidLine = itemList.get(i);
                BigDecimal noTaxPrice = null;
                BigDecimal decimalPrice = null;
                if (null != bidLine.getNoTaxPrice()) {//单价不为空
                    BigDecimal taxPriceOld = bidLine.getNoTaxPrice();
                    noTaxPrice = taxPriceOld.multiply(percentage);
                    noTaxPrice = noTaxPrice.divide(new BigDecimal(100));
                    noTaxPrice = taxPriceOld.subtract(noTaxPrice);
                    noTaxPrice = noTaxPrice.setScale(numberPriceDecimals, BigDecimal.ROUND_DOWN);
                    Map<String, Object> slvMap = new HashMap<String, Object>();
                    slvMap.put("lookup_type", "PON_TAX_LIST");
                    slvMap.put("lookup_code", bidLine.getTaxRate());
                    List<SaafLookupValuesEntity_HI> slvList = saafLookupValuesDAO_HI.findByProperty(slvMap);
                    String taxRateCode = slvList.get(0).getMeaning().replace("%", "");
                    Float f = 1 + (Float.valueOf(taxRateCode) / 100);
                    decimalPrice = new BigDecimal(f);
                }
                bidLine.setNoTaxPrice(noTaxPrice);
                bidLine.setTaxPrice((noTaxPrice.multiply(decimalPrice)).setScale(numberPriceDecimals, BigDecimal.ROUND_DOWN));
                bidLineList.add(bidLine);
            }
            json.put("bidLineList", bidLineList);
        }
        return SToolUtils.convertResultJSONObj("S", "执行成功", 1, json);
    }

    /**
     * Description：供应商询价，查看报价次数和当前排名
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject getBidCurrentRank(JSONObject params) throws Exception {
        JSONObject json = new JSONObject();
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        if (auctionHeaderId == null) {
            throw new Exception("招标头auctionHeaderId 是必须的");
        }
        Integer rank = null;
        SrmPonAuctionHeadersEntity_HI auctionHeader = auctionHeadersDAO_HI.getById(auctionHeaderId);
        Integer numberPriceDecimals = auctionHeader.getNumberPriceDecimals();//报价位数
        if (null == numberPriceDecimals) {
            numberPriceDecimals = 4;
        }
        try {
            //查询失效状态和生效状态的报价头
            Map<String, Object> map = new HashMap<String, Object>();
//            System.out.println("VarSupplierId:" + params.getInteger("varSupplierId"));
//            System.out.println("SupplierId:" + params.getInteger("supplierId"));
            if (null != params.getInteger("varSupplierId")) {
                map.put("supplierId", params.getInteger("varSupplierId"));
            } else {
                map.put("supplierId", params.getInteger("supplierId"));
            }
            map.put("bidStatus", "ACT");//不包含拟定状态的
            map.put("auctionHeaderId", auctionHeaderId);
            StringBuffer queryString = new StringBuffer("from SrmPonBidHeadersEntity_HI l where " +
                    "l.supplierId =:supplierId and l.auctionHeaderId =:auctionHeaderId and " +
                    "l.bidStatus = :bidStatus order by l.publishDate asc");
            List<SrmPonBidHeadersEntity_HI> bidList = srmPonBidHeadersDAO_HI.findList(queryString, map);
            JSONArray jsonArray = new JSONArray();
            BigDecimal sum = new BigDecimal(0);//加权价，初始化为0
            BigDecimal comNum = new BigDecimal(0);//加权价，初始化为0
            if (bidList.size() > 0) {
                List<SrmPonBidItemPricesEntity_HI> bidLineList = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", bidList.get(0).getBidHeaderId());
                if (bidLineList.size() > 0) {
                    for (int i = 0; i < bidLineList.size(); i++) {
                        JSONObject jsonTemp = new JSONObject();
                        jsonTemp.put("bidLineId", bidLineList.get(i).getBidLineId());
                        jsonTemp.put("bidNumber", bidList.get(0).getBidNumber());
                        if (null != bidLineList.get(i).getNoTaxPrice()) {
                            jsonTemp.put("price", bidLineList.get(i).getNoTaxPrice());
                        } else {
                            jsonTemp.put("price", 0);
                        }
                        jsonArray.add(jsonTemp);
                        comNum = comNum.add(bidLineList.get(i).getNoTaxPrice());
                    }
                }

                //计算该供应商当前的排名
                Map<String, Object> map1 = new HashMap<String, Object>();
                map1.put("bidStatus", "ACT");//生效状态的报价
                map1.put("auctionHeaderId", auctionHeaderId);
                List<SrmPonBidHeadersEntity_HI> actBidList = srmPonBidHeadersDAO_HI.findByProperty(map1);
                ArrayList<Double> al = new ArrayList<Double>();//存储所有有效报价的加权价
                for (int p = 0; p < actBidList.size(); p++) {//遍历每一个报价头
                    sum = new BigDecimal(0);//报价头的加权价，初始化为0
                    //找出报价行
                    List<SrmPonBidItemPricesEntity_HI> actBidLineList = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", actBidList.get(p).getBidHeaderId());
                    for (SrmPonBidItemPricesEntity_HI actBidLine : actBidLineList) {
                        sum = sum.add(actBidLine.getNoTaxPrice());
                    }
                    al.add(sum.doubleValue());
                }
                Collections.sort(al);//价格排序[0,1,2,3]
                for (int c = 0; c < al.size(); c++) {
                    if (al.get(c).doubleValue() == comNum.doubleValue()) {
                        rank = c + 1;
                    }
                }
            }
            json.put("data", jsonArray);
            json.put("rank", rank);
            System.out.println("JSON:" + json.toJSONString());
            return SToolUtils.convertResultJSONObj("S", "查询排名成功成功", 1, json);
        } catch (Exception e) {
            throw new Exception("查看报价失败");
        }
    }

    /**
     * Description：确认／拒绝跟价
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public JSONObject saveFollowPriceStatus(JSONObject jsonParams) throws Exception {
        int userId = jsonParams.getInteger("varUserId");
        String action = jsonParams.getString("action");
        Integer bidLineId = jsonParams.getInteger("bidLineId");
        SrmPonBidItemPricesEntity_HI bidLine = srmPonBidItemPricesDAO_HI.getById(bidLineId);
        if (null != bidLine) {
            if ("YES".equals(action)) {//跟价，记录最低价
//                StringBuffer minPriceSql = new StringBuffer(SrmPonAuctionEntity_HI_RO.QUERY_BID_MIN_PRICE_SQL);
//                BigDecimal minPrice = auctionDAO_HI_RO.findList(minPriceSql, bidLine.getAuctionLineId()).get(0).getTaxPrice();
            }
            bidLine.setOperatorUserId(userId);
            srmPonBidItemPricesDAO_HI.save(bidLine);
        }
        String msg = "";
        if ("YES".equals(action)) {
            msg = "确认跟价成功";
        } else {
            msg = "拒绝跟价成功";
        }
        return SToolUtils.convertResultJSONObj("S", msg, 1, null);
    }

    /**
     * Description：应标
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public JSONObject saveRespondTo(JSONObject jsonParams) throws Exception {
        String msg = "";
        try {
            int userId = jsonParams.getInteger("varUserId");
            if (jsonParams.getInteger("varSupplierId") != null) {
                int supplierId = jsonParams.getInteger("varSupplierId");
                int auctionHeaderId = jsonParams.getInteger("auctionHeaderId");
                Map<String, Object> supplierMap = new HashMap<String, Object>();
                supplierMap.put("auctionHeaderId", auctionHeaderId);
                supplierMap.put("supplierId", supplierId);
                List<SrmPonAuctionSuppliersEntity_HI> auctionSuppliers = auctionSuppliersDAO_HI.findByProperty(supplierMap);
                if (auctionSuppliers.size() > 0) {
                    msg = "该供应商已对本次洽谈应标";
                    return SToolUtils.convertResultJSONObj("W", msg, 1, null);
                } else {
                    SrmPonAuctionSuppliersEntity_HI auctionSupplier = new SrmPonAuctionSuppliersEntity_HI();
                    SrmPosSupplierInfoEntity_HI suppliers = supplierDAO_HI.getById(supplierId);
                    Map<String, Object> contactMap = new HashMap<String, Object>();
                    contactMap.put("supplierId", supplierId);
                    List<SrmPosSupplierContactsEntity_HI> contactsList = supplierContactsDAO_HI.findByProperty(contactMap);
                    Map<String, Object> invitationsMap = new HashMap<String, Object>();
                    invitationsMap.put("varSupplierId", supplierId);
                    StringBuffer queryInvitations = new StringBuffer();
                    queryInvitations.append(SrmPonAuctionSuppliersEntity_HI_RO.QUERY_INVITATIONS_SQL);
                    List<SrmPonAuctionSuppliersEntity_HI_RO> auctionSupplierInvitations = auctionSuppliersDAO_HI_RO.findList(queryInvitations, invitationsMap);
                    if (auctionSupplierInvitations.size() > 0) {
                        auctionSupplier.setNumberOfInvitations(auctionSupplierInvitations.get(0).getNumberOfInvitations());
                    }
                    StringBuffer queryAwarded = new StringBuffer();
                    queryAwarded.append(SrmPonAuctionSuppliersEntity_HI_RO.QUERY_AWARDED_SQL);
                    List<SrmPonAuctionSuppliersEntity_HI_RO> auctionSupplierAwarded = auctionSuppliersDAO_HI_RO.findList(queryInvitations, invitationsMap);
                    if (auctionSupplierAwarded.size() > 0) {
                        auctionSupplier.setNumberOfAwarded(auctionSupplierAwarded.get(0).getNumberOfAwarded());
                    }

                    auctionSupplier.setInvitingBidWay("2");
                    auctionSupplier.setAuctionHeaderId(auctionHeaderId);
                    auctionSupplier.setSupplierId(supplierId);
                    auctionSupplier.setSupplierName(suppliers.getSupplierName());
                    auctionSupplier.setAwardedStatus("3");
                    if (contactsList.size() > 0) {
                        auctionSupplier.setSupplierContactId(contactsList.get(0).getSupplierContactId());
                        auctionSupplier.setSupplierContactName(contactsList.get(0).getContactName());
                        auctionSupplier.setSupplierContactEmail(contactsList.get(0).getEmailAddress());
                        auctionSupplier.setSupplierContactPhone(contactsList.get(0).getMobilePhone());
                    }
                    auctionSupplier.setOperatorUserId(userId);
                    auctionSuppliersDAO_HI.save(auctionSupplier);
                    msg = "应标成功！";
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "非供应商不能应标!", 1, null);
            }
            return SToolUtils.convertResultJSONObj("S", msg, 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：保存议价
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveBargainInfo(JSONObject jsonParams) throws Exception {
        if (null == jsonParams.getInteger("varSupplierId") || "".equals(jsonParams.getInteger("varSupplierId"))) {
            return SToolUtils.convertResultJSONObj("E", "非供应商不允许报价！", 0, null);
        }
        JSONObject json = new JSONObject();
        int userId = jsonParams.getInteger("varUserId");
        String biddingType = jsonParams.getString("biddingType");
        String auctionType = jsonParams.getString("auctionType");
        String bidStatus = jsonParams.getString("bidStatus");
        //头税率
        String taxRateCode=jsonParams.getString("taxRateCode");
        Integer numberPriceDecimals = jsonParams.getInteger("numberPriceDecimals");//报价位数
        SrmPonAuctionHeadersEntity_HI findAuctionHeader = auctionHeadersDAO_HI.getById(jsonParams.getInteger("auctionHeaderId"));
        if ("Y".equals(findAuctionHeader.getAllowScoreFlag())) {
            return SToolUtils.convertResultJSONObj("E", "标书已进入评分阶段，不允许议价！", 0, null);
        }
        if (null == numberPriceDecimals) {
            numberPriceDecimals = 4;
        }
        Integer originalBidHeaderId = null;//上一次有效报价ID
        String originalBargainReason = null;//上一次有效报价议价原因
        BigDecimal basePrice = jsonParams.getBigDecimal("basePrice");//基准价
        String subsectionFlag = jsonParams.getString("subsectionFlag");//是否阶梯

        //查询生效的报价数据
        Map<String, Object> querymap = new HashMap<String, Object>();
        querymap.put("supplierId", jsonParams.getInteger("varSupplierId"));
        querymap.put("auctionHeaderId", jsonParams.getInteger("auctionHeaderId"));
        querymap.put("bidStatus", "ACT");
        List<SrmPonBidHeadersEntity_HI> list1 = srmPonBidHeadersDAO_HI.findByProperty(querymap);//查询生效的记录
        originalBidHeaderId = list1.get(0).getBidHeaderId();//记录上一次有效报价ID
        originalBargainReason = list1.get(0).getBargainReason();//记录上一次有效报价议价原因
        SrmPonBidHeadersEntity_HI row = srmPonBidHeadersDAO_HI.getById(list1.get(0).getBidHeaderId());
        if ("4".equals(row.getAwardStatus())) {
            return SToolUtils.convertResultJSONObj("E", "供应商 " + row.getSupplierName() + " 已中标，无需议价！", 0, null);
        }
        if ("ACT".equals(bidStatus)) {//提交议价
            row.setBidStatus("INACT");//设置为失效
            row.setBargainFlag("4");
        } else {
            row.setBargainFlag("5");//保存拟定的议价数据，原待议价数据作废
        }
        row.setOperatorUserId(userId);
        srmPonBidHeadersDAO_HI.save(row);

        SrmPonBidHeadersEntity_HI header = null;
        if (null == jsonParams.getInteger("bidHeaderId")) {//新增报价
            header = new SrmPonBidHeadersEntity_HI();
            header.setAuctionHeaderId(jsonParams.getInteger("auctionHeaderId"));//招标头
            header.setSupplierId(jsonParams.getInteger("varSupplierId"));//当前登陆的供应商
            header.setPaymentCondition(jsonParams.getString("paymentCondition"));//付款条件
            header.setTaxRateCode(taxRateCode);
            //运杂及管理费
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("tranManagePercen"))){
                header.setTranManagePercen(jsonParams.getBigDecimal("tranManagePercen"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("tranManageFees"))){
                header.setTranManageFees(jsonParams.getBigDecimal("tranManageFees"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("measuresPercen"))){
                header.setMeasuresPercen(jsonParams.getBigDecimal("measuresPercen"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("measuresFees"))){
                header.setMeasuresFees(jsonParams.getBigDecimal("measuresFees"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("projectCosts"))){
                header.setProjectCosts(jsonParams.getBigDecimal("projectCosts"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("engineeringTax"))){
                header.setEngineeringTax(jsonParams.getBigDecimal("engineeringTax"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("totalProjectCost"))){
                header.setTotalProjectCost(jsonParams.getBigDecimal("totalProjectCost"));
            }
            //判断邀标方式
            if ("1".equals(findAuctionHeader.getInvitingBidWay())) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("supplierId", jsonParams.getInteger("varSupplierId"));
                map.put("auctionHeaderId", jsonParams.getInteger("auctionHeaderId"));
                List<SrmPonAuctionSuppliersEntity_HI> list = auctionSuppliersDAO_HI.findByProperty(map);
                if (list.size() > 0) {
                    header.setSupplierName(list.get(0).getSupplierName());
                    header.setSupplierContactId(list.get(0).getSupplierContactId());
                    header.setSupplierContactName(list.get(0).getSupplierContactName());
                    header.setSupplierContactEmail(list.get(0).getSupplierContactEmail());
                    header.setSupplierContactPhone(list.get(0).getSupplierContactPhone());
                    header.setSupplierSiteId(list.get(0).getSupplierSiteId());
                }
            } else if ("2".equals(findAuctionHeader.getInvitingBidWay())) {
                SrmPosSupplierInfoEntity_HI list2 = supplierDAO_HI.getById(jsonParams.getInteger("varSupplierId"));
                Map<String, Object> contactMap = new HashMap<String, Object>();
                contactMap.put("supplierId", jsonParams.getInteger("varSupplierId"));
                List<SrmPosSupplierContactsEntity_HI> contactsList = supplierContactsDAO_HI.findByProperty(contactMap);
                if (null != list2) {
                    header.setSupplierName(list2.getSupplierName());
                    if (contactsList.size() > 0) {
                        header.setSupplierContactId(contactsList.get(0).getSupplierContactId());
                        header.setSupplierContactName(contactsList.get(0).getContactName());
                        header.setSupplierContactEmail(contactsList.get(0).getEmailAddress());
                        header.setSupplierContactPhone(contactsList.get(0).getMobilePhone());
                    }
                }
            }
            header.setCurrencyCode(jsonParams.getString("currencyCode"));//币种
        } else {//修改报价信息
            header = srmPonBidHeadersDAO_HI.getById(jsonParams.getInteger("bidHeaderId"));
            header.setTaxRateCode(taxRateCode);
            //运杂及管理费
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("tranManagePercen"))){
                header.setTranManagePercen(jsonParams.getBigDecimal("tranManagePercen"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("tranManageFees"))){
                header.setTranManageFees(jsonParams.getBigDecimal("tranManageFees"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("measuresPercen"))){
                header.setMeasuresPercen(jsonParams.getBigDecimal("measuresPercen"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("measuresFees"))){
                header.setMeasuresFees(jsonParams.getBigDecimal("measuresFees"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("projectCosts"))){
                header.setProjectCosts(jsonParams.getBigDecimal("projectCosts"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("engineeringTax"))){
                header.setEngineeringTax(jsonParams.getBigDecimal("engineeringTax"));
            }
            if(!ObjectUtils.isEmpty(jsonParams.getBigDecimal("totalProjectCost"))){
                header.setTotalProjectCost(jsonParams.getBigDecimal("totalProjectCost"));
            }
        }
        header.setBidStatus(bidStatus);//报价状态
        if ("ACT".equals(bidStatus)) {//提交报价,设置报价提交时间
            header.setBargainFlag("4");//已处理待议价
            header.setPublishDate(new Date());
            header.setOriginalBidHeaderId(originalBidHeaderId);//设置上一次的有效报价ID
        } else {
            header.setBargainFlag("3");
        }
        header.setBargainReason(originalBargainReason);//复制上一次的有效报价议价原因
        header.setNoteToAuctionOwner(jsonParams.getString("noteToAuctionOwner"));//供应商备注
        header.setToOwnerFileId(jsonParams.getInteger("toOwnerFileId"));//供应商附件
        header.setOperatorUserId(userId);
        if(null==header.getBidNumber()){
            header.setBidNumber(DateUtil.date2Str(new Date(), "yyyyMMddHHmmss"));//报价编号
        }
        if (null != jsonParams.getString("paymentCondition") || !"".equals(jsonParams.getString("paymentCondition"))) {
            header.setPaymentCondition(jsonParams.getString("paymentCondition"));
        }
        header.setOperatorUserId(userId);
        srmPonBidHeadersDAO_HI.save(header);
        if (null != jsonParams.getJSONArray("lineData")) {
            saveLine(jsonParams.getJSONArray("lineData"), userId, header.getBidHeaderId(), biddingType, bidStatus, basePrice, subsectionFlag);
        }
        json.put("bidHeaderId", header.getBidHeaderId());
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, json);
    }

    /**
     * Description：批量导入——供应商报价
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveBatchImportBidLineInfo(JSONObject jsonParams, Integer userId) throws Exception {
        LOGGER.info("参数：" + jsonParams.toString());
        LOGGER.info("userId:" + userId);
        if (jsonParams.getJSONArray("data") == null || "".equals(jsonParams.getJSONArray("data"))
                || jsonParams.getJSONArray("data").size() <= 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "导入的数据为空，不可导入", 0, null);
        }
        JSONObject info = jsonParams.getJSONObject("info");
        JSONArray jsonArray = jsonParams.getJSONArray("data");
        JSONObject headerData=info.getJSONObject("headerData");
        JSONArray lineData=info.getJSONArray("lineData");
        JSONArray error = checkArray(jsonArray, info);//导入前校验
        if (error.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "导入失败", error.size(), error);
        }
        if(ObjectUtils.isEmpty(headerData.getString("taxRateCode"))){
            return SToolUtils.convertResultJSONObj("E", "请先选择头税率", 0, null);
        }
        JSONObject jsonData = new JSONObject(); // 最终返回的结果
        jsonParams.put("auctionHeaderId",Integer.parseInt(info.getString("auctionHeaderId")));

        headerData.put("varUserName",jsonParams.getString("varUserName"));
        headerData.put("operatorUserId",jsonParams.getInteger("operatorUserId"));
        headerData.put("varUserName",jsonParams.getString("varUserName"));
        headerData.put("varPlatformCode",jsonParams.getString("varPlatformCode"));
        headerData.put("varIsAdmin",jsonParams.getString("varIsAdmin"));
        headerData.put("varSupplierId",jsonParams.getInteger("varSupplierId"));
        headerData.put("varUserId",jsonParams.getInteger("varUserId"));
        headerData.put("bidStatus","DRAFT");
        for(int n=0;n<lineData.size();n++){
            lineData.getJSONObject(n).put("itemType",headerData.getString("itemType"));
            lineData.getJSONObject(n).put("unitOfMeasure",lineData.getJSONObject(n).getString("unitOfMeasureNum"));
            for(int j=0;j<jsonArray.size();j++){
                if(lineData.getJSONObject(n).getString("auctionLineId").equals(jsonArray.getJSONObject(j).getString("auctionLineId"))){
                    lineData.getJSONObject(n).put("taxPrice",jsonArray.getJSONObject(j).getBigDecimal("taxPrice"));
                    lineData.getJSONObject(n).put("noTaxPrice",jsonArray.getJSONObject(j).getBigDecimal("noTaxPrice"));
                    lineData.getJSONObject(n).put("taxRateName",jsonArray.getJSONObject(j).getString("taxRateName"));
                    lineData.getJSONObject(n).put("taxRateCode",jsonArray.getJSONObject(j).getString("taxRateCode"));
                    lineData.getJSONObject(n).put("materialsPrice",jsonArray.getJSONObject(j).getBigDecimal("materialsPrice"));
                    lineData.getJSONObject(n).put("artificialPrice",jsonArray.getJSONObject(j).getBigDecimal("artificialPrice"));
                }
            }

        }
        headerData.put("lineData",lineData);
        jsonData=saveBidInfo(headerData);
        return SToolUtils.convertResultJSONObj("S", "成功导入" + jsonArray.size() + "行数据", 1, jsonData);
    }

    /**
     * Description：导入校验——供应商报价
     * @param jsonArray 校验数组
     * @param info 输入信息
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public JSONArray checkArray(JSONArray jsonArray, JSONObject info) {
        if (jsonArray.isEmpty()) {
            return null;
        }
        Integer auctionHeaderId = Integer.parseInt(info.getString("auctionHeaderId"));
        Integer parentInstId = Integer.parseInt(info.getString("parentInstId"));
        JSONArray error = new JSONArray();
        JSONObject e = null;
        JSONObject header=info.getJSONObject("headerData");
        for (Integer i = 0; i < jsonArray.size(); i++) { // 检验数据正确性
            JSONObject object = jsonArray.getJSONObject(i);
            //验证含税价/不含税价是否为BigDecimal
            if("LOGISTICS".equals(header.getString("itemType"))){
                if(!StringUtils.isEmpty(object.getString("taxPrice"))){
                    boolean taxPrice = isBigDecimal(object.getString("taxPrice"));
                    if (!taxPrice) {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "含税价" + object.getString("taxPrice") + "输入有误");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                }else{
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "请输入含税价");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }else{
                if("ENGINEERING".equals(header.getString("itemType"))){
                    boolean materialsPrice=true;
                    if(!StringUtils.isEmpty(object.getString("materialsPrice"))){
                        materialsPrice = isBigDecimal(object.getString("materialsPrice"));
                        if (!materialsPrice) {
                            e = new JSONObject();
                            e.put("ERR_MESSAGE", "材料单价" + object.getString("materialsPrice") + "输入有误");
                            e.put("ROW_NUM", i + 1);
                            error.add(e);
                            continue;
                        }
                    }else{
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "请输入材料单价");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                    boolean artificialPrice=true;
                    if(!StringUtils.isEmpty(object.getString("artificialPrice"))){
                        artificialPrice = isBigDecimal(object.getString("artificialPrice"));
                        if (!artificialPrice) {
                            e = new JSONObject();
                            e.put("ERR_MESSAGE", "人工单价" + object.getString("artificialPrice") + "输入有误");
                            e.put("ROW_NUM", i + 1);
                            error.add(e);
                            continue;
                        }
                    }else{
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "请输入人工单价");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                    if(materialsPrice&&artificialPrice){
                        object.put("noTaxPrice",String.valueOf(object.getBigDecimal("materialsPrice").add(object.getBigDecimal("artificialPrice"))));
                    }
                }else{
                    if(!StringUtils.isEmpty(object.getString("noTaxPrice"))){
                        boolean taxPrice = isBigDecimal(object.getString("noTaxPrice"));
                        if (!taxPrice) {
                            e = new JSONObject();
                            e.put("ERR_MESSAGE", "不含税价" + object.getString("noTaxPrice") + "输入有误");
                            e.put("ROW_NUM", i + 1);
                            error.add(e);
                            continue;
                        }
                    }else{
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "请输入不含税");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                }
            }

            // 判断税率的快码值
            if (!(object.getString("taxRateName") == null || "".equals(object.getString("taxRateName")))) {
                String taxRateName = object.getString("taxRateName"); // 检查输入的税率
                Map<String, Object> mapV = new HashMap<>();
                mapV.put("lookupType", "PON_TAX_LIST");
                mapV.put("meaning", taxRateName);
                List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                if (lookupValueList == null || lookupValueList.size() < 1 || "".equals(lookupValueList)) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "税率" + taxRateName + "输入有误");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                } else {
                    SaafLookupValuesEntity_HI entity = lookupValueList.get(0);
                    if (null != entity.getEnabledFlag() && "Y".equals(entity.getEnabledFlag())) {
                        object.put("taxRateCode", lookupValueList.get(0).getLookupCode());
                    } else {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "税率" + taxRateName + "是未生效状态");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                }
            }

            // 处理物料编码
            JSONObject jsonObject = new JSONObject();
            //存在编码
            if(!ObjectUtils.isEmpty(object.getString("itemNumber"))){
                String itemNumber=object.getString("itemNumber");
                jsonObject.put("itemCode", itemNumber);
            }else{
                //不存在编码
                String itemDescription = object.getString("itemDescription");
                jsonObject.put("itemDescription", itemDescription);
            }
            jsonObject.put("auctionHeaderId",auctionHeaderId);
            List<SrmPonAuctionItemsEntity_HI_RO> auctionItems = iSrmPonAuctionItems.getAuctionItemsListByItemCode(jsonObject);
            if(null!=auctionItems){
                object.put("auctionLineId", auctionItems.get(0).getAuctionLineId());
            }
        }
        return error;
    }

    /**
     * Description：BigDecimal数据类型校验
     * @param integer 输入数据
     * @return boolean
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public static boolean isBigDecimal(String integer) {
        try{
            BigDecimal bd = new BigDecimal(integer);
            return true;
        }catch(NumberFormatException e)
        {
            return false;
        }
    }

    /**
     * Description：日期转换
     * @param sDate 输入数据
     * @return Date
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    private Date toDate(String sDate) throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(sDate);
        return date;
    }
}
