package saaf.common.fmw.base.model.entities.readonly;

import java.io.Serializable;

public class FndFlexValuesEntity_HI_RO implements Serializable {

	public FndFlexValuesEntity_HI_RO() {
		super();
	}

	public static String QUERY_SQL_M = "SELECT\r\n"
			+ "	t.FLEX_VALUE_SET_ID flexValueSetId,\r\n"
			+ "	t.FLEX_VALUE_ID flexValueId,\r\n"
			+ "	t.FLEX_VALUE flexValue,\r\n"
			+ "	t.FLEX_VALUE_MEANING flexValueMeaning,\r\n"
			+ "	t.DESCRIPTION description,\r\n"
			+ "	DATE_FORMAT(t.LAST_UPDATE_DATE,\"%Y-%m-%d %T\") lastUpdateDate,\r\n"
			+ "	t.LAST_UPDATED_BY lastUpdatedBy,\r\n"
			+ "	DATE_FORMAT(t.CREATION_DATE,\"%Y-%m-%d %T\") creationDate,\r\n"
			+ "	t.CREATED_BY createdBy,\r\n"
			+ "	t.LAST_UPDATE_LOGIN lastUpdateLogin,\r\n"
			+ "	t.ENABLED_FLAG enabledFlag,\r\n"
			+ "	t.SUMMARY_FLAG summaryFlag,\r\n"
			+ "	DATE_FORMAT(t.START_DATE_ACTIVE,\"%Y-%m-%d %T\") startDateActive,\r\n"
			+ "	DATE_FORMAT(t.END_DATE_ACTIVE,\"%Y-%m-%d %T\") endDateActive,\r\n"
			+ "	t.PARENT_FLEX_VALUE_LOW parentFlexValueLow,\r\n"
			+ "	t.PARENT_FLEX_VALUE_HIGH parentFlexValueHigh,\r\n"
			+ "	ffvs.DESCRIPTION parentFlexValueHighMeaning\r\n"
			+ "FROM\r\n"
			+ "	fnd_flex_values t\r\n"
			+ "	LEFT JOIN fnd_flex_value_sets ffvs ON t.PARENT_FLEX_VALUE_HIGH = ffvs.FLEX_VALUE_SET_NAME \r\n"
			+ "WHERE\r\n" + "	1 = 1 ";

	public static String QUERY_SQL = "SELECT\r\n"
			+ "	t.FLEX_VALUE idColumnName,\r\n"
			+ "	t.FLEX_VALUE_MEANING valueColumnName,\r\n"
			+ "	t.DESCRIPTION meaningColumnName\r\n"
			+ "FROM\r\n"
			+ "	fnd_flex_values t\r\n"
			+ "WHERE\r\n"
			+ "	1 = 1\r\n"
			+ "and t.enabled_flag = 'Y'\r\n"
			+ "and date_format(t.start_date_active,'%Y-%m-%d') <=CURDATE()\r\n"
			+ "and (t.end_date_active is null or date_format(t.end_date_active,'%Y-%m-%d') >= CURDATE()) ";
	private Integer flexValueSetId;
	private Integer flexValueId;
	private String flexValue;
	private String flexValueMeaning;
	private String description;
	private String lastUpdateDate;
	private Integer lastUpdatedBy;
	private String creationDate;
	private Integer createdBy;
	private Integer lastUpdateLogin;
	private String enabledFlag;
	private String summaryFlag;
	private String startDateActive;
	private String endDateActive;
	private String parentFlexValueLow;
	private String parentFlexValueHigh;
	private String parentFlexValueHighMeaning;

	public Integer getFlexValueSetId() {
		return flexValueSetId;
	}

	public void setFlexValueSetId(Integer flexValueSetId) {
		this.flexValueSetId = flexValueSetId;
	}

	public Integer getFlexValueId() {
		return flexValueId;
	}

	public void setFlexValueId(Integer flexValueId) {
		this.flexValueId = flexValueId;
	}

	public String getFlexValue() {
		return flexValue;
	}

	public void setFlexValue(String flexValue) {
		this.flexValue = flexValue;
	}

	public String getFlexValueMeaning() {
		return flexValueMeaning;
	}

	public void setFlexValueMeaning(String flexValueMeaning) {
		this.flexValueMeaning = flexValueMeaning;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public String getSummaryFlag() {
		return summaryFlag;
	}

	public void setSummaryFlag(String summaryFlag) {
		this.summaryFlag = summaryFlag;
	}

	public String getStartDateActive() {
		return startDateActive;
	}

	public void setStartDateActive(String startDateActive) {
		this.startDateActive = startDateActive;
	}

	public String getEndDateActive() {
		return endDateActive;
	}

	public void setEndDateActive(String endDateActive) {
		this.endDateActive = endDateActive;
	}

	public String getParentFlexValueLow() {
		return parentFlexValueLow;
	}

	public void setParentFlexValueLow(String parentFlexValueLow) {
		this.parentFlexValueLow = parentFlexValueLow;
	}

	public String getParentFlexValueHigh() {
		return parentFlexValueHigh;
	}

	public void setParentFlexValueHigh(String parentFlexValueHigh) {
		this.parentFlexValueHigh = parentFlexValueHigh;
	}

	public String getParentFlexValueHighMeaning() {
		return parentFlexValueHighMeaning;
	}

	public void setParentFlexValueHighMeaning(String parentFlexValueHighMeaning) {
		this.parentFlexValueHighMeaning = parentFlexValueHighMeaning;
	}
}
