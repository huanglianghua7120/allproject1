package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActIdUser{

	String findActIdUserInfo(JSONObject queryParamJSON);

	String saveActIdUserInfo(JSONObject queryParamJSON);

}
