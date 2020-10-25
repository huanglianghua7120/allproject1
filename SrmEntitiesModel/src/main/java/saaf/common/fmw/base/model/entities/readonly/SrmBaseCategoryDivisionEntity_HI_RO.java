package saaf.common.fmw.base.model.entities.readonly;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmBaseCategoryDivisionEntity_HI_RO Entity Object
 * Fri Mar 09 09:29:07 CST 2018  Auto Generate
 */

public class SrmBaseCategoryDivisionEntity_HI_RO {

	public static final String QUERY_POSITION_LOV="SELECT\n"+
	"t.lookup_code quartersCode,\n"+
	"t.meaning quartersName\n"+
	"FROM\n"+
	"saaf_lookup_values t\n"+
	"WHERE t.lookup_type = 'EMPLOYEE_POST'";

	public static final String QUERY_CATEGORY_DIVISION =
					"SELECT t.division_id divisionId\n" +
					"      ,t.org_id orgId\n" +
					"      ,org.inst_name instName\n" +
					"      ,t.employee_id employeeId\n" +
					"      ,emp.employee_number employeeNumber\n" +
					"      ,emp.employee_name employeeName\n" +
					"      ,(SELECT val.meaning\n" +
					"        FROM   saaf_lookup_values val\n" +
					"        WHERE  val.lookup_code = emp.quarters_code\n" +
					"        AND    val.lookup_type = 'EMPLOYEE_POST') quartersName\n" +
					"      ,t.category_id categoryId\n" +
					"      ,cgs.category_code categoryCode\n" +
					"      ,cgs.category_name categoryName\n" +
					"      ,cgs.full_category_code fullCategoryCode\n" +
					"      ,cgs.full_category_name fullCategoryName\n" +
					"      ,t.division_status divisionStatus\n" +
					"      ,val.meaning divisionStatusDesc\n" +
					"      ,t.start_date startDate\n" +
					"      ,t.end_date endDate\n" +
					"      ,t.user_id userId\n" +
					"      ,us.employee_name userName\n" +
					"      ,t.creation_date creationDate\n" +
					"      ,t.last_updated_by lastUpdatedBy\n" +
					"      ,us1.employee_name userName1\n" +
					"      ,t.last_update_date lastUpdateDate\n" +
					"FROM   srm_base_category_division t\n" +
					"LEFT   JOIN saaf_institution org\n" +
					"ON     t.org_id = org.inst_id\n" +
					"LEFT   JOIN saaf_employees emp\n" +
					"ON     t.employee_id = emp.employee_id\n" +
					"LEFT   JOIN srm_base_categories cgs\n" +
					"ON     t.category_id = cgs.category_id\n" +
					"LEFT   JOIN saaf_lookup_values val\n" +
					"ON     t.division_status = val.lookup_code\n" +
					"AND    val.lookup_type = 'CATEGORY_DIVISION_STATES'\n" +
					"LEFT   JOIN saaf_employees us\n" +
					"ON     t.created_by = us.user_id\n" +
					"LEFT   JOIN saaf_employees us1\n" +
					"ON     t.last_updated_by = us1.user_id\n" +
					"WHERE  1 = 1\n";

	public static final String QUERY_CATEGORY_DIVISION1 =
					"SELECT t.division_id divisionId\n" +
					"      ,t.org_id      orgId\n" +
					"      ,t.employee_id employeeId\n" +
					"      ,t.category_id categoryId\n" +
					"FROM   srm_base_category_division t\n" +
					"WHERE  1 = 1\n" +
					"AND    t.division_status = :divisionStatus\n";

	public static final String QUERY_CATEGORY_DIVISION_INFO =
					"SELECT cated.division_id divisionId\n" +
					"      ,cated.org_id orgId\n" +
					"FROM   srm_base_category_division cated\n" +
					"LEFT   JOIN saaf_users su\n" +
					"ON     cated. employee_id = su.employee_id\n" +
					"WHERE  1 = 1\n";

	private Integer divisionId; //分工ID
    private Integer orgId; //组织ID，关联表：SAAF_INSTITUTION
    private Integer departmentId; //部门ID（备用）
    private Integer positionId; //职位ID（备用）
    private Integer employeeId; //员工ID，关联表：SAAF_EMPLOYEES
    private Integer userId; //用户ID，关联表：SAAF_USERS
    private String allCategoryFlag; //是否可操作全品类5，关联表：SAAF_LOOKUP_VALUES（YSE_NO）
    private Integer categoryId; //分类ID（备用），关联表：SRM_BASE_CATEGORIES
    private String segment1; //一级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_BIG_CATEGORY）
    private String segment2; //二级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_MIDDLE_CATEGORY）
    private String segment3; //三级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_SMALL_CATEGORY）
    private String segment4; //四级分类编码（备用）
    private String divisionStatus; //分工状态，关联表：SAAF_LOOKUP_VALUES（BASE_DIVISION_STATUS）
	private String quartersName;//彈出框崗位名稱
	private String quartersCode;//彈出框崗位編碼
	private String instName;//组织名称
	private String employeeNumber;//人员编号
	private String employeeName;//人员名称
	private String positionName;//岗位
	private String categoryCode;//分类编码
	private String categoryName;//类别名称
	private String divisionStatusDesc;//状态描述
	private String userName;//用户名
	private String userName1;//用户名
	private String fullCategoryCode;//分类全编码
	private String fullCategoryName;//类别全名称
	private String ts;

	@JSONField(format = "yyyy-MM-dd")
	private Date startDateFrom; //生效日期从
	@JSONField(format = "yyyy-MM-dd")
	private Date startDateTo; //生效日期至
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate; //生效日期
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate; //失效日期
    private Integer versionNum;
	@JSONField(format = "yyyy-MM-dd")
	private Date creationDateFrom;//创建时间从
	@JSONField(format = "yyyy-MM-dd")
	private Date creationDateTo;//创建时间至
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDate;//创建时间
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd")
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
    private Integer operatorUserId;

	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}

	
	public Integer getDivisionId() {
		return divisionId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	
	public Integer getOrgId() {
		return orgId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	
	public Integer getPositionId() {
		return positionId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public Integer getUserId() {
		return userId;
	}

	public void setAllCategoryFlag(String allCategoryFlag) {
		this.allCategoryFlag = allCategoryFlag;
	}

	
	public String getAllCategoryFlag() {
		return allCategoryFlag;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}

	
	public String getSegment1() {
		return segment1;
	}

	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}

	
	public String getSegment2() {
		return segment2;
	}

	public void setSegment3(String segment3) {
		this.segment3 = segment3;
	}

	
	public String getSegment3() {
		return segment3;
	}

	public void setSegment4(String segment4) {
		this.segment4 = segment4;
	}

	
	public String getSegment4() {
		return segment4;
	}

	public void setDivisionStatus(String divisionStatus) {
		this.divisionStatus = divisionStatus;
	}

	
	public String getDivisionStatus() {
		return divisionStatus;
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

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
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

	public String getDivisionStatusDesc() {
		return divisionStatusDesc;
	}

	public void setDivisionStatusDesc(String divisionStatusDesc) {
		this.divisionStatusDesc = divisionStatusDesc;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName1() {
		return userName1;
	}

	public void setUserName1(String userName1) {
		this.userName1 = userName1;
	}

	public Date getCreationDateFrom() {
		return creationDateFrom;
	}

	public void setCreationDateFrom(Date creationDateFrom) {
		this.creationDateFrom = creationDateFrom;
	}

	public Date getCreationDateTo() {
		return creationDateTo;
	}

	public void setCreationDateTo(Date creationDateTo) {
		this.creationDateTo = creationDateTo;
	}

	public Date getStartDateFrom() {
		return startDateFrom;
	}

	public void setStartDateFrom(Date startDateFrom) {
		this.startDateFrom = startDateFrom;
	}

	public Date getStartDateTo() {
		return startDateTo;
	}

	public void setStartDateTo(Date startDateTo) {
		this.startDateTo = startDateTo;
	}

	public String getTs() {
		return "Y";
	}


	public String getFullCategoryCode() {
		return fullCategoryCode;
	}

	public void setFullCategoryCode(String fullCategoryCode) {
		this.fullCategoryCode = fullCategoryCode;
	}

	public String getFullCategoryName() {
		return fullCategoryName;
	}

	public void setFullCategoryName(String fullCategoryName) {
		this.fullCategoryName = fullCategoryName;
	}

	public String getQuartersName() {
		return quartersName;
	}

	public void setQuartersName(String quartersName) {
		this.quartersName = quartersName;
	}

	public String getQuartersCode() {
		return quartersCode;
	}

	public void setQuartersCode(String quartersCode) {
		this.quartersCode = quartersCode;
	}
}
