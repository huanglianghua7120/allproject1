package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierGradeEntity_HI;
import org.springframework.stereotype.Component;

/**
 * 
 * @author zhy Created by zhy on 2018/09/04.
 */
@Component("srmPosSupplierGradeDAO_HI")
public class SrmPosSupplierGradeDAO_HI extends ViewObjectImpl<SrmPosSupplierGradeEntity_HI>  {
	public SrmPosSupplierGradeDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSupplierGradeEntity_HI entity) {
		return super.save(entity);
	}
}

