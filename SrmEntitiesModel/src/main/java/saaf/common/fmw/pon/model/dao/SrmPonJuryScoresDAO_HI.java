package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonJuryScoresEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonJuryScoresDAO_HI")
public class SrmPonJuryScoresDAO_HI extends ViewObjectImpl<SrmPonJuryScoresEntity_HI>  {
	public SrmPonJuryScoresDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonJuryScoresEntity_HI entity) {
		return super.save(entity);
	}
}
