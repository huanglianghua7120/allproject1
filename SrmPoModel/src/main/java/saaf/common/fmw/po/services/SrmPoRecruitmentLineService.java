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
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitmentLineEntity_HI_RO;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.po.model.inter.ISrmPoRecruitmentLine;

import java.util.List;

@Component("srmPoRecruitmentLineService")
@Path("/srmPoRecruitmentLineService")
public class SrmPoRecruitmentLineService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitmentLineService.class);
	@Autowired
	private ISrmPoRecruitmentLine srmPoRecruitmentLineServer;
	public SrmPoRecruitmentLineService() {
		super();
	}


	/**
	 * Description：删除订单行
	 * @param params 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@POST
	@Path("deleteRecruitmentLines")
	public String deleteRecruitmentLines(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmPoRecruitmentLineServer.deleteRecruitmentLines(jsonParams));
		} catch (UtilsException e){
			LOGGER.error(e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}

	/**
	 * Description：招聘行查询
	 * @param params 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@POST
	@Path("findRecruitmentLineInfo")
	public String findRecruitmentLineInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
									  @FormParam(PAGE_ROWS) Integer pageRows) {
		try {
			pageRows=10;
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPoRecruitmentLineEntity_HI_RO> result = srmPoRecruitmentLineServer.findRecruitmentLineInfo(jsonParams);
			LOGGER.info("-->>参数：" + params + "查询成功！");
			//return JSON.toJSONString(result);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功！", result.size(), result);
		} catch (UtilsException e) {
			LOGGER.error(e.getMessage());
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}



}
