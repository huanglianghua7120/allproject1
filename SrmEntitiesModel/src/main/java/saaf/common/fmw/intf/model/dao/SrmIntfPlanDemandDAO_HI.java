package saaf.common.fmw.intf.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.intf.model.entities.SrmIntfPlanDemandEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmIntfPlanDemandDAO_HI")
public class SrmIntfPlanDemandDAO_HI extends ViewObjectImpl<SrmIntfPlanDemandEntity_HI>  {
	public SrmIntfPlanDemandDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmIntfPlanDemandEntity_HI entity) {
		return super.save(entity);
	}
}
