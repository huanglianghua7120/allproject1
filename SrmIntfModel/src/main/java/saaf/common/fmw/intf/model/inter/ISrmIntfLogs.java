package saaf.common.fmw.intf.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.intf.model.entities.readonly.SrmIntfLogsEntity_HI_RO;
import java.util.List;

public interface ISrmIntfLogs {
	
	String saveData(String  logId,String intfType,String inData,String outData)  throws Exception;
	void saveOrUpdateDate(SrmIntfLogsEntity_HI row) throws Exception;
	Pagination<SrmIntfLogsEntity_HI_RO> findLogList(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
	List findLogData(JSONObject jsonParams) throws Exception;
}
