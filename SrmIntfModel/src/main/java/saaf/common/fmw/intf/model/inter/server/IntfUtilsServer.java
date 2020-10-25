package saaf.common.fmw.intf.model.inter.server;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.*;
import saaf.common.fmw.intf.model.entities.readonly.SrmPlanAnalyzeEntity_HI_RO;
import saaf.common.fmw.intf.model.inter.IIntfUtils;
import saaf.common.fmw.po.model.entities.SrmPoApprovedListEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierBankEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;

import java.util.HashMap;
import java.util.Map;

@Component("intfUtilsServer")
public class IntfUtilsServer implements IIntfUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(IntfUtilsServer.class);

	@Autowired
	private ViewObject srmIntfLogsDAO_HI;

	@Autowired
	private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;
	@Autowired
	private ViewObject<SrmBaseCategoriesEntity_HI> srmBaseCategoriesDAO_HI;
	@Autowired
	private ViewObject<SrmBaseItemsEntity_HI> srmBaseItemsDAO_HI;
	@Autowired
	private ViewObject<SaafInstitutionEntity_HI> saafInstitutionDAO_HI;
	@Autowired
	private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;
	@Autowired
	private ViewObject<SrmPosSupplierBankEntity_HI> srmPosSupplierBankDAO_HI;
	@Autowired
	private BaseViewObject<SrmPlanAnalyzeEntity_HI_RO> srmPlanAnalyzeDAO_HI_RO;
	@Autowired
	private ViewObject<SaafEmployeesEntity_HI> saafEmployeesDAO_HI;
	@Autowired
	private ViewObject<SrmPoApprovedListEntity_HI> srmPoApprovedListDAO_HI;

	/**
	 * 获取分厂
	 * 
	 * @param instCode
	 * @return
	 */
	public SaafInstitutionEntity_HI findInstByCode(String instCode) {
		try {
			SaafInstitutionEntity_HI row = saafInstitutionDAO_HI.findByProperty("inst_code", instCode).get(0);

			return row;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取物料
	 * 
	 * @param itemCode
	 * @return
	 */
	public SrmBaseItemsEntity_HI findItemByCode(String itemCode) {
		try {
			SrmBaseItemsEntity_HI row = srmBaseItemsDAO_HI.findByProperty("item_code", itemCode).get(0);

			return row;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取供应商信息
	 * 
	 * @param itemCode
	 * @return
	 */
	public SrmPosSupplierInfoEntity_HI findSupplierByCode(String supplierNumber) {
		try {
			SrmPosSupplierInfoEntity_HI row = srmPosSupplierInfoDAO_HI.findByProperty("supplier_number", supplierNumber)
					.get(0);
			return row;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取供应商信息
	 * 
	 * @param itemCode
	 * @return
	 */
	public SrmPosSupplierInfoEntity_HI findSupplierById(Integer supplierId) {
		try {
			SrmPosSupplierInfoEntity_HI row = srmPosSupplierInfoDAO_HI.findByProperty("supplier_id", supplierId).get(0);
			return row;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取供应商银行信息
	 * 
	 * @param itemCode
	 * @return
	 */
	public SrmPosSupplierBankEntity_HI findSupplierByCode(Integer supplierId) {
		try {
			SrmPosSupplierBankEntity_HI row = srmPosSupplierBankDAO_HI.findByProperty("supplier_id", supplierId).get(0);
			return row;
		} catch (Exception e) {
			return null;
		}
	}

	public SrmPlanAnalyzeEntity_HI_RO findPoIntfByLineNum(String poNumber, Integer lineNumber) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(SrmPlanAnalyzeEntity_HI_RO.QUERY_PO_INFO);
			sql.append("   and  h.po_number = '" + poNumber + "'");
			sql.append("   and  l.line_number =   " + lineNumber);
			SrmPlanAnalyzeEntity_HI_RO row = srmPlanAnalyzeDAO_HI_RO.findList(sql).get(0);
			return row;
		} catch (Exception e) {
			// //e.printStackTrace();
			return null;
		}

	}

	/**
	 * 获取采购员信息
	 * 
	 * @return
	 */
	public SaafEmployeesEntity_HI findEmpByCode(String employeeNumber) {
		try {
			SaafEmployeesEntity_HI row = saafEmployeesDAO_HI.findByProperty("employee_number", employeeNumber).get(0);
			return row;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取货源表 信息
	 * 
	 * @return
	 */
	public SrmPoApprovedListEntity_HI findApprovedList(Integer itemId, Integer supplierId) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("item_id", itemId);
			map.put("supplier_id", supplierId);
			SrmPoApprovedListEntity_HI row = srmPoApprovedListDAO_HI.findByProperty(map).get(0);
			return row;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 清除接口表历史记录
	 * 
	 * @throws Exception
	 */
	public void deleteIntfLogs() throws Exception {
		// 删除查询日志记录
		String sql1 = " DELETE from srm_intf_logs  where intf_type like 'C%' AND  CREATION_DATE < DATE_SUB(SYSDATE(),INTERVAL 7 DAY) ";
		String sqll2 = " DELETE from srm_intf_logs  where  INTF_STATUS = 'E' and  CREATION_DATE < DATE_SUB(SYSDATE(),INTERVAL 7 DAY) ";
		String sql2 = " DELETE from srm_intf_logs  where   CREATION_DATE < DATE_SUB(SYSDATE(),INTERVAL 270 DAY) ";
		srmIntfLogsDAO_HI.executeSqlUpdate(sql1);
		srmIntfLogsDAO_HI.fluch();
		srmIntfLogsDAO_HI.executeSqlUpdate(sql2);
		srmIntfLogsDAO_HI.fluch();
		srmIntfLogsDAO_HI.executeSqlUpdate(sqll2);
		srmIntfLogsDAO_HI.fluch();
		// 删除接口表记录
		String sql3 = " DELETE from srm_intf_datas  where    CREATION_DATE < DATE_SUB(SYSDATE(),INTERVAL 7 DAY) ";
		String sql4 = " DELETE from srm_intf_po_changes  where    CREATION_DATE < DATE_SUB(SYSDATE(),INTERVAL 7 DAY) ";
		String sql5 = "  DELETE from srm_intf_forecasts  where    CREATION_DATE < DATE_SUB(SYSDATE(),INTERVAL 7 DAY) ";
		String sqlp1 = " DELETE from srm_intf_plan_demand  where    CREATION_DATE < DATE_SUB(SYSDATE(),INTERVAL 7 DAY) ";
		srmIntfLogsDAO_HI.executeSqlUpdate(sql3);
		srmIntfLogsDAO_HI.executeSqlUpdate(sql4);
		srmIntfLogsDAO_HI.executeSqlUpdate(sql5);
		srmIntfLogsDAO_HI.executeSqlUpdate(sqlp1);
		// 删除历史表数据
		String sql6 = "   DELETE from srm_po_change_info_his  where  `status` ='UNCHECK' and  CREATION_DATE < DATE_SUB(SYSDATE(),INTERVAL 7 DAY) ";
		String sql7 = "  DELETE from srm_po_plan_demand_his  where    CREATION_DATE < DATE_SUB(SYSDATE(),INTERVAL 7 DAY)  ";
		String sql8 = "  DELETE from srm_po_plan_demand_line_his  where    CREATION_DATE < DATE_SUB(SYSDATE(),INTERVAL 7 DAY)  ";

		srmIntfLogsDAO_HI.executeSqlUpdate(sql6);
		srmIntfLogsDAO_HI.executeSqlUpdate(sql7);
		srmIntfLogsDAO_HI.executeSqlUpdate(sql8);

	}

}
