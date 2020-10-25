package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActIdInfo;
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
import saaf.common.fmw.bpm.model.entities.ActIdInfoEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActIdInfoDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actIdInfoServer")
public class ActIdInfoServer implements IActIdInfo{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActIdInfoServer.class);
	@Autowired
	private ViewObject<ActIdInfoEntity_HI> actIdInfoDAO_HI;
	public ActIdInfoServer() {
		super();
	}

	public String findActIdInfoInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActIdInfoEntity_HI> findListResult = actIdInfoDAO_HI.findList("from ActIdInfoEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActIdInfoInfo(JSONObject queryParamJSON) {
		ActIdInfoEntity_HI actIdInfoEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActIdInfoEntity_HI.class);
		Object resultData = actIdInfoDAO_HI.save(actIdInfoEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActIdInfoServer actIdInfoServer = (ActIdInfoServer)SaafToolUtils.context.getBean("actIdInfoServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actIdInfoServer.findActIdInfoInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
