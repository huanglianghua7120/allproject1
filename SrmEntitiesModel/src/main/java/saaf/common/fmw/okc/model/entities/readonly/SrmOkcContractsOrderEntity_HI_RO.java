package saaf.common.fmw.okc.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



public class SrmOkcContractsOrderEntity_HI_RO implements Serializable {


    public static String SRM_OKC_CONTRACTS_QUERY_SQL =
                    "SELECT\n" +
                    "  soc.contract_id AS contractId,\n" +
                    "  soc.contract_code AS contractCode,\n" +
                    "  soc.contract_name AS contractName,\n" +
                    "  soc.contract_type AS contractType,\n" +
                    "  soc.party_a_id AS partyAId,\n" +
                    "  soc.party_b_id AS partyBId,\n" +
                    "  soc.party_c_id AS partyCId,\n" +
                    "  soc.contract_status AS contractStatus,\n" +
                    "  soc.contract_approval_status AS contractApprovalStatus,\n" +
                    "  soc.handle_department_id AS handleDepartmentId,\n" +
                    "  soc.main_contract_flag AS mainContractFlag,\n" +
                    "  soc.transfer_po_flag AS transferPoFlag,\n" +
                    "  soc.version_number AS versionNumber,\n" +
                    "  soc.created_mode AS createdMode,\n" +
                    "  soc.template_id AS templateId,\n" +
                    "  soc.watermark_id AS watermarkId,\n" +
                    "  soc.payment_term_id AS paymentTermId,\n" +
                    "  soc.total_amount AS totalAmount,\n" +
                    "  soc.paid_amount AS paidAmount,\n" +
                    "  soc.currency_code AS currencyCode,\n" +
                    "  soc.party_a_sign_date AS partyASignDate,\n" +
                    "  soc.party_b_sign_date AS partyBSignDate,\n" +
                    "  soc.party_c_sign_date AS partyCSignDate,\n" +
                    "  soc.start_date AS startDate,\n" +
                    "  soc.end_date AS endDate,\n" +
                    "  soc.main_contract_id AS mainContractId,\n" +
                    "  soc.signed_site AS signedSite,\n" +
                    "  soc.termination_date AS terminationDate,\n" +
                    "  soc.termination_reasons AS terminationReasons,\n" +
                    "  soc.comments AS comments,\n" +
                    "  soc.source_code AS sourceCode,\n" +
                    "  soc.version_num AS versionNum,\n" +
                    "  soc.creation_date AS creationDate,\n" +
                    "  soc.created_by AS createdBy,\n" +
                    "  soc.last_update_date AS lastUpdateDate,\n" +
                    "  soc.last_updated_by AS lastUpdatedBy,\n" +
                    "  soc.last_update_login AS lastUpdateLogin,\n" +
                    "  soc.attribute_category AS attributeCategory,\n" +
                    "  soc.attribute1 AS attribute1,\n" +
                    "  soc.attribute2 AS attribute2,\n" +
                    "  soc.attribute3 AS attribute3,\n" +
                    "  soc.attribute4 AS attribute4,\n" +
                    "  soc.attribute5 AS attribute5,\n" +
                    "  soc.attribute6 AS attribute6,\n" +
                    "  soc.attribute7 AS attribute7,\n" +
                    "  soc.attribute8 AS attribute8,\n" +
                    "  soc.attribute9 AS attribute9,\n" +
                    "  soc.attribute10 AS attribute10,\n" +
                    "  soc.attribute11 AS attribute11,\n" +
                    "  soc.attribute12 AS attribute12,\n" +
                    "  soc.attribute13 AS attribute13,\n" +
                    "  soc.attribute14 AS attribute14,\n" +
                    "  soc.attribute15 AS attribute15,\n" +
                    "  soc.attribute16 AS attribute16,\n" +
                    "  soc.attribute17 AS attribute17,\n" +
                    "  soc.attribute18 AS attribute18,\n" +
                    "  soc.attribute19 AS attribute19,\n" +
                    "  soc.attribute20 AS attribute20,\n" +
                    "  soc.attribute21 AS attribute21,\n" +
                    "  soc.attribute22 AS attribute22,\n" +
                    "  soc.attribute23 AS attribute23,\n" +
                    "  soc.attribute24 AS attribute24,\n" +
                    "  soc.attribute25 AS attribute25,\n" +
                    "  soc.attribute26 AS attribute26,\n" +
                    "  soc.attribute27 AS attribute27,\n" +
                    "  soc.attribute28 AS attribute28,\n" +
                    "  soc.attribute29 AS attribute29,\n" +
                    "  soc.attribute30 AS attribute30,\n" +
                    "  a.inst_name AS partyAName,\n" +
                    "  b.supplier_name AS partyBName,\n" +
                    "  c.supplier_name AS partyCName,\n" +
                    "  tpl.template_name AS templateName,\n" +
                    "  u.user_name AS createdByName,\n" +
                    "  u2.user_name AS lastUpdatedByName,\n" +
                    "  pt.payment_term_name AS paymentTermName,\n" +
                    "  pt.payment_term_code AS paymentTermCode,\n" +
                    "  ocp.supplier_site_id AS partyBSiteId,\n" +
                    "  spss.site_name AS siteName\n" +
                    "FROM\n" +
                    "  srm_okc_contracts soc\n" +
                    "  INNER JOIN saaf_institution a\n" +
                    "    ON soc.party_a_id = a.inst_id\n" +
                    "  INNER JOIN srm_pos_supplier_info b\n" +
                    "    ON soc.party_b_id = b.supplier_id\n" +
                    "  LEFT JOIN srm_okc_contract_templates tpl\n" +
                    "    ON soc.template_id = tpl.template_id\n" +
                    "  INNER JOIN saaf_users u\n" +
                    "    ON u.user_id = soc.created_by\n" +
                    "  INNER JOIN srm_pon_payment_terms pt\n" +
                    "    ON pt.payment_term_id = soc.payment_term_id\n" +
                    "  INNER JOIN saaf_users u2\n" +
                    "    ON u2.user_id = soc.last_updated_by\n" +
                    "  LEFT JOIN srm_pos_supplier_info c\n" +
                    "    ON soc.party_c_id = c.supplier_id\n" +
                    "  LEFT JOIN srm_okc_contract_parties ocp\n" +
                    "    ON ocp.contract_id = soc.contract_id\n" +
                    "    AND ocp.contract_party_role = '乙方公司'\n" +
                    "  LEFT JOIN srm_pos_supplier_sites spss\n" +
                    "    ON spss.supplier_site_id = ocp.supplier_site_id\n" +
                    "WHERE 1 = 1";

    public static String SRM_OKC_CONTRACT_ITEMS_QUERY_SQL = "select " +
            "   t.contract_item_id as contractItemId ,  \r\n" +
            "   t.contract_id as contractId ,  \r\n" +
            "   t.organization_id as organizationId ,  \r\n" +
            "   t.item_id as itemId ,  \r\n" +
            "   t.purchase_quantity as purchaseQuantity ,  \r\n" +
            "   t.tolerance_ratio as toleranceRatio ,  \r\n" +
            "   t.transferred_po_quantity as transferredPoQuantity ,  \r\n" +
            "   t.tax_rate as taxRate ,  \r\n" +
            "   t.tax_price as taxPrice ,  \r\n" +
            "   t.non_tax_price as nonTaxPrice ,  \r\n" +
            "   t.demand_date as demandDate ,  \r\n" +
            "   t.contract_item_comments as contractItemComments ,  \r\n" +
            "   t.version_num as versionNum ,  \r\n" +
            "   t.creation_date as creationDate ,  \r\n" +
            "   t.created_by as createdBy ,  \r\n" +
            "   t.last_update_date as lastUpdateDate ,  \r\n" +
            "   t.last_updated_by as lastUpdatedBy ,  \r\n" +
            "   t.last_update_login as lastUpdateLogin ,  \r\n" +
            "   t.attribute_category as attributeCategory ,  \r\n" +
            "   t.attribute1 as attribute1 ,  \r\n" +
            "   t.attribute2 as attribute2 ,  \r\n" +
            "   t.attribute3 as attribute3 ,  \r\n" +
            "   t.attribute4 as attribute4 ,  \r\n" +
            "   t.attribute5 as attribute5 ,  \r\n" +
            "   t.attribute6 as attribute6 ,  \r\n" +
            "   t.attribute7 as attribute7 ,  \r\n" +
            "   t.attribute8 as attribute8 ,  \r\n" +
            "   t.attribute9 as attribute9 ,  \r\n" +
            "   t.attribute10 as attribute10 ,  \r\n" +
            "   t.attribute11 as attribute11 ,  \r\n" +
            "   t.attribute12 as attribute12 ,  \r\n" +
            "   t.attribute13 as attribute13 ,  \r\n" +
            "   t.attribute14 as attribute14 ,  \r\n" +
            "   t.attribute15 as attribute15 ,  \r\n" +
            "   t.attribute16 as attribute16 ,  \r\n" +
            "   t.attribute17 as attribute17 ,  \r\n" +
            "   t.attribute18 as attribute18 ,  \r\n" +
            "   t.attribute19 as attribute19 ,  \r\n" +
            "   t.attribute20 as attribute20 ,  \r\n" +
            "   t.attribute21 as attribute21 ,  \r\n" +
            "   t.attribute22 as attribute22 ,  \r\n" +
            "   t.attribute23 as attribute23 ,  \r\n" +
            "   t.attribute24 as attribute24 ,  \r\n" +
            "   t.attribute25 as attribute25 ,  \r\n" +
            "   t.attribute26 as attribute26 ,  \r\n" +
            "   t.attribute27 as attribute27 ,  \r\n" +
            "   t.attribute28 as attribute28 ,  \r\n" +
            "   t.attribute29 as attribute29 ,  \r\n" +
            "   t.attribute30 as attribute30 ,  \r\n" +
            " i.item_code as itemCode,i.item_name as itemName,i.uom_code as uomCode, slv.meaning AS uomCodeName,c.category_id as categoryId, c.full_category_code as  categoryCode, c.full_category_name as categoryName,si.inst_name as instName,si.inst_code as instCode,\n"+
            "  i.specification as specification,t.tax_rate_code as taxRateCode \r\n" +
            "  FROM srm_okc_contract_items t left  join srm_base_items i on t.item_id = i.item_id and t.organization_id = i.organization_id \r\n" +
            " left JOIN srm_base_categories c on i.category_id = c.category_id \r\n" +
            " left JOIN  saaf_institution si on t.organization_id = si.inst_id \r\n" +
            " left JOIN  Saaf_Lookup_Values slv on slv.lookup_code = i.uom_code \r\n" +
            "    		 WHERE t.contract_id = :contractId";




    private Integer contractId;

	private String contractCode;

	private String contractName;

	private String contractType;

	private Integer partyAId;

	private Integer partyBId;

	private Integer partyCId;

    private Integer partyBSiteId;

	private String contractStatus;

	private String contractApprovalStatus;

	private Integer handleDepartmentId;

	private String mainContractFlag;

	private String transferPoFlag;

	private Integer versionNumber;

	private String createdMode;

	private Integer templateId;

	private Integer watermarkId;

	private Integer paymentTermId;

	private BigDecimal totalAmount;

	private BigDecimal paidAmount;

	private String currencyCode;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date partyASignDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date partyBSignDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date partyCSignDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date endDate;

	private Integer mainContractId;

	private String signedSite;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date terminationDate;

	private String terminationReasons;

	private String comments;

    private BigDecimal supRate;

    public BigDecimal getSupRate() {
        return supRate;
    }

    public void setSupRate(BigDecimal supRate) {
        this.supRate = supRate;
    }

    private String sourceCode;

	private Integer versionNum;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;

	private Integer createdBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;

	private Integer lastUpdatedBy;

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

	private String attribute11;

	private String attribute12;

	private String attribute13;

	private String attribute14;

	private String attribute15;

	private Integer attribute16;

	private Integer attribute17;

	private Integer attribute18;

	private Integer attribute19;

	private Integer attribute20;

	private BigDecimal attribute21;

	private BigDecimal attribute22;

	private BigDecimal attribute23;

	private BigDecimal attribute24;

	private BigDecimal attribute25;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute26;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute27;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute28;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute29;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute30;

    private String partyAName;
    private String partyBName;
    private String partyCName;
    private String templateName;
    private String createdByName;
    private String lastUpdatedByName;
    private String paymentTermName;
    private String paymentTermCode;

    public String getPaymentTermCode() {
        return paymentTermCode;
    }

    public void setPaymentTermCode(String paymentTermCode) {
        this.paymentTermCode = paymentTermCode;
    }

    private Integer contractItemId;


    private Integer organizationId;

    private Integer itemId;

    private BigDecimal purchaseQuantity;

    private BigDecimal toleranceRatio;

    private BigDecimal transferredPoQuantity;

    private BigDecimal taxRate;

    private BigDecimal taxPrice;

    private BigDecimal nonTaxPrice;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date demandDate;

    private String contractItemComments;

    private String itemCode;
    private String itemName;
    private String uomCodeName;
    private String categoryCode;
    private String categoryName;
    private String instName;

    private Integer categoryId;
    private String uomCode;
    private String instCode;
    private String specification;
    private String taxRateCode;
    private String siteName;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getTaxRateCode() {
        return taxRateCode;
    }

    public void setTaxRateCode(String taxRateCode) {
        this.taxRateCode = taxRateCode;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    public SrmOkcContractsOrderEntity_HI_RO(){
        super();
    }

    public Integer  getContractId() {
        return contractId;
    }

    public void setContractId( Integer contractId) {
        this.contractId = contractId;
    }

    public String  getContractCode() {
        return contractCode;
    }

    public void setContractCode( String contractCode) {
        this.contractCode = contractCode;
    }

    public String  getContractName() {
        return contractName;
    }

    public void setContractName( String contractName) {
        this.contractName = contractName;
    }

    public String  getContractType() {
        return contractType;
    }

    public void setContractType( String contractType) {
        this.contractType = contractType;
    }

    public Integer  getPartyAId() {
        return partyAId;
    }

    public void setPartyAId( Integer partyAId) {
        this.partyAId = partyAId;
    }

    public Integer  getPartyBId() {
        return partyBId;
    }

    public void setPartyBId( Integer partyBId) {
        this.partyBId = partyBId;
    }

    public Integer  getPartyCId() {
        return partyCId;
    }

    public void setPartyCId( Integer partyCId) {
        this.partyCId = partyCId;
    }

    public String  getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus( String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String  getContractApprovalStatus() {
        return contractApprovalStatus;
    }

    public void setContractApprovalStatus( String contractApprovalStatus) {
        this.contractApprovalStatus = contractApprovalStatus;
    }

    public Integer  getHandleDepartmentId() {
        return handleDepartmentId;
    }

    public void setHandleDepartmentId( Integer handleDepartmentId) {
        this.handleDepartmentId = handleDepartmentId;
    }

    public String  getMainContractFlag() {
        return mainContractFlag;
    }

    public void setMainContractFlag( String mainContractFlag) {
        this.mainContractFlag = mainContractFlag;
    }

    public String  getTransferPoFlag() {
        return transferPoFlag;
    }

    public void setTransferPoFlag( String transferPoFlag) {
        this.transferPoFlag = transferPoFlag;
    }

    public Integer  getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber( Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String  getCreatedMode() {
        return createdMode;
    }

    public void setCreatedMode( String createdMode) {
        this.createdMode = createdMode;
    }

    public Integer  getTemplateId() {
        return templateId;
    }

    public void setTemplateId( Integer templateId) {
        this.templateId = templateId;
    }

    public Integer  getWatermarkId() {
        return watermarkId;
    }

    public void setWatermarkId( Integer watermarkId) {
        this.watermarkId = watermarkId;
    }

    public Integer  getPaymentTermId() {
        return paymentTermId;
    }

    public void setPaymentTermId( Integer paymentTermId) {
        this.paymentTermId = paymentTermId;
    }

    public BigDecimal  getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount( BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal  getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount( BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String  getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode( String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Date  getPartyASignDate() {
        return partyASignDate;
    }

    public void setPartyASignDate( Date partyASignDate) {
        this.partyASignDate = partyASignDate;
    }

    public Date  getPartyBSignDate() {
        return partyBSignDate;
    }

    public void setPartyBSignDate( Date partyBSignDate) {
        this.partyBSignDate = partyBSignDate;
    }

    public Date  getPartyCSignDate() {
        return partyCSignDate;
    }

    public void setPartyCSignDate( Date partyCSignDate) {
        this.partyCSignDate = partyCSignDate;
    }

    public Date  getStartDate() {
        return startDate;
    }

    public void setStartDate( Date startDate) {
        this.startDate = startDate;
    }

    public Date  getEndDate() {
        return endDate;
    }

    public void setEndDate( Date endDate) {
        this.endDate = endDate;
    }

    public Integer  getMainContractId() {
        return mainContractId;
    }

    public void setMainContractId( Integer mainContractId) {
        this.mainContractId = mainContractId;
    }

    public String  getSignedSite() {
        return signedSite;
    }

    public void setSignedSite( String signedSite) {
        this.signedSite = signedSite;
    }

    public Date  getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate( Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String  getTerminationReasons() {
        return terminationReasons;
    }

    public void setTerminationReasons( String terminationReasons) {
        this.terminationReasons = terminationReasons;
    }

    public String  getComments() {
        return comments;
    }

    public void setComments( String comments) {
        this.comments = comments;
    }

    public String  getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode( String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public Integer  getVersionNum() {
        return versionNum;
    }

    public void setVersionNum( Integer versionNum) {
        this.versionNum = versionNum;
    }

    public Date  getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer  getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy( Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date  getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate( Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer  getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy( Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer  getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin( Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public String  getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory( String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    public String  getAttribute1() {
        return attribute1;
    }

    public void setAttribute1( String attribute1) {
        this.attribute1 = attribute1;
    }

    public String  getAttribute2() {
        return attribute2;
    }

    public void setAttribute2( String attribute2) {
        this.attribute2 = attribute2;
    }

    public String  getAttribute3() {
        return attribute3;
    }

    public void setAttribute3( String attribute3) {
        this.attribute3 = attribute3;
    }

    public String  getAttribute4() {
        return attribute4;
    }

    public void setAttribute4( String attribute4) {
        this.attribute4 = attribute4;
    }

    public String  getAttribute5() {
        return attribute5;
    }

    public void setAttribute5( String attribute5) {
        this.attribute5 = attribute5;
    }

    public String  getAttribute6() {
        return attribute6;
    }

    public void setAttribute6( String attribute6) {
        this.attribute6 = attribute6;
    }

    public String  getAttribute7() {
        return attribute7;
    }

    public void setAttribute7( String attribute7) {
        this.attribute7 = attribute7;
    }

    public String  getAttribute8() {
        return attribute8;
    }

    public void setAttribute8( String attribute8) {
        this.attribute8 = attribute8;
    }

    public String  getAttribute9() {
        return attribute9;
    }

    public void setAttribute9( String attribute9) {
        this.attribute9 = attribute9;
    }

    public String  getAttribute10() {
        return attribute10;
    }

    public void setAttribute10( String attribute10) {
        this.attribute10 = attribute10;
    }

    public String  getAttribute11() {
        return attribute11;
    }

    public void setAttribute11( String attribute11) {
        this.attribute11 = attribute11;
    }

    public String  getAttribute12() {
        return attribute12;
    }

    public void setAttribute12( String attribute12) {
        this.attribute12 = attribute12;
    }

    public String  getAttribute13() {
        return attribute13;
    }

    public void setAttribute13( String attribute13) {
        this.attribute13 = attribute13;
    }

    public String  getAttribute14() {
        return attribute14;
    }

    public void setAttribute14( String attribute14) {
        this.attribute14 = attribute14;
    }

    public String  getAttribute15() {
        return attribute15;
    }

    public void setAttribute15( String attribute15) {
        this.attribute15 = attribute15;
    }

    public Integer  getAttribute16() {
        return attribute16;
    }

    public void setAttribute16( Integer attribute16) {
        this.attribute16 = attribute16;
    }

    public Integer  getAttribute17() {
        return attribute17;
    }

    public void setAttribute17( Integer attribute17) {
        this.attribute17 = attribute17;
    }

    public Integer  getAttribute18() {
        return attribute18;
    }

    public void setAttribute18( Integer attribute18) {
        this.attribute18 = attribute18;
    }

    public Integer  getAttribute19() {
        return attribute19;
    }

    public void setAttribute19( Integer attribute19) {
        this.attribute19 = attribute19;
    }

    public Integer  getAttribute20() {
        return attribute20;
    }

    public void setAttribute20( Integer attribute20) {
        this.attribute20 = attribute20;
    }

    public BigDecimal  getAttribute21() {
        return attribute21;
    }

    public void setAttribute21( BigDecimal attribute21) {
        this.attribute21 = attribute21;
    }

    public BigDecimal  getAttribute22() {
        return attribute22;
    }

    public void setAttribute22( BigDecimal attribute22) {
        this.attribute22 = attribute22;
    }

    public BigDecimal  getAttribute23() {
        return attribute23;
    }

    public void setAttribute23( BigDecimal attribute23) {
        this.attribute23 = attribute23;
    }

    public BigDecimal  getAttribute24() {
        return attribute24;
    }

    public void setAttribute24( BigDecimal attribute24) {
        this.attribute24 = attribute24;
    }

    public BigDecimal  getAttribute25() {
        return attribute25;
    }

    public void setAttribute25( BigDecimal attribute25) {
        this.attribute25 = attribute25;
    }

    public Date  getAttribute26() {
        return attribute26;
    }

    public void setAttribute26( Date attribute26) {
        this.attribute26 = attribute26;
    }

    public Date  getAttribute27() {
        return attribute27;
    }

    public void setAttribute27( Date attribute27) {
        this.attribute27 = attribute27;
    }

    public Date  getAttribute28() {
        return attribute28;
    }

    public void setAttribute28( Date attribute28) {
        this.attribute28 = attribute28;
    }

    public Date  getAttribute29() {
        return attribute29;
    }

    public void setAttribute29( Date attribute29) {
        this.attribute29 = attribute29;
    }

    public Date  getAttribute30() {
        return attribute30;
    }

    public void setAttribute30( Date attribute30) {
        this.attribute30 = attribute30;
    }

    public String getPartyAName() {
        return partyAName;
    }

    public void setPartyAName(String partyAName) {
        this.partyAName = partyAName;
    }

    public String getPartyBName() {
        return partyBName;
    }

    public void setPartyBName(String partyBName) {
        this.partyBName = partyBName;
    }

    public String getPartyCName() {
        return partyCName;
    }

    public void setPartyCName(String partyCName) {
        this.partyCName = partyCName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getLastUpdatedByName() {
        return lastUpdatedByName;
    }

    public void setLastUpdatedByName(String lastUpdatedByName) {
        this.lastUpdatedByName = lastUpdatedByName;
    }

    public String getPaymentTermName() {
        return paymentTermName;
    }

    public void setPaymentTermName(String paymentTermName) {
        this.paymentTermName = paymentTermName;
    }

    public Integer getContractItemId() {
        return contractItemId;
    }

    public void setContractItemId(Integer contractItemId) {
        this.contractItemId = contractItemId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(BigDecimal purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public BigDecimal getToleranceRatio() {
        return toleranceRatio;
    }

    public void setToleranceRatio(BigDecimal toleranceRatio) {
        this.toleranceRatio = toleranceRatio;
    }

    public BigDecimal getTransferredPoQuantity() {
        return transferredPoQuantity;
    }

    public void setTransferredPoQuantity(BigDecimal transferredPoQuantity) {
        this.transferredPoQuantity = transferredPoQuantity;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    public BigDecimal getNonTaxPrice() {
        return nonTaxPrice;
    }

    public void setNonTaxPrice(BigDecimal nonTaxPrice) {
        this.nonTaxPrice = nonTaxPrice;
    }

    public Date getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(Date demandDate) {
        this.demandDate = demandDate;
    }

    public String getContractItemComments() {
        return contractItemComments;
    }

    public void setContractItemComments(String contractItemComments) {
        this.contractItemComments = contractItemComments;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUomCodeName() {
        return uomCodeName;
    }

    public void setUomCodeName(String uomCodeName) {
        this.uomCodeName = uomCodeName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public Integer getPartyBSiteId() {
        return partyBSiteId;
    }

    public void setPartyBSiteId(Integer partyBSiteId) {
        this.partyBSiteId = partyBSiteId;
    }
}
