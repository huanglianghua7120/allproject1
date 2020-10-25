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
 * SrmShortMaterialInvEntity_HI Entity Object
 * Thu Dec 28 09:38:35 CST 2017  Auto Generate
 */
@Entity
@Table(name = "SRM_SHORT_MATERIAL_INV")
public class SrmShortMaterialInvEntity_HI {
    private Integer id;
    private String materialId;
    private String materialCode;
    private String invId;
    private String invCode;
    private BigDecimal qty;
    private BigDecimal useAbleQty;
    private String invorg;
    private String considerdelivplan;
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDate;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private String seibanno;
    
    private String whname;
    private String effective;
    
    
    @Column(name = "whname", nullable = true, length = 100)  
    public String getWhname() {
		return whname;
	}

	public void setWhname(String whname) {
		this.whname = whname;
	}
	
	@Column(name = "effective", nullable = true, length = 2)  
	public String getEffective() {
		return effective;
	}

	public void setEffective(String effective) {
		this.effective = effective;
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

    public void setMaterialId(String materialId) {
	this.materialId = materialId;
    }

    @Column(name = "material_id", nullable = true, length = 100)    
    public String getMaterialId() {
	return materialId;
    }

    public void setMaterialCode(String materialCode) {
	this.materialCode = materialCode;
    }

    @Column(name = "material_code", nullable = true, length = 50)    
    public String getMaterialCode() {
	return materialCode;
    }

    public void setInvId(String invId) {
	this.invId = invId;
    }

    @Column(name = "inv_id", nullable = true, length = 100)    
    public String getInvId() {
	return invId;
    }

    public void setInvCode(String invCode) {
	this.invCode = invCode;
    }

    @Column(name = "inv_code", nullable = true, length = 50)    
    public String getInvCode() {
	return invCode;
    }

    public void setQty(BigDecimal qty) {
	this.qty = qty;
    }

    @Column(name = "qty", precision = 38, scale = 9)    
    public BigDecimal getQty() {
	return qty;
    }

    public void setUseAbleQty(BigDecimal useAbleQty) {
	this.useAbleQty = useAbleQty;
    }

    @Column(name = "use_able_qty", precision = 38, scale = 9)    
    public BigDecimal getUseAbleQty() {
	return useAbleQty;
    }

    public void setInvorg(String invorg) {
	this.invorg = invorg;
    }

    @Column(name = "invorg", nullable = true, length = 50)    
    public String getInvorg() {
	return invorg;
    }

    public void setConsiderdelivplan(String considerdelivplan) {
	this.considerdelivplan = considerdelivplan;
    }

    @Column(name = "considerdelivplan", nullable = true, length = 10)    
    public String getConsiderdelivplan() {
	return considerdelivplan;
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

    public void setSeibanno(String seibanno) {
	this.seibanno = seibanno;
    }

    @Column(name = "seibanno", nullable = true, length = 50)    
    public String getSeibanno() {
	return seibanno;
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

