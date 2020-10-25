package saaf.common.fmw.po.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saaf.common.fmw.po.model.entities.readonly.SrmPoTechnicalNumEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPoTechnicalNumDAO_HI_RO")
public class SrmPoTechnicalNumDAO_HI_RO extends DynamicViewObjectImpl<SrmPoTechnicalNumEntity_HI_RO>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoTechnicalNumDAO_HI_RO.class);
	public SrmPoTechnicalNumDAO_HI_RO() {
		super();
	}

}
