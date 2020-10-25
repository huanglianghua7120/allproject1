package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActHiActinst{

	String findActHiActinstInfo(JSONObject queryParamJSON);

	String saveActHiActinstInfo(JSONObject queryParamJSON);
	
	String findActHiActinst(JSONObject queryParamJSON);

}
