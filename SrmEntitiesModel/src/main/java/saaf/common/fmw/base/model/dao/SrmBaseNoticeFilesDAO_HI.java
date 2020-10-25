package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SrmBaseNoticeFilesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmBaseNoticeFilesDAO_HI")
public class SrmBaseNoticeFilesDAO_HI extends ViewObjectImpl<SrmBaseNoticeFilesEntity_HI>  {
	public SrmBaseNoticeFilesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmBaseNoticeFilesEntity_HI entity) {
		return super.save(entity);
	}
}

