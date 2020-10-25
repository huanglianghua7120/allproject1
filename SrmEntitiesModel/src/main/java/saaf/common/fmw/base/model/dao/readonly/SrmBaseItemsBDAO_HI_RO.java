package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseItemsBEntity_HI_RO;

@Component("srmBaseItemsBDAO_HI_RO")
public class SrmBaseItemsBDAO_HI_RO extends DynamicViewObjectImpl<SrmBaseItemsBEntity_HI_RO> {
    public SrmBaseItemsBDAO_HI_RO() {
        super();
    }
}
