package saaf.common.fmw.intf.bean;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;

public class ApprovedListInfoBean {
	private String U9MessageNO;//u9对于接收到的每个报文进行的编号
	private String JsonNO;//报文号 接口系统+timestanp
	private String ItemCode;//物料编码
	private String RowNo;//序号 供货顺序默认为1当一个料号对应多个供应商时，要求不能重复
	private String OperateType;//操作方式 0 新增 2 修改
	private String FromDate;//有效期从
	private String ToDate;//有效期至
	private String SupplierCode;//供应商编码
	private BigDecimal DaySupplyAmount;//日供货量
	private String State;//0 生效 0 为失效 1生效
	
	@JSONField(name = "U9MessageNO")
	public String getU9MessageNO() {
		return U9MessageNO;
	}
	public void setU9MessageNO(String u9MessageNO) {
		U9MessageNO = u9MessageNO;
	}
	@JSONField(name = "JsonNO")
	public String getJsonNO() {
		return JsonNO;
	}
	public void setJsonNO(String jsonNO) {
		JsonNO = jsonNO;
	}
	@JSONField(name = "ItemCode")
	public String getItemCode() {
		return ItemCode;
	}
	public void setItemCode(String itemCode) {
		ItemCode = itemCode;
	}
	@JSONField(name = "RowNo")
	public String getRowNo() {
		return RowNo;
	}
	public void setRowNo(String rowNo) {
		RowNo = rowNo;
	}
	@JSONField(name = "OperateType")
	public String getOperateType() {
		return OperateType;
	}
	public void setOperateType(String operateType) {
		OperateType = operateType;
	}
	@JSONField(name = "FromDate")
	public String getFromDate() {
		return FromDate;
	}
	public void setFromDate(String fromDate) {
		FromDate = fromDate;
	}
	@JSONField(name = "ToDate")
	public String getToDate() {
		return ToDate;
	}
	public void setToDate(String toDate) {
		ToDate = toDate;
	}
	@JSONField(name = "SupplierCode")
	public String getSupplierCode() {
		return SupplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		SupplierCode = supplierCode;
	}
	@JSONField(name = "State")
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	@JSONField(name = "DaySupplyAmount")
	public BigDecimal getDaySupplyAmount() {
		return DaySupplyAmount;
	}
	public void setDaySupplyAmount(BigDecimal daySupplyAmount) {
		DaySupplyAmount = daySupplyAmount;
	}
	
}
