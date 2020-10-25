package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActEvtLog;
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
import saaf.common.fmw.bpm.model.entities.ActEvtLogEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActEvtLogDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actEvtLogServer")
public class ActEvtLogServer implements IActEvtLog{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActEvtLogServer.class);
	@Autowired
	private ViewObject<ActEvtLogEntity_HI> actEvtLogDAO_HI;
	public ActEvtLogServer() {
		super();
	}

	public String findActEvtLogInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActEvtLogEntity_HI> findListResult = actEvtLogDAO_HI.findList("from ActEvtLogEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActEvtLogInfo(JSONObject queryParamJSON) {
		ActEvtLogEntity_HI actEvtLogEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActEvtLogEntity_HI.class);
		Object resultData = actEvtLogDAO_HI.save(actEvtLogEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActEvtLogServer actEvtLogServer = (ActEvtLogServer)SaafToolUtils.context.getBean("actEvtLogServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actEvtLogServer.findActEvtLogInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
