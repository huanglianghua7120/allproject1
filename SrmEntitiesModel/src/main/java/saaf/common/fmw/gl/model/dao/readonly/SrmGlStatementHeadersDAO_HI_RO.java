package saaf.common.fmw.gl.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.gl.model.entities.readonly.SrmGlStatementHeadersEntity_HI_RO;

@Component("srmGlStatementHeadersDAO_HI_RO")
public class SrmGlStatementHeadersDAO_HI_RO extends DynamicViewObjectImpl<SrmGlStatementHeadersEntity_HI_RO> {

    public SrmGlStatementHeadersDAO_HI_RO(){super();}
}
