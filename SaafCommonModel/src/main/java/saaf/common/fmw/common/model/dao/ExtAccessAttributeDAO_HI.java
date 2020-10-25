package saaf.common.fmw.common.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.common.model.entities.ExtAccessAttributeEntity_HI;

import org.springframework.stereotype.Component;

@Component("extAccessAttributeDAO_HI")
public class ExtAccessAttributeDAO_HI extends ViewObjectImpl<ExtAccessAttributeEntity_HI> {
    public ExtAccessAttributeDAO_HI() {
        super();
    }
}
