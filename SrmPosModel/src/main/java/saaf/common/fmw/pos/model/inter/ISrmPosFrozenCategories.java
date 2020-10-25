package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import saaf.common.fmw.pon.model.entities.readonly.SrmPosFrozenCategoriesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.SrmPosFrozenCategoriesEntity_HI;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商冻结、恢复
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     hgq             创建
 * ===========================================================================
 */
public interface ISrmPosFrozenCategories {

	/**
	 * 查询冻结品类信息
	 * @param queryParamJSON
	 * @return
	 * @throws Exception
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     hgq             创建
	 * ===========================================================================
	 */
	List<SrmPosFrozenCategoriesEntity_HI_RO> findSrmPosFrozenCategoriesByCon(JSONObject queryParamJSON) throws Exception;

}
