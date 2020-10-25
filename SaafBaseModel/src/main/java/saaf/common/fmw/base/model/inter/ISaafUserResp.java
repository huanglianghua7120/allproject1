package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONObject;

import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.model.entities.readonly.SaafUserMainRespEntity_HI_RO;

import java.util.List;
import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafUserResp.java
 * Description：用来处理用户维护
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface ISaafUserResp {

    /**
     *保存有效职责（内部创建供应商）
     * @param userId
     * @param responsibilityKey  默认职责
     * @param platformCode      职责编码
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public boolean saveSaafUserResp(Integer userId,Integer varUserId,String responsibilityKey, String platformCode);

    /**
     * 查询用户职责SessionList（登录）
     * @param param
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    List findSaafUserResp(Object[] param) throws Exception;

    /**
     * 查询用户职责列表（用户维护）
     * @param map
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    List findSaafUserRespByUserId(Map<String, Object> map) throws Exception;

    /**
     * 查询用户供应商职责列表
     * @param map
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    List findSaafUserSupplierRespByUserId(Map<String, Object> map) throws Exception;

    /**
     * 查询职责维护列表（用户维护）
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findSaafUserRespList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;


    /**
     * 查询职责所有用户(用户职责维护)
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findRespUserList(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询职责未分配所有用户(用户职责维护)
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findRespRemaining(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 查询用户所有职责(用户职责维护)
     * @param parameters
     * @return
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findUserRespAll(JSONObject parameters) throws Exception;

    /**
     * LOV:查询未分配给用户的所以职责
     * @param parameters
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    Pagination findRemainderUserResp(JSONObject parameters, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * 创建用户职责
     * @param parameters
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    List saveSaafUserResp(JSONObject parameters) throws Exception;

    /**
     * 删除用户职责
     * @param parameters
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject deleteSaafUserResp(JSONObject parameters) throws Exception;

    /**
     * 验证字段：userRespName是否重复
     * @param parameters
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    boolean findExisUserRespName(JSONObject parameters) throws Exception;
}
