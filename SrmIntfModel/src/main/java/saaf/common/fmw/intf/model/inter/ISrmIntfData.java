package saaf.common.fmw.intf.model.inter;



import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.intf.bean.U9ResultBean;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;

import java.util.Map;


public interface ISrmIntfData {
	/**
	 * 验证参数
	 * @param jsonStrParam
	 * @param transCode
	 * @return
	 */
	public String  validateParame(String jsonStrParam, String transCode);
	public U9ResultBean saveU9Data(String jsonStr,String transCode,String batchId,Integer userId) throws Exception  ;
	public U9ResultBean saveU9DataByLog(Integer logId, String transCode,String batchId, Integer userId) throws Exception ;
 }
