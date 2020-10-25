package saaf.common.fmw.base.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

import java.util.Date;


public class SaafMenuFuncEntity_HI_RO implements Serializable {
    public SaafMenuFuncEntity_HI_RO() {
        super();
    }

    //菜单全部Tree
    public static String QUERY_TREE_SQL = " SELECT menu.menu_id        \"menuId\"\r\n" +
        "       ,menu.menu_parent_id \"menuParentId\"\r\n" +
        "       ,menu.prompt         \"prompt\"\r\n" +
        "       ,menu.is_menu        \"isMenu\"\r\n" +
        "       ,menu.is_mobile_show        \"isMobileShow\"\r\n" +
        "  FROM saaf_menu_functions menu\r\n" +
        " WHERE menu.platform_code = ?";

    //菜单行
    public static String QUERY_LINE_SQL = " SELECT\n" +
        "       smf.menu_id \"menuId\",\r\n" +
        "       smf.menu_parent_id \"menuParentId\",\r\n" +
        "       smf.entry_sequence \"entrySequence\",\r\n" +
        "       smf.prompt \"prompt\",\r\n" +
        "       smf.menu_tittle \"menuTittle\",\r\n" +
        "       smf.is_menu \"isMenu\",\r\n" +
        "       smf.start_date_active \"startDateActive\",\r\n" +
        "       smf.end_date_active \"endDateActive\",\r\n" +
        "       smf.enable_flag \"enableFlag\",\r\n" +
        "       smf.functions_type \"functionsType\",\r\n" +
        "       smf.web_html_call \"webHtmlCall\",\r\n" +
        "       smf.image_link \"imageLink\",\r\n" +
        "       smf.description \"description\",\r\n" +
        "       smf.is_mobile_show \"isMobileShow\",\r\n" +
        "       smf.category_flag \"categoryFlag\",\r\n" +
        "       smf.image_color \"imageColor\"\r\n" +
        " FROM\n" +
        "       saaf_menu_functions smf\n" +
        " WHERE\n" +
        "       smf.menu_id = ?\n" +
        " ORDER BY\n" +
        "       smf.menu_parent_id,\n" +
        "       smf.entry_sequence ";

    private Integer menuId;
    private Integer menuParentId;
    private Integer entrySequence;
    private String prompt;
    private String menuTittle;
    private String isMenu;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDateActive;
    private String enableFlag;
    private String functionsType;
    private String platformCode;
    private String webHtmlCall;
    private String imageLink;
    private String description;
    private String isMobileShow;
    private String imageColor;
    private String categoryFlag;

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
    }

    public Integer getMenuParentId() {
        return menuParentId;
    }

    public void setEntrySequence(Integer entrySequence) {
        this.entrySequence = entrySequence;
    }

    public Integer getEntrySequence() {
        return entrySequence;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setMenuTittle(String menuTittle) {
        this.menuTittle = menuTittle;
    }

    public String getMenuTittle() {
        return menuTittle;
    }

    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }

    public String getIsMenu() {
        return isMenu;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    public Date getEndDateActive() {
        return endDateActive;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setFunctionsType(String functionsType) {
        this.functionsType = functionsType;
    }

    public String getFunctionsType() {
        return functionsType;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setWebHtmlCall(String webHtmlCall) {
        this.webHtmlCall = webHtmlCall;
    }

    public String getWebHtmlCall() {
        return webHtmlCall;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

	public String getIsMobileShow() {
		return isMobileShow;
	}

	public void setIsMobileShow(String isMobileShow) {
		this.isMobileShow = isMobileShow;
	}

	public String getImageColor() {
		return imageColor;
	}

	public void setImageColor(String imageColor) {
		this.imageColor = imageColor;
	}

    public String getCategoryFlag() {
        return categoryFlag;
    }

    public void setCategoryFlag(String categoryFlag) {
        this.categoryFlag = categoryFlag;
    }
}
