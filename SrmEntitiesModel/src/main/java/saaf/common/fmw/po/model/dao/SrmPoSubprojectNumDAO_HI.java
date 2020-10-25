package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saaf.common.fmw.po.model.entities.SrmPoSubprojectNumEntity_HI;

@Component("srmPoSubprojectNumDAO_HI")
public class SrmPoSubprojectNumDAO_HI extends ViewObjectImpl<SrmPoSubprojectNumEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoSubprojectNumDAO_HI.class);

	public SrmPoSubprojectNumDAO_HI() {
		super();
	}

}
