package saaf.common.fmw.base.model.inter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.base.model.entities.SrmBaseManualsEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseManualsEntity_HI_RO;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：系统帮助
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmBaseManuals {
    /**
     * 查询系统帮助
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    Pagination<SrmBaseManualsEntity_HI_RO> findManuals(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception;
    /**
     * 查询系统帮助
     * @param jsonArray
     * @param operatorUserId
     * @return
     * @throws Exception
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * noticeViewerId  公告查看者ID  NUMBER  Y
     * noticeId  公告ID  NUMBER  Y
     * userId  查看用户ID  NUMBER  Y
     * viewDate  查看时间  DATE  N
     * viewIp  查看IP  VARCHAR2  N
     * replyDate  回复时间  DATE  N
     * replyContent  回复内容  VARCHAR2  N
     * noticeViewerId  公告查看者ID  NUMBER  Y
     * noticeId  公告ID  NUMBER  Y
     * userId  查看用户ID  NUMBER  Y
     * viewDate  查看时间  DATE  N
     * viewIp  查看IP  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    JSONObject saveManualsFile(JSONArray jsonArray, int operatorUserId) throws Exception ;

}
