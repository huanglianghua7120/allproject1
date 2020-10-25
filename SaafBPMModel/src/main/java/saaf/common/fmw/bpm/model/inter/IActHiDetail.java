package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActHiDetail{

	String findActHiDetailInfo(JSONObject queryParamJSON);

	String saveActHiDetailInfo(JSONObject queryParamJSON);

}
