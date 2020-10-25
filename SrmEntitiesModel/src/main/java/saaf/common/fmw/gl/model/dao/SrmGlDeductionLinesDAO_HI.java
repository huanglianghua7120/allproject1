package saaf.common.fmw.gl.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.gl.model.entities.SrmGlDeductionLinesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmGlDeductionLinesDAO_HI")
public class SrmGlDeductionLinesDAO_HI extends ViewObjectImpl<SrmGlDeductionLinesEntity_HI>  {

	public SrmGlDeductionLinesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmGlDeductionLinesEntity_HI entity) {
		return super.save(entity);
	}
}

