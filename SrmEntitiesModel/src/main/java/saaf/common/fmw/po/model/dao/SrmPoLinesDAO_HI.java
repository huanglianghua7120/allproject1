package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.po.model.entities.SrmPoLinesEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmPoLinesDAO_HI")
public class SrmPoLinesDAO_HI extends ViewObjectImpl<SrmPoLinesEntity_HI>  {
	public SrmPoLinesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoLinesEntity_HI entity) {
		return super.save(entity);
	}
}
