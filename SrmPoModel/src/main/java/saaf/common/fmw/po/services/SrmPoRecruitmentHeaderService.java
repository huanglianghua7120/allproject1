package saaf.common.fmw.po.services;

import com.alibaba.fastjson.JSON;
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
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitmentHeaderEntity_HI_RO;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.po.model.inter.ISrmPoRecruitmentHeader;

import java.util.List;

/**
 * Project Name：srmPoRecruitmentHeaderService
 * Company Name：SIE
 * Program Name：
 * Description：招聘需求
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoRecruitmentHeaderService")
@Path("/srmPoRecruitmentHeaderService")
public class SrmPoRecruitmentHeaderService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitmentHeaderService.class);
	@Autowired
	private ISrmPoRecruitmentHeader srmPoRecruitmentHeaderServer;
	public SrmPoRecruitmentHeaderService() {
		super();
	}

	/**
	 * Description：招聘头
	 * @param params 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@POST
	@Path("findRecruitmentHeaderInfo")
	public String findRecruitmentHeaderInfo(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			if (null == jsonParams.getInteger("recruitmentHeaderId")) {
				LOGGER.error("查询失败");
				return CommonAbstractServices.convertResultJSONObj("E", "头ID不存在，查询失败!", 0, null);
			}
			List<SrmPoRecruitmentHeaderEntity_HI_RO> result = srmPoRecruitmentHeaderServer.findRecruitmentHeaderInfo(jsonParams);

			LOGGER.info("-->>参数：" + params + "查询成功！");
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功!", 1, result);
		}catch (UtilsException e){
			LOGGER.error(e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}
	/**
	 * Description：招聘查询页
	 * @param params 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@POST
	@Path("findRecruitmentInfo")
	public String findRecruitmentInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
									  @FormParam(PAGE_ROWS) Integer pageRows) {
		try {
			pageRows=10;
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SrmPoRecruitmentHeaderEntity_HI_RO> result = srmPoRecruitmentHeaderServer.findRecruitmentInfo(jsonParams,pageIndex,pageRows);
			LOGGER.info("-->>参数：" + params + "查询成功！");
			return JSON.toJSONString(result);
			//return CommonAbstractServices.convertResultJSONObj("S", "查询成功!", 0, result);
		} catch (UtilsException e) {
			LOGGER.error(e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}


	/**
	 * Description：查找招聘版本
	 * @param params 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@POST
	@Path("findEditionNum")
	public String findEditionNum(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			if (null == jsonParams.getInteger("recruitmentHeaderId")) {
				LOGGER.error("查询失败");
				return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
			}
			List<SrmPoRecruitmentHeaderEntity_HI_RO> result = srmPoRecruitmentHeaderServer.findEditionNum(jsonParams);

			LOGGER.info("-->>参数：" + params + "查询成功！");
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功!", 1, result);
		}catch (UtilsException e){
			LOGGER.error(e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}

	/**
	 * Description：招聘 取消,变更
	 * @param params 条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@POST
	@Path("updateRecruitmentHeaderStatus")
	public String updateRecruitmentHeaderStatus(@FormParam(PARAMS) String params) {
		try {

			JSONObject jsonParams = this.parseObject(params);

			JSONObject jsondata = srmPoRecruitmentHeaderServer.updateRecruitmentHeaderStatus(jsonParams);
			return convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT),
					jsondata.get(DATA));
		}catch (UtilsException e){
			LOGGER.error(e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}

	/**
	 * Description：招聘 保存 发布
	 * @param params 条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@POST
	@Path("saveOrSubmitRecruitmentInfo")
	public String saveOrSubmitRecruitmentInfo(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmPoRecruitmentHeaderServer.saveOrSubmitRecruitmentInfo(jsonParams);
			return convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT),
					jsondata.get(DATA));
		}catch (UtilsException e){
			LOGGER.error(e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误"+e, 0, null);
		}
	}


	/**
	 * Description：删除招聘需求
	 * @param params 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@POST
	@Path("deleteRecruitment")
	public String deleteRecruitment(@FormParam(PARAMS) String params) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			JSONObject json = srmPoRecruitmentHeaderServer.deleteRecruitment(paramJSON);
			return CommonAbstractServices.convertResultJSONObj(
					json.getString("status"), json.getString("msg"),
					json.getInteger("count"), json.get("data"));
		}catch (UtilsException e){
			LOGGER.error(e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}

}
