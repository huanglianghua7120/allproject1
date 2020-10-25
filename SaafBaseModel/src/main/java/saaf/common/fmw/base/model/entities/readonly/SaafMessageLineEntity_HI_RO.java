package saaf.common.fmw.base.model.entities.readonly;

public class SaafMessageLineEntity_HI_RO {

    public static String QUERY_SQL = "SELECT sml.message_line_id \"messageLineId\"\r\n" +
        "       ,sml.message_id      \"messageId\"\r\n" +
        "       ,sml.type            \"type\"\r\n" +
        "       ,sml.userid_or_group \"useridOrGroup\"\r\n" +
        "       ,sml.user_code       \"userCode\"\r\n" +
        "       ,sml.user_name       \"userName\"\r\n" +
        "       ,sml.description     \"description\"\r\n" +
        "  FROM saaf_message_line  sml\r\n" +
        "      ,saaf_lookup_values slv\r\n" +
        " WHERE slv.lookup_type = 'USER_OR_GROUP'\r\n" +
        "   AND slv.lookup_code = sml.type ";
    public static String QUERY_USER_LOV = "SELECT su.user_id \"userId\"\r\n" +
        "       ,su.user_name \"userCode\"\r\n" +
        "       ,su.user_full_name \"userName\"\r\n" +
        "       ,(CASE\r\n" +
        "         WHEN (SELECT COUNT(1)\r\n" +
        "                 FROM saaf_message_line sml\r\n" +
        "                WHERE instr(sml.userid_or_group\r\n" +
        "                           ,',' || su.user_id || ',') > 0 AND sml.message_line_id = :messagelineid) > 0\r\n" +
        "         THEN\r\n" +
        "          'Y'\r\n" +
        "         ELSE\r\n" +
        "          'N'\r\n" +
        "       END) \"checkFlag\"\r\n" +
        "  FROM saaf_users su\r\n" +
        " WHERE SYSDATE() BETWEEN su.start_date_active AND\r\n" +
        "       ifnull(su.end_date_active\r\n" +
        "          ,SYSDATE())";

    public static String QUERY_GROUP_LOV =
        "select       " + "sug.user_group_id \"userId\" ,      " + "sug.group_code \"userCode\" ,      " + "sug.group_desc \"userName\"  ,    " +
        "((CASE WHEN (SELECT COUNT(1) FROM saaf_message_line sml      " + "    WHERE INSTR(sml.USERID_OR_GROUP,','||sug.USER_GROUP_ID||',')>0      " +
        "AND sml.MESSAGE_LINE_ID=:messageLineId" + "     )>0 THEN 'Y' ELSE 'N' END)) \"checkFlag\"         " + "from saaf_user_group sug  ";
    
    public static String QUERY_SUPPLIER_LOV="select si.supplier_id supplierId \r\n" + 
    		",si.supplier_name supplierName\r\n" + 
    		",si.supplier_number supplierNumber\r\n" + 
    		",si.supplier_status supplierStatus\r\n" + 
    		",slv.meaning supplierStatusMean\r\n" + 
    		"from \r\n" + 
    		"srm_pos_supplier_info si\r\n" + 
    		"LEFT JOIN saaf_lookup_values slv on si.supplier_status = slv.lookup_code\r\n" + 
    		"AND lookup_type ='POS_SUPPLIER_STATUS'\r\n" + 
    		"where 1=1\r\n" + 
    		"and si.supplier_status not in('QUIT','REJECTED','SUBMITTED')";
    
    public static String QUERY_SUPPLIER_SQL="SELECT sml.message_line_id messageLineId\r\n" + 
    		",sml.message_id      messageId\r\n" + 
    		",sml.type            type\r\n" + 
    		",sml.userid_or_group useridOrGroup\r\n" + 
    		",sml.user_code       userCode\r\n" + 
    		",sml.user_name       userName\r\n" + 
    		",sml.description     description\r\n" + 
    		",si.supplier_id supplierId\r\n" + 
    		",si.supplier_number supplierNumber\r\n" + 
    		",si.supplier_name supplierName\r\n" + 
    		"FROM saaf_message_line  sml\r\n" + 
    		"LEFT JOIN srm_pos_supplier_info si on si.supplier_id = sml.supplier_id\r\n" + 
    		" WHERE 1=1";
    
    private Integer messageLineId;
    private Integer messageId;
    private String type;
    private String useridOrGroup;
    private String userCode;
    private String userName;
    private String description;
    private Integer userId;
    private String checkFlag;
    
    private Integer supplierId;
    private String supplierName;
	private String supplierNumber;
	private String supplierStatus;
	private String supplierStatusMean;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUseridOrGroup() {
        return useridOrGroup;
    }

    public void setUseridOrGroup(String useridOrGroup) {
        this.useridOrGroup = useridOrGroup;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Integer getMessageLineId() {
        return messageLineId;
    }

    public void setMessageLineId(Integer messageLineId) {
        this.messageLineId = messageLineId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public String getSupplierStatusMean() {
		return supplierStatusMean;
	}

	public void setSupplierStatusMean(String supplierStatusMean) {
		this.supplierStatusMean = supplierStatusMean;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
    

}
