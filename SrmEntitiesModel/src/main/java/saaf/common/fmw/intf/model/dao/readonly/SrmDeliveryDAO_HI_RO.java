package saaf.common.fmw.intf.model.dao.readonly;

import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import saaf.common.fmw.intf.model.entities.readonly.SrmDeliveryEntity_HI_RO;

@Component("srmDeliveryDAO_HI_RO")
public class SrmDeliveryDAO_HI_RO extends DynamicViewObjectImpl <SrmDeliveryEntity_HI_RO>{
	public SrmDeliveryDAO_HI_RO() {
		super();
	}
}
