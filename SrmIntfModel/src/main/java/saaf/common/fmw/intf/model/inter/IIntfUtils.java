package saaf.common.fmw.intf.model.inter;

import org.springframework.beans.factory.annotation.Autowired;

import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafInstitutionEntity_HI;
import saaf.common.fmw.intf.model.entities.readonly.SrmPlanAnalyzeEntity_HI_RO;
import saaf.common.fmw.base.model.entities.SrmBaseItemsEntity_HI;
import saaf.common.fmw.po.model.entities.SrmPoApprovedListEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierBankEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;

public interface IIntfUtils {
	/**
	 * 获取分厂
	 * @param instCode
	 * @return
	 */
	public SaafInstitutionEntity_HI findInstByCode(String instCode);
	/**
	 * 获取物料
	 * @param itemCode
	 * @return
	 */
	public SrmBaseItemsEntity_HI findItemByCode(String itemCode);	/**
	 * 获取供应商信息
	 * @param itemCode
	 * @return
	 */
	public SrmPosSupplierInfoEntity_HI findSupplierByCode(String supplierNumber)	;
	
	public SrmPosSupplierInfoEntity_HI findSupplierById(Integer supplierId);
	/**
	 * 获取供应商银行信息
	 * @param itemCode
	 * @return
	 */
	public SrmPosSupplierBankEntity_HI findSupplierByCode(Integer supplierId);
	/**
	 * 获取订单信息
	 * @param poNumber
	 * @param lineNumber
	 * @return
	 */
	public SrmPlanAnalyzeEntity_HI_RO findPoIntfByLineNum(String poNumber, Integer lineNumber);
	/**
	 * 获取采购员信息 
	 * @return
	 */
	public SaafEmployeesEntity_HI findEmpByCode(String employeeNumber);
	/**
	 * 获取货源表信息
	 * @return
	 */
	public SrmPoApprovedListEntity_HI findApprovedList(Integer itemId,Integer supplierId); 
	/**
	 * 清除接口表历史记录
	 * @throws Exception
	 */
	public void deleteIntfLogs() throws Exception;
	
}
