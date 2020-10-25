package saaf.common.fmw.bpm.model.inter.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.dao.readonly.SaafActHiProcinstDAO_HI_RO;
import saaf.common.fmw.bpm.model.entities.ActHiProcinstEntity_HI;
import saaf.common.fmw.bpm.model.entities.readonly.SaafActHiProcinstEntity_HI_RO;
import saaf.common.fmw.bpm.model.inter.IActHiProcinst;
import saaf.common.fmw.common.utils.SaafToolUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

@Component("actHiProcinstServer")
public class ActHiProcinstServer implements IActHiProcinst{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActHiProcinstServer.class);
	@Autowired
	private ViewObject<ActHiProcinstEntity_HI> actHiProcinstDAO_HI;
	@Autowired
	private SaafActHiProcinstDAO_HI_RO saafActHiProcinstDAO_HI_RO;
	
	public ActHiProcinstServer() {
		super();
	}

	public String findActHiProcinstInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		List<ActHiProcinstEntity_HI> findListResult = actHiProcinstDAO_HI.findList("from ActHiProcinstEntity_HI", queryParamMap);
		String resultData = JSON.toJSONString(findListResult);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), resultData);
		return resultStr.toString();
	}
	public String saveActHiProcinstInfo(JSONObject queryParamJSON) {
		ActHiProcinstEntity_HI actHiProcinstEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActHiProcinstEntity_HI.class);
		Object resultData = actHiProcinstDAO_HI.save(actHiProcinstEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	
	@Override
	public String findActHiProcinsts(Integer pageIndex, Integer pageRows,
			JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		Pagination<ActHiProcinstEntity_HI> findListResult = actHiProcinstDAO_HI.findPagination("from ActHiProcinstEntity_HI", queryParamMap, pageIndex, pageRows);
		return JSON.toJSONString(findListResult);
	}
	
	@Override
	public String findMyFlows(Integer pageIndex, Integer pageRows,
			JSONObject params) {
		StringBuffer sb = new StringBuffer();
		sb.append(SaafActHiProcinstEntity_HI_RO.QUERY_MY_PROC_SQL);
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		SaafToolUtils.parperParam(params, "ahpi.START_USER_ID_","start_user_id_", sb, paramsMap, "=");
		sb.append(" order by ahpi.start_time_ asc");
		Pagination<SaafActHiProcinstEntity_HI_RO> paginations = saafActHiProcinstDAO_HI_RO.findPagination(sb, paramsMap, pageIndex, pageRows);
		
		return JSON.toJSONString(paginations);
	}

	@Override
	public ActHiProcinstEntity_HI findActHiProcinstEntityById(
			String procInstId) {
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("procInstId_", procInstId);
		List<ActHiProcinstEntity_HI> findListResult = actHiProcinstDAO_HI.findByProperty(paramMap);
		if(findListResult!=null && !findListResult.isEmpty()){
			return findListResult.get(0);
		}
		return null;
	}

}
