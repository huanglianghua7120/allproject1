package saaf.common.fmw.bpm.model.inter.server;

import saaf.common.fmw.bpm.model.inter.IActGeBytearray;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yhg.base.utils.SToolUtils;

import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.utils.SaafToolUtils;
import saaf.common.fmw.bpm.model.entities.ActGeBytearrayEntity_HI;
import saaf.common.fmw.bpm.model.dao.ActGeBytearrayDAO_HI;

import com.yhg.hibernate.core.dao.ViewObject;

@Component("actGeBytearrayServer")
public class ActGeBytearrayServer implements IActGeBytearray{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActGeBytearrayServer.class);
	@Autowired
	private ViewObject<ActGeBytearrayEntity_HI> actGeBytearrayDAO_HI;
	public ActGeBytearrayServer() {
		super();
	}

	public String findActGeBytearrayInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActGeBytearrayEntity_HI> findListResult = actGeBytearrayDAO_HI.findList("from ActGeBytearrayEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActGeBytearrayInfo(JSONObject queryParamJSON) {
		ActGeBytearrayEntity_HI actGeBytearrayEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActGeBytearrayEntity_HI.class);
		Object resultData = actGeBytearrayDAO_HI.save(actGeBytearrayEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}

	@Override
	public ActGeBytearrayEntity_HI getActGeBytearrayEntity(String name,
			String deploymentId) {
		String hql = "from ActGeBytearrayEntity_HI where name_=:name and deploymentId_=:deploymentId";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", name);
		paramMap.put("deploymentId", deploymentId);
		List<ActGeBytearrayEntity_HI> list = actGeBytearrayDAO_HI.findList(hql, paramMap);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
}
