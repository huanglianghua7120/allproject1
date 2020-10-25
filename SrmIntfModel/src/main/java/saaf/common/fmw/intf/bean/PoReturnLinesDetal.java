package saaf.common.fmw.intf.bean;

import java.math.BigDecimal;

public class PoReturnLinesDetal {
	private BigDecimal ID;
	private Integer POLineNo;
	private String PONO;
	public BigDecimal getID() {
		return ID;
	}
	public void setID(BigDecimal iD) {
		ID = iD;
	}
	public Integer getPOLineNo() {
		return POLineNo;
	}
	public void setPOLineNo(Integer pOLineNo) {
		POLineNo = pOLineNo;
	}
	public String getPONO() {
		return PONO;
	}
	public void setPONO(String pONO) {
		PONO = pONO;
	}
	
	

}
