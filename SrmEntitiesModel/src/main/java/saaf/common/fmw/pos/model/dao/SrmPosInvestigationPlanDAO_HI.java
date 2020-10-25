package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosInvestigationPlanEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("srmPosInvestigationPlanDAO_HI")
public class SrmPosInvestigationPlanDAO_HI extends ViewObjectImpl<SrmPosInvestigationPlanEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosInvestigationPlanDAO_HI.class);

	public SrmPosInvestigationPlanDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosInvestigationPlanEntity_HI entity) {
		return super.save(entity);
	}
}
