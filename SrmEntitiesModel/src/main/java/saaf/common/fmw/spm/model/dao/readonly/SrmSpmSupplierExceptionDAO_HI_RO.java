package saaf.common.fmw.spm.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmSupplierExceptionEntity_HI_RO;

@Component("srmSpmSupplierExceptionDAO_HI_RO")
public class SrmSpmSupplierExceptionDAO_HI_RO extends DynamicViewObjectImpl<SrmSpmSupplierExceptionEntity_HI_RO> {

    public SrmSpmSupplierExceptionDAO_HI_RO(){super();}
}
