package saaf.common.fmw.pos.model.dao.readonly;

import org.springframework.stereotype.Component;

import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierInfo2Entity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

@Component("srmPosSupplierInfo2DAO_HI_RO")
public class SrmPosSupplierInfo2DAO_HI_RO extends DynamicViewObjectImpl<SrmPosSupplierInfo2Entity_HI_RO> {
    public SrmPosSupplierInfo2DAO_HI_RO() {
        super();
    }
}
