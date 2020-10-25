package saaf.common.fmw.pon.model.inter.server;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.yhg.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseCategoriesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseItemsEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseItem;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pon.model.entities.*;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionItemsEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidHeadersEntity_HI_RO;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidItemPricesEntity_HI_RO;
import saaf.common.fmw.pon.model.inter.ISrmPonAuctionItems;
import saaf.common.fmw.pon.model.inter.ISrmPonBidItemPrices;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SrmPonAuctionItemLaddersServer.java
 * Description：寻源--寻源标的物信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
@Component("srmPonAuctionItemsServer")
public class SrmPonAuctionItemsServer implements ISrmPonAuctionItems {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonAuctionItemsServer.class);

    @Autowired
    private ViewObject<SrmPonAuctionItemsEntity_HI> srmPonAuctionItemsDAO_HI;

    @Autowired
    private BaseViewObject<SrmPonAuctionItemsEntity_HI_RO> srmPonAuctionItemsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPonAuctionItemLaddersEntity_HI> srmPonAuctionItemLaddersDAO_HI; // 供应商行的阶梯数量

    @Autowired
    private ViewObject<SrmPonAuctionGroupsEntity_HI> srmPonAuctionGroupsDAO_HI; // 组别

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI; // 快码值

    @Autowired
    private BaseViewObject<SrmPonBidItemPricesEntity_HI_RO> srmPonBidItemPricesDAO_HI_RO;//报价行

    @Autowired
    private ISrmBaseItem iSrmBaseItem; // 物料

    @Autowired
    private ISrmPonBidItemPrices iSrmPonBidItemPrices;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    @Autowired
    private ViewObject<SrmPonAuctionHeadersEntity_HI> srmPonAuctionHeadersDAO_HI;

    @Autowired
    private ViewObject<SrmBaseCategoriesEntity_HI> srmBaseCategoriesDAO_HI;

    public SrmPonAuctionItemsServer() {
        super();
    }

    /**
     * Description：根据传入的字符串判断是不是正整数，是正整数就返回true，否则为false
     * @param str 输入字符
     * @return boolean
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        return pattern.matcher(str).matches();
    }

    /**
     * Description：根据传入的字符串判断是不是正浮点数，是正浮点数就返回true，否则为false
     * @param str 输入字符
     * @return boolean
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public boolean isPositiveNumber(String str) {
        Pattern pattern = Pattern.compile("^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$");
        return pattern.matcher(str).matches();
    }

    /**
     * Description：判断日期格式是否正确，且日期范围是否正确
     * @param date 输入日期
     * @return boolean
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public boolean isDate(String date) {
        /**
         * 判断日期格式和范围
         */
        String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(date);
        boolean dateType = mat.matches();
        return dateType;
    }

    /**
     * Description：删除洽谈行（同时删除对应的供应商行的阶梯数量的数据）——根据主键ID（单条数据）
     * @param jsonParams 参数
     * @return JSONObject
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteSrmPonAuctionItemById(JSONObject jsonParams) {
        LOGGER.info("参数：" + jsonParams.toString());
        Integer auctionLineId = jsonParams.getInteger("auctionLineId");
        if (!(auctionLineId == null || "".equals(auctionLineId))) {
            SrmPonAuctionItemsEntity_HI row = srmPonAuctionItemsDAO_HI.getById(jsonParams.getInteger("auctionLineId"));
            if (null != row) {
                List<SrmPonAuctionItemLaddersEntity_HI> list = srmPonAuctionItemLaddersDAO_HI
                        .findByProperty("auctionLineId", row.getAuctionLineId());
                if (null != list && list.size() > 0) {
                    srmPonAuctionItemLaddersDAO_HI.deleteAll(list);
                }
                srmPonAuctionItemsDAO_HI.delete(row.getAuctionLineId());
                return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "未知错误", 0, null);
            }
        } else {
            return SToolUtils.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

    /**
     * Description：查询标的物（不分页）
     * @param params 参数
     * @return List<SrmPonAuctionItemsEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<SrmPonAuctionItemsEntity_HI_RO> getAuctionItemsList(JSONObject params) {
        LOGGER.info("参数：" + params.toString());
        String completedFlag = params.getString("completedFlag"); // 是否已完成、已截止
        StringBuffer querySql = null;
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        if (auctionHeaderId == null || "".equals(auctionHeaderId)) {
            return null;
        }
        if ("Y".equals(completedFlag)) {//是否已完成、已截止
            querySql = new StringBuffer(SrmPonAuctionItemsEntity_HI_RO.QUERY_AUCTION_ITEMS_SUPPLISER_LIST_SQL);
            List<SrmPonAuctionItemsEntity_HI_RO> list = srmPonAuctionItemsDAO_HI_RO.findList(querySql, new Object[]{auctionHeaderId});
            List<SrmPonAuctionItemsEntity_HI_RO> newList = new ArrayList<SrmPonAuctionItemsEntity_HI_RO>();
            if (null != list && list.size() > 0) {
                for (SrmPonAuctionItemsEntity_HI_RO k : list) {
                    newList.add(k);
                    List<SrmPonAuctionItemLaddersEntity_HI> iteLadderList = srmPonAuctionItemLaddersDAO_HI.findByProperty("auctionLineId", k.getAuctionLineId());
                    if (null != iteLadderList && iteLadderList.size() > 0) {
                        for (SrmPonAuctionItemLaddersEntity_HI bb : iteLadderList) {
                            SrmPonAuctionItemsEntity_HI_RO temp = (SrmPonAuctionItemsEntity_HI_RO) k.clone();
                            temp.setQuantity(bb.getLadderQuantity());
                            newList.add(temp);
                        }
                    }
                }
            }
            return newList;
        } else {  //招标，询价的拟定，使用的查询方法
            querySql = new StringBuffer(SrmPonAuctionItemsEntity_HI_RO.QUERY_AUCTION_ITEMS_LIST_SQL);
            List<SrmPonAuctionItemsEntity_HI_RO> list = srmPonAuctionItemsDAO_HI_RO.findList(querySql, new Object[]{auctionHeaderId});
            if (null != list && list.size() > 0) {
                for (SrmPonAuctionItemsEntity_HI_RO k : list) {
                    List<SrmPonAuctionItemLaddersEntity_HI> iteLadderList = srmPonAuctionItemLaddersDAO_HI.findByProperty("auctionLineId", k.getAuctionLineId());
                    if (null != iteLadderList && iteLadderList.size() > 0) {
                        k.setItemLaddersNumber(iteLadderList.size()); //阶梯数量list的记录条数
                        int r = iteLadderList.get(0).getLadderQuantity().compareTo(BigDecimal.ZERO);
                        if (iteLadderList.size() == 1 && r == 0) {
                            k.setLaddersFlag("Y");
                        } else {
                            k.setLaddersFlag("N");
                        }
                    }
                }
            }
            return list;
        }
    }

    /**
     * Description：查询标的物（根据物料编码）
     * @param params 参数
     * @return List<SrmPonAuctionItemsEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public List<SrmPonAuctionItemsEntity_HI_RO> getAuctionItemsListByItemCode(JSONObject params) {
        LOGGER.info("参数：" + params.toString());
        StringBuffer querySql = null;
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        String itemDescription = params.getString("itemDescription");
        String itemCode = params.getString("itemCode");
        if (auctionHeaderId == null || "".equals(auctionHeaderId)) {
            return null;
        }
        querySql = new StringBuffer(SrmPonAuctionItemsEntity_HI_RO.QUERY_AUCTION_ITEMS_LINE_SQL);
        querySql.append(" and t.auction_header_id = :auctionHeaderId ");
        if(!ObjectUtils.isEmpty(itemCode)){
            querySql.append(" and b.item_code = :itemCode ");
        }else{
            querySql.append(" and t.item_description = :itemDescription ");
        }

        Map queryMap = new HashMap();
        queryMap.put("auctionHeaderId", auctionHeaderId);
        if(!ObjectUtils.isEmpty(itemCode)){
            queryMap.put("itemCode", itemCode);
        }else{
            queryMap.put("itemDescription", itemDescription);
        }
        List<SrmPonAuctionItemsEntity_HI_RO> list = srmPonAuctionItemsDAO_HI_RO.findList(querySql, queryMap);
        return list;
    }

    /**
     * Description：保存拟标洽谈行==标的物（招标）
     * @param ponAuctionItemsList 标的物信息
     * @param userId 操作者ID
     * @param auctionHeaderId 寻源单据ID
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public void savePonAuctionItemsList(List<SrmPonAuctionItemsEntity_HI> ponAuctionItemsList, Integer userId,
                                        Integer auctionHeaderId) {
        try {
            if (null != ponAuctionItemsList && ponAuctionItemsList.size() > 0) {
                for (SrmPonAuctionItemsEntity_HI k : ponAuctionItemsList) {
                    if (null == k.getAuctionLineId() || "".equals(k.getAuctionLineId())) {
                        if (k.getGroupName() != null && !"".equals(k.getGroupName())) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("auctionHeaderId", auctionHeaderId);
                            map.put("groupName", k.getGroupName());
                            List<SrmPonAuctionGroupsEntity_HI> list = srmPonAuctionGroupsDAO_HI.findByProperty(map);
                            if (null != list && list.size() > 0) {
                                k.setGroupId(list.get(0).getGroupId());
                            }
                        }
                        k.setAuctionHeaderId(auctionHeaderId);
                        k.setOperatorUserId(userId);
                    } else {
                        BigDecimal webReferencePrice = new BigDecimal(0);
                        BigDecimal discountPrice = new BigDecimal(0);
                        BigDecimal quantity = new BigDecimal(0);
                        BigDecimal marketInquiry = new BigDecimal(0);

                        SrmPonAuctionHeadersEntity_HI auctionHeader =srmPonAuctionHeadersDAO_HI.getById(auctionHeaderId);
                        if (!ObjectUtils.isEmpty(k.getAuctionLineId())&&!"DRAFT".equals(auctionHeader.getAuctionStatus())) {
                            k = srmPonAuctionItemsDAO_HI.getById(k.getAuctionLineId());
                        }

                        if (null != k.getWebReferencePrice()) {
                            webReferencePrice = k.getWebReferencePrice();
                            discountPrice = new BigDecimal(0.95).multiply(webReferencePrice);
                            k.setWebReferencePrice(webReferencePrice.compareTo(BigDecimal.ZERO)==0?null:webReferencePrice.setScale(2, BigDecimal.ROUND_HALF_UP));
                            k.setDiscountPrice(discountPrice.compareTo(BigDecimal.ZERO)==0?null:discountPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if("LOGISTICS".equals(auctionHeader.getItemType())&&null != k.getMarketInquiry()){
                            marketInquiry= k.getMarketInquiry();
                            k.setMarketInquiry(marketInquiry.compareTo(BigDecimal.ZERO)==0?null:marketInquiry.setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        if (null != k.getQuantity()) {
                            quantity= k.getQuantity();
                            k.setQuantity(quantity.compareTo(BigDecimal.ZERO)==0?null:quantity.setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        k.setOperatorUserId(userId);

                    }
                    srmPonAuctionItemsDAO_HI.saveOrUpdate(k);
                }
            }
                /*if(null==ponAuctionItemsList.get(0).getAuctionLineId()){
                    for (SrmPonAuctionItemsEntity_HI k : ponAuctionItemsList) {
                        if (k.getGroupName() != null && !"".equals(k.getGroupName())) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("auctionHeaderId", auctionHeaderId);
                            map.put("groupName", k.getGroupName());
                            List<SrmPonAuctionGroupsEntity_HI> list = srmPonAuctionGroupsDAO_HI.findByProperty(map);
                            if (null != list && list.size() > 0) {
                                k.setGroupId(list.get(0).getGroupId());
                            }
                        }
                        k.setAuctionHeaderId(auctionHeaderId);
                        k.setOperatorUserId(userId);
                    }
                    srmPonAuctionItemsDAO_HI.saveOrUpdateAll(ponAuctionItemsList);
                }else{
                    for (SrmPonAuctionItemsEntity_HI k : ponAuctionItemsList) {
                       BigDecimal webReferencePrice = new BigDecimal(0);
                        BigDecimal discountPrice = new BigDecimal(0);
                        if (null != k.getWebReferencePrice()) {
                            webReferencePrice = k.getWebReferencePrice();
                            discountPrice = new BigDecimal(0.95).multiply(webReferencePrice);
                        }
                        if (null != k.getAuctionLineId()) {
                            SrmPonAuctionItemsEntity_HI updateItems = srmPonAuctionItemsDAO_HI.getById(k.getAuctionLineId());
                            updateItems.setOperatorUserId(userId);
                            updateItems.setWebReferencePrice(webReferencePrice.setScale(2, BigDecimal.ROUND_HALF_UP));
                            updateItems.setDiscountPrice(discountPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
                            srmPonAuctionItemsDAO_HI.saveOrUpdate(updateItems);
                        }
                    }
                }*/
        } catch (Exception e) {
            LOGGER.error("未知错误:{}" , e);
        }
    }

    /**
     * Description：批量导入——洽谈行数据==标的物
     * @param jsonParams 参数
     * @param userId 操作者ID
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveBatchImportPonAuctionItems(JSONObject jsonParams, Integer userId) {
        LOGGER.info("参数：" + jsonParams.toString());
        LOGGER.info("userId:" + userId);
        if (jsonParams.getJSONArray("data") == null || "".equals(jsonParams.getJSONArray("data"))
                || jsonParams.getJSONArray("data").size() <= 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "导入的数据为空，不可导入", 0, null);
        }
        if (jsonParams.getJSONObject("info") == null || "".equals(jsonParams.getJSONObject("info"))
                || jsonParams.getJSONObject("info").getString("auctionHeaderId") == null
                || "".equals(jsonParams.getJSONObject("info").getString("auctionHeaderId"))) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "头层未保存，不可导入", 0, null);
        }
        if (jsonParams.getJSONObject("info") == null || "".equals(jsonParams.getJSONObject("info"))
                || jsonParams.getJSONObject("info").getString("parentInstId") == null
                || "".equals(jsonParams.getJSONObject("info").getString("parentInstId"))) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "请先选择业务实体", 0, null);
        }
        JSONObject info = jsonParams.getJSONObject("info");
        JSONArray jsonArray = jsonParams.getJSONArray("data");
        JSONArray error = checkArray(jsonArray, info,userId);//导入前校验
        if (error.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "导入失败", error.size(), error);
        }

        JSONObject jsonData = new JSONObject(); // 最终返回的结果
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (Integer i = 0; i < jsonArray.size(); i++) { //转换日期类型
            JSONObject object = jsonArray.getJSONObject(i);

            try {
                if(!ObjectUtils.isEmpty(object.getString("startDate"))){
                    Date sdate = toDate(object.getString("startDate"));
                    jsonArray.getJSONObject(i).put("startDate", sdate);
                }
                if(!ObjectUtils.isEmpty(object.getString("endDate"))){
                    Date edate = toDate(object.getString("endDate"));
                    jsonArray.getJSONObject(i).put("endDate", edate);
                }
            } catch (Exception e) {
                return SToolUtils.convertResultJSONObj("E", "日期转换有误" + e.getMessage(), 0, null);
            }

        }
        JSONArray ponAuctionItemsListJSON = jsonArray;
        Integer auctionHeaderId = Integer.parseInt(info.getString("auctionHeaderId"));
        List<SrmPonAuctionItemsEntity_HI> ponAuctionItemsList = JSON.parseArray(ponAuctionItemsListJSON.toJSONString(), SrmPonAuctionItemsEntity_HI.class);
        if (ponAuctionItemsList != null && ponAuctionItemsList.size() > 0) {
            for (SrmPonAuctionItemsEntity_HI k : ponAuctionItemsList) {
                if (k.getAwardStatus() == null || "".equals(k.getAwardStatus())) {
                    k.setAwardStatus("1");// 未决标
                }
            }
        }
        savePonAuctionItemsList(ponAuctionItemsList, userId, auctionHeaderId);
        return SToolUtils.convertResultJSONObj("S", "成功导入" + ponAuctionItemsList.size() + "行数据", 1, jsonData);
    }

    private Date toDate(String sDate) throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(sDate);
        return date;
    }

    /**
     * Description：批量导入——询价-网站参考价
     * @param jsonParams 参数
     * @param userId 操作者ID
     * @return void
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveBatchImportPonWebReferencePrice(JSONObject jsonParams, Integer userId) {
        LOGGER.info("参数：" + jsonParams.toString());
        LOGGER.info("userId:" + userId);
        if (jsonParams.getJSONArray("data") == null || "".equals(jsonParams.getJSONArray("data"))
                || jsonParams.getJSONArray("data").size() <= 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "导入的数据为空，不可导入", 0, null);
        }
        JSONObject info = jsonParams.getJSONObject("info");
        JSONArray jsonArray = jsonParams.getJSONArray("data");
        JSONArray error = checkWebArray(jsonArray, info);//导入前校验
        if (error.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "导入失败", error.size(), error);
        }

        JSONObject jsonData = new JSONObject(); // 最终返回的结果
        JSONArray ponAuctionItemsListJSON = jsonArray;
        Integer auctionHeaderId = Integer.parseInt(info.getString("auctionHeaderId"));
        List<SrmPonAuctionItemsEntity_HI> ponAuctionItemsList = JSON.parseArray(ponAuctionItemsListJSON.toJSONString(), SrmPonAuctionItemsEntity_HI.class);
        savePonAuctionItemsList(ponAuctionItemsList, userId, auctionHeaderId);
        //保存份额
        iSrmPonBidItemPrices.savePonBidItemPricesList(userId, auctionHeaderId, null, ponAuctionItemsListJSON);
        return SToolUtils.convertResultJSONObj("S", "成功导入" + ponAuctionItemsList.size() + "行数据", 1, jsonData);
    }

    /**
     * Description：批量导入——询价-网站参考价
     * @param jsonArray 待校验数组
     * @param info 导入参数
     * @param userId 操作者ID
     * @return JSONArray
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONArray checkArray(JSONArray jsonArray, JSONObject info,Integer userId) {
        if (jsonArray.isEmpty()) {
            return null;
        }
        Integer auctionHeaderId = Integer.parseInt(info.getString("auctionHeaderId"));
        Integer parentInstId = Integer.parseInt(info.getString("parentInstId"));
        JSONArray error = new JSONArray();
        JSONObject e = null;
        for (Integer i = 0; i < jsonArray.size(); i++) { // 检验数据正确性
            JSONObject object = jsonArray.getJSONObject(i);
            // 判断税率的快码值
            /*if (!(object.getString("meaning") == null || "".equals(object.getString("meaning")))) {
                String meaning = object.getString("meaning"); // 检查输入的税率
                Map<String, Object> mapV = new HashMap<>();
                mapV.put("lookupType", "PON_TAX_LIST");
                mapV.put("meaning", meaning);
                List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                if (lookupValueList == null || lookupValueList.size() < 1 || "".equals(lookupValueList)) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "税率" + meaning + "输入有误");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                } else {
                    SaafLookupValuesEntity_HI entity = lookupValueList.get(0);
                    if (null != entity.getEnabledFlag() && "Y".equals(entity.getEnabledFlag())) {
                        object.put("taxRateCode", lookupValueList.get(0).getLookupCode());
                    } else {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "税率" + meaning + "是未生效状态");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                }
            }*/
            boolean temp = isPositiveNumber(object.getString("quantity"));// 检查输入的数量是否为正浮点数
            if (!temp) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "数量" + object.getString("quantity") + "输入有误，请输入非负数");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            if(!ObjectUtils.isEmpty(object.getString("startDate"))){
                boolean k = isDate(object.getString("startDate"));// 检查日期格式
                if (!k) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "价格有效期从" + object.getString("startDate") + "日期有误，正确格式：yyyy-MM-dd");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
                // 合同有效期从的年份判断
                String startDate = object.getString("startDate");
                Integer startDateNumber = Integer.parseInt(startDate.substring(0, 2));
                if (startDateNumber < 19) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "价格有效期从" + object.getString("startDate") + "年份有误，正确格式：yyyy-MM-dd");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
            if(!ObjectUtils.isEmpty(object.getString("endDate"))){
                boolean key = isDate(object.getString("endDate"));
                if (!key) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "价格有效期至" + object.getString("endDate") + "日期有误，正确格式：yyyy-MM-dd");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
                // 合同有效期至的年份判断
                String endDate = object.getString("endDate");
                Integer endDateNumber = Integer.parseInt(endDate.substring(0, 2));
                LOGGER.info("年份至---------------------：" + endDateNumber);
                if (endDateNumber < 19) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "价格有效期至" + object.getString("endDate") + "年份有误，正确格式：yyyy-MM-dd");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }

            // 判断组别名称
            String groupName = object.getString("groupName");
            if (groupName != null && !"".equals(groupName)) {
                Map<String, Object> map = new HashMap<>();
                map.put("auctionHeaderId", auctionHeaderId);
                map.put("groupName", groupName);
                List<SrmPonAuctionGroupsEntity_HI> list = srmPonAuctionGroupsDAO_HI.findByProperty(map);
                if (null != list && list.size() > 0) {
                    object.put("groupId", list.get(0).getGroupId());
                } else {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "组别" + object.getString("groupName") + "不存在，请先添加组别并且保");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }

            // 处理物料编码
            if (!(object.getString("itemCode") == null || "".equals(object.getString("itemCode")))) {
                String itemCode = object.getString("itemCode");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("item_Code", itemCode);
                jsonObject.put("parentInstId", parentInstId);
                jsonObject.put("varUserId", userId);
                Pagination<SrmBaseItemsEntity_HI_RO> baseItemListPagination = iSrmBaseItem.findSrmBaseItemList(jsonObject, 1, 9000);
                if (baseItemListPagination != null) {
                    List<SrmBaseItemsEntity_HI_RO> baseItemList = baseItemListPagination.getData();
                    if (baseItemList.size() > 0) {
                        SrmBaseItemsEntity_HI_RO entity = baseItemList.get(0);
                        if (!"ACT".equals(entity.getItemStatus())) {
                            e = new JSONObject();
                            e.put("ERR_MESSAGE", "物料编码" + itemCode + "不是生效状态");
                            e.put("ROW_NUM", i + 1);
                            error.add(e);
                            continue;
                        }
                        //判断该物料编码是否已经存在洽谈行表
                        Map<String, Object> mapV = new HashMap<>();
                        mapV.put("itemId", entity.getItemId());
                        mapV.put("auctionHeaderId", auctionHeaderId);
                        List<SrmPonAuctionItemsEntity_HI> itemslist = srmPonAuctionItemsDAO_HI.findByProperty(mapV);
                        if (null != itemslist && itemslist.size() > 0) {
                            e = new JSONObject();
                            e.put("ERR_MESSAGE", "物料编码" + itemCode + "已经存在于标的物");
                            e.put("ROW_NUM", i + 1);
                            error.add(e);
                            continue;
                        }
                        object.put("itemId", entity.getItemId());
                        object.put("itemDescription", entity.getItemName());//取物料名称
                        object.put("unitOfMeasure", entity.getUomCode());
                        object.put("categoryId", entity.getCategoryId());
                        object.put("specification", entity.getSpecification());
                        object.put("cost", entity.getCost());
                    } else {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "物料编码" + itemCode + "不存在");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }
                } else {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "物料编码" + itemCode + "不存在");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }else{
                if(!ObjectUtils.isEmpty(object.getString("costBudget"))){
                    object.put("cost", new BigDecimal(object.getString("costBudget")));
                }else{
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "描述招标物料需填写成本");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }


                if(StringUtils.isEmpty(object.getString("fullCategoryName"))){
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "描述招标物料需填写分类名称");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }else{
                    List<SrmBaseCategoriesEntity_HI> baseCategories=srmBaseCategoriesDAO_HI.findByProperty("fullCategoryName",object.getString("fullCategoryName"));
                    if(ObjectUtils.isEmpty(baseCategories)){
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "分类名称不存在");
                        e.put("ROW_NUM", i + 1);
                        error.add(e);
                        continue;
                    }else{
                        object.put("categoryId", baseCategories.get(0).getCategoryId());
                    }
                }

                //处理单位
                if (!(object.getString("unitOfMeasureName") == null || "".equals(object.getString("unitOfMeasureName")))) {
                    String unitOfMeasureName = object.getString("unitOfMeasureName"); // 检查输入的单位
                    Map<String, Object> mapV = new HashMap<>();
                    mapV.put("lookupType", "BASE_ITEMS_UNIT");
                    mapV.put("meaning", unitOfMeasureName);
                    List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
                    //if(!ObjectUtils.isEmpty(object.getString("itemCode"))){
                        if (lookupValueList == null || lookupValueList.size() < 1 || "".equals(lookupValueList)) {
                            e = new JSONObject();
                            e.put("ERR_MESSAGE", "单位" + unitOfMeasureName + "输入有误");
                            e.put("ROW_NUM", i + 1);
                            error.add(e);
                            continue;
                        } else {
                            SaafLookupValuesEntity_HI entity = lookupValueList.get(0);
                            if (null != entity.getEnabledFlag() && "Y".equals(entity.getEnabledFlag())) {
                                object.put("unitOfMeasure", lookupValueList.get(0).getLookupCode());
                            } else {
                                e = new JSONObject();
                                e.put("ERR_MESSAGE", "单位" + unitOfMeasureName + "是未生效状态");
                                e.put("ROW_NUM", i + 1);
                                error.add(e);
                                continue;
                            }
                        }
                    /*}else{
                        object.put("unitOfMeasure", unitOfMeasureName);
                    }*/
                }else{
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "描述招标物料需填写单位");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }

                if(!ObjectUtils.isEmpty(object.getString("specification"))){
                    object.put("specification", object.getString("specification"));
                }else{
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "描述招标物料需填写规格型号");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }



            }
        }
        return error;
    }

    /**
     * Description：导入校验——询价-网站参考价
     * @param jsonArray 待校验数组
     * @param info 导入参数
     * @return JSONArray
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public JSONArray checkWebArray(JSONArray jsonArray, JSONObject info) {
        if (jsonArray.isEmpty()) {
            return null;
        }
        Integer auctionHeaderId = Integer.parseInt(info.getString("auctionHeaderId"));
        Integer parentInstId = Integer.parseInt(info.getString("parentInstId"));
        JSONArray error = new JSONArray();
        JSONObject e = null;
        for (Integer i = 0; i < jsonArray.size(); i++) { // 检验数据正确性
            JSONObject object = jsonArray.getJSONObject(i);
            if (!ObjectUtils.isEmpty(object.getString("webReferencePrice"))) {
                boolean temp = isBigDecimal(object.getString("webReferencePrice")); // 检查输入的数量是否为BigDecimal
                if (!temp) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "网站参考价" + object.getString("webReferencePrice") + "非数值类型，请重新输入");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
            //判断同一标的物网站参考价是否一致
            for (int j = i + 1; j < jsonArray.size(); j++) {
                if (!jsonArray.getJSONObject(j).getString("webReferencePrice").equals(object.getString("webReferencePrice")) && jsonArray.getJSONObject(j).getString("itemCode").equals(object.getString("itemCode"))) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "网站参考价与第" + (j + 1) + "行同标的物网站参考价不一致");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }
            // 处理物料编码
            JSONObject jsonObject = new JSONObject();
            if(!StringUtils.isEmpty(object.getString("itemCode"))){
                String itemCode = object.getString("itemCode");
                jsonObject.put("itemCode", itemCode);
            }else{
                String itemDescription = object.getString("itemDescription");
                jsonObject.put("itemDescription", itemDescription);
            }
            jsonObject.put("auctionHeaderId", auctionHeaderId);
            List<SrmPonAuctionItemsEntity_HI_RO> auctionItems = getAuctionItemsListByItemCode(jsonObject);
            if (null != auctionItems) {
                object.put("auctionLineId", auctionItems.get(0).getAuctionLineId());
                object.put("itemId", auctionItems.get(0).getItemId());
            }


            //处理供应商名称
            if (!ObjectUtils.isEmpty(object.getString("supplierName"))) {
                List<SrmPosSupplierInfoEntity_HI> supplierList = srmPosSupplierInfoDAO_HI.findByProperty("supplierName", object.getString("supplierName"));
                if (!ObjectUtils.isEmpty(supplierList)) {
                    object.put("supplierId", supplierList.get(0).getSupplierId());
                }
            }

            if (!ObjectUtils.isEmpty(object.getString("awardQuantity"))) {
                boolean flag = isBigDecimal(object.getString("awardQuantity"));// 检查输入的数量是否为BigDecimal
                if (!flag) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "供应商中标数量" + object.getString("awardProportion") + "非数值类型，请重新输入");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }

            //后勤增加市场询价
            if (!ObjectUtils.isEmpty(object.getString("marketInquiry"))) {
                boolean temp = isBigDecimal(object.getString("marketInquiry")); // 检查输入的数量是否为BigDecimal
                if (!temp) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "市场询价" + object.getString("marketInquiry") + "非数值类型，请重新输入");
                    e.put("ROW_NUM", i + 1);
                    error.add(e);
                    continue;
                }
            }

        }
        /*List<SrmPonAuctionItemsEntity_HI_RO> list = JSON.parseArray(jsonArray.toJSONString(), SrmPonAuctionItemsEntity_HI_RO.class);
        List<String> itemCodeList = list.stream().map(SrmPonAuctionItemsEntity_HI_RO::getItemCode).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());

        for(int i=0;i<itemCodeList.size();i++){
           BigDecimal awardQuantity=new BigDecimal("0");
            for(int j=0;j<jsonArray.size();j++){
                if(jsonArray.getJSONObject(j).getString("itemCode").equals(itemCodeList.get(i))){
                    BigDecimal quantity=new BigDecimal(jsonArray.getJSONObject(j).getString("awardQuantity"));
                    awardQuantity=awardQuantity.add(quantity);
                }
               }
            if(awardQuantity.compareTo(new BigDecimal("100"))!=0){
                e = new JSONObject();
                e.put("ERR_MESSAGE", "物料"+itemCodeList.get(i)+"各个供应商总中标数量需等于标的物数量");
                for(int j=0;j<jsonArray.size();j++){
                    if(jsonArray.getJSONObject(j).getString("itemCode").equals(itemCodeList.get(i))){
                        e.put("ROW_NUM", j + 1);
                        break;
                    }
                }
                error.add(e);
                continue;
            }
        }*/
        List<SrmPonAuctionItemsEntity_HI> itemList=srmPonAuctionItemsDAO_HI.findByProperty("auctionHeaderId",auctionHeaderId);
        for(int i=0;i<itemList.size();i++){
            BigDecimal awardQuantity=new BigDecimal("0");
            for(int j=0;j<jsonArray.size();j++){
                if(Integer.valueOf(jsonArray.getJSONObject(j).getString("auctionLineId")).equals(itemList.get(i).getAuctionLineId())){
                    if(!ObjectUtils.isEmpty(jsonArray.getJSONObject(j).getString("awardQuantity"))){
                        BigDecimal quantity=new BigDecimal(jsonArray.getJSONObject(j).getString("awardQuantity"));
                        awardQuantity=awardQuantity.add(quantity);
                    }
                }
            }
            if(awardQuantity.compareTo(itemList.get(i).getQuantity())!=0&&awardQuantity.compareTo(BigDecimal.ZERO)!=0){
                e = new JSONObject();
                e.put("ERR_MESSAGE", "相同标的物供应商中标数量总和需等于标的物数量");
                for(int j=0;j<jsonArray.size();j++){
                    if(Integer.valueOf(jsonArray.getJSONObject(j).getString("auctionLineId")).equals(itemList.get(i).getAuctionLineId())){
                        e.put("ROW_NUM", j + 1);
                        break;
                    }
                }
                error.add(e);
                continue;
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
        try {
            BigDecimal bd = new BigDecimal(integer);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Description：登录界面招标公告详情行
     * @param params 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return Pagination<SrmPonAuctionItemsEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPonAuctionItemsEntity_HI_RO> findAuctionListLines(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info("参数：" + params.toString());
        StringBuffer querySql = null;
        Map<String, Object> map = new HashMap<>();
        Integer auctionHeaderId = params.getInteger("auctionHeaderId");
        if (auctionHeaderId == null || "".equals(auctionHeaderId)) {
            return null;
        }
        querySql = new StringBuffer(SrmPonAuctionItemsEntity_HI_RO.QUERY_AUCTION_ITEMS_SUPPLISER_SQL);
        SaafToolUtils.parperParam(params, "t.auction_header_id", "auctionHeaderId", querySql, map, "=");
        System.out.println(querySql);
        String countSql = "select count(1) from (" + querySql + ")";
        Pagination<SrmPonAuctionItemsEntity_HI_RO> list = srmPonAuctionItemsDAO_HI_RO.findPagination(querySql,countSql, map, pageIndex, pageRows);
        return list;
    }

    /**
     * Description：导入模板下载
     * @param jsonParams 参数
     * @param pageIndex 起始页
     * @param pageRows 行数
     * @return Pagination<SrmPonAuctionItemsEntity_HI_RO>
     * =======================================================================
     *  Version    Date                Updated By     Description
     *  -------    ----------------  -----------    ---------------
     *  V1.0       2020-03-19           zwj             创建
     * =======================================================================
     */
    public Pagination<SrmPonBidItemPricesEntity_HI_RO> findAuctionItemsPriceList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            Integer auctionHeaderId = jsonParams.getInteger("auctionHeaderId"); //洽谈Id
            StringBuffer queryString = new StringBuffer();
            /*queryString.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BID_ITEMS_SQL);
            queryString.append(" AND t.auction_header_id = :auctionHeaderId ");
            queryString.append(" ORDER BY t.auction_line_id ASC ");
            queryParamMap.put("auctionHeaderId",auctionHeaderId);*/
            queryString.append(SrmPonBidItemPricesEntity_HI_RO.QUERY_BID_ITEMS_SQL_ALL);
            queryString.append(" AND l.auction_header_id = :auctionHeaderId ");
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" ORDER BY l.auction_line_id ASC,h.Supplier_Id");
            queryParamMap.put("auctionHeaderId", auctionHeaderId);

            Pagination<SrmPonBidItemPricesEntity_HI_RO> result = srmPonBidItemPricesDAO_HI_RO.findPagination(queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * Description：删除采购申请行
     * @param params 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteSrmPonAuctionItem(JSONObject params) throws Exception {
        LOGGER.info("删除信息，参数：" + params.toString());
        JSONArray lineIds = params.getJSONArray("data");
        if(null == lineIds || lineIds.isEmpty()){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        List<SrmPonAuctionItemsEntity_HI> linesList = null;
        for (int i = 0, j = lineIds.size(); i < j; i++) {
            Integer auctionLineId = lineIds.getInteger(i);
            linesList = srmPonAuctionItemsDAO_HI.findByProperty("auctionLineId", auctionLineId);
            if (!(auctionLineId == null || "".equals(auctionLineId))) {
                if (linesList != null && linesList.size() > 0) {
                    srmPonAuctionItemsDAO_HI.delete(linesList);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除标的行，" + params.getString("auctionLineId") + "不存在", 0, null);
            }
        }
        return SToolUtils.convertResultJSONObj("S", "删除成功", linesList.size(), null);
    }

}
