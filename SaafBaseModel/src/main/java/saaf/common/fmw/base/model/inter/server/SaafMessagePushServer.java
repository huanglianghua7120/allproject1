package saaf.common.fmw.base.model.inter.server;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.dao.SaafMessageHeadDAO_HI;
import saaf.common.fmw.base.model.entities.SaafMessageHeadEntity_HI;
import saaf.common.fmw.base.model.entities.SaafMessagePushEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafMessagePushEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafMessage;
import saaf.common.fmw.base.model.inter.ISaafMessagePush;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：saafMessagePushServer.java
 * Description：消息护接口类
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
@Component("saafMessagePushServer")
public class SaafMessagePushServer implements ISaafMessagePush {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaafMessagePushServer.class);

    public SaafMessagePushServer() {
        super();
    }

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private SaafMessageHeadDAO_HI saafMessageHeadDAO_HI;

    @Autowired
    private ViewObject<SaafMessagePushEntity_HI> saafMessagePushDAO_HI;

    @Autowired
    private BaseViewObject<SaafMessagePushEntity_HI_RO> saafMessagePushDAO_HI_RO;

    @Autowired
    private ISaafMessage saafMessageServer;

    @Autowired
    private SaafUsersServer saafUsersServer;

    /**
     * 查询自己有多少通知和代办
     *
     * @param parameters
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public Pagination findBySelf(JSONObject parameters, Integer pageIndex,
                                 Integer pageSize) throws Exception {
        LOGGER.info("===============================" + parameters);
        try {
            int userId = parameters.getIntValue("varUserId");
            Map<String, Object> map = new HashMap<String, Object>();
            StringBuffer sb = new StringBuffer();
            Pagination<SaafMessagePushEntity_HI_RO> findPagination = null;
            if ("NOTICE".equals(parameters.get("var_equal_messageType"))) {
                sb.append(SaafMessagePushEntity_HI_RO.QUERY_NOTICE_SQL);
                map.put("var_equal_userId", userId);
                if (parameters.containsKey("var_equal_messageType") && parameters.getString("var_equal_messageType") != null) {
                    sb.append(" AND t.messageType = :var_equal_messageType");
                    map.put("var_equal_messageType", parameters.getString("var_equal_messageType"));
                }

                SaafToolUtils.parperParam(parameters, "to_char(t.startDateActive, 'yyyy-mm-dd') ", "startDateTo", sb, map, ">=");
                SaafToolUtils.parperParam(parameters, "to_char(t.startDateActive, 'yyyy-mm-dd') ", "startDateFrom", sb, map, "<=");
                SaafToolUtils.parperParam(parameters, "t.messageDesc ", "messageDesc", sb, map, "LIKE");
                SaafToolUtils.parperParam(parameters, "t.noticeType ", "noticeType", sb, map, "=");

                if (parameters.containsKey("var_equal_state") && parameters.getString("var_equal_state") != null) {
                    sb.append(" AND t.state = :var_equal_state");
                    map.put("var_equal_state", parameters.getString("var_equal_state"));
                }
                sb.append(" ORDER BY paixv, startDateActive DESC");
                String countSql = "select count(1) from (" + sb + ")";
                findPagination = saafMessagePushDAO_HI_RO.findPagination(sb,countSql, map, pageIndex, pageSize);
            } else if (parameters.get("var_equal_messageType").equals("NEED")) {
                sb.append(SaafMessagePushEntity_HI_RO.QUERY_NEED_SQL);
                map.put("var_equal_userId", userId);
                if (parameters.containsKey("var_equal_messageType") && parameters.getString("var_equal_messageType") != null) {
                    sb.append(" AND t.messageType = :var_equal_messageType");
                    map.put("var_equal_messageType", parameters.getString("var_equal_messageType"));
                }
                SaafToolUtils.parperParam(parameters, "t.messageDesc ", "messageDesc", sb, map, "like");
                SaafToolUtils.parperParam(parameters, "t.messageCode ", "messageCode", sb, map, "like");
                if (parameters.containsKey("var_equal_state") && parameters.getString("var_equal_state") != null) {
                    sb.append(" AND t.state = :var_equal_state");
                    map.put("var_equal_state", parameters.getString("var_equal_state"));
                }
                String countSql = "select count(1) from (" + sb + ")";
                sb.append(" ORDER BY paixv, startDateActive DESC");
                findPagination = saafMessagePushDAO_HI_RO.findPagination(sb,countSql, map, pageIndex, pageSize);
            }
            return findPagination;
        } catch (Exception e) {
            LOGGER.error("获取待办出错:", e);
            return null;
        }
    }

    /**
     * 设置为已读状态
     *
     * @param params
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public Boolean setReaded(JSONObject params) throws Exception {
        int userId = params.getIntValue("varUserId");
        SaafMessagePushEntity_HI entity = saafMessagePushDAO_HI.getById(params
                .getInteger("messagePushId"));
        if (entity.getState().equals("UNREADED")) {
            entity.setState("READED");
            entity.setOperatorUserId(userId);
            // entity.setLastUpdateDate(new Date());
            // entity.setLastUpdatedBy(userId);
            // entity.setLastUpdateLogin(userId);
            saafMessagePushDAO_HI.update(entity);
        }
        return true;
    }

    /**
     * 创建待办推送
     *
     * @param desc       待办描述
     * @param url        待办处理地址
     * @param personSql  选择人员的sql
     * @param sourceType 来源类型
     * @param sourceId   来源id
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public boolean createPushOfNeed(String desc, String url, String personSql,
                                    String sourceType, String sourceId) throws Exception {
        String messageCode = saafSequencesUtil.getDocSequences(
                "saaf_message_push".toUpperCase(), "N", 8);
        int userId = -999999; // 待办都是后台发送的消息，一次设置用户为-1

        SaafMessageHeadEntity_HI saafMessageHeadEntity_HI = new SaafMessageHeadEntity_HI();
        saafMessageHeadEntity_HI.setMessageCode(messageCode);
        saafMessageHeadEntity_HI.setMessageDesc(desc);
        saafMessageHeadEntity_HI.setMessageType("NEED");
        saafMessageHeadEntity_HI.setMessageUrl(url);

        saafMessageHeadEntity_HI.setLastUpdateDate(new Date());
        saafMessageHeadEntity_HI.setLastUpdatedBy(userId);
        saafMessageHeadEntity_HI.setLastUpdateLogin(userId);
        saafMessageHeadEntity_HI.setCreatedBy(userId);
        saafMessageHeadEntity_HI.setCreationDate(new Date());
        saafMessageHeadDAO_HI.save(saafMessageHeadEntity_HI);
        int MessageHeadId = saafMessageHeadEntity_HI.getMessageId();

        String insertLineSql = " INSERT INTO saaf_message_line ( MESSAGE_ID, TYPE, USERID_OR_GROUP, USER_CODE, USER_NAME, CREATION_DATE, CREATED_BY, LAST_UPDATED_BY, LAST_UPDATE_DATE, LAST_UPDATE_LOGIN ) SELECT VAR_MESSAGE_ID, 'USER', user_table.USER_ID, user_table.USER_NAME, user_table.USER_FULL_NAME, now(), VAR_USERID, VAR_USERID, now(), VAR_USERID FROM ( SELECT 1 AS flag, CONCAT(',',group_concat(saaf_users.USER_ID),',') USER_ID, group_concat(saaf_users.USER_NAME) USER_NAME, group_concat( CASE saaf_users.PLATFORM_CODE WHEN 'AUX' THEN ( SELECT saaf_employees.EMPLOYEE_NAME FROM saaf_employees WHERE saaf_employees.EMPLOYEE_ID = saaf_users.EMPLOYEE_ID ) ELSE saaf_users.USER_FULL_NAME END ) USER_FULL_NAME FROM saaf_users WHERE saaf_users.USER_ID IN (VAR_personSql) GROUP BY flag ) user_table  ";
        insertLineSql = insertLineSql
                .replaceAll("VAR_MESSAGE_ID", Integer.toString(MessageHeadId))
                .replaceAll("VAR_USERID", Integer.toString(userId))
                .replaceAll("VAR_personSql", personSql);

        saafMessageHeadDAO_HI.executeSqlUpdate(insertLineSql);
        // ISaafMessage server = (ISaafMessage)
        // SaafToolUtils.context.getBean("saafMessageServer");
        saafMessageServer.updateSaafMessage(MessageHeadId, userId, sourceType,
                sourceId);

        return true;
    }

    /**
     * 创建通知推送
     *
     * @param desc       通知描述
     * @param messageTex 通知的内容
     * @param personSql  选择人员的sql
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public boolean createPushOfNotice(String desc, String messageTex,
                                      String personSql) throws Exception {
        return createPushOfNotice(desc, messageTex, personSql, null);

    }

    /**
     * 创建一个推送推送（例如xls导出下载推送，无需创建消息表的信息）
     *
     * @param desc       通知描述
     * @param messageTex 通知内容
     * @param personSql  选择人员的sql
     * @param url
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public boolean createPushOfNotice(String desc, String messageTex,
                                      String personSql, String url) throws Exception {
        String messageCode = saafSequencesUtil.getDocSequences(
                "saaf_message_push".toUpperCase(), "M", 8);
        ;// SaafToolUtils.getBillNumber("SAAF_MESSAGE_PUSH");
        int userId = -9999; // 待办都是后台发送的消息，一次设置用户为-1

        SaafMessageHeadEntity_HI saafMessageHeadEntity_HI = new SaafMessageHeadEntity_HI();
        saafMessageHeadEntity_HI.setMessageCode(messageCode);
        saafMessageHeadEntity_HI.setMessageDesc(desc);
        saafMessageHeadEntity_HI.setMessageType("NOTICE");
        if (url != null && !url.trim().equals("")) {
            saafMessageHeadEntity_HI.setMessageUrl(url);
        } else {
            saafMessageHeadEntity_HI.setMessageUrl("#/home/show_message_List/"
                    + messageCode);
        }

        saafMessageHeadEntity_HI.setMessageTex(messageTex);
        saafMessageHeadEntity_HI.setLastUpdateDate(new Date());
        saafMessageHeadEntity_HI.setLastUpdatedBy(userId);
        saafMessageHeadEntity_HI.setLastUpdateLogin(userId);
        saafMessageHeadEntity_HI.setCreatedBy(userId);
        saafMessageHeadEntity_HI.setCreationDate(new Date());
        saafMessageHeadDAO_HI.save(saafMessageHeadEntity_HI);
        int MessageHeadId = saafMessageHeadEntity_HI.getMessageId();

        String insertLineSql = " INSERT INTO saaf_message_line ( MESSAGE_ID, TYPE, USERID_OR_GROUP, USER_CODE, USER_NAME, CREATION_DATE, CREATED_BY, LAST_UPDATED_BY, LAST_UPDATE_DATE, LAST_UPDATE_LOGIN ) SELECT VAR_MESSAGE_ID, 'USER', user_table.USER_ID, user_table.USER_NAME, user_table.USER_FULL_NAME, now(), VAR_USERID, VAR_USERID, now(), VAR_USERID FROM ( SELECT 1 AS flag, CONCAT(',',group_concat(saaf_users.USER_ID),',') USER_ID, group_concat(saaf_users.USER_NAME) USER_NAME, group_concat( CASE saaf_users.PLATFORM_CODE WHEN 'AUX' THEN ( SELECT saaf_employees.EMPLOYEE_NAME FROM saaf_employees WHERE saaf_employees.EMPLOYEE_ID = saaf_users.EMPLOYEE_ID ) ELSE saaf_users.USER_FULL_NAME END ) USER_FULL_NAME FROM saaf_users WHERE saaf_users.USER_ID IN (VAR_personSql) GROUP BY flag ) user_table  ";
        insertLineSql = insertLineSql
                .replaceAll("VAR_MESSAGE_ID", Integer.toString(MessageHeadId))
                .replaceAll("VAR_USERID", Integer.toString(userId))
                .replaceAll("VAR_personSql", personSql);

        saafMessageHeadDAO_HI.executeSqlUpdate(insertLineSql);
        // ISaafMessage server = (ISaafMessage)
        // SaafToolUtils.context.getBean("saafMessageServer");
        saafMessageServer.updateSaafMessage(MessageHeadId, userId,
                "createPushOfNotice", "createPushOfNotice");
        return true;
    }

    /**
     * 设置推送状态为已经处理
     *
     * @param sourceType 来源类型
     * @param sourceId   来源id
     * @param userId     操作用户的id
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public Boolean setDone(String sourceType, String sourceId, int userId)
            throws Exception {
        return setPushState(sourceType, sourceId, userId, "DONE");
    }

    /**
     * 设置推送状态为指定
     *
     * @param sourceType 来源类型
     * @param sourceId   来源id
     * @param userId     操作用户的id
     * @param state      设置的状态
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public Boolean setPushState(String sourceType, String sourceId, int userId,
                                String state) throws Exception {
        if (sourceType == null || sourceId == null
                || sourceType.trim().equals("") || sourceId.trim().equals("")) {
            throw new Exception("设置推送状态异常，来源类型和来源id不能为空");
        }

        Map map = new HashMap<>();
        map.put("sourceType", sourceType);
        map.put("sourceId", sourceId);
        List<SaafMessagePushEntity_HI> entitys = saafMessagePushDAO_HI
                .findByProperty(map);
        for (int i = 0; i < entitys.size(); i++) {
            entitys.get(i).setState(state);
            entitys.get(i).setLastUpdateDate(new Date());
            entitys.get(i).setLastUpdatedBy(userId);
            entitys.get(i).setLastUpdateLogin(userId);

        }
        saafMessagePushDAO_HI.update(entitys);
        return true;
    }

    /**
     * 设置推送状态为指定
     *
     * @param messageId 消息id
     * @param userId    操作用户的id
     * @param state     设置的状态
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    @Override
    public Boolean setPushState(int messageId, int userId, String state)
            throws Exception {

        if (state == null || state.trim().equals("")) {
            throw new Exception("设置推送状态异常，状态不能为空");
        }
        Map map = new HashMap<>();
        map.put("messageId", messageId);
        List<SaafMessagePushEntity_HI> entitys = saafMessagePushDAO_HI
                .findByProperty(map);
        for (int i = 0; i < entitys.size(); i++) {
            entitys.get(i).setState(state);
            entitys.get(i).setLastUpdateDate(new Date());
            entitys.get(i).setLastUpdatedBy(userId);
            entitys.get(i).setLastUpdateLogin(userId);

        }
        saafMessagePushDAO_HI.update(entitys);
        return true;
    }

    /**
     * 更具sourceId 和 sourceType 查询是否存在未读和已读状态的代办，
     *
     * @param sourceId
     * @param sourceType
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public Boolean chenkPush(String sourceId, String sourceType) {

        Map<String, Object> map = new HashMap<>();
        map.put("sourceId", sourceId);
        map.put("sourceType", sourceType);

        List<SaafMessagePushEntity_HI> entitys = saafMessagePushDAO_HI
                .findList(
                        " from SaafMessagePushEntity_HI smp where smp.state in('UNREADED','READED') and smp.messageType='NEED'  and  smp.sourceType=:sourceType and smp.sourceId=:sourceId ",
                        map);
        if (entitys.size() > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 删除所有的HTML标签
     *
     * @param source 需要进行除HTML的文本
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public static String deleteAllHTMLTag(String source) {

        if (source == null) {
            return "";
        }

        String s = source;
        s = s.replaceAll("&nbsp;", " ");
        /** 删除普通标签 */
        s = s.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "");
        /** 删除转义字符 */
        s = s.replaceAll("&.{2,6}?;", "");
        return s;
    }

}
