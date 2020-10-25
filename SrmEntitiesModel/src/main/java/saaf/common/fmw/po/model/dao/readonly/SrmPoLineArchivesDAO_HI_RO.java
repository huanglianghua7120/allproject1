package saaf.common.fmw.po.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLineArchivesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPoLineArchivesDAO_HI_RO")
public class SrmPoLineArchivesDAO_HI_RO extends DynamicViewObjectImpl<SrmPoLineArchivesEntity_HI_RO>  {
	public SrmPoLineArchivesDAO_HI_RO() {
		super();
	}

}
