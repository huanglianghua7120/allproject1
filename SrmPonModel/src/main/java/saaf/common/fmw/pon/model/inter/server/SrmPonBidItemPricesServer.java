package saaf.common.fmw.pon.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.docx4j.wml.U;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafBaseResultFile;
import saaf.common.fmw.base.utils.ESBClientUtils;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.base.utils.enums.ESBParams;
import saaf.common.fmw.base.ws.service.EKPSyncService;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionItemsEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonBidHeadersEntity_HI;
import saaf.common.fmw.pon.model.entities.SrmPonBidItemPricesEntity_HI;
import saaf.common.fmw.pon.model.entities.readonly.*;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionHeaders;
import saaf.common.fmw.pon.model.inter.ISrmPonBidHeaders;
import saaf.common.fmw.pon.model.inter.ISrmPonBidItemPrices;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;
import static saaf.common.fmw.services.CommonAbstractServices.SUCCESS_STATUS;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonBidItemPricesServer.java
 * Description：寻源--寻源报价物料行信息
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           	   Updated By     Description
 * -------    ---------------    -----------    ---------------
 * V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonBidItemPricesServer")
public class SrmPonBidItemPricesServer implements ISrmPonBidItemPrices {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonBidItemPricesServer.class);

    @Autowired
    private ViewObject<SrmPonBidItemPricesEntity_HI> srmPonBidItemPricesDAO_HI;
    @Autowired
    private BaseViewObject<SrmPonBidItemPricesEntity_HI_RO> srmPonBidItemPricesDAO_HI_RO;
    @Autowired
    private ViewObject<SrmPonBidHeadersEntity_HI> srmPonBidHeadersDao_HI;//供应商报价头表
    @Autowired
    private BaseViewObject<SrmPonBidHeadersEntity_HI_RO> srmPonBidHeadersDAO_HI_RO;//供应商报价头表
    @Autowired
    private ISrmPonBidHeaders iSrmPonBidHeaders;//供应商报价头表
    @Autowired
    private ViewObject<SrmPonAuctionItemsEntity_HI> srmPonAuctionItemsDAO_HI;//标的物
    @Autowired
    private ViewObject<SrmPonAuctionHeadersEntity_HI> srmPonAuctionHeadersDAO_HI;//洽谈头层
    @Autowired
    private BaseViewObject<SrmPonAuctionSuppliersEntity_HI_RO> srmPonAuctionSuppliersDAO_HI_RO;//供应商
    @Autowired
    private BaseViewObject<SrmPonJuryScoreLinesEntity_HI_RO> srmPonJuryScoreLinesDAO_HI_RO;//评分行
    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值
    @Autowired
    private EKPSyncService ekpSyncService;
    @Autowired
    private ViewObject<SaafEmployeesEntity_HI> saafEmployeesDAO_HI;
    @Autowired
    private BaseViewObject<SrmPonAuctionHeadersEntity_HI_RO> srmPonAuctionHeadersDAO_HI_RO;
    @Autowired
    private ISrmPonAuctionHeaders iSrmPonAuctionHeaders;//供应商报价头表

    public SrmPonBidItemPricesServer() {
        super();
    }

    /**
     * Description：报价中——供应商，单个价格降幅判断
     *
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject findBidItemPricesByNoTaxPrice(JSONObject jsonParams) {
        JSONObject jsonData = new JSONObject();
        Integer auctionHeaderId = null;
        BigDecimal noTaxPrice = null;
        Integer index = null;
        Integer supplierId = jsonParams.getInteger("varSupplierId");//当前用户为供应商身份
        if (null == supplierId || "".equals(supplierId)) {
            return SToolUtils.convertResultJSONObj("E", "非供应商不允许报价", 0, null);
        }

        if (null != jsonParams.getInteger("auctionHeaderId") && !"".equals(jsonParams.getInteger("auctionHeaderId"))) {
            auctionHeaderId = jsonParams.getInteger("auctionHeaderId");
        }
        if (null != jsonParams.getBigDecimal("noTaxPrice") && !"".equals(jsonParams.getBigDecimal("noTaxPrice"))) {
            noTaxPrice = jsonParams.getBigDecimal("noTaxPrice");
        }
        if (null != jsonParams.getInteger("index") && !"".equals(jsonParams.getInteger("index"))) {
            index = jsonParams.getInteger("index");
        }
        if (null == auctionHeaderId || "".equals(auctionHeaderId)) {
            return SToolUtils.convertResultJSONObj("E", "洽谈头ID为" + auctionHeaderId, 0, null);
        }
        if (null == noTaxPrice || "".equals(noTaxPrice)) {
            return SToolUtils.convertResultJSONObj("E", "不含税单价为" + noTaxPrice, 0, null);
        }
        String subsectionFlag = jsonParams.getString("subsectionFlag");//分段价格
        Integer auctionLineId = jsonParams.getInteger("auctionLineId");//标的物ID
        Integer itemLadderId = jsonParams.getInteger("itemLadderId");//阶梯数量的ID
        //查询出有效的供应商报价头
        Map<String, Object> map = new HashMap<>();
        System.out.println(auctionHeaderId);
        System.out.println(supplierId);
        map.put("auctionHeaderId", auctionHeaderId);
        map.put("supplierId", supplierId);
        map.put("bidStatus", "ACT");
        List<SrmPonBidHeadersEntity_HI> bidHeadersList = srmPonBidHeadersDao_HI.findByProperty(map);
        System.out.println(bidHeadersList);
        if (null == bidHeadersList || bidHeadersList.size() <= 0) {//不存在有效的供应商报价头
            //status设置为S是为了在第一次报价时修改不含税价时通过验证
            return SToolUtils.convertResultJSONObj("S", "不存在有效的供应商报价，不必降幅", 0, null);
        }
        SrmPonBidHeadersEntity_HI bidHeader = bidHeadersList.get(0);//存在有效的供应商报价头
        //查询对应的标的物的供应商报价行
        Map<String, Object> mapLine = new HashMap<>();
        mapLine.put("auctionLineId", auctionLineId);
        mapLine.put("bidHeaderId", bidHeader.getBidHeaderId());
        mapLine.put("auctionHeaderId", auctionHeaderId);
        if (null != subsectionFlag && "Y".equals(subsectionFlag)) {//勾选了分段价格，存在阶梯数量
            mapLine.put("itemLadderId", itemLadderId);
        }
        List<SrmPonBidItemPricesEntity_HI> bidItemPriceList = srmPonBidItemPricesDAO_HI.findByProperty(mapLine);
        if (null == bidItemPriceList || bidItemPriceList.size() <= 0) {
            return SToolUtils.convertResultJSONObj("E", "存在有效供应商报价，但不存在有效的供应商报价行，不必降幅", 0, null);
        }
        //存在对应的有效的供应商报价行的数据
        SrmPonBidItemPricesEntity_HI itemPricesEntity = bidItemPriceList.get(0);
        SrmPonAuctionHeadersEntity_HI auctionHeader = srmPonAuctionHeadersDAO_HI.getById(itemPricesEntity.getAuctionHeaderId());
        if (null != auctionHeader.getMinDecreasingRange()) {
            if (null == itemPricesEntity.getNoTaxPrice() || itemPricesEntity.getNoTaxPrice().compareTo(new BigDecimal(0)) == 0) {
                return SToolUtils.convertResultJSONObj("E", "没有降幅，不含税单价为空或0", 0, null);
            }
            if (noTaxPrice.compareTo(itemPricesEntity.getNoTaxPrice()) == 0) {
                return SToolUtils.convertResultJSONObj("E", "没有降幅，价格一致", 0, null);
            }
        }
        BigDecimal value = noTaxPrice.divide(itemPricesEntity.getNoTaxPrice(), 4, BigDecimal.ROUND_HALF_UP);//不含税单价除以（有效的不含税单价）
        value = new BigDecimal(1).subtract(value);//1-新的不含税单价/有效不含税单价=降幅度
        value = value.multiply(new BigDecimal(100));//乘以100，转换为正数（大于0）
        if (null != auctionHeader.getMinDecreasingRange()) {
            if (value.compareTo(auctionHeader.getMinDecreasingRange()) >= 0) {//最小降幅比较，若大于等于最小降幅，则该价格满足条件
                return SToolUtils.convertResultJSONObj("S", "降幅成功", 0, null);
            } else {
                jsonData.put("noTaxPrice", itemPricesEntity.getNoTaxPrice());
                String msg = "";
                if (null != index) {
                    index++;
                    msg = "第" + index + "行的";
                }
                return SToolUtils.convertResultJSONObj("E", msg + "价格幅度的变化必须大于或等于最小降幅(" + auctionHeader.getMinDecreasingRange() + "%)", 0, jsonData);
            }
        } else {
            return SToolUtils.convertResultJSONObj("S", "降幅成功", 0, null);
        }
    }


    @Override
    public JSONObject findSrmPonActionItemPricesList(JSONObject params) {
        return null;
    }

    /**
     * Description：查询供应商报价行list（不分页）——已截止
     *
     * @param params 参数
     * @return JSONObject
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject findSrmPonBidItemPricesList(JSONObject params) {
        LOGGER.info("参数：" + params.toString());
        Integer auctionHeaderId = params.getInteger("auctionHeaderId"); //洽谈Id
        String subsectionFlag = params.getString("subsectionFlag");//分段价格
        if (auctionHeaderId == null || "".equals(auctionHeaderId)) {
            return null;
        }
        JSONObject jsonData = new JSONObject();
        List<SrmPonBidItemPricesEntity_HI_RO> bidItemPricesList = null;
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BID_ITEMS_SQL);
        sb.append(" AND t.auction_header_id = " + auctionHeaderId);
        sb.append(" ORDER BY t.auction_line_id ASC ");
        bidItemPricesList = srmPonBidItemPricesDAO_HI_RO.findList(sb);
        //<editor-fold desc="阶梯数量查询">
        /*if ("Y".equals(subsectionFlag)) {
            sb.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BIDITEMPRICESLIST_SQL);
            sb.append(" AND pbip.auction_header_id = " + auctionHeaderId);
            sb.append(" ORDER BY pbip.auction_line_id ASC, pbip.bid_header_id ASC ");
            bidItemPricesList = srmPonBidItemPricesDAO_HI_RO.findList(sb);
            if (null != bidItemPricesList && bidItemPricesList.size() > 0) {
                Integer auctionLineId = bidItemPricesList.get(0).getAuctionLineId();//标的物的Id
                Integer bidHeaderId = bidItemPricesList.get(0).getBidHeaderId();//供应商报价头表Id
                for (Integer i = 1; i < bidItemPricesList.size(); i++) {
                    SrmPonBidItemPricesEntity_HI_RO k = bidItemPricesList.get(i);
                    if (bidHeaderId.equals(k.getBidHeaderId()) && auctionLineId.equals(k.getAuctionLineId())) {
                        k.setNumberByBidItemPrices(-1);
                    } else {
                        auctionLineId = k.getAuctionLineId();
                        bidHeaderId = k.getBidHeaderId();
                    }
                }
                //洽谈标的物的数据
                StringBuffer querySql = new StringBuffer(SrmPonAuctionItemsEntity_HI_RO.QUERY_AUCTION_ITEMS_LIST_SQLV);
                List<SrmPonAuctionItemsEntity_HI_RO> itemList = srmPonAuctionItemsDAO_HI_RO.findList(querySql, new Object[]{auctionHeaderId});
                if (null != itemList && itemList.size() > 0) {
                    Iterator<SrmPonAuctionItemsEntity_HI_RO> it = itemList.iterator();
                    while (it.hasNext()) {
                        SrmPonAuctionItemsEntity_HI_RO k = it.next();
                        for (SrmPonBidItemPricesEntity_HI_RO tem : bidItemPricesList) {
                            if (null != k.getAuctionLineId() && tem.getAuctionLineId().equals(k.getAuctionLineId())) {
                                it.remove();//去除已经报价的洽谈标的物
                                break;
                            }
                        }
                    }
                }
                //排除筛选后的洽谈标的物
                if (null != itemList && itemList.size() > 0) {
                    for (SrmPonAuctionItemsEntity_HI_RO k : itemList) {
                        SrmPonBidItemPricesEntity_HI_RO entity = new SrmPonBidItemPricesEntity_HI_RO();
                        entity.setAuctionHeaderId(k.getAuctionHeaderId());
                        entity.setAuctionLineId(k.getAuctionLineId());
                        entity.setBidLineId(-1);
                        entity.setItemId(k.getItemId());
                        entity.setItemDescription(k.getItemDescription());
                        entity.setItemCode(k.getItemCode());
                        entity.setUnitOfMeasure(k.getUnitOfMeasure());
                        entity.setUnitOfMeasureName(k.getUnitOfMeasureName());
                        entity.setNotes(k.getNotes());
                        entity.setBidLineFileId(k.getFileId());
                        entity.setBidLineFileName(k.getFileName());
                        entity.setBidLineFileAccessPath(k.getAccessPath());
                        entity.setGroupId(k.getGroupId());
                        entity.setGroupName(k.getGroupName());
                        entity.setCategoryId(k.getCategoryId());
                        entity.setCategoryName(k.getCategoryName());
                        entity.setQuantity(k.getQuantity());
                        entity.setLadderQuantity(k.getLadderQuantity());
                        entity.setItemLadderId(k.getItemLadderId());
                        entity.setStartDate(k.getStartDate());
                        entity.setEndDate(k.getEndDate());
                        bidItemPricesList.add(entity);
                    }
                }

            }
        } else { //无阶梯数量
            List<SrmPonAuctionItemsEntity_HI> itemLists = srmPonAuctionItemsDAO_HI.findByProperty("auctionHeaderId",auctionHeaderId);
            if(itemLists.size()>0){

            }
            sb.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BIDITEMPRICESLISTV_SQL);
            sb.append(" AND pbip.auction_header_id = " + auctionHeaderId);
            bidItemPricesList = srmPonBidItemPricesDAO_HI_RO.findList(sb);
            if (null != bidItemPricesList && bidItemPricesList.size() > 0) {
                StringBuffer querySql = new StringBuffer(SrmPonAuctionItemsEntity_HI_RO.QUERY_AUCTION_ITEMS_LIST_SQL);
                List<SrmPonAuctionItemsEntity_HI_RO> itemList = srmPonAuctionItemsDAO_HI_RO.findList(querySql, new Object[]{auctionHeaderId});
                if (null != itemList && itemList.size() > 0) {
                    Iterator<SrmPonAuctionItemsEntity_HI_RO> it = itemList.iterator();
                    while (it.hasNext()) {
                        SrmPonAuctionItemsEntity_HI_RO k = it.next();
                        for (SrmPonBidItemPricesEntity_HI_RO tem : bidItemPricesList) {
                            if (null != k.getAuctionLineId() && tem.getAuctionLineId().equals(k.getAuctionLineId())) {
                                it.remove();//去除已经报价的洽谈标的物
                                break;
                            }
                        }
                    }
                }
                //排除筛选后的洽谈标的物
                if (null != itemList && itemList.size() > 0) {
                    for (SrmPonAuctionItemsEntity_HI_RO k : itemList) {
                        SrmPonBidItemPricesEntity_HI_RO entity = new SrmPonBidItemPricesEntity_HI_RO();
                        entity.setAuctionHeaderId(k.getAuctionHeaderId());
                        entity.setAuctionLineId(k.getAuctionLineId());
                        entity.setBidLineId(-1);
                        entity.setItemId(k.getItemId());
                        entity.setUnitOfMeasureName(k.getUnitOfMeasureName());
                        entity.setNotes(k.getNotes());
                        entity.setBidLineFileId(k.getFileId());
                        entity.setBidLineFileName(k.getFileName());
                        entity.setBidLineFileAccessPath(k.getAccessPath());
                        entity.setGroupId(k.getGroupId());
                        entity.setGroupName(k.getGroupName());
                        entity.setCategoryId(k.getCategoryId());
                        entity.setCategoryName(k.getCategoryName());
                        entity.setQuantity(k.getQuantity());
                        entity.setStartDate(k.getStartDate());
                        entity.setEndDate(k.getEndDate());
                        bidItemPricesList.add(entity);
                    }
                }
            }
        }
        if (null != bidItemPricesList && bidItemPricesList.size() > 0) {
            for (SrmPonBidItemPricesEntity_HI_RO k : bidItemPricesList) {
                if (null != k.getBidLineId() && !"".equals(k.getBidLineId()) && (null == k.getAwardStatus() || "".equals(k.getAwardStatus()))) {
                    k.setAwardStatusName("未中标");
                }
            }
            jsonData.put("bidItemPricesList", bidItemPricesList);
            return SToolUtils.convertResultJSONObj("S", "查询成功", 1, jsonData);
        } else {//没有任何供应商报价
            if (null != subsectionFlag && "Y".equals(subsectionFlag)) {//阶梯数量
                StringBuffer querySql = new StringBuffer(SrmPonAuctionItemsEntity_HI_RO.QUERY_AUCTION_ITEMS_LIST_SQLV);
                List<SrmPonAuctionItemsEntity_HI_RO> itemList = srmPonAuctionItemsDAO_HI_RO.findList(querySql, new Object[]{auctionHeaderId});
                if (null != itemList && itemList.size() > 0) {
                    bidItemPricesList = new ArrayList<>();
                    for (SrmPonAuctionItemsEntity_HI_RO k : itemList) {
                        SrmPonBidItemPricesEntity_HI_RO entity = new SrmPonBidItemPricesEntity_HI_RO();
                        entity.setAuctionHeaderId(k.getAuctionHeaderId());
                        entity.setAuctionLineId(k.getAuctionLineId());
                        entity.setBidLineId(-1);
                        entity.setItemId(k.getItemId());
                        entity.setItemDescription(k.getItemDescription());
                        entity.setItemCode(k.getItemCode());
                        entity.setUnitOfMeasure(k.getUnitOfMeasure());
                        entity.setUnitOfMeasureName(k.getUnitOfMeasureName());
                        entity.setNotes(k.getNotes());
                        entity.setBidLineFileId(k.getFileId());
                        entity.setBidLineFileName(k.getFileName());
                        entity.setBidLineFileAccessPath(k.getAccessPath());
                        entity.setGroupId(k.getGroupId());
                        entity.setGroupName(k.getGroupName());
                        entity.setCategoryId(k.getCategoryId());
                        entity.setCategoryName(k.getCategoryName());
                        entity.setQuantity(k.getQuantity());
                        entity.setLadderQuantity(k.getLadderQuantity());
                        entity.setItemLadderId(k.getItemLadderId());
                        entity.setStartDate(k.getStartDate());
                        entity.setEndDate(k.getEndDate());
                        bidItemPricesList.add(entity);
                    }
                }
            } else {
                StringBuffer querySql = new StringBuffer(SrmPonAuctionItemsEntity_HI_RO.QUERY_AUCTION_ITEMS_LIST_SQL);
                List<SrmPonAuctionItemsEntity_HI_RO> itemList = srmPonAuctionItemsDAO_HI_RO.findList(querySql, new Object[]{auctionHeaderId});
                if (null != itemList && itemList.size() > 0) {
                    bidItemPricesList = new ArrayList<>();
                    for (SrmPonAuctionItemsEntity_HI_RO k : itemList) {
                        SrmPonBidItemPricesEntity_HI_RO entity = new SrmPonBidItemPricesEntity_HI_RO();
                        entity.setAuctionHeaderId(k.getAuctionHeaderId());
                        entity.setAuctionLineId(k.getAuctionLineId());
                        entity.setBidLineId(-1);
                        entity.setItemId(k.getItemId());
                        entity.setItemDescription(k.getItemDescription());
                        entity.setItemCode(k.getItemCode());
                        entity.setUnitOfMeasure(k.getUnitOfMeasure());
                        entity.setUnitOfMeasureName(k.getUnitOfMeasureName());
                        entity.setNotes(k.getNotes());
                        entity.setBidLineFileId(k.getFileId());
                        entity.setBidLineFileName(k.getFileName());
                        entity.setBidLineFileAccessPath(k.getAccessPath());
                        entity.setGroupId(k.getGroupId());
                        entity.setGroupName(k.getGroupName());
                        entity.setCategoryId(k.getCategoryId());
                        entity.setCategoryName(k.getCategoryName());
                        entity.setQuantity(k.getQuantity());
                        entity.setStartDate(k.getStartDate());
                        entity.setEndDate(k.getEndDate());
                        bidItemPricesList.add(entity);
                    }
                }
            }
            jsonData.put("bidItemPricesList", bidItemPricesList);
            return SToolUtils.convertResultJSONObj("S", "查询成功", 1, jsonData);
        }*/
        //</editor-fold>
        //return SToolUtils.convertResultJSONObj("S", "无此数据", 0, null);
        jsonData.put("bidItemPricesList", bidItemPricesList);
        return SToolUtils.convertResultJSONObj("S", "查询成功", 1, jsonData);
    }

    /**
     * Description：会签的模态框的中标记录——已截止
     *
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject findSrmPonBidItemPricesAll(JSONObject jsonParams) {
        JSONObject jsonData = new JSONObject();
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId"); //洽谈Id
        Map<String, Object> map = new HashMap<>();
        map.put("auctionHeaderId", auctionHeaderId);
        map.put("bidStatus", "ACT");
        List<SrmPonBidHeadersEntity_HI> bidHeadersList = srmPonBidHeadersDao_HI.findByProperty(map);
        if (null == bidHeadersList || bidHeadersList.size() == 0 || "".equals(bidHeadersList)) {
            jsonData.put("status", "S");
            jsonData.put("msg", "无数据");
            jsonData.put("count", 0);
            return jsonData;
        }
        Set<Integer> auctionLineIdList = new HashSet<>();//存放不重复的所有报价行的洽谈行Id

        for (Integer i = 0; i < bidHeadersList.size(); i++) {
            SrmPonBidHeadersEntity_HI k = bidHeadersList.get(i);
            BigDecimal scoreSum = iSrmPonBidHeaders.getScoreSum(k.getBidHeaderId(), k.getAuctionHeaderId());
            Integer numOffer = scoreSum.compareTo(new BigDecimal(0));
            if (numOffer > 0) {
                k.setScoreSum(scoreSum);//综合得分赋值
            }
            List<SrmPonBidItemPricesEntity_HI> bidItemPricesList = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", k.getBidHeaderId());
            if (null != bidItemPricesList && bidItemPricesList.size() > 0) {
                for (SrmPonBidItemPricesEntity_HI tem : bidItemPricesList) {
                    auctionLineIdList.add(tem.getAuctionLineId());
                }
            }
        }
        jsonData.put("supplierList", bidHeadersList);
        List<Object> bidItemPricesList = new ArrayList<>();//供应商报价行的结果
        if (auctionLineIdList.size() > 0) {
            for (Integer auctionLineId : auctionLineIdList) {//洽谈行
                String[] lineResult = new String[bidHeadersList.size() + 2];
                for (SrmPonBidHeadersEntity_HI temp : bidHeadersList) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BIDITEMPRICELIST_SQL);
                    sb.append(" AND t.bid_header_id = " + temp.getBidHeaderId());
                    sb.append(" AND t.auction_line_id = " + auctionLineId);
                    List<SrmPonBidItemPricesEntity_HI_RO> bidItemPricesListRO = srmPonBidItemPricesDAO_HI_RO.findList(sb);
                    if (null != bidItemPricesListRO && bidItemPricesListRO.size() > 0) {
                        SrmPonBidItemPricesEntity_HI_RO bidItemPrice = bidItemPricesListRO.get(0);
                        lineResult[0] = bidItemPrice.getItemCode();
                        lineResult[1] = bidItemPrice.getItemDescription();
                        String[] supplierAwardStatusName = getAwardStatusName(auctionHeaderId, auctionLineId, bidHeadersList);//获取供应商的中标记录
                        for (Integer i = 0; i < supplierAwardStatusName.length; i++) {
                            lineResult[i + 2] = supplierAwardStatusName[i];
                        }
                    }
                }
                bidItemPricesList.add(lineResult);
            }
            jsonData.put("bidItemPricesList", bidItemPricesList);
        }
        return SToolUtils.convertResultJSONObj("S", "查询成功", 1, jsonData);
    }

    /**
     * Description：根据供应商报价行来判断，该标的物——物料编码是否中标，用于获取供应商中标记录（会签的模态框的中标记录——已截止）
     *
     * @param auctionHeaderId 寻源单据ID
     * @param auctionLineId   寻源标的物ID
     * @param bidHeadersList  报价头信息列表
     * @return String[]
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public String[] getAwardStatusName(Integer auctionHeaderId, Integer auctionLineId, List<SrmPonBidHeadersEntity_HI> bidHeadersList) {
        String[] supplierAwardStatusName = new String[bidHeadersList.size()]; //存放供应商报价头表的中标状态情况
        Integer index = -1;
        for (SrmPonBidHeadersEntity_HI k : bidHeadersList) {
            index++;
            StringBuffer sb = new StringBuffer();
            sb.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BIDITEMPRICELIST_SQL);
            sb.append(" AND t.bid_header_id = " + k.getBidHeaderId());
            sb.append(" AND t.auction_line_id = " + auctionLineId);
            sb.append(" AND t.auction_header_id = " + auctionHeaderId);
            List<SrmPonBidItemPricesEntity_HI_RO> bidItemPricesListRO = srmPonBidItemPricesDAO_HI_RO.findList(sb);
            if (bidItemPricesListRO == null || bidItemPricesListRO.size() == 0 || "".equals(bidItemPricesListRO)) {
                supplierAwardStatusName[index] = "";
            } else {
                boolean flag = false;
                for (SrmPonBidItemPricesEntity_HI_RO temp : bidItemPricesListRO) {
                    if (null != temp.getAwardStatus() && "4".equals(temp.getAwardStatus())) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    supplierAwardStatusName[index] = "已中标";
                } else {
                    supplierAwardStatusName[index] = "未中标";
                }
            }
        }
        return supplierAwardStatusName;
    }

    /**
     * Description：决标汇总模态框的查询（已截止）
     *
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject findSrmPonBidItemPricesAllSummary(JSONObject jsonParams) {
        JSONObject jsonData = new JSONObject();
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId"); //洽谈Id
        String subsectionFlag = jsonParams.getString("subsectionFlag");//分段价格
        StringBuffer sbBid = new StringBuffer(SrmPonBidHeadersEntity_HI_RO.QUERY_BIDHEADERSLIST_SQL);
        sbBid.append(" AND t.bid_status = 'ACT'");
        sbBid.append(" AND t.auction_header_id = " + auctionHeaderId);
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
        boolean flag = false;
        if (null != subsectionFlag && "Y".equals(subsectionFlag)) {
            flag = true;//阶梯数量
        } else {
            flag = false;//无阶梯数量
        }
        List<Object> bidItemPricesList = new ArrayList<>();//供应商报价行的结果
        if (auctionLineIdList.size() > 0) {
            for (Integer auctionLineId : auctionLineIdList) {//洽谈行
                List<SrmPonBidItemPricesEntity_HI_RO> bidItemPricesListV = null;
                for (SrmPonBidHeadersEntity_HI_RO temp : bidHeadersList) {
                    StringBuffer sb = new StringBuffer();
                    if (flag) {
                        sb.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BIDITEMPRICESLIST_SQL);
                        sb.append(" AND pbip.auction_line_id=" + auctionLineId);
                        sb.append(" AND pbip.auction_header_id=" + auctionHeaderId);
                        sb.append(" AND pbip.bid_header_id=" + temp.getBidHeaderId());
                        sb.append(" ORDER BY pbip.auction_line_id ASC,pbip.bid_header_id ASC ");
                        bidItemPricesListV = srmPonBidItemPricesDAO_HI_RO.findList(sb);
                    } else { //无阶梯数量
                        sb.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BIDITEMPRICESLISTV_SQL);
                        sb.append(" AND pbip.auction_line_id=" + auctionLineId);
                        sb.append(" AND pbip.auction_header_id=" + auctionHeaderId);
                        sb.append(" AND pbip.bid_header_id=" + temp.getBidHeaderId());
                        bidItemPricesListV = srmPonBidItemPricesDAO_HI_RO.findList(sb);
                    }
                    if (null != bidItemPricesListV && bidItemPricesListV.size() > 0) {
                        break;
                    }
                }
                if (flag) {//有阶梯数量
                    if (null != bidItemPricesListV && bidItemPricesListV.size() > 0) {
                        for (SrmPonBidItemPricesEntity_HI_RO k : bidItemPricesListV) {
                            Object[] lineResult = new Object[bidHeadersList.size() + 4];
                            lineResult[0] = k.getItemCode();
                            lineResult[1] = k.getItemDescription();
                            lineResult[2] = k.getCategoryName();
                            lineResult[3] = k.getLadderQuantity();
                            Object[] supplierNoTaxPrice = getNoTaxPrice(auctionHeaderId, auctionLineId, k.getItemLadderId(), subsectionFlag, bidHeadersList);
                            for (Integer i = 0; i < supplierNoTaxPrice.length; i++) {
                                lineResult[i + 4] = supplierNoTaxPrice[i];
                            }
                            bidItemPricesList.add(lineResult);
                        }
                    }
                } else {//无阶梯数量
                    if (null != bidItemPricesListV && bidItemPricesListV.size() > 0) {
                        SrmPonBidItemPricesEntity_HI_RO bidItemPricesEntity = bidItemPricesListV.get(0);
                        Object[] lineResult = new Object[bidHeadersList.size() + 4];
                        lineResult[0] = bidItemPricesEntity.getItemCode();
                        lineResult[1] = bidItemPricesEntity.getItemDescription();
                        lineResult[2] = bidItemPricesEntity.getCategoryName();
                        lineResult[3] = bidItemPricesEntity.getQuantity();
                        for (SrmPonBidItemPricesEntity_HI_RO k : bidItemPricesListV) {
                            Object[] supplierNoTaxPrice = getNoTaxPrice(auctionHeaderId, auctionLineId, null, subsectionFlag, bidHeadersList);
                            for (Integer i = 0; i < supplierNoTaxPrice.length; i++) {
                                lineResult[i + 4] = supplierNoTaxPrice[i];
                            }
                        }
                        bidItemPricesList.add(lineResult);
                    }
                }
            }
            jsonData.put("bidItemPricesList", bidItemPricesList);
        }
        List<SrmPonBidHeadersEntity_HI_RO> bidHeadersListNew = getTotalAccount(auctionLineIdList, bidHeadersList, subsectionFlag);//计算供应商报价的报价总计金额，中标总计金额
        jsonData.put("supplierList", bidHeadersListNew);
        return SToolUtils.convertResultJSONObj("S", "查询成功", 1, jsonData);
    }

    /**
     * Description：决标汇总模态框的查询（已截止）——获取供应商报价行的不含税单价，并且判断是否中标
     *
     * @param auctionHeaderId 寻源单据ID
     * @param auctionLineId   寻源标的物ID
     * @param itemLadderId    寻源标的物阶梯行ID
     * @param subsectionFlag  是否分段价格标识
     * @param bidHeadersList  报价头信息列表
     * @return Object[]
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public Object[] getNoTaxPrice(Integer auctionHeaderId, Integer auctionLineId, Integer itemLadderId, String subsectionFlag, List<SrmPonBidHeadersEntity_HI_RO> bidHeadersList) {
        Object[] supplierNoTaxPrice = new Object[bidHeadersList.size()]; //存放供应商报价行的不含税单价以及中标状态情况
        Integer index = -1;
        for (SrmPonBidHeadersEntity_HI_RO k : bidHeadersList) {
            index++;
            StringBuffer sb = new StringBuffer();
            sb.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BIDITEMPRICELIST_SQL);
            sb.append(" AND t.bid_header_id = " + k.getBidHeaderId());
            sb.append(" AND t.auction_line_id = " + auctionLineId);
            sb.append(" AND t.auction_header_id = " + auctionHeaderId);
            if (null != subsectionFlag && "Y".equals(subsectionFlag)) {
                if (null != itemLadderId && !"".equals(itemLadderId)) {
                    sb.append(" AND t.item_ladder_id = " + itemLadderId);
                }
            }
            List<SrmPonBidItemPricesEntity_HI_RO> bidItemPricesListRO = srmPonBidItemPricesDAO_HI_RO.findList(sb);
            if (bidItemPricesListRO == null || bidItemPricesListRO.size() == 0 || "".equals(bidItemPricesListRO)) {
                supplierNoTaxPrice[index] = new SrmPonBidItemPricesEntity_HI_RO();
            } else {
                supplierNoTaxPrice[index] = bidItemPricesListRO.get(0);
            }
        }
        return supplierNoTaxPrice;
    }

    /**
     * Description：决标汇总模态框的查询（已截止）——获取供应商报价头表的报价总计金额与中标总计金额
     *
     * @param auctionLineIdList 寻源标的物ID集合
     * @param bidHeadersList    报价头信息列表
     * @param subsectionFlag    是否分段价格标识
     * @return List<SrmPonBidHeadersEntity_HI_RO>
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<SrmPonBidHeadersEntity_HI_RO> getTotalAccount(Set<Integer> auctionLineIdList, List<SrmPonBidHeadersEntity_HI_RO> bidHeadersList, String subsectionFlag) {
        boolean flag = false;
        if (null != subsectionFlag && "Y".equals(subsectionFlag)) {
            flag = true;
        }
        List<SrmPonBidItemPricesEntity_HI_RO> bidItemPricesListRO = null;
        int isWinCount = 0;
        String itemType = bidHeadersList.get(0).getItemType();
        for (SrmPonBidHeadersEntity_HI_RO k : bidHeadersList) {
            if ("4".equals(k.getAwardStatus())) {
                isWinCount++;
            }
        }

        for (SrmPonBidHeadersEntity_HI_RO k : bidHeadersList) {
            BigDecimal totalAccountOffer = new BigDecimal(0);//报价总计金额
            BigDecimal totalAccountBiddinng = new BigDecimal(0);//中标总计金额
            for (Integer auctionLineId : auctionLineIdList) {
                StringBuffer sb = new StringBuffer();
                sb.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BIDITEMPRICELIST_SQL);
                sb.append(" AND t.bid_header_id = " + k.getBidHeaderId());
                sb.append(" AND t.auction_line_id = " + auctionLineId);
                sb.append(" AND t.auction_header_id = " + k.getAuctionHeaderId());
                if (flag) {
                    sb.append(" ORDER BY spail.ladder_quantity ASC ");
                }
                bidItemPricesListRO = srmPonBidItemPricesDAO_HI_RO.findList(sb);

                //TODO 如果存在议价，用议价数据计算

                if (null != bidItemPricesListRO && bidItemPricesListRO.size() > 0) {
                    SrmPonBidItemPricesEntity_HI_RO bidItemPrices = bidItemPricesListRO.get(0);
                    if (flag) {//有阶梯数量情况
                        if (null != bidItemPrices.getNoTaxPrice() && !"".equals(bidItemPrices.getNoTaxPrice())
                                && null != bidItemPrices.getLadderQuantity() && !"".equals(bidItemPrices.getLadderQuantity())) {
                            totalAccountOffer = totalAccountOffer.add(("LOGISTICS".equals(itemType) ? bidItemPrices.getTaxPrice() : bidItemPrices.getNoTaxPrice()).multiply(bidItemPrices.getLadderQuantity()));
                            //totalAccountOffer = totalAccountOffer.add(bidItemPrices.getNoTaxPrice().multiply(bidItemPrices.getLadderQuantity()));
                            if (null != bidItemPrices.getAwardStatus() && "4".equals(bidItemPrices.getAwardStatus())) {//中标情况
                                totalAccountBiddinng = totalAccountBiddinng.add(("LOGISTICS".equals(itemType) ? bidItemPrices.getTaxPrice() : bidItemPrices.getNoTaxPrice()).multiply(bidItemPrices.getLadderQuantity()));
                                //totalAccountBiddinng = totalAccountBiddinng.add(bidItemPrices.getNoTaxPrice().multiply(bidItemPrices.getLadderQuantity()));
                            }
                        }
                    } else {//无阶梯数量的情况
                        if (null != bidItemPrices.getQuantity() && !"".equals(bidItemPrices.getQuantity())
                                && null != bidItemPrices.getNoTaxPrice() && !"".equals(bidItemPrices.getNoTaxPrice())) {
                            totalAccountOffer = totalAccountOffer.add(("LOGISTICS".equals(itemType) ? bidItemPrices.getTaxPrice() : bidItemPrices.getNoTaxPrice()).multiply(bidItemPrices.getQuantity()));
                            //totalAccountOffer = totalAccountOffer.add(bidItemPrices.getNoTaxPrice().multiply(bidItemPrices.getQuantity()));
                            //分两种情况，如果只有一个供应商中标，且中标份额为空，就默认为100。
                            if (null != bidItemPrices.getAwardStatus() && "4".equals(bidItemPrices.getAwardStatus())
                                    && null != bidItemPrices.getAwardProportion() && !"".equals(bidItemPrices.getAwardProportion())) {
                                BigDecimal number = bidItemPrices.getAwardProportion().multiply(("LOGISTICS".equals(itemType) ? bidItemPrices.getTaxPrice() : bidItemPrices.getNoTaxPrice()).multiply(bidItemPrices.getQuantity()));
                                //BigDecimal number = bidItemPrices.getAwardProportion().multiply(bidItemPrices.getNoTaxPrice().multiply(bidItemPrices.getQuantity()));
                                totalAccountBiddinng = totalAccountBiddinng.add(number.divide(new BigDecimal(100)));
                            } else if (isWinCount == 1 && (null == bidItemPrices.getAwardProportion() || !"".equals(bidItemPrices.getAwardProportion())) && null != bidItemPrices.getAwardStatus() && "4".equals(bidItemPrices.getAwardStatus())) {
                                BigDecimal number = ("LOGISTICS".equals(itemType) ? bidItemPrices.getTaxPrice() : bidItemPrices.getNoTaxPrice()).multiply(bidItemPrices.getQuantity());
                                //BigDecimal number = bidItemPrices.getNoTaxPrice().multiply(bidItemPrices.getQuantity());
                                totalAccountBiddinng = totalAccountBiddinng.add(number.divide(new BigDecimal(1)));
                            }
                        }
                    }
                }
            }
            Integer numOffer = totalAccountOffer.compareTo(new BigDecimal(0));
            if (numOffer > 0) {
                k.setTotalAccountOffer(totalAccountOffer);
            }
            Integer numBidding = totalAccountBiddinng.compareTo(new BigDecimal(0));
            if (numBidding > 0) {
                k.setTotalAccountBiddinng(totalAccountBiddinng);
            }
        }
        return bidHeadersList;
    }

    /**
     * Description：标的物的中标按钮=====供应商报价行的中标操作
     *
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject updatePonBidItemPriceAwardStatus(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        boolean flag = true;
        Integer userId = jsonParams.getInteger("varUserId");
        Integer bidHeaderId = jsonParams.getInteger("bidHeaderId");//供应商报价头表的主键Id
        Integer auctionLineId = jsonParams.getInteger("auctionLineId");//标的物的Id
        String awardStatus = jsonParams.getString("awardStatus");//中标状态
        BigDecimal awardProportion = jsonParams.getBigDecimal("awardProportion");//中标份额
        BigDecimal awardQuantity = jsonParams.getBigDecimal("awardQuantity");//中标数量
        BigDecimal quantity = jsonParams.getBigDecimal("quantity");
        String subsectionFlag = "";//分段价格
        if (null != jsonParams.getString("subsectionFlag") && !"".equals(jsonParams.getString("subsectionFlag"))) {
            subsectionFlag = jsonParams.getString("subsectionFlag");
        }
        if (bidHeaderId == null || "".equals(bidHeaderId)) {
            flag = false;
            return SToolUtils.convertResultJSONObj("E", "供应商报价头表，参数为" + bidHeaderId, 0, null);
        }
        if (auctionLineId == null || "".equals(auctionLineId)) {
            flag = false;
            return SToolUtils.convertResultJSONObj("E", "标的物，参数为" + auctionLineId, 0, null);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("auctionLineId", auctionLineId);
        map.put("bidHeaderId", bidHeaderId);
        List<SrmPonBidItemPricesEntity_HI> bidItemPricesListV = srmPonBidItemPricesDAO_HI.findByProperty(map);
        if (null == bidItemPricesListV || bidItemPricesListV.size() == 0 || "".equals(bidItemPricesListV)) {
            flag = false;
            return SToolUtils.convertResultJSONObj("E", "无此记录，参数为", 0, null);
        }
        SrmPonBidHeadersEntity_HI bidHeadersEntity = srmPonBidHeadersDao_HI.getById(bidHeaderId);//查出供应商报价头表
        List<SrmPonBidItemPricesEntity_HI> bidItemPricesList = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", bidHeaderId); //所有供应商报价行
        if (flag) {
            String awardStatusStr = null;
            String awardStatusNew = null;
            if (awardStatus == null || "".equals(awardStatus)) {//此时供应商报价行的中标状态为空
                awardStatusNew = "4";
                awardStatusStr = "已中标";
            } else if ("3".equals(awardStatus)) {
                awardStatusNew = "4";
                awardStatusStr = "已中标";
            } else if ("4".equals(awardStatus)) {
                awardStatusNew = "3";
                awardStatusStr = "未中标";
            }
            for (SrmPonBidItemPricesEntity_HI k : bidItemPricesListV) {
                k.setAwardStatus(awardStatusNew);
                k.setOperatorUserId(userId);
                if ("3".equals(awardStatusNew)) {
                    k.setAwardDate(null);
                    k.setAwardProportion(null);//中标份额置空
                    k.setAwardQuantity(null);//中标数量置空
                } else if ("4".equals(awardStatusNew)) {
                    if (null != subsectionFlag && "Y".equals(subsectionFlag)) {
                        k.setAwardQuantity(k.getPromisedQuantity());//当有阶梯数量（分段价格勾选）时，承若数量赋值给中标数量
                    }
                    if (null != awardProportion && !"".equals(awardProportion)) {
                        k.setAwardProportion(awardProportion);//中标份额
                    }
                    /*if (null != quantity && !"".equals(quantity) && null != awardProportion && !"".equals(awardProportion)) {
                        BigDecimal awardQuantity = (awardProportion.multiply(quantity).divide(new BigDecimal(100)));
                        k.setAwardQuantity(awardQuantity);//中标数量
                    }*/
                    if (null != awardQuantity && !"".equals(awardQuantity)) {
                        k.setAwardProportion(awardQuantity);//中标数量
                    }
                    k.setAwardDate(new Date());
                }
            }
            //1、供应商报价行为中标状态
            if ("4".equals(awardStatusNew)) {
                if (null == bidHeadersEntity.getAwardStatus() || "".equals(bidHeadersEntity.getAwardStatus())) {//此时供应商报价头表的中标状态为空
                    bidHeadersEntity.setAwardStatus(awardStatusNew);
                    bidHeadersEntity.setAwardDate(new Date());
                    bidHeadersEntity.setOperatorUserId(userId);
                    srmPonBidItemPricesDAO_HI.saveOrUpdateAll(bidItemPricesListV);
                    srmPonBidHeadersDao_HI.saveEntity(bidHeadersEntity);
                    return SToolUtils.convertResultJSONObj("S", awardStatusStr, 1, null);
                } else if (!awardStatusNew.equals(bidHeadersEntity.getAwardStatus())) {//此时供应商报价头表的中标状态不是4，即是非中标状态
                    bidHeadersEntity.setAwardStatus(awardStatusNew);
                    bidHeadersEntity.setAwardDate(new Date());
                    bidHeadersEntity.setOperatorUserId(userId);
                    srmPonBidItemPricesDAO_HI.saveOrUpdateAll(bidItemPricesListV);
                    srmPonBidHeadersDao_HI.saveEntity(bidHeadersEntity);
                    return SToolUtils.convertResultJSONObj("S", awardStatusStr, 1, null);
                } else if ("4".equals(bidHeadersEntity.getAwardStatus())) {//此时供应商报价头表的中标状态也是4
                    srmPonBidItemPricesDAO_HI.saveOrUpdateAll(bidItemPricesListV);
                    return SToolUtils.convertResultJSONObj("S", awardStatusStr, 1, null);
                }
            } else if ("3".equals(awardStatusNew)) {
                //2、供应商报价行为未中标状态，即是3
                if (null == bidHeadersEntity.getAwardStatus() || "".equals(bidHeadersEntity.getAwardStatus())) {//此时供应商报价头表的中标状态为空
                    srmPonBidItemPricesDAO_HI.saveOrUpdateAll(bidItemPricesListV);
                    return SToolUtils.convertResultJSONObj("S", awardStatusStr, 1, null);
                } else if (awardStatusNew.equals(bidHeadersEntity.getAwardStatus())) {//此时供应商报价头表的中标状态是3，即未中标状态
                    srmPonBidItemPricesDAO_HI.saveOrUpdateAll(bidItemPricesListV);
                    return SToolUtils.convertResultJSONObj("S", awardStatusStr, 1, null);
                } else if ("4".equals(bidHeadersEntity.getAwardStatus())) {//此时供应商报价头表的中标状态是4，即中标状态
                    boolean temp = false;
                    for (SrmPonBidItemPricesEntity_HI k : bidItemPricesList) {
                        //存在其他的供应商报价行的中标状态是已中标，即与供应商报价头表的中标状态一致
                        if (null != k.getAwardStatus() && k.getAwardStatus().equals(bidHeadersEntity.getAwardStatus()) && k.getAuctionLineId() != auctionLineId) {
                            temp = true;
                            break;
                        }
                    }
                    if (temp) {
                        srmPonBidItemPricesDAO_HI.saveOrUpdateAll(bidItemPricesListV);
                        return SToolUtils.convertResultJSONObj("S", awardStatusStr, 1, null);
                    } else {
                        bidHeadersEntity.setAwardStatus(awardStatusNew);
                        bidHeadersEntity.setAwardDate(null);//中标时间设置为null
                        bidHeadersEntity.setOperatorUserId(userId);
                        srmPonBidItemPricesDAO_HI.saveOrUpdateAll(bidItemPricesListV);
                        srmPonBidHeadersDao_HI.saveEntity(bidHeadersEntity);
                        return SToolUtils.convertResultJSONObj("S", awardStatusStr, 1, null);
                    }
                }
            }
        }
        return SToolUtils.convertResultJSONObj("E", "没有操作", 0, null);
    }

    /**
     * Description：已截止的保存操作（保存、提交按钮，包括保存中标份额、决标意见）
     *
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveAwardProportionAll(JSONObject jsonParams) throws Exception {
        LOGGER.info("参数：" + jsonParams.toJSONString());
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        String action = jsonParams.getString("action"); // 操作按钮
        //TODO中标份额保存方法需要重写
        if (null == action || "".equals(action)) {
            return SToolUtils.convertResultJSONObj("E", "操作字符为空", 0, null);
        }
        SrmPonAuctionHeadersEntity_HI auctionHeader = JSON.parseObject(jsonParams.toJSONString(), SrmPonAuctionHeadersEntity_HI.class); // 洽谈头层
        List<SrmPonBidItemPricesEntity_HI> ponBidItemPricesList = null;
        JSONArray awardProportionListJSON = null;
        if (!(jsonParams.getJSONArray("ponBidItemPricesList") == null || "".equals(jsonParams.getJSONArray("ponBidItemPricesList")))) {
            JSONArray ponBidItemPricesListJSON = jsonParams.getJSONArray("ponBidItemPricesList");
            ponBidItemPricesList = JSON.parseArray(ponBidItemPricesListJSON.toJSONString(), SrmPonBidItemPricesEntity_HI.class);
        }
        /*if (!(jsonParams.getJSONArray("awardProportionList") == null || "".equals(jsonParams.getJSONArray("awardProportionList")))) {
            awardProportionListJSON = jsonParams.getJSONArray("awardProportionList");
        }*/
        if (!(jsonParams.getJSONArray("awardQuantityList") == null || "".equals(jsonParams.getJSONArray("awardQuantityList")))) {
            awardProportionListJSON = jsonParams.getJSONArray("awardQuantityList");
        }
        boolean flag = false;
        if (null != auctionHeader.getSubsectionFlag() && "Y".equals(auctionHeader.getSubsectionFlag())) {
            flag = true;//有阶梯数量
        }
        auctionHeader.setOperatorUserId(userId);
        if ("save".equals(action)) {//保存按钮
            srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeader);
            if (!flag) {
                savePonBidItemPricesList(userId, auctionHeader.getAuctionHeaderId(), ponBidItemPricesList, awardProportionListJSON);
            }
            jsonData.put("auctionHeaderId", auctionHeader.getAuctionHeaderId());
            return SToolUtils.convertResultJSONObj("S", "保存成功", 1, jsonData);
        } else if ("submit".equals(action)) {//提交按钮
            Map<String, Object> map = new HashMap<>();
            map.put("auctionHeaderId", auctionHeader.getAuctionHeaderId());
            map.put("bidStatus", "ACT");
            List<SrmPonBidHeadersEntity_HI> bidHeadersList = srmPonBidHeadersDao_HI.findByProperty(map);//查询所有供应商报价
            if (null == bidHeadersList || bidHeadersList.size() == 0 || "".equals(bidHeadersList)) {//无供应商报价
                /*update by zwj start 在完成评标之后，点击提交按钮，点击审批按钮之后，标书状态变为了已完成*/
                /*auctionHeader.setAuctionStatus("CANCELED");//取消状态*/
                auctionHeader.setAuctionStatus("COMPLETED");//已完成
                /*update by zwj end 在完成评标之后，点击提交按钮，点击审批按钮之后，标书状态变为了已完成*/
                auctionHeader.setJudgeCompleteDate(new Date());//评标完成时间
                srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeader);
                if (!flag) {
                    savePonBidItemPricesList(userId, auctionHeader.getAuctionHeaderId(), ponBidItemPricesList, awardProportionListJSON);
                }
                jsonData.put("auctionHeaderId", auctionHeader.getAuctionHeaderId());
                return SToolUtils.convertResultJSONObj("S", "提交成功", 1, jsonData);
            }
            List<SrmPonBidItemPricesEntity_HI> bidItemPricesList = new ArrayList<>();
            for (SrmPonBidHeadersEntity_HI k : bidHeadersList) {
                List<SrmPonBidItemPricesEntity_HI> bidItemPricesListNew = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", k.getBidHeaderId()); //所有供应商报价行
                if (null != bidItemPricesListNew && bidItemPricesListNew.size() > 0) {
                    bidItemPricesList.addAll(bidItemPricesListNew);
                }
            }
            if (null == bidItemPricesList || bidItemPricesList.size() == 0) {//没有任何一条供应商报价行
                /*update by zwj start  在完成评标之后，点击提交按钮，点击审批按钮之后，标书状态变为了已完成*/
                /*auctionHeader.setAuctionStatus("CANCELED");//取消状态*/
                auctionHeader.setAuctionStatus("COMPLETED");//完成状态
                /*update by zwj end 在完成评标之后，点击提交按钮，点击审批按钮之后，标书状态变为了已完成*/
                auctionHeader.setJudgeCompleteDate(new Date());//评标完成时间
                auctionHeader.setAwardStatus("2");//已决标
                auctionHeader.setAwardCompleteDate(new Date());//决标完成时间

                List<SrmPonAuctionItemsEntity_HI> ponAuctionItemsList = srmPonAuctionItemsDAO_HI.findByProperty("auctionHeaderId", auctionHeader.getAuctionHeaderId());
                if (null != ponAuctionItemsList && ponAuctionItemsList.size() > 0) {
                    for (SrmPonAuctionItemsEntity_HI k : ponAuctionItemsList) {
                        k.setOperatorUserId(userId);
                        k.setAwardStatus("2");//已决标
                    }
                    srmPonAuctionItemsDAO_HI.update(ponAuctionItemsList);//所有的标的物
                }
                srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeader);
                if (!flag) {
                    savePonBidItemPricesList(userId, auctionHeader.getAuctionHeaderId(), ponBidItemPricesList, awardProportionListJSON);
                }
                jsonData.put("auctionHeaderId", auctionHeader.getAuctionHeaderId());
                return SToolUtils.convertResultJSONObj("S", "提交成功", 1, jsonData);
            }
            auctionHeader.setAwardApprovalStatus("APPROVING");//决标审批状态为审批中
            auctionHeader.setJudgeCompleteDate(new Date());//评标完成时间
            srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeader);
            if (!flag) {
                savePonBidItemPricesList(userId, auctionHeader.getAuctionHeaderId(), ponBidItemPricesList, awardProportionListJSON);
            }
            if ("INFORMATION_TECHNOLOGY".equals(auctionHeader.getItemType()) || "ENGINEERING".equals(auctionHeader.getItemType())) {
                //获取EKP流程编号
                JSONArray jsonArray = findEkpId(auctionHeader.getEkpNumber());
                if (!ObjectUtils.isEmpty(jsonArray)) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    //查询节点快码
                    if ("ENGINEERING".equals(auctionHeader.getItemType())) {
                        //查询业务实体区域
                        StringBuffer instRegionString = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_INST_REGION);
                        instRegionString.append(" and  siv.Inst_Id=" + auctionHeader.getOrgId());
                        List<SrmPonAuctionHeadersEntity_HI_RO> instRegionList = srmPonAuctionHeadersDAO_HI_RO.findList(instRegionString.toString());
                        if (null == instRegionList || instRegionList.isEmpty()) {
                            throw new RuntimeException("无此业务实体");
                        }
                        String instRegion = instRegionList.get(0).getInstRegion();

                        Map<String, Object> mapV = new HashMap<>();
                        mapV.put("tag", "10");

                        if ("SHENZHEN".equals(instRegion)) { //深圳
                            mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE");
                        } else if ("NANTONG".equals(instRegion)) { //南通
                            mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE_NT");
                        } else if ("WUXI".equals(instRegion)) {//无锡
                            mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE_WX");
                        }
                        List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                        String node = "";
                        if (null != lookupValueList && lookupValueList.size() > 0) {
                            node = lookupValueList.get(0).getLookupCode();
                        } else {
                            throw new UtilsException("请在快码中配置招标询价EKP流程接入节点！");
                        }
                        if (!ObjectUtils.isEmpty(jsonObject.getString("fdFactNodeId")) && !ObjectUtils.isEmpty(jsonObject.getString("fdStatus")) && node.equals(jsonObject.getString("fdFactNodeId")) && "20".equals(jsonObject.getString("fdStatus"))) {
                            auctionHeader.setAttribute1(jsonObject.getString("fdId"));
                            auctionHeader.setOperatorUserId(userId);
                            srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeader);
                            srmPonAuctionHeadersDAO_HI.fluch();
                            //更新EKP流程
                            JSONObject data = savePonAuctionToEkp(auctionHeader.getAuctionHeaderId(), userId, jsonParams);
                            if (!"S".equals(data.getString("status"))) {
                                throw new UtilsException(data.getString("msg"));
                            }
                        } else {
                            throw new UtilsException("当前EKP流程节点非" + node + "或审批状态非审批中！");
                        }
                    } else if ("INFORMATION_TECHNOLOGY".equals(auctionHeader.getItemType())) {
                        Map<String, Object> mapV = new HashMap<>();
                        mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE_IT");
                        mapV.put("tag", "10");
                        List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                        if (null == lookupValueList || lookupValueList.size() <= 0) {
                            throw new UtilsException("请在快码中配置IT招标询价EKP流程接入节点！");
                        }
                        List<String> lookCodeList = lookupValueList.stream().map(SaafLookupValuesEntity_HI::getLookupCode).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList()).stream().filter(String -> String != null).collect(Collectors.toList());
                        if (!ObjectUtils.isEmpty(jsonObject.getString("fdFactNodeId")) && !ObjectUtils.isEmpty(jsonObject.getString("fdStatus")) && lookCodeList.contains(jsonObject.getString("fdFactNodeId")) && "20".equals(jsonObject.getString("fdStatus"))) {
                            auctionHeader.setAttribute1(jsonObject.getString("fdId"));
                            auctionHeader.setOperatorUserId(userId);
                            srmPonAuctionHeadersDAO_HI.saveEntity(auctionHeader);
                            //更新EKP流程
                            JSONObject data = savePonAuctionToEkp(auctionHeader.getAuctionHeaderId(), userId, jsonParams);
                            if (!"S".equals(data.getString("status"))) {
                                throw new UtilsException(data.getString("msg"));
                            }
                        } else {
                            String node = "";
                            for (String roNode : lookCodeList) {
                                node = node + "  " + roNode;
                            }
                            throw new UtilsException("当前EKP流程节点非" + node + "或审批状态非审批中！");
                        }

                    }

                }

            }
            jsonData.put("auctionHeaderId", auctionHeader.getAuctionHeaderId());
            return SToolUtils.convertResultJSONObj("S", "提交成功", 1, jsonData);
        }
        return SToolUtils.convertResultJSONObj("E", "没有操作", 0, null);
    }

    /**
     * Description：保存供应商标报价行list
     *
     * @param userId               操作者ID
     * @param auctionHeaderId      寻源单据ID
     * @param ponBidItemPricesList 报价行信息列表
     * @return void
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public void savePonBidItemPricesList(Integer userId, Integer auctionHeaderId, List<SrmPonBidItemPricesEntity_HI> ponBidItemPricesList, JSONArray awardProportionListJSON) {
        for (int i = 0; i < awardProportionListJSON.size(); i++) {
            JSONObject awardProportion = JSONObject.parseObject(awardProportionListJSON.get(i).toString());
            //<editor-fold desc="查询报价头">
            StringBuffer queryHString = new StringBuffer(SrmPonBidHeadersEntity_HI_RO.QUERY_BIDHEADERS);
            Map queryHMap = new HashMap();
            queryHString.append(" and a.auction_header_id = :auctionHeaderId ");
            queryHString.append(" and a.supplier_id = :supplierId ");
            queryHString.append(" and a.bid_status = :bidStatus");
            queryHMap.put("auctionHeaderId", auctionHeaderId);
            queryHMap.put("supplierId", awardProportion.getInteger("supplierId"));
            queryHMap.put("bidStatus", "ACT");
            List<SrmPonBidHeadersEntity_HI_RO> bidHeaders = srmPonBidHeadersDAO_HI_RO.findList(queryHString, queryHMap);
            //</editor-fold>
            //<editor-fold desc="查询报价行">
            if (bidHeaders.size() > 0) {
                StringBuffer queryString = new StringBuffer(SrmPonBidItemPricesEntity_HI_RO.QUERY_BID_ITEMS_PRICES_SQL);
                Map queryMap = new HashMap();
                queryString.append(" and t.auction_header_id = :auctionHeaderId ");
                queryString.append(" and t.bid_header_id = :bidHeaderId");
                queryString.append(" and t.auction_line_id = :auctionLineId");
                queryMap.put("auctionHeaderId", auctionHeaderId);
                queryMap.put("bidHeaderId", bidHeaders.get(0).getBidHeaderId());
                queryMap.put("auctionLineId", awardProportion.getInteger("auctionLineId"));
                List<SrmPonBidItemPricesEntity_HI_RO> bidPriceLists = srmPonBidItemPricesDAO_HI_RO.findList(queryString, queryMap);
                //<editor-fold desc="保存报价行">
                if (null != bidPriceLists && bidPriceLists.size() > 0) {
                    int countAward = 0;
                    for (SrmPonBidItemPricesEntity_HI_RO k : bidPriceLists) {
                        SrmPonBidItemPricesEntity_HI bidItemPricesEntity = null;
                        if (null != k.getBidLineId()) {
                            bidItemPricesEntity = srmPonBidItemPricesDAO_HI.getById(k.getBidLineId());
                            if (null != bidItemPricesEntity) {
                                bidItemPricesEntity.setOperatorUserId(userId);
                                bidItemPricesEntity.setAuctionHeaderId(auctionHeaderId);
                                // bidItemPricesEntity.setAwardProportion(awardProportion.getBigDecimal("awardProportion"));//中标份额
                                bidItemPricesEntity.setAwardQuantity(awardProportion.getBigDecimal("awardQuantity"));//中标数量
                                //if(null != bidItemPricesEntity.getAwardProportion()){
                                if (null != bidItemPricesEntity.getAwardQuantity()) {
                                    countAward++;
                                    bidItemPricesEntity.setAwardDate(new Date());
                                    bidItemPricesEntity.setAwardStatus("4");
                                }
                                SrmPonAuctionItemsEntity_HI itemsEntity = null;
                                if (null != k.getAuctionLineId() && !"".equals(k.getAuctionLineId())) {
                                    itemsEntity = srmPonAuctionItemsDAO_HI.getById(k.getAuctionLineId());
                                }
                                /*if (null != itemsEntity) {
                                    if (null != itemsEntity.getQuantity() && !"".equals(itemsEntity.getQuantity())
                                            && null != bidItemPricesEntity.getAwardProportion() && !"".equals(bidItemPricesEntity.getAwardProportion())) {
                                        BigDecimal awardQuantity = (bidItemPricesEntity.getAwardProportion().multiply(itemsEntity.getQuantity())).divide(new BigDecimal(100));
                                        bidItemPricesEntity.setAwardQuantity(awardQuantity);//中标数量
                                    }
                                }*/
                                srmPonBidItemPricesDAO_HI.saveEntity(bidItemPricesEntity);
                            }
                        }

                    }
                    //<editor-fold desc="修改中标标识">
                    if (countAward > 0) {
                        SrmPonBidHeadersEntity_HI header = srmPonBidHeadersDao_HI.getById(bidHeaders.get(0).getBidHeaderId());
                        header.setAwardDate(new Date());
                        header.setAwardStatus("4");
                        header.setOperatorUserId(userId);
                        srmPonBidHeadersDao_HI.saveOrUpdate(header);
                    }
                    //</editor-fold>
                }
                //</editor-fold>
            }
            //</editor-fold>
        }
        /*if (null != ponBidItemPricesList && ponBidItemPricesList.size() > 0) {
            for (SrmPonBidItemPricesEntity_HI k : ponBidItemPricesList) {
                SrmPonBidItemPricesEntity_HI bidItemPricesEntity = null;
                if (null == k.getBidLineId()) {
                    bidItemPricesEntity = new SrmPonBidItemPricesEntity_HI();
                } else {
                    bidItemPricesEntity = srmPonBidItemPricesDAO_HI.getById(k.getBidLineId());
                    if (null != bidItemPricesEntity) {
                        bidItemPricesEntity.setOperatorUserId(userId);
                        bidItemPricesEntity.setAuctionHeaderId(auctionHeaderId);
                        bidItemPricesEntity.setAwardProportion(k.getAwardProportion());//中标份额
                        SrmPonAuctionItemsEntity_HI itemsEntity = null;
                        if (null != k.getAuctionLineId() && !"".equals(k.getAuctionLineId())) {
                            itemsEntity = srmPonAuctionItemsDAO_HI.getById(k.getAuctionLineId());
                        }
                        if (null != itemsEntity) {
                            if (null != itemsEntity.getQuantity() && !"".equals(itemsEntity.getQuantity())
                                    && null != k.getAwardProportion() && !"".equals(k.getAwardProportion())) {
                                BigDecimal awardQuantity = (k.getAwardProportion().multiply(itemsEntity.getQuantity())).divide(new BigDecimal(100));
                                bidItemPricesEntity.setAwardQuantity(awardQuantity);//中标数量
                            }
                        }
                        srmPonBidItemPricesDAO_HI.saveEntity(bidItemPricesEntity);
                    }
                }

            }
        }*/
    }

    /**
     * Description：获取标的物的信息，根据auctionLineId，auctionHeaderId，bidHeaderId，flag——用于决标汇总的导出（已截止）
     *
     * @param auctionLineId   寻源标的物ID
     * @param auctionHeaderId 寻源单据ID
     * @param bidHeadersList  报价头信息列表
     * @param flag            阶梯数量标识
     * @return List<SrmPonBidItemPricesEntity_HI_RO>
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<SrmPonBidItemPricesEntity_HI_RO> findBidItemPricesList(Integer auctionLineId, Integer auctionHeaderId, List<SrmPonBidHeadersEntity_HI_RO> bidHeadersList, boolean flag) {
        List<SrmPonBidItemPricesEntity_HI_RO> bidItemPricesList = new ArrayList<>();
        for (SrmPonBidHeadersEntity_HI_RO k : bidHeadersList) {
            StringBuffer sb = new StringBuffer();
            if (flag) {
                sb.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BIDITEMPRICESLIST_SQL);
                sb.append(" AND pbip.auction_line_id = " + auctionLineId);
                sb.append(" AND pbip.auction_header_id = " + auctionHeaderId);
                sb.append(" AND pbip.bid_header_id = " + k.getBidHeaderId());
                sb.append(" ORDER BY pbip.auction_line_id ASC,pbip.bid_header_id ASC ");
                bidItemPricesList = srmPonBidItemPricesDAO_HI_RO.findList(sb);
            } else {//无阶梯数量
                sb.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BIDITEMPRICESLISTV_SQL);
                sb.append(" AND pbip.auction_line_id = " + auctionLineId);
                sb.append(" AND pbip.auction_header_id = " + auctionHeaderId);
                sb.append(" AND pbip.bid_header_id = " + k.getBidHeaderId());
                bidItemPricesList = srmPonBidItemPricesDAO_HI_RO.findList(sb);
            }
            if (null != bidItemPricesList && bidItemPricesList.size() > 0) {
                break;
            }
        }
        return bidItemPricesList;
    }

    /**
     * Description：报价供应商中标按钮（已截止）——标的物中标状态改变，即是供应商报价行的中标状态变动
     *
     * @param userId          操作者ID
     * @param auctionHeaderId 寻源单据ID
     * @param bidHeaderId     报价头ID
     * @param awardStatus     中标状态
     * @param subsectionFlag  阶梯数量标识
     * @return void
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public void updateBidItemPriceAwardStatusByBidHeaderSupplier(Integer userId, Integer auctionHeaderId, Integer bidHeaderId, String awardStatus, String subsectionFlag) {
        boolean flag = false;
        boolean temp = false;
        if (null != awardStatus && "4".equals(awardStatus)) {//已中标状态
            flag = true;
        }
        if (null != subsectionFlag && "Y".equals(subsectionFlag)) {
            temp = true;
        }
        Set<Integer> auctionLineIdList = new HashSet<>();//标的物auctionLineIdList
        List<SrmPonBidItemPricesEntity_HI> bidItemPricesList = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", bidHeaderId);
        if (null != bidItemPricesList && bidItemPricesList.size() > 0) {
            for (SrmPonBidItemPricesEntity_HI k : bidItemPricesList) {
                k.setOperatorUserId(userId);
                k.setAwardStatus(awardStatus);
                if ("4".equals(awardStatus)) {//中标
                    k.setAwardDate(new Date());
                    if (temp) {
                        k.setAwardQuantity(k.getPromisedQuantity());//当有阶梯数量（分段价格勾选）时，承若数量赋值给中标数量
                    }
                } else if ("3".equals(awardStatus)) {//未中标
                    k.setAwardDate(null);//中标时间置空
                    k.setAwardQuantity(null);//中标数量置空
                    k.setAwardProportion(null);//中标份额置空
                }
                if (flag && !temp) {//分段价格未勾选
                    SrmPonAuctionItemsEntity_HI itemsEntity = srmPonAuctionItemsDAO_HI.getById(k.getAuctionLineId());
                    if (null != itemsEntity) {
                        auctionLineIdList.add(itemsEntity.getAuctionLineId());
                        k.setAwardProportion(new BigDecimal(100));//中标份额为100
                        k.setAwardQuantity(itemsEntity.getQuantity());//中标数量
                    }
                }
            }
            srmPonBidItemPricesDAO_HI.update(bidItemPricesList);
            //处理其他供应商对此标的物的报价
            List<SrmPonBidHeadersEntity_HI> bidHeadersList = new ArrayList<>();
            if (flag && !temp) {//分段价格勾选，且为已中标的状态
                Map<String, Object> map = new HashMap<>();
                map.put("auctionHeaderId", auctionHeaderId);
                map.put("bidStatus", "ACT");
                bidHeadersList = srmPonBidHeadersDao_HI.findByProperty(map);
            }
            if (null != bidHeadersList && bidHeadersList.size() > 0) {
                Iterator<SrmPonBidHeadersEntity_HI> it = bidHeadersList.iterator();
                while (it.hasNext()) {
                    SrmPonBidHeadersEntity_HI k = it.next();
                    if (null != k.getBidHeaderId() && k.getBidHeaderId().equals(bidHeaderId)) {
                        it.remove();//去掉点击中标的供应商报价bidHeaderId
                        continue;
                    }
                    if (null == k.getAwardStatus() || "".equals(k.getAwardStatus()) || "3".equals(k.getAwardStatus())) {//去掉原本未中标的供应商报价的数据
                        it.remove();
                    }
                }
            }
            //处理bidHeadersList
            List<SrmPonBidHeadersEntity_HI> bidHeadersListV = new ArrayList<>();
            if (null != bidHeadersList && bidHeadersList.size() > 0) {
                List<SrmPonBidItemPricesEntity_HI> bidItemPricesListV = new ArrayList<>();//存储过滤后的供应商报价行的数据
                for (SrmPonBidHeadersEntity_HI k : bidHeadersList) {
                    boolean key = false;
                    List<SrmPonBidItemPricesEntity_HI> bidItemPricesListNew = srmPonBidItemPricesDAO_HI.findByProperty("bidHeaderId", k.getBidHeaderId());
                    if (null != bidItemPricesListNew && bidItemPricesListNew.size() > 0) {
                        for (SrmPonBidItemPricesEntity_HI tem : bidItemPricesListNew) {
                            if (null == tem.getAwardStatus() || "".equals(tem.getAwardStatus()) || "3".equals(tem.getAwardStatus())) {
                                continue;
                            }
                            for (Integer auctionLineId : auctionLineIdList) {
                                if (null != tem.getAuctionLineId() && tem.getAuctionLineId().equals(auctionLineId) && null != tem.getAwardStatus() && "4".equals(tem.getAwardStatus())) {
                                    key = true;
                                    bidItemPricesListV.add(tem);
                                    break;
                                }
                            }
                        }
                    }
                    if (key) {
                        k.setAwardStatus("3");//将供应商报价头表的中标状态置为为中标
                        k.setAwardDate(null);
                        k.setOperatorUserId(userId);
                        bidHeadersListV.add(k);
                    }
                }
                //排除后的供应商报价行的数据
                if (null != bidItemPricesListV && bidItemPricesListV.size() > 0) {
                    for (SrmPonBidItemPricesEntity_HI t : bidItemPricesListV) {
                        t.setAwardProportion(null);//中标份额置空
                        t.setAwardQuantity(null);////中标数量置空
                        t.setAwardDate(null);
                        t.setAwardStatus("3");//未中标
                        t.setOperatorUserId(userId);
                    }
                    srmPonBidItemPricesDAO_HI.save(bidItemPricesListV);
                }
                if (null != bidHeadersListV && bidHeadersListV.size() > 0) {
                    srmPonBidHeadersDao_HI.save(bidHeadersListV);
                }
            }
        }
    }

    /**
     * Description：查询供应商报价，议价信息--已截止
     *
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject findSupplierBidPrice(JSONObject jsonParams) {
        LOGGER.info("params:" + jsonParams.toString());
        JSONObject jsonData = new JSONObject();
        List<JSONObject> supplierDataList = new ArrayList<>();
        Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId");//洽谈Id

        List<SrmPonAuctionSuppliersEntity_HI_RO> supplierLists = null;
        StringBuffer querySupplier = new StringBuffer();
        querySupplier.append(SrmPonAuctionSuppliersEntity_HI_RO.QUERY_AUCTION_SUPPLIER_LIST_SQL);
        supplierLists = srmPonAuctionSuppliersDAO_HI_RO.findList(querySupplier, new Object[]{auctionHeaderId});
        if (null == supplierLists || supplierLists.size() == 0 || "".equals(supplierLists)) {
            jsonData.put("status", "S");
            jsonData.put("msg", "无数据");
            jsonData.put("count", 0);
            return jsonData;
        } else {
            for (int i = 0; i < supplierLists.size(); i++) {
                JSONObject supplierData = new JSONObject();
                List<JSONObject> bidPriceDataList = new ArrayList<>();
                supplierData.put("supplierId", supplierLists.get(i).getSupplierId());
                supplierData.put("supplierName", supplierLists.get(i).getSupplierName());

                List<SrmPonBidItemPricesEntity_HI_RO> bidItemPricesList = null;
                StringBuffer querySb = new StringBuffer();
                querySb.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BID_ITEMS_SQL);
                querySb.append(" AND t.auction_header_id = " + auctionHeaderId);
                querySb.append(" ORDER BY t.auction_line_id ASC ");
                bidItemPricesList = srmPonBidItemPricesDAO_HI_RO.findList(querySb);
                if (bidItemPricesList.size() > 0) {
                    for (SrmPonBidItemPricesEntity_HI_RO bidItemPrices : bidItemPricesList) {
                        JSONObject bidPriceData = new JSONObject();
                        StringBuffer sb = new StringBuffer();
                        Map<String, Object> map = new HashMap<>();
                        sb.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_SUPPLIER_BID_PRICE_SQL);
                        map.put("auctionHeaderId", auctionHeaderId);
                        map.put("supplierId", supplierLists.get(i).getSupplierId());
                        map.put("auctionLineId", bidItemPrices.getAuctionLineId());
                        List<SrmPonBidItemPricesEntity_HI_RO> bidPricesList = srmPonBidItemPricesDAO_HI_RO.findList(sb, map);
                        if (bidPricesList.size() > 0) {
                            bidPriceData.put("itemId", bidItemPrices.getItemId());
                            bidPriceData.put("auctionLineId", bidItemPrices.getAuctionLineId());
                            bidPriceData.put("taxPrice", bidPricesList.get(0).getTaxPrice());//含税单价
                            bidPriceData.put("noTaxPrice", bidPricesList.get(0).getNoTaxPrice());//不含税单价
                            bidPriceData.put("bargain", bidPricesList.get(0).getBargain());
                            bidPriceData.put("bargainTax", bidPricesList.get(0).getBargainTax());
                            bidPriceData.put("taxRate", bidPricesList.get(0).getTaxRate());
                            bidPriceData.put("rank", bidPricesList.get(0).getRank());
                            bidPriceData.put("awardProportion", bidPricesList.get(0).getAwardProportion());
                            bidPriceData.put("awardQuantity", bidPricesList.get(0).getAwardQuantity());
                        }
                        bidPriceDataList.add(bidPriceData);
                    }
                }
                StringBuffer checkSorce = new StringBuffer(SrmPonJuryScoreLinesEntity_HI_RO.QUERY_SCORE_LINES_ALL_SQL);
                Map<String, Object> checkSorceMap = new HashMap<>();
                checkSorce.append(" AND spasr.auction_header_id = :auctionHeaderId ");
                checkSorce.append(" AND spjsl.score is null ");
                checkSorceMap.put("auctionHeaderId", auctionHeaderId);
                List<SrmPonJuryScoreLinesEntity_HI_RO> checkSorceList = srmPonJuryScoreLinesDAO_HI_RO.findList(checkSorce, checkSorceMap);
                if (checkSorceList.size() > 0) {
                    supplierData.put("editPriceFlag", "N");
                }
                supplierData.put("bidPriceList", bidPriceDataList);
                supplierDataList.add(supplierData);
            }
            jsonData.put("supplierList", supplierDataList);
            return SToolUtils.convertResultJSONObj("S", "查询成功", 1, jsonData);
        }
    }

    /**
     * Description：提交招标询价发送至EKP系统
     *
     * @param auctionHeaderId 寻源单据ID
     * @param userId          操作者ID
     * @return JSONObject
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-05-29         xiexiaoxia      创建
     * =======================================================================
     */
    @Override
    public JSONObject savePonAuctionToEkp(Integer auctionHeaderId, Integer userId, JSONObject params) throws Exception {
        JSONObject obj = new JSONObject();
        if (null == auctionHeaderId || "".equals(auctionHeaderId)) {
            throw new UtilsException("传输EKP所需参数为空！");
        }
        SrmPonAuctionHeadersEntity_HI header = srmPonAuctionHeadersDAO_HI.getById(auctionHeaderId);
        if (null == header || "".equals(header)) {
            throw new UtilsException("无此记录，已被删除！");
        }

        String employeeNumber = "";
        List<SaafEmployeesEntity_HI> employeeList = saafEmployeesDAO_HI.findByProperty("userId", userId);
        if (null == employeeList || employeeList.isEmpty()) {
            throw new UtilsException("该用户没有对应员工信息！");
        }
        employeeNumber = employeeList.get(0).getEmployeeNumber();
        if (null == employeeNumber || "".equals(employeeNumber)) {
            throw new UtilsException("查无此员工工号！");
        }
        //查询EKP编号
        StringBuffer ekpSql = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_EKP_NUMBER);
        ekpSql.append(" and Spah.Ekp_Number= '" + header.getEkpNumber() + "' ");
        ekpSql.append(" and Spah.Auction_Header_Id != " + header.getAuctionHeaderId() + " ");
        List<SrmPonAuctionHeadersEntity_HI_RO> ekp = srmPonAuctionHeadersDAO_HI_RO.findList(ekpSql.toString());
        if (!ObjectUtils.isEmpty(ekp)) {
            String auctionNumber = "";
            for (int i = 0; i < ekp.size(); i++) {
                if (!"CLOSED".equals(ekp.get(i).getAuctionStatus())) {
                    auctionNumber = auctionNumber + "  " + ekp.get(i).getAuctionNumber();
                }
            }
            if (!StringUtils.isEmpty(auctionNumber)) {
                throw new UtilsException("同一EKP编号下已被驳回的寻源单据" + auctionNumber + "非已截止状态，无法发送EKP");
            }
        }
        //查询快码值对应的流程模板Id
        String tempateId = "";
        String itemType = params.getString("itemType");
        Map<String, Object> slvMap = new HashMap<>();
        slvMap.put("lookupType", "SER_EKF_FD_DATA");
        List<SaafLookupValuesEntity_HI> slvList = saafLookupValuesDAO_HI.findByProperty(slvMap);
        if (null == slvList || slvList.size() < 1) {
            throw new UtilsException("没有对应的流程模板Id！");
        }
        //查询业务实体区域
        StringBuffer instRegionString = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_INST_REGION);
        instRegionString.append(" and  siv.Inst_Id=" + header.getOrgId());
        List<SrmPonAuctionHeadersEntity_HI_RO> instRegionList = srmPonAuctionHeadersDAO_HI_RO.findList(instRegionString.toString());
        if (null == instRegionList || instRegionList.isEmpty()) {
            throw new UtilsException("无此业务实体");
        }
        String instRegion = instRegionList.get(0).getInstRegion();

        //工程/IT
        for (int i = 0; i < slvList.size(); i++) {
            if ("ENGINEERING".equals(itemType)) {
                //区域
                if ("SHENZHEN".equals(instRegion) && "SER_SZ_SPO_PROJECT".equals(slvList.get(i).getLookupCode())) { //深圳
                    tempateId = slvList.get(i).getMeaning();
                    break;
                } else if ("NANTONG".equals(instRegion) && "SER_NT_SPO_PROJECT".equals(slvList.get(i).getLookupCode())) { //南通
                    tempateId = slvList.get(i).getMeaning();
                    break;
                } else if ("WUXI".equals(instRegion) && "SER_WX_SPO_PROJECT".equals(slvList.get(i).getLookupCode())) {//无锡
                    tempateId = slvList.get(i).getMeaning();
                    break;
                }
            } else if ("INFORMATION_TECHNOLOGY".equals(itemType)) {
                tempateId = slvList.get(i).getMeaning();
                break;
            }
        }

        Map<String, Object> map = new HashMap<>();
        // 文件附件列表
        Map<String, Map<String, byte[]>> fileMap = new HashMap<>();
        //行附件列表
        JSONArray lineFileArray = new JSONArray();
        //头层的附件

        if ("ENGINEERING".equals(itemType)) {
            //中标
            List<String> supplierNameList = new ArrayList<>();//供应商名称
            List<String> supplierEbsNumberList = new ArrayList<>();//供应商EBS编码
            List<String> totalAccountOfferList = new ArrayList<>();//材料总价
            List<String> tranManageFeesList = new ArrayList<>();//运杂及管理费
            List<String> measuresFeesList = new ArrayList<>();//规费及措施费
            List<String> projectCostsList = new ArrayList<>();//工程造价
            List<String> engineeringTaxList = new ArrayList<>();//税金
            List<String> totalProjectCostList = new ArrayList<>();//工程总造价
            List<String> totalAccountOfferRankingList = new ArrayList<>();//报价排名
            List<String> taxRateNameList = new ArrayList<>();//供应商头税率
            List<String> awardStatusList = new ArrayList<>();//供应商是否已中标
            List<String> auctionNumberList = new ArrayList<>();//寻源单号
            List<String> auctionTitleList = new ArrayList<>();//SRM标题
            List<String> roundStatusList = new ArrayList<>();//状态

            //未中标
            List<String> supplierNameListN = new ArrayList<>();//供应商名称
            List<String> supplierEbsNumberListN = new ArrayList<>();//供应商EBS编码
            List<String> totalAccountOfferListN = new ArrayList<>();//材料总价
            List<String> tranManageFeesListN = new ArrayList<>();//运杂及管理费
            List<String> measuresFeesListN = new ArrayList<>();//规费及措施费
            List<String> projectCostsListN = new ArrayList<>();//工程造价
            List<String> engineeringTaxListN = new ArrayList<>();//税金
            List<String> totalProjectCostListN = new ArrayList<>();//工程总造价
            List<String> totalAccountOfferRankingListN = new ArrayList<>();//报价排名
            List<String> taxRateNameListN = new ArrayList<>();//供应商头税率
            List<String> awardStatusListN = new ArrayList<>();//供应商是否已中标
            List<String> auctionNumberListN = new ArrayList<>();//寻源单号
            List<String> auctionTitleListN = new ArrayList<>();//SRM标题
            List<String> roundStatusListN = new ArrayList<>();//状态

            //传Header
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("auctionHeaderId", header.getAuctionHeaderId());
            jsonParams.put("itemType", itemType);
            List<SrmPonBidHeadersEntity_HI_RO> headerSupplierInfo = iSrmPonBidHeaders.findBidHeadersSupplierList(jsonParams);
            LOGGER.info(" headerSupplierInfo :" + headerSupplierInfo);
            if (!ObjectUtils.isEmpty(headerSupplierInfo)) {
                for (SrmPonBidHeadersEntity_HI_RO ro : headerSupplierInfo) {
                    if ("4".equals(ro.getAwardStatus())) {
                        supplierNameList.add(ro.getSupplierName());
                        supplierEbsNumberList.add(ro.getSupplierEbsNumber());
                        totalAccountOfferList.add(String.valueOf(ro.getTotalAccountOffer()));
                        tranManageFeesList.add(String.valueOf(ro.getTranManageFees()));
                        measuresFeesList.add(String.valueOf(ro.getMeasuresFees()));
                        projectCostsList.add(String.valueOf(ro.getProjectCosts()));
                        engineeringTaxList.add(String.valueOf(ro.getEngineeringTax()));
                        totalProjectCostList.add(String.valueOf(ro.getTotalProjectCost()));
                        totalAccountOfferRankingList.add(String.valueOf(ro.getTotalProjectCostRank()));
                        taxRateNameList.add(ro.getTaxRateName());
                        awardStatusList.add(ro.getAwardStatusToEkp());
                        auctionNumberList.add(ro.getAuctionNumber());
                        auctionTitleList.add(ro.getAuctionTitle());
                        roundStatusList.add(ro.getRoundStatus());
                    } else {
                        supplierNameListN.add(ro.getSupplierName());
                        supplierEbsNumberListN.add(ro.getSupplierEbsNumber());
                        totalAccountOfferListN.add(String.valueOf(ro.getTotalAccountOffer()));
                        tranManageFeesListN.add(String.valueOf(ro.getTranManageFees()));
                        measuresFeesListN.add(String.valueOf(ro.getMeasuresFees()));
                        projectCostsListN.add(String.valueOf(ro.getProjectCosts()));
                        engineeringTaxListN.add(String.valueOf(ro.getEngineeringTax()));
                        totalProjectCostListN.add(String.valueOf(ro.getTotalProjectCost()));
                        totalAccountOfferRankingListN.add(String.valueOf(ro.getTotalProjectCostRank()));
                        taxRateNameListN.add(ro.getTaxRateName());
                        awardStatusListN.add(ro.getAwardStatusToEkp());
                        auctionNumberListN.add(ro.getAuctionNumber());
                        auctionTitleListN.add(ro.getAuctionTitle());
                        roundStatusListN.add(ro.getRoundStatus());
                    }

                }
            }


            //传已截止+已驳回状态
            if (!ObjectUtils.isEmpty(ekp)) {
                for (SrmPonAuctionHeadersEntity_HI_RO ekpRo : ekp) {
                    jsonParams.clear();
                    jsonParams.put("auctionHeaderId", ekpRo.getAuctionHeaderId());
                    jsonParams.put("itemType", itemType);
                    List<SrmPonBidHeadersEntity_HI_RO> supplierInfo = iSrmPonBidHeaders.findBidHeadersSupplierList(jsonParams);
                    if (!ObjectUtils.isEmpty(supplierInfo)) {
                        for (SrmPonBidHeadersEntity_HI_RO ro : supplierInfo) {
                            if ("4".equals(ro.getAwardStatus())) {
                                supplierNameList.add(ro.getSupplierName());
                                supplierEbsNumberList.add(ro.getSupplierEbsNumber());
                                totalAccountOfferList.add(String.valueOf(ro.getTotalAccountOffer()));
                                tranManageFeesList.add(String.valueOf(ro.getTranManageFees()));
                                measuresFeesList.add(String.valueOf(ro.getMeasuresFees()));
                                projectCostsList.add(String.valueOf(ro.getProjectCosts()));
                                engineeringTaxList.add(String.valueOf(ro.getEngineeringTax()));
                                totalProjectCostList.add(String.valueOf(ro.getTotalProjectCost()));
                                totalAccountOfferRankingList.add(String.valueOf(ro.getTotalProjectCostRank()));
                                taxRateNameList.add(ro.getTaxRateName());
                                awardStatusList.add(ro.getAwardStatusToEkp());
                                auctionNumberList.add(ro.getAuctionNumber());
                                auctionTitleList.add(ro.getAuctionTitle());
                                roundStatusList.add(ro.getRoundStatus());
                            } else {
                                supplierNameListN.add(ro.getSupplierName());
                                supplierEbsNumberListN.add(ro.getSupplierEbsNumber());
                                totalAccountOfferListN.add(String.valueOf(ro.getTotalAccountOffer()));
                                tranManageFeesListN.add(String.valueOf(ro.getTranManageFees()));
                                measuresFeesListN.add(String.valueOf(ro.getMeasuresFees()));
                                projectCostsListN.add(String.valueOf(ro.getProjectCosts()));
                                engineeringTaxListN.add(String.valueOf(ro.getEngineeringTax()));
                                totalProjectCostListN.add(String.valueOf(ro.getTotalProjectCost()));
                                totalAccountOfferRankingListN.add(String.valueOf(ro.getTotalProjectCostRank()));
                                taxRateNameListN.add(ro.getTaxRateName());
                                awardStatusListN.add(ro.getAwardStatusToEkp());
                                auctionNumberListN.add(ro.getAuctionNumber());
                                auctionTitleListN.add(ro.getAuctionTitle());
                                roundStatusListN.add(ro.getRoundStatus());
                            }
                        }
                    }

                }
            }
            LOGGER.info("寻源供应商名称 supplierNameList :" + supplierNameList);
            LOGGER.info("寻源供应商EBS编码 supplierEbsNumberList :" + supplierEbsNumberList);
            LOGGER.info("寻源材料总价 totalAccountOfferList :" + totalAccountOfferList);
            LOGGER.info("寻源运杂及管理费 tranManageFeesList :" + tranManageFeesList);
            LOGGER.info("寻源规费及措施费 measuresFeesList :" + measuresFeesList);
            LOGGER.info("寻源工程造价 projectCostsList :" + projectCostsList);
            LOGGER.info("寻源税金 engineeringTaxList :" + engineeringTaxList);
            LOGGER.info("寻源工程总造价 totalProjectCostList :" + totalProjectCostList);
            LOGGER.info("寻源报价排名 totalAccountOfferRankingList :" + totalAccountOfferRankingList);
            LOGGER.info("寻源供应商头税率 taxRateNameList :" + taxRateNameList);
            LOGGER.info("寻源供应商是否已中标 awardStatusList :" + awardStatusList);
            LOGGER.info("寻源寻源单号 auctionNumberList :" + auctionNumberList);
            LOGGER.info("寻源SRM标题 auctionTitleList :" + auctionTitleList);
            LOGGER.info("寻源状态 roundStatusList :" + roundStatusList);

            //查询快码
            Map<String, Object> mapV = new HashMap<>();
            mapV.put("lookupType", "BID_QUERY_EKP_INTERFACE_FIELDS");
            List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
            if (!ObjectUtils.isEmpty(lookupValueList)) {
                String lineNumber = "";
                String lineNumberN = "";
                for (int i = 0; i < lookupValueList.size(); i++) {
                    SaafLookupValuesEntity_HI interfaceFields = lookupValueList.get(i);
                    if ("lineNumber".equals(interfaceFields.getTag())) {
                        lineNumber = interfaceFields.getLookupCode();
                    }
                    if ("lineNumber_N".equals(interfaceFields.getTag())) {
                        lineNumberN = interfaceFields.getLookupCode();
                    }
                }

                if (!ObjectUtils.isEmpty(supplierNameList)) {
                    if (!StringUtils.isEmpty(lineNumber)) {
                        for (int i = 0; i < lookupValueList.size(); i++) {
                            SaafLookupValuesEntity_HI interfaceFields = lookupValueList.get(i);
                        /*if ("auctionNumber".equals(interfaceFields.getTag())) {
                            map.put(interfaceFields.getLookupCode(), header.getAuctionNumber());
                        }
                        if ("auctionTitle".equals(interfaceFields.getTag())) {
                            map.put(interfaceFields.getLookupCode(), header.getAuctionTitle());
                        }*/
                            if ("awardComments".equals(interfaceFields.getTag())) {
                                map.put(interfaceFields.getLookupCode(), header.getAwardComments());
                            }
                            if ("supplierName".equals(interfaceFields.getTag())) {
                                map.put(lineNumber + "." + interfaceFields.getLookupCode(), supplierNameList);
                            }
                        /*if ("supplierEbsNumber".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), supplierEbsNumberList);
                        }*/
                            if ("totalAccountOffer".equals(interfaceFields.getTag())) {
                                map.put(lineNumber + "." + interfaceFields.getLookupCode(), totalAccountOfferList);
                            }
                            if ("tranManageFees".equals(interfaceFields.getTag())) {
                                map.put(lineNumber + "." + interfaceFields.getLookupCode(), tranManageFeesList);
                            }
                            if ("measuresFees".equals(interfaceFields.getTag())) {
                                map.put(lineNumber + "." + interfaceFields.getLookupCode(), measuresFeesList);
                            }
                            if ("projectCosts".equals(interfaceFields.getTag())) {
                                map.put(lineNumber + "." + interfaceFields.getLookupCode(), projectCostsList);
                            }
                            if ("taxRateName".equals(interfaceFields.getTag())) {
                                map.put(lineNumber + "." + interfaceFields.getLookupCode(), taxRateNameList);
                            }
                            if ("engineeringTax".equals(interfaceFields.getTag())) {
                                map.put(lineNumber + "." + interfaceFields.getLookupCode(), engineeringTaxList);
                            }
                            if ("totalProjectCost".equals(interfaceFields.getTag())) {
                                map.put(lineNumber + "." + interfaceFields.getLookupCode(), totalProjectCostList);
                            }
                            if ("totalAccountOfferRanking".equals(interfaceFields.getTag())) {
                                map.put(lineNumber + "." + interfaceFields.getLookupCode(), totalAccountOfferRankingList);
                            }
                            if ("awardStatus".equals(interfaceFields.getTag())) {
                                map.put(lineNumber + "." + interfaceFields.getLookupCode(), awardStatusList);
                            }
                            if ("auctionNumber".equals(interfaceFields.getTag())) {
                                map.put(lineNumber + "." + interfaceFields.getLookupCode(), auctionNumberList);
                            }
                            if ("auctionTitle".equals(interfaceFields.getTag())) {
                                map.put(lineNumber + "." + interfaceFields.getLookupCode(), auctionTitleList);
                            }
                            if ("roundStatus".equals(interfaceFields.getTag())) {
                                map.put(lineNumber + "." + interfaceFields.getLookupCode(), roundStatusList);
                            }
                        }
                    } else {
                        throw new UtilsException("请维护招标询价EKP接口中标序号字段快码！");
                    }
                }
                if (!ObjectUtils.isEmpty(supplierNameListN)) {
                    if (!StringUtils.isEmpty(lineNumberN)) {
                        for (int i = 0; i < lookupValueList.size(); i++) {
                            SaafLookupValuesEntity_HI interfaceFields = lookupValueList.get(i);
                            if ("awardComments_N".equals(interfaceFields.getTag())) {
                                map.put(interfaceFields.getLookupCode(), header.getAwardComments());
                            }
                            if ("supplierName_N".equals(interfaceFields.getTag())) {
                                map.put(lineNumberN + "." + interfaceFields.getLookupCode(), supplierNameListN);
                            }
                            if ("totalAccountOffer_N".equals(interfaceFields.getTag())) {
                                map.put(lineNumberN + "." + interfaceFields.getLookupCode(), totalAccountOfferListN);
                            }
                            if ("tranManageFees_N".equals(interfaceFields.getTag())) {
                                map.put(lineNumberN + "." + interfaceFields.getLookupCode(), tranManageFeesListN);
                            }
                            if ("measuresFees_N".equals(interfaceFields.getTag())) {
                                map.put(lineNumberN + "." + interfaceFields.getLookupCode(), measuresFeesListN);
                            }
                            if ("projectCosts_N".equals(interfaceFields.getTag())) {
                                map.put(lineNumberN + "." + interfaceFields.getLookupCode(), projectCostsListN);
                            }
                            if ("taxRateName_N".equals(interfaceFields.getTag())) {
                                map.put(lineNumberN + "." + interfaceFields.getLookupCode(), taxRateNameListN);
                            }
                            if ("engineeringTax_N".equals(interfaceFields.getTag())) {
                                map.put(lineNumberN + "." + interfaceFields.getLookupCode(), engineeringTaxListN);
                            }
                            if ("totalProjectCost_N".equals(interfaceFields.getTag())) {
                                map.put(lineNumberN + "." + interfaceFields.getLookupCode(), totalProjectCostListN);
                            }
                            if ("totalAccountOfferRanking_N".equals(interfaceFields.getTag())) {
                                map.put(lineNumberN + "." + interfaceFields.getLookupCode(), totalAccountOfferRankingListN);
                            }
                            if ("awardStatus_N".equals(interfaceFields.getTag())) {
                                map.put(lineNumberN + "." + interfaceFields.getLookupCode(), awardStatusListN);
                            }
                            if ("auctionNumber_N".equals(interfaceFields.getTag())) {
                                map.put(lineNumberN + "." + interfaceFields.getLookupCode(), auctionNumberListN);
                            }
                            if ("auctionTitle_N".equals(interfaceFields.getTag())) {
                                map.put(lineNumberN + "." + interfaceFields.getLookupCode(), auctionTitleListN);
                            }
                            if ("roundStatus_N".equals(interfaceFields.getTag())) {
                                map.put(lineNumberN + "." + interfaceFields.getLookupCode(), roundStatusListN);
                            }
                        }
                    } else {
                        throw new UtilsException("请维护招标询价EKP接口未中标序号字段快码！");
                    }
                }

                obj.put("ekpData", ekp);

            } else {
                throw new UtilsException("查询招标询价EKP接口字段快码值失败！");
            }
        }

        if ("INFORMATION_TECHNOLOGY".equals(itemType)) {
            //标的物信息
            List<String> itemCodeList = new ArrayList<>();//标的物编码
            List<String> itemNameList = new ArrayList<>();//标的物名称
            List<String> unitOfMeasureList = new ArrayList<>();//标的物单位
            List<String> quantityList = new ArrayList<>();//标的物数量
            List<String> latestPriceList = new ArrayList<>();//标的物最新价格
            List<String> lowPriceList = new ArrayList<>();//标的物历史最低价格
            List<String> costList = new ArrayList<>();//标的物成本预算
            List<String> startDateList = new ArrayList<>();//标的物价格有效期
            List<String> endDateList = new ArrayList<>();//标的物价格有效期
            StringBuffer itemString = new StringBuffer();
            itemString.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BID_ITEMS_SQL);
            itemString.append(" AND t.auction_header_id = " + auctionHeaderId);
            itemString.append(" ORDER BY t.auction_line_id ASC ");
            List<SrmPonBidItemPricesEntity_HI_RO> bidItemPricesList = srmPonBidItemPricesDAO_HI_RO.findList(itemString.toString());
            if (null != bidItemPricesList) {
                for (SrmPonBidItemPricesEntity_HI_RO ro : bidItemPricesList) {
                    itemCodeList.add(ro.getItemCode());
                    itemNameList.add(ro.getItemDescription());
                    unitOfMeasureList.add(ro.getUnitOfMeasureName());
                    quantityList.add(String.valueOf(ro.getQuantity()));
                    latestPriceList.add(String.valueOf(ro.getLatestPrice()));
                    lowPriceList.add(String.valueOf(ro.getLowPrice()));
                    costList.add(String.valueOf(ro.getCost()));
                    startDateList.add(String.valueOf(ro.getStartDate()));
                    endDateList.add(String.valueOf(ro.getEndDate()));
                }
            }


            LOGGER.info("寻源标的物编码 itemCodeList :" + itemCodeList);
            LOGGER.info("寻源标的物名称 itemNameList :" + itemNameList);
            LOGGER.info("寻源标的物单位 unitOfMeasureList :" + unitOfMeasureList);
            LOGGER.info("寻源标的物数量 quantityList :" + quantityList);
            LOGGER.info("寻源标的物最新价格 latestPriceList :" + latestPriceList);
            LOGGER.info("寻源标的物历史最低价格 lowPriceList :" + lowPriceList);
            LOGGER.info("寻源标的物成本预算 costList :" + costList);
            LOGGER.info("寻源标的物价格有效期 startDateList :" + startDateList);
            LOGGER.info("寻源标的物价格有效期 endDateList :" + endDateList);

            //供应商信息
            List<String> supplierNameList = new ArrayList<>();//供应商名称
            List<String> supplierEbsNumberList = new ArrayList<>();//供应商EBS编码
            List<String> taxPriceList = new ArrayList<>();//供应商含税总价
            List<String> noTaxPriceList = new ArrayList<>();//供应商不含税总价
            List<String> taxRateNameList = new ArrayList<>();//供应商头税率
            List<String> awardStatusList = new ArrayList<>();//供应商是否已中标
            List<String> rankList = new ArrayList<>();//排名
            StringBuffer supplierString = new StringBuffer();
            supplierString.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BID_SUPPLIER_SQL);
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            queryParamMap.put("auctionheaderid", auctionHeaderId);
            List<SrmPonBidItemPricesEntity_HI_RO> supplierList = srmPonBidItemPricesDAO_HI_RO.findList(supplierString.toString(), queryParamMap);
            if (supplierList.size() > 0) {//进行排序
                Collections.sort(supplierList, new Comparator<SrmPonBidItemPricesEntity_HI_RO>() {
                    @Override
                    public int compare(SrmPonBidItemPricesEntity_HI_RO o1, SrmPonBidItemPricesEntity_HI_RO o2) {
                        BigDecimal number = (ObjectUtils.isEmpty(o1.getBargain()) ? o1.getNoTaxPrice() : o1.getBargain()).subtract((ObjectUtils.isEmpty(o2.getBargain()) ? o2.getNoTaxPrice() : o2.getBargain()));//升序
                        Integer num = number.compareTo(new BigDecimal(0));
                        return num;
                    }
                });

                for (Integer i = 0; i < supplierList.size(); i++) {
                    supplierList.get(i).setRank(i + 1);
                }

                for (SrmPonBidItemPricesEntity_HI_RO ro : supplierList) {
                    supplierNameList.add(ro.getSupplierName());
                    supplierEbsNumberList.add(ro.getSupplierEbsNumber());
                    if (!ObjectUtils.isEmpty(ro.getBargainTax())) {
                        taxPriceList.add(String.valueOf(ro.getBargainTax()));
                    } else {
                        taxPriceList.add(String.valueOf(ro.getTaxPrice()));
                    }
                    if (!ObjectUtils.isEmpty(ro.getBargain())) {
                        noTaxPriceList.add(String.valueOf(ro.getBargain()));
                    } else {
                        noTaxPriceList.add(String.valueOf(ro.getNoTaxPrice()));
                    }
                    taxRateNameList.add(ro.getTaxRateName());
                    awardStatusList.add(ro.getAwardStatus());
                    rankList.add(String.valueOf(ro.getRank()));
                }
            }

            LOGGER.info("寻源供应商名称 supplierNameList :" + supplierNameList);
            LOGGER.info("寻源供应商EBS编码 supplierEbsNumberList :" + supplierEbsNumberList);
            LOGGER.info("寻源供应商含税总价 taxPriceList :" + taxPriceList);
            LOGGER.info("寻源供应商不含税总价 noTaxPriceList :" + noTaxPriceList);
            LOGGER.info("寻源供应商头税率 taxRateNameList :" + taxRateNameList);
            LOGGER.info("寻源供应商是否已中标 awardStatusList :" + awardStatusList);

            //查询快码
            Map<String, Object> mapV = new HashMap<>();
            mapV.put("lookupType", "BID_QUERY_EKP_INTER_FIELDS_IT");
            List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
            if (!ObjectUtils.isEmpty(lookupValueList)) {
                String lineNumber = "";
                String supplierNumber = "";
                for (int i = 0; i < lookupValueList.size(); i++) {
                    SaafLookupValuesEntity_HI interfaceFields = lookupValueList.get(i);
                    if ("auctionNumber".equals(interfaceFields.getTag())) {
                        map.put(interfaceFields.getLookupCode(), header.getAuctionNumber());
                    }
                    if ("itemType".equals(interfaceFields.getTag())) {
                        map.put(interfaceFields.getLookupCode(), header.getItemType());
                    }
                    if ("awardComments".equals(interfaceFields.getTag())) {
                        map.put(interfaceFields.getLookupCode(), header.getAwardComments());
                    }
                    if ("lineNumber".equals(interfaceFields.getTag())) {
                        lineNumber = interfaceFields.getLookupCode();
                    }
                    if ("supplierNumber".equals(interfaceFields.getTag())) {
                        supplierNumber = interfaceFields.getLookupCode();
                    }
                }

                if (ObjectUtils.isEmpty(lineNumber) && ObjectUtils.isEmpty(supplierNumber)) {
                    throw new UtilsException("请维护IT招标询价EKP接口序号字段快码！");
                }

                if (!StringUtils.isEmpty(lineNumber)) {
                    for (int i = 0; i < lookupValueList.size(); i++) {
                        SaafLookupValuesEntity_HI interfaceFields = lookupValueList.get(i);
                        if ("itemCode".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), itemCodeList);
                        }
                        if ("itemName".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), itemNameList);
                        }
                        if ("unitOfMeasure".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), unitOfMeasureList);
                        }
                        if ("quantity".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), quantityList);
                        }
                        if ("latestPrice".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), latestPriceList);
                        }
                        if ("lowPrice".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), lowPriceList);
                        }
                        if ("cost".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), costList);
                        }
                        if ("startDate".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), startDateList);
                        }
                        if ("endDate".equals(interfaceFields.getTag())) {
                            map.put(lineNumber + "." + interfaceFields.getLookupCode(), endDateList);
                        }
                    }
                }

                if (!StringUtils.isEmpty(supplierNumber)) {
                    for (int i = 0; i < lookupValueList.size(); i++) {
                        SaafLookupValuesEntity_HI interfaceFields = lookupValueList.get(i);
                        if ("supplierName".equals(interfaceFields.getTag())) {
                            map.put(supplierNumber + "." + interfaceFields.getLookupCode(), supplierNameList);
                        }
                        if ("supplierEbsNumber".equals(interfaceFields.getTag())) {
                            map.put(supplierNumber + "." + interfaceFields.getLookupCode(), supplierEbsNumberList);
                        }
                        if ("taxPrice".equals(interfaceFields.getTag())) {
                            map.put(supplierNumber + "." + interfaceFields.getLookupCode(), taxPriceList);
                        }
                        if ("noTaxPrice".equals(interfaceFields.getTag())) {
                            map.put(supplierNumber + "." + interfaceFields.getLookupCode(), noTaxPriceList);
                        }
                        if ("taxRateName".equals(interfaceFields.getTag())) {
                            map.put(supplierNumber + "." + interfaceFields.getLookupCode(), taxRateNameList);
                        }
                        if ("rank".equals(interfaceFields.getTag())) {
                            map.put(supplierNumber + "." + interfaceFields.getLookupCode(), rankList);
                        }
                        if ("awardStatus".equals(interfaceFields.getTag())) {
                            map.put(supplierNumber + "." + interfaceFields.getLookupCode(), awardStatusList);
                        }
                    }
                }

                obj.put("itemData", bidItemPricesList);
                obj.put("supplierData", supplierList);

            } else {
                throw new UtilsException("查询招标询价EKP接口字段快码值失败！");
            }
        }
        //文档关键字
        List<String> descList = new ArrayList<String>(4);
        descList.add("【SRM】");
        descList.add("招标询价审批");
        descList.add(header.getAuctionTitle());
        descList.add(header.getAuctionNumber());

        //调用EKP的启动流程
        String processId = ekpSyncService.createFlow("20", header.getAttribute1(), tempateId, "【SRM】招标询价审批" + header.getAuctionTitle() + header.getAuctionNumber(), employeeNumber, descList, map, fileMap, lineFileArray, "PON");
        if (null == processId || "".equals(processId)) {
            throw new UtilsException("EKP没有返回值" + processId);
        }
        Boolean flag = ekpSyncService.isJSONValid(processId);
        if (flag) {
            throw new UtilsException("EKP返回值错误" + processId);
        }

        //回写状态
        if (!ObjectUtils.isEmpty(ekp)) {
            for (SrmPonAuctionHeadersEntity_HI_RO ro : ekp) {
                if (!header.getAuctionHeaderId().equals(ro.getAuctionHeaderId())) {
                    SrmPonAuctionHeadersEntity_HI roHeader = srmPonAuctionHeadersDAO_HI.getById(ro.getAuctionHeaderId());
                    roHeader.setAttribute1(processId);
                    roHeader.setAwardApprovalStatus("APPROVING");//决标审批状态为审批中
                    roHeader.setJudgeCompleteDate(new Date());//评标完成时间
                    roHeader.setOperatorUserId(userId);
                    srmPonAuctionHeadersDAO_HI.saveEntity(roHeader);
                    srmPonAuctionHeadersDAO_HI.fluch();
                }
            }
        }

        return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "操作成功", 0, obj);
    }

    /**
     * Description：定时查询EKP审批状态
     *
     * @return void
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-05-29         xiexiaoxia      创建
     * =======================================================================
     */
    @Override
    /*public void saveEkpStatus() throws Exception {
        StringBuffer sql = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_AUCTIONINFO_FROMEKP);
        //   sql.append(" AND Spah.auction_header_id = " + 10565);
        List<SrmPonAuctionHeadersEntity_HI_RO> list = srmPonAuctionHeadersDAO_HI_RO.findList(sql.toString());
        if (null != list && list.size() > 0) {
            ESBClientUtils esb = new ESBClientUtils();
            JSONObject baseQueryParams = new JSONObject();
            JSONArray businessData = new JSONArray();
            for (SrmPonAuctionHeadersEntity_HI_RO ro : list) {
                JSONObject businessJson = new JSONObject();
                businessJson.put("fdId", ro.getAttribute1());
                businessData.add(businessJson);
            }
            JSONObject result = esb.doPost(ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.SRM_SERVER.getValue(),
                    ESBParams.SystemName.EKP.getValue(), ESBParams.ServiceName.EKP_REVIEW_MAIN.getValue(),
                    null, "S", baseQueryParams, businessData);
            LOGGER.info("----------------------EKP查询审批返回结果: " + result);
            if (SUCCESS_STATUS.equalsIgnoreCase(result.getString("status"))) {
                if (null != result.getJSONObject("data")) {
                    JSONObject data = result.getJSONObject("data");
                    if (null != data.getJSONObject("obj")) {
                        JSONObject obj = data.getJSONObject("obj");
                        if (null != obj.getJSONArray("rows") && obj.getJSONArray("rows").size() > 0) {
                            JSONArray rows = obj.getJSONArray("rows");
                            LOGGER.info("----------------------查询EKP审批结果,返回结果: " + rows);
                            for (int i = 0; i < rows.size(); i++) {
                                JSONObject row = rows.getJSONObject(i);
                                List<SrmPonAuctionHeadersEntity_HI> rowList = srmPonAuctionHeadersDAO_HI.findByProperty("attribute1", row.getString("fdId"));
                                if (null != rowList && rowList.size() > 0) {
                                    for (int n = 0; n < rowList.size(); n++) {
                                        SrmPonAuctionHeadersEntity_HI qualInfo = rowList.get(n);
                                        if (!"20".equals(row.getString("docStatus"))) {
                                            JSONObject params = new JSONObject();
                                            JSONObject responseJson = new JSONObject();
                                            params.put("auctionHeaderId", qualInfo.getAuctionHeaderId());
                                            params.put("varUserId", -999);
                                            if ("30".equals(row.getString("docStatus"))) {
                                                params.put("action", "APPROVED");
                                                responseJson = iSrmPonAuctionHeaders.updatePonAuctionHeadersApprove(params);
                                            } else if ("11".equals(row.getString("docStatus"))) {
                                                params.put("action", "REJECT");
                                                responseJson = iSrmPonAuctionHeaders.updatePonAuctionHeadersApprove(params);
                                            }
                                            if ("S".equals(responseJson.getString("status"))) {
                                                qualInfo.setOperatorUserId(-999);
                                                srmPonAuctionHeadersDAO_HI.saveEntity(qualInfo);
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            throw new UtilsException("查询EKP审批结果返回结果rows不能为空, 返回结果: " + result);
                        }
                    } else {
                        throw new UtilsException("查询EKP审批结果返回结果obj为空, 返回结果: " + result);
                    }
                } else {
                    throw new UtilsException("查询EKP审批结果,查询SRM调用EKP接口srm工具类返回结果data为空, 返回结果: " + result);
                }
            } else {
                throw new UtilsException("查询EKP审批结果查询EKP接口返回结果不为S, 返回结果: " + result);
            }
        }
    }*/

    public JSONObject saveEkpStatus() throws Exception {
        try {
            StringBuffer sql = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_AUCTIONINFO_FROMEKP);
            sql.append(" and Spah.Attribute1 is not null");
            List<SrmPonAuctionHeadersEntity_HI_RO> list = srmPonAuctionHeadersDAO_HI_RO.findList(sql.toString());
            if (null != list && list.size() > 0) {
                ESBClientUtils esb = new ESBClientUtils();
                JSONObject baseQueryParams = new JSONObject();
                JSONArray businessData = new JSONArray();
                for (SrmPonAuctionHeadersEntity_HI_RO ro : list) {
                    try{
                        JSONArray jsonArray = findEkpId(ro.getEkpNumber());
                        if (!ObjectUtils.isEmpty(jsonArray)) {
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            //查询节点快码
                            if ("ENGINEERING".equals(ro.getItemType())) {
                                //查询业务实体区域
                                StringBuffer instRegionString = new StringBuffer(SrmPonAuctionHeadersEntity_HI_RO.QUERY_INST_REGION);
                                instRegionString.append(" and  siv.Inst_Id=" + ro.getOrgId());
                                List<SrmPonAuctionHeadersEntity_HI_RO> instRegionList = srmPonAuctionHeadersDAO_HI_RO.findList(instRegionString.toString());
                                if (null == instRegionList || instRegionList.isEmpty()) {
                                    throw new RuntimeException("无此业务实体");
                                }
                                String instRegion = instRegionList.get(0).getInstRegion();

                                Map<String, Object> mapV = new HashMap<>();
                                mapV.put("tag", "20");

                                if ("SHENZHEN".equals(instRegion)) { //深圳
                                    mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE");
                                } else if ("NANTONG".equals(instRegion)) { //南通
                                    mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE_NT");
                                } else if ("WUXI".equals(instRegion)) {//无锡
                                    mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE_WX");
                                }
                                List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                                String node = "";
                                if (null != lookupValueList && lookupValueList.size() > 0) {
                                    node = lookupValueList.get(0).getLookupCode();
                                } else {
                                    throw new UtilsException("请在快码中配置招标询价EKP流程接出节点！");
                                }
                                if (!ObjectUtils.isEmpty(jsonObject.getString("fdFactNodeId"))&& node.equals(jsonObject.getString("fdFactNodeId"))) {
                                    JSONObject params = new JSONObject();
                                    params.put("auctionHeaderId", ro.getAuctionHeaderId());
                                    params.put("varUserId", -999);
                                    params.put("action", "APPROVED");
                                    iSrmPonAuctionHeaders.updatePonAuctionHeadersApprove(params);
                                }else{
                                    JSONObject businessJson = new JSONObject();
                                    businessJson.put("fdId", ro.getAttribute1());
                                    businessData.add(businessJson);
                                }
                            }else if ("INFORMATION_TECHNOLOGY".equals(ro.getItemType())) {
                                Map<String, Object> mapV = new HashMap<>();
                                mapV.put("lookupType", "BIDDING_INQUIRY_EKP_NODE_IT");
                                mapV.put("tag", "20");
                                List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                                if (null == lookupValueList || lookupValueList.size() <= 0) {
                                    throw new UtilsException("请在快码中配置IT招标询价EKP流程接出节点！");
                                }
                                List<String> lookCodeList = lookupValueList.stream().map(SaafLookupValuesEntity_HI::getLookupCode).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList()).stream().filter(String -> String != null).collect(Collectors.toList());
                                if (!ObjectUtils.isEmpty(jsonObject.getString("fdFactNodeId")) && lookCodeList.contains(jsonObject.getString("fdFactNodeId"))) {
                                    JSONObject params = new JSONObject();
                                    params.put("auctionHeaderId", ro.getAuctionHeaderId());
                                    params.put("varUserId", -999);
                                    params.put("action", "APPROVED");
                                    iSrmPonAuctionHeaders.updatePonAuctionHeadersApprove(params);
                                }else{
                                    JSONObject businessJson = new JSONObject();
                                    businessJson.put("fdId", ro.getAttribute1());
                                    businessData.add(businessJson);
                                }
                            }
                        }
                    }catch (Exception e){
                        LOGGER.info("接口返回结果异常："+e.getMessage());
                        continue;
                    }
                   /* JSONObject businessJson = new JSONObject();
                    businessJson.put("fdId", ro.getAttribute1());
                    businessData.add(businessJson);*/
                }
                JSONObject result = esb.doPost(ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.SRM_SERVER.getValue(),
                        ESBParams.SystemName.EKP.getValue(), ESBParams.ServiceName.EKP_REVIEW_MAIN.getValue(),
                        null, "S", baseQueryParams, businessData);
                LOGGER.info("----------------------EKP查询审批返回结果: " + result);
                if (SUCCESS_STATUS.equalsIgnoreCase(result.getString("status"))) {
                    if (null != result.getJSONObject("data")) {
                        JSONObject data = result.getJSONObject("data");
                        if (null != data.getJSONObject("obj")) {
                            JSONObject obj = data.getJSONObject("obj");
                            if (null != obj.getJSONArray("rows") && obj.getJSONArray("rows").size() > 0) {
                                JSONArray rows = obj.getJSONArray("rows");
                                LOGGER.info("----------------------查询EKP审批结果,返回结果: " + rows);
                                for (int i = 0; i < rows.size(); i++) {
                                    JSONObject row = rows.getJSONObject(i);
                                    sql.append(" and Spah.attribute1 =" +row.getString("fdId"));
                                    List<SrmPonAuctionHeadersEntity_HI> rowList = srmPonAuctionHeadersDAO_HI.findList(sql.toString());
                                    //List<SrmPonAuctionHeadersEntity_HI> rowList = srmPonAuctionHeadersDAO_HI.findByProperty("attribute1", row.getString("fdId"));
                                    if (null != rowList && rowList.size() > 0) {
                                        for (int n = 0; n < rowList.size(); n++) {
                                            SrmPonAuctionHeadersEntity_HI qualInfo = rowList.get(n);
                                            if (!"20".equals(row.getString("docStatus"))) {
                                                JSONObject params = new JSONObject();
                                                params.put("auctionHeaderId", qualInfo.getAuctionHeaderId());
                                                params.put("varUserId", -999);
                                                if ("30".equals(row.getString("docStatus"))) {
                                                    params.put("action", "APPROVED");
                                                    iSrmPonAuctionHeaders.updatePonAuctionHeadersApprove(params);
                                                } else if ("11".equals(row.getString("docStatus"))) {
                                                    params.put("action", "REJECT");
                                                    iSrmPonAuctionHeaders.updatePonAuctionHeadersApprove(params);
                                                }
                                                /*if ("S".equals(responseJson.getString("status"))) {
                                                    qualInfo.setOperatorUserId(-999);
                                                    srmPonAuctionHeadersDAO_HI.saveEntity(qualInfo);
                                                }*/
                                            }
                                        }
                                    }
                                }
                            } else {
                                throw new UtilsException("查询EKP审批结果返回结果rows不能为空, 返回结果: " + result);
                            }
                        } else {
                            throw new UtilsException("查询EKP审批结果返回结果obj为空, 返回结果: " + result);
                        }
                    } else {
                        throw new UtilsException("查询EKP审批结果,查询SRM调用EKP接口srm工具类返回结果data为空, 返回结果: " + result);
                    }
                } else {
                    throw new UtilsException("查询EKP审批结果查询EKP接口返回结果不为S, 返回结果: " + result);
                }
            }
            return SToolUtils.convertResultJSONObj("S", "获取招标询价结果成功", 0, null);
        }catch (Exception e){
            //手动回滚事务
            // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOGGER.error("未知错误:{}", e);
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"操作失败"+e.getMessage(),0,null);
        }
    }

    /**
     * Description：通过EKP编号查询流程节点
     *
     * @return JSONArray
     * =======================================================================
     * Version    Date                Updated By     Description
     * -------    ----------------  -----------    ---------------
     * V1.0       2020-05-29         xiexiaoxia      创建
     * =======================================================================
     */
    private JSONArray findEkpId(String ekpNumber) throws Exception {
        JSONArray rows = null;
        String isSuccess = null;
        ESBClientUtils esb = new ESBClientUtils();
        JSONObject baseQueryParams = new JSONObject();
        JSONArray businessData = new JSONArray();
        JSONObject businessJson = new JSONObject();
        businessJson.put("fdNumber", ekpNumber);
        businessData.add(businessJson);
        JSONObject result = esb.doPost(ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.SRM_SERVER.getValue(),
                ESBParams.SystemName.EKP.getValue(), ESBParams.ServiceName.EKP_REVIEW_NODE.getValue(),
                null, "S", baseQueryParams, businessData);
        LOGGER.info("----------------------EKP查询返回结果: " + result);
        if (SUCCESS_STATUS.equalsIgnoreCase(result.getString("status"))) {
            if (null != result.getJSONObject("data")) {
                JSONObject data = result.getJSONObject("data");
                if (null != data.getJSONObject("obj")) {
                    JSONObject obj = data.getJSONObject("obj");
                    if (null != obj.getJSONArray("rows") && obj.getJSONArray("rows").size() > 0) {
                        rows = obj.getJSONArray("rows");
                        LOGGER.info("----------------------查询EKP流程ID,返回结果: " + rows);
                        return rows;
                    } else {
                        throw new UtilsException("查询EKP流程节点返回结果rows不能为空, 返回结果: " + result);
                    }
                } else {
                    throw new UtilsException("查询EKP流程节点返回结果obj为空, 返回结果: " + result);
                }
            } else {
                throw new UtilsException("查询EKP流程节点,查询SRM调用EKP接口srm工具类返回结果data为空, 返回结果: " + result);
            }
        } else {
            throw new UtilsException("查询EKP流程节点查询EKP接口返回结果不为S, 返回结果: " + result);
        }
    }
}
