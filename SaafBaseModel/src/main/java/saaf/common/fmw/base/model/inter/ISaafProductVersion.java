package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.entities.SaafProductVersionEntity_HI;


/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafProductVersion.java
 * Description：项目维护护
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface ISaafProductVersion {

    /**
     *
     * @param
     * @param nowPage
     * @param pageSzie
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findProductVersion(JSONObject jsonParams, int nowPage, int pageSzie, int userId);

    /**
     *新增产品版本
     * @param instance
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    boolean saveProductVersion(SaafProductVersionEntity_HI instance, int userId);

    /**
     * 修改产品版本信息
     * @param instance
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    SaafProductVersionEntity_HI editProductVersion(SaafProductVersionEntity_HI instance, int userId);

    /**
     *
     * @param id
     * @param userId
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    boolean deleteProductVersion(Integer id, int userId);

    /**
     * 唯一性校验
     *
     * @param instance
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    boolean uniqueCheck(SaafProductVersionEntity_HI instance);

}
