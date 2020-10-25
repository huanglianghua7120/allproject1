package saaf.common.fmw.bpm.model.inter.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.ActHiActinstEntity_HI;
import saaf.common.fmw.bpm.model.entities.readonly.ActHiActinstEntity_HI_RO;
import saaf.common.fmw.bpm.model.inter.IActHiActinst;
import saaf.common.fmw.common.model.inter.server.SessionBeanServer;
import saaf.common.fmw.common.utils.SaafToolUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("actHiActinstServer")
public class ActHiActinstServer extends SessionBeanServer implements IActHiActinst{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActHiActinstServer.class);
	@Autowired
	private ViewObject<ActHiActinstEntity_HI> actHiActinstDAO_HI;
	@Autowired
	private DynamicViewObjectImpl<ActHiActinstEntity_HI_RO> actHiActinstDAO_HI_RO;
	
	public ActHiActinstServer() {
		super();
	}

	public String findActHiActinstInfo(JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		StringBuffer hql = new StringBuffer();
		hql.append("from ActHiActinstEntity_HI where 1=1 ");
		for (Iterator iterator = queryParamMap.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			hql.append(" and "+key+"=:"+key);
		}
	    hql.append(" order by id_ asc");
		
		List<ActHiActinstEntity_HI> findListResult = actHiActinstDAO_HI.findList(hql, queryParamMap);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), JSON.toJSON(findListResult));
		return resultStr.toString();
	}
	
	public String saveActHiActinstInfo(JSONObject queryParamJSON) {
		ActHiActinstEntity_HI actHiActinstEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActHiActinstEntity_HI.class);
		Object resultData = actHiActinstDAO_HI.save(actHiActinstEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
	
	
	public String findActHiActinst(JSONObject queryParamJSON){
		StringBuffer query = new StringBuffer();
		query.append(ActHiActinstEntity_HI_RO.QUERY_ACTHIACTINST);
		
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		SaafToolUtils.parperParam(queryParamJSON, "actHiActinst.proc_inst_id_", "procInstId_", query, queryParamMap, "=");
		query.append(" ORDER BY actHiActinst.START_TIME_ ASC");
		List<ActHiActinstEntity_HI_RO> findListResult = actHiActinstDAO_HI_RO.findList(query, queryParamMap);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", findListResult.size(), JSON.toJSON(findListResult));
		return resultStr.toString();
	}
}
