package saaf.common.fmw.cua.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.Map;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaInvoiceLinesEntity_HI_RO;
import saaf.common.fmw.cua.model.inter.ISrmCuaInvoiceLines;
import saaf.common.fmw.exception.NotLoginException;
/**
 *@Author zhangxuan
 *@Date 2018/11/9
 *@Decription 发票单行Service
 **/

@Component("srmCuaInvoiceLinesService")
@Path("/srmCuaInvoiceLinesService")
public class SrmCuaInvoiceLinesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmCuaInvoiceLinesService.class);
	@Autowired
	private ISrmCuaInvoiceLines srmCuaInvoiceLinesServer;
	public SrmCuaInvoiceLinesService() {
		super();
	}
	/**
	 * 查询发票中可以添加的对账单列表
	 * @param params	获得搜索条件
     * @param pageIndex 页码索引
     * @param pageRows  每页显示记录数量
	 * @return	返回对账单列表
	 */
    @POST
    @Path("findValidAccountLov")
    public String findValidAccountLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {

        try {
            LOGGER.info(" findValidAccountLov --> params:"+ params);
            JSONObject jsonParams = parseObject(params);
            Pagination<SrmCuaInvoiceLinesEntity_HI_RO> supplierPagination = srmCuaInvoiceLinesServer.findValidAccountLov(jsonParams, pageIndex, pageRows);
            return JSON.toJSONString(supplierPagination);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询发票对账单失败" + e, e);
            return convertResultJSONObj(ERROR_STATUS, "查询发票对账单失败!" + e, 0, null);
        }
    }
    
	/**
	 * 查询发票单行信息
	 * @param params	获得搜索条件
     * @param pageIndex 页码索引
     * @param pageRows  每页显示记录数量
	 * @return	返回对账单据信息
	 */
	@POST
	@Path("findInvoiceLineList")
	public String findInvoiceLineList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
            @FormParam(PAGE_ROWS) Integer pageRows) {	
	     try {
	            JSONObject jsonParam = this.parseObject(params);
	            Pagination<SrmCuaInvoiceLinesEntity_HI_RO> lineList = srmCuaInvoiceLinesServer.findInvoiceLineList(jsonParam,pageIndex,pageRows);
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
  * 保存发票单行信息
  * @param params	获得发票单行信息
  * @param line	获得发票单行信息
  * @return
  */
 @Path("saveInvoiceLine")
 @POST
 public String saveInvoiceLine(@FormParam("params")
     String params,@FormParam("line")
 String line) {
     try {
     	
         JSONObject jsonParam = this.parseObject(params);
         LOGGER.info(" saveAccountLine --> params:"+ params);
         Map<String,Object> map = srmCuaInvoiceLinesServer.saveInvoiceLine(jsonParam, line);
         if(map.get("status").equals("S")) {
         	map = srmCuaInvoiceLinesServer.saveInvoiceLineAmount(jsonParam);
         	return convertResultJSONObj(SUCCESS_STATUS, "保存发票单行成功!", 0, map);
         }
         else {
     	   return CommonAbstractServices.convertResultJSONObj("E", (String)map.get("msg"), 1, null);
        }
     } catch (NotLoginException e) {
         return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
     } catch (Exception e) {
         //e.printStackTrace();
         LOGGER.error("保存发票单行异常：" + e);
         return CommonAbstractServices.convertResultJSONObj("E", e.getMessage(), 0, null);
     }
 }
 
	/**
	 * 发票单行删除
	 * @param params	获得删除id
	 * @return	返回状态
	 */
	@Path("deleteInvoiceLine")
	@POST
	public String deleteInvoiceLine(@FormParam("params") String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			srmCuaInvoiceLinesServer.deleteInvoiceLine(jsonParams);
			 Map<String,Object> map = srmCuaInvoiceLinesServer.saveInvoiceLineAmount(jsonParams);
			 return convertResultJSONObj(SUCCESS_STATUS, "删除发票单行成功!", 0, map);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("删除失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "删除失败!" + e, 0, null);
		}
	}
    

}
