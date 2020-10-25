package saaf.common.fmw.pos.model.entities;

import javax.persistence.*;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPosReasonsEntity_HI Entity Object
 * Wed Nov 15 10:54:46 CST 2017  Auto Generate
 */
@Entity
@Table(name = "srm_pos_reasons")
public class SrmPosReasonsEntity_HI {
    private Integer reasonsId;
    private String docTable;
    private Integer docId;
    private String reasonCode;
    private String reasonDescription;
    private String selectedFlag;
    private String isBlanklistFlag;
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

    public void setReasonsId(Integer reasonsId) {
	this.reasonsId = reasonsId;
    }

    @Id
    @SequenceGenerator(name = "SRM_POS_REASONS_S", sequenceName = "SRM_POS_REASONS_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_POS_REASONS_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "reasons_id", nullable = false, length = 11)    
    public Integer getReasonsId() {
	return reasonsId;
    }

    public void setDocTable(String docTable) {
	this.docTable = docTable;
    }

    @Column(name = "doc_table", nullable = true, length = 50)    
    public String getDocTable() {
	return docTable;
    }

    public void setDocId(Integer docId) {
	this.docId = docId;
    }

    @Column(name = "doc_id", nullable = false, length = 11)    
    public Integer getDocId() {
	return docId;
    }

    public void setReasonCode(String reasonCode) {
	this.reasonCode = reasonCode;
    }

    @Column(name = "reason_code", nullable = true, length = 100)    
    public String getReasonCode() {
	return reasonCode;
    }

    public void setReasonDescription(String reasonDescription) {
	this.reasonDescription = reasonDescription;
    }

    @Column(name = "reason_description", nullable = true, length = 240)    
    public String getReasonDescription() {
	return reasonDescription;
    }

    public void setSelectedFlag(String selectedFlag) {
	this.selectedFlag = selectedFlag;
    }

    @Column(name = "selected_flag", nullable = true, length = 10)    
    public String getSelectedFlag() {
	return selectedFlag;
    }

    public void setIsBlanklistFlag(String isBlanklistFlag) {
	this.isBlanklistFlag = isBlanklistFlag;
    }

    @Column(name = "is_blanklist_flag", nullable = true, length = 10)    
    public String getIsBlanklistFlag() {
	return isBlanklistFlag;
    }

    public void setVersionNum(Integer versionNum) {
	this.versionNum = versionNum;
    }

    @Column(name = "version_num", nullable = true, length = 11)    
    public Integer getVersionNum() {
	return versionNum;
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

    @Column(name = "created_by", nullable = false, length = 11)    
    public Integer getCreatedBy() {
	return createdBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
	this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "last_updated_by", nullable = false, length = 11)    
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

    @Column(name = "last_update_login", nullable = true, length = 11)    
    public Integer getLastUpdateLogin() {
	return lastUpdateLogin;
    }

    public void setAttributeCategory(String attributeCategory) {
	this.attributeCategory = attributeCategory;
    }

    @Column(name = "attribute_category", nullable = true, length = 30)    
    public String getAttributeCategory() {
	return attributeCategory;
    }

    public void setAttribute1(String attribute1) {
	this.attribute1 = attribute1;
    }

    @Column(name = "attribute1", nullable = true, length = 240)    
    public String getAttribute1() {
	return attribute1;
    }

    public void setAttribute2(String attribute2) {
	this.attribute2 = attribute2;
    }

    @Column(name = "attribute2", nullable = true, length = 240)    
    public String getAttribute2() {
	return attribute2;
    }

    public void setAttribute3(String attribute3) {
	this.attribute3 = attribute3;
    }

    @Column(name = "attribute3", nullable = true, length = 240)    
    public String getAttribute3() {
	return attribute3;
    }

    public void setAttribute4(String attribute4) {
	this.attribute4 = attribute4;
    }

    @Column(name = "attribute4", nullable = true, length = 240)    
    public String getAttribute4() {
	return attribute4;
    }

    public void setAttribute5(String attribute5) {
	this.attribute5 = attribute5;
    }

    @Column(name = "attribute5", nullable = true, length = 240)    
    public String getAttribute5() {
	return attribute5;
    }

    public void setAttribute6(String attribute6) {
	this.attribute6 = attribute6;
    }

    @Column(name = "attribute6", nullable = true, length = 240)    
    public String getAttribute6() {
	return attribute6;
    }

    public void setAttribute7(String attribute7) {
	this.attribute7 = attribute7;
    }

    @Column(name = "attribute7", nullable = true, length = 240)    
    public String getAttribute7() {
	return attribute7;
    }

    public void setAttribute8(String attribute8) {
	this.attribute8 = attribute8;
    }

    @Column(name = "attribute8", nullable = true, length = 240)    
    public String getAttribute8() {
	return attribute8;
    }

    public void setAttribute9(String attribute9) {
	this.attribute9 = attribute9;
    }

    @Column(name = "attribute9", nullable = true, length = 240)    
    public String getAttribute9() {
	return attribute9;
    }

    public void setAttribute10(String attribute10) {
	this.attribute10 = attribute10;
    }

    @Column(name = "attribute10", nullable = true, length = 240)    
    public String getAttribute10() {
	return attribute10;
    }

    private Integer operatorUserId;
    public void setOperatorUserId(Integer operatorUserId) {
          this.operatorUserId = operatorUserId;
      }

      @Transient
      public Integer getOperatorUserId() {
          return operatorUserId;
      }
}

