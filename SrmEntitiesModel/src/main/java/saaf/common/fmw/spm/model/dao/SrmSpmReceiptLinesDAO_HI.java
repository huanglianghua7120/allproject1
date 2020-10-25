package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmReceiptLinesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmReceiptLinesDAO_HI")
public class SrmSpmReceiptLinesDAO_HI extends ViewObjectImpl<SrmSpmReceiptLinesEntity_HI>  {
	public SrmSpmReceiptLinesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmReceiptLinesEntity_HI entity) {
		return super.save(entity);
	}
}
