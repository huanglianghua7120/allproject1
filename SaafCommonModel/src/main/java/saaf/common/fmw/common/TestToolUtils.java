package saaf.common.fmw.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public interface TestToolUtils {
    public static final ApplicationContext context = new FileSystemXmlApplicationContext("classpath:saaf/common/fmw/config/spring.hibernate.cfg.xml");
}
