package saaf.common.fmw.spm.model.entities.readonly;

import java.io.Serializable;

public class SrmSpmTplCategoriesEntity_HI_RO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String QUERY_CATEGORIES_INFO_LIST =
					"SELECT sc.tpl_category_id tplCategoryId\n" +
					"      ,sc.tpl_id          tplId\n" +
					"      ,sc.category_id     categoryid\n" +
					"      ,st.category_code   categoryCode\n" +
					"      ,st.category_name   categoryName\n" +
					"FROM   srm_spm_tpl_categories sc\n" +
					"LEFT   JOIN srm_base_categories st\n" +
					"ON     sc.category_id = st.category_id\n" +
					"WHERE  1 = 1\n";

	private Integer tplCategoryId;
	private Integer tplId;
	private Integer categoryId;
	private String categoryCode;
	private String categoryName;

	public Integer getTplCategoryId() {
		return tplCategoryId;
	}

	public void setTplCategoryId(Integer tplCategoryId) {
		this.tplCategoryId = tplCategoryId;
	}

	public Integer getTplId() {
		return tplId;
	}

	public void setTplId(Integer tplId) {
		this.tplId = tplId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

}
