package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActRuTask{

	String findActRuTaskInfo(JSONObject queryParamJSON);

//	String saveActRuTaskInfo(JSONObject queryParamJSON);

}
