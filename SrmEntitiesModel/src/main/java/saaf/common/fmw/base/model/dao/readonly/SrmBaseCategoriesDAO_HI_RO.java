package saaf.common.fmw.base.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseCategoriesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmBaseCategoriesDAO_HI_RO")
public class SrmBaseCategoriesDAO_HI_RO extends DynamicViewObjectImpl<SrmBaseCategoriesEntity_HI_RO>  {
	public SrmBaseCategoriesDAO_HI_RO() {
		super();
	}

}
