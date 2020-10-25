package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：IUserForCunstom.java
 * Description：经销商管理的用户
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface IUserForCunstom {

    /**
     *  查询经销商管理的用户
     * @param parameters
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-03-09     吴嘉利          创建
     * ==============================================================================
     */
    public Pagination find(JSONObject parameters, Integer pageIndex, Integer pageSize) throws Exception;

    /**
     * 保存经销商新增的用户
     * @param params
     * @return
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-03-09     吴嘉利          创建
     * ==============================================================================
     */
    public JSONObject save(JSONObject params);


}
