package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.po.model.entities.SrmPoPlanDemandLineEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmPoPlanDemandLineDAO_HI")
public class SrmPoPlanDemandLineDAO_HI extends ViewObjectImpl<SrmPoPlanDemandLineEntity_HI>  {
	public SrmPoPlanDemandLineDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoPlanDemandLineEntity_HI entity) {
		return super.save(entity);
	}
}
