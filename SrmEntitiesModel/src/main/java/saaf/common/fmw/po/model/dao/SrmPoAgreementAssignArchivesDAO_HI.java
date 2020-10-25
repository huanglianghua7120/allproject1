package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoAgreementAssignArchivesEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("srmPoAgreementAssignArchivesDAO_HI")
public class SrmPoAgreementAssignArchivesDAO_HI extends ViewObjectImpl<SrmPoAgreementAssignArchivesEntity_HI>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoAgreementAssignArchivesDAO_HI.class);
	public SrmPoAgreementAssignArchivesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoAgreementAssignArchivesEntity_HI entity) {
		return super.save(entity);
	}
}
