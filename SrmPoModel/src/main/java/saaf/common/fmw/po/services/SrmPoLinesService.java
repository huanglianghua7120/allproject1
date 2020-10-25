package saaf.common.fmw.po.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLinesEntity_HI_RO;
import saaf.common.fmw.po.model.inter.ISrmPoLines;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.*;
/**
 * Project Name：SrmPoLinesService
 * Company Name：SIE
 * Program Name：
 * Description：采购订单行
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoLinesService")
@Path("/srmPoLinesService")
public class SrmPoLinesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoLinesService.class);
	@Autowired
	private ISrmPoLines iSrmPoLines;
	public SrmPoLinesService() {
		super();
	}

    /**
     * Description：目录采购查询list（分页）
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
	@Path("findSrmPoLinesInfoList")
	@Produces("application/json")
	public String findSrmPoLinesInfoList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) @DefaultValue("1") Integer curIndex, @FormParam(PAGE_ROWS) @DefaultValue("18") Integer pageSize) {
        try {
            JSONObject jsonParams = this.parseObject(params);
            Pagination<SrmPoLinesEntity_HI_RO> result = iSrmPoLines.findSrmPoLinesInfoList(jsonParams,curIndex,pageSize);
            return JSON.toJSONString(result);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
        }
	}
    /**
     * Description：订单确认（确认APPROVED）——内部，所有订单行的反馈状态改为反馈审核通过——系统代办通知
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("updatePoLineStatus")
	public String updatePoLineStatus(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPoLines.updatePoLineStatus(jsonParams);
			return convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.get(DATA));
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}
    /**
     * Description：订单反馈（确认CONFIRMED）——供应商，订单行的反馈状态改为已确认CONFIRMED，反馈内容改为确认CONFIRM——系统代办通知
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("updatePoLineFeedBackStatus")
	public String updatePoLineFeedBackStatus(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPoLines.updatePoLineFeedBackStatus(jsonParams);
			return convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.get(DATA));
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}

    /**
     * Description：拆分订单行
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("saveSplitPoLine")
	public String saveSplitPoLine(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsonData = iSrmPoLines.saveSplitPoLine(jsonParams);
			return convertResultJSONObj(jsonData.getString(STATUS),jsonData.getString(MSG),jsonData.getInteger(COUNT),jsonData.get(DATA));
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}

    /**
     * Description：取消订单行
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("saveCancelPoLine")
	public String saveCancelPoLine(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsonData = iSrmPoLines.saveCancelPoLine(jsonParams);
			return convertResultJSONObj(jsonData.getString(STATUS),jsonData.getString(MSG),jsonData.getInteger(COUNT),jsonData.get(DATA));
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}

    /**
     * Description：结算订单行
     * @param params
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("saveSettlePoLine")
	public String saveSettlePoLine(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsonData = iSrmPoLines.saveSettlePoLine(jsonParams);
			return convertResultJSONObj(jsonData.getString(STATUS),jsonData.getString(MSG),jsonData.getInteger(COUNT),jsonData.get(DATA));
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}

}
