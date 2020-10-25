package saaf.common.fmw.base.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseManualsEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmBaseManualsDAO_HI_RO")
public class SrmBaseManualsDAO_HI_RO extends DynamicViewObjectImpl<SrmBaseManualsEntity_HI_RO>  {
	public SrmBaseManualsDAO_HI_RO() {
		super();
	}

}
