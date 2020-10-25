package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosSceneManageEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosSceneManageDAO_HI")
public class SrmPosSceneManageDAO_HI extends ViewObjectImpl<SrmPosSceneManageEntity_HI>  {
	public SrmPosSceneManageDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSceneManageEntity_HI entity) {
		return super.save(entity);
	}
}

