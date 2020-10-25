package saaf.common.fmw.pos.services;

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
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.pos.model.inter.ISrmPosLocaleReviewCates;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：现场考察地点
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
@Component("srmPosLocaleReviewCatesService")
@Path("/srmPosLocaleReviewCatesService")
public class SrmPosLocaleReviewCatesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosLocaleReviewCatesService.class);
	@Autowired
	private ISrmPosLocaleReviewCates srmPosLocaleReviewCatesServer;
	public SrmPosLocaleReviewCatesService() {
		super();
	}

	/**
	 * 
	 * @param params
	 * @param curIndex
	 * @param pageSize
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSrmPosLocaleReviewCatesInfo")
	//	//srmPosLocaleReviewCatesService/findSrmPosLocaleReviewCatesInfo
	public String findSrmPosLocaleReviewCatesInfo(@FormParam("params")
        String params, @FormParam("pageIndex")
        @DefaultValue("1")
        Integer curIndex, @FormParam("pageRows")
        @DefaultValue("10")
        Integer pageSize) throws Exception {
		LOGGER.info(params);
		JSONObject paramJSON = this.parseObject(params);
		String resultStr = JSONObject.toJSONString(srmPosLocaleReviewCatesServer.findSrmPosLocaleReviewCatesInfo(paramJSON));
		LOGGER.info(resultStr);
		return resultStr;
	}
}
