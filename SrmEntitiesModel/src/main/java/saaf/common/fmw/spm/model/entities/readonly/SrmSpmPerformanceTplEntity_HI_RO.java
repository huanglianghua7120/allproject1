package saaf.common.fmw.spm.model.entities.readonly;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmSpmPerformanceTplEntity_HI_RO implements Serializable{

	private static final long serialVersionUID = 1L;

	public static String QUERY_TPL_INFO_LIST =
					"SELECT tpl.tpl_id tplId\n" +
					"      ,tpl.tpl_code tplCode\n" +
					"      ,tpl.tpl_name tplName\n" +
					"      ,tpl.tpl_code tplNameTp\n" +
					"      ,tpl.tpl_frequency tplFrequency\n" +
					"      ,slv2.meaning tplFrequencyName\n" +
					"      ,tpl.org_id orgId\n" +
					"      ,tpl.status status\n" +
					"      ,slv3.meaning statusName\n" +
					"      ,tpl.from_tpl_id fromTplId\n" +
					"      ,tpl.tpl_code fromCode\n" +
					"      ,tpl.description description\n" +
					"      ,tpl.start_date startDate\n" +
					"      ,tpl.end_date endDate\n" +
					"      ,si.inst_name orgName\n" +
					"      ,nvl(emp1.employee_name, su1.user_full_name) createdByName\n" +
					"      ,tpl.created_by createdBy\n" +
					"      ,tpl.creation_date creationDate\n" +
					"      ,tpl.last_updated_by lastUpdatedBy\n" +
					"      ,nvl(emp2.employee_name, su2.user_full_name) lastUpdatedByName\n" +
					"      ,tpl.last_update_date lastupdateDate\n" +
                    "      ,tpl.item_type itemType\n" +
                    "      ,slv4.meaning itemName\n" +
					"FROM   srm_spm_performance_tpl tpl\n" +
					"LEFT   JOIN saaf_users su1\n" +
					"ON     su1.user_id = tpl.created_by\n" +
					"LEFT   JOIN saaf_employees emp1\n" +
					"ON     emp1.employee_id = su1.employee_id\n" +
					"LEFT   JOIN saaf_users su2\n" +
					"ON     su2.user_id = tpl.last_updated_by\n" +
                    "LEFT   JOIN saaf_lookup_values slv4\n" +
                    "ON     tpl.item_type = slv4.lookup_code\n" +
                    "AND    slv4.lookup_type = 'POS_SUPPLIER_TYPE'\n" +
					"LEFT   JOIN saaf_employees emp2\n" +
					"ON     emp2.employee_id = su2.employee_id, saaf_institution si,\n" +
					" saaf_lookup_values slv2, saaf_lookup_values slv3\n" +
					"WHERE  tpl.org_id = si.inst_id\n" +
					"AND    tpl.tpl_frequency = slv2.lookup_code\n" +
					"AND    slv2.lookup_type = 'SPM_TEMPLATE_FREQUENCY'\n" +
					"AND    tpl.status = slv3.lookup_code\n" +
					"AND    slv3.lookup_type = 'SPM_TEMPLATE_STATUS'\n";

	public static String QUERY_EXP_INFO_LIST =
					"SELECT tpl.tpl_id tplId\n" +
					"      ,tpl.tpl_code tplCode\n" +
					"      ,tpl.tpl_name tplName\n" +
					"      ,tpl.tpl_code tplNameTp\n" +
					"      ,tpl.tpl_frequency tplFrequency\n" +
					"      ,slv2.meaning tplFrequencyName\n" +
					"      ,tpl.org_id orgId\n" +
					"      ,tpl.status status\n" +
					"      ,slv3.meaning statusName\n" +
					"      ,tpl.from_tpl_id fromTplId\n" +
					"      ,tpl.tpl_code fromCode\n" +
					"      ,tpl.description description\n" +
					"      ,tpl.start_date startDate\n" +
					"      ,tpl.end_date endDate\n" +
					"      ,si.inst_name orgName\n" +
					"      ,nvl(emp1.employee_name, su1.user_full_name) createdByName\n" +
					"      ,tpl.created_by createdBy\n" +
					"      ,tpl.creation_date creationDate\n" +
					"      ,tpl.last_updated_by lastUpdatedBy\n" +
					"      ,nvl(emp2.employee_name, su2.user_full_name) lastUpdatedByName\n" +
					"      ,tpl.last_update_date lastupdateDate\n" +
					"      ,src.category_id categoryId\n" +
					"FROM   srm_spm_performance_tpl tpl\n" +
					"LEFT   JOIN saaf_users su1\n" +
					"ON     su1.user_id = tpl.created_by\n" +
					"LEFT   JOIN saaf_employees emp1\n" +
					"ON     emp1.employee_id = su1.employee_id\n" +
					"LEFT   JOIN saaf_users su2\n" +
					"ON     su2.user_id = tpl.last_updated_by\n" +
					"LEFT   JOIN saaf_employees emp2\n" +
					"ON     emp2.employee_id = su2.employee_id, saaf_institution si, \n" +
					" saaf_lookup_values slv2, saaf_lookup_values slv3\n" +
					"WHERE  tpl.org_id = si.inst_id\n" +
					"AND    tpl.tpl_frequency = slv2.lookup_code\n" +
					"AND    slv2.lookup_type = 'SPM_TEMPLATE_FREQUENCY'\n" +
					"AND    tpl.status = slv3.lookup_code\n" +
					"AND    slv3.lookup_type = 'SPM_TEMPLATE_STATUS'\n";

	public static String QUERY_TPL_INFO_COUNT =
					"SELECT COUNT(1)\n" +
					"FROM   srm_spm_performance_tpl tpl\n" +
					"LEFT   JOIN saaf_users su1\n" +
					"ON     su1.user_id = tpl.created_by\n" +
					"LEFT   JOIN saaf_employees emp1\n" +
					"ON     emp1.employee_id = su1.employee_id\n" +
					"LEFT   JOIN saaf_users su2\n" +
					"ON     su2.user_id = tpl.last_updated_by\n" +
					"LEFT   JOIN saaf_employees emp2\n" +
					"ON     emp2.employee_id = su2.employee_id, saaf_institution si,\n" +
					" saaf_lookup_values slv2, saaf_lookup_values slv3\n" +
					"WHERE  tpl.org_id = si.inst_id\n" +
					"AND    tpl.tpl_frequency = slv2.lookup_code\n" +
					"AND    slv2.lookup_type = 'SPM_TEMPLATE_FREQUENCY'\n" +
					"AND    tpl.status = slv3.lookup_code\n" +
					"AND    slv3.lookup_type = 'SPM_TEMPLATE_STATUS'\n";

	public static String QUERY_CATE_INFO_COUNT =
					"SELECT tpl.tpl_id     tplId\n" +
					"      ,tpl.tpl_code   tplCode\n" +
					"      ,tpl.tpl_domain tplDomain\n" +
					"FROM   srm_spm_performance_tpl tpl\n" +
/*					"LEFT   JOIN srm_spm_tpl_categories cate\n" +
					"ON     tpl.tpl_id = cate.tpl_id\n" +*/
					"WHERE  1 = 1\n";

	private Integer tplId; //模版ID
    private String tplCode; //模版编码
    private String tplName; //模版名称
    private String tplDomain; //应用领域，关联表：SAAF_LOOKUP_VALUES（SPM_APPLICATION_DOMAIN）
    private String tplDomainName;
    private String tplFrequency; //评价频率，关联表：SAAF_LOOKUP_VALUES（SPM_TEMPLATE_FREQUENCY）
    private String tplFrequencyName;
    private Integer orgId; //组织ID，关联表：SAAF_INSTITUTION
    private String instName;
    private String status; //模版状态，关联表：SAAF_LOOKUP_VALUES（SPM_TEMPLATE_STATUS）
    private String statusName;
    private Integer fromTplId; //来源模版ID
    private String tplNameTp;
    private String description; //说明、备注
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate; //生效日期
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate; //失效日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private String createdByName;
    private Integer lastUpdatedBy;
    private String lastUpdatedByName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    
    private String categoryName;
    private String categoryCode;
    private Integer categoryId;
    
    private String fromCode;
    private String orgName;
    private String itemType;
    private String itemName;

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getTplId() {
		return tplId;
	}
	public void setTplId(Integer tplId) {
		this.tplId = tplId;
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
	public String getTplDomain() {
		return tplDomain;
	}
	public void setTplDomain(String tplDomain) {
		this.tplDomain = tplDomain;
	}
	public String getTplDomainName() {
		return tplDomainName;
	}
	public void setTplDomainName(String tplDomainName) {
		this.tplDomainName = tplDomainName;
	}
	public String getTplFrequency() {
		return tplFrequency;
	}
	public void setTplFrequency(String tplFrequency) {
		this.tplFrequency = tplFrequency;
	}
	public String getTplFrequencyName() {
		return tplFrequencyName;
	}
	public void setTplFrequencyName(String tplFrequencyName) {
		this.tplFrequencyName = tplFrequencyName;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getFromTplId() {
		return fromTplId;
	}
	public void setFromTplId(Integer fromTplId) {
		this.fromTplId = fromTplId;
	}
	public String getTplNameTp() {
		return tplNameTp;
	}
	public void setTplNameTp(String tplNameTp) {
		this.tplNameTp = tplNameTp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getLastUpdatedByName() {
		return lastUpdatedByName;
	}
	public void setLastUpdatedByName(String lastUpdatedByName) {
		this.lastUpdatedByName = lastUpdatedByName;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getFromCode() {
		return fromCode;
	}
	public void setFromCode(String fromCode) {
		this.fromCode = fromCode;
	}
    
    
    

}
