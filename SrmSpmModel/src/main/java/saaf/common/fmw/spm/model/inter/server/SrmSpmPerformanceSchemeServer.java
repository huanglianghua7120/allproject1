package saaf.common.fmw.spm.model.inter.server;


import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SaafInstitutionEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.intf.util.IntfUtils;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierInfoEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceSchemeEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceSchemeEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceScheme;

import java.rmi.ServerException;
import java.util.*;

@Component("srmSpmPerformanceSchemeServer")
public class SrmSpmPerformanceSchemeServer implements ISrmSpmPerformanceScheme {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmPerformanceSchemeServer.class);

    public SrmSpmPerformanceSchemeServer() {
        super();
    }

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ViewObject<SrmSpmPerformanceSchemeEntity_HI> srmSpmPerformanceSchemeDAO_HI;

    @Autowired
    private BaseViewObject<SrmSpmPerformanceSchemeEntity_HI_RO> srmSpmPerformanceSchemeDAO_HI_RO;


    /**
     * 更新绩效方案数据表
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updatePerformanceScheme(JSONObject paramJSON) throws Exception {
        Integer varUserId = paramJSON.getInteger("varUserId");
        if (paramJSON.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无更新的数据", 0, null);
        }
        SrmSpmPerformanceSchemeEntity_HI entity = null;
        entity = srmSpmPerformanceSchemeDAO_HI.getById(paramJSON.getInteger("schemeId"));
        String jobType = paramJSON.getString("jobType");
        if ("INDICATORCOLLECT".equals(jobType)) {
            entity.setRequestId(paramJSON.getInteger("scheduleId"));
            entity.setRequestStatus("EXECUTING");
            entity.setStatus("COLLECTIND");
        } else if ("SPCALCULAT".equals(jobType)) {
            entity.setDataRequestId(paramJSON.getInteger("scheduleId"));
            entity.setDataRequestStatus("EXECUTING");
            entity.setStatus("CALCULATING");
        } else if ("SUPPLYRATIOREC".equals(jobType)) {
            entity.setRatioRequestId(paramJSON.getInteger("scheduleId"));
            entity.setRatioRequestStatus("EXECUTING");
        }
        entity.setOperatorUserId(varUserId);
        try {
            srmSpmPerformanceSchemeDAO_HI.update(entity);
            return SToolUtils.convertResultJSONObj("S", "更新成功", 1, null);
        } catch (Exception e) {
            //e.printStackTrace();
            //throw new Exception("更新失败");
            throw new UtilsException("更新失败");
        }
    }


    /**
     * 绩效方案发布
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateSchemePublished(JSONObject paramJSON) throws Exception {
        Integer varUserId = paramJSON.getInteger("varUserId");
        SrmSpmPerformanceSchemeEntity_HI entity = srmSpmPerformanceSchemeDAO_HI.getById(paramJSON.getInteger("schemeId"));
        //如果数据不存在，抛出异常
        if (null == entity) {
            //throw new ServerException("系统不存在该数据！");
            throw new UtilsException("更新失败");
        } else {
            entity.setStatus("PUBLISHED");
            entity.setOperatorUserId(varUserId);
            srmSpmPerformanceSchemeDAO_HI.update(entity);
        }
        return SToolUtils.convertResultJSONObj("S", "已成功执行综合绩效发布活动", 1, null);
    }




    /**
     * 绩效方案作废
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateSchemeCancel(JSONObject paramJSON) throws Exception {
        Integer varUserId = paramJSON.getInteger("varUserId");
        SrmSpmPerformanceSchemeEntity_HI entity = srmSpmPerformanceSchemeDAO_HI.getById(paramJSON.getInteger("schemeId"));
        //如果数据不存在，抛出异常
        if (null == entity) {
            throw new UtilsException("系统不存在该数据！");
        } else {
            entity.setStatus("CANCELED");
            entity.setOperatorUserId(varUserId);
            srmSpmPerformanceSchemeDAO_HI.update(entity);
        }
        return SToolUtils.convertResultJSONObj("S", "已成功执行方案作废活动", 1, null);
    }


    /**
     * 删除绩效方案数据
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteScheme(JSONObject paramJSON) throws Exception {
        SrmSpmPerformanceSchemeEntity_HI entity = srmSpmPerformanceSchemeDAO_HI.getById(paramJSON.getInteger("schemeId"));
        //如果数据不存在，抛出异常，相反删除
        if (null == entity) {
            throw new UtilsException("系统不存在该数据！");
        } else {
            srmSpmPerformanceSchemeDAO_HI.delete(entity);
        }
        return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
    }



    /**
     * 导出绩效方案数据
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmSpmPerformanceSchemeEntity_HI_RO> findExportScheme(JSONObject paramJSON) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmSpmPerformanceSchemeEntity_HI_RO.QUERY_SCHEME_INFO_LIST);
            Map<String, Object> map = new HashMap();
            // 查询过滤条件
            SaafToolUtils.parperParam(paramJSON, "ps.scheme_number", "schemeNumber", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "tpl.tpl_code", "tplCode", queryString, map, "=");
            String creationDateFrom = paramJSON.getString("creationDateFrom");
            if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
                queryString.append(" AND trunc(ps.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
                map.put("creationDateFrom", creationDateFrom);
            }
            String creationDateTo = paramJSON.getString("creationDateTo");
            if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
                queryString.append(" AND trunc(ps.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
                map.put("creationDateTo", creationDateTo);
            }
            SaafToolUtils.parperParam(paramJSON, "ps.scheme_name", "schemeName", queryString, map, "LIKE");
            SaafToolUtils.parperParam(paramJSON, "tpl.tpl_name", "tplName", queryString, map, "LIKE");
            SaafToolUtils.parperParam(paramJSON, "ps.org_id", "orgId", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "ps.evaluate_period", "evaluatePeriod", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "su.user_full_name", "userFullName", queryString, map, "LIKE");
            SaafToolUtils.parperParam(paramJSON, "ps.status", "status", queryString, map, "=");
            String evaluateIntervalFrom = paramJSON.getString("evaluateIntervalFrom");
            if (evaluateIntervalFrom != null && !"".equals(evaluateIntervalFrom.trim())) {
                queryString.append(" AND ps.evaluate_interval_from >= to_date(:evaluateIntervalFrom, 'yyyy-mm-dd')\n");
                map.put("evaluateIntervalFrom", evaluateIntervalFrom);
            }
            String evaluateIntervalTo = paramJSON.getString("evaluateIntervalTo");
            if (evaluateIntervalTo != null && !"".equals(evaluateIntervalTo.trim())) {
                queryString.append(" AND ps.evaluate_interval_from <= to_date(:evaluateIntervalTo, 'yyyy-mm-dd')\n");
                map.put("evaluateIntervalTo", evaluateIntervalTo);
            }

            // 排序
            queryString.append(" ORDER BY ps.creation_date DESC");
            return srmSpmPerformanceSchemeDAO_HI_RO.findList(queryString, map);
        } catch (Exception e) {
            throw new UtilsException("导出失败");
        }
    }


    /**
     * 保存绩效方案数据
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveScheme(JSONObject params) throws Exception {
        SrmSpmPerformanceSchemeEntity_HI entity_hi = null;
        JSONObject obj = params.getJSONObject("data");
        try {
            Integer varUserId = params.getInteger("varUserId");
            //如果id为空则新增 否则为修改
            if (null == obj.get("schemeId") || "".equals(SToolUtils.object2String(obj.get("schemeId")))) {
                boolean flag = findExistsScheme(obj.getInteger("orgId"), obj.getInteger("tplId"), obj.getString("evaluateIntervalFrom"));
                if (flag) {
                    return SToolUtils.convertResultJSONObj("E", "数据重复，保存失败!", 0, null);
                } else {
                    Date date = new Date();
                    String dateStr = IntfUtils.getDateStr(date, "yyyyMMdd");
                    entity_hi = new SrmSpmPerformanceSchemeEntity_HI();
                    entity_hi.setSchemeNumber(saafSequencesUtil.getDocSequences("srm_spm_performance_scheme".toUpperCase(), "S-", dateStr + "-", 3));
                    entity_hi.setSchemeName(obj.getString("schemeName"));
                    entity_hi.setOrgId(obj.getInteger("orgId"));
                    entity_hi.setTplId(obj.getInteger("tplId"));
                    entity_hi.setEvaluatePeriod(obj.getString("en_tplFrequency"));
                    entity_hi.setEvaluateIntervalFrom(obj.getString("evaluateIntervalFrom"));
                    entity_hi.setEvaluateIntervalTo(obj.getString("evaluateIntervalTo"));
                    entity_hi.setStatus(obj.getString("status"));
                    entity_hi.setRatioRequestStatus(obj.getString("ratioRequestStatus"));
                    entity_hi.setOperatorUserId(varUserId);
                    srmSpmPerformanceSchemeDAO_HI.save(entity_hi);
                    return SToolUtils.convertResultJSONObj("S", "保存绩效方案数据成功", 1, entity_hi);
                }
            } else { // 更新
                entity_hi = srmSpmPerformanceSchemeDAO_HI.findByProperty("schemeId", obj.get("schemeId")).get(0);
                entity_hi.setSchemeNumber(obj.getString("schemeNumber"));
                entity_hi.setSchemeName(obj.getString("schemeName"));
                entity_hi.setOrgId(obj.getInteger("orgId"));
                entity_hi.setTplId(obj.getInteger("tplId"));
                entity_hi.setEvaluatePeriod(obj.getString("en_tplFrequency"));
                entity_hi.setEvaluateIntervalFrom(obj.getString("evaluateIntervalFrom"));
                entity_hi.setEvaluateIntervalTo(obj.getString("evaluateIntervalTo"));
                entity_hi.setStatus(obj.getString("status"));
                entity_hi.setRatioRequestStatus(obj.getString("ratioRequestStatus"));
                entity_hi.setOperatorUserId(varUserId);
                srmSpmPerformanceSchemeDAO_HI.update(entity_hi);
                return SToolUtils.convertResultJSONObj("S", "更新绩效方案数据成功", 1, entity_hi);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new UtilsException("保存失败");
        }
    }


    /**
     * 校验数据是否存在
     * @param orgId
     * @param tplId
     * @param evaluateIntervalFrom
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public boolean findExistsScheme(Integer orgId, Integer tplId, String evaluateIntervalFrom) throws Exception {
        try {
            StringBuffer sql = new StringBuffer(SrmSpmPerformanceSchemeEntity_HI_RO.QUERY_SCHEME_COUNT);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orgId", orgId);
            map.put("tplId", tplId);
            map.put("evaluateIntervalFrom", evaluateIntervalFrom);
            map.put("status", "CANCELED");
            int count = srmSpmPerformanceSchemeDAO_HI_RO.findList(sql, map).get(0).getCount();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new UtilsException(e);
        }
    }


    /**
     * 绩效方案查询
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
    public Pagination<SrmSpmPerformanceSchemeEntity_HI_RO> findSchemeInfoList(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmSpmPerformanceSchemeEntity_HI_RO.QUERY_SCHEME_INFO_LIST);
            Map<String, Object> map = new HashMap();
            // 查询过滤条件
            SaafToolUtils.parperParam(paramJSON, "ps.scheme_number", "schemeNumber", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "tpl.tpl_code", "tplCode", queryString, map, "=");
            String creationDateFrom = paramJSON.getString("creationDateFrom");
            if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
                queryString.append(" AND trunc(ps.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
                map.put("creationDateFrom", creationDateFrom);
            }
            String creationDateTo = paramJSON.getString("creationDateTo");
            if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
                queryString.append(" AND trunc(ps.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
                map.put("creationDateTo", creationDateTo);
            }
            SaafToolUtils.parperParam(paramJSON, "ps.scheme_name", "schemeName", queryString, map, "LIKE");
            SaafToolUtils.parperParam(paramJSON, "tpl.tpl_name", "tplName", queryString, map, "LIKE");
            SaafToolUtils.parperParam(paramJSON, "ps.org_id", "orgId", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "ps.evaluate_period", "evaluatePeriod", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "su.user_full_name", "userFullName", queryString, map, "LIKE");
            SaafToolUtils.parperParam(paramJSON, "ps.status", "status", queryString, map, "=");
            String evaluateIntervalFrom = paramJSON.getString("evaluateIntervalFrom");
            if (evaluateIntervalFrom != null && !"".equals(evaluateIntervalFrom.trim())) {
                queryString.append(" AND ps.evaluate_interval_from >= to_date(:evaluateIntervalFrom, 'yyyy-mm-dd')\n");
                map.put("evaluateIntervalFrom", evaluateIntervalFrom);
            }
            String evaluateIntervalTo = paramJSON.getString("evaluateIntervalTo");
            if (evaluateIntervalTo != null && !"".equals(evaluateIntervalTo.trim())) {
                queryString.append(" AND ps.evaluate_interval_from <= to_date(:evaluateIntervalTo, 'yyyy-mm-dd')\n");
                map.put("evaluateIntervalTo", evaluateIntervalTo);
            }
            String countSql = "select count(1) from (" + queryString + ")";
            // 排序
            queryString.append(" ORDER BY ps.creation_date DESC");
            Pagination<SrmSpmPerformanceSchemeEntity_HI_RO> scheneList = srmSpmPerformanceSchemeDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return scheneList;
        } catch (Exception e) {
            throw new UtilsException("查询失败："+e.getMessage());
        }
    }



    /**
     * 应用绩效模板
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
    public Pagination<SrmSpmPerformanceSchemeEntity_HI_RO> findTplLov(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer sqlBuffer = new StringBuffer(SrmSpmPerformanceSchemeEntity_HI_RO.QUERY_SCHEME_TPL_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(params, " tpl.tpl_code", "tplCode", sqlBuffer, map, "LIKE");
            SaafToolUtils.parperParam(params, " tpl.tpl_name", "tplName", sqlBuffer, map, "LIKE");
            SaafToolUtils.parperParam(params, " tpl.status", "status", sqlBuffer, map, "=");
            SaafToolUtils.parperParam(params, " tpl.org_id", "orgId", sqlBuffer, map, "=");
            SaafToolUtils.parperParam(params, " tpl.item_type", "itemType", sqlBuffer, map, "=");
            String countSql = "select count(1) from (" + sqlBuffer + ")";
            Pagination<SrmSpmPerformanceSchemeEntity_HI_RO> list = srmSpmPerformanceSchemeDAO_HI_RO.findPagination(sqlBuffer,countSql, map, pageIndex, pageRows);
            return list;
        } catch (Exception e) {
            //e.printStackTrace();
            throw new UtilsException("查询失败");
        }
    }


    /**
     * 查询方案编号
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
    public Pagination<SrmSpmPerformanceSchemeEntity_HI_RO> findSchemeNumber(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer sqlBuffer = new StringBuffer(SrmSpmPerformanceSchemeEntity_HI_RO.QUERY_SCHEME_NUMBER_SQL);
            Map<String, Object> map = new HashMap();
            SaafToolUtils.parperParam(params, " ps.scheme_number", "schemeNumber", sqlBuffer, map, "LIKE");
            SaafToolUtils.parperParam(params, " ps.scheme_name", "schemeName", sqlBuffer, map, "LIKE");
            SaafToolUtils.parperParam(params, " ps.scheme_id", "schemeId", sqlBuffer, map, "=");
            String countSql = "select count(1) from (" + sqlBuffer + ")";
            Pagination<SrmSpmPerformanceSchemeEntity_HI_RO> list = srmSpmPerformanceSchemeDAO_HI_RO.findPagination(sqlBuffer,countSql, map, pageIndex, pageRows);
            return list;
        } catch (Exception e) {
            //e.printStackTrace();
            throw new UtilsException("查询失败");
        }
    }
}

