package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.po.model.entities.SrmPoHeadersEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmPoHeadersDAO_HI")
public class SrmPoHeadersDAO_HI extends ViewObjectImpl<SrmPoHeadersEntity_HI>  {
	public SrmPoHeadersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoHeadersEntity_HI entity) {
		return super.save(entity);
	}
}
