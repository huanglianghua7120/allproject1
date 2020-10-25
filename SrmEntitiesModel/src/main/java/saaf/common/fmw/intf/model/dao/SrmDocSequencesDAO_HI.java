package saaf.common.fmw.intf.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.intf.model.entities.SrmDocSequencesEntity_HI;

@Component("srmDocSequencesDAO_HI")
public class SrmDocSequencesDAO_HI extends ViewObjectImpl<SrmDocSequencesEntity_HI> {
    public SrmDocSequencesDAO_HI() {
        super();
    }
}
