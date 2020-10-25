package saaf.common.fmw.common.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.common.model.entities.readonly.ExtAccessAttributeEntity_HI_RO;

@Component("extAccessAttributeDAO_HI_RO")
public class ExtAccessAttributeDAO_HI_RO extends DynamicViewObjectImpl<ExtAccessAttributeEntity_HI_RO> {
    public ExtAccessAttributeDAO_HI_RO() {
        super();
    }
}
