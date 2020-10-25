package saaf.common.fmw.spm.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SrmSpmPerformanceEvaluateEntity_HI_RO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String QUERY_PERFORMANCE_EVALUATE_INFO =
			"SELECT Evaluate_Id              AS evaluateId             \n" +
			"      ,Performance_Id           AS performanceId          \n" +
			"      ,Performance_Line_Id      AS performanceLineId     \n" +
			"      ,Performance_Indicator_Id AS performanceIndicatorId\n" +
			"      ,Evaluate_Employee_Id     AS evaluateEmployeeId    \n" +
			"      ,Score                    AS score                   \n" +
			"      ,Evaluate_Date            AS evaluateDate           \n" +
			"      ,Evaluate_Comment         AS evaluateComment        \n" +
			"      ,Submit_Flag              AS submitFlag             \n" +
			"      ,Submit_Date              AS submitDate             \n" +
			"      ,Version_Num              AS versionNum             \n" +
			"      ,Creation_Date            AS creationDate           \n" +
			"      ,Created_By               AS createdBy              \n" +
			"      ,Last_Updated_By          AS lastUpdatedBy         \n" +
			"      ,Last_Update_Date         AS lastUpdateDate        \n" +
			"      ,Last_Update_Login        AS lastUpdateLogin       \n" +
			"      ,Attribute_Category       AS attributeCategory      \n" +
			"      ,Attribute1               AS attribute1              \n" +
			"      ,Attribute2               AS attribute2              \n" +
			"      ,Attribute3               AS attribute3              \n" +
			"      ,Attribute4               AS attribute4              \n" +
			"      ,Attribute5               AS attribute5              \n" +
			"      ,Attribute6               AS attribute6              \n" +
			"      ,Attribute7               AS attribute7              \n" +
			"      ,Attribute8               AS attribute8              \n" +
			"      ,Attribute9               AS attribute9              \n" +
			"      ,Attribute10              AS attribute10             \n" +
			"      ,Attribute11              AS attribute11             \n" +
			"      ,Attribute12              AS attribute12             \n" +
			"      ,Attribute13              AS attribute13             \n" +
			"      ,Attribute14              AS attribute14             \n" +
			"      ,Attribute15              AS attribute15             \n" +
			"      ,Evaluate_User_Id         AS evaluateUserId        \n" +
			"  FROM Srm_Spm_Performance_Evaluate Spe\n" +
			" WHERE 1 = 1 \n";


	public static String QUERY_PERFORMANCE_EVALUATE_LIST =
			"      SELECT Spe.Evaluate_Id        AS evaluateId\n" +
            "            ,Spi.Tpl_Id             AS tplId           \n" +
            "            ,Spi.Performance_Indicator_Id AS performanceIndicatorId\n" +
            "            ,Std.Tpl_Dimension_Id   AS tplDimensionId  \n" +
            "            ,Std.Evaluate_Dimension AS evaluateDimension\n" +
            "            ,Slv1.Meaning           AS evaluateDimensionName\n" +
            "            ,Std.Dimension_Weight   AS dimensionWeight  \n" +
            "            ,Sti.Tpl_Indicator_Id   AS tplIndicatorId  \n" +
            "            ,Sti.Indicator_Id       AS indicatorId      \n" +
            "            ,Spi.Indicator_Code     AS indicatorCode    \n" +
            "            ,Spi.Score_Explain      AS scoreExplain     \n" +
            "            ,Sti.Indicator_Weight   AS indicatorWeight\n" +
            "            ,Spe.score              AS score\n" +
            "            ,Spe.Evaluate_Date      AS evaluateDate   \n" +
            "            ,Spe.Evaluate_Comment   AS evaluateComment\n" +
            "            ,Spe.Submit_Flag        AS submitFlag     \n" +
            "            ,Spe.Submit_Date        AS submitDate     \n" +
            "        FROM srm_spm_performance_evaluate spe\n" +
            "            ,Srm_Spm_Performance_Indicators Spi\n" +
            "            ,Srm_Spm_Tpl_Dimension   Std\n" +
            "        LEFT JOIN Saaf_Lookup_Values Slv1\n" +
            "          ON Std.Evaluate_Dimension = Slv1.Lookup_Code\n" +
            "         AND Slv1.Lookup_Type = 'SPM_INDICATOR_DIMENSION',\n" +
            "       Srm_Spm_Tpl_Indicators Sti, Srm_Spm_Indicators Spi\n" +
            "       WHERE Spe.Performance_Indicator_Id = spi.performance_indicator_id\n" +
            "         AND Spi.Tpl_Dimension_Id = Std.Tpl_Dimension_Id\n" +
            "         AND Spi.Tpl_Indicator_Id = Sti.Tpl_Indicator_Id\n" +
            "         AND Sti.Indicator_Id = Spi.Indicator_Id";

	public static String QUERY_PERFORMANCE_EVALUATE_EXPORT_LIST =
			"SELECT Spe.Evaluate_Id        AS evaluateId\n" +
			"      ,Psi.Supplier_Number    AS supplierNumber\n" +
			"      ,Psi.Supplier_Name      AS supplierName\n" +
			"      ,Spi.Tpl_Id             AS tplId           \n" +
			"      ,Spi.Performance_Indicator_Id AS performanceIndicatorId\n" +
			"      ,Std.Tpl_Dimension_Id   AS tplDimensionId  \n" +
			"      ,Std.Evaluate_Dimension AS evaluateDimension\n" +
			"      ,Slv1.Meaning           AS evaluateDimensionName\n" +
			"      ,Std.Dimension_Weight   AS dimensionWeight  \n" +
			"      ,Sti.Tpl_Indicator_Id   AS tplIndicatorId  \n" +
			"      ,Sti.Indicator_Id       AS indicatorId      \n" +
			"      ,Spi.Indicator_Code     AS indicatorCode    \n" +
			"      ,Spi.Score_Explain      AS scoreExplain     \n" +
			"      ,Sti.Indicator_Weight   AS indicatorWeight\n" +
			"      ,Spe.score              AS score\n" +
			"      ,Spe.Evaluate_Date      AS evaluateDate   \n" +
			"      ,Spe.Evaluate_Comment   AS evaluateComment\n" +
			"      ,Spe.Submit_Flag        AS submitFlag     \n" +
			"      ,Spe.Submit_Date        AS submitDate  \n" +
			"      ,Spe.Evaluate_User_Id   \n" +
			"  FROM srm_spm_performance_evaluate Spe\n" +
			"      ,Srm_Spm_Performance_Lines Spl\n" +
			"      ,Srm_Pos_Supplier_Info Psi\n" +
			"      ,Srm_Spm_Performance_Indicators Spi\n" +
			"      ,Srm_Spm_Tpl_Dimension   Std\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv1\n" +
			"    ON Std.Evaluate_Dimension = Slv1.Lookup_Code\n" +
			"   AND Slv1.Lookup_Type = 'SPM_INDICATOR_DIMENSION',\n" +
			" Srm_Spm_Tpl_Indicators Sti, Srm_Spm_Indicators Spi\n" +
			" WHERE Spe.Performance_Line_Id = Spl.Performance_Line_Id\n" +
			"   AND Spl.Performance_Line_Id = Spi.Performance_Line_Id\n" +
			"   AND Spl.Supplier_Id = Psi.Supplier_Id\n" +
			"   AND Spe.Performance_Indicator_Id = spi.performance_indicator_id\n" +
			"   AND Spi.Tpl_Dimension_Id = Std.Tpl_Dimension_Id\n" +
			"   AND Spi.Tpl_Indicator_Id = Sti.Tpl_Indicator_Id\n" +
			"   AND Sti.Indicator_Id = Spi.Indicator_Id";

    public static String QUERY_PERFORMANCE_MONITOR_SQL =
            "SELECT Spe.Evaluate_User_Id AS evaluateUserId\n" +
                    "      ,(SELECT Nvl(Se.Employee_Name\n" +
                    "                 ,Su.User_Name)\n" +
                    "         FROM Saaf_Users Su\n" +
                    "         LEFT JOIN Saaf_Employees Se\n" +
                    "           ON Su.Employee_Id = Se.Employee_Id\n" +
                    "        WHERE Su.User_Id = Spe.Evaluate_User_Id) AS evaluateEmployeeName\n" +
                    "      ,DECODE(MIN(Spe.Submit_Flag),'Y','提交','拟定') AS status\n" +
                    "      ,MIN(Spe.Evaluate_Date) AS evaluateDate\n" +
                    "      ,MIN(Spe.Submit_Date) AS submitDate\n" +
                    "  FROM Srm_Spm_Performance_Evaluate Spe\n" +
                    " WHERE Spe.Performance_Id = :performanceId\n" +
                    " GROUP BY Spe.Evaluate_User_Id\n";

	public static String FIND_PERFORMANCE_EVALUATE_ID =
			"SELECT Spe.Evaluate_Id        AS evaluateId\n" +
			"  FROM srm_spm_performance_evaluate Spe\n" +
			"      ,Srm_Spm_Performance_Lines Spl\n" +
			"      ,Srm_Pos_Supplier_Info Psi\n" +
			"      ,Srm_Spm_Performance_Indicators Spi\n" +
			"      ,Srm_Spm_Tpl_Dimension   Std\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv1\n" +
			"    ON Std.Evaluate_Dimension = Slv1.Lookup_Code\n" +
			"   AND Slv1.Lookup_Type = 'SPM_INDICATOR_DIMENSION',\n" +
			" Srm_Spm_Tpl_Indicators Sti, Srm_Spm_Indicators Spi\n" +
			" WHERE Spe.Performance_Line_Id = Spl.Performance_Line_Id\n" +
			"   AND Spl.Performance_Line_Id = Spi.Performance_Line_Id\n" +
			"   AND Spl.Supplier_Id = Psi.Supplier_Id\n" +
			"   AND Spe.Performance_Indicator_Id = spi.performance_indicator_id\n" +
			"   AND Spi.Tpl_Dimension_Id = Std.Tpl_Dimension_Id\n" +
			"   AND Spi.Tpl_Indicator_Id = Sti.Tpl_Indicator_Id\n" +
			"   AND Sti.Indicator_Id = Spi.Indicator_Id";

	public static String QUERY_PERFORMANCE_EVALUATE =
			"SELECT Spe.Evaluate_Id              AS evaluateId             \n" +
			"      ,Spe.Performance_Id           AS performanceId          \n" +
			"      ,Spe.Performance_Line_Id      AS performanceLineId     \n" +
			"      ,Spe.Performance_Indicator_Id AS performanceIndicatorId\n" +
			"      ,Spe.Evaluate_Employee_Id     AS evaluateEmployeeId    \n" +
			"      ,Spe.Score                    AS score                   \n" +
			"      ,Spe.Evaluate_Date            AS evaluateDate           \n" +
			"      ,Spe.Evaluate_Comment         AS evaluateComment        \n" +
			"      ,Spe.Submit_Flag              AS submitFlag             \n" +
			"      ,Spe.Submit_Date              AS submitDate             \n" +
			"      ,Spe.Version_Num              AS versionNum             \n" +
			"      ,Spe.Creation_Date            AS creationDate           \n" +
			"      ,Spe.Created_By               AS createdBy              \n" +
			"      ,Spe.Last_Updated_By          AS lastUpdatedBy         \n" +
			"      ,Spe.Last_Update_Date         AS lastUpdateDate        \n" +
			"      ,Spe.Last_Update_Login        AS lastUpdateLogin       \n" +
			"      ,Spe.Attribute_Category       AS attributeCategory      \n" +
			"      ,Spe.Attribute1               AS attribute1              \n" +
			"      ,Spe.Attribute2               AS attribute2              \n" +
			"      ,Spe.Attribute3               AS attribute3              \n" +
			"      ,Spe.Attribute4               AS attribute4              \n" +
			"      ,Spe.Attribute5               AS attribute5              \n" +
			"      ,Spe.Attribute6               AS attribute6              \n" +
			"      ,Spe.Attribute7               AS attribute7              \n" +
			"      ,Spe.Attribute8               AS attribute8              \n" +
			"      ,Spe.Attribute9               AS attribute9              \n" +
			"      ,Spe.Attribute10              AS attribute10             \n" +
			"      ,Spe.Attribute11              AS attribute11             \n" +
			"      ,Spe.Attribute12              AS attribute12             \n" +
			"      ,Spe.Attribute13              AS attribute13             \n" +
			"      ,Spe.Attribute14              AS attribute14             \n" +
			"      ,Spe.Attribute15              AS attribute15             \n" +
			"      ,Spe.Evaluate_User_Id         AS evaluateUserId        \n" +
			"      ,Nvl(Se.Employee_Name\n" +
			"          ,Su.User_Name) AS evaluateEmployeeName\n" +
			"  FROM Srm_Spm_Performance_Evaluate Spe\n" +
			"      ,Saaf_Users                   Su\n" +
			"  LEFT JOIN Saaf_Employees Se\n" +
			"    ON Su.Employee_Id = Se.Employee_Id\n" +
			" WHERE Spe.Evaluate_User_Id = Su.User_Id\n";

	public static String QUERY_PERFORMANCE_EVALUATE_PERSON =
			"SELECT Spe.Evaluate_User_Id AS evaluateUserId\n" +
			"      ,Nvl(Se.Employee_Name\n" +
			"          ,Su.User_Name) AS evaluateEmployeeName\n" +
			"  FROM Srm_Spm_Performance_Evaluate Spe\n" +
			"      ,Saaf_Users                   Su\n" +
			"  LEFT JOIN Saaf_Employees Se\n" +
			"    ON Su.Employee_Id = Se.Employee_Id\n" +
			" WHERE Spe.Evaluate_User_Id = Su.User_Id\n";

    public static String QUERY_PERFORMANCE_EVALUATE_ID =
            "SELECT Spe.Evaluate_Id AS EvaluateId\n" +
            "  FROM Srm_Spm_Performance_Evaluate Spe\n" +
            " WHERE 1 = 1 ";


	private Integer evaluateId; //绩效评分ID
	private Integer performanceId; //绩效头ID
	private Integer performanceLineId; //绩效行ID
	private Integer performanceIndicatorId; //绩效指标ID
	private Integer evaluateUserId; //绩效评分用户ID
	private Integer evaluateEmployeeId; //绩效评分员工ID
	private BigDecimal score; //分数
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date evaluateDate; //评分日期
	private String evaluateComment; //说明
    private String submitFlag; //是否已提交
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date submitDate; //提交日期
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
	private String attribute11;
	private String attribute12;
	private String attribute13;
	private String attribute14;
	private String attribute15;

	//add
	private Integer tplId;
	private Integer tplDimensionId;
	private String evaluateDimension;
	private String evaluateDimensionName;
	private BigDecimal dimensionWeight;
	private Integer tplIndicatorId;
	private Integer indicatorId;
	private String indicatorCode;
	private String scoreExplain;
	private BigDecimal indicatorWeight;
    private String evaluateEmployeeName;
    private String status;
	private String supplierNumber;
	private String supplierName;




	public Integer getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(Integer evaluateId) {
		this.evaluateId = evaluateId;
	}

	public Integer getPerformanceId() {
		return performanceId;
	}

	public void setPerformanceId(Integer performanceId) {
		this.performanceId = performanceId;
	}

	public Integer getPerformanceLineId() {
		return performanceLineId;
	}

	public void setPerformanceLineId(Integer performanceLineId) {
		this.performanceLineId = performanceLineId;
	}

	public Integer getPerformanceIndicatorId() {
		return performanceIndicatorId;
	}

	public void setPerformanceIndicatorId(Integer performanceIndicatorId) {
		this.performanceIndicatorId = performanceIndicatorId;
	}

	public Integer getEvaluateUserId() {
		return evaluateUserId;
	}

	public void setEvaluateUserId(Integer evaluateUserId) {
		this.evaluateUserId = evaluateUserId;
	}

	public Integer getEvaluateEmployeeId() {
		return evaluateEmployeeId;
	}

	public void setEvaluateEmployeeId(Integer evaluateEmployeeId) {
		this.evaluateEmployeeId = evaluateEmployeeId;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Date getEvaluateDate() {
		return evaluateDate;
	}

	public void setEvaluateDate(Date evaluateDate) {
		this.evaluateDate = evaluateDate;
	}

	public String getEvaluateComment() {
		return evaluateComment;
	}

	public void setEvaluateComment(String evaluateComment) {
		this.evaluateComment = evaluateComment;
	}

    public String getSubmitFlag() {
        return submitFlag;
    }

    public void setSubmitFlag(String submitFlag) {
        this.submitFlag = submitFlag;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
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

	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	public String getAttribute15() {
		return attribute15;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	public Integer getTplId() {
		return tplId;
	}

	public void setTplId(Integer tplId) {
		this.tplId = tplId;
	}

	public Integer getTplDimensionId() {
		return tplDimensionId;
	}

	public void setTplDimensionId(Integer tplDimensionId) {
		this.tplDimensionId = tplDimensionId;
	}

	public String getEvaluateDimension() {
		return evaluateDimension;
	}

	public void setEvaluateDimension(String evaluateDimension) {
		this.evaluateDimension = evaluateDimension;
	}

	public String getEvaluateDimensionName() {
		return evaluateDimensionName;
	}

	public void setEvaluateDimensionName(String evaluateDimensionName) {
		this.evaluateDimensionName = evaluateDimensionName;
	}

	public BigDecimal getDimensionWeight() {
		return dimensionWeight;
	}

	public void setDimensionWeight(BigDecimal dimensionWeight) {
		this.dimensionWeight = dimensionWeight;
	}

	public Integer getTplIndicatorId() {
		return tplIndicatorId;
	}

	public void setTplIndicatorId(Integer tplIndicatorId) {
		this.tplIndicatorId = tplIndicatorId;
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

	public String getScoreExplain() {
		return scoreExplain;
	}

	public void setScoreExplain(String scoreExplain) {
		this.scoreExplain = scoreExplain;
	}

	public BigDecimal getIndicatorWeight() {
		return indicatorWeight;
	}

	public void setIndicatorWeight(BigDecimal indicatorWeight) {
		this.indicatorWeight = indicatorWeight;
	}

    public String getEvaluateEmployeeName() {
        return evaluateEmployeeName;
    }

    public void setEvaluateEmployeeName(String evaluateEmployeeName) {
        this.evaluateEmployeeName = evaluateEmployeeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
}
