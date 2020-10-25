package saaf.common.fmw.spm.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class SrmSpmIndicatorsEntity_HI_RO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static String QUERY_SUPPLIER_INFO_LIST =
					"SELECT spi.indicator_id indicatorId\n" +
					"      ,spi.indicator_code indicatorCode\n" +
					"      ,slv3.meaning AS indicatorDimensionName\n" +
					"      ,spi.indicator_dimension indicatorDimension\n" +
					"      ,slv4.meaning AS indicatorTypeName\n" +
					"      ,spi.indicator_type indicatorType\n" +
					"      ,slv5.meaning AS indicatorValueTypeName\n" +
					"      ,spi.indicator_value_type indicatorValueType\n" +
					"      ,slv6.meaning AS indicatorStatusName\n" +
					"      ,spi.indicator_status indicatorStatus\n" +
					"      ,spi.score_deducting_limit scoreDeductingLimit\n" +
					"      ,spi.score_explain scoreExplain\n" +
					"      ,spi.target_value targetValue\n" +
					"      ,spi.start_date startDate\n" +
					"      ,spi.end_date endDate\n" +
					"      ,spi.created_by createdBy\n" +
					"      ,nvl(emp1.employee_name, su1.user_full_name) AS createName\n" +
					"      ,spi.creation_date creationDate\n" +
					"      ,spi.last_updated_by lastUpdatedBy\n" +
					"      ,nvl(emp2.employee_name, su2.user_full_name) AS lastName\n" +
					"      ,spi.last_update_date lastUpdateDate\n" +
                    "      ,spi.item_Type itemType\n" +
                    "      ,slv7.meaning AS itemTypeName\n" +
					"FROM   srm_spm_indicators spi\n" +
					"LEFT   JOIN saaf_users su1\n" +
					"ON     su1.user_id = spi.created_by\n" +
					"LEFT   JOIN saaf_employees emp1\n" +
					"ON     emp1.employee_id = su1.employee_id\n" +
					"LEFT   JOIN saaf_users su2\n" +
					"ON     su2.user_id = spi.last_updated_by\n" +
					"LEFT   JOIN saaf_employees emp2\n" +
					"ON     emp2.employee_id = su2.employee_id\n" +
                    "LEFT   JOIN saaf_lookup_values slv7\n" +
                    "ON     spi.item_type = slv7.lookup_code\n" +
                    "AND    slv7.lookup_type = 'POS_SUPPLIER_TYPE',\n" +
					" saaf_lookup_values slv3, saaf_lookup_values slv4,\n" +
					" saaf_lookup_values slv5, saaf_lookup_values slv6\n" +
					"WHERE  spi.indicator_dimension = slv3.lookup_code\n" +
					"AND    slv3.lookup_type = 'SPM_INDICATOR_DIMENSION'\n" +
					"AND    spi.indicator_type = slv4.lookup_code\n" +
					"AND    slv4.lookup_type = 'SPM_INDICATOR_TYPE'\n" +
					"AND    spi.indicator_value_type = slv5.lookup_code\n" +
					"AND    slv5.lookup_type = 'SPM_INDICATOR_VALUE'\n" +
					"AND    spi.indicator_status = slv6.lookup_code\n" +
					"AND    slv6.lookup_type = 'SPM_INDICATOR_STATUS'\n";

	public static String QUERY_COUNT =
					"SELECT COUNT(1)\n" +
					"FROM   srm_spm_indicators spi\n" +
					"LEFT   JOIN saaf_users su1\n" +
					"ON     su1.user_id = spi.created_by\n" +
					"LEFT   JOIN saaf_employees emp1\n" +
					"ON     emp1.employee_id = su1.employee_id\n" +
					"LEFT   JOIN saaf_users su2\n" +
					"ON     su2.user_id = spi.last_updated_by\n" +
					"LEFT   JOIN saaf_employees emp2\n" +
					"ON     emp2.employee_id = su2.employee_id, saaf_lookup_values slv1,\n" +
					" saaf_lookup_values slv2, saaf_lookup_values slv3, saaf_lookup_values slv4,\n" +
					" saaf_lookup_values slv5, saaf_lookup_values slv6\n" +
					"WHERE  spi.indicator_code = slv1.lookup_code\n" +
					"AND    slv1.lookup_type = 'SPM_INDICATOR_NAME'\n" +
					"AND    spi.application_domain = slv2.lookup_code\n" +
					"AND    slv2.lookup_type = 'SPM_APPLICATION_DOMAIN'\n" +
					"AND    spi.indicator_dimension = slv3.lookup_code\n" +
					"AND    slv3.lookup_type = 'SPM_INDICATOR_DIMENSION'\n" +
					"AND    spi.indicator_type = slv4.lookup_code\n" +
					"AND    slv4.lookup_type = 'SPM_INDICATOR_TYPE'\n" +
					"AND    spi.indicator_value_type = slv5.lookup_code\n" +
					"AND    slv5.lookup_type = 'SPM_INDICATOR_VALUE'\n" +
					"AND    spi.indicator_status = slv6.lookup_code\n" +
					"AND    slv6.lookup_type = 'SPM_INDICATOR_STATUS'\n";

	public static String QUERY_FLAG =
					"SELECT spi.indicator_id        indicatorId\n" +
					"      ,spi.indicator_code      indicatorCode\n" +
					"      ,spi.application_domain  applicationDomain\n" +
					"      ,spi.indicator_dimension indicatorDimension\n" +
					"FROM   srm_spm_indicators spi\n" +
					"WHERE  spi.indicator_status = 'ACTIVE'\n";

	public static String QUERY_EXPORT =
					"SELECT spi.indicator_id indicatorId\n" +
					"      ,spi.indicator_code indicatorCode\n" +
					"      ,slv3.meaning AS indicatorDimensionName\n" +
					"      ,spi.indicator_dimension indicatorDimension\n" +
					"      ,slv4.meaning AS indicatorTypeName\n" +
					"      ,spi.indicator_type indicatorType\n" +
					"      ,slv5.meaning AS indicatorValueTypeName\n" +
					"      ,spi.indicator_value_type indicatorValueType\n" +
					"      ,slv6.meaning AS indicatorStatusName\n" +
					"      ,spi.indicator_status indicatorStatus\n" +
					"      ,spi.score_deducting_limit scoreDeductingLimit\n" +
					"      ,spi.score_explain scoreExplain\n" +
					"      ,spi.start_date startDate\n" +
					"      ,spi.target_value targetValue\n" +
					"      ,spi.end_date endDate\n" +
					"      ,spi.created_by createdBy\n" +
					"      ,nvl(emp1.employee_name, su1.user_full_name) AS createName\n" +
					"      ,spi.creation_date creationDate\n" +
					"      ,spi.last_updated_by lastUpdatedBy\n" +
					"      ,nvl(emp2.employee_name, su2.user_full_name) AS lastName\n" +
					"      ,spi.last_update_date lastUpdateDate\n" +
					"      ,st.indicator_item_desc AS indicatorItemDesc\n" +
					"      ,st.pk1_value AS pk1Value\n" +
					"      ,st.pk2_value AS pk2Value\n" +
					"      ,st.score AS score\n" +
					"FROM   srm_spm_indicators spi\n" +
					"LEFT   JOIN saaf_users su1\n" +
					"ON     su1.user_id = spi.created_by\n" +
					"LEFT   JOIN saaf_employees emp1\n" +
					"ON     emp1.employee_id = su1.employee_id\n" +
					"LEFT   JOIN saaf_users su2\n" +
					"ON     su2.user_id = spi.last_updated_by\n" +
					"LEFT   JOIN saaf_employees emp2\n" +
					"ON     emp2.employee_id = su2.employee_id, \n" +
					" saaf_lookup_values slv3, saaf_lookup_values slv4,\n" +
					" saaf_lookup_values slv5, saaf_lookup_values slv6,\n" +
					" srm_spm_indicator_items st\n" +
					"WHERE  spi.indicator_dimension = slv3.lookup_code\n" +
					"AND    slv3.lookup_type = 'SPM_INDICATOR_DIMENSION'\n" +
					"AND    spi.indicator_type = slv4.lookup_code\n" +
					"AND    slv4.lookup_type = 'SPM_INDICATOR_TYPE'\n" +
					"AND    spi.indicator_value_type = slv5.lookup_code\n" +
					"AND    slv5.lookup_type = 'SPM_INDICATOR_VALUE'\n" +
					"AND    spi.indicator_status = slv6.lookup_code\n" +
					"AND    slv6.lookup_type = 'SPM_INDICATOR_STATUS'\n" +
					"AND    spi.indicator_id = st.indicator_id\n";

	public static final String QUERY_SUPPLIER_INFO =
					"SELECT tt.indicatorId\n" +
					"      ,tt.indicatorName\n" +
					"FROM   (SELECT spi.indicator_id indicatorId\n" +
					"              ,slv.meaning      indicatorName\n" +
					"        FROM   srm_spm_indicators spi\n" +
					"              ,saaf_lookup_values slv\n" +
					"        WHERE  spi.indicator_code = slv.lookup_code\n" +
					"        AND    slv.lookup_type = 'SPM_INDICATOR_NAME'\n" +
					"        AND    spi.indicator_status = 'ACTIVE') tt\n" +
					"WHERE  1 = 1\n";

	private Integer indicatorId; // 指标ID
	private String indicatorCode; // 指标编码，关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_CODE）
	private String indicatorName; // 指标名称
	private String applicationDomain; // 应用领域，关联表：SAAF_LOOKUP_VALUES（SPM_APPLICATION_DOMAIN）
	private String applicationDomainName;
	private String indicatorDimension; // 指标维度，关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_DIMENSION）
	private String indicatorDimensionName;
	private String indicatorType; // 评价类型（数据来源），关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_TYPE）
	private String indicatorTypeName;
	private String indicatorValueType; // 评价项值类型，关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_VALUE）
	private String indicatorValueTypeName;
	private String indicatorStatus; // 指标状态，关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_STATUS）
	private String indicatorStatusName;
	private BigDecimal scoreDeductingLimit; // 扣分上限
	private BigDecimal targetValue;
	private String scoreExplain; // 指标逻辑描述
	@JSONField(format = "yyyy-MM-dd")
	private Date startDate; // 生效日期
	@JSONField(format = "yyyy-MM-dd")
	private Date endDate; // 失效日期
	private Integer versionNum;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	private Integer createdBy;
	private String createName;
	private Integer lastUpdatedBy;
	private String lastName;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;
	
	private String indicatorItemDesc; // 评分项描述
	private String pk1Value; // 关键值1（大于或等于）
	private String pk2Value; // 关键值2（小于）
	private BigDecimal score; // 分值

    private String itemType;
    private String itemTypeName;

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getIndicatorId() {
		return indicatorId;
	}
	public void setIndicatorId(Integer indicatorId) {
		this.indicatorId = indicatorId;
	}
	public String getIndicatorCode() {
		return indicatorCode;
	}
	public void setIndicatorCode(String indicatorCode) {
		this.indicatorCode = indicatorCode;
	}
	public String getIndicatorName() {
		return indicatorName;
	}
	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}
	public String getApplicationDomain() {
		return applicationDomain;
	}
	public void setApplicationDomain(String applicationDomain) {
		this.applicationDomain = applicationDomain;
	}
	public String getApplicationDomainName() {
		return applicationDomainName;
	}
	public void setApplicationDomainName(String applicationDomainName) {
		this.applicationDomainName = applicationDomainName;
	}
	public String getIndicatorDimension() {
		return indicatorDimension;
	}
	public void setIndicatorDimension(String indicatorDimension) {
		this.indicatorDimension = indicatorDimension;
	}
	public String getIndicatorDimensionName() {
		return indicatorDimensionName;
	}
	public void setIndicatorDimensionName(String indicatorDimensionName) {
		this.indicatorDimensionName = indicatorDimensionName;
	}
	public String getIndicatorType() {
		return indicatorType;
	}
	public void setIndicatorType(String indicatorType) {
		this.indicatorType = indicatorType;
	}
	public String getIndicatorTypeName() {
		return indicatorTypeName;
	}
	public void setIndicatorTypeName(String indicatorTypeName) {
		this.indicatorTypeName = indicatorTypeName;
	}
	public String getIndicatorValueType() {
		return indicatorValueType;
	}
	public void setIndicatorValueType(String indicatorValueType) {
		this.indicatorValueType = indicatorValueType;
	}
	public String getIndicatorValueTypeName() {
		return indicatorValueTypeName;
	}
	public void setIndicatorValueTypeName(String indicatorValueTypeName) {
		this.indicatorValueTypeName = indicatorValueTypeName;
	}
	public String getIndicatorStatus() {
		return indicatorStatus;
	}
	public void setIndicatorStatus(String indicatorStatus) {
		this.indicatorStatus = indicatorStatus;
	}
	public String getIndicatorStatusName() {
		return indicatorStatusName;
	}
	public void setIndicatorStatusName(String indicatorStatusName) {
		this.indicatorStatusName = indicatorStatusName;
	}
	public BigDecimal getScoreDeductingLimit() {
		return scoreDeductingLimit;
	}
	public void setScoreDeductingLimit(BigDecimal scoreDeductingLimit) {
		this.scoreDeductingLimit = scoreDeductingLimit;
	}
	public String getScoreExplain() {
		return scoreExplain;
	}
	public void setScoreExplain(String scoreExplain) {
		this.scoreExplain = scoreExplain;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getIndicatorItemDesc() {
		return indicatorItemDesc;
	}
	public void setIndicatorItemDesc(String indicatorItemDesc) {
		this.indicatorItemDesc = indicatorItemDesc;
	}
	public String getPk1Value() {
		return pk1Value;
	}
	public void setPk1Value(String pk1Value) {
		this.pk1Value = pk1Value;
	}
	public String getPk2Value() {
		return pk2Value;
	}
	public void setPk2Value(String pk2Value) {
		this.pk2Value = pk2Value;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public BigDecimal getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(BigDecimal targetValue) {
		this.targetValue = targetValue;
	}
	
	
	
	

}
