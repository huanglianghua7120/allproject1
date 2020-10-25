package saaf.common.fmw.po.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeaderArchivesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPoHeaderArchivesDAO_HI_RO")
public class SrmPoHeaderArchivesDAO_HI_RO extends DynamicViewObjectImpl<SrmPoHeaderArchivesEntity_HI_RO>  {
	public SrmPoHeaderArchivesDAO_HI_RO() {
		super();
	}

}
