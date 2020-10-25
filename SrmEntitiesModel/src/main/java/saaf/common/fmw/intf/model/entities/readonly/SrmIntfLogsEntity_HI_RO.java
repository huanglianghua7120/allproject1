package saaf.common.fmw.intf.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class SrmIntfLogsEntity_HI_RO {

	public static final String QUERY_LOG_INFO =
					"SELECT\n" +
					"  sil.log_id logId,\n" +
					"  sil.intf_type intfType,\n" +
					"  sil.intf_status intfStatus,\n" +
					"  sil.description description,\n" +
					"  sil.error_msg errorMsg,\n" +
					"  sil.creation_date creationDate,\n" +
					"  slv1.meaning TYPE,\n" +
					"  slv2.meaning STATUS\n" +
					"FROM\n" +
					"  srm_intf_logs sil\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_type = 'SRM_INTF_STATUS'\n" +
					"    AND slv2.lookup_code = sil.intf_status,\n" +
					"  saaf_lookup_values slv1\n" +
					"WHERE slv1.lookup_type = 'SRM_INTF_TYPE'\n" +
					"  AND slv1.lookup_code = sil.intf_type";

	public static final String QUERY_LOG_COUNT =
					"SELECT\n" +
					"  COUNT(1)\n" +
					"FROM\n" +
					"  srm_intf_logs sil\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_type = 'SRM_INTF_STATUS'\n" +
					"    AND slv2.lookup_code = sil.intf_status,\n" +
					"  saaf_lookup_values slv1\n" +
					"WHERE slv1.lookup_type = 'SRM_INTF_TYPE'\n" +
					"  AND slv1.lookup_code = sil.intf_type";

	public static final String QUERY_DATA_INFO =
					"SELECT\n" +
					"  sil.log_id logId,\n" +
					"  sil.out_data outData,\n" +
					"  sil.in_data inData\n" +
					"FROM\n" +
					"  srm_intf_logs sil\n" +
					"WHERE 1 = 1";
	private Integer logId; //日志ID
	private String intfType; //接口类型（快码：BASE_INTF_TYPE）
	private String tableName; //接口取数对应的表
	private Integer tableId; //接口取数对应的表ID
	private String dataDirection; //数据方向(IN：输入， OUT：输出)
	private String sendSystem; //数据发送方
	private String receiveSystem; //数据接收方
	private String outData; //输出报文
	private String inData; //输入报文
	private String intfStatus; //接口状态
	private String errorMsg; //错误信息
	private String description; //说明
	private Integer versionNum;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	private Integer createdBy;
	private Integer lastUpdatedBy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;
	private Integer lastUpdateLogin;
	private String attributeCategory;
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;
	private String attribute6;
	private String attribute7;
	private String attribute8;
	private String attribute9;
	private String attribute10;
	private Integer operatorUserId;
	//别名
	private String type;
	private String status;

	public Integer getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getIntfType() {
		return intfType;
	}

	public void setIntfType(String intfType) {
		this.intfType = intfType;
	}

	public String getOutData() {
		return outData;
	}

	public void setOutData(String outData) {
		this.outData = outData;
	}

	public String getInData() {
		return inData;
	}

	public void setInData(String inData) {
		this.inData = inData;
	}

	public String getDataDirection() {
		return dataDirection;
	}

	public void setDataDirection(String dataDirection) {
		this.dataDirection = dataDirection;
	}

	public String getIntfStatus() {
		return intfStatus;
	}

	public void setIntfStatus(String intfStatus) {
		this.intfStatus = intfStatus;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public String getSendSystem() {
		return sendSystem;
	}

	public void setSendSystem(String sendSystem) {
		this.sendSystem = sendSystem;
	}

	public String getReceiveSystem() {
		return receiveSystem;
	}

	public void setReceiveSystem(String receiveSystem) {
		this.receiveSystem = receiveSystem;
	}
}
