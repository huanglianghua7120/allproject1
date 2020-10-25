package saaf.common.fmw.po.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;

public class SrmShortMaterialInvEntity_HI_RO  implements Serializable{
	private static final long serialVersionUID = 1L;

	public static String QUERY_SQL= " select a.MATERIAL_CODE materialCode ,"
		                                 + " b.ITEM_NAME itemName, "
			                             + " a.qty, "
			                             + " if(a.considerdelivplan='1','考虑','不考虑') considerdelivplan , "
			                             + " c.inst_name instName, "
			                             + " a.INV_CODE invCode, "
			                             + " a.whname, "
			                             + " if(a.Effective='0','无效','有效') effective , "
			                             + " a.seibanno  "
			                             + " from SRM_SHORT_MATERIAL_INV a "
			                             + " join srm_base_items b on a.MATERIAL_CODE = b.ITEM_CODE "
			                             + " LEFT JOIN saaf_institution c "
			                             + " ON a.invorg = c.inst_code "
			                             + " where 1=1  ";
	
	
    private String materialCode;
    private BigDecimal qty;
    private String considerdelivplan;    
    private String instName;   
    private String invCode;
    private String seibanno;
    private String itemName;
    private String effective;
    private String whname;
    
    
	public String getEffective() {
		return effective;
	}
	public void setEffective(String effective) {
		this.effective = effective;
	}
	public String getWhname() {
		return whname;
	}
	public void setWhname(String whname) {
		this.whname = whname;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSeibanno() {
		return seibanno;
	}
	public void setSeibanno(String seibanno) {
		this.seibanno = seibanno;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public String getConsiderdelivplan() {
		return considerdelivplan;
	}
	public void setConsiderdelivplan(String considerdelivplan) {
		this.considerdelivplan = considerdelivplan;
	}
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}
	public String getInvCode() {
		return invCode;
	}
	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}

    

}
