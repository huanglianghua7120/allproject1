package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoHeaderArchivesEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("srmPoHeaderArchivesDAO_HI")
public class SrmPoHeaderArchivesDAO_HI extends ViewObjectImpl<SrmPoHeaderArchivesEntity_HI>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoHeaderArchivesDAO_HI.class);
	public SrmPoHeaderArchivesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoHeaderArchivesEntity_HI entity) {
		return super.save(entity);
	}
}
