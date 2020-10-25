package saaf.common.fmw.spm.model.dao.readonly;

import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplIndicatorsEntity_HI_RO;

@Component
public class SrmSpmTplIndicatorsDAO_HI_RO extends DynamicViewObjectImpl<SrmSpmTplIndicatorsEntity_HI_RO>{
	public SrmSpmTplIndicatorsDAO_HI_RO() {
		super();
	}
}
