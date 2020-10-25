package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoRecruitArchivesHEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("srmPoRecruitArchivesHDAO_HI")
public class SrmPoRecruitArchivesHDAO_HI extends ViewObjectImpl<SrmPoRecruitArchivesHEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitArchivesHDAO_HI.class);
	public SrmPoRecruitArchivesHDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoRecruitArchivesHEntity_HI entity) {
		return super.save(entity);
	}
}
