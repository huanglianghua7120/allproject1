package saaf.common.fmw.bpm.model.inter.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.dao.readonly.SaafActTaskHistoryDAO_HI_RO;
import saaf.common.fmw.bpm.model.entities.ActHiTaskinstEntity_HI;
import saaf.common.fmw.bpm.model.entities.readonly.SaafActTaskHistoryEntity_HI_RO;
import saaf.common.fmw.bpm.model.inter.IActHiTaskinst;
import saaf.common.fmw.bpm.utils.SaafToolUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

@Component("actHiTaskinstServer")
public class ActHiTaskinstServer implements IActHiTaskinst{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActHiTaskinstServer.class);
	@Autowired
	private ViewObject<ActHiTaskinstEntity_HI> actHiTaskinstDAO_HI;
	@Autowired
	private SaafActTaskHistoryDAO_HI_RO saafActTaskHistoryDAO_HI_RO;
	 
	public ActHiTaskinstServer() {
		super();
	}

	public String findActHiTaskinstInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActHiTaskinstEntity_HI> findListResult = actHiTaskinstDAO_HI.findList("from ActHiTaskinstEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActHiTaskinstInfo(JSONObject queryParamJSON) {
		ActHiTaskinstEntity_HI actHiTaskinstEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActHiTaskinstEntity_HI.class);
		Object resultData = actHiTaskinstDAO_HI.save(actHiTaskinstEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	
	public String findActHiTaskinsts(Integer pageIndex,Integer pageRows,JSONObject queryParamJSON){
		
		StringBuffer sb = new StringBuffer();
		sb.append(SaafActTaskHistoryEntity_HI_RO.QUERY_TASK_HISTORY_SQL);
		
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		Pagination<SaafActTaskHistoryEntity_HI_RO> paginations = saafActTaskHistoryDAO_HI_RO.findPagination(sb, paramsMap, pageIndex, pageRows);
		return JSON.toJSONString(paginations);
	}
	
	public static void main(String[] args) {
		ActHiTaskinstServer actHiTaskinstServer = (ActHiTaskinstServer)SaafToolUtils.context.getBean("actHiTaskinstServer");
		JSONObject paramJSON = new JSONObject();
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("ordercode", 1);
		//paramJSON.put("tid", 1);
		String resultStr = actHiTaskinstServer.findActHiTaskinstInfo(paramJSON);
		LOGGER.info(resultStr);
	}
}
