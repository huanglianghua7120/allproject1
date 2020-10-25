package saaf.common.fmw.rule.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.rule.model.entities.PageModelGroupDetailEntity_HI;

import org.springframework.stereotype.Component;

@Component("pageModelGroupDetailDAO_HI")
public class PageModelGroupDetailDAO_HI extends ViewObjectImpl<PageModelGroupDetailEntity_HI> {
    public PageModelGroupDetailDAO_HI() {
        super();
    }

    @Override
    public Object save(PageModelGroupDetailEntity_HI entity) {
        return super.save(entity);
    }
}

