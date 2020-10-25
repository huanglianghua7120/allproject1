package saaf.common.fmw.po.model.entities.readonly;

import java.io.Serializable;

public class SrmShortMaterialConfigEntity_HI_RO implements Serializable {
	private static final long serialVersionUID = -3861199313703030486L;

	public static String QUERY_SQL = " select id, attr_name, " + " attr_desc, " + " is_valid "
			+ " from srm_short_material_config " + " where 1=1 ";

	private String attrName;
	private String attrDesc;
	private String isValid;
	private Integer id;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrDesc() {
		return attrDesc;
	}

	public void setAttrDesc(String attrDesc) {
		this.attrDesc = attrDesc;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

}
