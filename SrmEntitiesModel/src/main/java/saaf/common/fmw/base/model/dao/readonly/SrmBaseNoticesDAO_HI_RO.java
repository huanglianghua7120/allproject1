package saaf.common.fmw.base.model.dao.readonly;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SrmBaseNoticesEntity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

@Component("srmBaseNoticesDAO_HI_RO")
public class SrmBaseNoticesDAO_HI_RO extends DynamicViewObjectImpl<SrmBaseNoticesEntity_HI_RO> {
	
	public SrmBaseNoticesDAO_HI_RO(){
		super();
	}
}
