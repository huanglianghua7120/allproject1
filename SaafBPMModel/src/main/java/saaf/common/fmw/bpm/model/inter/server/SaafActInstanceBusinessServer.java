package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.ISaafActInstanceBusiness;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.yhg.base.utils.SToolUtils;

import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.utils.SaafToolUtils;
import saaf.common.fmw.bpm.model.entities.SaafActInstanceBusinessEntity_HI;
import saaf.common.fmw.bpm.model.dao.SaafActInstanceBusinessDAO_HI;

import com.yhg.hibernate.core.dao.ViewObject;

@Component("saafActInstanceBusinessServer")
public class SaafActInstanceBusinessServer implements ISaafActInstanceBusiness {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaafActInstanceBusinessServer.class);
    @Autowired
    private ViewObject<SaafActInstanceBusinessEntity_HI> saafActInstanceBusinessDAO_HI;

    public SaafActInstanceBusinessServer() {
        super();
    }

    public String findSaafActInstanceBusinessInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SaafActInstanceBusinessEntity_HI> findListResult = saafActInstanceBusinessDAO_HI.findList("from SaafActInstanceBusinessEntity_HI", queryParamMap);
        String resultData = JSON.toJSONString(findListResult);
        JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
        return resultStr.toString();
    }

    public String saveSaafActInstanceBusinessInfo(JSONObject queryParamJSON) {
        SaafActInstanceBusinessEntity_HI saafActInstanceBusinessEntity_HI = JSON.parseObject(queryParamJSON.toString(), SaafActInstanceBusinessEntity_HI.class);
        Object resultData = saafActInstanceBusinessDAO_HI.save(saafActInstanceBusinessEntity_HI);
        JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
        return resultStr.toString();
    }

    public static void main(String[] args) {
        SaafActInstanceBusinessServer saafActInstanceBusinessServer = (SaafActInstanceBusinessServer)SaafToolUtils.context.getBean("saafActInstanceBusinessServer");
        JSONObject paramJSON = new JSONObject();
        //paramJSON.put("ordercode", 1);
        //paramJSON.put("ordercode", 1);
        //paramJSON.put("tid", 1);
        String resultStr = saafActInstanceBusinessServer.findSaafActInstanceBusinessInfo(paramJSON);
        LOGGER.info(resultStr);
    }
}
