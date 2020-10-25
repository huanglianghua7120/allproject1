package saaf.common.fmw.base.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.*;


/**
 * SaafMenuFunctionsEntity_HI Entity Object
 * Thu Apr 20 11:13:24 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_menu_functions")
public class SaafMenuFunctionsEntity_HI {
    private Integer menuId;
    private Integer menuParentId;
    private Integer entrySequence;
    private String menuTittle;
    private String prompt;
    private String imageLink;
    private String imageColor;
    private String parameter;
    private String platformCode;
    private String enableFlag;
    private String description;
    private String functionsType;
    private String menuLanguage;
    private String actionBean;
    private String webHtmlCall;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDateActive;
    private String isMenu;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private Integer versionNum;
    private Integer operatorUserId;
    private String isMobileShow;
    private String categoryFlag;    //是否启用采购分类权限控制

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Id
    @SequenceGenerator(name = "ISEQ$$_77818", sequenceName = "ISEQ$$_77818", allocationSize = 1)
    @GeneratedValue(generator = "ISEQ$$_77818", strategy = GenerationType.SEQUENCE)
    @Column(name = "menu_id", nullable = false, length = 11)
    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
    }

    @Column(name = "menu_parent_id", precision = 22, scale = 0)
    public Integer getMenuParentId() {
        return menuParentId;
    }

    public void setEntrySequence(Integer entrySequence) {
        this.entrySequence = entrySequence;
    }

    @Column(name = "entry_sequence", precision = 22, scale = 0)
    public Integer getEntrySequence() {
        return entrySequence;
    }

    public void setMenuTittle(String menuTittle) {
        this.menuTittle = menuTittle;
    }

    @Column(name = "menu_tittle", nullable = true, length = 100)
    public String getMenuTittle() {
        return menuTittle;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    @Column(name = "prompt", nullable = true, length = 60)
    public String getPrompt() {
        return prompt;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Column(name = "image_link", nullable = true, length = 60)
    public String getImageLink() {
        return imageLink;
    }
    
    @Column(name = "image_color", nullable = true, length = 10)
    public String getImageColor() {
		return imageColor;
	}

	public void setImageColor(String imageColor) {
		this.imageColor = imageColor;
	}

	public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Column(name = "parameter", nullable = true, length = 2000)
    public String getParameter() {
        return parameter;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    @Column(name = "platform_code", nullable = true, length = 30)
    public String getPlatformCode() {
        return platformCode;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    @Column(name = "enable_flag", nullable = true, length = 10)
    public String getEnableFlag() {
        return enableFlag;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "description", nullable = true, length = 240)
    public String getDescription() {
        return description;
    }

    public void setFunctionsType(String functionsType) {
        this.functionsType = functionsType;
    }

    @Column(name = "functions_type", nullable = true, length = 30)
    public String getFunctionsType() {
        return functionsType;
    }

    public void setMenuLanguage(String menuLanguage) {
        this.menuLanguage = menuLanguage;
    }

    @Column(name = "menu_language", nullable = true, length = 30)
    public String getMenuLanguage() {
        return menuLanguage;
    }

    public void setActionBean(String actionBean) {
        this.actionBean = actionBean;
    }

    @Column(name = "action_bean", nullable = true, length = 30)
    public String getActionBean() {
        return actionBean;
    }

    public void setWebHtmlCall(String webHtmlCall) {
        this.webHtmlCall = webHtmlCall;
    }

    @Column(name = "web_html_call", nullable = true, length = 240)
    public String getWebHtmlCall() {
        return webHtmlCall;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    @Column(name = "start_date_active", nullable = true, length = 0)
    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    @Column(name = "end_date_active", nullable = true, length = 0)
    public Date getEndDateActive() {
        return endDateActive;
    }

    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }

    @Column(name = "is_menu", nullable = true, length = 10)
    public String getIsMenu() {
        return isMenu;
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

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Version
    @Column(name = "version_num", precision = 11, scale = 0)
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    @Column(name = "is_mobile_show", nullable = true, length = 10)
	public String getIsMobileShow() {
		return isMobileShow;
	}

	public void setIsMobileShow(String isMobileShow) {
		this.isMobileShow = isMobileShow;
	}

    @Column(name = "category_flag", nullable = true, length = 10)
    public String getCategoryFlag() {
        return categoryFlag;
    }

    public void setCategoryFlag(String categoryFlag) {
        this.categoryFlag = categoryFlag;
    }
}
