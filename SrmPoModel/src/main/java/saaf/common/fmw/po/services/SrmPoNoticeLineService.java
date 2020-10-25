package saaf.common.fmw.po.services;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.po.model.inter.ISrmPoNoticeLine;
import saaf.common.fmw.services.CommonAbstractServices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
/**
 * Project Name：SrmPoNoticeLineService
 * Company Name：SIE
 * Program Name：
 * Description：送货通知
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-06-18     SIE 谢晓霞       创建
 * ===========================================================================
 */
@Component("srmPoNoticeLineService")
@Path("/srmPoNoticeLineService")
public class SrmPoNoticeLineService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoNoticeLineService.class);
	@Autowired
	private ISrmPoNoticeLine iSrmPoNoticeLine;
	public SrmPoNoticeLineService() {
		super();
	}
    /**
     * Description：送货通知确认（确认APPROVED），所有订单行的状态改为反馈审核通过——系统代办通知
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("updatePoNoticeLineStatus")
	public String updatePoLineStatus(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPoNoticeLine.updatePoNoticeLineStatus(jsonParams);
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
     * Description：送货通知反馈（确认CONFIRMED）——供应商，送货通知行的反馈状态改为已确认CONFIRMED，反馈内容改为确认CONFIRM——系统代办通知
     * @param params 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("updatePoNoticeLineFeedBackStatus")
	public String updatePoNoticeLineFeedBackStatus(@FormParam(PARAMS) String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = iSrmPoNoticeLine.updatePoNoticeLineFeedBackStatus(jsonParams);
			return convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.get(DATA));
		} catch (UtilsException e){
			LOGGER.error("未知错误:{}",e);
			return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}",e);
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS, "未知错误", 0, null);
		}
	}
}
