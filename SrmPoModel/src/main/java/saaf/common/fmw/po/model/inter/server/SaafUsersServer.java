package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafUserResp;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierContactsEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SaafUserEmployees2Entity_HI_RO;
import saaf.common.fmw.po.model.inter.ISaafUsers;
import saaf.common.fmw.utils.SHA1Util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Project Name：SaafUsersServer
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

@Component("srmUsersServer3")
public class SaafUsersServer implements ISaafUsers {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaafUsersServer.class);

    private BaseViewObject<SaafUserEmployees2Entity_HI_RO> saafUserEmployeesDAO_HI_RO;

    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;  //用户DAO

    @Autowired
    private ISaafUserResp iSaafUserResp;  //用户职责

    public SaafUsersServer() {
        super();
    }


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
    @Override
    public SaafUsersEntity_HI findUserLogin(Map<String, Object> map) {
        StringBuffer querySQL = new StringBuffer(" FROM SaafUsersEntity_HI ");
        querySQL.append(" WHERE upper(userName) = upper(:userName)");
        querySQL.append(" AND encryptedPassword = :encryptedPassword");
        querySQL.append(" AND sysdate() >= startDateActive AND sysdate() < ifnull(endDateActive,sysdate() + 1)");
        SaafUsersEntity_HI userEntity = saafUsersDAO_HI.get(querySQL, map);
        return userEntity;
    }

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
    @Override
    public boolean userLoginByDatabase(String userName, String password) {
        SHA1Util shal = new SHA1Util();
        Map<String, Object> map = new HashMap<String, Object>();
        String encryptedPassword = "";
        try {
            encryptedPassword = shal.getEncrypt(password); //密码加密
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        }
        map.put("userName", userName);
        map.put("encryptedPassword", encryptedPassword);
        SaafUsersEntity_HI userEntity = findUserLogin(map);
        if (null != userEntity) {
            return true; //登录成功
        }
        return false;
    }

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
    @Override
    public SaafUsersEntity_HI userByDatabase(String userName, String password) {
        SHA1Util shal = new SHA1Util();
        Map<String, Object> map = new HashMap<String, Object>();
        String encryptedPassword = "";
        try {
            encryptedPassword = shal.getEncrypt(password); //密码加密
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        }
        map.put("userName", userName);
        map.put("encryptedPassword", encryptedPassword);
        SaafUsersEntity_HI userEntity = findUserLogin(map);
        if (null != userEntity) {
            return userEntity; //存在该用户
        }
        return null;
    }

    /**
     * 生成随机数字和字母
     *
     * @param length
     * @return
     */
    public String getStringRandom(int length) {
        String val = "";
        Random random = new Random(); //length为几位密码
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
    /**
     * 保存供应商对应的用户信息（档案自助维护/内部创建供应商）
     * @param saafUser
     * @param userId
     * @param supplierId
     * @return
     */
    /*@Override
    public boolean saveSaafUser(SaafUsersEntity_HI saafUser, Integer userId, Integer supplierId) {
        boolean flag = false;
        boolean temp = false;
        if(null != saafUser){
            SaafUsersEntity_HI findSaafUser = null;
            if(!(saafUser.getUserId() == null || "".equals(saafUser.getUserId()))){
                findSaafUser = saafUsersDAO_HI.getById(saafUser.getUserId());
            }else{
                if(!(saafUser.getUserName() == null || "".equals(saafUser.getUserName()))){
                    List<SaafUsersEntity_HI> saafUserList = saafUsersDAO_HI.findByProperty("userName",saafUser.getUserName());
                    if(null != saafUserList && saafUserList.size()>0){
                        SaafUsersEntity_HI user = saafUserList.get(0);
                        findSaafUser = saafUsersDAO_HI.getById(user.getUserId());
                    }
                }
            }
            if(null != findSaafUser){  //修改用户状态
                findSaafUser.setUserName(saafUser.getUserName());
                findSaafUser.setSupplierId(supplierId);
                findSaafUser.setOperatorUserId(userId);
                saafUsersDAO_HI.saveEntity(findSaafUser);
                return true;
            }else{ //insert
                List<SrmPosSupplierContactsEntity_HI> contactsList = srmPosSupplierContactsDAO_HI.findByProperty("supplierId",supplierId);
                SendMailUtil sendMailUtil = new SendMailUtil(true);
                String passWord = getStringRandom(6); //随机生成六位数密码（数字+字母）
                SHA1Util sha1Util = new SHA1Util();   //加密
                try{
                    saafUser.setEncryptedPassword(sha1Util.getEncrypt(passWord));  //密码加密
                }catch (Exception e){
                    LOGGER.error("异常，密码加密出错："+e.getMessage());
                    return false;
                }
                saafUser.setSupplierId(supplierId);
                saafUser.setStartDateActive(new Date());
                saafUser.setIsadmin("N");
                saafUser.setPlatformCode("EX");
                saafUser.setOperatorUserId(userId);
                saafUsersDAO_HI.saveEntity(saafUser);
                if(null != contactsList && contactsList.size()>0){
                    for(SrmPosSupplierContactsEntity_HI k:contactsList){
                        String emailAddress = k.getEmailAddress();
                        sendMailUtil.doSendHtmlEmail("创建供应商成功","<p>您好！</p><br/>"+"<p>您的登录账号是："+saafUser.getUserName()+"，其初始密码是：</p>"+passWord+"，登录后请及时更改密码！",new String[]{emailAddress});
                    }
                }
                temp = iSaafUserResp.saveSaafUserResp(saafUser.getUserId(),userId,"SUPPLIER_RESP","EX");
                return temp;
            }

        }else{
            temp = true;
        }
        return temp;
    }*/

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
    public Pagination findSaafUsersList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SaafUserEmployees2Entity_HI_RO.QUERY_USERLIST_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "su.User_Name", "varInputUserName", queryString, map, "like");
            SaafToolUtils.parperParam(parameters, "su.User_Full_Name", "varUserFullName", queryString, map, "like");
            SaafToolUtils.parperParam(parameters, "su.sex", "varUserSex", queryString, map, "like");
            //平台
            SaafToolUtils.parperParam(parameters, "su.platform_code", "var_equal_platformCode", queryString, map, "=");
            //新增查询过滤条件
            SaafToolUtils.parperParam(parameters, "se.EMPLOYEE_NUMBER", "employeeNumber", queryString, map, "like");
            SaafToolUtils.parperParam(parameters, "se.EMPLOYEE_NAME", "employeeName", queryString, map, "like");
            SaafToolUtils.parperParam(parameters, "se.MOBILE_PHONE", "mobilePhone", queryString, map, "like");
            //排序
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" ORDER BY su.last_update_date DESC");
            Pagination<SaafUserEmployees2Entity_HI_RO> userRowSet = saafUserEmployeesDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return userRowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}

