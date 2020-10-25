package saaf.common.fmw.intf.model.inter;

import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;

import java.util.Map;

public interface IIntfBaseExPlain {

	public String saveItem(Integer logId, String batchId, String handleStatus, Integer userId) throws Exception;

	/**
	 * 解析保存采购分类
	 * 
	 * @param logId
	 * @param batchId
	 * @param handleStatus
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String saveCategory(Integer logId, String batchId, String handleStatus, Integer userId) throws Exception;

	public String saveEmployee(Integer logId, String batchId, String handleStatus, Integer userId) throws Exception;
}
