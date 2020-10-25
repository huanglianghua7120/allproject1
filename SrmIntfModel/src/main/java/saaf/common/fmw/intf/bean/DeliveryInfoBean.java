package saaf.common.fmw.intf.bean;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class DeliveryInfoBean {
	private String JsonNO;//报文号 接口系统+timestamp
	private String DocumentType;//单据类型
	private String DocNo;//收货单号建议SRM开头以区分牛牛创建的单据
	private String BusinessDate;//单据日期
	private String SupplierCode;//供应商编码
	private String BRANCH_CODE;//收货组织
	private String Currency;//币种
	private String ReceiveName;//接收人
	private List<DeliveryRevLinesInfoBean> RCVLineS;
	
	@JSONField(name = "JsonNO")
	public String getJsonNO() {
		return JsonNO;
	}
	public void setJsonNO(String jsonNO) {
		JsonNO = jsonNO;
	}
	@JSONField(name = "DocumentType")
	public String getDocumentType() {
		return DocumentType;
	}
	public void setDocumentType(String documentType) {
		DocumentType = documentType;
	}
	@JSONField(name = "DocNo")
	public String getDocNo() {
		return DocNo;
	}
	public void setDocNo(String docNo) {
		DocNo = docNo;
	}
	@JSONField(name = "BusinessDate")
	public String getBusinessDate() {
		return BusinessDate;
	}
	public void setBusinessDate(String businessDate) {
		BusinessDate = businessDate;
	}
	@JSONField(name = "SupplierCode")
	public String getSupplierCode() {
		return SupplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		SupplierCode = supplierCode;
	}
	@JSONField(name = "BRANCH_CODE")
	public String getBRANCH_CODE() {
		return BRANCH_CODE;
	}
	public void setBRANCH_CODE(String bRANCH_CODE) {
		BRANCH_CODE = bRANCH_CODE;
	}
	@JSONField(name = "Currency")
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	@JSONField(name = "RCVLineS")
	public List<DeliveryRevLinesInfoBean> getRCVLineS() {
		return RCVLineS;
	}
	public void setRCVLineS(List<DeliveryRevLinesInfoBean> rCVLineS) {
		RCVLineS = rCVLineS;
	}
	@JSONField(name = "ReceiveName")
	public String getReceiveName() {
		return ReceiveName;
	}
	public void setReceiveName(String receiveName) {
		ReceiveName = receiveName;
	}
	
	


}
