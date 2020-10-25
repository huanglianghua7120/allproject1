package saaf.common.fmw.base.model.inter;

import java.util.List;

import saaf.common.fmw.base.model.entities.SrmBaseNoticeFilesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseNoticesEntity_HI_RO;

import com.alibaba.fastjson.JSONObject;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：公告附件
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
public interface ISrmBaseNoticeFiles {
	
	/**
     * 查询公告附件
     * @param params
     * @return
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    public List<SrmBaseNoticesEntity_HI_RO> findNoticeFilesList(JSONObject params) throws Exception;
    
    /**
     * 保存公告附件
     * @param noticeFilesList
     * @param userId
     * @param noticeId
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    public void saveNoticeFilesList(List<SrmBaseNoticeFilesEntity_HI> noticeFilesList,Integer userId,Integer noticeId) throws Exception;
    
    /**
	 * 删除公告附件
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
    JSONObject deleteNoticeFiles(JSONObject params) throws Exception;
}
