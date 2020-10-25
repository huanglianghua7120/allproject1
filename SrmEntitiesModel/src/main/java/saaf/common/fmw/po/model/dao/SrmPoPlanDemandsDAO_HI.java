package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.po.model.entities.SrmPoPlanDemandsEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmPoPlanDemandsDAO_HI")
public class SrmPoPlanDemandsDAO_HI extends ViewObjectImpl<SrmPoPlanDemandsEntity_HI>  {
	public SrmPoPlanDemandsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoPlanDemandsEntity_HI entity) {
		return super.save(entity);
	}
}
