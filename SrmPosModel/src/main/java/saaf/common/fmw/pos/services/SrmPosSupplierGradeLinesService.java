package saaf.common.fmw.pos.services;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierGradeLinesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierGradeLines;
import saaf.common.fmw.services.CommonAbstractServices;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商等级行
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Path("/srmPosSupplierGradeLinesService")
@Component
public class SrmPosSupplierGradeLinesService extends CommonAbstractServices {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SrmPosSupplierGradeService.class);

	@Autowired
	private ISrmPosSupplierGradeLines srmPosSupplierGradeLinesServer;

	public SrmPosSupplierGradeLinesService() {
		super();
	}
	
	/**
	 * 查询供应商级别行列表
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSupplierGradeLinesList")
	public String findSupplierGradeLinesList(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmPosSupplierGradeLinesServer.findSupplierGradeLinesList(jsonParams));
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}
	
	
	
	/**
	 * 删除供应商级别行信息
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("deleteSupplierGradeLines")
	@POST
	public String deleteSupplierGradeLines(@FormParam("params") String params) {
		LOGGER.info("删除信息,参数：" + params.toString());
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmPosSupplierGradeLinesServer.deleteSupplierGradeLines(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(
					jsondata.getString("status"), jsondata.getString("msg"),
					jsondata.getInteger("count"), jsondata.get("data"));
		} catch (Exception e) {
			LOGGER.error("--->>删除失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "删除失败!", 0,
					null);
		}
	}
	
	/**
	 * 查询导出级别行数据
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSupplierGradeLinesExport")
	public String findSupplierGradeLinesExport(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			List<SrmPosSupplierGradeLinesEntity_HI_RO> list = srmPosSupplierGradeLinesServer.findSupplierGradeLinesExport(jsonParams);
			return CommonAbstractServices.convertResultJSONObj("S", "导出成功", list.size(), list);
		}  catch (Exception e) {
			LOGGER.error("导出级别行数据异常：" + e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "导出异常!" + e, 0, null);
		}
	}
	
	/**
	 * 导入级别行信息
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("saveSupplierGradeLinesImport")
    @POST
    public String saveSupplierGradeLinesImport(@FormParam("params")
        String params) {
    	LOGGER.info("导入信息:"+params);
        try {
        	JSONObject jsonParam = this.parseObject(params);
            JSONObject object = srmPosSupplierGradeLinesServer.saveSupplierGradeLinesImport(jsonParam);
            return object.toJSONString();
        } catch (Exception e) {
            LOGGER.error("导入失败" + e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
            return CommonAbstractServices.convertResultJSONObj("E",   e.getMessage(), 0, null);
        }
    }
	
	/**
	 * 查询历史级别行数据
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSupplierHistoryGradeLines")
	public String findSupplierHistoryGradeLines(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmPosSupplierGradeLinesServer.findSupplierHistoryGradeLines(jsonParams));
		} catch (Exception e) {
			LOGGER.error("查询失败" + e.getMessage());
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "操作失败：" + e.getMessage(), 0, null);
		}
	}
}
