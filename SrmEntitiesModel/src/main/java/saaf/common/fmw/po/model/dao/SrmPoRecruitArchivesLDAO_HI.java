package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoRecruitArchivesLEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("srmPoRecruitArchivesLDAO_HI")
public class SrmPoRecruitArchivesLDAO_HI extends ViewObjectImpl<SrmPoRecruitArchivesLEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitArchivesLDAO_HI.class);
	public SrmPoRecruitArchivesLDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoRecruitArchivesLEntity_HI entity) {
		return super.save(entity);
	}
}
