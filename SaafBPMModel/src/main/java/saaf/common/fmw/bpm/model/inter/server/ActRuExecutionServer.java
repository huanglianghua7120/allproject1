package saaf.common.fmw.bpm.model.inter.server;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.ActRuExecutionEntity_HI;
import saaf.common.fmw.bpm.model.entities.readonly.SaafActRuExecutionEntity_HI_RO;
import saaf.common.fmw.bpm.model.inter.IActRuExecution;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;

@Component("actRuExecutionServer")
public class ActRuExecutionServer implements IActRuExecution{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActRuExecutionServer.class);
	@Autowired
	private ViewObject<ActRuExecutionEntity_HI> actRuExecutionDAO_HI;
	@Autowired
	private DynamicViewObjectImpl<SaafActRuExecutionEntity_HI_RO> saafActRuExecutionDAO_HI_RO;
	
	public ActRuExecutionServer() {
		super();
	}

	public String findActRuExecutionInfo(JSONObject queryParamJSON,Integer pageIndex,Integer pageSize) {
		Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
		
		Pagination<ActRuExecutionEntity_HI> findPagination = actRuExecutionDAO_HI.findPagination(" from ActRuExecutionEntity_HI ", queryParamMap, pageIndex, pageSize);
		return JSON.toJSONString(findPagination);
	}
	
	public String saveActRuExecutionInfo(JSONObject queryParamJSON) {
		ActRuExecutionEntity_HI actRuExecutionEntity_HI = JSON.parseObject(queryParamJSON.toString(), ActRuExecutionEntity_HI.class);
		Object resultData = actRuExecutionDAO_HI.save(actRuExecutionEntity_HI);
		JSONObject resultStr = SToolUtils.convertResultJSONObj("S", "", 1, resultData);
		return resultStr.toString();
	}
}
