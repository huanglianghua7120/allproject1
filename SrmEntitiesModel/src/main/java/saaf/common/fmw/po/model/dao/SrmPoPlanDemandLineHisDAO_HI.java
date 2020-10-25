package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.po.model.entities.SrmPoPlanDemandLineHisEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmPoPlanDemandLineHisDAO_HI")
public class SrmPoPlanDemandLineHisDAO_HI extends ViewObjectImpl<SrmPoPlanDemandLineHisEntity_HI>  {
	public SrmPoPlanDemandLineHisDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoPlanDemandLineHisEntity_HI entity) {
		return super.save(entity);
	}
}
