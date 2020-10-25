package saaf.common.fmw.bpm.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActHiTaskinst{

	String findActHiTaskinstInfo(JSONObject queryParamJSON);

	String saveActHiTaskinstInfo(JSONObject queryParamJSON);

	String findActHiTaskinsts(Integer pageIndex,Integer pageRows,JSONObject queryParamJSON);
}
