package saaf.common.fmw.pon.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonPriceLibraryEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPonPriceLibraryDAO_HI_RO")
public class SrmPonPriceLibraryDAO_HI_RO extends DynamicViewObjectImpl<SrmPonPriceLibraryEntity_HI_RO>  {
	public SrmPonPriceLibraryDAO_HI_RO() {
		super();
	}

}
