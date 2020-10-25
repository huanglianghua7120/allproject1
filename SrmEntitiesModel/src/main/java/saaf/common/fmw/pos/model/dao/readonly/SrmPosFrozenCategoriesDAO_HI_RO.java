package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.pon.model.entities.readonly.SrmPosFrozenCategoriesEntity_HI_RO;

@Component("srmPosFrozenCategoriesDAO_HI_RO")
public class SrmPosFrozenCategoriesDAO_HI_RO extends DynamicViewObjectImpl<SrmPosFrozenCategoriesEntity_HI_RO>  {
	public SrmPosFrozenCategoriesDAO_HI_RO() {
		super();
	}

}
