package saaf.common.fmw.po.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmShortMaterialInfoEntity_HI Entity Object
 * Fri Dec 29 11:37:42 CST 2017  Auto Generate
 */
@Entity
@Table(name = "SRM_Short_Material_Info")
public class SrmShortMaterialInfoEntity_HI {
    private Integer id;
    private String docno;
    private String doctype;
    private Integer docstate;
    private Integer isholdrelease;
    private String moorg;
    private Integer doclineno;
    private String itemcode;
    private String itemname;
    private BigDecimal bomreqqty;
    private BigDecimal issuedqty;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date actualreqdate;
    private String seibancode;
    private Integer deliverldtime;
    private Integer itemformattribute;
    private Integer isinheritbommasterno;
    private BigDecimal purchasebatchqty;
    private String operatorcode;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private Integer rtngoodstype;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date mostartdate;
    private BigDecimal actqty;
    private String demandcode  ;
    private String mrpcode  ;
    private String itemflag  ;
    private String whName;
    private String ItemFormAttributeName;
    private String RTNGoodsTypeName;
    
    private String gjfl;
    private String gjfln;
    private String blfl;
    private String whman;
    private String whmanname;
    
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

	@Column(name = "demandcode", nullable = true, length = 100) 
    public String getDemandcode() {
		return demandcode;
	}

	public void setDemandcode(String demandcode) {
		this.demandcode = demandcode;
	}

	@Column(name = "mrpcode", nullable = true, length = 100)
	public String getMrpcode() {
		return mrpcode;
	}

	public void setMrpcode(String mrpcode) {
		this.mrpcode = mrpcode;
	}

	@Column(name = "itemflag", nullable = true, length = 100)
	public String getItemflag() {
		return itemflag;
	}

	public void setItemflag(String itemflag) {
		this.itemflag = itemflag;
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

    public void setDocno(String docno) {
	this.docno = docno;
    }

    @Column(name = "docno", nullable = true, length = 50)    
    public String getDocno() {
	return docno;
    }

    public void setDoctype(String doctype) {
	this.doctype = doctype;
    }

    @Column(name = "doctype", nullable = true, length = 50)    
    public String getDoctype() {
	return doctype;
    }

    public void setDocstate(Integer docstate) {
	this.docstate = docstate;
    }

    @Column(name = "docstate", nullable = true, length = 11)    
    public Integer getDocstate() {
	return docstate;
    }

    public void setIsholdrelease(Integer isholdrelease) {
	this.isholdrelease = isholdrelease;
    }

    @Column(name = "isholdrelease", nullable = true, length = 1)    
    public Integer getIsholdrelease() {
	return isholdrelease;
    }

    public void setMoorg(String moorg) {
	this.moorg = moorg;
    }

    @Column(name = "moorg", nullable = true, length = 50)    
    public String getMoorg() {
	return moorg;
    }

    public void setDoclineno(Integer doclineno) {
	this.doclineno = doclineno;
    }

    @Column(name = "doclineno", nullable = true, length = 11)    
    public Integer getDoclineno() {
	return doclineno;
    }

    public void setItemcode(String itemcode) {
	this.itemcode = itemcode;
    }

    @Column(name = "itemcode", nullable = true, length = 50)    
    public String getItemcode() {
	return itemcode;
    }

    public void setItemname(String itemname) {
	this.itemname = itemname;
    }

    @Column(name = "itemname", nullable = true, length = 250)    
    public String getItemname() {
	return itemname;
    }

    public void setBomreqqty(BigDecimal bomreqqty) {
	this.bomreqqty = bomreqqty;
    }

    @Column(name = "bomreqqty", precision = 24, scale = 9)    
    public BigDecimal getBomreqqty() {
	return bomreqqty;
    }

    public void setIssuedqty(BigDecimal issuedqty) {
	this.issuedqty = issuedqty;
    }

    @Column(name = "issuedqty", precision = 24, scale = 9)    
    public BigDecimal getIssuedqty() {
	return issuedqty;
    }

    public void setActualreqdate(Date actualreqdate) {
	this.actualreqdate = actualreqdate;
    }

    @Column(name = "actualreqdate", nullable = true, length = 0)    
    public Date getActualreqdate() {
	return actualreqdate;
    }

    public void setSeibancode(String seibancode) {
	this.seibancode = seibancode;
    }

    @Column(name = "seibancode", nullable = true, length = 30)    
    public String getSeibancode() {
	return seibancode;
    }

    public void setDeliverldtime(Integer deliverldtime) {
	this.deliverldtime = deliverldtime;
    }

    @Column(name = "deliverldtime", nullable = true, length = 11)    
    public Integer getDeliverldtime() {
	return deliverldtime;
    }

    public void setItemformattribute(Integer itemformattribute) {
	this.itemformattribute = itemformattribute;
    }

    @Column(name = "itemformattribute", nullable = true, length = 11)    
    public Integer getItemformattribute() {
	return itemformattribute;
    }

    public void setIsinheritbommasterno(Integer isinheritbommasterno) {
	this.isinheritbommasterno = isinheritbommasterno;
    }

    @Column(name = "isinheritbommasterno", nullable = true, length = 1)    
    public Integer getIsinheritbommasterno() {
	return isinheritbommasterno;
    }

    public void setPurchasebatchqty(BigDecimal purchasebatchqty) {
	this.purchasebatchqty = purchasebatchqty;
    }

    @Column(name = "purchasebatchqty", precision = 24, scale = 9)    
    public BigDecimal getPurchasebatchqty() {
	return purchasebatchqty;
    }

    public void setOperatorcode(String operatorcode) {
	this.operatorcode = operatorcode;
    }

    @Column(name = "operatorcode", nullable = true, length = 20)    
    public String getOperatorcode() {
	return operatorcode;
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

    public void setRtngoodstype(Integer rtngoodstype) {
	this.rtngoodstype = rtngoodstype;
    }

    @Column(name = "rtngoodstype", nullable = true, length = 1)    
    public Integer getRtngoodstype() {
	return rtngoodstype;
    }

    public void setMostartdate(Date mostartdate) {
	this.mostartdate = mostartdate;
    }

    @Column(name = "mostartdate", nullable = true, length = 0)    
    public Date getMostartdate() {
	return mostartdate;
    }

    public void setActqty(BigDecimal actqty) {
	this.actqty = actqty;
    }

    @Column(name = "actqty", precision = 24, scale = 9)    
    public BigDecimal getActqty() {
	return actqty;
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

