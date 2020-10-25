package saaf.common.fmw.base.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseItemsEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmBaseItemsDAO_HI_RO")
public class SrmBaseItemsDAO_HI_RO extends DynamicViewObjectImpl<SrmBaseItemsEntity_HI_RO>  {
	public SrmBaseItemsDAO_HI_RO() {
		super();
	}

}
