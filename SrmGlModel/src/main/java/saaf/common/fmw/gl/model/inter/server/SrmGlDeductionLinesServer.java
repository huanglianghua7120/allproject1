package saaf.common.fmw.gl.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Map;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.gl.model.entities.SrmGlDeductionLinesEntity_HI;
import saaf.common.fmw.gl.model.dao.SrmGlDeductionLinesDAO_HI;
import saaf.common.fmw.gl.model.entities.readonly.SrmGlDeductionLinesEntity_HI_RO;
import saaf.common.fmw.gl.model.inter.ISrmGlDeductionLines;

@Component("srmGlDeductionLinesServer")
public class SrmGlDeductionLinesServer implements ISrmGlDeductionLines{

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmGlDeductionLinesServer.class);

	public SrmGlDeductionLinesServer() {
		super();
	}

	private ViewObject<SrmGlDeductionLinesEntity_HI> srmGlDeductionLinesDAO_HI;

	private BaseViewObject<SrmGlDeductionLinesEntity_HI_RO> srmGlDeductionLinesDAO_HI_RO;


}

