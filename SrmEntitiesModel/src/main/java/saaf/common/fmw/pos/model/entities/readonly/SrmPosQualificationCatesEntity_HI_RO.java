package saaf.common.fmw.pos.model.entities.readonly;

import javax.persistence.Version;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Transient;

/**
 * SrmPosQualificationCatesEntity_HI_RO Entity Object
 * Mon Mar 26 14:03:11 CST 2018  Auto Generate
 */

public class SrmPosQualificationCatesEntity_HI_RO {

	public static String QUERY_CATEGORY_BY_ADDFLAG =
					"SELECT pqc.qualif_category_id\n" +
					"      ,pqc.qualification_id\n" +
					"      ,pqc.supplier_category_id\n" +
					"      ,pqc.category_id\n" +
					"      ,pqc.status\n" +
					"      ,pqc.add_flag\n" +
					"      ,pqc.version_num\n" +
					"      ,pqc.created_by\n" +
					"      ,pqc.creation_date\n" +
					"      ,pqc.last_updated_by\n" +
					"      ,pqc.last_update_date\n" +
					"      ,pqc.last_update_login\n" +
					"      ,sbc.full_category_code\n" +
					"      ,sbc.full_category_name\n" +
					"      ,slv.meaning AS statusDisp\n" +
					"  FROM srm_pos_qualification_cates pqc\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON pqc.status = slv.lookup_code\n" +
					"   AND slv.lookup_type = 'POS_CATEGORY_STATUS', srm_base_categories sbc\n" +
					" WHERE pqc.category_id = sbc.category_id\n";

	private Integer qualifCategoryId; //资质审查品类ID
    private Integer qualificationId; //资质审查ID，关联表:srm_pos_qualification_info
    private Integer supplierCategoryId; //产品和服务ID
    private Integer categoryId; //采购分类ID,关联表：srm_base_categories
    private String bigCategoryCode; //(备用)大类编码，（BASE_BIG_CATEGORY）
    private String middleCategoryCode; //(备用)中类编码，（BASE_MIDDLE_CATEGORY）
    private String smallCategoryCode; //(备用)小类编码，（BASE_SMALL_CATEGORY）
    private String selectedFlag; //(备用)勾选标识
    private String status; //状态(POS_CATEGORY_STATUS)
    private String addFlag; //新增标识(Y/N)
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
    private Integer operatorUserId;

    private String fullCategoryCode;
    private String fullCategoryName;
    private String statusDisp;

	public void setQualifCategoryId(Integer qualifCategoryId) {
		this.qualifCategoryId = qualifCategoryId;
	}
	
	public Integer getQualifCategoryId() {
		return qualifCategoryId;
	}

	public void setQualificationId(Integer qualificationId) {
		this.qualificationId = qualificationId;
	}

	public Integer getQualificationId() {
		return qualificationId;
	}

	public void setSupplierCategoryId(Integer supplierCategoryId) {
		this.supplierCategoryId = supplierCategoryId;
	}
	
	public Integer getSupplierCategoryId() {
		return supplierCategoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setBigCategoryCode(String bigCategoryCode) {
		this.bigCategoryCode = bigCategoryCode;
	}

	public String getBigCategoryCode() {
		return bigCategoryCode;
	}

	public void setMiddleCategoryCode(String middleCategoryCode) {
		this.middleCategoryCode = middleCategoryCode;
	}

	public String getMiddleCategoryCode() {
		return middleCategoryCode;
	}

	public void setSmallCategoryCode(String smallCategoryCode) {
		this.smallCategoryCode = smallCategoryCode;
	}
	
	public String getSmallCategoryCode() {
		return smallCategoryCode;
	}

	public void setSelectedFlag(String selectedFlag) {
		this.selectedFlag = selectedFlag;
	}

	public String getSelectedFlag() {
		return selectedFlag;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}
	
	public String getAddFlag() {
		return addFlag;
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

	public String getStatusDisp() {
		return statusDisp;
	}

	public void setStatusDisp(String statusDisp) {
		this.statusDisp = statusDisp;
	}
}
