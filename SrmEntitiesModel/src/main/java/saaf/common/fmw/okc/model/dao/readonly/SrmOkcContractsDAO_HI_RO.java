package saaf.common.fmw.okc.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractsEntity_HI_RO;

@Component
public class SrmOkcContractsDAO_HI_RO extends DynamicViewObjectImpl<SrmOkcContractsEntity_HI_RO> {
    public SrmOkcContractsDAO_HI_RO() {
        super();
    }
}
