package saaf.common.fmw.intf.model.inter;

import com.alibaba.fastjson.JSONObject;

public interface IIntfApprovedList {
	//货源表批量传U9
	public JSONObject pushApprovedLists( Integer userId) throws Exception;
	public JSONObject pushApprovedList(Integer listId, Integer userId) throws Exception;
	public JSONObject pushLoopApprovedList(Integer userId) throws Exception ;
}
