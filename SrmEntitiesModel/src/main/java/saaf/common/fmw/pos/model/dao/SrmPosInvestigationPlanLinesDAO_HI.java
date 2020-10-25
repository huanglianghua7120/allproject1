package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosInvestigationPlanLinesEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("srmPosInvestigationPlanLinesDAO_HI")
public class SrmPosInvestigationPlanLinesDAO_HI extends ViewObjectImpl<SrmPosInvestigationPlanLinesEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosInvestigationPlanLinesDAO_HI.class);

	public SrmPosInvestigationPlanLinesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosInvestigationPlanLinesEntity_HI entity) {
		return super.save(entity);
	}
}
