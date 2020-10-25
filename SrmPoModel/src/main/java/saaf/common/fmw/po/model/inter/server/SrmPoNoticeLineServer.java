package saaf.common.fmw.po.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.SrmBaseNotificationsEntity_HI;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.po.model.entities.SrmPoNoticeLineEntity_HI;
import saaf.common.fmw.po.model.inter.ISrmPoNoticeLine;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
/**
 * Project Name：SrmPoNoticeLineServer
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
@Component("srmPoNoticeLineServer")
public class SrmPoNoticeLineServer implements ISrmPoNoticeLine {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPoNoticeLineServer.class);

    @Autowired
    private ViewObject<SrmPoNoticeLineEntity_HI> srmPoNoticeLineDAO_HI;

    @Autowired
    private ViewObject<SrmBaseNotificationsEntity_HI> srmBaseNotificationsDAO_HI;//系统通知表

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;//规则编号生成

    public SrmPoNoticeLineServer() {
        super();
    }

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
    @Override
    public JSONObject updatePoNoticeLineStatus(JSONObject jsonParams) throws Exception{
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        String feedbackStatus = jsonParams.getString("feedbackStatus"); // 操作按钮
        if (null == feedbackStatus || "".equals(feedbackStatus)) {
            return SToolUtils.convertResultJSONObj("E", "操作字符为空", 0, null);
        }
        Integer poNoticeId = jsonParams.getInteger("poNoticeId");
        if (null == poNoticeId || "".equals(poNoticeId)) {
            return SToolUtils.convertResultJSONObj("E", "参数为空", 0, null);
        }
        if ("APPROVED".equals(feedbackStatus)) { //确认操作
            List<SrmPoNoticeLineEntity_HI> noticeLinesList = srmPoNoticeLineDAO_HI.findByProperty("poNoticeId", poNoticeId);
            if (null != noticeLinesList && noticeLinesList.size() > 0) {
                Iterator<SrmPoNoticeLineEntity_HI> it = noticeLinesList.iterator();
                while (it.hasNext()) {
                    SrmPoNoticeLineEntity_HI tem = it.next();
                    if (null != tem.getFeedbackStatus() && !"SUBMITTED".equals(tem.getFeedbackStatus())) {//过滤订单行的反馈状态不是已反馈待审核SUBMITTED
                        it.remove();
                    }
                }
            }
            if (null != noticeLinesList && noticeLinesList.size() > 0) {
                for (SrmPoNoticeLineEntity_HI k : noticeLinesList) {
                    k.setFeedbackStatus(feedbackStatus);
                    k.setOperatorUserId(userId);
                    if (k.getFeedbackAdjustDate() != null) {
                        k.setOriginalDeliveryDate(k.getNoticeDeliveryDate());
                        k.setNoticeDeliveryDate(k.getFeedbackAdjustDate());
                    }
                    if (k.getFeedbackAdjustQty() != null) {
                        k.setOriginalDeliveryQty(k.getNoticeDeliveryQty());
                        k.setNoticeDeliveryQty(k.getFeedbackAdjustQty());
                    }
                }
                srmPoNoticeLineDAO_HI.save(noticeLinesList);
            }
            //插入系统通知
            SrmBaseNotificationsEntity_HI notificationsEntity = new SrmBaseNotificationsEntity_HI();
            String notificationCode = saafSequencesUtil.getDocSequences("srm_base_notifications".toUpperCase(), "SH-" + DateUtil.date2Str(new Date(), "yyyyMMdd"), 4);
            notificationsEntity.setNotificationCode(notificationCode);
            notificationsEntity.setNotificationType("13");//送货通知确认
            notificationsEntity.setNotificationStatus("2");//已处理
            notificationsEntity.setTableId(poNoticeId);
            notificationsEntity.setTableIdName("poNoticeId");
            notificationsEntity.setTableName("srm_po_notice");
            notificationsEntity.setFunctionUrl("home.noticeDetail");
            notificationsEntity.setOperatorUserId(userId);
            srmBaseNotificationsDAO_HI.save(notificationsEntity);
            jsonData.put("poNoticeId", poNoticeId);
            return SToolUtils.convertResultJSONObj("S", "确认成功", 1, jsonData);
        }
        return SToolUtils.convertResultJSONObj("E", "没有操作", 0, null);
    }

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
    @Override
    public JSONObject updatePoNoticeLineFeedBackStatus(JSONObject jsonParams) throws Exception{
        JSONObject jsonData = new JSONObject();
        Integer userId = jsonParams.getInteger("varUserId"); // 用户Id
        String feedbackStatus = jsonParams.getString("feedbackStatus"); // 操作按钮
        if (null == feedbackStatus || "".equals(feedbackStatus)) {
            return SToolUtils.convertResultJSONObj("E", "操作字符为空", 0, null);
        }
        Integer lineId = jsonParams.getInteger("lineId");
        if (null == lineId || "".equals(lineId)) {
            return SToolUtils.convertResultJSONObj("E", "参数为" + lineId, 0, null);
        }
        if ("CONFIRMED".equals(feedbackStatus)) { //确认操作
            SrmPoNoticeLineEntity_HI noticeLineEntity = srmPoNoticeLineDAO_HI.getById(lineId);
            if (null != noticeLineEntity) {
                noticeLineEntity.setFeedbackStatus("CONFIRMED");
                noticeLineEntity.setFeedbackResult("CONFIRM");
                noticeLineEntity.setOperatorUserId(userId);
                srmPoNoticeLineDAO_HI.saveEntity(noticeLineEntity);
                //插入系统通知
                SrmBaseNotificationsEntity_HI notificationsEntity = new SrmBaseNotificationsEntity_HI();
                String notificationCode = saafSequencesUtil.getDocSequences("srm_base_notifications".toUpperCase(), "SHFK-" + DateUtil.date2Str(new Date(), "yyyyMMdd"), 4);
                notificationsEntity.setNotificationCode(notificationCode);
                notificationsEntity.setNotificationType("23");//送货通知反馈
                notificationsEntity.setNotificationStatus("2");//已处理
                notificationsEntity.setTableId(lineId);
                notificationsEntity.setTableIdName("poLineId");
                notificationsEntity.setTableName("srm_po_notice_line");
                notificationsEntity.setFunctionUrl("home.noticeSupplierDetails");
                notificationsEntity.setOperatorUserId(userId);
                srmBaseNotificationsDAO_HI.save(notificationsEntity);
                jsonData.put("lineId", lineId);
                return SToolUtils.convertResultJSONObj("S", "确认成功", 1, jsonData);
            }
        }
        return SToolUtils.convertResultJSONObj("E", "没有操作", 0, null);
    }
}
