package saaf.common.fmw.po.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitArchivesLEntity_HI_RO;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.po.model.inter.ISrmPoRecruitArchivesL;

import java.util.List;

@Component("srmPoRecruitArchivesLService")
@Path("/srmPoRecruitArchivesLService")
public class SrmPoRecruitArchivesLService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitArchivesLService.class);
	@Autowired
	private ISrmPoRecruitArchivesL srmPoRecruitArchivesLServer;
	public SrmPoRecruitArchivesLService() {
		super();
	}
	/**
	 * Description：查询招聘历史行
	 * @param params 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@POST
	@Path("findRecruitmentArchivesLine")
	public String findRecruitmentArchivesLine(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			if (null == jsonParams.getInteger("recruitmentHeaderId") || null == jsonParams.getInteger("editionNum")) {
				LOGGER.error("查询失败");
				return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
			}
			List<SrmPoRecruitArchivesLEntity_HI_RO> result = srmPoRecruitArchivesLServer.findRecruitmentArchivesLine(jsonParams);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功!", 1, result);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}
}
