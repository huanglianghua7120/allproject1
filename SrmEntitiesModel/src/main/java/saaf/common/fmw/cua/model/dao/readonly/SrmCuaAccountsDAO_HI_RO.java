package saaf.common.fmw.cua.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaAccountsEntity_HI_RO;

@Component("srmCuaAccountsDAO_HI_RO")
public class SrmCuaAccountsDAO_HI_RO extends DynamicViewObjectImpl<SrmCuaAccountsEntity_HI_RO>  {
		public SrmCuaAccountsDAO_HI_RO() {
		super();
	}

}
