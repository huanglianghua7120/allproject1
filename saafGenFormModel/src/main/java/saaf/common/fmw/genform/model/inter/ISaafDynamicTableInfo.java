package saaf.common.fmw.genform.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface ISaafDynamicTableInfo {
	
	/**
	 * 查询表字段名称
	 * @param table
	 * @return 返回只包含名称的数组
	 */
	public JSONArray findColumnNames(String table);
	/**
	 * 查询表字段
	 * @param table 数据表
	 * @return
	 */
	public JSONObject findColumns(String table);

	/**
	 * 查询表信息
	 * @param table 数据表
	 * @return
	 */
	public JSONObject findTableInfo(String table);
}
