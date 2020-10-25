package saaf.common.fmw.base.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SrmBaseItemsBEntity_HI;

@Component("srmBaseItemsBDAO_HI")
public class SrmBaseItemsBDAO_HI extends ViewObjectImpl<SrmBaseItemsBEntity_HI> {
    public SrmBaseItemsBDAO_HI() {
        super();
    }

    @Override
    public Object save(SrmBaseItemsBEntity_HI entity) {
        return super.save(entity);
    }
}
