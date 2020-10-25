package saaf.common.fmw.genform.model.inter.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import saaf.common.fmw.genform.model.dao.SaafDynamicTableInfoDAO;
import saaf.common.fmw.genform.model.inter.ISaafDynamicTableInfo;

@Component("saafDynamicTableInfoServer")
public class SaafDynamicTableInfoServer implements ISaafDynamicTableInfo {
	public SaafDynamicTableInfoServer() {
		super();
	}

	@Autowired
	private SaafDynamicTableInfoDAO saafDynamicTableInfoDAO;

	@Override
	public JSONObject findColumns(String table) {
		return saafDynamicTableInfoDAO.findColumns(table);
	}

	@Override
	public JSONObject findTableInfo(String table) {
		return saafDynamicTableInfoDAO.findTableInfo(table);
	}

	@Override
	public JSONArray findColumnNames(String table) {
		return saafDynamicTableInfoDAO.findColumnNames(table);
	}

}
