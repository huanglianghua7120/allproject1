package saaf.common.fmw.base.model.inter;

import java.util.List;

import saaf.common.fmw.base.model.entities.SaafShortcutEntity_HI;

import com.alibaba.fastjson.JSONObject;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafShortcut.java
 * Description：用来处理用户维护
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface ISaafShortcut {

	/**
	 * 查询LIST列表
	 * @param queryParamJSON
	 * @return
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     钟士元             创建
	 * ===========================================================================
	 */
	List<SaafShortcutEntity_HI> findSaafShortcutInfo(JSONObject queryParamJSON);



	/**
	 *  保存
	 * @param
	 * @return
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     钟士元             创建
	 * ===========================================================================
	 */
	public JSONObject save(JSONObject parameters);
	/**
	 *  根据用户查询
	 * @param
	 * @return
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     钟士元             创建
	 * ===========================================================================
	 */
	String queryShortcutInUser(JSONObject paramJSON);
	/**
	 *  删除
	 * @param
	 * @return
	 * ===========================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     钟士元             创建
	 * ===========================================================================
	 */
	void deleteShortcut(Integer id);
}
