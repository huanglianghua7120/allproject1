package saaf.common.fmw.intf.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class PoInfoBean {
	private String U9MessageNO ;
	private String JsonNO = "SRM" + new Date().getTime(); // 报文号 系统名+当前时间截
	private String DocNo; // 采购订单号
	private String DocType; // 单据类型
	private String BusinessDate; 
	private String FCur; // 币种
	private String Operator; // 业务员
	private String Status; // 状态
	private String OperateType; // 0操作类型  0新增、1修改
	private String SupplierCode; // 供应商编码
//	private  String Price  ;  //价格
	private  Integer PriceIncludeTax ;//含税价
	private String orgCode;  //供应组织
	private String contractNumber;
	
	private List<PoLineInfoBean> POLineS;
	
	@JSONField(name = "ContractNumber")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	@JSONField(name = "orgCode")
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

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

	@JSONField(name = "DocNo")
	public String getDocNo() {
		return DocNo;
	}

	@JSONField(name = "PriceIncludeTax")
	public Integer getPriceIncludeTax() {
		return PriceIncludeTax;
	}

	public void setPriceIncludeTax(Integer priceIncludeTax) {
		PriceIncludeTax = priceIncludeTax;
	}

	public void setDocNo(String docNo) {
		DocNo = docNo;
	}

	@JSONField(name = "DocType")
	public String getDocType() {
		return DocType;
	}

	public void setDocType(String docType) {
		DocType = docType;
	}

	@JSONField(name = "BusinessDate")
	public String getBusinessDate() {
		return BusinessDate;
	}

	public void setBusinessDate(String businessDate) {
		BusinessDate = businessDate;
	}

	@JSONField(name = "FCur")
	public String getFCur() {
		return FCur;
	}

	public void setFCur(String fCur) {
		FCur = fCur;
	}

	@JSONField(name = "Operator")
	public String getOperator() {
		return Operator;
	}

	public void setOperator(String operator) {
		Operator = operator;
	}

	@JSONField(name = "Status")
	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@JSONField(name = "OperateType")
	public String getOperateType() {
		return OperateType;
	}

	public void setOperateType(String operateType) {
		OperateType = operateType;
	}
	
	@JSONField(name = "SupplierCode")
	public String getSupplierCode() {
		return SupplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		SupplierCode = supplierCode;
	}

	@JSONField(name = "POLineS")
	public List<PoLineInfoBean> getPOLineS() {
		return POLineS;
	}

	public void setPOLineS(List<PoLineInfoBean> pOLineS) {
		POLineS = pOLineS;
	} 
	public PoInfoBean(){
		POLineS = new ArrayList<PoLineInfoBean>();
	}
	
	


}


