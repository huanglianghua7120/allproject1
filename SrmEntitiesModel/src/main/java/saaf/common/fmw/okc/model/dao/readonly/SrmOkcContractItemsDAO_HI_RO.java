package saaf.common.fmw.okc.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractItemsEntity_HI_RO;

@Component
public class SrmOkcContractItemsDAO_HI_RO extends DynamicViewObjectImpl<SrmOkcContractItemsEntity_HI_RO> {
    public SrmOkcContractItemsDAO_HI_RO() {
        super();
    }
}
