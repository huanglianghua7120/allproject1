package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActHiIdentitylink;
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
import saaf.common.fmw.bpm.model.entities.ActHiIdentitylinkEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActHiIdentitylinkDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actHiIdentitylinkServer")
public class ActHiIdentitylinkServer implements IActHiIdentitylink{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActHiIdentitylinkServer.class);
	@Autowired
	private ViewObject<ActHiIdentitylinkEntity_HI> actHiIdentitylinkDAO_HI;
	public ActHiIdentitylinkServer() {
		super();
	}

	public String findActHiIdentitylinkInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActHiIdentitylinkEntity_HI> findListResult = actHiIdentitylinkDAO_HI.findList("from ActHiIdentitylinkEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActHiIdentitylinkInfo(JSONObject queryParamJSON) {
		ActHiIdentitylinkEntity_HI actHiIdentitylinkEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActHiIdentitylinkEntity_HI.class);
		Object resultData = actHiIdentitylinkDAO_HI.save(actHiIdentitylinkEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActHiIdentitylinkServer actHiIdentitylinkServer = (ActHiIdentitylinkServer)SaafToolUtils.context.getBean("actHiIdentitylinkServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actHiIdentitylinkServer.findActHiIdentitylinkInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
