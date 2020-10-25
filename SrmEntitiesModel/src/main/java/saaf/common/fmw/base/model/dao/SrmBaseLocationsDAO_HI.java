package saaf.common.fmw.base.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SrmBaseItemsBEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseLocationsEntity_HI;

@Component("srmBaseLocationsDAO_HI")
public class SrmBaseLocationsDAO_HI extends ViewObjectImpl<SrmBaseLocationsEntity_HI> {
    public SrmBaseLocationsDAO_HI() {
        super();
    }

    @Override
    public Object save(SrmBaseLocationsEntity_HI entity) {
        return super.save(entity);
    }
}
