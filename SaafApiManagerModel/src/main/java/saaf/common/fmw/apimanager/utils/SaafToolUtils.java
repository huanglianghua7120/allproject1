package saaf.common.fmw.apimanager.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public final class SaafToolUtils {
	public static final String STATUS = "status";
	public static final String MSG = "msg";
	public static final String COUNT = "count";
	public static final String DATA = "data";
	public static final ApplicationContext context = new FileSystemXmlApplicationContext("classpath:saaf/common/fmw/apimanager/config/spring.hibernate.cfg.xml");
	public SaafToolUtils() {
		super();
	}
}
