package saaf.common.fmw.base.model.inter.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.base.utils.DateUtil;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUserRespEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafUserEmployeesEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafUserMainRespEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafUsers;
import saaf.common.fmw.base.utils.Base64Utils;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.model.inter.server.ShortDescMessageServer;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.utils.SHA1Util;
import saaf.common.fmw.utils.SrmUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：SaafUsersServer.java
 * Description：用来处理用户维护
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
@Component("baseSaafUsersServer")
public class SaafUsersServer implements ISaafUsers {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaafUsersServer.class);

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ShortDescMessageServer shortDescMessageServer;

    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;

    @Autowired
    private ViewObject<SaafEmployeesEntity_HI> saafEmployeesDAO_HI;

    @Autowired
    private BaseViewObject<SaafUserEmployeesEntity_HI_RO> saafUserEmployeesDAO_HI_RO;

    @Autowired
    private ViewObject<SaafUserRespEntity_HI> saafUserRespDAO_HI;

    @Autowired
    private BaseViewObject<SaafUserMainRespEntity_HI_RO> saafUserMainRespDAO_HI_RO;
    private SHA1Util sha1 = new SHA1Util();

    public SaafUsersServer() {
        super();
    }

    /**
     * 用户登录
     *
     * @param map
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public SaafUsersEntity_HI findUserLogin(Map<String, Object> map) throws Exception {
        StringBuffer querySQL = new StringBuffer(" from SaafUsersEntity_HI ");
        querySQL.append(" WHERE upper(userName) = upper(:userName)");
        querySQL.append(" AND encryptedPassword = :encryptedPassword");
        querySQL.append(" AND trunc(startDateActive) <= trunc(SYSDATE) AND trunc(nvl(endDateActive, SYSDATE)) >= trunc(SYSDATE)");
        SaafUsersEntity_HI saafUsersEntity_HI = saafUsersDAO_HI.get(querySQL, map);
        return saafUsersEntity_HI;
    }

    /**
     * 用户登录:查询员工名称
     *
     * @param map
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public SaafEmployeesEntity_HI findEmployees(Map<String, Object> map) throws Exception {
        SaafEmployeesEntity_HI saafEmployeesEntity_HI = saafEmployeesDAO_HI.get(" from SaafEmployeesEntity_HI where userId = :userId ", map);
        return saafEmployeesEntity_HI;
    }

    /**
     * Description：获取用户信息不验证密码
     *
     * @param map
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public SaafUsersEntity_HI findUserLoginNopossword(Map<String, Object> map) throws Exception {
        String nowDate = DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss");
        StringBuffer querySQL = new StringBuffer(" from SaafUsersEntity_HI ");
        querySQL.append(" where upper(userName) = upper(:userName)");
        querySQL.append(" and to_date('" + nowDate + "','yyyy-mm-dd hh24:mi:ss') >= startDateActive ");
        querySQL.append(" and (to_date('" + nowDate + "','yyyy-mm-dd hh24:mi:ss') < endDateActive OR endDateActive is null)");
        SaafUsersEntity_HI saafUsersEntity_HI = saafUsersDAO_HI.get(querySQL, map);
        return saafUsersEntity_HI;
    }


    /**
     * 用户登录:查询员工名称
     *
     * @param map
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public SaafEmployeesEntity_HI findEmployeesByEmpId(Map<String, Object> map) throws Exception {
        SaafEmployeesEntity_HI saafEmployeesEntity_HI = saafEmployeesDAO_HI.get(" from SaafEmployeesEntity_HI where employeeId = :employeeId ", map);
        return saafEmployeesEntity_HI;
    }

    /**
     * 查询用户列表
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public Pagination findSaafUsersList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SaafUserEmployeesEntity_HI_RO.QUERY_USERLIST_SQL);
            //StringBuffer countString = new StringBuffer(SaafUserEmployeesEntity_HI_RO.COUNT_USERLIST_SQL);
            StringBuffer filterString = new StringBuffer();
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "su.supplier_id", "supplierId", filterString, map, "=");
            SaafToolUtils.parperParam(parameters, "su.user_name", "varInputUserName", filterString, map, "like");
            SaafToolUtils.parperParam(parameters, "su.user_full_name", "varUserFullName", filterString, map, "like");
            SaafToolUtils.parperParam(parameters, "su.sex", "varUserSex", filterString, map, "like");
            //平台
            SaafToolUtils.parperParam(parameters, "su.platform_code", "var_equal_platformCode", filterString, map, "=");
            //新增查询过滤条件
            SaafToolUtils.parperParam(parameters, "se.employee_number", "employeeNumber", filterString, map, "like");
            SaafToolUtils.parperParam(parameters, "se.employee_name", "employeeName", filterString, map, "like");
            SaafToolUtils.parperParam(parameters, "psi.supplier_name", "supplierName", filterString, map, "like");

            queryString.append(filterString);
            //countString.append(filterString);
            //解决分页bug
            //Pagination<SaafUserEmployeesEntity_HI_RO> userRowSet = saafUserEmployeesDAO_HI_RO.findPagination(queryString, countString, map, pageIndex, pageRows);
            String countSql = "select count(1) from (" + queryString + ")";
            Pagination<SaafUserEmployeesEntity_HI_RO> userRowSet = saafUserEmployeesDAO_HI_RO.
                    findPagination(queryString,countSql, map, pageIndex, pageRows);
            return userRowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询用户名称（LOV）
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public String findSaafUsersLov(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SaafUserEmployeesEntity_HI_RO.QUERY_USERS_LOV);
            Map<String, Object> map = new HashMap<String, Object>();
            //用户名
            SaafToolUtils.parperParam(parameters, "su.USER_NAME", "userName", queryString, map, "like");
            //业务员名称
            SaafToolUtils.parperParam(parameters, "emp.employee_name", "employeeName", queryString, map, "like");
            //来源平台
            SaafToolUtils.parperParam(parameters, "su.platform_code", "platformCode", queryString, map, "like");
            String countSql = "select count(1) from (" + queryString + ")";
            //排序
            queryString.append(" order by su.user_name");
            Pagination<SaafUserEmployeesEntity_HI_RO> rowSet = saafUserEmployeesDAO_HI_RO
                    .findPagination(queryString,countSql, map, pageIndex, pageRows);
            return JSON.toJSONString(rowSet);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询用户员工信息
     *
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public SaafUserEmployeesEntity_HI_RO findSaafUsersById(JSONObject parameters) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SaafUserEmployeesEntity_HI_RO.QUERY_USERLIST_SQL);
            queryString.append(" AND su.user_id = :userId");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", parameters.getInteger("userId"));
            SaafUserEmployeesEntity_HI_RO saafUserEmployeesEntity_HI_RO = saafUserEmployeesDAO_HI_RO.findList(queryString, map).get(0);
            return saafUserEmployeesEntity_HI_RO;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 新增用户信息
     *
     * @param parameters,
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public JSONObject saveSaafUsersInfo(JSONObject parameters) throws Exception {
        try {
            LOGGER.info("-----parameters----------" + parameters.toString());
            //验证用户名称是否重复
            findExistsUserName(SToolUtils.object2String(parameters.get("userName")), parameters.get("userId"));
            //验证员工编号是否重复  //modify by huqitao
            if(ObjectUtils.isEmpty(parameters.getInteger("employeeId"))&&"SAAF".equals(parameters.getString("platformCode"))){
                findExistsEmployeeNum(parameters.getString("employeeNumber").trim(), parameters.get("employeeId"));
            }
            //验证员工姓名是否重复
            // findExistsEmployeeName(parameters.getString("employeeName").trim(), parameters.get("employeeId"));
            //保存Saaf_users
            SaafUsersEntity_HI usersRow = saveSaafUsers(parameters);
            saafUsersDAO_HI.saveOrUpdate(usersRow);
            if ("SAAF".equals(parameters.getString("platformCode")) && "Y".equals(parameters.getString("empMaintainFlag"))) {
                //保存SAAF_Employees
                parameters.put("userId", usersRow.getUserId());
                parameters.put("accountNumber", usersRow.getUserName());
                SaafEmployeesEntity_HI emplRow = saveEpmloyees(parameters);
                //修改EmployeeId
                usersRow.setEmployeeId(emplRow.getEmployeeId());
                saafUsersDAO_HI.update(usersRow);
            }
            return SToolUtils.convertResultJSONObj("S", "保存成功", 1, usersRow);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return SToolUtils.convertResultJSONObj("E", e.getMessage(), 1, null);
        }
    }

    /**
     * 新增用户信息
     *
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public SaafUsersEntity_HI saveSaafUsers(JSONObject parameters) throws Exception {
        LOGGER.info("-----parameters----saveSaafUsers------" + parameters);
        SaafUsersEntity_HI usersRow = null;
        String encryptedPassword = null;
        try {
            Integer varUserId = parameters.getInteger("varUserId");
            //新增
            if (null != parameters.get("userId") && !"".equals(parameters.getString("userId"))) {
                usersRow = saafUsersDAO_HI.findByProperty("userId", parameters.getInteger("userId")).get(0);
            } else {
                usersRow = new SaafUsersEntity_HI();
            }
            usersRow.setOperatorUserId(varUserId);
            usersRow.setUserName(parameters.getString("userName"));
            if(!ObjectUtils.isEmpty(parameters.getString("userFullName"))){
                usersRow.setUserFullName(parameters.getString("userFullName"));
            }else{
                usersRow.setUserFullName(parameters.getString("userName"));
            }


            usersRow.setPlatformCode(parameters.getString("platformCode"));
            usersRow.setSupplierId(parameters.getInteger("supplierId"));
            //默认供应商账号，不是供应商主账号
            if (parameters.getInteger("supplierId") != null) {
                usersRow.setSupplierPrimaryFlag("N");
            }
            usersRow.setEmployeeId(parameters.getInteger("employeeId"));
            usersRow.setUserFileId(parameters.getInteger("userFileId"));
            if(null != parameters.getString("password") && !"".equals(parameters.getString("password"))) {
                //说明要修改密码
                String password = Base64Utils.decode2PreToString(parameters.getString("userName"),parameters.getString("password"));
                encryptedPassword = sha1.getEncrypt(password);
                usersRow.setEncryptedPassword(encryptedPassword);
            }
            String startDateActive_ = null;
            if (null != parameters.get("startDateActive") && !"".equals(parameters.getString("startDateActive").trim())) {
                startDateActive_ = parameters.getString("startDateActive");
                Date startDateActive = SToolUtils.string2DateTime(startDateActive_, "yyyy-MM-dd");
                usersRow.setStartDateActive(startDateActive);
            }
            String endDateActive_ = null;
            if (null != parameters.get("endDateActive") && !"".equals(parameters.getString("endDateActive").trim())) {
                endDateActive_ = parameters.getString("endDateActive");
                Date endDateActive = SToolUtils.string2DateTime(endDateActive_, "yyyy-MM-dd");
                usersRow.setEndDateActive(endDateActive);
            } else {
                usersRow.setEndDateActive(null);
            }
            if (null != parameters.get("isAdmin") && !"".equals(parameters.getString("isAdmin"))) {
                usersRow.setIsadmin(parameters.getString("isAdmin"));
            } else {
                usersRow.setIsadmin("N");
            }
            return usersRow;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 修改用户密码
     *
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public String updateUserPassword(JSONObject parameters) throws Exception {
        LOGGER.info("-----parameters----updateUserPassword------" + parameters);
        Integer varUserId = parameters.getInteger("varUserId");
        String varUserName = parameters.getString("varUserName");
        try {
            //新增
            SaafUsersEntity_HI usersRow = saafUsersDAO_HI.getById(varUserId);
            if (usersRow == null) {
                return SToolUtils.convertResultJSONObj("E", "修改失败，该用户不存在", 0, null).toString();
            }
            usersRow.setOperatorUserId(varUserId);
            String oldPassword = Base64Utils.decode2PreToString(varUserName,parameters.getString("oldPassword"));
            String newPassword = Base64Utils.decode2PreToString(varUserName,parameters.getString("newPassword"));
            //验证输入的旧密码是否正确
            if (usersRow.getEncryptedPassword() == null || usersRow.getEncryptedPassword().equals("")) {
                //如果不存在旧密码，则默认验证成功
            } else {
                String oldEncryptedPassword = sha1.getEncrypt(oldPassword);
                if (!oldEncryptedPassword.equals(usersRow.getEncryptedPassword())) {
                    return SToolUtils.convertResultJSONObj("E", "验证旧密码失败", 0, null).toString();
                }
            }
            //设置新密码
            String newEncryptedPassword = sha1.getEncrypt(newPassword);
            LOGGER.info("*******************修改用户密码， 新密码（加密后）>" + newEncryptedPassword + "<");
            usersRow.setEncryptedPassword(newEncryptedPassword);
            saafUsersDAO_HI.update(usersRow);
            return SToolUtils.convertResultJSONObj("S", "修改成功", 0, null).toString();
        } catch (Exception e) {
            LOGGER.error("修改用户密码失败， " + e);
            throw e;
        }
    }

    /**
     * 保存员工信息表
     *
     * @param parameters
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public SaafEmployeesEntity_HI saveEpmloyees(JSONObject parameters) throws Exception {
        SaafEmployeesEntity_HI emplRow = null;
        Integer varUserId = parameters.getInteger("varUserId");
        try {
            if (null != parameters.get("employeeId") && !"".equals(parameters.getString("employeeId").trim())) {
                emplRow = saafEmployeesDAO_HI.findByProperty("employeeId", parameters.getInteger("employeeId")).get(0);
            } else {
                emplRow = new SaafEmployeesEntity_HI();
                //update by liuwenjun 20170214
                //String employeeNumber = saafSequencesUtil.getDocSequences("SAAF_EMPLOYEE", "YG", 8); //因为报错，默认写死
                //emplRow.setEmployeeNumber(employeeNumber);
                emplRow.setEmployeeNumber(SToolUtils.object2String(parameters.get("employeeNumber")));
            }
            emplRow.setOperatorUserId(varUserId);
            emplRow.setUserId(SToolUtils.object2Int(parameters.get("userId")));
            emplRow.setAccountNumber(SToolUtils.object2String(parameters.get("accountNumber")));
            emplRow.setEmployeeName(SToolUtils.object2String(parameters.get("employeeName")));
            emplRow.setPlatformCode(SToolUtils.object2String(parameters.get("varPlatformCode")));
            emplRow.setSex(SToolUtils.object2String(parameters.get("sex")));
            emplRow.setQuartersCode(parameters.getString("quartersCode"));
            if (null != parameters.get("parentsQuartersCode") && !"".equals(parameters.get("parentsQuartersCode").toString())) {
                emplRow.setParentsQuartersCode(SToolUtils.object2String(parameters.get("parentsQuartersCode")));
            }
            LOGGER.info("dy parameters.get(\"birthDay\"):" + parameters.get("birthDay"));
            String birthDay_ = SToolUtils.object2String(parameters.get("birthDay"));
            if (null != parameters.get("birthDay") && !"".equals(birthDay_.trim())) {
                LOGGER.info("dy birthDay_:" + birthDay_);
                Date birthDay = SToolUtils.string2DateTime(birthDay_, "yyyy-MM-dd");
                emplRow.setBirthDay(birthDay);
            }
            emplRow.setCardNo(SToolUtils.object2String(parameters.get("cardNo")));

            if (null != parameters.get("instId") && !"".equals(parameters.get("instId").toString())) {
                Integer bdInstId = SToolUtils.object2Int(parameters.get("instId"));
                emplRow.setInstId(bdInstId);
            }
            if (null != parameters.get("instName") && !"".equals(parameters.get("instName").toString())) {
                emplRow.setInstName(SToolUtils.object2String(parameters.get("instName")));
            }
            if (null != parameters.get("instCode") && !"".equals(parameters.get("instCode").toString())) {
                emplRow.setInstCode(SToolUtils.object2String(parameters.get("instCode")));
            }
            if (null != parameters.get("positionId") && !"".equals(parameters.getString("positionId"))) {
                Integer bdPositionId = parameters.getInteger("positionId");
                emplRow.setPositionId(bdPositionId);
            }
            if (null != parameters.get("positionName") && !"".equals(parameters.getString("positionName"))) {
                emplRow.setPositionName(SToolUtils.object2String(parameters.get("positionName")));
            }

            if (null != parameters.get("positionInstId") && !"".equals(parameters.getString("positionInstId"))) {
                emplRow.setPositionInstId(SToolUtils.object2Int(parameters.get("positionInstId")));
            }
            emplRow.setTelPhone(SToolUtils.object2String(parameters.get("telPhone")));
            emplRow.setMobilePhone(SToolUtils.object2String(parameters.get("mobilePhone")));
            emplRow.setEmail(SToolUtils.object2String(parameters.get("email")));
            emplRow.setPostalAddress(SToolUtils.object2String(parameters.get("postalAddress")));
            if (null != parameters.get("postcode") || !"".equals(parameters.getString("postcode"))) {
                emplRow.setPostcode(SToolUtils.object2String(parameters.get("postcode")));
            }

            saafEmployeesDAO_HI.saveOrUpdate(emplRow);
            return emplRow;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 编辑或创建用户职责
     *
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public List saveSaafUserResp(JSONObject parameters) throws Exception {
        SaafUserRespEntity_HI row = null;
        List userRespList = new ArrayList();
        //获取页面数据
        int varUserId = SToolUtils.object2Int(parameters.get("varUserId"));
        try {
            //            BigDecimal varUserId =  new BigDecimal(parameters.getString("varUserId"));
            System.out.println("---------111---saveSaafUserResp---------" + parameters.toString());
            JSONArray valuesArray = parameters.getJSONArray("userRespData");
            for (int i = 0; i < valuesArray.size(); i++) {
                JSONObject valuesJson = valuesArray.getJSONObject(i);
                //判断是新增还是修改
                if (null == valuesJson.get("userRespId") || "".equals(SToolUtils.object2String(valuesJson.get("userRespId")).trim())) {
                    row = new SaafUserRespEntity_HI();
                } else {
                    row = saafUserRespDAO_HI.findByProperty("userRespId", SToolUtils.object2Int(valuesJson.get("userRespId"))).get(0);
                }
                row.setOperatorUserId(varUserId);
                row.setPlatformCode(SToolUtils.object2String(parameters.get("varPlatformCode")));
                row.setUserId(SToolUtils.object2Int(parameters.get("userId")));
                row.setResponsibilityId(SToolUtils.object2Int(valuesJson.get("responsibilityId")));
                userRespList.add(row);
            }
            saafUserRespDAO_HI.saveOrUpdateAll(userRespList);
            return userRespList;
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    /**
     * 验证字段：userName是否重复
     *
     * @param userName
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public boolean findExistsUserName(String userName, Object userId) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userName", userName);
            List<SaafUsersEntity_HI> rowSet = saafUsersDAO_HI.findByProperty(map);
            LOGGER.info("********用户信息维护，参数，rowSet=" + JSONObject.toJSONString(rowSet) + ", userName" + userName + ", userId=" + userId);
            if (rowSet != null && rowSet.size() > 0) {
                for (SaafUsersEntity_HI userRow : rowSet) {
                    if (userName.equals(userRow.getUserName()) && (userId == null || userRow.getUserId() != SToolUtils.object2Int(userId))) {
                        throw new Exception("用户账号有重复,不允许保存请检查：" + userName);
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return false;
    }

    /**
     * 验证字段：EMPLOYEE_NAME是否重复
     *
     * @param employeeName
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public boolean findExistsEmployeeName(String employeeName, Object employeeId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnResult = false;
        try {
            map.put("employeeName", employeeName);
            List rowSet = saafEmployeesDAO_HI.findByProperty(map);
            if (rowSet.size() > 0) {
                SaafEmployeesEntity_HI empRow = null;
                for (int i = 0; i < rowSet.size(); i++) {
                    empRow = (SaafEmployeesEntity_HI) rowSet.get(i);
                    if ((null == employeeId && empRow.getEmployeeName().equals(employeeName)) ||
                            (null != employeeId && (empRow.getEmployeeId() != employeeId && empRow.getEmployeeName().equals(employeeName)))) {
                        returnResult = true;
                    }
                }
            } else {
                returnResult = false;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (returnResult) {
            throw new Exception("员工姓名有重复,不允许保存请检查：" + employeeName);
        }
        return returnResult;
    }

    /**
     * 验证字段：EMPLOYEE_NUMBER是否重复
     *
     * @param employeeNumber
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public boolean findExistsEmployeeNum(String employeeNumber, Object employeeId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnResult = false;
        try {
            map.put("employeeNumber", employeeNumber);
            List rowSet = saafEmployeesDAO_HI.findByProperty(map);
            if (rowSet.size() > 0) {
                SaafEmployeesEntity_HI empRow = null;
                for (int i = 0; i < rowSet.size(); i++) {
                    empRow = (SaafEmployeesEntity_HI) rowSet.get(i);
                    if ((null == employeeId && empRow.getEmployeeNumber().equals(employeeNumber)) ||
                            (null != employeeId && (!empRow.getEmployeeId().equals(employeeId) && empRow.getEmployeeNumber().equals(employeeNumber)))) {
                        returnResult = true;
                    }
                }
            } else {
                returnResult = false;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (returnResult) {
            //throw new Exception("员工编号有重复,不允许保存请检查：" + employeeNumber);
            throw new UtilsException("员工编号有重复,不允许保存请检查：" + employeeNumber);
        }
        return returnResult;
    }

    /**
     * 验证字段：userRespName是否重复
     *
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public boolean findExisUserRespName(JSONObject parameters) throws Exception {
        boolean returnResult = false;
        try {
            String userRespId = null;
            String userRespIdContains = null;
            String userRespIdDBList = null;
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("platformCode", parameters.getString("varPlatformCode"));
            map.put("userId", parameters.getString("userId"));
            StringBuffer queryString = new StringBuffer();
            queryString.append(SaafUserMainRespEntity_HI_RO.QUERY_SQL);
            queryString.append(" and s.platform_code = :platformCode");
            queryString.append(" and s.user_id = :userId");
            List userRespList = saafUserMainRespDAO_HI_RO.findList(queryString.toString(), map);
            for (int k = 0; k < userRespList.size(); k++) {
                SaafUserMainRespEntity_HI_RO row = (SaafUserMainRespEntity_HI_RO) userRespList.get(k);
                if (userRespIdDBList == null) {
                    userRespIdDBList = "#" + row.getResponsibilityId() + "#";
                } else {
                    userRespIdDBList = userRespIdDBList + "#" + row.getResponsibilityId() + "#";
                }
            }
            //验证重复
            JSONArray valuesArray = parameters.getJSONArray("userRespData");
            for (int i = 0; i < valuesArray.size(); i++) {
                JSONObject valuesJson = valuesArray.getJSONObject(i);
                if (valuesJson.getString("userRespId") == null || "".equals(valuesJson.getString("userRespId"))) {
                    userRespId = "#" + valuesJson.getString("responsibilityId") + "#";
                    if (userRespIdContains == null) {
                        userRespIdContains = userRespId;
                    } else {
                        //验证提交数据是否重复
                        if (userRespIdContains.contains(userRespId)) {
                            returnResult = true;
                        } else {
                            userRespIdContains = userRespIdContains + userRespId;
                        }
                    }
                    //验证提交数据在数据库数据是否重复
                    if (userRespIdDBList != null && userRespIdDBList.contains(userRespId)) {
                        returnResult = true;
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (returnResult) {
            throw new Exception("用户职责有重复,不允许保存请检查");
        }
        return returnResult;
    }

    /**
     * LOV:员工信息
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public Pagination findEmpInfo(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer hql = new StringBuffer();
            hql.append(SaafUserEmployeesEntity_HI_RO.QUERY_EMP_LOV);
            Map<String, Object> map = new HashMap<String, Object>();
            if (!SrmUtils.isNvl(parameters.getString("varEmpNumber"))) {
                hql.append(" AND emp.employee_number LIKE :varEmpNumber");
                map.put("varEmpNumber", "%" + parameters.getString("varEmpNumber") + "%");
            }
            if (!SrmUtils.isNvl(parameters.getString("varEmpName"))) {
                hql.append(" AND emp.employee_Name LIKE :varEmpName");
                map.put("varEmpName", "%" + parameters.getString("varEmpName") + "%");
            }
            String countSql = "select count(1) from (" + hql + ")";
            Pagination<SaafUserEmployeesEntity_HI_RO> rowSet = saafUserEmployeesDAO_HI_RO.findPagination(hql, countSql,map, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询人员lov  查询字段以及条件employeeNumber,employeeName,positionName
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    @Override
    public Pagination<SaafUserEmployeesEntity_HI_RO> findEmployeesLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap();
        if (pageIndex == null || "".equals(pageIndex) || pageRows == null || "".equals(pageRows)) {
            pageIndex = 1;
            pageRows = 10;
        }
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SaafUserEmployeesEntity_HI_RO.QUERY_EMP_LOV);
            Integer instId = jsonParams.getInteger("instId");
            if (instId != null) {
                queryString.append(
                        " AND EXISTS\n" +
                                "(SELECT\n" +
                                "  1\n" +
                                "FROM\n" +
                                "  saaf_user_access_orgs sua\n" +
                                "WHERE sua.user_id = su.user_id\n" +
                                "  AND sua.inst_id = " + instId + ")");
            }
            SaafToolUtils.parperParam(jsonParams, "emp.employee_number", "employeeNumber", queryString, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(jsonParams, "emp.employee_name", "employeeName", queryString, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(jsonParams, "emp.position_name", "positionName", queryString, queryParamMap, "LIKE");
            if (jsonParams.getString("flag") != null && "flag".equals(jsonParams.getString("flag"))) {
                SaafToolUtils.parperParam(jsonParams, "su.user_id", "operatorUserId", queryString, queryParamMap, "=");
            }
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" order by emp.last_Update_Date desc");
            Pagination<SaafUserEmployeesEntity_HI_RO> result = saafUserEmployeesDAO_HI_RO.findPagination
                    (queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 創建者lov  查询字段及条件saaf_users.userName,saaf_employees.employeeName
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    @Override
    public Pagination<SaafUserEmployeesEntity_HI_RO> findCreatorLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SaafUserEmployeesEntity_HI_RO.QUERY_USERS_LOV);
            SaafToolUtils.parperParam(jsonParams, "su.user_name", "userName", queryString, queryParamMap, "like");
            SaafToolUtils.parperParam(jsonParams, "emp.employee_name", "employeeName", queryString, queryParamMap, "like");
            String countSql = "select count(1) from (" + queryString + ")";
            Pagination<SaafUserEmployeesEntity_HI_RO> result =
                    saafUserEmployeesDAO_HI_RO.findPagination(queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    /**
     * 查询所有用户的基本信息，转为接口提供
     *
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public JSONObject findAllUser() throws Exception {
        List userRespList = saafUserEmployeesDAO_HI_RO.findList(SaafUserEmployeesEntity_HI_RO.QUERY_All_USERS, new HashMap<String, Object>());
        return SToolUtils.convertResultJSONObj("S", shortDescMessageServer.getMessage("QUERY-SUCCESS"), userRespList.size(), userRespList);
    }
}
