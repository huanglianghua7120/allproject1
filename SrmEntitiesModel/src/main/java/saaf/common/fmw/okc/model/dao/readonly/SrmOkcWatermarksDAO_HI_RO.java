package saaf.common.fmw.okc.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcWatermarksEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmOkcWatermarksDAO_HI_RO")
public class SrmOkcWatermarksDAO_HI_RO extends DynamicViewObjectImpl<SrmOkcWatermarksEntity_HI_RO>  {
	public SrmOkcWatermarksDAO_HI_RO() {
		super();
	}

}
