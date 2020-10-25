package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceSchemeEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceScheme;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.util.List;



@Path("/srmSpmPerformanceSchemeService")
@Component
public class SrmSpmPerformanceSchemeService extends CommonAbstractServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmPerformanceSchemeService.class);

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	public SrmSpmPerformanceSchemeService() {
		super();
	}

	@Autowired
	private ISrmSpmPerformanceScheme srmSpmPerformanceSchemeServer;


    /**
     * 更新绩效方案数据表
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("updatePerformanceScheme")
	public String updatePerformanceScheme(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmSpmPerformanceSchemeServer.updatePerformanceScheme(jsonParams));
		}catch (UtilsException e){
            LOGGER.error("更新失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "更新失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			LOGGER.error("更新失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "更新失败：" + e.getMessage(), 0, null);
		}
	}



    /**
     * 绩效方案发布
     * @param params 传入schemeId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("updateSchemePublished")
	public String updateSchemePublished(@FormParam(PARAMS) String params) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			JSONObject json = srmSpmPerformanceSchemeServer.updateSchemePublished(paramJSON);
			return JSON.toJSONString(json);
		}catch (UtilsException e){
            LOGGER.error("方案发布失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "方案发布失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "方案发布失败!" + e.getMessage(), 0, null));
		}
	}


    /**
     * 绩效方案作废
     * @param params 传入schemeId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("updateSchemeCancel")
	public String updateSchemeCancel(@FormParam(PARAMS) String params) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			JSONObject json = srmSpmPerformanceSchemeServer.updateSchemeCancel(paramJSON);
			return JSON.toJSONString(json);
		}catch (UtilsException e){
            LOGGER.error("方案作废失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "方案作废失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "方案作废失败!" + e.getMessage(), 0, null));
		}
	}


    /**
     * 删除绩效方案数据（只有拟定状态允许删除）
     * @param params 传入Id
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("deleteScheme")
	public String deleteScheme(@FormParam(PARAMS) String params) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			JSONObject json = srmSpmPerformanceSchemeServer.deleteScheme(paramJSON);
			return JSON.toJSONString(json);
		}catch (UtilsException e){
            LOGGER.error("删除失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "删除失败!" + e.getMessage(), 0, null));
		}
	}


    /**
     * 导出记录例外数据
     *
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
	@Path("findExportScheme")
	@POST
	public String findExportScheme(@FormParam(PARAMS)
	   String params, @FormParam(PAGE_INDEX)
	   Integer pageIndex, @FormParam(PAGE_ROWS)
	   Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			List<SrmSpmPerformanceSchemeEntity_HI_RO> list = srmSpmPerformanceSchemeServer.findExportScheme(jsonParam);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功", list.size(), list);
		} catch (UtilsException e){
            LOGGER.error("查询异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询异常:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			LOGGER.error("查询异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "查询异常!" + e, 0, null);
		}
	}



    /**
     * 保存绩效例外
     *
     * @param params 表单信息
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("saveScheme")
	@POST
	public String saveScheme(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject json = srmSpmPerformanceSchemeServer.saveScheme(jsonParam);
			return CommonAbstractServices.convertResultJSONObj("S", json.getString("msg"), 1, json.get("data"));
		} catch (UtilsException e){
            LOGGER.error("保存信息异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "保存信息异常:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			LOGGER.error("保存信息异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "保存异常!" + e, 0, null);
		}
	}



    /**
     * 绩效例外管理列表查询
     *
     * @param params 传查询所需的查询条件
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("findSchemeInfoList")
	@POST
	public String findSchemeInfoList(@FormParam(PARAMS)
		String params, @FormParam(PAGE_INDEX)
		Integer pageIndex, @FormParam(PAGE_ROWS)
		Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			LOGGER.info("------jsonParam------" + jsonParam.toString());
			Pagination<SrmSpmPerformanceSchemeEntity_HI_RO> infoList = srmSpmPerformanceSchemeServer.findSchemeInfoList(jsonParam, pageIndex, pageRows);
			return JSON.toJSONString(infoList);
		}catch (UtilsException e){
            LOGGER.error("综合绩效方案列表异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "综合绩效方案列表异常:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("综合绩效方案列表异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "综合绩效方案列表失败!" + e, 0, null);
		}
	}


    /**
     * 应用绩效模板查询
     * @param params 传入状态：生效
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("findTplLov")
	@POST
	public String findTplLov(@FormParam(PARAMS)
	   String params, @FormParam(PAGE_INDEX)
	   Integer pageIndex, @FormParam(PAGE_ROWS)
	   Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			Pagination<SrmSpmPerformanceSchemeEntity_HI_RO> deductionNumbers = srmSpmPerformanceSchemeServer.findTplLov(jsonParam, pageIndex, pageRows);
			return JSONObject.toJSONString(deductionNumbers);
		}catch (UtilsException e){
            LOGGER.error("应用绩效模板查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "应用绩效模板查询失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			LOGGER.error("findTplLov-->应用绩效模板查询：" + e);
			//e.printStackTrace();
			return CommonAbstractServices.convertResultJSONObj("E", "应用绩效模板查询失败!" + e, 0, null);
		}
	}


    /**
     * 查询方案编号
     * @param params 传入方案Id
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("findSchemeNumber")
	@POST
	public String findSchemeNumber(@FormParam(PARAMS)
	  String params, @FormParam(PAGE_INDEX)
	  Integer pageIndex, @FormParam(PAGE_ROWS)
	  Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			Pagination<SrmSpmPerformanceSchemeEntity_HI_RO> deductionNumbers = srmSpmPerformanceSchemeServer.findSchemeNumber(jsonParam, pageIndex, pageRows);
			return JSONObject.toJSONString(deductionNumbers);
		}catch (UtilsException e){
            LOGGER.error("查询方案编号失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询方案编号失败:" + e.getMessage(), 0, null);
        }  catch (Exception e) {
			LOGGER.error("findSchemeNumber-->查询方案编号：" + e);
			//e.printStackTrace();
			return CommonAbstractServices.convertResultJSONObj("E", "查询方案编号失败!" + e, 0, null);
		}
	}


}

