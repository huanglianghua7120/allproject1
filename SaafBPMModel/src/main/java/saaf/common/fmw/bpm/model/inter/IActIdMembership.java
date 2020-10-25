package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActIdMembership{

	String findActIdMembershipInfo(JSONObject queryParamJSON);

	String saveActIdMembershipInfo(JSONObject queryParamJSON);

}
