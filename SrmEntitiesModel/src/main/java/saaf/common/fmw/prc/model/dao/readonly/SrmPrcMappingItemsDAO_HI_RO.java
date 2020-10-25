package saaf.common.fmw.prc.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pos.model.entities.readonly.SaafUserEmployees2Entity_HI_RO;
import saaf.common.fmw.prc.model.entities.readonly.SrmPrcMappingItmesEntity_HI_RO;

@Component("srmPrcMappingItemsDAO_HI_RO")
public class SrmPrcMappingItemsDAO_HI_RO extends DynamicViewObjectImpl<SrmPrcMappingItmesEntity_HI_RO> {
    public SrmPrcMappingItemsDAO_HI_RO() {
        super();
    }
}
