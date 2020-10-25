package saaf.common.fmw.intf.model.dao.readonly;

import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import saaf.common.fmw.intf.model.entities.readonly.SrmApprovedListEntity_HI_RO;

@Component("srmApprovedListDAO_HI_RO")
public class SrmApprovedListDAO_HI_RO extends DynamicViewObjectImpl <SrmApprovedListEntity_HI_RO>{
	public SrmApprovedListDAO_HI_RO() {
		super();
	}
}
