package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoRecruitmentLineEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("srmPoRecruitmentLineDAO_HI")
public class SrmPoRecruitmentLineDAO_HI extends ViewObjectImpl<SrmPoRecruitmentLineEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitmentLineDAO_HI.class);
	public SrmPoRecruitmentLineDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoRecruitmentLineEntity_HI entity) {
		return super.save(entity);
	}
}
