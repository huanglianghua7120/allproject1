package saaf.common.fmw.base.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmBaseExchangeRateEntity_HI_RO Entity Object
 * Tue Nov 27 14:58:36 CST 2018  Auto Generate
 */

public class SrmBaseExchangeRateEntity_HI_RO {


	public static final String QUERY_EXCHANGE_RATE_SQL =
					"SELECT er.exchange_rate_id     exchangeRateId\n" +
					"      ,er.exchange_rate        exchangeRate\n" +
					"      ,er.rate_type            rateType\n" +
					"      ,er.original_currency    originalCurrency\n" +
					"      ,er.target_currency      targetCurrency\n" +
					"      ,er.effective_start_date effectiveStartDate\n" +
					"      ,er.creation_date        creationDate\n" +
					"      ,er.last_update_date     lastUpdateDate\n" +
					"      ,su.user_full_name       creater\n" +
					"      ,sfu.user_full_name      updatePerson\n" +
					"      ,slv.meaning             rateTypeMean\n" +
					"      ,slv2.meaning            originalCurrencyMean\n" +
					"      ,slv3.meaning            targetCurrencyMean\n" +
					"FROM   srm_base_exchange_rate er\n" +
					"LEFT   JOIN saaf_users su\n" +
					"ON     er.created_by = su.user_id\n" +
					"LEFT   JOIN saaf_users sfu\n" +
					"ON     er.last_updated_by = sfu.user_id\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     er.rate_type = slv.lookup_code\n" +
					"AND    slv.lookup_type = 'EXCHANGE_RATE_TYPE'\n" +
					"LEFT   JOIN saaf_lookup_values slv2\n" +
					"ON     er.original_currency = slv2.lookup_code\n" +
					"AND    slv2.lookup_type = 'BANK_CURRENCY'\n" +
					"LEFT   JOIN saaf_lookup_values slv3\n" +
					"ON     er.target_currency = slv3.lookup_code\n" +
					"AND    slv3.lookup_type = 'BANK_CURRENCY'\n" +
					"WHERE  1 = 1\n";

	private Integer exchangeRateId; //汇率ID
    private BigDecimal exchangeRate; //汇率
	private String rateType; //汇率类型（快码）
    private String rateTypeMean; //汇率类型
	private String originalCurrency; //原币种（快码）
	private String targetCurrency; //目标币种（快码）
    private String originalCurrencyMean; //原币种
    private String targetCurrencyMean; //目标币种
    @JSONField(format="yyyy-MM-dd")
    private Date effectiveStartDate; //有效开始日期
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private String creater;
	private String updatePerson;

	public void setExchangeRateId(Integer exchangeRateId) {
		this.exchangeRateId = exchangeRateId;
	}

	
	public Integer getExchangeRateId() {
		return exchangeRateId;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}


	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public String getRateType() { return rateType; }

	public void setRateType(String rateType) { this.rateType = rateType; }

	public String getRateTypeMean() {
		return rateTypeMean;
	}

	public void setRateTypeMean(String rateTypeMean) {
		this.rateTypeMean = rateTypeMean;
	}

	public String getOriginalCurrency() { return originalCurrency; }

	public void setOriginalCurrency(String originalCurrency) { this.originalCurrency = originalCurrency; }

	public String getTargetCurrency() { return targetCurrency; }

	public void setTargetCurrency(String targetCurrency) { this.targetCurrency = targetCurrency; }

	public String getOriginalCurrencyMean() {
		return originalCurrencyMean;
	}

	public void setOriginalCurrencyMean(String originalCurrencyMean) {
		this.originalCurrencyMean = originalCurrencyMean;
	}

	public String getTargetCurrencyMean() {
		return targetCurrencyMean;
	}

	public void setTargetCurrencyMean(String targetCurrencyMean) {
		this.targetCurrencyMean = targetCurrencyMean;
	}

	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
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

	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}
}
