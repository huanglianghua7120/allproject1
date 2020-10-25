package saaf.common.fmw.base.model.entities.readonly;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmBaseItemDeliversEntity_HI_RO Entity Object
 * Thu May 31 10:18:13 CST 2018  Auto Generate
 */

public class SrmBaseItemDeliversEntity_HI_RO {

	public static final String QUERY_ITEM_DELIVERS = "SELECT\r\n" + 
			"deli.item_deliver_id,\r\n" + 
			"deli.category_id,\r\n" + 
			"deli.item_id,\r\n" + 
			"deli.deliver_type,\r\n" + 
			"deli.deliver_status,\r\n" + 
			"deli.version_num,\r\n" + 
			"deli.creation_date,\r\n" + 
			"deli.created_by,\r\n" + 
			"deli.last_updated_by,\r\n" + 
			"deli.last_update_date,\r\n" + 
			"deli.last_update_login,\r\n" + 
			"deli.attribute_category,\r\n" + 
			"deli.attribute1,\r\n" + 
			"deli.attribute2,\r\n" + 
			"deli.attribute3,\r\n" + 
			"deli.attribute4,\r\n" + 
			"deli.attribute5,\r\n" + 
			"deli.attribute6,\r\n" + 
			"deli.attribute7,\r\n" + 
			"deli.attribute8,\r\n" + 
			"deli.attribute9,\r\n" + 
			"deli.attribute10,\r\n" + 
			"cate.full_category_code,\r\n" + 
			"cate.full_category_name,\r\n" + 
			"items.organization_id,\r\n" + 
			"items.item_code,\r\n" + 
			"items.item_name,\r\n" + 
			"slv2.meaning AS deliverStatusName,\r\n" + 
			"slv1.meaning AS deliverTypeName,\r\n" + 
			"su.user_full_name AS createdByName,\r\n" + 
			"sfu.user_full_name AS lastUpdatedByName\r\n" + 
			"FROM\r\n" + 
			" srm_base_item_delivers deli\r\n" + 
			"LEFT JOIN srm_base_items items ON deli.item_id = items.item_id\r\n" + 
			"AND EXISTS (\r\n" + 
			" SELECT\r\n" + 
			" 1\r\n" + 
			" FROM\r\n" + 
			" saaf_institution si\r\n" + 
			" WHERE\r\n" + 
			" si.inst_id = items.organization_id\r\n" + 
			" AND si.inst_type = 'ORGANIZATION'\r\n" +
			" AND si.master_organization_flag = 'Y'\r\n" + 
			")LEFT JOIN srm_base_categories cate ON deli.category_id = cate.category_id\r\n" + 
			"LEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_type = 'ISP_RETURN_TYPE'\r\n" + 
			"AND deli.deliver_type = slv1.lookup_code\r\n" + 
			"LEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_type = 'ISP_RETURN _STATUS'\r\n" + 
			"AND deli.deliver_status = slv2.lookup_code\r\n" + 
			"LEFT JOIN saaf_users su ON su.user_id = deli.created_by\r\n" + 
			"LEFT JOIN saaf_users sfu ON sfu.user_id = deli.last_updated_by\r\n" + 
			"WHERE 1=1";

	private Integer itemDeliverId; //回货方式ID
	private Integer categoryId; //采购分类ID
	private Integer itemId; //物料ID
	private String deliverType; //回货类型
	private String deliverStatus; //状态
	private Integer versionNum; //版本号
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate; //创建日期
	private Integer createdBy; //创建人
	private Integer lastUpdatedBy; //修改人
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate; //修改时间
	private Integer lastUpdateLogin; //最后登录人
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

	//查询字段：
	private String fullCategoryCode;//分类编码
	private String fullCategoryName;//分类名称
	private String itemCode;//物料编码
	private String itemName;//物料名称
	private String deliverStatusName;//状态
	private String deliverTypeName;//回货类型
	private String createdByName;//创建人名
	private String lastUpdatedByName;//修改人名

	public Integer getItemDeliverId() {
		return itemDeliverId;
	}

	public void setItemDeliverId(Integer itemDeliverId) {
		this.itemDeliverId = itemDeliverId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getDeliverType() {
		return deliverType;
	}

	public void setDeliverType(String deliverType) {
		this.deliverType = deliverType;
	}

	public String getDeliverStatus() {
		return deliverStatus;
	}

	public void setDeliverStatus(String deliverStatus) {
		this.deliverStatus = deliverStatus;
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

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDeliverStatusName() {
		return deliverStatusName;
	}

	public void setDeliverStatusName(String deliverStatusName) {
		this.deliverStatusName = deliverStatusName;
	}

	public String getDeliverTypeName() {
		return deliverTypeName;
	}

	public void setDeliverTypeName(String deliverTypeName) {
		this.deliverTypeName = deliverTypeName;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getLastUpdatedByName() {
		return lastUpdatedByName;
	}

	public void setLastUpdatedByName(String lastUpdatedByName) {
		this.lastUpdatedByName = lastUpdatedByName;
	}

}
