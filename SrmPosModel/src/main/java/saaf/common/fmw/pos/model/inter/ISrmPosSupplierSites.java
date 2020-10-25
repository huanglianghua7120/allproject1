package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.pos.model.entities.SrmPosSupplierSitesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierSitesEntity_HI_RO;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商地点
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmPosSupplierSites {

	public List<SrmPosSupplierSitesEntity_HI_RO> findSupplierSites(JSONObject jsonParam);
	
	 /**
     * 查询供应商地点
     * @param jsonParams
     * @return
	  * ==============================================================================
	  *  Version    Date           Updated By     Description
	  *  -------    -----------    -----------    ---------------
	  *  V1.0       2020-04-15     hgq             创建
	  * ==============================================================================
	  */
    List<SrmPosSupplierSitesEntity_HI_RO> findSrmPosSupplierSites(JSONObject jsonParams) throws Exception;
    /**
     * 查询供应商地点ly
     * @param jsonParams
     * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    List<SrmPosSupplierSitesEntity_HI_RO> findSites(JSONObject jsonParams) throws Exception;

	/**
	 * 查询退出供应商地点
	 * @param jsonParams
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	List<SrmPosSupplierSitesEntity_HI_RO> findQuitSites(JSONObject jsonParams) throws Exception;

	/**
	 * 根据供应商ID，查询有效的供应商地点
	 * @param jsonParams
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	List<SrmPosSupplierSitesEntity_HI_RO> findSiteListByEffective(JSONObject jsonParams) throws Exception;

	/**
	 * 根据供应商ID，查询可新增的地点
	 * @param jsonParams
	 * @return
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	List<SrmPosSupplierSitesEntity_HI_RO> findSiteListByNew(JSONObject jsonParams) throws Exception;

	/**
	 * 查询供应商地点,本方法属于现场考察
	 * 
	 * @param jsonParams
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public List<SrmPosSupplierSitesEntity_HI_RO> findSupplierSiteOfReview(JSONObject jsonParams) throws Exception;

	/**
	 * 保存供应商的地点——供应商接口（数据输入）
	 * @param supplierSitesList
	 * @param supplierAddressId
	 * @param userId
	 * @param supplierId
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public void saveSupplierSitesExternal(List<SrmPosSupplierSitesEntity_HI> supplierSitesList,Integer supplierAddressId,Integer userId,Integer supplierId);

	/**
	 * 校验供应商的地点必填项——供应商接口（数据输入）
	 * @param supplierSitesList
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public String judgeSpaceSupplierSites(List<SrmPosSupplierSitesEntity_HI> supplierSitesList);

	
	/**
	 * 根据供应商ID查询地点LOV
	 * @param jsonParams
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public Pagination<SrmPosSupplierSitesEntity_HI_RO> findSupplierSiteLov(JSONObject jsonParams,Integer pageIndex,Integer pageRows);
	/**
	 * 送货通知查询供应商地点
	 * @param jsonParams
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public List<SrmPosSupplierSitesEntity_HI_RO> findSupplierSiteForNotice(JSONObject jsonParams)throws Exception;
	/**
	 * 查找计划考察的地点
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	public List<SrmPosSupplierSitesEntity_HI_RO> findSupplierPlanSites(JSONObject jsonParam);
}
