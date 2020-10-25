package saaf.common.fmw.base.model.inter.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafLookupTypesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafLookupTypesEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafLookup;
import saaf.common.fmw.common.utils.SaafToolUtils;


@Component("baseSaafLookupServer")
public class SaafLookupServer implements ISaafLookup {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaafLookupServer.class);

    public SaafLookupServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SaafLookupValuesEntity_HI_RO> saafLookupValuesDAO_HI_RO;

    @Autowired
    private BaseViewObject<SaafLookupTypesEntity_HI_RO> saafLookupTypesDAO_HI_RO;

    @Autowired
    private ViewObject<SaafLookupTypesEntity_HI> saafLookupTypesDAO_HI;

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;

    /**
     * 查询快码lov（分页，通用）
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination<SaafLookupValuesEntity_HI_RO> findLookupCodeLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM saaf_lookup_values t WHERE 1 = 1 ");
        SaafToolUtils.parperParam(jsonParams, "t.lookup_code", "lookupCode", sb, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "t.meaning", "meaning", sb, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "t.tag", "tag", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.enabled_flag", "enabledFlag", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t.lookup_type", "lookupType", sb, queryParamMap, "=");
        Pagination<SaafLookupValuesEntity_HI_RO> result = saafLookupValuesDAO_HI_RO.findPagination(sb.toString(), queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * 查询下拉框(通用）
     *
     * @param
     * @return
     */
    public List<SaafLookupValuesEntity_HI_RO> findLookupCode(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SaafLookupValuesEntity_HI_RO.QUERY_SQL);
            SaafToolUtils.parperParam(jsonParams, "slv.lookup_type", "lookupType", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "slv.tag", "tag", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "slv.lookup_code", "lookupCode", queryString, queryParamMap, "=");
            System.out.println(queryString.toString());
            List<SaafLookupValuesEntity_HI_RO> rowSet = saafLookupValuesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询快码（可分页）
     * @param map
     * @return
     */
    public Pagination findLookupCodePagination(Map<String, Object> map) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SaafLookupValuesEntity_HI_RO.QUERY_SQL);
            Pagination<SaafLookupValuesEntity_HI_RO> rowSet = saafLookupValuesDAO_HI_RO.findPagination(queryString.toString(), map, 0, -1);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询快码（可分页）
     *
     * @param parameters
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public Pagination findLookupCodePagination(JSONObject parameters, Integer pageIndex, Integer pageSize) throws Exception {
        try {
            Pagination<SaafLookupValuesEntity_HI_RO> findPagination =
                saafLookupValuesDAO_HI_RO.findPagination(SaafLookupValuesEntity_HI_RO.QUERY_SQL, parameters, pageIndex, pageSize);
            return findPagination;
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception(e);
        }
    }

    /**
     * 查询快码头表
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public Pagination findLookupType(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SaafLookupTypesEntity_HI_RO.QUERY_SQL);
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "slt.platform_code", "varPplatformCode", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(parameters, "slt.lookup_type_id", "varLookupTypeId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(parameters, "slt.lookup_type", "varLookupType", queryString, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(parameters, "slt.meaning", "varMeaning", queryString, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(parameters, "slt.description", "varDescription", queryString, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(parameters, "slt.customization_level", "varLevelName", queryString, queryParamMap, "=");
            Pagination rowSet = saafLookupTypesDAO_HI_RO.findPagination(queryString, queryParamMap, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询快码头表ById
     *
     * @param lookupTypeId
     * @return
     */
    public SaafLookupTypesEntity_HI findLookupTypeById(Integer lookupTypeId) throws Exception {
        try {
            return saafLookupTypesDAO_HI.findByProperty("lookupTypeId", lookupTypeId).get(0);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 查询快码行表
     *
     * @param map
     * @return
     */
    public List findLookupValues(Map<String, Object> map) throws Exception {
        try {
            StringBuffer hql = new StringBuffer();
            hql.append("select slv from SaafLookupTypesEntity_HI slt,SaafLookupValuesEntity_HI slv where slt.lookupType=slv.lookupType and  slt.lookupType=:lookupType");
            List rowSet = saafLookupTypesDAO_HI.findList(hql, map);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询快码行表ById
     *
     * @param lookupValuesId
     * @return
     */
    public SaafLookupValuesEntity_HI findLookupValuesById(Integer lookupValuesId) throws Exception {
        try {
            return saafLookupValuesDAO_HI.findByProperty("lookupValuesId", lookupValuesId).get(0);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 编辑快码头表
     *
     * @param params
     * @return
     */
    public JSONObject saveLookupType(JSONObject params) throws Exception {
        try {
            SaafLookupTypesEntity_HI lookupTypesRow = null;
            //保存快码类型LookupType
            //判断Id是否存在，如果不存在就是新增
            Integer operatorUserId = params.getInteger("varUserId");
            if (null == params.get("lookupTypeId") || "".equals(SToolUtils.object2String(params.get("lookupTypeId")))) {
                boolean flag = findExistsLookupType(params.getString("lookupType"), params.get("lookupTypeId"));
                if (flag) {
                    return SToolUtils.convertResultJSONObj("E", "保存失败，" + params.getString("lookupType") + "已存在！", 0, null);
                } else {
                    lookupTypesRow = new SaafLookupTypesEntity_HI();
                }
            } else { //判断存在就是修改
                lookupTypesRow = findLookupTypeById(params.getInteger("lookupTypeId"));
            }
            lookupTypesRow.setPlatformCode(params.getString("varPlatformCode"));
            lookupTypesRow.setLookupType(params.getString("lookupType"));
            lookupTypesRow.setCustomizationLevel(params.getString("customizationLevel"));
            lookupTypesRow.setDescription(params.getString("description"));
            lookupTypesRow.setMeaning(params.getString("meaning"));
            //保存快码值
            SaafLookupValuesEntity_HI lookupValuesRow = null;
            JSONArray valuesArray = params.getJSONArray("valuesData");
            if (valuesArray != null) {
                for (int i = 0; i < valuesArray.size(); i++) {
                    JSONObject valuesJson = valuesArray.getJSONObject(i);
                    //判断是新增还是修改
                    if (null == valuesJson.get("lookupValuesId") || "".equals(valuesJson.getString("lookupValuesId"))) {
                        //验证重复LookupCode
                        lookupValuesRow = new SaafLookupValuesEntity_HI();
                        lookupValuesRow.setCreatedBy(params.getInteger("varUserId"));
                        lookupValuesRow.setCreationDate(new Date());
                        lookupValuesRow.setLastUpdatedBy(params.getInteger("varUserId"));
                        lookupValuesRow.setLastUpdateDate(new Date());
                        lookupValuesRow.setLastUpdateLogin(params.getInteger("varUserId"));
                    } else {
                        Integer lookupValuesId = valuesJson.getInteger("lookupValuesId");
                        lookupValuesRow = findLookupValuesById(lookupValuesId);
                        lookupValuesRow.setLastUpdatedBy(params.getInteger("varUserId"));
                        lookupValuesRow.setLastUpdateDate(new Date());
                        lookupValuesRow.setLastUpdateLogin(params.getInteger("varUserId"));
                    }
                    if (findExistsLookupCode(valuesJson.getString("lookupCode"), params.getString("lookupType"), valuesJson.get("lookupValuesId"))) {
                        return SToolUtils.convertResultJSONObj("E", "保存快码值失败!编码：" + valuesJson.get("lookupCode") + "已存在", 0, null);
                    }
                    lookupValuesRow.setLookupType(params.getString("lookupType"));
                    lookupValuesRow.setLookupCode(valuesJson.getString("lookupCode"));
                    lookupValuesRow.setMeaning(valuesJson.getString("meaning"));
                    lookupValuesRow.setDescription(valuesJson.getString("description"));
                    lookupValuesRow.setTag(valuesJson.getString("tag"));
                    lookupValuesRow.setEnabledFlag(valuesJson.getString("enabledFlag"));
                    if (null != valuesJson.get("startDateActive") && !"".equals(valuesJson.getString("startDateActive").trim())) {
                        Date startDateActive = SToolUtils.string2DateTime(valuesJson.getString("startDateActive"), "yyyy-MM-dd");
                        lookupValuesRow.setStartDateActive(startDateActive);
                    }
                    if (null != valuesJson.get("endDateActive") && !"".equals(valuesJson.getString("endDateActive").trim())) {
                        Date endDateActive = SToolUtils.string2DateTime(valuesJson.getString("endDateActive"), "yyyy-MM-dd");
                        lookupValuesRow.setEndDateActive(endDateActive);
                    } else {
                        lookupValuesRow.setEndDateActive(null);
                    }
                    lookupValuesRow.setOperatorUserId(operatorUserId);
                    saafLookupValuesDAO_HI.saveOrUpdate(lookupValuesRow);
                }
            }
            lookupTypesRow.setOperatorUserId(operatorUserId);
            saafLookupTypesDAO_HI.saveOrUpdate(lookupTypesRow);
            return SToolUtils.convertResultJSONObj("S", "保存快码成功", 1, lookupTypesRow);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 编辑快码行表值
     * @param lookupValuesList
     * @return
     */
    public JSONObject saveLookupValues(List<SaafLookupValuesEntity_HI> lookupValuesList, String lookupType) throws Exception {
        try {
            SaafLookupValuesEntity_HI lookupValuesRow = null;
            for (int i = 0; i < lookupValuesList.size(); i++) {
                lookupValuesRow = lookupValuesList.get(i);
                lookupValuesRow.setLookupType(lookupType);
                saafLookupValuesDAO_HI.saveOrUpdate(lookupValuesRow);
            }
            return SToolUtils.convertResultJSONObj("S", "", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 删除快码行表值
     * @param params
     * @return
     */
    public JSONObject deleteLookupValues(JSONObject params) throws Exception {
        try {
            SaafLookupValuesEntity_HI lookupValuesRow = null;
            Integer lookupValuesId = null;
            if (params.get("lookupValuesId") != null && !"".equals(params.getString("lookupValuesId"))) {
                lookupValuesId = params.getInteger("lookupValuesId");
                lookupValuesRow = saafLookupValuesDAO_HI.findByProperty("lookupValuesId", lookupValuesId).get(0);
                saafLookupValuesDAO_HI.delete(lookupValuesRow);
            }
            return SToolUtils.convertResultJSONObj("S", "", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 验证块码头表lookupType是否存在
     *
     * @param lookupType
     * @return
     */
    public boolean findExistsLookupType(String lookupType, Object lookupTypeId) throws Exception {
        try {
            Map<String, Object> map = new HashMap();
            map.put("lookupType", lookupType);
            List<SaafLookupTypesEntity_HI> rowSet = saafLookupTypesDAO_HI.findByProperty(map);
            if (rowSet.size() > 0) {
                SaafLookupTypesEntity_HI lookupTypeRow = null;
                for (int i = 0; i < rowSet.size(); i++) {
                    lookupTypeRow = rowSet.get(i);
                    if (lookupTypeId != null && (!lookupTypeId.equals(lookupTypeRow.getLookupTypeId()) && lookupType.equals(lookupTypeRow.getLookupType()))) {
                        return true;
                    } else if (lookupTypeId == null && lookupType.equals(lookupTypeRow.getLookupType())) {
                        return true;
                    }
                }

            }
            return false;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 验证块码行表lookupCode是否重复
     *
     * @param lookupCode
     * @param lookupType
     * @return
     */
    public boolean findExistsLookupCode(String lookupCode, String lookupType, Object lookupCodeId) throws Exception {
        try {
            List dbValuesList = saafLookupValuesDAO_HI.findByProperty("lookupType", lookupType);
            SaafLookupValuesEntity_HI valuesRow = null;
            for (int i = 0; i < dbValuesList.size(); i++) {
                valuesRow = (SaafLookupValuesEntity_HI)dbValuesList.get(i);
                if (lookupCodeId != null && (!valuesRow.getLookupValuesId().equals(lookupCodeId) && valuesRow.getLookupCode().equals(lookupCode))) {
                    return true;
                } else if (lookupCodeId == null && valuesRow.getLookupCode().equals(lookupCode)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 根据块码类型及块码名称查询 不考虑重复，返回第一个，否则抛出异常
     *
     * @param lookupType
     * @param lookupCode
     * @return
     * @throws Exception
     * @date 2016-12-09
     * @author lag
     */
    public Map findLookUpValuesByTypeCode(String lookupType, String lookupCode) throws Exception {
        //String[] params1 = new String[]{lookupType, lookupCode};
        List result = saafLookupValuesDAO_HI.findList(SaafLookupValuesEntity_HI_RO.QUERY_LOOKUPVALUES_BYTYPE_CODE_SQL, new Object[] { lookupType, lookupCode });
        Iterator iterator = result.iterator();
        //不考虑重复，返回第一个，否则抛出异常
        while (iterator.hasNext()) {
            return (Map)iterator.next();
        }
        throw new Exception("查询快码异常");
    }

}
