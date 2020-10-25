package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoRecruitmentHeaderEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("srmPoRecruitmentHeaderDAO_HI")
public class SrmPoRecruitmentHeaderDAO_HI extends ViewObjectImpl<SrmPoRecruitmentHeaderEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitmentHeaderDAO_HI.class);
	public SrmPoRecruitmentHeaderDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoRecruitmentHeaderEntity_HI entity) {
		return super.save(entity);
	}
}
