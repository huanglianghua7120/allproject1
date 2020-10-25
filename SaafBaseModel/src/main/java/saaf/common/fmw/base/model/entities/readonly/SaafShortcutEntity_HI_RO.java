package saaf.common.fmw.base.model.entities.readonly;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SaafShortcutEntity_HI_RO Entity Object
 * Tue Sep 05 10:13:44 CST 2017  Auto Generate
 */

public class SaafShortcutEntity_HI_RO implements Serializable{
	
	public SaafShortcutEntity_HI_RO(){
		super();
	}
	
	public static final String QUERY_SHORTCUT_IN_USER =
					"SELECT saaf_shortcut.shortcut_id         shortcutId\n" +
					"      ,saaf_shortcut.menu_id             menuId\n" +
					"      ,saaf_menu_functions.image_link    imageLink\n" +
					"      ,saaf_menu_functions.prompt        prompt\n" +
					"      ,saaf_menu_functions.web_html_call webHtmlCall\n" +
					"      ,saaf_menu_functions.image_color   imageColor\n" +
					"FROM   saaf_shortcut\n" +
					"INNER  JOIN saaf_menu_functions\n" +
					"ON     saaf_shortcut.menu_id = saaf_menu_functions.menu_id\n" +
					"AND    trunc(saaf_menu_functions.start_date_active) <= trunc(SYSDATE)\n" +
					"AND    trunc(nvl(saaf_menu_functions.end_date_active, SYSDATE)) >=\n" +
					"       trunc(SYSDATE)\n" +
					"AND    saaf_menu_functions.enable_flag = 'Y'\n" +
					"AND    saaf_menu_functions.is_menu = 'N'\n" +
					"WHERE  1 = 1\n" +
					"AND    saaf_shortcut.user_id = :varUserId\n" +
					"AND    saaf_shortcut.resp_id = :varRespId\n";

	private Integer shortcutId; //表ID，主键，供其他表做外键
    private Integer userId; //用户ID
    private Integer respId; //职责id
    private Integer menuId; //菜单id
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
    private Integer operatorUserId;
    
    private String imageLink;
    private String prompt;
    private String webHtmlCall;
    private String imageColor;

	public void setShortcutId(Integer shortcutId) {
		this.shortcutId = shortcutId;
	}

	
	public Integer getShortcutId() {
		return shortcutId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public Integer getUserId() {
		return userId;
	}

	public void setRespId(Integer respId) {
		this.respId = respId;
	}

	
	public Integer getRespId() {
		return respId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	
	public Integer getMenuId() {
		return menuId;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	
	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	
	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	
	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	
	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	
	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	
	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	
	public String getAttribute15() {
		return attribute15;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}


	public String getPrompt() {
		return prompt;
	}


	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}


	public String getWebHtmlCall() {
		return webHtmlCall;
	}


	public void setWebHtmlCall(String webHtmlCall) {
		this.webHtmlCall = webHtmlCall;
	}


	public String getImageColor() {
		return imageColor;
	}


	public void setImageColor(String imageColor) {
		this.imageColor = imageColor;
	}
}
