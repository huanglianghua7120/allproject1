package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierGradeLinesEntity_HI;
import org.springframework.stereotype.Component;

/**
 * 
 * @author zhy Created by zhy on 2018/09/04.
 */
@Component("srmPosSupplierGradeLinesDAO_HI")
public class SrmPosSupplierGradeLinesDAO_HI extends ViewObjectImpl<SrmPosSupplierGradeLinesEntity_HI>  {
	public SrmPosSupplierGradeLinesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSupplierGradeLinesEntity_HI entity) {
		return super.save(entity);
	}
}

