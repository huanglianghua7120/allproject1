package saaf.common.fmw.base.model.inter.server;


import com.alibaba.fastjson.JSONObject;

import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafResponsibilitysEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafResponsibilitysEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafResponsibilitys;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：baseSaafResponsibilitysServer.java
 * Description：用来处理职责维护
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
@Component("baseSaafResponsibilitysServer")
public class SaafResponsibilitysServer implements ISaafResponsibilitys {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafResponsibilitysServer.class);
    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    public SaafResponsibilitysServer() {
        super();
    }
    @Autowired
    private ViewObject<SaafResponsibilitysEntity_HI> saafResponsibilitysDAO_HI;
    @Autowired
    private BaseViewObject<SaafResponsibilitysEntity_HI_RO> saafResponsibilitysDAO_HI_RO;


    /**
     * 查询职责名称（LOV）
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public Pagination findRespNameLov(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer where = new StringBuffer(" where responsibilityId is not null");
            SaafToolUtils.parperParam(parameters, "platformCode", "varPlatformCode", where, queryParamMap, "=");
            SaafToolUtils.parperParam(parameters, "responsibilityName", "varResponsibilityName", where, queryParamMap, "like");

            Pagination<SaafResponsibilitysEntity_HI> RespRowSet =
                saafResponsibilitysDAO_HI.findPagination
                        (" from SaafResponsibilitysEntity_HI " + where, queryParamMap, pageIndex, pageRows);
            return RespRowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询有效职责列表
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public Pagination findRespist(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SaafResponsibilitysEntity_HI_RO.QUERY_RESP_SQL);
            Map<String, Object> map = new HashMap();
            SaafToolUtils.parperParam(parameters, "sr.responsibility_name", "varResponsibilityName", queryString, map, "like");
            String countSql = "select count(1) from (" + queryString + ")";
            return saafResponsibilitysDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询职责列表
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public Pagination findResponsibilityList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SaafResponsibilitysEntity_HI_RO.QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "sr.responsibility_name", "varResponsibilityName", queryString, map, "LIKE");
            SaafToolUtils.parperParam(parameters, "sr.responsibility_key", "varResponsibilityKey", queryString, map, "LIKE");

            String startDateActiveFrom = parameters.getString("varStartDateActiveFrom");
            if (startDateActiveFrom != null && !"".equals(startDateActiveFrom.trim())) {
                queryString.append("AND    sr.start_date_active >= to_date(:startDateActiveFrom, 'yyyy/mm/dd')\n");
                map.put("startDateActiveFrom", startDateActiveFrom);
            }
            String startDateActiveTo = parameters.getString("varStartDateActiveTo");
            if (startDateActiveTo != null && !"".equals(startDateActiveTo.trim())) {
                queryString.append("AND    sr.start_date_active <= to_date(:startDateActiveTo, 'yyyy/mm/dd')\n");
                map.put("startDateActiveTo", startDateActiveTo);
            }
            String countSql = "select count(1) from (" + queryString + ")";
            //新增排序条件
            queryString.append(" ORDER BY sr.last_update_date DESC ");
            Pagination respRowSet = saafResponsibilitysDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return respRowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询职责ById
     * @param map
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public SaafResponsibilitysEntity_HI findRespById(Map<String, Object> map) throws Exception {
        try {
            SaafResponsibilitysEntity_HI row = saafResponsibilitysDAO_HI.findByProperty(map).get(0);
            return row;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 编辑职责
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public JSONObject saveResponsibility(JSONObject parameters) throws Exception {
        try {
            int varUserId = SToolUtils.object2Int(parameters.get("varUserId"));
            SaafResponsibilitysEntity_HI row = null;
            if (null == parameters.get("responsibilityId") || "".equals(SToolUtils.object2String(parameters.get("responsibilityId")).trim())) { //判断是否新增
                row = new SaafResponsibilitysEntity_HI();
                row.setCreatedBy(varUserId);
                row.setCreationDate(new Date());
                //update by liuwenjun 20170214
                String respSeq = saafSequencesUtil.getDocSequences("saaf_responsibilitys", null, 6);
                row.setResponsibilityKey(respSeq);
            } else {
                row = saafResponsibilitysDAO_HI.findByProperty("responsibilityId", SToolUtils.object2Int(parameters.get("responsibilityId"))).get(0);
            }
            boolean nameflag = findExistsRespNameRepeat(parameters.get("responsibilityName").toString(), row.getResponsibilityId());
            if (nameflag) { // || keyflag
                String msg = "";
                if (nameflag) {
                    msg = msg + "职责名称：" + parameters.get("responsibilityName").toString() + "已存在";
                }
                return SToolUtils.convertResultJSONObj("E", "保存失败!" + msg, 0, null);
            }
            row.setOperatorUserId(varUserId);
            String responsibilityName = SToolUtils.object2String(parameters.get("responsibilityName"));
            String platformCode = SToolUtils.object2String(parameters.get("platformCode"));
            row.setResponsibilityName(responsibilityName);
            row.setPlatformCode(platformCode);
            String startDateActive_ = SToolUtils.object2String(parameters.get("startDateActive"));
            if (null != parameters.get("startDateActive") && !"".equals(startDateActive_.trim())) {
                Date startDateActive = SToolUtils.string2DateTime(startDateActive_, "yyyy-MM-dd");
                row.setStartDateActive(startDateActive);
            }
            String endDateActive_ = SToolUtils.object2String(parameters.get("endDateActive"));
            if (null != parameters.get("endDateActive") && !"".equals(endDateActive_.trim())) {
                Date endDateActive = SToolUtils.string2DateTime(endDateActive_, "yyyy-MM-dd");
                row.setEndDateActive(endDateActive);
            } else {
                row.setEndDateActive(null);
            }
            saafResponsibilitysDAO_HI.saveOrUpdate(row);
            return SToolUtils.convertResultJSONObj("S", " 保存成功!", 1, row);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 验证职责名称responsibilityName
     * @param responsibilityName
     * @param responsibilityId
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public boolean findExistsRespNameRepeat(String responsibilityName, Object responsibilityId) {
        StringBuffer queryString = new StringBuffer();
        queryString.append(SaafResponsibilitysEntity_HI_RO.QUERY_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != responsibilityId && !"".equals(responsibilityId)) {
            queryString.append(" and sr.responsibility_id <> :respId ");
            map.put("respId", responsibilityId);
        }
        queryString.append("  and  sr.responsibility_name =:responsibilityName");
        map.put("responsibilityName", responsibilityName);
        List<SaafResponsibilitysEntity_HI_RO> rowSet = saafResponsibilitysDAO_HI_RO.findList(queryString.toString(), map);
        if (rowSet.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 验证职责简称responsibilityKey
     * @param responsibilityKey
     * @param responsibilityId
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public boolean findExistsRespKeyRepeat(String responsibilityKey, Object responsibilityId) {
        StringBuffer queryString = new StringBuffer();
        queryString.append(SaafResponsibilitysEntity_HI_RO.QUERY_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != responsibilityId && !"".equals(responsibilityId)) {
            queryString.append(" and sr.responsibility_id <> :respId ");
            map.put("respId", responsibilityId);
        }
        queryString.append(" and  sr.responsibility_name =:responsibilityKey");
        map.put("responsibilityKey", responsibilityKey);
        List<SaafResponsibilitysEntity_HI_RO> rowSet = null;
        rowSet = saafResponsibilitysDAO_HI_RO.findList(queryString.toString(), map);
        if (rowSet.size() > 0) {
            return true;
        }
        return false;
    }
}
