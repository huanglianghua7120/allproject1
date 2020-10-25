package saaf.common.fmw.common.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.common.model.entities.ExtAccessMessageEntity_HI;
@Component("extAccessMessageDAO_HI")
public class ExtAccessMessageDAO_HI extends ViewObjectImpl<ExtAccessMessageEntity_HI> {
    public ExtAccessMessageDAO_HI() {
        super();
    }
}
