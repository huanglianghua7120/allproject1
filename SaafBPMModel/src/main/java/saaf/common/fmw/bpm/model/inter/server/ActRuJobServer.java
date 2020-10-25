package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActRuJob;
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
import saaf.common.fmw.bpm.model.entities.ActRuJobEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActRuJobDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actRuJobServer")
public class ActRuJobServer implements IActRuJob{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActRuJobServer.class);
	@Autowired
	private ViewObject<ActRuJobEntity_HI> actRuJobDAO_HI;
	public ActRuJobServer() {
		super();
	}

	public String findActRuJobInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActRuJobEntity_HI> findListResult = actRuJobDAO_HI.findList("from ActRuJobEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActRuJobInfo(JSONObject queryParamJSON) {
		ActRuJobEntity_HI actRuJobEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActRuJobEntity_HI.class);
		Object resultData = actRuJobDAO_HI.save(actRuJobEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActRuJobServer actRuJobServer = (ActRuJobServer)SaafToolUtils.context.getBean("actRuJobServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actRuJobServer.findActRuJobInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
