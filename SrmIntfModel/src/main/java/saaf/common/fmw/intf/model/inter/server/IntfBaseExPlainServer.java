package saaf.common.fmw.intf.model.inter.server;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.intf.model.entities.SrmIntfDatasEntity_HI;
import saaf.common.fmw.intf.model.inter.IIntfBaseExPlain;
import saaf.common.fmw.intf.util.IntfUtils;
import saaf.common.fmw.intf.util.U9IntfUtils;
import saaf.common.fmw.base.model.entities.SrmBaseCategoriesEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseItemsEntity_HI;

@Component("intfBaseExPlainServer")
public class IntfBaseExPlainServer implements IIntfBaseExPlain {
	private static final Logger LOGGER = LoggerFactory.getLogger(IntfBaseExPlainServer.class);

	// private ViewObject srmIntfLogsDAO_HI;
	// @Autowired
	// private ViewObject srmIntfLogsDAO_HI;
	@Autowired
	private ViewObject<SrmIntfDatasEntity_HI> srmIntfDatasDAO_HI;
	// @Autowired
	// private ViewObject<SaafLookupValuesEntity_HI> saafLookupTypesDAO_HI;
	@Autowired
	private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;
	@Autowired
	private ViewObject<SrmBaseCategoriesEntity_HI> srmBaseCategoriesDAO_HI;
	@Autowired
	private ViewObject<SrmBaseItemsEntity_HI> srmBaseItemsDAO_HI;
	@Autowired
	private ViewObject<SaafEmployeesEntity_HI> saafEmployeesDAO_HI;

	public Map<String, Object> convertMap(Integer logId, String batchId, String handleStatus, String transCode) {
		LOGGER.info(logId + batchId + handleStatus + transCode);
		Map<String, Object> map = new HashMap<String, Object>();
		if (logId != null && !"".equals(logId)) {
			map.put("source_log_id", logId);
		}
		if (batchId != null && !"".equals(batchId)) {
			map.put("batch_id", batchId);
		}

		if (handleStatus != null && !"".equals(handleStatus)) {
			map.put("handle_status", handleStatus);
		} else {
			map.put("handle_status", "N");
		}

		map.put("intf_type", transCode);
		return map;

	}

	/**
	 * 保存物料信息
	 */
	public String saveItem(Integer logId, String batchId, String handleStatus, Integer userId) throws Exception {

		Map<String, Object> map = this.convertMap(logId, batchId, handleStatus, U9IntfUtils.ITEM_TRANS_CODE);
		 
		List<SrmIntfDatasEntity_HI> list = srmIntfDatasDAO_HI.findByProperty(map);
		SrmIntfDatasEntity_HI row = null;
		SrmBaseItemsEntity_HI itemRow = null;
		SaafEmployeesEntity_HI  empRow = null;
		String itemCode = null;
		for (int i = 0; i < list.size(); i++) {
			row = list.get(i);
			itemCode = row.getAttribute1();
			if (itemCode ==null||"".equals(itemCode.trim())){
				throw new Exception("物料编码不能为空");
			}
			
			try {
			List<SrmBaseItemsEntity_HI> itemList = srmBaseItemsDAO_HI.findByProperty("item_code", itemCode);
			if (itemList != null && itemList.size() > 0) {
				itemRow = itemList.get(0);
			} else {
				itemRow = new SrmBaseItemsEntity_HI();
			}
				itemRow.setItemCode(itemCode);
				itemRow.setItemDescription(row.getAttribute2());
				itemRow.setItemName(row.getAttribute2());
				itemRow.setItemStatus(row.getAttribute3());
				//itemRow.setUnitOfMeasure(row.getAttribute5());
				//itemRow.setUnitOfMeasureName(row.getAttribute6());
				itemRow.setPurchasingFlag(IntfUtils.getYesNo( row.getAttribute7()));
				//itemRow.setCustomerModel(row.getAttribute8());
				
				/*if (row.getAttribute9() == null || "".equals(row.getAttribute9().trim())) {
					itemRow.setCategoryCode("000000");
				} else {
					itemRow.setCategoryCode(row.getAttribute9());
				}*/
				//itemRow.setItemProp(row.getAttribute10());
				//itemRow.setItemPropDesc(row.getAttribute11());
				//itemRow.setAgentNumber(row.getAttribute12());
				
				if (row.getAttribute12()!=null){
					try{
					empRow =  saafEmployeesDAO_HI.findByProperty   ("employee_number",row.getAttribute12()).get(0);
					itemRow.setAgentId(empRow.getEmployeeId());
					}catch(Exception e){
					
					}
				}
				
				String deliverType = "1";
				if ("2".equals(row.getAttribute18())){
					deliverType="2";
				}
				
				//itemRow.setPlanFlag(IntfUtils.getYesNo( row.getAttribute14()));
				//itemRow.setCustomerSupply(row.getAttribute15());
				//itemRow.setCustomerDesign(row.getAttribute16());
				//itemRow.setDifficultItem(IntfUtils.getYesNo( row.getAttribute17()));
				//itemRow.setDeliveryType(deliverType);
				//itemRow.setPeriodType(row.getAttribute19());
				//itemRow.setPurchasingLeadTime(IntfUtils.getBigDecimalValue((row.getAttribute20())));
				//itemRow.setOrderLeadTime(IntfUtils.getBigDecimalValue(row.getAttribute21()));
				//itemRow.setConfirmLeadTime(IntfUtils.getBigDecimalValue(row.getAttribute22()));
				//itemRow.setDeliveryLeadTime(IntfUtils.getBigDecimalValue(row.getAttribute24()));
				itemRow.setOperatorUserId(userId);
				srmBaseItemsDAO_HI.saveOrUpdate(itemRow);
				row.setHandleStatus("S");
				row.setOperatorUserId(userId);
				row.setErrorMsg(null);
				srmIntfDatasDAO_HI.save(row);

			} catch (Exception e) {
				//e.printStackTrace();
				row.setHandleStatus("E");
				row.setOperatorUserId(userId);
				row.setErrorMsg(e.getMessage());
				srmIntfDatasDAO_HI.save(row);
			}
		}

		return "S"+list.size();

	}

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
	public String saveCategory(Integer logId, String batchId, String handleStatus, Integer userId) throws Exception {

		Map<String, Object> map = this.convertMap(logId, batchId, handleStatus, U9IntfUtils.CATE_TRANS_CODE);
		List<SrmIntfDatasEntity_HI> list = srmIntfDatasDAO_HI.findByProperty(map);
		if (list == null) {
			return "S";
		}
		SrmIntfDatasEntity_HI row = null;
		String categoryCode = null;
		String categoryName = null;
		String middleCategoryCode = null;
		String middleCategoryName = null;
		String bigCategoryCode = null;
		String bigCategoryName = null;
		String tag = null;
		String enabledFlag = "Y";

		//
		for (int i = 0; i < list.size(); i++) {
			row = list.get(i);
			try{
			categoryCode = row.getAttribute1();
			categoryName = row.getAttribute2();
			if (categoryCode ==null||"".equals(categoryCode.trim())){
				throw new Exception(" 编码不能为空");
			}
			if (categoryName ==null||"".equals(categoryName.trim())){
				throw new Exception("名称不能为空");
			}
			
				enabledFlag = IntfUtils.getYesNo(row.getAttribute4());

			 
				if (categoryCode.length() == 2) {
					saveLookup("BASE_BIG_CATEGORY", categoryCode, categoryName, null, enabledFlag, userId);
				} else if (categoryCode.length() == 4) {
					tag = categoryCode.substring(0, 2);
					saveLookup("BASE_MIDDLE_CATEGORY", categoryCode, categoryName, tag, enabledFlag, userId);
				} else if (categoryCode.length() == 6) {
					tag = categoryCode.substring(0, 4);
					saveLookup("BASE_SMALL_CATEGORY", categoryCode, categoryName, tag, enabledFlag, userId);
				}
 			

			row.setHandleStatus("S");
			row.setOperatorUserId(userId);
			row.setErrorMsg(null);
			srmIntfDatasDAO_HI.save(row);

		} catch (Exception e) {
			row.setHandleStatus("E");
			row.setOperatorUserId(userId);
			row.setErrorMsg(e.getMessage());
			srmIntfDatasDAO_HI.save(row);
		}

		}

		Map<String, Object> lookupMap = new HashMap<String, Object>();
		lookupMap.put("lookup_type", "BASE_SMALL_CATEGORY");

		List<SaafLookupValuesEntity_HI> lookupList = saafLookupValuesDAO_HI.findByProperty("lookup_type",
				"BASE_SMALL_CATEGORY");
		SaafLookupValuesEntity_HI lookupRow = null;
		SrmBaseCategoriesEntity_HI cateRow = null;
		for (int i = 0; i < lookupList.size(); i++) {
			lookupRow = lookupList.get(i);
			categoryCode = lookupRow.getLookupCode();
			categoryName = lookupRow.getMeaning();
			enabledFlag = lookupRow.getEnabledFlag();
			if (categoryCode.length() == 6) {
				List<SrmBaseCategoriesEntity_HI> cateList = srmBaseCategoriesDAO_HI.findByProperty("category_code",
						lookupRow.getLookupCode());
				if (cateList != null && cateList.size() > 0) {
					cateRow = cateList.get(0);
				} else {
					cateRow = new SrmBaseCategoriesEntity_HI();
				}

				bigCategoryCode = categoryCode.substring(0, 2);
				bigCategoryName = findLookupMeaning("BASE_BIG_CATEGORY", bigCategoryCode);
				middleCategoryCode = categoryCode.substring(0, 4);
				middleCategoryName = findLookupMeaning("BASE_MIDDLE_CATEGORY", middleCategoryCode);

				cateRow.setOperatorUserId(userId);
				cateRow.setCategoryCode(categoryCode);
				cateRow.setCategoryName(bigCategoryName + "." + middleCategoryName + "." + categoryName);
				cateRow.setEnabledFlag(enabledFlag);
				srmBaseCategoriesDAO_HI.saveOrUpdate(cateRow);
			}

		}

		return "S"+list.size();
	}

	/**
	 * 解析保存员工信息
	 * 
	 * @param logId
	 * @param batchId
	 * @param handleStatus
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String saveEmployee(Integer logId, String batchId, String handleStatus, Integer userId) throws Exception {

		Map<String, Object> map = this.convertMap(logId, batchId, handleStatus, U9IntfUtils.EMP_TRANS_CODE);
		List<SrmIntfDatasEntity_HI> list = srmIntfDatasDAO_HI.findByProperty(map);
		if (list == null) {
			return "S";
		}
		SrmIntfDatasEntity_HI row = null;
		String employeeNumber = null;
		String employeeName = null;
		String departName = null;
		String departNumber = null;
		String poFlag = null;
		String enabledFlag = null;
		SaafEmployeesEntity_HI empRow = null;
		//
		for (int i = 0; i < list.size(); i++) {
			row = list.get(i);
			try {
				employeeNumber = row.getAttribute1();
				employeeName = row.getAttribute2();
				departNumber = row.getAttribute3();
				departName = row.getAttribute4();
				if (employeeNumber ==null||"".equals(employeeNumber.trim())){
					throw new Exception("编码不能为空");
				}
				if (employeeName ==null||"".equals(employeeName.trim())){
					throw new Exception("名称不能为空");
				}
				
				
				poFlag = IntfUtils.getYesNo(row.getAttribute5());
				enabledFlag = IntfUtils.getYesNo(row.getAttribute6());
			 

				List<SaafEmployeesEntity_HI> empList = saafEmployeesDAO_HI.findByProperty("employee_number",
						employeeNumber);
				if (empList != null && empList.size() > 0) {
					empRow = empList.get(0);
				} else {
					empRow = new SaafEmployeesEntity_HI();
				}

				empRow.setEmployeeNumber(employeeNumber);
				empRow.setEmployeeName(employeeName + "");
				empRow.setPlatformCode("SAAF");
				empRow.setPositionCode(departNumber);
				empRow.setPositionName(departName);
				empRow.setPoFlag(poFlag);
				empRow.setEnabledFlag(enabledFlag);
				empRow.setOperatorUserId(userId);
				saafEmployeesDAO_HI.saveOrUpdate(empRow);
				row.setHandleStatus("S");
				row.setErrorMsg(null);
				row.setOperatorUserId(userId);
				srmIntfDatasDAO_HI.save(row);

			} catch (Exception e) {
				row.setHandleStatus("E");
				row.setOperatorUserId(userId);
				row.setErrorMsg(e.getMessage());
				srmIntfDatasDAO_HI.save(row);
			}
		}

		return "S"+list.size();
	}

	/**
	 * 获取代码含义
	 * 
	 * @param lookupType
	 * @param lookupCode
	 * @return
	 */
	public String findLookupMeaning(String lookupType, String lookupCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lookup_type", lookupType);
		map.put("lookup_code", lookupCode);
		SaafLookupValuesEntity_HI row = null;

		List<SaafLookupValuesEntity_HI> list = saafLookupValuesDAO_HI.findByProperty(map);
		if (list != null && list.size() > 0) {
			row = list.get(0);
			return row.getMeaning();
		}

		return "";
	}
	


	/**
	 * 保存代码
	 * 
	 * @param lookupType
	 * @param lookupCode
	 * @param meaning
	 * @param tag
	 * @param enabledFlag
	 * @param userId
	 */
	public void saveLookup(String lookupType, String lookupCode, String meaning, String tag, String enabledFlag,
			Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lookup_type", lookupType);
		map.put("lookup_code", lookupCode);
		SaafLookupValuesEntity_HI row = null;

		List<SaafLookupValuesEntity_HI> list = saafLookupValuesDAO_HI.findByProperty(map);
		if (list != null && list.size() > 0) {
			row = list.get(0);
		} else {
			row = new SaafLookupValuesEntity_HI();
		}
		row.setLookupType(lookupType);
		row.setLookupCode(lookupCode);
		row.setMeaning(meaning);
		row.setTag(tag);
		row.setStartDateActive(new Date());
		row.setEnabledFlag(enabledFlag);
		row.setOperatorUserId(userId);
		saafLookupValuesDAO_HI.saveOrUpdate(row);
	}
	
	 

}
