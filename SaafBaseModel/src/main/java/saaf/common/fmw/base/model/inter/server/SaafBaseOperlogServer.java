package saaf.common.fmw.base.model.inter.server;

import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SaafBaseOperlogEntity_HI;
import saaf.common.fmw.base.model.inter.ISaafBaseOperlog;

@Component("saafBaseOperlogServer")
public class SaafBaseOperlogServer implements ISaafBaseOperlog {
	private static final Logger LOGGER = LoggerFactory.getLogger(SaafBaseOperlogServer.class);
	@Autowired
	private ViewObject<SaafBaseOperlogEntity_HI> saafBaseOperlogDAO_HI;
	public SaafBaseOperlogServer() {
		super();
	}

	@Override
	public void saveOperLog(SaafBaseOperlogEntity_HI operlog) throws Exception{
		saafBaseOperlogDAO_HI.saveOrUpdate(operlog);
	}
}
