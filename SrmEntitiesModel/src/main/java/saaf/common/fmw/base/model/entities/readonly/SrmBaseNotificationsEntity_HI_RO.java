package saaf.common.fmw.base.model.entities.readonly;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmBaseNotificationsEntity_HI_RO Entity Object
 * Mon Aug 13 10:46:25 CST 2018  Auto Generate
 */

public class SrmBaseNotificationsEntity_HI_RO {

    //资质预警查询
	public static final String QUERY_WARNING_SQL="SELECT\n" +
			"sbn.notification_id,\n" +
			"sbn.notification_code,\n" +
			"sbn.notification_status,\n" +
			"sbn.notification_type,\n" +
			"sbn.menu_type,\n" +
			"sbn.notification_content,\n" +
			"sbn.receiver_id,\n" +
			"sbn.viewed_flag,\n" +
			"sbn.view_date,\n" +
			"sbn.table_name,\n" +
			"sbn.table_id,\n" +
			"sbn.table_id_name,\n" +
			"sbn.function_url,\n" +
            "sbn.creation_date,\n" +
            "sbn.created_by,\n" +
            "sbn.last_update_date,\n" +
            "sbn.last_updated_by,\n" +
            "sbn.last_update_login\n" +
			"FROM\n" +
			"srm_base_notifications sbn\n" +
			"WHERE sbn.notification_type = '31'";

	//查询资质预警未处理条数
	public static final String QUERY_COUNT_WARNING_SQL =
					"SELECT COUNT(bn.notification_id) countUnRead\n" +
					"FROM   srm_base_notifications bn\n" +
					"LEFT   JOIN saaf_users u\n" +
					"ON     u.user_id = :userId\n" +
					"WHERE  bn.notification_type = '31'\n" +
					"AND    bn.notification_status = '1'\n" +
					"AND    bn.creation_date >= u.creation_date\n";

	public static final String QUERY_COUNT_RESPONS_SQL =
					"SELECT\n" +
					"  COUNT(1) COUNT\n" +
					"FROM\n" +
					"  saaf_responsibilitys sr,\n" +
					"  saaf_user_resp sur\n" +
					"WHERE 1 = 1\n" +
					"  AND sur.responsibility_id = sr.responsibility_id\n" +
					"  AND sr.responsibility_id = 1\n";


	//标书发布查询
	public static final String QUERY_AUCTIONPUBLISHLIST =
					"SELECT\n" +
					"  t.auction_header_id AS auctionHeaderId,\n" +
					"  t.auction_number AS auctionNumber,\n" +
					"  t.auction_title AS auctionTitle,\n" +
					"  t.auction_status AS auctionStatus,\n" +
					"  t.publish_approval_status AS publishApprovalStatus,\n" +
					"  t.auction_type AS auctionType,\n" +
					"  t.rounds AS rounds,\n" +
					"  t.auction_round_number AS auctionRoundNumber,\n" +
					"  t.bid_start_date AS bidStartDate,\n" +
					"  t.bid_end_date AS bidEndDate,\n" +
					"  t.creation_date AS creationDate,\n" +
					"  t.created_by AS createdBy,\n" +
					"  t.last_updated_by AS lastUpdatedBy,\n" +
					"  t.last_update_date AS lastUpdateDate,\n" +
					"  slv.meaning AS typeMean\n" +
					"FROM\n" +
					"  srm_pon_auction_headers t\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_type = 'PON_AUCTION_TYPE'\n" +
					"    AND slv.lookup_code = t.auction_type\n";

	//订单确认查询——内部
	public static final String QUERY_POHEADERLIST =
					"SELECT t.po_header_id            AS poHeaderId\n" +
					"      ,t.po_number               AS poNumber\n" +
					"      ,t.org_id                  AS orgId\n" +
					"      ,t.bill_to_organization_id AS billToOrganizationId\n" +
					"      ,t.po_doc_type             AS poDocType\n" +
					"      ,t.supplier_id             AS supplierId\n" +
					"      ,t.buyer_id                AS buyerId\n" +
					"      ,t.status                  AS status\n" +
					"      ,t.creation_date           AS creationDate\n" +
					"      ,t.created_by              AS createdBy\n" +
					"      ,t.last_updated_by         AS lastUpdatedBy\n" +
					"      ,t.last_update_date        AS lastUpdateDate\n" +
					"      ,si.inst_name              AS orgName\n" +
					"      ,sps.supplier_name         AS supplierName\n" +
					"FROM   srm_po_headers t\n" +
					"LEFT   JOIN saaf_institution si\n" +
					"ON     si.inst_id = t.org_id\n" +
					"AND    si.inst_type = 'ORG'\n" +
					"LEFT   JOIN srm_pos_supplier_info sps\n" +
					"ON     sps.supplier_id = t.supplier_id\n";

	//订单反馈查询——供应商
	public static final String QUERY_POLINELIST =
					"SELECT spl.po_line_id                 AS poLineId\n" +
					"      ,spl.po_header_id               AS poHeaderId\n" +
					"      ,sph.po_number                  AS poNumber\n" +
					"      ,sph.org_id                     AS orgId\n" +
					"      ,sph.po_doc_type                AS poDocType\n" +
					"      ,sph.supplier_id                AS supplierId\n" +
					"      ,sph.buyer_id                   AS buyerId\n" +
					"      ,sph.status                     AS status\n" +
					"      ,spl.feedback_status            AS feedbackStatus\n" +
					"      ,spl.status                     AS linestatus\n" +
					"      ,spl.receive_to_organization_id AS receiveToOrganizationId\n" +
					"      ,si.inst_name                   AS orgName\n" +
					"      ,si2.inst_name                  AS receiveToOrganizationName\n" +
					"      ,spl.creation_date              AS creationDate\n" +
					"      ,spl.created_by                 AS createdBy\n" +
					"      ,spl.last_updated_by            AS lastUpdatedBy\n" +
					"      ,spl.last_update_date           AS lastUpdateDate\n" +
					"FROM   srm_po_lines     spl\n" +
					"      ,srm_po_headers   sph\n" +
					"      ,saaf_institution si\n" +
					"      ,saaf_institution si2\n" +
					"WHERE  spl.po_header_id = sph.po_header_id\n" +
					"AND    sph.org_id = si.inst_id\n" +
					"AND    spl.receive_to_organization_id = si2.inst_id\n";

	//送货通知确认查询——内部
	public static final String QUERY_PONOTICELIST = "SELECT t.po_notice_id AS poNoticeId\n" +
			",t.po_notice_code AS poNoticeCode\n" +
			",t.org_id AS orgId\n" +
			",t.bill_to_organization_id AS billToOrganizationId\n" +
			",t.supplier_id AS supplierId\n" +
			",t.buyer_id AS buyerId\n" +
			",t.po_notice_status AS poNoticeStatus\n" +
			",t.creation_date AS creationDate\n" +
			",t.created_by AS createdBy\n" +
			",t.last_updated_by AS lastUpdatedBy\n" +
			",t.last_update_date AS lastUpdateDate\n" +
			",si.inst_name AS orgName\n" +
			",sps.supplier_name AS supplierName \n" +
			"FROM srm_po_notice t\n" +
			"LEFT JOIN saaf_institution si ON si.inst_id = t.org_id AND si.inst_type='ORG'\n" +
			"LEFT JOIN srm_pos_supplier_info sps ON sps.supplier_id=t.supplier_id\n";

	//送货通知反馈查询——供应商
	public static final String QUERY_PONOTICELINELIST =
					"SELECT t.line_id          AS lineid\n" +
					"      ,t.line_status      AS linestatus\n" +
					"      ,spn.po_notice_code AS ponoticecode\n" +
					"      ,spn.org_id         AS orgid\n" +
					"      ,spn.po_notice_id   AS ponoticeid\n" +
					"      ,si.inst_name       AS orgname\n" +
					"      ,si2.inst_name      AS receivetoorganizationname\n" +
					"      ,t.creation_date    AS creationdate\n" +
					"      ,t.created_by       AS createdby\n" +
					"      ,t.last_updated_by  AS lastupdatedby\n" +
					"      ,t.last_update_date AS lastupdatedate\n" +
					"FROM   srm_po_notice_line t\n" +
					"LEFT   JOIN srm_po_lines spl\n" +
					"ON     spl.po_line_id = t.po_line_id\n" +
					"LEFT   JOIN srm_po_notice spn\n" +
					"ON     spn.po_notice_id = t.po_notice_id\n" +
					"LEFT   JOIN saaf_institution si\n" +
					"ON     si.inst_id = spn.org_id\n" +
					"AND    si.inst_type = 'ORG'\n" +
					"LEFT   JOIN saaf_institution si2\n" +
					"ON     si2.inst_id = spl.receive_to_organization_id\n" +
					"AND    si2.inst_type = 'ORGANIZATION'\n";

	//待报价查询——供应商
	public static final String QUERY_PONBIDHEADERLIST =
					"SELECT sbn.table_id           AS bidHeaderId\n" +
					"      ,spah.auction_header_id AS auctionHeaderId\n" +
					"      ,spah.bid_start_date    AS bidStartDate\n" +
					"      ,spah.bid_end_date      AS bidEndDate\n" +
					"      ,spah.auction_title     AS auctionTitle\n" +
					"      ,spah.auction_number    AS auctionNumber\n" +
					"      ,spah.auction_type      AS auctionType\n" +
					"      ,spah.auction_status    AS auctionStatus\n" +
					"      ,slv.meaning            AS auctionTypeName\n" +
					"FROM   srm_base_notifications sbn\n" +
					"LEFT   JOIN srm_pon_auction_headers spah\n" +
					"ON     spah.auction_header_id in \n" +
					"       (SELECT spbh.auction_header_id\n" +
					"        FROM   srm_pon_bid_headers spbh\n" +
					"        WHERE  spbh.bid_header_id = sbn.table_id\n" +
					"        AND    sbn.table_name = 'srm_pon_bid_headers')\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_type = 'PON_AUCTION_TYPE'\n" +
					"AND    slv.lookup_code = spah.auction_type\n" +
					"WHERE  sbn.notification_type = '21'\n";


	private Integer notificationId; //通知ID
	private Integer count;
    private String notificationCode; //通知编号
    private String notificationStatus; //通知状态，快码：DAI_BAN_TYPE
    private String notificationType; //通知类型，快码
    private String menuType; //业务类型（菜单名称）
    private String notificationContent; //通知内容
    private Integer receiverId; //接收者ID（用户ID，职责ID，供应商ID）
    private String viewedFlag; //查看标识（Y：已查看，N：未查看）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date viewDate; //查看时间
    private String tableName; //关联功能的表名
    private Integer tableId; //关联功能的表ID
    private String tableIdName; //关联功能的表ID字段名
    private String functionUrl; //关联功能的链接
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
    private Integer operatorUserId;
    //别名
    private Integer countUnRead;//未读条数

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	
	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationCode(String notificationCode) {
		this.notificationCode = notificationCode;
	}

	
	public String getNotificationCode() {
		return notificationCode;
	}

	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	
	public String getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	
	public String getNotificationType() {
		return notificationType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	
	public String getMenuType() {
		return menuType;
	}

	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent;
	}

	
	public String getNotificationContent() {
		return notificationContent;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	
	public Integer getReceiverId() {
		return receiverId;
	}

	public void setViewedFlag(String viewedFlag) {
		this.viewedFlag = viewedFlag;
	}

	
	public String getViewedFlag() {
		return viewedFlag;
	}

	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}

	
	public Date getViewDate() {
		return viewDate;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	
	public Integer getTableId() {
		return tableId;
	}

	public void setTableIdName(String tableIdName) {
		this.tableIdName = tableIdName;
	}

	
	public String getTableIdName() {
		return tableIdName;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	
	public String getFunctionUrl() {
		return functionUrl;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	
	public Integer getVersionNum() {
		return versionNum;
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

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	public Integer getCountUnRead() {
		return countUnRead;
	}

	public void setCountUnRead(Integer countUnRead) {
		this.countUnRead = countUnRead;
	}

	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
