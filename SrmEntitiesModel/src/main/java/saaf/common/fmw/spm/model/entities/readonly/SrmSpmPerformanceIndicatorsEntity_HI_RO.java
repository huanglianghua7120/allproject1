package saaf.common.fmw.spm.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SrmSpmPerformanceIndicatorsEntity_HI_RO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String QUERY_PERFORMANCE_INDICATORS =
					"SELECT Performance_Indicator_Id     AS performanceIndicatorId    \n" +
					"      ,Performance_Id               AS performanceId              \n" +
					"      ,Performance_Line_Id          AS performanceLineId         \n" +
					"      ,Tpl_Id                       AS tplId                      \n" +
					"      ,Tpl_Dimension_Id             AS tplDimensionId            \n" +
					"      ,Tpl_Indicator_Id             AS tplIndicatorId            \n" +
					"      ,Indicator_Score              AS indicatorScore             \n" +
					"      ,Evaluate_User_Id_Str         AS evaluateUserIdStr        \n" +
					"      ,Evaluate_Employee_Id_Str     AS evaluateEmployeeIdStr    \n" +
					"      ,Evaluate_Employee_Number_Str AS evaluateEmployeeNumberStr\n" +
					"      ,Evaluate_Employee_Name_Str   AS evaluateEmployeeNameStr  \n" +
                    "       ,Evaluate_User_Name_Str AS evaluateUserNameStr\n" +
					"      ,Version_Num                  AS versionNum                 \n" +
					"      ,Creation_Date                AS creationDate               \n" +
					"      ,Created_By                   AS createdBy                  \n" +
					"      ,Last_Updated_By              AS lastUpdatedBy             \n" +
					"      ,Last_Update_Date             AS lastUpdateDate            \n" +
					"      ,Last_Update_Login            AS lastUpdateLogin           \n" +
					"      ,Attribute_Category           AS attributeCategory          \n" +
					"      ,Attribute1                   AS attribute1                  \n" +
					"      ,Attribute2                   AS attribute2                  \n" +
					"      ,Attribute3                   AS attribute3                  \n" +
					"      ,Attribute4                   AS attribute4                  \n" +
					"      ,Attribute5                   AS attribute5                  \n" +
					"      ,Attribute6                   AS attribute6                  \n" +
					"      ,Attribute7                   AS attribute7                  \n" +
					"      ,Attribute8                   AS attribute8                  \n" +
					"      ,Attribute9                   AS attribute9                  \n" +
					"      ,Attribute10                  AS attribute10                 \n" +
					"      ,Attribute11                  AS attribute11                 \n" +
					"      ,Attribute12                  AS attribute12                 \n" +
					"      ,Attribute13                  AS attribute13                 \n" +
					"      ,Attribute14                  AS attribute14                 \n" +
					"      ,Attribute15                  AS attribute15                 \n" +
					"      ,Dimension_Weight             AS dimensionWeight            \n" +
					"      ,Indicator_Weight             AS indicatorWeight            \n" +
					"  FROM Srm_Spm_Performance_Indicators Spi\n" +
					" WHERE 1 = 1 \n";

	public static String QUERY_PERFORMANCE_INDICATORS_LIST =
					"      SELECT Spi.Tpl_Id             AS tplId           \n" +
					"            ,Spi.Performance_Indicator_Id AS performanceIndicatorId\n" +
					"            ,Spi.Performance_Id AS performanceId\n" +
					"            ,Spi.Performance_Line_Id AS performanceLineId\n" +
					"            ,Spi.indicator_score    AS indicatorScore\n" +
                    "            ,Std.Tpl_Dimension_Id   AS tplDimensionId  \n" +
                    "            ,Std.Evaluate_Dimension AS evaluateDimension\n" +
                    "            ,Slv1.Meaning           AS evaluateDimensionName\n" +
                    "            ,Std.Dimension_Weight   AS dimensionWeight  \n" +
                    "            ,Sti.Tpl_Indicator_Id   AS tplIndicatorId  \n" +
                    "            ,Sti.Indicator_Id       AS indicatorId      \n" +
                    "            ,Spi.Indicator_Code     AS indicatorCode    \n" +
                    "            ,Spi.Score_Explain      AS scoreExplain     \n" +
                    "            ,Sti.Indicator_Weight   AS indicatorWeight\n" +
                    "            ,Spi.Evaluate_User_Id_Str AS userIdStr\n" +
                    "            ,Spi.Evaluate_Employee_Id_Str AS employeeIdStr\n" +
                    "            ,Spi.Evaluate_Employee_Number_Str AS employeeNumberStr\n" +
                    "            ,Spi.Evaluate_Employee_Name_Str AS employeeNameStr\n" +
                    "            ,Spi.Evaluate_User_Name_Str AS userNameStr\n" +
                    "        FROM Srm_Spm_Performance_Indicators Spi\n" +
                    "            ,Srm_Spm_Tpl_Dimension   Std\n" +
                    "        LEFT JOIN Saaf_Lookup_Values Slv1\n" +
                    "          ON Std.Evaluate_Dimension = Slv1.Lookup_Code\n" +
                    "         AND Slv1.Lookup_Type = 'SPM_INDICATOR_DIMENSION',\n" +
                    "       Srm_Spm_Tpl_Indicators Sti, Srm_Spm_Indicators Spi\n" +
                    "       WHERE Spi.Tpl_Dimension_Id = Std.Tpl_Dimension_Id\n" +
                    "         AND Spi.Tpl_Indicator_Id = Sti.Tpl_Indicator_Id\n" +
                    "         AND Sti.Indicator_Id = Spi.Indicator_Id\n";

	public static String QUERY_PERFORMANCE_INDICATORS_FOR_EXPORT = "" +
			"      SELECT Spi.Tpl_Id             AS tplId           \n" +
			"            ,Spi.Performance_Indicator_Id AS performanceIndicatorId\n" +
			"            ,Spi.Performance_Id AS performanceId\n" +
			"            ,Spi.Performance_Line_Id AS performanceLineId\n" +
			"            ,Spl.Score              AS score\n" +
			"            ,Spl.Rank               AS rank\n" +
			"            ,Psi.Supplier_Number    AS SupplierNumber  \n" +
			"            ,Psi.Supplier_Name      AS SupplierName\n" +
			"            ,Spi.indicator_score    AS indicatorScore\n" +
			"            ,Std.Tpl_Dimension_Id   AS tplDimensionId  \n" +
			"            ,Std.Evaluate_Dimension AS evaluateDimension\n" +
			"            ,Slv1.Meaning           AS evaluateDimensionName\n" +
			"            ,Std.Dimension_Weight   AS dimensionWeight  \n" +
			"            ,Sti.Tpl_Indicator_Id   AS tplIndicatorId  \n" +
			"            ,Sti.Indicator_Id       AS indicatorId      \n" +
			"            ,Spi.Indicator_Code     AS indicatorCode    \n" +
			"            ,Spi.Score_Explain      AS scoreExplain     \n" +
			"            ,Sti.Indicator_Weight   AS indicatorWeight\n" +
			"            ,Spi.Evaluate_User_Id_Str AS userIdStr\n" +
			"            ,Spi.Evaluate_Employee_Id_Str AS employeeIdStr\n" +
			"            ,Spi.Evaluate_Employee_Number_Str AS employeeNumberStr\n" +
			"            ,Spi.Evaluate_Employee_Name_Str AS employeeNameStr\n" +
            "            ,Spi.Evaluate_User_Name_Str AS userNameStr\n" +
			"            ,(SELECT Listagg(Nvl(Se.Employee_Name\n" +
			"                              ,Su.User_Name) || ' : ' || Spe.Score\n" +
			"                          ,',') Within GROUP(ORDER BY Nvl(Se.Employee_Name, Su.User_Name) || ' : ' || Spe.Score)\n" +
			"                FROM Srm_Spm_Performance_Evaluate Spe\n" +
			"                    ,Saaf_Users                   Su\n" +
			"                LEFT JOIN Saaf_Employees Se\n" +
			"                  ON Su.Employee_Id = Se.Employee_Id\n" +
			"               WHERE Spe.Evaluate_User_Id = Su.User_Id\n" +
			"                 AND Spe.Performance_Indicator_Id = Spi.Performance_Indicator_Id) AS scoreInfo\n" +
			"        FROM Srm_Spm_Performance_Lines Spl\n" +
			"        LEFT JOIN Srm_Pos_Supplier_Info Psi\n" +
			"          ON Spl.Supplier_Id = Psi.Supplier_Id\n" +
			"            ,Srm_Spm_Performance_Indicators Spi\n" +
			"            ,Srm_Spm_Tpl_Dimension   Std\n" +
			"        LEFT JOIN Saaf_Lookup_Values Slv1\n" +
			"          ON Std.Evaluate_Dimension = Slv1.Lookup_Code\n" +
			"         AND Slv1.Lookup_Type = 'SPM_INDICATOR_DIMENSION',\n" +
			"       Srm_Spm_Tpl_Indicators Sti, Srm_Spm_Indicators Spi\n" +
			"       WHERE Spl.Performance_Line_Id = Spi.Performance_Line_Id\n" +
			"         AND Spi.Tpl_Dimension_Id = Std.Tpl_Dimension_Id\n" +
			"         AND Spi.Tpl_Indicator_Id = Sti.Tpl_Indicator_Id\n" +
			"         AND Sti.Indicator_Id = Spi.Indicator_Id";

	public static final String QUERY_MANUAL_SCORE_LIST =
			"SELECT '加减分情况' AS evaluateDimensionName\n" +
					"      ,100          AS dimensionWeight\n" +
					"      ,'加减分'     AS indicatorCode\n" +
					"      ,Sms.project  AS scoreExplain\n" +
					"      ,100          AS indicatorWeight\n" +
					"      ,Sms.Score    AS indicatorScore\n" +
					"  FROM Srm_Spm_Manual_Score Sms\n" +
					" WHERE Sms.Status = 'ACTIVE'";

	private Integer performanceIndicatorId; //绩效指标ID
	private Integer performanceId; //绩效头ID
	private Integer performanceLineId; //绩效行ID
	private Integer tplId; //绩效模板ID
	private Integer tplDimensionId; //绩效模板维度ID
	private Integer tplIndicatorId; //绩效模板指标ID
	private BigDecimal indicatorScore; //指标得分
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
	private String evaluateDimension;
	private String evaluateDimensionName;
	private BigDecimal dimensionWeight;
	private Integer indicatorId;
	private String indicatorCode;
	private String scoreExplain;
	private BigDecimal indicatorWeight;
    private String userIdStr; //评分用户ID字符串，逗号分隔
	private String employeeIdStr;
	private String employeeNumberStr;
	private String employeeNameStr;
	private BigDecimal score;
	private Integer rank;
	private String SupplierNumber;
	private String SupplierName;
	private String scoreInfo;
	private String evaluateUserNameStr;
    private String userNameStr;

	private List<SrmSpmPerformanceEvaluateEntity_HI_RO> evaluateList;


    public String getEvaluateUserNameStr() {
        return evaluateUserNameStr;
    }

    public void setEvaluateUserNameStr(String evaluateUserNameStr) {
        this.evaluateUserNameStr = evaluateUserNameStr;
    }

    public String getUserNameStr() {
        return userNameStr;
    }

    public void setUserNameStr(String userNameStr) {
        this.userNameStr = userNameStr;
    }

    public Integer getPerformanceIndicatorId() {
		return performanceIndicatorId;
	}

	public void setPerformanceIndicatorId(Integer performanceIndicatorId) {
		this.performanceIndicatorId = performanceIndicatorId;
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

	public Integer getTplIndicatorId() {
		return tplIndicatorId;
	}

	public void setTplIndicatorId(Integer tplIndicatorId) {
		this.tplIndicatorId = tplIndicatorId;
	}

	public BigDecimal getIndicatorScore() {
		return indicatorScore;
	}

	public void setIndicatorScore(BigDecimal indicatorScore) {
		this.indicatorScore = indicatorScore;
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

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    public String getEmployeeIdStr() {
		return employeeIdStr;
	}

	public void setEmployeeIdStr(String employeeIdStr) {
		this.employeeIdStr = employeeIdStr;
	}

	public String getEmployeeNumberStr() {
		return employeeNumberStr;
	}

	public void setEmployeeNumberStr(String employeeNumberStr) {
		this.employeeNumberStr = employeeNumberStr;
	}

	public String getEmployeeNameStr() {
		return employeeNameStr;
	}

	public void setEmployeeNameStr(String employeeNameStr) {
		this.employeeNameStr = employeeNameStr;
	}

	public List<SrmSpmPerformanceEvaluateEntity_HI_RO> getEvaluateList() {
		return evaluateList;
	}

	public void setEvaluateList(List<SrmSpmPerformanceEvaluateEntity_HI_RO> evaluateList) {
		this.evaluateList = evaluateList;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getSupplierNumber() {
		return SupplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		SupplierNumber = supplierNumber;
	}

	public String getSupplierName() {
		return SupplierName;
	}

	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
	}

	public String getScoreInfo() {
		return scoreInfo;
	}

	public void setScoreInfo(String scoreInfo) {
		this.scoreInfo = scoreInfo;
	}
}
