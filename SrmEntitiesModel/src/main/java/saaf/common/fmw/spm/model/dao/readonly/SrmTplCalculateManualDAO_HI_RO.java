package saaf.common.fmw.spm.model.dao.readonly;

import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import saaf.common.fmw.spm.model.entities.readonly.SrmTplCalculateManualEntity_HI_RO;

@Component
public class SrmTplCalculateManualDAO_HI_RO extends DynamicViewObjectImpl<SrmTplCalculateManualEntity_HI_RO>{
	public SrmTplCalculateManualDAO_HI_RO() {
		super();
	}
}
