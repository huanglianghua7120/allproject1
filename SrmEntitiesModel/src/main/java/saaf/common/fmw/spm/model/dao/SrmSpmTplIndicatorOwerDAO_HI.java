package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmTplIndicatorOwerEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmTplIndicatorOwerDAO_HI")
public class SrmSpmTplIndicatorOwerDAO_HI extends ViewObjectImpl<SrmSpmTplIndicatorOwerEntity_HI>  {
	public SrmSpmTplIndicatorOwerDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmTplIndicatorOwerEntity_HI entity) {
		return super.save(entity);
	}
}
