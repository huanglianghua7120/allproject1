package saaf.common.fmw.intf.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.intf.model.entities.readonly.SrmIntfLogsEntity_HI_RO;

@Component("srmIntfLogsDAO_HI_RO")
public class SrmIntfLogsDAO_HI_RO extends DynamicViewObjectImpl <SrmIntfLogsEntity_HI_RO>{
	public SrmIntfLogsDAO_HI_RO() {
		super();
	}
}
