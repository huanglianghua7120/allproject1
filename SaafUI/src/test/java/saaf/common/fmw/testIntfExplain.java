package saaf.common.fmw;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import saaf.common.fmw.intf.model.inter.IIntfBaseExPlain;
import saaf.common.fmw.intf.model.inter.ISrmIntfData;
import saaf.common.fmw.intf.util.U9IntfUtils;

public class testIntfExplain {
	public static final ApplicationContext context = new FileSystemXmlApplicationContext(
			"classpath:saaf/common/fmw/config/spring.hibernate.cfg.xml");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		IIntfBaseExPlain server = (IIntfBaseExPlain)context.getBean("intfBaseExPlainServer");
		ISrmIntfData srmIntfDataServer = (ISrmIntfData) context.getBean("srmIntfDataServer");
		try {
//			server.saveCategory(null, null, "N", -1);
			List<Integer > list =  Arrays.asList(  2267,2268,2269,2270,2271,2272,2273,2275,2276,2279,2283,2284);
			for (Integer logId :list){
			srmIntfDataServer.saveU9DataByLog(logId, U9IntfUtils.ITEM_TRANS_CODE, null, -1);
			server.saveItem(logId, null, "N", -1);
			}
//			server.saveItem(null,null , "N", -1);
//			server.saveEmployee(null, null, "N", -1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
