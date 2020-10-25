package saaf.common.fmw.spm.model.dao.readonly;

import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplIndicatorItemsEntity_HI_RO;

@Component
public class SrmSpmTplIndicatorItemsDAO_HI_RO extends DynamicViewObjectImpl<SrmSpmTplIndicatorItemsEntity_HI_RO>{
	public SrmSpmTplIndicatorItemsDAO_HI_RO() {
		super();
	}
}
