package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：saafMessagePushService.java
 * Description：消息护接口类
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface ISaafMessagePush {


    /**
     * 查询自己有多少通知和代办
     * @param parameters
     * @param pageIndex
     * @param pageSize
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    Pagination findBySelf(JSONObject parameters, Integer pageIndex, Integer pageSize) throws Exception;

    /**
     * 设置为已读状态
     * @param parameters
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    Boolean setReaded(JSONObject parameters) throws Exception;


    /**
     *
     * 创建待办推送
     * @param desc 待办描述
     * @param url 待办处理地址
     * @param personSql 选择人员的sql
     * @param sourceType
     * @param sourceId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    boolean createPushOfNeed(String desc, String url, String personSql, String sourceType, String sourceId) throws Exception;


    /**
     * 设置推送状态为已经处理
     * @param sourceType 来源类型
     * @param sourceId 来源id
     * @param userId 操作用户的id
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    Boolean setDone(String sourceType, String sourceId, int userId) throws Exception;

    /**
     * 设置推送状态为指定
     * @param sourceType 来源类型
     * @param sourceId 来源id
     * @param userId 操作用户的id
     * @param state 设置的状态
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */

    Boolean setPushState(String sourceType, String sourceId, int userId, String state) throws Exception;

    /**
     * 设置推送状态为指定
     * @param messageId 消息id
     * @param userId 操作用户的id
     * @param state 设置的状态
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    Boolean setPushState(int messageId, int userId, String state) throws Exception;

    /**
     * 创建通知推送
     *
     * @param desc       通知描述
     * @param messageTex 通知内容
     * @param personSql  选择人员的sql
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    boolean createPushOfNotice(String desc, String messageTex, String personSql) throws Exception;

    /**
     * 创建一个推送推送（例如xls导出下载推送，无需创建消息表的信息）
     *
     * @param desc       通知描述
     * @param messageTex 通知内容
     * @param personSql  选择人员的sql
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    boolean createPushOfNotice(String desc, String messageTex, String personSql, String url) throws Exception;
    
    

    /**
     * 更具sourceId 和 sourceType 查询是否存在未读和已读状态的代办，
     * @param sourceId
     * @param sourceType
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     钟士元             创建
     * ===========================================================================
     */
    public Boolean chenkPush(String sourceId, String sourceType);
}
