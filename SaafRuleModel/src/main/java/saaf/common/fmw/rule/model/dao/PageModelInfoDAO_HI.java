package saaf.common.fmw.rule.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.rule.model.entities.PageModelInfoEntity_HI;

import org.springframework.stereotype.Component;

@Component("pageModelInfoDAO_HI")
public class PageModelInfoDAO_HI extends ViewObjectImpl<PageModelInfoEntity_HI> {
    public PageModelInfoDAO_HI() {
        super();
    }

    @Override
    public Object save(PageModelInfoEntity_HI entity) {
        return super.save(entity);
    }
}

