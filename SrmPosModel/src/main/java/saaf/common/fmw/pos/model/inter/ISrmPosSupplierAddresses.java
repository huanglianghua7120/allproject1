package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierAddressesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierAddressesEntity_HI_RO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商地址
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmPosSupplierAddresses {

	/**
	 * 删除供应商地址及其子表地点
	 * @param jsonParams
	 * @return
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     zhj             创建
	 * ===========================================================================
	 */
	public JSONObject deleteSupplierAddress(JSONObject jsonParams);
	/**
	 * 查询供应商的地址谱
	 * @param jsonParams
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     zhj             创建
	 * ===========================================================================
	 */
	public Pagination<SrmPosSupplierAddressesEntity_HI_RO> findSupplierAddresses(JSONObject jsonParams, Integer pageIndex, Integer pageRows);

	/**
	 * 保存供应商地址谱的头表（档案自助维护/内部创建供应商）
	 * @param supplierAddressesList
	 * @param userId
	 * @param supplierId
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     zhj             创建
	 * ===========================================================================
	 */
	public boolean saveSupplierAddressHead(List<SrmPosSupplierAddressesEntity_HI> supplierAddressesList,Integer userId,Integer supplierId);

	/**
	 * 保存供应商地址谱的头表——供应商接口（数据输入）
	 * @param supplierAddressesInfoAllList
	 * @param userId
	 * @param supplierId
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     zhj             创建
	 * ===========================================================================
	 */
	public void saveSupplierAddressAllExternal(List<Map> supplierAddressesInfoAllList, Integer userId, Integer supplierId);

	/**
	 * 校验供应商的地址重复与必填项——供应商接口（数据输入）
	 * @param supplierAddressesInfoAllList
	 * @return
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     zhj             创建
	 * ===========================================================================
	 */
	public String judgeSpaceSupplierAddress(List<Map> supplierAddressesInfoAllList);
	/**
	 * 保存供应商地址
	 *
	 * @param paramDataList
	 * @param userId
	 * @param supplierId
	 * @return
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     zhj             创建
	 * ===========================================================================
	 */
	public JSONObject saveSupplierAddress(JSONArray paramDataList, int userId, int supplierId) throws Exception;
	
	/**
	 * 查询供应商地址
	 * @param jsonParams
	 * @return
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     zhj             创建
	 * ===========================================================================
	 */
	public List<SrmPosSupplierAddressesEntity_HI_RO> findSupplierAddressesById(JSONObject jsonParams);


}
