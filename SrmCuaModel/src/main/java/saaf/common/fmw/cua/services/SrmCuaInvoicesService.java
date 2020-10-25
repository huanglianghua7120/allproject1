package saaf.common.fmw.cua.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.util.List;

import javax.ws.rs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.cua.model.entities.SrmCuaInvoicesEntity_HI;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaInvoicesEntity_HI_RO;
import saaf.common.fmw.cua.model.inter.ISrmCuaInvoices;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierBankEntity_HI_RO;
/**
 *@Author zhangxuan
 *@Date 2018/11/9
 *@Decription 发票Service
 **/
@Component("srmCuaInvoicesService")
@Path("/srmCuaInvoicesService")
public class SrmCuaInvoicesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmCuaInvoicesService.class);
	@Autowired
	private ISrmCuaInvoices srmCuaInvoicesServer;
	public SrmCuaInvoicesService() {
		super();
	}
	
	   /**
     * 查询发票
     *
     * @param params    查询过滤条件参数
     * @param pageIndex 页码索引
     * @param pageRows  每页显示记录数量
     * @return 返回分页查询的发票信息的json字符串
     */
    @Path("findInvoiceList")
    @POST
    public String findInvoiceList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
            @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmCuaInvoicesEntity_HI_RO> invoiceList = srmCuaInvoicesServer.findInvoiceList(jsonParam,pageIndex,pageRows);
            return JSON.toJSONString(invoiceList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("findInvoiceList-->查询失败：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
        }
    }
    
    
    /**
	 * 数据删除
	 * @param params 获得删除发票的id
	 * @return	
	 */
	@Path("deleteInvoice")
	@POST
	public String deleteInvoice(@FormParam("params") String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmCuaInvoicesServer.deleteInvoice(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("删除失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "删除失败!" + e, 0, null);
		}
	}
	
    /**
     * 通过id查询发票
     *
     * @param params 参数应该包含发票的id
     * @return 发票的json字符串
     */
    @POST
    @Path("findInvoiceById")
    public String findInvoiceById(@FormParam(PARAMS) String params) {

        try {
            JSONObject jsonParams = this.parseObject(params);
            SrmCuaInvoicesEntity_HI_RO invoice = srmCuaInvoicesServer.findInvoiceById(jsonParams);
            return convertResultJSONObj(SUCCESS_STATUS, "查询发票成功!", 0, invoice);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查询发票失败" + e, e);
            return convertResultJSONObj(ERROR_STATUS, "查询发票失败!" + e, 0, null);
        }
    }
    
    
    /**
     * 保存发票单信息
     * @param params	获得发票单信息
     * @param line	获得发票单行信息
     * @return
     */
    @Path("saveInvoice")
    @POST
    public String saveInvoice(@FormParam("params")
        String params) {
        try {
        	LOGGER.info(" saveInvoice --> params:"+ params);
            JSONObject jsonParam = this.parseObject(params);
            SrmCuaInvoicesEntity_HI invoice =srmCuaInvoicesServer.saveInvoice(jsonParam);
           if(invoice == null) {
        	   return CommonAbstractServices.convertResultJSONObj("E", "保存失败!", 1, null);
           }
           else {
        	   return convertResultJSONObj(SUCCESS_STATUS, "保存发票成功!", 0, invoice);
           }
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("保存发票异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", e.getMessage(), 0, null);
        }
    }
    
	/**
	 *银行查询
	 * @param params
	 * @param curIndex
	 * @param pageSize
	 * @return
	 */
	@POST
	@Path("findSupplierBankInfoToInvoice")
	//	//srmBaseBanksService/findSrmBaseBanksInfo
	public String findSupplierBankInfoToInvoice(@FormParam(PARAMS) String params) {
		LOGGER.info(params);
		try{
			JSONObject paramJSON = this.parseObject(params);
			List<SrmPosSupplierBankEntity_HI_RO> list=srmCuaInvoicesServer.findSupplierBankInfoToInvoice(paramJSON);
			return JSON.toJSONString(list);
		}catch (Exception e){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"查询失败，"+e.getMessage(),0,null);
		}
	}


}
