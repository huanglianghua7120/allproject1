package saaf.common.fmw.pos.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pos.model.entities.readonly.SaafUserEmployees2Entity_HI_RO;

@Component("saafUserEmployees2DAO_HI_RO")
public class SaafUserEmployees2DAO_HI_RO extends DynamicViewObjectImpl<SaafUserEmployees2Entity_HI_RO> {
    public SaafUserEmployees2DAO_HI_RO() {
        super();
    }
}
