package saaf.common.fmw.po.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.po.model.entities.readonly.SrmPoRecruitArchivesHEntity_HI_RO;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.po.model.inter.ISrmPoRecruitArchivesH;

import java.util.List;

@Component("srmPoRecruitArchivesHService")
@Path("/srmPoRecruitArchivesHService")
public class SrmPoRecruitArchivesHService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoRecruitArchivesHService.class);
	@Autowired
	private ISrmPoRecruitArchivesH srmPoRecruitArchivesHServer;


	public SrmPoRecruitArchivesHService() {
		super();
	}
	/**
	 * Description：查询招聘历史头
	 * @param params 查询条件参数
	 * @return
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-18     SIE 谢晓霞       创建
	 * =======================================================================
	 */
	@POST
	@Path("findRecruitmentArchivesHeader")
	public String findRecruitmentArchivesHeader(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			if (null == jsonParams.getInteger("recruitmentHeaderId") || null == jsonParams.getInteger("editionNum")) {
				LOGGER.error("查询失败");
				return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
			}
			List<SrmPoRecruitArchivesHEntity_HI_RO> result = srmPoRecruitArchivesHServer.findRecruitmentArchivesHeader(jsonParams);
			LOGGER.info("-->>参数：" + params + "查询成功！");
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功!", 1, result);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}
}
