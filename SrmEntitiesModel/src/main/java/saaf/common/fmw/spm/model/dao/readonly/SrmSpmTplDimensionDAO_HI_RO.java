package saaf.common.fmw.spm.model.dao.readonly;

import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplDimensionEntity_HI_RO;

@Component
public class SrmSpmTplDimensionDAO_HI_RO extends DynamicViewObjectImpl<SrmSpmTplDimensionEntity_HI_RO>{
	public SrmSpmTplDimensionDAO_HI_RO() {
		super();
	}
}
