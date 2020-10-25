package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActHiComment;
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
import saaf.common.fmw.bpm.model.entities.ActHiCommentEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActHiCommentDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actHiCommentServer")
public class ActHiCommentServer implements IActHiComment{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActHiCommentServer.class);
	@Autowired
	private ViewObject<ActHiCommentEntity_HI> actHiCommentDAO_HI;
	public ActHiCommentServer() {
		super();
	}

	public String findActHiCommentInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActHiCommentEntity_HI> findListResult = actHiCommentDAO_HI.findList("from ActHiCommentEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActHiCommentInfo(JSONObject queryParamJSON) {
		ActHiCommentEntity_HI actHiCommentEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActHiCommentEntity_HI.class);
		Object resultData = actHiCommentDAO_HI.save(actHiCommentEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActHiCommentServer actHiCommentServer = (ActHiCommentServer)SaafToolUtils.context.getBean("actHiCommentServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actHiCommentServer.findActHiCommentInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
