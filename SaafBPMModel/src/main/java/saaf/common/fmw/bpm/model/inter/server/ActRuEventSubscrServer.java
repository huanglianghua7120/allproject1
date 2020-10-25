package saaf.common.fmw.bpm.model.inter.server;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.ActRuEventSubscrEntity_HI;
import saaf.common.fmw.bpm.model.inter.IActRuEventSubscr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actRuEventSubscrServer")
public class ActRuEventSubscrServer implements IActRuEventSubscr{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActRuEventSubscrServer.class);
	@Autowired
	private ViewObject<ActRuEventSubscrEntity_HI> actRuEventSubscrDAO_HI;
	public ActRuEventSubscrServer() {
		super();
	}

	public String findActRuEventSubscrInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActRuEventSubscrEntity_HI> findListResult = actRuEventSubscrDAO_HI.findList("from ActRuEventSubscrEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActRuEventSubscrInfo(JSONObject queryParamJSON) {
		ActRuEventSubscrEntity_HI actRuEventSubscrEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActRuEventSubscrEntity_HI.class);
		Object resultData = actRuEventSubscrDAO_HI.save(actRuEventSubscrEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}

	@Override
	public String findActRuEventSubscrInfo(JSONObject queryParamJSON,
			Integer pageIndex, Integer pageRows) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActRuEventSubscrEntity_HI> findListResult = actRuEventSubscrDAO_HI.findList("from ActRuEventSubscrEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		return resultData;
	}
	
}
