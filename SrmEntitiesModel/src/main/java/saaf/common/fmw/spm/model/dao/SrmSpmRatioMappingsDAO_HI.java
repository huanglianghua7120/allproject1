package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.spm.model.entities.SrmSpmRatioMappingsEntity_HI;

@Component("srmSpmRatioMappingsDAO_HI")
public class SrmSpmRatioMappingsDAO_HI extends ViewObjectImpl<SrmSpmRatioMappingsEntity_HI>  {

	public SrmSpmRatioMappingsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmRatioMappingsEntity_HI entity) {
		return super.save(entity);
	}
}

