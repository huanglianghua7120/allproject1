package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActReModel;
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
import saaf.common.fmw.bpm.model.entities.ActReModelEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActReModelDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actReModelServer")
public class ActReModelServer implements IActReModel{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActReModelServer.class);
	@Autowired
	private ViewObject<ActReModelEntity_HI> actReModelDAO_HI;
	public ActReModelServer() {
		super();
	}

	public String findActReModelInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActReModelEntity_HI> findListResult = actReModelDAO_HI.findList("from ActReModelEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActReModelInfo(JSONObject queryParamJSON) {
		ActReModelEntity_HI actReModelEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActReModelEntity_HI.class);
		Object resultData = actReModelDAO_HI.save(actReModelEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActReModelServer actReModelServer = (ActReModelServer)SaafToolUtils.context.getBean("actReModelServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actReModelServer.findActReModelInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
