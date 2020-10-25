package saaf.common.fmw.genform.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.genform.model.entities.readonly.SaafDynamicFormInfoEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("saafDynamicFormInfoDAO_HI_RO")
public class SaafDynamicFormInfoDAO_HI_RO extends DynamicViewObjectImpl<SaafDynamicFormInfoEntity_HI_RO>  {
	
	public SaafDynamicFormInfoDAO_HI_RO() {
		super();
	}

	public static final String COUNTS_SQL = "select count(id) from saaf_dynamic_form_info ";
	
}
