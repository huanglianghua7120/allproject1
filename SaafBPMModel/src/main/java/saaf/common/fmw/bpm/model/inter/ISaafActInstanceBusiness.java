package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface ISaafActInstanceBusiness{

	String findSaafActInstanceBusinessInfo(JSONObject queryParamJSON);

	String saveSaafActInstanceBusinessInfo(JSONObject queryParamJSON);

}
