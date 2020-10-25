package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseLocationsEntity_HI_RO;

import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：地址
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmBaseLocations {
    /**
     * 地址查询
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	public Pagination<SrmBaseLocationsEntity_HI_RO> findSrmBaseLocationsInfo(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 地址详情查询
     * @param paramJSON
     * @param pageIndex
     * @param pageRows
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Map<String, Object> findSrmBaseLocationsInfoByDefault(JSONObject paramJSON, Integer pageIndex, Integer pageRows);


    /**
     * Description：删除地点
     * @param
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      谢晓霞          创建
     * ==============================================================================
     */
    public JSONObject deleteBaseLocation(JSONObject params) throws Exception;


    /**
     * Description：保存地点
     * @param
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      谢晓霞          创建
     * ==============================================================================
     */
    public JSONObject saveBaseLocation(JSONObject jsonParams) throws Exception;
}
