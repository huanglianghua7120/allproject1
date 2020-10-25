package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosManagerCateOthersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosManagerCateOthersDAO_HI")
public class SrmPosManagerCateOthersDAO_HI extends ViewObjectImpl<SrmPosManagerCateOthersEntity_HI>  {
	public SrmPosManagerCateOthersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosManagerCateOthersEntity_HI entity) {
		return super.save(entity);
	}
}

