package saaf.common.fmw.spm.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SaafInstitutionEntity_HI_RO;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.readonly.SrmPoBaseCategoriesEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.SrmSpmRatioMappingsEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmRatioMappingsEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmRatioMappings;

@Component("srmSpmRatioMappingsServer")
public class SrmSpmRatioMappingsServer implements ISrmSpmRatioMappings {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmRatioMappingsServer.class);

    public SrmSpmRatioMappingsServer() {
        super();
    }

    @Autowired
    private ViewObject<SrmSpmRatioMappingsEntity_HI> srmSpmRatioMappingsDAO_HI;

    @Autowired
    private BaseViewObject<SrmSpmRatioMappingsEntity_HI_RO> srmSpmRatioMappingsDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPoBaseCategoriesEntity_HI_RO> srmPoBaseCategoriesDao_HI_RO;

    @Autowired
    private BaseViewObject<SaafInstitutionEntity_HI_RO> saafInstitutionDAO_HI_RO;


    /**
     * Description：保存供货比例对照关系数据
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveRatioMappings(JSONObject paramJSON) throws Exception {
        SrmSpmRatioMappingsEntity_HI entity_hi = null;
        JSONObject obj = paramJSON.getJSONObject("data");
        try {
            Integer varUserId = paramJSON.getInteger("varUserId");
            //如果id为空则新增 否则为修改
            if (null == obj.get("mappingId") || "".equals(SToolUtils.object2String(obj.get("mappingId")))) {
                int count = findExistsRatioMappings(obj.getInteger("instId"), obj.getInteger("categoryId"), obj.getInteger("vendorCount"));
                if (count > 0) {
                    return SToolUtils.convertResultJSONObj("E", "数据重复，保存失败!", 0, null);
                } else {
                    entity_hi = new SrmSpmRatioMappingsEntity_HI();
                    entity_hi.setOrgId(obj.getInteger("instId"));
                    entity_hi.setCategoryId(obj.getInteger("categoryId"));
                    entity_hi.setSegment1(obj.getString("segment1"));
                    entity_hi.setSegment2(obj.getString("segment2"));
                    entity_hi.setSegment3(obj.getString("segment3"));
                    entity_hi.setSegment4(obj.getString("segment4"));
                    entity_hi.setVendorCount(obj.getInteger("vendorCount"));
                    entity_hi.setSupplyRatio1(obj.getBigDecimal("supplyRatio1"));
                    entity_hi.setSupplyRatio2(obj.getBigDecimal("supplyRatio2"));
                    entity_hi.setSupplyRatio3(obj.getBigDecimal("supplyRatio3"));
                    entity_hi.setSupplyRatio4(obj.getBigDecimal("supplyRatio4"));
                    entity_hi.setSupplyRatio5(obj.getBigDecimal("supplyRatio5"));
                    entity_hi.setSupplyRatio6(obj.getBigDecimal("supplyRatio6"));
                    entity_hi.setSupplyRatio7(obj.getBigDecimal("supplyRatio7"));
                    entity_hi.setSupplyRatio8(obj.getBigDecimal("supplyRatio8"));
                    entity_hi.setSupplyRatio9(obj.getBigDecimal("supplyRatio9"));
                    entity_hi.setSupplyRatio10(obj.getBigDecimal("supplyRatio10"));
                    entity_hi.setSupplyRatio11(obj.getBigDecimal("supplyRatio11"));
                    entity_hi.setSupplyRatio12(obj.getBigDecimal("supplyRatio12"));
                    entity_hi.setSupplyRatio13(obj.getBigDecimal("supplyRatio13"));
                    entity_hi.setSupplyRatio14(obj.getBigDecimal("supplyRatio14"));
                    entity_hi.setSupplyRatio15(obj.getBigDecimal("supplyRatio15"));
                    entity_hi.setSupplyRatio16(obj.getBigDecimal("supplyRatio16"));
                    entity_hi.setSupplyRatio17(obj.getBigDecimal("supplyRatio17"));
                    entity_hi.setSupplyRatio18(obj.getBigDecimal("supplyRatio18"));
                    entity_hi.setSupplyRatio19(obj.getBigDecimal("supplyRatio19"));
                    entity_hi.setSupplyRatio20(obj.getBigDecimal("supplyRatio20"));
                    entity_hi.setDescription(obj.getString("description"));
                    entity_hi.setOperatorUserId(varUserId);
                    srmSpmRatioMappingsDAO_HI.save(entity_hi);
                    return SToolUtils.convertResultJSONObj("S", "保存成功", 1, entity_hi);
                }
            } else { // 更新
                entity_hi = srmSpmRatioMappingsDAO_HI.findByProperty("mappingId", obj.get("mappingId")).get(0);
                entity_hi.setOrgId(obj.getInteger("orgId"));
                entity_hi.setCategoryId(obj.getInteger("categoryId"));
                entity_hi.setSegment1(obj.getString("segment1"));
                entity_hi.setSegment2(obj.getString("segment2"));
                entity_hi.setSegment3(obj.getString("segment3"));
                entity_hi.setSegment4(obj.getString("segment4"));
                entity_hi.setVendorCount(obj.getInteger("vendorCount"));
                entity_hi.setSupplyRatio1(obj.getBigDecimal("supplyRatio1"));
                entity_hi.setSupplyRatio2(obj.getBigDecimal("supplyRatio2"));
                entity_hi.setSupplyRatio3(obj.getBigDecimal("supplyRatio3"));
                entity_hi.setSupplyRatio4(obj.getBigDecimal("supplyRatio4"));
                entity_hi.setSupplyRatio5(obj.getBigDecimal("supplyRatio5"));
                entity_hi.setSupplyRatio6(obj.getBigDecimal("supplyRatio6"));
                entity_hi.setSupplyRatio7(obj.getBigDecimal("supplyRatio7"));
                entity_hi.setSupplyRatio8(obj.getBigDecimal("supplyRatio8"));
                entity_hi.setSupplyRatio9(obj.getBigDecimal("supplyRatio9"));
                entity_hi.setSupplyRatio10(obj.getBigDecimal("supplyRatio10"));
                entity_hi.setSupplyRatio11(obj.getBigDecimal("supplyRatio11"));
                entity_hi.setSupplyRatio12(obj.getBigDecimal("supplyRatio12"));
                entity_hi.setSupplyRatio13(obj.getBigDecimal("supplyRatio13"));
                entity_hi.setSupplyRatio14(obj.getBigDecimal("supplyRatio14"));
                entity_hi.setSupplyRatio15(obj.getBigDecimal("supplyRatio15"));
                entity_hi.setSupplyRatio16(obj.getBigDecimal("supplyRatio16"));
                entity_hi.setSupplyRatio17(obj.getBigDecimal("supplyRatio17"));
                entity_hi.setSupplyRatio18(obj.getBigDecimal("supplyRatio18"));
                entity_hi.setSupplyRatio19(obj.getBigDecimal("supplyRatio19"));
                entity_hi.setSupplyRatio20(obj.getBigDecimal("supplyRatio20"));
                entity_hi.setDescription(obj.getString("description"));
                entity_hi.setOperatorUserId(varUserId);
                srmSpmRatioMappingsDAO_HI.update(entity_hi);
                return SToolUtils.convertResultJSONObj("S", "更新成功", 1, entity_hi);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new UtilsException("保存失败");
        }
    }



    /**
     * Description：校验数据是否存在(评价组织+采购分类编码+供应商数量)
     * @param instId
     * @param categoryId
     * @param vendorCount
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    private int findExistsRatioMappings(Integer instId, Integer categoryId, Integer vendorCount) {
        if (null == instId || null == categoryId || null == vendorCount) return -1;
        StringBuffer sql = new StringBuffer(SrmSpmRatioMappingsEntity_HI_RO.QUERY_RATIO_MAPPINGS_COUNT);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("instId", instId);
        map.put("categoryId", categoryId);
        map.put("vendorCount", vendorCount);
        try {
            return srmSpmRatioMappingsDAO_HI_RO.findList(sql, map).get(0).getCount();
        } catch (Exception e) {
            e.getStackTrace();
            return -1;
        }
    }

    /**
     * 删除供货比例对照关系数据
     *
     * @param paramJSON
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject deleteRatioMappings(JSONObject paramJSON) throws Exception {
        JSONArray mappingIds = paramJSON.getJSONArray("data");
        if (mappingIds.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无可删除的数据", 0, null);
        }
        SrmSpmRatioMappingsEntity_HI entity = null;
        List<SrmSpmRatioMappingsEntity_HI> list = new ArrayList<SrmSpmRatioMappingsEntity_HI>();
        for (int i = 0, j = mappingIds.size(); i < j; i++) {
            Integer mappingId = mappingIds.getInteger(i);
            entity = srmSpmRatioMappingsDAO_HI.getById(mappingId);
            if (entity != null) {
                list.add(entity);
            }
        }
        if (list.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无可删除的数据", 0, null);
        }
        try {
            srmSpmRatioMappingsDAO_HI.delete(list);
            return SToolUtils.convertResultJSONObj("S", "删除成功", list.size(), null);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new UtilsException("删除失败");
        }
    }


    /**
     * 导出供货比例对照关系数据
     *
     * @param paramJSON
     * @return
     * @throws Exception
     */
    @Override
    public List<SrmSpmRatioMappingsEntity_HI_RO> findRatioMappingsExport(JSONObject paramJSON) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmSpmRatioMappingsEntity_HI_RO.QUERY_RATIO_MAPPINGS_DATA_LIST);
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
            SaafToolUtils.parperParam(paramJSON, "bc.category_code", "categoryCode", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "bc.category_name", "categoryName", queryString, map, "like");
            // 排序
            queryString.append(" ORDER BY rm.creation_date DESC");
            return srmSpmRatioMappingsDAO_HI_RO.findList(queryString, map);
        } catch (Exception e) {
            throw new UtilsException("导出供货比例对照关系数据失败");
        }
    }


    /**
     * 导入供货比例对照关系数据
     * 1.导入时，校验“评价组织+供应商数量+采购分类只能有一条的数据
     * 2.验证控制每行的预设比例之和为100%,且控制“预设比例”的个数等于“供应商数量”
     *
     * @param paramJSON
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject saveRatioMappingsList(JSONObject paramJSON) throws Exception {
        JSONArray jsonArray = paramJSON.getJSONArray("data");
        SrmSpmRatioMappingsEntity_HI entity = null;
        SrmPoBaseCategoriesEntity_HI_RO categoriesEntity = null;
        SaafInstitutionEntity_HI_RO institutionEntity = null;
        Integer varUserId = paramJSON.getInteger("varUserId");
        JSONArray error = checkData(jsonArray);
        if (error.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存失败", error.size(), error);
        }
        try {
            List<SrmSpmRatioMappingsEntity_HI> dataList = new ArrayList<SrmSpmRatioMappingsEntity_HI>();
            for (int i = 0, j = jsonArray.size(); i < j; i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                try {
                    entity = new SrmSpmRatioMappingsEntity_HI();
                    //根据组织的名称去查组织Id
                    institutionEntity = getInstId(obj.getString("instName"));
                    entity.setOrgId(institutionEntity.getInstId());
                    //根据分类编码去查询分类id
                    categoriesEntity = getCategoryId(obj.getString("categoryCode"));
                    entity.setCategoryId(categoriesEntity.getCategoryId());
                    entity.setVendorCount(obj.getInteger("vendorCount"));
                    entity.setSupplyRatio1(obj.getBigDecimal("supplyRatio1"));
                    entity.setSupplyRatio2(obj.getBigDecimal("supplyRatio2"));
                    entity.setSupplyRatio3(obj.getBigDecimal("supplyRatio3"));
                    entity.setSupplyRatio4(obj.getBigDecimal("supplyRatio4"));
                    entity.setSupplyRatio5(obj.getBigDecimal("supplyRatio5"));
                    entity.setSupplyRatio6(obj.getBigDecimal("supplyRatio6"));
                    entity.setSupplyRatio7(obj.getBigDecimal("supplyRatio7"));
                    entity.setSupplyRatio8(obj.getBigDecimal("supplyRatio8"));
                    entity.setSupplyRatio9(obj.getBigDecimal("supplyRatio9"));
                    entity.setSupplyRatio10(obj.getBigDecimal("supplyRatio10"));
                    entity.setSupplyRatio11(obj.getBigDecimal("supplyRatio11"));
                    entity.setSupplyRatio12(obj.getBigDecimal("supplyRatio12"));
                    entity.setSupplyRatio13(obj.getBigDecimal("supplyRatio13"));
                    entity.setSupplyRatio14(obj.getBigDecimal("supplyRatio14"));
                    entity.setSupplyRatio15(obj.getBigDecimal("supplyRatio15"));
                    entity.setOperatorUserId(varUserId);
                    dataList.add(entity);
                    srmSpmRatioMappingsDAO_HI.save(dataList);
                } catch (Exception e) {
                    //e.printStackTrace();
                    LOGGER.error("批量导入供货比例对照关系数据,第" + (i + 1) + "失败" + JSONObject.toJSONString(obj));
                    obj.put("ERR_MESSAGE", "error");
                    obj.put("ROW_NUM", i + 1);
                    error.add(obj);
                }
            }
            if (error.size() == 0) {
                return SToolUtils.convertResultJSONObj("S", "保存成功行数为" + jsonArray.size() + "行", 0, null);
            }
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存成功行数为" + (jsonArray.size() - error.size()) + "行", error.size(), error);
        } catch (Exception e) {
            LOGGER.error("导入失败:" + e);
            throw new UtilsException("导入失败: " + e.getMessage());

        }
    }


    /**
     * 校验数据
     *
     * @param jsonArray
     * @return
     */
    private JSONArray checkData(JSONArray jsonArray) {
        JSONArray error = new JSONArray();
        JSONObject jsonObject = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            int count = 0;
            int count2 = 0;
            JSONObject obj1 = jsonArray.getJSONObject(i);
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject obj2 = jsonArray.getJSONObject(j);
                if (obj1.getString("instName").equals(obj2.getString("instName"))
                        && obj1.getString("categoryCode").equals(obj2.getString("categoryCode"))
                        && obj1.getString("vendorCount").equals(obj2.getString("vendorCount"))) {
                    count++;
                }
            }
            if (count > 1) {
                jsonObject = new JSONObject();
                jsonObject.put("ERR_MESSAGE", "评价组织,采购编码,供应商数量不允许重复");
                jsonObject.put("ROW_NUM", i + 1);
                error.add(jsonObject);
            }

            BigDecimal total = BigDecimal.ZERO;
            //组织Id
            Integer instId = getInstId(obj1.getString("instName")).getInstId();
            //采购分类
            Integer categoryId = getCategoryId(obj1.getString("categoryCode")).getCategoryId();
            //供应商数量
            Integer vendorCount = obj1.getInteger("vendorCount");
            for (int t = 1; t <= vendorCount; t++) {
                BigDecimal val = obj1.getBigDecimal("supplyRatio" + t);
                total = total.add(val);
            }

            if (total.compareTo(new BigDecimal(100)) != 0) {
                jsonObject = new JSONObject();
                jsonObject.put("ERR_MESSAGE", "每行的预设比例之和为100%！");
                jsonObject.put("ROW_NUM", i + 1);
                error.add(jsonObject);
            }

            if (findExistsRatioMappings(instId, categoryId, vendorCount) > 0) {
                jsonObject = new JSONObject();
                jsonObject.put("ERR_MESSAGE", "数据不允许重复");
                jsonObject.put("ROW_NUM", i + 1);
                error.add(jsonObject);
            }
        }
        return error;
    }


    /**
     * 根据组织名称查询组织id
     *
     * @param instName
     * @return
     */
    private SaafInstitutionEntity_HI_RO getInstId(String instName) {
        if (null == instName || "".equals(instName.trim())) return null;
        SaafInstitutionEntity_HI_RO instEntity = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("instName", instName.trim());
            instEntity = saafInstitutionDAO_HI_RO.findList(SrmSpmRatioMappingsEntity_HI_RO.QUERY_INST_ID, map).get(0);
            return instEntity;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据分类编码查询分类id
     *
     * @param categoryCode
     * @return
     */
    private SrmPoBaseCategoriesEntity_HI_RO getCategoryId(String categoryCode) {
        if (null == categoryCode || "".equals(categoryCode.trim())) return null;
        SrmPoBaseCategoriesEntity_HI_RO entity = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("categoryCode", categoryCode.trim());
            entity = srmPoBaseCategoriesDao_HI_RO.findList(SrmSpmRatioMappingsEntity_HI_RO.QUERY_CATEGORY_CODE, map).get(0);
            return entity;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }


    /**
     * 供货比例对照关系查询
     *
     * @param paramJSON
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     */
    @Override
    public Pagination<SrmSpmRatioMappingsEntity_HI_RO> findRatioMappingsList(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmSpmRatioMappingsEntity_HI_RO.QUERY_RATIO_MAPPINGS_DATA_LIST);
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
            SaafToolUtils.parperParam(paramJSON, "bc.category_code", "categoryCode", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "bc.category_name", "categoryName", queryString, map, "like");
            String countSql = "select count(1) from (" + queryString + ")";
            // 排序
            queryString.append(" ORDER BY rm.creation_date DESC");
            Pagination<SrmSpmRatioMappingsEntity_HI_RO> dataList = srmSpmRatioMappingsDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return dataList;
        } catch (Exception e) {
            throw new UtilsException("供货比例对照关系查询:"+e.getMessage());
        }
    }
}

