package saaf.common.fmw.rule.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


/**
 * Created by Administrator on Fri Jul 07 17:27:23 CST 2017
 */
public class PageModelGroupInfoEntity_HI_RO {


    public static final String query =
        "SELECT   pmgi.group_id AS groupId,   pmgi.model_code AS modelCode,   pmi.model_name AS modelName,   pmgi.group_code AS groupCode,   pmgi.group_name_cn  AS groupNameCn,   pmi.rule_business_line_code AS ruleBusinessLineCode,   rbl.rule_business_line_name AS ruleBusinessLineName,   pmgi.group_level AS groupLevel,   pmgi.group_parent_code AS groupParentCode,   pmgi.group_name_view_flag AS groupNameViewFlag,   pmgi.group_name_view_type AS groupNameViewType,   pmgi.group_order AS groupOrder,   pmgi.version_num AS versionNum,   pmgi.CREATION_DATE AS creationDate  FROM page_model_group_info pmgi LEFT JOIN page_model_info pmi ON pmi.model_code = pmgi.model_code   LEFT JOIN rule_business_line rbl ON rbl.rule_business_line_code = pmi.rule_business_line_code WHERE 1=1";
    private Integer groupId;
    private String modelCode;
    private String modelName;
    private String groupCode;
    private String groupNameCn;
    private String pmgiGroupNameCn;
    private String ruleBusinessLineCode;
    private String ruleBusinessLineName;
    private Integer groupLevel;
    private String groupParentCode;
    private String groupNameViewFlag;
    private String groupNameViewType;
    private Integer groupOrder;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    public java.lang.Integer getGroupId() {
        return this.groupId;
    }

    public void setGroupId(java.lang.Integer groupId) {
        this.groupId = groupId;
    }

    public java.lang.String getModelCode() {
        return this.modelCode;
    }

    public void setModelCode(java.lang.String modelCode) {
        this.modelCode = modelCode;
    }

    public java.lang.String getModelName() {
        return this.modelName;
    }

    public void setModelName(java.lang.String modelName) {
        this.modelName = modelName;
    }

    public java.lang.String getGroupCode() {
        return this.groupCode;
    }

    public void setGroupCode(java.lang.String groupCode) {
        this.groupCode = groupCode;
    }

    public java.lang.String getGroupNameCn() {
        return this.groupNameCn;
    }

    public void setGroupNameCn(java.lang.String groupNameCn) {
        this.groupNameCn = groupNameCn;
    }

    public java.lang.String getPmgiGroupNameCn() {
        return this.pmgiGroupNameCn;
    }

    public void setPmgiGroupNameCn(java.lang.String pmgiGroupNameCn) {
        this.pmgiGroupNameCn = pmgiGroupNameCn;
    }

    public java.lang.String getRuleBusinessLineCode() {
        return this.ruleBusinessLineCode;
    }

    public void setRuleBusinessLineCode(java.lang.String businessLineCode) {
        this.ruleBusinessLineCode = businessLineCode;
    }

    public java.lang.String getRuleBusinessLineName() {
        return this.ruleBusinessLineName;
    }

    public void setRuleBusinessLineName(java.lang.String ruleBusinessLineName) {
        this.ruleBusinessLineName = ruleBusinessLineName;
    }

    public java.lang.Integer getGroupLevel() {
        return this.groupLevel;
    }

    public void setGroupLevel(java.lang.Integer groupLevel) {
        this.groupLevel = groupLevel;
    }

    public java.lang.String getGroupParentCode() {
        return this.groupParentCode;
    }

    public void setGroupParentCode(java.lang.String groupParentCode) {
        this.groupParentCode = groupParentCode;
    }

    public java.lang.String getGroupNameViewFlag() {
        return this.groupNameViewFlag;
    }

    public void setGroupNameViewFlag(java.lang.String groupNameViewFlag) {
        this.groupNameViewFlag = groupNameViewFlag;
    }

    public java.lang.String getGroupNameViewType() {
        return this.groupNameViewType;
    }

    public void setGroupNameViewType(java.lang.String groupNameViewType) {
        this.groupNameViewType = groupNameViewType;
    }

    public java.lang.Integer getGroupOrder() {
        return this.groupOrder;
    }

    public void setGroupOrder(java.lang.Integer groupOrder) {
        this.groupOrder = groupOrder;
    }

    public java.lang.Integer getVersionNum() {
        return this.versionNum;
    }

    public void setVersionNum(java.lang.Integer versionNum) {
        this.versionNum = versionNum;
    }

    public java.util.Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(java.util.Date creationDate) {
        this.creationDate = creationDate;
    }
}
