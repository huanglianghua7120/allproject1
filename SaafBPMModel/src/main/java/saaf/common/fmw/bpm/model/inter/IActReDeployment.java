package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActReDeployment{

	String findActReDeploymentInfo(JSONObject queryParamJSON);

	String saveActReDeploymentInfo(JSONObject queryParamJSON);

}
