package saaf.common.fmw.po.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitmentLineEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPoRecruitmentLineDAO_HI_RO")
public class SrmPoRecruitmentLineDAO_HI_RO extends DynamicViewObjectImpl<SrmPoRecruitmentLineEntity_HI_RO>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitmentLineDAO_HI_RO.class);
	public SrmPoRecruitmentLineDAO_HI_RO() {
		super();
	}

}
