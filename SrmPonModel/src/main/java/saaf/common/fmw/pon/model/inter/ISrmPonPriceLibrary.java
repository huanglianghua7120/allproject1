package saaf.common.fmw.pon.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.pon.model.entities.SrmPonPriceLibraryEntity_HI;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISrmPonPriceLibrary.java
 * Description：寻源--价格库信息
 *
 * Update History
 * ===========================================================================
 *  Version    Date           	   Updated By     Description
 *  -------    ---------------    -----------    ---------------
 *  V1.0       15:38 2020/2/21    zwj             创建
 * ===========================================================================
 */
public interface ISrmPonPriceLibrary {

	List<SrmPonPriceLibraryEntity_HI> findSrmPonPriceLibraryInfo(JSONObject queryParamJSON);

	Object saveSrmPonPriceLibraryInfo(JSONObject queryParamJSON);

	/**
	 * Description：招标询价-已完成-创建价格库
	 * @param jsonParams
	 * @return JSONObject
	 * =======================================================================
	 *  Version    Date                Updated By     Description
	 *  -------    ----------------  -----------    ---------------
	 *  V1.0       15:38 2020/2/21    zwj     	创建
	 * =======================================================================
	 */
	public JSONObject updateAndCreatePriceLibrary(JSONObject jsonParams);
}
