package saaf.common.fmw.base.model.inter.server;

import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafUsers2;
import saaf.common.fmw.utils.SHA1Util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
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
@Component("srmUsersServer2")
public class SaafUsersServer2 implements ISaafUsers2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafUsersServer2.class);
    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;  //用户DAO
    public SaafUsersServer2() {
        super();
    }

    /**
     * 用户登录
     * @param map
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public SaafUsersEntity_HI findUserLogin(Map<String, Object> map) {
        StringBuffer querySQL = new StringBuffer(" from SaafUsersEntity_HI ");
        querySQL.append(" where upper(userName) = upper(:userName)");
        querySQL.append(" and encryptedPassword = :encryptedPassword");
        querySQL.append(" and sysdate() >= startDateActive and sysdate() < ifnull(endDateActive,sysdate() + 1)");
        SaafUsersEntity_HI userEntity = saafUsersDAO_HI.get(querySQL, map);
        return userEntity;
    }

    /**
     * 数据库用户登录验证方法
     * @param userName
     * @param password
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public boolean userLoginByDatabase(String userName, String password) {
        SHA1Util shal = new SHA1Util();
        Map<String, Object> map = new HashMap<String, Object>();
        String encryptedPassword ="";
        try{
            encryptedPassword = shal.getEncrypt(password); //密码加密
        }catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        }
        map.put("userName", userName);
        map.put("encryptedPassword", encryptedPassword);
        SaafUsersEntity_HI userEntity = findUserLogin(map);
        if(null != userEntity){
           return true; //登录成功
        }
        return false;
    }

    /**
     * 根据userName和password获取用户信息
     * @param userName
     * @param password
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public SaafUsersEntity_HI userByDatabase(String userName, String password) {
        SHA1Util shal = new SHA1Util();
        Map<String, Object> map = new HashMap<String, Object>();
        String encryptedPassword ="";
        try{
            encryptedPassword = shal.getEncrypt(password); //密码加密
        }catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        }
        map.put("userName", userName);
        map.put("encryptedPassword", encryptedPassword);
        SaafUsersEntity_HI userEntity = findUserLogin(map);
        if(null != userEntity){
           return userEntity; //存在该用户
        }
        return null;
    }

}

