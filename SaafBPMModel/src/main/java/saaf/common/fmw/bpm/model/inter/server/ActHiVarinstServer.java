package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActHiVarinst;
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
import saaf.common.fmw.bpm.model.entities.ActHiVarinstEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActHiVarinstDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actHiVarinstServer")
public class ActHiVarinstServer implements IActHiVarinst{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActHiVarinstServer.class);
	@Autowired
	private ViewObject<ActHiVarinstEntity_HI> actHiVarinstDAO_HI;
	public ActHiVarinstServer() {
		super();
	}

	public String findActHiVarinstInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActHiVarinstEntity_HI> findListResult = actHiVarinstDAO_HI.findList("from ActHiVarinstEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActHiVarinstInfo(JSONObject queryParamJSON) {
		ActHiVarinstEntity_HI actHiVarinstEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActHiVarinstEntity_HI.class);
		Object resultData = actHiVarinstDAO_HI.save(actHiVarinstEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActHiVarinstServer actHiVarinstServer = (ActHiVarinstServer)SaafToolUtils.context.getBean("actHiVarinstServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actHiVarinstServer.findActHiVarinstInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
