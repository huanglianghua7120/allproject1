package saaf.common.fmw.base.model.inter.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.DynamicBaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafUserRespEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafUserRespEntity_HI_RO;
import saaf.common.fmw.base.model.inter.IUserForCunstom;
import saaf.common.fmw.common.model.inter.server.ShortDescMessageServer;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.utils.SHA1Util;


@Component("userForCunstomServer")
public class UserForCunstomServer implements IUserForCunstom {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserForCunstomServer.class);
    @Autowired
    private ShortDescMessageServer shortDescMessageServer;
    @Autowired
    private DynamicBaseViewObject commonDAO_HI_DY;
    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;
    @Autowired
    private ViewObject<SaafUserRespEntity_HI> saafUserRespDAO_HI;
    //@Autowired
    //private BaseViewObject<SaafUserRespEntity_HI_RO> saafUserRespDAO_HI_RO;

    public UserForCunstomServer() {
        super();
    }


    /**
     * 查询用户列表
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     */
    @Override
    public Pagination find(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            Map<String, Object> map = SToolUtils.fastJsonObj2Map(parameters);
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT SUSER.USER_ID \"userId\"\r\n" +
                    "      ,SUSER.EMPLOYEE_ID \"employeeId\"\r\n" +
                    "      ,SUSER.MEMBER_ID \"memberId\"\r\n" +
                    "      ,SUSER.ISADMIN \"isadmin\"\r\n" +
                    "      ,SUSER.USER_NAME \"userName\"\r\n" +
                    "      ,SUSER.USER_FULL_NAME \"userFullName\"\r\n" +
                    "      ,SUSER.PLATFORM_CODE \"platformCode\"\r\n" +
                    "      ,SUSER.START_DATE_ACTIVE \"startDateActive\"\r\n" +
                    "      ,SUSER.END_DATE_ACTIVE \"endDateActive\"\r\n" +
                    "      ,RESP.USER_RESP_ID \"userRespId\"\r\n" +
                    "      ,RESP.USER_RESP_NAME \"userRespName\"\r\n" +
                    "      ,RESP.RESPONSIBILITY_ID \"responsibilityId\"\r\n" +
                    "  FROM SAAF_USERS SUSER\r\n" +
                    "  LEFT JOIN SAAF_USER_RESP RESP\r\n" +
                    "    ON SUSER.USER_ID = RESP.USER_ID\r\n" +
                    " WHERE 1 = 1\r\n");

            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            SaafToolUtils.parperParam(parameters, "MEMBER_ID", "varMemberId", sql, queryParamMap, "=");
            SaafToolUtils.parperParam(parameters, "USER_ID", "varUserId", sql, queryParamMap, "=");
            Pagination findPagination = commonDAO_HI_DY.findPagination(sql, map, pageIndex, pageRows);
            return findPagination; //baseDAO.findSqlPagination(sql, map, pageIndex, pageRows);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception(e);
        }

    }

    @Override
    public JSONObject save(JSONObject params) {
        //System.out.println(JSON.toJSON(params));
        try {
            int userId = params.getIntValue("varUserId");

            params.put("lastUpdateDate", new Date());
            params.put("lastUpdatedBy", userId);
            params.put("lastUpdateLogin", userId);

            params.remove("createdBy");
            params.remove("creationDate");
            params.put("isadmin", "N");
            params.put("platformCode", "AGENT");
            params.put("memberId", params.getIntValue("varMemberId"));
            if (null != params.get("encryptedPassword") && !"".equals(params.getString("encryptedPassword"))) {
                SHA1Util sha1 = new SHA1Util();
                String encryptedPassword;
                String encryptedPassword_ = params.getString("encryptedPassword");
                encryptedPassword = sha1.getEncrypt(encryptedPassword_);
                params.put("encryptedPassword", encryptedPassword);
            } else {
                params.remove("encryptedPassword");
            }
            // 保存到用户表
            if (params.getInteger("userId") != null) {
                params.remove("userName");
                SaafUsersEntity_HI getRow = saafUsersDAO_HI.findByProperty("userId", params.getInteger("userId")).get(0);
                /*if (!getRow.getUserName().equals(params.getString("userName"))) {
					List userNameCheckList = baseDAO.findByProperty("userName", params.getString("userName"));
					if (userNameCheckList.size() > 0) {

						return SToolUtils.convertResultJSONObj("E",  SToolUtils.getMessage("SAVE-FAILURE",new Object[] {"登陆名已经存在！"}), 1, null);
						
					}
				}*/
                JSONObject tempJson = (JSONObject)JSON.toJSON(getRow);
                tempJson.putAll(params);
                SaafUsersEntity_HI temp = JSONObject.parseObject(tempJson.toString(), SaafUsersEntity_HI.class);
                BeanUtils.copyProperties(temp, getRow);
                saafUsersDAO_HI.saveOrUpdate(getRow);
                List<SaafUserRespEntity_HI> list = saafUserRespDAO_HI.findByProperty("userRespId", params.getInteger("userRespId"));
                list.get(0).setResponsibilityId(params.getInteger("responsibilityId"));
                list.get(0).setUserRespName(params.getString("userRespName"));
                list.get(0).setLastUpdateDate(new Date());
                list.get(0).setLastUpdatedBy(userId);
                saafUserRespDAO_HI.saveOrUpdate(list.get(0));
            } else {

                List userNameCheckList = saafUsersDAO_HI.findByProperty("userName", params.getString("userName"));
                if (userNameCheckList.size() > 0) {
                    return SToolUtils.convertResultJSONObj("E", shortDescMessageServer.getMessage("SAVE-FAILURE", new Object[] { "登陆名重复！" }), 1, null);
                }

                params.put("createdBy", userId);
                params.put("creationDate", new Date());
                SaafUsersEntity_HI parameRow = JSONObject.toJavaObject(params, SaafUsersEntity_HI.class);
                saafUsersDAO_HI.saveOrUpdate(parameRow);
                //分配
                SaafUserRespEntity_HI saafUserRespEntity_HI = new SaafUserRespEntity_HI();
                saafUserRespEntity_HI.setUserId(parameRow.getUserId());
                saafUserRespEntity_HI.setUserRespName(params.getString("userRespName"));
                saafUserRespEntity_HI.setResponsibilityId(params.getInteger("responsibilityId"));
                saafUserRespEntity_HI.setPlatformCode("AGENT");
                saafUserRespEntity_HI.setStartDateActive(new Date());
                saafUserRespEntity_HI.setResponsibilityId(params.getInteger("responsibilityId"));
                saafUserRespEntity_HI.setUserRespName(params.getString("userRespName"));
                saafUserRespEntity_HI.setLastUpdateDate(new Date());
                saafUserRespEntity_HI.setLastUpdatedBy(userId);
                saafUserRespEntity_HI.setCreatedBy(userId);
                saafUserRespEntity_HI.setCreationDate(new Date());
                saafUserRespDAO_HI.saveOrUpdate(saafUserRespEntity_HI);
            }
            return SToolUtils.convertResultJSONObj("S", shortDescMessageServer.getMessage("SAVE-SUCCESS"), 1, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("经销商用户维护保存失败！" + e);
            return SToolUtils.convertResultJSONObj("E", shortDescMessageServer.getMessage("SAVE-FAILURE"), 1, null);
        }
    }
}
