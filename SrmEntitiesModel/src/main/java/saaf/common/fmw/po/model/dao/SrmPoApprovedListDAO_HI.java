package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.po.model.entities.SrmPoApprovedListEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmPoApprovedListDAO_HI")
public class SrmPoApprovedListDAO_HI extends ViewObjectImpl<SrmPoApprovedListEntity_HI>  {
	public SrmPoApprovedListDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoApprovedListEntity_HI entity) {
		return super.save(entity);
	}
}
