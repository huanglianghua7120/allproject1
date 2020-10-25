package saaf.common.fmw.spm.model.inter.server;

import saaf.common.fmw.spm.model.inter.ISrmSpmTplIndicatorOwer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.spm.model.entities.SrmSpmTplIndicatorOwerEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmSpmTplIndicatorOwerServer")
public class SrmSpmTplIndicatorOwerServer implements ISrmSpmTplIndicatorOwer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmTplIndicatorOwerServer.class);

    @Autowired
    private ViewObject<SrmSpmTplIndicatorOwerEntity_HI> srmSpmTplIndicatorOwerDAO_HI;

    public SrmSpmTplIndicatorOwerServer() {
        super();
    }

    public List<SrmSpmTplIndicatorOwerEntity_HI> findSrmSpmTplIndicatorOwerInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmSpmTplIndicatorOwerEntity_HI> findListResult = srmSpmTplIndicatorOwerDAO_HI.findList("FROM SrmSpmTplIndicatorOwerEntity_HI", queryParamMap);
        return findListResult;
    }

    public Object saveSrmSpmTplIndicatorOwerInfo(JSONObject queryParamJSON) {
        SrmSpmTplIndicatorOwerEntity_HI srmSpmTplIndicatorOwerEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmSpmTplIndicatorOwerEntity_HI.class);
        Object resultData = srmSpmTplIndicatorOwerDAO_HI.save(srmSpmTplIndicatorOwerEntity_HI);
        return resultData;
    }

}
