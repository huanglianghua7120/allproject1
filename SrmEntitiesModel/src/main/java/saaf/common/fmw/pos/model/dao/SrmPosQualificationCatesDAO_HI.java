package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.pos.model.entities.SrmPosQualificationCatesEntity_HI;

@Component("srmPosQualificationCatesDAO_HI")
public class SrmPosQualificationCatesDAO_HI extends ViewObjectImpl<SrmPosQualificationCatesEntity_HI>  {
	public SrmPosQualificationCatesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosQualificationCatesEntity_HI entity) {
		return super.save(entity);
	}
}

