package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierQuitSitesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierAddressesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierQuitSitesEntity_HI_RO;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商退出地点
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmPosSupplierQuitSites {
	/**
	 *  获取供应商地址
	 * @param queryParamJSON
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	List<SrmPosSupplierQuitSitesEntity_HI> findSrmPosSupplierQuitSitesInfo(JSONObject queryParamJSON);

	Object saveSrmPosSupplierQuitSitesInfo(JSONObject queryParamJSON);
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
	List<SrmPosSupplierQuitSitesEntity_HI_RO> findSupplierSites(JSONObject jsonParams);
}
