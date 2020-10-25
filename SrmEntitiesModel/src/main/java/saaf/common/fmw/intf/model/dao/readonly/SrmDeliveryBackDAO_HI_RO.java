


package saaf.common.fmw.intf.model.dao.readonly;

import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import saaf.common.fmw.intf.model.entities.readonly.SrmDeliveryBackEntity_HI_RO;

@Component("srmDeliveryBackDAO_HI_RO")
public class SrmDeliveryBackDAO_HI_RO extends DynamicViewObjectImpl <SrmDeliveryBackEntity_HI_RO>{
	public SrmDeliveryBackDAO_HI_RO() {
		super();
	}
}
