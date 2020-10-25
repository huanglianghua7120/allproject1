package saaf.common.fmw.base.model.inter;

import java.util.List;

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
public interface ISrmBaseNotices {
	
	/**
	 * 查询系统公告
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
    Pagination<SrmBaseNoticesEntity_HI_RO> findNoticesList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;  
    
    /**
     * 查询公告信息
     * @param params
     * @return
     * @throws Exception
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    List<SrmBaseNoticesEntity_HI_RO> findNoticesInfo(JSONObject params) throws Exception;
    /**
     * 查询已发布的公告
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
    Pagination<SrmBaseNoticesEntity_HI_RO> findReleaseNoticeList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;  
     
   /**
    * 查询已发布的公告列表
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
    Pagination<SrmBaseNoticesEntity_HI_RO> findPublishNoticeList(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception; 
    
    /**
     * 查询自已有多少公告
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
    Pagination<SrmBaseNoticesEntity_HI_RO> findNoticesBySelf(JSONObject params, Integer pageIndex, Integer pageRows) throws Exception;

	/**
	 * Description：保存公告
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * isTechnologyChange  是否技改  VARCHAR2  N
	 * endActiveDate  失效日期  DATE  N
	 * noticeId  公告ID  NUMBER  Y
	 * noticeCode  公告编号  VARCHAR2  Y
	 * noticeTitle  公告标题  VARCHAR2  Y
	 * noticeContent  公告内容  CLOB  N
	 * noticeType  公告类型  VARCHAR2  Y
	 * noticeStatus  公告状态  VARCHAR2  Y
	 * publishDate  发布时间  DATE  N
	 * publishUserId  发布用户  NUMBER  N
	 * cancelDate  撤回时间  DATE  N
	 * cancelUserId  撤回用户  NUMBER  N
	 * setTopFlag  置顶标识  VARCHAR2  N
	 * receiverType  发布对象  VARCHAR2  Y
	 * noticeId  公告ID  NUMBER  Y
	 * noticeCode  公告编号  VARCHAR2  Y
	 * noticeTitle  公告标题  VARCHAR2  Y
	 * noticeContent  公告内容  CLOB  N
	 * noticeType  公告类型  VARCHAR2  Y
	 * noticeStatus  公告状态  VARCHAR2  Y
	 * publishDate  发布时间  DATE  N
	 * publishUserId  发布用户  NUMBER  N
	 * cancelDate  撤回时间  DATE  N
	 * cancelUserId  撤回用户  NUMBER  N
	 * setTopFlag  置顶标识  VARCHAR2  N
	 * receiverType  发布对象  VARCHAR2  Y
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */
    JSONObject saveNotices(JSONObject params) throws Exception;
    
    /**
	 * 删除公告
	 * @param params
	 * @return
	 * @throws Exception
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    JSONObject deleteNotices(JSONObject params) throws Exception;
    
    /**
	 * 撤回公告
	 * @param params
	 * @return
	 * @throws Exception
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    JSONObject updateWithdrawNotices(JSONObject params) throws Exception;

	/**
	 * Description：发布公告
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * isTechnologyChange  是否技改  VARCHAR2  N
	 * endActiveDate  失效日期  DATE  N
	 * noticeId  公告ID  NUMBER  Y
	 * noticeCode  公告编号  VARCHAR2  Y
	 * noticeTitle  公告标题  VARCHAR2  Y
	 * noticeContent  公告内容  CLOB  N
	 * noticeType  公告类型  VARCHAR2  Y
	 * noticeStatus  公告状态  VARCHAR2  Y
	 * publishDate  发布时间  DATE  N
	 * publishUserId  发布用户  NUMBER  N
	 * cancelDate  撤回时间  DATE  N
	 * cancelUserId  撤回用户  NUMBER  N
	 * setTopFlag  置顶标识  VARCHAR2  N
	 * receiverType  发布对象  VARCHAR2  Y
	 * noticeId  公告ID  NUMBER  Y
	 * noticeCode  公告编号  VARCHAR2  Y
	 * noticeTitle  公告标题  VARCHAR2  Y
	 * noticeContent  公告内容  CLOB  N
	 * noticeType  公告类型  VARCHAR2  Y
	 * noticeStatus  公告状态  VARCHAR2  Y
	 * publishDate  发布时间  DATE  N
	 * publishUserId  发布用户  NUMBER  N
	 * cancelDate  撤回时间  DATE  N
	 * cancelUserId  撤回用户  NUMBER  N
	 * setTopFlag  置顶标识  VARCHAR2  N
	 * receiverType  发布对象  VARCHAR2  Y
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

    JSONObject saveReleaseNotices(JSONObject params) throws Exception;
    
}
