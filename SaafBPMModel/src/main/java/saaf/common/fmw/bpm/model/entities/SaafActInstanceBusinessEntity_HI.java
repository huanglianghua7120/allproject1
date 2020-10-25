package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SaafActInstanceBusinessEntity_HI Entity Object
 * Wed Aug 23 11:33:55 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_act_instance_business")
public class SaafActInstanceBusinessEntity_HI {
    private Integer saibId_;
    private String saibInstanceId_;
    private String saibTaskId_;
    private String saibBusinessId_;
    private String saibTaskTitle_;
    private String saibBusinessdataStr1;
    private String saibBusinessdataStr2;
    private String saibBusinessdataStr3;
    private String saibBusinessdataStr4;
    private String saibBusinessdataStr5;
    private String saibBusinessdataStr6;
    private String saibBusinessdataStr7;
    private String saibBusinessdataStr8;
    private String saibBusinessdataStr9;
    private String saibBusinessdataStr10;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate1;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate2;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate3;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate4;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate5;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate6;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate7;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate8;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate9;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate10;
    private Integer saibBusinessdataInt1;
    private Integer saibBusinessdataInt2;
    private Integer saibBusinessdataInt3;
    private Integer saibBusinessdataInt4;
    private Integer saibBusinessdataInt5;
    private Integer saibBusinessdataInt6;
    private Integer saibBusinessdataInt7;
    private Integer saibBusinessdataInt8;
    private Integer saibBusinessdataInt9;
    private Integer saibBusinessdataInt10;
    
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	private Integer createdBy;
	private Integer lastUpdatedBy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;
	private Integer lastUpdateLogin;
	private Integer operatorUserId;

	public void setSaibId_(Integer saibId_) {
		this.saibId_ = saibId_;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "saib_id_", nullable = false, length = 11)	
	public Integer getSaibId_() {
		return saibId_;
	}

	public void setSaibInstanceId_(String saibInstanceId_) {
		this.saibInstanceId_ = saibInstanceId_;
	}

	@Column(name = "saib_instance_id_", nullable = true, length = 64)	
	public String getSaibInstanceId_() {
		return saibInstanceId_;
	}

	public void setSaibTaskId_(String saibTaskId_) {
		this.saibTaskId_ = saibTaskId_;
	}

	@Column(name = "saib_task_id_", nullable = true, length = 64)	
	public String getSaibTaskId_() {
		return saibTaskId_;
	}

	public void setSaibBusinessId_(String saibBusinessId_) {
		this.saibBusinessId_ = saibBusinessId_;
	}

	@Column(name = "saib_business_id_", nullable = true, length = 200)	
	public String getSaibBusinessId_() {
		return saibBusinessId_;
	}

	public void setSaibTaskTitle_(String saibTaskTitle_) {
		this.saibTaskTitle_ = saibTaskTitle_;
	}

	@Column(name = "saib_task_title_", nullable = true, length = 400)	
	public String getSaibTaskTitle_() {
		return saibTaskTitle_;
	}

	public void setSaibBusinessdataStr1(String saibBusinessdataStr1) {
		this.saibBusinessdataStr1 = saibBusinessdataStr1;
	}

	@Column(name = "saib_businessdata_str1", nullable = true, length = 400)	
	public String getSaibBusinessdataStr1() {
		return saibBusinessdataStr1;
	}

	public void setSaibBusinessdataStr2(String saibBusinessdataStr2) {
		this.saibBusinessdataStr2 = saibBusinessdataStr2;
	}

	@Column(name = "saib_businessdata_str2", nullable = true, length = 400)	
	public String getSaibBusinessdataStr2() {
		return saibBusinessdataStr2;
	}

	public void setSaibBusinessdataStr3(String saibBusinessdataStr3) {
		this.saibBusinessdataStr3 = saibBusinessdataStr3;
	}

	@Column(name = "saib_businessdata_str3", nullable = true, length = 400)	
	public String getSaibBusinessdataStr3() {
		return saibBusinessdataStr3;
	}

	public void setSaibBusinessdataStr4(String saibBusinessdataStr4) {
		this.saibBusinessdataStr4 = saibBusinessdataStr4;
	}

	@Column(name = "saib_businessdata_str4", nullable = true, length = 400)	
	public String getSaibBusinessdataStr4() {
		return saibBusinessdataStr4;
	}

	public void setSaibBusinessdataStr5(String saibBusinessdataStr5) {
		this.saibBusinessdataStr5 = saibBusinessdataStr5;
	}

	@Column(name = "saib_businessdata_str5", nullable = true, length = 400)	
	public String getSaibBusinessdataStr5() {
		return saibBusinessdataStr5;
	}

	public void setSaibBusinessdataStr6(String saibBusinessdataStr6) {
		this.saibBusinessdataStr6 = saibBusinessdataStr6;
	}

	@Column(name = "saib_businessdata_str6", nullable = true, length = 400)	
	public String getSaibBusinessdataStr6() {
		return saibBusinessdataStr6;
	}

	public void setSaibBusinessdataStr7(String saibBusinessdataStr7) {
		this.saibBusinessdataStr7 = saibBusinessdataStr7;
	}

	@Column(name = "saib_businessdata_str7", nullable = true, length = 400)	
	public String getSaibBusinessdataStr7() {
		return saibBusinessdataStr7;
	}

	public void setSaibBusinessdataStr8(String saibBusinessdataStr8) {
		this.saibBusinessdataStr8 = saibBusinessdataStr8;
	}

	@Column(name = "saib_businessdata_str8", nullable = true, length = 400)	
	public String getSaibBusinessdataStr8() {
		return saibBusinessdataStr8;
	}

	public void setSaibBusinessdataStr9(String saibBusinessdataStr9) {
		this.saibBusinessdataStr9 = saibBusinessdataStr9;
	}

	@Column(name = "saib_businessdata_str9", nullable = true, length = 400)	
	public String getSaibBusinessdataStr9() {
		return saibBusinessdataStr9;
	}

	public void setSaibBusinessdataStr10(String saibBusinessdataStr10) {
		this.saibBusinessdataStr10 = saibBusinessdataStr10;
	}

	@Column(name = "saib_businessdata_str10", nullable = true, length = 400)	
	public String getSaibBusinessdataStr10() {
		return saibBusinessdataStr10;
	}

	public void setSaibBusinessdataDate1(Date saibBusinessdataDate1) {
		this.saibBusinessdataDate1 = saibBusinessdataDate1;
	}

	@Column(name = "saib_businessdata_date1", nullable = true, length = 0)	
	public Date getSaibBusinessdataDate1() {
		return saibBusinessdataDate1;
	}

	public void setSaibBusinessdataDate2(Date saibBusinessdataDate2) {
		this.saibBusinessdataDate2 = saibBusinessdataDate2;
	}

	@Column(name = "saib_businessdata_date2", nullable = true, length = 0)	
	public Date getSaibBusinessdataDate2() {
		return saibBusinessdataDate2;
	}

	public void setSaibBusinessdataDate3(Date saibBusinessdataDate3) {
		this.saibBusinessdataDate3 = saibBusinessdataDate3;
	}

	@Column(name = "saib_businessdata_date3", nullable = true, length = 0)	
	public Date getSaibBusinessdataDate3() {
		return saibBusinessdataDate3;
	}

	public void setSaibBusinessdataDate4(Date saibBusinessdataDate4) {
		this.saibBusinessdataDate4 = saibBusinessdataDate4;
	}

	@Column(name = "saib_businessdata_date4", nullable = true, length = 0)	
	public Date getSaibBusinessdataDate4() {
		return saibBusinessdataDate4;
	}

	public void setSaibBusinessdataDate5(Date saibBusinessdataDate5) {
		this.saibBusinessdataDate5 = saibBusinessdataDate5;
	}

	@Column(name = "saib_businessdata_date5", nullable = true, length = 0)	
	public Date getSaibBusinessdataDate5() {
		return saibBusinessdataDate5;
	}

	public void setSaibBusinessdataDate6(Date saibBusinessdataDate6) {
		this.saibBusinessdataDate6 = saibBusinessdataDate6;
	}

	@Column(name = "saib_businessdata_date6", nullable = true, length = 0)	
	public Date getSaibBusinessdataDate6() {
		return saibBusinessdataDate6;
	}

	public void setSaibBusinessdataDate7(Date saibBusinessdataDate7) {
		this.saibBusinessdataDate7 = saibBusinessdataDate7;
	}

	@Column(name = "saib_businessdata_date7", nullable = true, length = 0)	
	public Date getSaibBusinessdataDate7() {
		return saibBusinessdataDate7;
	}

	public void setSaibBusinessdataDate8(Date saibBusinessdataDate8) {
		this.saibBusinessdataDate8 = saibBusinessdataDate8;
	}

	@Column(name = "saib_businessdata_date8", nullable = true, length = 0)	
	public Date getSaibBusinessdataDate8() {
		return saibBusinessdataDate8;
	}

	public void setSaibBusinessdataDate9(Date saibBusinessdataDate9) {
		this.saibBusinessdataDate9 = saibBusinessdataDate9;
	}

	@Column(name = "saib_businessdata_date9", nullable = true, length = 0)	
	public Date getSaibBusinessdataDate9() {
		return saibBusinessdataDate9;
	}

	public void setSaibBusinessdataDate10(Date saibBusinessdataDate10) {
		this.saibBusinessdataDate10 = saibBusinessdataDate10;
	}

	@Column(name = "saib_businessdata_date10", nullable = true, length = 0)	
	public Date getSaibBusinessdataDate10() {
		return saibBusinessdataDate10;
	}

	public void setSaibBusinessdataInt1(Integer saibBusinessdataInt1) {
		this.saibBusinessdataInt1 = saibBusinessdataInt1;
	}

	@Column(name = "saib_businessdata_int1", nullable = true, length = 11)	
	public Integer getSaibBusinessdataInt1() {
		return saibBusinessdataInt1;
	}

	public void setSaibBusinessdataInt2(Integer saibBusinessdataInt2) {
		this.saibBusinessdataInt2 = saibBusinessdataInt2;
	}

	@Column(name = "saib_businessdata_int2", nullable = true, length = 11)	
	public Integer getSaibBusinessdataInt2() {
		return saibBusinessdataInt2;
	}

	public void setSaibBusinessdataInt3(Integer saibBusinessdataInt3) {
		this.saibBusinessdataInt3 = saibBusinessdataInt3;
	}

	@Column(name = "saib_businessdata_int3", nullable = true, length = 11)	
	public Integer getSaibBusinessdataInt3() {
		return saibBusinessdataInt3;
	}

	public void setSaibBusinessdataInt4(Integer saibBusinessdataInt4) {
		this.saibBusinessdataInt4 = saibBusinessdataInt4;
	}

	@Column(name = "saib_businessdata_int4", nullable = true, length = 11)	
	public Integer getSaibBusinessdataInt4() {
		return saibBusinessdataInt4;
	}

	public void setSaibBusinessdataInt5(Integer saibBusinessdataInt5) {
		this.saibBusinessdataInt5 = saibBusinessdataInt5;
	}

	@Column(name = "saib_businessdata_int5", nullable = true, length = 11)	
	public Integer getSaibBusinessdataInt5() {
		return saibBusinessdataInt5;
	}

	public void setSaibBusinessdataInt6(Integer saibBusinessdataInt6) {
		this.saibBusinessdataInt6 = saibBusinessdataInt6;
	}

	@Column(name = "saib_businessdata_int6", nullable = true, length = 11)	
	public Integer getSaibBusinessdataInt6() {
		return saibBusinessdataInt6;
	}

	public void setSaibBusinessdataInt7(Integer saibBusinessdataInt7) {
		this.saibBusinessdataInt7 = saibBusinessdataInt7;
	}

	@Column(name = "saib_businessdata_int7", nullable = true, length = 11)	
	public Integer getSaibBusinessdataInt7() {
		return saibBusinessdataInt7;
	}

	public void setSaibBusinessdataInt8(Integer saibBusinessdataInt8) {
		this.saibBusinessdataInt8 = saibBusinessdataInt8;
	}

	@Column(name = "saib_businessdata_int8", nullable = true, length = 11)	
	public Integer getSaibBusinessdataInt8() {
		return saibBusinessdataInt8;
	}

	public void setSaibBusinessdataInt9(Integer saibBusinessdataInt9) {
		this.saibBusinessdataInt9 = saibBusinessdataInt9;
	}

	@Column(name = "saib_businessdata_int9", nullable = true, length = 11)	
	public Integer getSaibBusinessdataInt9() {
		return saibBusinessdataInt9;
	}

	public void setSaibBusinessdataInt10(Integer saibBusinessdataInt10) {
		this.saibBusinessdataInt10 = saibBusinessdataInt10;
	}

	@Column(name = "saib_businessdata_int10", nullable = true, length = 11)	
	public Integer getSaibBusinessdataInt10() {
		return saibBusinessdataInt10;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creation_date", nullable = false, length = 0)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_by", precision = 22, scale = 0)
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "last_updated_by", precision = 22, scale = 0)
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "last_update_date", nullable = false, length = 0)
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name = "last_update_login", precision = 22, scale = 0)
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}
}
