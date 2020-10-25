package saaf.common.fmw.po.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPoRequisitionLinesEntity_HI Entity Object
 * Sat Jun 09 16:36:47 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_po_requisition_lines")
public class SrmPoRequisitionLinesEntity_HI {
    private Integer requisitionLineId; //采购申请行ID
    private Integer requisitionHeaderId; //采购申请ID
    private Integer lineNumber; //行号
    private Integer itemId; //物料ID
    private String itemName; //物料名称
    private String itemSpec; //物料规格
    private String uomCode; //计量单位
    private BigDecimal minPacking; //最小包装量
    private Integer buyerId; //采购员ID，关联表：saaf_employees
    private Integer categoryId; //采购分类ID
    private BigDecimal demandQty; //需求数量
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date demandDate; //需求时间
    private String handleWay; //处理方式
    private String lineStatus; //行状态
    private String lineComments; //行说明
    private String sourceNumber; //来源单号
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
    private Integer supplierId;
    private Integer poHeaderId;

    public void setRequisitionLineId(Integer requisitionLineId) {
        this.requisitionLineId = requisitionLineId;
    }

    @Id
    @SequenceGenerator(name = "SRM_PO_REQUISITION_LINES_S", sequenceName = "SRM_PO_REQUISITION_LINES_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_PO_REQUISITION_LINES_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "requisition_line_id", nullable = false, length = 11)
    public Integer getRequisitionLineId() {
        return requisitionLineId;
    }

    public void setRequisitionHeaderId(Integer requisitionHeaderId) {
        this.requisitionHeaderId = requisitionHeaderId;
    }

    @Column(name = "requisition_header_id", nullable = false, length = 11)
    public Integer getRequisitionHeaderId() {
        return requisitionHeaderId;
    }

    @Column(name = "supplier_id", nullable = true, length = 10)
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    @Column(name = "po_header_id", nullable = true, length = 10)
    public Integer getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoHeaderId(Integer poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Column(name = "line_number", nullable = true, length = 11)
    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Column(name = "item_id", nullable = true, length = 11)
    public Integer getItemId() {
        return itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Column(name = "item_name", nullable = true, length = 240)
    public String getItemName() {
        return itemName;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    @Column(name = "item_spec", nullable = true, length = 240)
    public String getItemSpec() {
        return itemSpec;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    @Column(name = "uom_code", nullable = true, length = 30)
    public String getUomCode() {
        return uomCode;
    }

    public void setMinPacking(BigDecimal minPacking) {
        this.minPacking = minPacking;
    }

    @Column(name = "min_packing", precision = 11, scale = 2)
    public BigDecimal getMinPacking() {
        return minPacking;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    @Column(name = "buyer_id", nullable = true, length = 11)
    public Integer getBuyerId() {
        return buyerId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "category_id", nullable = true, length = 11)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setDemandQty(BigDecimal demandQty) {
        this.demandQty = demandQty;
    }

    @Column(name = "demand_qty", precision = 15, scale = 3)
    public BigDecimal getDemandQty() {
        return demandQty;
    }

    public void setDemandDate(Date demandDate) {
        this.demandDate = demandDate;
    }

    @Column(name = "demand_date", nullable = true, length = 0)
    public Date getDemandDate() {
        return demandDate;
    }

    public void setHandleWay(String handleWay) {
        this.handleWay = handleWay;
    }

    @Column(name = "handle_way", nullable = true, length = 30)
    public String getHandleWay() {
        return handleWay;
    }

    public void setLineStatus(String lineStatus) {
        this.lineStatus = lineStatus;
    }

    @Column(name = "line_status", nullable = true, length = 30)
    public String getLineStatus() {
        return lineStatus;
    }

    public void setLineComments(String lineComments) {
        this.lineComments = lineComments;
    }

    @Column(name = "line_comments", nullable = true, length = 2000)
    public String getLineComments() {
        return lineComments;
    }

    public void setSourceNumber(String sourceNumber) {
        this.sourceNumber = sourceNumber;
    }

    @Column(name = "source_number", nullable = true, length = 30)
    public String getSourceNumber() {
        return sourceNumber;
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

    @Column(name = "last_updated_by", nullable = true, length = 11)
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
