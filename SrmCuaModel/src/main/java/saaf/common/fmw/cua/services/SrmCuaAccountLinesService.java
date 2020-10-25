package saaf.common.fmw.cua.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.util.Map;

import javax.ws.rs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaAccountLinesEntity_HI_RO;
import saaf.common.fmw.cua.model.entities.readonly.SrmErpTransactionsVEntity_HI_RO;
import saaf.common.fmw.cua.model.inter.ISrmCuaAccountLines;
import saaf.common.fmw.exception.NotLoginException;

/**
 * @author zhangxuan
 * @date 2018/10/30
 */
@Component("srmCuaAccountLinesService")
@Path("/srmCuaAccountLinesService")
public class SrmCuaAccountLinesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmCuaAccountLinesService.class);
	@Autowired
	private ISrmCuaAccountLines srmCuaAccountLinesServer;
	public SrmCuaAccountLinesService() {
		super();
	}

	/**
	 * 查询视图中的对账单据信息
	 * @param params	获得搜索条件
     * @param pageIndex 页码索引
     * @param pageRows  每页显示记录数量
	 * @return	返回对账单据信息
	 */
	@POST
	@Path("findViewList")
	//	/restServer/srmCuaAccountLinesService/findSrmCuaAccountLinesInfo
	public String findViewList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
            @FormParam(PAGE_ROWS) Integer pageRows) {	
	     try {
	            JSONObject jsonParam = this.parseObject(params);
	            Pagination<SrmErpTransactionsVEntity_HI_RO> viewList = srmCuaAccountLinesServer.findViewList(jsonParam,pageIndex,pageRows);
	            LOGGER.info(JSON.toJSONString(viewList));
	            return JSON.toJSONString(viewList);
	        } catch (NotLoginException e) {
	            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
	        } catch (Exception e) {
	            LOGGER.error("findViewList-->查询失败：" + e);
	            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
	        }
		
		
	}

	/**
	 * 查询对账单行信息
	 * @param params	获得搜索条件
     * @param pageIndex 页码索引
     * @param pageRows  每页显示记录数量
	 * @return	返回对账单据信息
	 */
	@POST
	@Path("findAccountLineForExcel")
	//	/restServer/srmCuaAccountLinesService/findSrmCuaAccountLinesInfo
	public String findAccountLineForExcel(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
            @FormParam(PAGE_ROWS) Integer pageRows) {	
	     try {
	            JSONObject jsonParam = this.parseObject(params);
	            Pagination<SrmCuaAccountLinesEntity_HI_RO> lineList = srmCuaAccountLinesServer.findAccountLineForExcel(jsonParam,pageIndex,pageRows);
	            LOGGER.info(JSON.toJSONString(lineList));
	            return JSON.toJSONString(lineList);
	        } catch (NotLoginException e) {
	            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
	        } catch (Exception e) {
	            LOGGER.error("findAccountLineList-->查询失败：" + e);
	            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
	        }
		
		
	}
	/**
	 * 对账单行删除
	 * @param params	获得删除id
	 * @return	返回状态
	 */
	@Path("deleteAccountLine")
	@POST
	public String deleteAccountLine(@FormParam("params") String params,@FormParam("line")
    String line) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			srmCuaAccountLinesServer.deleteAccountLine(jsonParams);
			 Map<String,Object> map = srmCuaAccountLinesServer.saveAccountLineAmount(jsonParams, line, false,false);
			 return convertResultJSONObj(SUCCESS_STATUS, "删除对账单行成功!", 0, map);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("删除失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "删除失败!" + e, 0, null);
		}
	}
	/**
	 * 对账单行删除
	 * @param params	获得删除id
	 * @return	返回状态
	 */
	@Path("saveAllAccountLine")
	@POST
	public String saveAllAccountLine(@FormParam("params") String params,@FormParam("line")
    String line) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			srmCuaAccountLinesServer.saveAllAccountLine(jsonParams);
			Map<String,Object> map = srmCuaAccountLinesServer.saveAccountLineAmount(jsonParams, line, true, false);
			 return convertResultJSONObj(SUCCESS_STATUS, "保存对账单行成功!", 0, map);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("保存失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "保存失败!" + e, 0, null);
		}
	}
	   /**
     * 保存对账单行信息
     * @param params	获得对账单行信息
     * @param line	获得对账单行信息
     * @return
     */
    @Path("saveAccountLine")
    @POST
    public String saveAccountLine(@FormParam("params")
        String params,@FormParam("line")
    String line) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info(" saveAccountLine --> params:"+ params);
            srmCuaAccountLinesServer.saveAccountLine(jsonParam, line);
            Map<String,Object>	map = srmCuaAccountLinesServer.saveAccountLineAmount(jsonParam, line, false,true);
            return convertResultJSONObj(SUCCESS_STATUS, "保存对账单行成功!", 0, map);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("保存对账单行异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", e.getMessage(), 0, null);
        }
    }
    
	
	   /**
  * 查询对账单行信息
  * @param params	获得对账单行信息
  * @param line	获得对账单行信息
  * @return
  */
 @Path("findAccountLineList")
 @POST
 public String findAccountLineList(@FormParam("params")
     String params,@FormParam(PAGE_INDEX) Integer pageIndex,
     @FormParam(PAGE_ROWS) Integer pageRows) {
     try {
     	
         JSONObject jsonParam = this.parseObject(params);
         LOGGER.info(" findAccountLineList --> params:"+ params);
//         if(null != jsonParam.getString("lineList")) {
//        	 srmCuaAccountLinesServer.saveAccountLine(jsonParam, jsonParam.getString("lineList"));
//         }
         Pagination<SrmCuaAccountLinesEntity_HI_RO> pagination = srmCuaAccountLinesServer.findAccountLineList(jsonParam, pageIndex, pageRows);
         if(null != pagination) {
        	 return JSON.toJSONString(pagination);
         }
         else {
     	   return CommonAbstractServices.convertResultJSONObj("E", "查询失败", 1, null);
        }
     } catch (NotLoginException e) {
         return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
     } catch (Exception e) {
         //e.printStackTrace();
         LOGGER.error("查询对账单行异常：" + e);
         return CommonAbstractServices.convertResultJSONObj("E", e.getMessage(), 0, null);
     }
 }


	
}
