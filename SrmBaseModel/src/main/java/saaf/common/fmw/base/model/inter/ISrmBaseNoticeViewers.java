package saaf.common.fmw.base.model.inter;

import saaf.common.fmw.base.model.entities.readonly.SrmBaseNoticesEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：公告
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmBaseNoticeViewers {
	
	/**
     * 查询查看列表
     * @param params
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
     Pagination<SrmBaseNoticesEntity_HI_RO> findViewersList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

    /**
     * Description：保存公告查看者
     *
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

     JSONObject saveViewers(JSONObject params,String viewIP) throws Exception;
}
