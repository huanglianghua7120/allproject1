package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActGeProperty{

	String findActGePropertyInfo(JSONObject queryParamJSON);

	String saveActGePropertyInfo(JSONObject queryParamJSON);

}
