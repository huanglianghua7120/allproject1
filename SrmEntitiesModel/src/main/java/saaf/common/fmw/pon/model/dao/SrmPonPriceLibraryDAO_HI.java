package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonPriceLibraryEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("srmPonPriceLibraryDAO_HI")
public class SrmPonPriceLibraryDAO_HI extends ViewObjectImpl<SrmPonPriceLibraryEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPonPriceLibraryDAO_HI.class);
	public SrmPonPriceLibraryDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonPriceLibraryEntity_HI entity) {
		return super.save(entity);
	}
}
