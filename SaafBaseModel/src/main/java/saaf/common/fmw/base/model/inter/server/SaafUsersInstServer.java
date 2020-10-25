package saaf.common.fmw.base.model.inter.server;


import com.alibaba.fastjson.JSON;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafUserAccessOrgsEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafUserInstEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafUsersInst;
import saaf.common.fmw.common.utils.SaafToolUtils;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SaafUsersInstServer.java
 * Description：区域所有用户
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     钟士元             创建
 * ===========================================================================
 */
@Component("baseSaafUsersInstServer")
public class SaafUsersInstServer implements ISaafUsersInst {
    public SaafUsersInstServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SaafUserInstEntity_HI_RO> saafUserInstDAO_HI_RO;
    @Autowired
    private ViewObject<SaafUserAccessOrgsEntity_HI> saafUserAccessOrgsDAO_HI;

    /**
     * 查询组织下所有用户
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public List findInstUsers(JSONObject parameters) throws Exception {
        try {
            StringBuffer querySql = new StringBuffer();
            querySql.append(SaafUserInstEntity_HI_RO.QUERY_USER_INST_SQL);
            querySql.append(" AND sua.inst_id =:instId");
            querySql.append(" AND sua.platform_code =:platformCode");
            List list = new ArrayList();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("instId", parameters.getInteger("instId"));
            map.put("platformCode", parameters.getString("varPlatformCode"));
            list = saafUserInstDAO_HI_RO.findList(querySql, map);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    /**
     * 查询用用户属于所有组织树结构
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public List findUsersInstTree(JSONObject parameters) throws Exception {
        try {
            StringBuffer querySql = new StringBuffer();
            querySql.append(SaafUserInstEntity_HI_RO.QUERY_USER_INST_TREE_SQL);
            querySql.append(" AND sua.user_id =:userId");
            querySql.append(" AND sua.platform_code =:platformCode");
            List list = new ArrayList();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", parameters.getInteger("userId"));
            map.put("platformCode", parameters.getString("platformCode"));
            list = saafUserInstDAO_HI_RO.findList(querySql, map);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * LOV：给用户分配组织{不显示已分配的组织)
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public Pagination findRemainderInst(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer querySql = new StringBuffer(SaafUserInstEntity_HI_RO.QUERY_ASSIGNED_INST_TO_USER_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "si.INST_CODE", "instCode", querySql, map, "like");
            SaafToolUtils.parperParam(parameters, "si.INST_NAME", "instName", querySql, map, "like");
            String countSql = "select count(1) from (" + querySql + ")";
            Pagination<SaafUserInstEntity_HI_RO> rowSet = saafUserInstDAO_HI_RO.findPagination(querySql,countSql, map, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * LOV：给组织分配用户(不显示当前组织已分配用户)
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public Pagination findRemainderUser(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer querySql = new StringBuffer(SaafUserInstEntity_HI_RO.QUERY_USERS_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            String instId = SToolUtils.object2String(parameters.get("instId"));
            if (parameters.get("instId") != null && !"".equals(instId.trim())) {
                map.put("instId", instId);
            }
            SaafToolUtils.parperParam(parameters, "se.employee_name", "employeeName", querySql, map, "like");
            String countSql = "select count(1) from (" + querySql + ")";
            Pagination<SaafUserInstEntity_HI_RO> rowSet = saafUserInstDAO_HI_RO.findPagination(querySql, countSql,map, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询用户已分配组织
     * @param userId
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */

    public List<SaafUserAccessOrgsEntity_HI> findUserInsts(Integer userId) throws Exception {
        try {
            List<SaafUserAccessOrgsEntity_HI> list = null;
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            list = saafUserAccessOrgsDAO_HI.findList(" from SaafUserAccessOrgsEntity_HI where userId = :userId ", map);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询用户所属区域
     * @param userId
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public List findUsersInstRegion(Integer userId) throws Exception {
        try {
            StringBuffer querySql = new StringBuffer();
            querySql.append(SaafUserInstEntity_HI_RO.QUERY_USER_INST_REGION_SQL);
            querySql.append(" AND Suao.User_Id = :userId");
            List list = new ArrayList();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            list = saafUserInstDAO_HI_RO.findList(querySql, map);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询区域所属用户
     * @param region
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    @Override
    public List findInstRegionUsers(String region) throws Exception {
        try {
            StringBuffer querySql = new StringBuffer();
            querySql.append(SaafUserInstEntity_HI_RO.QUERY_INST_REGION_USER_SQL);
            querySql.append(" AND Si.Inst_Region = :region");
            List list = new ArrayList();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("region", region);
            list = saafUserInstDAO_HI_RO.findList(querySql, map);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询组织已分配用户
     * @param accessOrgId
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */

    public List<SaafUserAccessOrgsEntity_HI> findInstUsers(Integer accessOrgId) throws Exception {
        try {
            List<SaafUserAccessOrgsEntity_HI> list = null;
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("accessOrgId", accessOrgId);
            list = saafUserAccessOrgsDAO_HI.findList(" from SaafUserAccessOrgsEntity_HI where accessOrgId = :accessOrgId ", map);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 保存用户组织关系
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public JSONObject saveUserInsts(JSONObject parameters) throws Exception {
        SaafUserAccessOrgsEntity_HI row = null;
        List userInstList = new ArrayList();
        deleteUserInst(parameters);
        //获取页面数据
        int varUserId = SToolUtils.object2Int(parameters.get("varUserId"));
        JSONArray valuesArray = JSON.parseArray(parameters.getString("userInstData"));
        try {
            for (int i = 0; i < valuesArray.size(); i++) {
                JSONObject valuesJson = valuesArray.getJSONObject(i);
                //判断是新增还是修改
                if (null == valuesJson.get("accessOrgId") || "".equals(SToolUtils.object2String(valuesJson.get("accessOrgId")).trim())) {
                    row = new SaafUserAccessOrgsEntity_HI();
                } else {
                    row = saafUserAccessOrgsDAO_HI.findByProperty("accessOrgId", SToolUtils.object2Int(valuesJson.get("accessOrgId"))).get(0);
                }
                row.setPlatformCode(SToolUtils.object2String(parameters.get("varPlatformCode")));
                row.setUserId(SToolUtils.object2Int(valuesJson.get("userId")));
                row.setInstId(SToolUtils.object2Int(valuesJson.get("instId")));
                row.setOperatorUserId(varUserId);
                String startDate = SToolUtils.object2String(valuesJson.get("startDate"));
                if (null != valuesJson.get("startDate") && !"".equals(startDate.trim())) {
                    Date startDateActive = SToolUtils.string2DateTime(startDate, "yyyy-MM-dd");
                    row.setStartDate(startDateActive);
                }
                String endDate = SToolUtils.object2String(valuesJson.get("endDate"));
                if (null != valuesJson.get("endDate") && !"".equals(endDate.trim())) {
                    Date endDateActive = SToolUtils.string2DateTime(endDate, "yyyy-MM-dd");
                    row.setEndDate(endDateActive);
                }
                userInstList.add(row);
            }
            saafUserAccessOrgsDAO_HI.saveOrUpdateAll(userInstList);
            return SToolUtils.convertResultJSONObj("S", "保存成功！", 0, userInstList);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 保存组织用户关系
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public JSONObject saveInstUsers(JSONObject parameters) throws Exception {
        SaafUserAccessOrgsEntity_HI row = null;
        List instUserList = new ArrayList();
        //获取页面数据
        int varUserId = SToolUtils.object2Int(parameters.get("varUserId"));
        JSONArray valuesArray = JSON.parseArray(parameters.getString("instUserData"));
        try {
            for (int i = 0; i < valuesArray.size(); i++) {
                JSONObject valuesJson = valuesArray.getJSONObject(i);
                //判断是新增还是修改
                if (null == valuesJson.get("accessOrgId") || "".equals(SToolUtils.object2String(valuesJson.get("accessOrgId")).trim())) {
                    row = new SaafUserAccessOrgsEntity_HI();
                } else {
                    row = saafUserAccessOrgsDAO_HI.findByProperty("accessOrgId", SToolUtils.object2Int(valuesJson.get("accessOrgId"))).get(0);
                }
                row.setPlatformCode(SToolUtils.object2String(parameters.get("varPlatformCode")));
                row.setUserId(SToolUtils.object2Int(valuesJson.get("userId")));
                row.setInstId(SToolUtils.object2Int(valuesJson.get("instId")));
                row.setOperatorUserId(varUserId);
                String startDate = SToolUtils.object2String(valuesJson.get("startDate"));
                if (null != valuesJson.get("startDate") && !"".equals(startDate.trim())) {
                    Date startDateActive = SToolUtils.string2DateTime(startDate, "yyyy-MM-dd");
                    row.setStartDate(startDateActive);
                }
                String endDate = SToolUtils.object2String(valuesJson.get("endDate"));
                if (null != valuesJson.get("endDate") && !"".equals(endDate.trim())) {
                    Date endDateActive = SToolUtils.string2DateTime(endDate, "yyyy-MM-dd");
                    row.setEndDate(endDateActive);
                }
                instUserList.add(row);
            }
            saafUserAccessOrgsDAO_HI.saveOrUpdateAll(instUserList);
            return SToolUtils.convertResultJSONObj("S", "保存成功！", 0, instUserList);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 删除用户组织关系
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public Boolean deleteUserInst(JSONObject parameters) throws Exception {
        try {
            List<SaafUserAccessOrgsEntity_HI> list = findUserInsts(parameters.getInteger("userId"));
            saafUserAccessOrgsDAO_HI.deleteAll(list);
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 删除组织用户关系
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public Boolean deleteInstUser(JSONObject parameters) throws Exception {
        try {
            List<SaafUserAccessOrgsEntity_HI> list = findInstUsers(parameters.getInteger("accessOrgId"));
            saafUserAccessOrgsDAO_HI.deleteAll(list);
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询当前用用户属于组织结构
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public Pagination<SaafUserInstEntity_HI_RO> findUsersInstByself(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            if (parameters.get("varUserId") != null) {
                parameters.put("var_equal_userId", parameters.getInteger("varUserId"));
            }
            if (parameters.get("instCode") != null) {
                parameters.put("var_like_instCode", parameters.getString("instCode"));
            }
            if (parameters.get("instName") != null) {
                parameters.put("var_like_instName", parameters.getString("instName"));
            }
            Pagination<SaafUserInstEntity_HI_RO> findPagination =
                saafUserInstDAO_HI_RO.findPagination(SaafUserInstEntity_HI_RO.QUERY_USER_INST_TREE_SQL, parameters, pageIndex, pageRows);
            return findPagination;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
