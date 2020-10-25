package saaf.common.fmw.pon.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonJuryScoresEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPonJuryScoresDAO_HI_RO")
public class SrmPonJuryScoresDAO_HI_RO extends DynamicViewObjectImpl<SrmPonJuryScoresEntity_HI_RO>  {
	public SrmPonJuryScoresDAO_HI_RO() {
		super();
	}

}
