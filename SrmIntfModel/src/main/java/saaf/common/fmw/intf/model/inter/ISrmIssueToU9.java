package saaf.common.fmw.intf.model.inter;



import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.intf.bean.U9ResultBean;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.intf.model.entities.readonly.SrmDeliveryBackEntity_HI_RO;

import java.util.List;
import java.util.Map;


public interface ISrmIssueToU9 {
	
	U9ResultBean saveLog(JSONObject jsonParam ) throws Exception;
	
	
	public boolean updateLog(U9ResultBean param) throws Exception;
	
	/**
	 * 保存五金需求计划数据入接口表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String savePlanData(U9ResultBean param) throws Exception;
	
	/**
	 * 处理接收数据
	 * 
	 * @throws Exception
	 */
	public void saveDeliery(U9ResultBean param) throws Exception;
	
	public List<SrmDeliveryBackEntity_HI_RO> updateAllInfo() throws Exception;
	
	public String updatePoDeliver(Integer logId, String batchId, Integer userId) throws Exception;
	public String updatePo(Integer logId, String batchId, Integer userId) throws Exception;
 }
