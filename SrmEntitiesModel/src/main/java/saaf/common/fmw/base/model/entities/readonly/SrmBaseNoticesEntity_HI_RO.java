package saaf.common.fmw.base.model.entities.readonly;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmBaseNoticesEntity_HI_RO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//查询公告列表
	public static final String QUERY_NOTICES_SQL="select n.notice_id noticeId\r\n" + 
			",n.notice_code noticeCode\r\n" + 
			",n.notice_title noticeTitle\r\n" + 
			",n.notice_type noticeType\r\n" + 
			",n.notice_status noticeStatus\r\n" + 
			",n.set_top_flag	setTopFlag\r\n" + 
			",n.publish_date publishDate\r\n" + 
			",n.creation_date creationDate\r\n" + 
			",n.last_update_date lastUpdateDate\r\n" + 
			",u.user_full_name creater\r\n" + 
			",su.user_full_name lastModifier\r\n" + 
			",slv.meaning noticeTypeMean\r\n" + 
			",slv2.meaning noticeStatusMean\r\n" + 
			"from \r\n" + 
			"srm_base_notices n\r\n" + 
			"left join saaf_users u on n.created_by=u.user_id\r\n" + 
			"left join saaf_users su on n.last_updated_by = su.user_id\r\n" + 
			"left join saaf_lookup_values slv on n.notice_type = slv.lookup_code\r\n" + 
			"and slv.lookup_type='ANNOUNCEMENT _TYPE'\r\n" + 
			"left join saaf_lookup_values slv2 on n.notice_status = slv2.lookup_code\r\n" + 
			"and slv2.lookup_type ='ANNOUNCEMENT_STATUS'\r\n" + 
			"where 1=1 ";
	
	//查询公告信息
	public static final String QUERY_NOTICES_INFO_SQL="select n.notice_id noticeId\r\n" + 
			",n.notice_code noticeCode\r\n" + 
			",n.notice_title noticeTitle\r\n" + 
			",n.notice_content noticeContent\r\n" + 
			",n.notice_type noticeType\r\n" + 
			",n.notice_status noticeStatus\r\n" + 
			",n.set_top_flag	setTopFlag\r\n" +
			",n.publish_date publishDate\r\n" + 
			",n.receiver_type receiverType\r\n" + 
			",slv.meaning noticeTypeMean\r\n" + 
			",slv2.meaning noticeStatusMean\r\n" + 
			",slv3.meaning receiverTypeMean\r\n" + 
			"from \r\n" + 
			"srm_base_notices n\r\n" + 
			"left join saaf_users u on n.created_by=u.user_id\r\n" + 
			"left join saaf_lookup_values slv on n.notice_type = slv.lookup_code\r\n" + 
			"and slv.lookup_type='ANNOUNCEMENT _TYPE'\r\n" + 
			"left join saaf_lookup_values slv2 on n.notice_status = slv2.lookup_code\r\n" + 
			"and slv2.lookup_type ='ANNOUNCEMENT_STATUS'\r\n" + 
			"left join saaf_lookup_values slv3 on n.receiver_type = slv3.lookup_code\r\n" + 
			"and slv3.lookup_type ='PUBLISHING_OBJECT'\r\n" + 
			"where 1=1";
	
	//查询已发布的公告
	public static final String QUERY_RELEASE_NOTICES_SQL="select c.* from(select n.notice_id noticeId\r\n" + 
			"	,n.notice_title noticeTitle\r\n" + 
			"	,n.set_top_flag setTopFlag\r\n" + 
			"	,n.publish_date publishDate\r\n" + 
			"	,n.receiver_type receiverType\r\n" + 
			"from srm_base_notices n\r\n" + 
			"where 1=1\r\n" + 
			"and n.notice_status = '2'\r\n" + 
			"and n.receiver_type ='1'\r\n" + 
			"union all \r\n" + 
			"select n.notice_id noticeId\r\n" + 
			"	,n.notice_title noticeTitle\r\n" + 
			"	,n.set_top_flag setTopFlag\r\n" + 
			"	,n.publish_date publishDate\r\n" + 
			"	,n.receiver_type receiverType\r\n" + 
			"from srm_base_notices n\r\n" + 
			"left join srm_base_notice_receivers r on n.notice_id = r.notice_id\r\n" + 
			"left join saaf_institution i on i.inst_id = r.receiver_id\r\n" + 
			"left join saaf_user_access_orgs o on o.inst_id = i.inst_id\r\n" + 
			"where 1=1\r\n" + 
			"and n.notice_status = '2'\r\n" + 
			"and n.receiver_type ='2'\r\n" + 
			"and o.user_id = :varUserId\r\n" + 
			"union all\r\n" + 
			"select n.notice_id noticeId\r\n" + 
			"	,n.notice_title noticeTitle\r\n" + 
			"	,n.set_top_flag setTopFlag\r\n" + 
			"	,n.publish_date publishDate\r\n" + 
			"	,n.receiver_type receiverType\r\n" + 
			"from srm_base_notices n\r\n" + 
			"left join srm_base_notice_receivers r on n.notice_id = r.notice_id\r\n" + 
			"left join saaf_employees s on s.employee_id = r.receiver_id \r\n" + 
			"where 1=1\r\n" + 
			"and n.notice_status = '2'\r\n" + 
			"and n.receiver_type = '3'\r\n" + 
			"and s.user_id = :varUserId\r\n" + 
			"union all\r\n" + 
			"select n.notice_id noticeId\r\n" + 
			"	,n.notice_title noticeTitle\r\n" + 
			"	,n.set_top_flag setTopFlag\r\n" + 
			"	,n.publish_date publishDate\r\n" + 
			"	,n.receiver_type receiverType\r\n" + 
			"from srm_base_notices n\r\n" + 
			"left join srm_base_notice_receivers r on n.notice_id = r.notice_id\r\n" + 
			"left join srm_pos_supplier_info i on r.receiver_id = i.supplier_id\r\n" + 
			"left join saaf_users u on u.supplier_id = i.supplier_id\r\n" + 
			"where 1=1\r\n" + 
			"and n.notice_status = '2'\r\n" + 
			"and n.receiver_type ='4'\r\n" + 
			"and u.user_id = :varUserId\r\n" + 
			" )c";
	
	//查询已发布的公告列表
	public static final String QUERY_PUBLISH_NOTICES_SQL="select  c.*\r\n" + 
			"from (select n.notice_id noticeId\r\n" + 
			"	,n.notice_code noticeCode\r\n" + 
			"	,n.notice_title noticeTitle\r\n" + 
			"	,n.notice_type noticeType\r\n" + 
			"	,n.publish_date publishDate\r\n" + 
			"	,n.set_top_flag setTopFlag\r\n" + 
			"	,u.user_full_name creater\r\n" + 
			"	,slv.meaning noticeTypeMean\r\n" + 
			"	,(SELECT count(*) from srm_base_notice_viewers v \r\n" + 
			"		WHERE v.user_id= :varUserId and n.notice_id= v.notice_id) noticeCount\r\n" + 
			"from \r\n" + 
			"srm_base_notices n\r\n" + 
			"left join saaf_users u on n.created_by=u.user_id\r\n" + 
			"left join saaf_lookup_values slv on n.notice_type = slv.lookup_code\r\n" + 
			"and slv.lookup_type='ANNOUNCEMENT _TYPE'\r\n" + 
			"where 1=1 \r\n" + 
			"and n.notice_status = 2\r\n" + 
			"and n.receiver_type = '1'\r\n" + 
			"union all\r\n" + 
			"select n.notice_id noticeId\r\n" + 
			"	,n.notice_code noticeCode\r\n" + 
			"	,n.notice_title noticeTitle\r\n" + 
			"	,n.notice_type noticeType\r\n" + 
			"	,n.publish_date publishDate\r\n" + 
			"	,n.set_top_flag setTopFlag\r\n" + 
			"	,u.user_full_name creater\r\n" + 
			"	,slv.meaning noticeTypeMean\r\n" + 
			"	,(SELECT count(*) from srm_base_notice_viewers v \r\n" + 
			"		WHERE v.user_id= :varUserId and n.notice_id= v.notice_id) noticeCount\r\n" + 
			"from \r\n" + 
			"srm_base_notices n\r\n" + 
			"left join saaf_users u on n.created_by=u.user_id\r\n" + 
			"left join srm_base_notice_receivers r on n.notice_id = r.notice_id\r\n" + 
			"left join saaf_institution i on i.inst_id = r.receiver_id\r\n" + 
			"left join saaf_user_access_orgs o on o.inst_id = i.inst_id\r\n" + 
			"left join saaf_lookup_values slv on n.notice_type = slv.lookup_code\r\n" + 
			"and slv.lookup_type='ANNOUNCEMENT _TYPE'\r\n" + 
			"where 1=1 \r\n" + 
			"and n.notice_status = 2\r\n" + 
			"and n.receiver_type = '2'\r\n" + 
			"and o.user_id = :varUserId\r\n" + 
			"union all \r\n" + 
			"select n.notice_id noticeId\r\n" + 
			"	,n.notice_code noticeCode\r\n" + 
			"	,n.notice_title noticeTitle\r\n" + 
			"	,n.notice_type noticeType\r\n" + 
			"	,n.publish_date publishDate\r\n" + 
			"	,n.set_top_flag setTopFlag\r\n" + 
			"	,u.user_full_name creater\r\n" + 
			"	,slv.meaning noticeTypeMean\r\n" + 
			"	,(SELECT count(*) from srm_base_notice_viewers v \r\n" + 
			"		WHERE v.user_id= :varUserId and n.notice_id= v.notice_id) noticeCount\r\n" + 
			"from \r\n" + 
			"srm_base_notices n\r\n" + 
			"left join saaf_users u on n.created_by=u.user_id\r\n" + 
			"left join srm_base_notice_receivers r on n.notice_id = r.notice_id\r\n" + 
			"left join saaf_employees s on s.employee_id = r.receiver_id \r\n" + 
			"left join saaf_lookup_values slv on n.notice_type = slv.lookup_code\r\n" + 
			"and slv.lookup_type='ANNOUNCEMENT _TYPE'\r\n" + 
			"where 1=1 \r\n" + 
			"and n.notice_status = 2\r\n" + 
			"and n.receiver_type = '3'\r\n" + 
			"and s.user_id = :varUserId\r\n" + 
			"union all \r\n" + 
			"select n.notice_id noticeId\r\n" + 
			"	,n.notice_code noticeCode\r\n" + 
			"	,n.notice_title noticeTitle\r\n" + 
			"	,n.notice_type noticeType\r\n" + 
			"	,n.publish_date publishDate\r\n" + 
			"	,n.set_top_flag setTopFlag\r\n" + 
			"	,u.user_full_name creater\r\n" + 
			"	,slv.meaning noticeTypeMean\r\n" + 
			"	,(SELECT count(*) from srm_base_notice_viewers v \r\n" + 
			"		WHERE v.user_id= :varUserId and n.notice_id= v.notice_id) noticeCount\r\n" + 
			"from \r\n" + 
			"srm_base_notices n\r\n" + 
			"left join saaf_users u on n.created_by=u.user_id\r\n" + 
			"left join srm_base_notice_receivers r on n.notice_id = r.notice_id\r\n" + 
			"left join srm_pos_supplier_info i on r.receiver_id = i.supplier_id\r\n" + 
			"left join saaf_users se on se.supplier_id = i.supplier_id\r\n" + 
			"left join saaf_lookup_values slv on n.notice_type = slv.lookup_code\r\n" + 
			"and slv.lookup_type='ANNOUNCEMENT _TYPE'\r\n" + 
			"where 1=1 \r\n" + 
			"and n.notice_status = 2\r\n" + 
			"and n.receiver_type = '4'\r\n" + 
			"and se.user_id = :varUserId\r\n" + 
			")c where 1=1\n";
	
	//查询自已有多少公告
	public static final String QUERY_NOTICES_BY_SELF_SQL="select  c.*\r\n" + 
			"from (select n.notice_id noticeId\r\n" + 
			"	,n.notice_code noticeCode\r\n" + 
			"	,n.notice_title noticeTitle\r\n" + 
			"	,n.notice_type noticeType\r\n" + 
			"	,n.receiver_type receiverType\r\n" +
			"	,n.publish_date publishDate\r\n" +
			"from \r\n" + 
			"srm_base_notices n\r\n" + 
			"where 1=1 \r\n" + 
			"and n.notice_status = 2\r\n" + 
			"and n.receiver_type = '1'\r\n" + 
			"union all\r\n" + 
			"select n.notice_id noticeId\r\n" + 
			"	,n.notice_code noticeCode\r\n" + 
			"	,n.notice_title noticeTitle\r\n" + 
			"	,n.notice_type noticeType\r\n" + 
			"	,n.receiver_type receiverType\r\n" + 
			"	,n.publish_date publishDate\r\n" +
			"from \r\n" + 
			"srm_base_notices n\r\n" + 
			"left join srm_base_notice_receivers r on n.notice_id = r.notice_id\r\n" + 
			"left join saaf_institution i on i.inst_id = r.receiver_id\r\n" + 
			"left join saaf_user_access_orgs o on o.inst_id = i.inst_id\r\n" + 
			"where 1=1 \r\n" + 
			"and n.notice_status = 2\r\n" + 
			"and n.receiver_type = '2'\r\n" + 
			"and o.user_id = :varUserId\r\n" + 
			"union all \r\n" + 
			"select n.notice_id noticeId\r\n" + 
			"	,n.notice_code noticeCode\r\n" + 
			"	,n.notice_title noticeTitle\r\n" + 
			"	,n.notice_type noticeType\r\n" + 
			"	,n.receiver_type receiverType\r\n" + 
			"	,n.publish_date publishDate\r\n" +
			"from \r\n" + 
			"srm_base_notices n\r\n" + 
			"left join srm_base_notice_receivers r on n.notice_id = r.notice_id\r\n" + 
			"left join saaf_employees s on s.employee_id = r.receiver_id \r\n" + 
			"where 1=1 \r\n" + 
			"and n.notice_status = 2\r\n" + 
			"and n.receiver_type = '3'\r\n" + 
			"and s.user_id = :varUserId\r\n" + 
			"union all \r\n" + 
			"select n.notice_id noticeId\r\n" + 
			"	,n.notice_code noticeCode\r\n" + 
			"	,n.notice_title noticeTitle\r\n" + 
			"	,n.notice_type noticeType\r\n" + 
			"	,n.receiver_type receiverType\r\n" + 
			"	,n.publish_date publishDate\r\n" +
			"from \r\n" + 
			"srm_base_notices n\r\n" + 
			"left join srm_base_notice_receivers r on n.notice_id = r.notice_id\r\n" + 
			"left join srm_pos_supplier_info i on r.receiver_id = i.supplier_id\r\n" + 
			"left join saaf_users se on se.supplier_id = i.supplier_id\r\n" + 
			"where 1=1 \r\n" + 
			"and n.notice_status = 2\r\n" + 
			"and n.receiver_type = '4'\r\n" + 
			"and se.user_id = :varUserId\r\n" + 
			")c where 1=1\r\n" + 
			"and not exists \r\n" + 
			"(select * from srm_base_notice_viewers \r\n" + 
			" v where v.notice_id = c.noticeId and v.user_id = :varUserId )";
	
	//查询公告附件
	public static final String QUERY_NOTICE_FILES_SQL="select f.notice_file_id noticeFileId\r\n" + 
			",f.notice_id noticeId\r\n" + 
			",f.created_by createdBy\r\n" + 
			",f.creation_date creationDate\r\n" + 
			",f.last_update_date lastUpdateDate\r\n" + 
			",f.last_updated_by lastUpdatedBy\r\n" + 
			",f.last_update_login lastUpdateLogin\r\n" + 
			",f.version_num versionNum\r\n" + 
			",f.file_id fileId\r\n" + 
			",r.access_Path accessPath\r\n" + 
			",r.file_Name fileName\r\n" + 
			"from \r\n" + 
			"srm_base_notice_files f\r\n" + 
			"left join saaf_base_result_file r on f.file_id = r.file_id\r\n" + 
			"where 1=1";
	
	//查询接收者(组织)
	public static final String QUERY_INST__LIST_SQL="select i.inst_id instId\r\n" + 
			",i.inst_code instCode\r\n" + 
			",i.inst_name instName\r\n" + 
			",r.notice_receiver_id noticeReceiverId\r\n" + 
			",r.notice_id noticeId\r\n" + 
			",r.receiver_type receiverType\r\n" + 
			",r.receiver_id receiverId\r\n" + 
			",r.version_num versionNum\r\n" + 
			",r.created_by createdBy\r\n" + 
			",r.creation_date creationDate\r\n" + 
			",r.last_updated_by lastUpdatedBy\r\n" + 
			",r.last_update_date lastUpdateDate\r\n" + 
			",r.last_update_login lastUpdateLogin\r\n" + 
			"from \r\n" + 
			"saaf_institution i\r\n" + 
			"left join srm_base_notice_receivers r on i.inst_id = r.receiver_id\r\n" + 
			"where 1=1\r\n" + 
			"and r.receiver_type = '2'";
	
	//查询接收者(员工)
	public static final String QUERY_EMPLOYEE__LIST_SQL="select e.employee_id employeeId \r\n" + 
			",e.employee_number employeeNumber \r\n" + 
			",e.employee_name employeeName\r\n" + 
			",r.notice_receiver_id noticeReceiverId \r\n" + 
			",r.notice_id noticeId \r\n" + 
			",r.receiver_type receiverType\r\n" + 
			",r.receiver_id receiverId\r\n" + 
			",r.version_num versionNum\r\n" + 
			",r.created_by createdBy\r\n" + 
			",r.creation_date creationDate\r\n" + 
			",r.last_updated_by lastUpdatedBy\r\n" + 
			",r.last_update_date lastUpdateDate\r\n" + 
			",r.last_update_login lastUpdateLogin\r\n" + 
			"from  \r\n" + 
			"saaf_employees e \r\n" + 
			"left join srm_base_notice_receivers r on e.employee_id = r.receiver_id \r\n" + 
			"where 1=1 \r\n" + 
			"and r.receiver_type = '3'";
	
	//查询接收者(供应商)
	public static final String QUERY_SUPPLIER_LIST_SQL="select i.supplier_id supplierId\r\n" + 
			",i.supplier_number supplierNumber\r\n" + 
			",i.supplier_name supplierName\r\n" + 
			",r.notice_receiver_id noticeReceiverId\r\n" + 
			",r.notice_id noticeId\r\n" + 
			",r.receiver_type receiverType\r\n" + 
			",r.receiver_id receiverId\r\n" + 
			",r.version_num versionNum\r\n" + 
			",r.created_by createdBy\r\n" + 
			",r.creation_date creationDate\r\n" + 
			",r.last_updated_by lastUpdatedBy\r\n" + 
			",r.last_update_date lastUpdateDate\r\n" + 
			",r.last_update_login lastUpdateLogin\r\n" + 
			"from \r\n" + 
			"srm_pos_supplier_info i\r\n" + 
			"left join srm_base_notice_receivers r on i.supplier_id = r.receiver_id\r\n" + 
			"where 1=1\r\n" + 
			"and r.receiver_type = '4'";
	
	//查询公告查看列表
	public static final String QUERY_VIEWERS_SQL="select v.notice_viewer_id noticeViewerId\r\n" + 
			",v.view_date viewDate\r\n" + 
			",v.view_ip viewIP\r\n" + 
			",v.notice_id noticeId\r\n" + 
			",u.user_full_name userName\r\n" + 
			"from \r\n" + 
			"srm_base_notice_viewers v\r\n" + 
			"left join saaf_users u on v.user_id= u.user_id\r\n" + 
			"where 1=1";
	
	private Integer noticeId;
    private String noticeCode;
    private String noticeTitle;
    private String noticeContent;
    private String noticeType;
    private String noticeStatus;
    private String setTopFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;
    private String receiverType;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private String creater;
    private String lastModifier;
    private String noticeTypeMean;
    private String noticeStatusMean;
    private String receiverTypeMean;
    
    private Integer noticeFileId;
    private Integer fileId;
    private String accessPath;
    private String fileName;
    
    private Integer instId;
    private String instCode;
    private String instName;
    
    private Integer employeeId;
    private String employeeNumber;
    private String employeeName;
    
    private Integer supplierId;
    private String supplierNumber;
    private String supplierName;
    
    private Integer noticeReceiverId;
    
    private Integer noticeViewerId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date viewDate;
    private String viewIP;
    private String userName;
    
    private Integer versionNum;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    private Integer lastUpdateLogin;
    
    private Integer receiverId;
    private Integer noticeCount;
    
	public Integer getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeCode() {
		return noticeCode;
	}
	public void setNoticeCode(String noticeCode) {
		this.noticeCode = noticeCode;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	public String getNoticeStatus() {
		return noticeStatus;
	}
	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	public String getSetTopFlag() {
		return setTopFlag;
	}
	public void setSetTopFlag(String setTopFlag) {
		this.setTopFlag = setTopFlag;
	}
	public String getReceiverType() {
		return receiverType;
	}
	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getLastModifier() {
		return lastModifier;
	}
	public void setLastModifier(String lastModifier) {
		this.lastModifier = lastModifier;
	}
	public String getNoticeTypeMean() {
		return noticeTypeMean;
	}
	public void setNoticeTypeMean(String noticeTypeMean) {
		this.noticeTypeMean = noticeTypeMean;
	}
	public String getNoticeStatusMean() {
		return noticeStatusMean;
	}
	public void setNoticeStatusMean(String noticeStatusMean) {
		this.noticeStatusMean = noticeStatusMean;
	}
	public String getReceiverTypeMean() {
		return receiverTypeMean;
	}
	public void setReceiverTypeMean(String receiverTypeMean) {
		this.receiverTypeMean = receiverTypeMean;
	}
	
	public Integer getNoticeFileId() {
		return noticeFileId;
	}
	public void setNoticeFileId(Integer noticeFileId) {
		this.noticeFileId = noticeFileId;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getAccessPath() {
		return accessPath;
	}
	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public Integer getInstId() {
		return instId;
	}
	public void setInstId(Integer instId) {
		this.instId = instId;
	}
	public String getInstCode() {
		return instCode;
	}
	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
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
	public Integer getNoticeReceiverId() {
		return noticeReceiverId;
	}
	public void setNoticeReceiverId(Integer noticeReceiverId) {
		this.noticeReceiverId = noticeReceiverId;
	}
	public Integer getNoticeViewerId() {
		return noticeViewerId;
	}
	public void setNoticeViewerId(Integer noticeViewerId) {
		this.noticeViewerId = noticeViewerId;
	}
	public Date getViewDate() {
		return viewDate;
	}
	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}
	public String getViewIP() {
		return viewIP;
	}
	public void setViewIP(String viewIP) {
		this.viewIP = viewIP;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
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
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}
	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}
	public Integer getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	public Integer getNoticeCount() {
		return noticeCount;
	}
	public void setNoticeCount(Integer noticeCount) {
		this.noticeCount = noticeCount;
	}
    
}
