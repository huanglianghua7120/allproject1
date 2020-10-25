package saaf.common.fmw.base.model.inter;

import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;

import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：用户
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISaafUsers2 {

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
     * 数据库用户登录验证方法
     * @param userName
     * @param password
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public boolean userLoginByDatabase(String userName, String password);
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
    public SaafUsersEntity_HI userByDatabase(String userName, String password);

}
