package saaf.common.fmw.intf.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.intf.model.entities.SrmIntfDeliveryEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmIntfDeliveryDAO_HI")
public class SrmIntfDeliveryDAO_HI extends ViewObjectImpl<SrmIntfDeliveryEntity_HI>  {
	public SrmIntfDeliveryDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmIntfDeliveryEntity_HI entity) {
		return super.save(entity);
	}
}
