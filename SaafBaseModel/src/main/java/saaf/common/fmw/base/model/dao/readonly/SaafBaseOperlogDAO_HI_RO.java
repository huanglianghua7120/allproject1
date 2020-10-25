package saaf.common.fmw.base.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SaafBaseOperlogEntity_HI_RO;

@Component("saafBaseOperlogDAO_HI_RO")
public class SaafBaseOperlogDAO_HI_RO extends DynamicViewObjectImpl<SaafBaseOperlogEntity_HI_RO> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SaafBaseOperlogDAO_HI_RO.class);
	public SaafBaseOperlogDAO_HI_RO() {
		super();
	}

}
