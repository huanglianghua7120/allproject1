package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActReModel{

	String findActReModelInfo(JSONObject queryParamJSON);

	String saveActReModelInfo(JSONObject queryParamJSON);

}
