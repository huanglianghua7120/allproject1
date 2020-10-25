package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoNoticeLineEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoNoticeLineDAO_HI")
public class SrmPoNoticeLineDAO_HI extends ViewObjectImpl<SrmPoNoticeLineEntity_HI>  {
	public SrmPoNoticeLineDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoNoticeLineEntity_HI entity) {
		return super.save(entity);
	}
}

