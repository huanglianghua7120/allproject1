package saaf.common.fmw.bpm.model.inter.server;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.ActRuTaskEntity_HI;
import saaf.common.fmw.bpm.model.entities.readonly.SaafActRuTaskEntity_HI_RO;
import saaf.common.fmw.bpm.model.inter.IActRuTask;
import saaf.common.fmw.bpm.utils.SaafToolUtils;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import com.yhg.hibernate.core.dao.ViewObject;
/**
 * 获取当前用户的代码信息
 */
@Component("actRuTaskServer")
public class ActRuTaskServer implements IActRuTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActRuTaskServer.class);
    @Autowired
    private ViewObject<ActRuTaskEntity_HI> actRuTaskDAO_HI;
    @Autowired
    private DynamicViewObjectImpl<SaafActRuTaskEntity_HI_RO> saafActRuTaskDAO_HI_RO;

    public ActRuTaskServer() {
        super();
    }

    public String findActRuTaskInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<ActRuTaskEntity_HI> findListResult = actRuTaskDAO_HI.findList("from ActRuTaskEntity_HI where assignee_ = :assignee_", queryParamMap);
        JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), findListResult);
        return resultStr.toString();
    }

//    public String saveActRuTaskInfo(JSONObject queryParamJSON) {
//        ActRuTaskEntity_HI actRuTaskEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActRuTaskEntity_HI.class);
//        Object resultData = actRuTaskDAO_HI.save(actRuTaskEntity_HI);
//        JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
//        return resultStr.toString();
//    }

    public static void main(String[] args) {
        ActRuTaskServer actRuTaskServer = (ActRuTaskServer)SaafToolUtils.context.getBean("actRuTaskServer");
        JSONObject paramJSON = new JSONObject();
        //paramJSON.put("ordercode", 1);
        //paramJSON.put("ordercode", 1);
        //paramJSON.put("tid", 1);
        String resultStr = actRuTaskServer.findActRuTaskInfo(paramJSON);
        LOGGER.info(resultStr);
    }
}
