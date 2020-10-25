package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActRuVariable{

	String findActRuVariableInfo(JSONObject queryParamJSON);

	String saveActRuVariableInfo(JSONObject queryParamJSON);

}
