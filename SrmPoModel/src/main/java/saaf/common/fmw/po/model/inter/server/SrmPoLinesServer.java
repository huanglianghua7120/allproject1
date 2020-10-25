package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SrmBaseNotificationsEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafInstitutionEntity_HI_RO;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoHeadersEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoLinesEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLinesEntity_HI_RO;
import saaf.common.fmw.po.model.inter.IPurchaseOrder;
import saaf.common.fmw.po.model.inter.ISrmPoLines;
import saaf.common.fmw.utils.SrmUtils;

import java.math.BigDecimal;
import java.util.*;
/**
 * Project Name：SrmPoLinesServer
 * Company Name：SIE
 * Program Name：
 * Description：采购订单行
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoLinesServer")
public class SrmPoLinesServer implements ISrmPoLines {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoLinesServer.class);

    @Autowired
    private ViewObject<SrmPoHeadersEntity_HI> srmPoHeadersDAO_HI;

    @Autowired
    private ViewObject<SrmPoLinesEntity_HI> srmPoLinesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPoLinesEntity_HI_RO> srmPoLinesDAO_HI_RO;

    @Autowired
    private BaseViewObject<SaafInstitutionEntity_HI_RO> saafInstitutionDAO_HI_RO;//组织表

    @Autowired
    private ViewObject<SrmBaseNotificationsEntity_HI> srmBaseNotificationsDAO_HI;//系统通知表

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;//规则编号生成

    @Autowired
    private IPurchaseOrder purchaseOrder;

    public SrmPoLinesServer() {
        super();
    }

    /**
     * Description：目录采购查询list（分页）
     * @param jsonParams 查询条件参数
     * @param pageIndex 页码
     * @param pageRows 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPoLinesEntity_HI_RO> findSrmPoLinesInfoList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Integer userId = jsonParams.getInteger("varUserId");//当前登录用户Id
        String searchFlag = "";//点击搜索按钮的标识
        Integer categoryId = null;//采购分类ID
        String itemCodeOrName = "";//物料名称或物料编码
        String supplierNumberOrName = "";//供应商编码或名称
        if (null != jsonParams.getInteger("categoryId") && !"".equals(jsonParams.getInteger("categoryId"))) {
            categoryId = jsonParams.getInteger("categoryId");
        }
        if (null != jsonParams.getString("searchFlag") && !"".equals(jsonParams.getString("searchFlag"))) {
            searchFlag = jsonParams.getString("searchFlag");
        }
        if (null != jsonParams.getString("itemCodeOrName") && !"".equals(jsonParams.getString("itemCodeOrName"))) {
            itemCodeOrName = jsonParams.getString("itemCodeOrName");
        }
        if (null != jsonParams.getString("supplierNumberOrName") && !"".equals(jsonParams.getString("supplierNumberOrName"))) {
            supplierNumberOrName = jsonParams.getString("supplierNumberOrName");
        }
        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(itemCodeOrName) || SrmUtils.isContainSQL(supplierNumberOrName)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);//当前登录用户Id
        Pagination<SrmPoLinesEntity_HI_RO> result = new Pagination<>();
        if (null == searchFlag || "".equals(searchFlag)) {
            sb.append(SrmPoLinesEntity_HI_RO.QUERY_SRMPOLINESINFOLIST_SQL);
            String countSql = "select count(1) from (" + sb + ")";
            result = srmPoLinesDAO_HI_RO.findPagination(sb.toString(),countSql, map, pageIndex, pageRows);
        } else {//搜索按钮的触发事件
            sb.append(SrmPoLinesEntity_HI_RO.QUERY_SRMPOLINESINFOLISTV_SQL);
            if (null != itemCodeOrName && !"".equals(itemCodeOrName)) {//物料名称或物料编码
                sb.append(" AND (sbi.item_name like '%" + itemCodeOrName + "%' OR sbi.item_code like '%" + itemCodeOrName + "%') ");
            }
            if (null != supplierNumberOrName && !"".equals(supplierNumberOrName)) {//供应商编码或名称
                sb.append(" AND (sps.supplier_name like '%" + supplierNumberOrName + "%' OR sps.supplier_number like '%" + supplierNumberOrName + "%') ");
            }
            if (null != categoryId && !"".equals(categoryId)) {//采购分类Id查询
                String categoryIdList = findCategoryIdList(categoryId);
                if (null != categoryIdList && !"".equals(categoryIdList)) {
                    sb.append(" AND t.category_id IN (" + categoryIdList + ") ");
                }
            }
            SaafToolUtils.parperParam(jsonParams, "sph.po_number", "poNumber", sb, map, "like");//按协议单号
            String countSql = "select count(1) from (" + sb + ")";
            sb.append(" ORDER BY sbi.item_code ");
            result = srmPoLinesDAO_HI_RO.findPagination(sb.toString(),countSql, map, pageIndex, pageRows);
        }
        if(null != result && null != result.getData() && result.getData().size()>0){
            for (SrmPoLinesEntity_HI_RO k : result.getData()) {
                StringBuffer sbv = new StringBuffer();
                sbv.append(SaafInstitutionEntity_HI_RO.QUERY_LIST_SQL);
                sbv.append(" AND t2.inst_id = t.inst_id ");
                sbv.append(" AND t.inst_type = 'ORG' ");
                sbv.append(" AND t2.user_id = " + userId);
                sbv.append(" AND t.inst_id = " + k.getOrgId());
                List<SaafInstitutionEntity_HI_RO> list = saafInstitutionDAO_HI_RO.findList(sbv);
                if (null == list || list.size() == 0 || "".equals(list)) {
                    k.setIsShowPrice("N");
                } else {
                    k.setIsShowPrice("Y");
                }
            }
        }
        return result;
    }

    /**
     * Description：根据categoryId获取采购分类的父节点及其子节点的categoryIdList
     * @param categoryId 品类ID
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public String findCategoryIdList(Integer categoryId) {
        String categoryIdList = "";
        StringBuffer sb = new StringBuffer("SELECT get_category_id_List(" + categoryId + ") AS categoryIdList");
        List<SrmPoLinesEntity_HI_RO> list = srmPoLinesDAO_HI_RO.findList(sb.toString());
        if (null != list && list.size() > 0) {
            categoryIdList = list.get(0).getCategoryIdList();
        }
        return categoryIdList;
    }

    /**
     * Description：订单确认（确认APPROVED）——内部，所有订单行的反馈状态改为反馈审核通过——系统代办通知
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updatePoLineStatus(JSONObject jsonParams) {
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        String feedbackStatus = jsonParams.getString("feedbackStatus"); // 操作按钮
        if (null == feedbackStatus || "".equals(feedbackStatus)) {
            return SToolUtils.convertResultJSONObj("E", "操作字符为空", 0, null);
        }
        Integer poHeaderId = jsonParams.getInteger("poHeaderId");
        if (null == poHeaderId || "".equals(poHeaderId)) {
            return SToolUtils.convertResultJSONObj("E", "参数为" + poHeaderId, 0, null);
        }
        if ("APPROVED".equals(feedbackStatus)) { //确认操作
            List<SrmPoLinesEntity_HI> linesList = srmPoLinesDAO_HI.findByProperty("poHeaderId", poHeaderId);
            if (null != linesList && linesList.size() > 0) {
                Iterator<SrmPoLinesEntity_HI> it = linesList.iterator();
                while (it.hasNext()) {
                    SrmPoLinesEntity_HI tem = it.next();
                    if (null != tem.getFeedbackStatus() && !"SUBMITTED".equals(tem.getFeedbackStatus())) {//过滤订单行的反馈状态不是已反馈待审核SUBMITTED
                        it.remove();
                    }
                }
            }
            if (null != linesList && linesList.size() > 0) {
                for (SrmPoLinesEntity_HI k : linesList) {
                    k.setFeedbackStatus(feedbackStatus);
                    k.setOperatorUserId(userId);
                    if (k.getFeedbackAdjustDate() != null) {
                        k.setOriginalDemandDate(k.getDemandDate());
                        k.setDemandDate(k.getFeedbackAdjustDate());
                    }
                    if (k.getFeedbackAdjustQty() != null) {
                        k.setOriginalDemandQty(k.getDemandQty());
                        k.setDemandQty(k.getFeedbackAdjustQty());
                    }
                }
                srmPoLinesDAO_HI.save(linesList);
            }
            //插入系统通知
            SrmBaseNotificationsEntity_HI notificationsEntity = new SrmBaseNotificationsEntity_HI();
            try {
                String notificationCode = saafSequencesUtil.getDocSequences("srm_base_notifications".toUpperCase(), "DD-" + DateUtil.date2Str(new Date(), "yyyyMMdd"), 4);
                notificationsEntity.setNotificationCode(notificationCode);
            } catch (Exception e) {
                LOGGER.error("创建系统通知编号出错！", e.getMessage());
                return SToolUtils.convertResultJSONObj("E", "创建系统通知编号出错！", 0, null);
            }
            notificationsEntity.setNotificationType("12");//订单确认
            notificationsEntity.setNotificationStatus("2");//已处理
            notificationsEntity.setTableId(poHeaderId);
            notificationsEntity.setTableIdName("poHeaderId");
            notificationsEntity.setTableName("srm_po_headers");
            notificationsEntity.setFunctionUrl("home.purchaseOrderDetail");
            notificationsEntity.setOperatorUserId(userId);
            srmBaseNotificationsDAO_HI.save(notificationsEntity);
            jsonData.put("poHeaderId", poHeaderId);
            return SToolUtils.convertResultJSONObj("S", "确认成功", 1, jsonData);
        }
        return SToolUtils.convertResultJSONObj("E", "没有操作", 0, null);
    }

    /**
     * Description：订单反馈（确认CONFIRMED）——供应商，订单行的反馈状态改为已确认CONFIRMED，反馈内容改为确认CONFIRM——系统代办通知
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updatePoLineFeedBackStatus(JSONObject jsonParams) {
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        String feedbackStatus = jsonParams.getString("feedbackStatus"); // 操作按钮
        if (null == feedbackStatus || "".equals(feedbackStatus)) {
            return SToolUtils.convertResultJSONObj("E", "操作字符为空", 0, null);
        }
        Integer poLineId = jsonParams.getInteger("poLineId");
        if (null == poLineId || "".equals(poLineId)) {
            return SToolUtils.convertResultJSONObj("E", "参数为" + poLineId, 0, null);
        }
        if ("CONFIRMED".equals(feedbackStatus)) { //确认操作
            SrmPoLinesEntity_HI poLinesEntity = srmPoLinesDAO_HI.getById(poLineId);
            if (null != poLinesEntity) {
                poLinesEntity.setFeedbackStatus("CONFIRMED");
                poLinesEntity.setFeedbackResult("CONFIRM");
                poLinesEntity.setOperatorUserId(userId);
                srmPoLinesDAO_HI.saveEntity(poLinesEntity);
                //插入系统通知
                SrmBaseNotificationsEntity_HI notificationsEntity = new SrmBaseNotificationsEntity_HI();
                try {
                    String notificationCode = saafSequencesUtil.getDocSequences("srm_base_notifications".toUpperCase(), "DDFK-" + DateUtil.date2Str(new Date(), "yyyyMMdd"), 4);
                    notificationsEntity.setNotificationCode(notificationCode);
                } catch (Exception e) {
                    LOGGER.error("创建系统通知编号出错！", e.getMessage());
                    return SToolUtils.convertResultJSONObj("E", "创建系统通知编号出错！", 0, null);
                }
                notificationsEntity.setNotificationType("22");//订单反馈
                notificationsEntity.setNotificationStatus("2");//已处理
                notificationsEntity.setTableId(poLineId);
                notificationsEntity.setTableIdName("poLineId");
                notificationsEntity.setTableName("srm_po_lines");
                notificationsEntity.setFunctionUrl("home.purchaseOrderDetailForSupplier");
                notificationsEntity.setOperatorUserId(userId);
                srmBaseNotificationsDAO_HI.save(notificationsEntity);
                jsonData.put("poLineId", poLineId);
                return SToolUtils.convertResultJSONObj("S", "确认成功", 1, jsonData);
            }
        }
        return SToolUtils.convertResultJSONObj("E", "没有操作", 0, null);
    }

    /**
     * Description：拆分订单行
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveSplitPoLine(JSONObject jsonParams) throws Exception{
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        Integer poHeaderId = jsonParams.getInteger("poHeaderId");
        Integer poLineId = jsonParams.getInteger("poLineId");
        SrmPoLinesEntity_HI line = srmPoLinesDAO_HI.getById(poLineId);
        if (null == line){
            return SToolUtils.convertResultJSONObj("E", "订单行不存在！", 0, null);
        }
        //校验是否已验收
        if (!"OPEN".equals(line.getStatus())){
            return SToolUtils.convertResultJSONObj("E", "只能对状态为打开的订单行进行拆分！", 0, null);
        }
        JSONArray splitLinesArray = jsonParams.getJSONArray("splitRows");
        List<SrmPoLinesEntity_HI> splitLinesList = new ArrayList<>();
        if (null != splitLinesArray && splitLinesArray.size() > 0){
            //获取最大行号
            Integer maxLineNumber = this.findPoLineNumberMax(poHeaderId);
            for (int i = 0;i < splitLinesArray.size(); i ++){
                maxLineNumber ++;
                JSONObject splitLineObject = (JSONObject)splitLinesArray.get(i);
                SrmPoLinesEntity_HI splitLine = new SrmPoLinesEntity_HI();
                PropertyUtils.copyProperties(splitLine, line);
                splitLine.setPoLineId(null);
                splitLine.setLineNumber(maxLineNumber);
                splitLine.setDemandQty(splitLineObject.getBigDecimal("demandQty"));
                splitLine.setDemandDate(splitLineObject.getDate("demandDate"));
                splitLine.setOriginalDemandDate(splitLineObject.getDate("originalDemandDate"));
                splitLine.setTaxTotalPrice(splitLine.getTaxPrice().multiply(splitLine.getDemandQty()).setScale(4, BigDecimal.ROUND_HALF_UP));
                splitLine.setNonTaxTotalPrice(splitLine.getNonTaxPrice().multiply(splitLine.getDemandQty()).setScale(4, BigDecimal.ROUND_HALF_UP));
                splitLine.setVersionNum(0);
                splitLine.setOperatorUserId(userId);
                splitLinesList.add(splitLine);
            }
            srmPoLinesDAO_HI.saveOrUpdateAll(splitLinesList);
            //删除原PO行
            srmPoLinesDAO_HI.delete(line);
            srmPoLinesDAO_HI.fluch();
        }
        return SToolUtils.convertResultJSONObj("S", "保存成功", 1, null);
    }

    /**
     * Description：取消订单行
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveCancelPoLine(JSONObject jsonParams) throws Exception{
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        Integer poHeaderId = jsonParams.getInteger("poHeaderId");
        JSONArray cancelLinesArray = jsonParams.getJSONArray("cancelRows");
        List<SrmPoLinesEntity_HI> cancelLinesList = new ArrayList<>();
        if (null != cancelLinesArray && cancelLinesArray.size() > 0){
            for (int i = 0;i < cancelLinesArray.size(); i ++){
                JSONObject cancelLineObject = (JSONObject)cancelLinesArray.get(i);
                SrmPoLinesEntity_HI cancelLine = srmPoLinesDAO_HI.getById(cancelLineObject.getInteger("poLineId"));
                if (null == cancelLine){
                    return SToolUtils.convertResultJSONObj("E", "第" + cancelLineObject.getString("lineNumber") + "行不存在！请重新查询后操作", 0, null);
                }
                //只能取消状态为 打开 的订单行
                if (!"OPEN".equals(cancelLine.getStatus())){
                    return SToolUtils.convertResultJSONObj("E", "第"+ cancelLineObject.getString("lineNumber") + "行状态不是打开，无法取消", 0, null);
                }
                cancelLine.setStatus("CANCELLED");
                cancelLine.setOperatorUserId(userId);
                cancelLinesList.add(cancelLine);
            }
            srmPoLinesDAO_HI.updateAll(cancelLinesList);
            srmPoLinesDAO_HI.fluch();
        }
        return SToolUtils.convertResultJSONObj("S", "取消成功", 1, null);
    }

    /**
     * Description：结算订单行
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveSettlePoLine(JSONObject jsonParams) throws Exception{
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        Integer poHeaderId = jsonParams.getInteger("poHeaderId");
        JSONArray settleLinesArray = jsonParams.getJSONArray("settleRows");
        List<SrmPoLinesEntity_HI> settleLinesList = new ArrayList<>();
        if (null != settleLinesArray && settleLinesArray.size() > 0){
            for (int i = 0;i < settleLinesArray.size(); i ++){
                JSONObject settleLineObject = (JSONObject)settleLinesArray.get(i);
                SrmPoLinesEntity_HI settleLine = srmPoLinesDAO_HI.getById(settleLineObject.getInteger("poLineId"));
                if (null == settleLine){
                    return SToolUtils.convertResultJSONObj("E", "第" + (i + 1) + "行不存在！请重新查询后操作", 0, null);
                }
                //只能对状态为 已验收 的订单行进行结算
                if (!"RECEIVED".equals(settleLine.getStatus())){
                    return SToolUtils.convertResultJSONObj("E", "第"+ (i + 1) + "行订单行状态不是“已验收”，无法进行结算！请重新查询后操作", 0, null);
                }
                settleLine.setStatus("SETTLED");
                settleLine.setOperatorUserId(userId);
                settleLinesList.add(settleLine);
            }
            srmPoLinesDAO_HI.updateAll(settleLinesList);
            srmPoLinesDAO_HI.fluch();
        }
        return SToolUtils.convertResultJSONObj("S", "结算成功", 1, null);
    }


    /**
     * Description：根据订单头ID查询最大行号
     *
     * @param poHeaderId 订单头ID
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    public Integer findPoLineNumberMax(Integer poHeaderId){
        Integer lineNumber = null;
        if (null != poHeaderId) {
            Map<String, Object> map = new HashMap<>();
            map.put("poHeaderId", poHeaderId);
            List<SrmPoLinesEntity_HI_RO> list = srmPoLinesDAO_HI_RO.findList(SrmPoLinesEntity_HI_RO.QUERY_PO_LINE_NUMBER_MAX_SQL, map);
            if (null != list && list.size() > 0) {
                lineNumber = list.get(0).getLineNumber();
            }
        }
        return lineNumber;
    }

    /**
     * Description：简单查询PO行
     * @param jsonParams 参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmPoLinesEntity_HI_RO> findPoLineSimpleList(JSONObject jsonParams) {
        StringBuffer queryString = new StringBuffer();
        Map<String, Object> queryParamMap = new HashMap<>();
        queryString.append(SrmPoLinesEntity_HI_RO.QUERY_ORDER_LINE_INFO);
        SaafToolUtils.parperParam(jsonParams, "t.po_header_id", "poHeaderId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.status", "lineStatus", queryString, queryParamMap, "=");
        List<SrmPoLinesEntity_HI_RO> list = srmPoLinesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        return list;
    }

}
