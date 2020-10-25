package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.po.model.entities.SrmPoChangeInfoEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmPoChangeInfoDAO_HI")
public class SrmPoChangeInfoDAO_HI extends ViewObjectImpl<SrmPoChangeInfoEntity_HI>  {
	public SrmPoChangeInfoDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoChangeInfoEntity_HI entity) {
		return super.save(entity);
	}
}
