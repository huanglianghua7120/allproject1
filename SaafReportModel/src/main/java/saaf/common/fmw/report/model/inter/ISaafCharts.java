package saaf.common.fmw.report.model.inter;

import com.alibaba.fastjson.JSONObject;

public interface ISaafCharts {
	public String findList(JSONObject parameters, Integer pageIndex, Integer pageRows);
	public String findSqlList(JSONObject parameters, Integer pageIndex, Integer pageRows);
	public JSONObject save(JSONObject parameters) throws Exception;
	public JSONObject delete(JSONObject parameters) throws Exception;
}
