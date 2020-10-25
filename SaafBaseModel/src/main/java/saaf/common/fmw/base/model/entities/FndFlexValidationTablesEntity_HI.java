package saaf.common.fmw.base.model.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;


/**
 * FndFlexValidationTablesEntity_HI Entity Object
 * Fri Aug 04 15:48:43 CST 2017  Auto Generate
 */
@Entity
@Table(name = "FND_FLEX_VALIDATION_TABLES")
public class FndFlexValidationTablesEntity_HI {
    private Integer flexValidationId;
    private Integer flexValueSetId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private String applicationTableName;
    private Integer lastUpdateLogin;
    private String valueColumnName;
    private String valueColumnType;
    private Integer valueColumnSize;
    private String compiledAttributeColumnName;
    private String enabledColumnName;
    private String hierarchyLevelColumnName;
    private String startDateColumnName;
    private String endDateColumnName;
    private String summaryAllowedFlag;
    private String summaryColumnName;
    private String idColumnName;
    private Integer idColumnSize;
    private String idColumnType;
    private String meaningColumnName;
    private Integer meaningColumnSize;
    private String meaningColumnType;
    private Integer tableApplicationId;
    private String additionalQuickpickColumns;
    private String additionalWhereClause;
    private Integer operatorUserId;

    @Id
    @GeneratedValue
    @Column(name = "flex_validation_id", nullable = false, length = 11)
    public Integer getFlexValidationId() {
        return flexValidationId;
    }

    public void setFlexValidationId(Integer flexValidationId) {
        this.flexValidationId = flexValidationId;
    }


    public void setFlexValueSetId(Integer flexValueSetId) {
        this.flexValueSetId = flexValueSetId;
    }

    @Column(name = "flex_value_set_id", nullable = false, length = 11)
    public Integer getFlexValueSetId() {
        return flexValueSetId;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name = "last_update_date", nullable = false, length = 0)
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "last_updated_by", nullable = false, length = 11)
    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
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

    public void setApplicationTableName(String applicationTableName) {
        this.applicationTableName = applicationTableName;
    }

    @Column(name = "application_table_name", nullable = false, length = 240)
    public String getApplicationTableName() {
        return applicationTableName;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    @Column(name = "last_update_login", nullable = true, length = 11)
    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setValueColumnName(String valueColumnName) {
        this.valueColumnName = valueColumnName;
    }

    @Column(name = "value_column_name", nullable = false, length = 240)
    public String getValueColumnName() {
        return valueColumnName;
    }

    public void setValueColumnType(String valueColumnType) {
        this.valueColumnType = valueColumnType;
    }

    @Column(name = "value_column_type", nullable = false, length = 1)
    public String getValueColumnType() {
        return valueColumnType;
    }

    public void setValueColumnSize(Integer valueColumnSize) {
        this.valueColumnSize = valueColumnSize;
    }

    @Column(name = "value_column_size", nullable = false, length = 11)
    public Integer getValueColumnSize() {
        return valueColumnSize;
    }

    public void setCompiledAttributeColumnName(String compiledAttributeColumnName) {
        this.compiledAttributeColumnName = compiledAttributeColumnName;
    }

    @Column(name = "compiled_attribute_column_name", nullable = true, length = 240)
    public String getCompiledAttributeColumnName() {
        return compiledAttributeColumnName;
    }

    public void setEnabledColumnName(String enabledColumnName) {
        this.enabledColumnName = enabledColumnName;
    }

    @Column(name = "enabled_column_name", nullable = true, length = 240)
    public String getEnabledColumnName() {
        return enabledColumnName;
    }

    public void setHierarchyLevelColumnName(String hierarchyLevelColumnName) {
        this.hierarchyLevelColumnName = hierarchyLevelColumnName;
    }

    @Column(name = "hierarchy_level_column_name", nullable = true, length = 240)
    public String getHierarchyLevelColumnName() {
        return hierarchyLevelColumnName;
    }

    public void setStartDateColumnName(String startDateColumnName) {
        this.startDateColumnName = startDateColumnName;
    }

    @Column(name = "start_date_column_name", nullable = true, length = 240)
    public String getStartDateColumnName() {
        return startDateColumnName;
    }

    public void setEndDateColumnName(String endDateColumnName) {
        this.endDateColumnName = endDateColumnName;
    }

    @Column(name = "end_date_column_name", nullable = true, length = 240)
    public String getEndDateColumnName() {
        return endDateColumnName;
    }

    public void setSummaryAllowedFlag(String summaryAllowedFlag) {
        this.summaryAllowedFlag = summaryAllowedFlag;
    }

    @Column(name = "summary_allowed_flag", nullable = true, length = 1)
    public String getSummaryAllowedFlag() {
        return summaryAllowedFlag;
    }

    public void setSummaryColumnName(String summaryColumnName) {
        this.summaryColumnName = summaryColumnName;
    }

    @Column(name = "summary_column_name", nullable = true, length = 240)
    public String getSummaryColumnName() {
        return summaryColumnName;
    }

    public void setIdColumnName(String idColumnName) {
        this.idColumnName = idColumnName;
    }

    @Column(name = "id_column_name", nullable = true, length = 240)
    public String getIdColumnName() {
        return idColumnName;
    }

    public void setIdColumnSize(Integer idColumnSize) {
        this.idColumnSize = idColumnSize;
    }

    @Column(name = "id_column_size", nullable = true, length = 11)
    public Integer getIdColumnSize() {
        return idColumnSize;
    }

    public void setIdColumnType(String idColumnType) {
        this.idColumnType = idColumnType;
    }

    @Column(name = "id_column_type", nullable = true, length = 1)
    public String getIdColumnType() {
        return idColumnType;
    }

    public void setMeaningColumnName(String meaningColumnName) {
        this.meaningColumnName = meaningColumnName;
    }

    @Column(name = "meaning_column_name", nullable = true, length = 240)
    public String getMeaningColumnName() {
        return meaningColumnName;
    }

    public void setMeaningColumnSize(Integer meaningColumnSize) {
        this.meaningColumnSize = meaningColumnSize;
    }

    @Column(name = "meaning_column_size", nullable = true, length = 11)
    public Integer getMeaningColumnSize() {
        return meaningColumnSize;
    }

    public void setMeaningColumnType(String meaningColumnType) {
        this.meaningColumnType = meaningColumnType;
    }

    @Column(name = "meaning_column_type", nullable = true, length = 1)
    public String getMeaningColumnType() {
        return meaningColumnType;
    }

    public void setTableApplicationId(Integer tableApplicationId) {
        this.tableApplicationId = tableApplicationId;
    }

    @Column(name = "table_application_id", nullable = true, length = 11)
    public Integer getTableApplicationId() {
        return tableApplicationId;
    }

    public void setAdditionalQuickpickColumns(String additionalQuickpickColumns) {
        this.additionalQuickpickColumns = additionalQuickpickColumns;
    }

    @Column(name = "additional_quickpick_columns", nullable = true, length = 240)
    public String getAdditionalQuickpickColumns() {
        return additionalQuickpickColumns;
    }

    public void setAdditionalWhereClause(String additionalWhereClause) {
        this.additionalWhereClause = additionalWhereClause;
    }

    @Column(name = "additional_where_clause", nullable = true, length = 0)
    public String getAdditionalWhereClause() {
        return additionalWhereClause;
    }
    
    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }
}

