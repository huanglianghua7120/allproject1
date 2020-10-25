package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;

import java.util.Map;
/**
 * Project Name：ISaafUsers
 * Company Name：SIE
 * Program Name：
 * Description：用户信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
public interface ISaafUsers {

    /**
     * Description：用户登录
     * @param map 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public SaafUsersEntity_HI findUserLogin(Map<String, Object> map);

    /**
     * Description：数据库用户登录验证方法
     * @param userName 用户名称
     * @param password 用户密码
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public boolean userLoginByDatabase(String userName, String password);

    /**
     * Description：根据userName和password获取用户信息
     * @param userName 用户名称
     * @param password 用户密码
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    public SaafUsersEntity_HI userByDatabase(String userName, String password);
    /**
     * 保存供应商对应的用户信息（档案自助维护/内部创建供应商）
     * @param saafUser
     * @param userId
     * @param supplierId
     * @return
     */
    //public boolean  saveSaafUser(SaafUsersEntity_HI saafUser, Integer userId, Integer supplierId);

    /**
     * Description：查询用户列表
     * @param parameters 查询条件
     * @param pageIndex 页码
     * @param pageIndex 行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
    Pagination findSaafUsersList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;


}
