package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SrmBaseNoticeViewersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmBaseNoticeViewersDAO_HI")
public class SrmBaseNoticeViewersDAO_HI extends ViewObjectImpl<SrmBaseNoticeViewersEntity_HI>  {
	public SrmBaseNoticeViewersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmBaseNoticeViewersEntity_HI entity) {
		return super.save(entity);
	}
}

