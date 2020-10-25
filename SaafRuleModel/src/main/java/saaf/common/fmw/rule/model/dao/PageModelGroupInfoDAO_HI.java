package saaf.common.fmw.rule.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.rule.model.entities.PageModelGroupInfoEntity_HI;

import org.springframework.stereotype.Component;

@Component("pageModelGroupInfoDAO_HI")
public class PageModelGroupInfoDAO_HI extends ViewObjectImpl<PageModelGroupInfoEntity_HI> {
    public PageModelGroupInfoDAO_HI() {
        super();
    }

    @Override
    public Object save(PageModelGroupInfoEntity_HI entity) {
        return super.save(entity);
    }
}

