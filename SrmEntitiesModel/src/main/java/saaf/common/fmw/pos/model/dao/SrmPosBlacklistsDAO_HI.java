package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosBlacklistsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosBlacklistsDAO_HI")
public class SrmPosBlacklistsDAO_HI extends ViewObjectImpl<SrmPosBlacklistsEntity_HI>  {
	public SrmPosBlacklistsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosBlacklistsEntity_HI entity) {
		return super.save(entity);
	}
}

