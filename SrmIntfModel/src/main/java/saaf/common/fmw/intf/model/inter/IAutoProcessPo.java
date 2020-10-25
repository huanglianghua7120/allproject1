package saaf.common.fmw.intf.model.inter;

import com.alibaba.fastjson.JSONObject;

public interface IAutoProcessPo {
	 //自动更新审批过期标识
	public String updateAutoPoOverdue( ) throws Exception;
	public String updateAutoClosePo( ) throws Exception;
	public String updateAutoCloseNotice( ) throws Exception;
	
	
 
}
