package saaf.common.fmw.po.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * SrmPoRequisitionLinesEntity_HI_RO Entity Object
 * Sat Jun 09 16:36:47 CST 2018  Auto Generate
 */

public class SrmPoRequisitionLinesEntity_HI_RO  implements Serializable {
	//获取申请数量
	public static final String QUERY_REQ_DEMANDQTY_SQL = "SELECT\n" +
			"\tprl.requisition_line_id requisitionLineId,\n" +
			"\tprl.item_id itemId,\n" +
			"\tprl.demand_qty demandQty,\n" +
			"\tprl.source_id sourceId\n" +
			"FROM\n" +
			"\tsrm_po_requisition_lines prl\n" +
			"WHERE\n" +
			"\tprl.requisition_header_id = (\n" +
			"\t\t-- 根据采购订单头查找对应的申请订单头id\n" +
			"\t\tSELECT\n" +
			"\t\t\trh.requisition_header_id\n" +
			"\t\tFROM\n" +
			"\t\t\tsrm_po_requisition_headers rh\n" +
			"\t\tWHERE\n" +
			"\t\t\trh.source_id =:poHeaderId\n" +
			"\t)\n" +
			"AND prl.source_id =:poLineId\n" +
			"AND prl.item_id =:itemId";


    public static final String QUERY_REQUISITION_LINE_NUMBER_MAX_SQL =
            "SELECT MAX(Spql.Line_Number) AS lineNumber\n" +
                    "  FROM Srm_Po_Requisition_Lines Spql\n" +
                    " WHERE Spql.REQUISITION_HEADER_ID = :requisitionHeaderId\n";

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
    private Integer agentId; //采购员ID
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
    private Integer orgId;
    private Integer organizationId;
    private Integer supplierId;
    private String requisitionType;
    private Integer requisitionEmpId;
    private String requisitionNumber;

    public String getRequisitionNumber() {
        return requisitionNumber;
    }

    public void setRequisitionNumber(String requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getRequisitionType() {
        return requisitionType;
    }

    public void setRequisitionType(String requisitionType) {
        this.requisitionType = requisitionType;
    }

    public Integer getRequisitionEmpId() {
        return requisitionEmpId;
    }

    public void setRequisitionEmpId(Integer requisitionEmpId) {
        this.requisitionEmpId = requisitionEmpId;
    }

    public void setRequisitionLineId(Integer requisitionLineId) {
		this.requisitionLineId = requisitionLineId;
	}

	
	public Integer getRequisitionLineId() {
		return requisitionLineId;
	}

	public void setRequisitionHeaderId(Integer requisitionHeaderId) {
		this.requisitionHeaderId = requisitionHeaderId;
	}

	
	public Integer getRequisitionHeaderId() {
		return requisitionHeaderId;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	
	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	
	public String getItemName() {
		return itemName;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	
	public String getItemSpec() {
		return itemSpec;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	
	public String getUomCode() {
		return uomCode;
	}

	public void setMinPacking(BigDecimal minPacking) {
		this.minPacking = minPacking;
	}

	
	public BigDecimal getMinPacking() {
		return minPacking;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	
	public Integer getBuyerId() {
		return buyerId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setDemandQty(BigDecimal demandQty) {
		this.demandQty = demandQty;
	}

	
	public BigDecimal getDemandQty() {
		return demandQty;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	
	public Date getDemandDate() {
		return demandDate;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	
	public Integer getAgentId() {
		return agentId;
	}

	public void setHandleWay(String handleWay) {
		this.handleWay = handleWay;
	}

	
	public String getHandleWay() {
		return handleWay;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	
	public String getLineStatus() {
		return lineStatus;
	}

	public void setLineComments(String lineComments) {
		this.lineComments = lineComments;
	}

	
	public String getLineComments() {
		return lineComments;
	}

	public void setSourceNumber(String sourceNumber) {
		this.sourceNumber = sourceNumber;
	}

	
	public String getSourceNumber() {
		return sourceNumber;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	
	public String getSourceId() {
		return sourceId;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	
	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	
	public String getAttribute10() {
		return attribute10;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
