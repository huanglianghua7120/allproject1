package saaf.common.fmw.po.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitArchivesHEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPoRecruitArchivesHDAO_HI_RO")
public class SrmPoRecruitArchivesHDAO_HI_RO extends DynamicViewObjectImpl<SrmPoRecruitArchivesHEntity_HI_RO>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitArchivesHDAO_HI_RO.class);
	public SrmPoRecruitArchivesHDAO_HI_RO() {
		super();
	}

}
