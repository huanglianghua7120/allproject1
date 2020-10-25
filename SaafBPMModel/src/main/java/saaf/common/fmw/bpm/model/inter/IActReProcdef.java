package saaf.common.fmw.bpm.model.inter;

import saaf.common.fmw.bpm.model.entities.ActReProcdefEntity_HI;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

public interface IActReProcdef{
	String findActReProcdefInfo(JSONObject queryParamJSON, Integer pageIndex, Integer pageRows);

//	String saveActReProcdefInfo(JSONObject queryParamJSON);

	ActReProcdefEntity_HI getById(String Id);

	String findProcessDefinitions(JSONObject queryParamJSON, Integer pageIndex,
			Integer pageRows);
}
