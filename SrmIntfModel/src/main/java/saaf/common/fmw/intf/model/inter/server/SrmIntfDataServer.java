package saaf.common.fmw.intf.model.inter.server;

import java.util.Date;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.intf.bean.U9ResultBean;
import saaf.common.fmw.intf.model.entities.SrmIntfDatasEntity_HI;
import saaf.common.fmw.intf.model.entities.SrmIntfForecastsEntity_HI;
import saaf.common.fmw.intf.model.entities.SrmIntfLogsEntity_HI;
import saaf.common.fmw.intf.model.entities.SrmIntfPlanDemandEntity_HI;
import saaf.common.fmw.intf.model.entities.SrmIntfPoChangesEntity_HI;
import saaf.common.fmw.intf.model.entities.SrmIntfPoStarvingEntity_HI;
import saaf.common.fmw.intf.model.inter.ISrmIntfData;
import saaf.common.fmw.intf.model.inter.ISrmIntfLogs;
import saaf.common.fmw.intf.util.IntfUtils;
import saaf.common.fmw.intf.util.U9Client;
import saaf.common.fmw.intf.util.U9IntfUtils;
import saaf.common.fmw.po.model.entities.SrmPoForecastInfoEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosReasonsEntity_HI;
import saaf.common.fmw.utils.SrmUtils;

@Component("srmIntfDataServer")
public class SrmIntfDataServer implements ISrmIntfData {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmIntfDataServer.class);

	// private ViewObject srmIntfLogsDAO_HI;
	// @Autowired
	// private ViewObject srmIntfLogsDAO_HI;
	@Autowired
	private ViewObject<SrmIntfLogsEntity_HI> srmIntfLogsDAO_HI;
	@Autowired
	private ViewObject<SrmIntfDatasEntity_HI> srmIntfDatasDAO_HI;
	@Autowired
	private ViewObject<SrmIntfPlanDemandEntity_HI> srmIntfPlanDemandDAO_HI;
	@Autowired
	private ViewObject<SrmIntfPoChangesEntity_HI> srmIntfPoChangesDAO_HI;
	@Autowired
	private ViewObject<SrmIntfPoStarvingEntity_HI> srmIntfPoStarvingDAO_HI;
	@Autowired
	private ViewObject<SrmIntfForecastsEntity_HI> srmIntfForecastsDAO_HI;
	@Autowired
	private ViewObject<SrmPoForecastInfoEntity_HI> srmPoForecastInfoDAO_HI;

	
	/**
	 * 验证参数
	 * @param jsonStrParam
	 * @param transCode
	 * @return
	 */
	public String  validateParame(String jsonStrParam, String transCode){
		if (U9IntfUtils.FORECAST_TRANS_CODE.equals(transCode)) {
			// 判断是否有跑过预测信息
			String forecastCode = jsonStrParam;
			List<SrmPoForecastInfoEntity_HI> list = srmPoForecastInfoDAO_HI.findByProperty("FORECAST_CODE",
					forecastCode);
			if (list.size() > 0) {
				return  forecastCode + "已经存在预测信息";
			 
			}
		}
		
		return "S";
		
	}
	
	/**
	 * 调用U9接口写入临时表
	 */
	public U9ResultBean saveU9Data(String jsonStrParam, String transCode, String batchId, Integer userId)
			throws Exception {

		SrmIntfLogsEntity_HI row = new SrmIntfLogsEntity_HI();
		U9ResultBean resultBean = new U9ResultBean();
		String status = "W";
		try {
			row.setIntfStatus(status);
			row.setDataDirection("IN");
			row.setInData(jsonStrParam);
			row.setIntfType(transCode);
			row.setOperatorUserId(userId);
			srmIntfLogsDAO_HI.saveOrUpdate(row);

		

			Map<String, String> retInfo = U9Client.invokeService(jsonStrParam, transCode);

			status = retInfo.get("status");
			String returnMsg = retInfo.get("returnMsg");
			row.setOutData(returnMsg);
			String JsonStr = null;
			JSONObject json = null;
			JSONArray valuesArray = null;
			String retCode = null;

			if ("Y".equals(status)) {
				json = JSON.parseObject(returnMsg);
				retCode = json.getString("RetCode");
				if ("200".equals(retCode)) {
					status = "S";
				} else if ("300".equals(retCode)) {
					status = "F";
				} else {
					status = "E";
				}
			}
			resultBean.setBatchId(batchId);
			resultBean.setCurrPage(json.getInteger("CurrPage"));
			resultBean.setPageCount(json.getInteger("PageCount"));
			resultBean.setStatus(status);
			resultBean.setMsg(json.getString("RetMsg"));
			resultBean.setIntfType(transCode);
			resultBean.setLogId(row.getLogId());
			resultBean.setUserId(userId);

			row.setIntfStatus(status);
			srmIntfLogsDAO_HI.saveOrUpdate(row);

			if (!"S".equals(status)) {
				return resultBean;
			}

			// 采购价格
			if (U9IntfUtils.PRICE_TRANS_CODE.equals(transCode)) {
				resultBean.setData(returnMsg);
				return resultBean;
			}

			if ("S".equals(status)) {
				JsonStr = IntfUtils.replaceBackslash(json.getString("JsonStr"));
				try {
					valuesArray = JSONObject.parseArray(JsonStr);
				} catch (Exception e) {
					valuesArray = null;
				}

				if (valuesArray == null || valuesArray.size() < 1) {
					return resultBean;
				}
				// 采购分类 C0001
				if (U9IntfUtils.CATE_TRANS_CODE.equals(transCode)) {
					saveCategory(transCode, JsonStr, row.getLogId(), batchId, userId);
				}
				// 物料
				else if (U9IntfUtils.ITEM_TRANS_CODE.equals(transCode)) {
					saveItem(transCode, JsonStr, row.getLogId(), batchId, userId);
				}
				// 采购员
				else if (U9IntfUtils.EMP_TRANS_CODE.equals(transCode)) {
					saveEmployee(transCode, JsonStr, row.getLogId(), batchId, userId);
				}
				// 计划数据
				else if (U9IntfUtils.PLAN_TRANS_CODE.equals(transCode)) {
					savePlanData(transCode, JsonStr, row.getLogId(), batchId, userId);
				}
				// 采购订单变更
				else if (U9IntfUtils.POCHANGE_TRANS_CODE.equals(transCode)) {
					this.savePoChange(transCode, JsonStr, row.getLogId(), batchId, userId);
				}
				// 预测信息接口
				else if (U9IntfUtils.FORECAST_TRANS_CODE.equals(transCode)) {
					saveForecastData(transCode, JsonStr, row.getLogId(), batchId, userId);
				}

			}

			LOGGER.info("returnMsg:" + retInfo.get("returnMsg"));
			LOGGER.info("JsonStr:" + JsonStr);

		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			//e.printStackTrace();
			row.setIntfStatus("E");
			row.setErrorMsg(e.getMessage());
			try {
				srmIntfLogsDAO_HI.saveOrUpdate(row);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			resultBean.setStatus("E");
			resultBean.setMsg(e.getMessage());
		}
		LOGGER.info("jsonStrParam:" + jsonStrParam);
		return resultBean;

	}

	/**
	 * 日志表数据写入临时表
	 */
	public U9ResultBean saveU9DataByLog(Integer logId, String transCode, String batchId, Integer userId)
			throws Exception {
		
		
		
		SrmIntfLogsEntity_HI row = srmIntfLogsDAO_HI.findByProperty("log_Id", logId).get(0);
		U9ResultBean resultBean = new U9ResultBean();
		String status = "Y";
		try {

			String returnMsg = row.getOutData();
			String JsonStr = null;
			JSONObject json = null;
			JSONArray valuesArray = null;
			String retCode = null;

			if ("Y".equals(status)) {
				json = JSON.parseObject(returnMsg);
				retCode = json.getString("RetCode");
				if ("200".equals(retCode)) {
					status = "S";
				} else if ("300".equals(retCode)) {
					status = "F";
				} else {
					status = "E";
				}
			}
			resultBean.setBatchId(batchId);
			resultBean.setCurrPage(json.getInteger("CurrPage"));
			resultBean.setPageCount(json.getInteger("PageCount"));
			resultBean.setStatus(status);
			resultBean.setMsg(json.getString("RetMsg"));
			resultBean.setIntfType(transCode);
			resultBean.setLogId(row.getLogId());
			resultBean.setUserId(userId);

			row.setOperatorUserId(userId);
			row.setIntfStatus(status);
			srmIntfLogsDAO_HI.saveOrUpdate(row);

			if (!"S".equals(status)) {
				return resultBean;
			}

			// 采购价格
			if (U9IntfUtils.PRICE_TRANS_CODE.equals(transCode)) {
				resultBean.setData(returnMsg);
				return resultBean;
			}

			if ("S".equals(status)) {
				JsonStr = IntfUtils.replaceBackslash(json.getString("JsonStr"));
				System.out.println(JsonStr);
				try {
					valuesArray = JSONObject.parseArray(JsonStr);
				} catch (Exception e) {
					//e.printStackTrace();
					valuesArray = null;
				}

				if (valuesArray == null || valuesArray.size() < 1) {
					return resultBean;
				}
				// 采购分类 C0001
				if (U9IntfUtils.CATE_TRANS_CODE.equals(transCode)) {
					saveCategory(transCode, JsonStr, row.getLogId(), batchId, userId);
				}
				// 物料
				else if (U9IntfUtils.ITEM_TRANS_CODE.equals(transCode)) {
					saveItem(transCode, JsonStr, row.getLogId(), batchId, userId);
				}
				// 采购员
				else if (U9IntfUtils.EMP_TRANS_CODE.equals(transCode)) {
					saveEmployee(transCode, JsonStr, row.getLogId(), batchId, userId);
				}
				// 计划数据
				else if (U9IntfUtils.PLAN_TRANS_CODE.equals(transCode)) {
					savePlanData(transCode, JsonStr, row.getLogId(), batchId, userId);
				}
				// 采购订单变更
				else if (U9IntfUtils.POCHANGE_TRANS_CODE.equals(transCode)) {
					this.savePoChange(transCode, JsonStr, row.getLogId(), batchId, userId);
				}
				// 预测信息接口
				else if (U9IntfUtils.FORECAST_TRANS_CODE.equals(transCode)) {
					saveForecastData(transCode, JsonStr, row.getLogId(), batchId, userId);
				}
			}

			LOGGER.info("returnMsg:" + returnMsg);
			LOGGER.info("JsonStr:" + JsonStr);

		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			//e.printStackTrace();
			row.setIntfStatus("E");
			row.setErrorMsg(e.getMessage());
			try {
				srmIntfLogsDAO_HI.saveOrUpdate(row);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			resultBean.setStatus("E");
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;

	}

	/**
	 * 保存采购分类临时信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveCategory(String transCode, String jsonStr, Integer logId, String batchId, Integer userId)
			throws Exception {

		JSONArray valuesArray = JSONObject.parseArray(jsonStr);

		for (int i = 0; i < valuesArray.size(); i++) {
			JSONObject lineJson = valuesArray.getJSONObject(i);

			SrmIntfDatasEntity_HI row = new SrmIntfDatasEntity_HI();

			row.setIntfType(transCode);
			row.setBatchId(batchId);
			row.setOperatorUserId(userId);
			row.setSourceLogId(logId);
			row.setHandleStatus("N");
			row.setAttribute1(SrmUtils.getString(lineJson.getString("CATEGORY_CODE")));
			row.setAttribute2(SrmUtils.getString(lineJson.getString("CATEGORY_NAME")));
			row.setAttribute3(SrmUtils.getString(lineJson.getString("LAST_UPDATE_DATE")));
			row.setAttribute4(SrmUtils.getString(lineJson.getString("STATUS")));

			srmIntfDatasDAO_HI.save(row);
		}
		return "S";

	}

	/**
	 * 保存物料临时信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveItem(String transCode, String jsonStr, Integer logId, String batchId, Integer userId)
			throws Exception {

		JSONArray valuesArray = JSONObject.parseArray(jsonStr);

		for (int i = 0; i < valuesArray.size(); i++) {
			JSONObject lineJson = valuesArray.getJSONObject(i);

			SrmIntfDatasEntity_HI row = new SrmIntfDatasEntity_HI();

			row.setIntfType(transCode);
			row.setBatchId(batchId);
			row.setOperatorUserId(userId);
			row.setSourceLogId(logId);
			row.setHandleStatus("N");
			row.setAttribute1(SrmUtils.getString(lineJson.getString("ITEM_CODE")));
			row.setAttribute2(SrmUtils.getString(lineJson.getString("ITEM_DESCRIPTION")));
			row.setAttribute3(SrmUtils.getString(lineJson.getString("ITEM_STATUS")));
			row.setAttribute4(SrmUtils.getString(lineJson.getString("ITEM_STATUS_DESC")));
			row.setAttribute5(SrmUtils.getString(lineJson.getString("UNIT_OF_MEASURE")));
			row.setAttribute6(SrmUtils.getString(lineJson.getString("UNIT_OF_MEASURE_NAME")));
			row.setAttribute7(SrmUtils.getString(lineJson.getString("PURCHASING_FLAG")));
			row.setAttribute8(SrmUtils.getString(lineJson.getString("CUSTOMER_MODEL")));
			row.setAttribute9(SrmUtils.getString(lineJson.getString("CATEGORY_CODE")));
			row.setAttribute10(SrmUtils.getString(lineJson.getString("ITEM_PROP")));
			row.setAttribute11(SrmUtils.getString(lineJson.getString("TEM_PROP_DESC")));
			row.setAttribute12(SrmUtils.getString(lineJson.getString("EMPLOYEE_NUMBER")));
			row.setAttribute13(SrmUtils.getString(lineJson.getString("EMPLOYEE_NAME")));
			row.setAttribute14(SrmUtils.getString(lineJson.getString("PurForeCast")));
			row.setAttribute15(SrmUtils.getString(lineJson.getString("CUSTOMER_SUPPLY")));
			row.setAttribute16(SrmUtils.getString(lineJson.getString("CUSTOMER_DESIGN")));
			row.setAttribute17(SrmUtils.getString(lineJson.getString("PurBottle")));
			row.setAttribute18(SrmUtils.getString(lineJson.getString("RTNGoodsType")));
			row.setAttribute19(SrmUtils.getString(lineJson.getString("ItemCycle")));
			row.setAttribute20(SrmUtils.getString(lineJson.getString("PURCHASING_LEAD_TIME")));
			row.setAttribute21(SrmUtils.getString(lineJson.getString("ORDER_LEAD_TIME")));
			row.setAttribute22(SrmUtils.getString(lineJson.getString("CONFIRM_LEAD_TIME")));
			row.setAttribute23(SrmUtils.getString(lineJson.getString("LAST_UPDATE_DATE")));
			row.setAttribute24(SrmUtils.getString(lineJson.getString("Deliver_LEAD_TIME")));
			row.setAttribute25(SrmUtils.getString(lineJson.getString("rownum")));
			srmIntfDatasDAO_HI.save(row);
		}
		return "S";

	}

	/**
	 * 保存采购员临时信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveEmployee(String transCode, String jsonStr, Integer logId, String batchId, Integer userId)
			throws Exception {

		JSONArray valuesArray = JSONObject.parseArray(jsonStr);

		for (int i = 0; i < valuesArray.size(); i++) {
			JSONObject lineJson = valuesArray.getJSONObject(i);

			SrmIntfDatasEntity_HI row = new SrmIntfDatasEntity_HI();

			row.setIntfType(transCode);
			row.setBatchId(batchId);
			row.setOperatorUserId(userId);
			row.setSourceLogId(logId);
			row.setHandleStatus("N");
			row.setAttribute1(SrmUtils.getString(lineJson.getString("employee_number")));
			row.setAttribute2(SrmUtils.getString(lineJson.getString("employee_name")));
			row.setAttribute3(SrmUtils.getString(lineJson.getString("DEPT_NUMBER")));
			row.setAttribute4(SrmUtils.getString(lineJson.getString("DEPT_NAME")));
			row.setAttribute5(SrmUtils.getString(lineJson.getString("PO_FLAG")));
			row.setAttribute6(SrmUtils.getString(lineJson.getString("State")));
			srmIntfDatasDAO_HI.save(row);
		}
		return "S";

	}

	/**
	 * 保存计划数据入接口表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String savePlanData(String transCode, String jsonStr, Integer logId, String batchId, Integer userId)
			throws Exception {

		JSONArray valuesArray = JSONObject.parseArray(jsonStr);

		for (int i = 0; i < valuesArray.size(); i++) {
			JSONObject lineJson = valuesArray.getJSONObject(i);

			SrmIntfPlanDemandEntity_HI row = new SrmIntfPlanDemandEntity_HI();
			row.setHandleStatus("N");
			row.setPlanDate(lineJson.getDate("PLAN_DATE"));
			row.setPlanCode(SrmUtils.getString(lineJson.getString("PLAN_CODE")));
			row.setPlanType(SrmUtils.getString(lineJson.getString("PLAN_TYPE")));
			row.setInstCode(SrmUtils.getString(lineJson.getString("BRANCH_CODE")));
			row.setItemCode(SrmUtils.getString(lineJson.getString("ITEM_CODE")));
			row.setNeedQuantity(lineJson.getBigDecimal("NEED_QUANTITY"));
			row.setNeedByDate(lineJson.getDate("NEED_BY_DATE"));
			row.setAdviseOrderDate(lineJson.getDate("StartDate"));
			row.setDemandClassify(SrmUtils.getString(lineJson.getString("BATCH_NUM")));
			row.setSupplierNumber(SrmUtils.getString(lineJson.getString("SUPPLIER_NUMBER")));
			row.setSpecialUseNum(SrmUtils.getString(lineJson.getString("SPECIAL_USE_NUM")));
			row.setBatchId(batchId);
			row.setLogId(logId);
			row.setOperatorUserId(userId);
			srmIntfPlanDemandDAO_HI.save(row);
		}
		return "S";

	}

	// 保存供应商变更信息
	public String savePoChange(String transCode, String jsonStr, Integer logId, String batchId, Integer userId) {
		JSONArray valuesArray = JSONObject.parseArray(jsonStr);

		for (int i = 0; i < valuesArray.size(); i++) {
			JSONObject lineJson = valuesArray.getJSONObject(i);
			SrmIntfPoChangesEntity_HI row = new SrmIntfPoChangesEntity_HI();
			row.setHandleStatus("N");
			row.setPlanDate(lineJson.getDate("PLAN_DATE"));
			row.setChangeType(SrmUtils.getString(lineJson.getString("CHANGE_TYPE")));
			row.setPoNumber(SrmUtils.getString(lineJson.getString("PO_NUMBER")));
			row.setPoLineNum(lineJson.getInteger("PO_LINE_NUM"));
			row.setItemCode(SrmUtils.getString(lineJson.getString("ITEM_CODE")));
			row.setOrderQuantity(lineJson.getBigDecimal("ORDER_QUANTITY"));
			row.setNeedByDate(lineJson.getDate("NEED_BY_DATE"));
			row.setOriginDate(lineJson.getDate("OriginalDate"));
			row.setOriginQuantity(lineJson.getBigDecimal("OriginalQTY"));
			row.setSupplyType(SrmUtils.getString(lineJson.getString("SupplyType")));
			row.setBatchId(batchId);
			row.setLogId(logId);
			row.setOperatorUserId(userId);
			srmIntfPoChangesDAO_HI.save(row);
		}
		return "S";

	}

	// 保存工单缺料信息
	public String saveStarving(String transCode, String jsonStr, Integer logId, String batchId, Integer userId) {
		JSONArray valuesArray = JSONObject.parseArray(jsonStr);

		for (int i = 0; i < valuesArray.size(); i++) {
			JSONObject lineJson = valuesArray.getJSONObject(i);
			SrmIntfPoStarvingEntity_HI row = new SrmIntfPoStarvingEntity_HI();
			row.setHandleStatus("N");
			row.setShortCheckDate(lineJson.getDate("SHORT_CHECK_DATE"));
			row.setSpecialUseNum(SrmUtils.getString(lineJson.getString("SPECIAL_USE_NUM")));
			row.setInstCode(SrmUtils.getString(lineJson.getString("BRANCH_CODE")));
			row.setWipEntityNumber(SrmUtils.getString(lineJson.getString("WIP_ENTITY_NUMBER")));
			row.setSupplierNumber(SrmUtils.getString(lineJson.getString("SUPPLIER_NUMBER")));
			row.setNeedQuantity(lineJson.getBigDecimal("NEED_QUANTITY"));
			row.setNeedByDate(lineJson.getDate("NEED_BY_DATE"));
			row.setDemandClassify(SrmUtils.getString(lineJson.getString("BATCH_NUM")));
			row.setItemCode(SrmUtils.getString(lineJson.getString("ITEM_CODE")));

			row.setBatchId(batchId);
			row.setLogId(logId);
			row.setOperatorUserId(userId);
			srmIntfPoStarvingDAO_HI.save(row);
		}
		return "S";

	}

	// 保存预测信息到接口表
	public String saveForecastData(String transCode, String jsonStr, Integer logId, String batchId, Integer userId) {
		JSONArray valuesArray = JSONObject.parseArray(jsonStr);

		for (int i = 0; i < valuesArray.size(); i++) {
			JSONObject lineJson = valuesArray.getJSONObject(i);
			SrmIntfForecastsEntity_HI row = new SrmIntfForecastsEntity_HI();
			row.setHandleStatus("N");
			row.setForecastDate(lineJson.getDate("FORECAST_DATE"));
			row.setForecastName(SrmUtils.getString(lineJson.getString("FORECAST_NAME")));
			row.setForecastCode(SrmUtils.getString(lineJson.getString("FORECAST_CODE")));
			row.setForecastType(SrmUtils.getString(lineJson.getString("FORECAST_TYPE")));
			row.setNeedQuantity(lineJson.getBigDecimal("NEED_QUANTITY"));
			row.setNeedByDate(lineJson.getDate("NEED_BY_DATE"));
			row.setItemCode(SrmUtils.getString(lineJson.getString("ITEM_CODE")));
			row.setItemDescription(SrmUtils.getString(lineJson.getString("ITEM_DESCRIPTION")));
			row.setEmployeeNumber(SrmUtils.getString(lineJson.getString("EMPLOYEE_NUMBER")));

			row.setBatchId(batchId);
			row.setLogId(logId);
			row.setOperatorUserId(userId);
			srmIntfForecastsDAO_HI.save(row);
		}
		return "S";

	}

}
