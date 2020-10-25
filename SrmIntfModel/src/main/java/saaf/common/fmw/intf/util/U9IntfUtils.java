package saaf.common.fmw.intf.util;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;

import saaf.common.fmw.intf.bean.U9ResultBean;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.intf.model.inter.IIntfApprovedList;
import saaf.common.fmw.intf.model.inter.IIntfBaseExPlain;
import saaf.common.fmw.intf.model.inter.IIntfDelivery;
import saaf.common.fmw.intf.model.inter.IIntfPlanAnalyze;
import saaf.common.fmw.intf.model.inter.IIntfPo;
import saaf.common.fmw.intf.model.inter.IIntfPoAnalyze;
import saaf.common.fmw.intf.model.inter.IIntfSupplier;
import saaf.common.fmw.intf.model.inter.ISrmIntfData;
import saaf.common.fmw.intf.model.inter.ISrmIntfLogs;
import saaf.common.fmw.intf.model.inter.ISrmShortMaterialInfo;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLinesEntity_HI_RO;
import saaf.common.fmw.utils.SrmUtils;

public class U9IntfUtils {

	public static final ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
			"classpath:saaf/common/fmw/config/spring.hibernate.cfg.xml");
	private static final Logger LOGGER = LoggerFactory.getLogger(U9IntfUtils.class);

	public static final Integer IMP_USER_ID = -1;// 导入用户id

	public static final String CATE_TRANS_CODE = "C0001";// 分类接口
	public static final String ITEM_TRANS_CODE = "C0002";// 物料接口
	public static final String PLAN_TRANS_CODE = "C0003";// 计划需求接口
	public static final String EMP_TRANS_CODE = "C0004";// 采购员
	public static final String PRICE_TRANS_CODE = "C0005";// 采购价格
	public static final String STARVING_TRANS_CODE = "C0006";// 工单缺料
	public static final String POCHANGE_TRANS_CODE = "C0007";// 采购例外 供应商变更建议
	public static final String FORECAST_TRANS_CODE = "C0008";// 计划预测

	public static final String SUPPLIER_TRANS_CODE = "D0003";// 供应商接口
	public static final String DELIVERY_TRANS_CODE = "D0004";// 采购收货接口
	public static final String APPROVED_LIST_TRANS_CODE = "D0005";// 采购货源表接口
	public static final String APPROVED_LISTS_TRANS_CODE = "D0006";// 批量导入货源表

	public static final String PO_ORDER_CODE = "D0001";// 采购订单新增接口
	public static final String PO_CLOSED_ORDER_CODE = "D0009";// 采购订单新增接口

	public static final String BANK_TRANS_CODE = "D0008";// 银行接口

	/**
	 * 从U9取物料
	 * 
	 * @param itemCode
	 * @param lastUpdateDateF
	 * @param lastUpdateDateE
	 * @return
	 */
	public static String handleItemIntf(String itemCode, String lastUpdateDateF, String lastUpdateDateE,
			String pageNum) {

		ISrmIntfData srmIntfDataServer = (ISrmIntfData) applicationContext.getBean("srmIntfDataServer");
		IIntfBaseExPlain intfBaseExPlainServer = (IIntfBaseExPlain) applicationContext.getBean("intfBaseExPlainServer");
		String batchId = UUID.randomUUID().toString();
		try {
			for (int i = 1; i < 120; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Code", itemCode);
				map.put("LAST_UPDATE_DATE_S", lastUpdateDateF);
				map.put("LAST_UPDATE_DATE_E", lastUpdateDateE);
				map.put("Count", 3000);
				map.put("PageNum", i);
				JSONObject json = new JSONObject(map);
				U9ResultBean resultBean = srmIntfDataServer.saveU9Data(json.toString(), U9IntfUtils.ITEM_TRANS_CODE,
						batchId, IMP_USER_ID);

				intfBaseExPlainServer.saveItem(resultBean.getLogId(), null, "N", IMP_USER_ID);

				if (resultBean.getPageCount() == null || i >= resultBean.getPageCount()) {
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "出现异常：" + e.getMessage();
		}
		return "S";
	}

	/**
	 * 从U9取采购分类
	 * 
	 * @param categoryCode
	 * @param categoryName
	 * @return
	 */
	public static String handleCateIntf(String categoryCode, String categoryName) {

		ISrmIntfData srmIntfDataServer = (ISrmIntfData) applicationContext.getBean("srmIntfDataServer");
		IIntfBaseExPlain intfBaseExPlainServer = (IIntfBaseExPlain) applicationContext.getBean("intfBaseExPlainServer");
		String batchId = UUID.randomUUID().toString();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Code", categoryCode);
		map.put("Name", categoryName);

		JSONObject json = new JSONObject(map);

		LOGGER.info("jsonStr:" + json.toString());
		try {
			U9ResultBean resultBean = srmIntfDataServer.saveU9Data(json.toString(), U9IntfUtils.CATE_TRANS_CODE,
					batchId, IMP_USER_ID);
			intfBaseExPlainServer.saveCategory(resultBean.getLogId(), null, "N", IMP_USER_ID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "出现异常：" + e.getMessage();
		}
		LOGGER.info("jsonStr:" + json.toString());
		return "S";
	}

	/**
	 * 从U9取采购员
	 * 
	 * @param employeeNumber
	 * @param employeeName
	 * @return
	 */
	public static String handleEmpIntf(String employeeNumber, String employeeName) {

		ISrmIntfData srmIntfDataServer = (ISrmIntfData) applicationContext.getBean("srmIntfDataServer");
		IIntfBaseExPlain intfBaseExPlainServer = (IIntfBaseExPlain) applicationContext.getBean("intfBaseExPlainServer");
		String batchId = UUID.randomUUID().toString();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("employee_number", employeeNumber);
		map.put("employee_name", employeeName);

		JSONObject json = new JSONObject(map);

		LOGGER.info("jsonStr:" + json.toString());
		try {
			U9ResultBean resultBean = srmIntfDataServer.saveU9Data(json.toString(), U9IntfUtils.EMP_TRANS_CODE, batchId,
					IMP_USER_ID);
			intfBaseExPlainServer.saveEmployee(resultBean.getLogId(), null, "N", IMP_USER_ID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "出现异常：" + e.getMessage();
		}
		LOGGER.info("jsonStr:" + json.toString());
		return "S";
	}

	/**
	 * 处理例外
	 * 
	 * @param planDate
	 * @param planType
	 * @param limit
	 * @return
	 */
	public static String handlePoChange(String planDate, String planType, String planName, String planCode,
			String limit, String pageCount) {
		ISrmIntfData srmIntfDataServer = (ISrmIntfData) applicationContext.getBean("srmIntfDataServer");
		IIntfPoAnalyze intfPoAnalyzeServer = (IIntfPoAnalyze) applicationContext.getBean("intfPoAnalyzeServer");

		if (SrmUtils.isNvl(planCode)) {
			return "planCode 不能为空！";
		}
		if (SrmUtils.isNvl(pageCount)) {
			return "pageCount 不能为空！";
		}

		String batchId = UUID.randomUUID().toString();
		Map<String, Object> map = new HashMap<String, Object>();

		JSONObject json = new JSONObject(map);

		try {
			// int i = 1;
			intfPoAnalyzeServer.saveHistory();
			for (int i = 1; i < 60; i++) {
				map.put("PLAN_DATE", planDate);
				map.put("PLAN_TYPE", planType);
				map.put("PLAN_NAME", planName);
				map.put("PLAN_CODE", planCode);
				map.put("Count", pageCount);
				map.put("Limit", limit);
				map.put("PageNum", i);
				U9ResultBean resultBean = srmIntfDataServer.saveU9Data(json.toString(), U9IntfUtils.POCHANGE_TRANS_CODE,
						batchId, IMP_USER_ID);

				intfPoAnalyzeServer.savePoChange(resultBean.getLogId(), batchId, "N", IMP_USER_ID);
				if (resultBean.getPageCount() == null || i >= resultBean.getPageCount()) {
					break;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "出现异常：" + e.getMessage();
		}
		LOGGER.info("jsonStr:" + json.toString());
		return "S";

	}

	/**
	 * 处理计划数据
	 * 
	 * @param planDate
	 * @param planType
	 * @param limit
	 * @return
	 */
	public static String handlePlanData(String planDate, String planType, String planName, String planCode,
			String pageCount, String limit) {
		ISrmIntfData srmIntfDataServer = (ISrmIntfData) applicationContext.getBean("srmIntfDataServer");
		IIntfPlanAnalyze intfPlanAnalyzeServer = (IIntfPlanAnalyze) applicationContext.getBean("intfPlanAnalyzeServer");
		String batchId = UUID.randomUUID().toString();
		Map<String, Object> map = new HashMap<String, Object>();

		JSONObject json = new JSONObject(map);

		if (SrmUtils.isNvl(planType)) {
			return "planType 不能为空！";
		}
		if (SrmUtils.isNvl(planCode)) {
			return "planCode 不能为空！";
		}
		if (SrmUtils.isNvl(pageCount)) {
			return "pageCount 不能为空！";
		}

		LOGGER.info("jsonStr:" + json.toString());
		try {
			for (int i = 1; i < 30; i++) {
				map.put("PLAN_DATE", planDate);
				map.put("PLAN_TYPE", planType);
				map.put("PLAN_NAME", planName);
				map.put("PLAN_CODE", planCode);
				map.put("Count", pageCount);
				map.put("PageNum", i);
				map.put("Limit", limit);
				U9ResultBean resultBean = srmIntfDataServer.saveU9Data(json.toString(), U9IntfUtils.PLAN_TRANS_CODE,
						batchId, IMP_USER_ID);
				if (resultBean.getPageCount() == null || i >= resultBean.getPageCount()) {
					break;
				}
			}
			// batchId="69132913-33e5-4106-bfb7-a03bf5b83fe8";
			intfPlanAnalyzeServer.saveHis(planType);
			String retStatu = intfPlanAnalyzeServer.savePlanData(planType, null, batchId, "N", IMP_USER_ID);
			if ("S".equals(retStatu)) {
				intfPlanAnalyzeServer.savePlanLineInSupp(planType, IMP_USER_ID);
				intfPlanAnalyzeServer.savePlanLineInInst(planType, IMP_USER_ID);
				intfPlanAnalyzeServer.savePlanLineInItem(planType, IMP_USER_ID);
				intfPlanAnalyzeServer.savePlanLineCateR(planType, IMP_USER_ID);
				intfPlanAnalyzeServer.savePlanLineCate(planType, IMP_USER_ID);
				intfPlanAnalyzeServer.savePlanLineOther(planType, IMP_USER_ID);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "出现异常：" + e.getMessage();
		}
		// LOGGER.info("jsonStr:" + json.toString());
		return "S";

	}

	// 处理计划预测数据
	public static String handleForecast(String forecastName, String forecastCode, String forecastType, String pageCount,
			Integer limit) {
		ISrmIntfData srmIntfDataServer = (ISrmIntfData) applicationContext.getBean("srmIntfDataServer");
		IIntfPlanAnalyze intfPlanAnalyzeServer = (IIntfPlanAnalyze) applicationContext.getBean("intfPlanAnalyzeServer");
		String batchId = UUID.randomUUID().toString();

		Map<String, Object> map = new HashMap<String, Object>();
		if (SrmUtils.isNvl(forecastCode)) {
			return "forecastCode 不能为空！";
		}
		if (SrmUtils.isNvl(pageCount)) {
			return "pageCount 不能为空！";
		}

		String validataInfo = srmIntfDataServer.validateParame(forecastCode, U9IntfUtils.FORECAST_TRANS_CODE);
		if (!validataInfo.equals("S")) {
			return validataInfo;
		}

		for (int i = 1; i < 30; i++) {
			map.put("FORECAST_NAME", forecastName);
			map.put("FORECAST_CODE", forecastCode);
			map.put("FORECAST_TYPE", forecastType);
			map.put("Count", pageCount);
			map.put("PageNum", i);
			map.put("Limit", limit);

			JSONObject json = new JSONObject(map);

			LOGGER.info("jsonStr:" + json.toString());
			try {
				U9ResultBean resultBean = srmIntfDataServer.saveU9Data(json.toString(), U9IntfUtils.FORECAST_TRANS_CODE,
						batchId, IMP_USER_ID);
				intfPlanAnalyzeServer.saveForecastData(resultBean.getLogId(), null, "N", IMP_USER_ID);
				if (resultBean.getPageCount() == null || i >= resultBean.getPageCount()) {
					break;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return "出现异常：" + e.getMessage();
			}

		}
		return "S";

	}

	/**
	 * 把SRM供应商推U9
	 * 
	 * @param supplierNumber
	 * @param userId
	 * @return
	 */
	public static String pushSupplier(String supplierNumber, Integer userId) {
		IIntfSupplier intfSupplierServer = (IIntfSupplier) applicationContext.getBean("intfSupplierServer");
		try {
			intfSupplierServer.pushSupplier(supplierNumber, userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return null;
	}

	// 推批准供应商列表
	public static String pushList(Integer id, Integer userId) {
		IIntfApprovedList intfApprovedListServer = (IIntfApprovedList) applicationContext
				.getBean("intfApprovedListServer");
		try {
			intfApprovedListServer.pushApprovedList(id, userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return null;
	}

	// 推批准供应商列表
	public static String pushLoopApprovedList(Integer userId) {
		IIntfApprovedList intfApprovedListServer = (IIntfApprovedList) applicationContext
				.getBean("intfApprovedListServer");
		try {
			intfApprovedListServer.pushLoopApprovedList(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return null;
	}

	// 推送货单
	public static String pushDelivery(Integer id, Integer userId) {
		IIntfDelivery intfDeliveryServer = (IIntfDelivery) applicationContext.getBean("intfDeliveryServer");
		try {
			return intfDeliveryServer.pushDelivery(id, userId).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把SRM采购订单推U9
	 * 
	 * @param id
	 * @param userId
	 * @return
	 */
	public static String pushPoOrder(String id, Integer userId) {
		IIntfPo intfPoServer = (IIntfPo) applicationContext.getBean("intfPoServer");
		try {
			JSONObject json = intfPoServer.pushPoOrder(id, userId);
			return JSON.toJSONString(json);
		} catch (Exception e) {
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "推送U9异常!" + e, 0, null));
		}
	}

	public static void pushBatchPoOrder() {
		IIntfPo intfPoServer = (IIntfPo) applicationContext.getBean("intfPoServer");
		List<SrmPoLinesEntity_HI_RO> poLineRow = null;
		try {
			poLineRow = intfPoServer.pushBatchPoOrder();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		try {
			if (null != poLineRow && poLineRow.size() > 0) {
				for (SrmPoLinesEntity_HI_RO line : poLineRow) {
					intfPoServer.pushPoOrder(String.valueOf(line.getPoHeaderId()), -1);
				}

			}
		} catch (Exception e) {
			//e.printStackTrace();
		}

	}

	public static String getShortItems() {
		ISrmShortMaterialInfo inter = (ISrmShortMaterialInfo) applicationContext.getBean("srmShortMaterialInfoServer");
		try {
			if (inter.saveU9Data())
				inter.saveShortMaterialInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return null;
	}

	/**
	 * 调用U9服务
	 * 
	 * @param jsonStr
	 * @param transCode
	 * @return
	 */
	public static String invokeService(String jsonStr, String transCode) {

		ISrmIntfLogs srmIntfLogsServer = (ISrmIntfLogs) applicationContext.getBean("srmIntfLogsServer");
		SrmIntfLogsEntity_HI row = new SrmIntfLogsEntity_HI();

		try {
			// String jsonStr
			// ="{\"Code\":\"010001\",\"org\":\"Homa\",\"ID\":\"1001209190000930\"}";
			// String transCode ="D0004";
			row.setIntfStatus("W");
			row.setDataDirection("IN");
			row.setInData(jsonStr);
			row.setIntfType(transCode);
			row.setOperatorUserId(-1);
			srmIntfLogsServer.saveOrUpdateDate(row);
			Map<String, String> retInfo = U9Client.invokeService(jsonStr, transCode);
			row.setIntfStatus(retInfo.get("status"));
			row.setOutData(retInfo.get("returnMsg"));
			srmIntfLogsServer.saveOrUpdateDate(row);
			return retInfo.get("returnMsg");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			// //e.printStackTrace();
			row.setIntfStatus("E");
			row.setOutData(e.getMessage());
			try {
				srmIntfLogsServer.saveOrUpdateDate(row);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return e.getMessage();
		}

	}

}
