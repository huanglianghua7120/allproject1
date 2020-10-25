package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;

import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafUsers
 * Description：用来处理用户维护
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISaafUsers {

    /**
     * 用户登录
     * @param map
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SaafUsersEntity_HI findUserLogin(Map<String, Object> map);
    /**
     * Description：数据库用户登录验证方法
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * manualFlag  是否手动创建员工(Y/N)  VARCHAR2  N
     * supplierPrimaryFlag  供应商主账号标识（Y/N）  VARCHAR2  N
     * onlineStatus  在线状态  VARCHAR2  N
     * userId    NUMBER  Y
     * employeeId    NUMBER  N
     * memberId    NUMBER  N
     * isadmin    VARCHAR2  Y
     * userName    VARCHAR2  Y
     * userFullName    VARCHAR2  N
     * encryptedPassword    VARCHAR2  Y
     * platformCode    VARCHAR2  Y
     * startDateActive    DATE  N
     * endDateActive    DATE  N
     * supplierId  供应商id  NUMBER  N
     * agreeLegal  是否同意法律声明  VARCHAR2  N
     * employeeDutys  招投标协作小组人员职责筛选  VARCHAR2  N
     * userFileId    NUMBER  N
     * sourceId  数据来源ID  VARCHAR2  N
     * sourceCode  数据来源  VARCHAR2  N
     * userId    NUMBER  Y
     * employeeId    NUMBER  N
     * memberId    NUMBER  N
     * isadmin    VARCHAR2  Y
     * userName    VARCHAR2  Y
     * userFullName    VARCHAR2  N
     * encryptedPassword    VARCHAR2  Y
     * platformCode    VARCHAR2  Y
     * startDateActive    DATE  N
     * endDateActive    DATE  N
     * supplierId  供应商ID  NUMBER  N
     * supplierPrimaryFlag  供应商主账号标识（Y/N）  VARCHAR2  N
     * userFileId  用户头像ID  NUMBER  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    public boolean userLoginByDatabase(String userName,String password);
    /**
     * 根据userName和password获取用户信息
     * @param userName
     * @param password
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public SaafUsersEntity_HI userByDatabase(String userName,String password);
    /**
     * 保存供应商对应的用户信息（档案自助维护/内部创建供应商）
     * @param saafUser
     * @param userId
     * @param supplierId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public boolean  saveSaafUser(SaafUsersEntity_HI saafUser, Integer userId, Integer supplierId);
    /**
     * 查询用户列表
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination findSaafUsersList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;


}
