package saaf.common.fmw.base.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

import java.util.Date;


public class SaafMessagePushEntity_HI_RO implements Serializable {
	
	//查询通知
	public static String QUERY_NOTICE_SQL =
					"SELECT *\n" +
					"FROM   (SELECT smp.message_push_id messagePushId\n" +
					"              ,smp.message_type messageType\n" +
					"              ,smp.message_url messageUrl\n" +
					"              ,smp.user_id userId\n" +
					"              ,smp.state state\n" +
					"              ,smp.message_id messageId\n" +
					"              ,smp.message_code messageCode\n" +
					"              ,smp.creation_date creationDate\n" +
					"              ,slv.meaning stateMean\n" +
					"              ,smh.notice_type noticeType\n" +
					"              ,smh.message_desc messageDesc\n" +
					"              ,slv3.meaning noticeTypeMean\n" +
					"              ,smp.start_date_active startDateActive\n" +
					"              ,(CASE smp.state\n" +
					"                 WHEN 'UNREADED' THEN\n" +
					"                  10\n" +
					"                 WHEN 'DONE' THEN\n" +
					"                  40\n" +
					"                 WHEN 'READED' THEN\n" +
					"                  30\n" +
					"               END) paixv\n" +
					"        FROM   saaf_message_push smp\n" +
					"        INNER  JOIN saaf_lookup_values slv\n" +
					"        ON     smp.state = slv.lookup_code\n" +
					"        AND    slv.lookup_type = 'PUSH_STATE'\n" +
					"        LEFT   JOIN saaf_message_head smh\n" +
					"        ON     smp.message_id = smh.message_id\n" +
					"        LEFT   JOIN saaf_lookup_values slv2\n" +
					"        ON     smp.message_type = slv2.lookup_code\n" +
					"        AND    slv2.lookup_type = 'MESSAGE_TYPE'\n" +
					"        LEFT   JOIN saaf_lookup_values slv3\n" +
					"        ON     smh.notice_type = slv3.lookup_code\n" +
					"        AND    slv3.lookup_type = 'NOTICE_TYPE'\n" +
					"        WHERE  nvl(smp.enable_flag, 'Y') = 'Y'\n" +
					"        AND    nvl(smp.start_date_active, SYSDATE) <= SYSDATE\n" +
					"        AND    smp.user_id = :var_equal_userId) t\n" +
					"WHERE  1 = 1\n";

	//查询待办
    public static String QUERY_NEED_SQL =
					"SELECT *\n" +
					"FROM   (SELECT smp.message_push_id messagePushId\n" +
					"              ,smp.message_type messageType\n" +
					"              ,smp.message_url messageUrl\n" +
					"              ,smp.user_id userId\n" +
					"              ,smp.state state\n" +
					"              ,smp.message_id messageId\n" +
					"              ,smp.message_code messageCode\n" +
					"              ,smp.creation_date creationDate\n" +
					"              ,slv.meaning stateMean\n" +
					"              ,smh.message_desc messageDesc\n" +
					"              ,smp.start_date_active startDateActive\n" +
					"              ,(CASE smp.state\n" +
					"                 WHEN 'UNREADED' THEN\n" +
					"                  10\n" +
					"                 WHEN 'DONE' THEN\n" +
					"                  40\n" +
					"                 WHEN 'READED' THEN\n" +
					"                  30\n" +
					"               END) paixv\n" +
					"        FROM   saaf_message_push smp\n" +
					"        INNER  JOIN saaf_lookup_values slv\n" +
					"        ON     smp.state = slv.lookup_code\n" +
					"        AND    slv.lookup_type = 'PUSH_STATE'\n" +
					"        LEFT   JOIN saaf_message_head smh\n" +
					"        ON     smp.message_id = smh.message_id\n" +
					"        LEFT   JOIN saaf_lookup_values slv2\n" +
					"        ON     smp.message_type = slv2.lookup_code\n" +
					"        AND    slv2.lookup_type = 'MESSAGE_TYPE'\n" +
					"        WHERE  nvl(smp.enable_flag, 'Y') = 'Y'\n" +
					"        AND    nvl(smp.start_date_active, SYSDATE) <= SYSDATE\n" +
					"        AND    smp.user_id = :var_equal_userId) t\n" +
					"WHERE  1 = 1\n";

	private String messageDesc;
    private String messageType;
    private String messageCode;
    private String messageUrl;
    private Integer userId;
    private String state;
    private String stateMean;
    private Integer messageId;
    
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateActive;
    private Integer messagePushId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    
    private String noticeType;
    private String noticeTypeMean;
    
    public String getMessageDesc() {
        return messageDesc;
    }

    public void setMessageDesc(String messageDesc) {
        this.messageDesc = messageDesc;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessagePushId() {
        return messagePushId;
    }

    public void setMessagePushId(Integer messagePushId) {
        this.messagePushId = messagePushId;
    }


    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStateMean() {
        return stateMean;
    }

    public void setStateMean(String stateMean) {
        this.stateMean = stateMean;
    }

	public Date getStartDateActive() {
		return startDateActive;
	}

	public void setStartDateActive(Date startDateActive) {
		this.startDateActive = startDateActive;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getNoticeTypeMean() {
		return noticeTypeMean;
	}

	public void setNoticeTypeMean(String noticeTypeMean) {
		this.noticeTypeMean = noticeTypeMean;
	}
    
}
