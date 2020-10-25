package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.po.model.entities.SrmPoPlanDemandHisEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmPoPlanDemandHisDAO_HI")
public class SrmPoPlanDemandHisDAO_HI extends ViewObjectImpl<SrmPoPlanDemandHisEntity_HI>  {
	public SrmPoPlanDemandHisDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoPlanDemandHisEntity_HI entity) {
		return super.save(entity);
	}
}
