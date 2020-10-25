package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActIdMembership;
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
import saaf.common.fmw.bpm.model.entities.ActIdMembershipEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActIdMembershipDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actIdMembershipServer")
public class ActIdMembershipServer implements IActIdMembership{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActIdMembershipServer.class);
	@Autowired
	private ViewObject<ActIdMembershipEntity_HI> actIdMembershipDAO_HI;
	public ActIdMembershipServer() {
		super();
	}

	public String findActIdMembershipInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActIdMembershipEntity_HI> findListResult = actIdMembershipDAO_HI.findList("from ActIdMembershipEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActIdMembershipInfo(JSONObject queryParamJSON) {
		ActIdMembershipEntity_HI actIdMembershipEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActIdMembershipEntity_HI.class);
		Object resultData = actIdMembershipDAO_HI.save(actIdMembershipEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActIdMembershipServer actIdMembershipServer = (ActIdMembershipServer)SaafToolUtils.context.getBean("actIdMembershipServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actIdMembershipServer.findActIdMembershipInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
