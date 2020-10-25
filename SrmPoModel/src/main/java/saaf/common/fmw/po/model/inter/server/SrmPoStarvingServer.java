package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoNoticeEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoNoticeLineEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoStarvingEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoStarvingEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoStarving;
import saaf.common.fmw.utils.SrmUtils;

import java.math.BigDecimal;
import java.util.*;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：缺料
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-20     hgq             modify
 * ==============================================================================
 */
@Component("srmPoStarvingServer")
public class SrmPoStarvingServer implements ISrmPoStarving {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoStarvingServer.class);
    @Autowired
    private BaseViewObject<SrmPoStarvingEntity_HI_RO> starvingDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPoStarvingEntity_HI> starvingDAO_HI;

    @Autowired
    private ViewObject<SrmPoNoticeEntity_HI> noticeDAO_HI;

    @Autowired
    private ViewObject<SrmPoNoticeLineEntity_HI> noticeLineDAO_HI;

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;
    /**
     * Description：查询缺料
     * @param params 参数
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Override
    public List<SrmPoStarvingEntity_HI_RO> findStarvingList(JSONObject params) throws Exception {
        StringBuffer sqlBuffer = new StringBuffer(SrmPoStarvingEntity_HI_RO.QUERY_STARVING_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        // 物料类别
        SaafToolUtils.parperParam(params, "sps.category_code", "categoryCode", sqlBuffer, map, "=");
        // 物料
        SaafToolUtils.parperParam(params, "sps.item_id", "itemId", sqlBuffer, map, "=");
        SaafToolUtils.parperParam(params, "sps.delivery_site_id", "deliverySiteId", sqlBuffer, map, "=");

        // 分厂
        SaafToolUtils.parperParam(params, "sps.inst_id", "instId", sqlBuffer, map, "=");
        if (params.getInteger("employeeId") == null) {
            sqlBuffer.append(" and isnull(sps.employee_id) ");
        } else {
            SaafToolUtils.parperParam(params, "sps.employee_id", "employeeId", sqlBuffer, map, "=");
        }

        String startDate = null;
        String endDate = null;
        if (params.getString("startDate") != null && !"".equals(params.getString("startDate").trim())) {
            startDate = params.getString("startDate");
        }
        if (params.getString("endDate") != null && !"".equals(params.getString("endDate").trim())) {
            endDate = params.getString("endDate");
        }
        if (startDate != null && endDate != null) {
            sqlBuffer.append(" AND sps.NEED_BY_DATE between :startDate  and  :endDate ");
            map.put("startDate", startDate);
            map.put("endDate", endDate);
        } else if (startDate != null && endDate == null) {
            sqlBuffer.append(" AND sps.NEED_BY_DATE >= :startDate ");
            map.put("startDate", startDate);
        }
        String countSql = "select count(1) from (" + sqlBuffer + ")";
        sqlBuffer.append(" ORDER BY sbi.item_code, sps.NEED_BY_DATE, sps.po_starving_id ");
        //TODO 每次最多显示200行
        List<SrmPoStarvingEntity_HI_RO> list = starvingDAO_HI_RO.findPagination(sqlBuffer,countSql, map, 0, 100).getData();

        List<SrmPoStarvingEntity_HI_RO> list2 = new ArrayList<SrmPoStarvingEntity_HI_RO>();
        SrmPoStarvingEntity_HI_RO row = null;
        List<SrmPoStarvingEntity_HI_RO> poList = null;
        if(null != list && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Integer supplierId = params.getInteger("supplierId");
                row = list.get(i);
                row.setStarvingNum(i + 1);
                // 查询条件供应商 跟指定供应商不一致
                if (row.getSupplierId() != null && supplierId != null && row.getSupplierId() == supplierId) {
                    SrmPoStarvingEntity_HI_RO row1 = new SrmPoStarvingEntity_HI_RO();
                    BeanUtils.copyProperties(row, row1);
                    row1.setIsMatchPo("N");
                    list2.add(row1);
                    continue;
                }

                if (supplierId == null) {
                    supplierId = row.getSupplierId();
                }

                // 匹配PO
                poList = this.findMatchSupplier(row.getInstId(), row.getItemId(), supplierId, row.getSpecialUseNum(), row.getNeedQuantity().subtract(row.getNoticeQty()),
                        list2);
                if (poList.size() == 0) {
                    SrmPoStarvingEntity_HI_RO row1 = new SrmPoStarvingEntity_HI_RO();
                    BeanUtils.copyProperties(row, row1);
                    row1.setIsMatchPo("N");
                    list2.add(row1);
                    continue;
                }
                for (SrmPoStarvingEntity_HI_RO poRow : poList) {
                    SrmPoStarvingEntity_HI_RO row1 = new SrmPoStarvingEntity_HI_RO();
                    BeanUtils.copyProperties(row, row1);
                    row1.setIsMatchPo("Y");
                    row1.setMatchQty(poRow.getMatchQty());
                    row1.setCanMatchQty(poRow.getCanMatchQty());
                    row1.setSupplierName(poRow.getSupplierName());
                    row1.setSupplierId(poRow.getSupplierId());
                    list2.add(row1);
                }
            }
        }
        return list2;
    }

    /**
     * Description：查询缺料明细
     * @param params 参数
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    public List<SrmPoStarvingEntity_HI_RO> findStarvingInfoPoList(JSONObject params) throws Exception {
        List<SrmPoStarvingEntity_HI_RO> list = new ArrayList<SrmPoStarvingEntity_HI_RO>();
        JSONArray valuesArray = params.getJSONArray("data");
        if(null != valuesArray && valuesArray.size()>0){
            for (int i = 0; i < valuesArray.size(); i++) {
                JSONObject lineJson = valuesArray.getJSONObject(i);
                Integer instId = lineJson.getInteger("instId");
                Integer itemId = lineJson.getInteger("itemId");
                Integer supplierId = lineJson.getInteger("supplierId");
                Integer starvingNum = lineJson.getInteger("starvingNum");
                BigDecimal matchQty = lineJson.getBigDecimal("matchQty");
                String categoryCode = lineJson.getString("categoryCode");
                String categoryName = lineJson.getString("categoryName");
                String demandClassify = lineJson.getString("demandClassify");
                String instName = lineJson.getString("instName");
                String itemCode = lineJson.getString("itemCode");
                String itemName = lineJson.getString("itemName");
                String supplierNumber = lineJson.getString("supplierNumber");
                String employeeName = lineJson.getString("employeeName");
                Date needByDate = lineJson.getDate("needByDate");
                String specialUseNum = lineJson.getString("specialUseNum");
                BigDecimal needQuantity = lineJson.getBigDecimal("needQuantity");
                List<SrmPoStarvingEntity_HI_RO> list2 = this.findMatchPo(instId, itemId, supplierId, specialUseNum, matchQty, list);
                for (SrmPoStarvingEntity_HI_RO row : list2) {
                    SrmPoStarvingEntity_HI_RO row1 = new SrmPoStarvingEntity_HI_RO();
                    BeanUtils.copyProperties(row, row1);
                    row1.setStarvingNum(starvingNum);
                    row1.setCategoryCode(categoryCode);
                    row1.setCategoryName(categoryName);
                    row1.setDemandClassify(demandClassify);
                    row1.setInstName(instName);
                    row1.setItemCode(itemCode);
                    row1.setItemName(itemName);
                    row1.setSupplierNumber(supplierNumber);
                    row1.setEmployeeName(employeeName);
                    row1.setNeedByDate(needByDate);
                    row1.setNeedQuantity(needQuantity);
                    row1.setSpecialUseNum(specialUseNum);
                    row1.setMatchQty(row.getMatchQty());
                    row1.setCanMatchQty(row.getCanMatchQty());
                    row1.setSupplierName(row.getSupplierName());
                    row1.setSupplierId(row.getSupplierId());
                    if (row1.getMatchQty().compareTo(new BigDecimal(0)) > 0) {
                        list.add(row1);
                    }
                }
            }
            Collections.sort(list, new SrmPoStarvingEntity_HI_RO());
        }
        return list;
    }
    /**
     * Description：匹配的供应商
     * @param instId
     * @param itemId
     * @param supplierId
     * @param specialUseNum
     * @param needQuantity
     * @param matchedList
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    public List<SrmPoStarvingEntity_HI_RO> findMatchSupplier(Integer instId, Integer itemId, Integer supplierId, String specialUseNum, BigDecimal needQuantity, List<SrmPoStarvingEntity_HI_RO> matchedList) {
        List<SrmPoStarvingEntity_HI_RO> list = null;
        StringBuffer sqlBuffer = new StringBuffer(SrmPoStarvingEntity_HI_RO.QUERY_SUM_PO_INFO);
        sqlBuffer.append(" AND l.inst_id = " + instId);
        sqlBuffer.append(" AND l.item_id = " + itemId);
        if (!SrmUtils.isNvl(specialUseNum)) {
            //验证字符串是否含有SQL关键字及字符，有则返回NULL
            if (SrmUtils.isContainSQL(specialUseNum)) {
                return null;
            }
            sqlBuffer.append(" AND l.SPECIAL_USE_NUM = '" + specialUseNum + "'");
        } else {
            sqlBuffer.append("AND ifnull(l.SPECIAL_USE_NUM,'') = ''");
        }
        if (supplierId != null) {
            sqlBuffer.append(" AND h.supplier_id = " + supplierId);
        }
        sqlBuffer.append(" group by l.inst_id,l.item_id,h.supplier_id,si.supplier_name,si.supplier_number,ifnull(l.SPECIAL_USE_NUM,'')  ");
        list = starvingDAO_HI_RO.findList(sqlBuffer);
        if (needQuantity != null) {
            //去掉已经分配的数量
            for (SrmPoStarvingEntity_HI_RO row : list) {
                for (SrmPoStarvingEntity_HI_RO matchedRow : matchedList) {
                    if ("Y".equals(matchedRow.getIsMatchPo()) && instId.equals(matchedRow.getInstId()) && itemId.equals(matchedRow.getItemId())
                            && row.getSupplierId().equals(matchedRow.getSupplierId()) && SrmUtils.compareSame(specialUseNum, matchedRow.getSpecialUseNum())) {
                        if (row.getCanMatchQty().compareTo(matchedRow.getMatchQty()) > -1) {
                            row.setCanMatchQty(row.getCanMatchQty().subtract(matchedRow.getMatchQty()));
                        } else {
                            row.setCanMatchQty(new BigDecimal(0));
                            break;
                        }
                    }
                }
            }
            this.sortByCanMatchQty(list);
            for (SrmPoStarvingEntity_HI_RO row : list) {
                if (row.getCanMatchQty().compareTo(needQuantity) < 0) {
                    row.setMatchQty(row.getCanMatchQty());
                    needQuantity = needQuantity.subtract(row.getCanMatchQty());
                } else {
                    row.setMatchQty(needQuantity);
                    needQuantity = new BigDecimal(0);
                }
            }
        }
        return list;
    }
    /**
     * Description：匹配的PO
     * @param instId 组织ID
     * @param itemId 物料ID
     * @param supplierId 供应商ID
     * @param specialUseNum
     * @param needQuantity
     * @param matchedList
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    public List<SrmPoStarvingEntity_HI_RO> findMatchPo(Integer instId, Integer itemId, Integer supplierId, String specialUseNum, BigDecimal needQuantity, List<SrmPoStarvingEntity_HI_RO> matchedList) {
        List<SrmPoStarvingEntity_HI_RO> list = null;
        StringBuffer sqlBuffer = new StringBuffer(SrmPoStarvingEntity_HI_RO.QUERY_MATCH_PO_INFO);
        sqlBuffer.append(" AND l.inst_id=" + instId);
        sqlBuffer.append(" AND l.item_id =" + itemId);
        if (supplierId != null) {
            sqlBuffer.append(" AND h.supplier_id =" + supplierId);
        }
        if (!SrmUtils.isNvl(specialUseNum)) {
            //验证字符串是否含有SQL关键字及字符，有则返回NULL
            if (SrmUtils.isContainSQL(specialUseNum)) {
                return null;
            }
            sqlBuffer.append(" AND l.SPECIAL_USE_NUM = '" + specialUseNum + "'");
        } else {
            sqlBuffer.append(" AND ifnull(l.SPECIAL_USE_NUM,'') = ''");
        }

        sqlBuffer.append(" ORDER BY l.demand_date,l.po_line_id ");
        list = starvingDAO_HI_RO.findList(sqlBuffer);
        if (matchedList != null && matchedList.size()>0) {
            //去掉已经分配的数量
            for (SrmPoStarvingEntity_HI_RO row : list) {
                for (SrmPoStarvingEntity_HI_RO matchedRow : matchedList) {
                    if (row.getPoLineId().equals(matchedRow.getPoLineId())) {
                        if (row.getCanMatchQty().compareTo(matchedRow.getMatchQty()) > -1) {
                            row.setCanMatchQty(row.getCanMatchQty().subtract(matchedRow.getMatchQty()));
                        } else {
                            row.setCanMatchQty(new BigDecimal(0));
                            break;
                        }
                    }
                }
            }
        }
        if (needQuantity != null) {
            for (SrmPoStarvingEntity_HI_RO row : list) {
                if (row.getCanMatchQty().compareTo(needQuantity) < 1) {
                    row.setMatchQty(row.getCanMatchQty());
                    row.setMarkMatchQty(row.getCanMatchQty());
                    needQuantity = needQuantity.subtract(row.getCanMatchQty());
                } else {
                    row.setMatchQty(needQuantity);
                    row.setMarkMatchQty(needQuantity);
                    needQuantity = new BigDecimal(0);
                }
            }
        }
        return list;
    }

    /**
     * Description：排序
     * @param list
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    private void sortByCanMatchQty(List<SrmPoStarvingEntity_HI_RO> list) {
        Collections.sort(list, new Comparator<SrmPoStarvingEntity_HI_RO>() {
            @Override
            public int compare(SrmPoStarvingEntity_HI_RO o1, SrmPoStarvingEntity_HI_RO o2) {
                int i = o2.getCanMatchQty().compareTo(o1.getCanMatchQty());
                return i;
            }
        });
    }
    /**
     * Description：创建缺料信息
     * @param params 参数
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * poStarvingId    NUMBER  Y
     * shortCheckDate  缺料检查日期  DATE  N
     * wipEntityNumber  工单号  VARCHAR2  N
     * instId  分厂id  NUMBER  N
     * itemCode    VARCHAR2  N
     * itemId  物料id  NUMBER  N
     * categoryCode  分类code  VARCHAR2  N
     * needQuantity  需求数量  NUMBER  N
     * needByDate  需求日期  DATE  N
     * supplierNumber  指定供应商  VARCHAR2  N
     * specialUseNum  番号  VARCHAR2  N
     * demandClassify  需求分类  VARCHAR2  N
     * employeeId  采购员id  NUMBER  N
     * noticeQty  已创建送货通知数量  NUMBER  N
     * poStarvingId    NUMBER  Y
     * shortCheckDate  缺料检查日期  DATE  N
     * wipEntityNumber  工单号  VARCHAR2  N
     * instId  分厂id  NUMBER  N
     * itemCode    VARCHAR2  N
     * itemId  物料id  NUMBER  N
     * categoryCode  分类code  VARCHAR2  N
     * needQuantity  需求数量  NUMBER  N
     * needByDate  需求日期  DATE  N
     * supplierNumber  指定供应商  VARCHAR2  N
     * specialUseNum  番号  VARCHAR2  N
     * demandClassify  需求分类  VARCHAR2  N
     * employeeId  采购员id  NUMBER  N
     * noticeQty  已创建送货通知数量  NUMBER  N
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-20     hgq             modify
     * ==============================================================================
     */
    @Override
    public JSONObject saveNoticeLines(JSONObject params) throws Exception {
        LOGGER.info("----->" + params);
        SrmPoNoticeEntity_HI notice = null;
        SrmPoNoticeLineEntity_HI noticeLine = null;
        SrmPoStarvingEntity_HI starvingEntityHi = null;
        Integer userId = params.getInteger("varUserId");
        //获取到list
        List<SrmPoStarvingEntity_HI_RO> list = new ArrayList<SrmPoStarvingEntity_HI_RO>();
        JSONArray valuesArray = params.getJSONArray("data");
        if(null == valuesArray || valuesArray.isEmpty()){
            return SToolUtils.convertResultJSONObj(ERROR_STATUS,"暂无数据",0,null);
        }
        List<String> noticeNumbers = new ArrayList<String>();
        for (int i = 0; i < valuesArray.size(); i++) {
            notice = new SrmPoNoticeEntity_HI();
            JSONObject lineJson = valuesArray.getJSONObject(i);
            BigDecimal matchQty = lineJson.getBigDecimal("matchQty");
            if (matchQty == null && matchQty.compareTo(new BigDecimal(0)) <= 0) {
                continue;
            }
            Integer instId = lineJson.getInteger("instId");
            Integer itemId = lineJson.getInteger("itemId");
            Integer supplierId = lineJson.getInteger("supplierId");
            Date needByDate = lineJson.getDate("needByDate");
            String specialUseNum = lineJson.getString("specialUseNum");
            BigDecimal needQuantity = lineJson.getBigDecimal("needQuantity");
            //---------------------------------------------------
            notice.setPoNoticeCode(saafSequencesUtil.getDocSequences("srm_po_notice".toUpperCase(), DateUtil.date2Str(new Date(), "yyyyMMdd"), 6));
            notice.setInstId(instId);
            notice.setItemId(itemId);
            notice.setPoStarvingId(lineJson.getInteger("poStarvingId"));
            notice.setSupplierId(supplierId);
            notice.setDemandDate(needByDate);
            notice.setDeliverySiteId(lineJson.getInteger("deliverySiteId"));
            notice.setQuantity(matchQty);
            notice.setEmployeeNum(lineJson.getString("employeeNumber"));
            notice.setStatus("CREATED");
            notice.setSpecialUseNum(specialUseNum);
            notice.setDemandClassify(lineJson.getString("demandClassify"));
            notice.setDocumentType("01");
            notice.setCreationDate(new Date());
            notice.setCreatedBy(userId);
            notice.setLastUpdatedBy(userId);
            notice.setLastUpdateDate(new Date());
            notice.setOperatorUserId(userId);
            noticeDAO_HI.save(notice);
            List<SrmPoStarvingEntity_HI_RO> list2 = this.findMatchPo(instId, itemId, supplierId, specialUseNum, matchQty, null);
            LOGGER.info("List: -{}", JSONObject.toJSONString(list2));
            LOGGER.info("ListSize: " + list2.size());
            BigDecimal hasMatchQty = new BigDecimal(0);
            List<SrmPoNoticeLineEntity_HI> noticeLineList = new ArrayList<SrmPoNoticeLineEntity_HI>();
            String poDocType = null;
            for (SrmPoStarvingEntity_HI_RO row : list2) {
                SrmPoStarvingEntity_HI_RO row1 = new SrmPoStarvingEntity_HI_RO();
                BeanUtils.copyProperties(row, row1);
                if (row1.getMatchQty().compareTo(new BigDecimal(0)) <= 0) {
                    continue;
                }
                hasMatchQty = hasMatchQty.add(row.getMatchQty());
                row1.setNeedByDate(needByDate);
                row1.setNeedQuantity(needQuantity);
                row1.setSpecialUseNum(specialUseNum);
                row1.setMatchQty(row.getMatchQty());
                row1.setCanMatchQty(row.getCanMatchQty());
                row1.setSupplierName(row.getSupplierName());
                row1.setSupplierId(row.getSupplierId());
                list.add(row1);
                noticeLine = new SrmPoNoticeLineEntity_HI();
                noticeLine.setPoNoticeId(notice.getPoNoticeId());
                noticeLine.setPoHeaderId(row1.getPoHeaderId());
                noticeLine.setPoLineId(row1.getPoLineId());
                noticeLine.setOperatorUserId(userId);
                noticeLineList.add(noticeLine);
                noticeLineDAO_HI.save(noticeLine);
                if (SrmUtils.isNvl(poDocType)) {
                    poDocType = row.getPoDocType();
                }
            }
            //取单据类型修改
            if (poDocType != null) {
                notice.setDocumentType(poDocType);
                noticeDAO_HI.update(notice);
            }

            if (hasMatchQty.compareTo(new BigDecimal(0)) == 0 || hasMatchQty.compareTo(notice.getQuantity()) != 0) {
                noticeDAO_HI.delete(notice);
            } else {
                noticeLineDAO_HI.save(noticeLineList);
                noticeNumbers.add(notice.getPoNoticeCode());
            }
            starvingEntityHi = starvingDAO_HI.getById(lineJson.getInteger("poStarvingId"));
            if (starvingEntityHi.getNoticeQty() == null) {
                starvingEntityHi.setNoticeQty(new BigDecimal(0));
            }
            starvingEntityHi.setNoticeQty(starvingEntityHi.getNoticeQty().add(lineJson.getBigDecimal("matchQty")));
            starvingEntityHi.setOperatorUserId(userId);
            starvingDAO_HI.update(starvingEntityHi);
            noticeDAO_HI.fluch();
            noticeLineDAO_HI.fluch();
        }
        LOGGER.info("noticeNumbers: " + noticeNumbers);
        return SToolUtils.convertResultJSONObj("S", "分配成功,已创建送货通知单" + noticeNumbers.toString(), 0, null);
    }
}
