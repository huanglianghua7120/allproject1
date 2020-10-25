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
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmRatioMappingsEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmRatioMappings;

import java.util.List;

@Path("/srmSpmRatioMappingsService")
@Component
public class SrmSpmRatioMappingsService extends CommonAbstractServices{

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmRatioMappingsService.class);

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	public SrmSpmRatioMappingsService() {
		super();
	}

	@Autowired
	private ISrmSpmRatioMappings srmSpmRatioMappingsServer;



    /**
     * 导入供货比例对照关系数据
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("saveRatioMappingsList")
	@POST
	public String saveRatioMappingsList(@FormParam(PARAMS) String params) {
		LOGGER.info("导入信息:"+params);
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject object = srmSpmRatioMappingsServer.saveRatioMappingsList(jsonParam);
			return object.toJSONString();
		}catch (UtilsException e){
            LOGGER.error("导入失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "导入失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			LOGGER.error("导入失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E",   e.getMessage(), 0, null);
		}
	}


    /**
     * 导出供货比例对照关系数据
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
	@Path("findRatioMappingsExport")
	@POST
	public String findRatioMappingsExport(@FormParam(PARAMS)
	  	String params, @FormParam(PAGE_INDEX)
	  	Integer pageIndex, @FormParam(PAGE_ROWS)
	  	Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			List<SrmSpmRatioMappingsEntity_HI_RO> list = srmSpmRatioMappingsServer.findRatioMappingsExport(jsonParam);
			return CommonAbstractServices.convertResultJSONObj("S", "导出成功", list.size(), list);
		} catch (UtilsException e){
            LOGGER.error("导出供货比例对照关系数据异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "导出供货比例对照关系数据异常:" + e.getMessage(), 0, null);
        }  catch (Exception e) {
			LOGGER.error("导出供货比例对照关系数据异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "导出异常!" + e, 0, null);
		}
	}

    /**
     * 删除供货比例对照关系数据
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
	@Path("deleteRatioMappings")
	public String deleteRatioMappings(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(srmSpmRatioMappingsServer.deleteRatioMappings(jsonParams));
		}catch (UtilsException e){
            LOGGER.error("删除供货比例对照关系数据失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "删除供货比例对照关系数据失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			LOGGER.error("删除供货比例对照关系数据失败" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "删除失败：" + e.getMessage(), 0, null);
		}
	}



    /**
     * 保存供货比例对照关系数据
     *
     * @param params 表单数据
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("saveRatioMappings")
	@POST
	public String saveRatioMappings(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject json = srmSpmRatioMappingsServer.saveRatioMappings(jsonParam);
			return CommonAbstractServices.convertResultJSONObj("S", json.getString("msg"), 1, json.get("data"));
		} catch (UtilsException e){
            LOGGER.error("保存供货比例对照关系数据异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "保存供货比例对照关系数据异常:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			LOGGER.error("保存供货比例对照关系数据异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "保存异常!" + e, 0, null);
		}
	}




    /**
     * 供货比例对照关系查询
     * @param params 查询条件(采购分类，分类名称)
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("findRatioMappingsList")
	@POST
	public String findRatioMappingsList(@FormParam(PARAMS)
	 	String params, @FormParam(PAGE_INDEX)
	 	Integer pageIndex, @FormParam(PAGE_ROWS)
	 	Integer pageRows) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			Pagination<SrmSpmRatioMappingsEntity_HI_RO> dataList = srmSpmRatioMappingsServer.findRatioMappingsList(jsonParam, pageIndex, pageRows);
			return JSON.toJSONString(dataList);
		} catch (UtilsException e){
            LOGGER.error("供货比例对照关系查询异常：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "供货比例对照关系查询异常:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("供货比例对照关系查询异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "供货比例对照关系查询失败!" + e, 0, null);
		}
	}

}

