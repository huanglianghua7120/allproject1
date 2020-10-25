package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActIdGroup{

	String findActIdGroupInfo(JSONObject queryParamJSON);

	String saveActIdGroupInfo(JSONObject queryParamJSON);

}
