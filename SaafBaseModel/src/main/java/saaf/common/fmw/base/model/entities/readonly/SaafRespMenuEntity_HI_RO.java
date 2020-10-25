package saaf.common.fmw.base.model.entities.readonly;

import java.io.Serializable;


public class SaafRespMenuEntity_HI_RO implements Serializable {

    public SaafRespMenuEntity_HI_RO() {
        super();
    }

    //登录查询职责菜单
    public static String QUERY_SQL =
                    "SELECT smf.menu_id        menuId\n" +
                    "      ,smf.menu_parent_id menuParentId\n" +
                    "      ,smf.entry_sequence entrySequence\n" +
                    "      ,smf.prompt         prompt\n" +
                    "      ,smf.platform_code  platformCode\n" +
                    "      ,smf.web_html_call  webHtmlCall\n" +
                    "      ,smf.image_link     imageLink\n" +
                    "      ,smf.is_menu        isMenu\n" +
                    "      ,smf.is_mobile_show isMobileShow\n" +
                    "FROM   saaf_resp_menu      srm\n" +
                    "      ,saaf_menu_functions smf\n" +
                    "WHERE  srm.menu_id = smf.menu_id\n" +
                    "AND    smf.enable_flag = 'Y'\n" +
                    "AND    smf.start_date_active <= trunc(SYSDATE)\n" +
                    "AND    trunc(nvl(smf.end_date_active, SYSDATE)) >= trunc(SYSDATE)\n" +
                    "AND    srm.resp_id = :respId\n" +
                    "AND    smf.platform_code = :platformCode\n";

    public static String QUERY_BUTTON_SQL =
                    "SELECT srf.resp_id           respId\n" +
                    "      ,srf.resp_fun_id       respFunId\n" +
                    "      ,srf.menu_id           menuId\n" +
                    "      ,srf.fun_button_id     funButtonId\n" +
                    "      ,sfa.button_type       buttonType\n" +
                    "      ,sfa.button_code       buttonCode\n" +
                    "      ,sfa.button_name       buttonName\n" +
                    "      ,sfa.button_desc       buttonDesc\n" +
                    "      ,sfa.button_permission buttonpermission\n" +
                    "FROM   saaf_resp_fun       srf\n" +
                    "      ,saaf_functions_auth sfa\n" +
                    "WHERE  srf.fun_button_id = sfa.fun_button_id\n" +
                    "AND    srf.resp_id = ?\n" +
                    "AND    srf.platform_code = ?\n";

    //职责菜单,功能，按钮（已分配、未分配）
    public static String QUERY_RESP_MENU_BTN_SQL =
                    "SELECT menubtn.menu_id        menuId\n" +
                    "      ,menubtn.menu_parent_id menuParentId\n" +
                    "      ,menubtn.prompt         prompt\n" +
                    "      ,menubtn.is_menu        isMenu\n" +
                    "      ,menubtn.ischecked      ischecked\n" +
                    "      ,menubtn.menuorbtn      menuorbtn\n" +
                    "      ,menubtn.fun_button_id  funButtonId\n" +
                    "      ,menubtn.button_type    buttonType\n" +
                    "      ,menubtn.button_code    buttonCode\n" +
                    "      ,menubtn.button_name    buttonName\n" +
                    "      ,menubtn.button_desc    buttonDesc\n" +
                    "      ,menubtn.resp_fun_id    respFunId\n" +
                    "      ,menubtn.resp_id        respId\n" +
                    "      ,menubtn.platform_code  platformCode\n" +
                    "FROM   (SELECT men.menu_id\n" +
                    "              ,men.menu_parent_id\n" +
                    "              ,men.prompt\n" +
                    "              ,men.is_menu\n" +
                    "              ,decode(rme.resp_id, NULL, 'N', 'Y') ischecked\n" +
                    "              ,'MENU' menuorbtn\n" +
                    "              ,0 fun_button_id\n" +
                    "              ,'' button_type\n" +
                    "              ,'' button_code\n" +
                    "              ,'' button_name\n" +
                    "              ,'' button_desc\n" +
                    "              ,0 resp_fun_id\n" +
                    "              ,nvl(rme.resp_id, -99) resp_id\n" +
                    "              ,men.platform_code\n" +
                    "        FROM   saaf_menu_functions men\n" +
                    "        LEFT   JOIN saaf_resp_menu rme\n" +
                    "        ON     men.menu_id = rme.menu_id\n" +
                    "        AND    rme.resp_id = :respId\n" +
                    "        UNION ALL\n" +
                    "        SELECT (sfa.menu_id * 10000 +\n" +
                    "               nvl(srf.resp_fun_id, (90 + sfa.fun_button_id))) menu_id\n" +
                    "              ,sfa.menu_id menu_parent_id\n" +
                    "              ,sfa.button_name prompt\n" +
                    "              ,'B' is_menu\n" +
                    "              ,decode(srf.resp_fun_id, NULL, 'N', 'Y') ischecked\n" +
                    "              ,'BUTTON' menuorbtn\n" +
                    "              ,sfa.fun_button_id\n" +
                    "              ,sfa.button_type\n" +
                    "              ,sfa.button_code\n" +
                    "              ,sfa.button_name\n" +
                    "              ,sfa.button_desc\n" +
                    "              ,srf.resp_fun_id\n" +
                    "              ,nvl(srf.resp_id, -99) resp_id\n" +
                    "              ,sfa.platform_code\n" +
                    "        FROM   saaf_functions_auth sfa\n" +
                    "        LEFT   JOIN saaf_resp_fun srf\n" +
                    "        ON     sfa.fun_button_id = srf.fun_button_id\n" +
                    "        AND    srf.resp_id = :respId) menubtn\n" +
                    "WHERE  menubtn.platform_code = :platformCode\n" +
                    "AND    (menubtn.resp_id = :respId OR menubtn.resp_id = -99)\n" +
                    "ORDER  BY menubtn.menu_parent_id\n" +
                    "         ,menubtn.menu_id\n" +
                    "         ,menubtn.fun_button_id\n";

    //职责菜单,功能，按钮（已分配）
    public static String QUERY_RESP_MENU_BTN_CHECKED_SQL =
                    "SELECT menubtn.menu_id        menuId\n" +
                    "      ,menubtn.menu_parent_id menuParentId\n" +
                    "      ,menubtn.prompt         prompt\n" +
                    "      ,menubtn.is_menu        isMenu\n" +
                    "      ,menubtn.menuorbtn      menuorbtn\n" +
                    "      ,menubtn.fun_button_id  funButtonId\n" +
                    "      ,menubtn.button_type    buttonType\n" +
                    "      ,menubtn.button_code    buttonCode\n" +
                    "      ,menubtn.button_name    buttonName\n" +
                    "      ,menubtn.button_desc    buttonDesc\n" +
                    "      ,menubtn.resp_menu_id   respMenuId\n" +
                    "      ,menubtn.resp_fun_id    respFunId\n" +
                    "      ,menubtn.resp_id        respId\n" +
                    "      ,menubtn.platform_code  platformCode\n" +
                    "FROM   (SELECT men.menu_id\n" +
                    "              ,men.menu_parent_id\n" +
                    "              ,men.prompt\n" +
                    "              ,men.is_menu\n" +
                    "              ,'MENU' menuorbtn\n" +
                    "              ,rme.resp_menu_id\n" +
                    "              ,0 fun_button_id\n" +
                    "              ,'' button_type\n" +
                    "              ,'' button_code\n" +
                    "              ,'' button_name\n" +
                    "              ,'' button_desc\n" +
                    "              ,0 resp_fun_id\n" +
                    "              ,nvl(rme.resp_id, -99) resp_id\n" +
                    "              ,men.platform_code\n" +
                    "        FROM   saaf_menu_functions men\n" +
                    "              ,saaf_resp_menu      rme\n" +
                    "        WHERE  men.menu_id = rme.menu_id\n" +
                    "        UNION ALL\n" +
                    "        SELECT (sfa.menu_id * 10000 +\n" +
                    "               nvl(srf.resp_fun_id, (90 + sfa.fun_button_id))) menu_id\n" +
                    "              ,sfa.menu_id menu_parent_id\n" +
                    "              ,sfa.button_name prompt\n" +
                    "              ,'B' is_menu\n" +
                    "              ,'BUTTON' menuorbtn\n" +
                    "              ,0 resp_menu_id\n" +
                    "              ,sfa.fun_button_id\n" +
                    "              ,sfa.button_type\n" +
                    "              ,sfa.button_code\n" +
                    "              ,sfa.button_name\n" +
                    "              ,sfa.button_desc\n" +
                    "              ,srf.resp_fun_id\n" +
                    "              ,nvl(srf.resp_id, -99) resp_id\n" +
                    "              ,sfa.platform_code\n" +
                    "        FROM   saaf_functions_auth sfa\n" +
                    "              ,saaf_resp_fun       srf\n" +
                    "        WHERE  sfa.fun_button_id = srf.fun_button_id) menubtn\n" +
                    "WHERE  menubtn.platform_code = ?\n" +
                    "AND    (menubtn.resp_id = ? OR menubtn.resp_id = -99)\n" +
                    "ORDER  BY menubtn.menu_parent_id\n" +
                    "         ,menubtn.menu_id\n" +
                    "         ,menubtn.fun_button_id\n";

    //查询不在快捷菜单中的用户菜单
    public static final String QUERY_USER_ALL_MENU_NOTIN_SHORTCUT =
                    "SELECT saaf_resp_menu.resp_id                respId\n" +
                    "      ,saaf_resp_menu.menu_id                menuId\n" +
                    "      ,saaf_menu_functions.image_link        imageLink\n" +
                    "      ,saaf_menu_functions.enable_flag       enableFlag\n" +
                    "      ,saaf_menu_functions.web_html_call     webHtmlCall\n" +
                    "      ,saaf_menu_functions.start_date_active startDateActive\n" +
                    "      ,saaf_menu_functions.end_date_active   endDateActive\n" +
                    "      ,saaf_menu_functions.prompt            prompt\n" +
                    "      ,saaf_menu_functions.is_menu           isMenu\n" +
                    "FROM   saaf_resp_menu\n" +
                    "INNER  JOIN saaf_menu_functions\n" +
                    "ON     saaf_resp_menu.menu_id = saaf_menu_functions.menu_id\n" +
                    "AND    saaf_menu_functions.start_date_active <= trunc(SYSDATE)\n" +
                    "AND    trunc(nvl(saaf_menu_functions.end_date_active, SYSDATE + 1)) >= trunc(SYSDATE)\n" +
                    "AND    saaf_menu_functions.enable_flag = 'Y'\n" +
                    "AND    saaf_menu_functions.is_menu = 'N'\n" +
                    "WHERE  1 = 1\n" +
                    "AND    saaf_resp_menu.resp_id = :varInputRespId\n" +
                    "AND    saaf_resp_menu.resp_id IN\n" +
                    "       (SELECT saaf_user_resp.responsibility_id\n" +
                    "         FROM   saaf_user_resp\n" +
                    "         WHERE  saaf_user_resp.user_id = :varUserId)\n" +
                    "AND    saaf_resp_menu.menu_id NOT IN\n" +
                    "       (SELECT saaf_shortcut.menu_id\n" +
                    "         FROM   saaf_shortcut\n" +
                    "         WHERE  saaf_shortcut.user_id = :varUserId\n" +
                    "         AND    saaf_shortcut.resp_id = :varInputRespId)\n";

    private Integer menuId;
    private Integer menuParentId;
    private Integer entrySequence;
    private String prompt;
    private String platformCode;
    private String webHtmlCall;
    private String isMenu;

    private Integer responsibilityId;
    private String responsibilityName;

    private String ischecked;
    private String menuorbtn;

    private Integer funButtonId;
    private String buttonType;
    private String buttonCode;
    private String buttonName;
    private String buttonDesc;
    private Integer respMenuId;
    private Integer respFunId;
    private Integer respId;
    private String imageLink;
    private String buttonPermission;


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

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getPlatformCode() {
        return platformCode;
    }


    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }

    public String getIsMenu() {
        return isMenu;
    }

    public void setResponsibilityId(Integer responsibilityId) {
        this.responsibilityId = responsibilityId;
    }

    public Integer getResponsibilityId() {
        return responsibilityId;
    }

    public void setResponsibilityName(String responsibilityName) {
        this.responsibilityName = responsibilityName;
    }

    public String getResponsibilityName() {
        return responsibilityName;
    }

    public void setIschecked(String ischecked) {
        this.ischecked = ischecked;
    }

    public String getIschecked() {
        return ischecked;
    }

    public void setMenuorbtn(String menuorbtn) {
        this.menuorbtn = menuorbtn;
    }

    public String getMenuorbtn() {
        return menuorbtn;
    }

    public void setFunButtonId(Integer funButtonId) {
        this.funButtonId = funButtonId;
    }

    public Integer getFunButtonId() {
        return funButtonId;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }

    public String getButtonType() {
        return buttonType;
    }

    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode;
    }

    public String getButtonCode() {
        return buttonCode;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonDesc(String buttonDesc) {
        this.buttonDesc = buttonDesc;
    }

    public String getButtonDesc() {
        return buttonDesc;
    }

    public void setRespFunId(Integer respFunId) {
        this.respFunId = respFunId;
    }

    public Integer getRespFunId() {
        return respFunId;
    }

    public void setRespId(Integer respId) {
        this.respId = respId;
    }

    public Integer getRespId() {
        return respId;
    }

    public void setEntrySequence(Integer entrySequence) {
        this.entrySequence = entrySequence;
    }

    public Integer getEntrySequence() {
        return entrySequence;
    }

    public void setWebHtmlCall(String webHtmlCall) {
        this.webHtmlCall = webHtmlCall;
    }

    public String getWebHtmlCall() {
        return webHtmlCall;
    }

    public void setRespMenuId(Integer respMenuId) {
        this.respMenuId = respMenuId;
    }

    public Integer getRespMenuId() {
        return respMenuId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

	public String getButtonPermission() {
		return buttonPermission;
	}

	public void setButtonPermission(String buttonPermission) {
		this.buttonPermission = buttonPermission;
	}

}
