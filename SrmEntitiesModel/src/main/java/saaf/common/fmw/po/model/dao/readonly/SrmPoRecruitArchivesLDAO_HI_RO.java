package saaf.common.fmw.po.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitArchivesLEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPoRecruitArchivesLDAO_HI_RO")
public class SrmPoRecruitArchivesLDAO_HI_RO extends DynamicViewObjectImpl<SrmPoRecruitArchivesLEntity_HI_RO>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitArchivesLDAO_HI_RO.class);
	public SrmPoRecruitArchivesLDAO_HI_RO() {
		super();
	}

}
