package saaf.common.fmw.solr.bean;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Admin on 2017/9/6.
 */
@SolrDocument(solrCoreName = "collection1")
public class MemberCardInfoBean implements SolrBean {

    @Id
    @Field(ID)
    private Integer memberCardId;
    @Field(INT_FIELD)
    private Integer memberId;
    @Field(INT_FIELD)
    private Integer cardLevelId;
    @Field(STRING_FIELD)
    private String cardLevelName;
    @Field(STRING_FIELD)
    private String cardNumber;
    @Field(STRING_FIELD)
    private String cardDescription;
    @Field(STRING_FIELD)
    private String cardPhoneNumber;
    @Field(DATE_FIELD)
    private Date activateDate;
    @Field(DOUBLE_FIELD)
    private BigDecimal cardBalance;
    @Field(STRING_FIELD)
    private String chipNumber;
    @Field(STRING_FIELD)
    private String paymentPassword;
    @Field(STRING_FIELD)
    private String status;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Field(DATE_FIELD)
    private Date expiryDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Field(DATE_FIELD)
    private Date creationDate;
    @Field(INT_FIELD)
    private Integer createdBy;
    @Field(INT_FIELD)
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Field(DATE_FIELD)
    private Date lastUpdateDate;
    @Field(INT_FIELD)
    private Integer lastUpdateLogin;


    public Integer getMemberCardId() {
        return memberCardId;
    }

    public void setMemberCardId(Integer memberCardId) {
        this.memberCardId = memberCardId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getCardLevelId() {
        return cardLevelId;
    }

    public void setCardLevelId(Integer cardLevelId) {
        this.cardLevelId = cardLevelId;
    }

    public String getCardLevelName() {
        return cardLevelName;
    }

    public void setCardLevelName(String cardLevelName) {
        this.cardLevelName = cardLevelName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public String getCardPhoneNumber() {
        return cardPhoneNumber;
    }

    public void setCardPhoneNumber(String cardPhoneNumber) {
        this.cardPhoneNumber = cardPhoneNumber;
    }

    public Date getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(Date activateDate) {
        this.activateDate = activateDate;
    }

    public BigDecimal getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(BigDecimal cardBalance) {
        this.cardBalance = cardBalance;
    }

    public String getChipNumber() {
        return chipNumber;
    }

    public void setChipNumber(String chipNumber) {
        this.chipNumber = chipNumber;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }
}
