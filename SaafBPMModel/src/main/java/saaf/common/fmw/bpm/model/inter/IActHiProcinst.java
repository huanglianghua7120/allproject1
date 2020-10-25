package saaf.common.fmw.bpm.model.inter;

import saaf.common.fmw.bpm.model.entities.ActHiProcinstEntity_HI;

import com.alibaba.fastjson.JSONObject;

public interface IActHiProcinst{

	String findActHiProcinstInfo(JSONObject queryParamJSON);

	String saveActHiProcinstInfo(JSONObject queryParamJSON);

	String findActHiProcinsts(Integer pageIndex, Integer pageRows,
			JSONObject queryParamJSON);

	/**
	 * 查询我发起的流程
	 * @param pageIndex
	 * @param pageRows
	 * @param params
	 * @return
	 */
	String findMyFlows(Integer pageIndex, Integer pageRows, JSONObject params);
	
	/**
	 * 根据流程实例Id查询
	 * @param procInstId
	 * @return
	 */
	ActHiProcinstEntity_HI findActHiProcinstEntityById(String procInstId);

}
