package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActHiIdentitylink{

	String findActHiIdentitylinkInfo(JSONObject queryParamJSON);

	String saveActHiIdentitylinkInfo(JSONObject queryParamJSON);

}
