package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmReceiptHeadersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmReceiptHeadersDAO_HI")
public class SrmSpmReceiptHeadersDAO_HI extends ViewObjectImpl<SrmSpmReceiptHeadersEntity_HI>  {
	public SrmSpmReceiptHeadersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmReceiptHeadersEntity_HI entity) {
		return super.save(entity);
	}
}
