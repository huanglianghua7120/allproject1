package saaf.common.fmw.po.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPoDeliveryDetailsEntity_HI Entity Object
 * Fri Dec 15 17:22:41 CST 2017  Auto Generate
 */
@Entity
@Table(name = "srm_po_delivery_details")
public class SrmPoDeliveryDetailsEntity_HI {
    private Integer poNoticeLineId;
    private Integer detailId;
    private Integer deliveryLineId;
    private Integer deliveryHeaderId;
    private Integer poNoticeId;
    private Integer poLineId;
    private Integer itemId;
    private Integer instId;
    private Date demandDate;
    private BigDecimal quantity;
    private BigDecimal deliveryQty;
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



    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    @Id
    @SequenceGenerator(name = "SRM_PO_DELIVERY_DETAILS_S", sequenceName = "SRM_PO_DELIVERY_DETAILS_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_PO_DELIVERY_DETAILS_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "detail_id", nullable = false, length = 11)
    public Integer getDetailId() {
        return detailId;
    }

    @Column(name = "demand_date", nullable = false, length = 0)
    public Date getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(Date demandDate) {
        this.demandDate = demandDate;
    }

    public void setDeliveryLineId(Integer deliveryLineId) {
        this.deliveryLineId = deliveryLineId;
    }

    @Column(name = "delivery_line_id", nullable = false, length = 11)
    public Integer getDeliveryLineId() {
        return deliveryLineId;
    }

    public void setDeliveryHeaderId(Integer deliveryHeaderId) {
        this.deliveryHeaderId = deliveryHeaderId;
    }

    @Column(name = "po_notice_line_id", nullable = false, length = 11)
    public Integer getPoNoticeLineId() {
        return poNoticeLineId;
    }

    public void setPoNoticeLineId(Integer poNoticeLineId) {
        this.poNoticeLineId = poNoticeLineId;
    }

    @Column(name = "delivery_header_id", nullable = true, length = 11)
    public Integer getDeliveryHeaderId() {
        return deliveryHeaderId;
    }

    public void setPoNoticeId(Integer poNoticeId) {
        this.poNoticeId = poNoticeId;
    }

    @Column(name = "po_notice_id", nullable = true, length = 11)
    public Integer getPoNoticeId() {
        return poNoticeId;
    }

    public void setPoLineId(Integer poLineId) {
        this.poLineId = poLineId;
    }

    @Column(name = "po_line_id", nullable = true, length = 11)
    public Integer getPoLineId() {
        return poLineId;
    }

    @Column(name = "item_id", nullable = true, length = 11)
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Column(name = "inst_id", nullable = true, length = 11)
    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Column(name = "quantity", precision = 20, scale = 6)
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setDeliveryQty(BigDecimal deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    @Column(name = "delivery_qty", precision = 20, scale = 6)
    public BigDecimal getDeliveryQty() {
        return deliveryQty;
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

