package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActRuJob{

	String findActRuJobInfo(JSONObject queryParamJSON);

	String saveActRuJobInfo(JSONObject queryParamJSON);

}
