package saaf.common.fmw.pos.model.entities.readonly;



import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class SrmPosManagerCatesOthersEntity_HI_RO implements Serializable {

	public static final String QUERY_OTHER_SQL="SELECT "+
				" spmco.other_category_id otherCategoryId ,"+
				" spmco.manager_cate_id managerCateId,"+
				" slv1.meaning  bigCategoryName,"+
				" slv2.meaning  middleCategoryName,"+
				" slv3.meaning  smallCategoryName,"+
				" slv4.meaning  statusStr,"+
				" spmco.CREATION_DATE  creationDate"+
				" FROM"+
				" srm_pos_manager_cate_others AS spmco"+
				" LEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_type = 'BASE_BIG_CATEGORY' "+
				" AND spmco.big_category_code = slv1.lookup_code "+
				" LEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_type = 'BASE_MIDDLE_CATEGORY' "+
				" AND spmco.middle_category_code = slv2.lookup_code"+
				" LEFT JOIN saaf_lookup_values slv3 ON slv3.lookup_type = 'BASE_SMALL_CATEGORY' "+
				" AND spmco.small_category_code = slv3.lookup_code"+
				" LEFT JOIN saaf_lookup_values slv4 ON slv4.lookup_type = 'POS_CATEGORY_STATUS' "+
				" AND spmco.category_status = slv4.lookup_code"+
				" WHERE"+
				" 1=1";
	
	private Integer otherCategoryId;
	private Integer managerCateId;
	private String bigCategoryName;
	private String middleCategoryName;
	private String smallCategoryName;
	private String statusStr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
	public Integer getOtherCategoryId() {
		return otherCategoryId;
	}
	public void setOtherCategoryId(Integer otherCategoryId) {
		this.otherCategoryId = otherCategoryId;
	}
	public Integer getManagerCateId() {
		return managerCateId;
	}
	public void setManagerCateId(Integer managerCateId) {
		this.managerCateId = managerCateId;
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
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
	
}
