
package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoRequisitionLinesEntity_HI;
import org.springframework.stereotype.Component;


import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoRequisitionLinesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoRequisitionLinesDAO_HI")
public class SrmPoRequisitionLinesDAO_HI extends ViewObjectImpl<SrmPoRequisitionLinesEntity_HI>  {
	public SrmPoRequisitionLinesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoRequisitionLinesEntity_HI entity) {
		return super.save(entity);
	}
}

