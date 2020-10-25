package saaf.common.fmw.pos.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

import com.base.adf.common.utils.SToolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosQualificationCatesEntity_HI_RO;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.pos.model.inter.ISrmPosQualificationCates;

import java.util.List;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商资质
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
@Component("srmPosQualificationCatesService")
@Path("/srmPosQualificationCatesService")
public class SrmPosQualificationCatesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosQualificationCatesService.class);
	@Autowired
	private ISrmPosQualificationCates srmPosQualificationCatesServer;
	public SrmPosQualificationCatesService() {
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
	@Path("findSrmPosQualificationCatesInfo")
	@Consumes("application/json")
	@Produces("application/json")
	//	//srmPosQualificationCatesService/findSrmPosQualificationCatesInfo
	public String findSrmPosQualificationCatesInfo(@FormParam("params")
        String params, @FormParam("pageIndex")
        @DefaultValue("1")
        Integer curIndex, @FormParam("pageRows")
        @DefaultValue("10")
        Integer pageSize) {
		LOGGER.info(params);
		return "";
	}

	/**
	 * 根据资质审查ID和是否新增标识，查询资质审查单下面供应商品类
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findQualificationCategoryByAddFlag")
	public String findQualificationCategoryByAddFlag(@FormParam(PARAMS) String params){
		LOGGER.info(params);
		try {
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPosQualificationCatesEntity_HI_RO> rowSet = srmPosQualificationCatesServer.findQualificationCategoryByAddFlag(jsonParams);
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("S", "查询成功!", 0, rowSet));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

	/**
	 * 删除
	 *
	 * @param params
	 * @return ==============================================================================
	 * Version    Date           Updated By     Description
	 * -------    -----------    -----------    ---------------
	 * V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    @Path("deleteQualificationCategory")
    @POST
    public String deleteQualificationCategory(@FormParam("params")
        String params) {
    	LOGGER.info("删除信息，参数：" + params.toString());
    	try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmPosQualificationCatesServer.deleteQualificationCategory(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
        } catch (Exception e) {
            LOGGER.error("--->>删除失败！参数：" + params + ",异常：" + e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败!", 0, null);
        }
    }

}
