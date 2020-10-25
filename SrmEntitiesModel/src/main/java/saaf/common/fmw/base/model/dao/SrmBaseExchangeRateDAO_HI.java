package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SrmBaseExchangeRateEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("srmBaseExchangeRateDAO_HI")
public class SrmBaseExchangeRateDAO_HI extends ViewObjectImpl<SrmBaseExchangeRateEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseExchangeRateDAO_HI.class);

	public SrmBaseExchangeRateDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmBaseExchangeRateEntity_HI entity) {
		return super.save(entity);
	}
}
