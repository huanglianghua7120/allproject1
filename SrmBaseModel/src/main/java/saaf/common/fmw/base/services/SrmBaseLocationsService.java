package saaf.common.fmw.base.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseLocationsEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseLocations;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：地址
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseLocationsService")
@Path("/srmBaseLocationsService")
public class SrmBaseLocationsService extends CommonAbstractServices {
private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseLocationsService.class);
	@Autowired
	private ISrmBaseLocations srmBaseLocationsServer;
	public SrmBaseLocationsService() {
		super();
	}
	/**
	 * 业务实体地址查询
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
	@Path("findSrmBaseLocationsInfo")
	public String findSrmBaseOrgLocationsInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows){
		try{
			JSONObject paramJSON = this.parseObject(params);
			Pagination<SrmBaseLocationsEntity_HI_RO> result = srmBaseLocationsServer.findSrmBaseLocationsInfo(paramJSON, pageIndex, pageRows);
			LOGGER.info("-->>参数：" + params + "查询成功！");
			return JSON.toJSONString(result);
		}catch (Exception e){
			 e.printStackTrace();
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e.getMessage(), 0, null));
		}
	}

	/**
	 * 业务实体地址查询
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
	@Path("findSrmBaseLocationsInfoByDefault")
	public String findSrmBaseLocationsInfoByDefault(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows){
		try{
			JSONObject paramJSON = this.parseObject(params);
			Map<String, Object> result = srmBaseLocationsServer.findSrmBaseLocationsInfoByDefault(paramJSON, pageIndex, pageRows);
			LOGGER.info("-->>参数：" + params + "查询成功！");
			return JSON.toJSONString(result);
		}catch (Exception e){
			 e.printStackTrace();
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return JSON.toJSONString(SToolUtils.convertResultJSONObj(ERROR_STATUS, "查询失败!" + e.getMessage(), 0, null));
		}
	}


    /**
     * Description：删除地点
     * @param
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      谢晓霞          创建
     * ==============================================================================
     */
    @Path("deleteBaseLocation")
    @POST
    public String deleteBaseLocation(@FormParam(PARAMS) String params) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            return JSON.toJSONString(srmBaseLocationsServer.deleteBaseLocation(jsonParams));
        } catch (UtilsException e){
            LOGGER.error(e.getMessage());
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
    }

    /**
     * Description：保存地点
     * @param
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019/6/4      谢晓霞          创建
     * ==============================================================================
     */
    @POST
    @Path("saveBaseLocation")
    public String saveBaseLocation(@FormParam(PARAMS) String params) {
        LOGGER.info("参数：" + params.toString());
        if (StringUtils.isBlank(params)) {
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "参数为空，不可操作！", 0, null);
        }
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmBaseLocationsServer.saveBaseLocation(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS), jsondata.getString(MSG), jsondata.getInteger(COUNT), jsondata.getString(DATA));
        } catch (Exception e) {
            LOGGER.error("--------------------------->操作失败！参数：" + params.toString() + "，异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "操作失败！", 0, null);
        }
    }



}
