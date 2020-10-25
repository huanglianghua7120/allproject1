package saaf.common.fmw.spm.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SrmSpmPerformanceHeadersEntity_HI_RO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String QUERY_PERFORMANCE_HEADERS_LIST =
			"SELECT Sph.Performance_Id         AS performanceId\n" +
			"      ,Sph.Org_Id                 AS orgId\n" +
			"      ,Si.Inst_Name               AS orgName\n" +
			"      ,Sph.Performance_Number     AS performanceNumber\n" +
			"      ,Sph.Performance_Name       AS performanceName\n" +
			"      ,Sph.Tpl_Id                 AS tplId\n" +
			"      ,Spl.Tpl_Code               AS tplCode\n" +
			"      ,Spl.Tpl_Name               AS tplName\n" +
			"      ,Sph.Evaluate_Period        AS evaluatePeriod\n" +
			"      ,Slv1.Meaning               AS evaluatePeriodName\n" +
			"      ,Sph.Status                 AS status\n" +
			"      ,Slv2.Meaning               AS statusName\n" +
			"      ,Sph.Evaluate_Interval_From AS evaluateIntervalFrom\n" +
			"      ,Sph.Evaluate_Interval_To   AS evaluateIntervalTo\n" +
			"      ,Sph.Publish_Flag           AS publishFlag\n" +
			"      ,Sph.Publish_Date           AS publishDate\n" +
			"      ,Sph.Description            AS description\n" +
			"      ,Sph.File_Id                AS fileId\n" +
            "      ,Sph.Evaluate_Year          AS evaluateYear\n" +
            "      ,Sph.Evaluate_Quarter       AS evaluateQuarter\n" +
            "      ,Sph.Evaluate_Month         AS evaluateMonth\n" +
			"      ,Sph.Version_Num            AS versionNum\n" +
			"      ,Sph.Creation_Date          AS creationDate\n" +
			"      ,Sph.Created_By             AS createdBy\n" +
			"      ,Sph.Last_Updated_By        AS lastUpdatedBy\n" +
			"      ,Sph.Last_Update_Date       AS lastUpdateDate\n" +
			"      ,Sph.Last_Update_Login      AS lastUpdateLogin\n" +
			"      ,Sph.Attribute_Category     AS attributeCategory\n" +
			"      ,Sph.Attribute1             AS attribute1\n" +
			"      ,Sph.Attribute2             AS attribute2\n" +
			"      ,Sph.Attribute3             AS attribute3\n" +
			"      ,Sph.Attribute4             AS attribute4\n" +
			"      ,Sph.Attribute5             AS attribute5\n" +
			"      ,Sph.Attribute6             AS attribute6\n" +
			"      ,Sph.Attribute7             AS attribute7\n" +
			"      ,Sph.Attribute8             AS attribute8\n" +
			"      ,Sph.Attribute9             AS attribute9\n" +
			"      ,Sph.Attribute10            AS attribute10\n" +
			"      ,Sph.Attribute11            AS attribute11\n" +
			"      ,Sph.Attribute12            AS attribute12\n" +
			"      ,Sph.Attribute13            AS attribute13\n" +
			"      ,Sph.Attribute14            AS attribute14\n" +
			"      ,Sph.Attribute15            AS attribute15\n" +
            "      ,Sph.item_type      AS itemType\n" +
			"      ,(SELECT 'Y'\n" +
			"          FROM Srm_Spm_Performance_Evaluate Spe\n" +
			"         WHERE Spe.Performance_Id = Sph.Performance_Id\n" +
			"           AND Spe.Evaluate_User_Id = :userId\n" +
			"           AND Rownum = 1) AS evaluateFlag\n" +
			"  FROM Srm_Spm_Performance_Headers Sph\n" +
			"  LEFT JOIN Saaf_Institution Si\n" +
			"    ON Sph.Org_Id = Si.Inst_Id\n" +
			"  LEFT JOIN Srm_Spm_Performance_Tpl Spl\n" +
			"    ON Sph.Tpl_Id = Spl.Tpl_Id\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv1\n" +
			"    ON Slv1.Lookup_Type = 'SPM_TEMPLATE_FREQUENCY'\n" +
			"   AND Sph.Evaluate_Period = Slv1.Lookup_Code\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv2\n" +
			"    ON Slv2.Lookup_Type = 'SPM_PERFORMANCE_STATUS'\n" +
			"   AND Sph.Status = Slv2.Lookup_Code\n" +
			" WHERE 1 = 1 \n";

	public static String QUERY_PERFORMANCE_RESULT_LIST =
			"SELECT Sph.Performance_Id         AS performanceId\n" +
			"      ,Sph.Org_Id                 AS orgId\n" +
			"      ,Si.Inst_Name               AS orgName\n" +
			"      ,Sph.Performance_Number     AS performanceNumber\n" +
			"      ,Sph.Performance_Name       AS performanceName\n" +
			"      ,Sph.Tpl_Id                 AS tplId\n" +
			"      ,Spl.Tpl_Code               AS tplCode\n" +
			"      ,Spl.Tpl_Name               AS tplName\n" +
			"      ,Sph.Evaluate_Period        AS evaluatePeriod\n" +
			"      ,Sph.Evaluate_Year          AS evaluateYear\n" +
			"      ,Sph.Evaluate_Quarter       AS evaluateQuarter\n" +
			"      ,Sph.Evaluate_Month         AS evaluateMonth\n" +
			"      ,Slv1.Meaning               AS evaluatePeriodName\n" +
			"      ,Sph.Status                 AS status\n" +
			"      ,Slv2.Meaning               AS statusName\n" +
			"      ,Sph.Evaluate_Interval_From AS evaluateIntervalFrom\n" +
			"      ,Sph.Evaluate_Interval_To   AS evaluateIntervalTo\n" +
			"      ,Sph.Publish_Flag           AS publishFlag\n" +
			"      ,Sph.Publish_Date           AS publishDate\n" +
			"      ,Sph.Description            AS description\n" +
			"      ,Sph.File_Id                AS fileId\n" +
			"      ,Sph.Version_Num            AS versionNum\n" +
			"      ,Sph.Creation_Date          AS creationDate\n" +
			"      ,Sph.Created_By             AS createdBy\n" +
			"      ,Sph.Last_Updated_By        AS lastUpdatedBy\n" +
			"      ,Sph.Last_Update_Date       AS lastUpdateDate\n" +
			"      ,Sph.Last_Update_Login      AS lastUpdateLogin\n" +
			"      ,Sph.Attribute_Category     AS attributeCategory\n" +
			"      ,Sph.Attribute1             AS attribute1\n" +
			"      ,Sph.Attribute2             AS attribute2\n" +
			"      ,Sph.Attribute3             AS attribute3\n" +
			"      ,Sph.Attribute4             AS attribute4\n" +
			"      ,Sph.Attribute5             AS attribute5\n" +
			"      ,Sph.Attribute6             AS attribute6\n" +
			"      ,Sph.Attribute7             AS attribute7\n" +
			"      ,Sph.Attribute8             AS attribute8\n" +
			"      ,Sph.Attribute9             AS attribute9\n" +
			"      ,Sph.Attribute10            AS attribute10\n" +
			"      ,Sph.Attribute11            AS attribute11\n" +
			"      ,Sph.Attribute12            AS attribute12\n" +
			"      ,Sph.Attribute13            AS attribute13\n" +
			"      ,Sph.Attribute14            AS attribute14\n" +
			"      ,Sph.Attribute15            AS attribute15\n" +
			"      ,Psi.Supplier_Number        AS supplierNumber\n" +
			"      ,Psi.Supplier_Name          AS supplierName\n" +
			"      ,Spls.Performance_Line_Id   AS performanceLineId\n" +
			"      ,Spls.Score                 AS score\n" +
			"      ,Spls.Confirm_Flag          AS confirmFlag\n" +
			"      ,Spls.Confirm_Date          AS confirmDate\n" +
			"      ,Spls.Performance_Comment   AS performanceComment\n" +
			"      ,Spls.Improvement_Plan      AS improvementPlan\n" +
            "      ,Sph.item_type      AS itemType\n" +
			"      ,(SELECT COUNT(1) + 1\n" +
			"          FROM Srm_Spm_Performance_Headers Sph2\n" +
			"              ,Srm_Spm_Performance_Lines   Spls2\n" +
			"         WHERE Sph2.Performance_Id = Spls2.Performance_Id\n" +
			"           AND Sph2.Org_Id = Sph.Org_Id\n" +
            "           AND Sph2.item_type = Sph.item_type\n" +
			"           AND Sph2.Evaluate_Period = Sph.Evaluate_Period\n" +
			"           AND Sph2.Evaluate_Interval_From = Sph.Evaluate_Interval_From\n" +
			"           AND Sph2.Evaluate_Interval_To = Sph.Evaluate_Interval_To\n" +
			"           AND (Sph2.Status = 'PUBLISH' OR Sph2.Status = 'COMPLETE')\n" +
			"           AND Spls2.Score > Spls.Score) rankInOrg\n" +
			"  FROM Srm_Spm_Performance_Headers Sph\n" +
			"  LEFT JOIN Saaf_Institution Si\n" +
			"    ON Sph.Org_Id = Si.Inst_Id\n" +
			"  LEFT JOIN Srm_Spm_Performance_Tpl Spl\n" +
			"    ON Sph.Tpl_Id = Spl.Tpl_Id\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv1\n" +
			"    ON Slv1.Lookup_Type = 'SPM_TEMPLATE_FREQUENCY'\n" +
			"   AND Sph.Evaluate_Period = Slv1.Lookup_Code\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv2\n" +
			"    ON Slv2.Lookup_Type = 'SPM_PERFORMANCE_STATUS'\n" +
			"   AND Sph.Status = Slv2.Lookup_Code, Srm_Spm_Performance_Lines Spls\n" +
			"  LEFT JOIN Srm_Pos_Supplier_Info Psi\n" +
			"    ON Spls.Supplier_Id = Psi.Supplier_Id\n" +
			" WHERE Sph.Performance_Id = Spls.Performance_Id\n" +
			"   AND (Sph.Status = 'PUBLISH' OR Sph.Status = 'COMPLETE')";

	public static String QUERY_PERFORMANCE_HEADER =
			"SELECT Sph.Performance_Id         AS performanceId\n" +
			"      ,Sph.Org_Id                 AS orgId\n" +
			"      ,Si.Inst_Name               AS orgName\n" +
			"      ,Sph.Performance_Number     AS performanceNumber\n" +
			"      ,Sph.Performance_Name       AS performanceName\n" +
			"      ,Sph.Tpl_Id                 AS tplId\n" +
			"      ,Spl.Tpl_Code               AS tplCode\n" +
			"      ,Spl.Tpl_Name               AS tplName\n" +
			"      ,Sph.Evaluate_Period        AS evaluatePeriod\n" +
			"      ,Slv1.Meaning               AS evaluatePeriodName\n" +
			"      ,Sph.Evaluate_Year          AS evaluateYear\n" +
			"      ,Sph.Evaluate_Quarter       AS evaluateQuarter\n" +
			"      ,Sph.Evaluate_Month         AS evaluateMonth\n" +
			"      ,Sph.Status                 AS status\n" +
			"      ,Slv2.Meaning               AS statusName\n" +
			"      ,Sph.Evaluate_Interval_From AS evaluateIntervalFrom\n" +
			"      ,Sph.Evaluate_Interval_To   AS evaluateIntervalTo\n" +
			"      ,Sph.Publish_Flag           AS publishFlag\n" +
			"      ,Sph.Publish_Date           AS publishDate\n" +
			"      ,Sph.Description            AS description\n" +
			"      ,Sph.File_Id                AS fileId\n" +
			"      ,Sph.Version_Num            AS versionNum\n" +
			"      ,Sph.Creation_Date          AS creationDate\n" +
			"      ,Sph.Created_By             AS createdBy\n" +
			"      ,Nvl(Se.Employee_Name,Su.User_Name) AS createdByName\n" +
			"      ,Sph.Last_Updated_By        AS lastUpdatedBy\n" +
			"      ,Sph.Last_Update_Date       AS lastUpdateDate\n" +
			"      ,Sph.Last_Update_Login      AS lastUpdateLogin\n" +
			"      ,Sph.Attribute_Category     AS attributeCategory\n" +
			"      ,Sph.Attribute1             AS attribute1\n" +
			"      ,Sph.Attribute2             AS attribute2\n" +
			"      ,Sph.Attribute3             AS attribute3\n" +
			"      ,Sph.Attribute4             AS attribute4\n" +
			"      ,Sph.Attribute5             AS attribute5\n" +
			"      ,Sph.Attribute6             AS attribute6\n" +
			"      ,Sph.Attribute7             AS attribute7\n" +
			"      ,Sph.Attribute8             AS attribute8\n" +
			"      ,Sph.Attribute9             AS attribute9\n" +
			"      ,Sph.Attribute10            AS attribute10\n" +
			"      ,Sph.Attribute11            AS attribute11\n" +
			"      ,Sph.Attribute12            AS attribute12\n" +
			"      ,Sph.Attribute13            AS attribute13\n" +
			"      ,Sph.Attribute14            AS attribute14\n" +
			"      ,Sph.Attribute15            AS attribute15\n" +
            "      ,Sph.item_type            AS itemType\n" +
			"  FROM Srm_Spm_Performance_Headers Sph\n" +
			"  LEFT JOIN Saaf_Institution Si\n" +
			"    ON Sph.Org_Id = Si.Inst_Id\n" +
			"  LEFT JOIN Srm_Spm_Performance_Tpl Spl\n" +
			"    ON Sph.Tpl_Id = Spl.Tpl_Id\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv1\n" +
			"    ON Slv1.Lookup_Type = 'SPM_TEMPLATE_FREQUENCY'\n" +
			"   AND Sph.Evaluate_Period = Slv1.Lookup_Code\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv2\n" +
			"    ON Slv2.Lookup_Type = 'SPM_PERFORMANCE_STATUS'\n" +
			"   AND Sph.Status = Slv2.Lookup_Code\n" +
			"  ,Saaf_Users Su\n" +
			"  LEFT JOIN Saaf_Employees Se\n" +
			"    ON Su.Employee_Id = Se.Employee_Id \n" +
			" WHERE Sph.Created_By = Su.User_Id";

	private Integer performanceId; //绩效ID
	private Integer orgId; //组织ID
	private String performanceNumber; //绩效编号
	private String performanceName; //绩效名称
	private Integer tplId; //绩效模板ID
	private String evaluatePeriod; //评价频率
	private String status; //状态
	private String evaluateIntervalFrom; //绩效月份从
	private String evaluateIntervalTo; //绩效月份至
	private String publishFlag; //是否已发布
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date publishDate; //发布时间
	private String description; //说明、备注
	private Integer fileId; //附件ID（备用）
    private String evaluateYear; //评价年份
    private String evaluateQuarter; //评价季度
    private String evaluateMonth; //评价月份
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

	//别名
	private String instName;
	private String tplCode;
    private String tplName;
	private String orgName;
	private String evaluatePeriodName;
	private String statusName;
	private String createdByName;
	private String lastUpdatedByName;
	private String evaluateFlag;
	private String supplierNumber;
	private String supplierName;
	private Integer performanceLineId;
	private BigDecimal score;
	private String confirmFlag;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date confirmDate;
	private String performanceComment;
	private String improvementPlan;
	private BigDecimal rankInOrg;
	private String itemType;

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public BigDecimal getRankInOrg() {
        return rankInOrg;
    }

    public void setRankInOrg(BigDecimal rankInOrg) {
        this.rankInOrg = rankInOrg;
    }

    public Integer getPerformanceId() {
		return performanceId;
	}

	public void setPerformanceId(Integer performanceId) {
		this.performanceId = performanceId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getPerformanceNumber() {
		return performanceNumber;
	}

	public void setPerformanceNumber(String performanceNumber) {
		this.performanceNumber = performanceNumber;
	}

	public String getPerformanceName() {
		return performanceName;
	}

	public void setPerformanceName(String performanceName) {
		this.performanceName = performanceName;
	}

	public Integer getTplId() {
		return tplId;
	}

	public void setTplId(Integer tplId) {
		this.tplId = tplId;
	}

	public String getEvaluatePeriod() {
		return evaluatePeriod;
	}

	public void setEvaluatePeriod(String evaluatePeriod) {
		this.evaluatePeriod = evaluatePeriod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEvaluateIntervalFrom() {
		return evaluateIntervalFrom;
	}

	public void setEvaluateIntervalFrom(String evaluateIntervalFrom) {
		this.evaluateIntervalFrom = evaluateIntervalFrom;
	}

	public String getEvaluateIntervalTo() {
		return evaluateIntervalTo;
	}

	public void setEvaluateIntervalTo(String evaluateIntervalTo) {
		this.evaluateIntervalTo = evaluateIntervalTo;
	}

	public String getPublishFlag() {
		return publishFlag;
	}

	public void setPublishFlag(String publishFlag) {
		this.publishFlag = publishFlag;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

    public String getEvaluateYear() {
        return evaluateYear;
    }

    public void setEvaluateYear(String evaluateYear) {
        this.evaluateYear = evaluateYear;
    }

    public String getEvaluateQuarter() {
        return evaluateQuarter;
    }

    public void setEvaluateQuarter(String evaluateQuarter) {
        this.evaluateQuarter = evaluateQuarter;
    }

    public String getEvaluateMonth() {
        return evaluateMonth;
    }

    public void setEvaluateMonth(String evaluateMonth) {
        this.evaluateMonth = evaluateMonth;
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

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getTplCode() {
		return tplCode;
	}

	public void setTplCode(String tplCode) {
		this.tplCode = tplCode;
	}

    public String getTplName() {
        return tplName;
    }

    public void setTplName(String tplName) {
        this.tplName = tplName;
    }

    public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getEvaluatePeriodName() {
		return evaluatePeriodName;
	}

	public void setEvaluatePeriodName(String evaluatePeriodName) {
		this.evaluatePeriodName = evaluatePeriodName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getLastUpdatedByName() {
		return lastUpdatedByName;
	}

	public void setLastUpdatedByName(String lastUpdatedByName) {
		this.lastUpdatedByName = lastUpdatedByName;
	}

	public String getEvaluateFlag() {
		return evaluateFlag;
	}

	public void setEvaluateFlag(String evaluateFlag) {
		this.evaluateFlag = evaluateFlag;
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

	public Integer getPerformanceLineId() {
		return performanceLineId;
	}

	public void setPerformanceLineId(Integer performanceLineId) {
		this.performanceLineId = performanceLineId;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getPerformanceComment() {
		return performanceComment;
	}

	public void setPerformanceComment(String performanceComment) {
		this.performanceComment = performanceComment;
	}

	public String getImprovementPlan() {
		return improvementPlan;
	}

	public void setImprovementPlan(String improvementPlan) {
		this.improvementPlan = improvementPlan;
	}
}
