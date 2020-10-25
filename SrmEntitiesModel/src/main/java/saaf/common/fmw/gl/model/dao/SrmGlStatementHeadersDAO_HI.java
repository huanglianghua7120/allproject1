package saaf.common.fmw.gl.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.gl.model.entities.SrmGlStatementHeadersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmGlStatementHeadersDAO_HI")
public class SrmGlStatementHeadersDAO_HI extends ViewObjectImpl<SrmGlStatementHeadersEntity_HI>  {
	public SrmGlStatementHeadersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmGlStatementHeadersEntity_HI entity) {
		return super.save(entity);
	}
}

