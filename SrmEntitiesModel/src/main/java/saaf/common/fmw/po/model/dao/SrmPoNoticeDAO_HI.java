package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoNoticeEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoNoticeDAO_HI")
public class SrmPoNoticeDAO_HI extends ViewObjectImpl<SrmPoNoticeEntity_HI>  {
	public SrmPoNoticeDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoNoticeEntity_HI entity) {
		return super.save(entity);
	}
}

