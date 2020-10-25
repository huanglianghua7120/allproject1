package saaf.common.fmw.po.model.inter;

import com.alibaba.fastjson.JSONObject;
/**
 * Project Name：ISrmPoNoticeLine
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
public interface ISrmPoNoticeLine {

    /**
     * Description：送货通知确认（确认APPROVED），所有订单行的状态改为反馈审核通过——系统代办通知
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	JSONObject updatePoNoticeLineStatus(JSONObject jsonParams)throws Exception;


    /**
     * Description：送货通知反馈（确认CONFIRMED）——供应商，送货通知行的反馈状态改为已确认CONFIRMED，反馈内容改为确认CONFIRM——系统代办通知
     * @param jsonParams 查询条件参数
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-18     SIE 谢晓霞       创建
     * =======================================================================
     */
	JSONObject updatePoNoticeLineFeedBackStatus(JSONObject jsonParams)throws Exception;
}
