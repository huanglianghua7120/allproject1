package saaf.common.fmw.base.model.inter.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.base.model.entities.SrmBaseNoticeFilesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseNoticesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseNoticeFiles;
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
@Component("/srmBaseNoticeFilesServer")
public class SrmBaseNoticeFilesServer implements ISrmBaseNoticeFiles {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseNoticeFilesServer.class);

	public SrmBaseNoticeFilesServer() {
		super();
	}
	
	@Autowired
	private ViewObject<SrmBaseNoticeFilesEntity_HI> srmBaseNoticeFilesDAO_HI;
	
	@Autowired
	private BaseViewObject<SrmBaseNoticesEntity_HI_RO> srmBaseNoticesDAO_HI_RO;
	
	
	/**
	 * 查询公告公告附件
	 * @throws Exception
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Override
	public List<SrmBaseNoticesEntity_HI_RO> findNoticeFilesList(
			JSONObject params) throws Exception {
		LOGGER.info("查询参数：" + params.toString());
		try {
			Integer noticeId = params.getInteger("noticeId");
			String sql = SrmBaseNoticesEntity_HI_RO.QUERY_NOTICE_FILES_SQL;
			StringBuffer queryString = new StringBuffer();
			Map<String, Object> map = new HashMap<String, Object>();
			queryString.append(" and f.notice_id  = :noticeId");
			map.put("noticeId", noticeId);
			List<SrmBaseNoticesEntity_HI_RO> filesList = srmBaseNoticesDAO_HI_RO.findList(sql + queryString, map);
			return filesList;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 保存公告附件
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Override
	public void saveNoticeFilesList(
			List<SrmBaseNoticeFilesEntity_HI> noticeFilesList, Integer userId,
			Integer noticeId) throws Exception {
		try {
			if (null != noticeFilesList && noticeFilesList.size() > 0) {
				for (SrmBaseNoticeFilesEntity_HI i : noticeFilesList) {
					i.setNoticeId(noticeId);
					i.setOperatorUserId(userId);
				}
				srmBaseNoticeFilesDAO_HI.saveOrUpdateAll(noticeFilesList);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 删除公告附件
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Override
	public JSONObject deleteNoticeFiles(JSONObject params) throws Exception {
		LOGGER.info("删除参数：" + params.toString());
		SrmBaseNoticeFilesEntity_HI filesRow = null;
		try {
			List<SrmBaseNoticeFilesEntity_HI> filesList = srmBaseNoticeFilesDAO_HI.findByProperty("noticeFileId", params.getInteger("noticeFileId"));
			if (filesList != null && filesList.size() > 0) {
				filesRow = filesList.get(0);
				srmBaseNoticeFilesDAO_HI.delete(filesRow);
			} else {
				return SToolUtils.convertResultJSONObj("E","删除失败，" + params.getString("noticeFileId") + "不存在",0, null);
			}
			return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
