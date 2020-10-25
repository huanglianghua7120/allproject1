package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;

@Component("srmPosSupplierInfoDAO_HI")
public class SrmPosSupplierInfoDAO_HI extends ViewObjectImpl<SrmPosSupplierInfoEntity_HI>  {
	public SrmPosSupplierInfoDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSupplierInfoEntity_HI entity) {
		return super.save(entity);
	}
}

