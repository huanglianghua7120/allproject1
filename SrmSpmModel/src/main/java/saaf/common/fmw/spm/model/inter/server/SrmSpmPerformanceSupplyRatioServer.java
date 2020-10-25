package saaf.common.fmw.spm.model.inter.server;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.SrmPoSupplyProportionHEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoSupplyProportionLEntity_HI;
import saaf.common.fmw.po.model.entities.readonly.SrmPoSupplyProportionHEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.SrmSpmSupplierScoreEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceSupplyRatioEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceSupplyRatio;

import java.math.BigDecimal;
import java.rmi.ServerException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component("srmSpmPerformanceSupplyRatioServer")
public class SrmSpmPerformanceSupplyRatioServer implements ISrmSpmPerformanceSupplyRatio {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmPerformanceSupplyRatioServer.class);

    public SrmSpmPerformanceSupplyRatioServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SrmSpmPerformanceSupplyRatioEntity_HI_RO> srmSpmPerformanceSupplyRatioDAO_HI_RO;

    @Autowired
    private ViewObject<SrmSpmSupplierScoreEntity_HI> srmSpmSupplierScoreDAO_HI;

    @Autowired
    private ViewObject<SrmPoSupplyProportionHEntity_HI> srmPoSupplyProportionHDAO_HI;

    @Autowired
    private ViewObject<SrmPoSupplyProportionLEntity_HI> srmPoSupplyProportionLDAO_HI;

    @Autowired
    private BaseViewObject<SrmPoSupplyProportionHEntity_HI_RO> supplyProportionHDAO_HI_RO;


    /**
     * 确认
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updatePerformanceSupplyRatio(JSONObject paramJSON) throws Exception {
        BigDecimal total = new BigDecimal(0);
        Integer varUserId = paramJSON.getInteger("varUserId");
        List<SrmSpmPerformanceSupplyRatioEntity_HI_RO> data = null;
        JSONArray jsonArray = paramJSON.getJSONArray("data");
        if (jsonArray.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无确认的数据", 0, null);
        }
        //根据categoryCode过滤掉重复的data
        JSONArray array = uniqueArray(jsonArray, "categoryCode");
        SrmSpmSupplierScoreEntity_HI entity = null;
        List<SrmSpmSupplierScoreEntity_HI> list = new ArrayList<SrmSpmSupplierScoreEntity_HI>();
        for (int i = 0, j = array.size(); i < j; i++) {
            JSONObject obj = array.getJSONObject(i);
            String evaluateIntervalTo = obj.getString("evaluateIntervalTo"); //绩效评价区间至的日期
            //获取N月月底日期
            String lastDayOfMonthStr = getLastDayOfMonth(evaluateIntervalTo);
            Date lastDayOfMonthDate = getDateFormat(lastDayOfMonthStr, "yyyy-MM-dd");
            //获取N+1月1日日期
            String firstDayOfNextMonthStr = getFirstDayOfNextMonth(evaluateIntervalTo + "-01", "yyyy-MM-dd");
            Date firstDayOfNextMonth = getDateFormat(firstDayOfNextMonthStr, "yyyy-MM-dd");
            //根据分类编码查询供货比例表
            Integer supplyId = null;
            List<SrmPoSupplyProportionHEntity_HI_RO> dataList = findSupplyProportion(obj);
            if (dataList.size() > 0) {
                for (int k = 0; k < dataList.size(); k++) {
                    SrmPoSupplyProportionHEntity_HI_RO entityHiRo = dataList.get(k);
                    supplyId = entityHiRo.getSupplyId();
                    Date startDateActive = entityHiRo.getStartDateActive();
                    Date endDateActive = entityHiRo.getEndDateActive();
                    if (startDateActive.compareTo(firstDayOfNextMonth) < 0 && (null == endDateActive || "".equals(endDateActive))) {//日期从在N+1月1日前，日期至为空
                        //更新数据到供货比例头表
                        updateSupplyProportionH(entityHiRo, "updateType2", lastDayOfMonthDate, varUserId);
                        saveSupplyProportionH(obj, varUserId, firstDayOfNextMonth, jsonArray);//插入一条新数据
                    } else if (startDateActive.compareTo(firstDayOfNextMonth) < 0 && endDateActive.compareTo(firstDayOfNextMonth) > 0) {//日期从在N+1月1日前，日期至N+1月后
                        //更新数据到供货比例头表
                        updateSupplyProportionH(entityHiRo, "updateType2", lastDayOfMonthDate, varUserId);
                        saveSupplyProportionH(obj, varUserId, firstDayOfNextMonth, jsonArray);//插入一条新数据
                    } else if (startDateActive.compareTo(firstDayOfNextMonth) > 0) {//若已有日期从为N+1月1日后的供货比例生效数据，则将“生效Y”去掉,（4）	最后，新增同样的一条生效的供货比例头数据，日期从为N+1月1日，日期至为空
                        //更新数据到供货比例头表
                        updateSupplyProportionH(entityHiRo, "updateType1", lastDayOfMonthDate, varUserId);
                        saveSupplyProportionH(obj, varUserId, firstDayOfNextMonth, jsonArray);//插入一条新数据
                    }
                }
            } else {
                //插入数据到供货比例头表
                saveSupplyProportionH(obj, varUserId, firstDayOfNextMonth, jsonArray);
            }

            Integer categoryId = obj.getInteger("categoryId");
            Integer schemeId = obj.getInteger("schemeId");
            StringBuffer queryString = new StringBuffer(SrmSpmPerformanceSupplyRatioEntity_HI_RO.QUERY_PERFORMANCE_SUPPLY_RATIO_LIST_1);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("schemeId", schemeId);
            map.put("categoryId", categoryId);
            queryString.append(" order by ss.source_scheme_number desc, bc.category_code asc, ss.curr_supply_ratio desc");
            data = srmSpmPerformanceSupplyRatioDAO_HI_RO.findList(queryString, map);
            for (int t = 0; t < data.size(); t++) {
                Integer supplierScoreId = data.get(t).getSupplierScoreId();
                entity = srmSpmSupplierScoreDAO_HI.getById(supplierScoreId);
                BigDecimal newSupplyRatio = entity.getNewSupplyRatio();
                if (newSupplyRatio == null || newSupplyRatio.equals("")) {
                    throw new UtilsException("制定供货比例不允许为空！");
                } else {
                    total = total.add(newSupplyRatio);
                }
                entity.setStatus("CONFIRMED");
                entity.setOperatorUserId(varUserId);
                if (entity != null) {
                    list.add(entity);
                }
            }
            if (total.compareTo(new BigDecimal(100)) != 0) {
                throw new UtilsException("同一个分类下制定供货比例之和为100%！");
            }

        }
        if (list.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无确认的数据", 0, null);
        }
        try {
            srmSpmSupplierScoreDAO_HI.update(list);
            return SToolUtils.convertResultJSONObj("S", "确认成功", list.size(), null);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new UtilsException("确认失败");
        }
    }


    /**
     * 获取指定日期下个月的第一天
     * @param dateStr
     * @param format
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public static String getFirstDayOfNextMonth(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MONTH, 2);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取格式化日期
     * @param dateStr
     * @param format
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public static Date getDateFormat(String dateStr, String format) {
        try {
            if (format == null) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取某年某月的最后一天
     * @param evaluateIntervalTo
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public static String getLastDayOfMonth(String evaluateIntervalTo) {
        int year = Integer.parseInt(evaluateIntervalTo.substring(0, 4));
        int month = Integer.parseInt(evaluateIntervalTo.substring(5, 7));
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month);
        //cal.set(year,month-2,Calendar.DATE);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }


    /**
     * 根据categoryCode 查找供货比例头数据
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmPoSupplyProportionHEntity_HI_RO> findSupplyProportion(JSONObject paramJSON) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmPoSupplyProportionHEntity_HI_RO.QUERY_SUPPLY_PROPORTION_SQL_SUPPLY_RATIO);
            Map<String, Object> map = new HashMap<String, Object>();
            // 物料类型SMALL_CATEGORY_CODE
            SaafToolUtils.parperParam(paramJSON, "sph.SMALL_CATEGORY_CODE", "categoryCode", queryString, map, "=");
            queryString.append(" ORDER BY sph.SUPPLY_ID DESC ");
            List<SrmPoSupplyProportionHEntity_HI_RO> dataList = supplyProportionHDAO_HI_RO.findList(queryString, map);
            return dataList;
        } catch (Exception e) {
            throw new Exception("查询供货比例异常");
        }
    }


    /**
     * 更新供货比例表行表
     * @param supplyId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public void updateSupplyProportionL(Integer supplyId, Integer varUserId) throws Exception {
        SrmPoSupplyProportionLEntity_HI entityHi = null;
        try {
            entityHi = srmPoSupplyProportionLDAO_HI.findByProperty("supplyId", supplyId).get(0);
            entityHi.setEnableFlag("N");
            entityHi.setLastUpdatedBy(varUserId);
            srmPoSupplyProportionLDAO_HI.update(entityHi);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("更新失败");
        }
    }



    /**
     * 更新供货比例表头表
     * @param entity
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public void updateSupplyProportionH(SrmPoSupplyProportionHEntity_HI_RO entity, String updateType, Date lastDayOfMonthDate, Integer varUserId) throws Exception {
        SrmPoSupplyProportionHEntity_HI poSupplyProportionHEntity_HI = null;
        try {
            Integer supplyId = entity.getSupplyId();
            poSupplyProportionHEntity_HI = srmPoSupplyProportionHDAO_HI.findByProperty("supplyId", supplyId).get(0);
            if ("updateType1".equals(updateType)) {
                poSupplyProportionHEntity_HI.setStatus("N");
                poSupplyProportionHEntity_HI.setDescription("绩效自动失效");
            } else if ("updateType2".equals(updateType)) {
                poSupplyProportionHEntity_HI.setEndDateActive(lastDayOfMonthDate);
                poSupplyProportionHEntity_HI.setDescription("绩效自动失效");
            }
            poSupplyProportionHEntity_HI.setOperatorUserId(varUserId);
            srmPoSupplyProportionHDAO_HI.update(poSupplyProportionHEntity_HI);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("更新失败");
        }
    }


    /**
     * 插入数据到供货比例头表
     * @param obj
     * @param varUserId
     * @param firstDayOfNextMonth
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public void saveSupplyProportionH(JSONObject obj, Integer varUserId, Date firstDayOfNextMonth, JSONArray jsonArray) throws Exception {
        SrmPoSupplyProportionHEntity_HI poSupplyProportionHEntity_HI = null;
        try {
            poSupplyProportionHEntity_HI = new SrmPoSupplyProportionHEntity_HI();
            poSupplyProportionHEntity_HI.setSupplyType("CLASSIFY");
            poSupplyProportionHEntity_HI.setSmallCategoryCode(obj.getString("categoryCode"));
            poSupplyProportionHEntity_HI.setSmallCategoryName(obj.getString("categoryName"));
            poSupplyProportionHEntity_HI.setStartDateActive(firstDayOfNextMonth);
            poSupplyProportionHEntity_HI.setEndDateActive(null);
            poSupplyProportionHEntity_HI.setStatus("Y");
            poSupplyProportionHEntity_HI.setDescription("绩效自动新增");
            poSupplyProportionHEntity_HI.setOperatorUserId(varUserId);
            srmPoSupplyProportionHDAO_HI.save(poSupplyProportionHEntity_HI);
            //保存行表
            Integer supplyId = poSupplyProportionHEntity_HI.getSupplyId();
            if (supplyId != null) {
                saveSupplyProportionL(jsonArray, supplyId, varUserId);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("保存失败");
        }
    }


    /**
     * 插入数据到供货比例行表
     * @param jsonArray
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public void saveSupplyProportionL(JSONArray jsonArray, Integer supplyId, Integer varUserId) throws Exception {
        SrmPoSupplyProportionLEntity_HI entity_HI = null;
        try {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject valuesJson = jsonArray.getJSONObject(i);
                BigDecimal newSupplyRatio = valuesJson.getBigDecimal("newSupplyRatio");//等于0不插入
                if (newSupplyRatio.compareTo(BigDecimal.ZERO) > 0) {
                    entity_HI = new SrmPoSupplyProportionLEntity_HI();
                    entity_HI.setSupplyId(supplyId);
                    entity_HI.setSupplierId(valuesJson.getInteger("supplierId"));
                    entity_HI.setSupplierNmuber(valuesJson.getString("supplierNumber"));
                    entity_HI.setSupplierName(valuesJson.getString("supplierName"));
                    entity_HI.setProportion(valuesJson.getBigDecimal("newSupplyRatio"));
                    entity_HI.setEnableFlag("Y");
                    entity_HI.setCreatedBy(varUserId);
                    entity_HI.setOperatorUserId(varUserId);
                    srmPoSupplyProportionLDAO_HI.save(entity_HI);
                }
            }
        } catch (Exception e) {
            throw new Exception("保存失败");
        }

    }


    /**
     * JSON数组去重
     * @param: [jsonArray] json Array
     * @param: [string] 唯一的key名，根据此键名进行去重
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    private JSONArray uniqueArray(JSONArray jsonArray, String key) {
        JSONArray array = new JSONArray();
        JSONObject obj = jsonArray.getJSONObject(0);
        array.add(obj);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj1 = jsonArray.getJSONObject(i);
            boolean repeat = false;
            for (int j = 0; j < array.size(); j++) {
                JSONObject obj2 = array.getJSONObject(j);
                if (obj1.getString(key).equals(obj2.getString(key))) {
                    repeat = true;
                    break;
                }
            }
            if (!repeat) {
                array.add(obj1);
            }
        }
        return array;
    }



    /**
     * 绩效供货比例维护数据导出
     *
     * @param paramJSON 查询条件
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmSpmPerformanceSupplyRatioEntity_HI_RO> findPerformanceSupplyRatioExport(JSONObject paramJSON) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmSpmPerformanceSupplyRatioEntity_HI_RO.QUERY_PERFORMANCE_SUPPLY_RATIO_LIST);
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
            SaafToolUtils.parperParam(paramJSON, "ss.source_scheme_number", "sourceSchemeNumber", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "ss.category_id ", "categoryId", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "ss.status ", "status", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "bc.category_name", "categoryName", queryString, map, "like");
            //绩效方案Id
            SaafToolUtils.parperParam(paramJSON, "ss.scheme_id", "schemeId", queryString, map, "=");
            // 排序
            queryString.append(" ORDER BY ss.source_scheme_number DESC, bc.category_code ASC, ss.curr_supply_ratio DESC");
            return srmSpmPerformanceSupplyRatioDAO_HI_RO.findList(queryString, map);
        } catch (Exception e) {
            throw new Exception("导出失败");
        }
    }


    /**
     * 绩效供货比例维护查询
     * @param paramJSON 查询条件
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmSpmPerformanceSupplyRatioEntity_HI_RO> findPerformanceSupplyRatioList(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmSpmPerformanceSupplyRatioEntity_HI_RO.QUERY_PERFORMANCE_SUPPLY_RATIO_LIST);
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
            SaafToolUtils.parperParam(paramJSON, "ss.source_scheme_number", "sourceSchemeNumber", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "ss.category_id ", "categoryId", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "ss.status ", "status", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "bc.category_name", "categoryName", queryString, map, "like");
            //绩效方案Id
            SaafToolUtils.parperParam(paramJSON, "ss.scheme_id", "schemeId", queryString, map, "=");
            String countSql = "select count(1) from (" + queryString + ")";
            // 排序
            queryString.append(" ORDER BY ss.source_scheme_number DESC, bc.category_code ASC, ss.curr_supply_ratio DESC");
            Pagination<SrmSpmPerformanceSupplyRatioEntity_HI_RO> dataList = srmSpmPerformanceSupplyRatioDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return dataList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }



    /**
     * 查询来源绩效方案版本
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
    @Override
    public Pagination<SrmSpmPerformanceSupplyRatioEntity_HI_RO> findSourceSchemeNumber(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer sqlBuffer = new StringBuffer(SrmSpmPerformanceSupplyRatioEntity_HI_RO.QUERY_SCHEME_NUMBER_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(params, " ss.source_scheme_number", "sourceSchemeNumber", sqlBuffer, map, "like");
            String countSql = "select count(1) from (" + sqlBuffer + ")";
            Pagination<SrmSpmPerformanceSupplyRatioEntity_HI_RO> dataList = srmSpmPerformanceSupplyRatioDAO_HI_RO.findPagination(sqlBuffer,countSql, map, pageIndex, pageRows);
            return dataList;
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("查询失败");
        }
    }

}

