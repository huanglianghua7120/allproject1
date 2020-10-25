package saaf.common.fmw.cua.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaInvoiceLinesEntity_HI_RO;

@Component("srmCuaInvoiceLinesDAO_HI_RO")
public class SrmCuaInvoiceLinesDAO_HI_RO extends DynamicViewObjectImpl<SrmCuaInvoiceLinesEntity_HI_RO>  {
	public SrmCuaInvoiceLinesDAO_HI_RO() {
		super();
	}

}
