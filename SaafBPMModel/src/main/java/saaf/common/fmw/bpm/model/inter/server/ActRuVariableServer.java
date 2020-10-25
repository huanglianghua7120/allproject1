package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActRuVariable;
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
import saaf.common.fmw.bpm.model.entities.ActRuVariableEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActRuVariableDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actRuVariableServer")
public class ActRuVariableServer implements IActRuVariable{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActRuVariableServer.class);
	@Autowired
	private ViewObject<ActRuVariableEntity_HI> actRuVariableDAO_HI;
	public ActRuVariableServer() {
		super();
	}

	public String findActRuVariableInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActRuVariableEntity_HI> findListResult = actRuVariableDAO_HI.findList("from ActRuVariableEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActRuVariableInfo(JSONObject queryParamJSON) {
		ActRuVariableEntity_HI actRuVariableEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActRuVariableEntity_HI.class);
		Object resultData = actRuVariableDAO_HI.save(actRuVariableEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActRuVariableServer actRuVariableServer = (ActRuVariableServer)SaafToolUtils.context.getBean("actRuVariableServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actRuVariableServer.findActRuVariableInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
