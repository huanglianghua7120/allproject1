package saaf.common.fmw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import saaf.common.fmw.intf.model.inter.ISrmIntfData;

public class testInft2 {
	public static final ApplicationContext context = new FileSystemXmlApplicationContext(
			"classpath:saaf/common/fmw/config/spring.hibernate.cfg.xml");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String jsonStr = "{\"Code\":\"\",\"Name\":\"\",\"ID\":\"1001209190000930\"}";
//		String transCode = "C0001";
		
		String jsonStr = "{\"Code\":\"\",\"LAST_UPDATE_DATE_S\":\"2017-05-05\",\"LAST_UPDATE_DATE_E\":\"2017-05-6\"}";
		String transCode = "C0002";
//		String jsonStr = "{\"employee_number\":\"\",\"employee_name\":\"\"}";
//		String transCode = "C0004";
		ISrmIntfData server = (ISrmIntfData)context.getBean("SrmIntfDataServer");
		try {
			server.saveU9Data(jsonStr, transCode,"", -1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
