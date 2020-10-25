
package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.springframework.stereotype.Component;


import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoRequisitionHeadersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoRequisitionHeadersDAO_HI")
public class SrmPoRequisitionHeadersDAO_HI extends ViewObjectImpl<SrmPoRequisitionHeadersEntity_HI>  {
	public SrmPoRequisitionHeadersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoRequisitionHeadersEntity_HI entity) {
		return super.save(entity);
	}
}

