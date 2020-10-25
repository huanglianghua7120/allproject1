package saaf.common.fmw.okc.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.okc.model.entities.SrmOkcWatermarksEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmOkcWatermarksDAO_HI")
public class SrmOkcWatermarksDAO_HI extends ViewObjectImpl<SrmOkcWatermarksEntity_HI>  {
	public SrmOkcWatermarksDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmOkcWatermarksEntity_HI entity) {
		return super.save(entity);
	}
}
