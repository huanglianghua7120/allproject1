package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActRuIdentitylink;
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
import saaf.common.fmw.bpm.model.entities.ActRuIdentitylinkEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActRuIdentitylinkDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actRuIdentitylinkServer")
public class ActRuIdentitylinkServer implements IActRuIdentitylink{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActRuIdentitylinkServer.class);
	@Autowired
	private ViewObject<ActRuIdentitylinkEntity_HI> actRuIdentitylinkDAO_HI;
	public ActRuIdentitylinkServer() {
		super();
	}

	public String findActRuIdentitylinkInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActRuIdentitylinkEntity_HI> findListResult = actRuIdentitylinkDAO_HI.findList("from ActRuIdentitylinkEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActRuIdentitylinkInfo(JSONObject queryParamJSON) {
		ActRuIdentitylinkEntity_HI actRuIdentitylinkEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActRuIdentitylinkEntity_HI.class);
		Object resultData = actRuIdentitylinkDAO_HI.save(actRuIdentitylinkEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActRuIdentitylinkServer actRuIdentitylinkServer = (ActRuIdentitylinkServer)SaafToolUtils.context.getBean("actRuIdentitylinkServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actRuIdentitylinkServer.findActRuIdentitylinkInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
