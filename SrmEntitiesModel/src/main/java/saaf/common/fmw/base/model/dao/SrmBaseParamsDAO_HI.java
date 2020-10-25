package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SrmBaseParamsEntity_HI;
import org.springframework.stereotype.Component;

/**
 * 
 * @author zhy
 * Created by zhy on 2018/4/08.
 */
@Component("srmBaseParamsDAO_HI")
public class SrmBaseParamsDAO_HI extends ViewObjectImpl<SrmBaseParamsEntity_HI>  {
	public SrmBaseParamsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmBaseParamsEntity_HI entity) {
		return super.save(entity);
	}
}

