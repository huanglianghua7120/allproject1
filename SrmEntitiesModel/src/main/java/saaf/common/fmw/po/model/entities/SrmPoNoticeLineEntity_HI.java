package saaf.common.fmw.po.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import java.math.BigDecimal;

/**
 * SrmPoNoticeLineEntity_HI Entity Object
 * Wed Aug 15 14:33:04 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_po_notice_line")
public class SrmPoNoticeLineEntity_HI {
    private Integer lineId; //送货通知单行ID
    private Integer poNoticeId; //送货通知单ID
    private Integer poHeaderId; //采购订单ID
    private Integer poLineId; //采购订单行ID
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date noticeDeliveryDate; //通知送货日期
    private BigDecimal noticeDeliveryQty; //通知送货数量
    private BigDecimal onWayQty; //在途数量（已创建送货单数量）
    private String lineComments; //送货备注
    private String lineStatus; //行状态
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date originalDeliveryDate; //原通知送货日期
    private BigDecimal originalDeliveryQty; //原通知送货数量
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date feedbackAdjustDate; //反馈调整日期
    private BigDecimal feedbackAdjustQty; //反馈调整数量
    private String feedbackStatus; //反馈状态
    private String feedbackResult; //反馈结果
    private String rejectReason; //供应商拒绝原因
    private String sourceCode; //数据来源
    private String sourceId; //数据来源ID
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private String attributeCategory;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String attribute6;
    private String attribute7;
    private String attribute8;
    private String attribute9;
    private String attribute10;
    private Integer operatorUserId;

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	@Id
	@SequenceGenerator(name = "SRM_PO_NOTICE_LINE_S", sequenceName = "SRM_PO_NOTICE_LINE_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PO_NOTICE_LINE_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "line_id", nullable = false, length = 11)	
	public Integer getLineId() {
		return lineId;
	}

	public void setPoNoticeId(Integer poNoticeId) {
		this.poNoticeId = poNoticeId;
	}

	@Column(name = "po_notice_id", nullable = false, length = 11)	
	public Integer getPoNoticeId() {
		return poNoticeId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	@Column(name = "po_header_id", nullable = true, length = 11)	
	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}

	@Column(name = "po_line_id", nullable = true, length = 11)	
	public Integer getPoLineId() {
		return poLineId;
	}

	public void setNoticeDeliveryDate(Date noticeDeliveryDate) {
		this.noticeDeliveryDate = noticeDeliveryDate;
	}

	@Column(name = "notice_delivery_date", nullable = true, length = 0)	
	public Date getNoticeDeliveryDate() {
		return noticeDeliveryDate;
	}

	public void setNoticeDeliveryQty(BigDecimal noticeDeliveryQty) {
		this.noticeDeliveryQty = noticeDeliveryQty;
	}

	@Column(name = "notice_delivery_qty", precision = 15, scale = 3)	
	public BigDecimal getNoticeDeliveryQty() {
		return noticeDeliveryQty;
	}

	public void setOnWayQty(BigDecimal onWayQty) {
		this.onWayQty = onWayQty;
	}

	@Column(name = "on_way_qty", precision = 15, scale = 3)	
	public BigDecimal getOnWayQty() {
		return onWayQty;
	}

	public void setLineComments(String lineComments) {
		this.lineComments = lineComments;
	}

	@Column(name = "line_comments", nullable = true, length = 2000)	
	public String getLineComments() {
		return lineComments;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	@Column(name = "line_status", nullable = true, length = 30)	
	public String getLineStatus() {
		return lineStatus;
	}

	public void setOriginalDeliveryDate(Date originalDeliveryDate) {
		this.originalDeliveryDate = originalDeliveryDate;
	}

	@Column(name = "original_delivery_date", nullable = true, length = 0)	
	public Date getOriginalDeliveryDate() {
		return originalDeliveryDate;
	}

	public void setOriginalDeliveryQty(BigDecimal originalDeliveryQty) {
		this.originalDeliveryQty = originalDeliveryQty;
	}

	@Column(name = "original_delivery_qty", precision = 15, scale = 3)	
	public BigDecimal getOriginalDeliveryQty() {
		return originalDeliveryQty;
	}

	public void setFeedbackAdjustDate(Date feedbackAdjustDate) {
		this.feedbackAdjustDate = feedbackAdjustDate;
	}

	@Column(name = "feedback_adjust_date", nullable = true, length = 0)	
	public Date getFeedbackAdjustDate() {
		return feedbackAdjustDate;
	}

	public void setFeedbackAdjustQty(BigDecimal feedbackAdjustQty) {
		this.feedbackAdjustQty = feedbackAdjustQty;
	}

	@Column(name = "feedback_adjust_qty", precision = 15, scale = 3)	
	public BigDecimal getFeedbackAdjustQty() {
		return feedbackAdjustQty;
	}

	public void setFeedbackStatus(String feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	@Column(name = "feedback_status", nullable = true, length = 30)	
	public String getFeedbackStatus() {
		return feedbackStatus;
	}

	public void setFeedbackResult(String feedbackResult) {
		this.feedbackResult = feedbackResult;
	}

	@Column(name = "feedback_result", nullable = true, length = 30)	
	public String getFeedbackResult() {
		return feedbackResult;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	@Column(name = "reject_reason", nullable = true, length = 240)	
	public String getRejectReason() {
		return rejectReason;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	@Column(name = "source_code", nullable = true, length = 30)	
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source_id", nullable = true, length = 30)	
	public String getSourceId() {
		return sourceId;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
	@Column(name = "version_num", nullable = true, length = 11)	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creation_date", nullable = false, length = 0)	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_by", nullable = false, length = 11)	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "last_updated_by", nullable = false, length = 11)	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "last_update_date", nullable = false, length = 0)	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name = "last_update_login", nullable = true, length = 11)	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	@Column(name = "attribute_category", nullable = true, length = 30)	
	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	@Column(name = "attribute1", nullable = true, length = 240)	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	@Column(name = "attribute2", nullable = true, length = 240)	
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	@Column(name = "attribute3", nullable = true, length = 240)	
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	@Column(name = "attribute4", nullable = true, length = 240)	
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	@Column(name = "attribute5", nullable = true, length = 240)	
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	@Column(name = "attribute6", nullable = true, length = 240)	
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	@Column(name = "attribute7", nullable = true, length = 240)	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	@Column(name = "attribute8", nullable = true, length = 240)	
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	@Column(name = "attribute9", nullable = true, length = 240)	
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	@Column(name = "attribute10", nullable = true, length = 240)	
	public String getAttribute10() {
		return attribute10;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
