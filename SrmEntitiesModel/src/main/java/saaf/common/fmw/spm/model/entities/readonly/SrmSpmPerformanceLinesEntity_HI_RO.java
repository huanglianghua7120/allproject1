package saaf.common.fmw.spm.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SrmSpmPerformanceLinesEntity_HI_RO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String QUERY_PERFORMANCE_LINES_LIST =
					"SELECT Spl.Performance_Line_Id AS performanceLineId \n" +
					"      ,Spl.Performance_Id      AS performanceId      \n" +
					"      ,Spl.Supplier_Id         AS supplierId         \n" +
					"      ,Psi.Supplier_Number     AS supplierNumber     \n" +
					"      ,Psi.Supplier_Name       AS supplierName       \n" +
					"      ,Spl.Score               AS score               \n" +
					"      ,Spl.Rank                AS rank               \n" +
					"      ,Spl.calculate_date      AS calculateDate     \n" +
					"      ,Spl.Performance_Comment AS performanceComment \n" +
					"      ,Spl.Improvement_Plan    AS improvementPlan    \n" +
					"      ,Spl.Confirm_Flag        AS confirmFlag    \n" +
					"      ,Spl.Confirm_Date        AS confirmDate    \n" +
					"      ,Spl.Manual_Score_Id_Str AS manualScoreIdStr    \n" +
					"      ,Spl.Version_Num         AS versionNum         \n" +
					"      ,Spl.Creation_Date       AS creationDate       \n" +
					"      ,Spl.Created_By          AS createdBy          \n" +
					"      ,Spl.Last_Updated_By     AS lastUpdatedBy     \n" +
					"      ,Spl.Last_Update_Date    AS lastUpdateDate    \n" +
					"      ,Spl.Last_Update_Login   AS lastUpdateLogin   \n" +
					"      ,Spl.Attribute_Category  AS attributeCategory  \n" +
					"      ,Spl.Attribute1          AS attribute1          \n" +
					"      ,Spl.Attribute2          AS attribute2          \n" +
					"      ,Spl.Attribute3          AS attribute3          \n" +
					"      ,Spl.Attribute4          AS attribute4          \n" +
					"      ,Spl.Attribute5          AS attribute5          \n" +
					"      ,Spl.Attribute6          AS attribute6          \n" +
					"      ,Spl.Attribute7          AS attribute7          \n" +
					"      ,Spl.Attribute8          AS attribute8          \n" +
					"      ,Spl.Attribute9          AS attribute9          \n" +
					"      ,Spl.Attribute10         AS attribute10         \n" +
					"      ,Spl.Attribute11         AS attribute11         \n" +
					"      ,Spl.Attribute12         AS attribute12         \n" +
					"      ,Spl.Attribute13         AS attribute13         \n" +
					"      ,Spl.Attribute14         AS attribute14         \n" +
					"      ,Spl.Attribute15         AS attribute15         \n" +
					"  FROM Srm_Spm_Performance_Lines Spl\n" +
					"  LEFT JOIN Srm_Pos_Supplier_Info Psi\n" +
					"    ON Spl.Supplier_Id = Psi.Supplier_Id\n" +
			        " WHERE 1=1 ";

	private Integer performanceLineId; //绩效行ID
	private Integer performanceId; //绩效ID
	private Integer supplierId; //供应商ID
	private BigDecimal score; //综合得分
	private Integer rank; //排名
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date calculateDate; //计算时间
	private String performanceComment; //绩效评语
	private String improvementPlan; //改进计划
	private String confirmFlag; //是否已确认
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date confirmDate; //确认日期
	private String manualScoreIdStr; //加减分id串
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
	private String attribute11;
	private String attribute12;
	private String attribute13;
	private String attribute14;
	private String attribute15;

	//别名
	private String supplierNumber;
	private String supplierName;

	private List<SrmSpmPerformanceEvaluateEntity_HI_RO> evaluateList;
	private List<SrmSpmPerformanceIndicatorsEntity_HI_RO> indicatorList;

	public Integer getPerformanceLineId() {
		return performanceLineId;
	}

	public void setPerformanceLineId(Integer performanceLineId) {
		this.performanceLineId = performanceLineId;
	}

	public Integer getPerformanceId() {
		return performanceId;
	}

	public void setPerformanceId(Integer performanceId) {
		this.performanceId = performanceId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Date getCalculateDate() {
		return calculateDate;
	}

	public void setCalculateDate(Date calculateDate) {
		this.calculateDate = calculateDate;
	}

	public String getPerformanceComment() {
		return performanceComment;
	}

	public void setPerformanceComment(String performanceComment) {
		this.performanceComment = performanceComment;
	}

	public String getImprovementPlan() {
		return improvementPlan;
	}

	public void setImprovementPlan(String improvementPlan) {
		this.improvementPlan = improvementPlan;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getManualScoreIdStr() {
		return manualScoreIdStr;
	}

	public void setManualScoreIdStr(String manualScoreIdStr) {
		this.manualScoreIdStr = manualScoreIdStr;
	}

	public Integer getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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

	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	public String getAttribute15() {
		return attribute15;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	public List<SrmSpmPerformanceEvaluateEntity_HI_RO> getEvaluateList() {
		return evaluateList;
	}

	public void setEvaluateList(List<SrmSpmPerformanceEvaluateEntity_HI_RO> evaluateList) {
		this.evaluateList = evaluateList;
	}

	public List<SrmSpmPerformanceIndicatorsEntity_HI_RO> getIndicatorList() {
		return indicatorList;
	}

	public void setIndicatorList(List<SrmSpmPerformanceIndicatorsEntity_HI_RO> indicatorList) {
		this.indicatorList = indicatorList;
	}
}
