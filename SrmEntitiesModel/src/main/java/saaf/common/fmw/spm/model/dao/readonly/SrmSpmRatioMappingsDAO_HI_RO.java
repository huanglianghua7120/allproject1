package saaf.common.fmw.spm.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmRatioMappingsEntity_HI_RO;

@Component("srmSpmRatioMappingsDAO_HI_RO")
public class SrmSpmRatioMappingsDAO_HI_RO extends DynamicViewObjectImpl<SrmSpmRatioMappingsEntity_HI_RO> {

    public SrmSpmRatioMappingsDAO_HI_RO(){super();}
}
