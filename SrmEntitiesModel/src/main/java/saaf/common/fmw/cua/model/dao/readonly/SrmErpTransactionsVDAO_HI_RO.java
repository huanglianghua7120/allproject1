package saaf.common.fmw.cua.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.cua.model.entities.readonly.SrmErpTransactionsVEntity_HI_RO;

@Component("srmErpTransactionsVDAO_HI_RO")
public class SrmErpTransactionsVDAO_HI_RO extends DynamicViewObjectImpl<SrmErpTransactionsVEntity_HI_RO>  {
	public SrmErpTransactionsVDAO_HI_RO() {
		super();
	}

}
