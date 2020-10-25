package saaf.common.fmw.base.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseManualsEntity_HI_RO;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.base.model.inter.ISrmBaseManuals;
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
@Component("srmBaseManualsService")
@Path("/srmBaseManualsService")
public class SrmBaseManualsService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseManualsService.class);
	@Autowired
	private ISrmBaseManuals srmBaseManualsServer;
	public SrmBaseManualsService() {
		super();
	}

	/**
	 * 查询系统帮助
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
	@POST
	@Path("findManuals")
	public String findCategoryLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SrmBaseManualsEntity_HI_RO> pag = srmBaseManualsServer.findManuals(jsonParams, pageIndex, pageRows);
			LOGGER.info("-->>参数：" + params + "查询成功！");
			return JSON.toJSONString(pag);
		} catch (Exception e) {
			LOGGER.error("查询失败" + e,e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}
	/**
	 * 保存系统帮助附件
	 * @param params
	 * @return
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
	@POST
	@Path("saveManualsFile")
	@Produces("application/json")
	public String saveManualsFile(@FormParam(PARAMS) String params) {
		LOGGER.info(params);
		try{
			JSONObject jsonParams = this.parseObject(params);
			int operatorUserId = jsonParams.getInteger("operatorUserId");
			JSONArray jsonArray = jsonParams.getJSONArray("array");
			JSONObject object = srmBaseManualsServer.saveManualsFile(jsonArray,operatorUserId);
			return JSONObject.toJSONString(object);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("生效失败" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
		}
	}
}
