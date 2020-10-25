package saaf.common.fmw.gl.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.gl.model.entities.readonly.SrmGlDeductionHeadersEntity_HI_RO;

@Component("srmGlDeductionHeadersDAO_HI_RO")
public class SrmGlDeductionHeadersDAO_HI_RO extends DynamicViewObjectImpl<SrmGlDeductionHeadersEntity_HI_RO> {

    public SrmGlDeductionHeadersDAO_HI_RO(){super();}
}
