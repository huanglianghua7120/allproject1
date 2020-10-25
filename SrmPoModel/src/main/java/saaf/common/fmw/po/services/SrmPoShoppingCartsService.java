package saaf.common.fmw.po.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.StringUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.po.model.entities.readonly.SrmPoShoppingCartsEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoShoppingCarts;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
/**
 * Project Name：SrmPoShoppingCartsService
 * Company Name：SIE
 * Program Name：
 * Description：购物车
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoShoppingCartsService")
@Path("/srmPoShoppingCartsService")
public class SrmPoShoppingCartsService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoShoppingCartsService.class);
	@Autowired
	private ISrmPoShoppingCarts iSrmPoShoppingCarts;
	public SrmPoShoppingCartsService() {
		super();
	}

    /**
     * Description：购物车查询list（分页）
     * @param params 查询条件参数
     * @param curIndex 页码
     * @param pageSize 页行数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("findSrmPoShoppingCartsInfoList")
	@Produces("application/json")
	public String findSrmPoShoppingCartsInfoList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1") Integer curIndex, @FormParam(PAGE_ROWS) @DefaultValue("15") Integer pageSize) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SrmPoShoppingCartsEntity_HI_RO> result = iSrmPoShoppingCarts.findSrmPoShoppingCartsInfoList(jsonParams,curIndex,pageSize);
			return JSON.toJSONString(result);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}
    /**
     * Description：批量删除购物车
     * @param params 删除条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("deleteShoppingCartByBatch")
	public String deleteShoppingCartByBatch(@FormParam(PARAMS) String params) {
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try {
			JSONObject jsonParams = this.parseObject(params);
			return JSON.toJSONString(iSrmPoShoppingCarts.deleteShoppingCartByBatch(jsonParams));
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}

    /**
     * Description：保存购物车list
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("saveShoppingCartsList")
	@Produces("application/json")
	public String saveShoppingCartsList(@FormParam(PARAMS) String params) {
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPoShoppingCarts.saveShoppingCartsList(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e){
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}

    /**
     * Description：加入购物车的保存（单条数据）
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("saveShoppingCart")
	@Produces("application/json")
	public String saveShoppingCart(@FormParam(PARAMS) String params) {
		LOGGER.info("参数："+params.toString());
		if(StringUtils.isBlank(params)){
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"参数为空，不可操作！",0,null);
		}
		try{
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPoShoppingCarts.saveShoppingCart(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e){
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}
}
