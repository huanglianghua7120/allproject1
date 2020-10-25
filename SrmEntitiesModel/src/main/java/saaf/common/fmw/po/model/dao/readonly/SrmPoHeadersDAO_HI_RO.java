package saaf.common.fmw.po.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.po.model.entities.readonly.SrmPoHeadersEntity_HI_RO;

@Component("srmPoHeadersDAO_HI_RO")
public class SrmPoHeadersDAO_HI_RO  extends DynamicViewObjectImpl<SrmPoHeadersEntity_HI_RO> {
	public SrmPoHeadersDAO_HI_RO() {
		super();
		
	}
	
}
