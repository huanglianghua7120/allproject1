package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import saaf.common.fmw.base.model.entities.readonly.SaafAreasEntity_HI_RO;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafAreas.java
 * Description：用来处理用户信息
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface ISaafAreas {

    /**
     * 查询地址
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    String findData(JSONObject params, Integer pageIndex, Integer pageRows);

    /**
     * 编辑地址
     * @param params
     * @param userId
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    boolean save(JSONObject params, Integer userId);

    /**
     * 查询地址
     * @param sql
     * @param map
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    List<SaafAreasEntity_HI_RO> findList(String sql, Map<String, Object> map);

    /**
     * 删除地址
     * @param areaId
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    boolean delete(Integer areaId) throws Exception;

    /**
     * 查询城市LOV
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    String findCity(JSONObject params, Integer pageIndex, Integer pageRows);
}
