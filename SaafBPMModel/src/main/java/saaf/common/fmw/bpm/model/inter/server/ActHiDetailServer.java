package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActHiDetail;
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
import saaf.common.fmw.bpm.model.entities.ActHiDetailEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActHiDetailDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actHiDetailServer")
public class ActHiDetailServer implements IActHiDetail{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActHiDetailServer.class);
	@Autowired
	private ViewObject<ActHiDetailEntity_HI> actHiDetailDAO_HI;
	public ActHiDetailServer() {
		super();
	}

	public String findActHiDetailInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActHiDetailEntity_HI> findListResult = actHiDetailDAO_HI.findList("from ActHiDetailEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActHiDetailInfo(JSONObject queryParamJSON) {
		ActHiDetailEntity_HI actHiDetailEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActHiDetailEntity_HI.class);
		Object resultData = actHiDetailDAO_HI.save(actHiDetailEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActHiDetailServer actHiDetailServer = (ActHiDetailServer)SaafToolUtils.context.getBean("actHiDetailServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actHiDetailServer.findActHiDetailInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
