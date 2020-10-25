package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActEvtLog{

	String findActEvtLogInfo(JSONObject queryParamJSON);

	String saveActEvtLogInfo(JSONObject queryParamJSON);

}
