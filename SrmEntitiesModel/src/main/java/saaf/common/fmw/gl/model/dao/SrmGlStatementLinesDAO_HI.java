package saaf.common.fmw.gl.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.gl.model.entities.SrmGlStatementLinesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmGlStatementLinesDAO_HI")
public class SrmGlStatementLinesDAO_HI extends ViewObjectImpl<SrmGlStatementLinesEntity_HI>  {
	public SrmGlStatementLinesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmGlStatementLinesEntity_HI entity) {
		return super.save(entity);
	}
}

