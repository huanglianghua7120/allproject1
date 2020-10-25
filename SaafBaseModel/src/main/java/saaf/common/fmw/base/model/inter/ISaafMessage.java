package saaf.common.fmw.base.model.inter;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：ISaafMessage.java
 * Description：消息
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2020-04-15     HLH            创建
 * ===========================================================================
 */
public interface ISaafMessage {
    /**
     * 查询消息头
     *
     * @param queryParamJSON
     * @param pagIndex
     * @param pagRow
     * @return
     * @throws Exception
     * Update History
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public Pagination findSaafMessageHead(JSONObject queryParamJSON, Integer pagIndex, Integer pagRow) throws Exception;
    /**
     * 查询消息行
     *
     * @param queryParamJSON
     * @param pagIndex
     * @param pagRow
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public Pagination findSaafMessageLine(JSONObject queryParamJSON, Integer pagIndex, Integer pagRow) throws Exception;
    /**
     * 保存消息头
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public JSONObject saveSaafMessageHead(JSONObject paramData, JSONArray paramDataList, int userId) throws Exception;
    /**
     * 查询用户
     *
     * @param queryParamJSON
     * @param pagIndex
     * @param pagRow
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public Pagination findMessageUserLOV(JSONObject queryParamJSON, Integer pagIndex, Integer pagRow) throws Exception;
    /**
     * 查询用户组
     *
     * @param queryParamJSON
     * @param pagIndex
     * @param pagRow
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public Pagination findMessageGroupLOV(JSONObject queryParamJSON, Integer pagIndex, Integer pagRow) throws Exception;
    /**
     * 消息推送
     *
     * @param messageId
     * @param crateuserId
     * @param sourceType
     * @param sourceId
     * @return
     * @throws Exception
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public JSONObject updateSaafMessage(Integer messageId, int crateuserId, String sourceType, String sourceId) throws Exception;
    /**
     * 用于调度，删除代办/通知数据
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject deleteSaafMessagePush(JSONObject params);
    /**
     * 查询冻结、合格、引入中状态下的供应商
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public Pagination findSupplierUserLov(JSONObject parameters, Integer pageIndex, Integer pageRows)throws Exception;
    
    /**
     * 推送通知
     * @param messageId
     * @param crateuserId
     * @param sourceType
     * @param sourceId
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public JSONObject pushSaafMessage(Integer messageId, int crateuserId, String sourceType, String sourceId) throws Exception;
    
    /**
     * 删除消息行
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject deleteSaafMessageLine(JSONObject params) throws Exception;
    
    /**
     * 保存头行
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    public JSONObject saveSaafMessageHeadLine(JSONObject params) throws Exception;
    
    /**
     * 删除通知头
     * @param params
     * @return
     * ===========================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     HLH            创建
     * ===========================================================================
     */
    JSONObject deleteSaafMessageHeader(JSONObject params) throws Exception;
}
