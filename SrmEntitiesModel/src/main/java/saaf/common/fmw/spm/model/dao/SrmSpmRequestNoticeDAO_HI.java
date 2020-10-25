package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmRequestNoticeEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmRequestNoticeDAO_HI")
public class SrmSpmRequestNoticeDAO_HI extends ViewObjectImpl<SrmSpmRequestNoticeEntity_HI>  {
	public SrmSpmRequestNoticeDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmRequestNoticeEntity_HI entity) {
		return super.save(entity);
	}
}
