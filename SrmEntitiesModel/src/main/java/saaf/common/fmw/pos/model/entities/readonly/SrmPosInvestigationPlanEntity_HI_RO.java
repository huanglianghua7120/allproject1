package saaf.common.fmw.pos.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * SrmPosInvestigationPlanEntity_HI_RO Entity Object
 * Tue Nov 27 14:54:59 CST 2018  Auto Generate
 */

public class SrmPosInvestigationPlanEntity_HI_RO {

	// 考察计划分页查询SQL
	public static String PAGE_SQL =
					"SELECT ip.investigation_plan_id investigationPlanId\n" +
					"      ,ip.plan_name planName\n" +
					"      ,ip.plan_number planNumber\n" +
					"      ,ip.plan_status planStatus\n" +
					"      ,slv1.meaning planStatusStr\n" +
					"      ,ipl.supplier_id supplierId\n" +
					"      ,si.supplier_name supplierName\n" +
					"      ,ipl.investigation_plan_lines_id investigationPlanLinesId\n" +
					"      ,ipl.planned_by plannedBy\n" +
					"      ,ipl.planned_time plannedTime\n" +
					"      ,ip.description description\n" +
					"      ,round(to_number(ipl.planned_time - SYSDATE)) AS surplusDays\n" +
					"      ,lr.locale_review_id localReviewId\n" +
					"      ,decode(lr.locale_review_id, NULL, '未考察', '已考察') AS executStatus\n" +
					"      ,lr.locale_review_result localReviewResult\n" +
					"      ,slv2.meaning localReviewResultStr\n" +
					"      ,lr.review_date reviewDate\n" +
					"      ,(CASE\n" +
					"         WHEN lr.review_date IS NULL THEN\n" +
					"          ''\n" +
					"         WHEN round(to_number(ipl.planned_time - lr.review_date)) < 0 THEN\n" +
					"          '是'\n" +
					"         ELSE\n" +
					"          '否'\n" +
					"       END) delayOrNot\n" +
					"      ,lr.locale_review_number localReviewNumber\n" +
					"FROM   srm_pos_investigation_plan ip\n" +
					"LEFT   JOIN srm_pos_investigation_plan_lines ipl\n" +
					"ON     ip.investigation_plan_id = ipl.investigation_plan_id\n" +
					"LEFT   JOIN srm_pos_supplier_info si\n" +
					"ON     ipl.supplier_id = si.supplier_id\n" +
					"LEFT   JOIN saaf_lookup_values slv1\n" +
					"ON     ip.plan_status = slv1.lookup_code\n" +
					"AND    slv1.lookup_type = 'POS_APPROVAL_STATUS'\n" +
					"LEFT   JOIN srm_pos_locale_reviews lr\n" +
					"ON     lr.investigation_plan_lines_id = ipl.investigation_plan_lines_id\n" +
					"LEFT   JOIN saaf_lookup_values slv2\n" +
					"ON     lr.locale_review_result = slv2.lookup_code\n" +
					"AND    slv2.lookup_type = 'POS_EXAMINE_RESULT'\n" +
					"WHERE  1 = 1\n";

	// 根据ID查找考察计划详细信息SQL
	public static String INVESTIGATION_PLAN_INFO_SQL = " SELECT ip.investigation_plan_id investigationPlanId,\n" +
			"ip.plan_name planName,\n" +
			"ip.plan_number planNumber,\n" +
			"ip.plan_status planStatus,\n" +
			"slv1.meaning planStatusStr,\n" +
			"ip.creation_date creationDate,\n" +
			"ip.created_by creatBy,\n" +
			"su.user_full_name  creatName,\n" +
			"ip.description description,\n" +
			"ipl.investigation_plan_lines_id investigationPlanLinesId,\n" +
			"ipl.supplier_id supplierId,\n" +
			"si.supplier_name supplierName,\n" +
			"ipl.planned_by plannedBy,\n" +
			"ipl.planned_time plannedTime,\n" +
			"ipl.lines_description linesDescription\n" +
			"FROM srm_pos_investigation_plan ip\n" +
			"\tLEFT JOIN srm_pos_investigation_plan_lines ipl ON ip.investigation_plan_id = ipl.investigation_plan_id\n" +
			"\tLEFT JOIN srm_pos_supplier_info si ON ipl.supplier_id = si.supplier_id \n" +
			"\tLEFT JOIN saaf_lookup_values slv1 ON ip.plan_status = slv1.lookup_code AND slv1.lookup_type = 'POS_APPROVAL_STATUS'\n" +
			"\tLEFT JOIN saaf_users su ON su.user_id = ip.created_by\n" +
			"Where 1 = 1 \n";


    private Integer investigationPlanId; //考察计划ID
    private String planName; //计划名称
    private String planNumber; //单据编号
    private String planStatus; //单据状态（POS_APPROVAL_STATUS）
    private String description; //备注说明
    private Integer versionNum;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
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
    private String planStatusStr;//单据状态
    private String supplierName;//供应商名称
    private String plannedBy;//考察人
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date plannedTime;//考察时间
    private BigDecimal surplusDays;//剩余天数
	private Integer supplierId;//供应商Id;
	private Integer localReviewId;//现场考察Id
	private String executStatus;//执行状态
	private String localReviewResult;//考察结果
	private String localReviewResultStr;//考察结果显示
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date reviewDate;//现场考察时间
	private String delayOrNot;//是否延期
	private String localReviewNumber;//现场考察单据号（执行单据号）
	private List<SrmPosInvestigationPlanLinesEntity_HI_RO> categoryAndSitesList;//考察地点和品类的信息
	private String linesDescription;//行备注
	private Integer creatBy;//创建人ID
	private String creatName;//创建人名称
	private Integer investigationPlanLinesId;//行ID
	private List<SrmPosInvestigationPlanLinesEntity_HI_RO> sitesInfoList;//考察地点信息
	private List<SrmPosInvestigationPlanLinesEntity_HI_RO> categoriesInfoList;//考察品类的信息

	public List<SrmPosInvestigationPlanLinesEntity_HI_RO> getSitesInfoList() {
		return sitesInfoList;
	}

	public void setSitesInfoList(List<SrmPosInvestigationPlanLinesEntity_HI_RO> sitesInfoList) {
		this.sitesInfoList = sitesInfoList;
	}

	public List<SrmPosInvestigationPlanLinesEntity_HI_RO> getCategoriesInfoList() {
		return categoriesInfoList;
	}

	public void setCategoriesInfoList(List<SrmPosInvestigationPlanLinesEntity_HI_RO> categoriesInfoList) {
		this.categoriesInfoList = categoriesInfoList;
	}

	public Integer getInvestigationPlanLinesId() {
		return investigationPlanLinesId;
	}

	public void setInvestigationPlanLinesId(Integer investigationPlanLinesId) {
		this.investigationPlanLinesId = investigationPlanLinesId;
	}

	public Integer getCreatBy() {
		return creatBy;
	}

	public void setCreatBy(Integer creatBy) {
		this.creatBy = creatBy;
	}

	public String getCreatName() {
		return creatName;
	}

	public void setCreatName(String creatName) {
		this.creatName = creatName;
	}

	public String getLinesDescription() {
		return linesDescription;
	}

	public void setLinesDescription(String linesDescription) {
		this.linesDescription = linesDescription;
	}

	public List<SrmPosInvestigationPlanLinesEntity_HI_RO> getCategoryAndSitesList() {
		return categoryAndSitesList;
	}

	public void setCategoryAndSitesList(List<SrmPosInvestigationPlanLinesEntity_HI_RO> categoryAndSitesList) {
		this.categoryAndSitesList = categoryAndSitesList;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getLocalReviewId() {
		return localReviewId;
	}

	public void setLocalReviewId(Integer localReviewId) {
		this.localReviewId = localReviewId;
	}

	public String getExecutStatus() {
		return executStatus;
	}

	public void setExecutStatus(String executStatus) {
		this.executStatus = executStatus;
	}

	public String getLocalReviewResult() {
		return localReviewResult;
	}

	public void setLocalReviewResult(String localReviewResult) {
		this.localReviewResult = localReviewResult;
	}

	public String getLocalReviewResultStr() {
		return localReviewResultStr;
	}

	public void setLocalReviewResultStr(String localReviewResultStr) {
		this.localReviewResultStr = localReviewResultStr;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getDelayOrNot() {
		return delayOrNot;
	}

	public void setDelayOrNot(String delayOrNot) {
		this.delayOrNot = delayOrNot;
	}

	public String getLocalReviewNumber() {
		return localReviewNumber;
	}

	public void setLocalReviewNumber(String localReviewNumber) {
		this.localReviewNumber = localReviewNumber;
	}

	public BigDecimal getSurplusDays() {
		return surplusDays;
	}

	public void setSurplusDays(BigDecimal surplusDays) {
		this.surplusDays = surplusDays;
	}

	public String getPlanStatusStr() {
		return planStatusStr;
	}

	public void setPlanStatusStr(String planStatusStr) {
		this.planStatusStr = planStatusStr;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPlannedBy() {
		return plannedBy;
	}

	public void setPlannedBy(String plannedBy) {
		this.plannedBy = plannedBy;
	}

	public Date getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(Date plannedTime) {
		this.plannedTime = plannedTime;
	}

	public void setInvestigationPlanId(Integer investigationPlanId) {
		this.investigationPlanId = investigationPlanId;
	}

	
	public Integer getInvestigationPlanId() {
		return investigationPlanId;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	
	public String getPlanName() {
		return planName;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	
	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	
	public String getPlanStatus() {
		return planStatus;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getDescription() {
		return description;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
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

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
