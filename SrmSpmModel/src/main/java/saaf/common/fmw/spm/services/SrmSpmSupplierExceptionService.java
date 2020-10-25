package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmSupplierExceptionEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmSupplierException;

import java.util.List;

@Path("/srmSpmSupplierExceptionService")
@Component
public class SrmSpmSupplierExceptionService extends CommonAbstractServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmSupplierExceptionService.class);

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	public SrmSpmSupplierExceptionService() {
		super();
	}


	@Autowired
	private ISrmSpmSupplierException srmSpmSupplierExceptionServer;




    /**
     * 导入例外数据列表
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("saveExceptionList")
	@POST
	public String saveExceptionList(@FormParam(PARAMS) String params) {
		LOGGER.info("导入信息:"+params);
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject object = srmSpmSupplierExceptionServer.saveExceptionList(jsonParam);
			return object.toJSONString();
		} catch (UtilsException e){
            LOGGER.error("导入失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "导入失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			LOGGER.error("导入失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E",   e.getMessage(), 0, null);
		}
	}


    /**
     * 失效
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
	@Path("invalidException")
	public String invalidException(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmSpmSupplierExceptionServer.updateInvalidException(jsonParams));
		}catch (UtilsException e){
            LOGGER.error("失效失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "失效失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			LOGGER.error("失效失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "失效失败：" + e.getMessage(), 0, null);
		}
	}


    /**
     * 生效
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
	@Path("effectiveException")
	public String effectiveException(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmSpmSupplierExceptionServer.updateEffectiveException(jsonParams));
		}catch (UtilsException e){
            LOGGER.error("生效失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "生效失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			LOGGER.error("生效失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "生效失败：" + e.getMessage(), 0, null);
		}
	}


    /**
     * 删除绩效例外数据
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
	@Path("deleteException")
	public String deleteException(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmSpmSupplierExceptionServer.deleteException(jsonParams));
		}catch (UtilsException e){
            LOGGER.error("删除失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			LOGGER.error("删除失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "删除失败：" + e.getMessage(), 0, null);
		}
	}



    /**
     * 导出绩效例外数据
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
	@Path("findExceptionExport")
	@POST
	public String findExceptionExport(@FormParam(PARAMS)
	   String params, @FormParam(PAGE_INDEX)
	   Integer pageIndex, @FormParam(PAGE_ROWS)
	   Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			List<SrmSpmSupplierExceptionEntity_HI_RO> list = srmSpmSupplierExceptionServer.findExceptionExport(jsonParam);
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
	@Path("saveException")
	@POST
	public String saveException(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject json = srmSpmSupplierExceptionServer.saveException(jsonParam);
			return CommonAbstractServices.convertResultJSONObj("S", "保存成功!", 1, json.get("data"));
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
	@Path("findExceptionInfoList")
	@POST
	public String findExceptionInfoList(@FormParam(PARAMS)
		String params, @FormParam(PAGE_INDEX)
		Integer pageIndex, @FormParam(PAGE_ROWS)
		Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			LOGGER.info("------jsonParam------" + jsonParam.toString());
			Pagination<SrmSpmSupplierExceptionEntity_HI_RO> infoList = srmSpmSupplierExceptionServer.findExceptionInfoList(jsonParam, pageIndex, pageRows);
			return JSON.toJSONString(infoList);
		}catch (UtilsException e){
            LOGGER.error("绩效例外列表异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "绩效例外列表异常:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("绩效例外列表异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "查询绩效例外列表失败!" + e, 0, null);
		}
	}


}

