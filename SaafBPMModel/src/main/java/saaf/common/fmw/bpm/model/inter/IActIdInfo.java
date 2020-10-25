package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActIdInfo{

	String findActIdInfoInfo(JSONObject queryParamJSON);

	String saveActIdInfoInfo(JSONObject queryParamJSON);

}
