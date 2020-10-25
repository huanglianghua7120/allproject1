package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;

public interface IActRuEventSubscr{

	String findActRuEventSubscrInfo(JSONObject queryParamJSON);

	String saveActRuEventSubscrInfo(JSONObject queryParamJSON);

	String findActRuEventSubscrInfo(JSONObject queryParamJSON,
			Integer pageIndex, Integer pageRows);

}
