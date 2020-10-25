package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceSupplyRatioEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceSupplyRatio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("/srmSpmPerformanceSupplyRatioService")
@Component
public class SrmSpmPerformanceSupplyRatioService extends CommonAbstractServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmPerformanceSupplyRatioService.class);

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	public SrmSpmPerformanceSupplyRatioService() {
		super();
	}

	@Autowired
	private ISrmSpmPerformanceSupplyRatio srmSpmPerformanceSupplyRatioServer;



    /**
     * 确认
     *
     * @param params（ids）
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("updatePerformanceSupplyRatio")
	public String updatePerformanceSupplyRatio(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmSpmPerformanceSupplyRatioServer.updatePerformanceSupplyRatio(jsonParams));
		}catch (UtilsException e){
            LOGGER.error("确认失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "确认失败:" + e.getMessage(), 0, null);
        }  catch (Exception e) {
			LOGGER.error("确认失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "确认失败：" + e.getMessage(), 0, null);
		}
	}


    /**
     * 导出绩效供货比例数据
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
	@Path("findPerformanceSupplyRatioExport")
	@POST
	public String findPerformanceSupplyRatioExport(@FormParam(PARAMS)
	  	String params, @FormParam(PAGE_INDEX)
	  	Integer pageIndex, @FormParam(PAGE_ROWS)
	  	Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			List<SrmSpmPerformanceSupplyRatioEntity_HI_RO> list = srmSpmPerformanceSupplyRatioServer.findPerformanceSupplyRatioExport(jsonParam);
			return CommonAbstractServices.convertResultJSONObj("S", "绩效供货比例数据导出成功", list.size(), list);
		} catch (UtilsException e){
            LOGGER.error("绩效供货比例数据导出异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "绩效供货比例数据导出异常:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			LOGGER.error("绩效供货比例数据导出异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "绩效供货比例数据导出异常!" + e, 0, null);
		}
	}



    /**
     * 绩效供货比例维护查询
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
	@Path("findPerformanceSupplyRatioList")
	@POST
	public String findPerformanceSupplyRatioList(@FormParam(PARAMS)
		String params, @FormParam(PAGE_INDEX)
		Integer pageIndex, @FormParam(PAGE_ROWS)
		Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			Pagination<SrmSpmPerformanceSupplyRatioEntity_HI_RO> infoList = srmSpmPerformanceSupplyRatioServer.findPerformanceSupplyRatioList(jsonParam, pageIndex, pageRows);
			return JSON.toJSONString(infoList);
		}catch (UtilsException e){
            LOGGER.error("绩效供货比例查询异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "绩效供货比例查询异常:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("绩效供货比例查询异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "绩效供货比例查询失败!" + e, 0, null);
		}
	}


    /**
     * 查询来源绩效方案版本
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
	@Path("findSourceSchemeNumber")
	@POST
	public String findSourceSchemeNumber(@FormParam(PARAMS)
	    String params, @FormParam(PAGE_INDEX)
	    Integer pageIndex, @FormParam(PAGE_ROWS)
	    Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			Pagination<SrmSpmPerformanceSupplyRatioEntity_HI_RO> deductionNumbers = srmSpmPerformanceSupplyRatioServer.findSourceSchemeNumber(jsonParam, pageIndex, pageRows);
			return JSONObject.toJSONString(deductionNumbers);
		} catch (UtilsException e){
            LOGGER.error("查询来源绩效方案版本失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询来源绩效方案版本失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			//e.printStackTrace();
			return CommonAbstractServices.convertResultJSONObj("E", "查询来源绩效方案版本失败!" + e, 0, null);
		}
	}


}

