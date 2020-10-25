package saaf.common.fmw.rule.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


/**
 * Created by Administrator on Fri Jul 07 17:28:51 CST 2017
 */
public class PageModelGroupDetailEntity_HI_RO {


    public static final String query =
        "SELECT   pmgd.group_det_id AS groupDetId,   pmgd.group_code AS groupCode,   pmgi.group_name_cn AS groupNameCn,   pmgi.group_name_en AS groupNameEn,   pmgi.group_level AS groupLevel,   pmgi.group_parent_code AS groupParentCode,   pmgi.group_name_view_flag AS groupNameViewFlag,   pmgi.group_name_view_type AS groupNameViewType,   pmgd.group_det_dim_type AS groupDetDimType,   pmgd.group_det_dim_action_view_type AS groupDetDimActionViewType,   pmgd.group_det_dim_name_en AS groupDetDimNameEn,   pmgd.group_det_dim_name_cn AS groupDetDimNameCn,   pmgd.group_det_dim_code AS groupDetDimCode,   pmgd.group_det_dim_opt_code AS groupDetDimOptCode,   pmgd.group_det_dim_opt_name AS groupDetDimOptName,   pmgd.version_num AS versionNum,   pmgd.CREATION_DATE AS creationDate  FROM page_model_group_detail pmgd LEFT JOIN page_model_group_info pmgi ON pmgi.group_code =pmgd.group_code WHERE 1=1";
    private Integer groupDetId;
    private String groupCode;
    private String groupNameCn;
    private String groupNameEn;
    private Integer groupLevel;
    private String groupParentCode;
    private String groupNameViewFlag;
    private String groupNameViewType;
    private String groupDetDimType;
    private String groupDetDimActionViewType;
    private String groupDetDimNameEn;
    private String groupDetDimNameCn;
    private String groupDetDimCode;
    private String groupDetDimOptCode;
    private String groupDetDimOptName;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    public java.lang.Integer getGroupDetId() {
        return this.groupDetId;
    }

    public void setGroupDetId(java.lang.Integer groupDetId) {
        this.groupDetId = groupDetId;
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

    public java.lang.String getGroupNameEn() {
        return this.groupNameEn;
    }

    public void setGroupNameEn(java.lang.String groupNameEn) {
        this.groupNameEn = groupNameEn;
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

    public java.lang.String getGroupDetDimType() {
        return this.groupDetDimType;
    }

    public void setGroupDetDimType(java.lang.String groupDetDimType) {
        this.groupDetDimType = groupDetDimType;
    }

    public java.lang.String getGroupDetDimActionViewType() {
        return this.groupDetDimActionViewType;
    }

    public void setGroupDetDimActionViewType(java.lang.String groupDetDimActionViewType) {
        this.groupDetDimActionViewType = groupDetDimActionViewType;
    }

    public java.lang.String getGroupDetDimNameEn() {
        return this.groupDetDimNameEn;
    }

    public void setGroupDetDimNameEn(java.lang.String groupDetDimNameEn) {
        this.groupDetDimNameEn = groupDetDimNameEn;
    }

    public java.lang.String getGroupDetDimNameCn() {
        return this.groupDetDimNameCn;
    }

    public void setGroupDetDimNameCn(java.lang.String groupDetDimNameCn) {
        this.groupDetDimNameCn = groupDetDimNameCn;
    }

    public java.lang.String getGroupDetDimCode() {
        return this.groupDetDimCode;
    }

    public void setGroupDetDimCode(java.lang.String groupDetDimCode) {
        this.groupDetDimCode = groupDetDimCode;
    }

    public java.lang.String getGroupDetDimOptCode() {
        return this.groupDetDimOptCode;
    }

    public void setGroupDetDimOptCode(java.lang.String groupDetDimOptCode) {
        this.groupDetDimOptCode = groupDetDimOptCode;
    }

    public java.lang.String getGroupDetDimOptName() {
        return this.groupDetDimOptName;
    }

    public void setGroupDetDimOptName(java.lang.String groupDetDimOptName) {
        this.groupDetDimOptName = groupDetDimOptName;
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
