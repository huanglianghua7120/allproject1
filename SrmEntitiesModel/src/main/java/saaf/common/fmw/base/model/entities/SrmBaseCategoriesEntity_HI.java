package saaf.common.fmw.base.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmBaseCategoriesEntity_HI Entity Object
 * Thu Mar 15 10:23:03 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_base_categories")
public class SrmBaseCategoriesEntity_HI {
	private Integer categoryId; //采购分类ID
	private String categoryCode; //采购分类编号
	private String categoryName; //采购分类名称
	private Integer parentCategoryId; //父采购分类ID
	private String fullCategoryCode; //采购分类全编号
	private String fullCategoryName; //采购分类全名称
	private Integer categoryLevel; //分类层级
	private String leafFlag; //是否叶节点(Y/N)
	private String enabledFlag; //是否有效(Y/N)
	private Integer versionNum;
	private Integer fileId;
	private String sourceCode; //数据来源
	private Integer sourceId; //数据来源id
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date invalidDate;
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
	private Integer operatorUserId;
	private String expenseItemCode; //费用物料编码
	private String expenseItemName; //费用物料描述

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Id
	@SequenceGenerator(name = "SRM_BASE_CATEGORIES_S", sequenceName = "SRM_BASE_CATEGORIES_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_BASE_CATEGORIES_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "category_id", nullable = false, length = 11)
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Column(name = "category_code", nullable = false, length = 30)
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "category_name", nullable = false, length = 100)
	public String getCategoryName() {
		return categoryName;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	@Column(name = "parent_category_id", nullable = true, length = 11)
	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	public void setFullCategoryCode(String fullCategoryCode) {
		this.fullCategoryCode = fullCategoryCode;
	}

	@Column(name = "full_category_code", nullable = false, length = 30)
	public String getFullCategoryCode() {
		return fullCategoryCode;
	}

	public void setFullCategoryName(String fullCategoryName) {
		this.fullCategoryName = fullCategoryName;
	}

	@Column(name = "full_category_name", nullable = false, length = 240)
	public String getFullCategoryName() {
		return fullCategoryName;
	}

	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	@Column(name = "category_level", nullable = false, length = 2)
	public Integer getCategoryLevel() {
		return categoryLevel;
	}

	public void setLeafFlag(String leafFlag) {
		this.leafFlag = leafFlag;
	}

	@Column(name = "leaf_flag", nullable = true, length = 1)
	public String getLeafFlag() {
		return leafFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	@Column(name = "enabled_flag", nullable = true, length = 10)
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
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

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
	@Column(name = "invalid_date", nullable = false, length = 0)
	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}
	@Column(name = "file_id", nullable = false, length = 11)
	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	@Column(name = "source_code", nullable = false, length = 30)
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	@Column(name = "source_id", nullable = false, length = 30)
	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "expense_item_code", nullable = false, length = 200)
	public String getExpenseItemCode() {
		return expenseItemCode;
	}

	public void setExpenseItemCode(String expenseItemCode) {
		this.expenseItemCode = expenseItemCode;
	}

	@Column(name = "expense_item_name", nullable = false, length = 200)
	public String getExpenseItemName() {
		return expenseItemName;
	}

	public void setExpenseItemName(String expenseItemName) {
		this.expenseItemName = expenseItemName;
	}
}
