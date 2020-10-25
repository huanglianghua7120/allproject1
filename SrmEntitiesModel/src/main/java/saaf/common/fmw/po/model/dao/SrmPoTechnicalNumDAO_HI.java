package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoTechnicalNumEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("srmPoTechnicalNumDAO_HI")
public class SrmPoTechnicalNumDAO_HI extends ViewObjectImpl<SrmPoTechnicalNumEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoTechnicalNumDAO_HI.class);

	public SrmPoTechnicalNumDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoTechnicalNumEntity_HI entity) {
		return super.save(entity);
	}
}
