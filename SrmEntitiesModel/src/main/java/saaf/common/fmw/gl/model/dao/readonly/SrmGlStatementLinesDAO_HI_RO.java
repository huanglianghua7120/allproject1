package saaf.common.fmw.gl.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.gl.model.entities.readonly.SrmGlStatementLinesEntity_HI_RO;

@Component("srmGlStatementLinesDAO_HI_RO")
public class SrmGlStatementLinesDAO_HI_RO extends DynamicViewObjectImpl<SrmGlStatementLinesEntity_HI_RO> {

    public SrmGlStatementLinesDAO_HI_RO(){super();}
}
