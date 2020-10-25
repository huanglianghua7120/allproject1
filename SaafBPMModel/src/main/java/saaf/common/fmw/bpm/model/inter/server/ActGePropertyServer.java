package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActGeProperty;
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
import saaf.common.fmw.bpm.model.entities.ActGePropertyEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActGePropertyDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actGePropertyServer")
public class ActGePropertyServer implements IActGeProperty{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActGePropertyServer.class);
	@Autowired
	private ViewObject<ActGePropertyEntity_HI> actGePropertyDAO_HI;
	public ActGePropertyServer() {
		super();
	}

	public String findActGePropertyInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActGePropertyEntity_HI> findListResult = actGePropertyDAO_HI.findList("from ActGePropertyEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActGePropertyInfo(JSONObject queryParamJSON) {
		ActGePropertyEntity_HI actGePropertyEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActGePropertyEntity_HI.class);
		Object resultData = actGePropertyDAO_HI.save(actGePropertyEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActGePropertyServer actGePropertyServer = (ActGePropertyServer)SaafToolUtils.context.getBean("actGePropertyServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actGePropertyServer.findActGePropertyInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
