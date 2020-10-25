package saaf.common.fmw.report.model.inter;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface ISaafQueryDynamicReport {
	public String findList(String querySetNum, JSONObject parameters, Map<String,String> sessionBean, Integer pageIndex, Integer pageRows);
	
	public String findLov(JSONObject parameters, Map<String,String> sessionBean, Integer pageIndex, Integer pageRows);
	
	public String findService(String querySetNum, JSONObject parameters, Map<String,String> sessionBean, Integer pageIndex, Integer pageRows);
}
