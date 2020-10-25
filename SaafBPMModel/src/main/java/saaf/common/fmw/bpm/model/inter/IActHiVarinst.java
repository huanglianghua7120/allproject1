package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActHiVarinst{

	String findActHiVarinstInfo(JSONObject queryParamJSON);

	String saveActHiVarinstInfo(JSONObject queryParamJSON);

}
