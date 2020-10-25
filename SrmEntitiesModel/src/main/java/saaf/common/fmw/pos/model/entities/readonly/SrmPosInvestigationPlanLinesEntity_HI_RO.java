package saaf.common.fmw.pos.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * SrmPosInvestigationPlanLinesEntity_HI_RO Entity Object
 * Tue Nov 27 14:55:00 CST 2018  Auto Generate
 */

public class SrmPosInvestigationPlanLinesEntity_HI_RO {
	//LOV:计划考察单号
	public static String LOV_INVESTIGATION_QUERY_SQL = "SELECT\r\n"
			+ "ipl.supplier_id supplierId,\r\n"
			+ "ip.plan_number planNumber,\r\n"
			+ "ipl.investigation_plan_id investigationPlanId,\r\n"
			+ "ipl.investigation_plan_lines_id investigationPlanLinesId,\r\n"
			+ "ipl.creation_date creationDate\r\n"
			+ "FROM\r\n"
			+ "srm_pos_investigation_plan_lines ipl  \r\n"
			+ "LEFT JOIN srm_pos_investigation_plan ip  ON ipl.investigation_plan_id = ip.investigation_plan_id\r\n"
			+ "WHERE 1 = 1 ";
	//查找计划考察品类
	public static String QUERY_PLAN_CATEGORIES_SQL = "SELECT ipc.category_id AS categoryId,\n" +
			"bc.category_code        AS categoryCode,\n" +
			"bc.category_name        AS categoryName,\n" +
			"lrc.selected_flag        AS selectedFlag\n" +
			"FROM srm_pos_investigation_plan_categories ipc  \n" +
			"LEFT JOIN srm_pos_investigation_plan_lines ipl ON ipl.investigation_plan_lines_id = ipc.investigation_plan_lines_id\n" +
			"LEFT JOIN srm_base_categories bc ON ipc.category_id = bc.category_id\n" +
			"LEFT JOIN srm_pos_locale_review_cates lrc ON ipc.category_id = lrc.category_id and lrc.locale_review_id=:localeReviewId \n " +
			"WHERE 1=1 \n";


    private Integer investigationPlanLinesId; //考察计划行ID
    private Integer investigationPlanId; //考察计划ID,关联表:srm_pos_investigation_plan
    private Integer supplierId; //供应商ID,关联表:srm_pos_supplier_info
    private String plannedBy; //计划考察人
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date plannedTime; //计划考察时间
    private String linesDescription; //行备注
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
	private Integer categoryId;//品类Id
    private String fullCategoryName;//品类全名
	private String siteName;//地点名称
	private Integer supplierSiteId;//地点Id
	private Integer orgId;//业务实体ID
	private String instName;//业务实体名称
	private String fullCategoryCode;//业务实体编号
	private Integer investigationPlanSitesId;//地点行ID
	private Integer investigationPlanCategoriesId;//品类行ID

	private String planNumber;//计划考察单号
	private String categoryCode;
	private String categoryName;
	private String selectedFlag;

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

	public String getSelectedFlag() {
		return selectedFlag;
	}

	public void setSelectedFlag(String selectedFlag) {
		this.selectedFlag = selectedFlag;
	}

	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	public Integer getInvestigationPlanSitesId() {
		return investigationPlanSitesId;
	}

	public void setInvestigationPlanSitesId(Integer investigationPlanSitesId) {
		this.investigationPlanSitesId = investigationPlanSitesId;
	}

	public Integer getInvestigationPlanCategoriesId() {
		return investigationPlanCategoriesId;
	}

	public void setInvestigationPlanCategoriesId(Integer investigationPlanCategoriesId) {
		this.investigationPlanCategoriesId = investigationPlanCategoriesId;
	}

	public String getFullCategoryCode() {
		return fullCategoryCode;
	}

	public void setFullCategoryCode(String fullCategoryCode) {
		this.fullCategoryCode = fullCategoryCode;
	}

	public String getLinesDescription() {
		return linesDescription;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSupplierSiteId() {
		return supplierSiteId;
	}

	public void setSupplierSiteId(Integer supplierSiteId) {
		this.supplierSiteId = supplierSiteId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getFullCategoryName() {
		return fullCategoryName;
	}

	public void setFullCategoryName(String fullCategoryName) {
		this.fullCategoryName = fullCategoryName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public void setInvestigationPlanLinesId(Integer investigationPlanLinesId) {
		this.investigationPlanLinesId = investigationPlanLinesId;
	}

	
	public Integer getInvestigationPlanLinesId() {
		return investigationPlanLinesId;
	}

	public void setInvestigationPlanId(Integer investigationPlanId) {
		this.investigationPlanId = investigationPlanId;
	}

	
	public Integer getInvestigationPlanId() {
		return investigationPlanId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setPlannedBy(String plannedBy) {
		this.plannedBy = plannedBy;
	}

	
	public String getPlannedBy() {
		return plannedBy;
	}

	public void setPlannedTime(Date plannedTime) {
		this.plannedTime = plannedTime;
	}

	
	public Date getPlannedTime() {
		return plannedTime;
	}

	public void setLinesDescription(String linesDescription) {
		this.linesDescription = linesDescription;
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
