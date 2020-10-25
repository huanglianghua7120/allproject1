package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.po.model.entities.SrmPoChangeInfoHisEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmPoChangeInfoHisDAO_HI")
public class SrmPoChangeInfoHisDAO_HI extends ViewObjectImpl<SrmPoChangeInfoHisEntity_HI>  {
	public SrmPoChangeInfoHisDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoChangeInfoHisEntity_HI entity) {
		return super.save(entity);
	}
}
