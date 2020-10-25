package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosChangeCertificateAfEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosChangeCredentialsEntity_HI_RO;

@Component("srmPosChangeCredentialsDAO_HI_RO")
public class SrmPosChangeCredentialsDAO_HI_RO extends DynamicViewObjectImpl<SrmPosChangeCredentialsEntity_HI_RO>  {
	public SrmPosChangeCredentialsDAO_HI_RO() {
		super();
	}

}
