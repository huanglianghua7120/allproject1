package saaf.common.fmw.po.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitmentHeaderEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPoRecruitmentHeaderDAO_HI_RO")
public class SrmPoRecruitmentHeaderDAO_HI_RO extends DynamicViewObjectImpl<SrmPoRecruitmentHeaderEntity_HI_RO>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitmentHeaderDAO_HI_RO.class);
	public SrmPoRecruitmentHeaderDAO_HI_RO() {
		super();
	}

}
