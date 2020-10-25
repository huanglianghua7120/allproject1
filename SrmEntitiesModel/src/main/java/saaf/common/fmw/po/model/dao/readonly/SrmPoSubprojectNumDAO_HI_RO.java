package saaf.common.fmw.po.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saaf.common.fmw.po.model.entities.readonly.SrmPoSubprojectNumEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPoSubprojectNumDAO_HI_RO")
public class SrmPoSubprojectNumDAO_HI_RO extends DynamicViewObjectImpl<SrmPoSubprojectNumEntity_HI_RO>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoSubprojectNumDAO_HI_RO.class);
	public SrmPoSubprojectNumDAO_HI_RO() {
		super();
	}

}
