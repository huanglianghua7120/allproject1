package saaf.common.fmw.report.model.inter;

import com.alibaba.fastjson.JSONObject;

public interface ISaafDynamicQuerySet {
	public String findHeadersList(JSONObject parameters, Integer pageIndex, Integer pageRows);
	public String findLinesList(JSONObject parameters, Integer pageIndex, Integer pageRows);
	public JSONObject saveHeader(JSONObject parameters) throws Exception;
	public JSONObject deleteHeaderAndLines(JSONObject parameters) throws Exception;
	public JSONObject saveLine(JSONObject parameters) throws Exception;
	public JSONObject deleteLine(JSONObject parameters) throws Exception;	
	public String generateColumns(JSONObject parameters) throws Exception;
}
