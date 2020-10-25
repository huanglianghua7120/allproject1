package saaf.common.fmw.spm.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;

public class SrmSpmIndicatorItemsEntity_HI_RO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String QUERY_SUPPLIER_INFO_LIST = "SELECT st.INDICATOR_ITEM_ID indicatorItemId,st.indicator_id indicatorId,st.seq_number seqNumber,st.indicator_item_desc indicatorItemDesc,st.PK1_VALUE pk1Value,st.PK2_VALUE pk2Value,st.PK3_VALUE pk3Value,st.SCORE score  FROM  srm_spm_indicator_items st WHERE 1=1";
	public static String QUERY_COUNT = "SELECT count(1) FROM srm_spm_indicator_items st WHERE 1 = 1";
	private Integer indicatorItemId; // 指标评分项ID
	private Integer indicatorId; // 指标ID
	private Integer seqNumber; // 序号
	private String indicatorItemDesc; // 评分项描述
	private String pk1Value; // 关键值1（大于或等于）
	private String pk2Value; // 关键值2（小于）
	private String pk3Value; // 关键值3（备用）
	private BigDecimal score; // 分值

	public Integer getIndicatorItemId() {
		return indicatorItemId;
	}

	public void setIndicatorItemId(Integer indicatorItemId) {
		this.indicatorItemId = indicatorItemId;
	}

	public Integer getIndicatorId() {
		return indicatorId;
	}

	public void setIndicatorId(Integer indicatorId) {
		this.indicatorId = indicatorId;
	}

	public Integer getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	public String getIndicatorItemDesc() {
		return indicatorItemDesc;
	}

	public void setIndicatorItemDesc(String indicatorItemDesc) {
		this.indicatorItemDesc = indicatorItemDesc;
	}

	public String getPk1Value() {
		return pk1Value;
	}

	public void setPk1Value(String pk1Value) {
		this.pk1Value = pk1Value;
	}

	public String getPk2Value() {
		return pk2Value;
	}

	public void setPk2Value(String pk2Value) {
		this.pk2Value = pk2Value;
	}

	public String getPk3Value() {
		return pk3Value;
	}

	public void setPk3Value(String pk3Value) {
		this.pk3Value = pk3Value;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

}
