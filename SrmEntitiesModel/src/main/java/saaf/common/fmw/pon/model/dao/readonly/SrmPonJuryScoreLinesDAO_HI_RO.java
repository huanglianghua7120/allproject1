package saaf.common.fmw.pon.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonJuryScoreLinesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPonJuryScoreLinesDAO_HI_RO")
public class SrmPonJuryScoreLinesDAO_HI_RO extends DynamicViewObjectImpl<SrmPonJuryScoreLinesEntity_HI_RO>  {
	public SrmPonJuryScoreLinesDAO_HI_RO() {
		super();
	}

}
