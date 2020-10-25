package saaf.common.fmw.bpm.model.dao.readonly;

import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.ActHiActinstEntity_HI;
import saaf.common.fmw.bpm.model.entities.readonly.ActHiActinstEntity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

@Component("actHiActinstDAO_HI_RO")
public class ActHiActinstDAO_HI_RO extends DynamicViewObjectImpl<ActHiActinstEntity_HI_RO>  {
	public ActHiActinstDAO_HI_RO() {
		super();
	}


}
