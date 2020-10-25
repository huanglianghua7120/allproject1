package saaf.common.fmw.base.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import saaf.common.fmw.base.model.inter.ISrmBaseNoticeFiles;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：附件
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Path("/srmBaseNoticeFilesService")
@Component
public class SrmBaseNoticeFilesService extends CommonAbstractServices{
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SrmBaseNoticeFilesService.class);

	@Autowired
	private ISrmBaseNoticeFiles srmBaseNoticeFilesServer;
	
	
	/**
	 * 查询附件
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findNoticeFilesList")
	public String findNoticeFilesList(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmBaseNoticeFilesServer.findNoticeFilesList(jsonParams));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询失败" + e.getMessage());
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
		}
	}
	
	
	
	/**
	 * 删除公告附件
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("deleteNoticeFiles")
	@POST
	public String deleteNoticeFiles(@FormParam("params") String params) {
		LOGGER.info("删除信息,参数：" + params.toString());
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmBaseNoticeFilesServer.deleteNoticeFiles(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(
					jsondata.getString("status"), jsondata.getString("msg"),
					jsondata.getInteger("count"), jsondata.get("data"));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("--->>删除失败！参数：" + params + ",异常：" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0,
					null);
		}
	}
}
