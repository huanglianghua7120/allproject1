package saaf.common.fmw.okc.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractPaymentsEntity_HI_RO;

@Component
public class SrmOkcContractPaymentsDAO_HI_RO extends DynamicViewObjectImpl<SrmOkcContractPaymentsEntity_HI_RO> {
    public SrmOkcContractPaymentsDAO_HI_RO() {
        super();
    }
}
