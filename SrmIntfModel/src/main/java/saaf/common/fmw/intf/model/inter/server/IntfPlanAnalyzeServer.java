package saaf.common.fmw.intf.model.inter.server;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.exception.NotFindDataException;
import saaf.common.fmw.intf.model.entities.SrmIntfForecastsEntity_HI;
import saaf.common.fmw.intf.model.entities.SrmIntfPlanDemandEntity_HI;
import saaf.common.fmw.intf.model.entities.readonly.SrmPlanAnalyzeEntity_HI_RO;
import saaf.common.fmw.intf.model.inter.IIntfPlanAnalyze;
import saaf.common.fmw.intf.model.inter.IIntfUtils;
import saaf.common.fmw.intf.util.IntfUtils;
import saaf.common.fmw.base.model.entities.SrmBaseItemsEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoApprovedListEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoForecastDetailInfoEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoForecastInfoEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoPlanDemandHisEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoPlanDemandLineEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoPlanDemandLineHisEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoPlanDemandsEntity_HI;
import saaf.common.fmw.utils.SrmUtils;

@Component("intfPlanAnalyzeServer")
public class IntfPlanAnalyzeServer implements IIntfPlanAnalyze {
	private static final Logger LOGGER = LoggerFactory.getLogger(IntfPlanAnalyzeServer.class);

	// private ViewObject srmIntfLogsDAO_HI;
	@Autowired
	private IIntfUtils intfUtilsServer;

	// @Autowired
	// private ViewObject<SaafLookupValuesEntity_HI> saafLookupTypesDAO_HI;
	@Autowired
	private ViewObject<SrmIntfPlanDemandEntity_HI> srmIntfPlanDemandDAO_HI;
	@Autowired
	private ViewObject<SrmPoPlanDemandsEntity_HI> srmPoPlanDemandsDAO_HI;
	@Autowired
	private ViewObject<SrmPoPlanDemandLineEntity_HI> srmPoPlanDemandLineDAO_HI;
	@Autowired
	private BaseViewObject<SrmPlanAnalyzeEntity_HI_RO> srmPlanAnalyzeDAO_HI_RO;
	@Autowired
	private ViewObject<SrmPoForecastDetailInfoEntity_HI> srmPoForecastDetailInfoDAO_HI;
	@Autowired
	private ViewObject<SrmPoForecastInfoEntity_HI> srmPoForecastInfoDAO_HI;
	@Autowired
	private ViewObject<SrmIntfForecastsEntity_HI> srmIntfForecastsDAO_HI;
	@Autowired
	private ViewObject<SrmPoPlanDemandLineHisEntity_HI> srmPoPlanDemandLineHisDAO_HI;
	@Autowired
	private ViewObject<SrmPoPlanDemandHisEntity_HI> srmPoPlanDemandHisDAO_HI;

	public Map<String, Object> convertMap(Integer logId, String batchId, String handleStatus, String transCode) {
		LOGGER.info(logId + batchId + handleStatus + transCode);
		Map<String, Object> map = new HashMap<String, Object>();
		if (logId != null && !"".equals(logId)) {
			map.put("log_id", logId);
		}
		if (batchId != null && !"".equals(batchId)) {
			map.put("batch_id", batchId);
		}

		if (handleStatus != null && !"".equals(handleStatus)) {
			map.put("handle_status", handleStatus);
		} else {
			map.put("handle_status", "N");
		}
		if (transCode != null && !"".equals(transCode)) {
			map.put("intf_type", transCode);
		}
		return map;

	}

	public void systemPrintln(String msg) {
		System.out.println(msg + "--" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

	}

	// 保存预测信息
	public String saveForecastData(Integer logId, String batchId, String handleStatus, Integer userId)
			throws Exception {
		Map<String, Object> map = this.convertMap(logId, batchId, handleStatus, null);

		List<SrmIntfForecastsEntity_HI> list = srmIntfForecastsDAO_HI.findByProperty(map);
		SrmIntfForecastsEntity_HI row = null;
		SrmPoForecastInfoEntity_HI porRow = null;

		SrmBaseItemsEntity_HI itemRow = null;

		SrmPoForecastDetailInfoEntity_HI lineRow = null;

		if (list == null) {
			return null;
		}

		for (int i = 0; i < list.size(); i++) {
			row = list.get(i);
			try {
				porRow = new SrmPoForecastInfoEntity_HI();
				itemRow = intfUtilsServer.findItemByCode(row.getItemCode());
				if (itemRow == null) {
					throw new Exception("物料编码数据不正确！");
				}
				//porRow.setCategoryCode(itemRow.getCategoryCode());
				porRow.setForecastDate(row.getForecastDate());
				porRow.setForecastName(row.getForecastCode());
				//porRow.setForecastCode(row.getForecastCode());
				porRow.setPredictionType("AT_THE_END _OF"); // row.getForecastType());
				porRow.setEmployeeId(itemRow.getAgentId());
				porRow.setItemCode(row.getItemCode());
				porRow.setItemDescription(itemRow.getItemDescription());
				porRow.setForecastStatus("UNPUBLISHED");
				porRow.setNeedByDate(row.getNeedByDate());
				porRow.setNeedQuantity(row.getNeedQuantity());
				// porRow.setHandleStatus("N");
				// porRow.setSourceId(row.getIntfId());
				porRow.setOperatorUserId(userId);

				srmPoForecastInfoDAO_HI.saveOrUpdate(porRow);

				// 预测行
				/*List<SrmPlanAnalyzeEntity_HI_RO> headerList = this.findProportionH(null,null, null,
						itemRow.getCategoryCode());*/
				// 预测行
				List<SrmPlanAnalyzeEntity_HI_RO> headerList = null;
				System.out.println(headerList);
				if (headerList == null || headerList.size() < 1) {
					//headerList = this.findProportionH(null,null, null, itemRow.getCategoryCode());
				}
				String vendnameGroup = null;
				if (headerList.size() > 0) {
					List<SrmPlanAnalyzeEntity_HI_RO> lineList = this.findProportionL(headerList.get(0).getSupplyId());
					if (lineList != null) {
						for (int c = 0; c < lineList.size(); c++) {
							SrmPlanAnalyzeEntity_HI_RO proLRow = lineList.get(c);
							lineRow = new SrmPoForecastDetailInfoEntity_HI();
							lineRow.setForecastId(porRow.getForecastId());
							lineRow.setSupplierId(proLRow.getSupplierId());
							lineRow.setSupplierName(proLRow.getSupplierName());
							lineRow.setSupplierNmuber(proLRow.getSupplierNumber());
							lineRow.setOperatorUserId(userId);
							vendnameGroup = IntfUtils.jointStr(vendnameGroup, ",", proLRow.getSupplierName());
							srmPoForecastDetailInfoDAO_HI.saveOrUpdate(lineRow);
						}
					}
				}
				porRow.setVendnameGroup(vendnameGroup);
				// 是否可以发布预测
				if (vendnameGroup == null) {
					porRow.setAttribute5("N");
				} else {
					porRow.setAttribute6(headerList.get(0).getSupplyId() + "");
					porRow.setAttribute5("Y");
				}
				porRow.setOperatorUserId(userId);
				srmPoForecastInfoDAO_HI.saveOrUpdate(porRow);
				row.setHandleStatus("S");
				row.setOperatorUserId(userId);
				row.setHandleMsg(null);
				srmIntfForecastsDAO_HI.saveOrUpdate(row);

			} catch (Exception e) {
				//e.printStackTrace();
				row.setHandleStatus("E");
				row.setOperatorUserId(userId);
				row.setHandleMsg(e.getMessage());
				srmIntfForecastsDAO_HI.saveOrUpdate(row);
			}
		}
		return null;
	}

	// 保存到历史表，清空当亲数据
	public void saveHis(String planType) throws Exception {

		systemPrintln("Start saveHis");
		List<SrmPoPlanDemandsEntity_HI> list = srmPoPlanDemandsDAO_HI.findByProperty("plan_type", planType);

		for (int i = 0; i < list.size(); i++) {
			SrmPoPlanDemandsEntity_HI row = list.get(i);
			SrmPoPlanDemandHisEntity_HI row1 = new SrmPoPlanDemandHisEntity_HI();
			row1.setPlanDemandId(row.getPlanDemandId());
			row1.setSupplyInstId(row.getSupplyInstId());
			row1.setInstId(row.getInstId());
			row1.setItemId(row.getItemId());
			row1.setCategoryCode(row.getCategoryCode());
			row1.setPlanDate(row.getPlanDate());
			row1.setPlanType(row.getPlanType());
			row1.setNeedQuantity(row.getNeedQuantity());
			row1.setNeedByDate(row.getNeedByDate());
			row1.setEmployeeNumber(row.getEmployeeNumber());
			row1.setSupplierNumber(row.getSupplierNumber());
			row1.setSpecialUseNum(row.getSpecialUseNum());
			row1.setDemandClassify(row.getDemandClassify());
			row1.setSourceId(row.getSourceId());
			row1.setHandleMsg(row.getHandleMsg());
			row1.setHandleStatus(row.getHandleStatus());
			row1.setU9HeaderId(row.getU9HeaderId());
			row1.setPlanCode( row.getPlanCode());
			row1.setOperatorUserId(row.getLastUpdatedBy());
			srmPoPlanDemandHisDAO_HI.save(row1);
		}
		srmPoPlanDemandsDAO_HI.delete(list);
		srmPoPlanDemandsDAO_HI.fluch();

		List<SrmPoPlanDemandLineEntity_HI> lineList = srmPoPlanDemandLineDAO_HI.findByProperty("plan_type", planType);
		for (int i = 0; i < lineList.size(); i++) {
			SrmPoPlanDemandLineEntity_HI row = lineList.get(i);
			SrmPoPlanDemandLineHisEntity_HI row1 = new SrmPoPlanDemandLineHisEntity_HI();
			row1.setLineId(row.getLineId());
			row1.setPlanDemandId(row.getPlanDemandId());
			row1.setSupplierId(row.getSupplierId());
			row1.setDistributeQuantity(row.getDistributeQuantity());
			row1.setProportion(row.getProportion());
			row1.setDayCapacity(row.getDayCapacity());
			row1.setOperatorUserId(row.getLastUpdatedBy());
			srmPoPlanDemandLineHisDAO_HI.save(row1);
		}
		srmPoPlanDemandLineDAO_HI.delete(lineList);
		srmPoPlanDemandsDAO_HI.fluch();
		systemPrintln("End saveHis");
	}

	/**
	 * 保存解析需求计划信息
	 */
	public String savePlanData(String planType, Integer logId, String batchId, String handleStatus, Integer userId)
			throws Exception {

		systemPrintln("Start savePlanData");

		String retState = "F";
		StringBuffer sql = new StringBuffer();
		sql.append(SrmPlanAnalyzeEntity_HI_RO.QUERY_INTF_PLAN_DEMAND);
		sql.append(" and  t.plan_type  = '" + planType + "'");
		sql.append(" and t.HANDLE_STATUS='N' ");
		if (logId != null) {
			sql.append(" and t.LOG_ID = " + logId);
		}
		if (batchId != null) {
			sql.append(" and t.batch_id = '" + batchId + "'");
		}

		List<SrmPlanAnalyzeEntity_HI_RO> list = srmPlanAnalyzeDAO_HI_RO.findList(sql);
		SrmPlanAnalyzeEntity_HI_RO row = null;

		SrmIntfPlanDemandEntity_HI intfRow = null;

		SrmPoPlanDemandsEntity_HI demandRow = null;
		if (list.size() < 1) {
			return "未有需要处理数据!";
		}		
		for (int i = 0; i < list.size(); i++) {
			row = list.get(i);

			intfRow = srmIntfPlanDemandDAO_HI.getById(row.getIntfId());
			try {
				demandRow = new SrmPoPlanDemandsEntity_HI();
				if (row.getInstId() == null) {
					throw new NotFindDataException("分厂数据不正确！");
				}
				// 委外
				if ("2".equals(row.getPlanType())) {
					demandRow.setSupplyInstId(row.getInstId());
				} else {
					demandRow.setSupplyInstId(row.getParentInstId());
				}

				demandRow.setInstId(row.getInstId());
				if (row.getItemId() == null) {
					throw new NotFindDataException("物料编码数据不正确！");
				}
				demandRow.setPlanDemandNum(i + 1);
				demandRow.setCategoryCode(row.getCategoryCode());
				demandRow.setItemId(row.getItemId());

				demandRow.setEmployeeNumber(row.getAgentNumber());
				demandRow.setPlanDate(row.getPlanDate());
				demandRow.setPlanType(row.getPlanType());
				demandRow.setPlanCode(row.getPlanCode());

				if (row.getNeedByDate() != null && row.getNeedByDate().after(new Date())) {
					demandRow.setNeedByDate(row.getNeedByDate());
				} else {
					demandRow.setNeedByDate(SrmUtils.getCurrentDate());
				}

				demandRow.setNeedQuantity(row.getNeedQuantity());
				demandRow.setAdviseOrderDate(row.getAdviseOrderDate());
				demandRow.setSupplierNumber(row.getSupplierNumber());
				demandRow.setSpecialUseNum(row.getSpecialUseNum());
				demandRow.setDemandClassify(row.getDemandClassify());
				demandRow.setU9HeaderId(row.getU9HeaderId());
				demandRow.setHandleStatus("N");
				demandRow.setSourceId(row.getIntfId());
				demandRow.setOperatorUserId(userId);
				srmPoPlanDemandsDAO_HI.save(demandRow);

				intfRow.setHandleStatus("S");
				intfRow.setOperatorUserId(userId);
				intfRow.setHandleMsg(null);
				srmIntfPlanDemandDAO_HI.update(intfRow);
				retState = "S";
			} catch (Exception e) {
				//e.printStackTrace();
				intfRow.setHandleStatus("E");
				intfRow.setOperatorUserId(userId);
				intfRow.setHandleMsg(e.getMessage());
				srmIntfPlanDemandDAO_HI.update(intfRow);
			}
		}
		srmIntfPlanDemandDAO_HI.fluch();

		systemPrintln("End savePlanData");
		// this.savePlanLineData(userId);
		return retState;

	}

	// 指定供应商
	public void savePlanLineInSupp(String planType, Integer userId) throws Exception {
		systemPrintln("Start savePlanLineInSupp");
		SrmPlanAnalyzeEntity_HI_RO row = null;
		SrmPoPlanDemandLineEntity_HI lineRow = null;
		SrmPoPlanDemandsEntity_HI demandRow = null;
		// SrmPosSupplierInfoEntity_HI supplierRow = null;
		// SrmPoApprovedListEntity_HI appListRow = null;

		List<SrmPlanAnalyzeEntity_HI_RO> list = findPlanDate("SUPPLIER", planType, null, null, null, null);
		LOGGER.info("findPlanDate指定供应商" + JSON.toJSON(list));
		for (int i = 0; i < list.size(); i++) {
			row = list.get(i);
			// demandRow =
			// srmPoPlanDemandsDAO_HI.findByProperty("plan_demand_id",
			// row.getPlanDemandId()).get(0);
			demandRow = srmPoPlanDemandsDAO_HI.getById(row.getPlanDemandId());
			// supplierRow =
			// intfUtilsServer.findSupplierByCode(demandRow.getSupplierNumber());

			lineRow = new SrmPoPlanDemandLineEntity_HI();
			lineRow.setPlanDemandId(row.getPlanDemandId());
			lineRow.setDistributeQuantity(row.getNeedQuantity());
			lineRow.setSupplierId(row.getSupplierId());
			// if (supplierRow == null) {
			// lineRow.setSupplierId(null);
			// lineRow.setIsApprovedList(null);
			// lineRow.setDayCapacity(null);
			// } else {
			// appListRow =
			// intfUtilsServer.findApprovedList(demandRow.getItemId(),
			// supplierRow.getSupplierId());
			// lineRow.setSupplierId(supplierRow.getSupplierId());
			// if (appListRow != null) {
			// lineRow.setIsApprovedList(appListRow.getEnabledFlag());
			// lineRow.setDayCapacity(appListRow.getDayCapacity());
			// } else {
			// lineRow.setIsApprovedList("N");
			// }
			//
			// }
			lineRow.setPlanType(planType);
			lineRow.setMonthProportion(null);
			lineRow.setProportion(null);
			lineRow.setOperatorUserId(userId);
			lineRow.setAttribute1("指定供应商");
			srmPoPlanDemandLineDAO_HI.save(lineRow);
			demandRow.setHandleStatus("S");
			demandRow.setOperatorUserId(userId);
			srmPoPlanDemandsDAO_HI.update(demandRow);

		}
		srmPoPlanDemandLineDAO_HI.fluch();

		systemPrintln("End savePlanLineInSupp");
	}

	// 指定分厂
	public void savePlanLineInInst(String planType,Integer userId) throws Exception {

		systemPrintln("Start savePlanLineInInst");

		List<SrmPlanAnalyzeEntity_HI_RO> list = null;
		SrmPlanAnalyzeEntity_HI_RO row = null;
		SrmPoPlanDemandLineEntity_HI lineRow = null;
		SrmPoPlanDemandsEntity_HI demandRow = null;
		// SrmPoApprovedListEntity_HI appListRow = null;
		// 供货比例
		List<SrmPlanAnalyzeEntity_HI_RO> proportionHlist = null;
		SrmPlanAnalyzeEntity_HI_RO proportionHRow = null;
		List<SrmPlanAnalyzeEntity_HI_RO> proportionLlist = null;

		proportionHlist = findProportionH(  planType,"INST", null, null);
		LOGGER.info("findProportionH指定分厂" + JSON.toJSON(proportionHlist));
		for (int a = 0; a < proportionHlist.size(); a++) {
			proportionHRow = proportionHlist.get(a);
			proportionLlist = findProportionL(proportionHRow.getSupplyId());
			if (proportionLlist.size() > 0) {
				Integer supplierId = proportionLlist.get(0).getSupplierId();

				list = findPlanDate(  planType,null, proportionHRow.getInstId(), proportionHRow.getItemId(), null, null);

				LOGGER.info("findPlanDate指定分厂" + JSON.toJSON(list));
				for (int i = 0; i < list.size(); i++) {
					row = list.get(i);
					demandRow = srmPoPlanDemandsDAO_HI.getById(row.getPlanDemandId());
					if ("S".equals(demandRow.getHandleStatus())) {
						continue;
					}

					lineRow = new SrmPoPlanDemandLineEntity_HI();
					lineRow.setPlanDemandId(row.getPlanDemandId());
					lineRow.setSupplierId(supplierId);
					lineRow.setDistributeQuantity(row.getNeedQuantity());
					lineRow.setProportion(new BigDecimal(100));
					// appListRow =
					// intfUtilsServer.findApprovedList(demandRow.getItemId(),
					// supplierId);
					// if (appListRow != null) {
					// lineRow.setIsApprovedList(appListRow.getEnabledFlag());
					// lineRow.setDayCapacity(appListRow.getDayCapacity());
					// } else {
					// lineRow.setIsApprovedList("N");
					// }
					lineRow.setPlanType(planType);
					lineRow.setAttribute1("指定分厂");
					lineRow.setOperatorUserId(userId);
					srmPoPlanDemandLineDAO_HI.save(lineRow);
					demandRow.setHandleStatus("S");
					demandRow.setOperatorUserId(userId);
					srmPoPlanDemandsDAO_HI.update(demandRow);

				}

			}
		}
		srmPoPlanDemandLineDAO_HI.fluch();

		systemPrintln("End savePlanLineInInst");

	}

	// 相同需求分类
	public Integer saveSameDemandClassify(String planType,List<SrmPlanAnalyzeEntity_HI_RO> list,
			SrmPlanAnalyzeEntity_HI_RO proportionLrow, Integer t, String DemandClassify, Integer itemId,
			Integer userId) {

		Integer count = 0;
		SrmPlanAnalyzeEntity_HI_RO row = null;
		SrmPoPlanDemandLineEntity_HI lineRow = null;
		SrmPoPlanDemandsEntity_HI demandRow = null;
		// SrmPoApprovedListEntity_HI appListRow = null;
		for (; t < list.size(); t++) {
			row = list.get(t);

			if (SrmUtils.compareValueSame(row.getDemandClassify(), DemandClassify)
					&& SrmUtils.compareValueSame(row.getItemId(), itemId)) {

				demandRow = srmPoPlanDemandsDAO_HI.getById(row.getPlanDemandId());
				if ("S".equals(demandRow.getHandleStatus()) || "S".equals(row.getHandleStatus())) {
					continue;
				}

				proportionLrow.setCanDisQty(proportionLrow.getCanDisQty().subtract(demandRow.getNeedQuantity()));
				proportionLrow.setDisQty(demandRow.getNeedQuantity());

				lineRow = new SrmPoPlanDemandLineEntity_HI();
				lineRow.setPlanDemandId(row.getPlanDemandId());
				lineRow.setSupplierId(proportionLrow.getSupplierId());
				lineRow.setDistributeQuantity(proportionLrow.getDisQty());
				lineRow.setProportion(proportionLrow.getProportion());
				lineRow.setMonthProportion(proportionLrow.getMonthProportion());

				// appListRow =
				// intfUtilsServer.findApprovedList(demandRow.getItemId(),
				// proportionLrow.getSupplierId());
				// if (appListRow != null) {
				// lineRow.setIsApprovedList(appListRow.getEnabledFlag());
				// lineRow.setDayCapacity(appListRow.getDayCapacity());
				// } else {
				// lineRow.setIsApprovedList("N");
				// }

				lineRow.setPlanType(planType);
				lineRow.setOperatorUserId(userId);
				lineRow.setAttribute1("相同需求分类");
				lineRow.setAttribute2(proportionLrow.getSupplyDetailId().toString());
				lineRow.setAttribute3(proportionLrow.getCanDisQty() + "");
				lineRow.setAttribute4(proportionLrow.getSumMonQuantity() + "");
				lineRow.setAttribute5(proportionLrow.getSumPlanQty() + "");
				srmPoPlanDemandLineDAO_HI.save(lineRow);

				demandRow.setHandleStatus("S");
				demandRow.setOperatorUserId(userId);
				srmPoPlanDemandsDAO_HI.update(demandRow);
				row.setHandleStatus("S");
				count++;
			} else {
				break;
			}
		}
		srmPoPlanDemandLineDAO_HI.fluch();

		return count;
	}

	// 指定物料
	public void savePlanLineInItem(String planType,Integer userId) throws Exception {

		systemPrintln("Start savePlanLineInItem");

		List<SrmPlanAnalyzeEntity_HI_RO> list = null;
		SrmPlanAnalyzeEntity_HI_RO row = null;
		SrmPoPlanDemandLineEntity_HI lineRow = null;
		SrmPoPlanDemandsEntity_HI demandRow = null;
		// SrmPoApprovedListEntity_HI appListRow = null;
		// 供货比例
		List<SrmPlanAnalyzeEntity_HI_RO> proportionHlist = null;
		SrmPlanAnalyzeEntity_HI_RO proportionHRow = null;
		List<SrmPlanAnalyzeEntity_HI_RO> proportionLlist = null;
		SrmPlanAnalyzeEntity_HI_RO proportionLrow = null;

		proportionHlist = findProportionH(  planType,"ITEM", null, null);
		LOGGER.info("findProportionH指定物料" + JSON.toJSON(proportionHlist));
		if (proportionHlist != null) {
			for (int a = 0; a < proportionHlist.size(); a++) {
				proportionHRow = proportionHlist.get(a);
				proportionLlist = findProportionL(proportionHRow);
				list = findPlanDate(  planType,null, null, proportionHRow.getItemId(), null, null);
				if (proportionLlist.size() < 1 || list.size() < 1) {
					continue;
				}
				LOGGER.info("findProportionL指定物料" + JSON.toJSON(proportionLlist));
				LOGGER.info("findProportionL指定物料" + JSON.toJSON(proportionLlist));
				LOGGER.info("findPlanDate指定物料" + JSON.toJSON(list));

				for (int i = 0; i < list.size(); i++) {
					row = list.get(i);
					demandRow = srmPoPlanDemandsDAO_HI.getById(row.getPlanDemandId());
					if ("S".equals(demandRow.getHandleStatus()) || "S".equals(row.getHandleStatus())) {
						continue;
					}

					BigDecimal needQuanty = row.getNeedQuantity();
					for (int b = 0; b < proportionLlist.size(); b++) {
						proportionLrow = proportionLlist.get(b);

						if (needQuanty.compareTo(new BigDecimal(0)) <= 0
								|| proportionLrow.getCanDisQty().compareTo(new BigDecimal(0)) <= 0) {
							continue;
						}

						if (!SrmUtils.isNvl(demandRow.getSpecialUseNum())
								|| !SrmUtils.isNvl(demandRow.getDemandClassify()) || b == (proportionLlist.size() - 1)
								|| proportionLrow.getCanDisQty().compareTo(needQuanty) >= 0) {
							proportionLrow.setDisQty(needQuanty);
							proportionLrow.setCanDisQty(proportionLrow.getCanDisQty().subtract(needQuanty));
						} else {
							proportionLrow.setDisQty(proportionLrow.getCanDisQty());
							proportionLrow.setCanDisQty(new BigDecimal(0));
						}
						needQuanty = needQuanty.subtract(proportionLrow.getDisQty());
						lineRow = new SrmPoPlanDemandLineEntity_HI();
						lineRow.setPlanDemandId(row.getPlanDemandId());
						lineRow.setSupplierId(proportionLrow.getSupplierId());
						lineRow.setDistributeQuantity(proportionLrow.getDisQty());
						lineRow.setProportion(proportionLrow.getProportion());
						lineRow.setMonthProportion(proportionLrow.getMonthProportion());

						// appListRow =
						// intfUtilsServer.findApprovedList(demandRow.getItemId(),
						// proportionLrow.getSupplierId());
						// if (appListRow != null) {
						// lineRow.setIsApprovedList(appListRow.getEnabledFlag());
						// lineRow.setDayCapacity(appListRow.getDayCapacity());
						// } else {
						// lineRow.setIsApprovedList("N");
						// }

						lineRow.setOperatorUserId(userId);
						lineRow.setPlanType(planType);
						lineRow.setAttribute1("指定物料");
						lineRow.setAttribute2(proportionLrow.getSupplyDetailId().toString());
						lineRow.setAttribute3(proportionLrow.getCanDisQty() + "");
						lineRow.setAttribute4(proportionLrow.getSumMonQuantity() + "");
						lineRow.setAttribute5(proportionLrow.getSumPlanQty() + "");
						srmPoPlanDemandLineDAO_HI.save(lineRow);

						if (!SrmUtils.isNvl(demandRow.getDemandClassify())) {
							Integer t = i + 1;

							Integer count = this.saveSameDemandClassify(planType,list, proportionLrow, t,
									demandRow.getDemandClassify(), demandRow.getItemId(), userId);
							i = i + count;

						}

					}
					demandRow.setHandleStatus("S");
					demandRow.setOperatorUserId(userId);
					srmPoPlanDemandsDAO_HI.update(demandRow);

				}
				srmPoPlanDemandLineDAO_HI.fluch();

			}

		}
		systemPrintln("End savePlanLineInItem");
	}

	// 指定分类 可追溯
	public void savePlanLineCateR(String planType,Integer userId) throws Exception {

		systemPrintln("Start savePlanLineCateR");

		List<SrmPlanAnalyzeEntity_HI_RO> list = null;
		SrmPlanAnalyzeEntity_HI_RO row = null;
		SrmPoPlanDemandLineEntity_HI lineRow = null;
		SrmPoPlanDemandsEntity_HI demandRow = null;
		// SrmPoApprovedListEntity_HI appListRow = null;

		list = findPlanDate(  planType,"CATER", null, null, null, null);
		LOGGER.info("findPlanDate指定分类 可追溯" + JSON.toJSON(list));

		for (int i = 0; i < list.size(); i++) {
			row = list.get(i);
			demandRow = srmPoPlanDemandsDAO_HI.getById(row.getPlanDemandId());
			SrmPlanAnalyzeEntity_HI_RO lastPoRow = findLastPo(row.getItemId());
			if (lastPoRow != null) {
				lineRow = new SrmPoPlanDemandLineEntity_HI();
				lineRow.setPlanDemandId(row.getPlanDemandId());
				lineRow.setSupplierId(lastPoRow.getSupplierId());
				lineRow.setDistributeQuantity(row.getNeedQuantity());
				lineRow.setIsApprovedList(null);
				lineRow.setDayCapacity(null);
				lineRow.setMonthProportion(null);
				lineRow.setProportion(null);
				lineRow.setOperatorUserId(userId);
				lineRow.setAttribute1("savePlanLineCateR");
				// appListRow =
				// intfUtilsServer.findApprovedList(demandRow.getItemId(),
				// lastPoRow.getSupplierId());
				// if (appListRow != null) {
				// lineRow.setIsApprovedList(appListRow.getEnabledFlag());
				// lineRow.setDayCapacity(appListRow.getDayCapacity());
				// } else {
				// lineRow.setIsApprovedList("N");
				// }
			} else {
				lineRow = new SrmPoPlanDemandLineEntity_HI();
				lineRow.setPlanDemandId(row.getPlanDemandId());
				lineRow.setSupplierId(null);
				lineRow.setDistributeQuantity(row.getNeedQuantity());
				lineRow.setIsApprovedList(null);
				lineRow.setDayCapacity(null);
				lineRow.setMonthProportion(null);
				lineRow.setProportion(null);
				lineRow.setOperatorUserId(userId);
			}
			lineRow.setPlanType(planType);
			lineRow.setAttribute1("指定分类 可追溯");
			srmPoPlanDemandLineDAO_HI.save(lineRow);
			demandRow.setHandleStatus("S");
			demandRow.setOperatorUserId(userId);
			srmPoPlanDemandsDAO_HI.update(demandRow);

		}
		srmPoPlanDemandLineDAO_HI.fluch();

		systemPrintln("End savePlanLineCateR");

	}

	// 分类
	public void savePlanLineCate(String planType,Integer userId) throws Exception {

		systemPrintln("Start savePlanLineCate");

		List<SrmPlanAnalyzeEntity_HI_RO> list = null;
		SrmPlanAnalyzeEntity_HI_RO row = null;
		SrmPoPlanDemandLineEntity_HI lineRow = null;
		SrmPoPlanDemandsEntity_HI demandRow = null;
		SrmPoApprovedListEntity_HI appListRow = null;
		// 供货比例
		List<SrmPlanAnalyzeEntity_HI_RO> proportionHlist = null;
		SrmPlanAnalyzeEntity_HI_RO proportionHRow = null;
		List<SrmPlanAnalyzeEntity_HI_RO> proportionLlist = null;
		SrmPlanAnalyzeEntity_HI_RO proportionLrow = null;

		proportionHlist = findProportionH(  planType,"CATE", null, null);
		LOGGER.info("findProportionH 分类" + JSON.toJSON(proportionHlist));
		if (proportionHlist != null) {

			for (int a = 0; a < proportionHlist.size(); a++) {
				proportionHRow = proportionHlist.get(a);
				proportionLlist = findProportionL(proportionHRow);
				list = findPlanDate(  planType,null, null, null, proportionHRow.getCategoryCode(), null);
				if (proportionLlist.size() < 1 || list.size() < 1) {
					continue;
				}
				LOGGER.info("findProportionL分类" + JSON.toJSON(proportionLlist));
				LOGGER.info("findPlanDate分类" + JSON.toJSON(list));

				for (int i = 0; i < list.size(); i++) {
					row = list.get(i);
					demandRow = srmPoPlanDemandsDAO_HI.getById(row.getPlanDemandId());
					if ("S".equals(demandRow.getHandleStatus()) || "S".equals(row.getHandleStatus())) {
						continue;
					}

					BigDecimal ExcPro = null;
					int last = 0;

					BigDecimal needQuanty = row.getNeedQuantity();
					for (int b = 0; b < proportionLlist.size(); b++) {
						proportionLrow = proportionLlist.get(b);

						appListRow = intfUtilsServer.findApprovedList(demandRow.getItemId(),
								proportionLrow.getSupplierId());

						// 不存在货源表不分配
//						if (appListRow == null || !"Y".equals(appListRow.getEnabledFlag())) {
//							continue;
//						}

						// 获取供货比例执行信息
						if (ExcPro == null || ExcPro.compareTo(
								proportionLrow.getMonthProportion().subtract(proportionLrow.getProportion())) < 0) {
							ExcPro = proportionLrow.getMonthProportion().subtract(proportionLrow.getProportion());
							last = b;
						}

						if (needQuanty.compareTo(new BigDecimal(0)) <= 0
								|| proportionLrow.getCanDisQty().compareTo(new BigDecimal(0)) <= 0) {
							continue;
						}

						proportionLrow.setDisQty(needQuanty);
						proportionLrow.setCanDisQty(proportionLrow.getCanDisQty().subtract(needQuanty));

						needQuanty = needQuanty.subtract(proportionLrow.getDisQty());
						lineRow = new SrmPoPlanDemandLineEntity_HI();
						lineRow.setPlanDemandId(row.getPlanDemandId());
						lineRow.setSupplierId(proportionLrow.getSupplierId());
						lineRow.setDistributeQuantity(proportionLrow.getDisQty());
						lineRow.setProportion(proportionLrow.getProportion());
						lineRow.setMonthProportion(proportionLrow.getMonthProportion());

						//lineRow.setIsApprovedList(appListRow.getEnabledFlag());
						lineRow.setDayCapacity(appListRow.getDayCapacity());
						lineRow.setOperatorUserId(userId);
						lineRow.setPlanType(planType);
						lineRow.setAttribute1("指定分类");
						lineRow.setAttribute2(proportionLrow.getSupplyDetailId().toString());
						lineRow.setAttribute3(proportionLrow.getCanDisQty() + "");
						lineRow.setAttribute4(proportionLrow.getSumMonQuantity() + "");
						lineRow.setAttribute5(proportionLrow.getSumPlanQty() + "");
						srmPoPlanDemandLineDAO_HI.save(lineRow);

						if (!SrmUtils.isNvl(demandRow.getDemandClassify())) {
							Integer t = i + 1;
							Integer count = this.saveSameDemandClassify(planType,list, proportionLrow, t,
									demandRow.getDemandClassify(), demandRow.getItemId(), userId);

						}
					}

					if (needQuanty.compareTo(new BigDecimal(0)) > 0) {

						if (ExcPro != null) {
							proportionLrow = proportionLlist.get(last);
							proportionLrow.setDisQty(needQuanty);
							proportionLrow.setCanDisQty(proportionLrow.getCanDisQty().subtract(needQuanty));

							needQuanty = needQuanty.subtract(proportionLrow.getDisQty());
							lineRow = new SrmPoPlanDemandLineEntity_HI();
							lineRow.setPlanDemandId(row.getPlanDemandId());
							lineRow.setSupplierId(proportionLrow.getSupplierId());
							lineRow.setDistributeQuantity(proportionLrow.getDisQty());
							lineRow.setProportion(proportionLrow.getProportion());
							lineRow.setMonthProportion(proportionLrow.getMonthProportion());

							// appListRow =
							// intfUtilsServer.findApprovedList(demandRow.getItemId(),
							// proportionLrow.getSupplierId());
							// if (appListRow != null) {
							// lineRow.setIsApprovedList(appListRow.getEnabledFlag());
							// lineRow.setDayCapacity(appListRow.getDayCapacity());
							// } else {
							// lineRow.setIsApprovedList("N");
							// }
							lineRow.setOperatorUserId(userId);
							lineRow.setPlanType(planType);
							lineRow.setAttribute1("指定分类-分给执行效率高的供应商");
							lineRow.setAttribute2(proportionLrow.getSupplyDetailId().toString());
							lineRow.setAttribute3(proportionLrow.getCanDisQty() + "");
							lineRow.setAttribute4(proportionLrow.getSumMonQuantity() + "");
							lineRow.setAttribute5(proportionLrow.getSumPlanQty() + "");
							srmPoPlanDemandLineDAO_HI.save(lineRow);
						} else {
							proportionLrow = proportionLlist.get(last);
							lineRow = new SrmPoPlanDemandLineEntity_HI();
							lineRow.setPlanDemandId(row.getPlanDemandId());
							lineRow.setSupplierId(null);
							lineRow.setDistributeQuantity(needQuanty);
							lineRow.setProportion(null);
							lineRow.setMonthProportion(null);

							lineRow.setOperatorUserId(userId);
							lineRow.setPlanType(planType);
							lineRow.setAttribute1("对应供货比例供应商都没有维护货源表");
							srmPoPlanDemandLineDAO_HI.save(lineRow);

						}
					}

					demandRow.setHandleStatus("S");
					demandRow.setOperatorUserId(userId);
					srmPoPlanDemandsDAO_HI.update(demandRow);

				}

			}
		}
		systemPrintln("End savePlanLineCate");
	}

	// 其他无分配逻辑需求
	public void savePlanLineOther(String planType,Integer userId) throws Exception {

		systemPrintln("Start savePlanLineOther");

		SrmPlanAnalyzeEntity_HI_RO row = null;
		SrmPoPlanDemandLineEntity_HI lineRow = null;
		SrmPoPlanDemandsEntity_HI demandRow = null;
		List<SrmPlanAnalyzeEntity_HI_RO> list = findPlanDate(     planType,"Other", null, null, null, null);
		LOGGER.info("findPlanDate其他无分配逻辑需求" + JSON.toJSON(list));
		for (int i = 0; i < list.size(); i++) {
			row = list.get(i);
			srmPoPlanDemandsDAO_HI.fluch();
			demandRow = srmPoPlanDemandsDAO_HI.getById(row.getPlanDemandId());
			if ("S".equals(demandRow.getHandleStatus()) || "S".equals(row.getHandleStatus())) {
				continue;
			}
			lineRow = new SrmPoPlanDemandLineEntity_HI();
			lineRow.setPlanDemandId(row.getPlanDemandId());
			lineRow.setDistributeQuantity(row.getNeedQuantity());
			lineRow.setIsApprovedList("N");
			lineRow.setMonthProportion(null);
			lineRow.setProportion(null);
			lineRow.setOperatorUserId(userId);
			lineRow.setPlanType(planType);
			lineRow.setAttribute1("其他无分配逻辑需求");
			srmPoPlanDemandLineDAO_HI.save(lineRow);
			demandRow.setHandleStatus("S");
			demandRow.setOperatorUserId(userId);
			srmPoPlanDemandsDAO_HI.update(demandRow);
		}

		systemPrintln("End savePlanLineOther");
	}

	/**
	 * 计算分配，保存行信息
	 */
	public String saveAndHandPlanData(String planType, Integer logId, String batchId, String handleStatus, Integer userId) throws Exception {
		systemPrintln("Start savePlanLineData");
        this.savePlanData(planType, logId, batchId, handleStatus, userId);
		// 指定供应商
		this.savePlanLineInSupp(planType,userId);
		// 指定分厂
		this.savePlanLineInInst(planType,userId);
		// 指定物料
		this.savePlanLineInItem(planType,userId);
		// 分类 可追溯历史订单
		this.savePlanLineCateR(planType,userId);
		// 分类分配
		this.savePlanLineCate(planType,userId);
		// others
		this.savePlanLineOther(planType,userId);

		systemPrintln("End savePlanLineData");

		return "S";
	}

	/**
	 * 查询需求数据
	 * 
	 * @return
	 */
	public List<SrmPlanAnalyzeEntity_HI_RO> findPlanDate(String planType, String type, Integer instId, Integer itemId,
			String categoryCode, Integer supplierId) {
		StringBuffer sql = new StringBuffer();
		sql.append(SrmPlanAnalyzeEntity_HI_RO.QUERY_PLAN_DEMAND);
		sql.append("  and  t.handle_status = 'N' ");
		sql.append(" and  t.plan_type  = '" + planType + "'   ");
		// 指定供应商
		if ("SUPPLIER".equals(type)) {
			sql.append("	 and  ifnull( t.SUPPLIER_NUMBER,'')!='' ");
		}
		// 采购分类可追溯
		if ("CATER".equals(type)) {
			sql.append("   and c.retrospect_flag ='Y' ");
		}

		// 其他
		if ("Other".equals(type)) {

		}
		if (instId != null) {
			sql.append("	 and  t.inst_id =  " + instId);
		}
		if (itemId != null) {
			sql.append("	 and  t.item_id =  " + itemId);
		}
		if (categoryCode != null) {
			sql.append("	 and  t.category_code =  '" + categoryCode + "'");
		}

		if (supplierId != null) {
			sql.append("	 and  t.supplier_id =  " + supplierId);
		}

		sql.append("  order by   t.item_id,t.demand_classify,t.need_by_date  ");
		List<SrmPlanAnalyzeEntity_HI_RO> list = srmPlanAnalyzeDAO_HI_RO.findList(sql);
		return list;
	}

	/**
	 * 获取有效供货比例头信息
	 * 
	 * @param categoryCode
	 * @return
	 */

	public List<SrmPlanAnalyzeEntity_HI_RO> findProportionH(String planType, String type, Integer itemId,
			String categoryCode) {

		StringBuffer sql = new StringBuffer();
		sql.append(SrmPlanAnalyzeEntity_HI_RO.QUERY_PROPORTION_H);
		// 指定分厂
		if ("INST".equals(type)) {
			sql.append("  and  h.SUPPLY_TYPE ='SUPPLY'  and h.item_id is not null  and   h.INST_ID is not null   ");
			sql.append(" AND EXISTS (\r\n" + "	SELECT\r\n" + "		1\r\n" + "	FROM\r\n"
					+ "		srm_po_plan_demands d\r\n" + "	WHERE\r\n" + "		h.item_id = d.item_id\r\n"
					+ " and h.INST_ID = d.inst_id \n" + " and  d.plan_type  = '" + planType + "'\r\n "
					+ "	AND d.handle_status = 'N'\r\n" + ") ");
		}
		// 指定物料
		if ("ITEM".equals(type)) {
			sql.append("  and  h.SUPPLY_TYPE ='SUPPLY' and   h.INST_ID is  null  and h.item_id is not null   ");
			sql.append(" AND EXISTS (\r\n" + "	SELECT\r\n" + "		1\r\n" + "	FROM\r\n"
					+ "		srm_po_plan_demands d\r\n" + "	WHERE\r\n" + "		h.item_id = d.item_id\r\n"
					+ " and  d.plan_type  = '" + planType + "'\r\n " + "	AND d.handle_status = 'N'\r\n" + ") ");
		}
		// 指定分类
		if ("CATE".equals(type)) {
			sql.append(" and  h.SUPPLY_TYPE ='CLASSIFY' and  h.SMALL_CATEGORY_CODE is not null   ");
			sql.append("  AND EXISTS (\r\n" + "	SELECT\r\n" + "		1\r\n" + "	FROM\r\n"
					+ "		srm_po_plan_demands d\r\n" + "	WHERE\r\n"
					+ "		d.category_code = h.SMALL_CATEGORY_CODE\r\n" + " and  d.plan_type  = '" + planType
					+ "'\r\n " + "	AND d.handle_status = 'N'\r\n" + ")  ");
		}

		if (itemId != null) {
			sql.append(" and   i.item_id =   " + itemId);
		}

		if (categoryCode != null) {
			sql.append(" and h.INST_ID is   null and h.SMALL_CATEGORY_CODE =   '" + categoryCode + "'");
		}

		List<SrmPlanAnalyzeEntity_HI_RO> list = srmPlanAnalyzeDAO_HI_RO.findList(sql);
		return list;

	}

	/**
	 * 获取有效供货比例头信息
	 * 
	 * @return
	 */

	public List<SrmPlanAnalyzeEntity_HI_RO> findProportionL(Integer supplyId) {
		StringBuffer sql = new StringBuffer();
		sql.append(SrmPlanAnalyzeEntity_HI_RO.QUERY_PROPORTION_L);
		sql.append("  and    l.SUPPLY_ID =   " + supplyId);
		List<SrmPlanAnalyzeEntity_HI_RO> list = srmPlanAnalyzeDAO_HI_RO.findList(sql);
		return list;
	}

	/**
	 * 获取有效供货比例行信息
	 * 
	 * @return
	 */

	public List<SrmPlanAnalyzeEntity_HI_RO> findProportionL(SrmPlanAnalyzeEntity_HI_RO proportionHRow) {
		StringBuffer sql = new StringBuffer();
		sql.append(SrmPlanAnalyzeEntity_HI_RO.QUERY_PROPORTION_L);
		sql.append("  and    l.SUPPLY_ID =   " + proportionHRow.getSupplyId());
		sql.append(" order by l.PROPORTION desc ");
		List<SrmPlanAnalyzeEntity_HI_RO> list = srmPlanAnalyzeDAO_HI_RO.findList(sql);

		BigDecimal sumPlanQty = this.findSumPlanQty(proportionHRow.getSupplyType(), proportionHRow.getItemId(),
				proportionHRow.getCategoryCode());
		if (sumPlanQty == null || sumPlanQty.compareTo(new BigDecimal(0)) < 1) {
			return list;
		}

		BigDecimal sumMonQuantity = new BigDecimal(0);
		BigDecimal sumSupplierQty = new BigDecimal(0);
		BigDecimal canDisQty = null;

		for (SrmPlanAnalyzeEntity_HI_RO row : list) {
			sumSupplierQty = this.findSupplierPoQty(proportionHRow.getSupplyType(), proportionHRow.getItemId(),
					proportionHRow.getCategoryCode(), row.getSupplierId());
			sumMonQuantity = sumMonQuantity.add(sumSupplierQty);
			row.setSumSupplierQty(sumSupplierQty);
		}

		for (SrmPlanAnalyzeEntity_HI_RO row : list) {
			if (sumMonQuantity.compareTo(new BigDecimal(0)) == 0) {
				row.setMonthProportion(new BigDecimal(0));
			} else {
				row.setMonthProportion(row.getSumSupplierQty().divide(sumMonQuantity, 4).multiply(new BigDecimal(100)));
			}
			canDisQty = (sumMonQuantity.add(sumPlanQty)).multiply(row.getProportion()).divide(new BigDecimal(100), 0)
					.subtract(row.getSumSupplierQty());

			row.setSumMonQuantity(sumMonQuantity);
			row.setSumPlanQty(sumPlanQty);
			row.setCanDisQty(canDisQty);

		}

		return list;
	}

	/**
	 *
	 * @param itemId
	 * @return
	 */
	public SrmPlanAnalyzeEntity_HI_RO findLastPo(Integer itemId) {
		StringBuffer sql = new StringBuffer();
		sql.append(SrmPlanAnalyzeEntity_HI_RO.QUERY_PO_INFO);
		sql.append("   and  I.item_id=" + itemId);
		sql.append("  order by h.creation_date desc ");
		try {
			SrmPlanAnalyzeEntity_HI_RO row = srmPlanAnalyzeDAO_HI_RO.findList(sql).get(0);
			return row;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 供应商当月采购数量
	 * 
	 * @param itemId
	 * @param categoryCode
	 * @param supplierId
	 * @return
	 */
	public BigDecimal findSupplierPoQty(String supplyType, Integer itemId, String categoryCode, Integer supplierId) {
		StringBuffer sql = new StringBuffer();
		sql.append(SrmPlanAnalyzeEntity_HI_RO.QUERY_PO_QTY);
		sql.append(" and h.supplier_id =  " + supplierId);
		if (itemId != null && "SUPPLY".equals(supplyType)) {
			sql.append("  and i.item_id =  " + itemId);
		} else if (categoryCode != null && "CLASSIFY".equals(supplyType)) {
			sql.append("  and i.category_code =  '" + categoryCode + "'");
		} else {
			return new BigDecimal(0);
		}
		try {
			SrmPlanAnalyzeEntity_HI_RO row = srmPlanAnalyzeDAO_HI_RO.findList(sql).get(0);
			return row.getSumQuantity();
		} catch (Exception e) {
			return new BigDecimal(0);
		}

	}

	/**
	 * 获取计划需求的数量
	 * 
	 * @param itemId
	 * @param categoryCode
	 * @return
	 */
	public BigDecimal findSumPlanQty(String supplyType, Integer itemId, String categoryCode) {
		StringBuffer sql = new StringBuffer();
		sql.append(SrmPlanAnalyzeEntity_HI_RO.QUERY_PLAN_QTY);
		sql.append("  and  t.handle_status ='N'    ");
		if (!SrmUtils.isNvl(itemId) && "SUPPLY".equals(supplyType)) {
			sql.append("  and t.item_id =  " + itemId);
		} else if (!SrmUtils.isNvl(categoryCode) && "CLASSIFY".equals(supplyType)) {
			sql.append("  and t.category_code =  '" + categoryCode + "'");
		} else {
			return new BigDecimal(0);
		}

		try {
			SrmPlanAnalyzeEntity_HI_RO row = srmPlanAnalyzeDAO_HI_RO.findList(sql).get(0);
			return row.getSumQuantity();
		} catch (Exception e) {
			return null;
		}
	}
}
