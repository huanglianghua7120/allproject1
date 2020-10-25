package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SrmBaseNoticeReceiversEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmBaseNoticeReceiversDAO_HI")
public class SrmBaseNoticeReceiversDAO_HI extends ViewObjectImpl<SrmBaseNoticeReceiversEntity_HI>  {
	public SrmBaseNoticeReceiversDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmBaseNoticeReceiversEntity_HI entity) {
		return super.save(entity);
	}
}

