package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActRuExecution{

	String findActRuExecutionInfo(JSONObject queryParamJSON,Integer pageIndex,Integer pageSize);

	String saveActRuExecutionInfo(JSONObject queryParamJSON);

}
