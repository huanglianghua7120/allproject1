package saaf.common.fmw.gl.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.gl.model.entities.SrmGlDeductionHeadersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmGlDeductionHeadersDAO_HI")
public class SrmGlDeductionHeadersDAO_HI extends ViewObjectImpl<SrmGlDeductionHeadersEntity_HI>  {

	public SrmGlDeductionHeadersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmGlDeductionHeadersEntity_HI entity) {
		return super.save(entity);
	}
}

