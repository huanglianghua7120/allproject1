package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActHiAttachment;
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
import saaf.common.fmw.bpm.model.entities.ActHiAttachmentEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActHiAttachmentDAO_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actHiAttachmentServer")
public class ActHiAttachmentServer implements IActHiAttachment{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActHiAttachmentServer.class);
	@Autowired
	private ViewObject<ActHiAttachmentEntity_HI> actHiAttachmentDAO_HI;
	public ActHiAttachmentServer() {
		super();
	}

	public String findActHiAttachmentInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActHiAttachmentEntity_HI> findListResult = actHiAttachmentDAO_HI.findList("from ActHiAttachmentEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActHiAttachmentInfo(JSONObject queryParamJSON) {
		ActHiAttachmentEntity_HI actHiAttachmentEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActHiAttachmentEntity_HI.class);
		Object resultData = actHiAttachmentDAO_HI.save(actHiAttachmentEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	public static void main(String[] args) {
		ActHiAttachmentServer actHiAttachmentServer = (ActHiAttachmentServer)SaafToolUtils.context.getBean("actHiAttachmentServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actHiAttachmentServer.findActHiAttachmentInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
