package saaf.common.fmw.base.services;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SrmBaseNoticesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseNotices;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;

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
@Path("/srmBaseNoticesService")
@Component
public class SrmBaseNoticesService extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SrmBaseNoticesService.class);

	@Autowired
	private ISrmBaseNotices srmBaseNoticesServer;

	/**
	 * 查询公告列表
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("findNoticesList")
	@POST
	public String findNoticesList(@FormParam("params") String params,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageRows") Integer pageRows) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			LOGGER.info("------jsonParams-----" + jsonParams.toString());
			Pagination<SrmBaseNoticesEntity_HI_RO> noticesList = srmBaseNoticesServer.findNoticesList(jsonParams, pageIndex, pageRows);
			return JSON.toJSONString(noticesList);
		} catch (Exception e) {
			LOGGER.error("查询列表异常：" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询列表失败!"
					+ e, 0, null);
		}
	}
	
	/**
	 * 查询公告信息
	 * 
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("findNoticesInfo")
	@POST
	public String findNoticesInfo(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = JSONObject.parseObject(params);
			Integer noticeId = jsonParam.getInteger("noticeId");
			if (noticeId == null || noticeId.equals("")) {
				return convertResultJSONObj("E", "请检查noticeId参数", 0, null);
			}
			List<SrmBaseNoticesEntity_HI_RO> noticesList = srmBaseNoticesServer
					.findNoticesInfo(jsonParam);
			return JSON.toJSONString(noticesList);
		} catch (Exception e) {
			LOGGER.error("查询失败！", e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}

	/**
	 * 查询已发布的公告
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("findReleaseNoticeList")
	@POST
	public String findReleaseNoticeList(@FormParam("params") String params,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageRows") Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			LOGGER.info("------jsonParam-----" + jsonParam.toString());
			Pagination<SrmBaseNoticesEntity_HI_RO> noticesList = srmBaseNoticesServer
					.findReleaseNoticeList(jsonParam, pageIndex, pageRows);
			return JSON.toJSONString(noticesList);
		} catch (Exception e) {
			LOGGER.error("查询列表异常：" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询列表失败!"
					+ e, 0, null);
		}
	}
	
	/**
	 * 查询已发布的公告列表
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("findPublishNoticeList")
	@POST
	public String findPublishNoticeList(@FormParam("params") String params,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageRows") Integer pageRows) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			LOGGER.info("------jsonParams-----" + jsonParams.toString());
			Pagination<SrmBaseNoticesEntity_HI_RO> noticesList = srmBaseNoticesServer.findPublishNoticeList(jsonParams, pageIndex, pageRows);
			return JSON.toJSONString(noticesList);
		} catch (Exception e) {
			LOGGER.error("查询列表异常：" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询列表失败!", 0, null);
		}
	}
	
	/**
	 * 查询自已有多少公告
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("findNoticesBySelf")
	@POST
	public String findNoticesBySelf(@FormParam("params") String params,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageRows") Integer pageRows) {
		Pagination<SrmBaseNoticesEntity_HI_RO> noticesList = null;
		try {
			JSONObject jsonParams = this.parseObject(params);
			LOGGER.info("------jsonParams-----" + jsonParams.toString());
			 noticesList = srmBaseNoticesServer.findNoticesBySelf(jsonParams, pageIndex, pageRows);
		} catch (Exception e) {
			LOGGER.error("查询用户的公告信息：" + "失败" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询用户的公告信息失败!"
					+ e, 0, null);
		}
		return JSON.toJSONString(noticesList);
	}

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

	@Path("saveNotices")
	@POST
	@Produces("application/json")
	public String saveNotices(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject posJson = srmBaseNoticesServer
					.saveNotices(jsonParam);
			return CommonAbstractServices.convertResultJSONObj(
					posJson.getString("status"), posJson.getString("msg"),
					posJson.getInteger("count"), posJson.get("data"));
		} catch (Exception e) {
			LOGGER.error("保存失败！" + e, e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E",
					"保存失败!" + e, 0, null);
		}
	}

	/**
	 * 删除公告
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("deleteNotices")
	@POST
	public String deleteNotices(@FormParam("params") String params) {
		LOGGER.info("删除信息,参数：" + params.toString());
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmBaseNoticesServer.deleteNotices(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(
					jsondata.getString("status"), jsondata.getString("msg"),
					jsondata.getInteger("count"), jsondata.get("data"));
		} catch (Exception e) {
			LOGGER.error("--->>删除失败！参数：" + params + ",异常：" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "删除失败!", 0,
					null);
		}
	}

	/**
	 * 撤回公告
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("updateWithdrawNotices")
	@POST
	public String updateWithdrawNotices(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject posJson = srmBaseNoticesServer
					.updateWithdrawNotices(jsonParam);
			return CommonAbstractServices.convertResultJSONObj(
					posJson.getString("status"), posJson.getString("msg"),
					posJson.getInteger("count"), posJson.get("data"));
		} catch (Exception e) {
			LOGGER.error("撤回失败！" + e, e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E",
					"撤回失败!" + e, 0, null);
		}
	}

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

	@Path("saveReleaseNotices")
	@POST
	public String saveReleaseNotices(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject posJson = srmBaseNoticesServer
					.saveReleaseNotices(jsonParam);
			return CommonAbstractServices.convertResultJSONObj(
					posJson.getString("status"), posJson.getString("msg"),
					posJson.getInteger("count"), posJson.get("data"));
		} catch (Exception e) {
			LOGGER.error("发布失败！" + e, e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E",
					"发布失败!" + e, 0, null);
		}
	}

}
