package saaf.common.fmw.cua.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaInvoicesEntity_HI_RO;

@Component("srmCuaInvoicesDAO_HI_RO")
public class SrmCuaInvoicesDAO_HI_RO extends DynamicViewObjectImpl<SrmCuaInvoicesEntity_HI_RO>  {
	public SrmCuaInvoicesDAO_HI_RO() {
		super();
	}

}
