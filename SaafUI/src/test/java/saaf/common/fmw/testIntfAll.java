package saaf.common.fmw;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import saaf.common.fmw.intf.util.IntfUtils;
import saaf.common.fmw.intf.util.U9IntfUtils;

public class testIntfAll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//	String str =	U9IntfUtils.handleCateIntf("", "");
//	String str2 =	U9IntfUtils.handleEmpIntf("", "");
//		Date date = new Date();
//		for (int i = 427; i < 600; i++) {
//			String dateStrf = IntfUtils.getDateStr(IntfUtils.dateAdd(date, -1 * i), "yyyy-MM-dd");
//			String dateStrt = IntfUtils.getDateStr(IntfUtils.dateAdd(date, -1 * i + 1), "yyyy-MM-dd");
//			System.out.println(dateStrf + "**" + i + "**" + dateStrt);
//			String str3 = U9IntfUtils.handleItemIntf("", dateStrf, dateStrt);
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

//	String str3 =	U9IntfUtils.handleItemIntf("501020002", "", "","1");	
//		U9IntfUtils.handlePlanData("2017-12-30", "", "","");  //1采购   2委外
//	U9IntfUtils.pushSupplier("050054Q", 1);
//	U9IntfUtils.getPrices("050014", "3070600585", "JIAN", "2017-12-12");
//	System.out.println( str3);
	
//	U9IntfUtils.pushList(8, -1);
//	U9IntfUtils.pushList(6, -1);
//	U9IntfUtils.pushList(25, -1);
//	U9IntfUtils.pushList(29, -1);
//	U9IntfUtils.pushList(37, -1);
//	U9IntfUtils.pushList(54, -1);
//	U9IntfUtils.pushList(55, -1);
//	U9IntfUtils.pushList(61, -1);
//	U9IntfUtils.pushList(63, -1);
//		U9IntfUtils.pushLists(-1);
//		U9IntfUtils.pushLoopApprovedList(-1)   ;
		
		
//		U9IntfUtils.pushPoOrder("147", -1);
	
//		U9IntfUtils.handleForecast(null, "2018二分厂1月主计划调整一000001.3", null, "6000", null);
	U9IntfUtils.handlePoChange(null, null, null, "MRP180302", null, "1000");
	
//	System.out.println(	U9IntfUtils.pushDelivery(960,1));
	
	
	}

}
