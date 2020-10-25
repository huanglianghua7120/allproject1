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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafProfileValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafProfilesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafProfileLovEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafProfileOrValueEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafProfileValueEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafProfilesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafProfiles;
import saaf.common.fmw.common.utils.SaafToolUtils;


@Component("baseSaafProfilesServer")
public class SaafProfilesServer implements ISaafProfiles {
    public SaafProfilesServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SaafProfileOrValueEntity_HI_RO> saafProfileOrValueDAO_HI_RO;
    @Autowired
    private BaseViewObject<SaafProfileLovEntity_HI_RO> saafProfileLovDAO_HI_RO;
    @Autowired
    private BaseViewObject<SaafProfilesEntity_HI_RO> saafProfilesDAO_HI_RO;
    @Autowired
    private ViewObject<SaafProfilesEntity_HI> saafProfilesDAO_HI;

    @Autowired
    private ViewObject<SaafProfileValuesEntity_HI> saafProfileValuesDAO_HI;

    /**
     * 根据配置文件编码查询配置文件值
     * @author LFW
     * @param profileNumber
     * @return
     */
    @Override
    public String findProfileValueByProNum(String profileNumber) throws Exception {
        StringBuffer sb = new StringBuffer(SaafProfileOrValueEntity_HI_RO.QUERY_SQL_2);
        sb.append(" and ep.profile_number  =:profileNumber ");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("profileNumber", profileNumber);
        SaafProfileOrValueEntity_HI_RO sp = saafProfileOrValueDAO_HI_RO.findList(sb.toString(), map).get(0);
        return String.valueOf(sp.getProfileValue());
    }

    /**
     * 查询配置文件值（公共方法）
     * @param parameters
     * @return
     */
    public Pagination findProfileValues(JSONObject parameters) throws Exception {
        try {
            Object pProfileNumber = parameters.get("pProfileNumber");
            Object pFunId = parameters.get("pFunId");
            Object pOrgId = parameters.get("pOrgId");
            Object pRespId = parameters.get("pRespId");
            Object pUserId = parameters.get("pUserId");
            Pagination<SaafProfileOrValueEntity_HI_RO> rowSet = null;
            if ((pUserId == null || "".equals(pUserId)) && (pFunId == null || "".equals(pFunId)) && (pOrgId == null || "".equals(pOrgId)) &&
                (pUserId == null || "".equals(pUserId))) {
                Map<String, Object> map_2 = new HashMap<String, Object>();
                StringBuffer sql_2 = new StringBuffer(SaafProfileOrValueEntity_HI_RO.QUERY_SQL_2);
                sql_2.append(" and ep.profile_number like :profileNumber ");
                map_2.put("profileNumber", "%" + pProfileNumber.toString() + "%");
                rowSet = saafProfileOrValueDAO_HI_RO.findPagination(sql_2.toString(), map_2, 0, 0);
                return rowSet;
            } else {
                String pProfileLevel = "";
                String pProfileLevelId = "";
                boolean flag = true;
                if (flag) {
                    if (pFunId != null || !"".equals(pFunId)) {
                        pProfileLevel = "F";
                        pProfileLevelId = pFunId.toString();
                        flag = false;
                    }
                } else if (flag) {
                    if (pOrgId != null || !"".equals(pOrgId)) {
                        pProfileLevel = "O";
                        pProfileLevelId = pOrgId.toString();
                        flag = false;
                    }
                } else if (flag) {
                    if (pRespId != null || !"".equals(pRespId)) {
                        pProfileLevel = "R";
                        pProfileLevelId = pRespId.toString();
                        flag = false;
                    }
                } else if (flag) {
                    if (pUserId != null || !"".equals(pUserId)) {
                        pProfileLevel = "U";
                        pProfileLevelId = pUserId.toString();
                        flag = false;
                    }
                }
                Map<String, Object> map = new HashMap<String, Object>();
                StringBuffer sql = new StringBuffer(SaafProfileOrValueEntity_HI_RO.QUERY_SQL_1);
                sql.append(" and epv.profile_level_id=:profileLevelId " + " and epv.profile_level like :profileLevel " + " and ep.profile_number like :profileNumber ");
                map.put("profileNumber", "%" + pProfileNumber.toString() + "%");
                map.put("profileLevel", "%" + pProfileLevel.toString() + "%");
                map.put("profileLevelId", Integer.parseInt(pProfileLevelId.toString()));
                rowSet = saafProfileOrValueDAO_HI_RO.findPagination(sql.toString(), map, 0, 0);
                return rowSet;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * LOV查询???
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public Pagination findProfileLov(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(SaafProfileLovEntity_HI_RO.QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            sql.append(" where leve is not null ");
            SaafToolUtils.parperParam(parameters, "leve", "varLeve", sql, map, "=");
            SaafToolUtils.parperParam(parameters, "levelName", "varLevelName", sql, map, "=");
            SaafToolUtils.parperParam(parameters, "platform_code", "varPlatformCode", sql, map, "=");
            Pagination<SaafProfileLovEntity_HI_RO> rowSet = saafProfileLovDAO_HI_RO.findPagination(sql, map, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询配置文件
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public Pagination findSaafProfilesList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer where = new StringBuffer(SaafProfilesEntity_HI_RO.QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "sp.platform_code", "varPlatformCode", where, map, "=");
            SaafToolUtils.parperParam(parameters, "sp.profile_id", "varProfileId", where, map, "=");
            SaafToolUtils.parperParam(parameters, "sp.profile_Number", "varProfileNumber", where, map, "like");
            SaafToolUtils.parperParam(parameters, "sp.profile_Name", "varProfileName", where, map, "like");
            SaafToolUtils.parperParam(parameters, "sp.profile_Type", "varProfileType", where, map, "like");
            if ((null != parameters.get("varEnabledFlag") && !"".equals(parameters.getString("varEnabledFlag").trim()))) {
                if ("Y".equals(parameters.get("varEnabledFlag"))) { //add by liujun on 2016/12/12
                    where.append(" and sp.ENABLED_FLAG = 'Y'");
                    where.append(" and sysdate() >= sp.START_DATE_ACTIVE and sysdate() < ifnull (sp.END_DATE_ACTIVE, sysdate()+1)");
                } else if ("N".equals(parameters.get("varEnabledFlag"))) {
                    where.append(" and (sp.ENABLED_FLAG = 'N' or (sysdate() < sp.START_DATE_ACTIVE or sysdate() > ifnull(sp.END_DATE_ACTIVE, sysdate()+1)))");
                }
            }
            where.append("  ORDER BY  sp.last_update_date desc  ");
            Pagination<SaafProfilesEntity_HI_RO> profilesrowSet = saafProfilesDAO_HI_RO.findPagination(where, map, pageIndex, pageRows);
            return profilesrowSet;
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception(e);
        }
    }

    /**
     * 查询配置文件行  add by liujun on 2016/12/12
     * @param parameters
     * @return
     */
    public List findEnabledList(JSONObject parameters) throws Exception {
        StringBuffer where = new StringBuffer(SaafProfilesEntity_HI_RO.QUERY_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(parameters, "sp.platform_code", "varPlatformCode", where, map, "=");
        SaafToolUtils.parperParam(parameters, "sp.PROFILE_TYPE", "varPlatformCode", where, map, "=");
        where.append(" and sp.ENABLED_FLAG = 'Y'");
        where.append(" and sysdate() >= sp.START_DATE_ACTIVE and sysdate() < ifnull (sp.END_DATE_ACTIVE, sysdate()+1)");
        List profileList = saafProfilesDAO_HI_RO.findList(where, map);
        return profileList;
    }

    /**
     *  查询配置文件行
     * @param parameters
     * @return
     */
    public SaafProfilesEntity_HI_RO findSaafProfilesLine(JSONObject parameters) throws Exception {
        StringBuffer where = new StringBuffer(SaafProfilesEntity_HI_RO.QUERY_SQL);
        SaafProfilesEntity_HI_RO profileLine = null;
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(parameters, "sp.platform_code", "varPlatformCode", where, map, "=");
        SaafToolUtils.parperParam(parameters, "sp.profile_id", "varProfileId", where, map, "=");
        List<SaafProfilesEntity_HI_RO> profileList = saafProfilesDAO_HI_RO.findList(where, map);
        if (profileList.size() > 0) {
            profileLine = profileList.get(0);
        }
        return profileLine;
    }

    /**
     * 查询配置文件值
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    public Pagination findProfileValue(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer sql = new StringBuffer(SaafProfileValueEntity_HI_RO.QUERY_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            sql.append(" where profile_Value_Id is not null");
            SaafToolUtils.parperParam(parameters, "profile_Value_Id", "varProfileValueId", sql, map, "=");
            SaafToolUtils.parperParam(parameters, "profile_Id", "varProfileId", sql, map, "=");
            SaafToolUtils.parperParam(parameters, "profile_Level", "varProfileLevel", sql, map, "=");
            SaafToolUtils.parperParam(parameters, "profile_Level_Id", "varProfileLevelId", sql, map, "=");
            Pagination<SaafProfilesEntity_HI_RO> rowSet = saafProfilesDAO_HI_RO.findPagination(sql, map, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 编辑配置文件
     * @param parameters
     * @return
     */
    public JSONObject saveProfilesInfo(JSONObject parameters) throws Exception {
        try {
            SaafProfilesEntity_HI profilesRow = null;
            if (null == parameters.get("profileId") || "".equals(SToolUtils.object2String(parameters.get("profileId")).trim())) { //判断是否新增
                //验证配置文件名称&编码是否重复
                verifyProfileNumberRepeat(parameters.get("profileName").toString(), parameters.get("profileNumber").toString(), parameters.get("profileId"));
            }
            //保存配置文件
            profilesRow = saveProfiles(parameters);
            if (null != parameters.getJSONArray("profilevalues") && parameters.getJSONArray("profilevalues") instanceof JSONArray) {
                parameters.remove("profileId");
                parameters.put("profileId", profilesRow.getProfileId());
                //保存配置文件值
                saveProfileValues(parameters);
            }
            return SToolUtils.convertResultJSONObj("S", "保存配置文件值成功：", 1, profilesRow);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 编辑配置文件
     * @param parameters
     * @return
     */
    public SaafProfilesEntity_HI saveProfiles(JSONObject parameters) throws Exception {
        try {
            SaafProfilesEntity_HI row = null;
            int userId = SToolUtils.object2Int(parameters.get("varUserId"));
            if (null == parameters.get("profileId") || "".equals(SToolUtils.object2String(parameters.get("profileId")).trim())) { //判断是否新增
                row = new SaafProfilesEntity_HI();
                //                row.setCreatedBy(userId);
                //                row.setCreationDate(new Date());
            } else {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("profileId", SToolUtils.object2Int(parameters.get("profileId")));
                List list = saafProfilesDAO_HI.findByProperty(map);
                row = (SaafProfilesEntity_HI)list.get(0);
            }
            //            row.setLastUpdatedBy(userId);
            //            row.setLastUpdateDate(new Date());
            //            row.setLastUpdateLogin(userId);

            row.setOperatorUserId(userId);
            row.setPlatformCode(SToolUtils.object2String(parameters.get("varPlatformCode")));
            row.setProfileName(SToolUtils.object2String(parameters.get("profileName")));
            row.setProfileNumber(SToolUtils.object2String(parameters.get("profileNumber")));
            row.setProfileType(SToolUtils.object2String(parameters.get("profileType")));
            row.setDescription(SToolUtils.object2String(parameters.get("description")));
            row.setEnabledFlag(SToolUtils.object2String(parameters.get("enabledFlag")));
            row.setProfileValue(SToolUtils.object2String(parameters.get("profileValue")));
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
            saafProfilesDAO_HI.saveOrUpdate(row);
            return row;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 编辑配置文件值
     * @param parameters
     * @return
     */
    public List saveProfileValues(JSONObject parameters) throws Exception {
        int userId = SToolUtils.object2Int(parameters.get("varUserId"));
        SaafProfileValuesEntity_HI profileValuesRow = null;
        List profileValuesList = new ArrayList();
        JSONArray jsonArray = parameters.getJSONArray("profilevalues");
        try {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                if (null == json.get("profileValueId") || "".equals(json.getString("profileValueId"))) { //判断是否新增
                    profileValuesRow = new SaafProfileValuesEntity_HI();
                } else {
                    profileValuesRow = saafProfileValuesDAO_HI.findByProperty("profileValueId", Integer.parseInt(json.get("profileValueId").toString())).get(0);
                }
                profileValuesRow.setOperatorUserId(userId);
                profileValuesRow.setProfileId(SToolUtils.object2Int(parameters.get("profileId")));
                profileValuesRow.setPlatformCode(SToolUtils.object2String(json.get("varPlatformCode")));
                profileValuesRow.setProfileLevel(SToolUtils.object2String(json.get("profileLevel")));
                profileValuesRow.setProfileLevelId(SToolUtils.object2Int(json.get("profileLevelId")));
                profileValuesRow.setDescription(SToolUtils.object2String(json.get("description")));
                profileValuesRow.setEnabledFlag(SToolUtils.object2String(json.get("enabledFlag")));
                profileValuesRow.setProfileValue(SToolUtils.object2String(json.get("profileValue")));
                profileValuesList.add(profileValuesRow);
            }
            saafProfileValuesDAO_HI.saveOrUpdateAll(profileValuesList);
            return profileValuesList;
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    /**
     * 验证配置文件编码是否重复
     * @param profileName
     * @param profileNumber
     * @return
     */
    public boolean verifyProfileNumberRepeat(String profileName, String profileNumber, Object profileId) throws Exception {
        boolean returnStr = false;
        StringBuffer sql = new StringBuffer();
        sql.append(SaafProfilesEntity_HI_RO.QUERY_SQL);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("profileName", profileName);
        map.put("profileNumber", profileNumber);
        sql.append("  and ( profile_Name=:profileName   or  profile_Number=:profileNumber )  ");
        List list = saafProfilesDAO_HI_RO.findList(sql.toString(), map);
        if (list.size() >= 1) {
            Map proRow = null;
            for (int i = 0; i < list.size(); i++) {
                proRow = (Map)list.get(i);

                if (null != profileId &&
                    (!profileId.equals(proRow.get("profileId")) && (profileNumber.equals(proRow.get("profileNumber")) || profileName.equals(proRow.get("profileName"))))) {
                    returnStr = true;
                } else if (null == profileId && (profileNumber.equals(proRow.get("profileNumber")) || profileName.equals(proRow.get("profileName")))) {
                    returnStr = true;
                }
            }
        }
        if (returnStr) {
            throw new Exception("配置文件名称或编码不能重复");
        }
        return returnStr;
    }

    /**
     * 验证配置文件值级别是否重复
     * @param parameters
     * @return
     */
    public boolean varidateParameter(JSONObject parameters) throws Exception {
        boolean returnStr = false;
        String levelStr = null;
        String levelCon = null;
        JSONArray valuesArray = parameters.parseArray("profilevalues");
        for (int i = 0; i < valuesArray.size(); i++) {
            JSONObject valuesJson = valuesArray.getJSONObject(i);
            if ((null != valuesJson.get("profileLevel") && !"".equals(valuesJson.getString("profileLevel"))) &&
                (null != valuesJson.get("profileLevelId") && !"".equals(valuesJson.getString("profileLevelId")))) {
                String Level = valuesJson.get("profileLevel").toString();
                String LevelId = valuesJson.get("profileLevelId").toString();
                levelStr = Level + LevelId;
                if (levelCon == null) {
                    levelCon = levelStr;
                } else if (levelCon.contains(levelStr)) {
                    returnStr = true;
                }
            }
        }
        if (returnStr) {
            throw new Exception("配置文件值层级不能重复");
        }
        return returnStr;
    }
}
