package saaf.common.fmw.common.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.*;


/**
 * SaafDocSequencesEntity_HI Entity Object
 * Thu Apr 20 11:13:12 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_doc_sequences")
public class SaafDocSequencesEntity_HI {
    private Integer docId;
    private String docType;
    private String pk1Value;
    private String pk2Value;
    private String pk3Value;
    private String pk4Value;
    private String pk5Value;
    private Integer nextSeqNumber;
    private Integer versionNum;
    private Integer createdBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private Integer operatorUserId;

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    @Id
    @SequenceGenerator(name = "SAAF_DOC_SEQUENCES_S", sequenceName = "SAAF_DOC_SEQUENCES_S", allocationSize = 1)
    @GeneratedValue(generator = "SAAF_DOC_SEQUENCES_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "doc_id", precision = 22, scale = 0)
    public Integer getDocId() {
        return docId;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    @Column(name = "doc_type", nullable = false, length = 30)
    public String getDocType() {
        return docType;
    }

    public void setPk1Value(String pk1Value) {
        this.pk1Value = pk1Value;
    }

    @Column(name = "pk1_value", nullable = true, length = 50)
    public String getPk1Value() {
        return pk1Value;
    }

    public void setPk2Value(String pk2Value) {
        this.pk2Value = pk2Value;
    }

    @Column(name = "pk2_value", nullable = true, length = 50)
    public String getPk2Value() {
        return pk2Value;
    }

    public void setPk3Value(String pk3Value) {
        this.pk3Value = pk3Value;
    }

    @Column(name = "pk3_value", nullable = true, length = 50)
    public String getPk3Value() {
        return pk3Value;
    }

    public void setPk4Value(String pk4Value) {
        this.pk4Value = pk4Value;
    }

    @Column(name = "pk4_value", nullable = true, length = 50)
    public String getPk4Value() {
        return pk4Value;
    }

    public void setPk5Value(String pk5Value) {
        this.pk5Value = pk5Value;
    }

    @Column(name = "pk5_value", nullable = true, length = 50)
    public String getPk5Value() {
        return pk5Value;
    }

    public void setNextSeqNumber(Integer nextSeqNumber) {
        this.nextSeqNumber = nextSeqNumber;
    }

    @Column(name = "next_seq_number", precision = 22, scale = 0)
    public Integer getNextSeqNumber() {
        return nextSeqNumber;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Column(name = "version_num", precision = 22, scale = 0)
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "created_by", precision = 22, scale = 0)
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "creation_date", nullable = false, length = 0)
    public Date getCreationDate() {
        return creationDate;
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
}

