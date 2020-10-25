package saaf.common.fmw;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.intf.util.IntfUtils;

public class testNumber {
	public static final ApplicationContext context = new FileSystemXmlApplicationContext(
			"classpath:saaf/common/fmw/config/spring.hibernate.cfg.xml");

	public static void main(String[] args) throws Exception {
		SaafSequencesUtil saafSequencesUtil = (SaafSequencesUtil) context.getBean("saafSequencesUtil");
		Date date= new Date();
		String dateStr = IntfUtils.getDateStr(date,"yyyyMMdd");
		String documentNumber = saafSequencesUtil.getDocSequences("srm_pos_supplier_quit".toUpperCase(), "GYSTC-",
				dateStr+"-", 4);
		String documentNumber2 = saafSequencesUtil.getDocSequences("srm_pos_supplier_quit".toUpperCase(), "GYSTC-",
				"20171128-", 4);
		System.out.println("documentNumber:" + documentNumber);

		System.out.println("documentNumber2:" + documentNumber2);
	}
}
