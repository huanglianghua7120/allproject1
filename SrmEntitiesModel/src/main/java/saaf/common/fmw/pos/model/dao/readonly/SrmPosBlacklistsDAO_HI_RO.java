package saaf.common.fmw.pos.model.dao.readonly;

import org.springframework.stereotype.Component;

import saaf.common.fmw.pos.model.entities.readonly.SrmPosBlacklistsEntity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

@Component("srmPosBlacklistsDAO_HI_RO")
public class SrmPosBlacklistsDAO_HI_RO extends DynamicViewObjectImpl<SrmPosBlacklistsEntity_HI_RO>{
	
	public SrmPosBlacklistsDAO_HI_RO() {
		super();
	}
}
