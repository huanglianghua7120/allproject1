package saaf.common.fmw.base.model.inter.server;

import com.alibaba.fastjson.JSONArray;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafInstitutionEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseCategoriesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafUserEmployeesEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseCategoryDivisionEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseCategoryDivision;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import java.sql.Timestamp;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.base.model.entities.SrmBaseCategoryDivisionEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@Component("srmBaseCategoryDivisionServer")
public class SrmBaseCategoryDivisionServer implements ISrmBaseCategoryDivision {


    private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseCategoryDivisionServer.class);
    @Autowired
    private ViewObject<SrmBaseCategoryDivisionEntity_HI> srmBaseCategoryDivisionDAO_HI;
    @Autowired
    private BaseViewObject<SrmBaseCategoryDivisionEntity_HI_RO> srmBaseCategoryDivisionDAO_HI_RO;
    @Autowired
    private BaseViewObject<SrmBaseCategoriesEntity_HI_RO> srmBaseCategoryDAO_HI_RO;
    @Autowired
    private BaseViewObject<SaafUserEmployeesEntity_HI_RO> SaafUserEmployeesDAO_HI_RO;
    @Autowired
    private ViewObject<SaafInstitutionEntity_HI> saafInstitutionDAO_HI;
    @Autowired
    private ViewObject<SaafEmployeesEntity_HI> saafEmployeesDAO_HI;
    @Autowired
    private ViewObject<SrmBaseCategoriesEntity_HI> srmBaseCategoriesDAO_HI;
    @Context
    protected HttpServletRequest request;

    public SrmBaseCategoryDivisionServer() {
        super();
    }

    /**
     * 批量删除
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    public JSONObject deleteCategoryDivision(JSONArray jsonArray) throws Exception {
        JSONObject resultObj = new JSONObject();
        try {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                if (!"NEW".equals(object.getString("divisionStatus"))) {
                    String message = "所选第" + (i + 1) + "行非拟定状态不可删除！";
                    return SToolUtils.convertResultJSONObj("E", message, 1, "");
                }
                srmBaseCategoryDivisionDAO_HI.delete(object.getInteger("divisionId"));
            }
            resultObj.put("msg", "删除成功！");
            resultObj.put("status", "S");
            return resultObj;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 批量失效
     *
     * @param jsonArray
     * @return
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateInvalidCategoryDivision(JSONArray jsonArray, Integer userId) throws Exception {
        JSONObject resultObj = new JSONObject();
        List<SrmBaseCategoryDivisionEntity_HI> list = new ArrayList<SrmBaseCategoryDivisionEntity_HI>();
        try {
            SrmBaseCategoryDivisionEntity_HI srmBaseCategoryDivisionEntity = null;
            for (int i = 0; i < jsonArray.size(); i++) {
                srmBaseCategoryDivisionEntity = new SrmBaseCategoryDivisionEntity_HI();
                JSONObject object = jsonArray.getJSONObject(i);
                if (!"ACTIVE".equals(object.getString("divisionStatus"))) {
                    String message = "所选第" + (i + 1) + "行非生效状态不可失效！";
                    return SToolUtils.convertResultJSONObj("E", message, 1, "");
                }
                object.getInteger("divisionId");
                srmBaseCategoryDivisionEntity = srmBaseCategoryDivisionDAO_HI.getById(object.getInteger("divisionId"));
                srmBaseCategoryDivisionEntity.setDivisionId(object.getInteger("divisionId"));
                srmBaseCategoryDivisionEntity.setDivisionStatus("INACTIVE");
                srmBaseCategoryDivisionEntity.setEndDate(new Date());
                srmBaseCategoryDivisionEntity.setUserId(userId);
                srmBaseCategoryDivisionEntity.setOperatorUserId(userId);
                list.add(srmBaseCategoryDivisionEntity);
            }
            srmBaseCategoryDivisionDAO_HI.saveOrUpdateAll(list);
            resultObj.put("msg", "失效成功！");
            resultObj.put("status", "S");
            return resultObj;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 批量生效
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateEffectCategoryDivision(JSONObject jsonParams, Integer userId) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        SrmBaseCategoryDivisionEntity_HI srmBaseCategoryDivisionEntity_HI = null;
        JSONArray lineArray = jsonParams.getJSONArray("array");
        List<SrmBaseCategoryDivisionEntity_HI> list = new ArrayList<>();
        StringBuffer appendString = new StringBuffer();
        appendString.append(SrmBaseCategoryDivisionEntity_HI_RO.QUERY_CATEGORY_DIVISION1);
        int count = 0;
        for (int i = 0; i < lineArray.size(); i++) {
            JSONObject object = lineArray.getJSONObject(i);
            LOGGER.info("line:" + object.toString());
            if ("Y".equals(String.valueOf(object.getString("flag")))) {
                if (!"NEW".equals(object.getString("divisionStatus"))) {
                    return SToolUtils.convertResultJSONObj("E", "非拟定状态不可生效，请重选！", 0, null);
                }
                SaafToolUtils.parperParam(object, "t.ORG_ID", "orgId", appendString, queryParamMap, "=");
                SaafToolUtils.parperParam(object, "t.EMPLOYEE_ID", "employeeId", appendString, queryParamMap, "=");
                SaafToolUtils.parperParam(object, "t.CATEGORY_ID", "categoryId", appendString, queryParamMap, "=");
                queryParamMap.put("divisionStatus", "ACTIVE");
                List<SrmBaseCategoryDivisionEntity_HI_RO> srmBaseCategoryDivision = srmBaseCategoryDivisionDAO_HI_RO.findList(appendString, queryParamMap);
                if (srmBaseCategoryDivision != null && srmBaseCategoryDivision.size() > 0) {
                    String message = "所选第" + (++count) + "行已存在有效的组织+员工+品类数据，请检查！";
                    return SToolUtils.convertResultJSONObj("E", message, 1, "");
                }
                srmBaseCategoryDivisionEntity_HI = new SrmBaseCategoryDivisionEntity_HI();
                Integer divisionId = object.getInteger("divisionId");
                srmBaseCategoryDivisionEntity_HI = new SrmBaseCategoryDivisionEntity_HI();
                srmBaseCategoryDivisionEntity_HI = srmBaseCategoryDivisionDAO_HI.getById(divisionId);
                srmBaseCategoryDivisionEntity_HI.setDivisionStatus("ACTIVE");
                srmBaseCategoryDivisionEntity_HI.setStartDate(new Date());
                srmBaseCategoryDivisionEntity_HI.setUserId(userId);
                srmBaseCategoryDivisionEntity_HI.setOperatorUserId(userId);
                list.add(srmBaseCategoryDivisionEntity_HI);
            }
        }
        srmBaseCategoryDivisionDAO_HI.saveOrUpdateAll(list);
        return SToolUtils.convertResultJSONObj("S", "生效成功！", 1, null);
    }

    /**
     * 导入
     *
     * @param jsonArray
     * @param userId
     * @return
     * @throws Exception
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public JSONObject batchImportCategoryDivision(JSONArray jsonArray, Integer userId) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        JSONArray error = cehckArray(jsonArray);
        if (error.size() > 0) {
            return SToolUtils.convertResultJSONObj("ERR_IMPORT", "保存失败", error.size(), error);
        }
        SrmBaseCategoryDivisionEntity_HI srmBaseCategoryDivisionEntity = null;
        int count = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            srmBaseCategoryDivisionEntity = new SrmBaseCategoryDivisionEntity_HI();
            JSONObject object = jsonArray.getJSONObject(i);
            SaafInstitutionEntity_HI saafInstitutionEntity = saafInstitutionDAO_HI.findByProperty("inst_name", object.getString("instName")).get(0);
            if (saafInstitutionEntity != null && saafInstitutionEntity.getInstId() > 0) {
                srmBaseCategoryDivisionEntity.setOrgId(saafInstitutionEntity.getInstId());
            }
            SaafEmployeesEntity_HI saafEmployeesEntity = saafEmployeesDAO_HI.findByProperty("employee_number", object.getString("employeeNumber")).get(0);
            if (saafEmployeesEntity != null && saafEmployeesEntity.getEmployeeId() > 0) {
                srmBaseCategoryDivisionEntity.setEmployeeId(saafEmployeesEntity.getEmployeeId());
            }

            StringBuffer queryString1 = new StringBuffer();
            queryString1.append(SrmBaseCategoriesEntity_HI_RO.GET_PO_HEADER_SQLS);
            SaafToolUtils.parperParam(object, "t.full_category_code", "fullCategoryCode", queryString1, queryParamMap, "=");
            SrmBaseCategoriesEntity_HI_RO categoryDivisionlist11 = srmBaseCategoryDAO_HI_RO.findList(queryString1, queryParamMap).get(0);
            if (categoryDivisionlist11 != null && categoryDivisionlist11.getCategoryId() > 0) {
                srmBaseCategoryDivisionEntity.setCategoryId(categoryDivisionlist11.getCategoryId());
            }
            srmBaseCategoryDivisionEntity.setDivisionStatus("ACTIVE");
            srmBaseCategoryDivisionEntity.setStartDate(new Date());
            srmBaseCategoryDivisionEntity.setOperatorUserId(userId);
            srmBaseCategoryDivisionDAO_HI.save(srmBaseCategoryDivisionEntity);
            ++count;
        }
        JSONObject resultObj = new JSONObject();
        resultObj.put("msg", "导入成功行数为:" + count + "行!");
        resultObj.put("status", "S");
        return resultObj;
    }

    private JSONArray cehckArray(JSONArray jsonArray) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmBaseCategoryDivisionEntity_HI_RO.QUERY_CATEGORY_DIVISION);
        queryString.append(" and t.DIVISION_STATUS = 'ACTIVE'");
        if (jsonArray.isEmpty()) return null;
        JSONArray error = new JSONArray();
        JSONObject e = null;
        for (int i = 0, j = jsonArray.size(); i < j; i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String employeeNumber1 = object.getString("employeeNumber");
            String instName1 = object.getString("instName");
            String fullCategoryCode1 = object.getString("fullCategoryCode");
            List<SaafInstitutionEntity_HI> saafInstitutionEntityList = saafInstitutionDAO_HI.findByProperty("inst_name", instName1);
            if (saafInstitutionEntityList == null || saafInstitutionEntityList.size() < 1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "组织不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            List<SaafEmployeesEntity_HI> saafEmployeesEntityList = saafEmployeesDAO_HI.findByProperty("employee_number", employeeNumber1);
            if (saafEmployeesEntityList == null || saafEmployeesEntityList.size() < 1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "人员编码不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            List<SrmBaseCategoriesEntity_HI> srmBaseCategoriesEntityList = srmBaseCategoriesDAO_HI.findByProperty("full_category_code", fullCategoryCode1);
            if (srmBaseCategoriesEntityList == null || srmBaseCategoriesEntityList.size() < 1) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "分类编号不存在");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
            SaafToolUtils.parperParam(object, "org.inst_name", "instName", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(object, "emp.employee_number", "employeeNumber", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(object, "cgs.full_category_code", "fullCategoryCode", queryString, queryParamMap, "=");
            List<SrmBaseCategoryDivisionEntity_HI_RO> list = srmBaseCategoryDivisionDAO_HI_RO.findList(queryString, queryParamMap);
            if (list.size() > 0) {
                e = new JSONObject();
                e.put("ERR_MESSAGE", "已存在有效的组织+员工+品类数据");
                e.put("ROW_NUM", i + 1);
                error.add(e);
                continue;
            }
        }
        return error;
    }

    /**
     * Description：保存品类分工
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * divisionId  分工ID  NUMBER  Y
     * orgId  组织ID，关联表：SAAF_INSTITUTION  NUMBER  N
     * departmentId  部门ID（备用）  NUMBER  N
     * positionId  职位ID（备用）  NUMBER  N
     * employeeId  员工ID，关联表：SAAF_EMPLOYEES  NUMBER  N
     * userId  用户ID，关联表：SAAF_USERS  NUMBER  N
     * allCategoryFlag  是否可操作全品类，关联表：SAAF_LOOKUP_VALUES（YSE_NO）  VARCHAR2  N
     * categoryId  分类ID，关联表：SRM_BASE_CATEGORIES  NUMBER  N
     * segment1  一级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_BIG_CATEGORY）  VARCHAR2  N
     * segment2  二级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_MIDDLE_CATEGORY）  VARCHAR2  N
     * segment3  三级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_SMALL_CATEGORY）  VARCHAR2  N
     * segment4  四级分类编码（备用）  VARCHAR2  N
     * divisionStatus  分工状态，关联表：SAAF_LOOKUP_VALUES（BASE_DIVISION_STATUS）  VARCHAR2  N
     * startDate  生效日期  DATE  N
     * endDate  失效日期  DATE  N
     * divisionId  分工ID  NUMBER  Y
     * orgId  组织ID，关联表：SAAF_INSTITUTION  NUMBER  N
     * departmentId  部门ID（备用）  NUMBER  N
     * positionId  职位ID（备用）  NUMBER  N
     * employeeId  员工ID，关联表：SAAF_EMPLOYEES  NUMBER  N
     * userId  用户ID，关联表：SAAF_USERS  NUMBER  N
     * allCategoryFlag  是否可操作全品类，关联表：SAAF_LOOKUP_VALUES（YSE_NO）  VARCHAR2  N
     * categoryId  分类ID，关联表：SRM_BASE_CATEGORIES  NUMBER  N
     * segment1  一级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_BIG_CATEGORY）  VARCHAR2  N
     * segment2  二级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_MIDDLE_CATEGORY）  VARCHAR2  N
     * segment3  三级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_SMALL_CATEGORY）  VARCHAR2  N
     * segment4  四级分类编码（备用）  VARCHAR2  N
     * divisionStatus  分工状态，关联表：SAAF_LOOKUP_VALUES（BASE_DIVISION_STATUS）  VARCHAR2  N
     * startDate  生效日期  DATE  N
     * endDate  失效日期  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveCategoryDivision(JSONArray jsonParams, Integer userId) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        JSONObject resultjson = new JSONObject();
        JSONObject object = null;
        SrmBaseCategoryDivisionEntity_HI srmBaseCategoryDivisionEntity = null;
        StringBuffer appendString = new StringBuffer();
        appendString.append(SrmBaseCategoryDivisionEntity_HI_RO.QUERY_CATEGORY_DIVISION1);
        int count = 0;
        for (int i = 0; i < jsonParams.size(); i++) {
            object = jsonParams.getJSONObject(i);
            if (object.getString("divisionId") != null && !"".equals(object.getString("divisionId"))) {
                System.out.print(object.getString("divisionId"));
            } else {
                ++count;
                srmBaseCategoryDivisionEntity = new SrmBaseCategoryDivisionEntity_HI();
                if (object.getInteger("orgId") > 0) {
                    srmBaseCategoryDivisionEntity.setOrgId(object.getInteger("orgId"));
                }
                if (object.getInteger("employeeId") > 0) {
                    srmBaseCategoryDivisionEntity.setEmployeeId(object.getInteger("employeeId"));
                }
                if (object.getInteger("categoryId") > 0) {
                    srmBaseCategoryDivisionEntity.setCategoryId(object.getInteger("categoryId"));
                }
                if ("000000".equals(object.getString("categoryCode"))) {
                    srmBaseCategoryDivisionEntity.setAllCategoryFlag("Y");
                }
                srmBaseCategoryDivisionEntity.setUserId(userId);
                srmBaseCategoryDivisionEntity.setDivisionStatus("NEW");
                srmBaseCategoryDivisionEntity.setOperatorUserId(userId);
                srmBaseCategoryDivisionDAO_HI.saveEntity(srmBaseCategoryDivisionEntity);
            }
        }
        if (count < 1) {
            return SToolUtils.convertResultJSONObj("E", "请新增一行！", 1, "");
        } else {
            return SToolUtils.convertResultJSONObj("S", "保存成功！", 1, "");
        }

    }

    /**
     * 查询品类分工
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmBaseCategoryDivisionEntity_HI_RO> findCategoryDivision(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmBaseCategoryDivisionEntity_HI_RO.QUERY_CATEGORY_DIVISION);
            SaafToolUtils.parperParam(jsonParams, "t.org_id", "orgId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "t.employee_id", "employeeId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "emp.employee_number", "employeeNumber", queryString, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(jsonParams, "emp.employee_name", "employeeName", queryString, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(jsonParams, "emp.quarters_code", "quartersCode", queryString, queryParamMap, "LIKE");
            if (jsonParams.getInteger("categoryId") != null && jsonParams.getInteger("categoryId") > 0) {
                SaafToolUtils.parperParam(jsonParams, "t.category_id", "categoryId", queryString, queryParamMap, "=");
            } else {
                SaafToolUtils.parperParam(jsonParams, "cgs.full_category_code", "fullCategoryCode", queryString, queryParamMap, "=");
                SaafToolUtils.parperParam(jsonParams, "cgs.full_category_name", "fullCategoryName", queryString, queryParamMap, "LIKE");
            }
            SaafToolUtils.parperParam(jsonParams, "t.division_status", "divisionStatus", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "us.user_name", "userName", queryString, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(jsonParams, "t.created_by", "userId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "us.user_name", "userName", queryString, queryParamMap, "LIKE");
//            SaafToolUtils.parperParam(jsonParams, "t.CREATION_DATE", "creationDateTo", queryString, queryParamMap, "<=");
//            SaafToolUtils.parperParam(jsonParams, "t.CREATION_DATE", "creationDateFrom", queryString, queryParamMap, ">=");
//            SaafToolUtils.parperParam(jsonParams, "t.START_DATE", "startDateFrom", queryString, queryParamMap, ">=");
//            SaafToolUtils.parperParam(jsonParams, "t.START_DATE", "startDateTo", queryString, queryParamMap, "<=");
            String creationDateFrom = jsonParams.getString("creationDateFrom");
            if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
                queryString.append("AND    trunc(t.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')");
                queryParamMap.put("creationDateFrom", creationDateFrom);
            }
            String creationDateTo = jsonParams.getString("creationDateTo");
            if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
                queryString.append("AND    trunc(t.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')");
                queryParamMap.put("creationDateTo", creationDateTo);
            }
            String startDateFrom = jsonParams.getString("startDateFrom");
            if (startDateFrom != null && !"".equals(startDateFrom.trim())) {
                queryString.append("AND    trunc(t.start_date) >= to_date(:startDateFrom, 'yyyy-mm-dd')");
                queryParamMap.put("startDateFrom", startDateFrom);
            }
            String startDateTo = jsonParams.getString("startDateTo");
            if (startDateTo != null && !"".equals(startDateTo.trim())) {
                queryString.append("AND    trunc(t.start_date) <= to_date(:startDateTo, 'yyyy-mm-dd')");
                queryParamMap.put("startDateTo", startDateTo);
            }
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" ORDER BY t.last_update_date DESC");
            Pagination<SrmBaseCategoryDivisionEntity_HI_RO> result =
                    srmBaseCategoryDivisionDAO_HI_RO.findPagination(queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 崗位LOV
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2019-05-29     秦晓钊          创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmBaseCategoryDivisionEntity_HI_RO> findPositionLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmBaseCategoryDivisionEntity_HI_RO.QUERY_POSITION_LOV);
            SaafToolUtils.parperParam(jsonParams, "t.meaning", "quartersName", queryString, queryParamMap, "like");
            String countSql = "select count(1) from (" + queryString + ")";
            Pagination<SrmBaseCategoryDivisionEntity_HI_RO> result =
                    srmBaseCategoryDivisionDAO_HI_RO.findPagination(queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    /**
     * 崗位查询
     * @param queryParamJSON
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmBaseCategoryDivisionEntity_HI> findSrmBaseCategoryDivisionInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmBaseCategoryDivisionEntity_HI> findListResult = srmBaseCategoryDivisionDAO_HI.findList("from SrmBaseCategoryDivisionEntity_HI", queryParamMap);
        return findListResult;
    }
    /**
     * 保存岗位
     * @param queryParamJSON
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmBaseCategoryDivisionInfo(JSONObject queryParamJSON) {
        SrmBaseCategoryDivisionEntity_HI srmBaseCategoryDivisionEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmBaseCategoryDivisionEntity_HI.class);
        Object resultData = srmBaseCategoryDivisionDAO_HI.save(srmBaseCategoryDivisionEntity_HI);
        return resultData;
    }
}
