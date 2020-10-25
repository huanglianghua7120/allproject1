package saaf.common.fmw.cua.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafInstitutionEntity_HI_RO;
import saaf.common.fmw.cua.model.entities.SrmCuaAccountsEntity_HI;
import saaf.common.fmw.cua.model.entities.readonly.SrmCuaAccountsEntity_HI_RO;
import saaf.common.fmw.cua.model.inter.ISrmCuaAccounts;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierInfoEntity_HI_RO;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Map;

/**
 * @author zhangxuan
 * @date 2018/10/30
 */
@Component("srmCuaAccountsService")
@Path("/srmCuaAccountsService")
public class SrmCuaAccountsService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmCuaAccountsService.class);
	@Autowired
	private ISrmCuaAccounts srmCuaAccountsServer;

	public SrmCuaAccountsService() {
		super();
	}


	
    /**
     * 查询对账单
     *
     * @param params    查询过滤条件参数
     * @param pageIndex 页码索引
     * @param pageRows  每页显示记录数量
     * @return 返回分页查询的对账单信息的json字符串
     */
    @Path("findAccountList")
    @POST
    public String findAccountsList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
            @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmCuaAccountsEntity_HI_RO> accountsList = srmCuaAccountsServer.findAccountList(jsonParam,pageIndex,pageRows);
            return JSON.toJSONString(accountsList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("findForecastDetailInfoList-->查询失败：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
        }
    }
    
	/**
	 * 查询当前用户负责的业务实体lov
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 */
	@POST
	@Path("findAccountInstitutionLov")
	public String findInstitutionLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
									 @FormParam(PAGE_ROWS) Integer pageRows){
		try {
			JSONObject jsonParam = this.parseObject(params);
			Pagination<SaafInstitutionEntity_HI_RO> institutionLov = srmCuaAccountsServer.findAccountInstitutionLov(jsonParam, pageIndex, pageRows);
			return JSONObject.toJSONString(institutionLov);
		}catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询业务实体LOV失败！" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "查询业务实体LOV失败!" + e, 0, null);
		}
	}
	
    /**
     * 查询对账单接收退货报表
     *
     * @param params    查询过滤条件参数
     * @param pageIndex 页码索引
     * @param pageRows  每页显示记录数量
     * @return 返回分页查询的对账单信息的json字符串
     */
    @Path("findAccountReport")
    @POST
    public String findAccountReport(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
            @FormParam(PAGE_ROWS) Integer pageRows) {
        try {
            JSONObject jsonParam = this.parseObject(params);
            Pagination<SrmCuaAccountsEntity_HI_RO> accountsList = srmCuaAccountsServer.findAccountReport(jsonParam,pageIndex,pageRows);
            return JSON.toJSONString(accountsList);
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            LOGGER.error("findAccountReport-->查询失败：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败!", 0, null);
        }
    }
	/**
	 * 数据删除
	 * @param params 获得删除的id
	 * @return	
	 */
	@Path("deleteAccount")
	@POST
	public String deleteAccount(@FormParam("params") String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmCuaAccountsServer.deleteAccount(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("删除失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "删除失败!,状态已改变", 0, null);
		}
	}
	/**
	 * 更新对账单状态
	 * @param params	获得对账单状态
	 * @param line	获得对账单行的信息
	 * @return
	 */
		@Path("updateAccountStatus")
		@POST
		public String updateAccountStatus(@FormParam("params") String params,@FormParam("line")  String line) {
	    	LOGGER.info(" updateAccountStatus line:"+line);
			try {
				JSONObject jsonParams = this.parseObject(params);
				JSONObject jsondata = srmCuaAccountsServer.updateAccountStatus(jsonParams,line);
				return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
			} catch (Exception e) {
				//e.printStackTrace();
				LOGGER.error("删除失败！参数：" + params + ",异常：" + e);
				return CommonAbstractServices.convertResultJSONObj("E", "删除失败!" + e, 0, null);
			}
		}
		/**
		 * 更新对账单状态
		 * @param params	获得对账单状态
		 * @return
		 */
			@Path("findAccountStatus")
			@POST
			public String findAccountStatus(@FormParam("params") String params) {
		    	LOGGER.info(" findAccountStatus params:"+params);
				try {
					JSONObject jsonParams = this.parseObject(params);
					JSONObject jsondata = srmCuaAccountsServer.findAccountStatus(jsonParams);
					return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
				} catch (Exception e) {
					//e.printStackTrace();
					LOGGER.error("删除失败！参数：" + params + ",异常：" + e);
					return CommonAbstractServices.convertResultJSONObj("E", "删除失败!" + e, 0, null);
				}
			}
	/**
	 * 查询对账单详情信息
	 * @param params	获得对账单id
	 * @return	返回对账单详细信息
	 */
	@Path("findAccountInfo")
    @POST
    public String findAccountInfo(@FormParam("params")String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			Integer accountId = jsonParam.getInteger("accountId");
			if(accountId!=null) {
			   Map<String,Object> accountInfo =srmCuaAccountsServer.findAccountInfo(jsonParam);
			   jsonParam.put("accountInfo", accountInfo);
		       jsonParam.put("status", "S");
		       jsonParam.put("msg", "查询成功");
		       return jsonParam.toJSONString();
			}else {
				 return convertResultJSONObj("E", "请检查accountId参数", 0, null);
			}
		}catch (Exception e) {
            LOGGER.error("查询失败！", e);
            return convertResultJSONObj("E", "查询失败!" + e, 0, null);
        }
	}
	
    /**
     * 保存对账单信息
     * @param params	获得对账单信息
     * @param line	获得对账单行信息
     * @return
     */
    @Path("saveAccount")
    @POST
    public String saveAccount(@FormParam("params")
        String params,@FormParam("line")
    String line) {
        try {
        	
        	LOGGER.info(" saveAccount --> line:"+line);
            JSONObject jsonParam = this.parseObject(params);
            LOGGER.info(" saveAccount --> jsonParam:"+jsonParam);
            SrmCuaAccountsEntity_HI accountInfo =srmCuaAccountsServer.saveAccount(jsonParam, line);
//             	   jsonParam.put("accountInfo", accountInfo);
//     		       jsonParam.put("status", "S");
//     		       jsonParam.put("msg", "保存成功");
           if(accountInfo == null) {
        	   return CommonAbstractServices.convertResultJSONObj("E", "保存失败!", 1, null);
           }
           else {
        	   	return convertResultJSONObj(SUCCESS_STATUS, "保存对账单成功!", 0, accountInfo);
           }
        } catch (NotLoginException e) {
            return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("保存对账单异常：" + e);
            return CommonAbstractServices.convertResultJSONObj("E", e.getMessage(), 0, null);
        }
    }


	/**
	 * 查询没有退出的供应商信息
	 *
	 * @param params    查询参数
	 * @param pageIndex 页码索引
	 * @param pageRows  每页记录数量
	 * @return 返回查询到的供应商信息
	 */
	@POST
	@Path("findValidSupplierLov")
	public String findValidSupplierLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex, @FormParam(PAGE_ROWS) Integer pageRows) {

		try {
			JSONObject jsonParams = parseObject(params);
			Pagination<SrmCuaAccountsEntity_HI_RO> supplierPagination = srmCuaAccountsServer.findValidSupplierLov(jsonParams, pageIndex, pageRows);
			return JSON.toJSONString(supplierPagination);
		} catch (Exception e) {
			//e.printStackTrace();
			if (e instanceof NotLoginException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return convertResultJSONObj(ERROR_STATUS, "查询供应商失败!" + e, 0, null);
		}
	}

	/**
	 * 查询状态为"合格，潜在供应商"的供应商
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 */
	@POST
	@Path("findSupplierLov")
	public String findSupplierLov(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,
								  @FormParam(PAGE_ROWS) Integer pageRows){
		try {
			JSONObject jsonParam = this.parseObject(params);
			Pagination<SrmPosSupplierInfoEntity_HI_RO> supplierlov = srmCuaAccountsServer.findSupplierLov(jsonParam, pageIndex, pageRows);
			return JSONObject.toJSONString(supplierlov);
		}catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("查询供应商LOV失败！" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "查询供应商LOV失败!" + e, 0, null);
		}
	}
}
