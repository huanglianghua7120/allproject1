package saaf.common.fmw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import saaf.common.fmw.intf.util.IntfUtils;
import saaf.common.fmw.intf.util.U9IntfUtils;

public class testIntf {
	public static void main(String[] args) throws Exception {
//		 String jsonStr =
		// "{\"JsonNO\":\"SRM134555566479\",\"Code\":\"2017192\",\"Name\":\"测试2供应商2\",\"ShortName\":\"测试简称供应商\",\"OperateType\":\"1\",\"OfficialLocationCode\":\"\",\"LocationCode\":\"2017001\",\"LocationName\":\"测试供应商\",\"canPurchase\":true,\"canpay\":true,\"orgCode\":\"Homa\",\"CateGoryCode\":\"03\",\"ReceiptRuleCode\":\"SH03\",\"CheckCurrencyCode\":\"RMB\",\"PaymentTermCode\":\"FK01\",\"APConfirmTermlCode\":\"YF02\",\"TaxScheduleCode\":\"TS01\",\"SupplierSiteCode\":\"2017001\"}";
//		 String transCode = "D0003";
		//采购分类
//		 String jsonStr =
//		 "{\"Code\":\"\",\"Name\":\"\",\"ID\":\"1001209190000930\"}";
//		 String transCode = "C0001";
		
//		 String jsonStr = "{\"employee_number\":\"\",\"employee_name\":\"\"}";
//		 String transCode = "C0004";
//		 String jsonStr =
//		 "{\"Code\":\"\",\"LAST_UPDATE_DATE_S\":\"\",\"LAST_UPDATE_DATE_E\":\"\",\"PageNum\":\"1\",\"Count\":100}";
//		 String transCode = "C0002";
	//	 System.out.println("jsonStr:"+jsonStr);

//		String jsonStr = "{\"PLAN_DATE\":\"2017-08-11\",\"PLAN_TYPE\":\"0\",\"Limit\":\"2\"}";
	//	String transCode = "C0003";
		
		String jsonStr = "{\"PLAN_DATE\":\"\",\"PLAN_TYPE\":\"\",\"Limit\":\"2\"}";
		String transCode ="C0006";
		
		

		
//		采购价格接口
//		 String jsonStr = "{\"SUPLIER_NUMBER\":\"030190\",\"ITEM_CODE\":[\"3070600585\",\"3070600585\",\"514010112\",\"3029900032\"],\"UNIT_OF_MEASURE\":\"JIAN\",\"DateT\":\"2017-01-12\"}";
//		 String transCode = "C0005";
		 
		//采购订单接口
//		 String jsonStr = "{\"U9MessageNO\":null,\"JsonNO\":null,\"DocNo\":\"srmpo2017011209\",\"DocType\":\"01\",\"BusinessDate\":\"2017-1-1\",\"FCur\":\"RMB\",\"Operator\":\"000091\",\"Status\":2,\"OperateType\":0,\"SupplierCode\":\"030089\",\"POLineS\":[{\"ItemCode\":\"3090200044\",\"ReqQtyTU\":10,\"TradeUOMCode\":\"JIAN\"\r\n" + 
//		 		",\"DocLineNo\":20,\"ReqDate\":\"2017-1-09\",\"HandBillCauseBg\":\"7\",\"HandBillCauseSm\":\"701\",\"BranchOrgCode\":\"205\",\"DemandCode\":\"\",\"seiBanCode\":\"\",\"LineStatus\":\"\",\"isHand\":\"\"},{\"ItemCode\":\"3090200043\",\"ReqQtyTU\":10,\"TradeUOMCode\":\"JIAN\"\r\n" + 
//		 		",\"DocLineNo\":10,\"ReqDate\":\"2017-1-09\",\"HandBillCauseBg\":\"7\",\"HandBillCauseSm\":\"701\",\"BranchOrgCode\":\"205\",\"DemandCode\":\"\",\"seiBanCode\":\"\",\"LineStatus\":\"\",\"isHand\":\"\"}]}\r\n" + 
//		 		"";
//		 String transCode = "D0001";
		//采购收货接口
//		String jsonStr = "{\"JsonNO\":\"SRM15112345696\",\"DocumentType\":\"RCVA\",\"DocNo\":\"RCV2017120955\",\"BusinessDate\":\"2017-4-11\",\"SupplierCode\":\"01001\",\"BRANCH_CODE\":\"205\",\"Currency\":\"rmb\",\"RCVLineS\":[{\"SrcDoc\":1003506282374092,\"DocLineNo\":10,\"ItemCode\":\"3090200043\",\"SeibanCode\":\"\",\"PoNo\":\"po2017011209\",\"PoLineNo\":10,\"POQty\":10}]}";
//		String transCode = "D0004";
		//采购货源表接口
//		String jsonStr = "{\"U9MessageNO\":null,\"JsonNO\":\"srm2012222235\",\"ItemCode\":\"3070600585\",\"RowNo\":\"21\",\"OperateType\":\"0\",\"FromDate\":\"2018-9-5\",\"ToDate\":\"2017-7-8\",\"SupplierCode\":\"020034\",\"State\":0}";
//		String transCode = "D0005";
//采购预测
//				String jsonStr = "{\"FORECAST_NAME\":\"\",\"FORECAST_TYPE\":\"\",\"Limit\":10}\r\n"  ;
//		String transCode = "C0008";
		 System.out.println("jsonStr:"+jsonStr);
		JSONObject json = JSON.parseObject(U9IntfUtils.invokeService(jsonStr, transCode));
		
		System.out.println(json.getString("JsonStr"));
		System.out.println(IntfUtils.replaceBackslash( json.getString("JsonStr")));
//		String JsonStr = json.getString("JsonStr");
		
	
		
		

//		System.out.println("JsonStr:"+jsonStr);

	}

}
