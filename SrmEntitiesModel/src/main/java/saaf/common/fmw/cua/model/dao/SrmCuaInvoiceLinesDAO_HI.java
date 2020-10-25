package saaf.common.fmw.cua.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.cua.model.entities.SrmCuaInvoiceLinesEntity_HI;

@Component("srmCuaInvoiceLinesDAO_HI")
public class SrmCuaInvoiceLinesDAO_HI extends ViewObjectImpl<SrmCuaInvoiceLinesEntity_HI>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmCuaInvoiceLinesDAO_HI.class);
	public SrmCuaInvoiceLinesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmCuaInvoiceLinesEntity_HI entity) {
		return super.save(entity);
	}
}
