package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonJuryScoreLinesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonJuryScoreLinesDAO_HI")
public class SrmPonJuryScoreLinesDAO_HI extends ViewObjectImpl<SrmPonJuryScoreLinesEntity_HI>  {
	public SrmPonJuryScoreLinesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonJuryScoreLinesEntity_HI entity) {
		return super.save(entity);
	}
}
