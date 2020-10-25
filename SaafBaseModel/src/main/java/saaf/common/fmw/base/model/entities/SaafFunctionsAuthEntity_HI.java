package saaf.common.fmw.base.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.*;


/**
 * SaafFunctionsAuthEntity_HI Entity Object
 * Thu Apr 20 11:13:16 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_functions_auth")
public class SaafFunctionsAuthEntity_HI {
    private Integer funButtonId;
    private Integer menuId;
    private String platformCode;
    private String buttonType;
    private String buttonCode;
    private String buttonName;
    private String buttonDesc;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private Integer operatorUserId;
    private String buttonPermission;

    public void setFunButtonId(Integer funButtonId) {
        this.funButtonId = funButtonId;
    }

    @Id
    @SequenceGenerator(name = "ISEQ$$_77782", sequenceName = "ISEQ$$_77782", allocationSize = 1)
    @GeneratedValue(generator = "ISEQ$$_77782", strategy = GenerationType.SEQUENCE)
    @Column(name = "fun_button_id", nullable = false, length = 11)
    public Integer getFunButtonId() {
        return funButtonId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Column(name = "menu_id", precision = 22, scale = 0)
    public Integer getMenuId() {
        return menuId;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    @Column(name = "platform_code", nullable = true, length = 30)
    public String getPlatformCode() {
        return platformCode;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }

    @Column(name = "button_type", nullable = true, length = 240)
    public String getButtonType() {
        return buttonType;
    }

    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode;
    }

    @Column(name = "button_code", nullable = true, length = 80)
    public String getButtonCode() {
        return buttonCode;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    @Column(name = "button_name", nullable = true, length = 240)
    public String getButtonName() {
        return buttonName;
    }

    public void setButtonDesc(String buttonDesc) {
        this.buttonDesc = buttonDesc;
    }

    @Column(name = "button_desc", nullable = true, length = 300)
    public String getButtonDesc() {
        return buttonDesc;
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

    @Column(name = "created_by", precision = 22, scale = 0)
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "last_updated_by", precision = 22, scale = 0)
    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name = "last_update_date", nullable = false, length = 0)
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    @Column(name = "last_update_login", precision = 22, scale = 0)
    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    @Column(name = "button_permission")
	public String getButtonPermission() {
		return buttonPermission;
	}

	public void setButtonPermission(String buttonPermission) {
		this.buttonPermission = buttonPermission;
	}
}

