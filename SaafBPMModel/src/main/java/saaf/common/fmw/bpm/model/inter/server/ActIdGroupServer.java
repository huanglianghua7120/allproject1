package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActIdGroup;
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
import saaf.common.fmw.bpm.model.entities.ActIdGroupEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActIdGroupDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actIdGroupServer")
public class ActIdGroupServer implements IActIdGroup{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActIdGroupServer.class);
	@Autowired
	private ViewObject<ActIdGroupEntity_HI> actIdGroupDAO_HI;
	public ActIdGroupServer() {
		super();
	}

	public String findActIdGroupInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActIdGroupEntity_HI> findListResult = actIdGroupDAO_HI.findList("from ActIdGroupEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActIdGroupInfo(JSONObject queryParamJSON) {
		ActIdGroupEntity_HI actIdGroupEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActIdGroupEntity_HI.class);
		Object resultData = actIdGroupDAO_HI.save(actIdGroupEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActIdGroupServer actIdGroupServer = (ActIdGroupServer)SaafToolUtils.context.getBean("actIdGroupServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actIdGroupServer.findActIdGroupInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
