package saaf.common.fmw.po.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPoSubprojectNumEntity_HI Entity Object
 * Sat Jun 27 14:50:55 CST 2020  Auto Generate
 */
@Entity
@Table(name="SRM_PO_SUBPROJECT_NUM")
public class SrmPoSubprojectNumEntity_HI {
    private Integer subprojectId;
    private String subprojectCode;
    private String subprojectName;
    private Integer technicalId;
    private String sourceId;
    private String enable;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date effectiveDate;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date expirationDate;
    private Integer versionNum;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdatedBy;
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
    private String attribute11;
    private String attribute12;
    private String attribute13;
    private String attribute14;
    private String attribute15;
    private Integer attribute16;
    private Integer attribute17;
    private Integer attribute18;
    private Integer attribute19;
    private Integer attribute20;
    private Integer attribute21;
    private Integer attribute22;
    private Integer attribute23;
    private Integer attribute24;
    private Integer attribute25;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date attribute26;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date attribute27;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date attribute28;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date attribute29;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date attribute30;
    private Integer operatorUserId;

	public void setSubprojectId(Integer subprojectId) {
		this.subprojectId = subprojectId;
	}
    @Id
    @SequenceGenerator(name = "SRM_PO_SUBPROJECT_NUM_S", sequenceName = "SRM_PO_SUBPROJECT_NUM_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_PO_SUBPROJECT_NUM_S", strategy = GenerationType.SEQUENCE)
	@Column(name="subproject_id", nullable=false, length=22)	
	public Integer getSubprojectId() {
		return subprojectId;
	}

	public void setSubprojectCode(String subprojectCode) {
		this.subprojectCode = subprojectCode;
	}

	@Column(name="subproject_code", nullable=false, length=30)	
	public String getSubprojectCode() {
		return subprojectCode;
	}

	public void setSubprojectName(String subprojectName) {
		this.subprojectName = subprojectName;
	}

	@Column(name="subproject_name", nullable=false, length=240)	
	public String getSubprojectName() {
		return subprojectName;
	}

	public void setTechnicalId(Integer technicalId) {
		this.technicalId = technicalId;
	}

	@Column(name="technical_id", nullable=false, length=22)	
	public Integer getTechnicalId() {
		return technicalId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name="source_id", nullable=true, length=30)	
	public String getSourceId() {
		return sourceId;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	@Column(name="enable", nullable=true, length=10)	
	public String getEnable() {
		return enable;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Column(name="effective_date", nullable=true, length=7)	
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Column(name="expiration_date", nullable=true, length=7)	
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
	@Column(name="version_num", nullable=false, length=22)	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name="creation_date", nullable=false, length=7)	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="created_by", nullable=false, length=22)	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name="last_update_date", nullable=false, length=7)	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name="last_updated_by", nullable=false, length=22)	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name="last_update_login", nullable=true, length=22)	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	@Column(name="attribute_category", nullable=true, length=30)	
	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	@Column(name="attribute1", nullable=true, length=240)	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	@Column(name="attribute2", nullable=true, length=240)	
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	@Column(name="attribute3", nullable=true, length=240)	
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	@Column(name="attribute4", nullable=true, length=240)	
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	@Column(name="attribute5", nullable=true, length=240)	
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	@Column(name="attribute6", nullable=true, length=240)	
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	@Column(name="attribute7", nullable=true, length=240)	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	@Column(name="attribute8", nullable=true, length=240)	
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	@Column(name="attribute9", nullable=true, length=240)	
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	@Column(name="attribute10", nullable=true, length=240)	
	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	@Column(name="attribute11", nullable=true, length=240)	
	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	@Column(name="attribute12", nullable=true, length=240)	
	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	@Column(name="attribute13", nullable=true, length=240)	
	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	@Column(name="attribute14", nullable=true, length=240)	
	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	@Column(name="attribute15", nullable=true, length=240)	
	public String getAttribute15() {
		return attribute15;
	}

	public void setAttribute16(Integer attribute16) {
		this.attribute16 = attribute16;
	}

	@Column(name="attribute16", nullable=true, length=22)	
	public Integer getAttribute16() {
		return attribute16;
	}

	public void setAttribute17(Integer attribute17) {
		this.attribute17 = attribute17;
	}

	@Column(name="attribute17", nullable=true, length=22)	
	public Integer getAttribute17() {
		return attribute17;
	}

	public void setAttribute18(Integer attribute18) {
		this.attribute18 = attribute18;
	}

	@Column(name="attribute18", nullable=true, length=22)	
	public Integer getAttribute18() {
		return attribute18;
	}

	public void setAttribute19(Integer attribute19) {
		this.attribute19 = attribute19;
	}

	@Column(name="attribute19", nullable=true, length=22)	
	public Integer getAttribute19() {
		return attribute19;
	}

	public void setAttribute20(Integer attribute20) {
		this.attribute20 = attribute20;
	}

	@Column(name="attribute20", nullable=true, length=22)	
	public Integer getAttribute20() {
		return attribute20;
	}

	public void setAttribute21(Integer attribute21) {
		this.attribute21 = attribute21;
	}

	@Column(name="attribute21", nullable=true, length=22)	
	public Integer getAttribute21() {
		return attribute21;
	}

	public void setAttribute22(Integer attribute22) {
		this.attribute22 = attribute22;
	}

	@Column(name="attribute22", nullable=true, length=22)	
	public Integer getAttribute22() {
		return attribute22;
	}

	public void setAttribute23(Integer attribute23) {
		this.attribute23 = attribute23;
	}

	@Column(name="attribute23", nullable=true, length=22)	
	public Integer getAttribute23() {
		return attribute23;
	}

	public void setAttribute24(Integer attribute24) {
		this.attribute24 = attribute24;
	}

	@Column(name="attribute24", nullable=true, length=22)	
	public Integer getAttribute24() {
		return attribute24;
	}

	public void setAttribute25(Integer attribute25) {
		this.attribute25 = attribute25;
	}

	@Column(name="attribute25", nullable=true, length=22)	
	public Integer getAttribute25() {
		return attribute25;
	}

	public void setAttribute26(Date attribute26) {
		this.attribute26 = attribute26;
	}

	@Column(name="attribute26", nullable=true, length=7)	
	public Date getAttribute26() {
		return attribute26;
	}

	public void setAttribute27(Date attribute27) {
		this.attribute27 = attribute27;
	}

	@Column(name="attribute27", nullable=true, length=7)	
	public Date getAttribute27() {
		return attribute27;
	}

	public void setAttribute28(Date attribute28) {
		this.attribute28 = attribute28;
	}

	@Column(name="attribute28", nullable=true, length=7)	
	public Date getAttribute28() {
		return attribute28;
	}

	public void setAttribute29(Date attribute29) {
		this.attribute29 = attribute29;
	}

	@Column(name="attribute29", nullable=true, length=7)	
	public Date getAttribute29() {
		return attribute29;
	}

	public void setAttribute30(Date attribute30) {
		this.attribute30 = attribute30;
	}

	@Column(name="attribute30", nullable=true, length=7)	
	public Date getAttribute30() {
		return attribute30;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
