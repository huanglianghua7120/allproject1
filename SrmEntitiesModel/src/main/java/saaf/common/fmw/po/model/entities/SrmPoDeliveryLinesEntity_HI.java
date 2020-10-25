package saaf.common.fmw.po.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPoDeliveryLinesEntity_HI Entity Object
 * Thu Dec 14 15:54:01 CST 2017  Auto Generate
 */
@Entity
@Table(name = "srm_po_delivery_lines")
public class SrmPoDeliveryLinesEntity_HI {
    private Integer deliveryLineId;//送货单行ID
    private Integer deliveryHeaderId;//送货单ID
    private Integer itemId;//物料ID，关联表：srm_base_items
    private Integer poLineId;//采购订单行ID，关联表：srm_po_lines
    private Integer poNoticeLineId;//送货通知行ID，关联表：srm_po_notice_line
    private Integer categoryId;//采购分类ID，关联表：srm_base_categories
    private BigDecimal deliveryQty;//送货数量
    private BigDecimal receivedQty;//已接收数量
    private String deliveryLineStatus;//行状态
    @JSONField(format = "yyyy-MM-dd")
    private Date receivedDate;//接收日期
    //private BigDecimal weight;
    //private String status;
    private String description;//说明
    private String sourceCode;//数据来源
    private String sourceId;//数据来源ID
    //private String specialUseNum;
    //private String demandClassify;
    private Integer versionNum;//版本号
    @JSONField(format = "yyyy-MM-dd")
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

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    public void setDeliveryLineId(Integer deliveryLineId) {
        this.deliveryLineId = deliveryLineId;
    }

    @Id
    @SequenceGenerator(name = "SRM_PO_DELIVERY_LINES_S", sequenceName = "SRM_PO_DELIVERY_LINES_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_PO_DELIVERY_LINES_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "delivery_line_id", nullable = false, length = 11)
    public Integer getDeliveryLineId() {
        return deliveryLineId;
    }

    public void setDeliveryHeaderId(Integer deliveryHeaderId) {
        this.deliveryHeaderId = deliveryHeaderId;
    }

    @Column(name = "delivery_header_id", nullable = false, length = 11)
    public Integer getDeliveryHeaderId() {
        return deliveryHeaderId;
    }

    @Column(name = "received_qty", precision = 15, scale = 6)
    public BigDecimal getReceivedQty() {
        return receivedQty;
    }
    public void setReceivedQty(BigDecimal receivedQty) {
        this.receivedQty = receivedQty;
    }
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    @Column(name = "source_code", nullable = false, length = 30)
    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Column(name = "source_Id", nullable = false, length = 30)
    public String getSourceId() {
        return sourceId;
    }
    @Column(name = "delivery_qty", precision = 15, scale = 6)
    public BigDecimal getDeliveryQty() {
        return deliveryQty;
    }

    public void setDeliveryQty(BigDecimal deliveryQty) {
        this.deliveryQty = deliveryQty;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
    @Column(name = "item_id", nullable = false, length = 11)
    public Integer getItemId() {
        return itemId;
    }

    public void setPoLineId(Integer poLineId) {
        this.poLineId = poLineId;
    }

    @Column(name = "po_line_id", nullable = false, length = 11)
    public Integer getPoLineId() {
        return poLineId;
    }

    public void setPoNoticeLineId(Integer poNoticeLineId) {
        this.poNoticeLineId = poNoticeLineId;
    }

    @Column(name = "notice_line_id", nullable = true, length = 11)
    public Integer getPoNoticeLineId() {
        return poNoticeLineId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "category_Id", nullable = true, length = 11)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "description", nullable = true, length = 500)
    public String getDescription() {
        return description;
    }
    public void setDeliveryLineStatus(String deliveryLineStatus) {
        this.deliveryLineStatus = deliveryLineStatus;
    }
    @Column(name = "delivery_line_status", nullable = true, length = 30)
    public String getDeliveryLineStatus() {
        return deliveryLineStatus;
    }

    /*public void setSpecialUseNum(String specialUseNum) {
        this.specialUseNum = specialUseNum;
    }

    @Column(name = "special_use_num", nullable = true, length = 240)
    public String getSpecialUseNum() {
        return specialUseNum;
    }*/

    /*public void setDemandClassify(String demandClassify) {
        this.demandClassify = demandClassify;
    }

    @Column(name = "demand_classify", nullable = true, length = 240)
    public String getDemandClassify() {
        return demandClassify;
    }*/

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
    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    @Column(name = "received_Date", nullable = false, length = 0)
    public Date getReceivedDate() {
        return receivedDate;
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
}

