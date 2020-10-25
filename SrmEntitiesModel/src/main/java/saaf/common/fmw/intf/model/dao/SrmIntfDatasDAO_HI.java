package saaf.common.fmw.intf.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.intf.model.entities.SrmIntfDatasEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmIntfDatasDAO_HI")
public class SrmIntfDatasDAO_HI extends ViewObjectImpl<SrmIntfDatasEntity_HI>  {
	public SrmIntfDatasDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmIntfDatasEntity_HI entity) {
		return super.save(entity);
	}
}
