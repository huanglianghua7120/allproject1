package saaf.common.fmw.common.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.common.model.entities.SaafImagesEntity_HI;

@Component("saafImagesDAO_HI")
public class SaafImagesDAO_HI extends ViewObjectImpl<SaafImagesEntity_HI> {
    public SaafImagesDAO_HI() {
        super();
    }
}
