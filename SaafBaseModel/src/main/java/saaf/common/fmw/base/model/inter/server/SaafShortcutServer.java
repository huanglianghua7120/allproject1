package saaf.common.fmw.base.model.inter.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.SaafShortcutEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafShortcutEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafShortcut;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
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
@Component("saafShortcutServer")
public class SaafShortcutServer implements ISaafShortcut {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SaafShortcutServer.class);

	@Autowired
	private ViewObject<SaafShortcutEntity_HI> saafShortcutDAO_HI;
	
	@Autowired
	private BaseViewObject<SaafShortcutEntity_HI_RO> saafShortcutDAO_HI_RO;

	public SaafShortcutServer() {
		super();
	}
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
	public List<SaafShortcutEntity_HI> findSaafShortcutInfo(
			JSONObject queryParamJSON) {
		Map<String, Object> queryParamMap = SToolUtils
				.fastJsonObj2Map(queryParamJSON);
		List<SaafShortcutEntity_HI> findListResult = saafShortcutDAO_HI
				.findList("from SaafShortcutEntity_HI", queryParamMap);
		return findListResult;
	}


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
	public JSONObject save(JSONObject parameters) {
		int userId = parameters.getInteger("varUserId").intValue();
		int varInputRespId = parameters.getInteger("varInputRespId").intValue();
		JSONArray array = parameters.getJSONArray("data");
		for (int i = 0; i < array.size(); i++) {
			Integer menuId = array.getInteger(i);
			SaafShortcutEntity_HI row = new SaafShortcutEntity_HI();
			row.setMenuId(menuId);
			row.setRespId(Integer.valueOf(varInputRespId));
			row.setUserId(Integer.valueOf(userId));
			row.setOperatorUserId(userId);
			this.saafShortcutDAO_HI.save(row);
		}
		return SToolUtils.convertResultJSONObj("S", "保存成功", 0, null);
	}
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
	@Override
	public String queryShortcutInUser(JSONObject paramJSON) {
		StringBuffer query = new StringBuffer();
        query.append(SaafShortcutEntity_HI_RO.QUERY_SHORTCUT_IN_USER);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        queryParamMap.put("varUserId", paramJSON.get("varUserId"));
        queryParamMap.put("varRespId", paramJSON.get("varRespId"));
        List findListResult = saafShortcutDAO_HI_RO.findList(query.toString(), queryParamMap);
		
		return JSON.toJSONString(findListResult);
	}
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
	@Override
	public void deleteShortcut(Integer id) {
		saafShortcutDAO_HI.delete(id);
	}

}
