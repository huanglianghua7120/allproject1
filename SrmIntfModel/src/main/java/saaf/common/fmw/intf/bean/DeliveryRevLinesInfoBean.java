package saaf.common.fmw.intf.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class DeliveryRevLinesInfoBean {
	private String SrcDoc;//采购订单计划行 ID 创建采购单时返回
	private String DocLineNo;//收货行号
	private String ItemCode;//料号
	private String SeibanCode;//番号
	private String PoNo;//采购单号
	private String PoLineNo;//采购行号
	private String POQty;//收货数量
	
	@JSONField(name = "SrcDoc")
	public String getSrcDoc() {
		return SrcDoc;
	}
	public void setSrcDoc(String srcDoc) {
		SrcDoc = srcDoc;
	}
	@JSONField(name = "DocLineNo")
	public String getDocLineNo() {
		return DocLineNo;
	}
	public void setDocLineNo(String docLineNo) {
		DocLineNo = docLineNo;
	}
	@JSONField(name = "ItemCode")
	public String getItemCode() {
		return ItemCode;
	}
	public void setItemCode(String itemCode) {
		ItemCode = itemCode;
	}
	@JSONField(name = "SeibanCode")
	public String getSeibanCode() {
		return SeibanCode;
	}
	public void setSeibanCode(String seibanCode) {
		SeibanCode = seibanCode;
	}
	@JSONField(name = "PoNo")
	public String getPoNo() {
		return PoNo;
	}
	public void setPoNo(String poNo) {
		PoNo = poNo;
	}
	@JSONField(name = "PoLineNo")
	public String getPoLineNo() {
		return PoLineNo;
	}
	public void setPoLineNo(String poLineNo) {
		PoLineNo = poLineNo;
	}
	@JSONField(name = "POQty")
	public String getPOQty() {
		return POQty;
	}
	public void setPOQty(String pOQty) {
		POQty = pOQty;
	}
	

}
