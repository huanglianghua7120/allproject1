package saaf.common.fmw.okc.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



public class SrmOkcContractAttachmentsEntity_HI_RO implements Serializable {

    public static String SRM_OKC_CONTRACT_ATTACHMENTS_QUERY_SQL = "select " +
   "   t.contract_attachment_id as contractAttachmentId ,  \r\n" +
   "   t.contract_id as contractId ,  \r\n" +
   "   t.attachment_file_id as attachmentFileId ,  \r\n" +
   "   t.attachment_comments as attachmentComments ,  \r\n" +
   "   t.version_num as versionNum ,  \r\n" +
   "   t.creation_date as creationDate ,  \r\n" +
   "   t.created_by as createdBy ,  \r\n" +
   "   t.last_update_date as lastUpdateDate ,  \r\n" +
   "   t.last_updated_by as lastUpdatedBy ,  \r\n" +
   "   t.last_update_login as lastUpdateLogin ,  \r\n" +
   "   t.attribute_category as attributeCategory ,  \r\n" +
   "   t.attribute1 as attribute1 ,  \r\n" +
   "   t.attribute2 as attribute2 ,  \r\n" +
   "   t.attribute3 as attribute3 ,  \r\n" +
   "   t.attribute4 as attribute4 ,  \r\n" +
   "   t.attribute5 as attribute5 ,  \r\n" +
   "   t.attribute6 as attribute6 ,  \r\n" +
   "   t.attribute7 as attribute7 ,  \r\n" +
   "   t.attribute8 as attribute8 ,  \r\n" +
   "   t.attribute9 as attribute9 ,  \r\n" +
   "   t.attribute10 as attribute10 ,  \r\n" +
   "   t.attribute11 as attribute11 ,  \r\n" +
   "   t.attribute12 as attribute12 ,  \r\n" +
   "   t.attribute13 as attribute13 ,  \r\n" +
   "   t.attribute14 as attribute14 ,  \r\n" +
   "   t.attribute15 as attribute15 ,  \r\n" +
   "   t.attribute16 as attribute16 ,  \r\n" +
   "   t.attribute17 as attribute17 ,  \r\n" +
   "   t.attribute18 as attribute18 ,  \r\n" +
   "   t.attribute19 as attribute19 ,  \r\n" +
   "   t.attribute20 as attribute20 ,  \r\n" +
   "   t.attribute21 as attribute21 ,  \r\n" +
   "   t.attribute22 as attribute22 ,  \r\n" +
   "   t.attribute23 as attribute23 ,  \r\n" +
   "   t.attribute24 as attribute24 ,  \r\n" +
   "   t.attribute25 as attribute25 ,  \r\n" +
   "   t.attribute26 as attribute26 ,  \r\n" +
   "   t.attribute27 as attribute27 ,  \r\n" +
   "   t.attribute28 as attribute28 ,  \r\n" +
   "   t.attribute29 as attribute29 ,  \r\n" +
   "   t.attribute30 as attribute30 ,  \r\n" +
    "   u.user_full_name as createdByName ,  \r\n" +
    " f.access_Path as accessPath ,f.file_Name  as fileName \r\n" +
    "    FROM srm_okc_contract_attachments t  \r\n" +
            " INNER JOIN  saaf_users u  on u.user_id = t.created_by  \r\n" +
            "  INNER JOIN saaf_base_result_file f on t.attachment_file_id = f.file_id " +
            "       WHERE t.contract_id = :contractId ";

	private Integer contractAttachmentId;

	private String accessPath;
	private String fileName;
	private String createdByName;

	private Integer contractId;

	private Integer attachmentFileId;

	private String attachmentComments;

	private Integer versionNum;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;

	private Integer createdBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;

	private Integer lastUpdatedBy;

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

	private Integer attribute16;

	private Integer attribute17;

	private Integer attribute18;

	private Integer attribute19;

	private Integer attribute20;

	private BigDecimal attribute21;

	private BigDecimal attribute22;

	private BigDecimal attribute23;

	private BigDecimal attribute24;

	private BigDecimal attribute25;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute26;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute27;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute28;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute29;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute30;


    public SrmOkcContractAttachmentsEntity_HI_RO(){
        super();
    }


    public Integer  getContractAttachmentId() {
        return contractAttachmentId;
    }

    public void setContractAttachmentId( Integer contractAttachmentId) {
        this.contractAttachmentId = contractAttachmentId;
    }


    public Integer  getContractId() {
        return contractId;
    }

    public void setContractId( Integer contractId) {
        this.contractId = contractId;
    }


    public Integer  getAttachmentFileId() {
        return attachmentFileId;
    }

    public void setAttachmentFileId( Integer attachmentFileId) {
        this.attachmentFileId = attachmentFileId;
    }


    public String  getAttachmentComments() {
        return attachmentComments;
    }

    public void setAttachmentComments( String attachmentComments) {
        this.attachmentComments = attachmentComments;
    }


    public Integer  getVersionNum() {
        return versionNum;
    }

    public void setVersionNum( Integer versionNum) {
        this.versionNum = versionNum;
    }


    public Date  getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( Date creationDate) {
        this.creationDate = creationDate;
    }


    public Integer  getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy( Integer createdBy) {
        this.createdBy = createdBy;
    }


    public Date  getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate( Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }


    public Integer  getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy( Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }


    public Integer  getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin( Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }


    public String  getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory( String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }


    public String  getAttribute1() {
        return attribute1;
    }

    public void setAttribute1( String attribute1) {
        this.attribute1 = attribute1;
    }


    public String  getAttribute2() {
        return attribute2;
    }

    public void setAttribute2( String attribute2) {
        this.attribute2 = attribute2;
    }


    public String  getAttribute3() {
        return attribute3;
    }

    public void setAttribute3( String attribute3) {
        this.attribute3 = attribute3;
    }


    public String  getAttribute4() {
        return attribute4;
    }

    public void setAttribute4( String attribute4) {
        this.attribute4 = attribute4;
    }


    public String  getAttribute5() {
        return attribute5;
    }

    public void setAttribute5( String attribute5) {
        this.attribute5 = attribute5;
    }


    public String  getAttribute6() {
        return attribute6;
    }

    public void setAttribute6( String attribute6) {
        this.attribute6 = attribute6;
    }


    public String  getAttribute7() {
        return attribute7;
    }

    public void setAttribute7( String attribute7) {
        this.attribute7 = attribute7;
    }


    public String  getAttribute8() {
        return attribute8;
    }

    public void setAttribute8( String attribute8) {
        this.attribute8 = attribute8;
    }


    public String  getAttribute9() {
        return attribute9;
    }

    public void setAttribute9( String attribute9) {
        this.attribute9 = attribute9;
    }


    public String  getAttribute10() {
        return attribute10;
    }

    public void setAttribute10( String attribute10) {
        this.attribute10 = attribute10;
    }


    public String  getAttribute11() {
        return attribute11;
    }

    public void setAttribute11( String attribute11) {
        this.attribute11 = attribute11;
    }


    public String  getAttribute12() {
        return attribute12;
    }

    public void setAttribute12( String attribute12) {
        this.attribute12 = attribute12;
    }


    public String  getAttribute13() {
        return attribute13;
    }

    public void setAttribute13( String attribute13) {
        this.attribute13 = attribute13;
    }


    public String  getAttribute14() {
        return attribute14;
    }

    public void setAttribute14( String attribute14) {
        this.attribute14 = attribute14;
    }


    public String  getAttribute15() {
        return attribute15;
    }

    public void setAttribute15( String attribute15) {
        this.attribute15 = attribute15;
    }


    public Integer  getAttribute16() {
        return attribute16;
    }

    public void setAttribute16( Integer attribute16) {
        this.attribute16 = attribute16;
    }


    public Integer  getAttribute17() {
        return attribute17;
    }

    public void setAttribute17( Integer attribute17) {
        this.attribute17 = attribute17;
    }


    public Integer  getAttribute18() {
        return attribute18;
    }

    public void setAttribute18( Integer attribute18) {
        this.attribute18 = attribute18;
    }


    public Integer  getAttribute19() {
        return attribute19;
    }

    public void setAttribute19( Integer attribute19) {
        this.attribute19 = attribute19;
    }


    public Integer  getAttribute20() {
        return attribute20;
    }

    public void setAttribute20( Integer attribute20) {
        this.attribute20 = attribute20;
    }


    public BigDecimal  getAttribute21() {
        return attribute21;
    }

    public void setAttribute21( BigDecimal attribute21) {
        this.attribute21 = attribute21;
    }


    public BigDecimal  getAttribute22() {
        return attribute22;
    }

    public void setAttribute22( BigDecimal attribute22) {
        this.attribute22 = attribute22;
    }


    public BigDecimal  getAttribute23() {
        return attribute23;
    }

    public void setAttribute23( BigDecimal attribute23) {
        this.attribute23 = attribute23;
    }


    public BigDecimal  getAttribute24() {
        return attribute24;
    }

    public void setAttribute24( BigDecimal attribute24) {
        this.attribute24 = attribute24;
    }


    public BigDecimal  getAttribute25() {
        return attribute25;
    }

    public void setAttribute25( BigDecimal attribute25) {
        this.attribute25 = attribute25;
    }


    public Date  getAttribute26() {
        return attribute26;
    }

    public void setAttribute26( Date attribute26) {
        this.attribute26 = attribute26;
    }


    public Date  getAttribute27() {
        return attribute27;
    }

    public void setAttribute27( Date attribute27) {
        this.attribute27 = attribute27;
    }


    public Date  getAttribute28() {
        return attribute28;
    }

    public void setAttribute28( Date attribute28) {
        this.attribute28 = attribute28;
    }


    public Date  getAttribute29() {
        return attribute29;
    }

    public void setAttribute29( Date attribute29) {
        this.attribute29 = attribute29;
    }


    public Date  getAttribute30() {
        return attribute30;
    }

    public void setAttribute30( Date attribute30) {
        this.attribute30 = attribute30;
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

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }
}
