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
import com.yhg.hibernate.core.paging.Pagination;

import saaf.common.fmw.base.model.entities.readonly.SrmBaseNoticesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseNoticeViewers;
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
@Path("/srmBaseNoticeViewersService")
@Component
public class SrmBaseNoticeViewersService extends CommonAbstractServices {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SrmBaseNoticeViewersService.class);
	
	@Autowired
	private ISrmBaseNoticeViewers srmBaseNoticeViewersServer;
	
	
	/**
	 * 查询查看列表
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
	@Path("findViewersList")
	@POST
	public String findViewersList(@FormParam("params") String params,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageRows") Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			LOGGER.info("------jsonParam-----" + jsonParam.toString());
			Pagination<SrmBaseNoticesEntity_HI_RO> supplierList = srmBaseNoticeViewersServer
					.findViewersList(jsonParam, pageIndex, pageRows);
			return JSON.toJSONString(supplierList);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询列表异常：" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询列表失败!"
					+ e, 0, null);
		}
	}

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

	@Path("saveViewers")
	@POST
	public String saveViewers(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			String viewIP = super.getIpAddr();
			jsonParam.put("viewIP", viewIP);
			JSONObject posJson = srmBaseNoticeViewersServer.saveViewers(jsonParam, viewIP);
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
}
