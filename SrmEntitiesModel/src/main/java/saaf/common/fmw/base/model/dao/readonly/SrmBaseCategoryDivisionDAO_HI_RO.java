package saaf.common.fmw.base.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseCategoryDivisionEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmBaseCategoryDivisionDAO_HI_RO")
@Transactional(readOnly=false)
public class SrmBaseCategoryDivisionDAO_HI_RO extends DynamicViewObjectImpl<SrmBaseCategoryDivisionEntity_HI_RO>  {
	public SrmBaseCategoryDivisionDAO_HI_RO() {
		super();
	}

}
