package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmShortMaterialInvInfoEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmShortMaterialInvInfoDAO_HI")
public class SrmShortMaterialInvInfoDAO_HI extends ViewObjectImpl<SrmShortMaterialInvInfoEntity_HI>  {
	public SrmShortMaterialInvInfoDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmShortMaterialInvInfoEntity_HI entity) {
		return super.save(entity);
	}
}
