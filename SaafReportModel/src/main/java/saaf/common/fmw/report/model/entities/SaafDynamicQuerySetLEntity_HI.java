package saaf.common.fmw.report.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
@Table(name = "saaf_dynamic_query_set_l")
public class SaafDynamicQuerySetLEntity_HI {
    private Integer querySetLineId;
    private Integer querySetHeaderId;
    private String querySetNum;
    private Integer columnSeq;
    private String columnName;
    private String columnTital;
    private Integer columnDisplayWidth;
    private String columnType;
    private String conditionFlag;
    private String rangeFlag;
    private String conditionExpression;
    private String conditionDefaultValue;
    private String conditionDefaultValueTo;
    private String conditionRequired;
    private String conditionRequiredTo;
    private String conditionRegion;
    private String lookupFlag;
    private String lookupType;
    private String listFlag;
    private String listDataSource;
    private String listValue;
    private String listValueName;
    private String listDisplay;
    private String listDisplayName;
    private String countFlag;
    private String sumFlag;
    private String avgFlag;
    private String displayFlag;
    private String enabledFlag;
    private String dynamicParamFlag;
    private String functionUrl;
    private String flexValueFlag;
    private String fvDisplayFlag;
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
    private String listFlexValueSetName;
   

    public void setQuerySetLineId(Integer querySetLineId) {
	this.querySetLineId = querySetLineId;
    }

    @Id    
    @GeneratedValue  
    @Column(name = "query_set_line_id", nullable = false, length = 11)    
    public Integer getQuerySetLineId() {
	return querySetLineId;
    }

    public void setQuerySetHeaderId(Integer querySetHeaderId) {
	this.querySetHeaderId = querySetHeaderId;
    }

    @Column(name = "query_set_header_id", nullable = true, length = 11)    
    public Integer getQuerySetHeaderId() {
	return querySetHeaderId;
    }
    
    public void setQuerySetNum(String querySetNum) {
	this.querySetNum = querySetNum;
    }

    @Column(name = "query_set_num", nullable = true, length = 30)    
    public String getQuerySetNum() {
	return querySetNum;
    }

    public void setColumnSeq(Integer columnSeq) {
	this.columnSeq = columnSeq;
    }

    @Column(name = "column_seq", nullable = true, length = 11)    
    public Integer getColumnSeq() {
	return columnSeq;
    }

    public void setColumnName(String columnName) {
	this.columnName = columnName;
    }

    @Column(name = "column_name", nullable = true, length = 30)    
    public String getColumnName() {
	return columnName;
    }

    public void setColumnTital(String columnTital) {
	this.columnTital = columnTital;
    }

    @Column(name = "column_tital", nullable = true, length = 50)    
    public String getColumnTital() {
	return columnTital;
    }

    public void setColumnDisplayWidth(Integer columnDisplayWidth) {
	this.columnDisplayWidth = columnDisplayWidth;
    }

    @Column(name = "column_display_width", nullable = true, length = 11)    
    public Integer getColumnDisplayWidth() {
	return columnDisplayWidth;
    }

    public void setColumnType(String columnType) {
	this.columnType = columnType;
    }

    @Column(name = "column_type", nullable = true, length = 30)    
    public String getColumnType() {
	return columnType;
    }

    public void setConditionFlag(String conditionFlag) {
	this.conditionFlag = conditionFlag;
    }

    @Column(name = "condition_flag", nullable = true, length = 30)    
    public String getConditionFlag() {
	return conditionFlag;
    }

	public void setConditionExpression(String conditionExpression) {
		this.conditionExpression = conditionExpression;
	}

	@Column(name = "condition_expression", nullable = true, length = 10)
    public String getConditionExpression() {
		return conditionExpression;
	}

	public void setConditionDefaultValue(String conditionDefaultValue) {
	this.conditionDefaultValue = conditionDefaultValue;
    }

    @Column(name = "condition_default_value", nullable = true, length = 100)    
    public String getConditionDefaultValue() {
	return conditionDefaultValue;
    }

    public void setConditionDefaultValueTo(String conditionDefaultValueTo) {
	this.conditionDefaultValueTo = conditionDefaultValueTo;
    }

    @Column(name = "condition_default_value_to", nullable = true, length = 100)    
    public String getConditionDefaultValueTo() {
	return conditionDefaultValueTo;
    }

	public void setConditionRequired(String conditionRequired) {
	this.conditionRequired = conditionRequired;
	}

    @Column(name = "condition_required", nullable = true, length = 30)
    public String getConditionRequired() {
	return conditionRequired;
	}
    
	public void setConditionRequiredTo(String conditionRequiredTo) {
	this.conditionRequiredTo = conditionRequiredTo;
	}

	@Column(name = "condition_required_to", nullable = true, length = 30)
	public String getConditionRequiredTo() {
	return conditionRequiredTo;
	}

	public void setConditionRegion(String conditionRegion) {
	this.conditionRegion = conditionRegion;
    }

    @Column(name = "condition_region", nullable = true, length = 30)    
    public String getConditionRegion() {
	return conditionRegion;
    }

	public void setLookupFlag(String lookupFlag) {
		this.lookupFlag = lookupFlag;
	}
	
	@Column(name = "lookup_flag", nullable = true, length = 30) 
	public String getLookupFlag() {
		return lookupFlag;
	}

	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}

	@Column(name = "lookup_type", nullable = true, length = 30) 
	public String getLookupType() {
		return lookupType;
	}

	public void setListFlag(String listFlag) {
	this.listFlag = listFlag;
    }

    @Column(name = "list_flag", nullable = true, length = 30)    
    public String getListFlag() {
	return listFlag;
    }

    public void setRangeFlag(String rangeFlag) {
	this.rangeFlag = rangeFlag;
    }

    @Column(name = "range_flag", nullable = true, length = 30)    
    public String getRangeFlag() {
	return rangeFlag;
    }

    public void setListDataSource(String listDataSource) {
	this.listDataSource = listDataSource;
    }

    @Column(name = "list_data_source", nullable = true, length = 0)    
    public String getListDataSource() {
	return listDataSource;
    }

    public void setListValue(String listValue) {
	this.listValue = listValue;
    }

    @Column(name = "list_value", nullable = true, length = 30)    
    public String getListValue() {
	return listValue;
    }

    public void setListValueName(String listValueName) {
	this.listValueName = listValueName;
    }

    @Column(name = "list_value_name", nullable = true, length = 30)    
    public String getListValueName() {
	return listValueName;
    }

    public void setListDisplay(String listDisplay) {
	this.listDisplay = listDisplay;
    }

    @Column(name = "list_display", nullable = true, length = 30)    
    public String getListDisplay() {
	return listDisplay;
    }
        
    public void setListDisplayName(String listDisplayName) {
	this.listDisplayName = listDisplayName;
    }

    @Column(name = "list_display_name", nullable = true, length = 30)    
    public String getListDisplayName() {
	return listDisplayName;
    }

    public void setCountFlag(String countFlag) {
	this.countFlag = countFlag;
    }

    @Column(name = "count_flag", nullable = true, length = 30)    
    public String getCountFlag() {
	return countFlag;
    }

    public void setSumFlag(String sumFlag) {
	this.sumFlag = sumFlag;
    }

    @Column(name = "sum_flag", nullable = true, length = 30)    
    public String getSumFlag() {
	return sumFlag;
    }

    public void setAvgFlag(String avgFlag) {
	this.avgFlag = avgFlag;
    }

    @Column(name = "avg_flag", nullable = true, length = 30)    
    public String getAvgFlag() {
	return avgFlag;
    }
    
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}
	
	@Column(name = "display_flag", nullable = true, length = 30)  
	public String getDisplayFlag() {
		return displayFlag;
	}

    public void setEnabledFlag(String enabledFlag) {
	this.enabledFlag = enabledFlag;
    }

    @Column(name = "enabled_flag", nullable = true, length = 30)    
    public String getEnabledFlag() {
	return enabledFlag;
    }
    
    @Column(name = "dynamic_param_flag", nullable = true, length = 30)
    public String getDynamicParamFlag() {
		return dynamicParamFlag;
	}

	public void setDynamicParamFlag(String dynamicParamFlag) {
		this.dynamicParamFlag = dynamicParamFlag;
	}

	@Column(name = "function_url", nullable = true, length = 255)
	public String getFunctionUrl() {
		return functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	@Column(name = "flex_value_flag", nullable = true, length = 30)
	public String getFlexValueFlag() {
		return flexValueFlag;
	}

	public void setFlexValueFlag(String flexValueFlag) {
		this.flexValueFlag = flexValueFlag;
	}

	@Column(name = "fv_display_flag", nullable = true, length = 30)
	public String getFvDisplayFlag() {
		return fvDisplayFlag;
	}

	public void setFvDisplayFlag(String fvDisplayFlag) {
		this.fvDisplayFlag = fvDisplayFlag;
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
	
	@Column(name = "list_flex_value_set_name",length = 60)
    public String getListFlexValueSetName() {
        return listFlexValueSetName;
    }

    public void setListFlexValueSetName(String listFlexValueSetName) {
        this.listFlexValueSetName = listFlexValueSetName;
    }
}

