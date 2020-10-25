package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import java.util.Map;

import saaf.common.fmw.base.model.entities.readonly.SaafPositionsEntity_HI_RO;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：BaseSaafMessageSerices.java
 * Description：部门维护护控制类
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface ISaafPositions {

    /**
     * LOV:职务名称
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findPositionsName(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询部门列表
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findPositionsList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询部门
     * @param map
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    SaafPositionsEntity_HI_RO findPositionsLine(Map<String, Object> map) throws Exception;

    /**
     * 保存部门
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject savePositions(JSONObject parameters) throws Exception;

    /**
     * 删除部门
     * @param posId
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject deletePositions(Integer posId) throws Exception;

}
