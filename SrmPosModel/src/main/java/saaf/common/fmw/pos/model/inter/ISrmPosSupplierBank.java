package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierBankEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierBankEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商银行
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmPosSupplierBank {

	/**
	 * 删除银行信息
	 * @param jsonParams
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public JSONObject deleteSupplierBank(JSONObject jsonParams);

    /**
     * 保存供应商的银行信息（档案自助维护/内部创建供应商）
     * @param supplierBankList
     * @param userId
     * @param supplierId
     * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public boolean saveSrmPosSupplierBankInfo(List<SrmPosSupplierBankEntity_HI> supplierBankList,Integer userId,Integer supplierId);

	/**
	 * 保存供应商的银行信息——供应商接口（数据输入）
	 * @param supplierBankList
	 * @param userId
	 * @param supplierId
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public void saveSrmPosSupplierBankInfoExternal(List<SrmPosSupplierBankEntity_HI> supplierBankList,Integer userId,Integer supplierId);

	/**
	 * 校验供应商的银行信息重复与必填项——供应商接口（数据输入）
	 * @param supplierBankList
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public String judgeSpaceSupplierBank(List<SrmPosSupplierBankEntity_HI> supplierBankList);
	/**
	 * 查询供应商的银行信息
	 * @param jsonParams
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public Pagination<SrmPosSupplierBankEntity_HI_RO> findSupplierBankInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

	public JSONObject saveSupplierBankInfo(JSONArray paramDataList, int userId, int supplierId) throws Exception ;

}
