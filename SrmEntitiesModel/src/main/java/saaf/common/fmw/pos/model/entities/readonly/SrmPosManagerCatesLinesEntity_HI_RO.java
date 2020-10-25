package saaf.common.fmw.pos.model.entities.readonly;



import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class SrmPosManagerCatesLinesEntity_HI_RO implements Serializable {

	public static final String QUERY_MANAGER_CATES_LINES_SQL="SELECT "+
					" spmcl.line_id lineId,"+
					" spmcl.manager_cate_id managerCateId,"+
					" spmcl.select_flag selectFlag,"+
					" spmcl.CREATION_DATE creationDate,"+
					" slv1.meaning bigCategoryName,"+
					" slv2.meaning middleCategoryName,"+
					" slv3.meaning smallCategoryName,"+
					" slv4.meaning statusStr"+
					" FROM"+
					" 	srm_pos_manager_cate_lines AS spmcl"+
					" LEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_type = 'BASE_BIG_CATEGORY'"+
					" AND spmcl.big_category_code = slv1.lookup_code"+
					" LEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_type = 'BASE_MIDDLE_CATEGORY'"+
					" AND spmcl.middle_category_code = slv2.lookup_code"+
					" LEFT JOIN saaf_lookup_values slv3 ON slv3.lookup_type = 'BASE_SMALL_CATEGORY'"+
					" AND spmcl.small_category_code = slv3.lookup_code"+
					" LEFT JOIN saaf_lookup_values slv4 ON slv4.lookup_type = 'POS_CATEGORY_STATUS'"+
					" AND spmcl.category_status = slv4.lookup_code"+
					" WHERE"+
					" 1=1";
//	" spmcl.category_id categoryId,"+
//	" spmcl.big_category_code bigCategoryCode,"+
//	" spmcl.middle_category_code middleCategoryCode,"+
//	" spmcl.small_category_code smallCategoryCode,"+
//	" spmcl.description description,"+
//	" spmcl.VERSION_NUM versionNum,"+
//	" spmcl.CREATED_BY createdBy,"+
//	" spmcl.LAST_UPDATED_BY lastUpdatedBy,"+
//	" spmcl.LAST_UPDATE_DATE lastUpdateDate,"+
//	" spmcl.LAST_UPDATE_LOGIN lastUpdateLogin,"+
//	" spmcl.ATTRIBUTE_CATEGORY attributeCategory"+
	private String bigCategoryName;
	private String middleCategoryName;
	private String smallCategoryName;
	private Integer lineId;
    private Integer managerCateId;
    private String selectFlag;
    private Integer categoryId;
    private String bigCategoryCode;
    private String middleCategoryCode;
    private String smallCategoryCode;
    private String categoryStatus;
    private String description;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private String attributeCategory;
    private String statusStr;
    
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public String getBigCategoryName() {
		return bigCategoryName;
	}
	public void setBigCategoryName(String bigCategoryName) {
		this.bigCategoryName = bigCategoryName;
	}
	public String getMiddleCategoryName() {
		return middleCategoryName;
	}
	public void setMiddleCategoryName(String middleCategoryName) {
		this.middleCategoryName = middleCategoryName;
	}
	public String getSmallCategoryName() {
		return smallCategoryName;
	}
	public void setSmallCategoryName(String smallCategoryName) {
		this.smallCategoryName = smallCategoryName;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public Integer getManagerCateId() {
		return managerCateId;
	}
	public void setManagerCateId(Integer managerCateId) {
		this.managerCateId = managerCateId;
	}
	public String getSelectFlag() {
		return selectFlag;
	}
	public void setSelectFlag(String selectFlag) {
		this.selectFlag = selectFlag;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getBigCategoryCode() {
		return bigCategoryCode;
	}
	public void setBigCategoryCode(String bigCategoryCode) {
		this.bigCategoryCode = bigCategoryCode;
	}
	public String getMiddleCategoryCode() {
		return middleCategoryCode;
	}
	public void setMiddleCategoryCode(String middleCategoryCode) {
		this.middleCategoryCode = middleCategoryCode;
	}
	public String getSmallCategoryCode() {
		return smallCategoryCode;
	}
	public void setSmallCategoryCode(String smallCategoryCode) {
		this.smallCategoryCode = smallCategoryCode;
	}
	public String getCategoryStatus() {
		return categoryStatus;
	}
	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
    
    
}
