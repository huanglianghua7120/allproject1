package saaf.common.fmw.intf.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.intf.model.entities.SrmIntfPoStarvingEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmIntfPoStarvingDAO_HI")
public class SrmIntfPoStarvingDAO_HI extends ViewObjectImpl<SrmIntfPoStarvingEntity_HI>  {
	public SrmIntfPoStarvingDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmIntfPoStarvingEntity_HI entity) {
		return super.save(entity);
	}
}
