package saaf.common.fmw.base.model.inter.server;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafResponsibilitysEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUserRespEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafUserMainRespEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafUserRespEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafUserResp;
import saaf.common.fmw.common.utils.SaafToolUtils;


@Component("baseSaafUserRespServer")
public class SaafUserRespServer implements ISaafUserResp {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaafUserRespServer.class);

    @Autowired
    private ViewObject<SaafResponsibilitysEntity_HI> saafResponsibilitysDAO_HI;

    @Autowired
    private BaseViewObject<SaafUserRespEntity_HI_RO> saafUserRespDAO_HI_RO;

    @Autowired
    private BaseViewObject<SaafUserMainRespEntity_HI_RO> saafUserMainRespDAO_HI_RO;

    @Autowired
    private ViewObject<SaafUserRespEntity_HI> saafUserRespDAO_HI;

    public SaafUserRespServer() {
        super();
    }

    /**
     * 保存有效职责（内部创建供应商）
     *
     * @param userId
     * @param responsibilityKey 默认职责
     * @param platformCode      职责编码
     * @return
     */
    @Override
    public boolean saveSaafUserResp(Integer userId, Integer varUserId, String responsibilityKey, String platformCode) {
        boolean flag = false;
        SaafUserRespEntity_HI resp = new SaafUserRespEntity_HI();
        SaafResponsibilitysEntity_HI findResp = null;
        List<SaafResponsibilitysEntity_HI> findList = saafResponsibilitysDAO_HI.findByProperty("responsibilityKey", responsibilityKey);
        if (findList.size() > 0) {
            findResp = findList.get(0);
        } else {
            LOGGER.error("默认职责不存在，请联系系统管理员！");
            return false;
        }
        resp.setStartDateActive(new Date());
        resp.setUserId(userId);
        resp.setResponsibilityId(findResp.getResponsibilityId());
        resp.setUserRespName(findResp.getResponsibilityName());
        resp.setPlatformCode(platformCode);
        resp.setOperatorUserId(varUserId);
        saafUserRespDAO_HI.saveEntity(resp);
        flag = true;
        return flag;
    }

    /**
     * 查询用户登录职责
     *
     * @param param
     * @return
     */
    public List findSaafUserResp(Object[] param) throws Exception {
        try {
            List userRespList = saafUserRespDAO_HI_RO.findList(SaafUserRespEntity_HI_RO.QUERY_SQL, param);
            return userRespList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询用户职责列表
     *
     * @param map
     * @return
     */
    public List findSaafUserRespByUserId(Map<String, Object> map) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SaafUserMainRespEntity_HI_RO.QUERY_SQL);
            queryString.append(" AND s.user_id = :userId");
            List rowSet = saafUserMainRespDAO_HI_RO.findList(queryString.toString(), map);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询用户供应商职责列表
     *
     * @param map
     * @return
     */
    public List findSaafUserSupplierRespByUserId(Map<String, Object> map) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SaafUserMainRespEntity_HI_RO.QUERY_SUPPLIER_RESP_SQL);
            queryString.append(" AND s.user_id = :userId");
            queryString.append(" AND s.Responsibility_Id = :responsibilityId");
            List rowSet = saafUserMainRespDAO_HI_RO.findList(queryString.toString(), map);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询用户职责维护列表XXX
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public Pagination findSaafUserRespList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SaafUserMainRespEntity_HI_RO.QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            Pagination<SaafUserMainRespEntity_HI_RO> rowSet = saafUserMainRespDAO_HI_RO.findPagination(queryString, map, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询用户所有职责
     *
     * @param parameters
     * @return
     * @throws Exception
     */
    public Pagination findUserRespAll(JSONObject parameters) throws Exception {
        Integer userId = parameters.getInteger("userId");
        if (userId == null) {
            userId = -1;
        }
        String platformCode = parameters.getString("platformCode");
        if (platformCode == null || "".equals(platformCode.trim())) {
            platformCode = "SAAF";
        }

        StringBuffer querySql = new StringBuffer();
        querySql.append(SaafUserRespEntity_HI_RO.QUERY_SQL2);
        Map<String, Object> map = new HashMap();
        map.put("userId", userId);
        map.put("platformCode", platformCode);
        Pagination<SaafUserRespEntity_HI_RO> rowSet = saafUserRespDAO_HI_RO.findPagination(querySql, map, 1, 20);
        return rowSet;
    }

    /**
     * 查询职责所有用户
     *
     * @param parameters
     * @return
     * @throws Exception
     */
    public Pagination findRespUserList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("responsibilityId", parameters.getInteger("respId"));
            Pagination<SaafUserRespEntity_HI_RO> findPagination = saafUserRespDAO_HI_RO.findPagination(SaafUserRespEntity_HI_RO.QUERY_USERS_SQL, paramMap, pageIndex, pageRows);
            return findPagination;
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception(e);
        }
    }

    /**
     * 查询职责未分配所有用户
     *
     * @param parameters
     * @return
     * @throws Exception
     */
    public Pagination findRespRemaining(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("responsibilityId", parameters.getInteger("respId"));
            StringBuffer whereSql = new StringBuffer(SaafUserRespEntity_HI_RO.QUERY_NOT_EXISTS_USERS_SQL);
            SaafToolUtils.parperParam(parameters, "su.user_full_name", "varUserFullName", whereSql, map, "like");
            SaafToolUtils.parperParam(parameters, "su.user_name", "userName", whereSql, map, "like");
            Pagination<SaafUserRespEntity_HI_RO> list = saafUserRespDAO_HI_RO.findPagination(whereSql, map, pageIndex, pageRows);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * LOV:查询未分配给用户的所以职责
     *
     * @param parameters
     * @return
     */
    public Pagination findRemainderUserResp(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer querySql = new StringBuffer();
            querySql.append(SaafUserRespEntity_HI_RO.QUERY_ASSIGNED_RESP_TO_USER_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", SToolUtils.object2Int(parameters.getInteger("userId")));
            SaafToolUtils.parperParam(parameters, "sr.RESPONSIBILITY_NAME", "responsibilityName", querySql, map, "like");
            //按平台控制分配权限
            SaafToolUtils.parperParam(parameters, "sr.platform_code", "platformCode", querySql, map, "=");
            Pagination<SaafUserRespEntity_HI_RO> rowSet = saafUserRespDAO_HI_RO.findPagination(querySql, map, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 编辑或创建用户职责
     *
     * @param parameters
     * @return
     */
    public List saveSaafUserResp(JSONObject parameters) throws Exception {
        SaafUserRespEntity_HI row = null;
        List userRespList = new ArrayList();
        //获取页面数据
        int varUserId = SToolUtils.object2Int(parameters.get("varUserId"));
        try {
            JSONArray valuesArray = parameters.getJSONArray("userRespData");
            for (int i = 0; i < valuesArray.size(); i++) {
                JSONObject valuesJson = valuesArray.getJSONObject(i);
                //判断是新增还是修改
                row = new SaafUserRespEntity_HI();
                row.setCreatedBy(varUserId); // 用户登录的userId，从session里面获取
                row.setCreationDate(new Date());
                row.setLastUpdatedBy(varUserId);
                row.setLastUpdateDate(new Date());
                row.setLastUpdateLogin(varUserId);
                row.setStartDateActive(new Date());
                row.setPlatformCode(SToolUtils.object2String(valuesJson.get("varPlatformCode") == null ? parameters.get("varPlatformCode") : valuesJson.get("varPlatformCode")));
                row.setUserId(SToolUtils.object2Int(valuesJson.get("userId")));
                row.setResponsibilityId(SToolUtils.object2Int(valuesJson.get("respId") == null ? parameters.get("respId") : valuesJson.get("respId")));
                row.setUserRespName(SToolUtils.object2String(valuesJson.get("userRespName")));
                userRespList.add(row);
            }
            saafUserRespDAO_HI.saveOrUpdateAll(userRespList);
            return userRespList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 删除用户职责
     *
     * @param parameters
     * @return
     */
    public JSONObject deleteSaafUserResp(JSONObject parameters) throws Exception {
        try {
            Integer userRespId = null;
            userRespId = parameters.getInteger("userRespId");
            SaafUserRespEntity_HI row = saafUserRespDAO_HI.getById(SToolUtils.object2Int(userRespId));
            saafUserRespDAO_HI.delete(row);
            return SToolUtils.convertResultJSONObj("S", "删除成功", 0, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 验证字段：userRespName是否重复
     *
     * @param parameters
     * @return
     */
    public boolean findExisUserRespName(JSONObject parameters) throws Exception {
        try {
            LOGGER.info("--------用户职责维护-验证字段：userRespName是否重复-参数=" + parameters.toString());
            JSONArray valuesArray = parameters.getJSONArray("userRespData");
            for (Object en : valuesArray.toArray()) {
                JSONObject objTemp = JSONObject.parseObject(en.toString());
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("platformCode", objTemp.getString("varPlatformCode"));
                map.put("respId", objTemp.getString("respId"));
                StringBuffer queryString = new StringBuffer();
                queryString.append(SaafUserMainRespEntity_HI_RO.QUERY_SQL);
                queryString.append(" and s.platform_code = :platformCode");
                queryString.append(" and s.responsibility_id = :respId");
                List<SaafUserMainRespEntity_HI_RO> userRespList = saafUserMainRespDAO_HI_RO.findList(queryString.toString(), map);
                LOGGER.info("-------用户职责维护，userRespList=" + JSONObject.toJSONString(userRespList));
                if (userRespList != null && userRespList.size() > 0) {
                    for (SaafUserMainRespEntity_HI_RO row : userRespList) {
                        if (row.getUserId().toString().equals(objTemp.get("userId").toString())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
