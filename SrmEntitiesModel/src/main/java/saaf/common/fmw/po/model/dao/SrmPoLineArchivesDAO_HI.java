package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoLineArchivesEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("srmPoLineArchivesDAO_HI")
public class SrmPoLineArchivesDAO_HI extends ViewObjectImpl<SrmPoLineArchivesEntity_HI>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoLineArchivesDAO_HI.class);
	public SrmPoLineArchivesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoLineArchivesEntity_HI entity) {
		return super.save(entity);
	}
}
