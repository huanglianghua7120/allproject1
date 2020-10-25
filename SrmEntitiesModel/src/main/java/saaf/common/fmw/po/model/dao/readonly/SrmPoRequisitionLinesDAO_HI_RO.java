package saaf.common.fmw.po.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRequisitionLinesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPoRequisitionLinesDAO_HI_RO")
public class SrmPoRequisitionLinesDAO_HI_RO extends DynamicViewObjectImpl<SrmPoRequisitionLinesEntity_HI_RO>  {
	public SrmPoRequisitionLinesDAO_HI_RO() {
		super();
	}

}
