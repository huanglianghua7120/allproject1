package saaf.common.fmw.intf.model.inter.server;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.base.model.entities.SaafInstitutionEntity_HI;
import saaf.common.fmw.intf.model.entities.SrmIntfPoChangesEntity_HI;
import saaf.common.fmw.intf.model.entities.SrmIntfPoStarvingEntity_HI;
import saaf.common.fmw.intf.model.entities.readonly.SrmPlanAnalyzeEntity_HI_RO;
import saaf.common.fmw.intf.model.inter.IIntfPoAnalyze;
import saaf.common.fmw.intf.model.inter.IIntfUtils;
import saaf.common.fmw.intf.util.U9IntfUtils;
import saaf.common.fmw.base.model.entities.SrmBaseItemsEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoChangeInfoEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoChangeInfoHisEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoStarvingEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;
import saaf.common.fmw.utils.SrmUtils;

@Component("intfPoAnalyzeServer")
public class IntfPoAnalyzeServer implements IIntfPoAnalyze {
	private static final Logger LOGGER = LoggerFactory.getLogger(IntfPoAnalyzeServer.class);

	// private ViewObject srmIntfLogsDAO_HI;
	// @Autowired
	// private ViewObject srmIntfLogsDAO_HI;

	// @Autowired
	// private ViewObject<SaafLookupValuesEntity_HI> saafLookupTypesDAO_HI;

	@Autowired
	private IIntfUtils intfUtilsServer;

	@Autowired
	private ViewObject<SrmIntfPoChangesEntity_HI> srmIntfPoChangesDAO_HI;
	@Autowired
	private ViewObject<SrmIntfPoStarvingEntity_HI> srmIntfPoStarvingDAO_HI;
	@Autowired
	private ViewObject<SrmPoChangeInfoEntity_HI> srmPoChangeInfoDAO_HI;
	@Autowired
	private ViewObject<SrmPoStarvingEntity_HI> srmPoStarvingDAO_HI;
	@Autowired
	private BaseViewObject<SrmPlanAnalyzeEntity_HI_RO> srmPlanAnalyzeDAO_HI_RO;

	@Autowired
	private ViewObject<SrmPoChangeInfoHisEntity_HI> srmPoChangeInfoHisDAO_HI;

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

	/**
	 * 保存工单缺料
	 * 
	 * @param logId
	 * @param batchId
	 * @param handleStatus
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String saveStarving(Integer logId, String batchId, String handleStatus, Integer userId) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("handle_status", "N");
		List<SrmIntfPoStarvingEntity_HI> list = srmIntfPoStarvingDAO_HI.findByProperty(map);
		if (list == null) {
			return null;
		}

		SrmIntfPoStarvingEntity_HI row = null;
		SrmPoStarvingEntity_HI starvingRow = null;

		SrmBaseItemsEntity_HI itemRow = null;
		SaafInstitutionEntity_HI instRow = null;
		SrmPosSupplierInfoEntity_HI supplierRow = null;

		for (int i = 0; i < list.size(); i++) {
			row = list.get(i);

			try {
				starvingRow = new SrmPoStarvingEntity_HI();

				instRow = intfUtilsServer.findInstByCode(row.getInstCode());
				if (instRow == null) {
					throw new Exception("分厂数据不正确！");
				}
				starvingRow.setInstId(instRow.getInstId());

				itemRow = intfUtilsServer.findItemByCode(row.getItemCode());
				if (itemRow == null) {
					throw new Exception("物料编码数据不正确！");
				}
				starvingRow.setItemId(itemRow.getItemId());
				//starvingRow.setCategoryCode(itemRow.getCategoryCode());
				starvingRow.setEmployeeId(itemRow.getAgentId());
				starvingRow.setShortCheckDate(row.getShortCheckDate());
				starvingRow.setWipEntityNumber(row.getWipEntityNumber());
				starvingRow.setDemandClassify(row.getDemandClassify());
				starvingRow.setNeedQuantity(row.getNeedQuantity());
				starvingRow.setNeedByDate(row.getNeedByDate());

				if (row.getSupplierNumber() != null) {
					supplierRow = intfUtilsServer.findSupplierByCode(row.getSupplierNumber());
					if (supplierRow == null) {
						throw new Exception("指定供应商数据不正确！");
					}
					starvingRow.setSupplierNumber(row.getSupplierNumber());
				}
				starvingRow.setSpecialUseNum(row.getSpecialUseNum());

				srmPoStarvingDAO_HI.saveOrUpdate(starvingRow);
				row.setHandleStatus("S");
				row.setOperatorUserId(userId);
				row.setHandleMsg(null);
				srmIntfPoStarvingDAO_HI.save(row);
			} catch (Exception e) {
				//e.printStackTrace();
				row.setHandleStatus("E");
				row.setOperatorUserId(userId);
				row.setHandleMsg(e.getMessage());
				srmIntfPoStarvingDAO_HI.save(row);
			}
		}
		return null;

	}

	// 保存历史信息，清空当前表记录

	public void saveHistory() throws Exception {
		List<SrmPoChangeInfoEntity_HI> list = srmPoChangeInfoDAO_HI.findByProperty("CREATED_BY",
				U9IntfUtils.IMP_USER_ID);
		for (int i = 0; i < list.size(); i++) {
			SrmPoChangeInfoEntity_HI row = list.get(i);
			SrmPoChangeInfoHisEntity_HI hisRow = new SrmPoChangeInfoHisEntity_HI();
			hisRow.setPoChangeId(row.getPoChangeId());
			hisRow.setPlanDate(row.getPlanDate());
			hisRow.setPoNumber(row.getPoNumber());
			hisRow.setPoHeaderId(row.getPoHeaderId());
			hisRow.setPoLineNum(row.getPoLineNum());
			hisRow.setPoLineId(row.getPoLineId());
			hisRow.setChangeType(row.getChangeType());
			hisRow.setOrderQuantity(row.getOrderQuantity());
			hisRow.setNeedByDate(row.getNeedByDate());
			hisRow.setStatus(row.getStatus());
			hisRow.setOldNeedByDate(row.getOldNeedByDate());
			hisRow.setOldOrderQuantity(row.getOldOrderQuantity());
			hisRow.setOriginDate(row.getOriginDate());
			hisRow.setOriginQuantity(row.getOriginQuantity());
			hisRow.setOperatorUserId(row.getLastUpdatedBy());
			srmPoChangeInfoHisDAO_HI.save(hisRow);
		}
		if (list.size() > 0) {
			srmPoChangeInfoDAO_HI.delete(list);
		}
	}

	/**
	 * 保存采购订单变更
	 * 
	 * @param logId
	 * @param batchId
	 * @param handleStatus
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String savePoChange(Integer logId, String batchId, String handleStatus, Integer userId) throws Exception {
		// Map<String, Object> map = this.convertMap(logId, batchId,
		// handleStatus, null);
		// List<SrmIntfPoChangesEntity_HI> list =
		// srmIntfPoChangesDAO_HI.findByProperty(map);

		StringBuffer sql = new StringBuffer(SrmPlanAnalyzeEntity_HI_RO.QUERY_PO_CHANGE_INFO);
		if (!SrmUtils.isNvl(logId)) {
			sql.append("  and  t.log_id=    " + logId);
		}

		if (!SrmUtils.isNvl(batchId)) {
			sql.append("  and  t.batch_Id='" + batchId + "'");
		}

		if (!SrmUtils.isNvl(handleStatus)) {
			sql.append("  and  t.handle_Status='" + handleStatus + "'");
		}

		List<SrmPlanAnalyzeEntity_HI_RO> list = srmPlanAnalyzeDAO_HI_RO.findList(sql);

		if (list.size() < 1) {
			return "S";
		}

		SrmIntfPoChangesEntity_HI row = null;
		SrmPoChangeInfoEntity_HI changeRow = null;
		SrmPlanAnalyzeEntity_HI_RO poChange = null;

		for (int i = 0; i < list.size(); i++) {
			poChange = list.get(i);
			row = srmIntfPoChangesDAO_HI.findByProperty("Intf_Id", poChange.getIntfId()).get(0);

			try {
				changeRow = new SrmPoChangeInfoEntity_HI();
				changeRow.setPlanDate(poChange.getPlanDate());
				changeRow.setOrderQuantity(poChange.getOrderQuantity());
				changeRow.setNeedByDate(poChange.getNeedByDate());
				changeRow.setOriginDate(poChange.getOriginDate());
				changeRow.setOriginQuantity(poChange.getOriginQuantity());
				// itemRow =
				// intfUtilsServer.findItemByCode(poChange.getItemCode());
				if (poChange.getItemId() == null) {
					throw new Exception("物料编码数据不正确！");
				}
				String changeType = null;
				if ("1".equals(poChange.getChangeType())) {
					changeType = "U";
				} else if ("2".equals(poChange.getChangeType())) {
					changeType = "C";
				} else {
					throw new Exception("其他更改类型不做处理！");
				}
				changeRow.setChangeType(changeType);
				changeRow.setStatus("UNCHECK");

				if (poChange.getPoLineId() == null) {
					throw new Exception("采购订单行数据不正确！");
				}

				if ("程序导入".equals(poChange.getAttribute10())) {
					throw new Exception("初始化数据不用调整！");
				}

				changeRow.setPoNumber(poChange.getPoNumber());
				changeRow.setPoLineNum(poChange.getLineNumber());
				changeRow.setPoHeaderId(poChange.getPoHeaderId());
				changeRow.setPoLineId(poChange.getPoLineId());
				changeRow.setOperatorUserId(userId);
				srmPoChangeInfoDAO_HI.save(changeRow);
				row.setHandleStatus("S");
				row.setOperatorUserId(userId);
				row.setHandleMsg(null);
				srmIntfPoChangesDAO_HI.update(row);

			} catch (Exception e) {
				// //e.printStackTrace();
				row.setHandleStatus("E");
				row.setOperatorUserId(userId);
				row.setHandleMsg(e.getMessage());
				srmIntfPoChangesDAO_HI.update(row);
			}
		}

		return null;
	}

}
