package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierSitesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPosSupplierSitesDAO_HI_RO")
public class SrmPosSupplierSitesDAO_HI_RO extends DynamicViewObjectImpl<SrmPosSupplierSitesEntity_HI_RO>  {
	public SrmPosSupplierSitesDAO_HI_RO() {
		super();
	}

}
