package saaf.common.fmw.common.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.common.model.entities.SaafDocSequencesEntity_HI;

@Component("saafDocSequencesDAO_HI")
public class SaafDocSequencesDAO_HI extends ViewObjectImpl<SaafDocSequencesEntity_HI> {
    public SaafDocSequencesDAO_HI() {
        super();
    }
}
