package saaf.common.fmw.report.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

@Entity
@Table(name = "saaf_charts")
public class SaafChartsEntity_HI {
    private Integer chartsId;
    private String chartsCode;
    private String chartsName;
    private String chartsTitle;
    private String chartsType;
    private String demoUrl;
    private String chartsScript;
    private String dimensions;
    private String dimensionLength;
    private String dataConversionScript;
    private String description;
    private Integer versionNum;
    private Integer createdBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
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
    private String attribute11;
    private String attribute12;
    private String attribute13;
    private String attribute14;
    private String attribute15;
    private Integer operatorUserId;

    public void setChartsId(Integer chartsId) {
	this.chartsId = chartsId;
    }

    @Id    
    @GeneratedValue
    @Column(name = "charts_id", nullable = false, length = 11)    
    public Integer getChartsId() {
	return chartsId;
    }

	public void setChartsCode(String chartsCode) {
		this.chartsCode = chartsCode;
	}
    
	@Column(name = "charts_code", nullable = false, length = 30)
	public String getChartsCode() {
		return chartsCode;
	}

	public void setChartsName(String chartsName) {
		this.chartsName = chartsName;
	}
	
	@Column(name = "charts_name", nullable = true, length = 30)
	public String getChartsName() {
		return chartsName;
	}
	
	public void setChartsTitle(String chartsTitle) {
		this.chartsTitle = chartsTitle;
	}
	
	@Column(name = "charts_title", nullable = true, length = 300)
	public String getChartsTitle() {
		return chartsTitle;
	}

	public void setChartsType(String chartsType) {
		this.chartsType = chartsType;
	}

	@Column(name = "charts_type", nullable = true, length = 30)
	public String getChartsType() {
		return chartsType;
	}

	public void setDemoUrl(String demoUrl) {
		this.demoUrl = demoUrl;
	}
	
	@Column(name = "demo_url", nullable = true, length = 240)
	public String getDemoUrl() {
		return demoUrl;
	}

	public void setChartsScript(String chartsScript) {
		this.chartsScript = chartsScript;
	}
	
	@Column(name = "charts_script", nullable = true, length = 0)
	public String getChartsScript() {
		return chartsScript;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	@Column(name = "dimensions", nullable = true, length = 30)
	public String getDimensions() {
		return dimensions;
	}

	public void setDimensionLength(String dimensionLength) {
		this.dimensionLength = dimensionLength;
	}

	@Column(name = "dimension_length", nullable = true, length = 30)
	public String getDimensionLength() {
		return dimensionLength;
	}

	public void setDataConversionScript(String dataConversionScript) {
		this.dataConversionScript = dataConversionScript;
	}

	@Column(name = "data_conversion_script", nullable = true, length = 30)
	public String getDataConversionScript() {
		return dataConversionScript;
	}

    public void setDescription(String description) {
	this.description = description;
    }

    @Column(name = "description", nullable = true, length = 240)    
    public String getDescription() {
	return description;
    }

    public void setVersionNum(Integer versionNum) {
    this.versionNum = versionNum;
    }

    @Version
    @Column(name = "version_num", nullable = true, length = 11)    
    public Integer getVersionNum() {
	return versionNum;
    }

    public void setCreatedBy(Integer createdBy) {
	this.createdBy = createdBy;
    }

    @Column(name = "created_by", nullable = true, length = 11)    
    public Integer getCreatedBy() {
	return createdBy;
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

    @Column(name = "attribute1", nullable = true, length = 150)    
    public String getAttribute1() {
	return attribute1;
    }

    public void setAttribute2(String attribute2) {
	this.attribute2 = attribute2;
    }

    @Column(name = "attribute2", nullable = true, length = 150)    
    public String getAttribute2() {
	return attribute2;
    }

    public void setAttribute3(String attribute3) {
	this.attribute3 = attribute3;
    }

    @Column(name = "attribute3", nullable = true, length = 150)    
    public String getAttribute3() {
	return attribute3;
    }

    public void setAttribute4(String attribute4) {
	this.attribute4 = attribute4;
    }

    @Column(name = "attribute4", nullable = true, length = 150)    
    public String getAttribute4() {
	return attribute4;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }
    @Column(name = "attribute5", nullable = true, length = 150)
    public String getAttribute5() {
        return attribute5;
    }
    
    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }
    @Column(name = "attribute6", nullable = true, length = 150)
    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }
    @Column(name = "attribute7", nullable = true, length = 150)
    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }
    @Column(name = "attribute8", nullable = true, length = 150)
    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }
    @Column(name = "attribute9", nullable = true, length = 150)
    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }
    @Column(name = "attribute10", nullable = true, length = 150)
    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute11(String attribute11) {
        this.attribute11 = attribute11;
    }
    @Column(name = "attribute11", nullable = true, length = 150)
    public String getAttribute11() {
        return attribute11;
    }
  
    public void setAttribute12(String attribute12) {
        this.attribute12 = attribute12;
    }
    @Column(name = "attribute12", nullable = true, length = 150)
    public String getAttribute12() {
        return attribute12;
    }
    
    public void setAttribute13(String attribute13) {
        this.attribute13 = attribute13;
    }
    @Column(name = "attribute13", nullable = true, length = 150)
    public String getAttribute13() {
        return attribute13;
    }

    public void setAttribute14(String attribute14) {
        this.attribute14 = attribute14;
    }
    @Column(name = "attribute14", nullable = true, length = 150)
    public String getAttribute14() {
        return attribute14;
    }

    public void setAttribute15(String attribute15) {
        this.attribute15 = attribute15;
    }
    @Column(name = "attribute15", nullable = true, length = 150)
    public String getAttribute15() {
        return attribute15;
    }
    @Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}
}

