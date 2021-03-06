package saaf.common.fmw.po.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPoRecruitmentLineEntity_HI_RO Entity Object
 * Wed Jul 29 14:27:45 CST 2020  Auto Generate
 */

public class SrmPoRecruitmentLineEntity_HI_RO {
    private Integer recruitmentLineId;
    private Integer recruitmentHeaderId;
    private String recruitmentDescription;
    private Integer demandNum;
    private Integer actualInterviewsNum;
    private Integer registrationNum;
    private Integer categoryId;
    @JSONField(format="yyyy-MM-dd")
    private Date demandDate;
    @JSONField(format="yyyy-MM-dd")
    private Date startDate;
    @JSONField(format="yyyy-MM-dd")
    private Date endDate;
    private Integer originalDemandNum;
	@JSONField(format="yyyy-MM-dd")
    private Date originalDemandDate;
    private String rmk;
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
    private BigDecimal attribute16;
    private BigDecimal attribute17;
    private BigDecimal attribute18;
    private BigDecimal attribute19;
    private BigDecimal attribute20;
    private BigDecimal attribute21;
    private BigDecimal attribute22;
    private BigDecimal attribute23;
    private BigDecimal attribute24;
    private BigDecimal attribute25;
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
	private String status;


	public static String QUERY_RECRUITMENT_LINE ="select sprl.recruitment_line_id as recruitmentLineId,\n" +
			"       sprl.recruitment_header_id as recruitmentHeaderId,\n" +
			"       sprl.line_status as lineStatus,\n" +
			"       slv.meaning as lineStatusName,\n" +
			"       sprl.recruitment_description as recruitmentDescription,\n" +
			"       sprl.demand_num as demandNum,\n" +
			"       sprl.actual_interviews_num as actualInterviewsNum,\n" +
			"       sprl.registration_num as registrationNum,\n" +
			"       sprl.category_id as categoryId,\n" +
			"       sbc.full_category_code as categoryCode,\n" +
			"       sbc.full_category_name as categoryName,\n" +
			"       sprl.demand_date as demandDate,\n" +
			"       sprl.start_date as startDate,\n" +
			"       sprl.end_date as endDate,\n" +
			"       sprl.original_demand_num as originalDemandNum,\n" +
			"       sprl.original_demand_date as originalDemandDate,\n" +
			"       sprl.rmk as lineRmk\n" +
			"  from srm_po_recruitment_line sprl\n" +
			"  left join saaf_lookup_values slv on slv.lookup_type='REC_LINE_STATUS' and slv.lookup_code=sprl.line_status\n" +
			"  left join srm_base_categories sbc on sbc.category_id=sprl.category_id\n" +
			" where 1=1 \n";


	private String lineStatus;
	private String lineStatusName;
	private String categoryCode;
	private String categoryName;
	private String lineRmk;

	public String getLineRmk() {
		return lineRmk;
	}

	public void setLineRmk(String lineRmk) {
		this.lineRmk = lineRmk;
	}

	public String getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public String getLineStatusName() {
		return lineStatusName;
	}

	public void setLineStatusName(String lineStatusName) {
		this.lineStatusName = lineStatusName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRecruitmentDescription(String recruitmentDescription) {
		this.recruitmentDescription = recruitmentDescription;
	}

	
	public String getRecruitmentDescription() {
		return recruitmentDescription;
	}


	public Integer getRecruitmentLineId() {
		return recruitmentLineId;
	}

	public void setRecruitmentLineId(Integer recruitmentLineId) {
		this.recruitmentLineId = recruitmentLineId;
	}

	public Integer getRecruitmentHeaderId() {
		return recruitmentHeaderId;
	}

	public void setRecruitmentHeaderId(Integer recruitmentHeaderId) {
		this.recruitmentHeaderId = recruitmentHeaderId;
	}

	public Integer getDemandNum() {
		return demandNum;
	}

	public void setDemandNum(Integer demandNum) {
		this.demandNum = demandNum;
	}

	public Integer getActualInterviewsNum() {
		return actualInterviewsNum;
	}

	public void setActualInterviewsNum(Integer actualInterviewsNum) {
		this.actualInterviewsNum = actualInterviewsNum;
	}

	public Integer getRegistrationNum() {
		return registrationNum;
	}

	public void setRegistrationNum(Integer registrationNum) {
		this.registrationNum = registrationNum;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getOriginalDemandNum() {
		return originalDemandNum;
	}

	public void setOriginalDemandNum(Integer originalDemandNum) {
		this.originalDemandNum = originalDemandNum;
	}

	public Date getOriginalDemandDate() {
		return originalDemandDate;
	}

	public void setOriginalDemandDate(Date originalDemandDate) {
		this.originalDemandDate = originalDemandDate;
	}

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

	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	
	public Date getDemandDate() {
		return demandDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	public Date getStartDate() {
		return startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	public Date getEndDate() {
		return endDate;
	}



	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	
	public String getRmk() {
		return rmk;
	}



	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	public Date getCreationDate() {
		return creationDate;
	}



	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}



	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	
	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	
	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	
	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	
	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	
	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	
	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	
	public String getAttribute15() {
		return attribute15;
	}

	public void setAttribute16(BigDecimal attribute16) {
		this.attribute16 = attribute16;
	}

	
	public BigDecimal getAttribute16() {
		return attribute16;
	}

	public void setAttribute17(BigDecimal attribute17) {
		this.attribute17 = attribute17;
	}

	
	public BigDecimal getAttribute17() {
		return attribute17;
	}

	public void setAttribute18(BigDecimal attribute18) {
		this.attribute18 = attribute18;
	}

	
	public BigDecimal getAttribute18() {
		return attribute18;
	}

	public void setAttribute19(BigDecimal attribute19) {
		this.attribute19 = attribute19;
	}

	
	public BigDecimal getAttribute19() {
		return attribute19;
	}

	public void setAttribute20(BigDecimal attribute20) {
		this.attribute20 = attribute20;
	}

	
	public BigDecimal getAttribute20() {
		return attribute20;
	}

	public void setAttribute21(BigDecimal attribute21) {
		this.attribute21 = attribute21;
	}

	
	public BigDecimal getAttribute21() {
		return attribute21;
	}

	public void setAttribute22(BigDecimal attribute22) {
		this.attribute22 = attribute22;
	}

	
	public BigDecimal getAttribute22() {
		return attribute22;
	}

	public void setAttribute23(BigDecimal attribute23) {
		this.attribute23 = attribute23;
	}

	
	public BigDecimal getAttribute23() {
		return attribute23;
	}

	public void setAttribute24(BigDecimal attribute24) {
		this.attribute24 = attribute24;
	}

	
	public BigDecimal getAttribute24() {
		return attribute24;
	}

	public void setAttribute25(BigDecimal attribute25) {
		this.attribute25 = attribute25;
	}

	
	public BigDecimal getAttribute25() {
		return attribute25;
	}

	public void setAttribute26(Date attribute26) {
		this.attribute26 = attribute26;
	}

	
	public Date getAttribute26() {
		return attribute26;
	}

	public void setAttribute27(Date attribute27) {
		this.attribute27 = attribute27;
	}

	
	public Date getAttribute27() {
		return attribute27;
	}

	public void setAttribute28(Date attribute28) {
		this.attribute28 = attribute28;
	}

	
	public Date getAttribute28() {
		return attribute28;
	}

	public void setAttribute29(Date attribute29) {
		this.attribute29 = attribute29;
	}

	
	public Date getAttribute29() {
		return attribute29;
	}

	public void setAttribute30(Date attribute30) {
		this.attribute30 = attribute30;
	}

	
	public Date getAttribute30() {
		return attribute30;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
