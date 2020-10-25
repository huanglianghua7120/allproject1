package saaf.common.fmw.cua.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.cua.model.entities.SrmCuaAccountsEntity_HI;

@Component("srmCuaAccountsDAO_HI")
public class SrmCuaAccountsDAO_HI extends ViewObjectImpl<SrmCuaAccountsEntity_HI>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmCuaAccountsDAO_HI.class);
	public SrmCuaAccountsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmCuaAccountsEntity_HI entity) {
		return super.save(entity);
	}
}
