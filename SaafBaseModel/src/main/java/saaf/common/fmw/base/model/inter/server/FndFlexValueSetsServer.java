package saaf.common.fmw.base.model.inter.server;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.FndFlexValidationTablesEntity_HI;
import saaf.common.fmw.base.model.entities.FndFlexValueSetsEntity_HI;
import saaf.common.fmw.base.model.entities.FndFlexValuesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.FndFlexValueSetsEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.FndFlexValuesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.IFndFlexValueSets;
import saaf.common.fmw.common.TestToolUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.DynamicBaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：FndFlexValueSetsServer.java
 * Description：用来处理值集
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
@Component("fndFlexValueSetsServer")
public class FndFlexValueSetsServer implements IFndFlexValueSets {
    private static final Logger LOGGER = LoggerFactory.getLogger(FndFlexValueSetsServer.class);
    @Autowired
    private BaseViewObject<FndFlexValueSetsEntity_HI_RO> fndFlexValueSetsDAO_HI_RO;
    @Autowired
    private ViewObject<FndFlexValueSetsEntity_HI> fndFlexValueSetsDAO_HI;
    @Autowired
    private ViewObject<FndFlexValuesEntity_HI> fndFlexValuesDAO_HI;
    @Autowired
    private ViewObject<FndFlexValidationTablesEntity_HI> fndFlexValidationTablesDAO_HI;
    @Autowired
    private BaseViewObject<FndFlexValuesEntity_HI_RO> fndFlexValuesDAO_HI_RO;

    @Autowired
    private DynamicBaseViewObject commonDAO_HI_DY;

    public FndFlexValueSetsServer() {
        super();
    }

    /**
     * 获取活动信息
     *
     * @param
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public String findFndFlexValueSetsInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<FndFlexValueSetsEntity_HI> findListResult = fndFlexValueSetsDAO_HI.findList("from FndFlexValueSetsEntity_HI", queryParamMap);
        String resultData = JSON.toJSONString(findListResult);
        JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
        return resultStr.toString();
    }
    /**
     * 保存活动信息
     *
     * @param
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public String saveFndFlexValueSetsInfo(JSONObject queryParamJSON) {
        FndFlexValueSetsEntity_HI fndFlexValueSetsEntity_HI = JSON.parseObject(queryParamJSON.toString(), FndFlexValueSetsEntity_HI.class);
        Object resultData = fndFlexValueSetsDAO_HI.save(fndFlexValueSetsEntity_HI);
        JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
        return resultStr.toString();
    }

    /**
     * 查询值集头表
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public Pagination findFlexValueSets(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            String flexValueSetName = null;
            String validationType = null;
            String description = null;
            StringBuffer queryString = new StringBuffer();
            queryString.append(FndFlexValueSetsEntity_HI_RO.QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            if (null != parameters.get("varFlexValueSetName") && !"".equals(parameters.getString("varFlexValueSetName").trim())) {
                flexValueSetName = parameters.getString("varFlexValueSetName");
                queryString.append(" and t.FLEX_VALUE_SET_NAME like :flexValueSetName");
                map.put("flexValueSetName", "%" + flexValueSetName + "%");
            }
            if (null != parameters.get("varValidationType") && !"".equals(parameters.getString("varValidationType").trim())) {
                validationType = parameters.getString("varValidationType");
                queryString.append(" and t.VALIDATION_TYPE like :validationType");
                map.put("validationType", "%" + validationType + "%");
            }

            if (null != parameters.get("varDescription") && !"".equals(parameters.getString("varDescription").trim())) {
                description = parameters.getString("varDescription");
                queryString.append(" and t.DESCRIPTION like :description");
                map.put("description", "%" + description + "%");
            }
            // 新增排序条件
            Pagination rowSet = null;
            String countSql = "select count(1) from (" + queryString + ")";
            rowSet = fndFlexValueSetsDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询值集头表ById
     *
     * @param flexValueSetId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public FndFlexValueSetsEntity_HI findFlexValueSetById(Integer flexValueSetId) throws Exception {
        try {
            return (FndFlexValueSetsEntity_HI)fndFlexValueSetsDAO_HI.findByProperty("flexValueSetId", flexValueSetId).get(0);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询值集行表ById
     *
     * @param flexValueId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public FndFlexValuesEntity_HI findFlexValueById(Integer flexValueId) throws Exception {
        try {
            return (FndFlexValuesEntity_HI)fndFlexValuesDAO_HI.findByProperty("flexValueId", flexValueId).get(0);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询值集行表ById
     *
     * @param flexValueSetId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public FndFlexValidationTablesEntity_HI findFlexValidationById(Integer flexValueSetId) throws Exception {
        try {
            return (FndFlexValidationTablesEntity_HI)fndFlexValidationTablesDAO_HI.findByProperty("flexValueSetId", flexValueSetId).get(0);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 验证SQL
     *
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public JSONObject validateSql(JSONObject params) throws Exception {
        StringBuffer queryString = new StringBuffer();
        try {
            queryString.append("select ");
            queryString.append(params.getString("idColumnName") + " idColumnName, ");
            queryString.append(params.getString("valueColumnName") + " valueColumnName, ");
            queryString.append(params.getString("meaningColumnName") + " meaningColumnName ");
            queryString.append(" from " + params.getString("applicationTableName"));
            queryString.append(" " + params.getString("additionalWhereClause"));
            queryString.append(" limit 0");
            System.out.println("exec sql:" + queryString.toString());
            List validationList = commonDAO_HI_DY.findList(queryString.toString());
            return SToolUtils.convertResultJSONObj("S", "验证SQL", 1, validationList);
        } catch (Exception e) {
            return SToolUtils.convertResultJSONObj("E", "SQL验证不通过！", 0, queryString);
            //			throw new Exception(e);
        }
    }

    /**
     * 编辑值集头表
     *
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public JSONObject saveFlexValueSet(JSONObject params) throws Exception {
        try {
            FndFlexValueSetsEntity_HI flexValueSetRow = null;
            // 判断Id是否存在，如果不存在就是新增
            if (null == params.get("flexValueSetId") || "".equals(SToolUtils.object2String(params.get("flexValueSetId")))) {
                boolean flag = findExistsFlexValueSet(params.getString("flexValueSetName"), params.get("flexValueSetId"));
                if (flag) {
                    return SToolUtils.convertResultJSONObj("E", "值集名重复，保存失败!" + params.getString("flexValueSetName") + "已存在", 0, null);
                } else {
                    flexValueSetRow = new FndFlexValueSetsEntity_HI();
                }
            } else { // 判断存在就是修改
                flexValueSetRow = findFlexValueSetById(params.getInteger("flexValueSetId"));
            }
            flexValueSetRow.setOperatorUserId(params.getInteger("varUserId"));

            String validationType = params.getString("validationType");
            flexValueSetRow.setFlexValueSetName(params.getString("flexValueSetName"));
            flexValueSetRow.setLonglistFlag(params.getString("longlistFlag"));
            flexValueSetRow.setValidationType(validationType);
            flexValueSetRow.setDescription(params.getString("description"));
            flexValueSetRow.setFormatType(params.getString("formatType"));
            // 保存头
            fndFlexValueSetsDAO_HI.saveOrUpdate(flexValueSetRow);
            // 保存值集值
            if ("S".equals(validationType)) { // 类型为独立的保存

                FndFlexValuesEntity_HI fndFlexValueRow = null;
                JSONArray valuesArray = params.getJSONArray("valuesData");
                for (int i = 0; i < valuesArray.size(); i++) {
                    JSONObject valuesJson = valuesArray.getJSONObject(i);
                    // 验证重复
                    if (findExistsFlexValue(valuesJson.getString("flexValue"), flexValueSetRow.getFlexValueSetId().toString(), valuesJson.get("flexValueId"))) {
                        return SToolUtils.convertResultJSONObj("E", "保存值集值失败!值：" + valuesJson.get("flexValue") + "已存在", 0, null);
                    }
                    // 验证数据类型
                    if ("N".equals(flexValueSetRow.getFormatType())) {
                        Pattern pattern = Pattern.compile("[0-9]*");
                        Matcher isNum = pattern.matcher(valuesJson.get("flexValue").toString());
                        if (!isNum.matches()) {
                            return SToolUtils.convertResultJSONObj("E", "保存值集值失败!值：" + valuesJson.get("flexValue") + "必须为数字格式", 0, null);
                        }
                    }
                }
                // 保存值
                for (int i = 0; i < valuesArray.size(); i++) {
                    JSONObject valuesJson = valuesArray.getJSONObject(i);
                    // 判断是新增还是修改
                    if (null == valuesJson.get("flexValueId") || "".equals(valuesJson.getString("flexValueId"))) {
                        fndFlexValueRow = new FndFlexValuesEntity_HI();
                        fndFlexValueRow.setFlexValueSetId(flexValueSetRow.getFlexValueSetId());
                    } else {
                        Integer flexValueId = valuesJson.getInteger("flexValueId");
                        fndFlexValueRow = findFlexValueById(flexValueId);
                    }
                    fndFlexValueRow.setOperatorUserId(params.getInteger("varUserId"));
                    System.out.println("S--saveFlexValueSet--flexValueSetRow.getFlexValueSetId():" + flexValueSetRow.getFlexValueSetId());
                    fndFlexValueRow.setFlexValue(valuesJson.getString("flexValue"));
                    fndFlexValueRow.setFlexValueMeaning(valuesJson.getString("flexValueMeaning"));
                    fndFlexValueRow.setDescription(valuesJson.getString("description"));
                    fndFlexValueRow.setEnabledFlag(valuesJson.getString("enabledFlag"));
                    fndFlexValueRow.setParentFlexValueHigh(valuesJson.getString("parentFlexValueHigh"));
                    fndFlexValueRow.setParentFlexValueLow(valuesJson.getString("parentFlexValueLow"));
                    if (null != valuesJson.get("startDateActive") && !"".equals(valuesJson.getString("startDateActive").trim())) {
                        Date startDateActive = SToolUtils.string2DateTime(valuesJson.getString("startDateActive"), "yyyy-MM-dd");
                        fndFlexValueRow.setStartDateActive(startDateActive);
                    }
                    if (null != valuesJson.get("endDateActive") && !"".equals(valuesJson.getString("endDateActive").trim())) {
                        Date endDateActive = SToolUtils.string2DateTime(valuesJson.getString("endDateActive"), "yyyy-MM-dd");
                        fndFlexValueRow.setEndDateActive(endDateActive);
                    } else {
                        fndFlexValueRow.setEndDateActive(null);
                    }
                    fndFlexValuesDAO_HI.saveOrUpdate(fndFlexValueRow);
                }
            } else if ("T".equals(validationType)) { // 类型为表的保存
                FndFlexValidationTablesEntity_HI fndFlexValidationRow = null;
                JSONArray valuesArray = params.getJSONArray("valuesData");
                for (int i = 0; i < valuesArray.size(); i++) {
                    JSONObject valuesJson = valuesArray.getJSONObject(i);
                    // 判断是新增还是修改
                    if (null == valuesJson.get("flexValidationId") || "".equals(valuesJson.getString("flexValidationId"))) {
                        fndFlexValidationRow = new FndFlexValidationTablesEntity_HI();
                        fndFlexValidationRow.setFlexValueSetId(flexValueSetRow.getFlexValueSetId());
                    } else {
                        fndFlexValidationRow = findFlexValidationById(flexValueSetRow.getFlexValueSetId());
                    }
                    fndFlexValidationRow.setOperatorUserId(params.getInteger("varUserId"));
                    
                    System.out.println("T--saveFlexValueSet--flexValueSetRow.getFlexValueSetId():" + flexValueSetRow.getFlexValueSetId());
                    fndFlexValidationRow.setApplicationTableName(valuesJson.getString("applicationTableName"));
                    fndFlexValidationRow.setValueColumnName(valuesJson.getString("valueColumnName"));
                    fndFlexValidationRow.setValueColumnType(valuesJson.getString("valueColumnType"));
                    if (valuesJson.getString("valueColumnSize") != null) {
                        fndFlexValidationRow.setValueColumnSize(Integer.parseInt(valuesJson.getString("valueColumnSize")));
                    }
                    fndFlexValidationRow.setMeaningColumnName(valuesJson.getString("meaningColumnName"));
                    fndFlexValidationRow.setMeaningColumnType(valuesJson.getString("meaningColumnType"));
                    if (valuesJson.getString("meaningColumnSize") != null) {
                        fndFlexValidationRow.setMeaningColumnSize(Integer.parseInt(valuesJson.getString("meaningColumnSize")));
                    }
                    fndFlexValidationRow.setIdColumnName(valuesJson.getString("idColumnName"));
                    fndFlexValidationRow.setIdColumnType(valuesJson.getString("idColumnType"));
                    if (valuesJson.getString("idColumnSize") != null) {
                        fndFlexValidationRow.setIdColumnSize(Integer.parseInt(valuesJson.getString("idColumnSize")));
                    }
                    fndFlexValidationRow.setAdditionalWhereClause(valuesJson.getString("additionalWhereClause"));
                    fndFlexValidationRow.setAdditionalQuickpickColumns(valuesJson.getString("additionalQuickpickColumns"));
                    // 验证sql
                    JSONObject validateJson = validateSql(valuesJson);
                    if ("E".equals(validateJson.get("status"))) {
                        return SToolUtils.convertResultJSONObj("E", "保存值集失败(" + validateJson.get("msg") + ")", 1, null);
                    }
                    fndFlexValidationTablesDAO_HI.saveOrUpdate(fndFlexValidationRow);
                }
            }
            return SToolUtils.convertResultJSONObj("S", "保存值集成功", 1, flexValueSetRow);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 验证值集头表flexValueSetName是否存在
     *
     * @param flexValueSetName
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public boolean findExistsFlexValueSet(String flexValueSetName, Object flexValueSetId) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("flexValueSetName", flexValueSetName);
            List<FndFlexValueSetsEntity_HI> rowSet = fndFlexValueSetsDAO_HI.findByProperty(map);
            if (rowSet.size() > 0) {
                FndFlexValueSetsEntity_HI fndFlexValueRow = null;
                for (int i = 0; i < rowSet.size(); i++) {
                    fndFlexValueRow = rowSet.get(i);
                    if (flexValueSetId != null &&
                        (!flexValueSetId.equals(fndFlexValueRow.getFlexValueSetId()) && flexValueSetName.equals(fndFlexValueRow.getFlexValueSetName()))) {
                        return true;
                    } else if (flexValueSetId == null && flexValueSetName.equals(fndFlexValueRow.getFlexValueSetName())) {
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
     * 验证值集行表flexValue是否重复
     *
     * @param flexValue
     * @param flexValueSetId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public boolean findExistsFlexValue(String flexValue, String flexValueSetId, Object flexValueId) throws Exception {
        try {
            System.out.println("findExistsFlexValue:flexValueSetId" + flexValueSetId);
            List dbValuesList = fndFlexValuesDAO_HI.findByProperty("flexValueSetId", Integer.parseInt(flexValueSetId));
            FndFlexValuesEntity_HI valuesRow = null;
            for (int i = 0; i < dbValuesList.size(); i++) {
                valuesRow = (FndFlexValuesEntity_HI)dbValuesList.get(i);
                if (flexValueId != null && (!valuesRow.getFlexValueId().equals(flexValueId) && valuesRow.getFlexValue().equals(flexValue))) {
                    return true;
                } else if (flexValueId == null && valuesRow.getFlexValue().equals(flexValue)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 获取值集的值
     *
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public Pagination findFlexValueByName(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            String flexValueSetName = params.getString("varFlexValueSetName").trim();
            Pagination returnList = null;
            List list = fndFlexValueSetsDAO_HI.findByProperty("flexValueSetName", flexValueSetName);
            if (list.size() == 0) {
                return fndFlexValueSetsDAO_HI.findPagination("select 1 from dual where 1=2 ", new HashMap(), pageIndex, pageRows);
            }
            FndFlexValueSetsEntity_HI fndFlexValueSetRow = (FndFlexValueSetsEntity_HI)list.get(0);
            Integer flexValueSetId = fndFlexValueSetRow.getFlexValueSetId();
            String validationType = fndFlexValueSetRow.getValidationType();
            if ("S".equals(validationType)) {
                String parentFlexValueLow = params.getString("varParentFlexValueLow");
                String parentFlexValueHigh = params.getString("varParentFlexValueHigh");
                String idColumnName = params.getString("varIdColumnName");
                String valueColumnName = params.getString("varValueColumnName");
                Map queryParamMap = new HashMap();
                queryParamMap.put("flexValueSetId", flexValueSetId);
                StringBuffer queryString = new StringBuffer();
                queryString.append(FndFlexValuesEntity_HI_RO.QUERY_SQL);
                queryString.append(" and t.FLEX_VALUE_SET_ID = :flexValueSetId");
                if (parentFlexValueLow != null && !"".equals(parentFlexValueLow)) {
                    queryParamMap.put("parentFlexValueLow", parentFlexValueLow);
                    queryString.append(" and t.PARENT_FLEX_VALUE_LOW = :parentFlexValueLow");
                    if (parentFlexValueHigh != null && !"".equals(parentFlexValueHigh)) {
                        queryParamMap.put("parentFlexValueHigh", parentFlexValueHigh);
                        queryString.append(" and t.PARENT_FLEX_VALUE_HIGH = :parentFlexValueHigh");
                    }
                }
                if (idColumnName != null && !"".equals(idColumnName)) {
                    queryParamMap.put("idColumnName", "%" + idColumnName + "%");
                    queryString.append(" and t.FLEX_VALUE like  :idColumnName");
                }
                if (valueColumnName != null && !"".equals(valueColumnName)) {
                    queryParamMap.put("valueColumnName", "%" + valueColumnName + "%");
                    queryString.append(" and t.FLEX_VALUE_MEANING like  :valueColumnName");
                }
                String countSql = "select count(1) from (" + queryString + ")";
                returnList = fndFlexValuesDAO_HI_RO.findPagination(queryString,countSql, queryParamMap, pageIndex, pageRows);
            } else if ("T".equals(validationType)) {
                FndFlexValidationTablesEntity_HI fndFlexValidationRow = findFlexValidationById(flexValueSetId);
                StringBuffer queryString = new StringBuffer();
                queryString.append("select ");
                queryString.append(fndFlexValidationRow.getIdColumnName() + " idColumnName, ");
                queryString.append(fndFlexValidationRow.getValueColumnName() + " valueColumnName, ");
                queryString.append(fndFlexValidationRow.getMeaningColumnName() + " meaningColumnName ");
                String additionalQuickpickColumns = fndFlexValidationRow.getAdditionalQuickpickColumns().trim();
                if (null != additionalQuickpickColumns && !"".equals(additionalQuickpickColumns)) {
                    queryString.append("," + fndFlexValidationRow.getAdditionalQuickpickColumns());
                }
                queryString.append(" from " + fndFlexValidationRow.getApplicationTableName());
                queryString.append(" " + fndFlexValidationRow.getAdditionalWhereClause());
                // queryString.append(" limit 0");
                System.out.println("exec sql:" + queryString.toString());
                String countSql = "select count(1) from (" + queryString + ")";
                returnList = commonDAO_HI_DY.findPagination(queryString.toString(),countSql, pageIndex, pageRows);
            }
            return returnList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static void main(String[] args) {
        FndFlexValueSetsServer fndFlexValueSetsServer = (FndFlexValueSetsServer)TestToolUtils.context.getBean("fndFlexValueSetsServer");
        JSONObject paramJSON = new JSONObject();
        // paramJSON.put("ordercode", 1);
        // paramJSON.put("ordercode", 1);
        // paramJSON.put("tid", 1);
        String resultStr = fndFlexValueSetsServer.findFndFlexValueSetsInfo(paramJSON);
        System.out.println(resultStr);
    }
}
