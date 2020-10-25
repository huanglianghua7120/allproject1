package saaf.common.fmw.base.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


public class SaafMessageHeadEntity_HI_RO {

    public static final String QUERY_SQL = "SELECT smh.message_id  messageId \r\n" + 
    		",smh.message_desc      messageDesc \r\n" + 
    		",smh.message_code      messageCode\r\n" +
    		",smh.notice_type       noticeType\r\n" + 
    		",smp.start_date_active startDateActive\r\n" + 
    		",smh.status          status \r\n" + 
    		",smh.message_tex     messageTex\r\n" + 
    		",smh.category_code1  bigCategoryCode\r\n" + 
    		",smh.category_code2  middleCategoryCode\r\n" + 
    		",smh.category_code3  smallCategoryCode\r\n" + 
    		",slv.meaning         statusStr \r\n" + 
    		",su.user_full_name   userFullName\r\n" + 
    		",smh.message_file_id messageFileId\r\n" + 
    		",rf.access_Path 	 messageFilePath\r\n" + 
    		",rf.file_Name 		messageFileName\r\n" + 
    		",slv2.meaning 		noticeTypeStr\r\n" + 
    		",slv3.meaning     bigCategoryName\r\n" + 
    		",slv4.meaning     middleCategoryName\r\n" + 
    		",slv5.meaning     smallCategoryName\r\n" + 
    		"FROM saaf_message_head  smh \r\n" + 
    		"LEFT JOIN saaf_users su on smh.created_by = su.user_id\r\n" + 
    		"LEFT JOIN saaf_base_result_file rf on smh.message_file_id = rf.file_id\r\n" +
    		"LEFT JOIN saaf_message_push smp on smh.message_id = smp.message_id\r\n" +
    		"LEFT JOIN saaf_lookup_values slv on smh.status = slv.lookup_code\r\n" + 
    		"AND slv.lookup_type = 'MESSAGE_STATUS'\r\n" + 
    		"LEFT JOIN saaf_lookup_values slv2 on smh.notice_type = slv2.lookup_code\r\n" + 
    		"AND slv2.lookup_type = 'NOTICE_TYPE'\r\n" + 
    		"LEFT JOIN saaf_lookup_values slv3 on smh.category_code1 = slv3.lookup_code\r\n" + 
    		"AND slv3.lookup_type='BASE_BIG_CATEGORY'\r\n" + 
    		"LEFT JOIN saaf_lookup_values slv4 on smh.category_code2 = slv4.lookup_code\r\n" + 
    		"AND slv4.lookup_type='BASE_MIDDLE_CATEGORY'\r\n" + 
    		"LEFT JOIN saaf_lookup_values slv5 on smh.category_code3 = slv5.lookup_code\r\n" + 
    		"AND slv5.lookup_type='BASE_SMALL_CATEGORY'\r\n" + 
    		"WHERE 1=1 \r\n" + 
    		"AND smh.message_type ='NOTICE'";
    
    //全局通知
    public static String QUERY_OVERALL_SQL="SELECT u.user_id userId\r\n" + 
    		"FROM\r\n" + 
    		"	srm_pos_supplier_info i,\r\n" + 
    		"	saaf_users u\r\n" + 
    		"WHERE\r\n" + 
    		"	i.supplier_id = u.supplier_id\r\n" + 
    		"AND i.supplier_status IN (\r\n" + 
    		"	'QUALIFIED',\r\n" + 
    		"	'INTRODUCE',\r\n" + 
    		"	'FROZEN'\r\n" + 
    		")\r\n";
    
    
    //指定通知
    public static String QUERY_APPOINT_SQL="SELECT u.user_id userId\r\n" + 
    		"FROM \r\n" + 
    		" saaf_users u\r\n" + 
    		" ,srm_pos_supplier_info i\r\n" + 
    		" ,saaf_message_line l\r\n" + 
    		"WHERE u.supplier_id = i.supplier_id\r\n" + 
    		"AND i.supplier_id = l.supplier_id\r\n" + 
    		"AND i.supplier_status IN (\r\n" + 
    		"	'QUALIFIED',\r\n" + 
    		"	'INTRODUCE',\r\n" + 
    		"	'FROZEN'\r\n" + 
    		")";
    
    
    //按分类通知
    public static String QUERY_CATEGORY_SQL="SELECT u.user_id userId\r\n" + 
    		"FROM\r\n" + 
    		"	srm_pos_supplier_categories c,\r\n" + 
    		"	srm_pos_supplier_info i,\r\n" + 
    		"	saaf_users u,\r\n" + 
    		"	saaf_message_head h\r\n" + 
    		"WHERE \r\n" + 
    		"  h.message_id = :varMessageId\r\n" + 
    		"AND c.`status` = 'ACT'\r\n" + 
    		"AND c.supplier_id = i.supplier_id\r\n" + 
    		"AND i.supplier_id = u.supplier_id\r\n" + 
    		"AND i.supplier_status IN (\r\n" + 
    		"	'QUALIFIED',\r\n" + 
    		"	'INTRODUCE',\r\n" + 
    		"	'FROZEN'\r\n" + 
    		")\r\n" + 
    		"AND is_meet_cate (\r\n" + 
    		"	c.big_category_code,\r\n" + 
    		"	c.middle_category_code,\r\n" + 
    		"	c.small_category_code,\r\n" + 
    		"	h.category_code1,\r\n" + 
    		"	h.category_code2,\r\n" + 
    		"	h.category_code3\r\n" + 
    		")\r\n" + 
    		"";

	public static String QUERY_NEED_SQL="SELECT smh.`message_id` messageId,smh.`message_code` messageCode,smh.`message_desc` messageDesc,smh.`message_type`,smh.`message_url`messageUrl,smh.`message_tex` messageTex  FROM saaf_message_head smh WHERE 1=1 ";
    private Integer messageId;
    private String messageCode;
    private String messageDesc;
    private String messageUrl;
    private String noticeType;
    private Integer userId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateActive;
    private String status;
    private String statusStr;
    private String description;
    private String noticeTypeStr;
    private String messageTex;
    private String userFullName;
    
    private Integer messageFileId;
    private String messageFilePath;
    private String messageFileName;
    
    private String bigCategoryName;
    private String middleCategoryName;
    private String smallCategoryName;
    
    private String bigCategoryCode;
    private String middleCategoryCode;
    private String smallCategoryCode;
    
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageDesc() {
        return messageDesc;
    }

    public void setMessageDesc(String messageDesc) {
        this.messageDesc = messageDesc;
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

    public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getNoticeTypeStr() {
		return noticeTypeStr;
	}

	public void setNoticeTypeStr(String noticeTypeStr) {
		this.noticeTypeStr = noticeTypeStr;
	}

	public String getMessageTex() {
        return messageTex;
    }

    public void setMessageTex(String messageTex) {
        this.messageTex = messageTex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public Integer getMessageFileId() {
		return messageFileId;
	}

	public void setMessageFileId(Integer messageFileId) {
		this.messageFileId = messageFileId;
	}

	public String getMessageFilePath() {
		return messageFilePath;
	}

	public void setMessageFilePath(String messageFilePath) {
		this.messageFilePath = messageFilePath;
	}

	public String getMessageFileName() {
		return messageFileName;
	}

	public void setMessageFileName(String messageFileName) {
		this.messageFileName = messageFileName;
	}

	public String getBigCategoryName() {
		return bigCategoryName;
	}

	public void setBigCategoryName(String bigCategoryName) {
		this.bigCategoryName = bigCategoryName;
	}

	public String getMiddleCategoryName() {
		return middleCategoryName;
	}

	public void setMiddleCategoryName(String middleCategoryName) {
		this.middleCategoryName = middleCategoryName;
	}

	public String getSmallCategoryName() {
		return smallCategoryName;
	}

	public void setSmallCategoryName(String smallCategoryName) {
		this.smallCategoryName = smallCategoryName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBigCategoryCode() {
		return bigCategoryCode;
	}

	public void setBigCategoryCode(String bigCategoryCode) {
		this.bigCategoryCode = bigCategoryCode;
	}

	public String getMiddleCategoryCode() {
		return middleCategoryCode;
	}

	public void setMiddleCategoryCode(String middleCategoryCode) {
		this.middleCategoryCode = middleCategoryCode;
	}

	public String getSmallCategoryCode() {
		return smallCategoryCode;
	}

	public void setSmallCategoryCode(String smallCategoryCode) {
		this.smallCategoryCode = smallCategoryCode;
	}

	
	
    
}
