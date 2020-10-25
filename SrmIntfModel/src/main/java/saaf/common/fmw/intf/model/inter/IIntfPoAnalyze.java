package saaf.common.fmw.intf.model.inter;

import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;

import java.util.Map;

public interface IIntfPoAnalyze {

   /**
	 * 保存工单缺料
	 * @param logId
	 * @param batchId
	 * @param handleStatus
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String saveStarving (Integer logId, String batchId, String handleStatus, Integer userId) throws Exception;
	/**
	 * 保存采购订单变更
	 * @param logId
	 * @param batchId
	 * @param handleStatus
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String savePoChange (Integer logId, String batchId, String handleStatus, Integer userId) throws Exception;
	
	//保存历史信息
	public void saveHistory ( ) throws Exception;
  
}
