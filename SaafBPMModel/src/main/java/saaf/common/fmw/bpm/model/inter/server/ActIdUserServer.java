package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActIdUser;
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
import saaf.common.fmw.bpm.model.entities.ActIdUserEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActIdUserDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actIdUserServer")
public class ActIdUserServer implements IActIdUser{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActIdUserServer.class);
	@Autowired
	private ViewObject<ActIdUserEntity_HI> actIdUserDAO_HI;
	public ActIdUserServer() {
		super();
	}

	public String findActIdUserInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActIdUserEntity_HI> findListResult = actIdUserDAO_HI.findList("from ActIdUserEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActIdUserInfo(JSONObject queryParamJSON) {
		ActIdUserEntity_HI actIdUserEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActIdUserEntity_HI.class);
		Object resultData = actIdUserDAO_HI.save(actIdUserEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActIdUserServer actIdUserServer = (ActIdUserServer)SaafToolUtils.context.getBean("actIdUserServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actIdUserServer.findActIdUserInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
