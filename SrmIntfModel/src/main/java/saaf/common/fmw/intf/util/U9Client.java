package saaf.common.fmw.intf.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.datacontract.schemas._2004._07.ufsoft_ubf_util.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tempuri.SRMInterServiceSVStub;
import org.ufida.HomaU9CustSRMInterfaceSVISRMInterServiceSV;


import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfKeyValueOfanyTypeanyType;
import com.microsoft.schemas._2003._10.serialization.arrays.ObjectFactory;

import exceptions.ubf.ufsoft.ArrayOfMessageBase;
import properties.IntfPropertiesUtil; 

public class U9Client {
//	private static SRMInterServiceSVStub create;
//	private static HomaU9CustSRMInterfaceSVISRMInterServiceSV createsv;
//	private static ThreadContext context;	
    private static final Logger LOGGER = LoggerFactory.getLogger(U9IntfUtils.class);
	private static String errorMsg;
//	static {
//		try {
//			create = new SRMInterServiceSVStub();
//			createsv = create.getBasicHttpBindingHomaU9CustSRMInterfaceSVISRMInterServiceSV();
//			context = createContext();
//		}catch(Exception e){
//			System.out.println(e.getMessage());
//		    //e.printStackTrace();
//		    errorMsg = e.getMessage();
//			
//		}
//		
//	
//	}

	public static  Map<String,String> convertMap(String status,String returnMsg){
		Map<String,String> map = new HashMap<String,String>();
		map.put("status", status);
		map.put("returnMsg", returnMsg);
		return map;		
	}
	
	public static Map<String,String> invokeService2(String jsonStr, String transCode,String orgCode){
		 SRMInterServiceSVStub create = new SRMInterServiceSVStub();
		 HomaU9CustSRMInterfaceSVISRMInterServiceSV createsv
		 =create.getBasicHttpBindingHomaU9CustSRMInterfaceSVISRMInterServiceSV();
		 ThreadContext context = createContext(orgCode);
		
		 if (createsv ==null){
			 return	convertMap("E",errorMsg); 
		 }
		
		
		javax.xml.ws.Holder<String> doResult = new javax.xml.ws.Holder<String>();
		javax.xml.ws.Holder<ArrayOfMessageBase> outMessages = new javax.xml.ws.Holder<ArrayOfMessageBase>();
	 
			try {
				createsv._do(context, jsonStr, transCode, doResult, outMessages);
				System.out.println("doResult:"+doResult.value);				
//				LOGGER.info("outMessages:"+outMessages.value);
				return	convertMap("Y",doResult.value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return	convertMap("E",e.getMessage());
			}
 	}
	
	public static Map<String, String> invokeService(final String jsonStr, final String transCode) {

		final ExecutorService exec = Executors.newFixedThreadPool(1);
		Map<String, String> map = new HashMap<String, String>();
		Callable<Map<String, String>> call = new Callable<Map<String, String>>() {
			public Map<String, String> call() throws Exception {
				// 开始执行耗时操作
				Map<String, String> retMap = U9Client.invokeService2(jsonStr, transCode,"Homa");
				return retMap;
			}
		};
		try {
			Future<Map<String, String>> future = exec.submit(call);
			int time = 1000;
			if (U9IntfUtils.ITEM_TRANS_CODE.equals(transCode) || U9IntfUtils.EMP_TRANS_CODE.equals(transCode)
					|| U9IntfUtils.CATE_TRANS_CODE.equals(transCode)
					|| U9IntfUtils.FORECAST_TRANS_CODE.equals(transCode)
					|| U9IntfUtils.POCHANGE_TRANS_CODE.equals(transCode)
					|| U9IntfUtils.PLAN_TRANS_CODE.equals(transCode)) {
				time = 1000 * 60 * 5;
			} else if (U9IntfUtils.STARVING_TRANS_CODE.equals(transCode)) {
				time = 1000 * 60 * 60;
			} else {
				time = 1000 * 60;
			}
			
			map = future.get(time, TimeUnit.MILLISECONDS); // 任务处理超时时间设为 2分钟
																 
//			System.out.println("任务成功返回:" + map);
		} catch (TimeoutException ex) {
			System.out.println("处理超时....");
			map = convertMap("E", "处理超时....");
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println("处理失败.");
			//e.printStackTrace();
			map = convertMap("E", "出现异常：" + e.getMessage());
		}
		// 关闭线程池
		exec.shutdown();
		return map;
	}
	
	public static Map<String, String> invokeService(final String jsonStr, final String transCode,final String orgCode) {

		final ExecutorService exec = Executors.newFixedThreadPool(1);
		Map<String, String> map = new HashMap<String, String>();
		Callable<Map<String, String>> call = new Callable<Map<String, String>>() {
			public Map<String, String> call() throws Exception {
				// 开始执行耗时操作
				Map<String, String> retMap = U9Client.invokeService2(jsonStr, transCode,orgCode);
				return retMap;
			}
		};
		try {
			Future<Map<String, String>> future = exec.submit(call);
			map = future.get(1000*60*2, TimeUnit.MILLISECONDS); // 任务处理超时时间设为 2分钟
																 
			System.out.println("任务成功返回:" + map);
		} catch (TimeoutException ex) {
			System.out.println("处理超时....");
			map = convertMap("E", "处理超时....");
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println("处理失败.");
			//e.printStackTrace();
			map = convertMap("E", "出现异常：" + e.getMessage());
		}
		// 关闭线程池
		exec.shutdown();
		return map;
	}

	private static ThreadContext createContext(String orgCode) {
		IntfPropertiesUtil properties = new IntfPropertiesUtil();
		String CultureName = "zh-CN";// 语言
//		Long UserID = Long.parseLong("1001208100001578"); // 当前用户ID 
//		Long OrgID = Long.parseLong("1001208100000014"); // 当前组织ID
//		String EnterpriseID = "01";// 企业号
//		String UserCode = "admin";// 当前用户编码
		
		Long UserID = Long.parseLong(properties.getStringValue("UserID")); // 当前用户ID 
		String EnterpriseID =properties.getStringValue("EnterpriseID");// 企业号
		String UserCode = properties.getStringValue("UserCode");// 当前用户编码
		String UserName = properties.getStringValue("UserName");
		
		Long OrgID = null;
		// 管道厂
		if ("301".equals(orgCode)) {
			OrgID = Long.parseLong(properties.getStringValue("301OrgID"));
		}
		// 注塑厂
		else if ("302".equals(orgCode)) {
			OrgID = Long.parseLong(properties.getStringValue("302OrgID")); // 当前组织ID
		} else {
			OrgID = Long.parseLong(properties.getStringValue("HomaOrgID"));

		}

		// Long OrgID = 1001208100000014L;//当前组织ID
		// Long UserID = 1001208100001578L;//当前用户ID
		// ,

		// String EnterpriseID = "001";//
		// String UserCode = "admin";//当前用户编码
		String DefaultCultureName = "zh-CN";

		org.datacontract.schemas._2004._07.ufsoft_ubf_util.ObjectFactory contextfactory = new org.datacontract.schemas._2004._07.ufsoft_ubf_util.ObjectFactory();

		ThreadContext thContext = contextfactory.createThreadContext();

		ObjectFactory arrayfactory = new ObjectFactory();

		ArrayOfKeyValueOfanyTypeanyType ns = arrayfactory.createArrayOfKeyValueOfanyTypeanyType();

		ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType k1 = new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();
		k1.setKey("OrgID");
		k1.setValue(OrgID);
		ns.getKeyValueOfanyTypeanyType().add(k1);

		ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType k2 = new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();

		k2.setKey("UserID");
		k2.setValue(UserID);
		ns.getKeyValueOfanyTypeanyType().add(k2);

		ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType k3 = new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();

		k3.setKey("CultureName");
		k3.setValue(CultureName);
		ns.getKeyValueOfanyTypeanyType().add(k3);

		ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType k4 = new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();
		k4.setKey("EnterpriseID");
		k4.setValue(EnterpriseID);
		ns.getKeyValueOfanyTypeanyType().add(k4);

		ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType k5 = new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();
		k5.setKey("DefaultCultureName");
		k5.setValue(DefaultCultureName);
		ns.getKeyValueOfanyTypeanyType().add(k5);

		ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType k6 = new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();
		k6.setKey("UserCode");
		k6.setValue(UserCode);
		ns.getKeyValueOfanyTypeanyType().add(k6);

		ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType k7 = new ArrayOfKeyValueOfanyTypeanyType.KeyValueOfanyTypeanyType();
		k7.setKey("UserName");
		k7.setValue(UserName);
		ns.getKeyValueOfanyTypeanyType().add(k7);

		thContext.setNameValueHas(ns);

		return thContext;

	}
	
}
