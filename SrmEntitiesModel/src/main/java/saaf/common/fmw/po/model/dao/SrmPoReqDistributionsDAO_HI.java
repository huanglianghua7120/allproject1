package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.po.model.entities.SrmPoReqDistributionsEntity_HI;

@Component("srmPoReqDistributionsDAO_HI")
public class SrmPoReqDistributionsDAO_HI extends ViewObjectImpl<SrmPoReqDistributionsEntity_HI>  {

	public SrmPoReqDistributionsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoReqDistributionsEntity_HI entity) {
		return super.save(entity);
	}
}

