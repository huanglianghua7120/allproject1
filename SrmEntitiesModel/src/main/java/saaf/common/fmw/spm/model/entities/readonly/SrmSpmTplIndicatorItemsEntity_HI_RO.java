package saaf.common.fmw.spm.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;

public class SrmSpmTplIndicatorItemsEntity_HI_RO implements Serializable {

	private static final long serialVersionUID = 1L;
	public static String QUERY_DIMENSION_INFO_LIST = "SELECT \r\n" + "  spit.`TPL_INDICATOR_ID` tplIndicatorId,\r\n"
			+ "  spit.`TPL_INDICATOR_ITEM_ID` tplIndicatorItemId,\r\n" + "  spit.`INDICATOR_ITEM_ID` indicatorItemId,\r\n" + "  spit.`SCORE` score,\r\n"
			+ "  spit.`DESCRIPTION` description,\r\n" + "  sp.`PK1_VALUE` pk1Value,\r\n" + "  sp.`PK2_VALUE` pk2Value,\r\n" + "  sp.`PK3_VALUE` pk3Value,\r\n"
			+ "  sp.`INDICATOR_ITEM_DESC` indicatorItemDesc,\r\n" + "  sp.`SCORE`  scoret\r\n" + "FROM\r\n"
			+ "  srm_spm_tpl_indicator_items spit \r\n" + "  LEFT JOIN srm_spm_indicator_items sp \r\n"
			+ "    ON spit.`INDICATOR_ITEM_ID` = sp.`INDICATOR_ITEM_ID`  WHERE 1=1 ";
	private Integer tplIndicatorItemId;
	private Integer tplIndicatorId;
	private Integer indicatorItemId;
	private BigDecimal score; // 分值
	private String description;

	private String indicatorItemDesc; // 评分项描述
	private String pk1Value; // 关键值1（大于或等于）
	private String pk2Value; // 关键值2（小于）
	private String pk3Value; // 关键值3（备用）
	private BigDecimal scoret; // 分值
	public Integer getTplIndicatorItemId() {
		return tplIndicatorItemId;
	}
	public void setTplIndicatorItemId(Integer tplIndicatorItemId) {
		this.tplIndicatorItemId = tplIndicatorItemId;
	}
	public Integer getTplIndicatorId() {
		return tplIndicatorId;
	}
	public void setTplIndicatorId(Integer tplIndicatorId) {
		this.tplIndicatorId = tplIndicatorId;
	}
	public Integer getIndicatorItemId() {
		return indicatorItemId;
	}
	public void setIndicatorItemId(Integer indicatorItemId) {
		this.indicatorItemId = indicatorItemId;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public BigDecimal getScoret() {
		return scoret;
	}
	public void setScoret(BigDecimal scoret) {
		this.scoret = scoret;
	}
	
	

}
