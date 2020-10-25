package saaf.common.fmw.base.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

/**
 * SaafBaseOperlogEntity_HI Entity Object
 * Wed Oct 30 16:15:51 GMT+08:00 2019  Auto Generate
 */
@Entity
@Table(name = "saaf_base_operlog")
public class SaafBaseOperlogEntity_HI {
    private String operRespParam;
    private String attribute15;
    private String attribute14;
    private String attribute13;
    private String attribute12;
    private String attribute11;
    private String attribute10;
    private String attribute9;
    private String attribute8;
    private String attribute7;
    private String attribute6;
    private String attribute5;
    private String attribute4;
    private String attribute3;
    private String attribute2;
    private String attribute1;
    private Integer lastUpdateLogin;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdatedBy;
    private Integer createdBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer versionNum;
    private String errorMsg;
    private String status;
    private String operParam;
    private String operLocation;
    private String operIp;
    private String operUrl;
    private String operName;
    private String operatorType;
    private String method;
    private String businessType;
    private String title;
    private Integer operlogId;
    private Integer operatorUserId;

	public void setOperRespParam(String operRespParam) {
		this.operRespParam = operRespParam;
	}

	@Column(name = "oper_resp_param", nullable = true)
	public String getOperRespParam() {
		return operRespParam;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	@Column(name = "attribute15", nullable = true, length = 240)
	public String getAttribute15() {
		return attribute15;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	@Column(name = "attribute14", nullable = true, length = 240)
	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	@Column(name = "attribute13", nullable = true, length = 240)
	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	@Column(name = "attribute12", nullable = true, length = 240)
	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	@Column(name = "attribute11", nullable = true, length = 240)
	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	@Column(name = "attribute10", nullable = true, length = 240)
	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	@Column(name = "attribute9", nullable = true, length = 240)
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	@Column(name = "attribute8", nullable = true, length = 240)
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	@Column(name = "attribute7", nullable = true, length = 240)
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	@Column(name = "attribute6", nullable = true, length = 240)
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	@Column(name = "attribute5", nullable = true, length = 240)
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	@Column(name = "attribute4", nullable = true, length = 240)
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	@Column(name = "attribute3", nullable = true, length = 240)
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	@Column(name = "attribute2", nullable = true, length = 240)
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	@Column(name = "attribute1", nullable = true, length = 240)
	public String getAttribute1() {
		return attribute1;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name = "last_update_login", nullable = true, length = 22)
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "last_update_date", nullable = false, length = 7)
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "last_updated_by", nullable = false, length = 22)
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_by", nullable = false, length = 22)
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creation_date", nullable = false, length = 7)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
	@Column(name = "version_num", nullable = true, length = 22)
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Column(name = "error_msg", nullable = true, length = 4000)
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "status", nullable = true, length = 64)
	public String getStatus() {
		return status;
	}

	public void setOperParam(String operParam) {
		this.operParam = operParam;
	}

	@Column(name = "oper_param", nullable = true)
	public String getOperParam() {
		return operParam;
	}

	public void setOperLocation(String operLocation) {
		this.operLocation = operLocation;
	}

	@Column(name = "oper_location", nullable = true, length = 512)
	public String getOperLocation() {
		return operLocation;
	}

	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}

	@Column(name = "oper_ip", nullable = true, length = 256)
	public String getOperIp() {
		return operIp;
	}

	public void setOperUrl(String operUrl) {
		this.operUrl = operUrl;
	}

	@Column(name = "oper_url", nullable = true, length = 2048)
	public String getOperUrl() {
		return operUrl;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	@Column(name = "oper_name", nullable = true, length = 256)
	public String getOperName() {
		return operName;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	@Column(name = "operator_type", nullable = true, length = 128)
	public String getOperatorType() {
		return operatorType;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Column(name = "method", nullable = true, length = 1024)
	public String getMethod() {
		return method;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@Column(name = "business_type", nullable = true, length = 256)
	public String getBusinessType() {
		return businessType;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "title", nullable = true, length = 256)
	public String getTitle() {
		return title;
	}

	public void setOperlogId(Integer operlogId) {
		this.operlogId = operlogId;
	}


	@Id
	@SequenceGenerator(name = "SAAF_BASE_OPERLOG_S", sequenceName = "SAAF_BASE_OPERLOG_S", allocationSize = 1)
	@GeneratedValue(generator = "SAAF_BASE_OPERLOG_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "operlog_id", nullable = false, length = 22)
	public Integer getOperlogId() {
		return operlogId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
