package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import java.util.Map;

import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafUserEmployeesEntity_HI_RO;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafUsers.java
 * Description：用来处理用户维护
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface ISaafUsers {
    /**
     * 創建者lov  查询字段及条件saaf_users.userName,saaf_employees.employeeName
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination<SaafUserEmployeesEntity_HI_RO> findCreatorLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询人员lov  查询字段以及条件employeeNumber,employeeName,positionName
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
     Pagination<SaafUserEmployeesEntity_HI_RO> findEmployeesLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     *修改用户密码
     * @param parameters
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    String updateUserPassword(JSONObject parameters) throws Exception;


    /**
     * Description：用户登录
     * @param map
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    SaafUsersEntity_HI findUserLoginNopossword(Map<String, Object> map) throws Exception;

    /**
     * 用户登录
     * @param map
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    SaafUsersEntity_HI findUserLogin(Map<String, Object> map) throws Exception;

    /**
     * 用户登录:查询员工名称
     * @param map
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public SaafEmployeesEntity_HI findEmployees(Map<String, Object> map) throws Exception;

    /**
     * 用户登录:查询员工名称
     * @param map
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public SaafEmployeesEntity_HI findEmployeesByEmpId(Map<String, Object> map) throws Exception;
    /**
     * 查询用户列表
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findSaafUsersList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询用户名称（LOV）
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    String findSaafUsersLov(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询用户员工信息
     * @param parameters
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    SaafUserEmployeesEntity_HI_RO findSaafUsersById(JSONObject parameters) throws Exception;

    /**
     * 保存用户信息
     * @param parameters
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject saveSaafUsersInfo(JSONObject parameters) throws Exception;

    /**
     * 新增用户信息：SaafUsersEntity_HI
     * @param parameters
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    SaafUsersEntity_HI saveSaafUsers(JSONObject parameters) throws Exception;

    /**
     * 保存员工信息表：SaafEmployeesEntity_HI
     * @param parameters
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    SaafEmployeesEntity_HI saveEpmloyees(JSONObject parameters) throws Exception;

    /**
     * 验证字段：userName是否重复
     * @param userName
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    boolean findExistsUserName(String userName, Object userId) throws Exception;

    /**
     * 验证字段：EMPLOYEE_NAME是否重复
     * @param employeeName
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    boolean findExistsEmployeeName(String employeeName, Object employeeId) throws Exception;

    /**
     * 验证字段：EMPLOYEE_NUMBER是否重复
     * @param employeeNumber
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    boolean findExistsEmployeeNum(String employeeNumber, Object employeeId) throws Exception;

    /**
     * LOV:员工信息
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public Pagination findEmpInfo(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;
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
    public JSONObject findAllUser() throws Exception;

}
