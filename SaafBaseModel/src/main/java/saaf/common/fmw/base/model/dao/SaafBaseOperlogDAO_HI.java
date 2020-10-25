package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SaafBaseOperlogEntity_HI;

@Component("saafBaseOperlogDAO_HI")
public class SaafBaseOperlogDAO_HI extends ViewObjectImpl<SaafBaseOperlogEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SaafBaseOperlogDAO_HI.class);
	public SaafBaseOperlogDAO_HI() {
		super();
	}

	@Override
	public Object save(SaafBaseOperlogEntity_HI entity) {
		return super.save(entity);
	}
}
