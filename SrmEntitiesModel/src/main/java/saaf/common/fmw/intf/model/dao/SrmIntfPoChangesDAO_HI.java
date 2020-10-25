package saaf.common.fmw.intf.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.intf.model.entities.SrmIntfPoChangesEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmIntfPoChangesDAO_HI")
public class SrmIntfPoChangesDAO_HI extends ViewObjectImpl<SrmIntfPoChangesEntity_HI>  {
	public SrmIntfPoChangesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmIntfPoChangesEntity_HI entity) {
		return super.save(entity);
	}
}
