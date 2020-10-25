package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActReDeployment;
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
import saaf.common.fmw.bpm.model.entities.ActReDeploymentEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActReDeploymentDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actReDeploymentServer")
public class ActReDeploymentServer implements IActReDeployment{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActReDeploymentServer.class);
	@Autowired
	private ViewObject<ActReDeploymentEntity_HI> actReDeploymentDAO_HI;
	public ActReDeploymentServer() {
		super();
	}

	public String findActReDeploymentInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActReDeploymentEntity_HI> findListResult = actReDeploymentDAO_HI.findList("from ActReDeploymentEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActReDeploymentInfo(JSONObject queryParamJSON) {
		ActReDeploymentEntity_HI actReDeploymentEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActReDeploymentEntity_HI.class);
		Object resultData = actReDeploymentDAO_HI.save(actReDeploymentEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActReDeploymentServer actReDeploymentServer = (ActReDeploymentServer)SaafToolUtils.context.getBean("actReDeploymentServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actReDeploymentServer.findActReDeploymentInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
