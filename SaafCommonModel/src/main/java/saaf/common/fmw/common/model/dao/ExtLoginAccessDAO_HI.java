package saaf.common.fmw.common.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.common.model.entities.ExtLoginAccessEntity_HI;
import saaf.common.fmw.common.model.inter.ISessionBean;

@Component("extLoginAccessDAO_HI")
public class ExtLoginAccessDAO_HI extends ViewObjectImpl<ExtLoginAccessEntity_HI> {
    @Autowired
    private ISessionBean sessionBeanServer;

    public ExtLoginAccessDAO_HI() {
        super();
    }
}
