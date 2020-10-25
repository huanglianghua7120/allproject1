package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActRuIdentitylink{

	String findActRuIdentitylinkInfo(JSONObject queryParamJSON);

	String saveActRuIdentitylinkInfo(JSONObject queryParamJSON);

}
