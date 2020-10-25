package saaf.common.fmw.intf.model.inter.server;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.intf.bean.BankInfoBean;
import saaf.common.fmw.intf.bean.SupplierInfoBean;
import saaf.common.fmw.intf.model.entities.SrmIntfDatasEntity_HI;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.intf.model.inter.IIntfSupplier;
import saaf.common.fmw.intf.model.inter.ISrmIntfData;
import saaf.common.fmw.intf.model.inter.ISrmIntfLogs;
import saaf.common.fmw.intf.util.IntfUtils;
import saaf.common.fmw.intf.util.U9Client;
import saaf.common.fmw.intf.util.U9IntfUtils;
import saaf.common.fmw.pos.model.entities.SrmPosReasonsEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierBankEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierCredentialsEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;
import saaf.common.fmw.utils.SrmUtils;

@Component("intfSupplierServer")
public class IntfSupplierServer implements IIntfSupplier {
	private static final Logger LOGGER = LoggerFactory.getLogger(IntfSupplierServer.class);

	// private ViewObject srmIntfLogsDAO_HI;
	// @Autowired
	// private ViewObject srmIntfLogsDAO_HI;
	@Autowired
	private ViewObject<SrmIntfLogsEntity_HI> srmIntfLogsDAO_HI;
	@Autowired
	private ViewObject<SrmIntfDatasEntity_HI> srmIntfDatasDAO_HI;
	@Autowired
	private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;
	@Autowired
	private ViewObject<SrmPosSupplierBankEntity_HI> SrmPosSupplierBankDAO_HI;
	@Autowired
	private ViewObject<SrmPosSupplierCredentialsEntity_HI> SrmPosSupplierCredentialsDAO_HI;


	 public JSONObject pushSupplier(String supplierNumber, Integer userId) throws Exception {
	     return this.pushSupplier(supplierNumber,null ,userId);
	 }

	public JSONObject pushSupplier(String supplierNumber,Integer supplierId, Integer userId) throws Exception {

		SrmPosSupplierInfoEntity_HI supplierRow = null;
		SrmPosSupplierBankEntity_HI bankRow = null;
		SrmPosSupplierCredentialsEntity_HI credRow = null;
		String operateType = "0";
		String status = "1"; // 生效
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			if (!SrmUtils.isNvl(supplierNumber)) {
				param.put("supplier_number", supplierNumber);
			} else {
				param.put("supplier_id", supplierId);
			}
			supplierRow = srmPosSupplierInfoDAO_HI.findByProperty(param).get(0);
			supplierNumber = supplierRow.getSupplierNumber();
			bankRow = SrmPosSupplierBankDAO_HI.findByProperty("supplier_id", supplierRow.getSupplierId()).get(0);
			credRow = SrmPosSupplierCredentialsDAO_HI.findByProperty("supplier_id", supplierRow.getSupplierId()).get(0);
			if ("QUIT".equals(supplierRow.getSupplierStatus())) {
				status = "0";
			}

			// 修改
			if ("U".equals(supplierRow.getPassU9Flag())) {
				operateType = "2";
			}

		} catch (Exception e) {
			//e.printStackTrace();
			return SToolUtils.convertResultJSONObj("E", "没找到供应商编码为" + supplierNumber+"相关信息", 0, null);
		}

		SupplierInfoBean su = new SupplierInfoBean();
		Date currentDate = new Date();
		su.setU9MessageNO("");
		su.setJsonNO("SRM" + currentDate.getTime());
		su.setCode(supplierRow.getSupplierNumber());
		su.setName(supplierRow.getSupplierName());
		su.setOperateType(operateType);
		String toDate = null;
		String fromDate = null;

		try {
			fromDate = SToolUtils.date2String(new Date(), "yyyy-MM-dd");
		} catch (Exception e) {
			fromDate = "1900-12-30";
		}
		try {
			if ("Y".equals(credRow.getLongLicenseIndate())) {
				toDate = "9999-12-30";
			} else {
				toDate = SToolUtils.date2String(credRow.getLicenseIndate(), "yyyy-MM-dd");
			}
		} catch (Exception e) {
			toDate = "9999-12-30";
		}
		su.setFromDate(fromDate);
		su.setToDate(toDate);
		if (!"2".equals(operateType)) {
			su.setLocationCode(supplierRow.getSupplierNumber());
			su.setLocationName(supplierRow.getSupplierName());
		}
		su.setCanPurchase(IntfUtils.getU9YesNo  (supplierRow.getPurchaseFlag()));
		su.setCanPay(IntfUtils.getU9YesNo(supplierRow.getPaymentFlag()));
		su.setOrgCode("Homa");
		su.setShortName(supplierRow.getSupplierShortName());
		su.setAssetCategory("");
		su.setBankAccount("");
		su.setBankName("");
		su.setAccountCur("");
		su.setAccountName("");
		su.setState(status);
		su.setSettleMode(supplierRow.getSettleAcctType());
		su.setCheckMode(supplierRow.getAcctCheckType());
		su.setCheckAccounter(supplierRow.getAcctCheckStaff());
		su.setShipTo("Y");
		su.setIsBillTo("Y");
		su.setClaim("Y");
		su.setInquire("Y");
		su.setContrast("Y");
		su.setRemit("Y");
		su.setBillTo("Y");
		su.setIsDefaultShipTo("Y");
		su.setIsDefaultIsBillTo("Y");
		su.setIsDefaultClaim("Y");
		su.setIsDefaultInquire("Y");
		su.setIsDefaultContrast("Y");
		su.setIsDefaultRemit("Y");
		su.setCateGoryCode(supplierRow.getFinClassify());
		su.setReceiptRuleCode("SH03");
		su.setCheckCurrencyCode(bankRow.getBankCurrency());
		su.setPaymentTermCode("FK01");
		su.setAPConfirmTermlCode(supplierRow.getPosAcctCondition());
		su.setTaxScheduleCode(supplierRow.getPosTax());
		String SRMSupplier = "False";
		if ("Y".equals(supplierRow.getSrmDelivery())){
			SRMSupplier = "True";
		}
		
		su.setSRMSupplier(SRMSupplier);

		SrmIntfLogsEntity_HI row = new SrmIntfLogsEntity_HI();
		try {
			String jsonStr = JSONObject.toJSONString(su);
			System.out.println("jsonStr:" + jsonStr);
			String transCode = U9IntfUtils.SUPPLIER_TRANS_CODE;

			row.setIntfStatus("W");
			row.setDataDirection("IN");
			row.setInData(jsonStr);
			row.setIntfType(transCode);
			row.setOperatorUserId(userId);
			srmIntfLogsDAO_HI.saveOrUpdate(row);
			Map<String, String> retInfo = U9Client.invokeService(jsonStr, transCode);
			row.setIntfStatus(retInfo.get("status"));
			row.setOutData(retInfo.get("returnMsg"));
			
			if ("E".equals(retInfo.get("status"))) {
				row.setIntfStatus("E");
				row.setErrorMsg(retInfo.get("returnMsg"));
				srmIntfLogsDAO_HI.saveOrUpdate(row);
				return SToolUtils.convertResultJSONObj("E", supplierNumber + "导入U9失败：" + retInfo.get("returnMsg"), 0,
						null);
			}

			JSONObject retJson = JSON.parseObject(retInfo.get("returnMsg"));
			row.setOutData(retInfo.get("returnMsg"));
			srmIntfLogsDAO_HI.saveOrUpdate(row);
			if (!"200".equals(retJson.getString("RetCode"))) {
				row.setIntfStatus("E");
				row.setErrorMsg(retJson.getString("RetMsg"));
				srmIntfLogsDAO_HI.saveOrUpdate(row);
				return SToolUtils.convertResultJSONObj("E", supplierNumber + "导入U9失败：" + retJson.getString("RetMsg"), 0,
						retJson);
			}
			// 如果是修改，还需要重新调用一次 修改U9弹性域字段
			if ("2".equals(operateType)) {
				su.setOperateType("6");
				jsonStr = JSONObject.toJSONString(su);
				row.setInData(row.getInData() + jsonStr);
				retInfo = U9Client.invokeService(jsonStr, transCode);
				row.setOutData(row.getOutData() + retInfo.get("returnMsg"));
				
				if ("E".equals(retInfo.get("status"))) {
					row.setIntfStatus("E");
					row.setErrorMsg(retJson.getString("RetMsg"));
					srmIntfLogsDAO_HI.saveOrUpdate(row);
					return SToolUtils.convertResultJSONObj("E", supplierNumber + "导入U9失败：" + retInfo.get("returnMsg"),
							0, null);
				}

				retJson = JSON.parseObject(retInfo.get("returnMsg"));
				if (!"200".equals(retJson.getString("RetCode"))) {
					row.setIntfStatus("E");
					srmIntfLogsDAO_HI.saveOrUpdate(row);
					return SToolUtils.convertResultJSONObj("E",
							supplierNumber + "导入U9失败：" + retJson.getString("RetMsg"), 0, retJson);
				}

			}
		
			supplierRow.setPassU9Flag("Y");
			supplierRow.setOperatorUserId(userId);
	
			 
			srmPosSupplierInfoDAO_HI.saveOrUpdate(supplierRow);
		
 		    
 			//调用银行接口
			BankInfoBean bankBean = new BankInfoBean();
			bankBean.setU9MessageNO("");
			bankBean.setJsonNO("SRM" + currentDate.getTime());
			bankBean.setOperateType(operateType);
			bankBean.setSupplierCode  (supplierRow.getSupplierNumber());
			bankBean.setBankCode(bankRow.getBankName());
			bankBean.setCurrency(bankRow.getBankCurrency());
			bankBean.setIsDefault("1");
			bankBean.setIsDefaultCur("1");
			bankBean.setOrgCode("Homa");
			bankBean.setSupplierBankAccountCode(bankRow.getBankAccountNumber());
			bankBean.setSupplierBankAccountName(bankRow.getBankUserName());
			
			jsonStr = JSONObject.toJSONString(bankBean);
			
			row.setInData(row.getInData() + jsonStr);
			retInfo = U9Client.invokeService(jsonStr, U9IntfUtils.BANK_TRANS_CODE);
			row.setOutData(row.getOutData() + retInfo.get("returnMsg"));
			
			if ("E".equals(retInfo.get("status"))) {
				row.setIntfStatus("E");
				row.setErrorMsg(retInfo.get("returnMsg"));
				srmIntfLogsDAO_HI.saveOrUpdate(row);
				return SToolUtils.convertResultJSONObj("E", supplierNumber + "导入U9失败：" + retInfo.get("returnMsg"), 0,
						null);
			}

			  retJson = JSON.parseObject(retInfo.get("returnMsg"));
			srmIntfLogsDAO_HI.saveOrUpdate(row);
			if (!"200".equals(retJson.getString("RetCode"))) {
				row.setIntfStatus("E");
				row.setErrorMsg( supplierNumber + "银行信息导入U9失败：" +retJson.getString("RetMsg"));
				srmIntfLogsDAO_HI.saveOrUpdate(row);
				return SToolUtils.convertResultJSONObj("E", supplierNumber + "银行信息导入U9失败：" + retJson.getString("RetMsg"), 0,
						retJson);
			}
		 
			bankRow.setAttribute1( "Y");
			bankRow.setOperatorUserId(bankRow.getLastUpdatedBy());
			SrmPosSupplierBankDAO_HI.update(bankRow);
			
			row.setIntfStatus("S");
			srmIntfLogsDAO_HI.saveOrUpdate(row);
			
			
			return SToolUtils.convertResultJSONObj("S", "导入U9成功", 0, null);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			//e.printStackTrace();
			row.setIntfStatus("E");
			row.setErrorMsg(e.getMessage());
			try {
				srmIntfLogsDAO_HI.saveOrUpdate(row);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return SToolUtils.convertResultJSONObj("E", supplierNumber + "导入U9失败：" + e.getMessage(), 0, null);
		}
	}

}
