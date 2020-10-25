package saaf.common.fmw.cua.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.cua.model.entities.SrmCuaAccountLinesEntity_HI;

@Component("srmCuaAccountLinesDAO_HI")
public class SrmCuaAccountLinesDAO_HI extends ViewObjectImpl<SrmCuaAccountLinesEntity_HI>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmCuaAccountLinesDAO_HI.class);
	public SrmCuaAccountLinesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmCuaAccountLinesEntity_HI entity) {
		return super.save(entity);
	}
}
