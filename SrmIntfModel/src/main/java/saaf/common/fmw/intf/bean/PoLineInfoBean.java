package saaf.common.fmw.intf.bean;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;

public class PoLineInfoBean {
	private String ItemCode; // 物料编码
	private String ReqQtyTU; // 需求数量
	private String TradeUOMCode; //交易单位编码
	private String DocLineNo;  //采购订单行号
	
	private String ReqDate;      //需求日期
	private String HandBillCauseBg; //手工单原因大类
	private String HandBillCauseSm; //手工单原因小类
	private String BranchOrgCode;    //分厂编码（货主组织）
	private String DemandCode;      //需求分类
	private String seiBanCode;      //番号
	private String LineStatus;     //行状态
	private String isHand;     //是否手工
	private BigDecimal price;
	private String PoNo;  //采购订单行号
	private String Memo;
	
	
	@JSONField(name = "Memo")
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	@JSONField(name = "PONO")
	public String getPoNo() {
		return PoNo;
	}
	public void setPoNo(String poNo) {
		PoNo = poNo;
	}
	
	@JSONField(name = "price")
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	@JSONField(name = "ItemCode")
	public String getItemCode() {
		return ItemCode;
	}
	public void setItemCode(String itemCode) {
		ItemCode = itemCode;
	}
	@JSONField(name = "ReqQtyTU")
	public String getReqQtyTU() {
		return ReqQtyTU;
	}
	public void setReqQtyTU(String reqQtyTU) {
		ReqQtyTU = reqQtyTU;
	}
	@JSONField(name = "TradeUOMCode")
	public String getTradeUOMCode() {
		return TradeUOMCode;
	}
	public void setTradeUOMCode(String tradeUOMCode) {
		TradeUOMCode = tradeUOMCode;
	}
	@JSONField(name = "DocLineNo")
	public String getDocLineNo() {
		return DocLineNo;
	}
	public void setDocLineNo(String docLineNo) {
		DocLineNo = docLineNo;
	}
	@JSONField(name = "ReqDate")
	public String getReqDate() {
		return ReqDate;//==null?"2000-01-01":ReqDate;
	}
	public void setReqDate(String reqDate) {
		ReqDate = reqDate;
	}
	@JSONField(name = "HandBillCauseBg")
	public String getHandBillCauseBg() {
		return HandBillCauseBg;
	}
	public void setHandBillCauseBg(String handBillCauseBg) {
		HandBillCauseBg = handBillCauseBg;
	}
	@JSONField(name = "HandBillCauseSm")
	public String getHandBillCauseSm() {
		return HandBillCauseSm;
	}
	public void setHandBillCauseSm(String handBillCauseSm) {
		HandBillCauseSm = handBillCauseSm;
	}
	@JSONField(name = "BranchOrgCode")
	public String getBranchOrgCode() {
		return BranchOrgCode;
	}
	public void setBranchOrgCode(String branchOrgCode) {
		BranchOrgCode = branchOrgCode;
	}
	@JSONField(name = "DemandCode")
	public String getDemandCode() {
		return DemandCode;
	}
	public void setDemandCode(String demandCode) {
		DemandCode = demandCode;
	}
	@JSONField(name = "seiBanCode")
	public String getSeiBanCode() {
		return seiBanCode;
	}
	public void setSeiBanCode(String seiBanCode) {
		this.seiBanCode = seiBanCode;
	}
	@JSONField(name = "LineStatus")
	public String getLineStatus() {
		return LineStatus;
	}
	public void setLineStatus(String lineStatus) {
		LineStatus = lineStatus;
	}
	@JSONField(name = "isHand")
	public String getIsHand() {
		return isHand;
	}
	public void setIsHand(String isHand) {
		this.isHand = isHand;
	}
	
	
	
}
