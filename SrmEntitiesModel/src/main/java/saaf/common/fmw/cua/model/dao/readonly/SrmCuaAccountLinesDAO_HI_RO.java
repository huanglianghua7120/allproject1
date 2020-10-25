package saaf.common.fmw.cua.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaAccountLinesEntity_HI_RO;

@Component("srmCuaAccountLinesDAO_HI_RO")
public class SrmCuaAccountLinesDAO_HI_RO extends DynamicViewObjectImpl<SrmCuaAccountLinesEntity_HI_RO>  {
		public SrmCuaAccountLinesDAO_HI_RO() {
		super();
	}

}
