package saaf.common.fmw.spm.model.dao.readonly;

import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import saaf.common.fmw.spm.model.entities.readonly.SrmSpmRequestDatasEntity_HI_RO;

@Component
public class SrmSpmRequestDatasDAO_HI_RO extends DynamicViewObjectImpl<SrmSpmRequestDatasEntity_HI_RO>{
	public SrmSpmRequestDatasDAO_HI_RO() {
		super();
	}
}
