package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.SrmSpmIndicatorItemsEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorItemsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmManualScoreEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmManualScore;

@Component("srmSpmManualScoreService")
@Path("/srmSpmManualScoreService")
public class SrmSpmManualScoreService extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmManualScoreService.class);
	private static final String PARAMS = "params";
	private static final String PAGE_INDEX = "pageIndex";
	private static final String PAGE_ROWS = "pageRows";
	@Autowired
	private ISrmSpmManualScore srmSpmManualScoreServer;

	public SrmSpmManualScoreService() {
		super();
	}

    /**
     * Description：查询加减分
     * @param params 加减分查询参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("findManualInfoList")
	public String findManualInfoList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,@FormParam(PAGE_ROWS) Integer pageRows) {
		try {
			JSONObject paramJSON = parseObject(params);
			Pagination<SrmSpmManualScoreEntity_HI_RO> page = srmSpmManualScoreServer.findManualInfoList(paramJSON,pageIndex, pageRows);
			return JSON.toJSONString(page);
		} catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}
    /**
     * Description：删除加减分
     * @param params
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("deleteManualScore")
	public String deleteManualScore(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmSpmManualScoreServer.deleteManualScore(jsonParams));
		}catch (UtilsException e){
            LOGGER.error("删除失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			LOGGER.error("删除失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "删除失败：" + e.getMessage(), 0, null);
		}
	}


    /**
     * Description：保存加减分
     * @param params 加减分参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("saveManualScore")
	public String saveManualScore(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmSpmManualScoreServer.saveManualScore(jsonParams));
		}catch (UtilsException e){
            LOGGER.error("指标录入项保存失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "指标录入项保存失败:" + e.getMessage(), 0, null);
        }  catch (Exception e) {
			LOGGER.error("指标录入项保存失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "指标录入项保存失败：" + e.getMessage(), 0, null);
		}
	}
    /**
     * Description：生效加减分
     * @param params
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("effectiveManualScore")
	public String effectiveManualScore(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmSpmManualScoreServer.updateEffectiveManualScore(jsonParams));
		} catch (UtilsException e){
            LOGGER.error("指标录入项生效失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "指标录入项生效失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			LOGGER.error("指标录入项生效失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "指标录入项生效失败：" + e.getMessage(), 0, null);
		}
	}

    /**
     * Description：指标录入项失效
     * @param params
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("invalidManualScore")
	public String invalidManualScore(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmSpmManualScoreServer.updateInvalidManualScore(jsonParams));
		}catch (UtilsException e){
            LOGGER.error("指标录入项失效失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "指标录入项失效失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			LOGGER.error("指标录入项失效失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "指标录入项失效失败：" + e.getMessage(), 0, null);
		}
	}
    /**
     * Description：指标录入项导出
     * @param params
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("exportManualScore")
	public String exportManualScore(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			List<SrmSpmManualScoreEntity_HI_RO> list = srmSpmManualScoreServer.exportManualScore(jsonParam);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功", list.size(), list);
		} catch (UtilsException e){
            LOGGER.error("指标录入项导出失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "指标录入项导出失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			LOGGER.error("指标录入项导出失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "指标录入项导出失败：" + e.getMessage(), 0, null);
		}
	}

    /**
     * Description：查询绩效类型
     * @param params
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("selectindicatorType")
	public String selectindicatorType(@FormParam("params")String params) throws Exception{
		LOGGER.info(params);
		JSONObject jsonParam = JSON.parseObject(params);
		List<SaafLookupValuesEntity_HI_RO> list=srmSpmManualScoreServer.selectindicatorType(jsonParam);
		jsonParam.put("LookupValues", list);
	       jsonParam.put("status", "S");
	       jsonParam.put("msg", "查询成功");
		return jsonParam.toJSONString();
	}
    /**
     * Description：查询绩效类型
     * @param params
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("findManualIndicatorType")
	public String findManualIndicatorType(@FormParam("params")String params) throws Exception{
		LOGGER.info(params);
		JSONObject jsonParam = JSON.parseObject(params);
		Map<String,Object> list=srmSpmManualScoreServer.findManualIndicatorType(jsonParam);
		jsonParam.put("LookupValues", list);
	       jsonParam.put("status", "S");
	       jsonParam.put("msg", "查询成功");
		return jsonParam.toJSONString();
	}
	@POST
	@Path("findManualIndicatorItems")
	public String findManualIndicatorItems(@FormParam("params")String params) throws Exception{
		LOGGER.info(params);
		JSONObject jsonParam = JSON.parseObject(params);
		SrmSpmIndicatorItemsEntity_HI list=srmSpmManualScoreServer.findManualIndicatorItems(jsonParam);
		jsonParam.put("LookupValues", list);
	       jsonParam.put("status", "S");
	       jsonParam.put("msg", "查询成功");
		return jsonParam.toJSONString();
	}
    /**
     * Description：导入
     * @param params
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("saveManualImport")
	public String saveManualImport(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = parseObject(params);
			JSONObject object = this.srmSpmManualScoreServer.saveManualImport(jsonParam);
			return object.toJSONString();
		}catch (UtilsException e){
            LOGGER.error("导入失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "导入失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "导入失败!" + e, 0L, null));
		}
	}
}
