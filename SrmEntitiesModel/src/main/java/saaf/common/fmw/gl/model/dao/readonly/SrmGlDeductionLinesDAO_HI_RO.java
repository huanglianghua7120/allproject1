package saaf.common.fmw.gl.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.gl.model.entities.readonly.SrmGlDeductionLinesEntity_HI_RO;

@Component("srmGlDeductionLinesDAO_HI_RO")
public class SrmGlDeductionLinesDAO_HI_RO extends DynamicViewObjectImpl<SrmGlDeductionLinesEntity_HI_RO> {

    public SrmGlDeductionLinesDAO_HI_RO(){super();}
}
