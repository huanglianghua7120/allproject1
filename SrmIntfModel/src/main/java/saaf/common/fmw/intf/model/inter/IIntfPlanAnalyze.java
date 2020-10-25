package saaf.common.fmw.intf.model.inter;

import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;

import java.util.Map;

public interface IIntfPlanAnalyze {

	/**
	 * 解析保存预测信息
	 * 
	 * @param logId
	 * @param batchId
	 * @param handleStatus
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String saveForecastData(Integer logId, String batchId, String handleStatus, Integer userId) throws Exception;

	/**
	 * 解析保存计划数据
	 * 
	 * @param logId
	 * @param batchId
	 * @param handleStatus
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String savePlanData(String planType, Integer logId, String batchId, String handleStatus, Integer userId)
			throws Exception;
	// 保存到历史表，清空当亲数据
	public void saveHis(String planType) throws Exception;
	// 指定供应商
	public void savePlanLineInSupp(String planType, Integer userId) throws Exception;

	// 指定分厂
	public void savePlanLineInInst(String planType, Integer userId) throws Exception;

	// 指定物料
	public void savePlanLineInItem(String planType, Integer userId) throws Exception;

	// 分类可追溯
	public void savePlanLineCateR(String planType, Integer userId) throws Exception;

	// 指定分类
	public void savePlanLineCate(String planType, Integer userId) throws Exception;

	// 无
	public void savePlanLineOther(String planType, Integer userId) throws Exception;
	//处理计划数据 五金接口调用
	public String saveAndHandPlanData(String planType, Integer logId, String batchId, String handleStatus, Integer userId) throws Exception ;
}
