package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.pos.model.entities.SrmPosQualificationInfoEntity_HI;

@Component("srmPosQualificationInfoDAO_HI")
public class SrmPosQualificationInfoDAO_HI extends ViewObjectImpl<SrmPosQualificationInfoEntity_HI>  {
	public SrmPosQualificationInfoDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosQualificationInfoEntity_HI entity) {
		return super.save(entity);
	}
}

