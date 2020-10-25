package saaf.common.fmw.po.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import java.math.BigDecimal;

/**
 * SrmOnlyShortMaterialInfoEntity_HI Entity Object Tue Jan 09 14:45:49 CST 2018
 * Auto Generate
 */
@Entity
@Table(name = "SRM_ONLY_SHORT_MATERIAL_INFO")
public class SrmOnlyShortMaterialInfoEntity_HI {
	private Integer id;
	private Integer itemId;
	private String instId;
	private String instName;
	private String docno;
	private String itemcode;
	private String itemname;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date mostartdate;
	@JSONField(format = "yyyy-MM-dd")
	private Date checkdate;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date calcdate;
	private BigDecimal actqty;
	private BigDecimal issuedqty;
	private BigDecimal invqty;
	private BigDecimal shortqty;
	private String doctype;
	private String seibancode;
	private Integer isholdrelease;
	private Integer docstate;
	private BigDecimal purchasebatchqty;
	private String flag;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	private Integer lastUpdatedBy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;
	private Integer lastUpdateLogin;
	private String demandcode;
	private String mrpcode;
	private String itemflag;
	private Integer rtngoodstype;
	private Integer employeeId;
	private Integer deliverldtime;
	private String whName;
    private String ItemFormAttributeName;
    private String RTNGoodsTypeName;
    private String unit;
    
    private String gjfl;
    private String gjfln;
    private String blfl;
    private String whman;
    private String whmanname;    
    private String operatorcode;
    
    private String categoryCode;
	private String categoryName;
	private String itemProp;
	private String itemPropDesc;
	
	private BigDecimal productqty;
    private BigDecimal notcompleteqty;
    private String moitemcode;
    private String moitemname;
    private String productdept;
    
    @Column(name = "productdept", nullable = true, length = 50)
    public String getProductdept() {
		return productdept;
	}

	public void setProductdept(String productdept) {
		this.productdept = productdept;
	}

	@Column(name = "moitemname", nullable = true, length = 200)
    public String getMoitemname() {
		return moitemname;
	}

	public void setMoitemname(String moitemname) {
		this.moitemname = moitemname;
	}

	@Column(name = "moitemcode", nullable = true, length = 50)
    public String getMoitemcode() {
		return moitemcode;
	}

	public void setMoitemcode(String moitemcode) {
		this.moitemcode = moitemcode;
	}

	@Column(name = "notcompleteqty", precision = 38, scale = 9)  
    public BigDecimal getNotcompleteqty() {
		return notcompleteqty;
	}

	public void setNotcompleteqty(BigDecimal notcompleteqty) {
		this.notcompleteqty = notcompleteqty;
	}

	@Column(name = "productqty", precision = 38, scale = 9)    
    public BigDecimal getProductqty() {
		return productqty;
	}

	public void setProductqty(BigDecimal productqty) {
		this.productqty = productqty;
	}
	
	public void setItemProp(String itemProp) {
		this.itemProp = itemProp;
	}

	@Column(name = "item_prop", nullable = true, length = 10)
	public String getItemProp() {
		return itemProp;
	}

	public void setItemPropDesc(String itemPropDesc) {
		this.itemPropDesc = itemPropDesc;
	}

	@Column(name = "item_prop_desc", nullable = true, length = 100)
	public String getItemPropDesc() {
		return itemPropDesc;
	}

	@Column(name = "category_code", nullable = true, length = 100)
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Column(name = "category_name", nullable = true, length = 100)
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setOperatorcode(String operatorcode) {
		this.operatorcode = operatorcode;
	}

	@Column(name = "operatorcode", nullable = true, length = 20)
	public String getOperatorcode() {
		return operatorcode;
	}
    
    @Column(name = "gjfl", nullable = true, length = 50) 
    public String getGjfl() {
		return gjfl;
	}

	public void setGjfl(String gjfl) {
		this.gjfl = gjfl;
	}

	@Column(name = "gjfln", nullable = true, length = 100)
	public String getGjfln() {
		return gjfln;
	}

	public void setGjfln(String gjfln) {
		this.gjfln = gjfln;
	}

	@Column(name = "blfl", nullable = true, length = 50)
	public String getBlfl() {
		return blfl;
	}

	public void setBlfl(String blfl) {
		this.blfl = blfl;
	}

	@Column(name = "whman", nullable = true, length = 50)
	public String getWhman() {
		return whman;
	}

	public void setWhman(String whman) {
		this.whman = whman;
	}

	@Column(name = "whmanname", nullable = true, length = 100)
	public String getWhmanname() {
		return whmanname;
	}

	public void setWhmanname(String whmanname) {
		this.whmanname = whmanname;
	}
    
    @Column(name = "unit", nullable = true, length = 50) 
    public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "itemname", nullable = true, length = 100) 
    public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	@Column(name = "whname", nullable = true, length = 100) 
    public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	@Column(name = "itemformattributename", nullable = true, length = 100) 
	public String getItemFormAttributeName() {
		return ItemFormAttributeName;
	}

	public void setItemFormAttributeName(String itemFormAttributeName) {
		ItemFormAttributeName = itemFormAttributeName;
	}

	@Column(name = "rtngoodstypename", nullable = true, length = 100) 
	public String getRTNGoodsTypeName() {
		return RTNGoodsTypeName;
	}

	public void setRTNGoodsTypeName(String rTNGoodsTypeName) {
		RTNGoodsTypeName = rTNGoodsTypeName;
	}

	public void setDeliverldtime(Integer deliverldtime) {
		this.deliverldtime = deliverldtime;
	}

	@Column(name = "deliverldtime", nullable = true, length = 11)
	public Integer getDeliverldtime() {
		return deliverldtime;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "employee_id", nullable = true, length = 22)
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setRtngoodstype(Integer rtngoodstype) {
		this.rtngoodstype = rtngoodstype;
	}

	@Column(name = "rtngoodstype", nullable = true, length = 1)
	public Integer getRtngoodstype() {
		return rtngoodstype;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, length = 11)
	public Integer getId() {
		return id;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "itemid", nullable = false, length = 11)
	public Integer getItemId() {
		return itemId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	@Column(name = "inst_id", nullable = true, length = 50)
	public String getInstId() {
		return instId;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	@Column(name = "inst_name", nullable = true, length = 50)
	public String getInstName() {
		return instName;
	}

	public void setDocno(String docno) {
		this.docno = docno;
	}

	@Column(name = "docno", nullable = true, length = 50)
	public String getDocno() {
		return docno;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	@Column(name = "itemcode", nullable = true, length = 50)
	public String getItemcode() {
		return itemcode;
	}

	public void setMostartdate(Date mostartdate) {
		this.mostartdate = mostartdate;
	}

	@Column(name = "mostartdate", nullable = true, length = 0)
	public Date getMostartdate() {
		return mostartdate;
	}

	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}

	@Column(name = "checkdate", nullable = true, length = 0)
	public Date getCheckdate() {
		return checkdate;
	}

	public void setCalcdate(Date calcdate) {
		this.calcdate = calcdate;
	}

	@Column(name = "calcdate", nullable = true, length = 0)
	public Date getCalcdate() {
		return calcdate;
	}

	public void setActqty(BigDecimal actqty) {
		this.actqty = actqty;
	}

	@Column(name = "actqty", precision = 24, scale = 9)
	public BigDecimal getActqty() {
		return actqty;
	}

	public void setIssuedqty(BigDecimal issuedqty) {
		this.issuedqty = issuedqty;
	}

	@Column(name = "issuedqty", precision = 24, scale = 9)
	public BigDecimal getIssuedqty() {
		return issuedqty;
	}

	public void setInvqty(BigDecimal invqty) {
		this.invqty = invqty;
	}

	@Column(name = "invqty", precision = 24, scale = 9)
	public BigDecimal getInvqty() {
		return invqty;
	}

	public void setShortqty(BigDecimal shortqty) {
		this.shortqty = shortqty;
	}

	@Column(name = "shortqty", precision = 24, scale = 9)
	public BigDecimal getShortqty() {
		return shortqty;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	@Column(name = "doctype", nullable = true, length = 50)
	public String getDoctype() {
		return doctype;
	}

	public void setSeibancode(String seibancode) {
		this.seibancode = seibancode;
	}

	@Column(name = "seibancode", nullable = true, length = 30)
	public String getSeibancode() {
		return seibancode;
	}

	public void setIsholdrelease(Integer isholdrelease) {
		this.isholdrelease = isholdrelease;
	}

	@Column(name = "isholdrelease", nullable = true, length = 1)
	public Integer getIsholdrelease() {
		return isholdrelease;
	}

	public void setDocstate(Integer docstate) {
		this.docstate = docstate;
	}

	@Column(name = "docstate", nullable = true, length = 11)
	public Integer getDocstate() {
		return docstate;
	}

	public void setPurchasebatchqty(BigDecimal purchasebatchqty) {
		this.purchasebatchqty = purchasebatchqty;
	}

	@Column(name = "purchasebatchqty", precision = 24, scale = 9)
	public BigDecimal getPurchasebatchqty() {
		return purchasebatchqty;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "flag", nullable = true, length = 5)
	public String getFlag() {
		return flag;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creation_date", nullable = true, length = 0)
	public Date getCreationDate() {
		return creationDate;
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

	@Column(name = "last_update_date", nullable = true, length = 0)
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

	public void setDemandcode(String demandcode) {
		this.demandcode = demandcode;
	}

	@Column(name = "demandcode", nullable = true, length = 100)
	public String getDemandcode() {
		return demandcode;
	}

	public void setMrpcode(String mrpcode) {
		this.mrpcode = mrpcode;
	}

	@Column(name = "mrpcode", nullable = true, length = 100)
	public String getMrpcode() {
		return mrpcode;
	}

	public void setItemflag(String itemflag) {
		this.itemflag = itemflag;
	}

	@Column(name = "itemflag", nullable = true, length = 100)
	public String getItemflag() {
		return itemflag;
	}

	private Integer operatorUserId;

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
