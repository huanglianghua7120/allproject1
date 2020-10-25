package saaf.common.fmw.cua.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.cua.model.entities.SrmCuaInvoicesEntity_HI;

@Component("srmCuaInvoicesDAO_HI")
public class SrmCuaInvoicesDAO_HI extends ViewObjectImpl<SrmCuaInvoicesEntity_HI>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmCuaInvoicesDAO_HI.class);
	public SrmCuaInvoicesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmCuaInvoicesEntity_HI entity) {
		return super.save(entity);
	}
}
